$(function () {
			$('#datepicker').datepicker({
				format: 'dd-mm-yyyy',
				autoclose: true,
			});
            $("#beneficiaryName").on("change",function() {
            	var beneficiaryName = $("#beneficiaryName").val();
            	var employeeId = $("#employeeId").val();
            	$.get("HospitalController?action=getValueAddedServicesForm", { beneficiaryName: beneficiaryName, employeeId: employeeId }, function(responseText) {
                    $("#healthInsuranceId").val(responseText);
                });
            });
            $("#stateName").on("change",function() {
        		var stateName = $("#stateName").val();
        		$('#cityName').empty();
        		$('<option>').val("").text("Select City").appendTo('#cityName');
        		$.get("HospitalController?action=getValueAddedServicesForm", { state: stateName }, function(responseJson) {
        	        $.each(responseJson, function(index, item) { 
        	        	$('<option>').val(item).text(item).appendTo('#cityName');
        	        });       
        		});
        	 });
            $("#cityName").on("change",function() {
            	var stateName = $("#stateName").val();
        		var cityName = $("#cityName").val();
        		$('#hospitalName').empty();
        		$('<option>').val("").text("Select Hospital").appendTo('#hospitalName');
        		$.get("HospitalController?action=getValueAddedServicesForm", { state: stateName, city: cityName }, function(responseJson) {
        	        $.each(responseJson, function(index, item) { 
        	        	$('<option>').val(item).text(item).appendTo('#hospitalName');
        	        });
        		});
        	 });
        });