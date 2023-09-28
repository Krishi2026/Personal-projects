package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dao.DAOManager;
import dao.SignInDAO;
import models.User;
import views.SignInView;

public class SignInController implements ActionListener {

	private SignInView view;

	public SignInController(SignInView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(view.getSignUpButton())) {
			view.getSignUpListener().onOpenSignUp();
		} else if (e.getSource().equals(view.getSignInButton())) {
			SignInDAO signInDAO = DAOManager.getSignInDAO();
			User user = signInDAO.signin(view.getUsername(), view.getPassword());
			if(user!=null) {
				view.getSignInListener().onSignInSuccess(user);
			}
			else {
				user = new User();
				user.setUsername(view.getUsername());
				view.getSignInListener().onSignInFailure(user);
				JOptionPane.showMessageDialog(null, "Invalid Username/Password", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
