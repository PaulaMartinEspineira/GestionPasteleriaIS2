package presentacion.GUIEmpleados;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import presentacion.Evento;
import presentacion.IGUI;
import presentacion.Controlador.Controlador;

public class VistaPrincipalEmpleados extends JFrame implements IGUI{ 


	private static final long serialVersionUID = 1L;

	public VistaPrincipalEmpleados() {
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Subsistema Empleados");
		
		JPanel mainPanel = new JPanel();
		setContentPane(mainPanel);
		
		GridLayout grid=new GridLayout(2,0);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(grid);
		mainPanel.add(buttonPanel);
		
		JButton altaBtn = new JButton("Alta Empleado");
		altaBtn.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_ALTA_EMPLEADO));
		buttonPanel.add(altaBtn);
		
		JButton bajaBtn = new JButton("Baja Empleado");
		bajaBtn.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_BAJA_EMPLEADO));
		buttonPanel.add(bajaBtn);
		
		JButton actualizarBtn = new JButton("Actualizar Empleado");
		actualizarBtn.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_ACTUALIZAR_EMPLEADO));
		buttonPanel.add(actualizarBtn);
		
		JButton buscarBtn = new JButton("Buscar Empleados");
		buscarBtn.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_BUSCAR_EMPLEADO));
		buttonPanel.add(buscarBtn);
		
		JButton listarBtn = new JButton("Listar Empleados");
		listarBtn.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_LISTAR_EMPLEADOS));
		buttonPanel.add(listarBtn);
		
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
				
	}
	
	
	private void listenerAbrirVentana(Evento e) {
		Controlador.getInstance().accion(e, null);
	}
	
	@Override
	public void actualizar(Evento e, Object datos) {
	}
	
}
