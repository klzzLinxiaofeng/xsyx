<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<div id="biao">
<c:forEach items="${list}" var="item" varStatus="i">
    <c:if test="${item.leixing!=null}">
        <h3>11+${item.leixing}</h3>
     <div id='xiguans_${item.id}'>
             <%-- <span>${item.studentName}</span>--%>
              <button onclick="chehui('${item.id}')">撤回</button>
                <br/>
              <h3>${item.lexingName}</h3>
             </div>
    </c:if>
     </c:forEach>
</div>

