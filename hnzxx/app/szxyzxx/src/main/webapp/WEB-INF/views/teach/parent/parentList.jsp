<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/css/layui.css"
		  media="all">
	<script type="text/javascript"
			src="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/layui.all.js"></script>
<title></title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="家长信息" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							家长信息列表
							<p class="btn_link" style="float: right;">
<%--							<a href="javascript:void(0)" class="a3" onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>--%>
<%--							<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">--%>
<%--							&lt;%&ndash;<a href="javascript:void(0)" onclick="downLoadParentInfo();" class="a1"><i class="fa fa-download"></i>下载家长信息模板</a>&ndash;%&gt;--%>
<%--							<a href="javascript:void(0)" onclick="uploadParentInfo();" class="a3"><i class="fa fa-plus"></i>批量导入家长信息</a>--%>
<%--							</c:if>--%>

							<%--	<a href="javascript:void(0)" id="upBtn" class="a3">导入进出场记录更新车牌</a>
--%>
<%--							<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">--%>
								<a href="javascript:void(0)" class="a2" onclick="downLoadParent();">导出家长信息</a>
<%--							</c:if>--%>
							<%-- <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
							   <a href="javascript:void(0)" onclick="loadCreatePage($('input:radio:checked').val());" class="a4"><i class="fa  fa-plus"></i>添加家长信息</a>
							</c:if> --%>
							
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							    <div class="select_b" id="parent_form">
									<div class="select_div" ><span>学年： </span><select id="xn" name="schoolYear" class="chzn-select"style="width:160px;"></select></div>
									<div class="select_div" ><span>年级： </span><select id="nj" name="gradeId" class="chzn-select" style="width:120px;"></select></div>
									<div class="select_div" ><span>班级： </span><select id="bj" name="teamId" class="chzn-select" style="width:160px;"></select></div>
									<div class="select_div" style="display: none;"><span>学生： </span><select id="stu" name="studentId" class="chzn-select" style="width:160px;"></select></div>
									<div class="select_div" ><span>学生： </span><input  type="text" name="student" id ="student" style="width:160px;" placeholder="学生姓名" /></div>
								    <div class="select_div" ><span>家长电话： </span> <input  type="text" name="mobile" id ="mobile" style="width:160px;" placeholder="电话号码" /></div>
								    <div class="select_div" ><span>家长车牌： </span> <input  type="text" name="licensePlate" id ="licensePlate" style="width:160px;" placeholder="家长车牌" /></div>

<%--								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">--%>
								
								  <button type="button" class="btn btn-primary" onclick="search()">查询</button>
