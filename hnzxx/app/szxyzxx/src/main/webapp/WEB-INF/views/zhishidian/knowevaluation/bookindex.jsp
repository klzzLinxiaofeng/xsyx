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
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <title>知识点评价</title>
    <style>
        #publicClass_list_content {
            font-size: 100px;
        }
    </style>
</head>
<body>
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets white">
            <div class="widget-head">
                <h3>
                    知识点
                    <p class="btn_link" style="float: right;">
                        <a href="javascript:void(0)" class="a3"
                           onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
                    </p>
                </h3>
            </div>
            <div class="content-widgets">
                <div class="widget-container">
                    <div class="select_b">
                        <div class="select_div">
                            <span>学科：</span>
                            <select id="subjectId" name="subjectId" class="chzn-select"
                                    style="width:200px;">
                                <option value="">请选择</option>
                                <option value="1">语文</option>
                                <option value="2">数学</option>
                                <option value="3">英语</option>
                            </select>
                        </div>
                        <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                        <div class="clear"></div>
                    </div>
                    <table class="responsive table table-striped" id="data-table">
                        <thead>
                        <tr role="row">
                            <th>序号</th>
                            <th>名称</th>
                            <th>学科</th>
                            <th class="caozuo" style="max-width: 250px;">操作</th>
                        </tr>
                        </thead>
                        <tbody id="publicClass_list_content">
                        <jsp:include page="./booklist.jsp"/>
                        </tbody>
                    </table>
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="publicClass_list_content"/>
                        <jsp:param name="url" value="/KnowEvaluation/findByAllBook?sub=list"/>
                        <jsp:param name="pageSize" value="${page.pageSize}"/>
                    </jsp:include>
                    <div class="clear"></div>
                </div>
            </div>
            <input type="text" style="display: none" id="studentId" value="${studentId}"/>
            <input type="text" style="display: none" id="gradeId" value="${gradeId}"/>
        </div>
    </div>
</div>

<script type="text/javascript">
    //管理知识点
    function pingjia(id) {
        var studentId=$("#studentId").val();
        window.location.href = "${ctp}/KnowEvaluation/findByAllPinjia?sub=asd&studentId="+studentId+"&knowId="+id;
    }
    function search() {
        var val = {};
        var subjectId=$("#subjectId").val();
        if (subjectId != null && subjectId != "") {
            val.subjectId= subjectId;
        }
        var gradeId=$("#gradeId").val();
        var studentId=$("#studentId").val();
        var id = "publicClass_list_content";
        var url = "/KnowEvaluation/findByAllBook?sub=list&gradeId="+gradeId+"&studentId="+studentId;

        myPagination(id, val, url);
    }
</script>
</body>
</html>
