<%-- 
    Document   : discussReplyList
    Created on : 2012-10-12, 17:05:08
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib_lads.jsp" %>
<!DOCTYPE html>
<div id="discussUserStatus_${requestScope.id}">
    <div class="search"> <span>姓名：</span>
        <input name="discussRealName_${requestScope.id}" id="discussRealName_${requestScope.id}" type="text"/>
        <a href="javascript:void(0)" onclick="discussNameSearch('${requestScope.id}')" class="confirm">
            <img alt="搜索" title="搜索" src="${pageContext.request.contextPath}/res/images/common/lads/lads_confirm_btn.jpg"/>
        </a>
<%--        <a href="javascript:void(0)" onclick="loadDiscussReply('${requestScope.id}')" class="intelligent">
            <img src="${pageContext.request.contextPath}/res/images/common/lads/lads_discuss.jpg"/>
        </a>--%>
    </div>
    <table id="discussTable_${requestScope.id}" class="rtable">
        <thead>
            <tr>
                <th width="50">序号</th>
                <th width="200">学生姓名</th>
                <th width="250">成绩</th>
                <th width="150">评论数</th>
                <th>详细</th>
                <th>完成状况</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.voList}" var="vo" varStatus="st">
                <tr>
                    <td>${st.index+1}</td>
                    <td class="discussRealNameTitle" title="${vo.realName}">${vo.realName}</td>
                    <td>
                        <span id="discussScoreSpan_${vo.status.uuid}" >
                            ${(vo.status.score eq null||"" eq vo.status.score)?"0":vo.status.score}
                        </span>分
                        <a href="javascript:void(0)" onclick="discussScore('${vo.status.uuid}')" >
                            <img alt="评分" title="评分" src="${pageContext.request.contextPath}/res/images/common/lads/lads_pen.png"/>
                        </a>
                    </td>
                    <td>
                        ${vo.comments}
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${vo.comments>0}">
                                <a href="javascript:void(0)" onclick="loadDiscussReplyList('${requestScope.id}', '${vo.status.userId}')">查看</a>
                            </c:when>
                            <c:otherwise>
                                --
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${vo.comments>0}">
                                <img src ="${pageContext.request.contextPath}/res/images/common/lads/finished.png"/>
                            </c:when>
                            <c:otherwise>
                                <img src ="${pageContext.request.contextPath}/res/images/common/lads/unfinished.png"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>