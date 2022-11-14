<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
 
 <c:choose>
	 <c:when test="${!empty items }">
   <c:forEach items="${items }" var="entry" varStatus="statu">
    <tr>
       
      <td>${entry.createname }</td>
      <td>${entry.leavetype }</td>
      <td>${entry.day }</td>
      <td>${entry.leaveinfo }</td>
      <td><fmt:formatDate value="${entry.createDate}" pattern="yyyy/MM/dd" /></td>
      <td><button class="btn btn-red" type="button" onclick="loadApprPage('${entry.uuid}');">处理</button></td> 
      
    </tr>
     </c:forEach>
     </c:when>
				<c:otherwise>		 	 
				 <tr>
      <td colspan="6" ><div align="center"> 暂无数据！ </div></td>
    </tr>
 </c:otherwise>
				</c:choose>
				
				
				