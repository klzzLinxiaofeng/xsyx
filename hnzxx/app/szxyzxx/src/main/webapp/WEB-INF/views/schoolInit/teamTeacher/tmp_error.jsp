<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <table class="table">
        <thead>
        <tr><th><i class="ck" style="top: -11px;"></i></th><th>序号</th><th>年级</th><th>班级</th><th>科目</th><th>任课教师</th><th>别名</th><th>手机号码</th><th>异常数据</th><th class="caozuo">操作</th></tr>
        </thead>
        <tbody style="background-color: #fff1f1;">
        <c:forEach items="${result }" var="item" varStatus="status">
            <tr>
                <td><i class="ck"></i></td>
                <td>${status.count + (page.currentPage - 1)*page.pageSize}</td>
                <td>${item.gradeName}</td>
                <td>${item.teamNumber}班</td>
                <td>${item.subjectName}</td>
                <td>${item.teacherName}</td>
                <td>${item.alias}</td>
                <td>${item.phone}</td>
                <td>${item.errorInfo}</td>
                <td class="caozuo">
                    <c:if test="${fn:contains(item.errorInfo, '教师')}">
                        <button class="btn btn-green" onclick="editTeacher('${item.id}')">编辑</button>
                    </c:if>
                    <button class="btn btn-red" onclick="delTeacher('${item.id}', '${item.subjectTeacherId}');">删除</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
    </table>

<script>
    <%--function editTeacher(){--%>
        <%--$.initWinOnTopFromLeft_qyjx("编辑任课教师",  '${pageContext.request.contextPath}/views/demo/new_school/tta_edit.jsp', '713', '619');--%>
    <%--}--%>
    <%--$('.table tbody i.ck').click(function(){--%>

        <%--if($(this).hasClass('on')){--%>
            <%--$(this).removeClass('on');--%>
            <%--if( $('tbody i.ck').length!=$('tbody i.ck.on').length){--%>
                <%--$('.table thead i.ck').removeClass('on');--%>
            <%--}--%>
        <%--}else{--%>
            <%--$(this).addClass('on');--%>
            <%--if($('tbody i.ck').length==$('tbody i.ck.on').length){--%>
                <%--$('.table thead i.ck').addClass('on');--%>
            <%--}--%>
        <%--}--%>
    <%--});--%>
    <%--$('.table thead i.ck').click(function(){--%>
        <%--if($(this).hasClass('on')){--%>
            <%--$(this).removeClass('on');--%>
            <%--$('.ck').removeClass('on');--%>

        <%--}else{--%>
            <%--$(this).addClass('on');--%>
            <%--$('.ck').addClass('on');--%>
        <%--}--%>
    <%--});--%>
</script>
</body>
</html>