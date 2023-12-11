package mvc.controller;

import java.io.IOException;
import java.util.List;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import mvc.model.Claim;
import mvc.service.ClaimService;

@WebServlet("/adminClaimsListController")
public class adminClaimsListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public adminClaimsListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClaimService ClaimService = new ClaimService();
	    List<Claim> claimDetails = ClaimService.getClaimDetails();
	    request.setAttribute("claimDetails", claimDetails);
	    request.getRequestDispatcher("adminClaimsList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
}