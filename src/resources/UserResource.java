package resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import dataObjects.User;
import services.UserService;

@Path("/UserResource")
public class UserResource {

	UserService userService = new UserService();
	Gson gson = new Gson();
	
	@POST
	@Path("/addUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public String addUser(String userJson) {
		try {

			User user = gson.fromJson(userJson, User.class);
			int userAddStatus = userService.addNewUser(user);
			switch (userAddStatus) {
			case 201:
				return "User Successfully Created";
			case 2001:
				return "User already exists";
			case 5000:
				return "First Name is empty, please fill in the value";
			case 5001:
				return "Last Name is empty, please fill in the value";
			case 5002:
				return "Phone No is empty, please fill in the value";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Internal Server issues, Please try again later";
	}

	@PUT
	@Path("/updateUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateUser(String userJson)
	{
		try
		{
			User user = gson.fromJson(userJson, User.class);
			int userUpdateStatus = userService.updateUser(user);
			switch(userUpdateStatus)
			{
			case 201:
				return "User Information Successfully updated";
			case 5003:
				return "Updation cannot be performed due to server issues";
			}
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		return "Updation failed";
		}
	
	@GET
	@Path("/getUsers")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getAllUsers()
	{
		List<User> user = userService.getAllUsers();
		String userJson = gson.toJson(user);
		return userJson;
	}
	
	@GET
	@Path("/getUser/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getUserInfo(@PathParam("uid") String uid)
	{
		User userInfo = userService.getUserInfo(uid);
		return gson.toJson(userInfo);
	}
}
