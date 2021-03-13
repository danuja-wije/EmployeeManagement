<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="js/realtimeClock.js"></script>
<link href="css/header.css" rel="stylesheet" type="text/css">
<link href="css/footer.css" rel="stylesheet" type="text/css">
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/realtimeClock.js"></script>

<style >
.mainBody{
background: #f7f7f7;
}
.table{
margin-top: 5PX;
}
.table thead,tbody,th,td{
	width: 50px;

}
.whiteBox{
background:white;
	min-height: 650px;
}

.headerTitle {
	margin-top: 70px;
	opacity: 0.8;
}
</style>

</head>
<body onload="realTimeClock();">

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




<div class="container">
<h1>Summary Reports</h1>


	<div class="whiteBox">
<ul class="nav nav-tabs nav-justified">
    <li class="nav-item">
      <a class="nav-link active" href="RequestController?action=SUMMARY">Salary Request</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">Loans</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">Fund Request</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">Odit Request</a>
    </li>
  </ul>
	<table class="table" >
	
	<thead class="thead-dark">
		<tr>
			<th>
				Employee ID
			</th>
			
			<th>
				Request ID
			</th>
			
			<th>
				Purpose
			</th>
			
				<th>
					Date
				</th>
			
			<th>
				Status
			</th>
		
		</tr>
	
	</thead>
	
	<tbody>
	<c:forEach items="${reqList}" var="req">
	
	
		<tr>
		<td>
			${req.empId}
		</td>
			<td>
				${req.reqId}
			
			</td>
		
			<td>
				${req.purpose}
			</td>
			
			<td>
				${req.date}
			</td>
			<td>
				${req.status}
			</td>
			
		</tr>
	</c:forEach>
		
	
	</tbody>
	
	
	</table>
	

</div>

</div>
	
	</div>

<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>