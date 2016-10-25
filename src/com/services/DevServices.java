package com.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.DAO.*;
//import com.models.*;

@Path("/dev")
@Produces(MediaType.TEXT_PLAIN)
public class DevServices {

	@POST
	@Path("/fillAttributeValue")
	public String fillAttributeValue(@FormParam("taskID") int taskID, @FormParam("attributeID") int attributeID,
			@FormParam("value") String value, @FormParam("date") String date) {

		TaskDAO dao = new TaskDAO();
		String state = dao.addTaskAttributeValue(taskID, attributeID, date, value);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@POST
	@Path("/updateAttributeValue")
	public String update(@FormParam("taskID") int taskID, @FormParam("attributeID") int attributeID,
			@FormParam("value") String value, @FormParam("date") String date) {

		TaskDAO dao = new TaskDAO();
		String state = dao.updateTaskAttributeValue(taskID, attributeID, date, value);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@GET
	@Path("/")
	public String getJson() {
		return "Hello after editing";
	}
}
