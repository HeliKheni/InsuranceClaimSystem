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


@WebServlet("/feedbackController")
public class feedbackController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public feedbackController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		 FeedbackService fs = new FeedbackService();
		 ProductService productService = new ProductService();
		 String username = (String) request.getSession().getAttribute("username");
	       
        List<Feedback> feedbackList = fs.getFeedbackByUsername(username);
        request.setAttribute("feedbackList", feedbackList);
        
        // Fetch user's registered products
        List<userProduct> userProducts = productService.getUserProducts(username);
        request.setAttribute("userProducts", userProducts);
        
     // Check for the sucess parameter
        String success = request.getParameter("success");
        if (success != null && !success.isEmpty()) {
            // If there is an error, set it as an attribute
            request.setAttribute("success", success);
        }
        
        RequestDispatcher dispatcher =  request.getRequestDispatcher("AddFeedback.jsp");
        dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
