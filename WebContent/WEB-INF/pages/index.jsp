<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" href="css/comment.css">
<script type="text/javascript">
		$(function(){
			var token ="${token}";
			$("#login").click(function(){
				$.get(
					'v1/login',
					{token:token},
					function(data){
				
					}
				);
			});
			initialComment();
			
		});
		var totalPage = 0;
		function initialComment(page){
			if(page==undefined||page==0||page==1){
				$('div.comment').empty();
			}
			
			var htmlContent='';
			$.ajax({
	             type: "POST",
	             url: "comment/listComments",
	             data: {
	            	 page:page
	             },
	             dataType: "json",
	             success: function(res){
	            	 var data = res.comments;
	            	 totalPage = res.totalPage;
	            	 if(page==undefined||page==0||page==1){
	            	 htmlContent+='<div class="title">评论('+res.totalNum+')</div>'+
	            	 			  '<div class="mkc">'+
				     					'<textarea id="mkc" placeholder="说点啥吧~"></textarea>'+
				    					'<button id="mkc">发表评论</button>'+
			    				  '</div>';
	            	 htmlContent+='<div class="commentList">'; 
	            	 }
	            	 $.each(data,function(index,value){
	            		 htmlContent+='<div class="eachComment">'+
											'<div class="majorComment">'+
												'<div class="west">'+
													'<img class="avatar" src="img/'+value.owner_id+'.png"/>'+
												'</div>'+
												'<div class="east">'+
													'<div class="north">'+
														'<a class="owner" href="javascript:void(0)" onclick="toIts('+value.owner_id+')">'+value.owner_id+'</a>： '+value.owner_comment+
													'</div>'+
													'<div class="south">'+
														'<div class="sw">'+
															value.ct+
														'</div>'+
														'<div class="se">'+
															'<a href="javascript:void(0)" onclick="showRe(this)"><img class="recPic" src="img/comments.png"/><span class="times">'+value.commentList.length+'</span></a>'+
														'</div>'+
													'</div>'+
												'</div>'+
												'<div class="reComment">'+
													'<textarea id="rec" placeholder="说点啥吧~"></textarea>'+
													'<button id="rec" onclick="makeComment(this,'+value.id+')">回复评论</button>'+
												'</div>'+
											'</div>';
											if(value.commentList.length!=0){
												htmlContent+='<div id="triangle-up"></div>';
											}
						$.each(value.commentList,function(index,value){
							htmlContent+='<div class="minorComment">'+
											'<div class="west">'+
												'<img class = "avatar" src="img/'+value.owner_id+'.png"/>'+
											'</div>'+
											'<div class="east">'+
												'<div class="north">'+
													'<a class="owner" href="javascript:void(0)" onclick="toIts('+value.owner_id+'])">'+value.owner_id+'</a>：' +value.owner_comment+
												'</div>'+
												'<div class="south">'+
													'<div class="sw">'+
														value.ct+
													'</div>'+
													'<div class="se">'+
														'<a href="javascript:void(0)" onclick="showRe(this)"><img class="recPic" src="img/comments.png"/><span class="times"></span></a>'+
													'</div>'+
												'</div>'+
											'</div>'+
											'<div class="reComment">'+
												'<textarea id="rec" placeholder="说点啥吧~"></textarea>'+
												'<button id="rec" onclick="makeComment(this,'+value.id+')">回复评论</button>'+
											'</div>'+
										'</div>';
						});
						htmlContent+='</div>';
	            	 });
	            	 if(page==undefined||page==0||page==1){
	            	 	htmlContent+='</div>';
						$('div.comment').html(htmlContent);
	            	 }else{
	            		 $('div.commentList').append(htmlContent);
	            	 }
						$("div.reComment").hide();
						$("div.minorComment").hide();
	             }
	         });
		}

		$("div#triangle-up").live('click',function(){
			if($(this).parent().children("div.minorComment").is(":hidden")){
			        $(this).parent().children("div.minorComment").show();    //如果元素为隐藏,则将它显现
				    $(this).css({"border-top":"8px solid #A9A9A9","border-bottom":"0"});
			}else{
			        $(this).parent().children("div.minorComment").hide();     //如果元素为显现,则将其隐藏
			        $(this).css({"border-bottom":"8px solid #A9A9A9","border-top":"0"});
			}
		});
		var initialPage = 1;
		$("a#commentMore").live('click',function(){
			++initialPage;
			if(initialPage>totalPage){
				$(this).html("已全部加载完成");
				return;
			}
			initialComment(initialPage);
		});
		function toIts(val){
			
		}
		
		function showRe(t){
			var s = $(t).parent().parent().parent().next();
			s.slideToggle('fast');
		}
		function makeComment(t,val){
			var id = val;
			var owner_comment =  $(t).prev().val();
			if(owner_comment.trim() == ''){
				return;
			}
			console.log("makeComment");
			$.ajax({
	             type: "POST",
	             url: "comment/makeComment",
	             data: {
	            	 id:id,
	            	 owner_comment:owner_comment,
	             },
	             dataType: "json",
	             success: function(data){
	            	 initialPage=1;
	            	 initialComment(initialPage);
	            	 $("a#commentMore").html("点击加载更多...");
	             }
	         });
		}
		$("button#mkc").live('click', function(){
			makeComment(this);
		});
</script>
</head>
<body>
	${userList.size()}
	<input type="button" value="login" id="login"/>
	<div class="comment"></div>
	<div class="commentMore"><a href="javascript:void(0)" id="commentMore">点击加载更多...</a></div>
	<br>
	<br>
</body>
</html>