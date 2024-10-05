package negocio.Facturas;

import java.util.ArrayList;

public class TFacturaLineaFacturas {

	private TFactura factura;
	private ArrayList<TLineaFactura> lineasFactura;

	public TFacturaLineaFacturas(TFactura factura, ArrayList<TLineaFactura> lf) {
		this.factura = factura;
		this.lineasFactura = lf;
	}

//GETTERS
	public TFactura getTFactura() {
		return this.factura;
	}

	public ArrayList<TLineaFactura> getLineasFactura() {
		return this.lineasFactura;
	}
}
