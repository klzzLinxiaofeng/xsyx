<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<c:forEach items="${subjects}" var="subject" varStatus="sta">
	<tr>
		<c:if test="${sta.index == 0}">
		    <td id="subjectName" class="context_color" rowSpan="${fn:length(subjects)}"></td>
		</c:if>
	    <td>${subject.name}</td>
	    <td><span>
	    <c:forEach items="${learningDesignCounts}" var="ld">
	    	<c:choose>
	    		<c:when test="${ld.subjectCode == subject.code}">
	    			${ld.subjectCode}
	    		</c:when>
	    	</c:choose>
	    </c:forEach>
	    </span></td>
	    <td><span>
	    <c:forEach items="${teachPlanCounts}" var="tp">
	    	<c:choose>
	    		<c:when test="${tp.subjectCode == subject.code}">
	    			${tp.subjectCode}
	    		</c:when>
	    	</c:choose>
	    </c:forEach>
	    </span></td>
	    <td><span>
	    <c:forEach items="${examCounts}" var="em">
	    	<c:choose>
	    		<c:when test="${em.subjectCode == subject.code}">
	    			${em.subjectCode}
	    		</c:when>
	    	</c:choose>
	    </c:forEach>
	    </span></td>
	    <td><span>
	    <c:forEach items="${microCounts}" var="mi">
	    	<c:choose>
	    		<c:when test="${mi.subjectCode == subject.code}">
	    			${mi.subjectCode}
	    		</c:when>
	    	</c:choose>
	    </c:forEach>
	    </span></td>
	    <td><span>
	    <c:forEach items="${commonMicroCounts}" var="cm">
	    	<c:choose>
	    		<c:when test="${cm.subjectCode == subject.code}">
	    			${cm.subjectCode}
	    		</c:when>
	    	</c:choose>
	    </c:forEach>
	    </span></td>
	</tr>
</c:forEach>
<script type="text/javascript">
$(function(){
	var gradeName = $("#grade option:selected").html();
	if(gradeName != "请选择" && grade != ""){
		$("#subjectName ").html(gradeName);
	}
});
</script>