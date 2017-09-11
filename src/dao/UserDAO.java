package dao;

import dataObjects.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

	public Connection connect = null;
	public PreparedStatement preparedStatement = null;
	public ResultSet resultSet = null;
	DBConnection db = new DBConnection();

	public boolean isUserAlreadyExists(String phoneNo) {
		try {
			connect = db.connect();
			preparedStatement = connect.prepareStatement("select phoneNo from user where phoneNo = ?");
			preparedStatement.setString(1, phoneNo);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getString("phoneNo").equalsIgnoreCase(phoneNo))
					return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean createUser(User user) {
		try {
			connect = db.connect();
			preparedStatement = connect.prepareStatement("Insert into User values (?,?,?,?)");
			System.out.println(user.getUid());
			preparedStatement.setString(1, user.getUid());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getPhoneNo());
			preparedStatement.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateUser(User user) {
		try {
			connect = db.connect();
			preparedStatement = connect
					.prepareStatement("Update user set firstname = ?, lastname = ?, phoneNo = ? where userid = ?");
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getPhoneNo());
			preparedStatement.setString(4, user.getUid());
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<User> getALlUsers() {

		List<User> userList = new ArrayList<User>();
		try {
			connect = db.connect();
			preparedStatement = connect.prepareStatement("select * from user");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String uid = resultSet.getString("userid");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String phoneNo = resultSet.getString("phoneNo");
				userList.add(new User(uid, firstName, lastName, phoneNo));
			}
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

	public User getUserInfo(String uid) {
		try {
			connect = db.connect();
			preparedStatement = connect.prepareStatement("select * from user where userid = ?");
			preparedStatement.setString(1, uid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String userid = resultSet.getString("userid");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String phoneNo = resultSet.getString("phoneNo");
				User userInfo = new User(userid, firstName, lastName, phoneNo);
				return userInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
