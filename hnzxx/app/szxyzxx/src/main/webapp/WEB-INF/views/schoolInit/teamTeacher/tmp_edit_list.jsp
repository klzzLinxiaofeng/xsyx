<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>

<table class="table">
    <thead>
    <tr><th>序号</th><th>姓名</th><%--<th>别名</th>--%><th>性别</th><th>联系方式</th><th>在职状态</th><th class="caozuo">操作</th></tr>
    </thead>
    <tbody>
    <c:forEach items="${teacherList}" var="item" varStatus="status">
        <tr>
            <td>${status.count + (page.currentPage - 1)*page.pageSize}</td>
            <td>${item.name}</td>
            <%--<td>${item.alias}</td>--%>
            <td>${item.sex != null && item.sex != "" ? jcgcFn:getColValue("GB-XB", item.sex, "name") : "无"}</td>
            <td>${item.mobile}</td>
            <td>${item.jobState != null && item.jobState != "" ? jcgcFn:getColValue("JY-JZGDQZT", item.jobState, "name") : "无"}</td>
            <td class="caozuo"><button class="btn btn-green" onclick="save('${item.id}', '${item.name}', '${item.mobile}');">确定</button></td>
        </tr>
    </c:forEach>
    </tbody>
    <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
    <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
</table>

<script>
    $(".f_right span").text("搜索结果（${count}个结果）");
</script>