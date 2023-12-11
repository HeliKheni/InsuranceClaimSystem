package mvc.controller;

import java.io.IOException;
import java.util.List;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import mvc.model.Product;
import mvc.service.ProductService;

@WebServlet("/adminAddUpdateDeleteProductController")
public class adminAddUpdateDeleteProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;
	
    public adminAddUpdateDeleteProductController() {
        super();
        productService = new ProductService(); // Initialize the ProductService
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get all added products and set them as an attribute to display in JSP       
        List<Product> allProductDetails = productService.getAllProductDetails();
     
        // Check for the sucess parameter
        String success = request.getParameter("success");
        if (success != null && !success.isEmpty()) {
            // If there is an error, set it as an attribute
            request.setAttribute("success", success);
        }
       
        // Check for the sucess parameter
        String error = request.getParameter("error");
        if (error != null && !error.isEmpty()) {
            // If there is an error, set it as an attribute
            request.setAttribute("error", error);
        }
        
        request.setAttribute("allProductDetails", allProductDetails);
        request.getRequestDispatcher("adminAddUpdateDeleteProduct.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productSerialNo = request.getParameter("productSerialNo");
	    String productName = request.getParameter("productName");
	    String model = request.getParameter("model");
	    String priceStr = request.getParameter("price");
	    String manufacturer = request.getParameter("manufacturer");
	    String description = request.getParameter("description");
	    String color = request.getParameter("color");

	    if (productSerialNo.isEmpty() || productName.isEmpty() || model.isEmpty() || priceStr.isEmpty() ||
	        manufacturer.isEmpty() || description.isEmpty() || color.isEmpty()) {
	        request.setAttribute("error", "All fields are required.");
	        doGet(request, response);
	        return;
	    }
	    double price;
	    try {
	        price = Double.parseDouble(priceStr);
	    } catch (NumberFormatException e) {
	        request.setAttribute("error", "Invalid price format. Please enter a valid number.");
	        doGet(request, response);
	        return;
	    }

	    if (!productSerialNo.matches("[a-zA-Z0-9]+")) {
	        request.setAttribute("error", "Product Serial No should be alphanumeric.");
	        doGet(request, response);
	        return;
	    }

	    if (!productName.matches("[a-zA-Z]+")) {
	        request.setAttribute("error", "Product Name should contain only letters.");
	        doGet(request, response);
	        return;
	    }
	    
	    if (!manufacturer.matches("[a-zA-Z]+")) {
	        request.setAttribute("error", "Manufacturer should contain only letters.");
	        doGet(request, response);
	        return;
	    }
	    
	    if (!color.matches("[a-zA-Z]+")) {
	        request.setAttribute("error", "Color should contain only letters.");
	        doGet(request, response);
	        return;
	    }
	    
        // Add the product details to the database
        productService.addProduct(productSerialNo, productName, model, price, manufacturer, description, color);     
        
        // Fetch products by name
        List<Product> products = productService.getProductsByName(productName);
        request.setAttribute("addedProducts", products);
        request.setAttribute("success", "Product is added successfully!!");
        
        doGet(request, response);
	}
}