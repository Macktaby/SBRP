package com.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.models.*;

public class JSONBuilder {

	@SuppressWarnings("unchecked")
	public static JSONObject convertStateToJSON(String state) {

		JSONObject json = new JSONObject();
		json.put("state", state);

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertIDToJSON(int id) {

		JSONObject json = new JSONObject();
		json.put("id", id);

		return json;
	}

//	@SuppressWarnings("unchecked")
//	public static JSONObject convertProductsToJSON(ArrayList<Product> products) {
//
//		JSONObject json = new JSONObject();
//
//		if (products == null) {
//			json.put("state", "false");
//		} else {
//
//			JSONArray jsonArr = new JSONArray();
//			for (Product product : products)
//				jsonArr.add(convertProductToJSON(product));
//
//			json.put("products", jsonArr);
//		}
//
//		return json;
//	}
//
//	@SuppressWarnings("unchecked")
//	public static JSONObject convertProductToJSON(Product product) {
//
//		JSONObject json = new JSONObject();
//
//		if (product == null)
//			json.put("state", "false");
//		else {
//			json.put("id", product.getProductID());
//			json.put("name", product.getName());
//			json.put("desc", product.getDescription());
//			json.put("image", product.getImage());
//			json.put("quantity", product.getQuantity());
//			json.put("price", product.getPrice());
//			json.put("avgRating", product.getRating());
//			json.put("numRating", product.getNumRatingUsers());
//			json.put("isDayProd", product.isDayProd());
//
//			json.put("catID", product.getCategoryID());
//			json.put("showroomID", product.getShowRoomID());
//			json.put("brandID", product.getBrandID());
//			json.put("catName", product.getCategoryName());
//			json.put("showroomName", product.getShowRoomName());
//			json.put("brandName", product.getBrandName());
//		}
//
//		return json;
//	}

}
