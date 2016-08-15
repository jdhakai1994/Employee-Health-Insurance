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
<link href="<%=request.getContextPath()%>/css/employeeregister.css" rel="stylesheet" type="text/css" />

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<!-- validation -->
<script src="https://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<!-- custom JavaScript -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/unapprovedClaim.js"></script>

</head>
<body>
<div class="container-fluid">
	<div class="row">
	
		<!-- import navbar -->
		<c:import url="../navbars/admin_navbar.jsp" />
		
		<!-- CONTENT -->
		<div class="col-sm-9 no-gutter">
			<div class="content">
				<div class="content-header">
					<span class="glyphicon glyphicon-align-justify"></span>
				</div>
				<div class="content-body">
					<div>
						<h3><c:out value='${requestScope.heading}'/></h3>						
					</div>
					<!-- Modal -->
					<div class="modal fade" id="approveModal" tabindex="-1" role="dialog" aria-labelledby="approveModalLabel">
  						<div class="modal-dialog" role="document">
    						<div class="modal-content">
      							<div class="modal-header">
        							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        							<h4 class="modal-title" id="approveModalLabel">Approve/Reject Claim</h4>
      							</div>
      							<div id="approveDetails" class="modal-body">
      								<div class="container-fluid bd-example-row">
          								<div class="row">
          									<form id="approvalForm" role="form" class="form-horizontal" data-toggle="validator">
		            							<div class="form-group">
													<label for="totalSumInsured" class="col-sm-4 control-label">Total Sum Insured  </label>
													<div class="col-sm-6">
														<input type="text" class="form-control" name="totalSumInsured" id="totalSumInsured" readonly>		
													</div>							
												</div>
											<div class="form-group">
													<label for="totalClaimAmount" class="col-sm-4 control-label">Total Claim Amount  </label>
													<div class="col-sm-6">
														<input type="text" class="form-control" name="totalClaimAmount" id="totalClaimAmount" readonly>		
													</div>							
												</div>
												<div class="form-group has-feedback">
													<label for="totalApprovedAmount" class="col-sm-4 control-label">Approve Amount  </label>
													<div class="col-sm-6">
														<input type="text" pattern="[0-9]+[.]+[0-9]{2}" class="form-control" name="totalApprovedAmount" id="totalApprovedAmount" placeholder="XXXX.XX">
														<span class="glyphicon form-control-feedback"></span>		
													</div>							
												</div>
          									</form>
		      							</div>
		      						</div>
  								</div>
      							<div class="modal-footer">
      								<input type="hidden" name="rowId" id="rowId">
        							<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        							<button type="button" id="approve" name="approve" class="btn btn-success">Approve</button>
       								<button type="button" id="reject" name="reject" class="btn btn-danger">Reject</button>
      							</div>	
							</div>
						</div>
					</div>
					<div class="form-content">
					<c:set var="type" value="${requestScope.type }"/>
					<c:choose>
						<c:when test='${type == "list"}'>
						<form id="approveDomiciliaryClaimsForm" action="<%=request.getContextPath()%>/ClaimsController?action=approve_domiciliary_claim" method="post" role="form" class="form-horizontal">
							<table class="table table-bordered table-striped">
								<thead class="thead-inverse">
									<tr>
										<th>CLAIM-ID</th>
										<th>HI-ID</th>
										<th>BENEFICIARY NAME</th>
										<th>CONTACT</th>
										<th>CLAIM RAISED DATE<br>(dd/mm/yyyy)</th>
										<th style="display:none;">TREATMENT START DATE</th>
										<th>TYPE OF INJURY</th>
										<th style="display:none;">SUM INSURED</th>
										<th>CLAIMED AMOUNT</th>
										<th>APPROVED AMOUNT</th>
										<th>STATUS</th>
									</tr>
								</thead>
								
								<tbody>
									<c:forEach items="${unapprovedDomiciliaryClaimList}" var="current" varStatus="loop">
										<tr data-id="rowId${loop.index +1}" id="rowId${loop.index +1}">
											<td><c:out value="${current.claimNo}" /></td>
											<td><c:out value="${current.healthInsuranceId}" /></td>
											<td><c:out value="${current.beneficiaryName}" /></td>
											<td><c:out value="${current.mobNo}" /></td>
											<td><c:out value="${current.claimRaisedDate}" /></td>
											<td style="display:none;"><c:out value="${current.treatmentStartDate}" /></td>
											<td><c:out value="${current.typeOfInjury}" /></td>
											<td id="sumInsured" style="display:none;"><c:out value="${current.totalSumInsured}" /></td>
											<td id="claimedAmount"><c:out value="${current.totalClaimAmount}" /></td>
											<td id="approvedAmount">
												<input type="text" name="approvedAmount" id="approvedAmount">
											</td>
											<td>
												<input type="checkbox" name="approval" id="approval" value="${current.claimNo}" >Approved<br>
												<input type="checkbox" name="rejection" id="rejection" value="${current.claimNo}" >Rejected
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							
							<div class="form-group">
								<div class="pull-right col-sm-2">
									<input type="hidden" name="action" value="approve_domiciliary_claim">
									<input type="submit" name="submit" id="submit" class="form-control btn btn-primary" value="Submit">
								</div>
							</div>
						</form>
						</c:when>
						<c:when test='${type == "message"}'>
							<p><c:out value='${requestScope.message}'/></p>
						</c:when>
					</c:choose>	
					</div>
				</div>
				<div class="content-footer">
					<div class="container">
						<div class="row">
							<div class="col-sm-6 copyright"><strong>Copyright &#169; 2016.</strong> All Rights Reserved</div>
							<div class="col-sm-6 version"><strong>Version</strong> 1.0</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- CONTENT -->
		
	</div>
</div>
</body>
</html>