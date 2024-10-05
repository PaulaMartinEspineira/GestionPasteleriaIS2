package integracion.Producto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.InputStream;

import java.util.Collection;
import java.util.ArrayList;

import negocio.Producto.TProducto;
import negocio.Producto.TDulce;
import negocio.Producto.TPan;
import negocio.Producto.TBebida;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class DAOProductoImp implements DAOProducto {
	private static String _PRODUCTO_FILENAME = "resources/Productos.json";
	
	@Override
	public int altaProducto(TProducto producto) {
		JSONObject jProd = createJSON(producto);
		int next_id;
		JSONArray ja;
		
		JSONObject jO = getJSONFromFile();
		if (jO != null) {
			ja = jO.getJSONArray("productos");
			next_id = jO.getInt("next_id");
			jProd.put("id", next_id);
			ja.put(jProd);
		}
		else {
			jO = new JSONObject();
			next_id = 0;
			jProd.put("id", next_id);
			ja = new JSONArray();
			ja.put(jProd);
		}
		
		jO.put("productos", ja);
		jO.put("next_id", next_id+1);
		
		if (writeJSONObject(jO)) {
			return next_id;
		}
		else {
			return -1;
		}
		
	}
	
	@Override
	public int actualizarProducto(TProducto producto) {
		JSONObject jProd = createJSON(producto);
		JSONObject jO = getJSONFromFile();
		
		if (jO != null) {
			JSONArray ja = jO.getJSONArray("productos");
			ja.put(producto.getId(), jProd);
			jO.put("productos", ja);
		}
		
		else {
			return -1;
		}
		
		if (writeJSONObject(jO)) {
			return producto.getId();
		}
		else {
			return -1;
		}
		
	}
	
	@Override
	public void bajaProducto(int id) {
		JSONArray ja;
		JSONObject jO = getJSONFromFile();
		
		if (jO != null) {
			ja = jO.getJSONArray("productos");
			JSONObject jProd = ja.getJSONObject(id);
			jProd.put("activo", false);
			ja.put(id, jProd);
			jO.put("productos", ja);
		}
		
		writeJSONObject(jO);
	}

	
	public TProducto buscarProducto(String nombre) {
		JSONArray ja = getProductosJArray();
		TProducto prod = searchInJArray(nombre, ja);
		if (prod == null) {
			return null;
		}
		return prod;
	}
	
	public TProducto buscarProducto(int id) {
		JSONArray ja = getProductosJArray();
		JSONObject prodJO = getObjectInIndex(ja, id);
		if (prodJO == null) {
				return null;
		}
		return createTProducto(prodJO);
	}
	
	
	
	public Collection<TProducto> listarProductos() {
		Collection<TProducto> listaProductos = new ArrayList<TProducto>();
		
		JSONArray ja = getProductosJArray();
		if (ja != null) {
			for (int i = 0; i < ja.length(); i++) {
				TProducto prod = createTProducto(ja.getJSONObject(i));
				if (prod.getActivo()) {
					listaProductos.add(prod);
				}
			}
		}
		return listaProductos;
	}
	
	private TProducto createTProducto(JSONObject jO) {
		// Crea un transfer de producto a partir del JSONObject correspondiente
		String nombre = jO.getString("nombre");
		double precio = jO.getDouble("precio");
		String alergenos = jO.getString("alergenos");
		int stock = jO.getInt("stock");
		String tipo = jO.getString("tipo");
		int marcaId = jO.getInt("marcaId");
		int id = jO.getInt("id");
		boolean activo = jO.getBoolean("activo");
		// Como tenemos herencia, tenemos que diferenciar las diferentes subclases/tipos
		if (tipo.equals("Dulce")) {
			String relleno = jO.getString("relleno");
			TDulce dulce = new TDulce(nombre, precio, alergenos, stock, marcaId, relleno);
			dulce.setActivo(activo);
			dulce.setId(id);
			return dulce;
		}
		else if (tipo.equals("Pan")) {
			boolean sal = jO.getBoolean("sal");
			boolean integral = jO.getBoolean("integral");
			TPan pan = new TPan(nombre, precio, alergenos, stock, marcaId, sal, integral);
			pan.setActivo(activo);
			pan.setId(id);
			return pan;
		}
		else if (tipo.equals("Bebida")){
			String tamanyo = jO.getString("tamaño");
			TBebida bebida = new TBebida(nombre, precio, alergenos, stock, marcaId, tamanyo);
			bebida.setActivo(activo);
			bebida.setId(id);
			return bebida;
		}
		return null;
	}
	
	
	private TProducto searchInJArray(String nombre, JSONArray ja) {
		// Nos devuelve el TProducto con nombre 'nombre'  en la JSONArray ja
		// Devuelve null si no existe
		if (ja == null) {
			return null;
		}
		int i = 0;
		while (i < ja.length() && !(ja.getJSONObject(i).get("nombre").equals(nombre))) {
			i++;
		}
		if (i == ja.length()) {
			return null;
		}
		return createTProducto(ja.getJSONObject(i));
	}
	
	private JSONObject getJSONFromFile() {
		// Devuelve el JSONObject del archivo de productos
		JSONObject jO;
		try {
			InputStream in  = new FileInputStream(new File(_PRODUCTO_FILENAME));
			jO = new JSONObject(new JSONTokener(in));
			
		} catch (FileNotFoundException e) {
			jO = null;	
		}
		return jO;
	}
	
	
	private JSONArray getProductosJArray() {
		// Devuelve el JSONArray guardado en el archivo productos
		JSONObject jO = getJSONFromFile(); 
		if (jO == null) {
			return null;
		}
		else {
			return jO.getJSONArray("productos");
		}
	}
	
	private JSONObject createJSON(TProducto producto) {
		// Crea un JSONObject a partir de un transfer de producto
		JSONObject jProd = new JSONObject();
		String tipo = producto.getTipo();
		jProd.put("nombre", producto.getNombre());
		jProd.put("precio", producto.getPrecio());
		jProd.put("alergenos", producto.getAlergenos());
		jProd.put("stock", producto.getStock());
		jProd.put("tipo", tipo);
		jProd.put("marcaId", producto.getMarca());
		jProd.put("activo", producto.getActivo());
		// Tenemos en cuenta las subclases dadas por la herencia
		if (tipo.equals("Dulce")) {
			jProd.put("relleno", ((TDulce)producto).getRelleno());
		}
		else if (tipo.equals("Pan")) {
			jProd.put("integral", ((TPan)producto).getIntegral());
			jProd.put("sal", ((TPan)producto).getSal());
		}
		else {
			jProd.put("tamaño", ((TBebida)producto).getTamanyo());
		}
		jProd.put("id", producto.getId());
		return jProd;
	}
	
	private boolean writeJSONObject(JSONObject jO) {
		// Escribe el JSONObject jO al archivo filename
		try {
			BufferedWriter bW = new BufferedWriter(new FileWriter(_PRODUCTO_FILENAME, false));
			bW.write(jO.toString(3));
			bW.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	private JSONObject getObjectInIndex(JSONArray ja, int index) {
		if (ja != null && index >= 0 && index < ja.length()) {
			return ja.getJSONObject(index);
		}
		else {
			return null;
		}
	}

}
