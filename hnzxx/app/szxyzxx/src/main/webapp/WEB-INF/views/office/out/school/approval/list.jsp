<jsp:useBean id="approval" class="platform.education.service.model.OutSchoolActivityApproval"/>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<input type="hidden" id="totalPages" value="${page.totalPages}"/>
<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
<ul>
    <c:forEach items="${items}" var="item">
        <li id="li_${item.id}" style="height: 280px;">
            <div class="touxiang">
                <img src="<avatar:avatar userId='${item.userId}'/>">
            </div>
            <div class="detail">
                <div class="p2">
                        ${item.name}
                    <c:choose>
                        <c:when test="${item.state eq 0}">
                            <span>[待审批]</span>
                        </c:when>
                        <c:when test="${item.state eq 4}">
                            <span style="color:rgb(255,0,0)">[已驳回]</span>
                        </c:when>
                        <c:when test="${item.state eq 1}">
                            <span style="color:rgb(0,128,128)">[已通过]</span>
                        </c:when>
                        <c:when test="${item.state eq 2}">
                            <span style="color:rgb(128,0,128)">[待总结]</span>
                        </c:when>
                        <c:when test="${item.state eq 3}">
                            <span style="color: rgb(0,128,0)">[已总结]</span>
                        </c:when>
                        <c:otherwise>[]</c:otherwise>
                    </c:choose>
                </div>
                <div class="p5" style="word-wrap:break-word;">${item.description}</div>
                <div class="p3">
                    <i class="fa fa-user"></i>
                    <p class="p_div">申请人</p>
                    <span>${item.userName}</span>
                </div>
                <div class="p3">
                    <i class="fa fa-location-arrow"></i>
                    <p class="p_div">地点</p>
                    <span>${item.location}</span>
                </div>
<%--                <div class="p3">--%>
<%--                    <i class="fa fa-cloud-download"></i>--%>
<%--                    <p class="p_div">附件</p>--%>
<%--                    <c:if test="${not empty item.accessory}">--%>
<%--                        <span><a href="${eurlFn:getConvertedUrl(item.accessory)}" target="_blank">${item.fileName}</a></span>--%>
<%--                    </c:if>--%>
<%--                </div>--%>
                <div class="p3">
                    <i class="fa fa-clock-o"></i>
                    <p class="p_div">活动时间</p>
                    <span><fmt:formatDate pattern="yyyy/MM/dd HH:mm" value="${item.startTime}"/> - <fmt:formatDate pattern="yyyy/MM/dd HH:mm" value="${item.endTime}"/></span>
                </div>

                <c:if test="${item.state eq 4}">
                    <div class="p3">
                        <p class="p_div">驳回理由</p>
                        <span>${item.refuseCause}</span>
                    </div>
                </c:if>

                <div class="p4">
                    <fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${item.createDate}"/>
                </div>
            </div>
            <div class="caozuo" style="width:130px">
                <c:if test="${item.state eq 0}">
                    <a class="edit" href="javascript:" onclick="agreed('${item.id}')"><i class="fa fa-send"></i>&nbsp;同意</a>
                    <a class="delete" href="javascript:" onclick="reject('${item.id}')"><i class="fa fa-ban"></i>&nbsp;驳回</a>
                </c:if>
                <c:if test="${item.state eq 3}">
                    <a class="delete" href="javascript:" onclick="examine('${item.id}')"><i class="fa fa-eye"></i>&nbsp;查看总结</a>
                </c:if>
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