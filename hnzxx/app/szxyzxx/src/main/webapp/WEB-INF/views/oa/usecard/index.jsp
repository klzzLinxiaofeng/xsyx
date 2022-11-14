<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<title></title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="借车" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							借车列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
								<c:if test="${type=='' || type==null}">
									<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
									<a href="javascript:void(0)" class="a4"
										onclick="loadCreatePage();"><i class="fa fa-plus"></i>创建</a>
									</c:if>
								</c:if>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>车牌号：</span>
									<input type="text" id="plateNumber" name="plateNumber" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value="">
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>序号</th>
											<th>车牌号</th>
											<th>用车人员</th>
											<th>出车时间</th>
											<th>申请人</th>
											<th>审批人</th>
											<th>用途</th>
											<th>状态</th>
											<th>备注</th>
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="usecard_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<c:if test="${type != null && type != ''}">
								<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
									<jsp:param name="id" value="usecard_list_content" />
									<jsp:param name="url" value="/oa/usecard/index?sub=list&type=shenpi&dm=${param.dm}" />
									<jsp:param name="pageSize" value="${page.pageSize}" />
								</jsp:include>
							</c:if>
							<c:if test="${type == null || type == ''}">
								<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
									<jsp:param name="id" value="usecard_list_content" />
									<jsp:param name="url" value="/oa/usecard/index?sub=list&dm=${param.dm}" />
									<jsp:param name="pageSize" value="${page.pageSize}" />
								</jsp:include>
							</c:if>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="type" value="${type}"/>
</body>
<script type="text/javascript">
	function search() {
		var val = {
				"plateNumber" : $("#plateNumber").val()
		};
		var id = "usecard_list_content";
		var url = "/oa/usecard/index?sub=list&dm=${param.dm}";
		if($("#type").val()!=null && $("#type").val()!=""){
			url = "/oa/usecard/index?sub=list&type=shenpi&dm=${param.dm}";
		}
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${ctp}/oa/usecard/creator', '900', '800');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/oa/usecard/editor?id=' + id, '900', '800');
	}
	//加载查看对话框
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/oa/usecard/viewer?id=' + id, '700', '500');
	}
	//加载审批借车还车对话框
	function loadAuditPage(id,type){
		//type 审批类型：借车（1）  还车（3）   id 车辆的唯一主键
		var url = "${ctp}/oa/auditcard/shenpi?returnOrUseId=" + id +"&auditType=" + type;
		$.initWinOnTopFromLeft('审批', url, '700', '500');
	}
	//加载还车对话框
	function loadReturnPage(id){
		var auditOption = $.prompt("请输入还车说明 (必填)", function(data) {
			$.post("${ctp}/oa/returncard/creator", {
				"usecardId" : id,
				"returnExplain" : data
			}, function(data, status) {
				if("success" === status) {
					data = eval("(" + data + ")");
					if("success" === data.info) {
						$.success('审批成功');
						window.location.reload();
					} else {
						$.error("审批失败");
					}
				}else{
					$.error("审批失败");
				}
			});
		}, function() {});
	}
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/oa/usecard/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
</script>
</html>