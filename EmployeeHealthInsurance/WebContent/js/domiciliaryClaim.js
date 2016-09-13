$(function () {
            $('.date').datepicker({
            	format: 'dd-mm-yyyy',
            	autoclose: true,
            });
            $("#beneficiaryName").on("change",function() {
            	var beneficiaryName = $("#beneficiaryName").val();
            	var employeeId = $("#employeeId").val();
            	$.get("ClaimsController?action=getDomiciliaryClaimForm", { name: beneficiaryName, employeeId: employeeId }, function(responseText) {
                    $("#healthInsuranceId").val(responseText);
                });
            });
        });