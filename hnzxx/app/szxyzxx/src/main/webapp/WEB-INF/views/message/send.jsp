<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="framework.generic.im.Constants.EasemobConstants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一键写评语</title>
<%@ include file="/views/embedded/common.jsp"%>
<%@include file="/views/embedded/plugin/easemob_js.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/res/css/extra/message.css">
<%@include file="/views/embedded/plugin/dept_selector_js.jsp" %>
<%@ include file="/views/embedded/plugin/zTree.jsp"%>
<script type="text/javascript">
	var conn = null;
	conn = new Easemob.im.Connection();
	$(function(){
         conn.init({
 			onOpened : function(){
 				conn.setPresence();
 			},
 			onTextMessage : function(message){
 				getRecord(message);
			},
			 https : true
 		});	
         conn.open({
                 user : "${sessionScope[sca:currentUserKey()].imAccount}",
                 pwd : "<%=EasemobConstants.DEFAULT_PASSWORD%>",
                 appKey : '<%=EasemobConstants.APP_KEY%>'
         });
	});
</script>
</head>
<body>
<div class="x-tip">
  <div class="input">
    <input type="text" id="seachText" placeholder="输入同事姓名或手机号"/>
  </div>
  <div class="content clearfix">
   <div class="content-left" style="width:520px;border-bottom:1px solid #d4d4d4;margin-bottom:10px;">
	  <div class="tab">
		      <a class="on a">常用</a>
		      <a class="a">全部</a>
		      <div id="dept" style="float:left;"></div>
	      </div>
	      </div>
    <div class="content-left">
      
      <div class="x-swich" style="margin-left:10px;">
        <div class="tab-list on" style="border: 1px solid #d4d4d4;">
          <h4>全部${fn:length(latestList)}人</h4>
          <ul  style="height:213px;">
         <c:forEach items="${latestList}" var="late">
         		<c:if test="${late.userId != currentUserId}">
		            <li class="clearfix">
		              <div> <span class="fl img"><img alt="头像" src="<avatar:avatar userId='${late.userId}'/>"></span>
		                <div class="fl">
		                  <input type="hidden" class="teacherId" value="${late.teacherId}"/>
		                  <input type="hidden" class="userId" value="${late.userId}"/>
		                  <p class="name"><span>姓名：</span>${late.name}</p>
		                  <p class="num"><span>手机：</span>${empty late.mobile ? '无' : late.mobile}</p>
		                  <p class="dtName"><span>部门：</span>${empty late.departmentName ? '无' : late.departmentName}</p>
		                </div>
		              </div>
	            </li>
	            </c:if>
	          </c:forEach>
          </ul>
        </div>
        <div class="tab-list" style="display:none;">
          <h4>全部${fn:length(teacherList)}人</h4>
          <ul style="height:213px;">
            <c:forEach items="${teacherList}" var="info">
            	<c:if test="${info.userId != currentUserId}">
		            <li class="clearfix">
		              <div> <span class="fl img"><img alt="头像" src="<avatar:avatar userId='${info.userId}'/>"></span>
		                <div class="fl">
		                  <input type="hidden" class="teacherId" value="${info.id}"/>
		                  <input type="hidden" class="userId" value="${late.userId}"/>
		                  <p class="name"><span>姓名：</span>${info.name}</p>
		                  <p class="num"><span>手机：</span>${empty info.mobile ? '无' : info.mobile}</p>
		                  <p class="dtName"><span>部门：</span>${empty info.departmentName ? '无' : info.departmentName}</p>
		                </div>
		              </div>
		            </li>
	            </c:if>
	          </c:forEach>
          </ul>
        </div>
      </div>
    </div>
    <div class="content-right fl">
      <div id="textarea_${sessionScope[sca:currentUserKey()].id}" class="textarea" style="overflow: auto;height: 233px;">
      </div>
    </div>
  </div>
  <input type="hidden" id="teacher"/>
  <div class="clearfix send-btn" id="sendMessage" style="border:1px solid #ccc;margin:5px 10px;padding:5px;display: none">
  	<div id="fillTeacherName" style="text-align:left"></div>
  	<textarea id="message" class="span11" style="border:0 none;height:79px"></textarea>
   </div>
   <div style="text-align:center;display: none;" class="btn_cz">
     <button onclick="sendMessage();" class="btn btn-blue">发送</button><button onclick="cancleSend();" class="btn">取消</button> 
   </div>
