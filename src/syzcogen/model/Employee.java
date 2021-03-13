package syzcogen.model;

public class Employee {
	
	//declare variables
	private String title;
	private String empId;
	private String fName;
	private String lName;
	private String address;
	private String email;
	
	
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	@Override
	public String toString() {
		return "Employee [title=" + title + ", empId=" + empId + ", fName=" + fName + ", lName=" + lName + ", address="
				+ address + ", email=" + email + "]";
	}
	

}
