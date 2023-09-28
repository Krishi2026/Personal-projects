package dao;

public class DAOManager {

	public static UserDAO getUserDAO() {
		return new UserDAO();
	}
	
	public static SignInDAO getSignInDAO() {
		return new SignInDAO();
	}
	
	public static UserActivityDAO getUserUserActivityDAO() {
		return new UserActivityDAO();
	}
}
