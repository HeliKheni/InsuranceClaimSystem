package mvc.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbo.DbConnection;
import mvc.model.Claim;

public class ClaimService {
	// Add a claim for the specified product
	public boolean addClaim(String username, String serialNo, String dateOfClaim, String description) {
	    int userProductId = getUserProductId(username, serialNo); 
	    
	    try {
	        Connection connection = DbConnection.getConnection();
	        String sql = "INSERT INTO addclaim (userProductId, date_of_claim, descriptionOfIncident, Approval_status) VALUES (?, ?, ?, ?)";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setInt(1, userProductId);
	            statement.setDate(2, Date.valueOf(dateOfClaim));
	            statement.setString(3, description);
	            statement.setString(4, "Pending"); // Set default approval status to "Pending"
	            
	            int rowsInserted = statement.executeUpdate();
	            return rowsInserted > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	// Get the count of claims for a specific user and product
	public int getClaimCount(String username, String serialNo) {
	    int userProductId = getUserProductId(username, serialNo);
	    
	    try {
	        Connection connection = DbConnection.getConnection(); 
	        String sql = "SELECT COUNT(*) FROM addclaim WHERE userProductId = ?";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setInt(1, userProductId);
	            
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    return resultSet.getInt(1);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return 0;
	    }
		return 0;
	}

	// Update claim approval status
	public boolean updateClaimStatus(int claimId, String newStatus) {
	    try {
	        Connection connection = DbConnection.getConnection();
	        String sql = "UPDATE addclaim SET Approval_status = ? WHERE claimid = ?";
	        System.out.println("SQL Query: " + sql); // Print the SQL query for verification
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, newStatus);
	            statement.setInt(2, claimId);
	            
	            int rowsUpdated = statement.executeUpdate();
	            return rowsUpdated > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public int getUserProductId(String username, String serialNo) {
	    try {
	        Connection connection = DbConnection.getConnection();
	        String sql = "SELECT userProductId FROM userproduct WHERE username = ? AND product_serial_no = ?";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, username);
	            statement.setString(2, serialNo);

	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    return resultSet.getInt("userProductId");
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return 0; 
	}
	
	 // Method to retrieve claims for a specific user product
    public List<Claim> getClaimsForUserProduct(int userProductId) {
        List<Claim> claims = new ArrayList<>();

        String query = "SELECT * FROM addclaim WHERE userProductId = ?";

        try (Connection connection =DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
			{
	            preparedStatement.setInt(1, userProductId);
	
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    Claim claim = new Claim(
	                            resultSet.getInt("claimid"),
	                            resultSet.getInt("userProductId"),
	                            resultSet.getDate("date_of_claim"),
	                            resultSet.getString("descriptionOfIncident"),
	                            resultSet.getString("Approval_status")
	                    );
	
	                    claims.add(claim);
	                }
	            }
             }
         catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return claims;
    }
    
    

        public List<Claim> getClaimDetails() {
            List<Claim> claimDetailsList = new ArrayList<>();

            String query = "SELECT p.product_name, up.username, up.purchasedDate, ac.date_of_claim, ac.descriptionOfIncident, ac.Approval_status, ac.claimid " +
                    "FROM Product p " +
                    "JOIN UserProduct up ON p.product_serial_no = up.product_serial_no " +
                    "JOIN AddClaim ac ON up.userProductId = ac.userProductId";

            try (Connection connection = DbConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                	Claim claimDetails = new Claim(
                            resultSet.getString("product_name"),
                            resultSet.getString("username"),
                            resultSet.getDate("purchasedDate"),
                            resultSet.getDate("date_of_claim"),
                            resultSet.getString("descriptionOfIncident"),
                            resultSet.getString("Approval_status"),
                            resultSet.getInt("claimid")
                    );
                    claimDetailsList.add(claimDetails);
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
            return claimDetailsList;
        }      
    }