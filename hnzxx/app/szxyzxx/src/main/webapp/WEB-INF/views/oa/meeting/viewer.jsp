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

<script type="text/javascript">
	$(function() {
		$.createMemberSelector({
			"inputIdSelector" : "#member_selector",
// 			"isOnTopWindow" : true,
			"layerOp" : {
				"shift" : "top"
			}
		});
	});
</script>
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
      <td height="40"    align="right">创建人:</td>
      <td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${meeting.createname }</td>
    </tr>
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
      <td height="40"    align="right">开始时间:</td>
      <td align="left">&nbsp;&nbsp;&nbsp;&nbsp; ${meeting.starttime }</td>
    </tr>
    
    <tr>
      <td height="40"    align="right">结束时间:</td>
      <td  align="left">&nbsp;&nbsp;&nbsp;&nbsp; ${meeting.endtime }</td>
    </tr>
     <tr>
      <td height="40"   align="right">会议地点:</td>
      <td align="left"> &nbsp;&nbsp;&nbsp;&nbsp; ${meeting.address }</td>
    </tr>
    <tr>
      <td height="40"   align="right">会议说明:</td>
      <td align="left"> &nbsp;&nbsp;&nbsp;&nbsp; ${meeting.meetingContent }</td>
    </tr>
     <tr>
      <td height="40"  align="right">提交时间:</td>
      <td  align="left"> &nbsp;&nbsp;&nbsp;&nbsp; <fmt:formatDate value="${meeting.createDate}" pattern="yyyy-MM-dd" /></td>
    </tr>
   
   <c:if test="${meeting.state=='未开始' }">  
    <tr>
      <td height="40"    align="right">参会意见:</td>
      <td align="left"> &nbsp;&nbsp;&nbsp;&nbsp;<textarea  rows="5" cols="65" name="canhuiInfo" class="input-xlarge" placeholder="请反馈您的意见！">${meetingUser.canhuiInfo }</textarea></td>
    </tr>
    </c:if>
  </tbody>
  
</table>
</div>
						<div class="form-actions tan_bottom">
							 <c:if test="${meeting.state=='未开始' }">
							<button class="btn btn-warning <c:if test='${meetingUser.isCanhui==1 }'>disabled </c:if>" type="button"
								<c:if test="${meetingUser.isCanhui!=1 }">onclick="meeting(1);"</c:if>>参会</button>
								<button class="btn btn-blue" type="button"
								<c:if test="${meetingUser.isCanhui!=2 }">onclick="meeting(2);"</c:if> <c:if test='${meetingUser.isCanhui==2 }'>disabled </c:if>">不参加</button>
								</c:if>
								 
								  <c:if test="${!empty meeting.summaryId }">
								 <button class="btn btn-warning" type="button"
								onclick="showSummaryPage('${meeting.summaryId}');">查看纪要</button>
								 </c:if>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
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
 
	
	function meeting(type){
		  $("#meetingstate").val(type);
		  saveOrUpdate();
		 }
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			  
			var $requestData = formData2JSONObj("#meeting_form");
 
			var url = "${ctp}/office/meeting/canhui";
			 
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
	function showSummaryPage(id) {
		 
		$.initWinOnTopFromTop('纪要详情', '${ctp}/office/meeting/summary/info/' + id, '900');
	}
	
</script>


</html>