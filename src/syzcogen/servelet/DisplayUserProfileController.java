package syzcogen.servelet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import syzcogen.model.Employee;
import syzcogen.model.SalaryProfile;
import syzcogen.service.ProfileManagementService;
import syzcogen.service.ProfileManagementServiceImpli;
import syzcogen.service.RequestManagementService;
import syzcogen.service.RequestManagementServiceImpli;

/**
 * Servlet implementation class DisplayUserProfileController
 */
public class DisplayUserProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session; 
	ProfileManagementService profileManagementService = null;
	RequestManagementService requestManagement = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayUserProfileController() {
    	 profileManagementService = new ProfileManagementServiceImpli();
    	 requestManagement = new RequestManagementServiceImpli();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String empId = LoginController.getSession();
		SalaryProfile profile = profileManagementService.getSalaryDetailsById(empId);
		Employee employee = profileManagementService.getEmployeeDetailsById(empId);
		String status = requestManagement.getRequestStatusByEmpId(empId);
		request.setAttribute("status", status);
		request.setAttribute("profile", profile);
		request.setAttribute("employee", employee);
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/userView/displaySalaryProfile.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
