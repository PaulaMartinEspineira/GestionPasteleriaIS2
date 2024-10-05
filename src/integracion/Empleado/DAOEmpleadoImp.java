package integracion.Empleado;

import java.util.Collection;

import org.json.*;
import java.io.*;

import negocio.Empleados.*;
import negocio.Marca.TMarca;

import java.util.ArrayList;


//tenemos que implementar todos los metodos de la interfaz DAOEmpleado
public class DAOEmpleadoImp implements DAOEmpleado {

	@Override
	public int altaEmpleado(TEmpleado empleado) {
		JSONObject jEmpleado = this.createJSON(empleado);

		JSONObject jo = this.loadData();

		JSONArray ja = new JSONArray();

		int n_id;

		//Este fichero JSON ya existía previamente 
		if (jo != null) { 
			ja = jo.getJSONArray("empleados");
			n_id = jo.getInt("next_id");
			jEmpleado.put("id", n_id);
			ja.put(jEmpleado); //lo añadimos al JSONArray de empleados
		} 
		
		//Este fichero JSON no existe y entonces hay que crearlo
		else { 
			jo = new JSONObject();
			n_id = 0;
			jEmpleado.put("id", n_id);
			ja.put(jEmpleado); //añadimos al empleado al JSONArray
		}

		//tenemos que añadir al JSONobject el JSONArray 
		jo.put("empleados", ja);
		jo.put("next_id", n_id + 1);

		if (this.saveData(jo)) { // Alta del empleado realizada con éxito.
			return n_id;
		}

		else return -1; // si no se puede producir la correcta alta del empleado
	}

	@Override
	public void bajaEmpleado(int id) {
		JSONObject jo = this.loadData();
		
		if (jo != null) { // Fichero JSON existente.
			JSONArray ja = jo.getJSONArray("empleados");
			JSONObject jEmpleado = ja.getJSONObject(id);
			jEmpleado.put("activo", false); 
			ja.put(id, jEmpleado);
			jo.put("empleados", ja);
			this.saveData(jo);
		}
	}

	@Override
	//Búsqueda de empleado por id
	public TEmpleado buscarEmpleado(int id) { 
		JSONObject jo = this.loadData();

		//NO hay fichero JSON
		if (jo == null)
			return null; 

		JSONArray ja = jo.getJSONArray("empleados");

		JSONObject jEmpleado = ja.getJSONObject(id);

		return this.createTEmpleado(jEmpleado);
	}

	@Override
	//Búsqueda de empleado por dni
	public TEmpleado buscarEmpleado(String dni) { 
		JSONObject jo = this.loadData();

		//Fichero JSON inexistente
		if (jo == null) return null; 
		
		JSONArray ja = jo.getJSONArray("empleados");
		
		int i =0;
		while (i<ja.length() && !(ja.getJSONObject(i).getString("DNI").equals(dni))) {
			++i;
		}
		
		if (i == ja.length()) return null;
		
		return this.createTEmpleado(ja.getJSONObject(i));
	}

	
		

	@Override
	public int actualizarEmpleado(TEmpleado empleado) {
		JSONObject jo = this.loadData();

		//Todavía no se ha creado el fichero JSON
		if (jo == null) return -1; 

		JSONObject jEmpleado = this.createJSON(empleado);

		JSONArray ja = jo.getJSONArray("empleados");

		ja.put(empleado.getId(), jEmpleado);

		jo.put("empleados", ja);

		
		//entonces se habrá podido llevar a cabo la actualización del empleado
		if (this.saveData(jo)) { 
			return empleado.getId();
		}

		else return -1; //no se ha podido realizar la actualiación del empleado
		
	}
	

	@Override
	public Collection<TEmpleado> listarEmpleados() {
		JSONObject jo = this.loadData();

		if (jo == null)
			return null; // Fichero JSON inexistente (aún no hay clientes insertados)

		Collection<TEmpleado> listaEmpleados = new ArrayList<TEmpleado>();

		JSONArray ja = jo.getJSONArray("empleados");

		for (int i = 0; i < ja.length(); i++) {
			listaEmpleados.add(this.createTEmpleado(ja.getJSONObject(i)));
		}

		return listaEmpleados;
	}

	
	//Queremos cargar los datos de los empleados
	//Para obtener dichos datos tenemos que cargarlos del fichero JSON
	JSONObject loadData() { 
		JSONObject jo = new JSONObject();
		try {
			InputStream is = new FileInputStream(new File("resources/Empleado.json"));
			jo = new JSONObject(new JSONTokener(is));
		} catch (FileNotFoundException e) {
			jo = null;
		}
		return jo;
	}

	private boolean saveData(JSONObject jo) {
		try {
			BufferedWriter bufw = new BufferedWriter(new FileWriter("resources/Empleado.json", false));
			bufw.write(jo.toString(3));
			bufw.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	//creamos el JSON con la info de todo el empleado
	private JSONObject createJSON(TEmpleado empleado) {
		JSONObject jEmpleado = new JSONObject();
		jEmpleado.put("id", empleado.getId());
		jEmpleado.put("nombre", empleado.getNombre());
		jEmpleado.put("apellidos", empleado.getApellido());
		jEmpleado.put("DNI", empleado.getDNI());
		jEmpleado.put("email", empleado.getCorreo());
		jEmpleado.put("numTelefono", empleado.getNumTelefono());
		jEmpleado.put("direccion", empleado.getDireccion());
		jEmpleado.put("numVentas", empleado.getNumVentas());
		jEmpleado.put("activo", empleado.getActivo());
		
		return jEmpleado;
	}

	private TEmpleado createTEmpleado(JSONObject jo) {
		String nombre = jo.getString("nombre");
		String apellidos = jo.getString("apellidos");
		String dni = jo.getString("DNI");
		String email = jo.getString("email");
		String direccion= jo.getString("direccion");
		String numeroTelefono= jo.getString("numTelefono");
		int numVentas=jo.getInt("numVentas");
		boolean activo = jo.getBoolean("activo");
		int id = jo.getInt("id");
        
 
		//aqui en la constructora me falta algo mas seguro
		return new TEmpleado(nombre, apellidos, dni,email,activo,id,direccion,numeroTelefono,numVentas);
	}

	



	 

	

}
