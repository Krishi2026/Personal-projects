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

import controllers.MyProfileController;
import listeners.SignUpListener;
import models.User;

public class MyProfileView extends AbstractView {

	private JButton btnDelete;

	private JButton btnUpdate;

	private SignUpListener signUpListener;

	private JTextField txtUser;
	
	private JTextField txtFirstName;
	
	private JTextField txtLastName;

	private DatePicker pickDOB;

	
	private MyProfileController myProfileController;

	public MyProfileView() {
		myProfileController = new MyProfileController(this);
		TitledBorder border = new TitledBorder("My Profile");
		border.setTitleJustification(TitledBorder.CENTER);
		border.setTitlePosition(TitledBorder.TOP);
		setBorder(border);
		JLabel lblUser = new JLabel("Username:");
		txtUser = new JTextField(10);
		txtUser.setEditable(false);
		JLabel lblFirstName = new JLabel("First Name:");
		txtFirstName = new JTextField(10);
		JLabel lblLastName = new JLabel("Last Name:");
		txtLastName = new JTextField(10);
		JLabel lblDob = new JLabel("DOB:");
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		dateSettings.setAllowEmptyDates(false);
		pickDOB = new DatePicker(dateSettings);
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(myProfileController);
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(myProfileController);
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
		c.gridy = 6;
		c.anchor = GridBagConstraints.EAST;
		add(btnUpdate, c);
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.gridx = 1;
		c.gridy = 6;
		c.anchor = GridBagConstraints.WEST;
		add(btnDelete, c);

		setVisible(true);
	}
	
	public void showMyProfile() {
		String userName = getSession().getUserName();
		User user = myProfileController.getUser(userName);
		txtUser.setText(userName);
		txtFirstName.setText(user.getFirstName());
		txtLastName.setText(user.getLastName());
		pickDOB.setDate(user.getDob());
		setVisible(true);
	}

	public JButton getDeleteButton() {
		return btnDelete;
	}

	public JButton getUpdateButton() {
		return btnUpdate;
	}

	public void setSignUpListener(SignUpListener signUpListener) {
		this.signUpListener = signUpListener;
	}

	public SignUpListener getSignUpListener() {
		return signUpListener;
	}

	public User getUser() {
		User user = new User();
		user.setUsername(txtUser.getText());
		user.setFirstName(txtFirstName.getText());
		user.setLastName(txtLastName.getText());
		user.setDob(pickDOB.getDate());
		return user;
	}

	@Override
	public void reset() {
		txtUser.setText("");
		txtFirstName.setText("");
		txtLastName.setText("");
		pickDOB.setDate(LocalDate.now());
		
	}
}
