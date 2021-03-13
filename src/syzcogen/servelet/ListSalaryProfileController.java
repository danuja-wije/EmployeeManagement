package syzcogen.servelet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import syzcogen.model.Request;
import syzcogen.model.SalaryProfile;
import syzcogen.service.ProfileManagementService;
import syzcogen.service.ProfileManagementServiceImpli;
import syzcogen.service.RequestManagementService;
import syzcogen.service.RequestManagementServiceImpli;

/**
 * Servlet implementation class ListSalaryProfileController
 */
public class ListSalaryProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProfileManagementService profileManagementService = null;
	RequestManagementService requestManagement = null;
	     PrintWriter writer;
  /**
   * @see HttpServlet#HttpServlet()
   */
  public ListSalaryProfileController() {
	  profileManagementService = new ProfileManagementServiceImpli();
	  requestManagement = new RequestManagementServiceImpli();
  }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Request>req = requestManagement.getReqList();
		List<SalaryProfile> sal =  profileManagementService.getSalaryList();
		request.setAttribute("req", req);
		request.setAttribute("sal", sal);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/adminView/salaryProfilelist.jsp");
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
