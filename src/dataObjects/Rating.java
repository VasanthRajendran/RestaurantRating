package dataObjects;

public class Rating {

	private int cost;
	private 	int food;
	private int cleanliness;
	private int service;
	private Restaurant restaurant;
	private User user;
	private String date;
	
	public Rating(int cost, int food, int cleanliness, int service,Restaurant restaurant, User user, String date)
	{
		this.cost = cost;
		this.food = food;
		this.cleanliness = cleanliness;
		this.service = service;
		this.restaurant = restaurant;
		this.user = user;
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
