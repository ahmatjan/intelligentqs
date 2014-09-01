$(document).ready(function(){
	$.post('stared', {'question_id':32}, function(data){
		//console.log("this question stared");
		//console.log(data);
	});
	
	var person_id = $('#person').attr('value');
	//console.log(person_id);
	
	$.post('stars', {'rank': 0, 'user_id': person_id}, function(data){
		var datas = $.parseJSON(data);
		var html = $('#collectqs').html();
		for(i in datas["list"]){
			console.log(datas["list"][i])
			html = html + "<li class='row'>" +
                          "<div class='col-md-2'>" +
                          "<div class='btn btn-primary btn-lg active'>" +
                          "<span class='glyphicon glyphicon-comment'></span> 15<br></div></div>" +
                          "<div class='col-md-8 qa-margin-left'>" +
                          "<p><a href='getDetilQuestion?question_id=" + datas["list"][i]["questionid"] + "'>" +  datas["list"][i]["question"] +"</a></p>" +
                          "<div class='qa-tags'>" + 
                          "<span class='glyphicon glyphicon-tags'> </span> " +
                          "<a href=''><span class='badge'>linux</span></a>" +
                          "<a href=''><span class='badge'>python</span></a>&nbsp;&nbsp;" +
                          "<span class='glyphicon glyphicon-eye-open'> </span> " +
                          "<span class='badge'>1.2 K</span>&nbsp;&nbsp;" +
                          "<span class='glyphicon glyphicon-time'> </span> " +
                          "<span class='badge'>1 days ago</span></div></div> " +
                          "<div class='col-md-2'>" +
                       	  "<img src='./static/image/git.png' alt='...' class='qa-img img-circle'></div>"
		}
		$('#collectqs').html(html);
	})
	
	$.post('havestar', {'user_id': person_id}, function(data){	
		var havastar = "<span class='glyphicon glyphicon-bookmark'></span> 收藏" + data;
		$('#havastar').html(havastar);
	})
	
	$.post('follows', {'user_id': person_id}, function(data) {
		var datas = $.parseJSON(data);
		//console.log(datas['following']);
		var following = "<span class='glyphicon glyphicon-user'></span> 关注 " + datas['following'] 
		$('#following').html(following);
	});
	
	$.post('prefollow', {'user_id': person_id}, function(data) {
		var pre = $("#prefollow");
		if (data == 1) {  
			console.log(1);
			pre.attr("value", "1")
			pre.html("已关注")
		}
		else {
			console.log(0);
			pre.attr("value", "0")
			pre.html("关注")
		}
	});
});
function next_follow(){
	var pre = $("#prefollow");
	var person_id = $('#person').attr('value');
	if (pre.attr("value") == "0"){
		$.post('follow', {'following': person_id}, function(data) {
			if (data == 1) {
				pre.attr("value", "1")
				pre.html("已关注")
			}
		});
	}
	else{
		$.post('unfollow', {'following': person_id}, function(data) {
			if (data == 1) {
				pre.attr("value", "0")
				pre.html("关注")
			}
		});
	}
}