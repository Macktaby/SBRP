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

			json.put("state", "true");
			json.put("packages", jsonArr);
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertPackageToJSON(Package pkg) {
		JSONObject json = new JSONObject();

		if (pkg == null)
			json.put("state", "false");
		else {
			json.put("state", "true");
			json.put("id", pkg.getPackageID());
			json.put("name", pkg.getName());
			json.put("tech_ref", pkg.getTechReflection());
			json.put("mng_ref", pkg.getMngReflection());
			json.put("bz_ref", pkg.getBzReflection());
			json.put("parent_id", pkg.getParentID());
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertProjectsToJSON(ArrayList<Project> projects) {
		JSONObject json = new JSONObject();

		if (projects == null) {
			json.put("state", "false");
		} else {

			JSONArray jsonArr = new JSONArray();
			for (Project project : projects)
				jsonArr.add(convertProjectToJSON(project));

			json.put("state", "true");
			json.put("projects", jsonArr);
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertProjectToJSON(Project project) {
		JSONObject json = new JSONObject();

		if (project == null)
			json.put("state", "false");
		else {
			json.put("state", "true");
			json.put("id", project.getProjectID());
			json.put("name", project.getName());
			json.put("tech_ref", project.getTechReflection());
			json.put("mng_ref", project.getMngReflection());
			json.put("bz_ref", project.getBzReflection());
			json.put("parent_id", project.getParentID());
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertPersonsToJSON(ArrayList<Person> people) {
		JSONObject json = new JSONObject();

		if (people == null) {
			json.put("state", "false");
		} else {

			JSONArray jsonArr = new JSONArray();
			for (Person person : people)
				jsonArr.add(convertPersonToJSON(person));

			json.put("state", "true");
			json.put("people", jsonArr);
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertPersonToJSON(Person person) {
		JSONObject json = new JSONObject();

		if (person == null)
			json.put("state", "false");
		else {
			json.put("state", "true");
			json.put("id", person.getPersonID());
			json.put("name", person.getName());
			json.put("role", person.getRole());
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertAttributesToJSON(ArrayList<Attribute> attributes) {
		JSONObject json = new JSONObject();

		if (attributes == null) {
			json.put("state", "false");
		} else {

			JSONArray jsonArr = new JSONArray();
			for (Attribute attribute : attributes)
				jsonArr.add(convertAttributeToJSON(attribute));

			json.put("state", "true");
			json.put("attributes", jsonArr);
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertAttributeToJSON(Attribute attribute) {
		JSONObject json = new JSONObject();

		if (attribute == null)
			json.put("state", "false");
		else {
			json.put("state", "true");
			json.put("id", attribute.getAttributeID());
			json.put("name", attribute.getName());
		}

		return json;
	}

}