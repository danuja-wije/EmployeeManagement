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

import syzcogen.model.Request;
import syzcogen.util.DBConnectionUtil;

public class RequestManagementServiceImpli implements RequestManagementService {
	/** Initialize logger */
	public static final Logger log = Logger.getLogger(RequestManagementServiceImpli.class.getName());
	
	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	private static PreparedStatement prearedStatement = null;
	private static Request request = null;
	private static List<Request> reqList = null;

	

	@Override
	public String getRequestStatusByEmpId(String e) {
		String statu = null;
		String sql = "SELECT status FROM emp_request WHERE empId='"+e+"' AND status='pending'";
		
		try {
			connection = DBConnectionUtil.createConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			if(resultSet.next()) {
				
				statu = resultSet.getString("status");
			}
			
		} catch (SQLException ex) {
			log.log(Level.SEVERE, ex.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end
			 * of transaction
			 */
			try {
				if (prearedStatement != null) {
					prearedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException ex) {
				log.log(Level.SEVERE, ex.getMessage());
			}
		}
		
		return statu;
		
		
	}

	@Override
	public boolean addRequest(Request r) {
		boolean flag = false;
		
		String sql = "INSERT INTO emp_request(empId,date,purpose,status,reviewedby) VALUES('" + r.getEmpId() + "','"+r.getDate()+"','"+r.getPurpose()+"','"+r.getStatus()+"','"+r.getReviewedBy()+"')";
		
		try {
			connection = DBConnectionUtil.createConnection();
			
			prearedStatement = connection.prepareStatement(sql);
			prearedStatement.executeUpdate();

			flag = true;
		}catch (SQLException ex) {
			log.log(Level.SEVERE, ex.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end
			 * of transaction
			 */
			try {
				if (prearedStatement != null) {
					prearedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException ex) {
				log.log(Level.SEVERE, ex.getMessage());
			}
		}
		
		return flag;
	}


	@Override
	public boolean updateStatus(int id, String status,String rev) {
		boolean flag =false;
		String sql = "UPDATE emp_request SET status='"+status + "',reviewedby='"+rev+"' WHERE reqId="+id;
		
		try {
			
			connection = DBConnectionUtil.createConnection();
			prearedStatement = connection.prepareStatement(sql);
			
			prearedStatement.executeUpdate();
			
			flag = true;
			
		} catch (SQLException ex) {
			log.log(Level.SEVERE, ex.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end
			 * of transaction
			 */
			try {
				if (prearedStatement != null) {
					prearedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException ex) {
				log.log(Level.SEVERE, ex.getMessage());
			}
		}
		return flag;
	}

	@Override
	public List<Request> getReqList() {
		reqList = new ArrayList<Request>();
		
		String sql = "SELECT * FROM emp_request WHERE status='pending'";
		
		try {
			
			connection = DBConnectionUtil.createConnection();
			
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				request = new Request();
				request.setReqId(resultSet.getInt("reqId"));
				request.setDate(resultSet.getString("date"));
				request.setEmpId(resultSet.getString("empId"));
				request.setPurpose(resultSet.getString("purpose"));
				request.setStatus(resultSet.getString("status"));
				
				reqList.add(request);
			}
			
			
		} catch (SQLException ex) {
			log.log(Level.SEVERE, ex.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end
			 * of transaction
			 */
			try {
				if (prearedStatement != null) {
					prearedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException ex) {
				log.log(Level.SEVERE, ex.getMessage());
			}
		}
		
		return reqList;
	}

	@Override
	public List<Request> getDetailsByReview(String id) {
		reqList = new ArrayList<Request>();
		String sql = "SELECT * FROM emp_request  WHERE reviewedby='"+id+"'";
		
		try {
			connection = DBConnectionUtil.createConnection();
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				request = new Request();
				request.setEmpId(resultSet.getString("empId"));
				request.setDate(resultSet.getString("date"));
				request.setPurpose(resultSet.getString("purpose"));
				request.setReqId(resultSet.getInt("reqId"));
				request.setStatus(resultSet.getString("status"));
				
				reqList.add(request);				
			}
		}catch (SQLException ex) {
			log.log(Level.SEVERE, ex.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end
			 * of transaction
			 */
			try {
				if (prearedStatement != null) {
					prearedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException ex) {
				log.log(Level.SEVERE, ex.getMessage());
			}
		}
		return reqList;
	}


}
