package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.Product;
import com.mysql.jdbc.Statement;

public class ProductBean {

	private Connection conn;

	public ProductBean() {
		conn = DBConnection.getActiveConnection();
	}

	private Product parseProduct(ResultSet rs) throws SQLException {

		Product product = new Product();

		product.setProductID(rs.getInt("product_id"));
		product.setName(rs.getString("name"));
		product.setDescription(rs.getString("desc"));
		product.setImage(rs.getString("image"));
		product.setQuantity(rs.getInt("quantity"));
		product.setPrice(rs.getDouble("price"));
		product.setRating(rs.getDouble("rating"));
		product.setNumRatingUsers(rs.getInt("n_ratings"));
		product.setDayProd(rs.getBoolean("is_day_prod"));

		product.setCategoryID(rs.getInt("category_id"));
		product.setCategoryName(rs.getString("category_name"));
		product.setShowRoomID(rs.getInt("showroom_id"));
		product.setShowRoomName(rs.getString("showroom_name"));
		product.setBrandID(rs.getInt("brand_id"));
		product.setBrandName(rs.getString("brand_name"));

		return product;
	}

	public ArrayList<Product> getProductsOfTheDay() {

		try {
			String sql = "SELECT * FROM product WHERE is_day_prod = true";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			ArrayList<Product> products = new ArrayList<Product>();

			while (rs.next())
				products.add(parseProduct(rs));

			return products;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Product> getAllProducts() {

		try {
			String sql = "SELECT * FROM product";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			ArrayList<Product> products = new ArrayList<Product>();

			while (rs.next())
				products.add(parseProduct(rs));

			return products;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Product> getFilteredProducts(int brandID, int categoryID, int showRoomID) {

		try {
			int count = 0;
			boolean selection[] = { false, false, false };
			String sql = buildFilterSQL(brandID, categoryID, showRoomID, selection);

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			if (selection[0])
				stmt.setInt(++count, brandID);
			if (selection[1])
				stmt.setInt(++count, categoryID);
			if (selection[2])
				stmt.setInt(++count, showRoomID);

			ResultSet rs = stmt.executeQuery();

			ArrayList<Product> products = new ArrayList<Product>();

			while (rs.next())
				products.add(parseProduct(rs));

			return products;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private String buildFilterSQL(int brandID, int categoryID, int showRoomID, boolean[] selection)
			throws SQLException {

		String sql = "SELECT * FROM product";
		boolean flag = true;

		if (brandID != 0) {
			if (flag)
				sql += " WHERE ";
			else
				sql += " AND ";

			flag = false;

			sql += "brand_id = ?";
			selection[0] = true;
		}

		if (categoryID != 0) {
			if (flag)
				sql += " WHERE ";
			else
				sql += " AND ";

			flag = false;

			sql += "category_id = ?";
			selection[1] = true;
		}

		if (showRoomID != 0) {
			if (flag)
				sql += " WHERE ";
			else
				sql += " AND ";

			flag = false;
			sql += "showroom_id = ?";
			selection[2] = true;
		}

		return sql;
	}

	public Product addProduct(Product product) {

		try {
			String sql = "INSERT INTO `product` "
					+ "(`name`, `desc`, `image`, `quantity`, `price`, `rating`, `n_ratings`, "
					+ "`is_day_prod`, `category_id`, `showroom_id`, `brand_id` , `category_name`, `showroom_name`, `brand_name`) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, product.getName());
			stmt.setString(2, product.getDescription());
			stmt.setString(3, product.getImage());
			stmt.setInt(4, product.getQuantity());
			stmt.setDouble(5, product.getPrice());
			stmt.setDouble(6, product.getRating());
			stmt.setInt(7, product.getNumRatingUsers());
			stmt.setBoolean(8, product.isDayProd());
			stmt.setInt(9, product.getCategoryID());
			stmt.setInt(10, product.getShowRoomID());
			stmt.setInt(11, product.getBrandID());
			stmt.setString(12, product.getCategoryName());
			stmt.setString(13, product.getShowRoomName());
			stmt.setString(14, product.getBrandName());

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				product.setProductID(rs.getInt(1));

				return product;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String updateProduct(Product product) {

		try {
			String sql = "UPDATE `product` SET `name`=?, `desc`=?, `image`=?, `quantity`=?, `price`=?, `rating`=?,"
					+ "`n_ratings`=?, `is_day_prod`=?, `category_id`=?, `showroom_id`=?, `brand_id`=?, "
					+ "`category_name` = ?,`showroom_name` = ?,`brand_name` = ? " + "WHERE `product_id`= ?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, product.getName());
			stmt.setString(2, product.getDescription());
			stmt.setString(3, product.getImage());
			stmt.setInt(4, product.getQuantity());
			stmt.setDouble(5, product.getPrice());
			stmt.setDouble(6, product.getRating());
			stmt.setInt(7, product.getNumRatingUsers());
			stmt.setBoolean(8, product.isDayProd());
			stmt.setInt(9, product.getCategoryID());
			stmt.setInt(10, product.getShowRoomID());
			stmt.setInt(11, product.getBrandID());
			stmt.setString(12, product.getCategoryName());
			stmt.setString(13, product.getShowRoomName());
			stmt.setString(14, product.getBrandName());
			stmt.setInt(15, product.getProductID());

			int nRows = stmt.executeUpdate();
			if (nRows == 1)
				return "true";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "false";
	}

	public String deleteProduct(int productID) {

		try {
			String sql = "DELETE FROM `product` WHERE `product_id` = ?";

			PreparedStatement stmt;

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, productID);

			int nRows = stmt.executeUpdate();
			if (nRows == 1)
				return "true";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "false";
	}

	public int getProductQuantity(int productID) {
		try {
			String sql = "SELECT quantity FROM product WHERE product_id = ?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, productID);
			ResultSet rs = stmt.executeQuery();

			if (rs.next())
				return rs.getInt("quantity");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	public String reserveProductQuantity(int productID, int quantity) {

		int productQuantity = getProductQuantity(productID);

		if (productQuantity < quantity)
			return "Available quantity NOW is " + productQuantity;

		try {
			String sql = "UPDATE `product` SET `quantity`=? WHERE `product_id`= ?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, productQuantity - quantity);
			stmt.setInt(2, productID);

			int nRows = stmt.executeUpdate();
			if (nRows == 1)
				return "true";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "false";
	}

	public String addProductQuantity(int productID, int quantity) {

		int productQuantity = getProductQuantity(productID);

		try {
			String sql = "UPDATE `product` SET `quantity`=? WHERE `product_id`= ?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, productQuantity + quantity);
			stmt.setInt(2, productID);

			int nRows = stmt.executeUpdate();
			if (nRows == 1)
				return "true";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "false";
	}

	public String addDayProduct(int productID) {
		try {
			String sql = "UPDATE `product` SET `is_day_prod`=? WHERE `product_id`= ?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			stmt.setBoolean(1, true);
			stmt.setInt(2, productID);

			int nRows = stmt.executeUpdate();
			if (nRows == 1)
				return "true";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "false";
	}

	public ArrayList<Product> getUserFavoriteProducts(int id) {

		try {
			String sql = "SELECT * FROM product, wishlist "
					+ "WHERE wishlist.user_id=? AND product.product_id = wishlist.product_id";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			ArrayList<Product> products = new ArrayList<Product>();

			while (rs.next())
				products.add(parseProduct(rs));

			return products;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Product> getUserReservedProducts(int id) {

		try {
			String sql = "SELECT * FROM product, reservation "
					+ "WHERE reservation.user_id=? AND product.product_id = reservation.product_id";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			ArrayList<Product> products = new ArrayList<Product>();

			while (rs.next())
				products.add(parseProduct(rs));

			return products;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
