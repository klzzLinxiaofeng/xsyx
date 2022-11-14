<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>

<p class="title" style="margin-bottom:10px;">搜索结果<span style="font-size:12px;">（${page.totalRows}个结果）</span></p>
<table class="table table-bordered responsive white">
    <thead>
    <tr role="row">
		<th style="text-align:center"><input type="checkbox" id="checkAll"/></th>
        <th>序号</th>
        <th>标题</th>
        <th>星期</th>
        <th>上课时间</th>
        <th>下课时间</th>
    </tr>
    </thead>
    <tbody id="ryxx_trs">
    	<jsp:include page="time2.jsp"></jsp:include>
    </tbody>
</table>
<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
    <jsp:param name="id" value="ryxx_trs"/>
    <jsp:param name="url" value="/teach/teacher/listInfo?type=2&usePage=true&name=${nameParam}&schoolId=${schoolIdParam }&deptId=${deptIdParam}&enableBatch=${enableBatch}&subjectCode=${subjectCodeParam}&excludeSelf=${excludeSelf}&enableMultiCampus=${enableMultiCampus }&dm=${param.dm}"/>
    <jsp:param name="pageSize" value="${page.pageSize}"/>
</jsp:include>
<script type="text/javascript">
    $(function () {
        $(".cancelBubble").bind("click", function () {
            return false;
        });
    });

</script>