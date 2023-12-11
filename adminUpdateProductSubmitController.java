package mvc.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import mvc.service.ProductService;

@WebServlet("/adminUpdateProductSubmitController")
public class adminUpdateProductSubmitController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;
	
    public adminUpdateProductSubmitController() {
        super();
        productService = new ProductService(); // Initialize the ProductService
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String productSerialNo = request.getParameter("productSerialNo");
	    String productName = request.getParameter("productName");
	    String model = request.getParameter("model");
	    String price = request.getParameter("price"); 
	    String manufacturer = request.getParameter("manufacturer");
	    String description = request.getParameter("description");
	    String color = request.getParameter("color");

	    // Call the ProductService's updateProduct method
	    productService.updateProduct(productSerialNo, productName, model, price, manufacturer, description, color);
	  	    
	        // If product details are updated successfully, set a success message
	       // request.setAttribute("success", "Product details updated successfully!");
	       // request.setAttribute("editProductDetails", "");
	   	
	    // Forward the request back to the JSP for display
	   //request.getRequestDispatcher("adminHome.jsp").forward(request, response);
	   // request.getRequestDispatcher("adminAddUpdateDeleteProduct.jsp").forward(request, response);
	  response.sendRedirect("adminAddUpdateDeleteProductController?success=Product details updated successfully! ");
	        
	}
}