<%--								</c:if>--%>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											
											<th>学生姓名</th>
											<th>班级</th>
											<th>一卡通卡号</th>
											<th>家长姓名</th>
											<th>与学生关系</th>
											<th>是否主监护人</th>
											<th>家长用户名</th>
											<th>家长手机号码</th>
											<th>家长车牌</th>
											<th>家长邮箱</th>

										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="parent_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="parent_list_content" />
								<jsp:param name="url" value="/teach/parent/parentList?sub=list&dm=${param.dm}" />
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
	var sum;
	$(function() {
		/* $.initCascadeSelector({
			"type":"stu"

	  }); */

		$.initCascadeSelector({
			"type" : "stu",
			"yearCallback" : function($this){
				search();
			}
			
		});

		
		
		
	});


	layui.use('upload', function(){
		var upload = layui.upload;

		//执行实例
		var uploadInst = upload.render({
			elem: '#upBtn' //绑定元素
			,url: '/teach/parent/updateLp' //上传接口
			//,acceptMime:'application/csv,text/csv,text/x-csv,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
			,exts:'csv|xls|xlsx'
			,data:{
				schoolYear:function(){
					return $("#xn").val();
				}
			}
			,before: function(obj){
				var upBtn=$("#upBtn");
                upBtn.html("正在处理文件中......");
                upBtn.attr("disabled","disabled");
			}
			,done: function(res){
				var upBtn=$("#upBtn");
                upBtn.html("导入进出场记录更新车牌");
                upBtn.removeAttr("disabled");
				layer.closeAll('loading'); //关闭loading
				if(res.code==200) {
					layer.msg('更新成功', {
						icon: 1,
						time: 2000 //2秒关闭（如果不配置，默认是3秒）
					}, function () {
						search();
					});
				}else{
					if(res.code==300){
						res.msg='车牌更新失败列表：</br>'+res.msg;
					}
					layer.alert(res.msg,function(ind){
						layer.closeAll();
						if(res.code==300) {
							search();
						}
					});
				}
			}
		});
	});



	
	function search() {
		var val = {};
		var studentName = $("#student").val();
		var teamId = $("#bj").val();
		var schoolYear = $("#xn").val();
		var gradeId = $("#nj").val();
		var mobile = $("#mobile").val();
		var licensePlate = $("#licensePlate").val();

		if (studentName != null && studentName != "") {
			val.studentName = studentName;
		}
		if (gradeId != null && gradeId != "") {
			val.gradeId = gradeId;
		}
		if (teamId != null && teamId != "") {
			val.teamId = teamId;
		}
		if (schoolYear != null && schoolYear != "") {
			val.schoolYear = schoolYear;
		}
		
		if (mobile != null && mobile != "") {
			val.mobile = mobile;
		}
		if (licensePlate != null && licensePlate != "") {
			val.licensePlate = licensePlate;
		}
		var id = "parent_list_content";
		var url = "/teach/parent/parentList?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}
	
	//导出对话框
	function downLoadParentInfo(){
		$.initWinOnTopFromLeft("导出家长信息模板", "${pageContext.request.contextPath}/teach/parent/downLoadParentPage", '600', '400');
	}
	
	//导出对话框
	function downLoadParent(){
		$.initWinOnTopFromLeft("导出学生家长信息", "${pageContext.request.contextPath}/teach/parent/downLoadStudentParentPage", '600', '400');
	}
	//导入对话框
	function uploadParentInfo(){
		<%--$.initWinOnTopFromLeft("家长信息导入", "${pageContext.request.contextPath}/teach/parent/upLoadParentInfoPage", '800', '700');--%>
		window.location.href = "${pageContext.request.contextPath}/teach/parent/import";
	}
	// 	加载创建对话框
	function loadCreatePage(id) {
		$.initWinOnTopFromLeft('创建', '${ctp}/teach/parent/addParentPage?id=' + id + '&schoolYear=' + $("#xn").val(), '900', '465');
	}
	//  加载编辑对话框
	function loadEditPage(id,studentId) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/teach/parent/modifyParentPage?id=' + id + "&studentId=" + studentId + '&schoolYear=' + $("#xn").val(), '900', '465');
	}
	
	function deleteRelate(id,studentId, studentName, name) {
		var info = "是否解除"+name+"与"+studentName+"的关系？解除后可能导致家长数据丢失，请谨慎操作！";
		$.confirm(info, function() {
			executeDelRelate(id,studentId);
		});
	}
	
	//  加载编辑对话框
	function executeDelRelate(id,studentId) {
		var loader = new loadDialog();
        loader.show();
		$.ajax({
	        url: "${ctp}/teach/parent/deleteRelate",
	        type: "POST",
	        data: {"id":id, "studentId":studentId},
	        async: true,
	        success: function(result) {
	        	loader.close();
				if ("success" == result) {
					$.success("解绑成功");
					if(parent.core_iframe != null) {
						parent.core_iframe.window.location.reload();
					} else {
						parent.window.location.reload();
					}
				} else  {
					$.error(result);
				}
	        }
	    });
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/teach/parent/detailParentPage?id=' + id, '700', '600');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/teach/parent/detailParentPage?id=' + id, '700', '600');
	}
	

	
	// 	删除对话框
	function deleteObj(obj, id,studentId) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id,studentId);
		});
	}

	// 	执行删除 $("#" + id + "_tr").remove();
	function executeDel(obj, id,studentId) {
		$.post("${ctp}/teach/parent/deleteParent?id=" + id+'&studentId='+studentId, {"_method" : "delete"}, function(data, status) {

			if ("success" === status) {

				if ("success" === data) {
					$.success("删除成功");
					if(parent.core_iframe != null) {
						parent.core_iframe.window.location.reload();
					} else {
						parent.window.location.reload();
					}
					//search();
				} else  {
					$.error(data);
				}
			}else{
				$.error("删除失败，系统异常");
			}
		});
	}

</script>
</html>