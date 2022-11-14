<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="framework.generic.im.Constants.EasemobConstants"%>
<%@include file="/views/embedded/plugin/jquery.jsp" %>
<%-- <%@include file="/views/embedded/taglib.jsp" %> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/easemob/jquery-1.11.1.js"></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/res/plugin/easemob/strophe.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/res/plugin/easemob/json2.js'></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/easemob/easemob.im-1.0.7.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/easemob/easemob.im.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/falgun/js/bootstrap.js"></script>
<script type="text/javascript">
	var conn = null;
	conn = new Easemob.im.Connection();
	$(function(){
         conn.init({
 			onOpened : function(){
 				conn.setPresence();
 				window.external.InvokeOpen();
 			},
 			//接收及时消息
 			onTextMessage : function(message){
 				window.external.InvokeMessage(message.data);
			},
			onClosed : function() {
		        //处理登出事件
		        window.external.InvokeClose();
		    },
		    onError : function(e) {
		        //异常处理
		        window.external.InvokeException(e.msg);
		    },
             https : true
 			
 		});	
	});
	//登录Easemob
	function LoginEasemob(username,password){
		if(username != null && username != ""){
			conn.open({
	            user : username,
	            pwd : "<%=EasemobConstants.DEFAULT_PASSWORD%>",
                appKey : '<%=EasemobConstants.APP_KEY%>'
		    });
		}
	}
	//退出环信
	function CloseEasemob(){
		conn.close();
	}
</script>