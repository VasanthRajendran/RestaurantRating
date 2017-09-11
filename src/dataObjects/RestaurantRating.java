package dataObjects;

public class RestaurantRating {
	
	private String restaurantName;
	private Double rating;
	

	public RestaurantRating() {
		// TODO Auto-generated constructor stub
	}
	
	public RestaurantRating(String restaurantName,Double rating)
	{
		this.restaurantName = restaurantName;
		this.rating = rating;
	}


	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}
	

}
