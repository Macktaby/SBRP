package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;
import com.models.*;

public class TaskDAO {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	public TaskDAO() {
		conn = DBConnection.getActiveConnection();
	}

	private Task parseTask() throws SQLException {
		Task task = new Task();

		task.setTaskID(rs.getInt("task_id"));
		task.setName(rs.getString("task.name"));
		task.setParentID(rs.getInt("task.parent_id"));
		task.setDevComment(rs.getString("dev_comment"));

		task.setBlock(parseBlock());
		task.setAssignedTo(parsePerson());
		task.setUser(parseUser());

		return task;
	}

	private Block parseBlock() throws SQLException {
		Block block = new Block();

		block.setBlockID(rs.getInt("block.block_id"));
		block.setName(rs.getString("block.name"));

		return block;
	}

	private User parseUser() throws SQLException {
		User user = new User();

		user.setUserID(rs.getInt("user.user_id"));
		user.setName(rs.getString("user.name"));

		return user;
	}

	private Person parsePerson() throws SQLException {
		Person person = new Person();

		person.setPersonID(rs.getInt("people.person_id"));
		person.setName(rs.getString("people.name"));
		person.setRole(rs.getString("people.role"));

		return person;
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

	public ArrayList<Task> getSubTasks(int id) {

		try {
			String sql = "SELECT * FROM task, block, user, people " + "WHERE parent_id = ? "
					+ "AND block.block_id = task.block_id " + "AND user.user_id = task.user_id "
					+ "AND people.person_id = task.assigned_to " + "AND NOT task_id = 1";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			rs = stmt.executeQuery();

			ArrayList<Task> tasks = new ArrayList<Task>();

			while (rs.next())
				tasks.add(parseTask());

			return tasks;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
