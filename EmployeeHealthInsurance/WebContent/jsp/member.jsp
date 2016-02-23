<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
<!-- bootstrap -->
<link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />

<!-- custom CSS -->
<link href="<%=request.getContextPath()%>/css/member.css" rel="stylesheet" type="text/css" />

<!-- jquery -->
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>

<!-- validation -->
<script src="http://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>

<!-- bootstrap js-->
<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>

<!-- custom js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/member.js"></script>
</head>
<body>
<div class="container-fluid">
	<div class="row">
	
		<!-- SIDEBAR -->
		<div class="col-sm-3 no-gutter">
			<div class="nav-side-menu">
				<div class="brand">Health Insurance Company</div>
												
				<div class="profile-userpic">
					<img src="" class="img-responsive" alt="">
				</div>
				
				<div class="profile-usertitle">
					<div class="profile-usertitle-name">
						Marcus Doe
					</div>
					<div class="profile-usertitle-role">
						Member
					</div>
				</div>
				
				<div class="profile-userbuttons">
					<button type="button" class="btn btn-success btn-sm">Log Out</button>
				</div>
				<hr>
				<div class="menu-list">
  		            <ul id="menu-content" class="menu-content">
        		        <li  data-toggle="collapse" data-target="#registration">
                  			<a href="#"><span class="glyphicon glyphicon-user"></span>  Registration Management</a>
                		</li>
                		<ul class="sub-menu collapse in" id="registration">
                    		<li><a href="#">Employee Registration</a></li>
                    		<li><a href="#">Dependent Registration</a></li>
                    		<li><a href="#">Generate E-Card</a></li>
                    	</ul>
                    	<li  data-toggle="collapse" data-target="#claims">
                  			<a href="#"><span class="glyphicon glyphicon-usd"></span>  Claims Management</a>
                		</li>
                		<ul class="sub-menu collapse in" id="claims">
                    		<li><a href="#">Domiciliary Claims</a></li>
                    		<li><a href="#">Hospitalization Claims</a></li>
                    		<li><a href="#">Claims Search</a></li>
                    	</ul>
                    	<li  data-toggle="collapse" data-target="#hospitals">
                  			<a href="#"><span class="glyphicon glyphicon-home"></span>  Hospital Management</a>
                		</li>
                		<ul class="sub-menu collapse in" id="hospitals">
                    		<li><a href="#">Search Hospital</a></li>
                    		<li><a href="#">Value Added Services</a></li>
                    	</ul>
                    </ul>
                    
				</div>
         	</div>
		</div>
		<!-- END SIDEBAR -->
		
		<!-- CONTENT -->
		<div class="col-sm-9 no-gutter">
			<div class="content">
				<div class="content-header">
					<span class="glyphicon glyphicon-align-justify"></span>
				</div>
				<div class="content-body">
					Hello
				</div>
				<div class="content-footer">
					<div class="container">
						<div class="row">
							<div class="col-sm-6 copyright"><strong>Copyright &copy 2016.</strong> All Rights Reserved</div>
							<div class="col-sm-6 version"><strong>Version</strong> 1.0</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- END CONTENT -->
		
	</div>
</div>
</body>
</html>