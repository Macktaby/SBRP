package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;
import com.models.*;

public class ProjectUserDAO {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	public ProjectUserDAO() {
		conn = DBConnection.getActiveConnection();
	}

	private User parseUser() throws SQLException {
		User user = new User();

		user.setUserID(rs.getInt("user_id"));
		user.setName(rs.getString("user.name"));

		return user;
	}

	private Project parseProject() throws SQLException {
		Project project = new Project();

		project.setProjectID(rs.getInt("project_id"));
		project.setName(rs.getString("project.name"));
		project.setTechReflection(rs.getString("tech_ref"));
		project.setBzReflection(rs.getString("bz_ref"));
		project.setMngReflection(rs.getString("mng_ref"));
		project.setParentID(rs.getInt("parent_id"));

		return project;
	}

	private User parseUserAndProject() throws SQLException {
		User user = new User();

		user.setUserID(rs.getInt("user_id"));
		user.setName(rs.getString("name"));
		user.setProject(parseProject());

		return user;
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

	public ArrayList<User> getProjectUsers(int projectID) {
		try {
			String sql = "SELECT * FROM user where project_id = ?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, projectID);

			rs = stmt.executeQuery();

			ArrayList<User> users = new ArrayList<User>();

			while (rs.next())
				users.add(parseUser());

			return users;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public User getUserByID(int userID) {
		try {
			String sql = "SELECT * FROM user, project WHERE user_id = ? AND user.project_id = project.project_id";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userID);

			rs = stmt.executeQuery();

			if (rs.next())
				return (parseUserAndProject());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
