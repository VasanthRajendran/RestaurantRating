package services;

import java.util.List;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import dao.RestaurantDAO;
import dataObjects.Restaurant;

public class RestaurantService {

	RestaurantDAO restaurantDAO = new RestaurantDAO();

	public int addRestaurant(Restaurant restaurant) {

		int missingData = checkForInvalidData(restaurant);
		System.out.println(missingData);
		UUID restaurantID = UUID.randomUUID();
		restaurant.setRestaurantID(restaurantID.toString());
		if (missingData == 0) {
			if(restaurantDAO.isRestaurantAlreadyExists(restaurant.getName(), restaurant.getAddress().getZipCode()))
				return 2001;
			else if (restaurantDAO.createRestaurant(restaurant)) {
				return 201;
			}
			else
			{
				return 5003;
			}
		}
		else
		{
			return missingData;
		}
	}

	private int checkForInvalidData(Restaurant restaurant) {
		System.out.println("Cccc");
		System.out.println(restaurant.getName());
		System.out.println(restaurant.getCategory());
		if (StringUtils.isBlank(restaurant.getName()))
			return 5000;
		else if (StringUtils.isBlank(restaurant.getCategory()))
			return 5001;
		else
			return 0;
	}

	public int updateRestaurant(Restaurant restaurant) {
		RestaurantDAO restaurantDao = new RestaurantDAO();
		int missingField = checkForInvalidData(restaurant);
		if (missingField == 0) {
			if (restaurantDao.updateRestaurant(restaurant))
				return 201;
			else {
				return 5003;
			}
		} else {
			return missingField;
		}
	}

	public List<Restaurant> getAllRestaurants() {
		return restaurantDAO.getAllRestaurants();
	}

	public List<Restaurant> getRestaurant(Restaurant restaurant, int totalScore) {
		return restaurantDAO.getRestaurant(restaurant,totalScore);
	}

}
