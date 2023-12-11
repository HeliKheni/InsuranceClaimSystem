package mvc.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.service.ClaimService;

@WebServlet("/adminClaimStatusSubmitController")
public class adminClaimStatusSubmitController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public adminClaimStatusSubmitController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
        int claimId = Integer.parseInt(request.getParameter("claimId"));

        if (action != null && !action.isEmpty()) {
            String newStatus = "";

            // Determine new status based on action
            if (action.equals("approve")) {
                newStatus = "approved";
            } else if (action.equals("reject")) {
                newStatus = "rejected";
            }

            ClaimService claimService = new ClaimService();
            boolean statusUpdated = claimService.updateClaimStatus(claimId, newStatus);

            if (statusUpdated) {              
                request.getRequestDispatcher("adminClaimsListController").forward(request, response);
            } else {
                // Log error for debugging
                System.out.println("Failed to update status for claim ID: " + claimId);
                response.getWriter().println("Failed to update status");
            }
        } else {
            // Handle missing action parameter
            response.getWriter().println("Action parameter missing");
        }
    }
}