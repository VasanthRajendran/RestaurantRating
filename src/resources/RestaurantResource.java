package resources;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import dataObjects.Address;
import dataObjects.Restaurant;
import services.RestaurantService;

@Path("/RestaurantResource")
public class RestaurantResource {
	
	RestaurantService restaurantService = new RestaurantService();
	Gson gson = new Gson();
	
	@POST
	@Path("/addRestaurant")
	@Consumes(MediaType.APPLICATION_JSON)
	public String addRestaurant(String restaurantJson)
	{
		try
		{
			Restaurant restaurant = gson.fromJson(restaurantJson,Restaurant.class);
			int restaurantAddStatus = restaurantService.addRestaurant(restaurant);
			switch(restaurantAddStatus)
			{
				case 201:
					return "Successfully added the restaurant";
				case 2001:
					return "Restaurant is already added for that location";
				case 5000:
					return "Restaurant name is blank, please fill in the values";
				case 5001:
					return "Restaurant category is blank, please fill in the values";
				case 5003:
					return "Could not add the restaurant due to Server issues";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "Facing server issues, try again later";
	}	
	
	
	@PUT
	@Path("/updateRestaurant")
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateRestaurant(String restaurantJson)
	{
		try
		{
			Restaurant restaurant = gson.fromJson(restaurantJson, Restaurant.class);
			int updateRestaurantStatus = restaurantService.updateRestaurant(restaurant);
			switch(updateRestaurantStatus)
			{
				case 201:	
					return "Restaurant Information Sucessfully updated";
				case 5003:
					return "Could not update restaurnt due to internal server issues";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "Could not update restaurnt due to internal server issues";
	}
	
	@GET
	@Path("/getAllRestaurants")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllRestaurants()
	{
		List<Restaurant> restaurant = restaurantService.getAllRestaurants();
		String restaurantJson = gson.toJson(restaurant);
		return restaurantJson;
	}
	
	@GET
	@Path("/restaurants")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRestaurant(@QueryParam("restaurantID") String restaurantID, @QueryParam("name") String name,@QueryParam("category") String category,@QueryParam("city") String city,@QueryParam("totalScore") int totalScore)
	{
		Address address = new Address();
		if(city != null )
			address.setCity(city);
		Restaurant restaurant = new Restaurant(restaurantID,name,category,address);
		List<Restaurant> restaurantList = restaurantService.getRestaurant(restaurant,totalScore);
		return gson.toJson(restaurantList);
	}
}
