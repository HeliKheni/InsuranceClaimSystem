package mvc.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import mvc.service.ProductService;

@WebServlet("/adminDeleteProductController")
public class adminDeleteProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public adminDeleteProductController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productSerialNo = request.getParameter("productSerialNo");

        ProductService productService = new ProductService();
        boolean deletionSuccess = productService.deleteProductBySerialNumber(productSerialNo);

        if (deletionSuccess) {
            // Deletion was successful, redirect to admin page or another appropriate page
        	
            response.sendRedirect("adminAddUpdateDeleteProductController?success=Product details deleted successfully! ");
        } else {
            // Deletion failed, handle accordingly (e.g., show error message)
            //response.getWriter().println("Failed to delete product.");
            response.sendRedirect("adminAddUpdateDeleteProductController?error=Failed to delete product. This product already registered by user. ");
            
            // You might want to redirect or show an error message to the user
        }
    }
}