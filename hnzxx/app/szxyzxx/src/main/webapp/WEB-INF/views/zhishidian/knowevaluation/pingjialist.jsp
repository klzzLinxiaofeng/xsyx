<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
    <td style="padding:0;border:0 none;">
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
    </td>
</tr>
<c:forEach items="${student}" var="item" varStatus="i">
        <c:if test="${item.knowEvaluationList.size()>0}">
            <c:forEach items="${item.knowEvaluationList}" var="item2" varStatus="t">
                <tr id="tr_${item2.knowMenuId}">
                    <c:if test="${t.first}">
                        <td  rowspan="${item.knowEvaluationList.size()}">${item.knowMenuName}</td>
                    </c:if>
                <td>${item2.knowMenuName}</td>
                <c:if test="${item2.pingfen<60}">
                    <td style="background:red;"></td>
                </c:if>
                <c:if test="${item2.pingfen>=60 && 80>=item2.pingfen}">
                    <td style="background:yellow;"></td>
                </c:if>
                <c:if test="${item2.pingfen>80}">
                    <td style="background:green;"></td>
                </c:if>
                    <c:if test="${item2.pingfen==null}">
                        <td></td>
                    </c:if>
                <td>${item2.pingyu}</td>
                <td class="caozuo">
                    <button class="btn btn-green" type="button" onclick="pingjia('${item2.knowMenuId}','${studentId}','${item2.id}');">评价</button>
                </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${item.knowEvaluationList.size()<=0}">
            <tr>
                <td colspan="4">暂无知识点</td>
            </tr>
        </c:if>

</c:forEach>
<c:if test="${list.size()<=0}">暂无数据</c:if>