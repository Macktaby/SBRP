package com.services;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/pm")
public class PMServices {

	@POST
	@Path("/addProduct")
	@Produces(MediaType.TEXT_PLAIN)
	public String addProduct(@FormParam("name") String name, @FormParam("desc") String desc,
			@FormParam("image") String image, @FormParam("quantity") int quantity, @FormParam("price") double price,
			@FormParam("isDayProd") boolean isDayProduct, @FormParam("images") List<String> images,
			@FormParam("catID") int categoryID, @FormParam("catName") String categoryName,
			@FormParam("showroomID") int showroomID, @FormParam("showroomName") String showroomName,
			@FormParam("brandID") int brandID, @FormParam("brandName") String brandName) {

		return JSONBuilder.convertStateToJSON("").toJSONString();
	}

}
