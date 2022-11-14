<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
    <td style="padding:0;border:0 none;">
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
    </td>
</tr>
<c:forEach items="${canteens}" var="canteen" varStatus="i">
    <tr id="${canteen.id}_tr" class="blue_1">
        <td>${i.index+1+(page.currentPage - 1) * 10}</td>
        <td>${canteen.name}</td>
        <td>${canteen.teamName}</td>
        <td>${canteen.mesg}</td>
        <td>${canteen.oldIcNumber}</td>
        <td>${canteen.newIcNumber}</td>
        <td><fmt:formatDate value="${canteen.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

        <td>
            <c:if test="${canteen.isSend == 0}">
                <a href="#" onclick="loadEditPage('${canteen.id}');">发卡</a>
                &nbsp; &nbsp; &nbsp; &nbsp;<a href="#" onclick="del('${canteen.id}');">删除申请</a>
            </c:if>
            <c:if test="${canteen.isSend == 1}">
                发卡完成
            </c:if>
        </td>
    </tr>
</c:forEach>
