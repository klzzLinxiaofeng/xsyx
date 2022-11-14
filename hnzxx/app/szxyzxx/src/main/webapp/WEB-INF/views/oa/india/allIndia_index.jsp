<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>

<div class="widget-head">
	<h3>
		<p class="btn_link" style="float: right;">
			<a href="javascript:void(0)" class="a3" onclick="reload();"><i
				class="fa  fa-undo"></i>刷新列表</a> 
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
				</tr>
			</thead>
			<tbody id="india_list_content">
				<jsp:include page="./allIndia_list.jsp" />
			</tbody>
		</table>
		<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
			<jsp:param name="id" value="india_list_content" />
			<jsp:param name="url"
				value="/office/ajax/allIndia/allIndia_list?sub=allIndia_list&dm=${param.dm}" />
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

function reload(){
	window.self.location.reload(); 
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
