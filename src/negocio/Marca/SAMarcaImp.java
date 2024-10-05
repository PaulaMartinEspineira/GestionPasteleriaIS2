package negocio.Marca;

import java.util.Collection;

import negocio.Producto.TProducto;
import integracion.Factoria.FactoriaAbstractaIntegracion;
import integracion.Marca.DAOMarca;
import integracion.Producto.DAOProducto;

//"Negocio pide a integración que lo añada"
//Accede al DAOMarca, DAOProducto (comprobar que no hay ningun producto con esa marca la eliminarla) y a DAOMarcaProveedor

public class SAMarcaImp implements SAMarca{
	
	@Override
	public int altaMarca(TMarca marca) throws IllegalArgumentException {
		
		DAOMarca daoMarca = FactoriaAbstractaIntegracion.getInstance().crearDAOMarca();
		
		int id = -1;
		
		TMarca prueba = daoMarca.buscarMarca(marca.getNombre());
		if (prueba == null) { //no está insertada todavía
			id = daoMarca.altaMarca(marca);	
		}
		else if (!prueba.getActivo()) { //sí está pero "inactiva"
			marca.setID(prueba.getID());
			marca.setActivo(true);
			id = daoMarca.actualizarMarca(marca);
		}
		else throw new IllegalArgumentException("Marca ya existe.");
		return id;
	}
		
	
	public int bajaMarca(String nombre) throws IllegalArgumentException {
		DAOMarca daoMarca = FactoriaAbstractaIntegracion.getInstance().crearDAOMarca();
		
		//Comprobamos si la marca existe y está activa
		TMarca marca = daoMarca.buscarMarca(nombre);
		if (marca == null ) {
			throw new IllegalArgumentException("Marca no existente.");
		}
		else if (!marca.getActivo()) {
			throw new IllegalArgumentException("La marca ya ha sido dada de baja.");
		}
		
		int id = marca.getID();
		//Comprobamos que no haya ningun producto con esa marca 
		DAOProducto daoProducto = FactoriaAbstractaIntegracion.getInstance().crearDAOProducto();
		int cont = 0;
		for (TProducto prod : daoProducto.listarProductos()) {
			if (prod.getMarca() == id) {
				cont++;
			}
		}
		if (cont != 0) throw new IllegalArgumentException("Existen productos con esta marca.");
		
		daoMarca.bajaMarca(id);
		return id;
	}
	
	
	public int actualizarMarca(TMarca marca) throws IllegalArgumentException {
		DAOMarca daoMarca = FactoriaAbstractaIntegracion.getInstance().crearDAOMarca();
		
		//Comprobemos que la marca a actualizar existe
		TMarca tMarca = daoMarca.buscarMarca(marca.getNombre());
		
		if(tMarca == null) {
			throw new IllegalArgumentException("Marca no existente.");
		}
		
		marca.setID(tMarca.getID());
		return daoMarca.actualizarMarca(marca);
	}
	
	public TMarca buscarMarca(String nombre) {
		DAOMarca daoMarca = FactoriaAbstractaIntegracion.getInstance().crearDAOMarca();
		return daoMarca.buscarMarca(nombre);
	}
	
	public Collection<TMarca> listarMarcas(){
		DAOMarca daoMarca = FactoriaAbstractaIntegracion.getInstance().crearDAOMarca();
		return daoMarca.listarMarcas();
	}
}
