<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>

<table class="table table-striped" style="border:1px solid #e4e8eb;">
    <thead><tr><th style="width: 19%;">考试名称</th><th>考试类型</th><th>科目</th><th>考试时间</th><th>导入状态</th><th>导入人</th><th>发布状态</th><th>操作</th></tr></thead>
    <tbody>
    <c:forEach items="${list}" var="item">
        <tr id="${item.ewtsId}" data-code="${item.subjectCode}">
            <td>${item.name}</td>
            <td>
                <c:choose>
                    <c:when test="${item.examType eq '01'}">
                        期中考试
                    </c:when>
                    <c:when test="${item.examType eq '02'}">
                        期末考试
                    </c:when>
                    <c:when test="${item.examType eq '03'}">
                        模拟考
                    </c:when>
                    <c:when test="${item.examType eq '12'}">
                        月考
                    </c:when>
                    <c:otherwise>
                        班级测试
                    </c:otherwise>
                </c:choose>
            </td>
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
                    <c:when test="${empty item.publisherId && empty item.publishTime}">
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
                    <%--<c:when test="${empty item.publisherId}">--%>
                        <%--<button class="btn btn-orange">发布</button>--%>
                    <%--</c:when>--%>
                    <c:otherwise>
                        <button class="btn btn-blue" onclick="reLead(
                                '${item.schoolYear}', '${item.termCode}', '${item.examWorksId}',
                                '${item.subjectCode}', '${item.gradeId}', '${item.teamId}',
                                '${item.examType}', '${item.beginDate}', '${item.endDate}',
                                '${item.fullScore}', '${item.highScore}', '${item.lowScore}',
                                '${item.passScore}','${item.subjectName}', '${isPublish}'
                                );">重新导入</button>
                    </c:otherwise>
                </c:choose>
                <input type="hidden" value="${empty item.posterId}">
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<script>
    $(function () {
        /* 原按钮有三种状态：不能发布（成绩未全部导入），未发布（成绩已全部导入），已发布； 只能发布一次 */
        /* 现按钮只有两种状态：不能发布，可以发布； 能发布多次*/
        <%--var isPublish = "${isPublish}";--%>
        <%--if (isPublish == "true") {--%>
            <%--$("#have_publish").show();--%>
            <%--$("#can_publish").hide();--%>
            <%--$("#not_publish").hide();--%>
        <%--} else {--%>
            $("#have_publish").hide();
            var show = true;
            if ($(".table tbody tr").length == 0) {
                show = false;
            }
            $(".table tbody tr").each(function () {
                var noPost = $(this).find("td:last").find("input").val();
                if (noPost == "true") {
                    show = false;
                }
            });
            if (show) {
                $("#can_publish").show();
                $("#not_publish").hide();
            } else {
                $("#can_publish").hide();
                $("#not_publish").show();
            }
//        }

        //添加班主任和科任教师的判断
        //绿色按钮查看，蓝色按钮导入；科任教师，非所教的科目，不可导入
        var isManager = "${isManager}";
        var isMaster = "${isMaster}";
        var subjects = "${subjects}";
        if (isManager != "true" && isMaster != "true") {
            var split = subjects.split(",");
            $(".table tbody tr").each(function () {
                var subjectCode = $(this).data("code");
                var isMine = false;
                for (var i in split){
                    if (subjectCode == split[i]) {
                        isMine = true;
                    }
                }
                if (!isMine) {
                    $(this).find("td:last button.btn-blue").hide();
                }
            });
        }

    });

</script>