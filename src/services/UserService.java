package services;

import dao.UserDAO;
import dataObjects.User;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

public class UserService {
	
	UserDAO userDao = new UserDAO();
	
	public int addNewUser(User user) {
		UserDAO userDAO = new UserDAO();
		int missingField = checkForInvalidData(user);
		if (missingField == 0) {
			if (userDAO.isUserAlreadyExists(user.getPhoneNo())) {
				return 2001;
			} else {
				UUID idOne = UUID.randomUUID();
				user.setUid(idOne.toString());
				if (userDAO.createUser(user)) {
					return 201;
				} else {
					return 9000;
				}
			}
		} else {
			return missingField;
		}
	}

	public int checkForInvalidData(User user) {
		if (StringUtils.isBlank(user.getFirstName()))
		{
			return 5000;
		}
		else if (StringUtils.isBlank(user.getLastName()))
		{
			return 5001;
		}
		else if (StringUtils.isBlank(user.getPhoneNo()))
		{
			return 5002;
		}
		else{
			return 0;
		}
	}
	
	public int updateUser(User user)
	{
		int missingField = checkForInvalidData(user);
		if(missingField == 0)
		{
			if(userDao.updateUser(user))
				return 201;
			else
			{
				return 5003;
			}	
		}
		else
		{
			return missingField;
		}
	}
	
	public List<User> getAllUsers()
	{
		return userDao.getALlUsers();
	}

	public User getUserInfo(String uid) {
		return userDao.getUserInfo(uid);
	}
}
