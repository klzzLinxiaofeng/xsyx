<%@ page language="java" import="platform.education.user.contants.UserContants" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>

<tr style="display:none">
    <td>
    <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
    <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
    </td>
</tr>

<c:forEach items="${list}" var="item">
    <tr id="${item.ewtsId}">
        <td><a href="javascript:void(0)" <c:if test="${empty item.posterId}">class="unClick"</c:if>><input type="checkbox"></a></td>
        <td>${item.name}</td>
        <td>班级测试</td>
        <td>${item.subjectName}</td>
        <td><fmt:formatDate pattern="yyyy/MM/dd HH:mm" value="${item.examDate}"></fmt:formatDate></td>
        <td>
            <c:choose>
                <c:when test="${empty item.posterId}">
                    <span class="f_red">未导入</span>
                </c:when>
                <c:otherwise>
                    <span>已导入</span>
                </c:otherwise>
            </c:choose>
        </td>
        <td>
            <c:choose>
                <c:when test="${empty item.posterId}">
                    --
                </c:when>
                <c:otherwise>
                    ${item.poster}
                </c:otherwise>
            </c:choose>
        </td>
        <td>
            <c:choose>
                <c:when test="${empty item.publisherId}">
                    <span class="f_orange">未发布</span>
                </c:when>
                <c:otherwise>
                    <span>已发布</span>
                </c:otherwise>
            </c:choose>
        </td>
        <td>
            <button class="btn btn-green" onclick="toView('${item.examWorksId}', '${item.teamId}','${item.ewtsId}');">查看</button>
            <c:choose>
                <c:when test="${empty item.posterId}">
                    <button class="btn btn-blue" onclick="lead(
                            '${item.schoolYear}', '${item.termCode}', '${item.examWorksId}',
                            '${item.subjectCode}', '${item.gradeId}', '${item.teamId}',
                            '${item.examType}', '${item.beginDate}', '${item.endDate}',
                            '${item.fullScore}', '${item.highScore}', '${item.lowScore}',
                            '${item.passScore}','${item.subjectName}'
                            );">导入</button>
                </c:when>
                <c:otherwise>
                    <button class="btn btn-blue" onclick="reLead(
                            '${item.schoolYear}', '${item.termCode}', '${item.examWorksId}',
                            '${item.subjectCode}', '${item.gradeId}', '${item.teamId}',
                            '${item.examType}', '${item.beginDate}', '${item.endDate}',
                            '${item.fullScore}', '${item.highScore}', '${item.lowScore}',
                            '${item.passScore}','${item.subjectName}','${item.publisherId}'
                            );">重新导入</button>
                </c:otherwise>
            </c:choose>
            <c:if test="${!empty item.posterId}">
                <button class="btn btn-orange" onclick="publish('${item.ewtsId}');">发布</button>
            </c:if>
        </td>
    </tr>
</c:forEach>

<script>
    //原功能：未导入的不能发布，已发布的不能重复发布；现改为能多次发布
    $(function(){
        $(".ks_list table tbody  tr a").click(function(){
            if($(this).hasClass("unClick")!=true){
                $(this).toggleClass("on");
            }
        });
    });
</script>
