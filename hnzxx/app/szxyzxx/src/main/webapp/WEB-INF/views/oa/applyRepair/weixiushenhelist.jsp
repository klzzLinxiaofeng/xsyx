<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
    <td style="padding:0;border:0 none;">
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
    </td>
</tr>
<%--审核人userid==登录userid才能审核--%>
<c:forEach items="${weixiuList}" var="item" varStatus="i">
    <tr id="${item.id}_tr">
        <td>${i.index+1+(page.currentPage - 1) * 10}</td>
        <td>${item.proposerName}</td>
        <td>${item.title}</td>
        <td>${item.typeName}</td>
        <td>${item.shenherenName}</td>
        <td>
            <c:if test="${item.isShenhe==0}">
                待审核
            </c:if>
                <c:if test="${item.isShenhe==1}">
                审核通过
                </c:if>
                <c:if test="${item.isShenhe==2}">
                审核未通过
                </c:if>
        <td class="caozuo">
            <c:if test="${userid==item.shenherenId}">
                <c:if test="${item.isShenhe==0}">
                    <button class="btn btn-green" type="button" onclick="tongguo('${item.id}','${item.typeId}');">通过</button>
                    <button class="btn btn-green" type="button" onclick="nottongguo('${item.id}');">不通过</button>
                </c:if>
                <c:if test="${item.isShenhe!=0}">
                    <button class="btn btn-green" disabled type="button" onclick="tongguo('${item.id}','${item.typeId}');">通过</button>
                    <button class="btn btn-green" disabled type="button" onclick="nottongguo('${item.id}');">不通过</button>
                </c:if>
            </c:if>
            <button class="btn btn-green" type="button" onclick="chakan('${item.id}');">详情</button>
        </td>
    </tr>
</c:forEach>