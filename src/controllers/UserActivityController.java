package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import dao.DAOManager;
import dao.UserActivityDAO;
import views.UserActivityView;

public class UserActivityController implements ActionListener {

	private UserActivityView userActivityView;
	
	public UserActivityController(UserActivityView userActivityView) {
		this.userActivityView = userActivityView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(userActivityView.getSignOutButton())) {
			userActivityView.getSignInListener().onSignOut();
		}
		else if(e.getSource().equals(userActivityView.getQueryButton())) {
			UserActivityDAO userActivityDAO = DAOManager.getUserUserActivityDAO();
			String data[][] = userActivityDAO.listActivities();
			userActivityView.updateTable(data);
		}
	}

}
