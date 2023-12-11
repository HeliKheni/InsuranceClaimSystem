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

@WebServlet("/adminRegisteredProductListController")
public class adminRegisteredProductListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService;

    public adminRegisteredProductListController() {
        super();
        productService = new ProductService(); // Initialize the ProductService
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	List<Product> userProductDetails = productService.getAllUserProductDetails();
        request.setAttribute("userProductDetails", userProductDetails);
        request.getRequestDispatcher("adminRegisteredProductList.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}