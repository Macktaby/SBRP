package com.services;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.models.Package;
import com.DAO.*;
import com.models.*;

@Path("/cm")
@Produces(MediaType.TEXT_PLAIN)
public class CMServices {

	@POST
	@Path("/addPackage")
	public String addPackage(@FormParam("name") String name, @FormParam("tech_ref") String tech,
			@FormParam("mng_ref") String mng, @FormParam("bz_ref") String bz, @FormParam("parentID") int parentID) {

		Package pkg = new Package(0, name, tech, mng, bz, parentID);

		PackageDAO dao = new PackageDAO();
		int id = dao.addPackage(pkg);

		return JSONBuilder.convertIDToJSON(id).toJSONString();
	}

	@POST
	@Path("/updatePackage")
	public String updatePackage(@FormParam("id") int id, @FormParam("name") String name,
			@FormParam("tech_ref") String tech, @FormParam("mng_ref") String mng, @FormParam("bz_ref") String bz,
			@FormParam("parentID") int parentID) {

		Package pkg = new Package(id, name, tech, mng, bz, parentID);

		PackageDAO dao = new PackageDAO();
		String state = dao.updatePackage(pkg);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@POST
	@Path("/deletePackage")
	public String deletePackage(@FormParam("id") int id) {

		PackageDAO dao = new PackageDAO();
		String state = dao.deletePackage(id);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@POST
	@Path("/getPackages")
	public String getParentPackages() {

		PackageDAO dao = new PackageDAO();
		ArrayList<Package> packages = dao.getParentPackages();

		return JSONBuilder.convertPackagesToJSON(packages).toJSONString();
	}

	@POST
	@Path("/getSubPackages")
	public String getSubPackages(@FormParam("id") int id) {

		PackageDAO dao = new PackageDAO();
		ArrayList<Package> packages = dao.getSubPackages(id);

		return JSONBuilder.convertPackagesToJSON(packages).toJSONString();
	}

	@POST
	@Path("/getPackage")
	public String getPackage(@FormParam("id") int id) {

		PackageDAO dao = new PackageDAO();
		Package pkg = dao.getPackage(id);

		return JSONBuilder.convertPackageToJSON(pkg).toJSONString();
	}

	@POST
	@Path("/addProject")
	public String addProject(@FormParam("name") String name, @FormParam("tech_ref") String tech,
			@FormParam("mng_ref") String mng, @FormParam("bz_ref") String bz, @FormParam("parentID") int parentID) {

		Project pj = new Project(0, name, tech, mng, bz, parentID);

		ProjectDAO dao = new ProjectDAO();
		int id = dao.addProject(pj);

		return JSONBuilder.convertIDToJSON(id).toJSONString();
	}

	@POST
	@Path("/updateProject")
	public String updateProject(@FormParam("id") int id, @FormParam("name") String name,
			@FormParam("tech_ref") String tech, @FormParam("mng_ref") String mng, @FormParam("bz_ref") String bz,
			@FormParam("parentID") int parentID) {

		Project pj = new Project(id, name, tech, mng, bz, parentID);

		ProjectDAO dao = new ProjectDAO();
		String state = dao.updateProject(pj);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@POST
	@Path("/deleteProject")
	public String deleteProject(@FormParam("id") int id) {

		ProjectDAO dao = new ProjectDAO();
		String state = dao.deleteProject(id);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@POST
	@Path("/getProjects")
	public String getParentProjects() {

		ProjectDAO dao = new ProjectDAO();
		ArrayList<Project> projects = dao.getParentProjects();

		return JSONBuilder.convertProjectsToJSON(projects).toJSONString();
	}

	@POST
	@Path("/getSubProjects")
	public String getSubProjects(@FormParam("id") int id) {

		ProjectDAO dao = new ProjectDAO();
		ArrayList<Project> projects = dao.getSubProjects(id);

		return JSONBuilder.convertProjectsToJSON(projects).toJSONString();
	}

	@POST
	@Path("/getProject")
	public String getProject(@FormParam("id") int id) {

		ProjectDAO dao = new ProjectDAO();
		Project project = dao.getProject(id);

		return JSONBuilder.convertProjectToJSON(project).toJSONString();
	}

	@POST
	@Path("/addPerson")
	public String addPerson(@FormParam("name") String name, @FormParam("role") String role) {

		Person person = new Person(0, name, role);

		PersonDAO dao = new PersonDAO();
		int id = dao.addPerson(person);

		return JSONBuilder.convertIDToJSON(id).toJSONString();
	}

	@POST
	@Path("/updatePerson")
	public String updatePerson(@FormParam("id") int id, @FormParam("name") String name,
			@FormParam("role") String role) {

		Person person = new Person(id, name, role);

		PersonDAO dao = new PersonDAO();
		String state = dao.updatePerson(person);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}
	
	@POST
	@Path("/getPersons")
	public String getPersons(){

		PersonDAO dao = new PersonDAO();
		ArrayList<Person> people = dao.getPeople();

		return JSONBuilder.convertPersonsToJSON(people).toJSONString();		
	}
	
	@POST
	@Path("/getPerson")
	public String getPersons(@FormParam("id") int id){

		PersonDAO dao = new PersonDAO();
		Person person = dao.getPerson(id);

		return JSONBuilder.convertPersonToJSON(person).toJSONString();		
	}

	@POST
	@Path("/deletePerson")
	public String deletePerson(@FormParam("id") int id) {

		PersonDAO dao = new PersonDAO();
		String state = dao.deletePerson(id);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@POST
	@Path("/addAttribute")
	public String addAttrubute(@FormParam("name") String name) {

		Attribute attribute = new Attribute(0, name);

		AttributeDAO dao = new AttributeDAO();
		int id = dao.addAttribute(attribute);

		return JSONBuilder.convertIDToJSON(id).toJSONString();
	}

	@POST
	@Path("/updateAttribute")
	public String updateAttribute(@FormParam("id") int id, @FormParam("name") String name) {

		Attribute attribute = new Attribute(id, name);

		AttributeDAO dao = new AttributeDAO();
		String state = dao.updateAttribute(attribute);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@POST
	@Path("/deleteAttribute")
	public String deleteAttribute(@FormParam("id") int id) {

		AttributeDAO dao = new AttributeDAO();
		String state = dao.deleteAttribute(id);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@GET
	@Path("/")
	public String getJSON() {
		return "{\"state\":\"HELLO\"}";
	}

}
