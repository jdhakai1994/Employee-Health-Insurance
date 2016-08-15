$(function () {
	$('table tbody tr td').on('click',function(){
		$("#approveModal").modal("show");
		$("#dateOfAdmission").val($(this).closest('tr').children()[5].textContent);
	    $("#dateOfDischarge").val($(this).closest('tr').children()[6].textContent);
	    $("#totalSumInsured").val($(this).closest('tr').children()[9].textContent);
	    $("#totalClaimAmount").val($(this).closest('tr').children()[10].textContent);
	    $("#rowId").val($(this).closest('tr').data('id'));
	});
	$('#approve').click(function() {
	    var approvedAmount = $('#totalApprovedAmount').val();
	    var id = $('#rowId').val();
	    $('#'+id+' #approvedAmount').val(approvedAmount);
	    $('#'+id+' #approval').prop('checked', true);
	    $('#'+id+' #rejection').prop('checked', false);
	    $('#approveModal').modal('hide');
	  });
	$('#reject').click(function() {
	    var id = $('#rowId').val();
	    $('#'+id+' #approvedAmount').val('');
	    $('#'+id+' #approval').prop('checked', false);
	    $('#'+id+' #rejection').prop('checked', true);
	    $('#approveModal').modal('hide');
	  });
             
});