package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import dao.DAOManager;
import dao.UserDAO;
import models.User;
import views.SignUpView;

public class SignUpController implements ActionListener {

	private SignUpView signUpView;

	private UserDAO userDao;

	public SignUpController(SignUpView signUpView) {
		this.signUpView = signUpView;
		userDao = DAOManager.getUserDAO();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(signUpView.getCancelButton())) {
			signUpView.getSignUpListener().onCloseSignUp();
		} else if (e.getSource().equals(signUpView.getSignUpButton())) {
			User user = signUpView.getUser();
			String userName = user.getUsername();
			String firstName = user.getFirstName();
			String lastName = user.getLastName();
			String pwd = user.getPwd();
			LocalDate dob = user.getDob();
			LocalDate now = LocalDate.now();
			if (userName.trim().length() == 0) {
				JOptionPane.showMessageDialog(null, "Username is mandatory", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (firstName.trim().length() == 0) {
				JOptionPane.showMessageDialog(null, "First Name is mandatory", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (lastName.trim().length() == 0) {
				JOptionPane.showMessageDialog(null, "Last Name is mandatory", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (pwd.trim().length() == 0) {
				JOptionPane.showMessageDialog(null, "Password is mandatory", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (!signUpView.comparePasswords()) {
				JOptionPane.showMessageDialog(null, "Password and Confirm Password must be equal", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else if (dob.compareTo(now) > 0) {
				JOptionPane.showMessageDialog(null, "DOB must be lesser than today", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			boolean result = userDao.save(user);
			if (result) {
				JOptionPane.showMessageDialog(null, "User Saved Successfully", "Info", JOptionPane.INFORMATION_MESSAGE);
				signUpView.getSignUpListener().onCloseSignUp();
				signUpView.getSignUpListener().onSignUpSuccess(user);
				
			} else {
				JOptionPane.showMessageDialog(null, "Unable to Save User", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
