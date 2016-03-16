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
						<h3>Hospital Management</h3>						
					</div>
					
					<div class="form-content">
					<!-- FORM -->
					<form id="searchhospital" action="" method="post" role="form" class="form-horizontal" data-toggle="validator">
						<div class="form-group">
							<label for="stateName" class="col-sm-3 control-label">Location  <span style="color:red;">*</span></label>
							<div class="col-sm-9">
							<div class="form-inline">
								<div class="form-group has-feedback col-sm-6">
									<select class="form-control" name="stateName" id="stateName" tabindex="1">
										<option value="">Select State</option>
										<c:forEach items="${stateList}" var="stateValue">
											<option value="${stateValue}">${stateValue}</option>
										</c:forEach>
									</select>
									<span class="glyphicon form-control-feedback"></span>
								</div>
								<div class="form-group has-feedback col-sm-6">
									<select class="form-control" name="stateName" id="stateName" tabindex="2">
										<option value="">Select City</option>
											<c:forEach items="${stateList}" var="stateValue">
												<option value="${stateValue}">${stateValue}</option>
											</c:forEach>
									</select>
									<span class="glyphicon form-control-feedback"></span>
								</div>
							</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">&nbsp</span></label>
							<div class="col-sm-3">
								<p>OR</p>
							</div>							
						</div>
						<div class="form-group has-feedback">
							<label for="pincode" class="col-sm-3 control-label">Pin Code  <span style="color:red;">*</span></label>
							<div class="col-sm-3">
								<input type="text" pattern="[0-9]{6}" class="form-control" name="pincode" id="pincode" tabindex="3" placeholder="eg:- XXXXXX">							</div>							
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">&nbsp</span></label>
							<div class="col-sm-3">
								<p>OR</p>
							</div>							
						</div>
						<div class="form-group has-feedback">
							<label for="hospitalName" class="col-sm-3 control-label">Hospital Name  <span style="color:red;">*</span></label>
							<div class="col-sm-6">
								<input type="text" pattern="[A-z\s]{1,}" name="hospitalName" id="hospitalName" class="form-control" tabindex="4" placeholder="eg:- Some Hospital">
								<span class="glyphicon form-control-feedback"></span>
							</div>							
						</div>
						<div class="form-group">
							<label for="submit" class="col-sm-3 control-label">&nbsp</span></label>
							<div class="col-sm-3">
								<input type="submit" name="submit" id="submit" tabindex="2" class="form-control btn btn-primary" value="Submit">
							</div>							
						</div>
					</form>
					</div>
					<!-- END FORM -->
					 
					<!-- CONTENT FOOTER -->
					<!--
					<div class="content-footer">
					<div class="container">
						<div class="row">
							<div class="col-sm-6 copyright"><strong>Copyright &copy 2016.</strong> All Rights Reserved</div>
							<div class="col-sm-6 version"><strong>Version</strong> 1.0</div>
						</div>
					</div>
				</div>
				-->
				<!-- END CONTENT FOOTER -->
				
				</div>
				<!-- END CONTENT BODY -->
				
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
		</div>
		<!-- END CONTENT -->
		
	</div>
</div>
</body>
</html>