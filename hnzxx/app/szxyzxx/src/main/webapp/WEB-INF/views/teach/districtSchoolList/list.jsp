<%@ page language="java" import="platform.education.user.contants.UserContants" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
 <c:forEach items="${schoolList}" var="school">
   <tr>
       <td>${school.name}</td>
       <td>${school.englishName}</td>
       <td>${school.code}</td>
       <td>${school.code2}</td>
       <td><jcgc:cache tableCode="JY-XXLB" value="${school.schoolType}"></jcgc:cache></td>
       <td><jcgc:cache tableCode="JY-BXLB" value="${school.runningType}"></jcgc:cache></td>
       <td><t:showTime createTime="${school.createDate}" /></td>
   </tr>
</c:forEach>
