package presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import presentacion.Controlador.Controlador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JButton;



public class MainWindow extends JFrame implements IGUI {
	private static final long serialVersionUID = 7062033845360986187L;
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLUE = "\u001B[34m";
	
	public MainWindow() {
		
		super("Pasteleria Cerecillas con Nata ");
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setBackground(Color.white);
		
		GridBagConstraints gbc = new GridBagConstraints();
		mainPanel.setLayout(new GridBagLayout());
		
		//colocamos un titulo
		JLabel titulo = new JLabel("Cerecillas con Nata");
		titulo.setFont(new Font("Times New Roman", Font.BOLD, 30));
		titulo.setForeground(Color.red);
		gbc.gridx = 0;
		gbc.gridy = 0;
		mainPanel.add(Box.createRigidArea(new Dimension(0,100)));
		mainPanel.add(titulo,gbc);
		
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setSize(new Dimension(300,300));
		buttonPanel.setLayout(new GridBagLayout());
		buttonPanel.setBackground(Color.white);
		gbc.gridx = 0;
		gbc.gridy = 1;
		mainPanel.add(buttonPanel,gbc);
		
		
		
		gbc.insets = new Insets(5,5,5,5);
		JButton productoBtn = new JButton("Producto");
		productoBtn.setBackground( java.awt.Color.red);
		productoBtn.setForeground(Color.white);
		productoBtn.setPreferredSize(new Dimension (100,35));
		productoBtn.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_PRINCIPAL_PRODUCTO));
		gbc.gridx = gbc.WEST;
		gbc.gridy = gbc.NORTH;
		buttonPanel.add(productoBtn,gbc);
		
		JButton facturasBtn = new JButton("Facturas");
		facturasBtn.setBackground(getForeground());
		facturasBtn.setBackground(java.awt.Color.red);
		facturasBtn.setForeground(Color.white);
		facturasBtn.setPreferredSize(new Dimension (100,35));
		facturasBtn.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_PRINCIPAL_FACTURAS));
		gbc.gridx = gbc.WEST;
		gbc.gridy = gbc.CENTER;
		buttonPanel.add(facturasBtn,gbc);
		
		JButton marcasBtn = new JButton("Marca");
		marcasBtn.setBackground(java.awt.Color.red);
		marcasBtn.setForeground(Color.white);
		marcasBtn.setPreferredSize(new Dimension (100,35));
		marcasBtn.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_PRINCIPAL_MARCA));
		gbc.gridx = gbc.WEST;
		gbc.gridy = gbc.SOUTH;
		buttonPanel.add(marcasBtn,gbc);
		
		
		//en medio de los 3 botones (a cada lado) el logo de nuestra empresa
		gbc.gridx = 15;
		gbc.gridy = 11;
		JLabel logo = new JLabel(new ImageIcon("resources/cerecillas.png"));
		//logo.setSize(new Dimension(50,50));
		buttonPanel.add(logo,gbc);
		
		//gbc.insets = new Insets(15,15,15,15);
		JButton clienteBtn = new JButton("Cliente");
		clienteBtn.setBackground(java.awt.Color.red);
		clienteBtn.setForeground(Color.white);
		clienteBtn.setPreferredSize(new Dimension (100,35));
		clienteBtn.addActionListener((e) -> listenerAbrirVentana(Evento.VISTA_PRINCIPAL_CLIENTE));
		gbc.gridx = gbc.EAST;
		gbc.gridy = gbc.NORTH;
		buttonPanel.add(clienteBtn,gbc);
		
		JButton empleadoBtn= new JButton("Empleados");
		empleadoBtn.setBackground(java.awt.Color.red);
		empleadoBtn.setForeground(Color.white);
		empleadoBtn.setPreferredSize(new Dimension (100,35));
		empleadoBtn.addActionListener((e)->listenerAbrirVentana(Evento.VISTA_PRINCIPAL_EMPLEADOS));
		gbc.gridx = gbc.EAST;
		gbc.gridy = gbc.CENTER;
		buttonPanel.add(empleadoBtn,gbc);
		
		JButton proveedorBtn= new JButton("Proveedores");
		proveedorBtn.setBackground(java.awt.Color.red);
		proveedorBtn.setForeground(Color.white);
		proveedorBtn.setPreferredSize(new Dimension (125,35));
		proveedorBtn.addActionListener((e)->listenerAbrirVentana(Evento.VISTA_PRINCIPAL_PROVEEDOR));
		gbc.gridx = gbc.EAST;
		gbc.gridy = gbc.SOUTH;
		buttonPanel.add(proveedorBtn,gbc);
		
		
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Para que acabe el programa al cerrar la ventana.
		setResizable(false);
		setSize(new Dimension(600,550));
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	//Funcion a usar en los listeners de los botones
	private void listenerAbrirVentana(Evento e) {
		Controlador.getInstance().accion(e, null);
	}

	@Override
	public void actualizar(Evento e, Object datos) {
	}

}
