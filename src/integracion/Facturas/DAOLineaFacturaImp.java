package integracion.Facturas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Collection;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import negocio.Facturas.TFactura;
import negocio.Facturas.TLineaFactura;

public class DAOLineaFacturaImp implements DAOLineaFactura {
	private String filename = "resources/LineasFactura.json";

	public int crearLineaFactura(TLineaFactura lf) {
		boolean exito = true;
		try {
			// Accedemos al json
			JSONObject ji = getJSONFromFile(filename);
			JSONArray ja = ji.getJSONArray("ListaLineasFacturas");
			int next_id = ji.getInt("next_id");

			// Asignamos el id y lo dejamos preparado para la siguiente linea factura
			lf.setIdLinea(next_id);
			next_id++;

			// Creamos el json de la nueva linea factura
			JSONObject jo = new JSONObject();
			jo.put("id_linea", lf.getIdLinea());
			jo.put("id_factura", lf.getIdFactura());
			jo.put("id_producto", lf.getIdProducto());
			jo.put("cantidad", lf.getCantidad());
			jo.put("precio",
					Math.round(lf.getPrecio() * 1000.0) / 1000.0);
			jo.put("activa", lf.getActivo());
			ja.put(jo);// lo añadimos a nuestra lista de lineas de facturas

			/// Escribimos en el json
			writeJSONObject(filename, ja, next_id);

		} catch (JSONException e) {
			exito = false;
		}
		if (exito) {
			return lf.getIdLinea();
		} else
			return -1;
	}

	public TLineaFactura buscarLineaFactura(int id) {
		// Accedemos al json
		TLineaFactura linea_factura = null;
		JSONArray ja = getLineasFacturasJArray(filename);

		// Buscamos la factura en nuestro array
		int i = searchInJArray(id, ja);
		if (i != -1) {
			// Si la linea factura existe la creamos
			linea_factura = new TLineaFactura(ja.getJSONObject(i).getInt("id_producto"),
					ja.getJSONObject(i).getInt("id_factura"), ja.getJSONObject(i).getInt("id_linea"),
					ja.getJSONObject(i).getInt("cantidad"), ja.getJSONObject(i).getDouble("precio"),
					ja.getJSONObject(i).getBoolean("activa"));
		}
		return linea_factura;
	}

	public Collection<TLineaFactura> mostrarLineasFactura() {
		// Accedemos al json
		ArrayList<TLineaFactura> lineas_factura = new ArrayList<TLineaFactura>();
		JSONArray ja = getLineasFacturasJArray(filename);

		// Si existe el array lo recorremos y añadimos las lineas de facturas
		// (como solo mostramos las lineas al buscar la factura tenemos que añadir tanto
		// las activas como las no activas)
		if (ja != null) {
			for (int i = 0; i < ja.length(); i++) {
				lineas_factura.add(new TLineaFactura(ja.getJSONObject(i).getInt("id_producto"),
						ja.getJSONObject(i).getInt("id_factura"), ja.getJSONObject(i).getInt("id_linea"),
						ja.getJSONObject(i).getInt("cantidad"), ja.getJSONObject(i).getDouble("precio"),
						ja.getJSONObject(i).getBoolean("activa")));
			}
		}
		return lineas_factura;
	}

//EXTRAS
	public int modificarLineaFactura(TLineaFactura lf, ArrayList<TLineaFactura> lineas_factura) {
		int sol = -1;
		try {
			// Accedemos al json
			JSONObject ji = getJSONFromFile(filename);
			JSONArray ja = ji.getJSONArray("ListaLineasFacturas");
			int next_id = ji.getInt("next_id");
			// buscamos la linea en la lista de lineas de nuestra factura
			// cada producto tiene asociada una sola linea, la buscamos por id de producto
			int i = 0;
			while (i < lineas_factura.size() && lineas_factura.get(i).getIdProducto() != lf.getIdProducto()) {
				i++;
			}

			if (i < lineas_factura.size()) {
				// Si existe el producto en la factura buscamos su linea
				TLineaFactura linea_factura = buscarLineaFactura(lineas_factura.get(i).getIdLinea());
				// calculamos el precio del producto
				double precio = linea_factura.getPrecio() / linea_factura.getCantidad();
				// si sobran unidades despues de quitar las devueltas
				if (lf.getCantidad() < linea_factura.getCantidad()) {
					linea_factura.setCantidadProducto(linea_factura.getCantidad() - lf.getCantidad());
					linea_factura.setPrecio(linea_factura.getPrecio() - (lf.getCantidad() * precio));
					sol = lf.getCantidad();
				} else {// si no sobran unidades despues de quitar las devueltas la linea "se borra"
					sol = linea_factura.getCantidad();
					linea_factura.setCantidadProducto(0);
					linea_factura.setPrecio(0);
					linea_factura.setActivo(false);
					ja.getJSONObject(linea_factura.getIdLinea()).put("activa", false);
				}
				ja.getJSONObject(linea_factura.getIdLinea()).put("cantidad", linea_factura.getCantidad());
				ja.getJSONObject(linea_factura.getIdLinea()).put("precio",
						Math.round(linea_factura.getPrecio() * 1000.0) / 1000.0);

				// Escribimos en el json
				writeJSONObject(filename, ja, next_id);
			}
		} catch (JSONException e) {
		}
		return sol;
	}

// FUNCIONES AUXILIARES
	private int searchInJArray(int id_l, JSONArray ja) {
		// devuele la posicion de la linea factura en el array, o -1 si no existe
		if (ja == null) {
			return -1;
		}
		int i = 0;
		while (i < ja.length() && ja.getJSONObject(i).getInt("id_linea") != id_l) {
			i++;
		}
		if (i == ja.length()) {
			return -1;
		}
		return i;
	}

	private JSONObject getJSONFromFile(String filename) {
		// Devuelve el JSONObject del archivo filename
		JSONObject jO;
		try {
			InputStream in = new FileInputStream(new File(filename));
			jO = new JSONObject(new JSONTokener(in));

		} catch (FileNotFoundException e) {
			jO = null;
		}
		return jO;
	}

	private JSONArray getLineasFacturasJArray(String filename) {
		// Devuelve el JSONArray guardado en el archivo filename
		JSONObject jO = getJSONFromFile(filename);
		if (jO == null) {
			return null;
		} else {
			return jO.getJSONArray("ListaLineasFacturas");
		}
	}

	private boolean writeJSONObject(String filename, JSONArray ja, int next_id) {
		// Escribimos el fichero otra vez con los datos actualizados
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			JSONObject jo = new JSONObject();
			jo.put("ListaLineasFacturas", ja);
			jo.put("next_id", next_id);
			bw.write(jo.toString(3));

			if (bw != null) {// cerramos el fichero siempre
				bw.close();
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
