<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link href="<%=request.getContextPath()%>/css/employeeregister.css" rel="stylesheet" type="text/css" />

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<!-- validation -->
<script src="http://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<!-- datepicker JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.0/js/bootstrap-datepicker.js"></script>

<!-- custom JavaScript -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/employeeregister.js"></script>

</head>

<body>
<div class="container-fluid">
	<div class="row">
	
		<!-- import navbar -->
		<c:import url="member_navbar.jsp" />
	
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
						<h3>Employee Registration</h3>						
					</div>
					
					<div class="form-content">
					<!-- FORM -->
					<form id="employeeregister" action="" method="post" role="form" class="form-horizontal" data-toggle="validator">
						<div class="form-group has-feedback">
							<label for="employeeId" class="col-sm-3 control-label">Employee ID  <span style="color:red;">*</span></label>
							<div class="col-sm-4">
								<input type="text" pattern="[0-9]{1,}" name="employeeId" id="employeeId" class="form-control" tabindex="1" placeholder="eg:- 962975" required>
								<span class="glyphicon form-control-feedback"></span>
							</div>							
						</div>
						<div class="form-group has-feedback">
							<label for="employeeName" class="col-sm-3 control-label">Employee Name  <span style="color:red;">*</span></label>
							<div class="col-sm-6">
								<input type="text" pattern="[A-z\s]{1,}" name="employeeName" id="employeeName" class="form-control" tabindex="2" placeholder="eg:- Firstname Lastname" required>
								<span class="glyphicon form-control-feedback"></span>
							</div>							
						</div>
						<div class="form-group">
							<label for="dateOfBirth" class="col-sm-3 control-label">Date Of Birth  <span style="color:red;">*</span></label>
							<div class="col-sm-4"> 
								<div class="input-group date" id="datepicker">
									<input type="text" name="dateOfBirth" id="dateOfBirth" class="form-control" tabindex="3" placeholder="DD-MM-YYYY" required>
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
									<input type="radio" name="gender" tabindex="4" value="M" required>Male
								</label>
								<label class="radio-inline">
									<input type="radio" name="gender" tabindex="5" value="F" required>Female
								</label>
							</div>							
						</div>
						<div class="form-group">
							<label for="email" class="col-sm-3 control-label">Email  <span style="color:red;">*</span></label>
							<div class="col-sm-6"> 
								<input type="email"  name="email" id="email" class="form-control" tabindex="6" placeholder="eg:- someone@example.com" required>
								<span class="glyphicon form-control-feedback"></span>				
							</div>
						</div>
						<div class="form-group has-feedback">
							<label for="altEmailId" class="col-sm-3 control-label">Alternate Email</label>
							<div class="col-sm-6"> 
								<input type="email" name="altEmailId" id="altEmailId" class="form-control" tabindex="7" placeholder="Enter Alternate Email Address">
							</div>
						</div>
						<div class="form-group has-feedback">
							<label for="mobNo" class="col-sm-3 control-label">Mobile Number  <span style="color:red;">*</span></label>
							<div class="col-sm-4">
								<input type="text" pattern="[0-9]{10}" name="mobNo" id="mobNo" class="form-control" tabindex="8" placeholder="eg:- XXXXXXXXXX" required>
								<span class="glyphicon form-control-feedback"></span>
							</div>							
						</div>
						<div class="form-group">
							<label for="altMobNo" class="col-sm-3 control-label">Alternate Mobile Number</label>
							<div class="col-sm-4">
								<input type="text" pattern="[0-9]{10}" name="altMobNo" id="altMobNo" class="form-control" tabindex="9" placeholder="Enter Alternate Mobile Number">
							</div>							
						</div>
						<div class="form-group">
							<label for="policyStartDate" class="col-sm-3 control-label">Policy Start Date  <span style="color:red;">*</span></label>
							<div class="col-sm-4"> 
								<div class="input-group date" id="datepicker">
									<input type="text" name="policyStartDate" id="policyStartDate" class="form-control" tabindex="10" placeholder="DD-MM-YYYY" required>
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-th"></span>
									</div>
								</div>					
							</div>		
						</div>
						<div class="form-group has-feedback">
							<label for="policyPeriod" class="col-sm-3 control-label">Policy Period  <span style="color:red;">*</span></label>
							<div class="col-sm-3">
								<input type="text" pattern="[0-9]{1,2}" class="form-control" name="policyPeriod" id="policyPeriod" tabindex="11" placeholder="eg:- 1-99" required>
								<span class="glyphicon form-control-feedback"></span>
							</div>							
						</div>
						<div class="form-group has-feedback">
							<label for="totalSumInsured" class="col-sm-3 control-label">Total Sum Insured (per year)  <span style="color:red;">*</span></label>
							<div class="col-sm-4">
								<input type="text" pattern="[0-9]+[.]+[0-9]{2}" class="form-control" name="totalSumInsured" id="totalSumInsured" tabindex="12" placeholder="eg:- XXXXX.XX" required>
								<span class="glyphicon form-control-feedback"></span>
							</div>							
						</div>
						<div class="form-group">
							<label for="premiumAmount" class="col-sm-3 control-label">Premium Amount (per year)</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" name="premiumAmount" id="premiumAmount" readonly>
							</div>							
						</div>
						<div class="form-group has-feedback">
							<label for="accountNo" class="col-sm-3 control-label">Account Number  <span style="color:red;">*</span></label>
							<div class="col-sm-4">
								<input type="text" pattern="[0-9]" class="form-control" name="accountNo" id="accountNo" tabindex="13" placeholder="eg:- XXXXXXXXXXXXXXXXX" required>
							</div>							
						</div>
						<div class="form-group has-feedback">
							<label for="bankName" class="col-sm-3 control-label">Bank Name  <span style="color:red;">*</span></label>
							<div class="col-sm-6">
								<input type="text" pattern="[A-z]" class="form-control" name="bankName" id="bankName" tabindex="14" placeholder="eg: Axis Bank Of India" required>
							</div>							
						</div>
						<div class="form-group has-feedback">
							<label for="ifscCode" class="col-sm-3 control-label">IFSC Code  <span style="color:red;">*</span></label>
							<div class="col-sm-4">
								<input type="text" pattern="[A-Z0-9]" class="form-control" name="ifscCode" id="ifscCode" tabindex="15" placeholder="Enter IFSC Code" required>
							</div>							
						</div>
						<div class="form-group">
							<label for="submit" class="col-sm-3 control-label">&nbsp</span></label>
							<div class="col-sm-3">
								<input type="submit" name="submit" id="submit" tabindex="16" class="form-control btn btn-primary" value="Submit">
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