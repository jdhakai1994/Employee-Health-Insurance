$(function () {
            $('.date').datepicker({
            	format: 'dd-mm-yyyy',
            	autoclose: true,
            });
            $("#beneficiaryName").on("change",function() {
            	var req1;
            	var req2;
            	var beneficiaryName = $("#beneficiaryName").val();
            	var employeeId = $("#employeeId").val();
            	var healthInsuranceId = "healthInsuranceId";
            	var relation = "relation";
            	var url1 = "ClaimsController?action=getHospitalizationClaimForm&name=" + escape(beneficiaryName) + "&employeeId=" + employeeId + "&value=" + escape(healthInsuranceId);
            	var url2 = "ClaimsController?action=getHospitalizationClaimForm&name=" + escape(beneficiaryName) + "&employeeId=" + employeeId + "&value=" + escape(relation);
                if (window.XMLHttpRequest) {
                    req1 = new XMLHttpRequest( );
                    req2 = new XMLHttpRequest( );
                }
                else if (window.ActiveXObject) {
                    req1 = new ActiveXObject("Microsoft.XMLHTTP");
                    req2 = new ActiveXObject("Microsoft.XMLHTTP");
                }
                req1.open("Get",url1,true);
                req1.onreadystatechange = callback1;
                req1.send(null);
                
                req2.open("Get",url2,true);
                req2.onreadystatechange = callback2;
                req2.send(null);
                
                function callback1( ) {
                    if (req1.readyState==4) {
                        if (req1.status == 200) {
                        	$("#healthInsuranceId").val(req1.responseText);
                        }
                    }
                }
                
                function callback2( ) {
                    if (req2.readyState==4) {
                        if (req2.status == 200) {
                        	var relation = req2.responseText;
                        	$("#relation").val(relation);
                        	if(relation == "Father" || relation == "Father-In-Law")
                        		$("#gender1").prop('checked', true);
                        	else if(relation == "Mother" || relation == "Mother-In-Law")
                        		$("#gender2").prop('checked', true);
                        }
                    }
                }
              }); 
        });