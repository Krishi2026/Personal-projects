package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import common.Session;
import controllers.SignInController;
import listeners.SignInListener;
import listeners.SignUpListener;

public class SignInView extends AbstractView {

	private JButton btnSignIn;

	private JButton btnSignup;

	private JTextField txtUser;

	private JPasswordField txtPwd;

	public SignInView() {
		SignInController controller = new SignInController(this);
		TitledBorder border = new TitledBorder("Sign In");
		border.setTitleJustification(TitledBorder.CENTER);
		border.setTitlePosition(TitledBorder.TOP);
		setBorder(border);
		JLabel lblUser = new JLabel("Username:");
		JLabel lblPwd = new JLabel("Password:");
		txtUser = new JTextField(10);
		txtPwd = new JPasswordField(10);
		btnSignIn = new JButton("Sign in");
		btnSignup = new JButton("Sign Up");
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		add(lblUser, c);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 0;
		add(txtUser, c);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 5, 5, 5);
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 1;
		add(lblPwd, c);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 1;
		add(txtPwd, c);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.EAST;
		add(btnSignIn, c);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		add(btnSignup, c);
		btnSignIn.addActionListener(controller);
		btnSignup.addActionListener(controller);
		setVisible(true);
	}

	public JButton getSignInButton() {
		return btnSignIn;
	}

	public JButton getSignUpButton() {
		return btnSignup;
	}

	public String getUsername() {
		return txtUser.getText();
	}

	public String getPassword() {
		return new String(txtPwd.getPassword());
	}

	@Override
	public void reset() {
		txtUser.setText("");;
		txtPwd.setText("");
	}


}
