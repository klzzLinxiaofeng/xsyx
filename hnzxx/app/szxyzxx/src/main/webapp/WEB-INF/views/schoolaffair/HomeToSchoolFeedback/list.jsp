<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>--%>
<tr>
    <td style="padding:0;border:0 none;">
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
    </td>
</tr>
<c:forEach items="${items}" var="item" varStatus="i">
    <tr id="${item.id}_tr">
        <td>${i.index+1+(page.currentPage - 1) * 10}</td>
        <td><fmt:formatDate value="${item.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
        </td>


        <td>${item.className}</td>
    <%--    <td>${item.name}</td>
        <td>${item.parentName}</td>--%>
        <td>${item.phone}</td>
        <td>${item.content}</td>
        <td>
            <c:if test="${item.picUrl != null&& item.picUrl != ''}">
            <img src="${item.picUrl}" style="width: 100px;height: 100px">
            </c:if>
            <c:if test="${item.picUrl2 != null&& item.picUrl != ''}">
            <img src="${item.picUrl2}" style="width: 100px;height: 100px">
            </c:if>
            <c:if test="${item.picUrl3 != null&& item.picUrl != ''}">
            <img src="${item.picUrl3}" style="width: 100px;height: 100px">
            </c:if>
        </td>
        <td class="caozuo">

            <c:if test="${item.isReply == 0}">
                <c:if test="${item.teacherId == teacherId}">
                    <button class="btn btn-green" type="button"
                            onclick="replyObj('${item.id}');">回复
                    </button>
                </c:if>
            </c:if>
            <c:if test="${item.isReply == 1}">
                <button class="btn btn-blue" type="button"
                        onclick="editObj('${item.id}')">编辑
                </button>
            </c:if>
            <c:if test="${quanxian}">
                <c:if test="${item.isReply ==0}">
                    <c:if test="${item.teacherId =='' || item.teacherId==null}">
                    <button class="btn btn-blue" type="button"
                            onclick="openteacher('${item.id}')">设置回复人
                    </button>
                    </c:if>
                </c:if>
            </c:if>

           <%-- <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">--%>
                <button class="btn btn-green" type="button" onclick="loadViewerPage('${item.id}');">详情</button>
          <%--  </c:if>
            <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">--%>
                <button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
         <%--   </c:if>--%>
        </td>
    </tr>
</c:forEach>


