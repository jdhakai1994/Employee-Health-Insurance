<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Health Insurance Management</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<!-- custom CSS -->
<link href="<%=request.getContextPath()%>/css/result.css" rel="stylesheet" type="text/css" />

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<!-- validation -->
<script src="https://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container-fluid">
	<div class="row">
	
		<!-- importing appropriate navbar -->
		<c:import url="../navbars/admin_navbar.jsp" />
					
		<!-- CONTENT -->
		<div class="col-sm-9 no-gutter">
			<div class="content">
				<div class="content-header">
					<span class="glyphicon glyphicon-align-justify"></span>
				</div>
				<div class="content-body">
					<div>
						<h3><c:out value='${requestScope.heading}'/></h3>						
					</div>
					
					<div class="report-content">
						<p style="font-size:20px"><span class="glyphicon glyphicon-ok-circle"  style="color:green"></span> <c:out value='${requestScope.message}'/></p>
					</div>
					
				</div>
				<div class="content-footer">
					<div class="container">
						<div class="row">
							<div class="col-sm-6"><strong>Copyright &#169; 2016.</strong> All Rights Reserved</div>
							<div class="col-sm-6" style="text-align:right"><strong>Version</strong> 1.0</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- CONTENT -->
		
	</div>
</div>
</body>
</html>