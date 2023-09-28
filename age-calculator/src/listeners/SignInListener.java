package listeners;

import models.User;

public interface SignInListener {

	public void onSignInSuccess(User user);
	
	public void onSignInFailure(User user);
	
	public void onSignOut();
}
