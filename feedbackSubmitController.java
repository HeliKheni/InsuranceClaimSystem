package mvc.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.model.*;
import mvc.service.*;
import java.io.IOException;
import java.util.List;
import java.sql.Date;

@WebServlet("/feedbackSubmitController")
public class feedbackSubmitController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public feedbackSubmitController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		ProductService productService = new ProductService();
		 FeedbackService fs = new FeedbackService();
		 String username = (String) request.getSession().getAttribute("username");
		    String productName = request.getParameter("productName");
	        int rating = Integer.parseInt(request.getParameter("rating"));
	        String comments = request.getParameter("comment");
	        // Set the current date as java.sql.Date
            Date currentDate = new Date(System.currentTimeMillis());
            
	     // Fetch serial number for the selected product name
		    String productSerialNo = productService.getSerialNumberForProduct(productName);

	        Feedback feedback = new Feedback(username, productSerialNo,comments, rating, currentDate);
	        fs.addFeedback(feedback);
	        response.sendRedirect("feedbackController?success=Feedback sent successfully! ");
       
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
