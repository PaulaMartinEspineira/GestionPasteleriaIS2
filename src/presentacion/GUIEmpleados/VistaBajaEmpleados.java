package presentacion.GUIEmpleados;



import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import presentacion.Evento;
import presentacion.IGUI;
import presentacion.Controlador.Controlador;

public class VistaBajaEmpleados extends JDialog implements IGUI {

	
	private static final long serialVersionUID = 1L;
	JTextField idEmployee;
	JButton buttonOkey;
	JButton buttonCancel;

	public VistaBajaEmpleados() {
		initGUI();
	}

	void initGUI() {
		setTitle("Baja Empleado");

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);

		// IDENTIFICADOR
		JPanel panelIdentificador = new JPanel();
		JLabel etiquetaIdentificador = new JLabel("DNI: ");
	    idEmployee = new JTextField(15);
		panelIdentificador.add(etiquetaIdentificador);
		panelIdentificador.add(idEmployee);
		mainPanel.add(panelIdentificador);

		

		// BUTTON OKEY
		JPanel okCancelPanel = new JPanel();
		this.buttonOkey = new JButton("Aceptar");
		this.buttonOkey.addActionListener((e) -> {
			bajaEmpleado();
		});

		okCancelPanel.add(buttonOkey);

		// BUTTON CANCEL
		this.buttonCancel = new JButton("Cancelar");
		this.buttonCancel.addActionListener((e) -> {
			cancelImp(); //a lo mejor retocar esto?
		});

		okCancelPanel.add(buttonCancel);

		// añadimos ambos botones (ok y cancel) al main_panel
		mainPanel.add(okCancelPanel);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void bajaEmpleado() {
		String dni= idEmployee.getText();
		Controlador.getInstance().accion(Evento.BAJA_EMPLEADO, dni);
	}
 
	private void cancelImp() {
		SwingUtilities.invokeLater(() -> {
			setVisible(false); //libera todos los componentes
		});
	}

	
	@Override
	public void actualizar(Evento e, Object datos) {
		switch (e) {
		case BAJA_EMPLEADO_EXITO:
			JOptionPane.showMessageDialog(this, "Empleado con id " + datos + " dado de baja con éxito.", "Baja Empleado",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
			break;
		case BAJA_EMPLEADO_ERROR:
			JOptionPane.showMessageDialog(this, "Error al dar de baja al empleado: " + datos, "Baja Empleado",
					JOptionPane.ERROR_MESSAGE);
			dispose();
			break;
		default:
			break;
		}

	}

}