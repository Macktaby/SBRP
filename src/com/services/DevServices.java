package com.services;

import java.sql.Connection;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.DAO.*;
//import com.models.*;

@Path("/dev")
public class DevServices {

	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	public String login(@FormParam("email") String email, @FormParam("pass") String pass) {
		return "";
	}

	/************************ For test ONLY ************************/

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String getJson() {
		Connection conn;
		conn = DBConnection.getActiveConnection();
	
//		return conn.toString();

		return "Hello after editing";
		// Connection URL:
		// mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/
	}
}
