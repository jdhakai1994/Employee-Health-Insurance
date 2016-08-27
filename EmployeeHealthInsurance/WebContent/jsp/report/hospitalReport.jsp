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
		<c:set var="role" value="${sessionScope.role }"/>
		<c:choose>
			<c:when test='${role.equals("member")}'>
				<c:import url="../navbars/member_navbar.jsp" />
			</c:when>
			<c:otherwise>
				<c:import url="../navbars/admin_navbar.jsp" />
			</c:otherwise>
		</c:choose>
					
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
						<c:set var="type" value="${requestScope.type }"/>
						<c:choose>
							<c:when test='${type == "list"}'>
								<div class="report-body">
									<table class="table table-hover table-sm">
										<caption style="font-weight:bold;font-size:15px">Hospital List Details</caption>
										<thead>
											<tr>
												<th>#</th>
												<th>Name</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${hospitalList}" var="current" varStatus="loop">
												<tr class="clickable" data-toggle="collapse" data-target=".demo${loop.index +1}">
													<td><c:out value="${loop.index +1}" /></td>
													<td colspan=2><c:out value="${current.hospitalName}" /></td>
												</tr>
												<tr class="collapse out demo${loop.index +1}">
													<td>Address :</td>
													<td><c:out value="${current.address}" /></td>											
												</tr>
												<tr class="collapse out demo${loop.index +1}">
													<td>City Name :</td>
													<td><c:out value="${current.cityName}" /></td>											
												</tr>
												<tr class="collapse out demo${loop.index +1}">
													<td>State Name :</td>
													<td><c:out value="${current.stateName}" /></td>											
												</tr>
												<tr class="collapse out demo${loop.index +1}">														
													<td>Pin Code :</td>
													<td><c:out value="${current.pincode}" /></td>											
												</tr>
												<tr class="collapse out demo${loop.index +1}">
													<td>STD Code :</td>
													<td><c:out value="${current.stdcode}" /></td>											
												</tr>
												<tr class="collapse out demo${loop.index +1}">
													<td>Phone Number :</td>
													<td><c:out value="${current.phNo}" /></td>											
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>									
							</c:when>
							<c:when test='${type == "report"}'>
							<!-- REPORT CONTENT BODY -->
							<div class="report-body">
								<table class="table table-hover table-sm">
									<caption style="font-weight:bold;font-size:15px">Hospital Details</caption>
									<tbody>
										<tr>
											<td>Hospital Name :</td>
											<td><c:out value="${requestScope.hospitaldetails.hospitalName}" /></td>											
										</tr>
										<tr>
											<td>Address :</td>
											<td><c:out value="${requestScope.hospitaldetails.address}" /></td>											
										</tr>
										<tr>
											<td>City Name :</td>
											<td><c:out value="${requestScope.hospitaldetails.cityName}" /></td>											
										</tr>
										<tr>
											<td>State Name :</td>
											<td><c:out value="${requestScope.hospitaldetails.stateName}" /></td>											
										</tr>
										<tr>
											<td>Pin Code :</td>
											<td><c:out value="${requestScope.hospitaldetails.pincode}" /></td>											
										</tr>
										<tr>
											<td>STD Code :</td>
											<td><c:out value="${requestScope.hospitaldetails.stdcode}" /></td>											
										</tr>
										<tr>
											<td>Phone Number :</td>
											<td><c:out value="${requestScope.hospitaldetails.phNo}" /></td>											
										</tr>
									</tbody>
								</table>
							</div>
							<!-- END REPORT CONTENT BODY -->
							</c:when>
							<c:when test='${type == "success_message"}'>
								<p style="font-size:20px"><span class="glyphicon glyphicon-ok-circle" style="color:green"></span> <c:out value='${requestScope.message}'/></p>
							</c:when>
							<c:when test='${type == "failure_message"}'>
								<p style="font-size:20px"><span class="glyphicon glyphicon-remove-circle" style="color:red"></span> <c:out value='${requestScope.message}'/></p>
							</c:when>
							<c:otherwise>
								<p style="font-size:20px"><span class="glyphicon glyphicon-exclamation-sign" style="color:red"></span> Something went wrong, please try again.</p>
							</c:otherwise>
						</c:choose>
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