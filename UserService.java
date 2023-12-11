package mvc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbo.DbConnection;
import mvc.model.User;

public class UserService {
	
	public boolean isUsernameExists(String username) {
        try (Connection connection = DbConnection.getConnection()) {
            String sql = "SELECT COUNT(*) FROM user WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
	
	 public boolean registerUser(User user) {
	        try (Connection connection = DbConnection.getConnection()) {
	            String sql = "INSERT INTO user (username, password, phoneNo, email, address) VALUES (?, ?, ?, ?, ?)";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	                preparedStatement.setString(1, user.getUsername());
	                preparedStatement.setString(2, user.getPassword());
	                preparedStatement.setString(3, user.getCellphoneNo());
	                preparedStatement.setString(4, user.getEmail());
	                preparedStatement.setString(5, user.getAddress());

	                int rowsAffected = preparedStatement.executeUpdate();

	                // Check if the user registration was successful
	                return rowsAffected > 0;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Log the exception or handle it appropriately
	            return false;
	        }
	    }
	 
	 public boolean isValidLogin(String username, String password) {
	        try (Connection connection = DbConnection.getConnection()) {
	            String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	                preparedStatement.setString(1, username);
	                preparedStatement.setString(2, password);
	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    return resultSet.next(); // Returns true if a matching user is found
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	 
	 public List<User> getAllUsers() {
		    List<User> userList = new ArrayList<>();
		    try (Connection connection = DbConnection.getConnection()) {
		        String sql = "SELECT * FROM user";
		        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
		            try (ResultSet resultSet = preparedStatement.executeQuery()) {
		                while (resultSet.next()) {
		                    User user = new User();
		                    user.setUsername(resultSet.getString("username"));
		                    user.setPassword(resultSet.getString("password"));
		                    user.setCellphoneNo(resultSet.getString("phoneNo"));
		                    user.setEmail(resultSet.getString("email"));
		                    user.setAddress(resultSet.getString("address"));
		                    userList.add(user);
		                }
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return userList;
		}
	 
}
