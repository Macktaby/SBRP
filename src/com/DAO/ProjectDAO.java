package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;
import com.models.*;

public class ProjectDAO {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	public ProjectDAO() {
		conn = DBConnection.getActiveConnection();
	}

	private Project parseProject() throws SQLException {
		Project project = new Project();

		project.setProjectID(rs.getInt("project_id"));
		project.setName(rs.getString("name"));
		project.setTechReflection(rs.getString("tech_ref"));
		project.setBzReflection(rs.getString("bz_ref"));
		project.setMngReflection(rs.getString("mng_ref"));
		project.setParentID(rs.getInt("parent_id"));

		return project;
	}

	public int addProject(Project pj) {

		try {
			String sql = "INSERT INTO `project` (`name`, `tech_ref`, `mng_ref`, `bz_ref`, `parent_id`) "
					+ "VALUES ( ? , ? , ? , ? , ? );";

			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, pj.getName());
			stmt.setString(2, pj.getTechReflection());
			stmt.setString(3, pj.getMngReflection());
			stmt.setString(4, pj.getBzReflection());
			stmt.setInt(5, pj.getParentID());

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

	public String updateProject(Project pj) {

		try {
			String sql = "UPDATE `project` SET `name`=?, `tech_ref`=?, `mng_ref`=?, `bz_ref`=?, `parent_id`=? WHERE `project_id`= ?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, pj.getName());
			stmt.setString(2, pj.getTechReflection());
			stmt.setString(3, pj.getMngReflection());
			stmt.setString(4, pj.getBzReflection());
			stmt.setInt(5, pj.getParentID());
			stmt.setInt(6, pj.getProjectID());

			int nRows = stmt.executeUpdate();
			if (nRows == 1)
				return "true";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "false";
	}

	public String deleteProject(int id) {
		try {
			String sql = "DELETE FROM `project` WHERE `project_id` = ?";

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

	public ArrayList<Project> getParentProjects() {
		try {
			String sql = "SELECT * FROM project WHERE parent_id = 1 AND NOT project_id = 1";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			ArrayList<Project> projects = new ArrayList<Project>();

			while (rs.next())
				projects.add(parseProject());

			return projects;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Project> getSubProjects(int id) {
		try {
			String sql = "SELECT * FROM project WHERE parent_id = ? AND NOT project_id = 1";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			rs = stmt.executeQuery();

			ArrayList<Project> projects = new ArrayList<Project>();

			while (rs.next())
				projects.add(parseProject());

			return projects;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Project getProject(int id) {
		try {
			String sql = "SELECT * FROM project WHERE project_id = ? AND NOT project_id = 1";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			rs = stmt.executeQuery();

			while (rs.next())
				return parseProject();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
