package main.java.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class JFrameNuevoUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldLogin;
	private JPasswordField textFieldPassword;
	private JTextPane textPane;
	private JTextField textFieldDNI;

	private UserController userController = new UserController();

	/**
	 * Create the frame.
	 */
	public JFrameNuevoUsuario() {
		setTitle(userController.getMessage("newUser.title"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 440, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel(userController.getMessage("login"));
		label.setBounds(6, 37, 69, 16);
		contentPane.add(label);

		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(87, 31, 134, 28);
		contentPane.add(textFieldLogin);
		textFieldLogin.setColumns(10);

		JLabel lblLogin = new JLabel(userController.getMessage("password"));
		lblLogin.setBounds(6, 81, 69, 16);
		contentPane.add(lblLogin);

		textFieldPassword = new JPasswordField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(87, 81, 134, 28);
		contentPane.add(textFieldPassword);

		JLabel labelDNI = new JLabel(userController.getMessage("dni"));
		labelDNI.setBounds(6, 125, 69, 16);
		contentPane.add(labelDNI);

		textFieldDNI = new JTextField();
		textFieldDNI.setBounds(87, 125, 134, 28);
		contentPane.add(textFieldDNI);

		JLabel label_1 = new JLabel("Estado");
		label_1.setForeground(Color.RED);
		label_1.setBounds(6, 169, 61, 16);
		contentPane.add(label_1);

		textPane = new JTextPane();
		textPane.setToolTipText(userController.getMessage("newUser.panel"));
		textPane.setEditable(false);
		textPane.setBounds(6, 213, 407, 102);
		contentPane.add(textPane);
		
		
		JButton btnAltaUsuario = new JButton(userController.getMessage("newUser.newUser"));
		btnAltaUsuario.addActionListener(userController.btnAddUser(textFieldLogin, textFieldPassword, textFieldDNI, textPane));
		btnAltaUsuario.setBounds(253, 76, 117, 29);
		contentPane.add(btnAltaUsuario);
	}
}
