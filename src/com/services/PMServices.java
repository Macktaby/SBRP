package com.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.DAO.*;
import com.models.*;

@Path("/pm")
@Produces(MediaType.TEXT_PLAIN)
public class PMServices {

	@POST
	@Path("/addUserToProject")
	public String addProjectUser(@FormParam("name") String name, @FormParam("projectID") int projectID) {

		User user = new User(0, name, projectID);

		ProjectUserDAO dao = new ProjectUserDAO();
		int id = dao.addUser(user);

		return JSONBuilder.convertIDToJSON(id).toJSONString();
	}

	@POST
	@Path("/updateUser")
	public String updateUser(@FormParam("id") int id, @FormParam("name") String name,
			@FormParam("projectID") int projectID) {

		User user = new User(id, name, projectID);

		ProjectUserDAO dao = new ProjectUserDAO();
		String state = dao.updateUser(user);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@POST
	@Path("/deleteUser")
	public String deletePackage(@FormParam("id") int id) {

		ProjectUserDAO dao = new ProjectUserDAO();
		String state = dao.deleteUser(id);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

}
