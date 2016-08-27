$(function () {
	$("#stateName").on("change",function() {
		var stateName = $("#stateName").val();
		$('#cityName').empty();
		$('<option>').val("").text("Select City").appendTo('#cityName');
		$.get("HospitalController?action=getSearchHospitalForm", { state: stateName }, function(responseJson) {
	        $.each(responseJson, function(index, item) { 
	        	$('<option>').val(item).text(item).appendTo('#cityName');
	        });       
		});
	 });
});