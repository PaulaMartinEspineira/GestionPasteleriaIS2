package presentacion.GUIMarca;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import presentacion.Evento;
import presentacion.IGUI;
import presentacion.Controlador.Controlador;

public class VistaPrincipalMarca extends JDialog implements IGUI{

	private static final long serialVersionUID = 1L;

	public VistaPrincipalMarca() {
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Subsistema Marca");
		
		JPanel mainPanel = new JPanel();
		setContentPane(mainPanel);
		
		JPanel buttonPanel = new JPanel();
		mainPanel.add(buttonPanel);
		
		JButton altaBtn = new JButton("Alta Marca");
		altaBtn.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_ALTA_MARCA));
		buttonPanel.add(altaBtn);
		
		JButton bajaBtn = new JButton("Baja Marca");
		bajaBtn.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_BAJA_MARCA));
		buttonPanel.add(bajaBtn);
		
		JButton actualizarBtn = new JButton("Actualizar Marca");
		actualizarBtn.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_ACTUALIZAR_MARCA));
		buttonPanel.add(actualizarBtn);
		
		JButton buscarBtn = new JButton("Buscar Marca");
		buscarBtn.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_BUSCAR_MARCA));
		buttonPanel.add(buscarBtn);
		
		JButton listarBtn = new JButton("Listar Marcas");
		listarBtn.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_LISTAR_MARCAS));
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
