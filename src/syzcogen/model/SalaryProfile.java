package syzcogen.model;

public class SalaryProfile {
	
	//declare variables
	private String createdBy;
	private String empId;
	private float bSal;
	private float epf;
	private float etf;
	private float oThours;
	private float bonus;
	private String payDate;
	private String accNum;
	private String bank;
	private String accName;
	private float tax;
	private float welfareFee;
	private float totSalary;
	private String createDate;
	

	public SalaryProfile() {
		// TODO Auto-generated constructor stub
	}
	
	public float getEpf() {
		return epf;
	}

	public void setEpf(float epf) {
		this.epf = epf;
	}

	public float getEtf() {
		return etf;
	}

	public void setEtf(float etf) {
		this.etf = etf;
	}

	public float getoThours() {
		return oThours;
	}

	public void setoThours(float oThours) {
		this.oThours = oThours;
	}

	public float getBonus() {
		return bonus;
	}

	public void setBonus(float bonus) {
		this.bonus = bonus;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getAccNum() {
		return accNum;
	}

	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public float getTax() {
		return tax;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	public float getWelfareFee() {
		return welfareFee;
	}

	public void setWelfareFee(float welfareFee) {
		this.welfareFee = welfareFee;
	}

	public float getTotSalary() {
		return totSalary;
	}

	public void setTotSalary(float totSalary) {
		this.totSalary = totSalary;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public float getbSal() {
		return bSal;
	}

	public void setbSal(float bSal) {
		this.bSal = bSal;
	}

	public String getCreatedBy() {
		return createdBy;
	}
	
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Override
	public String toString() {
		return "SalaryProfile [createdBy=" + createdBy + ", empId=" + empId + ", bSal=" + bSal + ", epf=" + epf
				+ ", etf=" + etf + ", oThours=" + oThours + ", bonus=" + bonus + ", payDate=" + payDate + ", accNum="
				+ accNum + ", bank=" + bank + ", accName=" + accName + ", tax=" + tax + ", welfareFee=" + welfareFee
				+ ", totSalary=" + totSalary + ", createDate=" + createDate + "]";
	}


	

	
}
