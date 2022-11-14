<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
    <td style="padding:0;border:0 none;">
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
    </td>
</tr>
<c:forEach items="${teamStudentVos}" var="teamStudent" varStatus="i">
    <tr id="${teamStudent.id}_tr">
        <td>
            <c:choose>
                <c:when test="${not empty teamStudent.empCard && teamStudent.empCard != '0' && teamStudent.empCard != '' }">
                    <input class="user_checkbox" type="checkbox" name="ids" value="${teamStudent.studentId}"
                           data-username="${teamStudent.studentId}">

                </c:when>
                <c:otherwise>
                    <input disabled class="user_checkbox" type="checkbox">
                </c:otherwise>
            </c:choose>

        </td>
        <td>${i.index+1+(page.currentPage - 1) * 10}</td>
        <td>${teamStudent.userName }</td>

        <td>${teamStudent.name}</td>
        <td><jcgc:cache tableCode="GB-XB" value="${teamStudent.sex }"/></td>

        <td><c:if test="${empty teamStudent.parentList[0].name }">- - -</c:if>${teamStudent.parentList[0].name }</td>
        <td><c:if test="${empty teamStudent.parentList[0].mobile }">- - -</c:if>
                ${teamStudent.parentList[0].mobile }</td>

        <td>
                ${teamStudent.empCard}
        </td>

        <td class="caozuo">
            <c:choose>
                <c:when test="${not empty teamStudent.empCard && teamStudent.empCard != '0' && teamStudent.empCard != '' }">
                    <button class="btn btn-green" type="button" onclick="addObj('${teamStudent.studentId}');">
                        确定
                    </button>
                </c:when>
                <c:otherwise>
                    <span style="color: red">需设置卡号</span>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
</c:forEach>
