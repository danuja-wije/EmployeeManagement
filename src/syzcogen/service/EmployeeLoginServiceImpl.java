package syzcogen.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import syzcogen.model.EmployeeLogin;
import syzcogen.util.DBConnectionUtil;

public class EmployeeLoginServiceImpl implements EmployeeLoginService {
	
	
	
	/** Initialize logger */
	public static final Logger log = Logger.getLogger(EmployeeLoginServiceImpl.class.getName());
	
	private static Connection connection = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	@Override
	public String validateUser(EmployeeLogin e) {
		
		//declare query
		
		String sql = "SELECT * FROM login_details WHERE empId=? and password=?";
		try {
			connection = DBConnectionUtil.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, e.getUsername());
			preparedStatement.setString(2, e.getPassword());
			resultSet = preparedStatement.executeQuery();

			if(resultSet.next())return "True";
			else return "False";
			
			
		} catch (SQLException ex1)  {
			log.log(Level.SEVERE, ex1.getMessage());
		} finally {
			//close connection and preparedstatement
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException ex) {
				log.log(Level.SEVERE, ex.getMessage());
			}
		}
		return "Error";
	}
	@Override
	public boolean updateLastLog(String Id,String lg) {
		boolean flag = false;
		
		//declare query
		String sql = "UPDATE login_details SET `last_login` = '"+lg+"' WHERE (`empId` = '"+Id+"')";
		
		try {
			connection = DBConnectionUtil.createConnection();
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.executeUpdate();
			
			flag = true;
		} catch (SQLException ex1) {
			log.log(Level.SEVERE, ex1.getMessage());
		} finally {
			//close connection and preparedstatement
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
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

}
