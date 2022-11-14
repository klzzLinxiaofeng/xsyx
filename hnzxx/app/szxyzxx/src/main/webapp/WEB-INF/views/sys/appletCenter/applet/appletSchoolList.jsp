<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<c:forEach items="${appletOwnerList}" var ="owner">
	<c:choose>
		<c:when test="${owner.lineType == 1 }">
			<tr data-id="${owner.id}" id="${owner.id}_tr"><td><i class="ck" data-id="${owner.id}"></i></td><td>${owner.province}</td><td>${owner.city}</td><td>${owner.district}</td><td>${owner.schoolName}</td><td class="sj">${owner.lineTypeString}</td><td class="caozuo"><button class="btn btn-oxfordGray apply_xj" onclick="ownerXiaJia(this);">下架</button></td></tr>
		</c:when>
		<c:otherwise>
			<tr data-id="${owner.id}" id="${owner.id}_tr"><td><i class="ck" data-id="${owner.id}"></i></td><td>${owner.province}</td><td>${owner.city}</td><td>${owner.district}</td><td>${owner.schoolName}</td><td class="xj">${owner.lineTypeString}</td><td class="caozuo"><!-- <button class="btn btn-oxfordGray apply_xj" onclick="ownerXiaJia(this);">下架</button> --></td></tr>
		</c:otherwise>
	</c:choose>
</c:forEach>
<tr>
	<td style="padding:0;border:0 none; display:none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr>

<script>

function ownerXiaJia(obj){
	var ownerId = $(obj).parent().parent().data("id");
	$.post("${ctp}/sys/applet/ownerXiaJia/" + ownerId, {"_method" : "delete"},function(data, status) {
		if ("success" === status) {
			if ("success" === data) {
				$.success("下架成功");
				window.location.reload();
			} else if ("fail" === data) {
				$.error("下架失败，系统异常", 1);
			}
		}
	});
}

</script>