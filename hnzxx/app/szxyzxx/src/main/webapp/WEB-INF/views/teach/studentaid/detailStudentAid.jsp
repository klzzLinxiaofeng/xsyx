<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
. form-horizontal{position:relative;}
	.add_img{
		background:url("${pageContext.request.contextPath}/res/css/extra/images/up_img.jpg") no-repeat;
		display:block;
		width:98px;
		height:30px;
		
	}
	.head_photo{   height: 185px;
    right: 20px;
    position: absolute;
    top: 101px;
    width: 155px;}
    .form-horizontal .control-group{
    	width:45%;
    	display:inline-block;
    }
    .table thead th{height:34px;line-height:34px;}
    legend + .control-group{margin-top:0;}
    
</style>
</head>
<body style="background-color: cdd4d7 !important">
		<div class="row-fluid ">
			<div class="span12">
				<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
							<form id="modifyStudentAidForm"  class="form-horizontal left-align form-well" novalidate="novalidate">
								<input type="hidden" id="id" name="id" value="${studentAidVo.id}"/>
								
									<div class="control-group">
										<label class="control-label">学年</label>
										<div class="controls">
											<input type="text" readonly="readonly" name="schoolYear" id="schoolYear" value="${studentAidVo.schoolYear}" />
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label">年级</label>
										<div class="controls">
											<input type="text" readonly="readonly" name="gradeName" id="gradeName" value="${studentAidVo.gradeName}" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">班级</label>
										<div class="controls">
											<input type="text"  readonly="readonly" name="teamName" id="teamName" value="${studentAidVo.teamName}" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">姓名</label>
										<div class="controls">
											<input type="text"  readonly="readonly" name="studentName" id="studentName" value="${studentAidVo.studentName}" />
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label">贫困类别</label>
										<div class="controls">
											<select id="povertyCategory" disabled="disabled" name="povertyCategory" class="chzn-select" style="width:220px;" ></select>
				
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label">贫困原因</label>
										<div class="controls">
											<select id="povertyCauses" disabled="disabled" name="povertyCauses" class="chzn-select" style="width:220px;"></select>
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label">助困形式</label>
										<div class="controls">
											<input type="text"  readonly="readonly" name="aidForm" id="aidForm" value="${studentAidVo.aidForm}" />
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label">家庭年收入/人口</label>
										<div class="controls">
											<input  type="text"  readonly="readonly" name="oneIncome" id="oneIncome" value="${studentAidVo.oneIncome}" />
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label">资助日期</label>
										<div class="controls">
											<input type="text"  readonly="readonly" id="aidDay" name="aidDay" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${studentAidVo.aidDay}"></fmt:formatDate>' onclick="WdatePicker();"/>
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label">资助金额（元）</label>
										<div class="controls">
											<input  type="text"  readonly="readonly" name="aidAmount" id="aidAmount" value="${studentAidVo.aidAmount}" />
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label">备注</label>
										<div class="controls">
											<%-- <input  type="text"  readonly="readonly" name="remark" id=remark value="${studentAidVo.remark}" /> --%>
											 <textarea rows="3" cols=""  readonly="readonly" name="remark" id="remark"  placeholder="备注">${studentAidVo.remark}</textarea>

										</div>
									</div>
									
								
								<div class="form-actions tan_bottom">
									<button onclick="detailStudentAid();" class="btn btn-warning" >退出</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
	<script>

     $(function() {
 		//困难程度
 		$.jcGcSelector("#povertyCategory", {tc : "JY-KNCD"}, "${studentAidVo.povertyCategory}", function() {
 			$("#povertyCategory").chosen();
 		});
 		//困难原因
 		$.jcGcSelector("#povertyCauses", {tc : "JY-KNYY"}, "${studentAidVo.povertyCauses}", function() {
 			$("#povertyCauses").chosen();
 		});
		});
    
    function detailStudentAid(){
    	$.closeWindow();
    }
    

	</script>
</body>
</html>
