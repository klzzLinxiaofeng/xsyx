<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>	
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/editor.jsp"%>
<style>
.row-fluid .span13 {
	width: 75%;
}

.row-fluid .span4 {
	width: 75%;
}

.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
</style>

 
</head>
<body >
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="meeting_form" name="meeting_form" 
						 action="javascript:void(0);">
						<input type="hidden" name="meetingId" value="${meeting.id }">
	<input type="hidden" id="meetingstate" name="isCanhui" >
                        <div  style="padding-left: 160px;">
                        		<table >
  
  <tbody>
  
     <tr>
      <td height="40"    align="right">会议名称:</td>
      <td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${meeting.meetingName }</td>
    </tr>
    <tr>
      <td height="40"    align="right">主持人:</td>
      <td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${meeting.zhuchi }</td>
    </tr>
    
     <tr>
      <td height="40"   align="right">会议类别:</td>
      <td align="left" >&nbsp;&nbsp;&nbsp;&nbsp;${meeting.type }</td>
    </tr>
     <tr>
      <td height="40"   align="right">参会人员:</td>
      <td align="left" >&nbsp;&nbsp;&nbsp;&nbsp; 
							    <c:forEach items="${meetingUser}" var="entry" varStatus="statu">
                                 <input type="checkbox" 
						 	   name="muserid" 
							   value="${entry.id }" 
						            
							   /> ${entry.userName }
                                    </c:forEach> 
							   
							   </td>
    </tr>
     
    <tr>
      <td height="40"    align="right">会议记录<font style="color:red">*</font>:</td>
      <td align="left"> &nbsp;&nbsp;&nbsp;&nbsp;<textarea id="summaryContent" name="summaryContent" rows="15" cols="200"
										class="span8 required" style="width:650px;">${summary.summaryContent } </textarea></td>
    </tr>
    
  </tbody>
  
</table>
</div>
						<div class="form-actions tan_bottom">
							 
							<button class="btn btn-warning" type="button"
								onclick="saveOrUpdate();">确认</button>
								 
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>

<script>
var editor;
KindEditor.ready(function(K) {
	editor = K.create('textarea[name="summaryContent"]', {
		resizeType : 1,
		allowPreviewEmoticons : false,
		allowImageUpload : false,
		items : [
			'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			'insertunorderedlist', '|', 'emoticons', 'image', 'link']
	});
});
</script>
 <script>
 function meeting(type){
  $("#meetingstate").val(type);
  document.meeting_form.submit();
 }
			 
 
		 
		 
	</script>

<script type="text/javascript">
	var checker;
	$(function() {
		checker = initValidator();
	});

	function initValidator() {
		return $("#meeting_form").validate({
			errorClass : "myerror",
			rules : {

			},
			messages : {

			}
		});
	}
 
	
 
	//保存或更新修改
	function saveOrUpdate() {
		$("#summaryContent").val(editor.html());
		if (checker.form()) {
			var loader = new loadLayer();
			  
			var $requestData = formData2JSONObj("#meeting_form");
 
			var url = "${ctp}/office/meeting/summary";
			$requestData.summaryContent = editor.html();
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if ("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if ("success" === data.info) {
						if (parent.core_iframe != null) {
							parent.core_iframe.window.location.reload();
						} else {
							parent.window.location.reload();
						}
						$.closeWindow();
						 
					} else {
						$.error("操作失败");
					}
				} else {
					$.error("操作失败");
				}
				loader.close();
			});
		}
	}
 
</script>


</html>