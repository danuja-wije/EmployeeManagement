<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String name = (String)session.getAttribute("s_username");

	if(name != null) response.sendRedirect("LoginController?action=LIST");
	else response.sendRedirect("login.jsp"); %>
</body>
</html>


