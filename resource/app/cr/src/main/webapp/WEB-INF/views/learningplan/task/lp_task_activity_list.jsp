<%@include file="/views/embedded/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<div class="xj_div" >
	<c:if test="${userActivitySize>0 }">
		<div class="xj_d"  >
			<div class="jszj">${content }</div>
			<div class="xsxj">
				<ul class="cs_ul">
					<c:forEach items="${lpTaskUserActivityList }" var="taskActivity">
						<li>
							<div class="l_left">
								<img src="<avatar:avatar userId='${taskActivity.userId  }'></avatar:avatar>">
							</div>
							<div class="l_right" id="layer-photos-demo_${taskActivity.id}">
								<div class="name">
									<b>${taskActivity.studentName }</b>
									<fmt:formatDate pattern="yyyy/MM/dd HH:mm" value="${taskActivity.createTime }" />
								</div>
								<div class="neirong">
									<p>${taskActivity.content }</p>
									<c:forEach items="${taskActivity.files }" var="item">
										<img src="${item }">
									</c:forEach>
								</div>
							</div>
						</li>
					</c:forEach>
				</ul>
				<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
					<jsp:param name="id" value="xszj_list_id" />
					<jsp:param name="url" value="/learningPlan/task/activity/list?taskId=${taskId }&unitId=${unitId }&userId=${userId}" />
					<jsp:param name="pageSize" value="${page.pageSize}" />
				</jsp:include>
				<div class="clear"></div>
			</div>
		</div>
	</c:if>
	<c:if test="${userActivitySize==0 }">
		<div class="no_pinlun">暂时没有评论</div>
		<div style="display: none">
			<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
				<jsp:param name="id" value="xszj_list_id" />
				<jsp:param name="url" value="/learningPlan/task/activity/list?taskId=${taskId }&unitId=${unitId }&userId=${userId}" />
				<jsp:param name="pageSize" value="${page.pageSize}" />
			</jsp:include>
		</div>
	</c:if>
</div>
<script>
layer.ready(function(){ //为了layer.ext.js加载完毕再执行
	var s=$(".cs_ul li").size();
	for(var i=0;i<s;i++){
		var id=$(".cs_ul li").eq(i).children(".l_right").attr("id"); 		
	    layer.photos({
	    	shade:0.5,
	    	area:'auto',
	    	maxWidth:"800",
	    	shift : 5,//3.0之前版本设置动画的属性
	        photos: '#'+id
	    });
	}
	});
</script>