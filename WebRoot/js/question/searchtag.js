function addTag(value){
	
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
	$("input[name='question_tags']").attr("value", "")
	
	var tagsid = '';
	offer  = new Array();
	$("#tags span").each(function(){
		tagsid = tagsid + ',' + $(this).attr("value");
	})
	for(var i=0; i < tagsid.split(",").length; i++){
		if(i%2 != 0){
			offer.push(tagsid.split(",")[i])
		}
	}
	console.log(offer)
	$("input[name='question_tag']").attr("value", offer.toString())
	}

function removeTag(id){
	var select = "#tags span[value=" + id + "]"
	$(select).remove();
	
	var tagsid = '';
	offer  = new Array();
	$("#tags span").each(function(){
		tagsid = tagsid + ',' + $(this).attr("value");
	})
	for(var i=0; i < tagsid.split(",").length; i++){
		if(i%2 != 0){
			offer.push(tagsid.split(",")[i])
		}
	}
	console.log(offer)
	$("input[name='question_tag']").attr("value", offer.toString())
	}

function tagbutton(){
	var str = $("input[data-role=simple-input]").val()
	$.post("searchtag", {"tag_sinnpt": str}, function(data){
		var datas = $.parseJSON(data);
		var html = ""
		if(datas['tag'].length != 0){
			var id = datas['tag'][0]['info'].split(":")[1]
			var select = "#tagid_" + id;
			var html = $("#tags").html();
			
			var select_2 = "#tags span[value='" + id + "']"
			if($(select_2).val() == undefined){
				html = html + "<span class='mark-ask label label-default' value='" + id + "'>" + datas['tag'][0]['tag'] + 
				" &nbsp;<a  onclick='removeTag(" + id + ")'><span class='glyphicon glyphicon-remove'></span></a></span>"
				$("#tags").html(html);
			}
		}
		else{
			alert("no")
		}
		$("#tag-button").parent().removeClass("open");
		 $("input[name='question_tags']").attr("value", "")
		
		var tagsid = '';
			offer  = new Array();
		$("#tags span").each(function(){
			tagsid = tagsid + ',' + $(this).attr("value");
		})
		for(var i=0; i < tagsid.split(",").length; i++){
			if(i%2 != 0){
				offer.push(tagsid.split(",")[i])
			}
		}
		console.log(offer)
		$("input[name='question_tag']").attr("value", offer.toString())
	})
	
}