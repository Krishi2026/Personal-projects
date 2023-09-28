import javax.swing.JFrame;

import listeners.SignInListener;
import listeners.SignUpListener;
import models.User;
import views.AgeCalculatorView;
import views.MyProfileView;
import views.SignInView;
import views.SignUpView;
import views.UserActivityView;

public abstract class AbstractAgeCalculatorApp extends JFrame implements SignUpListener, SignInListener {

	protected SignUpView signUpView;

	protected SignInView signInView;

	protected AgeCalculatorView ageCalculatorView;
	
	protected  UserActivityView userActivityView;
	
	protected MyProfileView myProfileView;

	public boolean isSuperUser(User user) {
		return user.getUsername().equals("superuser");
	}

	protected void removeAllViews() {
		signInView.setVisible(false);
		signInView.reset();
		signUpView.setVisible(false);
		signUpView.reset();
		ageCalculatorView.setVisible(false);
		ageCalculatorView.reset();
		userActivityView.setVisible(false);
		userActivityView.reset();
		myProfileView.setVisible(false);
		myProfileView.reset();
	}

	@Override
	public void onOpenSignUp() {
		removeAllViews();
		signUpView.setVisible(true);

	}

	@Override
	public void onCloseSignUp() {
		removeAllViews();
		signInView.setVisible(true);
	}

	@Override
	public void onSignInSuccess(User user) {
		removeAllViews();
		if(isSuperUser(user)) {
			userActivityView.setVisible(true);
		}
		else {
			ageCalculatorView.setVisible(true);
		}
	}

	@Override
	public void onSignOut() {

		removeAllViews();
		signInView.setVisible(true);
	}

	@Override
	public void onSignInFailure(User user) {
		removeAllViews();
		signInView.setVisible(true);
	}

}
