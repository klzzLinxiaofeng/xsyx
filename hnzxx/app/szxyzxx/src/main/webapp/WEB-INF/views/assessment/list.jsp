<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/views/embedded/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<tr style="display:none">
<td colspan="8">
<input type="hidden" id="currentPage" name="currentPage"
       value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages"
       value="${page.totalPages}" />
<input type="hidden" id="total" name="total"
       value="${page.totalRows}" />
</td>
</tr>
<c:forEach items="${vos}" var="vo">
    <tr>
        <td>${vo.gradeName}</td>
        <td>${vo.teamName}</td>

        <td>
            <fmt:formatDate value="${vo.startDate}" type="date" pattern="MM月dd日"/>
            <c:if test="${evType==1}">
            -
            <fmt:formatDate value="${vo.finishDate}" type="date" pattern="MM月dd日"/>
            </c:if>
            <c:if test="${evType==2}">
                &nbsp;&nbsp;星期${vo.weekDay}
            </c:if>
        </td>
        <c:if test="${evType==2}"><td>${vo.subjectName}</td></c:if>
        <td>${vo.teacherName}</td>
        <td>${vo.scoredUserCount}/${vo.totalUserCount}</td>
        <td>${vo.score}</td>
        <c:if test="${evType==2}">
            <td><c:choose>
                <c:when test="${vo.isValid}">
                    <span style="color:#4caf50">有效</span>
                </c:when>
                <c:otherwise>
                    <span>无效</span>
                </c:otherwise>
            </c:choose>
            </td>
        </c:if>
        <td class="caozuo">
            <button class="btn btn-green" data-id="${vo.id}" data-evType="${vo.evType}" data-scoringType="${vo.scoringType}" onclick="detail(${vo.id},${vo.evType},${vo.scoringType})">查看评语</button>
        </td>

    </tr>
    <tr></tr>
</c:forEach>
<script>
    $(function () {
        if($("#total").val()>0){
            $("#export").show();
            $("#list_table").show();
            $(".page_div").show();
            $(".no_teacher").hide();
            $(".no_record").hide();
        }else {
            $("#export").hide();

            $("#list_table").hide();
            $(".page_div").hide();
            <c:choose>
            <c:when test="${isNoTeacher}">
            $(".no_teacher").show();
            $(".no_record").hide();
            </c:when>
            <c:otherwise>
            $(".no_record").show();
            $(".no_teacher").hide();
            </c:otherwise>
            </c:choose>
        }
    })
    


    function detail(id,evType,scoringType) {
        <%--alert("${ctp}");--%>
        window.open("${ctp}/assessment/teacher/detail?id="+id+"&evType="+evType+"&scoringType="+scoringType+"");
    }
</script>