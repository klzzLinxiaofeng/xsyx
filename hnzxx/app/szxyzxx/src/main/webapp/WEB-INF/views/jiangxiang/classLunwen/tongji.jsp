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
    <title>教赛数据统计</title>
    <style>
        #publicClass_list_content {
            font-size: 100px;
        }
    </style>

    <style type="text/css">
        #groupLists{
            background: #f5f5f8;
            position: fixed;
            text-align:center;
            width: 60%;
            height: 50%;
            box-shadow: 0 3px 4px 0 #00000020, 0 4px 12px #00000020;
            border: 1px solid #d9d9d9;
        }
        .select_div{
            margin:30px;
            margin-bottom:130px;
        }
        .off{
            position:absolute;
            top:5px;
            right:10px;
            color: white;
        }
        /*屏幕中间*/
        #groupLists{
            right: 20%;
            top: 20%;
        }
    </style>
    <style type="text/css">
        #teacherBiao {
            /*设置相邻单元格的边框间的距离*/
            border-spacing: 0;
            /*表格设置合并边框模型*/
            border-collapse: collapse;
            text-align: center;
        }
        /*关键设置 tbody出现滚动条*/
        #thbiao {
            display: block;
            height: 220px;
            overflow-y: scroll;
        }


        #idTh tr,
        #thbiao tr {
            display: table;
            width: 100%;
            table-layout: fixed;
        }
        /*关键设置：滚动条默认宽度是16px 将thead的宽度减16px*/
        #idTh {
            width: calc( 100% - 1em)
        }
        #idTh th {
            background: #ccc;
        }
        #teacherBiao {
            border-spacing: 0;
            border-collapse: collapse;
            text-align: center;
            width: 98%;
            margin-left: 1%;
        }

        #thbiao {
            display: block;
            height: 100%;
            overflow: auto;
        }
        #thbiao::-webkit-scrollbar {
            width: 0;
            height: 0;
        }
    </style>
</head>
<body>
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets white">
            <div class="widget-head">
                <h3>
                    教赛数据统计
                    <p class="btn_link" style="float: right;">
                        <a href="javascript:void(0)" class="a3"
                           onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
                        <%--<a id="downLoadExcel"  class="a2" href="#" onclick="daochu();" class="a2" >导出获奖记录</a>
--%>
                    </p>
                </h3>
            </div>
            <div class="light_grey"></div>
            <div class="content-widgets">
                <div class="widget-container">
                    <div class="select_b">
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
                            <th>教师姓名</th>
                            <th>工号</th>
                            <th>总得分</th>
                            <th>总绩效得分</th>
                            <th class="caozuo" style="max-width: 250px;">操作</th>
                        </tr>
                        </thead>
                        <tbody id="publicClass_list_content">
                        <jsp:include page="./tongjiList.jsp"/>
                        </tbody>
                    </table>
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="publicClass_list_content"/>
                        <jsp:param name="url" value="/huojiangFenXin/findByTongJi?sub=list"/>
                        <jsp:param name="pageSize" value="${page.pageSize}"/>
                    </jsp:include>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="groupLists"  style="display: none">
    <div style="background-color: #2F6144;height: 30px;">
            <span style="
                         position: absolute;
                         top:5px;left:10px;color: white;">获奖记录</span>
        <div class="off" onclick="closestudent();">X</div></div>
    <%--<div class="clearfix list-search-bar x-search">
        <div class="select_b">
            <div class="select_div">
                <span>教师姓名：</span>
                <input id="teacherNames" name="teacherNames" class=""  style="width:200px;padding-top: 4px;"/>
            </div>
            <button type="button" class="btn btn-primary" style="color: chartreuse" onclick="teacherList()">查询</button>
            <div class="clear"></div>
        </div>
    </div>--%>
   <%-- <div>
        <div style="text-align: left">
            <span>已选获奖教师</span>
        </div>
        <div id="yixuanTeacher" style="width: 100%;height: 50px;margin: 2px;position:relative">

        </div>
    </div>--%>
    <table id="teacherBiao" width="100%" border="1">
        <thead id="idTh">
        <tr>
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
        </tr>
        </thead>
        <tbody id="thbiao">

        </tbody>
    </table>
</div>
<script type="text/javascript">
    //打开堂狂
    function openstudent() {
        $("#groupLists").attr("style", "display:block;");
    }
    //关闭堂狂
    function closestudent() {
        $("#groupLists").attr("style", "display:none;");
    }



    function search() {
        var val = {};
        var teacherName=$("#teacherName").val();
        if(teacherName!=null && teacherName!=""){
            val.teacherName=teacherName;
        }
        var id = "publicClass_list_content";
        var url = "/huojiangFenXin/findByTongJi?sub=list";
        myPagination(id, val, url);
    }
    //获奖记录
    function xiangqing(teacherId) {

        var url="/huojiangFenXin/findByhuojiangJiLu?teacherId="+teacherId;
        $.get(url,function(d){
            $("#thbiao").html("");
            var obj=JSON.parse(d);
            for(var a=0;a<obj.length;a++){
                var student="<tr>" +
                    "<td>"+a+1+"</td>" +
                    "<td>"+obj[a]['theme']+"</td>" +
                    "<td>"+obj[a]['type']+"</td>" +
                    "<td>"+obj[a]['shuXing']+"</td>" +
                    "<td>"+obj[a]['winningTime']+"</td>" +
                    "<td>"+obj[a]['teacherNames']+"</td>" +
                    "<td>"+obj[a]['studentNames']+"</td>" +
                    "<td>"+obj[a]['nameWoke']+"</td>" +
                    "<td>"+obj[a]['winningLevelName']+"</td>" +
                    "<td>"+obj[a]['dengciName']+"</td>" +
                    "<td>"+obj[a]['allocated']+"</td>" +
                    "<td>"+obj[a]['score']+"</td>" +
                    "<td>"+obj[a]['jiXiaoDeFen']+"</td></tr>";
                $("#thbiao").append(student);
            }
        })
        $("#groupLists").attr("style", "display:block;");
    }
</script>
</body>
</html>
