<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Health Insurance Management</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<!-- custom CSS -->
<link href="<%=request.getContextPath()%>/css/employeeregister.css" rel="stylesheet" type="text/css" />

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<!-- validation -->
<script src="http://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container-fluid">
	<div class="row">
	
		<!-- import navbar -->
		<c:import url="./navbars/admin_navbar.jsp" />
		
		<!-- CONTENT -->
		<div class="col-sm-9 no-gutter">
			<div class="content">
				<div class="content-header">
					<span class="glyphicon glyphicon-align-justify"></span>
				</div>
				<div class="content-body">
					<div>
						<h3>Registration Management</h3>						
					</div>
					
					<div class="form-content">
						<form id="approveDependentForm" action="<%=request.getContextPath()%>/RegisterController?action=approve_dependent" method="post" role="form" class="form-horizontal">
							<table class="table table-bordered table-striped">
								<thead class="thead-inverse">
									<tr>
										<th>HI-ID</th>
										<th>EMPLOYEE NAME</th>
										<th>BENEFICIARY NAME</th>
										<th>RELATION</th>
										<th>D.O.B<br>(dd/mm/yyyy)</th>
										<th>START DATE<br>(dd/mm/yyyy)</th>
										<th>SUM INSURED</th>
										<th>VALIDITY<br>(in years)</th>
										<th>APPROVE</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${unapprovedDependentList}" var="current">
										<tr>
											<td><c:out value="${current.healthInsuranceId}" /></td>
											<td><c:out value="${current.employeeName}" /></td>
											<td><c:out value="${current.beneficiaryName}" /></td>
											<td><c:out value="${current.relation}" /></td>
											<td><c:out value="${current.dateOfBirth}" /></td>
											<td><c:out value="${current.startDate}" /></td>
											<td><c:out value="${current.sumInsured}" /></td>
											<td><c:out value="${current.policyPeriod}" /></td>
											<td><input type="checkbox" name="approved" value="${current.healthInsuranceId}"></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div class="form-group">
								<div class="pull-right col-sm-2">
									<input type="hidden" name="action" value="approve_dependent">
									<input type="submit" name="submit" id="submit" class="form-control btn btn-primary" value="Approve">
								</div>
							</div>
						</form>
						
					</div>
					
				</div>
				<div class="content-footer">
					<div class="container">
						<div class="row">
							<div class="col-sm-6 copyright"><strong>Copyright &copy 2016.</strong> All Rights Reserved</div>
							<div class="col-sm-6 version"><strong>Version</strong> 1.0</div>
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