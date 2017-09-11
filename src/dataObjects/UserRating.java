package dataObjects;

public class UserRating {
	
	private String restaurantName;
	private int cost;
	private 	int food;
	private int cleanliness;
	private int service;
	
	
	public UserRating(String restaurantName,int cost,int food, int cleanliness, int service)
	{
		this.cost = cost;
		this.food = food;
		this.cleanliness = cleanliness;
		this.service = service;
		this.restaurantName = restaurantName;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getFood() {
		return food;
	}

	public void setFood(int food) {
		this.food = food;
	}

	public int getCleanliness() {
		return cleanliness;
	}

	public void setCleanliness(int cleanliness) {
		this.cleanliness = cleanliness;
	}

	public int getService() {
		return service;
	}

	public void setService(int service) {
		this.service = service;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	
	
}
