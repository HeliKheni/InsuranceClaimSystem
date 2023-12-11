package mvc.controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.model.Product;
import mvc.model.userProduct;
import mvc.service.ProductService;

import java.io.IOException;
import java.util.List;


@WebServlet("/registerProductController")
public class registerProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public registerProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 // Fetch product list from the database using ProductService
        ProductService productService = new ProductService();
        List<Product> productList = productService.getAllProducts();

        // Set the product list in the request attribute
        request.setAttribute("productList", productList);

        // Fetch user's registered products
        String username = (String) request.getSession().getAttribute("username");
        List<userProduct> userProducts = productService.getUserProducts(username);
        request.setAttribute("userProducts", userProducts);
        
        // Check for the error parameter
        String error = request.getParameter("error");
        if (error != null && !error.isEmpty()) {
            // If there is an error, set it as an attribute
            request.setAttribute("error", error);
        }
        
     // Check for the sucess parameter
        String sucess = request.getParameter("sucess");
        if (sucess != null && !sucess.isEmpty()) {
            // If there is an error, set it as an attribute
            request.setAttribute("sucess", sucess);
        }
        
        // Forward the request to the registerProduct.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("registerProduct.jsp");
        dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
