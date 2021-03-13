<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page
	import="java.io.*,java.util.*,javax.servlet.*,syzcogen.servelet.LoginController"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="css/header.css" rel="stylesheet" type="text/css">
<link href="css/displaySalaryProfile.css" rel="stylesheet" type="text/css">
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/realtimeClock.js"></script>
<script src="js/jquery.min.js" type="text/javascript"></script>

</head>
<body onload="realTimeClock();"">

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
	
<%

try{
	
		String sessionName = LoginController.getSession();
	if (sessionName == null)
		response.sendRedirect("/login.jsp");
}catch(Exception e){
	e.printStackTrace();
}


	String name = (String) session.getAttribute("s_username");
	String success = (String) request.getAttribute("message1");
	String fail = (String) request.getAttribute("message2");
	String path = "";
	String type = "";
	String message = "";
	String status = (String) request.getAttribute("status");
	if (success != null)
		path = "<div class='alertBox'> <div class='alert alert-success alert-dismissible fade show'><button type='button' class='close' data-dismiss='alert'>&times;</button><strong>Success! </strong> "
		+ success + "</div> </div>";
	else if (fail != null)
		path = "<div class='alert alert-danger alert-dismissible fade show'><button type='button' class='close' data-dismiss='alert'>&times;</button><strong>Error!</strong> "
		+ fail + "</div>";

	if (status != null) {
		type = "disabled='disabled'";
		message = "<div class='alert alert-warning alert-dismissible fade show'><button type='button' class='close' data-dismiss='alert'>&times;</button><strong>Warning!</strong> Your Request Being Pending. You can't Request more than one request at the time.</div>";
	}
	if (name == null)
		response.sendRedirect("../index.jsp");
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
  <li><a class="active" href="#home">Home</a></li>
	<%=pathhead %>
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
	
	<div class="box">
	

	<div class="container">
		<div class="col-sm-4 personalDetail">

		
			<div class="card">



				<img id="profileImg" class="rounded-circle profilePic"
					src="images/profile_icon.jpg" alt="Card image"
					style="width: 47.5%; display: flex; position: static;">
				<div class="card-body" style="float: right">
					<h4 class="card-title">${employee.title} .${employee.fName} ${employee.lName}</h4>
					<div class="card-text" style="margin-left: 15px;">

						<div class="row">
							<b>Employee Id : </b>${employee.empId}
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
		<div class="col-sm-8 salaryDetail">
			<div class="card">
		<%=path%>




				<div class="card-body" style="float: right">

					<table class="sDetails">
						<thead>
							<tr>
								<td>
									<h3 class="card-title" style="color: #0b69db">Salary
										Details</h3>

								</td>
								<td>
									<h3 class="card-title" style="color: #0b69db">Account
										Details</h3>

								</td>
							</tr>

						</thead>
						<tbody>
							<tr>
								<td>
									<div class="row">
										<p>
											<b>Join Date : </b>&nbsp ${profile.createDate}
										</p>
									</div>
								</td>


								<td>
									<div class="row">
										<p>
											<b>Account Name : </b> &nbsp${profile.accName}
										</p>
									</div>
								</td>

							</tr>
							<tr>
								<td>
									<div class="row">
										<p>
											<b>Basic Salary : </b> &nbsp${profile.bSal}
										</p>
									</div>
								</td>

								<td>
									<div class="row">
										<p>
											<b>Account Number : </b> &nbsp${profile.accNum}
										</p>
									</div>

								</td>

							</tr>

							<tr>
								<td>
									<div class="row">
										<p>
											<b>ETF Fee : </b> &nbsp${profile.etf}
										</p>
									</div>
									<div class="row">
										<p>
											<b>EPF Fee : </b>&nbsp ${profile.epf}
										</p>
									</div>
									<div class="row">
										<p>
											<b>Over Time Hours : </b> &nbsp${profile.oThours}
										</p>
									</div>
									<div class="row">
										<p>
											<b>Over Time Value : </b> &nbsp${profile.oThours * 250}
										</p>
									</div>
									<div class="row">
										<p>
											<b>Bonus Salary : </b>&nbsp ${profile.bonus}
										</p>
									</div>
									<div class="row">
										<p>
											<b>Payment Date : </b>&nbsp ${profile.payDate}
										</p>
									</div>
									<div class="row">
										<p>
											<b>Tax Fee : </b>&nbsp ${profile.tax}
										</p>
									</div>
									<div class="row">
										<p>
											<b>Welfare Fee : </b>&nbsp ${profile.welfareFee}
										</p>
									</div>
									<div class="row">
										<p>
											<b>Total Salary : </b> &nbsp${profile.totSalary}
										</p>
									</div>
								</td>

							</tr>

						</tbody>

					</table>

					<div class="cardReq">
			
						<div class="jumbotron">
							<h1>Request</h1>
							<div class="req_btn">
								<p>
									<button type="button" class="btn btn-outline-primary"
										data-toggle="modal" data-target="#popupModel">Salary
										Statement</button>
								</p>
								<p>
									<button type="button" class="btn btn-outline-primary">Finacial
										Odit</button>
								</p>
								<p>
									<button type="button" class="btn btn-outline-primary">Event
										Fund</button>
								</p>
								<p>
									<button type="button" class="btn btn-outline-primary">Loan</button>
								</p>
							</div>
						</div>
					</div>

				</div>

			</div>

		</div>

		<div class="modal fade" tabindex="-1" role="dialog" id="popupModel">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Request Salary Statement</h5>
						<h5 class="modal-title">EMP ID:${s_username}</h5>
					</div>





					<%=message%>


					<form
						action="${pageContext.request.contextPath}/RequestController?action=STATEMENT"
						method="POST">

						<div class="modal-body">
							<div class="form-group">
								Enter Date : <input class="form-control"
									placeholder="Enter Date" type="date" name="date" <%=type%>>
							</div>
							<div class="form-group">
								<textarea rows="3" cols="1" class="form-control" name="purpose"
									placeholder="Enter Purpose" <%=type%>></textarea>
							</div>
							<div class="modal-footer">

								<input class="btn btn-primary" type="submit" value="Enter"
									style="width: 100px;]" <%=type%>>

							</div>
						</div>
					</form>
				</div>
			</div>

		</div>
	</div>
	
	</div>

	
	
		<div class="box2">
		<jsp:include page="/footer.jsp"></jsp:include>
		</div>
	
</div>
	
</body>
</html>






