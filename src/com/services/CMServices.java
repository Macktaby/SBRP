package com.services;

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

	
	@GET
	@Path("/")
	public String addProduct() {
		return "{\"state\":\"HELLO\"}";
	}

}
