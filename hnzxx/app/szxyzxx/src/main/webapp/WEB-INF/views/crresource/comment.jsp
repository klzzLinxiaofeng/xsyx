<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="platform.education.resource.contants.ResourceType"%>
<%@include file="/views/embedded/taglib.jsp"%>
<%-- <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/res/css/extra/base.css">--%>
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/res/css/extra/css/commentStar.css"> 
<style>
	.tab .title{
	font-size: 14px;
	color: #323232;
	line-height: 44px;
}
.comment-submit{
	width:530px;
}
.comment-content{
	width:88%;
}
</style>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr>
<div class="comment">
	<div class="tab">
		<p class='title'>评论</p>
	</div>
	<div class="comment-area">
		<div class="comment-write">
			<div class="">
<!-- 				<div class="pinlun" style="float: left"> -->
<!-- 					你的评论 &nbsp; <span class="small-rating"><span -->
<!-- 						style="width: 90.0%"></span></span><span class="dengji"> &nbsp; 还行</span> -->
<!-- 				</div> -->
<!-- 				<div class="value-btn" -->
<!-- 					style="float: left; position: relative; top: 7px; margin-left: 10px;"> -->
<!-- 					<a href="javaScript:;" hidefocus="true" id="value-btn-2" -->
<!-- 						class="enable" alog-action="view.userEvaluation"><b -->
<!-- 						class="ic ic-value"></b>我要评价</a> -->
<!-- 				</div> -->
			</div>
			<div class="jqte">
				<textarea style="border: 1px solid grey;width:97%;" rows="4" cols="115" tabindex="0" id="content1">
				</textarea>
			</div>
			<div class="comment-submit">
				<span>还可以输入<em id="title-counter">200</em>个字
				</span> <input type="button" onclick="comment();" id="commentBtn"
					value="评论">
			</div>
		</div>
		<div class="comment-title">所有评论( ${empty count ? 0 : count} )</div>
	</div>
	<div class="comment-list">
		<c:forEach items="${items}" var="item" varStatus="sta">
			<div class="comment-item clearfix">
				<div class="comment-img">
					
					<img alt="头像" src="<avatar:avatar userId="${item.posterId}"/>">
				</div>
				<div class="comment-content">
					<h4>
						<span class="name">${item.postName} </span><fmt:formatDate value="${item.postTime}" pattern="yyyy-MM-dd  H:mm:ss"/> 
						<span class="floor">${sta.index+1 + (page.currentPage-1)*10}楼 </span>
					</h4>
					<div class="score_1">
						<span class="small-rating"><span style="width: 0%"></span></span>
					</div>
					<div style="word-break: break-all;">${item.content}</div>
				</div>
			</div>
		</c:forEach>
		<div style="text-align:center">
		</div>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		$("#content1").html("");
		inputChange();
	});
	  function inputChange(){
      	$("#content1").on('input',function(e){
      		var content = $("#content1").val() + "";
      		if(content.length > 200){
      			$("#content1").val(content.substring(0,200));
      			$("#title-counter").html(0);
      			return;
      		}
      		var surplus = 200 - content.length;
      		$("#title-counter").html(surplus);
      	});
      }
	function commentReload(){
		window.location.reload();
	}
</script>

