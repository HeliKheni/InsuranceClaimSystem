package mvc.controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.model.User;
import mvc.service.UserService;

import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/registerUserController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public RegisterController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 // Retrieve user input from the registration form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String cellphoneNo = request.getParameter("cellphoneNo");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        // Check if the username already exists
        UserService userService = new UserService();
        if (userService.isUsernameExists(username)) {
        	  // Username already exists, redirect to the registration page with an error message
            String errorMessage = "Username '" + username + "' already exists. Please choose a different username.";
            response.sendRedirect("registerUser.jsp?error=" + URLEncoder.encode(errorMessage, "UTF-8"));
       } else {
            // Username is unique, proceed with registration
            User user = new User(username, password, cellphoneNo, email, address);
            boolean registrationSuccess = userService.registerUser(user);

            if (registrationSuccess) {
                // Registration successful, redirect to the success page
                response.sendRedirect("RegestrationSucess.jsp");
            } else {
                // Registration failed, redirect to the error page
                response.sendRedirect("Error.jsp");
            }
        
		doGet(request, response);
	}
	}
}
