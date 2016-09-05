$(function () {
	$('table tbody tr td').on('click',function(){
		$("#approveModal").modal("show");
	    $("#rowId").val($(this).closest('tr').data('id'));
	});
	$('#approve').click(function() {
	    var id = $('#rowId').val();
	    $('#'+id+' #approval').prop('checked', true);
	    $('#'+id+' #rejection').prop('checked', false);
	    $('#approveModal').modal('hide');
	  });
	$('#reject').click(function() {
	    var id = $('#rowId').val();
	    $('#'+id+' #approval').prop('checked', false);
	    $('#'+id+' #rejection').prop('checked', true);
	    $('#approveModal').modal('hide');
	  });
             
});