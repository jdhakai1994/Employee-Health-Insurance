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

<!-- custom CSS -->
<link href="<%=request.getContextPath()%>/css/employeeregister.css" rel="stylesheet" type="text/css" />

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<!-- validation -->
<script src="http://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<!-- custom JavaScript -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/employeeregister.js"></script>

</head>

<body>
<div class="container-fluid">
	<div class="row">
	
		<!-- import navbar -->
		<c:import url="../navbars/admin_navbar.jsp" />
	
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
						<h3>Hospital Management</h3>						
					</div>
					
					<!-- FORM -->
					<div class="form-content">
						<form id="addHospitalForm" action="<%=request.getContextPath()%>/HospitalController?action=add_hospital" method="post" role="form" class="form-horizontal" data-toggle="validator">
							<div class="form-group">
								<label for="hospitalId" class="col-sm-3 control-label">Hospital ID  <span style="color:red;">*</span></label>
								<div class="col-sm-4">
									<input type="text" name="hospitalId" id="hospitalId" class="form-control" value='<c:out value='${requestScope.hospitalId + 1}'></c:out>' readonly>
								</div>							
							</div>
							<div class="form-group has-feedback">
								<label for="hospitalName" class="col-sm-3 control-label">Hospital Name  <span style="color:red;">*</span></label>
								<div class="col-sm-6">
									<input type="text" pattern="[A-z\s]{1,}" name="hospitalName" id="hospitalName" class="form-control" tabindex="1" placeholder="eg:- Some Hospital" required>
									<span class="glyphicon form-control-feedback"></span>
								</div>							
							</div>
							<div class="form-group has-feedback">
								<label for="address" class="col-sm-3 control-label">Address  <span style="color:red;">*</span></label>
								<div class="col-sm-6">
									<textarea rows="4" cols="50" name="address" id="address" class="form-control" tabindex="2" placeholder="Enter Address here.." required></textarea>
									<span class="glyphicon form-control-feedback"></span>
								</div>							
							</div>
							<div class="form-group has-feedback">
								<label for="cityName" class="col-sm-3 control-label">City Name  <span style="color:red;">*</span></label>
								<div class="col-sm-4">
									<input type="text" pattern="[A-z]{1,}" name="cityName" id="cityName" class="form-control" tabindex="3" placeholder="eg:- Kolkata" required>
									<span class="glyphicon form-control-feedback"></span>
								</div>							
							</div>
							<div class="form-group has-feedback">
								<label for="stateName" class="col-sm-3 control-label">State Name  <span style="color:red;">*</span></label>
								<div class="col-sm-4">
									<select class="form-control" name="stateName" id="stateName" tabindex="4" required>
										<option value="">Select State</option>
										<c:forEach items="${stateList}" var="stateValue">
											<option value="${stateValue}">${stateValue}</option>
										</c:forEach>
									</select>
									<span class="glyphicon form-control-feedback"></span>
								</div>				
							</div>
							<div class="form-group has-feedback">
								<label for="pincode" class="col-sm-3 control-label">Pin Code  <span style="color:red;">*</span></label>
								<div class="col-sm-3">
									<input type="text" pattern="[0-9]{6}" class="form-control" name="pincode" id="pincode" tabindex="5" placeholder="eg:- XXXXXX" required>
								</div>							
							</div>
							<div class="form-group has-feedback">
								<label for="stdcode" class="col-sm-3 control-label">STD Code  <span style="color:red;">*</span></label>
								<div class="col-sm-3">
									<input type="text" pattern="[0-9-]{3,6}" class="form-control" name="stdcode" id="stdcode" tabindex="6" placeholder="eg:- XXXXXX" required>
								</div>							
							</div>
							<div class="form-group has-feedback">
								<label for="phNo" class="col-sm-3 control-label">Phone Number  <span style="color:red;">*</span></label>
								<div class="col-sm-4">
									<input type="text" pattern="[0-9]{8}" name="phNo" id="phNo" class="form-control" tabindex="7" placeholder="eg:- XXXXXXXX" required>
									<span class="glyphicon form-control-feedback"></span>
								</div>							
							</div>
							<div class="form-group">
								<label for="submit" class="col-sm-3 control-label">&nbsp</span></label>
								<div class="col-sm-3">
									<input type="hidden" name="action" value="add_hospital">
									<input type="submit" name="submit" id="submit" tabindex="8" class="form-control btn btn-primary" value="Add">
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