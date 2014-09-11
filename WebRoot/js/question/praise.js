$(document).ready(function() {
	question_id = $("#questionid").attr('value');
	var answerlist = new Array();
	var praise = $('#praise');
	var tread = $('#tread');
	var praises = $('#praises');
	
	// answerlist
	$('.list-1').each(function() {
		answerlist.push($(this).children('div.col-lg-1').children('button').first().children('span').attr('id')) 
	});
	
	// question praises
	$.post('praises', {'question_id': question_id}, function(data) {
		praises.html(data)
	});
	
	// answer praisesas and pre
	answerlist.forEach(function(answerid) {
		$.post('praisesas', {'answerid' : answerid}, function(data) {
			var select_id = "#" + answerid;
			$(select_id).html(data);
		});
	});
	
	answerlist.forEach(function(answerid){
		$.post('prepraiseas', {'answerid' : answerid}, function(data) {
				console.log(answerid);
				console.log(data); 
				var select_id = "#" + answerid;
				if(data == 1){
					$(select_id).parent().attr("value", 1);
					$(select_id).parent().next().html("<span class=' glyphicon glyphicon-thumbs-up'></span>已赞");
				}
				else if(data == -1){
					$(select_id).parent().attr("value", -1);
					$(select_id).parent().next().next().html("<span class=' glyphicon glyphicon-thumbs-down'></span>已踩");
				}
				else{
					$(select_id).parent().attr("value", 0);
				}
			})
	})
	
	//prepraise
	$.post('prepraise',{'question_id': question_id}, function(data) {
		if(data == 1) {
			praise.attr('value', 1);
			praise.html("<span class=' glyphicon glyphicon-thumbs-up'></span>已赞");
		}
		if(data == -1){
			praise.attr('value', -1);
			tread.html("<span class=' glyphicon glyphicon-thumbs-down'></span>已踩");
		}
		else{
			praise.attr('value', 0);
		}
	});

	//praise question
	praise.click(function() {
		console.log('praise');
		$.post('praise', {'question_id': question_id}, function(data) {
			console.log(data)
			if (data == "False") {
				console.log("praised");
			}
			else if (data == "True"){
				console.log("success");
				$.post('praises', {'question_id': question_id}, function(data) {
					$('#praises').html(data)
				});
				if(praise.attr('value') == 0){
					praise.attr('value', 1)
					praise.html("<span class=' glyphicon glyphicon-thumbs-up'></span>已赞");
				}
				else if(praise.attr('value') == -1){
					praise.attr('value', 1)
					praise.html("<span class=' glyphicon glyphicon-thumbs-up'></span>已赞");
					tread.html("<span class=' glyphicon glyphicon-thumbs-down'></span>踩");
				}
				else {
					praise.attr('value', 0)
					praise.html("<span class=' glyphicon glyphicon-thumbs-up'></span>赞");
				}
			}
			else {
				console.log(data);
				console.log("require login");
			}
		});
	});
	
	//tread question
	tread.click(function() {
		$.post('tread', {'question_id': question_id}, function(data) {
			if (data == "False") {
				console.log("treaded");
			}
			else if (data == "True"){
				console.log("success");
				$.post('praises', {'question_id': question_id}, function(data) {
					$('#praises').html(data);
				});
				if(praise.attr('value') == 0){
					praise.attr('value', -1)
					tread.html("<span class=' glyphicon glyphicon-thumbs-down'></span>已踩");
				}
				else if(praise.attr('value') == 1){
					praise.attr('value', -1);
					praise.html("<span class=' glyphicon glyphicon-thumbs-up'></span>赞");
					tread.html("<span class=' glyphicon glyphicon-thumbs-down'></span>已踩");
				}
				else {
					praise.attr('value', 0);
					tread.html("<span class=' glyphicon glyphicon-thumbs-down'></span>踩");
				}
			}
			else {
				console.log(data);
				console.log("require login");
			}
		});
	});

}); 

function praiseas(value){
	$.post('praiseas', {'answerid': value}, function(data) {

		if (data == "False") {
			console.log("praised"); 
		}
		else if (data == "True"){
			$.post('praisesas', {'answerid' : value}, function(data) {
				var select_id = "#" + value;
				console.log(value);
				
				if($(select_id).parent().attr("value") == 1){
					$(select_id).parent().attr("value", 0);
					$(select_id).parent().next().html("<span class=' glyphicon glyphicon-thumbs-up'></span>赞");
				}
				else if($(select_id).parent().attr("value") == -1){
					$(select_id).parent().attr("value", 1);
					$(select_id).parent().next().html("<span class=' glyphicon glyphicon-thumbs-up'></span>已赞");
					$(select_id).parent().next().next().html("<span class=' glyphicon glyphicon-thumbs-down'></span>踩");
				}
				else {
					$(select_id).parent().attr("value", 1);
					$(select_id).parent().next().html("<span class=' glyphicon glyphicon-thumbs-up'></span>已赞");
				}
				$(select_id).html(data);
			});
		} 
		else {
			console.log(data);
			console.log("require login");
		}
	});
};

function treadas(value){
	$.post('treadas', {'answerid': value}, function(data) {
		if (data == "False") {
			console.log("treaded");
		}
		else if (data == "True"){
			console.log("success");
			$.post('praisesas', {'answerid' : value}, function(data) {
				var select_id = "#" + value;
				console.log(value); 
				
			
				if($(select_id).parent().attr("value") == 1){
					$(select_id).parent().attr("value", -1);
					$(select_id).parent().next().html("<span class=' glyphicon glyphicon-thumbs-up'></span>赞");
					$(select_id).parent().next().next().html("<span class=' glyphicon glyphicon-thumbs-down'></span>已踩");
				}
				else if($(select_id).parent().attr("value") == -1){
					$(select_id).parent().attr("value", 0);
					$(select_id).parent().next().next().html("<span class=' glyphicon glyphicon-thumbs-down'></span>踩");
				}
				else {
					$(select_id).parent().attr("value", -1);
					$(select_id).parent().next().next().html("<span class=' glyphicon glyphicon-thumbs-down'></span>已踩");
				}
				
				$(select_id).html(data);
			});
		}
		else {
			console.log(data);
			console.log("require login");
		}
	});
};