package mvc.service;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbo.DbConnection;
import mvc.model.Product;
import mvc.model.userProduct;

public class ProductService {
	   // Method to retrieve all products from the database
	    public List<Product> getAllProducts() {
	        List<Product> productList = new ArrayList<>();
	        try (Connection connection = DbConnection.getConnection()) {
	            String query = "SELECT product_name FROM product";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    while (resultSet.next()) {
	                        String productName = resultSet.getString("product_name");
	                        productList.add(new Product(productName));
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); 
	        }

	        return productList;
	    }
	    
	 // Method to get the serial number for a given product name
	    public String getSerialNumberForProduct(String productName) {
	    	 try (Connection connection = DbConnection.getConnection()) {
	        	 String sql = "SELECT product_serial_no FROM product WHERE product_name = ?";
	            PreparedStatement preparedStatement = connection.prepareStatement(sql);      
	            preparedStatement.setString(1, productName);

	            ResultSet rs = preparedStatement.executeQuery();
	            if (rs.next()) {
	                return rs.getString("product_serial_no");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); 
	        }
	        return null;
	    }
	    
	    // Method to get the serial number for a given product name
	    public Date getRegisteredDate(String username, String serialNo) {
	    	 try (Connection connection = DbConnection.getConnection()) {
	        	 String sql = "SELECT purchasedDate FROM userproduct WHERE product_serial_no = ? and username=?";
	            PreparedStatement preparedStatement = connection.prepareStatement(sql);      
	            preparedStatement.setString(1, serialNo);
	            preparedStatement.setString(2, username);

	            ResultSet rs = preparedStatement.executeQuery();
	            if (rs.next()) {
	                return rs.getDate("purchasedDate");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); 
	        }
	        return null;
	    }
	    
	    
	    
