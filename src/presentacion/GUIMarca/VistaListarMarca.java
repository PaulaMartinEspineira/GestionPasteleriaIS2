package presentacion.GUIMarca;

import java.awt.Color;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import negocio.Marca.TMarca;
import presentacion.Evento;
import presentacion.IGUI;

public class VistaListarMarca extends JDialog implements IGUI{

	private static final long serialVersionUID = 1L;

private ModeloTablaMarca _modeloTabla;
	
	public VistaListarMarca() {
		initGUI();
	}
	
	void initGUI() {
		setTitle("Listar Marcas");

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		_modeloTabla = new ModeloTablaMarca();
		JTable table = new JTable(_modeloTabla);
		
		JPanel tablePanel = new JPanel();
		mainPanel.add(tablePanel);
		
		tablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2), "Marcas"));
		tablePanel.add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		
		JButton continuarBtn = new JButton("Continuar");
		continuarBtn.addActionListener((e) -> dispose());
		mainPanel.add(continuarBtn);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actualizar(Evento e, Object datos) {
		switch (e) {
		case LISTAR_MARCAS:
			Collection<TMarca> marcas = (Collection<TMarca>) datos;
			_modeloTabla.loadData(marcas);
			break;
		default:
			break;
		}
	}

}
