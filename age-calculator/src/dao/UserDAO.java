package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import common.Util;
import models.User;

public class UserDAO {

	private static final String INSERT_USER_SQL = "insert into users(username,firstname,lastname,password,dob)values(?,?,?,?,?)";

	public boolean save(User user) {
		boolean result = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = Util.getConnection();
			pstmt = con.prepareStatement(INSERT_USER_SQL);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getFirstName());
			pstmt.setString(3, user.getLastName());
			pstmt.setString(4, Util.hash(user.getPwd().toCharArray()));
			pstmt.setDate(5, Date.valueOf(user.getDob()));
			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			if(pstmt!=null) {
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
