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
    <title>日志记录</title>
    <style>
        #downLoadExcel {
            padding: 10px 20px;
            background-color: #e88a05;
            color: white;
            border-radius: 3px;
        }
    </style>
</head>
<body>
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets white">
            <div class="widget-head">
                <h3>
                    日志记录
                    <p class="btn_link" style="float: right;">
                        <a href="javascript:void(0)" class="a3"
                           onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
                        <a id="downLoadExcel"  class="a2" href="#" onclick="daochu();" class="a2" >导出日志列表</a>
                    </p>
                </h3>
            </div>
            <div class="light_grey"></div>
            <div class="content-widgets">
                <div class="widget-container">
                    <div class="select_b">
                        <div class="select_div"><span>姓名：</span>
                                <input type="text" id="keyword" name="keyword"
                                       style="width:200px;padding-top: 4px;" class="span4"
                                       value="">
                        </div>
                        <div class="select_div"><span>用户名：</span>
                            <input type="text" id="userName" name="userName"
                                   style="width:200px;padding-top: 4px;" class="span4"
                                   value="">
                        </div>
                        <div class="select_div"><span>模块名称：</span>
                            <input type="text" id="mokuaiName" name="mokuaiName"
                                   style="width:200px;padding-top: 4px;" class="span4"
                                   value="">
                        </div>
                        <div class="select_div">
                            <span>时间区间：</span>
                            <input type="text" id="startDate" name="startDate" class="chzn-select" autocomplete="off"
                                   style="width:200px;padding-top: 4px;"  onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})" placeholder="开始时间"
                                   value="">-
                            <input type="text" id="endDate" name="endDate" class="chzn-select" autocomplete="off"
                                   style="width:200px;padding-top: 4px;"  onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})" placeholder="结束时间"
                                   value="">
                        </div>
                        <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                            <div class="clear"></div>
                        <div class="clear"></div>
                    </div>
                    <table class="responsive table table-striped" id="data-table">
                        <thead>
                        <tr role="row">
                            <th>序号</th>
                            <th>操作者</th>
                            <th>操作模块</th>
                            <th>操作类型</th>
                            <th>操作时间</th>
                            <th>描述</th>
                        </tr>
                        </thead>
                        <tbody id="publicClass_list_content">
                        <jsp:include page="./list.jsp"/>
                        </tbody>
                    </table>
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="publicClass_list_content"/>
                        <jsp:param name="url" value="/logger/list?sub=list"/>
                        <jsp:param name="pageSize" value="${page.pageSize}"/>
                    </jsp:include>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function search() {
        var val = {};
        var startTime=$("#startDate").val();
        var endTime=$("#endDate").val();
        var keyword =$("#keyword").val();
        var userName=$("#userName").val();
        if (keyword != null && keyword != "") {
            val.name = keyword;
        }
        if (userName != null && userName != "") {
            val.username = userName;
        }
        if(startTime!=null && startTime !=""){
            val.startTime=startTime+" 00:00:00";
        }
        if(endTime!=null && endTime !=""){
            var data=new Date(endTime);
            var year= data.getFullYear();
            var mounth=data.getMonth()+1;
            var day=data.getDate();
            val.endTime=year+"-"+mounth+"-"+day+" 23:59:59";
        }
        var id = "publicClass_list_content";
        var url = "/logger/list?sub=list";
        myPagination(id, val, url);

    }
    function initSelect() {
        //初始填充学年、学期、年级
        addOption('/teach/schoolYear/list/json', "schoolYear", "year", "name",function (d) {
            if (d.length > 0) {
                addOptionxq('/teach/schoolTerm/list/json?schoolYear='+d[0].year, "schoolTrem", "code", "name");
                //因查询年级不需学期，所以不需在学期填充后的回调中执行
                addOption('/teach/grade/list/json?schoolYear=' + d[0].year, "nj", "id", "name")
            }
        })
    }

    function daochu() {
        $.initWinOnTopFromLeft('作业评价导出', '/logger/daochuView', '1200', '650');
    }


</script>
</body>
</html>
