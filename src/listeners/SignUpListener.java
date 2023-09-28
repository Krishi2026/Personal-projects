package listeners;

import models.User;

public interface SignUpListener {

	public void onOpenSignUp();
	
	public void onCloseSignUp();
	
	public void onSignUpSuccess(User user);
	
	public void onSignUpFailure(User user);
}
