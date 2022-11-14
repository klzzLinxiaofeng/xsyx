<%--
  Created by IntelliJ IDEA.
  User: zhangyong
  Date: 2021/10/25
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp" %>
<html>
<head>
    <title></title>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/css/extra/my.css" rel="stylesheet">
</head>
<body>
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets white">
            <div class="widget-head">
                <h3 class="x-head content-top">学科素养
                    <a href="javascript:history.go(-1);" class="back right">
                        <i class="fa fa-arrow-circle-left">
                        </i>返回
                    </a>
                </h3>
            </div>
            <div class="light_grey"></div>
            <div class="content-widgets">
                <div class="widget-container">
                    <table class="responsive table table-striped" id="data-table">
                        <thead>
                        <tr role="row">
                            <th></th>
                            <th>序号</th>
                            <th>班级</th>
                            <th>学生姓名</th>
                            <th>科目</th>
                            <th>学科素养指标</th>
                            <th>最大分值</th>
                            <th>分数</th>
                            <th>评语</th>
                           <%-- <th class="caozuo" style="max-width: 250px;">操作</th>--%>
                        </tr>
                        </thead>
                        <tbody id="publicClass_list_content">
                        <jsp:include page="./pinfenlist.jsp"/>
                        </tbody>
                    </table>
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="publicClass_list_content"/>
                        <jsp:param name="url" value="/Literacy/student/pinjialist?sub=list&subjectId=${subjectId}&studentId=${studentId}"/>
                        <jsp:param name="pageSize" value="${page.pageSize}"/>
                    </jsp:include>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
</div>



<script type="text/javascript">

    function fanhui() {
        $.closeWindow();
    }
    function search() {
        var val = {};
        var studentId = $("#studentId").val();
        var subjectId = $("#subjectId").val();
        var schoolYear = $("#schoolYear").val();
        var schoolTrem = $("#schoolTrem").val();
        if (studentId != null && studentId != "") {
            val.studentId = studentId;
        }
        if (subjectId != null && subjectId != "") {
            val.subjectId = subjectId;
        }
        if (schoolYear != null && schoolYear != "") {
            val.schoolYear = schoolYear;
        }
        if (schoolTrem != null && schoolTrem != "") {
            val.schoolTrem = schoolTrem;
        }
        var id = "publicClass_list_content";
        var url = "/Literacy/student/pinjialist?sub=list";
        myPagination(id, val, url);
    }

    function pingyu(id) {
       $.initWinOnTopFromLeft('添加评语', '/Literacy/student/findBypingyu?id='+id, '1000', '550');
   }


</script>
</body>
</html>
