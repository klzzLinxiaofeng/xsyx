<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_jspj.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
    <title>评价详情</title>
</head>
<body style="background-color:#e3e3e3">
<div class="container-fluid">
    <p class="top_link">首页 > <c:if test="${condition.evType==1}">班主任评价</c:if><c:if test="${condition.evType==2}">学科教师评价</c:if> > 评价记录 ><span>查看评语</span></p>
    <div class="content_main white">
        <div class="content_top">
            <div class="f_left"><p>查看评语</p></div>
            <div class="f_right">
                <button class="btn btn-lightGray" onclick="javascript:window.close()">返回</button>
            </div>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th>序号</th>
                <th>真实姓名</th>
                <th>发表时间</th>
                <c:forEach items="${titles}" var="title">
                    <th>${title.taskItemName}</th>
                </c:forEach>
                <th>评语内容</th>
                <th class="caozuo">操作</th>
            </tr>
            </thead>
            <tbody id="list_content">
            <jsp:include page="detail_list.jsp"/>
            </tbody>
        </table>
        <div class="page_div">
            <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                <jsp:param name="id" value="list_content"/>
                <jsp:param name="url"
                           value="/assessment/teacher/detail?sub=list&id=${condition.id}&dm=${param.dm}&scoringType=${condition.scoringType}"/>
                <jsp:param name="pageSize" value="${page.pageSize}"/>
            </jsp:include>
            <div class="clear"></div>
        </div>
    </div>
</div>
<script>
    $(function(){
        $(".caozuo .btn-green").click(function(){
            var py_text=$(this).parent().prev().children().data('text');
            // layer.open({
            //     title: '查看评语内容'
            //     ,content: py_text
            // });

            layer.open({
                type: 1,
                title: '查看评语内容',
                // area: ['600px', '360px'],
                shadeClose: true, //点击遮罩关闭
                content: '\<\div style="padding:20px;">'+py_text+'\<\/div>'
            });

        })
    })
</script>
</body>
</html>