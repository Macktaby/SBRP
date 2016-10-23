package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.models.*;

public class AttributeDAO {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	public AttributeDAO() {
		conn = DBConnection.getActiveConnection();
	}

	public int addAttribute(Attribute attribute) {

		try {
			String sql = "INSERT INTO `attribute` (`name`) VALUES ( ? );";

			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, attribute.getName());

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

	public String updateAttribute(Attribute attribute) {

		try {
			String sql = "UPDATE `attribute` SET `name`=? WHERE `attribute_id`= ?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, attribute.getName());
			stmt.setInt(2, attribute.getAttributeID());

			int nRows = stmt.executeUpdate();
			if (nRows == 1)
				return "true";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "false";
	}

	public String deleteAttribute(int id) {
		try {
			String sql = "DELETE FROM `attribute` WHERE `attribute_id` = ?";

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
