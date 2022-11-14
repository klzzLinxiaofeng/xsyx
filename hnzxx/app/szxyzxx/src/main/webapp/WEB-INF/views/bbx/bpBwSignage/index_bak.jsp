<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet">
<title></title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets">
					<div class="select_top">
						<div class="s1 s1_bg" id="schoolManagerSearch">	
							<div hidden><select id="xn"></select></div>
							<select id="nj" name="gradeId" style="width:160px;"></select>
							<select id="bj" name="teamId" style="width:160px;"></select>		
							<button class="btn btn-success" style="margin-top: -23px;" id="sosuo" onclick="search()" >查询</button>							
							<div class="content">
								<div class="click">
									<button class="btn btn-warning" type="button"
										onclick="$.refreshWin();">刷新列表</button>
								</div>
							</div>
						</div>
					</div>							
				</div>
				
				
				<div class="content-widgets white">
					<div class="content-widgets">
						<div class="widget-container">
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>设备ID</th>
											<th>设备号</th>
											<th>学校</th>
											<th>班级</th>											
											<th>状态</th>
											<th>创建时间</th>
											<th>修改时间</th>
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="bwSignage_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="bwSignage_list_content" />
								<jsp:param name="url" value="/bbx/bpBwSignage/index?sub=list&dm=${param.dm}" />
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
	$(function() {	  
		$.initCascadeSelector({
			"type" : "team",
			"teamCallback" : function($this) {
				search();
			}
		});
	});
	

	function search() {	
		var val = {};
		var gradeId = $("#nj").val();
		var teamId = $("#bj").val();
		/* if(gradeId != "" && teamId ==""){		
			$.error("请选择年级");
			return;
		} */
		if (gradeId != null && gradeId != "") {
			val.gradeId = gradeId;
		}
		if (teamId != null && teamId != "") {
			val.teamId = teamId;
		}
		var id = "bwSignage_list_content";
		var url = "/bbx/bpBwSignage/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	/* // 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${ctp}/bbx/bpBwSignage/creator', '700', '290');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/bbx/bpBwSignage/editor?id=' + id, '700', '300');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/bbx/bpBwSignage/viewer?id=' + id, '700', '300');
	} */
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/bbx/bpBwSignage/" + id, {"_method" : "delete"}, function(data, status) {
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