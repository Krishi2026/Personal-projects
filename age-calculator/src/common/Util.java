package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Util {

	private static final  PBKDF2Hasher HASHER = new PBKDF2Hasher();
	
	public static Connection getConnection() throws Exception {
		Connection con = null;
		Class.forName(Constants.JDBC_DRIVER);
		Properties props = new Properties();
		props.setProperty("user", Constants.JDBC_USER);
		props.setProperty("password", Constants.JDBC_PWD);
		props.setProperty("currentSchema", Constants.JDBC_SCHEMA);
		con = DriverManager.getConnection(Constants.JDBC_URL, props);
		return con;
	}

	public static String hash(char[] password) {
		return HASHER.hash(password);
	}
	
	public static boolean check(char[] password, String token) {
		return HASHER.checkPassword(password, token);
	}
	
	public static void main(String arg[]) {
		System.out.println(hash("superuser".toCharArray()));
	}
	

}
