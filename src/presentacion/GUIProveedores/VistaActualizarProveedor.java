package presentacion.GUIProveedores;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

import negocio.Proveedor.TProveedor;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.Controlador.Controlador;

public class VistaActualizarProveedor extends JDialog implements IGUI {

	private static final long serialVersionUID = 1L;
	
	JLabel _nombreLabel;
	JTextField _nombreText;
	JPanel _nombrePanel;
	
	JLabel _telefonoLabel;
	JTextField _telefonoText;
	JPanel _telefonoPanel;
	
	JLabel _correoLabel;
	JTextField _correoText;
	JPanel _correoPanel;
	
	JLabel _codigoPostalLabel;
	JTextField _codigoPostalText;
	JPanel _codigoPostalPanel;
	
	//botones ok y cancel
	JButton _okButton;
	JButton _cancelButton;
	JPanel _okCancelPanel;
	
	public VistaActualizarProveedor() {
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
			
			//TELEFONO
			_telefonoLabel = new JLabel("Teléfono: ");
			_telefonoText = new JTextField();
			_telefonoText.setPreferredSize(new Dimension(100, 20));
			_telefonoPanel = new JPanel(new FlowLayout());
			_telefonoPanel.add(_telefonoLabel);
			_telefonoPanel.add(_telefonoText);
			
			mainVistaAltaPanel.add(_telefonoPanel);
			
			//CORREO
			_correoLabel = new JLabel("Correo: ");
			_correoText = new JTextField();
			_correoText.setPreferredSize(new Dimension(100, 20));
			_correoPanel = new JPanel(new FlowLayout());
			_correoPanel.add(_correoLabel);
			_correoPanel.add(_correoText);
			
			mainVistaAltaPanel.add(_correoPanel);
			
			
			//CODIGO POSTAL
			
			_codigoPostalLabel = new JLabel("Código Postal: ");
			_codigoPostalText = new JTextField();
			_codigoPostalText.setPreferredSize(new Dimension(100, 20));
			_codigoPostalPanel = new JPanel(new FlowLayout());
			_codigoPostalPanel.add(_codigoPostalLabel);
			_codigoPostalPanel.add(_codigoPostalText);
			
			mainVistaAltaPanel.add(_codigoPostalPanel);
			
			
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
			TProveedor proveedor;
			
			String nombre = _nombreText.getText();
			if (nombre == null || nombre.equals("")) {
				JOptionPane.showMessageDialog(this, "Debe indicar un nombre", "Alta Proveedor", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			int telefono;
			try {
				telefono = Integer.parseInt(_telefonoText.getText());
				if(telefono<0) {
					JOptionPane.showMessageDialog(this, "El teléfono debe ser positivo", "Alta Proveedor", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(_telefonoText.getText().length()!=9) {
					JOptionPane.showMessageDialog(this, "El teléfono debe tener 9 dígitos", "Alta Proveedor", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Teléfono no válido", "Alta Proveedor", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			
			String correo = _correoText.getText();
			if (correo == null || correo.equals("")) {
				JOptionPane.showMessageDialog(this, "Debe indicar un correo", "Alta Proveedor", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else if (!correo.contains("@") || (!correo.contains(".es") && !correo.contains(".com"))) { 
				JOptionPane.showMessageDialog(this, "Indique una dirección de correo válida", "Alta Proveedor", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			
			int codigoPostal;
			try {
				codigoPostal = Integer.parseInt(_codigoPostalText.getText());
				if(codigoPostal<0) {
					JOptionPane.showMessageDialog(this, "El código postal debe ser positivo", "Alta Proveedor", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Código postal no válido", "Alta Proveedor", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			proveedor = new TProveedor(nombre, telefono, correo, codigoPostal);
			Controlador.getInstance().accion(Evento.ACTUALIZAR_PROVEEDOR, proveedor);
		}
		
		@Override
		public void actualizar(Evento e, Object datos) {
			switch(e) {
			case ACTUALIZAR_PROVEEDOR_SUCCESS:
				JOptionPane.showMessageDialog(this, "Proveedor con id " + datos + " actualizado con éxito", "Actualizar Proveedor", JOptionPane.INFORMATION_MESSAGE);
				dispose();
				break;
			case ACTUALIZAR_PROVEEDOR_ERROR:
				JOptionPane.showMessageDialog(this, "Error al actualizar el proveedor: " + datos, "Actualizar Proveedor", JOptionPane.ERROR_MESSAGE);
				dispose();
				break;
			default:
				break;
			}
			
		}
	
}
