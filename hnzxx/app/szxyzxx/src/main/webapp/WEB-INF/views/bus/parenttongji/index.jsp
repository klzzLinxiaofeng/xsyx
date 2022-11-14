<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <script type="text/javascript" defer="defer" src="/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/css/layui.css"  media="all">
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/layui.all.js"></script>
    <title>家长接送统计</title>
    <style>
        .breadcrumb{
            display: none;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon" />
        <jsp:param value="kkkkkk" name="menuId" />
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="layui-tab-content" style="height: 100px;">
                <div class="layui-tab-item layui-show">
                    <div class="content-widgets white">
                        <div class="widget-head">
                        </div>
                        <div class="light_grey"></div>
                        <div class="content-widgets">
                            <div class="widget-container">
                                <div class="select_b">
                                    <div class="select_div">
                                        <span>日期：</span>
                                        <input type="text" id="date"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" id="startDate" style="margin-bottom: 0; padding: 6px; width: 100px;" value="${date}"/>
                                    </div>
                                    <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                                    <div class="clear"></div>
                                </div>
                                接送总人数：<span id="zongshu"></span>，已出校总人数：<span id="chuxiao"></span>
                                <table class="responsive table table-striped" id="data-table">
                                    <thead>
                                    <tr role="row">
                                        <th>序号</th>
                                        <th>年级</th>
                                        <th>今日接送总人数</th>
                                        <th>今日接送已出校人数</th>
                                    </tr>
                                    </thead>
                                    <tbody  id="tbody">
                                    </tbody>

                                </table>
                                <div class="clear"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-tab-item layui-show">
                </div>
            </div>

        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        search();
    })



    function search() {
        var date=$("#date").val();
        var url="";
        if(date!=null && date!=""){
             url = "/bus/pickData/tongjiAll?nowDate="+date;
        }else{
            url = "/bus/pickData/tongjiAll";
        }
        $.get(url,function (data) {
            var d=JSON.parse(data);
            $("#zongshu").text(d.zongshu[0].number);
            $("#chuxiao").text(d.parentChuZhonShu[0].number);
            $("#tbody").html("");
            for(var i=0;i<d.list.length;i++){
                var str="<tr>" +
                    "<td>"+(i+1)+"</td>" +
                    "<td>"+d.list[i]['name']+"</td>" +
                    "<td>"+d.list[i]['gradeNumber']+"</td>" +
                    "<td>"+d.list[i]['yichuxiao']+"</td></tr>"
                $("#tbody").append(str);
            }
        })
    }
</script>
</html>