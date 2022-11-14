<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<style>
.row-fluid .span13 {
	width: 150px;
}

.form-horizontal .control-label{width:120px;}
.form-horizontal .controls{margin-left:140px;}
.form-horizontal .controls b{line-height:30px;}
.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="pjteachersalary_form" action="javascript:void(0);">
							<div class="control-group" style="display: none;">
								<label class="control-label">
									id：
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13" placeholder="" value="${pjTeacherSalaryVo.id}">
								</div>
							</div>
							<%-- <div class="control-group">
								<label class="control-label">
									所在学校id：
								</label>
								<div class="controls">
								<input type="text" id="schoolId" name="schoolId" class="span13" placeholder="" value="${pjteachersalary.schoolId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									部门id：
								</label>
								<div class="controls">
								<input type="text" id="departmentId" name="departmentId" class="span13" placeholder="" value="${pjteachersalary.departmentId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									教师id：
								</label>
								<div class="controls">
								<input type="text" id="teacherId" name="teacherId" class="span13" placeholder="" value="${pjteachersalary.teacherId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									用户id：
								</label>
								<div class="controls">
								<input type="text" id="userId" name="userId" class="span13" placeholder="" value="${pjteachersalary.userId}">
								</div>
							</div> --%>
							<c:if test="${pjTeacherSalaryVo.valueList != null}">
								<div class="control-group">
									<label class="control-label">
										教师姓名：
									</label>
									<div class="controls">
									<b id="name" name="name" class="span13" placeholder="" value="${pjTeacherSalaryVo.name}">${pjTeacherSalaryVo.name}</b>
									</div>
								</div>
							</c:if>
							<%-- <div class="control-group">
								<label class="control-label">
									工资发放年份：
								</label>
								<div class="controls">
								<input type="text" id="payYear" name="payYear" class="span13" placeholder="" value="${pjteachersalary.payYear}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									工资发放月份：
								</label>
								<div class="controls">
								<input type="text" id="payMonth" name="payMonth" class="span13" placeholder="" value="${pjteachersalary.payMonth}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									工资总额：
								</label>
								<div class="controls">
								<input type="text" id="salaryTotal" name="salaryTotal" class="span13" placeholder="" value="${pjteachersalary.salaryTotal}">
								</div>
							</div> --%>
							<c:forEach items="${pjTeacherSalaryVo.valueList}" var="fieldValue">
								<div class="control-group" style="width:50%;float:left;">
								<label class="control-label">
									${fieldValue.attrName}：
								</label>
								<div class="controls">
								<input type="text" id="${fieldValue.fieldName}" name="${fieldValue.fieldName}" class="span13 add_money" placeholder="0" value="${fieldValue.value}">
								<!-- onkeyup="this.value=this.value.replace(/[^\d.]/g, '')"   ^(-)?\d+(\.\d+)?$ onkeyup="this.value=this.value.replace(^(-)?[1-9][0-9]*$, '')"-->
								</div>
							</div>
							</c:forEach>
							<%-- <div class="control-group">
								<label class="control-label">
									工资明细项01：
								</label>
								<div class="controls">
								<input type="text" id="s1" name="s1" class="span13" placeholder="" value="${pjteachersalary.s1}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									工资明细项02：
								</label>
								<div class="controls">
								<input type="text" id="s2" name="s2" class="span13" placeholder="" value="${pjteachersalary.s2}">
								</div>
							</div> --%>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${pjteachersalary.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var checker;
	$(function() {
		checker = initValidator();
		 $(".add_money").keyup(function(event){
			 var keycode = event.which;
			    if (keycode != 37&&keycode != 38&&keycode != 39&&keycode != 40) {
				 //匹配负号，数字，小数点
				 this.value = this.value.replace(/[^\-\d.]/g, "");
	             //匹配第一个输入的字符不是小数点
	             this.value = this.value.replace(/^\./g, "");
	             //保证.-只出现一次，而不能出现两次以上
	             this.value = this.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
	             this.value = this.value.replace("-", "$#$").replace(/\-/g, "").replace("$#$", "-");
	             //保证-号只能是第一位
	             if(isNaN(this.value)){
	            	 var atr=this.value;
	            	 if(atr.length>=2){
	            	 this.value=atr.substring(0,atr.length-1);
	            	 }
	             }
			    }
         });
	}); 
	
	function initValidator() {
		return $("#pjteachersalary_form").validate({
			errorClass : "myerror",
			rules : {
				
			},
			messages : {
				
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#pjteachersalary_form");
			var url = "${ctp}/personnel/teacherSalary/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/personnel/teacherSalary/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						if(parent.core_iframe != null) {
 							parent.core_iframe.window.location.reload();
 						} else {
 							parent.window.location.reload();
 						}
						$.closeWindow();
					} else {
						$.error("操作失败");
					}
				}else{
					$.error("操作失败");
				}
				loader.close();
			});
		}
	}
	
</script>
</html>