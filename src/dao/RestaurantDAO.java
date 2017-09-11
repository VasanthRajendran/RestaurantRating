package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dataObjects.Address;
import dataObjects.Restaurant;

public class RestaurantDAO {

	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	DBConnection db = new DBConnection();

	public boolean createRestaurant(Restaurant restaurant) {
		try {
			connect = db.connect();
			preparedStatement = connect.prepareStatement("Insert into restaurant values (?,?,?)");
			preparedStatement.setString(1, restaurant.getRestaurantID());
			preparedStatement.setString(2, restaurant.getName());
			preparedStatement.setString(3, restaurant.getCategory());
			System.out.println(restaurant.getCategory());
			preparedStatement.execute();
			Address address = restaurant.getAddress();
			preparedStatement = connect.prepareStatement("Insert into Address values (?,?,?,?,?)");
			preparedStatement.setString(1, address.getAddress());
			preparedStatement.setString(2, restaurant.getRestaurantID());
			preparedStatement.setString(3, address.getCity());
			preparedStatement.setString(4, address.getState());
			preparedStatement.setString(5, address.getZipCode());
			preparedStatement.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isRestaurantAlreadyExists(String restaurantName,String zipCode) {
		try {
			System.out.println(restaurantName);
			System.out.println(zipCode);
			connect = db.connect();
			preparedStatement = connect.prepareStatement("select * from restaurant inner join address on restaurant.restaurantID = address.restaurantID where zipCode = ?");
			preparedStatement.setString(1, zipCode);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getString("Name").equalsIgnoreCase(restaurantName))
					return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateRestaurant(Restaurant restaurant) {
		try {
			connect = db.connect();
			preparedStatement = connect
					.prepareStatement("Update restaurant set name = ?, category =  ? where restaurantid = ?");
			preparedStatement.setString(1, restaurant.getName());
			preparedStatement.setString(2, restaurant.getCategory());
			preparedStatement.setString(3, restaurant.getRestaurantID());
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Restaurant> getAllRestaurants() {
		try {
			List<Restaurant> restaurantInfo = new ArrayList<Restaurant>();
			connect = db.connect();
			System.out.println("checking");
			preparedStatement= connect.prepareStatement("Select * from address inner join restaurant on address.restaurantID = restaurant.restaurantID");
		//	connect.prepareStatement("Select * from user");
			ResultSet newAddressResultSet = preparedStatement.executeQuery();
			while (newAddressResultSet.next()) {
				String restaurantId = newAddressResultSet.getString("restaurantid");
				String restaurantName = newAddressResultSet.getString("name");
				String category = newAddressResultSet.getString("category");
				String address = newAddressResultSet.getString("address");
				String state = newAddressResultSet.getString("state");
				String city = newAddressResultSet.getString("city");
				String zipCode = newAddressResultSet.getString("zipCode");
				Address addressObject = new Address(address, city, state, zipCode);
				restaurantInfo.add(new Restaurant(restaurantId, restaurantName, category, addressObject));
			}
			return restaurantInfo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Restaurant> getRestaurant(Restaurant restaurant, int totalScore) {
		try {
			List<Restaurant> restaurantInfo = new ArrayList<Restaurant>();
			connect = db.connect();
			StringBuilder stringBuilder = new StringBuilder();
			int filterNumber  = 0;
			if(restaurant.getName() != null)
				stringBuilder.append("Name = '" + restaurant.getName() + "'");
				filterNumber++;
			if(restaurant.getAddress().getCity() != null)
				if(filterNumber == 0)
					stringBuilder.append("city = '" + restaurant.getAddress().getCity() + "'");
				else
					stringBuilder.append(" and city = '" + restaurant.getAddress().getCity() + "'");
			if(restaurant.getCategory() != null)
				if(filterNumber == 0)
					stringBuilder.append("category like '" + restaurant.getCategory() + "'");
				else
					stringBuilder.append(" and category like '" + restaurant.getCategory() + "'");
			if(totalScore > 0)
				if(filterNumber == 0)
					stringBuilder.append("totalScore > " + totalScore );
				else
					stringBuilder.append(" and totalScore > " + totalScore);
			String filterQuery = stringBuilder.toString();
			System.out.println(filterQuery);
			preparedStatement = connect.prepareStatement(
					"Select * from address inner join restaurant on address.restaurantID = restaurant.restaurantID inner join rating"
					+ " on rating.restaurantid = address.restaurantid where " + filterQuery);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String restaurantId = resultSet.getString("restaurantid");
				String restaurantName = resultSet.getString("name");
				String category = resultSet.getString("category");
				String address = resultSet.getString("address");
				String state = resultSet.getString("state");
				String city = resultSet.getString("city");
				String zipCode = resultSet.getString("zipCode");
				Address addressObject = new Address(address, city, state, zipCode);
				restaurantInfo.add(new Restaurant(restaurantId, restaurantName, category, addressObject));
			}
			return restaurantInfo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
