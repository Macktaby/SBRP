package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;
import com.models.Package;

public class PackageDAO {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	public PackageDAO() {
		conn = DBConnection.getActiveConnection();
	}

	private Package parsePackage() throws SQLException {
		Package pkg = new Package();

		pkg.setPackageID(rs.getInt("package_id"));
		pkg.setName(rs.getString("name"));
		pkg.setTechReflection(rs.getString("tech_ref"));
		pkg.setBzReflection(rs.getString("bz_ref"));
		pkg.setMngReflection(rs.getString("mng_ref"));
		pkg.setParentID(rs.getInt("parent_id"));

		return pkg;
	}

	public int addPackage(Package pkg) {

		try {
			String sql = "INSERT INTO `package` (`name`, `tech_ref`, `mng_ref`, `bz_ref`, `parent_id`) "
					+ "VALUES ( ? , ? , ? , ? , ? );";

			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, pkg.getName());
			stmt.setString(2, pkg.getTechReflection());
			stmt.setString(3, pkg.getMngReflection());
			stmt.setString(4, pkg.getBzReflection());
			stmt.setInt(5, pkg.getParentID());

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

	public String updatePackage(Package pkg) {

		try {
			String sql = "UPDATE `package` SET `name`=?, `tech_ref`=?, `mng_ref`=?, `bz_ref`=?, `parent_id`=? WHERE `package_id`= ?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, pkg.getName());
			stmt.setString(2, pkg.getTechReflection());
			stmt.setString(3, pkg.getMngReflection());
			stmt.setString(4, pkg.getBzReflection());
			stmt.setInt(5, pkg.getParentID());
			stmt.setInt(6, pkg.getPackageID());

			int nRows = stmt.executeUpdate();
			if (nRows == 1)
				return "true";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "false";
	}

	public String deletePackage(int id) {
		try {
			String sql = "DELETE FROM `package` WHERE `package_id` = ?";

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

	public ArrayList<Package> getParentPackages() {

		try {
			String sql = "SELECT * FROM package WHERE parent_id = 1 AND NOT package_id = 1";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			ArrayList<Package> packages = new ArrayList<Package>();

			while (rs.next())
				packages.add(parsePackage());

			return packages;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Package> getSubPackages(int id) {
		try {
			String sql = "SELECT * FROM package WHERE parent_id = ? AND NOT package_id = 1";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			rs = stmt.executeQuery();

			ArrayList<Package> packages = new ArrayList<Package>();

			while (rs.next())
				packages.add(parsePackage());

			return packages;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Package getPackage(int id) {
		try {
			String sql = "SELECT * FROM package WHERE package_id = ? AND NOT package_id = 1";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			rs = stmt.executeQuery();

			while (rs.next())
				return parsePackage();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
