<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<span><p>拖动到相应座位</p>
	<p class="surplus">
		剩余<strong id="number" class="number">${fn:length(studentList) }</strong>人
	</p></span>
<div class="right">
	<ul id="student">
		<c:forEach items="${studentList }" var="student">
		<c:choose>
			<c:when test="${student.sex==2 }">
				<li data-obj-id="${student.id}" data-obj-name="${student.name}" class='female item'><img src="<avatar:avatar userId='${student.userId }'></avatar:avatar>"/><p>${student.name }</p></li>
			</c:when>
			<c:otherwise>
				<li data-obj-id="${student.id}" data-obj-name="${student.name}" class='male item'><img src="<avatar:avatar userId='${student.userId }'></avatar:avatar>"/><p>${student.name }</p></li>
			</c:otherwise>
		</c:choose>
			
		</c:forEach>
	</ul>
</div>
<script>
easyDraggable();
</script>