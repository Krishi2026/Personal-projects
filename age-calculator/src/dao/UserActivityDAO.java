package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import common.Util;
import models.User;
import models.UserActivity;

public class UserActivityDAO {

	private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	private static final String INSERT_ACTIVITY_SQL = "insert into users_activity(activitydate, username, sessionid, useraction)values(?,?,?,?)";

	private static final String INSERT_ACTIVITY_IO_SQL = "insert into users_activity_io(refid, input, output)values(?,?,?)";

	private static final String SELECT_ACTIVITIES_SQL = "select  ua.id,firstname||' '||lastname,activitydate,sessionid,useraction,input,output from users u, users_activity ua "
														+ "left outer join users_activity_io uaio on uaio.refid = ua.id "
														+ "where u.username = ua.username "
														+ "order by id desc";

	public String[][] listActivities() {
		List<String[]> data = new ArrayList<String[]>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = Util.getConnection();
			pstmt = con.prepareStatement(SELECT_ACTIVITIES_SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String row[] = new String[7];
				row[0] = rs.getString(1);
				row[1] = rs.getString(2);
				row[2] = FORMATTER.format(rs.getTimestamp(3));
				row[3] = rs.getString(4);
				row[4] = rs.getString(5);
				row[5] = rs.getString(6);
				row[6] = rs.getString(7);
				data.add(row);
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
		String result[][] = new String[data.size()][];
		return data.toArray(result);
	}

	public boolean insert(UserActivity userActivity) {
		boolean result = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = Util.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT_ACTIVITY_SQL, Statement.RETURN_GENERATED_KEYS);
			pstmt.setTimestamp(1, Timestamp.valueOf(userActivity.getActivityDate()));
			pstmt.setString(2, userActivity.getUsername());
			pstmt.setString(3, userActivity.getSessionid());
			pstmt.setString(4, userActivity.getUseraction());
			int count = pstmt.executeUpdate();
			if (userActivity.getUserinput().length() > 0 || userActivity.getUseroutput().length() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					pstmt = con.prepareStatement(INSERT_ACTIVITY_IO_SQL);
					pstmt.setInt(1, id);
					pstmt.setString(2, userActivity.getUserinput());
					pstmt.setString(3, userActivity.getUseroutput());
					count = pstmt.executeUpdate();
				}
			}
			con.commit();
			result = count == 1;
		} catch (Exception e) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
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
