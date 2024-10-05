package integracion.Facturas;

import java.util.Collection;

import negocio.Facturas.TFactura;

public interface DAOFactura {
	public int crearFactura(TFactura f);

	public TFactura buscarFactura(int id_factura);

	public boolean modificarFactura(int id_f, int id_c, int id_v, String fechaF);

	public Collection<TFactura> listarFacturas();

	// EXTRAS
	public Collection<TFactura> listarFacturasPorCliente(int id_cliente);

	public boolean devolucionFactura(TFactura factura);
}
