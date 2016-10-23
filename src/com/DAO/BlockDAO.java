package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.models.*;

public class BlockDAO {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	public BlockDAO() {
		conn = DBConnection.getActiveConnection();
	}

	public int addBlock(Block block) {

		try {
			String sql = "INSERT INTO `block` (`name`, `project_id`) VALUES ( ? , ? );";

			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, block.getName());
			stmt.setInt(2, block.getProject().getProjectID());

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

	public String updateBlock(Block block) {

		try {
			String sql = "UPDATE `block` SET `name`=?, `project_id`=? WHERE `block_id`= ?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, block.getName());
			stmt.setInt(2, block.getProject().getProjectID());
			stmt.setInt(3, block.getBlockID());

			int nRows = stmt.executeUpdate();
			if (nRows == 1)
				return "true";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "false";
	}

	public String deleteBlock(int id) {
		try {
			String sql = "DELETE FROM `block` WHERE `block_id` = ?";

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
