/**
 * 
 */

$(document).ready(function() {
	var typed = new Typed('.typed-words', {
		strings 	: [ 'C', ' Java', ' Python', ' SQL', ' Web' ],
		typeSpeed 	: 80,
		backSpeed 	: 80,
		backDelay 	: 4000,
		startDelay	: 1000,
		loop 		: true,
		showCursor 	: true
	});
	
	
	listSite();
	
	$('#google').click(function() {
		$('#searchNAVER').attr('style', 'display:none;');
		$('#searchGoogle').removeAttr('style');
	});
	
	$('#naver').click(function() {
		$('#searchGoogle').attr('style', 'display:none;');
		$('#searchNAVER').removeAttr('style');
	});
	
	$('#addSite').click(function() {
		$addSite	= $('#addSite').attr('disabled', 'disabled');
		
		var name	= $('#name').val();
		var url		= $('#url').val();
		
		var param = {
			"name"	: name,
			"url"	: url
		};
		$.ajax({
			url: "siteProcess.do",
			type: "post",
			data: param,
			success: function() {
				listSite();
				var name = document.querySelector("#name");
				name.value = "";
				var url = document.querySelector("#url");
				url.value = "";
			}
		});
		$addSite = $("#addSite").removeAttr("disabled");
	});
});


function listSite(){
	$.ajax({
		url: "siteList.do",
		success: function(result) {
			var output = "";
			
			for (var i in result) {
				output += "<h2><a href='" + result[i].url + "' target='_blank'>" + result[i].siteName + "</a>&nbsp;&nbsp;&nbsp;&nbsp;";
				output += "<input type='hidden' id='siteNum" + i + "' value='" + result[i].siteNum + "'>";
				output += "<a style='cursor: pointer;' onclick='delSite(" + i + ")'><img src='images/close.png' width='18px;'></a></h2>";
			}
			$("#siteList").html(output);
		}
	});
}

function delSite(i){
	var id = "#siteNum" + i;
	var siteNum = $(id).val();
	console.log(siteNum);
	var param = {
		"siteNum": siteNum	
	};
	$.ajax({
		url: "siteDelete.do",
		data: param,
		success: function() {
			listSite();
		}
	});
	return false;
}



	

