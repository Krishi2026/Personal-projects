package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;

import javax.swing.JOptionPane;

import common.Util;
import models.User;

public class SignInDAO {

	private static final String USER_LOGIN_SQL = "select password,dob from users where username = ? ";

	public User signin(String username, String password) {
		User result = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = Util.getConnection();
			pstmt = con.prepareStatement(USER_LOGIN_SQL);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbpassword = rs.getString(1);
				Date dobDate = rs.getDate(2);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dobDate);
				LocalDate dob = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1,
						calendar.get(Calendar.DAY_OF_MONTH));
				boolean checkPassword = Util.check(password.toCharArray(), dbpassword);
				if (checkPassword) {
					result = new User();
					result.setUsername(username);
					result.setDob(dob);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
