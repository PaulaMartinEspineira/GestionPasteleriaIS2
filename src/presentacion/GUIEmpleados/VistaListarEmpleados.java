package presentacion.GUIEmpleados;


import java.awt.Color;
import java.util.Collection;


import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import negocio.Empleados.*;


import presentacion.Evento;
import presentacion.IGUI;

public class VistaListarEmpleados extends JDialog implements IGUI{

	private static final long serialVersionUID = 1L;

private ModeloTablaEmpleados _modeloTabla;
	
	public VistaListarEmpleados() {
		initGUI();
	}
	
	void initGUI() {
		setTitle("Listar Empleados");

		//PANEL PRINCIPAL
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		_modeloTabla = new ModeloTablaEmpleados();
		JTable table = new JTable(_modeloTabla);
		JPanel tablePanel = new JPanel();
		mainPanel.add(tablePanel);
		
		tablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.pink, 2), "Empleados"));
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
		case LISTAR_EMPLEADOS:
			Collection<TEmpleado> empleados = (Collection<TEmpleado>) datos;
			_modeloTabla.loadData(empleados);
			break;
		default:
			break;
		}
	}

}
