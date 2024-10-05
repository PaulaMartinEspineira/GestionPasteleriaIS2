package integracion.Facturas;

import java.util.Collection;
import negocio.Facturas.TDatosVenta;
import negocio.Facturas.TFactura;
import negocio.Facturas.TLineaFactura;
import org.json.*;
import integracion.Factoria.FactoriaAbstractaIntegracion;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;

public class DAOFacturaImp implements DAOFactura {
	private String filename = "resources/Facturas.json";

	public int crearFactura(TFactura f) {
		boolean exito = true;
		try {
			// Accedemos al json
			JSONObject ji = getJSONFromFile(filename);
			JSONArray ja = ji.getJSONArray("ListaFacturas");
			int next_id = ji.getInt("next_id");

			// Asignamos el id y lo dejamos preparado para la siguiente factura
			f.setIdFactura(next_id);
			next_id++;

			// Creamos el json de la nueva factura
			JSONObject jo = new JSONObject();
			jo.put("id_factura", f.getIdFactura());
			jo.put("fecha", f.getFecha());
			jo.put("id_cliente", f.getIdCliente());
			jo.put("id_vendedor", f.getIdVendedor());
			jo.put("precio", Math.round(f.getPrecio_total() * 1000.0) / 1000.0);
			jo.put("activa", true);
			ja.put(jo);// lo añadimos a nuestra lista de facturas

			// Escribimos en el json
			writeJSONObject(filename, ja, next_id);

		} catch (JSONException e) {
			exito = false;
		}
		if (exito) {
			return f.getIdFactura();
		} else
			return -1;
	}

	public TFactura buscarFactura(int id_factura) {
		// Accedemos al json
		TFactura factura = null;
		JSONArray ja = getFacturasJArray(filename);

		// Buscamos la factura en nuestro array
		int i = searchInJArray(id_factura, ja);
		if (i != -1) {
			// Si la factura existe buscamos sus lineas
			JSONObject jo = ja.getJSONObject(i);
			factura = new TFactura(jo.getInt("id_factura"), jo.getInt("id_cliente"), jo.getInt("id_vendedor"),
					jo.getDouble("precio"), jo.getString("fecha"), jo.getBoolean("activa"));
		}
		return factura;
	}

	public boolean modificarFactura(int id_f, int id_c, int id_v, String fecha) {
		boolean exito = false;
		try {
			// Accedemos al json
			JSONObject ji = getJSONFromFile(filename);
			JSONArray ja = ji.getJSONArray("ListaFacturas");
			int next_id = ji.getInt("next_id");
			// Buscamos la factura en nuestro array
			int i = searchInJArray(id_f, ja);
			// solo se puede modificar la factura solo si existe y si esta activa
			if (i != -1 && ja.getJSONObject(i).getBoolean("activa")) {
				// Si la factura existe la modificamos
				exito = true;
				ja.getJSONObject(i).put("fecha", fecha);
				ja.getJSONObject(i).put("id_cliente", id_c);
				ja.getJSONObject(i).put("id_vendedor", id_v);

				// Escribimos en el json
				writeJSONObject(filename, ja, next_id);
			}
		} catch (JSONException e) {
			exito = false;
		}
		return exito;
	}

	public Collection<TFactura> listarFacturas() {
		// Accedemos al json
		ArrayList<TFactura> facturas = new ArrayList<TFactura>();
		JSONArray ja = getFacturasJArray(filename);

		// Si existe el array lo recorremos y añadimos las facturas activas
		if (ja != null) {
			for (int i = 0; i < ja.length(); i++) {
				JSONObject jo = ja.getJSONObject(i);
				if (jo.getBoolean("activa")) {
					facturas.add(
							new TFactura(jo.getInt("id_factura"), jo.getInt("id_cliente"), jo.getInt("id_vendedor"),
									jo.getDouble("precio"), jo.getString("fecha"), jo.getBoolean("activa")));
				}
			}
		}
		return facturas;
	}

//EXTRAS
	public Collection<TFactura> listarFacturasPorCliente(int id_cliente) {
		// Accedemos al json
		ArrayList<TFactura> facturas = new ArrayList<TFactura>();
		JSONArray ja = getFacturasJArray(filename);

		// Si existe el array lo recorremos y añadimos las facturas activas del cliente
		// pedido
		if (ja != null) {
			for (int i = 0; i < ja.length(); i++) {
				if (ja.getJSONObject(i).getInt("id_cliente") == id_cliente
						&& ja.getJSONObject(i).getBoolean("activa")) {
					JSONObject jo = ja.getJSONObject(i);
					facturas.add(
							new TFactura(jo.getInt("id_factura"), jo.getInt("id_cliente"), jo.getInt("id_vendedor"),
									jo.getDouble("precio"), jo.getString("fecha"), jo.getBoolean("activa")));
				}
			}
		}
		return facturas;
	}

	public boolean devolucionFactura(TFactura factura) {
		// Si se ha devuelto todo la factura "se borra"
		if (factura.getPrecio_total() <= 0) {
			factura.setPrecio_total(0);
			factura.setActivo(false);
		}
		boolean exito = true;
		try {
			// Accedemos al json
			JSONObject ji = getJSONFromFile(filename);
			JSONArray ja = ji.getJSONArray("ListaFacturas");
			int next_id = ji.getInt("next_id");
			// cambiamos el precio final y, si es necesario, su estado
			ja.getJSONObject(factura.getIdFactura()).put("precio",
					Math.round(factura.getPrecio_total() * 1000.0) / 1000.0);
			ja.getJSONObject(factura.getIdFactura()).put("activa", factura.getActivo());
			// Escribimos en el json
			writeJSONObject(filename, ja, next_id);
		} catch (JSONException e) {
			exito = false;
		}
		return exito;
	}

//FUNCIONES AUXILIARES
	private int searchInJArray(int id_f, JSONArray ja) {
		// devuele la posicion de la factura en el array, o -1 si no existe
		if (ja == null) {
			return -1;
		}
		int i = 0;
		while (i < ja.length() && ja.getJSONObject(i).getInt("id_factura") != id_f) {
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

	private JSONArray getFacturasJArray(String filename) {
		// Devuelve el JSONArray guardado en el archivo filename
		JSONObject jO = getJSONFromFile(filename);
		if (jO == null) {
			return null;
		} else {
			return jO.getJSONArray("ListaFacturas");
		}
	}

	private boolean writeJSONObject(String filename, JSONArray ja, int next_id) {
		// Escribimos el fichero otra vez con los datos actualizados
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			JSONObject jo = new JSONObject();
			jo.put("ListaFacturas", ja);
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
