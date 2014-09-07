(function ($) {

  var options = {
    confirmKeys: [13, 44],
    left: '37',
    top: '38',
    right: '39',
    bottom:'40'
  	
  }

  function keyCheck(keyPressEvent){
    var k = keyPressEvent.which;
    if (k == options["confirmKeys"][0] || k == options["confirmKeys"][1]) {
    	keyInput(); 
    }
    
    else if( k == options['top'] || k == options['left']){
    	if($('#tag-button li.active').prev().val() == undefined){
    		console.log("unde")
    	}
    	else{
    		var vs = $('#tag-button li.active').prev().val();
    		console.log(vs)
    		var select_3 = "#tag-button li[value='" + vs + "']"
    		$('#tag-button li.active').removeClass("active");
    		$(select_3).addClass("active");
    	}
    }
    
    else if( k == options['bottom'] || k == options['right']){
    	if($('#tag-button li.active').next().val() == undefined){
    		console.log("unde")
    	}
    	else{
    		var vs = $('#tag-button li.active').next().val();
    		console.log(vs)
    		var select_3 = "#tag-button li[value='" + vs + "']"
    		$('#tag-button li.active').removeClass("active");
    		$(select_3).addClass("active");
    	}
    }
    
    else{
      var keywords = $("input[data-role=simple-input]").val();
      keySearch(keywords);    
    }
  }

  function keyInput(){
	  var value = $('#tag-button li.active').val()
	  var select = "#tagid_" + value;
	  var tag = $(select).children("a").text();
	  var id = $(select).attr("value");
	  var html = $("#tags").html();
		
	  var select_2 = "#tags span[value='" + value + "']"
	  if($(select_2).val() == undefined){
		  html = html + "<span class='mark-ask label label-default' value='" + id + "'>" + tag + 
			" &nbsp;<a  onclick='removeTag(" + id + ")'><span class='glyphicon glyphicon-remove'></span></a></span>"
			$("#tags").html(html);
	  }
	  $("#tag-button").parent().removeClass("open");
  }

  function keySearch(keywords){
	  if(keywords == ""){
		  $("#tag-button").parent().removeClass("open");
	  }
	  else{
		  $.post("searchtag", {"tag_sinnpt": keywords}, function(data){
				var datas = $.parseJSON(data);
				var html = ""
				for(i in datas["tag"]){
					var tagid = datas["tag"][i]['info'].split(":")[1];
					html = html + "<li id='tagid_" + tagid + "' value='" + tagid + "' onclick='addTag(this.value)'><a>" +
						datas["tag"][i]['tag'] + "</a></li>"
				}
				if(html != ""){
					$("#tag-button").html(html);
					$("#tag-button").parent().addClass("open");
				}
				else{
					$("#tag-button").parent().removeClass("open");
				}
				$("#tag-button li").first().addClass("active")
		  })
	  }
  }

  function tagsinput(){
  }

  tagsinput.prototype = {
    constructor: tagsinput,
  }

  $.fn.tagsinput = function(){
      var keyValue = $(this).attr("value");
      $(this).keyup(function(e){
        keyCheck(e);
      });
  }

  $.fn.tagsinput.Constructor = tagsinput();

  $(function() {
    $("input[data-role=simple-input]").tagsinput();
    $("input[data-role=simple-input]").bind('input propertychange', function() {
    })
  });
})(window.jQuery);