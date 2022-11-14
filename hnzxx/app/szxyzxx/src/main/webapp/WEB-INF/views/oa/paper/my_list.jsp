<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<div id="oamy_list_content">
								<jsp:include page="./my_list_page.jsp" />
								</div>
								
								
								<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="oamy_list_content" />
								<jsp:param name="url" value="/office/ajax/paper/myList?sub=list&dm=${param.dm}" />
								<jsp:param name="pageSize" value="${page.pageSize}" />
							</jsp:include>
							<div class="clear"></div>
							
							
		<script type="text/javascript">
 
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/office/paper/del/" + id, {"_method" : "delete"}, function(data, status) {
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
 
	function loadViewerPage(id) {
	 
		$.initWinOnTopFromTop('详情', '${ctp}/office/paper/viewer/' + id, '900');
	}
 
</script>