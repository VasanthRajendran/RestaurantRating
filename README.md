# RestaurantRating

API design Coding challenge

Instructions to run the Application:

1. Download the MySQL dump and import the sql files into the MySQL database.

2. mport the project into the eclipse IDE and deploy it on Tomcat Webserver. 

3. Change the MySQL database User Name/Password permissions in the class DBConnection.java in the package dao.

4. Install Postman Client to run the REST API’s 

5.	Now you are ready to run the API 

Follow the below instructions to Run all the 10 features of the API,

Feature 1: Create User

Method: POST

URI: http://localhost:8080/RestaurentRatingApp/rest/UserResource/addUser

Message Body:

{
	"firstName":"Vasanth",
	"lastName":"Rajendran",
	"phoneNo":"3124784876"
}

Feature 2: Update User

Method : PUT

URI : http://localhost:8080/RestaurentRatingApp/rest/UserResource/updateUser

Message Body:

{
	"uid":"9b6c9932-aa26-458e-b392-ab46a32a3e0f",   Fetch the uid from user table	"firstName":"Vasanth",
	"lastName":"R",
	"phoneNo":"3124784876"
}

Feature 3: Get all users

Method: GET

URI: http://localhost:8080/RestaurentRatingApp/rest/UserResource/getUsers

Feature 4: Get a Particular User

Method: GET

http://localhost:8080/RestaurentRatingApp/rest/UserResource/getUser/{userID}

Fetch the userID from the database

Feature 5: Create a Restaurant

Method : POST

URI : http://localhost:8080/RestaurentRatingApp/rest/RestaurantResource/addRestaurant

MessageBody:

{
	"name":"7eleven",
	"category":"american",
	"address":{
	"address":"chicagoTaylor",
	"state":"illinois",
	"city":"dallas",
	"zipCode":"60000"
	}
}


Feature 6: Update a Restaurant

Method : PUT

URI: http://localhost:8080/RestaurentRatingApp/rest/RestaurantResource/updateRestaurant/

{
	"name":"7eleven", 
	"category":"mexican",
	"address":{
	"address":"chicagoTaylor",
	"state":"illinois",
	"city":"dallas",
	"zipCode":"60000"
}

Feature 7: Retrieve a restaurant based on name, city, total Score

Method: GET

URI:http://localhost:8080/RestaurentRatingApp/rest/RestaurantResource/restaurants?name=subway&restaurentID=10&city=illinois


Feature 8: Add a rating

Method: POST 

URI: http://localhost:8080/RestaurentRatingApp/rest/RatingResource/addRating

Message Body:

{
	"cost":"4",
	"food":"4",
	"cleanliness":"3",
	"service":"4",
	"restaurant" : {
		"restaurantID":"10",
		"address":{
			"zipCode":"500"
		}
	},
	"user":{
		"uid":"1"
	},
	"date":"2017-10-10"
}

Feature 9: Update a rating

Method: PUT

URI : http://localhost:8080/RestaurentRatingApp/rest/RatingResource/updateRating

Message Body:

{
	"cost":"4",
	"food":"4",
	"cleanliness":"3",
	"service":"4",
	"restaurant" : {
		"restaurantID":"10",
		"address":{
			"zipCode":"600"
		}
	},
	"user":{
		"uid":"1"
	},
	"date":"2017-10-10"
}

Feature 10 : Get a or list of ratings by user

Method: GET

URI: http://localhost:8080/RestaurentRatingApp/rest/RatingResource/UserRating/{userid}

Feature 11: Get a rating by restaurant:

Method: GET

http://localhost:8080/RestaurentRatingApp/rest/RatingResource/getRating/{restaurentid}





