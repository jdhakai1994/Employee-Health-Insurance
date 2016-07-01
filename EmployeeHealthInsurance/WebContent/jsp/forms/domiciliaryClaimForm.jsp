<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

<!-- datepicker CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.0/css/bootstrap-datepicker3.css">

<!-- custom CSS -->
<link href="<%=request.getContextPath()%>/css/employeeregister.css" rel="stylesheet" type="text/css" />

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<!-- validation -->
<script src="https://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<!-- datepicker JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.0/js/bootstrap-datepicker.js"></script>

<!-- custom JavaScript -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/domiciliaryClaim.js"></script>

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
						<h3>Domiciliary Claim</h3>						
					</div>
					
					<div class="form-content">
					<!-- FORM -->
					<form id="domiciliaryClaimForm" action="<%=request.getContextPath()%>/ClaimsController?action=domiciliaryClaim" method="post" role="form" class="form-horizontal" data-toggle="validator">
						<div class="form-group">
							<label for="employeeId" class="col-sm-3 control-label">Employee ID  <span style="color:red;">*</span></label>
							<div class="col-sm-4">
								<input type="text" name="employeeId" id="employeeId" class="form-control" value='<c:out value='${requestScope.employeeId}'></c:out>' readonly>
							</div>							
						</div>
						<div class="form-group">
  							<label for="beneficiaryName"  class="col-sm-3 control-label">Beneficiary Name  <span style="color:red;">*</span></label>
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
						<div class="form-group">
							<label for="mobNo" class="col-sm-3 control-label">Mobile Number  <span style="color:red;">*</span></label>
							<div class="col-sm-4">
								<input type="text" pattern="[0-9]{10}" name="mobNo" id="mobNo" class="form-control" tabindex="2" value='<c:out value='${requestScope.mobNo}'></c:out>' readonly>
							</div>							
						</div>
						<div class="form-group has-feedback">
							<label for="address" class="col-sm-3 control-label">Address  <span style="color:red;">*</span></label>
							<div class="col-sm-6">
								<textarea rows="4" cols="50" name="address" id="address" class="form-control" tabindex="3" placeholder="Enter Address here.." required></textarea>
								<span class="glyphicon form-control-feedback"></span>
							</div>							
						</div>
						<div class="form-group has-feedback">
							<label for="altEmailId" class="col-sm-3 control-label">Alternate Email</label>
							<div class="col-sm-6"> 
								<input type="email" name="altEmailId" id="altEmailId" class="form-control" tabindex="4" value='<c:out value='${requestScope.altEmailId}'></c:out>' placeholder="Enter Alternate Email Address">
							</div>
						</div>
						<div class="form-group">
							<label for="treatmentStartDate" class="col-sm-3 control-label">Treatment Start Date  <span style="color:red;">*</span></label>
							<div class="col-sm-4"> 
								<div class="input-group date" id="datepicker">
									<input type="text" name="treatmentStartDate" id="treatmentStartDate" class="form-control" tabindex="5" placeholder="DD-MM-YYYY" required>
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-th"></span>
									</div>
								</div>					
							</div>		
						</div>
						<div class="form-group">
							<label for="treatmentEndDate" class="col-sm-3 control-label">Treatment End Date  <span style="color:red;">*</span></label>
							<div class="col-sm-4"> 
								<div class="input-group date" id="datepicker">
									<input type="text" name="treatmentEndDate" id="treatmentEndDate" class="form-control" tabindex="6" placeholder="DD-MM-YYYY" required>
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-th"></span>
									</div>
								</div>					
							</div>		
						</div>
						<div class="form-group">
							<label for="dateOfInjury" class="col-sm-3 control-label">Date Of Injury  <span style="color:red;">*</span></label>
							<div class="col-sm-4"> 
								<div class="input-group date" id="datepicker">
									<input type="text" name="dateOfInjury" id="dateOfInjury" class="form-control" tabindex="7" placeholder="DD-MM-YYYY" required>
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-th"></span>
									</div>
								</div>					
							</div>		
						</div>
						<div class="form-group has-feedback">
							<label for="doctorName" class="col-sm-3 control-label">Name Of Doctor <span style="color:red;">*</span></label>
							<div class="col-sm-6">
								<input type="text" pattern="[A-z\s]{1,}" name="doctorName" id="doctorName" class="form-control" tabindex="8" placeholder="eg:- Firstname Lastname" required>
								<span class="glyphicon form-control-feedback"></span>
							</div>							
						</div>
						<div class="form-group">
  							<label for="typeOfInjury"  class="col-sm-3 control-label">Reason  <span style="color:red;">*</span></label>
  							<div class="col-sm-3">
  								<select class="form-control" name="typeOfInjury" id="typeOfInjury" tabindex="9" required>
  									<option value="">None</option>
  									<option value="ailment">Ailment</option>
  									<option value="disease">Disease</option>
  									<option value="injury">Injury</option>
  									<option value="contracted">Contracted</option>
  									<option value="substain">Sub Stain</option>
  								</select>
  							</div>
  						</div>
						<div class="form-group has-feedback">
							<label for="totalClaimAmount" class="col-sm-3 control-label">Total Claim Amount  <span style="color:red;">*</span></label>
							<div class="col-sm-4">
								<input type="text" pattern="[0-9]+[.]+[0-9]{2}" class="form-control" name="totalClaimAmount" id="totalClaimAmount" tabindex="10" placeholder="eg:- XXXXX.XX" required>
								<span class="glyphicon form-control-feedback"></span>
							</div>							
						</div>
						<div class="form-group">
							<label for="submit" class="col-sm-3 control-label"><span>&#160;</span></label>
							<div class="col-sm-3">
								<input type="hidden" name="action" value="domiciliaryClaim">
								<input type="submit" name="submit" id="submit" tabindex="11" class="form-control btn btn-primary" value="Submit">
							</div>							
						</div>
					</form>
					</div>
					<!-- END FORM -->
					
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
				<!-- END CONTENT BODY -->
						
			</div>
		</div>
		<!-- END CONTENT -->
		
	</div>
</div>
</body>
</html>