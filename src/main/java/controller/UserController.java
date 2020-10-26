package main.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.apache.commons.lang3.StringUtils;

import main.java.model.DAOUser;
import main.java.model.User;
import main.java.view.JFrameEliminarUsuario;
import main.java.view.JFrameNuevoUsuario;

public class UserController {

	private DAOUser daoUser = new DAOUser();

	public User authenticateUser(String login, String password) {
		boolean authenticated = false;
		User u = null;
		if (StringUtils.isNotBlank(login) && StringUtils.isNotBlank(password)) {
			User aux = new User(login, password, null);
			u = this.daoUser.read(aux);
		}
		return u;
	}

	public boolean checkDNI(String arg) {
		return Pattern.compile("^[0-9]{8,8}[A-Za-z]$").matcher(arg).find();
	}

	public boolean readDNI(String dni) {

		boolean exist = false;
		if (StringUtils.isNotBlank(dni)) {
			exist = this.daoUser.readDNI(dni);
		}
		return exist;
	}
	
	
	public String encryptPass(String txt) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    }

	public User createUser(User u) {
		User aux = null;

		if (StringUtils.isNotBlank(u.getName()) && StringUtils.isNotBlank(u.getPassword())
				&& StringUtils.isNotBlank(u.getDni())) {
			if (this.daoUser.read(u) == null) {
				aux = (User) this.daoUser.create(u);
			}
		}
		return aux;
	}

	public boolean deleteUser(User u) {
		boolean deleted = false;

		if (StringUtils.isNotBlank(u.getName()) && StringUtils.isNotBlank(u.getPassword())) {
			if (this.daoUser.read(u) != null) {
				deleted = this.daoUser.delete(u);
			}
		}
		return deleted;
	}

	public List<User> getAllUsers() {
		return this.daoUser.selectAllUsers();
	}

	public String getMessage(String name) {
		String chain = "";
		if (StringUtils.isNotBlank(name)) {
			InputStream inputStream = null;
			Properties prop = null;
			try {
				prop = new Properties();
				String propFileName = "messages.properties";

				inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

				if (inputStream != null) {
					prop.load(inputStream);
				} else {
					throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
				}

				chain = prop.getProperty(name);

				inputStream.close();
			} catch (Exception e) {
				System.out.println("Exception: " + e);
			}
		}
		return chain;
	}
	
	public ActionListener btnAddUser(JTextField textFieldLogin, JTextField textFieldPassword, JTextField textFieldDNI, JTextPane textPane) {

		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (StringUtils.isNotBlank(textFieldLogin.getText())
						&& StringUtils.isNotBlank(textFieldPassword.getText())
						&& StringUtils.isNotBlank(textFieldDNI.getText())) {

					if (new UserController().checkDNI(textFieldDNI.getText())) {
						if (!new UserController().readDNI(textFieldDNI.getText())) {
							User u = new User(textFieldLogin.getText(), new UserController().encryptPass(textFieldPassword.getText()),
									textFieldDNI.getText());
							User aux = new UserController().createUser(u);

							if (aux != null) {
								textPane.setText(new UserController().getMessage("newUser.newUserOk"));
							} else {
								textPane.setText(new UserController().getMessage("newUser.existUser"));
							}
						} else {
							textPane.setText(new UserController().getMessage("newUser.existDNI"));
						}

					} else {
						textPane.setText(new UserController().getMessage("newUser.notValidDNI"));
					}

				} else {
					textPane.setText(new UserController().getMessage("newUser.emptyFields"));
				}
				
			}

		};

	}
	
	
	public ActionListener btnAccept(JTextField textFieldLog, JTextField textFieldPass, JTextPane textPaneEstado, JButton buttonAceptar) {

		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					if (StringUtils.isNotBlank(textFieldLog.getText())
							&& StringUtils.isNotBlank(textFieldPass.getText())) {

						User u = new UserController().authenticateUser(textFieldLog.getText(), new UserController().encryptPass(textFieldPass.getText()));
						if (u != null) {
							textPaneEstado.setText(new UserController().getMessage("login.okLogin") + u.getName());
							buttonAceptar.setEnabled(false);
						} else {
							textPaneEstado.setText(new UserController().getMessage("login.errLogin"));
						}

					} else {
						textPaneEstado.setText(new UserController().getMessage("login.emptyFields"));
					}

				} catch (Exception e2) {
					textPaneEstado.setText(new UserController().getMessage("login.err") + e2.toString());
				}
				
			}

		};

	}
	
	public ActionListener btnDelUser(JTextField textFieldLogin, JTextField textFieldPassword, JTextPane textPane) {

		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (StringUtils.isNotBlank(textFieldLogin.getText())
						&& StringUtils.isNotBlank(textFieldPassword.getText())) {
					User u = new User(textFieldLogin.getText(), new UserController().encryptPass(textFieldPassword.getText()), null);

					if (new UserController().deleteUser(u)) {
						textPane.setText(new UserController().getMessage("delUser.delUserOk"));
					} else {
						textPane.setText(new UserController().getMessage("delUser.delUserErr"));
					}

				} else {
					textPane.setText(new UserController().getMessage("newUser.emptyFields"));
				}

			}

		};
	}
	
	public ActionListener btnClean(JTextField textFieldLog, JTextField textFieldPass, JTextPane textPaneEstado, JTextField textFieldDNI, JButton buttonAceptar) {

		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// textPaneEstado.setText(userController.getMessage("login.panel"));
				textPaneEstado.setText("");
				textFieldLog.setText("");
				textFieldPass.setText("");
				textFieldDNI.setText("");
				buttonAceptar.setEnabled(true);

			}

		};
	}
	public ActionListener btnNewUser() {

		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrameNuevoUsuario frame = new JFrameNuevoUsuario();
				frame.setVisible(true);
			}

		};
	}
	
	public ActionListener btnDelUser() {

		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrameEliminarUsuario frame = new JFrameEliminarUsuario();
				frame.setVisible(true);
			}

		};
	}

}
