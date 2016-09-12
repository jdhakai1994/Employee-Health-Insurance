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

<!-- datepicker CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.0/css/bootstrap-datepicker3.css">

<!-- custom CSS -->
<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css" />

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<!-- validation -->
<script src="https://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<!-- datepicker JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.0/js/bootstrap-datepicker.js"></script>

<!-- custom JavaScript -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/valueAddedServices.js"></script>

</head>

<body>
<div class="container-fluid">
	<div class="row">
	
		<!-- import navbar -->
		<c:import url="../navbars/member_navbar.jsp" />
	
		<!-- CONTENT -->
		<div class="col-sm-9 no-gutter">
			<div class="content">
				
				<!-- CONTENT HEADER -->
				<div class="content-header">
					<span class="glyphicon glyphicon-align-justify"></span>
				</div>
				<!-- CONTENT HEADER -->
				
				<!-- CONTENT BODY -->
				<div class="content-body">
					<div>
						<h3><c:out value='${requestScope.heading}'/></h3>						
					</div>
					
					<!-- FORM -->
					<div class="form-content">
					<c:set var="isEnrolled" value="${sessionScope.enrolled }"/>
						<c:choose>
							<c:when test='${isEnrolled.equals("no")}'>
								<p style="font-size:20px"><span class="glyphicon glyphicon-exclamation-sign" style="color:red"></span> Your employee registration must be approved before you can avail any services, please click on Employee Registration on the left panel.</p>
							</c:when>
							<c:otherwise>
								<form id="valueAddedServicesForm" action="<%=request.getContextPath()%>/HospitalController?action=value_added_services" method="post" role="form" class="form-horizontal" data-toggle="validator">
								<div class="form-group">
									<label for="employeeId" class="col-sm-3 control-label">Employee ID  <span style="color:red;">*</span></label>
									<div class="col-sm-4">
										<input type="text" name="employeeId" id="employeeId" class="form-control" value='<c:out value='${requestScope.employeeId}'></c:out>' readonly>
									</div>							
								</div>
								<div class="form-group">
									<label for="employeeName" class="col-sm-3 control-label">Employee Name  <span style="color:red;">*</span></label>
									<div class="col-sm-6">
										<input type="text" name="employeeName" id="employeeName" class="form-control" value='<c:out value='${requestScope.employeeName}'></c:out>' readonly>
									</div>							
								</div>
								<div class="form-group">
									<label for="mobNo" class="col-sm-3 control-label">Mobile Number  <span style="color:red;">*</span></label>
									<div class="col-sm-4">
										<input type="text" name="mobNo" id="mobNo" class="form-control" value='<c:out value='${requestScope.mobNo}'></c:out>' readonly>
									</div>							
								</div>
								<div class="form-group">
									<label for="email" class="col-sm-3 control-label">Email  <span style="color:red;">*</span></label>
									<div class="col-sm-6"> 
										<input type="email" name="email" id="email" class="form-control" value='<c:out value='${requestScope.emailId}'></c:out>' readonly>	
									</div>
								</div>
								<div class="form-group">
  									<label for="beneficiaryName"  class="col-sm-3 control-label">Name Of Beneficiary  <span style="color:red;">*</span></label>
  									<div class="col-sm-3">
  										<select class="form-control" name="beneficiaryName" id="beneficiaryName" tabindex="1" required>
  											<option value="">Select Beneficiary</option>
  											<c:forEach items="${beneficiaryNameList}" var="value">
  												<option value="${value}">${value}</option>
											</c:forEach>
  										</select>
	  								</div>
  								</div>
  								<div class="form-group has-feedback">
									<label for="healthInsuranceId" class="col-sm-3 control-label">Health Insurance ID  <span style="color:red;">*</span></label>
									<div class="col-sm-4">
										<input type="text" name="healthInsuranceId" id="healthInsuranceId" class="form-control" readonly>
										<span class="glyphicon form-control-feedback"></span>
									</div>							
								</div>
								<div class="form-group has-feedback">
									<label for="gender" class="col-sm-3 control-label">Gender  <span style="color:red;">*</span></label>
									<div class="col-sm-6">
										<label class="radio-inline">
											<input type="radio" name="gender" id="gender1" tabindex="2" value="M" required>Male
										</label>
										<label class="radio-inline">
											<input type="radio" name="gender" id="gender2" tabindex="3" value="F" required>Female
										</label>
									</div>							
								</div>
								<div class="form-group has-feedback">
									<label for="age" class="col-sm-3 control-label">Age  <span style="color:red;">*</span></label>
									<div class="col-sm-2">
										<input type="text" pattern="[0-9]{1,2}" name="age" id="age" class="form-control" tabindex="4" placeholder="eg 1-99" required>
										<span class="glyphicon form-control-feedback"></span>
									</div>							
								</div>
								<div class="form-group has-feedback">
									<label for="stateName" class="col-sm-3 control-label">State  <span style="color:red;">*</span></label>
									<div class="col-sm-4">
										<select class="form-control" name="stateName" id="stateName" tabindex="5" required>
											<option value="">Select State</option>
												<c:forEach items="${stateList}" var="stateValue">
													<option value="${stateValue}">${stateValue}</option>
												</c:forEach>
										</select>
										<span class="glyphicon form-control-feedback"></span>
									</div>							
								</div>
  								<div class="form-group has-feedback">
									<label for="cityName" class="col-sm-3 control-label">City  <span style="color:red;">*</span></label>
									<div class="col-sm-4">
										<select class="form-control" name="cityName" id="cityName" tabindex="6" required>
											<option value="">Select City</option>
										</select>
										<span class="glyphicon form-control-feedback"></span>
									</div>							
								</div>
								<div class="form-group has-feedback">
									<label for="hospitalName" class="col-sm-3 control-label">Hospital Name  <span style="color:red;">*</span></label>
									<div class="col-sm-6">
										<select class="form-control" name="hospitalName" id="hospitalName" tabindex="7" required>
											<option value="">Select Hospital</option>
										</select>
										<span class="glyphicon form-control-feedback"></span>
									</div>							
								</div>
								<div class="form-group">
									<label for="appointmentDate" class="col-sm-3 control-label">Date Of Appointment  <span style="color:red;">*</span></label>
									<div class="col-sm-4"> 
										<div class="input-group date" id="datepicker">
											<input type="text" name="appointmentDate" id="appointmentDate" class="form-control" tabindex="8" placeholder="DD-MM-YYYY" required>
											<div class="input-group-addon">
												<span class="glyphicon glyphicon-th"></span>
											</div>
										</div>					
									</div>		
								</div>							
								<div class="form-group">
									<label for="submit" class="col-sm-3 control-label"><span>&#160;</span></label>
									<div class="col-sm-3">
										<input type="submit" name="submit" id="submit" tabindex="9" class="form-control btn btn-primary" value="Submit">
									</div>							
								</div>
							</form>
							<!-- END FORM -->
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<!-- END CONTENT BODY -->
				<!-- CONTENT FOOTER -->
				<div class="content-footer">
					<div class="container">
						<div class="row">
							<div class="col-sm-6 copyright"><strong>Copyright &#169; 2016.</strong> All Rights Reserved</div>
							<div class="col-sm-6 version"><strong>Version</strong> 1.0</div>
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