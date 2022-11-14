<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>

<div class="widget-head">
	<h3>
		<p class="btn_link" style="float: right;">
			<a href="javascript:void(0)" class="a3" onclick="$.refreshWin();"><i
				class="fa  fa-undo"></i>刷新列表</a> <a href="javascript:void(0)" class="a4"
				onclick="loadCreatePage();"><i class="fa fa-plus"></i>创建</a>
		</p>
	</h3>
</div>
<c:choose>
	<c:when test="${!empty items }">
		<table class="responsive table table-striped" id="data-table">
			<thead>
				<tr role="row">
					<th>序号</th>
					<th>标题</th>
					<th>部门</th>
					<th>联系人</th>
					<th>联系方式</th>
					<th>打印状态</th>
					<th>说明(附注)</th>
					<th>文件</th>
					<th>创建时间</th>
					<th></th>
					<th class="caozuo" style="max-width: 250px;">操作</th>
				</tr>
			</thead>
			<tbody id="india_list_content">
				<jsp:include page="./list.jsp" />
			</tbody>
		</table>
		<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
			<jsp:param name="id" value="india_list_content" />
			<jsp:param name="url"
				value="/office/ajax/india/list?sub=list&dm=${param.dm}" />
			<jsp:param name="pageSize" value="${page.pageSize}" />
		</jsp:include>
		<div class="clear"></div>
	</c:when>
	<c:otherwise>
		<table class="table table-bordered table-hover">
			<tr>
				<td colspan="10"><div align="center">暂无数据！</div></td>
			</tr>
		</table>
	</c:otherwise>
</c:choose>


<script type="text/javascript">

	//	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${pageContext.request.contextPath}/office/india/creator', '700', '500');
	}
	
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${pageContext.request.contextPath}/office/india/editor?id=' + id, '700', '500');
	}
	
// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${pageContext.request.contextPath}/office/india/" + id, {"_method" : "delete"}, function(data, status) {
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
	
	//执行下载
	
	function download(id){
		var url = "${pageContext.request.contextPath}/office/ajax/india/download";
		var aj = $.ajax({
			url : url,
			data : 'id=' + id,
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(data) {
				$.alert("已下载");

			},
			error : function() {
				$.alert("异常！");
			}
		});
	}
	//执行打印
	function downloadPrint(id){
		var url = "${pageContext.request.contextPath}/office/ajax/india/downloadPrint";
		var aj = $.ajax({
			url : url,
			data : 'id=' + id,
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(data) {
				$.alert("已打印");

			},
			error : function() {
				$.alert("异常！");
			}
		});
		
	}
</script>