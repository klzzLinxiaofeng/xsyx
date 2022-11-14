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
					<form class="form-horizontal tan_form" id="leave_form" name="leave_form" 
						 action="javascript:void(0);">
						<input type="hidden" name="leaveId" value="${leave.id }">
	<input type="hidden" id="leavestate" name="leavestate" >
                        <div  style="padding-left: 160px;">
                        		<table >
  
  <tbody>
   <tr>
      <td height="40"    align="right">申请人:</td>
      <td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${leave.createname }</td>
    </tr>
    <tr>
      <td height="40"    align="right">请假类型:</td>
      <td align="left">&nbsp;&nbsp;&nbsp;&nbsp;${leave.leavetype }</td>
    </tr>
    
     <tr>
      <td height="40"   align="right">说明:</td>
      <td align="left" >&nbsp;&nbsp;&nbsp;&nbsp;${leave.leaveinfo }</td>
    </tr>
    
    <tr>
      <td height="40"    align="right">请假开始时间:</td>
      <td align="left">&nbsp;&nbsp;&nbsp;&nbsp; ${leave.starttime }</td>
    </tr>
    
    <tr>
      <td height="40"    align="right">请假结束时间:</td>
      <td  align="left">&nbsp;&nbsp;&nbsp;&nbsp; ${leave.endtime }</td>
    </tr>
     <tr>
      <td height="40"  align="right">提交时间:</td>
      <td  align="left"> &nbsp;&nbsp;&nbsp;&nbsp; <fmt:formatDate value="${leave.createDate}" pattern="yyyy-MM-dd" /></td>
    </tr>
    <tr>
      <td height="40"   align="right">请假天数:</td>
      <td align="left"> &nbsp;&nbsp;&nbsp;&nbsp; ${leave.day } 天</td>
    </tr>
    
    <tr>
      <td height="40"    align="right">审批意见:</td>
      <td align="left"> &nbsp;&nbsp;&nbsp;&nbsp;<textarea  rows="5" cols="65" name="approvation" class="input-xlarge" placeholder="请输入请假说明！">同意</textarea></td>
    </tr>
  </tbody>
  
</table>
</div>
						<div class="form-actions tan_bottom">
							 
							<button class="btn btn-warning" type="button"
								onclick="leave(1);">确定</button>
								<button class="btn btn-blue" type="button"
								onclick="leave(2);">不同意</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
 <script>
 function leave(type){
  $("#leavestate").val(type);
  document.leave_form.submit();
 }
			 
 
		 
		 
	</script>

<script type="text/javascript">
	var checker;
	$(function() {
		checker = initValidator();
	});

	function initValidator() {
		return $("#leave_form").validate({
			errorClass : "myerror",
			rules : {

			},
			messages : {

			}
		});
	}
 
	
	function leave(type){
		  $("#leavestate").val(type);
		  saveOrUpdate();
		 }
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			  
			var $requestData = formData2JSONObj("#leave_form");
 
			var url = "${ctp}/office/appr/shenpi";
			 
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
						leaveModal();
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