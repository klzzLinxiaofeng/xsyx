<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/css/layui.css"
          media="all">
    <script type="text/javascript" defer="defer" src="/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/layui.all.js"></script>
    <title>学期优秀班级</title>
    <style type="text/css">

    </style>
</head>

<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param name="title" value="学期优秀班级"/>
        <jsp:param name="icon" value="icon-glass"/>
        <jsp:param name="menuId" value="${param.dm}"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        学期优秀班级
                    </h3>
                </div>
                <div class="content-widgets">
                    <div class="widget-container">

                        <div class="select_b">
                            <div class="select_div"><span>学年：</span>
                                <select id="xn" name="xn" class="chzn-select"
                                        style="width:200px;"></select>
                            </div>

                            <div class="select_div"><span>学期：</span>
                                <select id="xq" name="xq" class="chzn-select"
                                        style="width:200px;padding-top: 4px;">
                                </select>
                            </div>
                            <button type="button" class="btn btn-primary" id="search">查询</button>
                            <div class="clear"></div>
                        </div>
                        <table class="layui-hide" id="weekTable" lay-filter="weekTable"></table>

                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<script type="text/javascript">
    var position = '#weekTable';
    var util = layui.util;
    var tableOption=null;
    var table=null
    $(function () {
        initXnXq("xn","xq",function(){
            layui.use(['table'], function () {
                table = layui.table;
                tableOption=getTableOption();
                tableOption.where.queryId=genQueryId();
                table.render(tableOption)
            });
        });

    });

    // 查询功能
    $("#search").bind("click", function () {
        reloadTable();
    });


    function reloadTable(){
        var where=tableOption.where;
        where.xn=$("#xn").val();
        where.xq=$("#xq").val();
        if(where.queryId!=undefined && where.queryId!=null){
            where.previousQueryId=where.queryId;
        }
        where.queryId=genQueryId();
        table.render(tableOption);
    }


    function getTableOption(){
        var option= {
            elem: '#weekTable'
                , url: '/dy/ex/ana/xqList'
            , parseData: function (res) { //res 即为原始返回的数据
            return {
                "code": 0, //解析接口状态
                "msg": "success", //解析提示文本
                "count": res.page.totalRows, //解析数据长度
                "data": res.list //解析数据列表
            };
        }
        , where: {
                xn: $("#xn").val(),
                xq:$("#xq").val()
        }
        , request: {
            pageName: 'currentPage',
                limitName: 'pageSize'
            //页码和显示数量
        }
        , page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                , groups: 3 //只显示 1 个连续页码
        }
        , cols: [[
                {type:'numbers', title: '排名', align: 'center'},
                 {field: 'teamName', title: '班级', align: 'center'},
                {field: 'sumScore', title: '总分', align: 'center'},
                {
                    title: '一日常规得分', align: 'center',templet:function(d){
                        var yrcgSum=d.yrcgSum;
                        var yrcgScore=d.yrcgScore;
                        var yrcgBl=d.yrcgBl;
                        if(yrcgSum==undefined){
                            yrcgSum=0;
                            yrcgScore=0;
                            yrcgBl=0.3
                        }
                        return yrcgScore+"("+yrcgSum+"×"+(yrcgBl*100)+"%)";
                    }
                },
                {
                    title: '年级评价得分', align: 'center',templet:function(d){
                        var njpjSum=d.njpjSum;
                        var njpjScore=d.njpjScore;
                        var njpjBl=d.njpjBl;
                        if(njpjSum==undefined){
                            njpjSum=0;
                            njpjScore=0;
                            njpjBl=0.2
                        }
                        return njpjScore+"("+njpjSum+"×"+(njpjBl*100)+"%)";
                    }
                },
                {
                    title: '学校抽检得分', align: 'center',templet:function(d){
                        var xxcjSum=d.xxcjSum;
                        var xxcjScore=d.xxcjScore;
                        var xxcjBl=d.xxcjBl;
                        if(xxcjSum==undefined){
                            xxcjSum=0;
                            xxcjScore=0;
                            xxcjBl=0.1
                        }
                        return xxcjScore+"("+xxcjSum+"×"+(xxcjBl*100)+"%)";
                    }
                },
                {
                    title: '大课间得分', align: 'center',templet:function(d){
                        var dkjSum=d.dkjSum;
                        var dkjScore=d.dkjScore;
                        var dkjBl=d.dkjBl;
                        if(dkjSum==undefined){
                            dkjSum=0;
                            dkjScore=0;
                            dkjBl=0.1
                        }
                        return dkjScore+"("+dkjSum+"×"+(dkjBl*100)+"%)";
                    }
                }
                ,
                {
                    title: '大型活动得分', align: 'center',templet:function(d){
                        var dxhdSum=d.dxhdSum;
                        var dxhdScore=d.dxhdScore;
                        var dxhdBl=d.dxhdBl;
                        if(dxhdSum==undefined){
                            dxhdSum=0;
                            dxhdScore=0;
                            dxhdBl=0.1
                        }
                        return dxhdScore+"("+dxhdSum+"×"+(dxhdBl*100)+"%)";
                    }
                },
                {
                    title: '个人荣誉得分', align: 'center',templet:function(d){
                        var grrySum=d.grrySum;
                        var grryScore=d.grryScore;
                        var grryBl=d.grryBl;
                        if(grrySum==undefined){
                            grrySum=0;
                            grryScore=0;
                            grryBl=0.1
                        }
                        return grryScore+"("+grrySum+"×"+(grryBl*100)+"%)";
                    }
                }
                ,
                {
                    title: '集体荣誉得分', align: 'center',templet:function(d){
                        var jtrySum=d.jtrySum;
                        var jtryScore=d.jtryScore;
                        var jtryBl=d.jtryBl;
                        if(jtrySum==undefined){
                            jtrySum=0;
                            jtryScore=0;
                            jtryBl=0.1
                        }
                        return jtryScore+"("+jtrySum+"×"+(jtryBl*100)+"%)";
                    }
                }
        ]]
        }

        return option;
    }


    function genQueryId(){
        return Math.random().toString(36).slice(-8)
    }

    function initXnXq(xnId,xqId,callback){
        $("#"+xnId).change(function(){
            $("#"+xqId).html("");
            addOptionxq('/teach/schoolTerm/list/json?schoolYear='+$(this).val(), xqId, "code", "name")
        })
        addOption('/teach/schoolYear/list/json',xnId,"year","name",function(d){
            if(d.length>0) {
                addOptionxq('/teach/schoolTerm/list/json?schoolYear='+d[0].year, xqId, "code", "name", callback)
            }
        })
    }
    function addOptionxq(url,id,valProperty,namePropety,callback){
        var defaultTerm = '${sessionScope[sca:currentUserKey()].schoolTermCode}';
        $.get(url,function(d){
            d=JSON.parse(d);
            for(var i=0;i<d.length;i++){
                var obj=d[i];
                if(defaultTerm==obj[valProperty]) {
                    $("#" + id).append("<option selected=selected value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
                }else{
                    $("#" + id).append("<option  value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");

                }
            }
            if(callback!=null && callback!=undefined) {
                callback(d);
            }
        })
    }
    function addOption(url,id,valProperty,namePropety,callback){
        $.get(url,function(d){
            d=JSON.parse(d);
            for(var i=0;i<d.length;i++){
                var obj=d[i];
                $("#"+id).append("<option value="+obj[valProperty]+">"+obj[namePropety]+"</option>");
            }
            if(callback!=null && callback!=undefined) {
                callback(d);
            }
        })
    }
</script>

</html>