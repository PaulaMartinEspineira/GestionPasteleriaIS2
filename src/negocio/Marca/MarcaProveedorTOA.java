package negocio.Marca;

import integracion.Factoria.FactoriaAbstractaIntegracion;
import integracion.Marca.DAOMarca;
import integracion.Marca.DAOMarcaProveedor;
import integracion.Proveedor.DAOProveedor;

public class MarcaProveedorTOA {
	
	
	public void altaMarcaProveedor(String nombreMarca, String nombreProveedor) throws IllegalArgumentException {
		
		DAOMarca daoMarca = FactoriaAbstractaIntegracion.getInstance().crearDAOMarca();
		TMarca prueba = daoMarca.buscarMarca(nombreMarca);
		if (prueba == null) { //no existe la marca 
			throw new IllegalArgumentException("Marca no existente.");
		}
		else if (!prueba.getActivo()) { //sí está pero "inactiva"
			throw new IllegalArgumentException("La marca no  está activa.");
		}

		TMarcaProveedor marcaProv = new TMarcaProveedor(nombreMarca, nombreProveedor);
		
		DAOMarcaProveedor daoMarcaProv = FactoriaAbstractaIntegracion.getInstance().crearDAOMarcaProveedor();
		if (daoMarcaProv.buscarMarcaProveedor(nombreMarca, nombreProveedor) == null) {
			daoMarcaProv.altaMarcaProveedor(marcaProv);
		}
		else throw new IllegalArgumentException("La marca " + nombreMarca + " ya está registrada con el proveedor " + nombreProveedor);
	}
	
	public void bajaMarcaProveedor(String nombreMarca, String nombreProv) {
		Object prueba;
		if (nombreMarca != null) { //hay que dar de baja todas las entradas que tengan esa marca
			DAOMarca daoMarca = FactoriaAbstractaIntegracion.getInstance().crearDAOMarca();
			prueba = daoMarca.buscarMarca(nombreMarca);
		}
		else {
			DAOProveedor daoProveedor = FactoriaAbstractaIntegracion.getInstance().crearDAOProveedor();
			prueba = daoProveedor.buscarProveedor(nombreProv);
		}
		
		if (prueba == null) return;
		else {
			DAOMarcaProveedor daoMarcaProv = FactoriaAbstractaIntegracion.getInstance().crearDAOMarcaProveedor();
			daoMarcaProv.bajaMarcaProveedor(nombreMarca, nombreProv);
		}
	}
	
	public TMarcaProveedor buscarMarcaProveedor(String nombreMarca, String nombreProveedor) {
		DAOMarcaProveedor daoMarcaProveedor = FactoriaAbstractaIntegracion.getInstance().crearDAOMarcaProveedor();
		return daoMarcaProveedor.buscarMarcaProveedor(nombreMarca, nombreProveedor );
	}
	
}
