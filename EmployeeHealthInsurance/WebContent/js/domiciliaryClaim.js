$(function () {
            $('.date').datepicker({
            	format: 'dd-mm-yyyy',
            	autoclose: true,
            });
            $("#beneficiaryName").on("change",function() {
            	var req;
            	var beneficiaryName = $("#beneficiaryName").val();
            	var employeeId = $("#employeeId").val();
            	var url = "ClaimsController?action=getDomiciliaryClaimForm&name=" + escape(beneficiaryName) + "&employeeId=" + employeeId;
                if (window.XMLHttpRequest) {
                    req = new XMLHttpRequest( );
                }
                else if (window.ActiveXObject) {
                    req = new ActiveXObject("Microsoft.XMLHTTP");
                }
                req.open("Get",url,true);
                req.onreadystatechange = callback;
                req.send(null);
                
                function callback( ) {
                    if (req.readyState==4) {
                        if (req.status == 200) {
                        	$("#healthInsuranceId").val(req.responseText);
                        }
                    }
                }
              }); 
        });