<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<title>教师列表</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="教师管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							教师列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" onclick="$.refreshWin();" class="a3"><i class="fa  fa-undo"></i>刷新列表</a>
								<!-- <a href="javascript:void(0)" onclick="uploadTeacherInfo();" class="a4"><i class="fa  fa-arrow-up"></i>导入教师信息</a> -->
								<a href="javascript:void(0)" onclick="exportTeacherInfoPage();" class="a1"><i class="fa  fa-arrow-down"></i>导出教师信息</a>
								
								<%--<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
									<a href="javascript:void(0)" class="a2" onclick="loadCreatePage();"><i class="fa fa-plus"></i>新增教师信息</a>
								</c:if>--%>
							</p>
						</h3>
					</div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>姓名：</span><input type="text" id="name" name="name" data-id-container="name" style="margin-bottom:0;  width: 120px; margin-right: 3px;" placeholder="" data-id="" value="">
								</div>
								<div class="select_div">
									<span>手机号码：</span><input type="text" id="mobile" name="mobile" data-id-container="mobile" style="margin-bottom:0;  width: 120px; margin-right: 3px;" placeholder="" data-id="" value="">
								</div>
								<div class="select_div">
									<span>教师状态：</span>
									<select id="jobState">
										<option value="11">在职</option>
										<option value="07">离职</option>
										<option value="01">退休</option>
									</select>
								</div>
								<div class="select_div">
									<span>职务：</span><input type="text" id="zhiwu" name="zhiwu" data-id-container="mobile" style="margin-bottom:0;  width: 120px; margin-right: 3px;" placeholder="" data-id="" value="">
								</div>
								<button onclick="search()" class="btn btn-primary" type="button">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
							<thead>
								<tr role="row">
									<th style="width:215px;">姓名</th>
									<th style="width:215px;">工号</th>
									<th style="width:215px;">教育云卡号</th>
									<th style="width:215px;">食堂卡号</th>
		                            <th style="width:100px;">用户名</th>
		                            <th style="width:100px;">性别</th>
		                            <th style="width:100px;">手机号码</th>
									<th style="width:100px;">状态</th>
		                            <!-- <th>办公室电话</th> -->
		                            <th  style="width:100px;">职务</th>
		                            <!-- <th>工号</th> -->
		                             <th>上课特色</th>
			                        <th class="caozuo" style=" width:210px;">操作</th>
								</tr>
							</thead>
							<tbody id="module_list_content">
								<jsp:include page="./list.jsp" />
							</tbody>
						</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
							<jsp:param name="id" value="module_list_content" />
							<jsp:param name="url" value="/teach/teacher/teacherList?sub=list" />
							<jsp:param name="pageSize" value="${page.pageSize}" />
						</jsp:include>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function search() {
		var val = {};
		var name = $("#name").val();
		var mobile = $("#mobile").val();
		var jobState = $("#jobState").val();
		var zhiwu=$("#zhiwu").val();
		if (name != null && name != "") {
			val.name = name;
		}
		if(jobState != null && jobState != ""){
			val.jobState = jobState;
		}
		if(mobile != null && mobile != ""){
			val.mobile = mobile;
		}
		if(zhiwu != null && zhiwu != ""){
			val.position = zhiwu;
		}
		
		var id = "module_list_content";
		var url = "/teach/teacher/teacherList?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}
	
	function uploadTeacherInfo(){
		$.initWinOnTopFromTop("教师信息导入", "${pageContext.request.contextPath}/teach/teacher/upLoadTeacherInfoPage", '900');
	}
	
	function exportTeacherInfoPage(){
		
		var url = "${pageContext.request.contextPath}/teach/teacher/exportTeacherInfoPage";
		
		var name  = $("#name").val();
		var data = "?name="+name
		url = url+data;
		url =  encodeURI(url);
		$.initWinOnTopFromTop("教师基本信息导出", url, '742','500');
		
		
		//$.initWinOnTopFromTop("教师基本信息导出", "${pageContext.request.contextPath}/teach/teacher/exportTeacherInfoPage", '742','500');
	}
	
	/*//	加载创建角色对话框
	function loadCreatePage() {
		$.initWinOnTopFromTop("新增教师信息", "${pageContext.request.contextPath}/teach/teacher/addTeacherPage", '1000');
	}*/
	
	function loadModifyTeacherPage(id){
		$.initWinOnTopFromTop("修改教师信息", "${pageContext.request.contextPath}/teach/teacher/modifyTeacher?id="+id, '1000');
	}
	
	function loadModifyTeacherPhotoPage(id){
		$.initWinOnTopFromTop("教师头像信息", "${pageContext.request.contextPath}/teach/teacher/modifyTeacherPhoto?id="+id, '800', '600');
	}
	
	function deleteTeacher(id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(id);
		});
	}
	
	function executeDel(id) {
		$.post("${pageContext.request.contextPath}/teach/teacher/deleteTeacher?id=" + id, {"_method" : "delete"}, function(data, status) {
			if("success" === status) {
				if("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
					window.location.reload();
				} else if("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}else{
				$.error("系统异常");
			}
		});
	}
</script>
</html>