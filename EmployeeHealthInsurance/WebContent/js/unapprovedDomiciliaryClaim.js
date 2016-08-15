$(function () {
	$('table tbody tr td').on('click',function(){
		$("#approveModal").modal("show");
	    $("#totalSumInsured").val($(this).closest('tr').children()[7].textContent);
	    $("#totalClaimAmount").val($(this).closest('tr').children()[8].textContent);
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