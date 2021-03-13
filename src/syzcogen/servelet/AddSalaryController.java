package syzcogen.servelet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import syzcogen.model.Employee;
import syzcogen.model.SalaryProfile;
import syzcogen.service.ProfileManagementService;
import syzcogen.service.ProfileManagementServiceImpli;

/**
 * Servlet implementation class AddSalaryController
 */
public class AddSalaryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProfileManagementService profileManagementService = null;
	PrintWriter writer;
	SalaryProfile salaryProfile = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddSalaryController() {
		profileManagementService = new ProfileManagementServiceImpli();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {

		case "ADD":
			addSalaryProfile(request, response);
			break;

		case "SEARCH":
			searchEmpployee(request, response);
			break;

		default:
			searchEmpployee(request, response);
			break;
		}

	}

	public void searchEmpployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Employee employee = null;
		String empNo = request.getParameter("empId").toUpperCase();

		if(profileManagementService.validateUser(empNo).equals("False")) {
			request.setAttribute("error", "Invalid Emp Id");
			RequestDispatcher dispatcher = request.getRequestDispatcher("ListSalaryProfileController");
			dispatcher.forward(request, response);
		}
		else if(profileManagementService.validateUser(empNo).equals("True")) {
			
			employee = profileManagementService.getEmployeeDetailsById(empNo);
			
			request.setAttribute("empId", empNo);
			request.setAttribute("employee", employee);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/adminView/addSalaryForm.jsp");
			dispatcher.forward(request, response);
		}
		else {
			request.setAttribute("error", "Cant add Profile at this movement");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ListSalaryProfileController");
			dispatcher.forward(request, response);
		}
		
	}

	public void addSalaryProfile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SalaryProfile s = new SalaryProfile();
		
		
		String createdBy = LoginController.getSession();
		String empId = request.getParameter("empId");
		float BasicSal = Float.parseFloat(request.getParameter("bSal"));
		float etf = Float.parseFloat(request.getParameter("etf"));
		float epf = Float.parseFloat(request.getParameter("epf"));
		float bonus = Float.parseFloat(request.getParameter("bonus"));
		String payDate = request.getParameter("payDate");
		String accNum = request.getParameter("accNum");
		String accName = request.getParameter("accName");
		String bank = request.getParameter("bank");
		float tax = Float.parseFloat(request.getParameter("tax"));
		float welfareFee = Float.parseFloat(request.getParameter("welfareFee"));
		String createDate = request.getParameter("createDate");
				
	
		s.setCreatedBy(createdBy);
		s.setEmpId(empId);
		s.setAccName(accName);
		s.setAccNum(accNum);
		s.setBank(bank);
		s.setBonus(bonus);
		s.setbSal(BasicSal);
		s.setCreateDate(createDate);
		s.setEpf(epf);
		s.setEtf(etf);
		s.setoThours(0);
		s.setPayDate(payDate);
		s.setTax(tax);
		s.setWelfareFee(welfareFee);
		s.setTotSalary(caltulateTotalSalary(BasicSal, epf, etf, bonus, tax, welfareFee));
		
		
		if(profileManagementService.addSalaryProfile(s)) {
			request.setAttribute("message1", "Salary Profile Successfully Created");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ListSalaryProfileController");
			dispatcher.forward(request, response);
			
		}else {
			request.setAttribute("message2", "Profile Creation Fail,Some Error Occured");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ListSalaryProfileController");
			dispatcher.forward(request, response);
		}
		
	}

	public float caltulateTotalSalary(float bsal, float epf, float etf, float bonus, float tax, float welfare) {
		float sal = (bsal + bonus) - (epf + etf + tax + welfare);

		return sal;

	}

}
