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
    <style>
        div#ddd button {
            background-color: white;
            border-radius: 5px;
            padding: 5px 10px;
            margin: 5px 15px;
            border: 0px;
        }
    </style>
</head>
<body>
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets white">
            <div class="widget-head">
                <h3>
                  添加教师
                </h3>
            </div>
            <div class="light_grey"></div>
            <div class="content-widgets">
                <div class="widget-container">
                    <div class="select_b">
                        <div class="select_div"><span>部门：</span>
                            <select id="bm" name="bumenId" class="chzn-select"style="width:200px;padding-top: 4px;">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div class="select_div"><span>科目：</span>
                            <select id="km" name="teamId" class="chzn-select" style="width:200px;padding-top: 4px;">
                                <option value="">请选择</option>
                            </select>
                        </div>

                        <div class="select_div"><span>姓名：</span>
                            <input id="teaname" name="teaname" class=""  style="width:200px;padding-top: 4px;"/>
                        </div>
                        <button type="button" class="btn btn-primary" onclick="chaxun()">查询</button>

                        <div class="clear"></div>
                    </div>
                    <table class="responsive table table-striped" id="data-table">
                        <thead>
                        <tr role="row">
                            <th>序号</th>
                            <th>姓名</th>
                            <th>性别</th>
                            <th>联系方式</th>
                            <th>在职状态</th>
                            <th class="caozuo" style="max-width: 250px;">操作</th>
                        </tr>
                        </thead>
                        <tbody id="publicClass_list_content">
                        <jsp:include page="./list.jsp"/>
                        </tbody>
                    </table>
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="publicClass_list_content"/>
                        <jsp:param name="url" value="/juzuAndjiaozhigong/teacherAll?sub=list&jiZuId=${jiZuId}"/>
                        <jsp:param name="pageSize" value="${page.pageSize}"/>
                    </jsp:include>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<input type="text" id="jiZuId" style="display:none;" value="${jiZuId}"/>
</div>

<script type="text/javascript">
    function chaxun() {
        var val = {};
        var km=$("#km").val();
        var bm=$("#bm").val();
        var teaname=$("#teaname").val();
        if(bm!=null && bm!=""){
            val.bumenId=bm;
        }
        if(km!=null && km!=""){
            val.subjectId=km;
        }
        if(teaname!=null && teaname!=""){
            val.teacherName=teaname;
        }
        var jiZuId=$("#jiZuId").val();
        var ids = "publicClass_list_content";
        var url = "/juzuAndjiaozhigong/teacherAll?sub=list&jiZuId="+jiZuId;
        myPagination(ids,val, url);
    }
    $(function () {
        initSelect()
    });

    function initSelect() {
        //初始填充部门
        addOption('/juzuAndjiaozhigong/depentAll', "bm", "id", "name")
        //初始填充科目
        addOption('/juzuAndjiaozhigong/subjectAll', "km", "code", "name")
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
    // 	确认添加教师
    function yichu(id) {
        var jiZuId=$("#jiZuId").val();
        $.post("${ctp}/juzuAndjiaozhigong/addTeacherJiZu?jiZuId="+jiZuId+"&teacherId="+id, function (d) {
                if ("success" === d) {
                    $.success("添加成功");
                    chaxun();
                } else{
                    $.error("添加失败，系统异常");
                }
        });
    }

    // 	确认对话框
    function queren(id) {
        $.confirm("确定执行此次操作？", function () {
            yichu(id);
        });
    }

</script>
</body>
</html>
