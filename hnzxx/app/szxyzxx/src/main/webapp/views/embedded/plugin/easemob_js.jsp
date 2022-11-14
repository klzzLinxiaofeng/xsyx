<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ page import="platform.education.push.Constants.Constants"%> --%>
<%-- <%@include file="/views/embedded/plugin/jquery.jsp" %> --%>
<%-- <%@include file="/views/embedded/taglib.jsp" %> --%>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/easemob/jquery-1.11.1.js"></script> --%>
<script type='text/javascript' src='${pageContext.request.contextPath}/res/plugin/easemob/strophe.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/res/plugin/easemob/json2.js'></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/easemob/easemob.im-1.0.7.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/easemob/easemob.im.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/falgun/js/bootstrap.js"></script>
<script type="text/javascript">
// 	var conn2 = null;
// 	conn2 = new Easemob.im.Connection();
// 	$(function(){
//          conn2.init({
//  			onOpened : function(){
//  				conn2.setPresence();
//  				window.external.InvokeOpen();
//  			},
//  			//接收及时消息
//  			onTextMessage : function(message){
//  				window.external.InvokeMessage(message.data);
// 			},
// 			onClosed : function() {
// 		        //处理登出事件
// 		        window.external.InvokeClose();
// 		    },
// 		    onError : function(e) {
// 		        //异常处理
// 		        window.external.InvokeException(e.msg);
// 		    }
 			
//  		});	
// 	});
// 	//登录Easemob
// 	function LoginEasemob(username,password){
// 		if(username != null && username != ""){
// 			conn.open({
// 	            user : username,
// 	            pwd : password,
// 	            appKey : 'yjjy#studystarforteacher'
// 		    });
// 		}
// 	}
</script>