$(document).ready(function(){
	$.post('stared', {'question_id':32}, function(data){
		console.log("this question stared");
		console.log(data);
	});
	
	$.get('stars', function(data){
		console.log("author stars");
		console.log(data);
	})
});

function star(question_id){
	$.post('star', {'question_id' : question_id}, function(data){
		console.log(data);
	});
}

function unstar(question_id){
	$.post('unstar', {'question_id' : question_id}, function(data){
		console.log(data);
	});
}