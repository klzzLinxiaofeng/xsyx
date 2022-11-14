<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<style>
	button{
		width:100px;
		height:40px;
		line-height:40px;
		text-align:center;
	}
</style>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">  
		<div  class="controls">
			 <p style="font-size: 16px;width: 378px;text-align: center;margin: 0 auto 27px;">删除<span style="color:#ff1e1e">${title }</span>会同时删除以下对象的所有任务，是否执行操作?</p>
			 <textarea rows="10" cols="25" class="span13" style="resize: none;background-color: #fff;height: 90px;width: 364px;margin: 0 auto 30px;" disabled="disabled">${team}</textarea>
			 <div style="text-align:center;">
				 <button class="btn-blue" onclick="del()" style="margin-right: 23px;">确定</button> 
				 <button class="btn-lightGray" onclick="back()"">取消</button>
			 </div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
function  del(){
	  var val={};
	   val.taskId=${param.taskId};
  $.ajax({
      url: "${pageContext.request.contextPath}/paperTask/del/task",
      type: "POST",
      data: val,
      async: false,
      success: function(data) {
        if(data==='promise'){
       	 alert("不是你发布的试卷，不能删除");
        }
        else if(data!=='fail'){
        	alert("删除成功")
        	window.parent.location.reload();
        	$.closeWindow();
           }
        else{
        	 alert("删除失败");
        }
      }
  });
}

function back(){
	$.closeWindow();
}


</script>
</html>