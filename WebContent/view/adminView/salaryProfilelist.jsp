<%@page import="syzcogen.servelet.LoginController"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page
	import="java.io.*,java.util.*,javax.servlet.*,syzcogen.model.Request,syzcogen.service.RequestManagementService,syzcogen.service.RequestManagementServiceImpli"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Salary Profile List</title>

<script type="text/javascript" src="js/realtimeClock.js"></script>
<link href="css/header.css" rel="stylesheet" type="text/css">
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="DataTables/datatables.min.css" rel="stylesheet"
	type="text/css">
<link href="DataTables/css/dataTables.jqueryui.min.css" rel="stylesheet"
	type="text/css">
<link href="css/salaryProfileList.css" rel="stylesheet" type="text/css">
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.min.js" type="text/javascript"></script>




</head>




<body onload="realTimeClock();">
	<div class="mainBody">
		<header>
			<%
				String pathhead = "";
			try {
				String type = (String) session.getAttribute("s_username");
				if (type != null) {

					if (type.trim().charAt(0) == 'F') {
				pathhead = "<li><a href= 'ListSalaryProfileController'>Profile Manager</a></li>";

					} else
				pathhead = "";
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			%>


			<div class="rowItem">


				<img alt="logo" src="images/syzcogen.png">


<h1 class="headerTitle">SyZcoGen IT Solutions (Pvt)Ltd</h1>

				<div class="detailBox">

					<h4>
						<b>Emp ID: </b> ${s_username}
					</h4>
					<br>
					<div class="mylogout">
						<button class="btn btn-primary"
							onclick="window.location.href='${pageContext.request.contextPath}/view/logout.jsp'"
							style="width: 150px;">Log Out</button>
					</div>


				</div>


			</div>





			<nav class="navBar">
				<ul class="unList">
					<li><a href="DisplayUserProfileController">Home</a></li>
					<li><a class="active" href="ListSalaryProfileController">Project
							Manager</a></li>
					<li><a href="#news">Notice</a></li>
					<li><a href="#contact">Projects</a></li>
					<li style="float: right"><div class="clockBox">
							<h4>
								<div id="clock"></div>
							</h4>

						</div></li>
				</ul>


			</nav>



		</header>

		<div class="box">

			<%
				/* try {

				String sessionName = LoginController.getSession();
				if (sessionName == null)
					response.sendRedirect("/login.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			} */

			String success = (String) request.getAttribute("message1");
			String fail = (String) request.getAttribute("message2");
			String error = (String) request.getAttribute("error");
			String errorPath = "";
			if (error != null)
				errorPath = "<div class='alert alert-danger'><strong>Add Salary Profile Fail!</strong> " + error + "</div>";
			String path = "";
			if (success != null) {
				path = "<div class='alert alert-success'><strong>Success! </strong> " + success + "</div>";
			} else if (fail != null) {
				path = "<div class='alert alert-danger'><strong>Danger!</strong> " + fail + "</div>";
			}

			RequestManagementService requestManagement = new RequestManagementServiceImpli();
			List<Request> list = new ArrayList<Request>();
			list = requestManagement.getReqList();
			String noReqMg = "";
			if (list.isEmpty() || list == null)
				noReqMg = "No any Pending Request";
			%>


			<div class="container">

				<%=errorPath%>
				<%=path%>

				<div class="listRow">
					<div class="mainTable">
						<div class="jumbotron">
						<div class="summaryBox">
							<button class="btn btn-light"
								onclick="window.location.href='RequestController?action=SUMMARY'">View
								Summary</button>
							
						</div>
							<h5 class="pendinReq">Pending Request..</h5>
							<div class="nomoreReq"><%=noReqMg%></div>
							<div class="scrollBox">
								<c:forEach items="${req}" var="request">

									<div class="scrollItems">
										<div class="alert alert-primary alert-dismissible">
											<button type="button" class="close" data-dismiss="alert">×</button>
											<strong>Salary Statement!<br></strong> Req Id :
											${request.reqId} <br> Emp Id : ${request.empId} <br>
											Date : ${request.date} <br> Purpose : ${request.purpose}
											<br>
											<div class="actionBtn" id="action1">
												<a class="btn btn-light"
													href="RequestController?action=APPROVE&reqId=${request.reqId}">Approve</a>
											</div>
											<div class="actionBtn" id="action2">
												<a class="btn btn-light"
													href="RequestController?action=REJECT&reqId=${request.reqId}">Reject</a>
											</div>
										</div>

									</div>
								</c:forEach>

							</div>
						</div>
					</div>
					<div class="secondTable ">
						<div class="mybtn">
							<button class="btn btn-primary btnAdd" data-toggle="modal"
								data-target="#popupModel" style="margin-top: 5px">
								<span>Add Salary Profile</span>
							</button>
						</div>
						<table class="table" id="datatable">
							<thead class="thead-dark">

								<tr>

									<th>User Name</th>
									<th>Total Salary</th>
									<th>Create Date</th>
									<th>Last Handle</th>
									<th>Action</th>

								</tr>
							</thead>
							<tbody>

								<c:forEach items="${sal}" var="salary">

									<tr>
										<td>${salary.empId}</td>
										<td>${salary.totSalary}</td>
										<td>${salary.createDate}</td>
										<td>${salary.createdBy}</td>
										<td><a
											href="${pageContext.request.contextPath}/UpdateSalaryController?action=EDIT&empId=${salary.empId}">Edit</a>
											| <a
											href="${pageContext.request.contextPath}/UpdateSalaryController?action=DELETE&empId=${salary.empId}">Remove</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>


						<div class="modal fade" tabindex="-1" role="dialog"
							id="popupModel">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title">Profile Manager</h5>
										<h5 class="modal-title">Manager ID:${s_username}</h5>
									</div>
									<form
										action="${pageContext.request.contextPath}/AddSalaryController?action=SEARCH"
										method="POST">

										<div class="modal-body">
											<div class="form-group">
												<input class="form-control" placeholder="Enter Employee ID"
													type="text" name="empId">
											</div>
											<div class="modal-footer">

												<input class="btn btn-primary" type="submit" value="Enter"
													style="margin-right: 115px">

											</div>
										</div>
									</form>
								</div>
							</div>

						</div>



					</div>

				</div>






			</div>

		</div>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/jquery.min.js"></script>
		<script type="text/javascript" src="DataTables/datatables.min.js"></script>
		<script>
		$(document).ready(function() {
			$('#datatable').DataTable();
		});
	</script>


		<div class="box2">
			<jsp:include page="/footer.jsp"></jsp:include>
		</div>
	</div>

</body>
</html>

