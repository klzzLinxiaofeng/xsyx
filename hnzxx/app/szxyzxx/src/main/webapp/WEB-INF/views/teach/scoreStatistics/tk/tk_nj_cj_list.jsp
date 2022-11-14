<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<div id="all_table">
    <table class="table table-striped table1" style="border:1px solid #e4e8eb;">
        <thead>
        <tr>
            <th style="width:38px;">学号</th>
            <th style="width:58px;">姓名</th>
            <th style="width:88px;">班级</th>
            <th style="width:48px;">总分</th>
            <th style="width:48px;">年级排名</th>
            <th class="caozuo">操作</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${totalList}" var="item" varStatus="status">
                <c:if test="${!empty item.userId}">
                    <tr>
                        <td>${item.number}</td>
                        <td>${item.name}</td>
                        <td>${item.teamName}</td>
                        <td>${item.totalScore}</td>
                        <td>
                            <c:forEach items="${statMajorStudents}" var="ms">
                                <c:if test="${ms.studentId eq item.studentId}">
                                    ${ms.gradeRank}
                                </c:if>
                            </c:forEach>
                        </td>
                            <%--<td>${item.gradeRank}</td>--%>
                            <%--<td>${status.count}</td>--%>
                        <td class="caozuo">
                            <button class="btn btn-green" onclick="toStudent('${item.userId}', '${item.teamId}');">个人成绩分析</button>
                            <%--<button class="btn btn-blue">导出分析报告</button>--%>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
            <tr style="display: none">
                <td style="padding:0;border:0 none;">
                    <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
                    <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
                </td>
            </tr>

        </tbody>
    </table>
    <div class="fd_table">
        <table class="table table-striped" style="">
            <thead>
            <tr>
                <c:forEach items="${subjectList}" var="item" varStatus="status">
                    <th style="min-width:60px;">${item.subjectName}</th><th style="width:50px;">年级排名</th>
                </c:forEach>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${totalList}" var="item" >
                <c:if test="${!empty item.userId}">
                    <tr>
                        <c:forEach items="${fn:split(item.scores, ';')}" var="score">
                            <td>
                                <c:choose>
                                    <c:when test="${fn:split(score, ',')[1] eq -1.00}">
                                        -
                                    </c:when>
                                    <c:otherwise>
                                        ${fn:split(score, ',')[1]}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${fn:split(score, ',')[2]}</td>
                        </c:forEach>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script>
    //翻页时变动
    $(function () {
        var w=$("body").width()-40;
        var w1=w-670;
        $(".fd_table").width(w1)
        var i=$(".fd_table table th").length;
        var th_width=83*i;
        if(i<=8){
            th_width=108*i
        }
        $(".fd_table table").width(th_width);
        $(".fd_table").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
    });
</script>