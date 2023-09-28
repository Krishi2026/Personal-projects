package views;

import javax.swing.JPanel;

import common.Session;
import listeners.SignInListener;
import listeners.SignUpListener;

public abstract class AbstractView extends JPanel {

	private Session session;

	private SignUpListener signUpListener;

	private SignInListener signInListener;
	
	public abstract void reset();

	public void setSession(Session session) {
		this.session = session;
	}

	public Session getSession() {
		return session;
	}

	public void setSignUpListener(SignUpListener signUpListener) {
		this.signUpListener = signUpListener;
	}

	public SignUpListener getSignUpListener() {
		return signUpListener;
	}

	public void setSignInListener(SignInListener signInListener) {
		this.signInListener = signInListener;
	}

	public SignInListener getSignInListener() {
		return signInListener;
	}

}
