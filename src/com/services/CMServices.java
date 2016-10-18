package com.services;

import java.sql.Timestamp;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/cm")
public class CMServices {

	@POST
	@Path("/addProduct")
	@Produces(MediaType.TEXT_PLAIN)
	public String addProduct(@FormParam("name") String name, @FormParam("desc") String desc,
			@FormParam("image") String image, @FormParam("quantity") int quantity, @FormParam("price") double price,
			@FormParam("isDayProd") boolean isDayProduct, @FormParam("images") List<String> images,
			@FormParam("catID") int categoryID, @FormParam("catName") String categoryName,
			@FormParam("showroomID") int showroomID, @FormParam("showroomName") String showroomName,
			@FormParam("brandID") int brandID, @FormParam("brandName") String brandName) {

		Product product = new Product(0, name, desc, image, quantity, price, 0, 0, isDayProduct, images, categoryID,
				categoryName, showroomID, showroomName, brandID, brandName);

		ProductBean pb = new ProductBean();
		product = pb.addProduct(product);

		if (product != null)
			addProductImages(product.getProductID(), images);

		return JSONBuilder.convertProductToJSON(product).toJSONString();
	}

}
