package mvc.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.model.Feedback;
import mvc.model.userProduct;
import mvc.service.FeedbackService;
import mvc.service.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet("/adminFeedbackController")
public class adminFeedbackController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public adminFeedbackController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		 FeedbackService fs = new FeedbackService();
		 ProductService productService = new ProductService();
		 String username = (String) request.getSession().getAttribute("username");
	       
        List<Feedback> feedbackList = fs.getAllFeedback();
        request.setAttribute("feedbackList", feedbackList);
        
        RequestDispatcher dispatcher =  request.getRequestDispatcher("adminFeedback.jsp");
        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
