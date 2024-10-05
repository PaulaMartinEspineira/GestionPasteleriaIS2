package negocio.Facturas;

import java.util.Collection;

import negocio.Cliente.TCliente;

public class TFacturasCliente {
	private TCliente cliente;
	private Collection<TFactura> facturas;

	public TFacturasCliente(TCliente c, Collection<TFactura> f) {
		this.cliente = c;
		this.facturas = f;
	}

//GETTERS
	public TCliente getCliente() {
		return this.cliente;
	}

	public Collection<TFactura> getFacturas() {
		return this.facturas;
	}

}
