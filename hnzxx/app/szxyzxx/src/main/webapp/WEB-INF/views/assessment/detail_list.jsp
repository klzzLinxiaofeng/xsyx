<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/views/embedded/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<tr style="display:none">
    <td colspan="8"><input type="hidden" id="currentPage" name="currentPage"
               value="${page.currentPage}"/>
        <input type="hidden" id="totalPages" name="totalPages"
               value="${page.totalPages}"/>
        <input type="hidden" id="total" name="total"
               value="${page.totalRows}"/></td>
</tr>
<c:forEach items="${vos}" var="vo" varStatus="varStatus">
    <tr>
        <td>${vo.num}</td>
        <td>${vo.name}</td>
        <td><fmt:formatDate value="${vo.checkTime}" type="date" pattern="yyyy-MM-dd HH:mm"/></td>

        <c:forEach items="${vo.scores}" var="score">
            <td>
                <c:if test="${condition.scoringType==15}">
                    <div class="pf_yuan"><p style="width:${score.itemScore/2*20}%;" <c:if test="${score.itemScore/2==1}">class="black_face"</c:if>></p></div>
                </c:if>
                    <c:if test="${condition.scoringType==11}">
                            <c:choose>
                            <c:when test="${score.itemScore==10}">
                            <div class="zan"></div>
                            </c:when>
                            <c:otherwise>
                            <div class="no_zan">
                                </c:otherwise>
                                </c:choose>
                                </c:if>
            </td>
        </c:forEach>
        <td>
            <div class="py_length" data-text="${vo.descriptionWithNewline}">${vo.description}</div>
        </td>
        <td class="caozuo">
            <button class="btn btn-green">查看评语</button>
        </td>
    </tr>
</c:forEach>