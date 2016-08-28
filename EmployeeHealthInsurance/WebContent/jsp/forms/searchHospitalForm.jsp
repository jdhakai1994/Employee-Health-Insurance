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

<!-- custom CSS -->
<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css" />

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<!-- validation -->
<script src="https://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<!-- custom JavaScript -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchHospital.js"></script>

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
					
					<div class="form-content">
					<!-- FORM -->
					<form id="searchHospitalFormByLocation" action="<%=request.getContextPath()%>/HospitalController?action=search_hospital" method="post" role="form" class="form-horizontal" data-toggle="validator">
						<div class="form-group">
							<label for="stateName" class="col-sm-3 control-label">Location  <span style="color:red;">*</span></label>
							<div class="col-sm-9">
							<div class="form-inline">
								<div class="form-group has-feedback col-sm-6">
									<select class="form-control" name="stateName" id="stateName" tabindex="1" required>
										<option value="">Select State</option>
										<c:forEach items="${stateList}" var="stateValue">
											<option value="${stateValue}">${stateValue}</option>
										</c:forEach>
									</select>
									<span class="glyphicon form-control-feedback"></span>
								</div>
								<div class="form-group has-feedback col-sm-6">
									<select class="form-control" name="cityName" id="cityName" tabindex="2" required>
										<option value="">Select City</option>
									</select>
									<span class="glyphicon form-control-feedback"></span>
								</div>
							</div>
							</div>
						</div>
						<div class="form-group">
							<label for="submit" class="col-sm-3 control-label"><span>&#160;</span></label>
							<div class="col-sm-3">
								<input type="hidden" name="action1" value="searchHospitalByLocation">
								<input type="submit" name="submit" id="submit" tabindex="3" class="form-control btn btn-primary" value="Search">
							</div>							
						</div>
					</form>
					<form id="searchHospitalFormByPin" action="<%=request.getContextPath()%>/HospitalController?action=search_hospital" method="post" role="form" class="form-horizontal" data-toggle="validator">
						<div class="form-group has-feedback">
							<label for="pincode" class="col-sm-3 control-label">Pin Code  <span style="color:red;">*</span></label>
							<div class="col-sm-3">
								<input type="text" pattern="[0-9]{6}" class="form-control" name="pincode" id="pincode" tabindex="4" placeholder="eg:- XXXXXX" required>		
							</div>							
						</div>
						<div class="form-group">
							<label for="submit" class="col-sm-3 control-label"><span>&#160;</span></label>
							<div class="col-sm-3">
								<input type="hidden" name="action1" value="searchHospitalByPin">
								<input type="submit" name="submit" id="submit" tabindex="5" class="form-control btn btn-primary" value="Search">
							</div>							
						</div>
					</form>
					<form id="searchHospitalFormByName" action="<%=request.getContextPath()%>/HospitalController?action=search_hospital" method="post" role="form" class="form-horizontal" data-toggle="validator">
						<div class="form-group has-feedback">
							<label for="hospitalName" class="col-sm-3 control-label">Hospital Name  <span style="color:red;">*</span></label>
							<div class="col-sm-6">
								<input type="text" pattern="[A-z\s]{1,}" name="hospitalName" id="hospitalName" class="form-control" tabindex="6" placeholder="eg:- Some Hospital" required>
								<span class="glyphicon form-control-feedback"></span>
							</div>							
						</div>
						<div class="form-group">
							<label for="submit" class="col-sm-3 control-label"><span>&#160;</span></label>
							<div class="col-sm-3">
								<input type="hidden" name="action1" value="searchHospitalByName">
								<input type="submit" name="submit" id="submit" tabindex="7" class="form-control btn btn-primary" value="Search">
							</div>							
						</div>
					</form>
					</div>
					<!-- END FORM -->				
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