package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.models.*;

public class TaskDAO {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	public TaskDAO() {
		conn = DBConnection.getActiveConnection();
	}

	public int addTask(Task task) {

		try {
			String sql = "INSERT INTO `task` (`name`, `parent_id`, `block_id`, `user_id`, `assigned_to`) "
					+ "VALUES ( ? , ? , ? , ? , ? );";

			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, task.getName());
			stmt.setInt(2, task.getParentID());
			stmt.setInt(3, task.getBlock().getBlockID());
			stmt.setInt(4, task.getUser().getUserID());
			stmt.setInt(5, task.getAssignedTo().getPersonID());

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

	public String updateTask(Task task) {

		try {

			String sql = "UPDATE `task` SET `name`=?, `parent_id`=?, `block_id`=?, `user_id`=?, `assigned_to`=? "
					+ "WHERE `task_id`= ?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, task.getName());
			stmt.setInt(2, task.getParentID());
			stmt.setInt(3, task.getBlock().getBlockID());
			stmt.setInt(4, task.getUser().getUserID());
			stmt.setInt(5, task.getAssignedTo().getPersonID());
			stmt.setInt(6, task.getTaskID());

			int nRows = stmt.executeUpdate();
			if (nRows == 1)
				return "true";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "false";
	}

	public String deleteTask(int id) {
		try {
			String sql = "DELETE FROM `task` WHERE `task_id` = ?";

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

	public String addTaskAttributeValue(int taskID, int attributeID, String date, String value) {

		try {
			String sql = "INSERT INTO `task_attributes` (`task_id`, `attribute_id`, `date`, `value`) "
					+ "VALUES ( ? , ? , ? , ? );";

			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, taskID);
			stmt.setInt(2, attributeID);
			stmt.setString(3, date);
			stmt.setString(4, value);

			stmt.executeUpdate();
			
			return "true";

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
		
		return "false";
	}

	public String updateTaskAttributeValue(int taskID, int attributeID, String date, String value) {

		try {
			String sql = "UPDATE `task_attributes` SET `value`=?"
					+ "WHERE `task_id`= ? AND `attribute_id`=? AND `date`=?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, value);
			stmt.setInt(2, taskID);
			stmt.setInt(3, attributeID);
			stmt.setString(4, date);

			int nRows = stmt.executeUpdate();
			if (nRows == 1)
				return "true";

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}

		return "false";
	}

}
