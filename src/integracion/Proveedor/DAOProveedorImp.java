package integracion.Proveedor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import negocio.Proveedor.TProveedor;

public class DAOProveedorImp implements DAOProveedor {

	@Override
	public int altaProveedor(TProveedor proveedor) {
		JSONObject jProv = createJSON(proveedor);
		JSONObject jDatos = getJSONFromFile();
		JSONArray jA;
		int next_id;
		
		//si no tenemos datos preexistentes
		if (jDatos == null) {
			System.out.println("Sueños de libertad");
			jDatos = new JSONObject();
			jA = new JSONArray();
			next_id = 0;
			jProv.put("Id", next_id);
			jA.put(jProv);
		}
		//si sí los tenemos
		else {
			jA = jDatos.getJSONArray("Proveedores");
		    next_id = jDatos.getInt("next_id");
		    jProv.put("Id", next_id);
			jA.put(jProv);
		}
		
		jDatos.put("Proveedores", jA);
		jDatos.put("next_id", next_id+1);
		
		if (writeJSONObject(jDatos)) {
			return next_id;
		}
		else {
			return -1;
		}
	}

	@Override
	public void bajaProveedor(int id) {
		JSONObject jDatos = getJSONFromFile();
		JSONArray jA;
		
		if(jDatos!= null) {
			jA = jDatos.getJSONArray("Proveedores");
			JSONObject jProv = jA.getJSONObject(id);
			
			jProv.put("Activo", false); //damos de baja
			jA.put(id, jProv); //actualizamos informacion 
			jDatos.put("Proveedores",jA); //actualizamos JSON datos
			
		}
		
		writeJSONObject(jDatos);
		
	}

	@Override
	public int actualizarProveedor(TProveedor proveedor) {
		JSONObject jProv = createJSON(proveedor);
		JSONObject jDatos = getJSONFromFile();
		
		if (jDatos != null) {
			JSONArray jA = jDatos.getJSONArray("Proveedores");
			jA.put(proveedor.getID(), jProv);
			jDatos.put("Proveedores", jA);
		}
		
		else {
			return -1;
		}
		
		if (writeJSONObject(jDatos)) {
			return proveedor.getID();
		}
		else {
			return -1;
		}
	}

	@Override
	public TProveedor buscarProveedor(int id) {
		JSONObject jDatos = getJSONFromFile();
		if(jDatos != null) {
			JSONArray jA = jDatos.getJSONArray("Proveedores");
			JSONObject jProv;
			try {
					jProv = jA.getJSONObject(id);
			}catch(JSONException e) {
					return null;
			}
			
			return createTProveedor(jProv);
		}else {
			return null;
		}
		
		
			
	}

	@Override
	public TProveedor buscarProveedor(String nombre) {
		JSONObject jDatos = getJSONFromFile();
		if(jDatos == null) {
			return null;
		}else {
			// Obtenemos los datos del JSON
			JSONArray jA = jDatos.getJSONArray("Proveedores");
			if (jA == null) {
				return null;
			}
			
			JSONObject jProveedor;
			try {
				int i = 0; 
				while (i < jA.length() && !(jA.getJSONObject(i).get("Nombre").equals(nombre))) {
					i++;
				}
				if (i == jA.length()) return null; //no lo ha encontrado
				else jProveedor = jA.getJSONObject(i);
			}
			catch (JSONException e) {
				return null;
			}
			
			return createTProveedor(jProveedor);
			
									
		}
		
	}

	@Override
	public Collection<TProveedor> listarProveedores() {
		Collection<TProveedor> listaProveedores = new ArrayList<TProveedor>();
		
		JSONObject jDatos = getJSONFromFile();
		if(jDatos!=null) {
			JSONArray jA = jDatos.getJSONArray("Proveedores");
			
			if(jA != null) {
				for	(int i = 0; i<jA.length();i++) {
					TProveedor aux = createTProveedor(jA.getJSONObject(i));
					if (aux.getActivo()) listaProveedores.add(aux);
				}
			}
			
		}
		
		return listaProveedores;
	}
	
	
	private JSONObject createJSON(TProveedor proveedor) {
		JSONObject jObj = new JSONObject();
		jObj.put("Nombre", proveedor.getNombre());
		jObj.put("Telefono", proveedor.getTelefono());
		jObj.put("Correo", proveedor.getCorreo());
		jObj.put("Codigo postal", proveedor.getCodigoPostal());
		jObj.put("Id", proveedor.getID());
		jObj.put("Activo", proveedor.getActivo());
		return jObj;
	}
	
	private JSONObject getJSONFromFile() {
		JSONObject jO;
		try {
			InputStream in  = new FileInputStream(new File("resources/Proveedor.json"));
			jO = new JSONObject(new JSONTokener(in));
			
		} catch (FileNotFoundException e) {
			jO = null;	
		}
		return jO;
	}
	
	private boolean writeJSONObject(JSONObject JO) {		
		try {
			BufferedWriter bW = new BufferedWriter(new FileWriter("resources/Proveedor.json"));
			bW.write(JO.toString(3));
			bW.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private TProveedor createTProveedor(JSONObject jProv) {
		
		String nombre = jProv.getString("Nombre");
		int telefono = jProv.getInt("Telefono");
		String correo = jProv.getString("Correo");
		int codigoPostal = jProv.getInt("Codigo postal");
		int id = jProv.getInt("Id");
		boolean activo = jProv.getBoolean("Activo");
		
		TProveedor proveedor = new TProveedor(nombre, telefono, correo, codigoPostal);
		proveedor.setActivo(activo);
		proveedor.setID(id);
		
		return proveedor;
		
		
	}

}
