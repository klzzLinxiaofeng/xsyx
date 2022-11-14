<jsp:useBean id="approval" class="platform.education.service.model.InSchoolActivityApproval"/>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<input type="hidden" id="totalPages" value="${page.totalPages}"/>
<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
<div class="entry">
    <ul>
        <c:forEach items="${roomVoList}" var="roomVo" varStatus="stat">
            <li><a href="javascript:" onclick="findData('${roomVo.id}')">${roomVo.name}（ <span class="people_num${stat.index}">${roomVo.roomCount}</span> ）</a></li>
        </c:forEach>
    </ul>
    <div class="clear"></div>
</div>

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
                        <c:when test="${item.state eq 1}">
                            <span class="yichuli">[已通过]</span>
                        </c:when>
                        <c:when test="${item.state eq 2}">
                            <span style="color: rgb(199,42,41)">[已驳回]</span>
                        </c:when>

                        <c:otherwise>[]</c:otherwise>
                    </c:choose>
                </div>
                <div class="p5" style="word-wrap:break-word;">${item.description}</div>

                <c:if test="${item.state eq 2}">
                <div class="p3">
                    <p class="p_div">驳回理由</p>
                    <span>${item.refuseCause}</span>
                </div>
                </c:if>

                <div class="p3">
                    <i class="fa fa-user"></i>
                    <p class="p_div">申请人</p>
                    <span>${item.userName}</span>
                </div>
                <div class="p3">
                    <i class="fa fa-location-arrow"></i>
                    <p class="p_div">活动室</p>
                    <span>${item.roomName}</span>
                </div>
                <div class="p3">
                    <i class="fa fa-clock-o"></i>
                    <p class="p_div">活动时间</p>
                    <span>${item.startTime} - ${item.endTime}
                </div>
                <div class="p3">
                    <i class="fa fa-user"></i>
                    <p class="p_div">参与人</p>
                    <span>${item.attendUserNames}</span>
                </div>
                <div class="p4">
                    <fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${item.createDate}"/>
                </div>
            </div>
            <c:if test="${item.state ==0}">
                <div class="caozuo">
                    <a class="edit" href="javascript:" onclick="handle('${item.id}',1)"><i class="fa fa-send"></i>&nbsp;同意</a>
                    <a style="margin-top: 10px" class="edit" href="javascript:" onclick="handle('${item.id}',2)"><i class="fa fa-ban"></i>&nbsp;驳回</a>
                </div>
            </c:if>
            <div class="clear"></div>
        </li>
    </c:forEach>
    <c:if test="${items.size() <= 0}">
        <div class="empty">
            <p>暂无相关数据</p>
        </div>
    </c:if>
</ul>