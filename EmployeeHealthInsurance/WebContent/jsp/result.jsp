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
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<!-- custom CSS -->
<link href="<%=request.getContextPath()%>/css/result.css" rel="stylesheet" type="text/css" />

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<!-- validation -->
<script src="https://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<!-- custom JavaScript -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/employeeregister.js"></script>

</head>

<body>
	<div class="container-fluid">
		<div class="row">
	
			<!-- importing appropriate navbar -->
			<c:set var="role" value="${sessionScope.role }"/>
			<c:choose>
				<c:when test='${role.equals("member")}'>
					<c:import url="navbars/member_navbar.jsp" />
				</c:when>
				<c:otherwise>
					<c:import url="navbars/admin_navbar.jsp" />
				</c:otherwise>
			</c:choose>
			
			<!-- CONTENT -->
			<div class="col-sm-9 no-gutter">
				<div class="content">
				
					<!-- CONTENT HEADER -->
					<div class="content-header">
						<span class="glyphicon glyphicon-align-justify"></span>
					</div>
					<!-- END CONTENT HEADER -->
				
					<!-- CONTENT BODY -->
					<div class="content-body">
						<div>
							<h3><c:out value='${requestScope.heading}'/></h3>						
						</div>
						
						<!-- REPORT CONTENT -->
						<div class="report-content">
						
						<c:set var="type" value="${requestScope.type }"/>
						<c:choose>
							<c:when test='${type == "report"}'>
							<!-- REPORT CONTENT HEADER -->
							<div class="report-header">
								<div class="row">
									<div class="col-md-6">
										<h5>E-Card Report</h5>
									</div>
									<div class="col-md-6" style="text-align:right">
										<h4>Health Insurance Company</h4>
										<p>Toll Free : 1800-0000-0000-0001<br>
										Toll Free : 1800-0000-0000-0002</p>
									</div>
								</div>
							</div>
							<!-- END REPORT CONTENT HEADER -->
							
							<!-- REPORT CONTENT BODY -->
							<div class="report-body">
								<hr>
								<c:set var="details" value="${requestScope.ecard }"/>
								<table class="table">
									<tr>
										<td>Beneficiary Name:</td>
										<td><c:out value='${details.beneficiaryName}'/></td>
									</tr>
									<tr>
										<td>Health Insurance ID (HI-ID):</td>
										<td><c:out value='${details.healthInsuranceId}'/></td>
									</tr>
									<tr>
										<td>Employee Code:</td>
										<td><c:out value='${details.employeeCode}'/></td>
									</tr>
									<tr>
										<td>Relation:</td>
										<td><c:out value='${details.relation}'/></td>
									</tr>
									<tr>
										<td>Date Of Birth:</td>
										<td><c:out value='${details.dateOfBirth}'/></td>
									</tr>
									<tr>
										<td>Primary Insured:</td>
										<td><c:out value='${details.primaryInsured}'/></td>
									</tr>
								</table>
							</div>
							<!-- END REPORT CONTENT BODY -->
							</c:when>
							<c:when test='${type == "success_message"}'>
								<p><span class="glyphicon glyphicon-ok"></span> <c:out value='${requestScope.message}'/></p>
							</c:when>
							<c:otherwise>
								<p><span class="glyphicon glyphicon-remove"></span> <c:out value='${requestScope.message}'/></p>
							</c:otherwise>
						</c:choose>		
						</div>
						<!-- END REPORT CONTENT -->
					</div>
					<!-- END CONTENT BODY -->
					
					<!-- CONTENT FOOTER -->
					<div class="content-footer">
						<div class="container">
							<div class="row">
								<div class="col-sm-6 copyright"><strong>Copyright &#169; 2016.</strong> All Rights Reserved</div>
								<div class="col-sm-6" style="text-align:right"><strong>Version</strong> 1.0</div>
							</div>
						</div>
					</div>
					<!-- END CONTENT FOOTER -->
				</div>
			</div>
			<!-- END CONTENT -->
		</div>
	</div>
</body>
</html>