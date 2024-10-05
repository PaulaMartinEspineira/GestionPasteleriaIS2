package presentacion.GUIProveedores;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

import negocio.Marca.TMarca;
import negocio.Proveedor.TProveedor;
import presentacion.Evento;
import presentacion.IGUI;
import presentacion.Controlador.Controlador;

public class VistaBuscarProveedor extends JDialog implements IGUI{

	private static final long serialVersionUID = 1L;
	
	
	
	JLabel _nombreLabel;
	JTextField _nombreText;
	JPanel _nombrePanel;
	
	//botones ok y cancel
	JButton _okButton;
	JButton _cancelButton;
	JPanel _okCancelPanel;
	

	
	JPanel _infoPanel;
	JPanel _mainVistaAltaPanel;
	
	public VistaBuscarProveedor() {
		initGUI();
	}
	
	//componentes gráficos
		private void initGUI() {
			setTitle("Buscar Proveedor");
			
			_mainVistaAltaPanel = new JPanel();
			this.setContentPane(_mainVistaAltaPanel);
			_mainVistaAltaPanel.setLayout(new BoxLayout(_mainVistaAltaPanel,BoxLayout.Y_AXIS));
		
			_nombreLabel = new JLabel("Nombre: ");
			_nombreText = new JTextField();
			_nombreText.setPreferredSize(new Dimension(100, 20));
			_nombrePanel = new JPanel(new FlowLayout());
			_nombrePanel.add(_nombreLabel);
			_nombrePanel.add(_nombreText);
			
			_mainVistaAltaPanel.add(_nombrePanel);
			
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
			
			_nombrePanel.add(_okCancelPanel);
			
			setPreferredSize(new Dimension (400, 150));
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
		
		}
		
		private void okImp() {
			String nombre = (String) _nombreText.getText();
			
			if (nombre == null || nombre.equals("")) {
				JOptionPane.showMessageDialog(this, "Debe indicar un nombre", "Buscar Proveedor", JOptionPane.ERROR_MESSAGE);
				return;
			}
			dispose();
			Controlador.getInstance().accion(Evento.BUSCAR_PROVEEDOR, nombre);
		}

		void initInfoGUI(TProveedor proveedor) {

			_infoPanel = new JPanel();
			_infoPanel.setLayout(new BoxLayout(_infoPanel, BoxLayout.Y_AXIS));
			
			
			JLabel nombreLabel = new JLabel("Nombre: " + proveedor.getNombre());
			JLabel telefonoLabel = new JLabel("Teléfono: " + proveedor.getTelefono());
			JLabel correoLabel = new JLabel("Correo: " + proveedor.getCorreo());
			JLabel codigoPostalLabel = new JLabel("Código Postal: " + proveedor.getCodigoPostal());
			String activo = proveedor.getActivo() ? "Si" : "No";
			JLabel activoLabel = new JLabel("Activo: " + activo);
			JLabel idLabel = new JLabel("Id: " + proveedor.getID());
			
			_infoPanel.add(nombreLabel);
			_infoPanel.add(telefonoLabel);
			_infoPanel.add(correoLabel);
			_infoPanel.add(codigoPostalLabel);
			_infoPanel.add(activoLabel);
			_infoPanel.add(idLabel);
			_mainVistaAltaPanel.add(_infoPanel);
			
			JButton continuarButton = new JButton("Continuar");
			continuarButton.addActionListener((e) -> dispose());
			_mainVistaAltaPanel.add(Box.createRigidArea(new Dimension(150, 20)));
			_mainVistaAltaPanel.add(continuarButton);
			
			setPreferredSize(new Dimension (400, 150));
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
		}
		
		
		@Override
		public void actualizar(Evento e, Object datos) {
			switch(e) {
			case BUSCAR_PROVEEDOR_SUCCESS:
				_nombrePanel.setVisible(false);
				initInfoGUI((TProveedor) datos);
				break;
			case BUSCAR_PROVEEDOR_ERROR:
				JOptionPane.showMessageDialog(this, "ERROR: " + datos, "Buscar Proveedor", JOptionPane.ERROR_MESSAGE);
				break;
			default:
				break;
			}
			
		}
}
