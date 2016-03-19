$(function() {
	
	$('#login-form-link').click(function(event) {
		$("#loginForm").delay(100).fadeIn(100);
 		$("#registerForm").fadeOut(100);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		event.preventDefault();
	});
	$('#register-form-link').click(function(event) {
		$("#registerForm").delay(100).fadeIn(100);
 		$("#loginForm").fadeOut(100);
		$('#login-form-link').removeClass('active');
		$(this).addClass('active');
		event.preventDefault();
	});
	
});