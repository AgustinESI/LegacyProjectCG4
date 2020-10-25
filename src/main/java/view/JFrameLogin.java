package main.java.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import org.apache.commons.lang3.StringUtils;

import main.java.controller.UserController;
import main.java.model.User;

public class JFrameLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldLog;
	private JPasswordField textFieldPass;
	private JTextField textFieldDNI;
	private JTextPane textPaneEstado;
	private UserController userController = new UserController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameLogin frame = new JFrameLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFrameLogin() {
		setTitle(userController.getMessage("login.title"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 438, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		userController.getAllUsers().stream().forEach((user) -> System.out.println(user));

		JLabel lblIntroduzcaElLogin = new JLabel(userController.getMessage("login.subtitle"));
		lblIntroduzcaElLogin.setBounds(6, 19, 386, 43);
		contentPane.add(lblIntroduzcaElLogin);

		textFieldLog = new JTextField();
		textFieldLog.setBounds(86, 68, 134, 28);
		contentPane.add(textFieldLog);
		textFieldLog.setColumns(10);

		JLabel lblLogin = new JLabel(userController.getMessage("password"));
		lblLogin.setBounds(6, 122, 73, 16);
		contentPane.add(lblLogin);

		JLabel label = new JLabel(userController.getMessage("login"));
		label.setBounds(6, 74, 61, 16);
		contentPane.add(label);

		textFieldPass = new JPasswordField();
		textFieldPass.setColumns(10);
		textFieldPass.setBounds(86, 116, 134, 28);
		contentPane.add(textFieldPass);

		JLabel labelDNI = new JLabel(userController.getMessage("dni"));
		labelDNI.setBounds(6, 164, 61, 16);
		contentPane.add(labelDNI);
		labelDNI.setVisible(Boolean.FALSE);

		textFieldDNI = new JTextField();
		textFieldDNI.setColumns(10);
		textFieldDNI.setBounds(86, 164, 134, 28);
		textFieldDNI.setVisible(Boolean.FALSE);
		contentPane.add(textFieldDNI);

		JButton buttonAceptar = new JButton(userController.getMessage("accept"));
		buttonAceptar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {

					if (StringUtils.isNotBlank(textFieldLog.getText())
							&& StringUtils.isNotBlank(textFieldPass.getText())) {

						User u = userController.authenticateUser(textFieldLog.getText(), textFieldPass.getText());
						if (u != null) {
							textPaneEstado.setText(userController.getMessage("login.okLogin") + u.getName());
							buttonAceptar.setEnabled(false);
						} else {
							textPaneEstado.setText(userController.getMessage("login.errLogin"));
						}

					} else {
						textPaneEstado.setText(userController.getMessage("login.emptyFields"));
					}

				} catch (Exception e) {
					textPaneEstado.setText(userController.getMessage("login.err") + e.toString());
				}

			}
		});
		buttonAceptar.setBounds(264, 69, 148, 29);
		contentPane.add(buttonAceptar);

		JLabel lblEstado = new JLabel(userController.getMessage("state"));
		lblEstado.setForeground(Color.RED);
		lblEstado.setBounds(6, 208, 61, 16);
		contentPane.add(lblEstado);

		textPaneEstado = new JTextPane();
		textPaneEstado.setToolTipText("");
		textPaneEstado.setEditable(false);
		textPaneEstado.setBounds(6, 235, 406, 102);
		contentPane.add(textPaneEstado);

		JButton buttonLimpiar = new JButton(userController.getMessage("clean"));
		buttonLimpiar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				// textPaneEstado.setText(userController.getMessage("login.panel"));
				textPaneEstado.setText("");
				textFieldLog.setText("");
				textFieldPass.setText("");
				textFieldDNI.setText("");
				buttonAceptar.setEnabled(true);
			}
		});
		buttonLimpiar.setBounds(264, 117, 148, 29);
		contentPane.add(buttonLimpiar);

		JButton btnNuevoUsuario = new JButton(userController.getMessage("login.newUser"));
		btnNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrameNuevoUsuario frame = new JFrameNuevoUsuario();
				frame.setVisible(true);
			}
		});
		btnNuevoUsuario.setBounds(264, 157, 148, 29);
		contentPane.add(btnNuevoUsuario);

		JButton btnEliminarUsuario = new JButton(userController.getMessage("login.deleteUser"));
		btnEliminarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameEliminarUsuario frame = new JFrameEliminarUsuario();
				frame.setVisible(true);
			}
		});
		btnEliminarUsuario.setBounds(264, 197, 148, 28);
		contentPane.add(btnEliminarUsuario);

		/*
		 * JScrollPane scrollPaneSalida = new JScrollPane(); scrollPaneSalida.
		 * setToolTipText("Este panel mostrar\u00E1 el resultado de la consulta, las excepciones o cualquier otro resultado"
		 * ); scrollPaneSalida.setBounds(6, 193, 407, 108); scrollPaneSalida.
		 * contentPane.add(scrollPaneSalida);
		 */
	}

}
