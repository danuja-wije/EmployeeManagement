package syzcogen.model;

public class EmployeeLogin {
	
	
	//declare variables
	private String username;
	private String password;
	private String lastLog;
	
	public EmployeeLogin() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLastLog() {
		return lastLog;
	}
	public void setLastLog(String lastLog) {
		this.lastLog = lastLog;
	}
	@Override
	public String toString() {
		return "EmployeeLogin [username=" + username + ", password=" + password + ", lastLog=" + lastLog + "]";
	}
	
	
	
}
