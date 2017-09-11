package resources;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import dataObjects.Rating;
import dataObjects.RestaurantRating;
import dataObjects.UserRating;
import services.RatingService;

@Path("/RatingResource")
public class RatingResource {
	RatingService ratingService = new RatingService();
	Gson gson = new Gson();

	@POST
	@Path("/addRating")
	@Consumes(MediaType.APPLICATION_JSON)
	public String addRating(String ratingJson) {

		Rating rating = gson.fromJson(ratingJson, Rating.class);
		int ratingStatus = ratingService.createRating(rating);
		switch (ratingStatus) {
		case 201:
			return "Success";
		case 5000:
			return "This user has already rated this restaurant for this location";
		case 9000:
			return "Could not add ratings";
		}
		return "Could not add the ratings";
	}

	@PUT
	@Path("/updateRating")
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateRating(String ratingJson) {
		RatingService ratingService = new RatingService();
		try {
			Rating rating = gson.fromJson(ratingJson, Rating.class);
			int updateRatingStatus = ratingService.updateRating(rating);
			switch (updateRatingStatus) {
			case 201:
				return "Success";
			case 9000:
				return "Internal Server error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "update Failed";
	}

	@GET
	@Path("/getRating/{restaurantID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRating(@PathParam("restaurantID") String restaurantID) {
		System.out.println("Reaching here");
		RestaurantRating restaurantRating = ratingService.getRating(restaurantID);
		return gson.toJson(restaurantRating);
	}

	@GET
	@Path("/UserRating/{userID}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getUserRating(@PathParam("userID") String userID) {
		System.out.println("Reaching here");
		List<UserRating> userRating = ratingService.getUserRating(userID);
		return gson.toJson(userRating);
	}

}
