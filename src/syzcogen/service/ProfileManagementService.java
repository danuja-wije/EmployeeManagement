package syzcogen.service;

import java.util.List;
import java.util.logging.Logger;

import syzcogen.model.Employee;
import syzcogen.model.SalaryProfile;

public interface ProfileManagementService {

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(ProfileManagementService.class.getName());
	
	public List<SalaryProfile> getSalaryList();

	public Employee getEmployeeDetailsById(String Id);

	public boolean addSalaryProfile(SalaryProfile s);

	public SalaryProfile getSalaryDetailsById(String Id);

	public boolean updateSalaryProfile(SalaryProfile s);

	public boolean deleteSalaryProfile(String Id);
	
	public String validateUser(String id);

}
