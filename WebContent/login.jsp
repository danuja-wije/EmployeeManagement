<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>

<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="css/login.css" rel="stylesheet" type="text/css">
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/validateLoginForm.js" type="text/javascript"></script>

<style type="text/css">



.row .headerTitle {
	margin-top: 70px;
	opacity: 0.8;
}
</style>

</head>
<body onload="realTimeClock();">


	<div class="row">
		<div class="col-sm-4">

			<img alt="logo" src="images/syzcogen.png">

		</div>

		<h1 class="headerTitle">SyZcoGen IT Solutions (Pvt)Ltd</h1>
	</div>


	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<ul class="navbar-nav">
			<li  class="nav-item active"><a class="nav-link"
				href="index.jsp">Home</a></li>
			<li class="nav-item"><a class="nav-link" href="#">Projects</a></li>
			<li class="nav-item"><a class="nav-link" href="#">About Us</a></li>
		</ul>
		<div class="clockBox">

			<h3>
				<div id="clock"></div>
			</h3>

		</div>
	</nav>


	<%
	try{
		String name = (String) session.getAttribute("s_username");
		
	if (name != null)
		response.sendRedirect("DisplayUserProfileController");
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	
	
		String loginMessage = (String)request.getAttribute("loginMessage");
		String path = "";
		if(loginMessage != null){
			path = "<div class='alert alert-danger'><strong>Error !  </strong>"+loginMessage+" </div>";
		}
	%>

	<div class="container p-3 my-3 ">
		<%=path %>
		<div class="jumbotron jumbotron-fluid">
			<form action="${pageContext.request.contextPath}/LoginController"
				method="POST" name="registration">


				<div class="row">
					<div class="col-sm-4">
						<center>
							<h1>Welcome</h1>
							<img src="images/syzcogen.png" class="rounded-circle"
								alt="Cinque Terre">


						</center>
					</div>
					<div class="col-sm-8">

						<div class="d-flex flex-column">
							<div class="p-2 ">
								<div class="form-group">
									<label for="uname">Username:</label> <input type="text"
										class="form-control" id="username"
										placeholder="Enter username" name="username" required
										pattern="^[a-zA-Z][0-9]+$">
								</div>

							</div>
							<div class="p-2 ">
								<div class="form-group">
									<label for="pwd">Password:</label> <input type="password"
										class="form-control" id="pwd" placeholder="Enter password"
										name="password" required>
									<div class="valid-feedback">Valid.</div>
									<div class="invalid-feedback">Enter Password</div>
								</div>

							</div>
							<div class="p-2 ">
								<button type="submit" class="btn btn-primary">Login</button>

							</div>
						</div>

					</div>
				</div>
			</form>
		</div>
	</div>





<jsp:include page="footer.jsp"></jsp:include>
</body>


</html>



