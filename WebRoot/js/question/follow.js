$(document).ready(function() {
	$.post('followings', {'user_id': 1}, function(data) {
		var datas = $.parseJSON(data);
		console.log('following');
		console.log(datas['following']);
	});

	$.post('followers', {'user_id': 10}, function(data) {
		var datas = $.parseJSON(data);
		console.log('follower');
		console.log(datas['follower']);
	});

	$.post('follows', {'user_id': 1}, function(data) {
		var datas = $.parseJSON(data);
		console.log('follower');
		console.log(datas['follower']);
		console.log('following');
		console.log(datas['following']);
	});  
})

function follow(){
	$.post('follow', {'following': 10}, function(data) {
		console.log(data);
	});
}

function unfollow(){
	$.post('unfollow', {'following': 10}, function(data) {
		console.log(data);
	});
}
