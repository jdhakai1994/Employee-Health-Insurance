<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- <title>Health Insurance Management</title> -->
<!-- keep these commented, importing twice doesn't work -->
<!-- Latest compiled and minified CSS -->
<!--  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"> -->
<!-- jQuery library -->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script> -->
<!-- Latest compiled JavaScript -->
<!-- <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script> -->

<!-- custom CSS -->
<link href="<%=request.getContextPath()%>/css/navbar.css" rel="stylesheet" type="text/css" />

</head>

<body>
	<!-- SIDEBAR -->
	<div class="col-sm-3 no-gutter">
		<div class="nav-side-menu">
			<div class="brand">Health Insurance Company</div>
								
			<div class="profile-userpic">
				<img src="" class="img-responsive" alt="">
			</div>
				
			<div class="profile-usertitle">
				<div class="profile-usertitle-name">
					<c:out value="${sessionScope.username}" />
				</div>
				<div class="profile-usertitle-role">
					Member
				</div>
			</div>
				
			<div class="profile-userbuttons">	
				<form id="logoutForm" action="<%=request.getContextPath()%>/LoginController?action=logout" method="post" role="form">
					<div class="form-group">
						<input type="submit" name="submit" id="submit" class="btn btn-success btn-md" value="Log Out">
					</div>
				</form>
			</div>
			<hr>
			<div class="menu-list">
  	            <ul id="menu-content" class="menu-content">
       		        <li data-toggle="collapse" data-target="#registration">
    	       			<a href="#"><span class="glyphicon glyphicon-user"></span>  Registration Management</a>
               		</li>
               		<ul class="sub-menu collapse in" id="registration">
                   		<li><a href="<%=request.getContextPath()%>/RegisterController?action=getRegisterEmployeeForm">Employee Registration</a></li>
                   		<li><a href="<%=request.getContextPath()%>/RegisterController?action=getDependentEmployeeForm">Dependent Registration</a></li>
                   		<li><a href="<%=request.getContextPath()%>/RegisterController?action=getECardForm">Generate E-Card</a></li>
                   	</ul>
                   	<li data-toggle="collapse" data-target="#claims">
               			<a href="#"><span class="glyphicon glyphicon-usd"></span>  Claims Management</a>
               		</li>
               		<ul class="sub-menu collapse in" id="claims">
                   		<li><a href="<%=request.getContextPath()%>/ClaimsController?action=getDomiciliaryClaimForm">Domiciliary Claims</a></li>
                   		<li><a href="<%=request.getContextPath()%>/ClaimsController?action=getHospitalizationClaimForm">Hospitalization Claims</a></li>
                   		<li><a href="<%=request.getContextPath()%>/ClaimsController?action=getSearchClaimForm">Claims Search</a></li>
                   	</ul>
                   	<li  data-toggle="collapse" data-target="#hospitals">
               			<a href="#"><span class="glyphicon glyphicon-home"></span>  Hospital Management</a>
               		</li>
               		<ul class="sub-menu collapse in" id="hospitals">
                   		<li><a href="<%=request.getContextPath()%>/HospitalController?action=getSearchHospitalForm">Search Hospital</a></li>
                   		<li><a href="<%=request.getContextPath()%>/HospitalController?action=getValueAddedServicesForm">Value Added Services</a></li>
                   	</ul>
				</ul>
                    
			</div>
       	</div>
	</div>
	<!-- END SIDEBAR -->
</body>
</html>