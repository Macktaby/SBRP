package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;
import com.models.*;

public class BlockDAO {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	public BlockDAO() {
		conn = DBConnection.getActiveConnection();
	}

	private Block parseBlock() throws SQLException {
		Block block = new Block();

		block.setBlockID(rs.getInt("block_id"));
		block.setName(rs.getString("name"));

		return block;
	}

	private Block parseBlockAndProject() throws SQLException {
		Block block = new Block();

		block.setBlockID(rs.getInt("block_id"));
		block.setName(rs.getString("name"));
		block.setProject(parseProject());

		return block;
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

	public ArrayList<Block> getProjectBlocks(int projectID) {
		try {
			String sql = "SELECT * FROM block where project_id = ?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, projectID);

			rs = stmt.executeQuery();

			ArrayList<Block> blocks = new ArrayList<Block>();

			while (rs.next())
				blocks.add(parseBlock());

			return blocks;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Block getBlockByID(int blockID) {
		try {
			String sql = "SELECT * FROM block, project WHERE block_id = ? AND block.project_id = project.project_id";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, blockID);

			rs = stmt.executeQuery();

			if (rs.next())
				return (parseBlockAndProject());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
