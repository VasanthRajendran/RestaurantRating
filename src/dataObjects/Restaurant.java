package dataObjects;

public class Restaurant {
	
	private String restaurantID;
	private String name;
	private String category;
	private Address address;
	
	public Restaurant(String restaurantID,String name,String category,Address address)
	{
		this.restaurantID = restaurantID;
		this.name = name;
		this.category = category;
		this.address = address;
	}

	public String getRestaurantID() {
		return restaurantID;
	}

	public void setRestaurantID(String restaurantID) {
		this.restaurantID = restaurantID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	

}
