package main.java.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import main.java.controller.UserController;
import main.java.model.User;

public class JFrameNuevoUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldLogin;
	private JTextField textFieldPassword;
	private JTextPane textPane;

	private UserController userController = new UserController();

	/**
	 * Launch the application.
	 * 
	 * 
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { JFrameNuevoUsuario frame = new
	 * JFrameNuevoUsuario(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public JFrameNuevoUsuario() {
		setTitle(userController.getMessage("newUser.title"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 285);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLogin = new JLabel(userController.getMessage("password"));
		lblLogin.setBounds(6, 81, 69, 16);
		contentPane.add(lblLogin);

		JLabel label = new JLabel(userController.getMessage("login"));
		label.setBounds(6, 37, 69, 16);
		contentPane.add(label);

		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(87, 31, 134, 28);
		contentPane.add(textFieldLogin);
		textFieldLogin.setColumns(10);

		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(87, 75, 134, 28);
		contentPane.add(textFieldPassword);

		JButton btnAltaUsuario = new JButton(userController.getMessage("newUser.newUser"));
		btnAltaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean insertado = false;
				try {
					User u = new User(textFieldLogin.getText(), textFieldPassword.getText(), null);
//					if (u.insert() == 1)
//						insertado = true;
//
//					if (insertado) {
//						textPane.setText("Usuario creado correctamente");
//					} else {
//						textPane.setText("No se ha podido insertar el usuario");
//					}

				} catch (Exception e) {
					textPane.setText("No se ha podido crear  el usuario.¿Tal vez ya existe?");
				}

			}
		});
		btnAltaUsuario.setBounds(253, 76, 117, 29);
		contentPane.add(btnAltaUsuario);

		JLabel label_1 = new JLabel("Estado");
		label_1.setForeground(Color.RED);
		label_1.setBounds(6, 126, 61, 16);
		contentPane.add(label_1);

		textPane = new JTextPane();
		textPane.setToolTipText(
				"Panel para mostrar el restultado de la comprobaci\u00F3n de login o las excepciones lanzadas");
		textPane.setEditable(false);
		textPane.setBounds(6, 154, 407, 102);
		contentPane.add(textPane);
	}
}
