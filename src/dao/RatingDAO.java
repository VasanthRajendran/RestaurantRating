package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dataObjects.Rating;
import dataObjects.Restaurant;
import dataObjects.RestaurantRating;
import dataObjects.User;
import dataObjects.UserRating;

public class RatingDAO {
	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	DBConnection db = new DBConnection();

	public boolean createRating(Rating rating) {
		try {
			connect = db.connect();
			Restaurant restaurant = rating.getRestaurant();
			User user = rating.getUser();
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = formatter1.parse(rating.getDate());
			java.sql.Date sqlDate = new Date(date.getTime());
			double totalScore = calculateTotalScore(rating.getCost(), rating.getFood(), rating.getCleanliness(),
					rating.getService());
			preparedStatement = connect.prepareStatement("Insert into rating values (?,?,?,?,?,?,?,?)");
			preparedStatement.setInt(1, rating.getCost());
			preparedStatement.setInt(2, rating.getFood());
			preparedStatement.setInt(3, rating.getCleanliness());
			preparedStatement.setInt(4, rating.getService());
			preparedStatement.setString(5, restaurant.getRestaurantID());
			preparedStatement.setString(6, user.getUid());
			preparedStatement.setDate(7, sqlDate);
			preparedStatement.setDouble(8, totalScore);
			preparedStatement.execute();
			preparedStatement = connect.prepareStatement("Insert into RestaurantRating values (?,?,?)");
			preparedStatement.setString(1, restaurant.getRestaurantID());
			preparedStatement.setDouble(2, totalScore);
			preparedStatement.setString(3, restaurant.getName());
			preparedStatement.execute();
			preparedStatement = connect.prepareStatement("Insert into userrating values (?,?,?)");
			preparedStatement.setString(1, restaurant.getRestaurantID());
			preparedStatement.setString(2, rating.getUser().getUid());
			preparedStatement.setString(3, restaurant.getAddress().getZipCode());
			preparedStatement.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @param rating
	 * @return
	 */
	public boolean updateRating(Rating rating) {
		try {
			connect = db.connect();
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = formatter1.parse(rating.getDate());
			java.sql.Date sqlDate = new Date(date.getTime());
			double totalScore = calculateTotalScore(rating.getCost(), rating.getFood(), rating.getCleanliness(),
					rating.getService());
			preparedStatement = connect.prepareStatement("Update rating set cost = ?, food =  ?, "
					+ "cleanliness =?, service = ?, ratingDate = ?, totalScore=? where restaurantid = ?");
			preparedStatement.setInt(1, rating.getCost());
			preparedStatement.setInt(2, rating.getFood());
			preparedStatement.setInt(3, rating.getCleanliness());
			preparedStatement.setInt(4, rating.getService());
			preparedStatement.setDate(5, sqlDate);
			preparedStatement.setDouble(6, totalScore);
			preparedStatement.setString(7, rating.getRestaurant().getRestaurantID());
			preparedStatement.executeUpdate();
			preparedStatement = connect.prepareStatement(
					"update restaurantrating set restaurantId =?, totalScore" + " = ?, restaurantname = ?");
			preparedStatement.setString(1, rating.getRestaurant().getRestaurantID());
			preparedStatement.setDouble(2, totalScore);
			preparedStatement.setString(3, rating.getRestaurant().getName());
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public double calculateTotalScore(int cost, int food, int cleanliness, int service) {
		double totalScore = (cost + food + cleanliness + service) / 4;
		System.out.println(totalScore);
		return totalScore;
	}

	public RestaurantRating getRating(String restaurantID) {

		try {
			connect = db.connect();
			preparedStatement = connect.prepareStatement(
					"Select restaurantName,AVG(totalScore) as total from restaurantRating where restaurantId = ? group by restaurantname");
			preparedStatement.setString(1, restaurantID);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				double totalScore = resultSet.getDouble("total");
				String name = resultSet.getString("restaurantName");
				System.out.println(name);
				RestaurantRating restaurantRating = new RestaurantRating(name, totalScore);
				return restaurantRating;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<UserRating> getUserRating(String userID) {

		try {
			List<UserRating> userRatingList = new ArrayList<UserRating>();
			connect = db.connect();
			preparedStatement = connect.prepareStatement(
					"Select * from rating inner join restaurant on restaurant.restaurantid = rating.restaurantid where userId = ?");
			preparedStatement.setString(1, userID);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int cost = resultSet.getInt("cost");
				int food = resultSet.getInt("food");
				int cleanliness = resultSet.getInt("cleanliness");
				int service = resultSet.getInt("Service");
				String restaurantName = resultSet.getString("Name");
				UserRating userRating = new UserRating(restaurantName, cost, food, cleanliness, service);
				userRatingList.add(userRating);
				return userRatingList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean isRatingAlreadyExists(Rating rating) {
		try {
			System.out.println(rating.getUser().getUid());
			System.out.println(rating.getRestaurant().getRestaurantID());
			connect = db.connect();
			preparedStatement = connect.prepareStatement("select * from userRating where userid = ? and restaurantid =?");
			preparedStatement.setString(1, rating.getUser().getUid());
			preparedStatement.setString(2, rating.getRestaurant().getRestaurantID());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getString("zipCode").equalsIgnoreCase(rating.getRestaurant().getAddress().getZipCode()))
					return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
