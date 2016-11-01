package com.services;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.models.*;
import com.models.Package;

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

	@SuppressWarnings("unchecked")
	public static JSONObject convertPackagesToJSON(ArrayList<Package> packages) {
		JSONObject json = new JSONObject();

		if (packages == null) {
			json.put("state", "false");
		} else {

			JSONArray jsonArr = new JSONArray();
			for (Package pkg : packages)
				jsonArr.add(convertPackageToJSON(pkg));

			json.put("products", jsonArr);
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertPackageToJSON(Package pkg) {
		JSONObject json = new JSONObject();

		if (pkg == null)
			json.put("state", "false");
		else {
			json.put("id", pkg.getPackageID());
			json.put("name", pkg.getName());
			json.put("tech_ref", pkg.getTechReflection());
			json.put("mng_ref", pkg.getMngReflection());
			json.put("bz_ref", pkg.getBzReflection());
			json.put("parent_id", pkg.getParentID());
		}

		return json;
	}

}