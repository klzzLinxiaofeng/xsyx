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
    <title>教赛记录</title>
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
                    获奖记录
                    <p class="btn_link" style="float: right;">
                        <a href="javascript:void(0)" class="a3"
                           onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
                        <a id="downLoadExcel"  class="a2" href="#" onclick="daochu();" class="a2" >导出教赛记录</a>

                    </p>
                </h3>
            </div>
            <div class="light_grey"></div>
            <div class="content-widgets">
                <div class="widget-container">
                    <div class="select_b">
                        <div class="select_div">
                            <span>获奖时间：</span>
                            <input type="text" id="startDate" name="startDate" class="chzn-select" autocomplete="off"
                                   style="width:200px;padding-top: 4px;"  onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})" placeholder="开始时间"
                                   value="">-
                            <input type="text" id="endDate" name="startDate" class="chzn-select" autocomplete="off"
                                   style="width:200px;padding-top: 4px;"  onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})" placeholder="结束时间"
                                   value="">
                        </div>
                        <div class="select_div">
                            <span>类型：</span>
                            <select id="type" name="type" class="chzn-select"
                                    style="width:200px;">
                                <option value="">请选择</option>
                                <option>论文</option>
                                <option>课题</option>
                                <option>公开课</option>
                                <option>教师竞赛</option>
                            </select>
                        </div>
                        <div class="select_div">
                            <span>主题：</span>
                            <input id="theme" name="theme" class="" style="width:200px;padding-top: 4px;"/>
                        </div>
                        <div class="select_div">
                            <span>教师姓名：</span>
                            <input id="teacherName" name="teacherName" class="" style="width:200px;padding-top: 4px;"/>
                        </div>
                        <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                        <div class="clear"></div>
                    </div>
                    <table class="responsive table table-striped" id="data-table">
                        <thead>
                        <tr role="row">
                            <th>序号</th>
                            <th>主题</th>
                            <th>获奖类型</th>
                            <th>获奖属性</th>
                            <th>获奖时间</th>
                            <th>获奖教师</th>
                            <th>获奖学生</th>
                            <th>作评名称</th>
                            <th>获奖级别</th>
                            <th>等次</th>
                            <th>发奖单位</th>
                            <th>得分</th>
                            <th>绩效得分</th>
                            <th class="caozuo" style="max-width: 250px;">操作</th>
                        </tr>
                        </thead>
                        <tbody id="publicClass_list_content">
                        <jsp:include page="./list.jsp"/>
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
    function search() {
        var val = {};
        var startDate=$("#startDate").val();
        var endDate=$("#endDate").val();
        var type=$("#type").val();
        var theme=$("#theme").val();
        var teacherName=$("#teacherName").val();
        if (startDate != null && startDate != "") {
            val.startTime = startDate;
        }
        if (endDate != null && endDate != "") {
            val.endTime=endDate;
        }
        if (type != null && type != "") {
            val.type = type;
        }
        if(theme!=null && theme !=""){
            val.theme=theme;
        }
        if(teacherName!=null && teacherName!=""){
            val.teacherName=teacherName;
        }
        val.leixing=2;
        var id = "publicClass_list_content";
        var url = "/huojiangFenXin/findByJiaoSaiAll?sub=list";
        myPagination(id, val, url);
    }

    //导出
    function daochu() {
        var url="/huojiangFenXin/findByHuoJiangExport?leixing=2";
        var startDate=$("#startDate").val();
        var endDate=$("#endDate").val();
        var type=$("#type").val();
        var theme=$("#theme").val();
        var teacherName=$("#teacherName").val();
        if (startDate != null && startDate != "") {
            url+="&startTime="+startDate;
        }
        if (endDate != null && endDate != "") {
            var data=new Date(endDate);
            var year= data.getFullYear();
            var mounth=data.getMonth()+1;
            var day=data.getDate()+1;
            url+="&endTime="+year+"-"+mounth+"-"+day;
        }
        if (type != null && type != "") {
            url+="&type="+type;
        }
        if(theme!=null && theme !=""){
            url+="&theme="+theme;
        }
        if(teacherName!=null && teacherName!=""){
            url+="&teacherName="+teacherName;
        }
        $("#downLoadExcel").attr("href", url);
    }
    //详情
    function xiangqing(id,teacherId) {
        location.href="/huojiangFenXin/findByJiaoSaiId?id="+id+"&teacherId="+teacherId;
    }

</script>
</body>
</html>
