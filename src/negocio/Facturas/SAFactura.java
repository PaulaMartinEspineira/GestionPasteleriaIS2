package negocio.Facturas;

import java.util.Collection;

public interface SAFactura {
	public TFacturaLineaFacturas buscarFactura(int id);

	public Collection<TFactura> listarFacturas();

	public int cerrarVenta(TDatosVenta datos);

	public boolean modificarFactura(int id_f, int id_c, int id_v, String fecha);

	// EXTRA
	public Collection<TFactura> listarFacturasPorCliente(int id_cliente);

	public TFacturasCliente listarFacturasConCliente(int id_cliente);

	public boolean devolucionFactura(TLineaFactura lf);
}
