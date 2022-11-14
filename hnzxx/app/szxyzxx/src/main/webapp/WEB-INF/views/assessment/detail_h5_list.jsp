<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/views/embedded/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<input type="hidden" id="currentPage" name="currentPage"
       value="${page.currentPage}"/>
<input type="hidden" id="totalPages" name="totalPages"
       value="${page.totalPages}"/>
<input type="hidden" id="total" name="total"
       value="${page.totalRows}"/>

<c:forEach items="${judges}" var="judge" varStatus="varStatus">
    <li>
        <img src="${pageContext.request.contextPath}/res/images/no_pic.jpg">
        <div class="pl_right">
            <div class="p1">
                <p class="p11">序号${judge.num}</p>
                <p class="p12"><fmt:formatDate value="${judge.checkTime}" type="date" pattern="yyyy-MM-dd HH:mm"/></p>
            </div>
            <div class="p2">
                <c:forEach items="${judge.scores}" var="score" varStatus="status">
                    <div class="pf_div table">
                        <p class="p21">${items[status.index].taskItemName}</p>

                        <c:if test="${vo.scoringType==15}">
                            <div class="pf_yuan"><p style="width:${score.itemScore/2*20}%;"
                                                    <c:if test="${score.itemScore/2==1}">class="black_face"</c:if>></p>
                            </div>
                        </c:if>

                        <c:if test="${vo.scoringType==11}">
                            <c:choose>
                                <c:when test="${score.itemScore==10}">
                                    <div class="zan"></div>
                                </c:when>
                                <c:otherwise>
                                    <div class="no_zan"></div>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
            <div class="p3"><span>评语：</span>${judge.descriptionWithNewline}</div>
        </div>
    </li>
</c:forEach>