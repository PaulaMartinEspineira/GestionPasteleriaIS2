package integracion.Marca;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import negocio.Marca.TMarcaProveedor;


public class DAOMarcaProveedorImp implements DAOMarcaProveedor {

	@Override
	public int altaMarcaProveedor(TMarcaProveedor marcaProveedor) {
		//cargamos los datos de la nueva marcaProv en un JSON
		JSONObject jo = createJSON(marcaProveedor);
		
		//obtenemos el JSON con toda la info 
		JSONObject JO = getJSONFromFile();
		
		JSONArray JA; int next_id = 0;
		if (JO == null) {
			JO = new JSONObject();
			JA = new JSONArray();
			JA.put(jo);
		}
		else {
			//Obtenemos los datos del JSON
			JA = JO.getJSONArray("ListaMarcasProveedor");
		    next_id = JO.getInt("next_id");
		    
		    
		    jo.put("Id", next_id);
			JA.put(jo);
		}
		
		//insertamos la nueva marcaProv en el JSON
		JO.put("ListaMarcasProveedor", JA);
		JO.put("next_id", next_id+1);
		
		
		marcaProveedor.setID(next_id);
		
		
		//Escribimos el nuevo JSON con toda la informacion
		if (writeJSONObject(JO)) {
			return next_id;
		}
		else {
			return -1;
		}
	}

	@Override
	public boolean bajaMarcaProveedor(String nombreMarca, String nombreProv) { //bucle en el que vamos dando de baja todas las lineas
		//Accedemos a los datos guardados
		JSONObject JO = getJSONFromFile();
		JSONArray JA;
		
		if (JO == null) {
			return false; 
		}
		else {
			JA = JO.getJSONArray("ListaMarcasProveedor");
			
			String nombre, nombreJSON;
			if (nombreMarca != null) {
				nombreJSON = "Nombre Marca";
				nombre = nombreMarca;
			}
			else {
				nombreJSON = "Nombre Proveedor";
				nombre = nombreProv;
			}
				
			
			int i = 0; 
			while (i < JA.length()) {
				JSONObject jo = JA.getJSONObject(i);
				if(jo.get(nombreJSON).equals(nombre)) {
					jo.put("Activo", false);
				}
				JA.put(i, jo);
				i++;
			}
			JO.put("ListaMarcasProveedor", JA);
			}
		return writeJSONObject(JO);
	}

	@Override
	public TMarcaProveedor buscarMarcaProveedor(String nombreMarca, String nombreProv) {
		//Accedemos a los datos guardados
		JSONObject JO = getJSONFromFile();
		
		if (JO == null) {
			return null; 
		}
		else {
			// Obtenemos los datos del JSON
			JSONArray JA = JO.getJSONArray("ListaMarcasProveedor");

			int i = 0; 
			while (i < JA.length() && !(JA.getJSONObject(i).get("Nombre Marca").equals(nombreMarca) && JA.getJSONObject(i).get("Nombre Proveedor").equals(nombreProv))) {
				i++;
			}
			if (i == JA.length()) return null;
			else {
				boolean activo = JA.getJSONObject(i).getBoolean("Activo");
				return new TMarcaProveedor(i, nombreMarca, nombreProv, activo);
			}
		}
	}
	
	@Override
	public Collection<TMarcaProveedor> listarMarcaProveedor() {
		Collection<TMarcaProveedor> lista = new ArrayList<TMarcaProveedor>();
		
		//Accedemos a los datos guardados
		JSONObject JO = getJSONFromFile();
		
		if (JO != null) {
			// Obtenemos los datos del JSON
			JSONArray JA = JO.getJSONArray("ListaMarcasProveedor");
			
			for (int i = 0; i < JA.length(); i++) {
				JSONObject jo = JA.getJSONObject(i);
				
				if(jo.getBoolean("Activo")) {
					lista.add(new TMarcaProveedor(i , jo.getString("Nombre Marca"), jo.getString("Nombre Proveedor"), jo.getBoolean("Activo")));
				}
			}
		}
		return lista;
	}


	
	
	//Funciones auxiliares
		private JSONObject getJSONFromFile() {
			JSONObject JO;
			try {
				InputStream in  = new FileInputStream(new File("resources/MarcasProveedor.json"));
				JO = new JSONObject(new JSONTokener(in));
				
			} catch (FileNotFoundException e) {
				JO = null;	
			}
			return JO;
		}
		
		private JSONObject createJSON(TMarcaProveedor marcaProv) {
			JSONObject jo = new JSONObject();
			jo.put("Id", marcaProv.getID());
			jo.put("Nombre Marca", marcaProv.getNombreMarca());
			jo.put("Nombre Proveedor", marcaProv.getNombreProv());
			jo.put("Activo", marcaProv.getActivo());
			return jo;
		}
		
		private boolean writeJSONObject(JSONObject JO) {		
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter("resources/MarcasProveedor.json"));
				bw.write(JO.toString(3));
				bw.close();
				return true;
			} catch (Exception e) {
				return false;
			}
		}
}
