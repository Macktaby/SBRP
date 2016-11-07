package com.services;

import java.util.ArrayList;

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
	@Path("/addUser")
	public String addUser(@FormParam("name") String name, @FormParam("projectID") int projectID) {

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
	public String deleteUser(@FormParam("id") int id) {

		ProjectUserDAO dao = new ProjectUserDAO();
		String state = dao.deleteUser(id);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@POST
	@Path("/getUsers")
	public String getUsers(@FormParam("id") int projectID) {

		ProjectUserDAO dao = new ProjectUserDAO();
		ArrayList<User> users = dao.getProjectUsers(projectID);

		return JSONBuilder.convertUsersToJSON(users).toJSONString();
	}

	@POST
	@Path("/getUser")
	public String getUser(@FormParam("id") int userID) {

		ProjectUserDAO dao = new ProjectUserDAO();
		User user = dao.getUserByID(userID);

		return JSONBuilder.convertUserToJSON(user).toJSONString();
	}

	@POST
	@Path("/addBlock")
	public String addBlock(@FormParam("name") String name, @FormParam("projectID") int projectID) {

		Block block = new Block(0, name, projectID);

		BlockDAO dao = new BlockDAO();
		int id = dao.addBlock(block);

		return JSONBuilder.convertIDToJSON(id).toJSONString();
	}

	@POST
	@Path("/updateBlock")
	public String updateBlock(@FormParam("id") int id, @FormParam("name") String name,
			@FormParam("projectID") int projectID) {

		Block block = new Block(id, name, projectID);

		BlockDAO dao = new BlockDAO();
		String state = dao.updateBlock(block);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@POST
	@Path("/deleteBlock")
	public String deletePackage(@FormParam("id") int id) {

		BlockDAO dao = new BlockDAO();
		String state = dao.deleteBlock(id);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}
	@POST
	@Path("/getBlocks")
	public String getBlocks(@FormParam("id") int projectID) {

		BlockDAO dao = new BlockDAO();
		ArrayList<Block> blocks = dao.getProjectBlocks(projectID);

		return JSONBuilder.convertBlocksToJSON(blocks).toJSONString();
	}

	@POST
	@Path("/getBlock")
	public String getBlock(@FormParam("id") int blockID) {

		BlockDAO dao = new BlockDAO();
		Block block = dao.getBlockByID(blockID);

		return JSONBuilder.convertBlockToJSON(block).toJSONString();
	}

	@POST
	@Path("/addTask")
	public String addTask(@FormParam("name") String name, @FormParam("parentID") int parentID,
			@FormParam("blockID") int blockID, @FormParam("userID") int userID, @FormParam("projectID") int projectID,
			@FormParam("assigned_to") int assigned_to) {

		Task task = new Task(0, name, blockID, userID, projectID, assigned_to, parentID);

		TaskDAO dao = new TaskDAO();
		int id = dao.addTask(task);

		return JSONBuilder.convertIDToJSON(id).toJSONString();
	}

	@POST
	@Path("/updateTask")
	public String updateTask(@FormParam("id") int id, @FormParam("name") String name,
			@FormParam("parentID") int parentID, @FormParam("blockID") int blockID, @FormParam("userID") int userID,
			@FormParam("projectID") int projectID, @FormParam("assigned_to") int assigned_to) {

		Task task = new Task(id, name, blockID, userID, projectID, assigned_to, parentID);

		TaskDAO dao = new TaskDAO();
		String state = dao.updateTask(task);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@POST
	@Path("/deleteTask")
	public String deleteTask(@FormParam("id") int id) {

		TaskDAO dao = new TaskDAO();
		String state = dao.deleteTask(id);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

}
