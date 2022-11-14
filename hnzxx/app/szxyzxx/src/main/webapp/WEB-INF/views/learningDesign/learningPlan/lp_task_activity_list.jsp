<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<ul class="cs_ul">
	<c:forEach items="${lpTaskUserActivityList }" var="taskActivity">
		<li >
			<div class="l_left">
				<img src="<avatar:avatar userId='${taskActivity.userId  }'></avatar:avatar>">
				<p class="name">${taskActivity.studentName }</p>
			</div>
			<div class="l_right" id="layer-photos-demo_${taskActivity.id}">
				<p class="nr">${taskActivity.content }</p>
					<c:forEach items="${taskActivity.files }" var="item">
						<img src="${item }">
					</c:forEach>
				<p class="time">${taskActivity.createTime }</p>
			</div>
		</li>
	</c:forEach>
</ul>
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