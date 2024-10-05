package integracion.Cliente;

import java.util.Collection;

import org.json.*;
import java.io.*;

import negocio.Cliente.TCliente;

import java.util.ArrayList;

public class DAOClienteImp implements DAOCliente {
	private static final String _CLIENTE_FILENAME = "resources/Clientes.json";

	@Override
	public int altaCliente(TCliente tcliente) {
		JSONObject jCliente = this.createJSON(tcliente);

		JSONObject jo = this.loadData();

		JSONArray ja = new JSONArray();

		int n_id;

		if (jo != null) { // Fichero JSON existente.
			ja = jo.getJSONArray("clientes");
			n_id = jo.getInt("next_id");
			jCliente.put("id", n_id);
			ja.put(jCliente);
		} else { // Fichero JSON inexistente (es la primera vez que se realiza el alta de un
					// cliente)
			jo = new JSONObject();
			n_id = 0;
			jCliente.put("id", n_id);
			ja.put(jCliente);
		}

		jo.put("clientes", ja);
		jo.put("next_id", n_id + 1);

		if (this.saveData(jo)) { // Alta del cliente realizada con éxito.
			return n_id;
		}

		return -1; // Ha ocurrido algún error y no se ha podido realizar el alta del cliente.
	}

	@Override
	public void bajaCliente(int id) {
		JSONObject jo = this.loadData();

		if (jo != null) { // Fichero JSON existente.
			JSONArray ja = jo.getJSONArray("clientes");

			JSONObject jCliente = ja.getJSONObject(id);

			jCliente.put("activo", false);

			ja.put(id, jCliente);

			jo.put("clientes", ja);

			this.saveData(jo);
		}
	}

	@Override
	public TCliente buscarCliente(int id) { // Para gestión interna (buscamos por id de la base de datos)
		JSONObject jo = this.loadData();

		if (jo == null)
			return null; // Fichero JSON inexistente (aún no hay clientes insertados)

		JSONArray ja = jo.getJSONArray("clientes");

		if (id < 0 || id >= ja.length())
			return null; // El id recibido no existe.

		JSONObject jCliente = ja.getJSONObject(id);

		return this.createTCliente(jCliente);
	}

	@Override
	public TCliente buscarCliente(String dni) { // Para búsqueda externa (buscamos por dni del cliente).
		JSONObject jo = this.loadData();

		if (jo == null)
			return null; // Fichero JSON inexistente (aún no hay clientes insertados)

		JSONArray ja = jo.getJSONArray("clientes");

		int i = 0;
		while (i < ja.length() && !(ja.getJSONObject(i).getString("DNI").equals(dni))) {
			++i;
		}

		if (i == ja.length())
			return null;

		return this.createTCliente(ja.getJSONObject(i));
	}

	@Override
	public int actualizarCliente(TCliente cliente) {
		JSONObject jo = this.loadData();

		if (jo == null)
			return -1; // Fichero JSON inexistente (aún no hay clientes insertados)

		JSONObject jCliente = this.createJSON(cliente);

		JSONArray ja = jo.getJSONArray("clientes");

		ja.put(cliente.getId(), jCliente);

		jo.put("clientes", ja);

		if (this.saveData(jo)) { // Actualización del cliente realizada con éxito.
			return cliente.getId();
		}

		return -1; // Ha ocurrido algún error y no se ha podido realizar la actualización del
					// cliente.
	}

	@Override
	public Collection<TCliente> listarClientes() {
		JSONObject jo = this.loadData();
		Collection<TCliente> listaClientes = new ArrayList<TCliente>();

		if (jo != null) {
			JSONArray ja = jo.getJSONArray("clientes");

			for (int i = 0; i < ja.length(); i++) { // Solo listamos los clientes dados de alta.
				JSONObject cl = ja.getJSONObject(i);
				if (cl.getBoolean("activo")) {
					listaClientes.add(this.createTCliente(cl));
				}
			}
		}

		return listaClientes;
	}

	private JSONObject loadData() { // Obtenemos los clientes del fichero JSON.
		JSONObject jo = new JSONObject();
		try {
			InputStream is = new FileInputStream(new File(_CLIENTE_FILENAME));
			jo = new JSONObject(new JSONTokener(is));
		} catch (FileNotFoundException e) {
			jo = null;
		}
		return jo;
	}

	private boolean saveData(JSONObject jo) {
		try {
			BufferedWriter bufw = new BufferedWriter(new FileWriter(_CLIENTE_FILENAME, false));
			bufw.write(jo.toString(3));
			bufw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	private JSONObject createJSON(TCliente tcliente) {
		JSONObject jCliente = new JSONObject();
		jCliente.put("nombre", tcliente.getNombre());
		jCliente.put("apellidos", tcliente.getApellidos());
		jCliente.put("DNI", tcliente.getDNI());
		jCliente.put("correo", tcliente.getCorreo());
		jCliente.put("activo", tcliente.getActivo());
		jCliente.put("id", tcliente.getId());

		return jCliente;
	}

	private TCliente createTCliente(JSONObject jo) {
		String nombre = jo.getString("nombre");
		String apellidos = jo.getString("apellidos");
		String dni = jo.getString("DNI");
		String correo = jo.getString("correo");
		boolean activo = jo.getBoolean("activo");
		int id = jo.getInt("id");

		return new TCliente(nombre, apellidos, dni, correo, activo, id);
	}

}
