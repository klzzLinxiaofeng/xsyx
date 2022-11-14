<%@include file="/views/embedded/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${items}" var="item">
	<tr id="${item.id}_tr">
				<td><input type="checkbox" onclick="singleClick(this);" name="single" value="${item.code}"/></td>
				<td>${item.name}</td>
				<td>${item.subjectName}</td>
				<td>${item.stageName}</td>
				<td>${item.versionName}</td>
				<td title="${item.description}">
					<c:choose>
	    				<c:when test="${fn:length(item.description)>15}">${fn:substring(item.description,0,15)}...</c:when>
	    				<c:otherwise>${item.description}</c:otherwise>
	    			</c:choose>
				</td>
	    		<td>
	    			<fmt:formatDate value="${item.createDate}" pattern="yyyy/MM/dd" />
	    		</td>
		
		<td class="caozuo">
			<button class="btn btn-green" type="button" onclick="catalogManage('${item.id}');">管理知识点</button>
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${item.id}');">编辑</button>
			<button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
		</td>
	</tr>
</c:forEach>
<c:if test="${empty items}">
	<tr>
		<td colspan="8">目前暂无数据</td>
	</tr>
</c:if>
<script type="text/javascript">
$(function(){
	$("#checkAll").click(function(){
		if(this.checked){    
	        $("#knowledgeVersion_list_content :checkbox").prop("checked", true);
	        $("#knowledgeVersion_list_content :checkbox").attr("checked", true);
	    }else{    
	        $("#knowledgeVersion_list_content :checkbox").prop("checked", false);
	        $("#knowledgeVersion_list_content :checkbox").attr("checked", false);
	    }  
	});
	var dataLength = "${fn:length(items)}";
	if(dataLength == 0){
		$("#page_div").hide();
	}else{
		$("#page_div").show();
	}
});
function singleClick(obj){
	var status = $(obj).attr("checked");
	if(status === "checked"){
		$(obj).prop("checked", false);
		$(obj).attr("checked", false);
	}else{
		$(obj).prop("checked", true);
		$(obj).attr("checked", true);
	}
}
</script>