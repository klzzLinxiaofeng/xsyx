<%-- 
    Document   : discussReplyList
    Created on : 2012-10-12, 17:05:08
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib_lads.jsp" %>
<!DOCTYPE html>
<c:forEach varStatus="st" items="${requestScope.replyList}" var="vo" >
    <div class="discuss_list">
        <c:choose>
            <c:when test='${vo.image eq null || "" eq vo.image}'>
                <img width="35px" height="36px" class="portrait" src="${pageContext.request.contextPath}/res/images/common/lads/noPhoto.jpg">
            </c:when>
            <c:otherwise>
                <img width="35px" height="36px" class="portrait" src="${vo.image}">
            </c:otherwise>
        </c:choose>
        <h6>${vo.userName}：</h6>
        <div class="fu"> 
            <c:forEach items="${vo.attachmentList}" var="at" >
                <div class="wen">
                    <a class="wenjian" href="/common/resources/frontResourcesAction_download.action?fileId=${at.fileId}">
                        ${at.fileName}
                    </a>
                    <c:if test="${vo.canDelete}">
                        <span>
                            <a onclick="discussDeleteAttachment(this, '${at.fileId}', '${vo.reply.uuid}')" class="delete">
                                删除
                            </a>
                        </span>
                    </c:if>
                </div>
            </c:forEach>
        </div>
        <p style="text-indent: 0">
            ${vo.reply.content}
        </p>
        <br>
        <p class="fo">
            ${st.index+1}楼&nbsp;&nbsp;<span>
                <fmt:formatDate pattern="MM-dd hh:mm" value="${vo.reply.createDate}"></fmt:formatDate>
                </span>
                <a href="javascript:void(0)" onclick="discussHideReplyReply(this, 'down')"  >回复（${fn:length(vo.childrenReply)}）</a>
            <a href="javascript:void(0)" onclick="discussHideReplyReply(this, 'up')" style="display:none;" >收起回复</a>
        </p>
        <input type="hidden" value="${vo.reply.uuid}" />
        <div class="core_reply_content" style="display:none;" >
            <ul>
                <c:forEach items="${vo.childrenReply}" var="cr">
                    <li>
                        <a class="head" href="javascript:void(0)">
                            <c:choose>
                                <c:when test='${cr.image eq null || "" eq cr.image}'>
                                    <img width="35px" height="36px" class="portrait" src="${pageContext.request.contextPath}/res/images/common/lads/noPhoto.jpg">
                                </c:when>
                                <c:otherwise>
                                    <img width="35px" height="36px" class="portrait" src="${cr.image}">
                                </c:otherwise>
                            </c:choose>
                        </a>
                        <div class="lzl_cnt">
                            <a href="javascript:void(0)">${cr.userName}</a>
                            <span>${cr.reply.content}</span>
                            <div class="lzl_content_reply">
                                <span>
                                    <fmt:formatDate pattern="MM-dd hh:mm" value="${cr.reply.createDate}"></fmt:formatDate>
                                    </span>
                                    <a onclick="discussReplyUserReply(this)" href="javascript:void(0)">回复</a>
                                </div>
                            </div>
                        </li>
                </c:forEach>
                <li style="height:26px;">
                    <p class="j_lzl_p">
                        <a href="javascript:void(0)">我也说一句</a>
                    </p>
                </li>
            </ul>
            <div class="fabiao">
                <textarea id="discussReplyReply_${requestScope.id}" rows="1" cols="93" class="lzl_simple_wrapper"></textarea>
                <span onclick="discussReplyReply(this)">发表</span>
            </div>
        </div>
    </div>
</c:forEach>