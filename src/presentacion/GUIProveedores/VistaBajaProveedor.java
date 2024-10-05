package presentacion.GUIProveedores;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

import negocio.Marca.MarcaProveedorTOA;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.Controlador.Controlador;

public class VistaBajaProveedor extends JDialog implements IGUI {

	private static final long serialVersionUID = 1L;
	
	//vamos a tener atributos para cada información que le queramos pedir del proveedor
	//por lo que vamos a tener JLabel y JTextFeild
	//y a suvez vamos a tener un panel por cada par label text
	
	
	JLabel _nombreLabel;
	JTextField _nombreText;
	JPanel _nombrePanel;
	
	
	//botones ok y cancel
	JButton _okButton;
	JButton _cancelButton;
	JPanel _okCancelPanel;
	
	public VistaBajaProveedor(){
		initGUI();
	}
	
	//componentes gráficos
		private void initGUI() {
			setTitle("Actualizar Proveedor");			
			
			JPanel mainVistaAltaPanel = new JPanel();
			this.setContentPane(mainVistaAltaPanel);
			mainVistaAltaPanel.setLayout(new BoxLayout(mainVistaAltaPanel,BoxLayout.Y_AXIS));
			//mainVistaAltaPanel.setSize(500,500);
			
			//CREAMOS tuplaS LABEL-TEXT-panel
			
			//NOMBRE
			_nombreLabel = new JLabel("Nombre: ");
			_nombreText = new JTextField();
			_nombreText.setPreferredSize(new Dimension(100, 20));
			_nombrePanel = new JPanel(new FlowLayout());
			_nombrePanel.add(_nombreLabel);
			_nombrePanel.add(_nombreText);
			
			mainVistaAltaPanel.add(_nombrePanel);			
			
			//AÑADIMOS 2 BOTONES de ok y canel
			_okCancelPanel = new JPanel(new FlowLayout());
			_okButton = new JButton("Aceptar");
			_okButton.addActionListener((e)->{
				okImp();	
			});
			_okCancelPanel.add(_okButton);
			
			_cancelButton = new JButton("Cancelar");
			_cancelButton.addActionListener((e)->{
				dispose();	
			});
			_okCancelPanel.add(_cancelButton);
			
			mainVistaAltaPanel.add(_okCancelPanel);
			
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
			
			
			
		}
		
		
		private void okImp() {
			String nombre = _nombreText.getText();

			if (nombre == null || nombre.equals("")) {
				JOptionPane.showMessageDialog(this, "Debe indicar un nombre", "Baja Proveedor", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			//Damos de baja todas las lineas de MarcaProveedor
			String nombreMarca = null;
			MarcaProveedorTOA marcaProvTOA = new MarcaProveedorTOA();
			marcaProvTOA.bajaMarcaProveedor(nombreMarca, nombre); //no hace falta lanzar la excepción porque si no existe la marca ya salta en bajaMarca();
			

			Controlador.getInstance().accion(Evento.BAJA_PROVEEDOR, nombre);
			
		}
		

		@Override
		public void actualizar(Evento e, Object datos) {
			switch(e) {
			case BAJA_PROVEEDOR_SUCCESS:
				JOptionPane.showMessageDialog(this, "Proveedor con id " + datos + " dado de baja con éxito.", "Baja Proveedor", JOptionPane.INFORMATION_MESSAGE);
				dispose();
				break;
			case BAJA_PROVEEDOR_ERROR:
				JOptionPane.showMessageDialog(this, "Error al dar de baja el proveedor: " + datos, "Baja Proveedor", JOptionPane.ERROR_MESSAGE);
				dispose();
				break;
			default:
				break;
			}
			
		}
		
}
