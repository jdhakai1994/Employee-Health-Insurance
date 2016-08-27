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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>

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
						<!-- END ERROR MESSAGE -->
						<form id="searchHospitalFormByHID" action="<%=request.getContextPath()%>/HospitalController?action=search_hospital" method="post" role="form" class="form-horizontal" data-toggle="validator">
							<div class="form-group">
								<label for="hospitalId" class="col-sm-3 control-label">Hospital ID  <span style="color:red;">*</span></label>
								<div class="col-sm-4">
									<select class="form-control" name="hospitalId" id="hospitalId" tabindex="1" required>
										<option value="">Select Hospital Id</option>
											<c:forEach items="${hospitalIdList}" var="hospitalIdValue">
												<option value="${hospitalIdValue}">${hospitalIdValue}</option>
											</c:forEach>
									</select>
								</div>							
							</div>
							<div class="form-group">
								<label for="submit" class="col-sm-3 control-label"><span>&#160;</span></label>
								<div class="col-sm-3">
									<input type="hidden" name="action1" value="searchHospitalById">
									<input type="submit" name="submit" id="submit" tabindex="2" class="form-control btn btn-primary" value="Submit">
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