<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一键写评语</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/res/css/extra/message.css">
<!-- <script type="text/javascript" -->
<%-- 	src="${pageContext.request.contextPath}/res/js/common/jquery/jquery-1.11.0.min.js"></script> --%>
	<%@ include file="/views/embedded/common.jsp"%>
</head>
<body>
<div class="x-tip">
 <div class="content clearfix">
    <div class="content-left" style="width:100%;">
		<div class="x-swich">
        <div class="tab-list">
			<ul>
				<li class="clearfix to">
					<div>
						<span class="fl img"><img alt="头像" src="<avatar:avatar userId='${message.posterId}'/>"></span>
						<div class="fl">
						<p class="name">${message.posterName}</p>
						<p class="num"><span>手机:</span>${empty teacher.mobile ? '无' : teacher.mobile}</p>
						<span style="background-color:silver;border-radius:5px;padding:3px;margin:3px 0;">${message.content}</span>
					</div>
				</div>
            </li>
          </ul>
        </div>
      </div>
    </div>
 </div>
  <div class="to-right">
      <div>
        <textarea id="message"></textarea>
      </div>
   </div>
  <div class="clearfix send-btn">
<%--   	<c:if test="${isTeacher}"><a href="javascript:sendMessage();" class="on">发送</a></c:if><a href="javascript:cancleSend();">取消</a> --%>
  	<c:if test="${isTeacher}"> 
  		<button onclick="sendMessage();" class="btn btn-blue">发送</button>
  	</c:if>
  	<button onclick="cancleSend();" class="btn">取消</button>
  </div>
</div>
</body>
<script type="text/javascript">
	function sendMessage(){
		var teacherId = "${teacher.id}";
		var content = $("#message").val();
		if(teacherId == null || teacherId === ""){
			$.alert("系统繁忙，请尝试刷新或等候");
			return;
		}
		var url = "${pageContext.request.contextPath}/message/center/sendMessageToTeacher"
		$.post(url,{"teacherId":teacherId,"content":content},function(data,status){
			if("success" === data){
				$.success("发送成功");
				$.closeWindow();
			}else{
				$.error("系统繁忙，请稍后再发送");
			}
		});
	}
	//取消发送消息
	function cancleSend(){
		$.closeWindow();
	}
</script>
</html>