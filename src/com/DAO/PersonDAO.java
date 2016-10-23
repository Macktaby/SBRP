package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.models.*;

public class PersonDAO {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	public PersonDAO() {
		conn = DBConnection.getActiveConnection();
	}

	public int addPerson(Person person) {

		try {
			String sql = "INSERT INTO `people` (`name`, `role`) VALUES ( ? , ? );";

			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, person.getName());
			stmt.setString(2, person.getRole());

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

	public String updatePerson(Person person) {

		try {
			String sql = "UPDATE `people` SET `name`=?, `role`=? WHERE `person_id`= ?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, person.getName());
			stmt.setString(2, person.getRole());
			stmt.setInt(3, person.getPersonID());

			int nRows = stmt.executeUpdate();
			if (nRows == 1)
				return "true";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "false";
	}

	public String deletePerson(int id) {
		try {
			String sql = "DELETE FROM `people` WHERE `person_id` = ?";

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
