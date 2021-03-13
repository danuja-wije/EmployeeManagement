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
 * Servlet implementation class UpdateSalaryController
 */
public class UpdateSalaryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProfileManagementService profileManagementService = null; 
    PrintWriter writer;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateSalaryController() {
		profileManagementService = new ProfileManagementServiceImpli();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		switch (action) {
		case "EDIT": editSalary(request, response);
			break;
		case "DELETE" : deleteSalaryProfile(request, response);;
			break;
		default: RequestDispatcher dispatcher = request.getRequestDispatcher("ListSalaryProfileController");
				 dispatcher.forward(request, response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("btn");
		
		switch (action) {
		case "Save Details": saveSalaryProfile(request, response);
			break;
		case "Delete Profile" : deleteSalaryProfile(request, response);
			break;
		default:request.setAttribute("message", "Error Occured");
						RequestDispatcher dispatcher = request.getRequestDispatcher("ListSalaryProfileController");
						dispatcher.forward(request, response);
			break;
		}
		
		
		
	}

	public void editSalary(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String empId = request.getParameter("empId");
		Employee employee = profileManagementService.getEmployeeDetailsById(empId);
		SalaryProfile salaryProfile =profileManagementService.getSalaryDetailsById(empId);
		request.setAttribute("employee", employee);
		request.setAttribute("profile", salaryProfile);
		request.setAttribute("empId", empId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/adminView/updateSalaryForm.jsp");
		dispatcher.forward(request, response);
		
	}

	public void deleteSalaryProfile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String empId = request.getParameter("empId");
		
		if(profileManagementService.deleteSalaryProfile(empId)) {
			request.setAttribute("message1", "Salary Profile Deleted Emp Id = "+empId);
			RequestDispatcher dispatcher = request.getRequestDispatcher("ListSalaryProfileController");
			dispatcher.forward(request, response);
		}else {
			request.setAttribute("message2", "Cant Delete Salary Profile Emp Id = "+empId);
			RequestDispatcher dispatcher = request.getRequestDispatcher("ListSalaryProfileController");
			dispatcher.forward(request, response);
			
		}
		
		
	}
	public void saveSalaryProfile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SalaryProfile save = new SalaryProfile();

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
		float otHours = Float.parseFloat(request.getParameter("otHours"));
		
	
		save.setoThours(otHours);
		save.setEmpId(empId);
		save.setAccName(accName);
		save.setAccNum(accNum);
		save.setBank(bank);
		save.setBonus(bonus);
		save.setbSal(BasicSal);
		save.setCreateDate(createDate);
		save.setEpf(epf);
		save.setEtf(etf);
		save.setoThours(otHours);
		save.setPayDate(payDate);
		save.setTax(tax);
		save.setWelfareFee(welfareFee);
		save.setTotSalary(caltulateTotalSalary(BasicSal, epf, etf, bonus, tax, welfareFee,otHours));
		
		if(profileManagementService.updateSalaryProfile(save)) {
			request.setAttribute("message1", "Salary Profile Successfully Updated Emp Id = "+save.getEmpId());
			RequestDispatcher dispatcher = request.getRequestDispatcher("ListSalaryProfileController");
			dispatcher.forward(request, response);
		}else {
			request.setAttribute("message2", "Salary Profile Updated Failed. Emp Id = "+save.getEmpId());
			RequestDispatcher dispatcher = request.getRequestDispatcher("ListSalaryProfileController");
			dispatcher.forward(request, response);	
		}
		
	}
	public float caltulateTotalSalary(float bsal, float epf, float etf, float bonus, float tax, float welfare,float ot) {
		float sal = (bsal + bonus + (ot * 250)) - (epf + etf + tax + welfare);

		return sal;

	}
	

}