</div>
</body>
<script type="text/javascript">
	var currentTeacher = null;
	$(function(){
		initData();
		initDeptSelector();
		$(".content-left .tab a").click(function(){
			var _num=$(this).index();
			$(this).addClass("on").siblings().removeClass("on");
			$(".x-swich").find(".tab-list").removeClass("on");
			if(_num != 2){
				$(".x-swich").find(".tab-list").eq(_num).show().siblings().hide();	
				$(".x-swich").find(".tab-list").eq(_num).addClass("on");
			}else{
				$(".x-swich").find(".tab-list").eq(1).show().siblings().hide();
				$(".x-swich").find(".tab-list").eq(1).addClass("on");
			}
			initData();
			initDeptSelector();
		});
		$(".tab-list ul li").click(function(){
			$(this).addClass("on").siblings().removeClass("on");
			var teacherName = $(this).find(".name").text();
			var name = teacherName.substring(teacherName.indexOf("：")+1);
			$("#fillTeacherName").html("");
			$("#teacher").val("");
			$("#teacher").val($(this).find(".teacherId").val());
			$("#fillTeacherName").append("<span>TO：" + name + "</span>");
			$("#sendMessage").show();
			$(".btn_cz").show();
			//显示聊天记录
			var teacherId = $(this).find(".teacherId").val();
			var userId = $(this).find(".userId").val();
			var url = "${pageContext.request.contextPath}/message/center/getMessageRecord?teacherId=" + teacherId;
			$.get(url,{},function(){});
			currentTeacher = userId;
		});
		var sendType = '${type}';
		if(sendType==1) {
			var specialName='${specialName}';
			$("#seachText").val(specialName);
			$(".x-tip .content .content-left .a").eq(1).click();
			$(".x-tip .tab-list ul li:visible").click();
		}
	});
	function initData(){
		seachTeacher();
		seachTeacherAuto();
		initDept();
	}
	function initDept(){
		$("#seachText").focus(function(){
			initDeptSelector();
			var seachText = $("#seachText").val();
			seachTeacherOnchange(seachText);
		});
	}
	function initDeptSelector(){
		$.createDeptSelector({
			"deptContainer" : "#dept",
			"clickCallback" : function (){
				var departmentName = $("#dept_selected_name_span").text();
				var data_id = $("#dept").attr("data-id");
				if(data_id != null && data_id !== '' && data_id !== "undefined"){
					seachTeacherOnchange(departmentName);
				}
			}
		});
	}
	//通讯录模糊查询
	function seachTeacher(){
		$("#seachText").keyup(function(e){
		    if(!e) var e = window.event; 
		    var seachText = $("#seachText").val();
		    var flag = 0;
			$(".x-tip .on ul li").each(function(index) {
				var teacherName = $(this).find(".name").text();
				var teacherNumber = $(this).find(".num").text();
				var departmentName = $("#dept_selected_name_span").text();
		    	if(teacherName.indexOf(seachText)>=0 || teacherNumber.indexOf(seachText)>=0){
			    		$(this).show();
			    		flag ++ ;
		    	}else{
		    		$(this).hide();
		    	}
		 	 });
			$(".x-tip .on h4").text("全部" + flag + "人");
		 });
	}
	//键盘自动查询
	function seachTeacherAuto(){
		var seachText = $("#seachText").val();
		var flag = 0;
		$(".x-tip .on ul li").each(function(index) {
			var teacherName = $(this).find(".name").text();
			var teacherNumber = $(this).find(".num").text();
	    	if(teacherName.indexOf(seachText)>=0 || teacherNumber.indexOf(seachText)>=0){
		    		$(this).show();
		    		flag ++ ;
	    	}else{
	    		$(this).hide();
	    	}
		 });
		$(".x-tip .on h4").text("全部" + flag + "人");
	}
	
	function seachTeacherOnchange(seachText){
			var flag = 0;
// 		    var seachText = $("#selectDepartment").val();
			$(".x-tip .on ul li").each(function(index) {
				var departmentName = $(this).find(".dtName").text();
		    	if(departmentName.indexOf(seachText)>=0){
		    		$(this).show();
		    		flag ++ ;
		    	}else{
		    		$(this).hide();
		    	}
		 	 });
			$(".x-tip .on h4").text("全部" + flag + "人");
	}
	//发送消息
	function sendMessage(){
		var teacherId = $("#teacher").val();
		var content = $("#message").val();
		if(content == null || content === "" || content === "undefined"){
			$.error("请先输入内容后在发送");
			return;
		}
		var currentUser = "${sessionScope[sca:currentUserKey()].id}";
		var currentName = "${sessionScope[sca:currentUserKey()].realName}";
		var url = "${pageContext.request.contextPath}/message/center/sendMessageToTeacher"
		$.post(url,{"teacherId":teacherId,"content":content},function(data,status){
			if("success" === data){
				var date = new Date();
				$("#textarea_" + currentUser).append("<div class='peo_name' style='text-align:right;'>" + date.toLocaleString() +"    <b>" + currentName + "</b></div><span class='ltnr' style='display:block;float:right;text-align: left;'>" + content + "</span>");
				$("#textarea_" + currentUser).append("<div class='clearfix'>");
				$('#textarea_' + currentUser).scrollTop($('#textarea_' + currentUser)[0].scrollHeight); 
				$("#message").val("");
			}else{
				$.error("系统繁忙，请稍后再发送");
			}
		});
	}
	//取消发送消息
	function cancleSend(){
		$("#sendMessage").hide();
		$(".btn_cz").hide();
	}
	function getRecord(message){
		var data = message.data;
		data = eval("("+ data +")");
		var length = data.length + "";
		if(length !== "undefined"){
			var currentUser = "${sessionScope[sca:currentUserKey()].id}";
			if(data.length > 1){
				$("#textarea_" + currentUser).empty();
				for(var i = 0 ; i<data.length ; i++){
					var postTime = data[i].postTime;
					var date = new Date(postTime.time);
					var own = data[i].own;
					var posterId = data[i].posterId;
					var id = data[i].id;
					postTime = date.toLocaleString();
					if(own == null || own === ""){
						return;
					}
					if(own === posterId){
						$("#textarea_" + currentUser).append("<div class='peo_name' style='text-align:right;'>" + postTime +"   <b>" + data[i].posterName + "</b></div><span class='ltnr' style='display:block;float:right;text-align: left;'>" + data[i].content + "</span>");
						$("#textarea_" + currentUser).append("<div class='clearfix'>");
					}else{
						$("#textarea_" + currentUser).append("<div class='peo_name'><b>" + data[i].posterName +"</b>   " + postTime + "</div><span class='ltnr'>" + data[i].content + "</span>");
						setReaded(id);
					}
				}
				$('#textarea_' + currentUser).scrollTop($('#textarea_' + currentUser)[0].scrollHeight); 
			}else if(data.length == 1){
				var postTime = data[0].postTime;
				var date = new Date(postTime.time);
				var posterId = data[0].posterId;
				var receiver = data[0].receivers;
				var id = data[0].id;
				postTime = date.toLocaleString();
				if(currentTeacher == posterId){
					$("#textarea_" + currentUser).append("<div class='peo_name'><b>" + data[0].posterName +"</b>   " + postTime + "</div><span class='ltnr'>" + data[0].content + "</span>");
					setReaded(id);
					$('#textarea_' + currentUser).scrollTop($('#textarea_' + currentUser)[0].scrollHeight); 
				}
			}else{
				$("#textarea_" + currentUser).empty();
			}
		}else{
			$("#count",window.top.document).html("");
			if(data[0] != 0){
				$("#count",window.top.document).html("+" + data);
			}else{
				$("#count",window.top.document).html("");
			}
		}
	}
// 	function pushMessage(data) {
// 		alert(data);
// 		data = data.split(",");
// 		var currentUser = "${sessionScope[sca:currentUserKey()].id}";
// 		if(currentUser == data[1]){
// 			$("#count",window.top.document).html("");
// 			if(data[0] != 0){
// 				$("#count",window.top.document).html("+" + data[0]);
// 			}else{
// 				$("#count",window.top.document).html("");
// 			}
// 		}
// 	}
	function setReaded(id){
		var url = "${pageContext.request.contextPath}/message/center/setReaded"
		$.post(url,{"id":id},function(data,status){
			if("success" === data){
				
			}else{
				$.error("系统繁忙，请稍后再发送");
			}
		});
	}
// 	function getCount(){
// 		var url = "${pageContext.request.contextPath}/message/center/getCount"
// 		$.post(url,function(data,status){
// 			if(status == "success"){
// 				if(data != 0){
// 					$("#count",window.top.document).html("+" + data);
// 				}else{
// 					$("#count",window.top.document).html("");
// 				}
// 			}
// 		});
// 	}
</script>
</html>