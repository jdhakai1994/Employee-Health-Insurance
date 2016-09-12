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

<!-- datepicker CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.0/css/bootstrap-datepicker3.css">

<!-- custom CSS -->
<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css" />

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<!-- validation -->
<script src="https://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<!-- datepicker JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.0/js/bootstrap-datepicker.js"></script>

<!-- custom JavaScript -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>

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
						<h3>Generate E-Card</h3>						
					</div>
					
					<div class="form-content">
					<c:set var="isEnrolled" value="${sessionScope.enrolled }"/>
						<c:choose>
							<c:when test='${isEnrolled.equals("no")}'>
								<p style="font-size:20px"><span class="glyphicon glyphicon-exclamation-sign" style="color:red"></span> Your employee registration must be approved before you can generate e-card reports, please click on Employee Registration on the left panel.</p>
							</c:when>
							<c:otherwise>					
								<!-- FORM -->
								<form id="e_CardForm" action="<%=request.getContextPath()%>/RegisterController?action=generate_eCard" method="post" role="form" class="form-horizontal" data-toggle="validator">
								<div class="form-group">
  									<label for="e-card"  class="col-sm-3 control-label">Generate E-Card  <span style="color:red;">*</span></label>
  									<div class="col-sm-3">
  										<select class="form-control" name="relation" id="relation" tabindex="1" required>
  										<option value="">None</option>
  											<c:forEach items="${list}" var="value">
  												<option value="${value}">${value}</option>
  											</c:forEach>
  										</select>
  									</div>
  								</div>
								<div class="form-group">
									<label for="submit" class="col-sm-3 control-label"><span>&#160;</span></label>
									<div class="col-sm-2">
										<input type="submit" name="submit" id="submit" tabindex="2" class="form-control btn btn-primary" value="Generate">
									</div>				
								</div>
								</form>
								<!-- END FORM -->
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<!-- END CONTENT BODY -->
				<!-- CONTENT FOOTER -->
				<div class="content-footer">
					<div class="container">
						<div class="row">
							<div class="col-sm-6 copyright"><strong>Copyright &#169; 2016.</strong> All Rights Reserved</div>
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