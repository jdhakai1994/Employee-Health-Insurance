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
<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css" />

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<!-- validation -->
<script src="https://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<!-- custom JavaScript -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/unapprovedAppointment.js"></script>

</head>
<body>
<div class="container-fluid">
	<div class="row">
	
		<!-- import navbar -->
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
					<!-- Modal -->
					<div class="modal fade" id="approveModal" tabindex="-1" role="dialog" aria-labelledby="approveModalLabel">
  						<div class="modal-dialog" role="document">
    						<div class="modal-content">
      							<div class="modal-header">
        							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        							<h4 class="modal-title" id="approveModalLabel">Approve/Reject Appointment</h4>
      							</div>
      							<div class="modal-body">
        							<p>Approve/Reject the appointment request.</p>
      							</div>
      							<div class="modal-footer">
      								<input type="hidden" name="rowId" id="rowId">
        							<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        							<button type="button" id="approve" name="approve" class="btn btn-success">Approve</button>
       								<button type="button" id="reject" name="reject" class="btn btn-danger">Reject</button>
      							</div>	
							</div>
						</div>
					</div>
					<div class="form-content">
					<c:set var="type" value="${requestScope.type }"/>
					<c:choose>
						<c:when test='${type == "list"}'>
						<form id="approveAppointmentForm" action="<%=request.getContextPath()%>/HospitalController?action=approve_appointment" method="post" role="form" class="form-horizontal">
							<table class="table table-bordered table-striped">
								<thead class="thead-inverse">
									<tr>
										<th>CHECK UP-ID</th>
										<th>HI-ID</th>
										<th>BENEFICIARY NAME</th>
										<th>AGE</th>
										<th>GENDER</th>
										<th>MOB NO.</th>
										<th>HOSPITAL NAME</th>
										<th>D.O.A<br>(dd/mm/yyyy)</th>
										<th>STATUS</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${unapprovedAppointmentList}" var="current" varStatus="loop">
										<tr data-id="rowId${loop.index +1}" id="rowId${loop.index +1}">
											<td><c:out value="${current.checkUpId}" /></td>
											<td><c:out value="${current.healthInsuranceId}" /></td>
											<td><c:out value="${current.beneficiaryName}" /></td>
											<td><c:out value="${current.age}" /></td>
											<td><c:out value="${current.gender}" /></td>
											<td><c:out value="${current.mobNo}" /></td>
											<td><c:out value="${current.hospitalName}" /></td>
											<td><c:out value="${current.appointmentDate}" /></td>
											<td>
												<input type="checkbox" name="approval" id="approval" value="${current.checkUpId}" >Approved<br>
												<input type="checkbox" name="rejection" id="rejection" value="${current.checkUpId}" >Rejected
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div class="form-group">
								<div class="pull-right col-sm-2">
									<input type="submit" name="submit" id="submit" class="form-control btn btn-primary" value="Submit">
								</div>
							</div>
						</form>
						</c:when>
						<c:when test='${type == "message"}'>
							<p style="font-size:20px"><span class="glyphicon glyphicon-exclamation-sign" style="color:red"></span><c:out value='${requestScope.message}'/></p>
						</c:when>
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