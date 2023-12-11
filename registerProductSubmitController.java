package mvc.controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.service.ProductService;

import java.io.IOException;

@WebServlet("/registerProductSubmitController")
public class registerProductSubmitController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public registerProductSubmitController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    // Retrieve user input from the registration form
	    String username = (String) request.getSession().getAttribute("username");
	    String productName = request.getParameter("productName");
	    String purchaseDate = request.getParameter("purchaseDate");

	    ProductService productService = new ProductService();
	    
	 // Fetch serial number for the selected product name
	    String serialNumber = productService.getSerialNumberForProduct(productName);

	    
	    // Check if the product is already registered by the user
	    if (productService.isProductAlreadyRegistered(username, serialNumber)) {
	        // Product is already registered, redirect with an error message
	        response.sendRedirect("registerProductController?error=This Product "+productName +" is already registered by the user.");
	        return; // Stop further processing
	    }

	    
	    // Create a Product object with the retrieved data
	    boolean registrationSuccess = productService.registerProduct(username, serialNumber, purchaseDate);

	    if (registrationSuccess) {
	        // Registration successful, redirect to a success page
	        response.sendRedirect("registerProductController?success=Registration successful!!");
	    } else {
	        // Registration failed, redirect to the registerProductController with an error parameter
	        response.sendRedirect("registerProductController?error=Registration failed. Please try again.");
	    }
	}


}
