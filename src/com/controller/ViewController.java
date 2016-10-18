package com.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;

@Path("/")
@Produces("text/html")
public class ViewController {

	@GET
	@Path("/signup")
	@Produces("text/html")
	public Response signUp() {
		try {
			return Response.ok(new Viewable("/WEB-INF/jsp/signup")).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/home")
	@Produces("text/html")
	public Response home() {
		try {
			return Response.ok(new Viewable("/WEB-INF/jsp/home")).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/signupViewer")
	@Produces(MediaType.TEXT_PLAIN)
	public String getJson() {
		return "Hello From ViewController";
	}

}
