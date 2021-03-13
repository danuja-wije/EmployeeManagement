package syzcogen.model;

public class Request {
	
	//declare variables
	private String empId;
	private int reqId;
	private String date;
	private String purpose;
	private String status;
	private String reviewedBy;
	
	
	public Request() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Request [empId=" + empId + ", reqId=" + reqId + ", date=" + date + ", purpose=" + purpose + ", status="
				+ status + ", reviewedBy=" + reviewedBy + "]";
	}
	
	
	public String getReviewedBy() {
		return reviewedBy;
	}


	public void setReviewedBy(String reviewedBy) {
		this.reviewedBy = reviewedBy;
	}


	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public int getReqId() {
		return reqId;
	}
	public void setReqId(int reqId) {
		this.reqId = reqId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
