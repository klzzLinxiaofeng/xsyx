<%@ page language="java" import="platform.education.user.contants.UserContants" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr style="display:none"><td>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
    <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
</td></tr>
<style>
.a_img_title{ 
    width: 50px;
    height: 50px;
    border-radius: 100px;
    overflow: hidden; float:left;
    }
    .text_con{width: 390px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;}
</style>
 <c:forEach items="${teacherList}" var="teacher">
   <tr>
       <td>
       		<c:choose>
				<c:when test="${not empty teacher.photoUuid}">
					<div class="a_img_title" style="background:url(<entity:getHttpUrl uuid='${teacher.photoUuid}'/>) 50% 50%;background-size:100%;">
					</div>
				</c:when>
				<c:otherwise>
				
				<div class="a_img_title" style="background:url(${pageContext.request.contextPath}/res/images/noPhoto.jpg) 50% 50%;background-size:100%;">
					</div>
				</c:otherwise>
			</c:choose>
			<span style="line-height:50px; margin-left:15px;">${teacher.name}</span>
       </td>
       <td>${teacher.jobNumber}</td>
       <td>${teacher.empCard}</td>
       <td>${teacher.shiTangCard}</td>
       <td>${teacher.userName}</td>
       <td><jcgc:cache tableCode="GB-XB" value="${teacher.sex}"></jcgc:cache></td>
       <td>${teacher.mobile}</td>
       <td>
           <c:if test="${teacher.jobState=='11'}">在职</c:if>
           <c:if test="${teacher.jobState=='07'}">离职</c:if>
           <c:if test="${teacher.jobState=='01'}">退休</c:if>
       </td>
       <%-- <td>${teacher.telephone}</td> --%>
       <td>${teacher.position}</td>
       <%-- <td>${teacher.jobNumber}</td> --%>
       <td><div class="text_con">${teacher.characteristic}</div></td>
       <td class="caozuo">
<%--       <button onclick="setCharacteristic('${teacher.userId}');" type="button" class="btn btn-blue">上课特色</button>--%>
       <button onclick="loadModifyTeacherPhotoPage('${teacher.teacherId}');" type="button" class="btn btn-blue">头像</button>
       <button onclick="loadModifyTeacherPage('${teacher.teacherId}');" type="button" class="btn btn-blue">编辑</button>
       	<!--<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
        	<button onclick="deleteTeacher('${teacher.teacherId}');" type="button" class="btn btn-red">删除</button> 
		</c:if>-->
       </td>
   </tr>
</c:forEach>
<script>
/*function setCharacteristic(userId) {
	$.initWinOnTopFromLeft_bbx('上课特色', '${ctp}/clazz/classMember/characteristic?userId='+userId, '700', '300');
}*/
</script>



