<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/zTree.jsp"%>
<%@ include file="/views/embedded/plugin/dept_selector_js.jsp"%>
<title>创建考核任务</title>
<style>
	.row-fluid .span4 {
	width: 227px;
}

.myerror {
	color: red !important;
	width: 34%;
	display: inline-block;
	padding-left: 10px;
}

.jfx{}
.jfx .jf_top{height:35px;z-index:2;position:relative}
.jfx .jf_top ul{margin:0}
.jfx .jf_top ul li{width:72px;height:33px;background-color:#F3F3F3;border:1px solid #BABBBB;position:relative;line-height:33px;float:left}
.jfx .jf_top ul li.on{background-color:#fff;border-bottom:1px solid #fff;}
.jfx .jf_top ul li a{color:#666666;display:block;padding-left:31px;}
.jfx .jf_top ul li input{position:absolute;left:10px;top:9px;margin:0;}
.jfx .jf_main{}
.jfx .jf_main .jf_xian{width:580px;min-height:44px;padding:18px 10px;background-color:#fff;border:1px solid #BABBBB;    position: relative;top: -1px;}
.jfx .jf_main .jf_xian ul{margin:0}
.jfx .jf_main .jf_xian ul li{float:left;}
.jfx .jf_main .jf_xian ul li input{position:relative;margin:0 10px;}
.select_p input{position:relative;margin:0 5px;top:-1px;}
</style>
</head>
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<jsp:useBean id="now" class="java.util.Date"></jsp:useBean>
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<form class="form-horizontal" id="aptTask_form">
						<div class="control-group">
							<label class="control-label"><span class="red">*</span>考核任务名称</label>
							<div class="controls">
								<input type="text" id="name" name="name" value="${aptTask.name}" class="span4" placeHolder="考核任务名称">
                            </div>
						</div>
						<div class="control-group">
							<label class="control-label"><span class="red">*</span>考核对象</label>
							<div class="controls">
								<p class="select_p"><input type="radio" value="1" checked="checked" name="scopeType">全校教职工</p>
								<p class="select_p">
									<input type="radio" name="scopeType" value="2">部门 
									<span id="dept_seleted_id" style="position:relative;top:10px;margin-left:10px;"></span>
								</p>
								<p class="select_p">
									<input type="radio" name="scopeType" value="9">
									指定人员：
									<input type="button" id="teacherSeletor" value="选择" style="color: black;background-color: white;" class="btn">
									<p id="teacherNames" style="color: blue;">${teacherNames}</p>
									<input type="hidden" name="teacherIds" value="${teacherIds}" id="teacherIds"/>
								</p>
                            </div>
						</div>
						<div class="control-group">
							<label class="control-label"><span class="red">*</span>考核标准</label>
							<div class="controls">
								<select id="ruleId" name="ruleId" class="span4">
									<option value="">请选择</option>
									<c:forEach items="${rules}" var="rule">
										<option value="${rule.id}" <c:if test="${rule.id == aptTask.ruleId}">selected="selected"</c:if>>${rule.name }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span class="red">*</span>学年</label>
							<div class="controls">
								<select id="schoolYear" name="schoolYear" class="span4">
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span class="red">*</span>学期</label>
							<div class="controls">
								<select id="termCode" name="termCode" class="span4">
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span class="red">*</span>考核阶段开始日期</label>
							<div class="controls">
								<input type="text" id="startDate" name="startDate" value="<fmt:formatDate value="${aptTask.startDate}" pattern="yyyy-MM-dd"/>" class="span4" placeholder="时间, 不能为空" onclick="WdatePicker();">
                            </div>
						</div>
						<div class="control-group">
							<label class="control-label"><span class="red">*</span>考核阶段结束日期</label>
							<div class="controls">
								<input type="text" id="finishDate" name="finishDate" value="<fmt:formatDate value="${aptTask.finishDate}" pattern="yyyy-MM-dd"/>" class="span4" placeholder="时间, 不能为空" onclick="WdatePicker();">
                            </div>
						</div>
						<div class="control-group">
							<label class="control-label">备注</label>
							<div class="controls">
								<textarea name="description" style="height:80px;" class="span4">${aptTask.description}</textarea>
                            </div>
						</div>
						<div class="form-actions tan_bottom">
								<input type="hidden" name="id" id="id" value="${aptTask.id}"/>
								<button class="btn btn-warning" type="button" onclick="saveOrUpdate(this);">确定</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var checker;
	$(function(){
		
		var scopeType = "${aptTask.scopeType}";
		if(scopeType != null){
			$(":radio[value='" + scopeType + "']").click();
		}
		$("#ruleId").chosen();
		//初始化部门下拉选
		$.createDeptSelector({
			"deptContainer" : "#dept_seleted_id",
			"selectedItemId" : "${aptTask.departmentId}" 
		});
		//初始化学年下拉选
		$.getSchoolYear({schoolId : "${schoolId}" , property : "name"},function(data){
			$("#schoolYear").append("<option value=''>请选择</option>");
			$.each(data,function(index,value){
				if(value.id == "${aptTask.schoolYear}"){
					$("#schoolYear").append("<option selected='selected' value='" + value.id + "'>" + value.name + "</option>");
				}else{
					$("#schoolYear").append("<option value='" + value.id + "'>" + value.name + "</option>");
				}
			});
			$("#schoolYear").chosen();
		});
		
		$.getSchoolTerm({schoolId : "${schoolId}"},function(data){
			$("#termCode").append("<option value=''>请选择</option>");
			$.each(data,function(index,value){
				if(value.id == "${aptTask.termCode}"){
					$("#termCode").append("<option selected='selected' value='" + value.id + "'>" + value.name + "</option>");
				}else{
					$("#termCode").append("<option value='" + value.id + "'>" + value.name + "</option>");
				}
			});
			$("#termCode").chosen();
		});
		
		$("#schoolYear").change(function(){
			$.getSchoolTerm({schoolId : "${schoolId}",schoolYearId : this.value},function(data){
				$("#termCode").empty();
				$("#termCode").append("<option value=''>请选择</option>");
				$.each(data,function(index,value){
					$("#termCode").append("<option value='" + value.id + "'>" +  value.name + "</option>");
				});
				$("#termCode").trigger("liszt:updated");
// 				$("#termCode").chosen();
			});
		});
		
		$(".jfx .jf_top ul li a").click(function(){
			var i=$(this).parent().index();
			$(".jfx .jf_top ul li").removeClass("on");
			$(".jfx .jf_top ul li").eq(i).addClass("on")
			$(".jfx .jf_main .jf_xian").hide();
			$(".jfx .jf_main .jf_xian").eq(i).show();
		});
		$(".jfx .jf_top ul li input").click(function(){
		    var j=$(this).parent().parent().index()
		    if($(this).is(":checked")){
		  	   $(".jfx .jf_main .jf_xian").eq(j).children().children().children("input").prop("checked", 'true');	 
            }
            else{
   				$(".jfx .jf_main .jf_xian").eq(j).children().children().children("input").removeAttr("checked");//取消全选
            }
		});
		
		//初始化教师选择弹出框
		var option = {
				"inputIdSelector" : "#teacherSeletor",
				"ry_type" : "teach",
				"enableBatch" : true,
				"layerOp" : {
					"type" : 2,
					"shift" : 'top'
				}
			};
		$.createMemberSelector(option);
		checker = initValidator();
	});
	
	function selectedHandler(data){
		if(data != null && data != "undefined"){
			$("#teacherNames").text(data.names);
			$("#teacherIds").val(data.ids);
			$.closeWindowByName(data.windowName);
		}
	}
	
	function initValidator() {
		return $("#aptTask_form").validate({
			errorClass : "myerror",
			rules : {
				"name" : {
					required : true,
					maxlength : 200
				},
				"ruleId" : {
					selectNone : true
				},
				"schoolYear" : {
					selectNone : true
				},
				"termCode" : {
					selectNone : true
				},
				"startDate" : {
					required : true
				},
				"finishDate" : {
					required : true
				}
			},
			messages : {
				
			}
		});
	}
	
	function checkRadio(){
		var $curSelector = $("input[type='radio']:checked");
		var selectRadio = $curSelector.val();
		if(selectRadio == 2){
			var departmentId = $("#dept_seleted_id").attr("data-id");
			if(departmentId == null || departmentId == ""){
				var $lis = $("#dept_seleted_id").find("li");
				if($lis.length == 1){
					$("#dept_seleted_id").append('<label for="departmentId" generated="true" class="myerror">请创建部门</label>');
				}else{
					$("#dept_seleted_id").append('<label for="departmentId" generated="true" class="myerror">必须选择一项</label>');
				}
				return false;
			}
		}
		if(selectRadio == 9){
			var teacherIds = $("#teacherIds").val();
			if(teacherIds == null || teacherIds == ""){
				$("#teacherNames").append('<label for="teacherIds" generated="true" class="myerror">请选择人员</label>');
				return false;
			}
		}
		return true;
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form() && checkRadio()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#aptTask_form");
			var $curSelector = $("input[type='radio']:checked");
			var selectRadio = $curSelector.val();
			if(selectRadio == 2){
				var departmentId = $("#dept_seleted_id").attr("data-id");
				$requestData.departmentId = departmentId;
			}
			var url = "${ctp}/schoolAffair/aptTask/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/schoolAffair/aptTask/" + $id;
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