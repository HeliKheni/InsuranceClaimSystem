package mvc.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import mvc.model.Product;
import mvc.service.ProductService;

@WebServlet("/adminUpdateProductController")
public class adminUpdateProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;
	
    public adminUpdateProductController() {
        super();
        productService = new ProductService(); // Initialize the ProductService
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
        String productSerialNo = request.getParameter("productSerialNo");
        Product productDetails = productService.getProductBySerialNo(productSerialNo);
        request.setAttribute("editProductDetails", productDetails);
        request.getRequestDispatcher("adminAddUpdateDeleteProduct.jsp").forward(request, response);     
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    
	    doGet(request, response);
	}
}