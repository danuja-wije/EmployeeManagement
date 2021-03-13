package syzcogen.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import syzcogen.model.Employee;
import syzcogen.model.SalaryProfile;
import syzcogen.util.DBConnectionUtil;

public class ProfileManagementServiceImpli implements ProfileManagementService {

	/** Initialize logger */
	//innitialize variables
	public static final Logger log = Logger.getLogger(ProfileManagementServiceImpli.class.getName());
	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	private static SalaryProfile salaryProfile = null;
	private static List<SalaryProfile> list = null;
	private static Employee employee = null;
	private static PreparedStatement prearedStatement = null;

	

	@Override
	public List<SalaryProfile> getSalaryList() {
		list = new ArrayList<SalaryProfile>();
		try {
				//declare query
			String sql = "SELECT * FROM salary_details";

			connection = DBConnectionUtil.createConnection(); //open connection

			statement = connection.createStatement();

			resultSet = statement.executeQuery(sql); //excecute query

			while (resultSet.next()) {
				salaryProfile = new SalaryProfile();

				salaryProfile.setEmpId(resultSet.getString("empId"));
				salaryProfile.setbSal(resultSet.getFloat("bsal"));
				salaryProfile.setEpf(resultSet.getFloat("epf"));
				salaryProfile.setEtf(resultSet.getFloat("etf"));
				salaryProfile.setoThours(resultSet.getFloat("over-time-hours"));
				salaryProfile.setBonus(resultSet.getFloat("bonus"));
				salaryProfile.setPayDate(resultSet.getString("pay-date"));
				salaryProfile.setAccNum(resultSet.getString("account-num"));
				salaryProfile.setBank(resultSet.getString("bank"));
				salaryProfile.setAccName(resultSet.getString("account-name"));
				salaryProfile.setTax(resultSet.getFloat("tax"));
				salaryProfile.setWelfareFee(resultSet.getFloat("welfare-fee"));
				salaryProfile.setTotSalary(resultSet.getFloat("total-salary"));
				salaryProfile.setCreateDate(resultSet.getString("create-date"));
				salaryProfile.setCreatedBy(resultSet.getString("lastChangeBy"));
				list.add(salaryProfile);
			}
		}  catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			//close the connection and preparedstatement
			try {
				if (prearedStatement != null) {
					prearedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return list;
	}

	

	@Override
	public Employee getEmployeeDetailsById(String Id) {

		try {
			//declare query
			String sql = "SELECT fname,lname,address,email,title,empId FROM emp_details WHERE empId = '" + Id + "'";

			connection = DBConnectionUtil.createConnection();//open connection

			statement = connection.createStatement();

			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				employee = new Employee();

				employee.setfName(resultSet.getString("fname"));
				employee.setlName(resultSet.getString("lname"));
				employee.setAddress(resultSet.getString("address"));
				employee.setEmail(resultSet.getString("email"));
				employee.setTitle(resultSet.getString("title"));
				employee.setEmpId(resultSet.getString("empId"));
			}

		}  catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			//close the connection and preparedstatement
			try {
				if (prearedStatement != null) {
					prearedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

		return employee;
	}

	@Override
	public boolean addSalaryProfile(SalaryProfile s) {
		boolean flag = false;

		try {

			//declare query
			String sql = "INSERT INTO salary_details VALUES('" + s.getEmpId() + "'," + s.getbSal() + "," + s.getEpf()
					+ "," + s.getEtf() + "," + s.getoThours() + "," + s.getBonus() + ",'" + s.getPayDate() + "','"
					+ s.getAccNum() + "','" + s.getBank() + "','" + s.getAccName() + "'," + s.getTax() + ","
					+ s.getWelfareFee() + "," + s.getTotSalary() + ",'" + s.getCreateDate() + "','"+s.getCreatedBy()+"')";
			
			
			
			connection = DBConnectionUtil.createConnection();//open connection

			prearedStatement = connection.prepareStatement(sql);

//			System.out.println(s.getAccNum());

			prearedStatement.execute();
			flag = true;

		}  catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			
			//close the connection and preparedstatement
			 
			 
			try {
				if (prearedStatement != null) {
					prearedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

		return flag;
	}

	@Override
	public SalaryProfile getSalaryDetailsById(String Id) {
		salaryProfile = new SalaryProfile();
		//declare query
		String sql = "SELECT * FROM salary_details WHERE empId = '" + Id + "'";

		try {
			connection = DBConnectionUtil.createConnection();//open connection

			statement = connection.createStatement();

			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				salaryProfile.setEmpId(resultSet.getString("empId"));
				salaryProfile.setbSal(resultSet.getFloat("bsal"));
				salaryProfile.setEpf(resultSet.getFloat("epf"));
				salaryProfile.setEtf(resultSet.getFloat("etf"));
				salaryProfile.setoThours(resultSet.getFloat("over-time-hours"));
				salaryProfile.setBonus(resultSet.getFloat("bonus"));
				salaryProfile.setPayDate(resultSet.getString("pay-date"));
				salaryProfile.setAccNum(resultSet.getString("account-num"));
				salaryProfile.setBank(resultSet.getString("bank"));
				salaryProfile.setAccName(resultSet.getString("account-name"));
				salaryProfile.setTax(resultSet.getFloat("tax"));
				salaryProfile.setWelfareFee(resultSet.getFloat("welfare-fee"));
				salaryProfile.setTotSalary(resultSet.getFloat("total-salary"));
				salaryProfile.setCreateDate(resultSet.getString("create-date"));

			}

		}  catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			//close the connection and preparedstatement
			try {
				if (prearedStatement != null) {
					prearedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

		return salaryProfile;

	}

	@Override
	public boolean updateSalaryProfile(SalaryProfile s) {
		boolean flag = false;

		//declare query

		String sql = "UPDATE salary_details SET `bsal` = '" + s.getbSal() + "', `epf` = '" + s.getEpf() + "', `etf` = '"
				+ s.getEtf() + "', `over-time-hours` = '" + s.getoThours() + "', `bonus` = '" + s.getBonus()
				+ "', `pay-date` = '" + s.getPayDate() + "', `account-num` = '" + s.getAccNum() + "', `bank` = '"
				+ s.getBank() + "', `account-name` = '" + s.getAccName() + "', `tax` = '" + s.getTax()
				+ "', `welfare-fee` = '" + s.getWelfareFee() + "', `total-salary` = '" + s.getTotSalary()
				+ "' WHERE (`empId` = '" + s.getEmpId() + "')";

		try {
			connection = DBConnectionUtil.createConnection();//open connection

			prearedStatement = connection.prepareStatement(sql);

//			System.out.println(s.getoThours());
//			System.out.println(s.getbSal());
			prearedStatement.executeUpdate();

			flag = true;
		}  catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			//close the connection and preparedstatement
			try {
				if (prearedStatement != null) {
					prearedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return flag;
	}

	@Override
	public boolean deleteSalaryProfile(String Id) {
		boolean flag = false;
		//declare query
		String sql = "DELETE FROM salary_details WHERE empId ='" + Id + "'";

		try {
			connection = DBConnectionUtil.createConnection();//open connection
			prearedStatement = connection.prepareStatement(sql);
			prearedStatement.executeUpdate();

			flag = true;
		}  catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			//close the connection and preparedstatement
			try {
				if (prearedStatement != null) {
					prearedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

		return flag;
	}

	@Override
	public String validateUser(String id) {
		//declare query
		
		String sql = "SELECT * FROM emp_details WHERE empId=?";
		try {
			connection = DBConnectionUtil.createConnection(); //open connection
			prearedStatement = connection.prepareStatement(sql);
			prearedStatement.setString(1, id);
			resultSet = prearedStatement.executeQuery();

			if(resultSet.next())return "True";
			else return "False";
			
			
		}  catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			//close the connection and preparedstatement
			try {
				if (prearedStatement != null) {
					prearedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return "Error";
	}

		
}
