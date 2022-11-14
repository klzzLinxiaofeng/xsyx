<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
	
<p class="title" style="margin-bottom:10px;">搜索结果<span style="font-size:12px;">（${page.totalRows}个结果）</span></p>
<table class="table table-bordered responsive white">
	<thead>
		<tr>
			<c:if test="${param.enableBatch}">
				<th style="text-align:center"><input type="checkbox" id="checkAll"/></th>
			</c:if>
			<th>序号</th>
			<th>姓名</th>
			<th>性别</th>
			<th >联系方式</th>
<!-- 			<th>所在部门</th> -->
			<th>在职状态</th>
			<th style="width:126px;">操作</th>
		</tr>
	</thead>
	<tbody id="ryxx_trs">
		<jsp:include page="list2.jsp"></jsp:include>
	</tbody>
</table>
<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
	<jsp:param name="id" value="ryxx_trs" />
	<jsp:param name="url" value="/teach/teacher/list?type=2&usePage=true&name=${nameParam}&schoolId=${schoolIdParam }&deptId=${deptIdParam}&enableBatch=${enableBatch}&subjectCode=${subjectCodeParam}&excludeSelf=${excludeSelf}&enableMultiCampus=${enableMultiCampus }&dm=${param.dm}" />
	<jsp:param name="pageSize" value="${page.pageSize}" />
</jsp:include>
<script type="text/javascript">
	$(function(){
		$("#teacher_list #ryxx_trs").on("click","input",function(){
			var $value = $(this).val();
			var length = $("#cancelBubble_" + $value).length;
			$("#clear_button").remove();
			if(length <= 0){
				$("#batch_data").append("<span class='cancelBubble' id='cancelBubble_" + $value + "'></span>");
				$("#cancelBubble_" + $value).append("<span style='color:#434343;'>" + $(this).attr("data-name") + "</span>");
				$("#cancelBubble_" + $value).append("<a class='close_1 fa fa-remove' data-id='"+ $value + "' onclick='move(this)'></a>");
			}else{
				$("#cancelBubble_" + $value).remove();
			}
			$("#batch_data").append('<button id="clear_button" class="btn btn-danger" onclick="batchClear();">清除</button>');
			
		});
		$(".cancelBubble").bind("click",function(){
			return false;
		});
	});
	function move(obj){
		$(obj).parent().remove();
		$("input:checkbox[value='" + $(obj).attr("data-id") + "']").prop({checked:false});
	}
	function batchClear(){
		$("#batch_data").empty();
		$("#ryxx_trs").find("input:checkbox:checked").prop({checked:false});
		$("#checkAll").prop({checked:false});
// 		$("#batch_data").append('<button id="clear_button" class="btn btn-danger" onclick="batchClear();">清除</button>');
	}
</script>