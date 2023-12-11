package mvc.controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.service.UserService;

import java.io.IOException;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L; 
  
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Retrieve user input from the login form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check if the username and password are valid 
        UserService userService = new UserService();
        if (username.equals("admin") && password.equals("admin")) {
            // Set the username attribute in the session for admin
            request.getSession().setAttribute("username", username);

            // Redirect admin to adminHome.jsp
            response.sendRedirect("adminHome.jsp");
        } else if (userService.isValidLogin(username, password)) {
            // Set the username attribute in the session for regular users
            request.getSession().setAttribute("username", username);

            // Valid login, redirect regular users to Home.jsp
            response.sendRedirect("Home.jsp");
        } else {
            // Invalid login, redirect to the login page with an error message
            response.sendRedirect("login.jsp?error=1");
        }
        
		doGet(request, response);
	}
}
