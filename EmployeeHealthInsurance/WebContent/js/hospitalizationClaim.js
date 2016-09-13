$(function () {
            $('.date').datepicker({
            	format: 'dd-mm-yyyy',
            	autoclose: true,
            });
            $("#beneficiaryName").on("change",function() {
            	$("#gender1").prop('checked', false);
            	$("#gender2").prop('checked', false);
            	var beneficiaryName = $("#beneficiaryName").val();
            	var employeeId = $("#employeeId").val();
            	$.get("ClaimsController?action=getHospitalizationClaimForm", { name: beneficiaryName, employeeId: employeeId, value: "healthInsuranceId" }, function(responseText1) {
            		$("#healthInsuranceId").val(responseText1);
            		$.get("ClaimsController?action=getHospitalizationClaimForm", { name: beneficiaryName, employeeId: employeeId, value: "relation" }, function(responseText2) {
                		var relation = responseText2;
                		$("#relation").val(responseText2);
                		if(relation == "Father" || relation == "Father-In-Law")
                			$("#gender1").prop('checked', true);
                		else if(relation == "Mother" || relation == "Mother-In-Law")
                			$("#gender2").prop('checked', true);
                    });
                });            	
            }); 
        });