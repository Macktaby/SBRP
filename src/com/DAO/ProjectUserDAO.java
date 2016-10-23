package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.models.*;

public class ProjectUserDAO {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	public ProjectUserDAO() {
		conn = DBConnection.getActiveConnection();
	}

	public int addUser(User user) {

		try {
			String sql = "INSERT INTO `user` (`name`, `project_id`) VALUES ( ? , ? );";

			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, user.getName());
			stmt.setInt(2, user.getProject().getProjectID());

			stmt.executeUpdate();

			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String updateUser(User user) {

		try {
			String sql = "UPDATE `user` SET `name`=?, `project_id`=? WHERE `user_id`= ?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, user.getName());
			stmt.setInt(2, user.getProject().getProjectID());
			stmt.setInt(3, user.getUserID());

			int nRows = stmt.executeUpdate();
			if (nRows == 1)
				return "true";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "false";
	}

	public String deleteUser(int id) {
		try {
			String sql = "DELETE FROM `user` WHERE `user_id` = ?";

			PreparedStatement stmt;

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			int nRows = stmt.executeUpdate();
			if (nRows == 1)
				return "true";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "false";
	}

}
