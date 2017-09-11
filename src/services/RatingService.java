package services;

import java.util.List;

import dao.RatingDAO;
import dataObjects.Rating;
import dataObjects.RestaurantRating;
import dataObjects.UserRating;

public class RatingService {
	RatingDAO ratingDao = new RatingDAO();

	public int createRating(Rating rating) {
		System.out.println(rating.getRestaurant().getAddress().getZipCode());
		boolean checkIfRatingExists = ratingDao.isRatingAlreadyExists(rating);
		if(checkIfRatingExists)
			return 5000;
		else if (ratingDao.createRating(rating)) {
			return 201;
		} else {
			return 9000;
		}
	}

	public int updateRating(Rating rating) {
		if (ratingDao.updateRating(rating))
			return 201;
		else
			return 9000;

	}

	public RestaurantRating getRating(String restaurantID) {

		return ratingDao.getRating(restaurantID);
	}

	public List<UserRating> getUserRating(String userID) {

		return ratingDao.getUserRating(userID);
	}

}
