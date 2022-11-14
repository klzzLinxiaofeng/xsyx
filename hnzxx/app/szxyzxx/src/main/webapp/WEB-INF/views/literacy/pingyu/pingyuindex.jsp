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
    <title>评语模板类容设置</title>
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
                    评语模板
                    <p class="btn_link" style="float: right;">
                        <a href="javascript:void(0)" class="a3"
                           onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
                    </p>
                </h3>
            </div>
            <div class="light_grey"></div>
            <div class="content-widgets">
                <div class="widget-container">
                    <div class="select_b">
                        <div class="select_div"><span>评语模板类型：</span>
                            <select id="pingType" name="pingType" class="chzn-select"
                                    style="width:200px;">
                            </select>
                        </div>
                        <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                        <div class="clear"></div>
                    </div>
                    <table class="responsive table table-striped" id="data-table">
                        <thead>
                        <tr role="row">
                            <th>序号</th>
                            <th>班级名称</th>
                            <th>模板类型</th>
                            <th>模板类容</th>
                            <th class="caozuo" style="  max-width: 250px;">操作</th>
                        </tr>
                        </thead>
                        <tbody id="publicClass_list_content">
                        <jsp:include page="./pingyulist.jsp"/>
                        </tbody>
                    </table>
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="publicClass_list_content"/>
                        <jsp:param name="url" value=""/>
                        <jsp:param name="pageSize" value="${page.pageSize}"/>
                    </jsp:include>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    $(function () {
        initSelect();
    })
    function initSelect() {
        $("#pingType").html('<option value="">请选择</option>');
        addOption('/pingyumoban/findBypingyu', "pingType", "id", "name");
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
    function search() {
        var ids=$("#studentId").val();
        var teamId=$("#teamId").val();
        var pingid= $("#pingType").val();
        var id = "publicClass_list_content";
        var val={};
        val.id=ids;
        val.teamId=teamId;
        var url = "/Literacy/student/feindBypingyu?sub=list";
        if(pingid!=null && pingid!=""){
            val.pingyuId=pingid;
        }
        myPagination(id, val, url);
    }
    function add(text) {
        $.confirm("确定执行此次操作？", function () {
            adds(text);
        });

    }

    function adds(text) {
        var studentId=$("#studentId").val();
        $.get("${ctp}/Literacy/student/updatePingyu?id="+studentId+"&pingyu="+text, function (data, status) {
            if ("success" === status) {
                if ("success" === data) {
                    $.success("添加成功");
                    parent.layer.closeAll();
                } else if ("fail" === data) {
                    $.error("删除失败，系统异常", 1);
                }
            }
        });
    }

</script>

</body>
</html>
