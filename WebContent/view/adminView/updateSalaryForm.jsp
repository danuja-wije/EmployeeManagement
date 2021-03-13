<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.io.*,java.util.*,javax.servlet.*,syzcogen.servelet.LoginController"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="js/realtimeClock.js"></script>
<link href="css/header.css" rel="stylesheet" type="text/css">
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="css/updateSalary.css" rel="stylesheet" type="text/css">


</head>
<body>
	<div class="mainBody">
	
	<header>
		<%

String pathhead = "";
try{
String type = (String)session.getAttribute("s_username");
if(type != null){
	
	if(type.trim().charAt(0) == 'F'){
		pathhead = "<li><a href= 'ListSalaryProfileController'>Profile Manager</a></li>";
		
	}else pathhead = "";
}
	
}catch(Exception e){
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
				<button  class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/view/logout.jsp'" style="width: 150px;">Log Out</button>
				</div>


			</div>

	
	</div>





	<nav class="navBar">
		<ul class="unList">
  <li><a href="DisplayUserProfileController">Home</a></li>
	<li><a class="active" href="ListSalaryProfileController">Project Manager</a></li>
  <li><a href="#news">Notice</a></li>
  <li><a href="#contact">Projects</a></li>
  <li style="float:right"><div class="clockBox">
			<h4>
				<div id="clock"></div>
			</h4>

		</div></li>
</ul>
		
		
	</nav>
	
	
	
	</header>
	
	
	<%
try{
	
	String sessionName = LoginController.getSession();
if (sessionName == null)
	response.sendRedirect("/login.jsp");
}catch(Exception e){
e.printStackTrace();
}
%>

	<div class="container">
		<div class="box">
		<div class="myTable">
		<div class="row" >
			<div class="col-sm-4 personalDetail">

			<div class="card" style="background: none;outline: none;border: none;">



				<img id="profileImg" class="rounded-circle profilePic"
					src="images/profile_icon.jpg" alt="Card image"
					style="width: 47.5%; display: flex; position: static;">
				<div class="card-body" style="float: right">
					<h4 class="card-title">${employee.title} .${employee.fName} ${employee.lName}</h4>
					<div class="card-text" style="margin-left: 15px;">

						<div class="row">
							<b>Employee Id : </b>${empId}
						</div>
						<div class="row">
							<b>Address : </b>${employee.address}
						</div>
						<div class="row">
							<b>E-mail : </b>${employee.email}
						</div>
					</div>
					<a href="#" class="btn btn-primary stretched-link"
						style="margin-top: 10px;">See Profile</a>
				</div>

			</div>


		</div>
			<div class="col-sm-8" style="margin-top: 20px;">

				<form
					action="${pageContext.request.contextPath}/UpdateSalaryController"
					method="POST" class="tableUpdate">
					<table class="updateTable">
						<tr>
							<td class="tableText"> <h5>EMP ID</h5></td>

							<td><input type="text" class="form-control" value="${empId}" name="empId"
								readonly="readonly"></td>
							<td class="tableText"> <h5>Over Time Hours</h5></td>

							<td><input type="text" class="form-control" value="${profile.oThours}"
								name="otHours" pattern="^[0-9]*$"></td>
						</tr>

						<tr>
							<td class="tableText"> <h5>Basic Salary</h5></td>
							<td><input type="text" class="form-control" value="${profile.bSal}" name="bSal" pattern="^[+]?([0-9]+([.][0-9]*)?|[.][0-9]+)$"></td>
							<td class="tableText"> <h5>ETF</h5></td>
							<td><input type="text" class="form-control" value="${profile.etf}" name="etf" pattern="^[+]?([0-9]+([.][0-9]*)?|[.][0-9]+)$"></td>
						</tr>

						<tr>
							<td class="tableText"> <h5>EPF</h5></td>
							<td><input type="text" class="form-control" value="${profile.epf}" name="epf" pattern="^[+]?([0-9]+([.][0-9]*)?|[.][0-9]+)$"></td>
							<td class="tableText"> <h5>Bonus</h5></td>
							<td><input type="text" class="form-control" value="${profile.bonus}" name="bonus" pattern="^[+]?([0-9]+([.][0-9]*)?|[.][0-9]+)$"></td>
						</tr>


						<tr>
							<td class="tableText"> <h5>Salary Date</h5></td>
							<td><input type="date" class="form-control" value="${profile.payDate}"
								name="payDate"></td>
							<td class="tableText"> <h5>Account Number</h5></td>
							<td><input type="text" class="form-control" value="${profile.accNum}"
								name="accNum" pattern="^[0-9]*$"></td>
						</tr>

						<tr>
							<td class="tableText"> <h5>Account Name</h5></td>
							<td><input type="text" class="form-control" value="${profile.accName}"
								name="accName"></td>
							<td class="tableText"> <h5>Bank</h5></td>
							<td><input type="text" class="form-control" value="${profile.bank}" name="bank"></td>
						</tr>


						<tr>
							<td class="tableText"> <h5>Tax</h5></td>
							<td><input type="text" class="form-control" value="${profile.tax}" name="tax" pattern="^[+]?([0-9]+([.][0-9]*)?|[.][0-9]+)$"></td>
							<td class="tableText"> <h5>Welfare Fee</h5></td>
							<td><input type="text" class="form-control" value="${profile.welfareFee}"
								name="welfareFee" pattern="^[+]?([0-9]+([.][0-9]*)?|[.][0-9]+)$"></td>
						</tr>

						<tr>
							<td class="tableText"> <h5>Joined Date</h5></td>

							<td><input type="date" class="form-control" placeholder="${profile.bSal}"
								value="${profile.createDate}" name="createDate"
								disabled="disabled"></td>
							<td class="tableText"> <h5>Total Salary</h5></td>

							<td><input type="text" class="form-control" value="${profile.totSalary}"
								name="totSalary" readonly="readonly"></td>
						</tr>

					</table>
					<input type="submit" name="btn"
						class="btn btn-primary stretched-link" value="Save Details"
						style="margin-top: 20px;"> <input type="submit" name="btn"
						class="btn btn-primary stretched-link" value="Delete Profile"
						style="margin-top: 20px;">
				</form>
			</div>

		</div>
		</div>
				
		
		</div>
	</div>
	
	
	</div>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>












