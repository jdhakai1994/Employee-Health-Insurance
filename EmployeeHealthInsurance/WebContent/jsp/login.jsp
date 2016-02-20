<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Employee Health Insurance</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- bootstrap -->
<link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />

<!-- custom CSS -->
<link href="<%=request.getContextPath()%>/css/login.css" rel="stylesheet" type="text/css" />

<!-- jquery -->
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>

<!-- validation -->
<script src="http://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>

<!-- bootstrap js-->
<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>

<!-- custom js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/login.js"></script>
</head>
<body>
	<div class="container">
    	<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
				
					<!-- heading -->
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
					
					<!-- body -->
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
							
							<!-- login form -->
								<form id="login-form" action="<%=request.getContextPath()%>/LoginServlet" method="post" role="form" data-toggle="validator" style="display: block;">
									<div class="form-group">
										<input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Username" value="" required>
									</div>
									<div class="form-group">
										<input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password" required>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="hidden" name="action" value="login">
												<input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Log In">
											</div>
										</div>
									</div>
								</form>
								
								<!-- register form -->
								<form id="register-form" action="<%=request.getContextPath()%>/LoginServlet" method="post" role="form" data-toggle="validator" style="display: none;">
									<div class="form-group">
										<input type="text" name="username" id="username" tabindex="1" class="form-control" data-minlength="6" placeholder="Username" value="" required>
										<span class="help-block">Minimum of 6 characters</span>
									</div>
									<div class="form-group">
										<input type="password" name="password" id="password" tabindex="2" class="form-control" data-minlength="8" placeholder="Password" required>
										<span class="help-block">Minimum of 8 characters</span>
									</div>
									<div class="form-group">
										<input type="password" name="confirm-password" id="confirm-password" tabindex="2" class="form-control" data-match="#password" data-match-error="Passwords don't match" placeholder="Confirm Password" required>
										<div class="help-block with-errors"></div>
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
								
							</div>
						</div>
					</div>
					<!-- end body -->
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>