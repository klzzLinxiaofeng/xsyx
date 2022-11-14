<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${fn:length(result)>0 }">
	<c:forEach items="${result }" var="entity">
		<div class="nj_list">
			<p class="f_left"><i class="ck all_choose"></i>${entity.grade.name }</p>
			<div class="bj">
				<c:forEach items="${entity.teamList }" var="team">
					<span><i class="ck" teamid="${team.id }"></i>${team.name }</span>
				</c:forEach>
				<div class="clear"></div>
			</div>
		</div>
	</c:forEach>
</c:if>
<c:if test="${fn:length(result)==0 }">
	<div class="zwnr">
		<span class="tu_ts"></span>
	</div>
</c:if>
