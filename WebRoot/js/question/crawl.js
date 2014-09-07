$(document).ready(function(){
	var qs_id = $("#questionid").attr('value');
	var qs_title = $("#questionid").html();
	$.post('netquestions', {"question_id": qs_id, "question_title":qs_title}, function(data){
		var datas = $.parseJSON(data);
		var html = "";
		for(var i=0; i < datas["lists"].length; i++){
			html = html + "<li>" +
					"<a href='" + datas["lists"][i]["url"] + "'>" +
					datas["lists"][i]["title"] +
					"</a></li>"
		}
		$("#net_question").html(html);
	})
})