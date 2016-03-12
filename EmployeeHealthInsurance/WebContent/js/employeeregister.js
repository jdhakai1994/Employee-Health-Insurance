$(function () {
            $('.date').datepicker({
            	format: 'dd-mm-yyyy',
            	autoclose: true,
            });
            $('#totalSumInsured').keyup(function(){
            	var sum = document.getElementById("totalSumInsured").value;
            	var amt = 0.02 * sum;
            	$('#premiumAmount').val(amt);
            })            
        });