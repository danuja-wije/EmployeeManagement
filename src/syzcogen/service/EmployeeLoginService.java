package syzcogen.service;

import java.util.logging.Logger;

import syzcogen.model.EmployeeLogin;

public interface EmployeeLoginService {

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(EmployeeLoginService.class.getName());

	String validateUser(EmployeeLogin e);

	boolean updateLastLog(String Id, String lg);
}
