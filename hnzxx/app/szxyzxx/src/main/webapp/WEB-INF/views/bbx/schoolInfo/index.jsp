<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link rel="stylesheet" href="${ctp}/res/css/extra/add.css">
<link rel="stylesheet" href="${ctp}/res/css/bbx/bbx.css">
<title></title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets">
					<div class="widget-head">
						<div class="widget-head">
							<div class="select_top">
								<div class="s1 s1_bg">
									<div class="click">
										<button class="btn btn-warning" type="button" onclick="loadCreatePage();">发布</button>
									</div>
								</div>
							</div>
						</div>												
					</div>			
				</div>
			</div>
			
			<div>
            	<div id="schoolInfo_list_content">
					<jsp:include page="./list.jsp" />
				</div>
				<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
					<jsp:param name="id" value="schoolInfo_content" />
					<jsp:param name="url" value="/bbx/schoolInfo/index?sub=list&dm=${param.dm}" />
					<jsp:param name="pageSize" value="${page.pageSize}" />
				</jsp:include>
				<div class="clear"></div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		search();
	})
	
	function search() {
		var val = {};
		var id = "schoolInfo_list_content";
		var url = "/bbx/schoolInfo/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}


	//	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft_bbx('发布校园信息', '${ctp}/bbx/schoolInfo/creator', '700', '395');
	}

	//  加载编辑对话框
 	function loadEditPage(id) {
		$.initWinOnTopFromLeft_bbx('编辑', '${ctp}/bbx/schoolInfo/editor?id=' + id, '700', '395');
	}






	

	
	
	
	
	
 	/*	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/bbx/bpBwInfoMore/blackboard/viewer?id=' + id, '700', '300');
	} */
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/bbx/schoolInfo/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					//$("#" + id + "_tr").remove();
					search();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
</script>
</html>