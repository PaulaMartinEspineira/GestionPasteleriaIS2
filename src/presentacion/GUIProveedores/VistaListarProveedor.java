package presentacion.GUIProveedores;


import java.awt.Color;
import java.util.Collection;

import javax.swing.*;

import negocio.Proveedor.TProveedor;
import presentacion.Evento;
import presentacion.IGUI;

public class VistaListarProveedor extends JDialog implements IGUI {

	private static final long serialVersionUID = 1L;
	
	private ModeloTablaProveedor _modeloTabla;
	
	public VistaListarProveedor(){
		initGUI();
	}
	
	//componentes gráficos
	private void initGUI() {
		setTitle("Listar Proveedores");

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		_modeloTabla = new ModeloTablaProveedor();
		JTable table = new JTable(_modeloTabla);
		JPanel tablePanel = new JPanel();
		mainPanel.add(tablePanel);
		
		tablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2), "Proveedores"));
		tablePanel.add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		
		JButton continuarButton = new JButton("Continuar");
		continuarButton.addActionListener((e) -> dispose());
		mainPanel.add(continuarButton);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actualizar(Evento e, Object datos) {
		switch (e) {
		case LISTAR_PROVEEDOR:
			Collection<TProveedor> proveedores = (Collection<TProveedor>) datos;
			_modeloTabla.loadData(proveedores);
			break;
		default:
			break;
		}
		
	}
	
}
