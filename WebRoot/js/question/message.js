$(document).ready(function(){
	var messages = $("#messages");
	if (messages.length != 0){
		var htmls = " ";
		$.get('message', function(data){
			var datas = $.parseJSON(data);
			for(var i=0; i<datas["mq"].length; i ++){
				htmls = htmls + "<li>" + datas["mq"][i]; + "</li>";
			}
			console.log(htmls)
			$("#messages").children("ul").html(htmls); 
			$("#messages").children("ul").addClass("message-ul");
			$("#messages").children("ul").children("li").addClass("message-li");
		});
	}
});
function select_messages(){
	$.get('messages', function(data) {
			$('#message').children("span").html(data);
	});
}
select_messages();
setInterval('select_messages()',5000);