<%@include file="/views/embedded/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<input type="hidden" id="source" name="source" value="${source}" />
<input type="hidden" id="total" name="total" value="${total }" />
<div class="no_resource" style="display:none"></div>
<ul>
	<c:forEach items="${result}" var="vo">
		<li>
			<p class="title">${vo.title }</p>
			<div class="detail">
				<p class="gx"><b class="fa"></b>更新时间：<fmt:formatDate pattern="yyyy/MM/dd" value="${vo.modifyDate}" /></p>
				<p class="sc"><b class="fa"></b>收藏（<span>${vo.favCount }</span>）</p>
				<p class="sy"><b class="fa"></b>使用（<span>${vo.usedCount }</span>）</p>
			</div>
			<div class="cz_btn">
				<button class="btn btn-green" onclick="preview(${vo.id})">查看</button>
				<c:choose>
					<c:when test="${vo.fav }">
						<button class="btn btn-blue" onclick="fav(${vo.id},this)" fav="${!vo.fav }">取消收藏</button>
					</c:when>
					<c:otherwise>
						<button class="btn btn-blue" onclick="fav(${vo.id},this)" fav="${!vo.fav }">收藏</button>
					</c:otherwise>
				</c:choose>
				<button class="btn btn-orange" onclick="publish(${vo.id});">布置</button>
				<button class="btn btn-peaGreen" onclick="edit(${vo.id})">编辑</button>
				<button class="btn btn-red" onclick="deleteLearningPlan(${vo.id},'person');">删除</button>
			</div>
		</li>
	</c:forEach>
</ul>
<div style="margin:20px;padding:0" class="page">
<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
	<jsp:param name="id" value="lpList" />
	<jsp:param name="url" value="/learningPlan/${source }?type=${type }" />
	<jsp:param name="pageSize" value="${page.pageSize}" />
</jsp:include>
</div>
<div class="clear"></div>
<script type="text/javascript">
$(function() {
	var total = $("#total").val();
	if(total==0) {
		$(".no_resource").show();
		$(".page").hide();
	} else {
		$(".no_resource").hide();
		$(".page").show();
	}
})
</script>