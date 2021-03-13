package syzcogen.service;

import java.util.List;
import java.util.logging.Logger;

import syzcogen.model.Request;

public interface RequestManagementService {
	/** Initialize logger */
	public static final Logger log = Logger.getLogger(RequestManagementService.class.getName());
	
	public String getRequestStatusByEmpId(String e);
	public List<Request> getDetailsByReview(String id);
	public boolean addRequest(Request r);
	public boolean updateStatus(int id,String status,String rev);
	public List<Request> getReqList();
}
