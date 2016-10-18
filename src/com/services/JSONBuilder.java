package com.services;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.models.Brand;
import com.models.Catalog;
import com.models.Category;
import com.models.Designer;
import com.models.DesignerReview;
import com.models.Envogue;
import com.models.House;
import com.models.Product;
import com.models.ProductReview;
import com.models.PromotionLocation;
import com.models.PromotionWishlist;
import com.models.Report;
import com.models.Reservation;
import com.models.ShowRoom;
import com.models.Sponsor;
import com.models.User;

public class JSONBuilder {

	/************************ JSON Build Functions ************************/

	@SuppressWarnings("unchecked")
	public static JSONObject convertStateToJSON(String state) {

		JSONObject json = new JSONObject();
		json.put("state", state);

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertUserToJSON(User user) {
		JSONObject json = new JSONObject();

		if (user == null)
			json.put("state", "false");
		else {
			json.put("id", user.getUserID());
			json.put("uname", user.getUserName());
			json.put("pass", user.getPassword());
			json.put("nickname", user.getNickName());
			json.put("email", user.getEmail());
			json.put("website", user.getWebsite());
			json.put("phone", user.getPhone());
			json.put("regTime", user.getRegisterTime().toString());
			json.put("actKey", user.getActivationKey());
			json.put("status", user.getUserStatus());
			json.put("isAdmin", user.isAdmin());
			json.put("location", user.getLocation());
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertProductsToJSON(ArrayList<Product> products) {

		JSONObject json = new JSONObject();

		if (products == null) {
			json.put("state", "false");
		} else {

			JSONArray jsonArr = new JSONArray();
			for (Product product : products)
				jsonArr.add(convertProductToJSON(product));

			json.put("products", jsonArr);
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertProductToJSON(Product product) {

		JSONObject json = new JSONObject();

		if (product == null)
			json.put("state", "false");
		else {
			json.put("id", product.getProductID());
			json.put("name", product.getName());
			json.put("desc", product.getDescription());
			json.put("image", product.getImage());
			json.put("quantity", product.getQuantity());
			json.put("price", product.getPrice());
			json.put("avgRating", product.getRating());
			json.put("numRating", product.getNumRatingUsers());
			json.put("isDayProd", product.isDayProd());

			json.put("catID", product.getCategoryID());
			json.put("showroomID", product.getShowRoomID());
			json.put("brandID", product.getBrandID());
			json.put("catName", product.getCategoryName());
			json.put("showroomName", product.getShowRoomName());
			json.put("brandName", product.getBrandName());
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertSponsorsToJSON(ArrayList<Sponsor> sponsors) {
		JSONObject json = new JSONObject();

		if (sponsors == null) {
			json.put("state", "false");
		} else {

			JSONArray jsonArr = new JSONArray();
			for (Sponsor sponsor : sponsors)
				jsonArr.add(convertSponsorToJSON(sponsor));

			json.put("sponsors", jsonArr);
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertSponsorToJSON(Sponsor sponsor) {
		JSONObject json = new JSONObject();

		if (sponsor == null)
			json.put("state", "false");
		else {
			json.put("id", sponsor.getSponserID());
			json.put("name", sponsor.getName());
			json.put("image", sponsor.getImage());
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertCatalogsToJSON(ArrayList<Catalog> catalogs) {

		JSONObject json = new JSONObject();

		if (catalogs == null) {
			json.put("state", "false");
		} else {

			JSONArray jsonArr = new JSONArray();
			for (Catalog catalog : catalogs)
				jsonArr.add(convertCatalogToJSON(catalog));

			json.put("catalogs", jsonArr);
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertCatalogToJSON(Catalog catalog) {

		JSONObject json = new JSONObject();

		if (catalog == null)
			json.put("state", "false");
		else {

			json.put("id", catalog.getCatalogID());
			json.put("name", catalog.getName());
			json.put("desc", catalog.getDescription());
			json.put("month", catalog.getMonth());
			json.put("year", catalog.getYear());
			json.put("pdf", catalog.getPdfLink());
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertShowRoomsToJSON(ArrayList<ShowRoom> showrooms) {

		JSONObject json = new JSONObject();

		if (showrooms == null) {
			json.put("state", "false");
		} else {

			JSONArray jsonArr = new JSONArray();
			for (ShowRoom showroom : showrooms)
				jsonArr.add(convertShowRoomToJSON(showroom));

			json.put("showrooms", jsonArr);
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertShowRoomToJSON(ShowRoom showroom) {

		JSONObject json = new JSONObject();

		if (showroom == null)
			json.put("state", "false");
		else {
			json.put("id", showroom.getShowRoomID());
			json.put("name", showroom.getName());
			json.put("desc", showroom.getDescription());
			json.put("address", showroom.getAddress());
			json.put("location", showroom.getLocation());
			json.put("phone", showroom.getPhone());
			json.put("image", showroom.getImage());
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertCategoriesToJSON(ArrayList<Category> categories) {

		JSONObject json = new JSONObject();

		if (categories == null) {
			json.put("state", "false");
		} else {

			JSONArray jsonArr = new JSONArray();
			for (Category category : categories)
				jsonArr.add(convertCategoryToJSON(category));

			json.put("categories", jsonArr);
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertCategoryToJSON(Category category) {

		JSONObject json = new JSONObject();

		if (category == null)
			json.put("state", "false");
		else {
			json.put("id", category.getCategoryID());
			json.put("name", category.getName());
			json.put("desc", category.getDescription());
		}

		return json;

	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertBrandsToJSON(ArrayList<Brand> brands) {

		JSONObject json = new JSONObject();

		if (brands == null) {
			json.put("state", "false");
		} else {

			JSONArray jsonArr = new JSONArray();
			for (Brand brand : brands)
				jsonArr.add(convertBrandToJSON(brand));

			json.put("brands", jsonArr);
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertBrandToJSON(Brand brand) {

		JSONObject json = new JSONObject();

		if (brand == null)
			json.put("state", "false");
		else {
			json.put("id", brand.getBrandID());
			json.put("name", brand.getName());
			json.put("desc", brand.getDescription());
			json.put("image", brand.getImage());
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertImagesToJSON(ArrayList<String> images) {

		JSONObject json = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		JSONObject imgJSON = new JSONObject();
		
		for (String image : images){
			imgJSON = new JSONObject();
			imgJSON.put("image", image);
			jsonArr.add(image);
		}
		
		json.put("images", jsonArr);
		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertDesignersToJSON(ArrayList<Designer> designers) {

		JSONObject json = new JSONObject();

		if (designers == null) {
			json.put("state", "false");
		} else {

			JSONArray jsonArr = new JSONArray();
			for (Designer designer : designers)
				jsonArr.add(convertDesignerToJSON(designer));

			json.put("designers", jsonArr);
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertDesignerToJSON(Designer designer) {

		JSONObject json = new JSONObject();

		if (designer == null)
			json.put("state", "false");
		else {

			json.put("id", designer.getDesignerID());
			json.put("name", designer.getName());
			json.put("email", designer.getEmail());
			json.put("phone", designer.getPhone());
			json.put("address", designer.getAddress());
			json.put("website", designer.getWebsite());
			json.put("rating", designer.getRating());
			json.put("nRating", designer.getnRatingUsers());
			json.put("image", designer.getProfileImage());
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertProductReviewsToJSON(ArrayList<ProductReview> reviews) {

		JSONObject json = new JSONObject();

		if (reviews == null) {
			json.put("state", "false");
		} else {

			JSONArray jsonArr = new JSONArray();
			for (ProductReview review : reviews)
				jsonArr.add(convertProductReviewToJSON(review));

			json.put("reviews", jsonArr);
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertProductReviewToJSON(ProductReview pr) {

		JSONObject json = new JSONObject();

		if (pr == null)
			json.put("state", "false");
		else {

			json.put("userID", pr.getUserID());
			json.put("productID", pr.getProductID());
			json.put("review", pr.getReview());
			json.put("rating", pr.getRating());
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertDesignerReviewsToJSON(ArrayList<DesignerReview> reviews) {

		JSONObject json = new JSONObject();

		if (reviews == null) {
			json.put("state", "false");
		} else {

			JSONArray jsonArr = new JSONArray();
			for (DesignerReview review : reviews)
				jsonArr.add(convertDesignerReviewToJSON(review));

			json.put("reviews", jsonArr);
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertDesignerReviewToJSON(DesignerReview review) {

		JSONObject json = new JSONObject();

		if (review == null)
			json.put("state", "false");
		else {
			json.put("userID", review.getUserID());
			json.put("designerID", review.getDesignerID());
			json.put("review", review.getReview());
			json.put("rating", review.getRating());
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertLocationPromotionToJSON(PromotionLocation promotion) {
		JSONObject json = new JSONObject();

		if (promotion == null)
			json.put("state", "false");
		else {

			json.put("id", promotion.getPromotionID());
			json.put("location", promotion.getLocation());
			json.put("productID", promotion.getProductID());
			json.put("discount", promotion.getDiscount());
			json.put("start", promotion.getStartTime().toString());
			json.put("end", promotion.getEndTime().toString());
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertWishlistPromotionToJSON(PromotionWishlist promotion) {
		JSONObject json = new JSONObject();

		if (promotion == null)
			json.put("state", "false");
		else {

			json.put("id", promotion.getPromotionID());
			json.put("productID", promotion.getProductID());
			json.put("discount", promotion.getDiscount());
			json.put("start", promotion.getStartTime().toString());
			json.put("end", promotion.getEndTime().toString());
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertQuantityToJSON(int quantity) {
		JSONObject json = new JSONObject();

		if (quantity < 0)
			json.put("state", "false");
		else {
			json.put("state", "true");
			json.put("quantity", quantity);
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertReservationToJSON(Reservation reservation) {
		JSONObject json = new JSONObject();

		if (reservation == null)
			json.put("state", "false");
		else {
			json.put("id", reservation.getReservationID());
			json.put("productID", reservation.getProductID());
			json.put("userID", reservation.getUserID());
			json.put("quantity", reservation.getQuantity());
			json.put("time", reservation.getTime().toString());
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertReportToJSON(Report report) {
		JSONObject json = new JSONObject();

		if (report == null)
			json.put("state", "false");
		else {
			json.put("id", report.getReportID());
			json.put("type", report.getType());
			json.put("details", report.getDetails());
			json.put("time", report.getTime().toString());
			json.put("userID", report.getUserID());
			json.put("userName", report.getUserName());
			json.put("prodID", report.getProductID());
			json.put("prodName", report.getProductName());
			json.put("desID", report.getDesignerID());
			json.put("desName", report.getDesignerName());
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertHousesToJSON(ArrayList<House> houses) {
		JSONObject json = new JSONObject();

		if (houses == null) {
			json.put("state", "false");
		} else {

			JSONArray jsonArr = new JSONArray();
			for (House house : houses)
				jsonArr.add(convertHouseToJSON(house));

			json.put("houses", jsonArr);
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertHouseToJSON(House house) {
		JSONObject json = new JSONObject();

		if (house == null)
			json.put("state", "false");
		else {
			json.put("id", house.getHouseID());
			json.put("name", house.getName());
			json.put("desc", house.getDescription());
			json.put("image", house.getImage());
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertEnvoguesToJSON(ArrayList<Envogue> envogues) {
		JSONObject json = new JSONObject();

		if (envogues == null) {
			json.put("state", "false");
		} else {

			JSONArray jsonArr = new JSONArray();
			for (Envogue envogue : envogues)
				jsonArr.add(convertEnvogueToJSON(envogue));

			json.put("envogues", jsonArr);
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertEnvogueToJSON(Envogue envogue) {
		JSONObject json = new JSONObject();

		if (envogue == null)
			json.put("state", "false");
		else {
			json.put("id", envogue.getEnvogueID());
			json.put("name", envogue.getName());
			json.put("desc", envogue.getDescription());
			json.put("image", envogue.getImage());
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertPriceToJSON(double price) {
		JSONObject json = new JSONObject();

		if (price < 0)
			json.put("state", "false");
		else {
			json.put("state", "true");
			json.put("price", price);
		}

		return json;
	}

}
