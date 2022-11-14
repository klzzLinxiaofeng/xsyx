<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<div id="oasponsor_list_content">
								<jsp:include page="./summary_list_page.jsp" />
								</div>
								
								
								<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="oasponsor_list_content" />
								<jsp:param name="url" value="/office/ajax/meeting/sponsorList?sub=list&dm=${param.dm}" />
								<jsp:param name="pageSize" value="${page.pageSize}" />
							</jsp:include>
							<div class="clear"></div>
							
							
		<script type="text/javascript">
		function loadSummaryPage(id) {
			 
			$.initWinOnTopFromTop('发布纪要', '${ctp}/office/meeting/summary/' + id, '900');
		}
		
		function showSummaryPage(id) {
			 
			$.initWinOnTopFromTop('纪要详情', '${ctp}/office/meeting/summary/info/' + id, '900');
		}
		
 
		 
		
//	 	删除对话框
		function delSummaryPage(obj, id) {
			$.confirm("确定执行此次操作？", function() {
				executeDel(obj, id);
			});
		}

		// 	执行删除
		function executeDel(obj, id) {
			$.post("${ctp}/office/meeting/summary/del/" + id, {"_method" : "delete"}, function(data, status) {
				if ("success" === status) {
					if ("success" === data) {
						$.success("删除成功");
						meetingSummaryModal();
					} else if ("fail" === data) {
						$.error("删除失败，系统异常", 1);
					}
				}
			});
		}
	 
</script>