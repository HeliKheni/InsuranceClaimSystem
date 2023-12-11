package mvc.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbo.DbConnection;
import mvc.model.Feedback;

public class FeedbackService {
	
	 public void addFeedback(Feedback feedback) {
	        try (Connection connection = DbConnection.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(
	                     "INSERT INTO feedback (username, product_serial_no, rating, comment, submissionDate) VALUES (?, ?, ?, ?, ?)")) {
	            preparedStatement.setString(1, feedback.getUsername());
	            preparedStatement.setString(2, feedback.getProduct_serial_no());
	            preparedStatement.setInt(3, feedback.getRating());
	            preparedStatement.setString(4, feedback.getComment());
	           preparedStatement.setDate(5, feedback.getSubmissionDate());

	            preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace(); 
	        }
	    }

	    
	    public List<Feedback> getAllFeedback() {
	        List<Feedback> feedbackList = new ArrayList<>();

	        try (Connection connection = DbConnection.getConnection();
	                PreparedStatement preparedStatement = connection.prepareStatement(
	                        "SELECT f.username, p.product_name, f.rating, f.comment, f.submissionDate " +
	                        "FROM feedback f " +
	                        "JOIN product p ON f.product_serial_no = p.product_serial_no");
	                ResultSet resultSet = preparedStatement.executeQuery()) {

	               while (resultSet.next()) {
	                   Feedback feedback = createFeedbackFromResultSet(resultSet);
	                   feedbackList.add(feedback);
	               }
	        } catch (SQLException e) {
	            e.printStackTrace(); 
	        }
	        return feedbackList;
	    }

	   
	    public List<Feedback> getFeedbackByUsername(String username) {
	        List<Feedback> feedbackList = new ArrayList<>();

	        try (Connection connection = DbConnection.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(
	            		 "SELECT f.username, p.product_name, f.rating, f.comment, f.submissionDate " +
	 	                        "FROM feedback f " +
	 	                        "JOIN product p ON f.product_serial_no = p.product_serial_no"
	 	                        + " WHERE username = ?");)
	        {
	            preparedStatement.setString(1, username);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    Feedback feedback = createFeedbackFromResultSet(resultSet);
	                    feedbackList.add(feedback);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); 
	        }
	        return feedbackList;
	    }


	    private Feedback createFeedbackFromResultSet(ResultSet resultSet) throws SQLException {
	        Feedback feedback = new Feedback();
	        feedback.setUsername(resultSet.getString("username"));
	        feedback.setProduct_serial_no(resultSet.getString("product_name"));
	        feedback.setRating(resultSet.getInt("rating"));
	        feedback.setComment(resultSet.getString("comment"));
	        feedback.setSubmissionDate(resultSet.getDate("submissionDate"));
	        return feedback;
	    }
}
