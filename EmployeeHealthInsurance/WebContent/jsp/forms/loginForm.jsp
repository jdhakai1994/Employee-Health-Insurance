<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Health Insurance Management</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<!-- custom CSS -->
<link href="<%=request.getContextPath()%>/css/login.css" rel="stylesheet" type="text/css" />

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<!-- validation -->
<script src="https://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<!-- custom JavaScript -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/login.js"></script>

</head>
<body>
	<div class="container">
    	<div class="row">
    		<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
				
					<!-- HEADING -->
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">
								<a href="#" class="active" id="login-form-link">Login</a>
							</div>
							<div class="col-xs-6">
								<a href="#" id="register-form-link">Register</a>
							</div>
						</div>
						<hr>
					</div>
					<!-- END HEADING -->
					
					<!-- ERROR MESSAGE -->
					<div>
						<p style="color:red;text-align:center;"><c:out value="${requestScope.message}"></c:out></p>
					</div>
					<!-- END ERROR MESSAGE -->
					
					<!-- BODY -->
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
							
								<!-- LOGIN FORM -->
								<form id="loginForm" action="<%=request.getContextPath()%>/LoginController?action=login" method="post" role="form" data-toggle="validator" style="display: block;">
									<div class="form-group">
										<input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Enter Username" value="" required>
									</div>
									<div class="form-group">
										<input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Enter Password" required>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="hidden" name="action" value="login">
												<c:set var="now" value="<%=System.currentTimeMillis()%>"/>
												<input type="hidden" name="logon" value="${now}">
												<input type="submit" name="login-submit" id="login-submit" tabindex="3" class="form-control btn btn-login" value="Log In">
											</div>
										</div>
									</div>
								</form>
								<!-- END LOGIN FORM-->
								
								<!-- REGISTER FORM -->
								<form id="registerForm" action="<%=request.getContextPath()%>/LoginController?action=register" method="post" role="form" data-toggle="validator" style="display: none;">
									<div class="form-group">
										<input type="text" name="username" id="username" tabindex="1" class="form-control" data-minlength="6" placeholder="Enter Username" value="" required>
										<span class="help-block">Minimum of 6 characters</span>
									</div>
									<div class="form-group">
										<div class="form-inline row">
											<div class="form-group col-sm-6">
												<input type="password" name="password" id="inputpassword" tabindex="2" class="form-control" data-minlength="8" placeholder="Enter Password" required>
												<span class="help-block">Minimum of 8 characters</span>
											</div>
											<div class="form-group col-sm-6">
												<input type="password" name="confirm-password" id="confirm-password" tabindex="3" class="form-control" data-match="#inputpassword" data-match-error="Passwords don't match" placeholder="Confirm Password" required>
												<div class="help-block with-errors"></div>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="hidden" name="action" value="register">
												<input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Register Now">
											</div>
										</div>
									</div>
								</form>
								<!-- END REGISTER FORM -->
								
							</div>
						</div>
					</div>
					<!-- END BODY -->
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>