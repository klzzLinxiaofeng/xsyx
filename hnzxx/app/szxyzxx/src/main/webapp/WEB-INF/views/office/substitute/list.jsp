<jsp:useBean id="approval" class="platform.education.service.model.Substitute"/>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<input type="hidden" id="totalPages" value="${page.totalPages}"/>
<ul>
    <c:forEach items="${items}" var="item">
        <li id="li_${item.id}" style="height: 280px;">
            <div class="touxiang">
                <img src="<avatar:avatar userId='${item.sender}'/>">
            </div>
            <div class="detail">
                <div class="p2">
                    <c:choose>
                        <c:when test="${item.status eq 0}">
                            <span>[待审批]</span>
                        </c:when>
                        <c:when test="${item.status eq 2}">
                            <span style="color:rgb(255,0,0)">[已驳回]</span>
                        </c:when>
                        <c:when test="${item.status eq 1}">
                            <span style="color:rgb(0,128,128)">[已同意]</span>
                        </c:when>
                        <c:otherwise>[]</c:otherwise>
                    </c:choose>
                </div>
                <div class="p5" style="word-wrap:break-word;">${item.description}</div>
                <div class="p3">
                    <i class="fa fa-user"></i>
                    <p class="p_div">申请人</p>
                    <span><c:out value="${item.senderName}"/></span>
                </div>
                <div class="p3">
                    <i class="fa fa-user"></i>
                    <p class="p_div">代课人</p>
                    <span><c:out value="${item.daikeName}"/></span>
                </div>
                <div class="p3">
                    <i class="fa fa-legal"></i>
                    <p class="p_div">审核人</p>
                    <span><c:out value="${item.receiverName}"/></span>
                </div>
                <div class="p3">
                    <i class="fa fa-clock-o"></i>
                    <p class="p_div">代课时间</p>
                    <span><fmt:formatDate pattern="yyyy/MM/dd HH:mm" value="${item.startTime}"/> - <fmt:formatDate pattern="yyyy/MM/dd HH:mm" value="${item.endTime}"/></span>
                </div>

                <c:if test="${item.status eq 2}">
                    <div class="p3">
                        <p class="p_div">驳回理由</p>
                        <span>${item.feedback}</span>
                    </div>
                </c:if>

                <div class="p4">
                    <fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${item.createDate}"/>
                </div>
            </div>
            <div class="clear"></div>
        </li>
    </c:forEach>
    <c:if test="${items.size() <= 0}">
        <div class="empty">
            <p>暂无相关数据</p>
        </div>
    </c:if>
</ul>