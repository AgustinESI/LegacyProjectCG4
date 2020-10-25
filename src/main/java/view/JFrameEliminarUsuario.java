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

import org.apache.commons.lang3.StringUtils;

import main.java.controller.UserController;
import main.java.model.User;

public class JFrameEliminarUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldLogin;
	private JTextField textFieldPassword;
	private JTextPane textPane;
	private UserController userController = new UserController();

	public JFrameEliminarUsuario() {
		setTitle("Eliminar usuario registrado");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

		JButton btnAltaUsuario = new JButton("Eliminar usuario");
		btnAltaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (StringUtils.isNotBlank(textFieldLogin.getText())
						&& StringUtils.isNotBlank(textFieldPassword.getText())) {
					User u = new User(textFieldLogin.getText(), textFieldPassword.getText(), null);

					if (userController.deleteUser(u)) {
						textPane.setText(userController.getMessage("delUser.delUserOk"));
					} else {
						textPane.setText(userController.getMessage("delUser.delUserErr"));
					}

				} else {
					textPane.setText(userController.getMessage("newUser.emptyFields"));
				}

			}

		});
		btnAltaUsuario.setBounds(253, 76, 141, 29);
		contentPane.add(btnAltaUsuario);

		JLabel label_1 = new JLabel(userController.getMessage("state"));
		label_1.setForeground(Color.RED);
		label_1.setBounds(6, 126, 61, 16);
		contentPane.add(label_1);

		textPane = new JTextPane();
		textPane.setToolTipText(userController.getMessage("delUser.panel"));
		textPane.setEditable(false);
		textPane.setBounds(6, 154, 407, 102);
		contentPane.add(textPane);
	}
}
