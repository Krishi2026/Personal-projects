package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import common.Session;
import controllers.SignUpController;
import listeners.SignUpListener;
import models.User;

public class SignUpView extends AbstractView {

	private JButton btnCancel;

	private JButton btnSignUp;

	private SignUpListener signUpListener;

	private JTextField txtUser;
	
	private JTextField txtFirstName;
	
	private JTextField txtLastName;

	private DatePicker pickDOB;

	private JPasswordField txtPwd;

	private JPasswordField txtCnfPwd;

	public SignUpView() {
		SignUpController signUpController = new SignUpController(this);
		TitledBorder border = new TitledBorder("Sign Up");
		border.setTitleJustification(TitledBorder.CENTER);
		border.setTitlePosition(TitledBorder.TOP);
		setBorder(border);
		JLabel lblUser = new JLabel("Username:");
		txtUser = new JTextField(10);
		JLabel lblFirstName = new JLabel("First Name:");
		txtFirstName = new JTextField(10);
		JLabel lblLastName = new JLabel("Last Name:");
		txtLastName = new JTextField(10);
		JLabel lblDob = new JLabel("DOB:");
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		dateSettings.setAllowEmptyDates(false);
		pickDOB = new DatePicker(dateSettings);
		JLabel lblPwd = new JLabel("Password");
		txtPwd = new JPasswordField(10);
		JLabel lblCnfPwd = new JLabel("Confirm Password");
		txtCnfPwd = new JPasswordField(10);
		btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(signUpController);
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(signUpController);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 0;
		add(lblUser, c);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 0;
		add(txtUser, c);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 1;
		add(lblFirstName, c);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 1;
		add(txtFirstName, c);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 2;
		add(lblLastName, c);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 2;
		add(txtLastName, c);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 5, 5, 5);
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 3;
		add(lblDob, c);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 3;
		add(pickDOB, c);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.EAST;
		add(lblPwd, c);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.gridx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.WEST;
		add(txtPwd, c);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 5;
		c.anchor = GridBagConstraints.EAST;
		add(lblCnfPwd, c);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.gridx = 1;
		c.gridy = 5;
		c.anchor = GridBagConstraints.WEST;
		add(txtCnfPwd, c);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 6;
		c.anchor = GridBagConstraints.EAST;
		add(btnSignUp, c);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.gridx = 1;
		c.gridy = 6;
		c.anchor = GridBagConstraints.WEST;
		add(btnCancel, c);
		setVisible(true);
	}

	public JButton getCancelButton() {
		return btnCancel;
	}

	public JButton getSignUpButton() {
		return btnSignUp;
	}

	public void setSignUpListener(SignUpListener signUpListener) {
		this.signUpListener = signUpListener;
	}

	public SignUpListener getSignUpListener() {
		return signUpListener;
	}

	public boolean comparePasswords() {
		return Arrays.equals(txtPwd.getPassword(), txtCnfPwd.getPassword());
	}

	public User getUser() {
		User user = new User();
		user.setUsername(txtUser.getText());
		user.setFirstName(txtFirstName.getText());
		user.setLastName(txtLastName.getText());
		user.setDob(pickDOB.getDate());
		user.setPwd(new String(txtPwd.getPassword()));
		return user;
	}

	@Override
	public void reset() {
		txtUser.setText("");
		txtFirstName.setText("");
		txtLastName.setText("");
		txtPwd.setText("");
		txtCnfPwd.setText("");
		pickDOB.setDate(LocalDate.now());
		
	}
}
