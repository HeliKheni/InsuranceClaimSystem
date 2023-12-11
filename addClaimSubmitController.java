package mvc.controller;

import mvc.service.ClaimService;
import mvc.service.ProductService;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@WebServlet("/addClaimSubmitController")
public class addClaimSubmitController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public addClaimSubmitController() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Retrieve user input from the registration form
	    String username = (String) request.getSession().getAttribute("username");
	    String productName = request.getParameter("productName");
	    String dateofClaim = request.getParameter("dateOfClaim");
	    String description = request.getParameter("description");

	    ProductService productService = new ProductService();
	    // Fetch serial number for the selected product name
	    String serialNumber = productService.getSerialNumberForProduct(productName);

	    ClaimService claimService = new ClaimService();
	    // Fetch registered date of the product
       Date registeredDate = productService.getRegisteredDate(username, serialNumber);

        // Check if the claim date is within 5 years from the registered date
	       Date claimDate = null;
	       try {
	           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	           claimDate = new Date(sdf.parse(dateofClaim).getTime());
	       } catch (ParseException e) {
	            e.printStackTrace();
	       }

        long timeDifference = claimDate.getTime() - registeredDate.getTime();
        long yearsDifference = TimeUnit.MILLISECONDS.toDays(timeDifference) / 365;

        if (yearsDifference > 5) {
            // If the claim date is outside the allowed range, redirect to the claim page with an error message
            response.sendRedirect("addClaimController?error=Your 5 years of protection plan is over for this product "+ productName +".");
        }
	 // Check if the user has already reached the maximum claims for the product
        else if (claimService.getClaimCount(username, serialNumber) >= 3) {
	        response.sendRedirect("addClaimController?error=You have reached the maximum number of claims for this product "+ productName+ " .");
	    } else {
	        // Add a claim for the specified product
	        boolean claimAdded = claimService.addClaim(username, serialNumber, dateofClaim, description);

	        if (claimAdded) {
	            // Claim submission successful, redirect to a success page
	            response.sendRedirect("addClaimController?sucess=Claim submitted successfully!");
	        } else {
	            // Claim submission failed, redirect to the claimStatusController with an error parameter
	            response.sendRedirect("addClaimController?error=Claim submission failed. Please try again.");
	        }
	    }

		doGet(request, response);
	}
}