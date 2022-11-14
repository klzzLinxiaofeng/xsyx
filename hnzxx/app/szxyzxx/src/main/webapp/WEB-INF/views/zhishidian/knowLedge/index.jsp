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
                            <span>年级：</span>
                            <select id="gradeId" name="gradeId" class="chzn-select"
                                    style="width:200px;">
                                <option value="">请选择</option>
                            </select>
                        </div>
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
                        <div class="select_div">
                            <span>名称：</span>
                            <input id="theme" name="theme" class="" style="width:200px;padding-top: 4px;"/>
                        </div>
                        <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                        <div class="clear"></div>
                    </div>
                    <table class="responsive table table-striped" id="data-table">
                        <thead>
                        <tr role="row">
                            <th>序号</th>
                            <th>名称</th>
                            <th>年级</th>
                            <th>学科</th>
                            <th>创建时间</th>
                            <th class="caozuo" style="max-width: 250px;">操作</th>
                        </tr>
                        </thead>
                        <tbody id="publicClass_list_content">
                        <jsp:include page="./list.jsp"/>
                        </tbody>
                    </table>
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="publicClass_list_content"/>
                        <jsp:param name="url" value="/knowLedge/findByAll?sub=list"/>
                        <jsp:param name="pageSize" value="${page.pageSize}"/>
                    </jsp:include>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function create() {
        $.initWinOnTopFromLeft("创建课本","/knowLedge/createOrUpdate",'800','650')
    }
    //编辑
    function bianji(id) {
        $.initWinOnTopFromLeft("编辑课本","/knowLedge/createOrUpdate?id="+id,'800','650')
    }
    
    //删除
    function shnachu(id) {
        $.confirm("确定执行此次操作？", function () {
            ddelete(id);
        });
    }
    function ddelete(id) {
        $.get("${ctp}/knowLedge/deleteUpdate?id="+id, function (data, status) {
            if ("success" === status) {
                if ("success" === data) {
                    $.success("删除成功");
                    search();
                } else if ("error" === data) {
                    $.error("删除失败，系统异常", 1);
                }
            }
        });
    }
    //管理知识点
    function guanli(id) {
        window.location.href = "${ctp}/knowLedge/findMenuView?id=" + id;
    }
    function search() {
        var val = {};
        var gradeId=$("#gradeId").val();
        var subjectId=$("#subjectId").val();
        var theme=$("#theme").val();

        if (gradeId != null && gradeId != "") {
            val.gradeId = gradeId;
        }
        if (subjectId != null && subjectId != "") {
            val.subjectId= subjectId;
        }
        if (theme != null && theme != "") {
            val.name = theme;
        }
        var id = "publicClass_list_content";
        var url = "/knowLedge/findByAll?sub=list";
        myPagination(id, val, url);
    }
    $(function () {
        initSelect();
    })
    function initSelect() {
        //因查询年级不需学期，所以不需在学期填充后的回调中执行
        addOption('/huojiang/findByGrade', "gradeId", "id", "name")
    }
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