	   // Save the registered product in the database
	    public boolean registerProduct(String username, String serialNo, String purchaseDate) {
	        try (Connection connection = DbConnection.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(
	                     "INSERT INTO userproduct (username, product_serial_no , purchasedDate) VALUES (?, ?, ?)")) {
	            preparedStatement.setString(1, username);
	            preparedStatement.setString(2, serialNo);
	            preparedStatement.setString(3, purchaseDate);

	            int rowsAffected = preparedStatement.executeUpdate();
	            return rowsAffected > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
	    public List<userProduct> getUserProducts(String username) {
	        List<userProduct> userProducts = new ArrayList<>();

	        try (Connection connection = DbConnection.getConnection()) {
	            String query = "SELECT p.product_name, up.product_serial_no, up.purchasedDate " +
	                           "FROM userproduct up " +
	                           "JOIN product p ON up.product_serial_no = p.product_serial_no " +
	                           "WHERE up.username = ?";
	                           
	            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	                preparedStatement.setString(1, username);
	                
	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    while (resultSet.next()) {
	                        String productName = resultSet.getString("product_name");
	                        String serialNo = resultSet.getString("product_serial_no");
	                        String purchaseDate = resultSet.getString("purchasedDate");
	                        
	                        userProducts.add(new userProduct(username, productName, serialNo, purchaseDate));
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return userProducts;
	    }

	    // Check if the user has already registered a product with the given name
	    public boolean isProductAlreadyRegistered(String username, String serialNo) {
	        try (Connection connection = DbConnection.getConnection()) {
	            // Check if the product is already registered for the user
	            String query = "SELECT COUNT(*) FROM userproduct WHERE username = ? AND product_serial_no = ?";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	                preparedStatement.setString(1, username);
	                preparedStatement.setString(2, serialNo);

	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    if (resultSet.next()) {
	                        int count = resultSet.getInt(1);
	                        return count > 0;
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	    
	    public List<Product> getAllUserProductDetails() {
	        List<Product> userProductDetails = new ArrayList<>();

	        try (Connection connection = DbConnection.getConnection()) {
	            String query = "SELECT up.username, p.product_serial_no, p.product_name, p.model, p.price, p.manufacturer, p.description, p.color, up.purchasedDate " +
	                           "FROM UserProduct up " +
	                           "JOIN Product p ON up.product_serial_no = p.product_serial_no";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    while (resultSet.next()) {
	                        String username = resultSet.getString("username");
	                        String productSerialNo = resultSet.getString("product_serial_no");
	                        String productName = resultSet.getString("product_name");
	                        String model = resultSet.getString("model");
	                        double price = resultSet.getDouble("price");
	                        String manufacturer = resultSet.getString("manufacturer");
	                        String description = resultSet.getString("description");
	                        String color = resultSet.getString("color");
	                        Date purchasedDate = resultSet.getDate("purchasedDate");

	                        // Create Product object and add it to the list
	                        Product productDetail = new Product(productSerialNo, productName, model, price, manufacturer, description, color);
	                        productDetail.setUsername(username);
	                        productDetail.setPurchasedDate(purchasedDate);
	                        userProductDetails.add(productDetail);
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return userProductDetails;
	    }
	    
	    public List<Product> getProductsByName(String productName) {
	        List<Product> productList = new ArrayList<>();

	        try (Connection connection = DbConnection.getConnection()) {
	            String query = "SELECT product_serial_no, model, price, manufacturer, description, color, purchasedDate " +
	                    "FROM UserProduct up " +
	                    "JOIN Product p ON up.product_serial_no = p.product_serial_no " +
	                    "WHERE p.product_name = ?";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	                preparedStatement.setString(1, productName);

	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    while (resultSet.next()) {
	                        String productSerialNo = resultSet.getString("product_serial_no");
	                        String model = resultSet.getString("model");
	                        double price = resultSet.getDouble("price");
	                        String manufacturer = resultSet.getString("manufacturer");
	                        String description = resultSet.getString("description");
	                        String color = resultSet.getString("color");
	                        Date purchasedDate = resultSet.getDate("purchasedDate");

	                        // Create Product object and add it to the list
	                        Product product = new Product(productSerialNo, productName, model, price, manufacturer, description, color);
	                        product.setPurchasedDate(purchasedDate);
	                        productList.add(product);
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return productList;
	    }
	    
	    public boolean addProduct(String productSerialNo, String productName, String model, double price, String manufacturer,
                String description, String color) {
				try (Connection connection = DbConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(
				       "INSERT INTO product (product_serial_no, product_name, model, price, manufacturer, description, color) " +
				               "VALUES (?, ?, ?, ?, ?, ?, ?)")) {
				preparedStatement.setString(1, productSerialNo);
				preparedStatement.setString(2, productName);
				preparedStatement.setString(3, model);
				preparedStatement.setDouble(4, price);
				preparedStatement.setString(5, manufacturer);
				preparedStatement.setString(6, description);
				preparedStatement.setString(7, color);
				
				int rowsAffected = preparedStatement.executeUpdate();
				return rowsAffected > 0;
				} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	    	    
	    public List<Product> getAllProductDetails() {
	        List<Product> productList = new ArrayList<>();

	        try (Connection connection = DbConnection.getConnection()) {
	            String query = "SELECT * FROM Product";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    while (resultSet.next()) {
	                        String productSerialNo = resultSet.getString("product_serial_no");
	                        String productName = resultSet.getString("product_name");
	                        String model = resultSet.getString("model");
	                        double price = resultSet.getDouble("price");
	                        String manufacturer = resultSet.getString("manufacturer");
	                        String description = resultSet.getString("description");
	                        String color = resultSet.getString("color");

	                        // Create Product object and add it to the list
	                        Product product = new Product(productSerialNo, productName, model, price, manufacturer, description, color);
	                        productList.add(product);
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return productList;
	   }	    
	    
	    public boolean updateProduct(String productSerialNo, String productName, String model, String price, String manufacturer,
                String description, String color) {
			    try (Connection connection = DbConnection.getConnection();
		            PreparedStatement preparedStatement = connection.prepareStatement(
		                "UPDATE product SET product_name = ?, model = ?, price = ?, manufacturer = ?, description = ?, color = ? " +
		                "WHERE product_serial_no = ?")) {
		
			        // Set parameters in the prepared statement
			        preparedStatement.setString(1, productName);
			        preparedStatement.setString(2, model);
			        preparedStatement.setString(3, price);
			        preparedStatement.setString(4, manufacturer);
			        preparedStatement.setString(5, description);
			        preparedStatement.setString(6, color);
			        preparedStatement.setString(7, productSerialNo);
			
			        // Execute the update query
			        int rowsAffected = preparedStatement.executeUpdate();
			
					return rowsAffected > 0;
				} 
			    catch (SQLException e) {
			    	e.printStackTrace();
			    	return false;
			    }
	    }    
	    
	    public Product getProductBySerialNo(String productSerialNo) {
	        String query = "SELECT * FROM product WHERE product_serial_no = ?";
	        try (Connection connection = DbConnection.getConnection();
	                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setString(1, productSerialNo);

	            ResultSet resultSet = preparedStatement.executeQuery();
	            if (resultSet.next()) {
	                // Retrieve product details from the result set and create a Product object
	                Product product = new Product();
	                product.setProductSerialNo(resultSet.getString("product_serial_no"));
	                product.setProductName(resultSet.getString("product_name"));
	                product.setModel(resultSet.getString("model"));
	                product.setPrice(resultSet.getDouble("price"));
	                product.setManufacturer(resultSet.getString("manufacturer"));
	                product.setDescription(resultSet.getString("description"));
	                product.setColor(resultSet.getString("color"));

	                return product;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null; // Return null if the product with the given serial number is not found
	    }
	    
	    public boolean deleteProductBySerialNumber(String serialNumber) {
	        Connection conn = null;
	        PreparedStatement ps = null;

	        try {	           
	            conn = DbConnection.getConnection(); 
	            String deleteQuery = "DELETE FROM product WHERE product_serial_no = ?";
	            ps = conn.prepareStatement(deleteQuery);
	            ps.setString(1, serialNumber);
	            int rowsAffected = ps.executeUpdate();

	            return rowsAffected > 0; 
	        } catch (SQLException e) {
	            e.printStackTrace(); 
	            return false; 
	        } 
	    }	    
}