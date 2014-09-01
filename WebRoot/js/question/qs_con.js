$(document).ready(function(){
	var qs_id = $("#questionid").attr('value');
	$.post('stared', {'question_id':qs_id}, function(data){
		console.log("this question stared");
		console.log(data);
		$('#total_star').html(data);
	});
	
	$.post('prestar', {'questionid': qs_id}, function(data) { 
		var pre = $("#prestar");
		if (data == 1) {  
			console.log(1);
			pre.attr("value", "1")
			pre.html("已收藏")
		}
		else {
			console.log(0);
			pre.attr("value", "0")
			pre.html("<span class='glyphicon glyphicon-star'></span>收藏")
		}
	});
});

function next_star(){
	var pre = $("#prestar");
	var qs_id = $("#questionid").attr('value');
	if (pre.attr("value") == 0) {
		$.post('star', {'question_id' : qs_id}, function(data){
			if (data == 1) {
				console.log(1);
				pre.attr("value", "1")
				pre.html("已收藏")
			}
		});
	}
	
	else {
		$.post('unstar', {'question_id' : qs_id}, function(data){
			if (data == 1){
				console.log(0);
				pre.attr("value", "0")
				pre.html("<span class='glyphicon glyphicon-star'></span>收藏")
			}
		});
	}
}