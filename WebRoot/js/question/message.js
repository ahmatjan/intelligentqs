function select_messages(){
	$.get('messages', function(data) {
			$('#message').children("span").html(data);
	});
}
select_messages()
setInterval('select_messages()',20000);