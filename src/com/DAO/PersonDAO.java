package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;
import com.models.*;

public class PersonDAO {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	public PersonDAO() {
		conn = DBConnection.getActiveConnection();
	}

	private Person parsePerson() throws SQLException {
		Person person = new Person();

		person.setPersonID(rs.getInt("person_id"));
		person.setName(rs.getString("name"));
		person.setRole(rs.getString("role"));

		return person;
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

	public ArrayList<Person> getPeople() {
		try {
			String sql = "SELECT * FROM people";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			ArrayList<Person> people = new ArrayList<Person>();

			while (rs.next())
				people.add(parsePerson());

			return people;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Person getPerson(int id) {
		try {
			String sql = "SELECT * FROM people where person_id = ?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			rs = stmt.executeQuery();

			if (rs.next())
				return (parsePerson());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
