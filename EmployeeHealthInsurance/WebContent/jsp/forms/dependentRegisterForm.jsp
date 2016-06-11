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

<!-- datepicker CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.0/css/bootstrap-datepicker3.css">

<!-- custom CSS -->
<link href="<%=request.getContextPath()%>/css/dependentregister.css" rel="stylesheet" type="text/css" />

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<!-- validation -->
<script src="http://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<!-- datepicker JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.0/js/bootstrap-datepicker.js"></script>

<!-- custom JavaScript -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dependentregister.js"></script>

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
						<h3>Dependent Registration</h3>						
					</div>
					
					<div class="form-content">
					<!-- FORM -->
					<form id="dependentRegisterForm" action="<%=request.getContextPath()%>/RegisterController?action=register_dependent" method="post" role="form" class="form-horizontal" data-toggle="validator">
						<div class="form-group has-feedback">
							<label for="employeeId" class="col-sm-3 control-label">Employee ID  <span style="color:red;">*</span></label>
							<div class="col-sm-4">
								<input type="text" pattern="[0-9]{1,}" name="employeeId" id="employeeId" class="form-control" tabindex="1" placeholder="eg:- 962975" required>
								<span class="glyphicon form-control-feedback"></span>
							</div>							
						</div>
						<div class="form-group has-feedback">
							<label for="beneficiaryName" class="col-sm-3 control-label">Beneficiary Name  <span style="color:red;">*</span></label>
							<div class="col-sm-6">
								<input type="text" pattern="[A-z\s]{1,}" name="beneficiaryName" id="beneficiaryName" class="form-control" tabindex="2" placeholder="eg:- Firstname Lastname" required>
								<span class="glyphicon form-control-feedback"></span>
							</div>							
						</div>
						<div class="form-group">
  							<label for="relation"  class="col-sm-3 control-label">Relation  <span style="color:red;">*</span></label>
  							<div class="col-sm-3">
  								<select class="form-control" name="relation" id="relation" tabindex="3" required>
  									<option value="">None</option>
  									<option value="father">Father</option>
  									<option value="mother">Mother</option>
  									<option value="spouse">Spouse</option>
  									<option value="father-in-law">Father-in-Law</option>
  									<option value="mother-in-law">Mother-in-Law</option>
  								</select>
  							</div>
  						</div>
						<div class="form-group">
							<label for="dateOfBirth" class="col-sm-3 control-label">Date Of Birth  <span style="color:red;">*</span></label>
							<div class="col-sm-4"> 
								<div class="input-group date" id="datepicker">
									<input type="text" name="dateOfBirth" id="dateOfBirth" class="form-control" tabindex="4" placeholder="DD-MM-YYYY" required>
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-th"></span>
									</div>
								</div>					
							</div>		
						</div>
						<div class="form-group has-feedback">
							<label for="gender" class="col-sm-3 control-label">Gender  <span style="color:red;">*</span></label>
							<div class="col-sm-6">
								<label class="radio-inline">
									<input type="radio" name="gender" tabindex="5" value="M" required>Male
								</label>
								<label class="radio-inline">
									<input type="radio" name="gender" tabindex="6" value="F" required>Female
								</label>
							</div>							
						</div>
						<div class="form-group">
							<label for="policyStartDate" class="col-sm-3 control-label">Policy Start Date  <span style="color:red;">*</span></label>
							<div class="col-sm-4"> 
								<div class="input-group date" id="datepicker">
									<input type="text" name="policyStartDate" id="policyStartDate" class="form-control" tabindex="7" placeholder="DD-MM-YYYY" required>
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-th"></span>
									</div>
								</div>					
							</div>		
						</div>
						<div class="form-group has-feedback">
							<label for="policyPeriod" class="col-sm-3 control-label">Policy Period  <span style="color:red;">*</span></label>
							<div class="col-sm-3">
								<input type="text" pattern="[0-9]{1,2}" class="form-control" name="policyPeriod" id="policyPeriod" tabindex="8" placeholder="eg:- 1-99" required>
								<span class="glyphicon form-control-feedback"></span>
							</div>							
						</div>
						<div class="form-group has-feedback">
							<label for="totalSumInsured" class="col-sm-3 control-label">Total Sum Insured (per year)  <span style="color:red;">*</span></label>
							<div class="col-sm-4">
								<input type="text" pattern="[0-9]+[.]+[0-9]{2}" class="form-control" name="totalSumInsured" id="totalSumInsured" tabindex="9" placeholder="eg:- XXXXX.XX" required>
								<span class="glyphicon form-control-feedback"></span>
							</div>							
						</div>
						<div class="form-group">
							<label for="premiumAmount" class="col-sm-3 control-label">Premium Amount (per year)</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" name="premiumAmount" id="premiumAmount" readonly>
							</div>							
						</div>
						<div class="form-group">
							<label for="submit" class="col-sm-3 control-label">&nbsp</span></label>
							<div class="col-sm-2">
								<input type="submit" name="submit" id="submit" tabindex="10" class="form-control btn btn-primary" value="Add">
							</div>
							<div class="col-sm-2">
								<input type="submit" name="submit" id="submit" tabindex="11" class="form-control btn btn-warning" value="Update">
							</div>
							<div class="col-sm-2">
								<input type="submit" name="submit" id="submit" tabindex="12" class="form-control btn btn-danger" value="Delete">
							</div>					
						</div>
					</form>
					</div>
					<!-- END FORM -->
					
					<!-- CONTENT FOOTER -->
					<div class="content-footer">
					<div class="container">
						<div class="row">
							<div class="col-sm-6 copyright"><strong>Copyright &copy 2016.</strong> All Rights Reserved</div>
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