<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>菜单创建</title>
<%@ include file="/views/embedded/common.jsp"%>
<style>
.row-fluid .span13 {
	width: 75%;
}
.myerror {
	color: red !important;
	width:22%;
	display:inline-block;
	padding-left:10px;
}
</style>
</head>
<body style="background-color:#cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<form class="form-horizontal" id="role_form">
											<div class="control-group">
							<label class="control-label">学校:</label>
							<div class="controls">
								<select class="span13" id="groupId" name="groupId">

								</select>
							</div>
						</div>	
						
						<div class="control-group">
							<label class="control-label">角色:</label>
							<div class="controls">
								<select class="span13" id="roleId" name="roleId">
<%-- 									<c:forEach items="${roles}" var="role"> --%>
<%-- 										<option value="${role.id}">${role.name}</option> --%>
<%-- 									</c:forEach> --%>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">优先级</label>
							<div class="controls">
								<input type="text" id="priority" name="priority"
									class="span13" placeholder="请输入优先级,必须为数字" value="${ur.priority}">
							</div>
						</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${ur.id}"/>
							<input type="hidden" id="userId" name="userId" value="${param.userId != null ? param.userId : ur.user.id}" />
							<button class="btn btn-warning" type="button" onclick="saveOrUpdate();">确定</button>
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
		
		 
		initGroupSelect();
		//jQuery("#groupId  option:selected").text();
		
		
	    $("#groupId").change(function(){
			initRoleSelect($("#groupId").val());
		});

	    //默认优先级
		$("#roleId").change(function () {
			initPriority($("#roleId").find("option:selected").data("code"));
        });
	});
	
	function initValidator() {
		return $("#role_form").validate({
			errorClass : "myerror",
			rules : {
				"roleId" : {
					required : true,
				},
				"priority" : {
					required : true,
					isInteger : true,
					remote : {
					    async : false,
						type : "get",
						url : "${pageContext.request.contextPath}/sys/ur/priority/checker",
						data : {
							"userId" : $("#userId").val(),
							"groupId" : function () {
								return $("#groupId").val()
                            },
							"priority" : function () {
                                return $("#priority").val()
                            }
                        }
					}
				},
			},
			messages : {
				"priority" : {
                    isInteger:  "必须为整数",
                    remote : "和已有角色优先级重复"
                }
			}
		});
	}
	
	function initGroupSelect(){
		var $requestData = {};
		$requestData.id = '${sessionScope[sca:currentUserKey()].groupId}';
		$.getGroup($requestData, function(data) {
			$("#groupId").empty();
			if(data.length > 1){
				$("#groupId").append("<option value='0'>" + '平台本身' + "</option>");
				$.each(data, function(index, value) {
					$("#groupId").append("<option value='" + value.id + "'>" + value.name + "</option>");
				});
			}else if(data.length == 1){
				$.each(data, function(index, value) {
					$("#groupId").append("<option value='" + value.id + "'>" + value.name + "</option>");
				});
				
			}else{
				$("#groupId").append("<option value=''>" + '请选择' + "</option>");
			}
			initRoleSelect($("#groupId").val());
		});
	}
	
	function initRoleSelect(groupId) {
		var $requestData = {};
		$requestData.usePage = false;
		$requestData.groupId=groupId;
		$("#roleId").empty();
		$.get("${pageContext.request.contextPath}/sys/role/list/json", $requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				$.each(data, function(index, value) {
					$("#roleId").append("<option value='" + value.id + "' data-code='" + value.code + "'>" + value.name + "</option>");
				});
				$("#roleId").find("option[value='${ur.role.id}']").prop("selected", true);
                initPriority($("#roleId").find("option:selected").data("code"));
			} 
		});
	}
	
	function initPriority(code) {
	    var priority = 0;
	    switch (code) {
			case "SCHOOL_OPERATOR" : priority = 99;	break;
			case "SCHOOL_MANAGER" : priority = 90;	break;
			case "SCHOOL_LEADER" : priority = 80;	break;
			case "SCHOOL_MASTER" : priority = 70;	break;
			case "CLASS_MASTER" : priority = 60;	break;
			case "SUBJECT_TEACHER" : priority = 50;	break;
			case "TEACHER" : priority = 30;	break;
			case "STUDENT" : priority = 35;	break;
			case "PARENT" : priority = 40;	break;
			case "STUDENT_MANAGER" : priority = 20;	break;
			case "SCHOOL_EDU_STAFF" : priority = 19;	break;
			case "SCHOOL_RESOURCE_STAFF" : priority = 18;	break;
			case "SCHOOL_AFFAIRE_STAFF" : priority = 17;	break;
			case "SCHOOL_SUPPORT_STAFF" : priority = 16;	break;
			case "SCHOOL_REPAIR_STAFF" : priority = 15;	break;
			case "SCHOOL_PRINT_STAFF" : priority = 14;	break;
			case "SCHOOL_WEB_EDITOR" : priority = 13;	break;
			case "SCHOOL_WEB_MANAGER" : priority = 12;	break;
			case "ENTROLL_STUDENT" : priority = 11;	break;
		}
		$("#priority").val(priority);
    }
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = {};
			$requestData.priority = $("#priority").val();
			$requestData.roleId = $("#roleId").val();
			$requestData.userId = $("#userId").val();
			var url = "${pageContext.request.contextPath}/sys/ur/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${pageContext.request.contextPath}/sys/ur/" + $id;
			}
			
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success("操作成功");
					if("success" === data) {
						if(parent.core_iframe != null) {
							parent.core_iframe.window.location.reload();
						} else {
							parent.window.location.reload();
						}
					} else {
						$.error('操作失败');
					}
					$.closeWindow();
				} else {
					$.error("操作失败");
				}
				loader.close();
			});
			
		}
	}
</script>
</html>