package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

import common.Constants;
import dao.DAOManager;
import dao.UserDAO;
import models.User;
import models.UserActivity;
import views.MyProfileView;

public class MyProfileController implements ActionListener {

	private MyProfileView myProfileView;

	private UserDAO userDao;

	public MyProfileController(MyProfileView myProfileView) {
		this.myProfileView = myProfileView;
		userDao = DAOManager.getUserDAO();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(myProfileView.getUpdateButton())) {
			User user = myProfileView.getUser();
			String userName = user.getUsername();
			String firstName = user.getFirstName();
			String lastName = user.getLastName();
			String pwd = user.getPwd();
			LocalDate dob = user.getDob();
			LocalDate now = LocalDate.now();
			if (firstName.trim().length() == 0) {
				JOptionPane.showMessageDialog(null, "First Name is mandatory", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (lastName.trim().length() == 0) {
				JOptionPane.showMessageDialog(null, "Last Name is mandatory", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (dob.compareTo(now) > 0) {
				JOptionPane.showMessageDialog(null, "DOB must be lesser than today", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			boolean result = userDao.update(user);
			if (result) {
				JOptionPane.showMessageDialog(null, "User Updated Successfully", "Info",
						JOptionPane.INFORMATION_MESSAGE);

			} else {
				JOptionPane.showMessageDialog(null, "Unable to Update User", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource().equals(myProfileView.getDeleteButton())) {
			User user = myProfileView.getUser();
			boolean result = userDao.delete(user);
			if (result) {
				JOptionPane.showMessageDialog(null, "User deleted Successfully", "Info",
						JOptionPane.INFORMATION_MESSAGE);
				myProfileView.getSignInListener().onSignOut();
				UserActivity deleteActivity = new UserActivity();
				deleteActivity.setUsername(user.getUsername());
				deleteActivity.setActivityDate(LocalDateTime.now());
				deleteActivity.setSessionid("");
				deleteActivity.setUseraction(Constants.ACTIVITY_DELETED);
				DAOManager.getUserUserActivityDAO().insert(deleteActivity);

			} else {
				JOptionPane.showMessageDialog(null, "Unable to delete User", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public User getUser(String userName) {
		return userDao.select(userName);
	}

}
