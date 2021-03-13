package syzcogen.servelet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import syzcogen.model.EmployeeLogin;
import syzcogen.service.EmployeeLoginService;
import syzcogen.service.EmployeeLoginServiceImpl;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     EmployeeLoginService employeeLoginService = null;  
     PrintWriter writer;
     static HttpSession session = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
       employeeLoginService = new EmployeeLoginServiceImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		switch (action) {
		case "LIST":
			response.sendRedirect("ListSalaryProfileController");	
			break;
		
		default:
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
			break;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session  = request.getSession();
		EmployeeLogin log = new EmployeeLogin();
		
		String username = request.getParameter("username").toUpperCase();
		String password = request.getParameter("password");
		
		log.setUsername(username);
		log.setPassword(password);
		
		DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		   LocalDateTime now = LocalDateTime.now();
		   
		String lastLog = date.format(now);
		
		log.setLastLog(lastLog);
		
		String result = employeeLoginService.validateUser(log);
		
		String loginMessage = null;
		if(result.equals("True")) { 
			if(employeeLoginService.updateLastLog(username, lastLog)) {
				session.setAttribute("s_username", log.getUsername());
				
				request.setAttribute("userType", username.trim().charAt(0));
				RequestDispatcher dispatcher = request.getRequestDispatcher("DisplayUserProfileController");
				dispatcher.forward(request, response);
				
			}
			
		}
		else {
			if(result.equals("False")) loginMessage ="Invalid User Credintial";
			else loginMessage ="Some Error Occured";
			
			request.setAttribute("loginMessage", loginMessage);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
		
	}
	public static String getSession() {
		return  (String)session.getAttribute("s_username");
	}

}
