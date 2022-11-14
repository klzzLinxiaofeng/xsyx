<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css"
	rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<script type="text/javascript">
</script>
</head>
    <input type="hidden" id="paperId" value="${paperId }"/>
	<div class="figure">
		<div id="yuedu_list_content">
			<jsp:include page="./yuedu_list.jsp" />
		</div>

<!-- 		<div style="margin-top: 20%;"> -->
<%-- 			<jsp:include page="/views/embedded/jqpagination.jsp" flush="true"> --%>
<%-- 				<jsp:param name="id" value="yuedu_list_content" /> --%>
<%-- 				<jsp:param name="url" --%>
<%-- 					value="/office/ajax/paper/yuedu_index?sub=yuedu_list&dm=${param.dm}&paperId=${paperId}&type=${type }" /> --%>
<%-- 				<jsp:param name="pageSize" value="${page.pageSize }" /> --%>
<%-- 			</jsp:include> --%>
<!-- 			<div class="clear"></div> -->
<!-- 		</div> -->

	</div>
<script type="text/javascript">
	function searchByCondition(type) {
		var paperId = $("#paperId").val();
		$.ajax({
			type : "post",
			url : "${ctp}/office/ajax/paper/yuedu_index?sub=yuedu_list&dm=${param.dm}",
			data : {"paperId":paperId,"type":type},
			dataType : "html", 
			success : function(data) {
				$("#yuedu_list_content").empty();
				$("#yuedu_list_content").append(data);
			}
		});
		
		if (type == "weiyuedu") {
			$("#yiyuedu").removeClass();
			$("#weiyuedu").addClass("on");
		} else {
			$("#weiyuedu").removeClass();
			$("#yiyuedu").addClass("on");
		}
		
// 		this.location.href = "${ctp}/office/ajax/paper/yuedu_index?dm=${param.dm}&paperId="+paperId+"&type="
// 				+ type;
	}
	
</script>