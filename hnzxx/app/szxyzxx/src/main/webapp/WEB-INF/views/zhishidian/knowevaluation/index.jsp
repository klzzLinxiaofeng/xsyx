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
    <title>知识点管理</title>
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
                        <a id="create"  class="a2" href="#" onclick="create();" class="a2" >新增课本</a>
                    </p>
                </h3>
            </div>
            <div class="content-widgets">
                <div class="widget-container">
                    <div class="select_b">
                        <div class="select_div">
                            <span>学年：</span>
                            <select id="xn" name="xn" class="chzn-select"
                                    style="width:200px;">
                            </select>
                        </div>
                        <div class="select_div">
                            <span>年级：</span>
                            <select id="gradeId" name="gradeId" class="chzn-select"
                                    style="width:200px;">
                                <option value="">全校</option>
                            </select>
                        </div>
                        <div class="select_div">
                            <span>班级：</span>
                            <select id="teamId" name="teamId" class="chzn-select"
                                    style="width:200px;">
                                <option value="">全校</option>
                            </select>
                        </div>
                        <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                        <div class="clear"></div>
                    </div>
                    <table class="responsive table table-striped" id="data-table">
                        <thead>
                        <tr role="row">
                            <th>序号</th>
                            <th>班级</th>
                            <th>姓名</th>
                            <th class="caozuo" style="max-width: 250px;">操作</th>
                        </tr>
                        </thead>
                        <tbody id="publicClass_list_content">
                        <jsp:include page="./list.jsp"/>
                        </tbody>
                    </table>
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="publicClass_list_content"/>
                        <jsp:param name="url" value="/KnowEvaluation/findByAll?sub=list"/>
                        <jsp:param name="pageSize" value="${page.pageSize}"/>
                    </jsp:include>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

    //评价
    function pingjia(gradeId,id) {
         window.location.href = "${ctp}/KnowEvaluation/findByAllBook?sub=asd&gradeId=" +gradeId+"&studentId="+id;
    }

    function search() {
        var val = {};
        var gradeId=$("#gradeId").val();
        var schoolYear=$("#xn").val();
        var teamId=$("#teamId").val();

        if (gradeId != null && gradeId != "") {
            val.gradeId = gradeId;
        }
        if (schoolYear != null && schoolYear != "") {
            val.schoolYear= schoolYear;
        }
        if (teamId != null && teamId != "") {
            val.teamId = teamId;
        }
        var id = "publicClass_list_content";
        var url = "/KnowEvaluation/findByAll?sub=list";
        myPagination(id, val, url);
    }
    $(function () {
        initSelect();
    });
    function initSelect() {
        //初始填充学年、学期、年级
        addOption('/teach/schoolYear/list/json', "xn", "year", "name", function (d) {
            if (d.length > 0) {
                //addOption('/teach/schoolTerm/list/json?schoolYear='+d[0].year, "xq", "code", "name");
                //因查询年级不需学期，所以不需在学期填充后的回调中执行
                addOption('/teach/grade/list/json?schoolYear=' + d[0].year, "gradeId", "id", "name")
            }
        })

    }
    //绑定下拉框改变事件
    $("#xn").change(function(){
        $("#gradeId").html('<option value="">全校</option>');
        $("#teamId").html('<option value="">全校</option>');
        //添加年级
        addOption('/teach/grade/list/json?schoolYear='+$(this).val(), "gradeId", "id", "name")
    })
    $("#gradeId").change(function(){

        //添加班级
        if($(this).val()!="") {
            $("#teamId").html('<option value="">全年级</option>');
            addOption('/teach/team/list/json?enableRole=false&gradeId=' + $(this).val(), "teamId", "id", "name")
        }else{
            $("#teamId").html('<option value="">全校</option>');
        }
    })
    function addOption(url, id, valProperty, namePropety, callback) {
        $.get(url, function (d) {
            d = JSON.parse(d);
            for (var i = 0; i < d.length; i++) {
                var obj = d[i];
                $("#" + id).append("<option value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
            }
            if (callback != null && callback != undefined) {
                callback(d);
            }
        })
    }
</script>
</body>
</html>
