/**
 * 
 */

$(document).ready(function() {
	listComment();
	
	$("#insertComment").click(function() {
		var $content	= $("#content");
		
		$insertComment = $("#insertComment").attr("disabled", "disabled");
		var num 	= $("#num").val();
		var content = $content.val();
		
		var param = {
			"num": num,
			"content": content
		};
		
		$.ajax({
			url: "insertComment.do",
			type: "post",
			data: param,
			success: function() {
				$content.val("");
				listComment();
			}
		});
		$insertComment = $("#insertComment").removeAttr("disabled");
	});
	
});

function delComment(i){
	var id = "#commentId" + i;
	var commentId = $(id).val();
	var param = {
		"commentId": commentId
	};
	$.ajax({
		url: "deleteComment.do",
		data: param,
		success: function() {
			listComment();
		}
	});
	return false;
}

function listComment(){
	var num = $('#num').val();
	
	$.ajax({
		url: 'comment.do?num='+num,
		success: function(result) {
			var $comment 	= $('#comment').empty();
			var $p			= $('<p>');
			var $span		= $('<span>');
			var $inp		= $('<input>').attr('type', 'hidden');
			var $a			= $('<a>');
			
			var $pClone, $spanClone;	// 일반적으로 close
			
			var adminId = 'admin';
			var sessionID = document.querySelector('#sessionID').innerHTML.trim();
			
			$p.clone().addClass('mb-4').html('댓글 : ' + result.length).appendTo($comment);
			
			$.each(result, function(i, obj){
			
				$pClone = $p.clone().addClass('em').appendTo($comment);
				$('<b></b>').html(obj.id+'&nbsp;&nbsp;&nbsp;&nbsp;').appendTo($pClone);	
				$span.clone().addClass('smallFont').html(dateFormatter(obj.reg_date)).appendTo($pClone);
				
				if (obj.id === sessionID || adminId === sessionID){
					$spanClone = $span.clone().attr('style', 'float: right;').appendTo($pClone);
					
					$inp.clone().attr('id', 'commentId'+i).val(obj.commentId).appendTo($spanClone);
					$a.clone().addClass('smallBtn').attr('onclick', 'delComment('+i+')').html('삭제').appendTo($spanClone);
					
				}
					
				$p.clone().addClass('mb-0').html(obj.content).appendTo($comment);
				$('<hr>').appendTo($comment);
				
			});
			
		}
	});
	
	
}

