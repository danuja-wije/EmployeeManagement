package syzcogen.servelet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import syzcogen.model.Request;
import syzcogen.service.RequestManagementService;
import syzcogen.service.RequestManagementServiceImpli;

/**
 * Servlet implementation class RequestController
 */
public class RequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestManagementService requestManagement = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestController() {
       requestManagement = new RequestManagementServiceImpli();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		switch (action) {
		case "APPROVE": 
			reqStatus(request, response);
			break;
		case "REJECT":
			reqStatus(request, response);
			break;
		case "SUMMARY":
			displaySummary(request, response);
			break;
		default:
			RequestDispatcher dispatcher = request.getRequestDispatcher("ListSalaryProfileController");
			dispatcher.forward(request, response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "STATEMENT":
				reqstatement(request, response);
			break;
		
		default:
			RequestDispatcher dispatcher = request.getRequestDispatcher("DisplayUserProfileController");
			dispatcher.forward(request, response);
			break;
		}
		
		

	}
	public void reqstatement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Request req = new Request();
		String date = request.getParameter("date");
		String purpose = request.getParameter("purpose");
		String status = "pending";
		String empId = LoginController.getSession();
		String reviewedBy = "Not Yet";
		req.setEmpId(empId);
		req.setDate(date);
		req.setPurpose(purpose);
		req.setStatus(status);
		req.setReviewedBy(reviewedBy);
		
		
		if(requestManagement.addRequest(req)) {
			String pending = requestManagement.getRequestStatusByEmpId(empId);
			request.setAttribute("status", pending);
			request.setAttribute("message1", "Request Submit Successfully");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/userView/displaySalaryProfile.jsp");
			dispatcher.forward(request, response);
		}else {request.setAttribute("message2", "unable to submit Request");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/userView/displaySalaryProfile.jsp");
		dispatcher.forward(request, response);
		}
	}
	public void reqStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reqId = Integer.parseInt(request.getParameter("reqId")) ;
		String action = request.getParameter("action");
		String reviewedBy = "";
		String status = "";
		if(action.equals("APPROVE")) {
		status = "approved";
		reviewedBy = LoginController.getSession();
		}else if(action.equals("REJECT")) {
			status = "reject";
			reviewedBy = LoginController.getSession();
		}
		
		if(requestManagement.updateStatus(reqId, status,reviewedBy)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("ListSalaryProfileController");
			dispatcher.forward(request, response);
		}
		
		
	}
	public void displaySummary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Request>reqlist = requestManagement.getDetailsByReview(LoginController.getSession());
		
		request.setAttribute("reqList", reqlist);
		if(reqlist.isEmpty()) {
			
			request.setAttribute("message", "summary not available");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/adminView/summary.jsp");
		dispatcher.forward(request, response);
		
	}

}
