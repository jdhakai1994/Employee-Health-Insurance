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
					<form id="domiciliaryClaimForm" action="" method="post" role="form" class="form-horizontal" data-toggle="validator">
						<div class="form-group">
							<label for="employeeId" class="col-sm-3 control-label">Employee ID  <span style="color:red;">*</span></label>
							<div class="col-sm-4">
								<input type="text" name="employeeId" id="employeeId" class="form-control" tabindex="1" readonly>
							</div>							
						</div>
						<div class="form-group">
  							<label for="relation"  class="col-sm-3 control-label">Relation  <span style="color:red;">*</span></label>
  							<div class="col-sm-3">
  								<select class="form-control" name="relation" id="relation" tabindex="2" required>
  									<option value="">None</option>
  									<option value="father">Father</option>
  									<option value="mother">Mother</option>
  									<option value="spouse">Spouse</option>
  									<option value="father-in-law">Father-in-Law</option>
  									<option value="mother-in-law">Mother-in-Law</option>
  								</select>
  							</div>
  						</div>
  						<div class="form-group has-feedback">
							<label for="hiId" class="col-sm-3 control-label">Health Insurance ID  <span style="color:red;">*</span></label>
							<div class="col-sm-4">
								<input type="text" pattern="[A-z0-9]{1,}" name="hiId" id="hiId" class="form-control" tabindex="3" placeholder="eg:- HI_962975" required>
								<span class="glyphicon form-control-feedback"></span>
							</div>							
						</div>
						<div class="form-group">
							<label for="mobNo" class="col-sm-3 control-label">Mobile Number  <span style="color:red;">*</span></label>
							<div class="col-sm-4">
								<input type="text" pattern="[0-9]{10}" name="mobNo" id="mobNo" class="form-control" tabindex="4" readonly>
							</div>							
						</div>
						<div class="form-group has-feedback">
							<label for="address" class="col-sm-3 control-label">Address  <span style="color:red;">*</span></label>
							<div class="col-sm-6">
								<textarea rows="4" cols="50" name="address" id="address" class="form-control" tabindex="5" placeholder="Enter Address here.." required></textarea>
								<span class="glyphicon form-control-feedback"></span>
							</div>							
						</div>
						<div class="form-group has-feedback">
							<label for="altEmailId" class="col-sm-3 control-label">Alternate Email</label>
							<div class="col-sm-6"> 
								<input type="email" name="altEmailId" id="altEmailId" class="form-control" tabindex="6" placeholder="Enter Alternate Email Address">
							</div>
						</div>
						<div class="form-group">
							<label for="startdate" class="col-sm-3 control-label">Treatment Start Date  <span style="color:red;">*</span></label>
							<div class="col-sm-4"> 
								<div class="input-group date" id="datepicker">
									<input type="text" name="startdate" id="startdate" class="form-control" tabindex="7" placeholder="DD-MM-YYYY" required>
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-th"></span>
									</div>
								</div>					
							</div>		
						</div>
						<div class="form-group">
							<label for="enddate" class="col-sm-3 control-label">Treatment End Date  <span style="color:red;">*</span></label>
							<div class="col-sm-4"> 
								<div class="input-group date" id="datepicker">
									<input type="text" name="enddate" id="enddate" class="form-control" tabindex="8" placeholder="DD-MM-YYYY" required>
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-th"></span>
									</div>
								</div>					
							</div>		
						</div>
						<div class="form-group">
							<label for="injurydate" class="col-sm-3 control-label">Date Of Injury  <span style="color:red;">*</span></label>
							<div class="col-sm-4"> 
								<div class="input-group date" id="datepicker">
									<input type="text" name="injurydate" id="injurydate" class="form-control" tabindex="9" placeholder="DD-MM-YYYY" required>
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-th"></span>
									</div>
								</div>					
							</div>		
						</div>
						<div class="form-group has-feedback">
							<label for="doctorName" class="col-sm-3 control-label">Name Of Doctor <span style="color:red;">*</span></label>
							<div class="col-sm-6">
								<input type="text" pattern="[A-z\s]{1,}" name="doctorName" id="doctorName" class="form-control" tabindex="10" placeholder="eg:- Firstname Lastname" required>
								<span class="glyphicon form-control-feedback"></span>
							</div>							
						</div>
						<div class="form-group">
  							<label for="type"  class="col-sm-3 control-label">Reason  <span style="color:red;">*</span></label>
  							<div class="col-sm-3">
  								<select class="form-control" name="type" id="type" tabindex="11" required>
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
							<label for="totalClaim" class="col-sm-3 control-label">Total Claim Amount  <span style="color:red;">*</span></label>
							<div class="col-sm-4">
								<input type="text" pattern="[0-9]+[.]+[0-9]{2}" class="form-control" name="totalClaim" id="totalClaim" tabindex="12" placeholder="eg:- XXXXX.XX" required>
								<span class="glyphicon form-control-feedback"></span>
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