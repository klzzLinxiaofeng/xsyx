<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>

<table class="table">
    <thead>
    <tr><th><i class="ck" style="top: -11px;"></i></th><th>序号</th><th>年级</th><th>班级</th><th>科目</th><th>任课教师</th><th>别名</th><th>手机号码</th><th class="caozuo">操作</th></tr>
    </thead>
    <tbody>
        <c:forEach items="${result}" var="item" varStatus="status">
            <tr>
                <td><i class="ck"></i></td>
                <td>${status.count + (page.currentPage - 1)*page.pageSize}</td>
                <td>${item.gradeName}</td>
                <td>${item.teamNumber}班</td>
                <td>${item.subjectName}</td>
                <td>${item.teacherName}</td>
                <td>${item.alias}</td>
                <td>${item.phone}</td>
                <td class="caozuo">
                    <button class="btn btn-green" onclick="editTeacher('${item.id}')">编辑</button>
                    <button class="btn btn-red" onclick="delTeacher('${item.id}', '${item.subjectTeacherId}');">删除</button>
                </td>
            </tr>
        </c:forEach>
    </tbody>
    <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
    <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
</table>