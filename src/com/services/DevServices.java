package com.services;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.DAO.BrandBean;
import com.DAO.CatalogBean;
import com.DAO.CategoryBean;
import com.DAO.DesignerBean;
import com.DAO.DesignerImagesBean;
import com.DAO.DesignerReviewBean;
import com.DAO.EnvogueBean;
import com.DAO.FavoriteProductBean;
import com.DAO.HouseBean;
import com.DAO.ProductBean;
import com.DAO.ProductImagesBean;
import com.DAO.ProductReviewBean;
import com.DAO.PromotionLocationBean;
import com.DAO.PromotionWishlistBean;
import com.DAO.ReportBean;
import com.DAO.ReservationBean;
import com.DAO.ShowRoomBean;
import com.DAO.SponsorBean;
import com.DAO.UserBean;
import com.models.Brand;
import com.models.Catalog;
import com.models.Category;
import com.models.Designer;
import com.models.DesignerReview;
import com.models.Envogue;
import com.models.FavoriteProduct;
import com.models.House;
import com.models.Product;
import com.models.ProductReview;
import com.models.PromotionLocation;
import com.models.PromotionWishlist;
import com.models.Report;
import com.models.Reservation;
import com.models.ShowRoom;
import com.models.Sponsor;
import com.models.User;

@Path("/dev")
public class DevServices {

	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	public String login(@FormParam("email") String email, @FormParam("pass") String pass) {

		UserBean ub = new UserBean();
		User user = ub.getUser(email, pass);

		return JSONBuilder.convertUserToJSON(user).toJSONString();
	}

	/************************ For test ONLY ************************/

	@POST
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String getJson() {
		return "Hello after editing";
		// Connection URL:
		// mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/
	}
}
