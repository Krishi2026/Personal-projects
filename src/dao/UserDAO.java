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

public class UserDAO {

	private static final String INSERT_USER_SQL = "insert into users(username,firstname,lastname,password,dob)values(?,?,?,?,?)";

	private static final String UPDATE_USER_SQL = "update users set firstname = ?, lastname = ?, dob = ? where username = ?";

	private static final String DELETE_USER_SQL = "delete from users where username = ?";

	private static final String SELECT_USER_SQL = "select firstname,lastname,dob from users where username = ?";

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

	public boolean update(User user) {
		boolean result = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = Util.getConnection();
			pstmt = con.prepareStatement(UPDATE_USER_SQL);
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setDate(3, Date.valueOf(user.getDob()));
			pstmt.setString(4, user.getUsername());
			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
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

	public boolean delete(User user) {
		boolean result = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = Util.getConnection();
			pstmt = con.prepareStatement(DELETE_USER_SQL);
			pstmt.setString(1, user.getUsername());
			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
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

	public User select(String userName) {
		User user = new User();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = Util.getConnection();
			pstmt = con.prepareStatement(SELECT_USER_SQL);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user.setFirstName(rs.getString(1));
				user.setLastName(rs.getString(2));
				Date dobDate = rs.getDate(3);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dobDate);
				LocalDate dob = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
						calendar.get(Calendar.DAY_OF_MONTH));
				user.setDob(dob);
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
		return user;
	}
}
