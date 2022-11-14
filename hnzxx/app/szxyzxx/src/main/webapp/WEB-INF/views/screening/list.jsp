<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
    <td style="padding:0;border:0 none;">
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
    </td>
</tr>
<c:forEach items="${wareHousingList}" var="item" varStatus="i">
    <tr id="${item.id}_tr">
        <td>${i.index+1+(page.currentPage - 1) * 10}</td>
        <td>${item.paichaUserName}</td>
        <td>${item.screeningArea}</td>
        <td><fmt:formatDate value="${item.paichaTime2}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        <td>
            <c:if test="${item.waterElectricity==0}">
                正常
            </c:if>
            <c:if test="${item.waterElectricity==1}">
                不正常
            </c:if>
        </td>
        <td>
            <c:if test="${item.trouble==0}">
                正常
            </c:if>
            <c:if test="${item.trouble==1}">
                不正常
            </c:if>
        </td>
        <td>
            <c:if test="${item.construction==0}">
                正常
            </c:if>
            <c:if test="${item.construction==1}">
                不正常
            </c:if>
        </td>
        <td>
            <c:if test="${item.facilities==0}">
                正常
            </c:if>
            <c:if test="${item.facilities==1}">
                不正常
            </c:if>
        </td>
        <td class="caozuo">


            <c:if test="${flag}">
                <button class="btn btn-green" type="button" onclick="bianji('${item.id}');">编辑</button>
                <button class="btn btn-green" type="button" onclick="shanchu('${item.id}');">删除</button>
            </c:if>
            <button class="btn btn-green" type="button" onclick="xiangqing('${item.id}');">详情</button>
        </td>
    </tr>
</c:forEach>