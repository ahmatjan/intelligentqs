$(document).ready(function() {
	question_id = $("#questionid").attr('value');
	$.post('praises', {'question_id': question_id}, function(data) {
		$('#praises').html(data)
	});

	$("#praise").click(function() {
		$.post('praise', {'question_id': question_id}, function(data) {
			if (data == "False") {
				console.log("praised");
			}
			else if (data == "True"){
				console.log("success");
				var praises = Number($('#praises').html()) + 1;
				$('#praises').html(praises);
			}
			else {
				console.log(data);
				console.log("require login");
			}
		});
	});
	
	$("#tread").click(function() {
		$.post('tread', {'question_id': question_id}, function(data) {
			if (data == "False") {
				console.log("treaded");
			}
			else if (data == "True"){
				console.log("success");
				var praises = Number($('#praises').html()) - 1;
				$('#praises').html(praises);
			}
			else {
				console.log(data);
				console.log("require login");
			}
		});
	});

});