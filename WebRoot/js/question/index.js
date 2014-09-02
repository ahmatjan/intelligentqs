$(document).ready(function() {
	var user_id = $('#user_id').attr('value');
	if ( user_id == undefined) {
	} 
	
	else {
		$.post('follows', {'user_id': user_id}, function(data) {
			var datas = $.parseJSON(data);
			var following = "<span class='glyphicon glyphicon-user'></span> 关注 " + datas['following']
			$('#m_follow').html(following);
		});
		
		$.post('basic', {'user_id': user_id}, function(data) {
			var datas = $.parseJSON(data);
			if (datas["solve"] == undefined){
				datas["solve"] = 0;
			}
			if (datas["questions"] == undefined){
				datas["solve"] = 0;
			}
			var question = "<span class='glyphicon glyphicon-question-sign'></span>问题"  + datas['questions'];
			$('#m_question').html(question);
			var solve = "<span class='glyphicon glyphicon-ok'></span>解决"  + datas['solve'];		
			$('#m_hava_solve').html(solve);
		});
		
		/*
		$.post('havestar', {'user_id': user_id}, function(data){	
			var havastar = "<span class='glyphicon glyphicon-bookmark'></span> 收藏" + data;
			console.log(data)
			$('#m_star').html(havastar);
		})
		*/
	}

})

