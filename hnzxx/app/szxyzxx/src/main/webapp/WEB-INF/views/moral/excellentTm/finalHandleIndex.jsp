<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/css/layui.css"
          media="all">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/layui.all.js"></script>
    <title>优秀班主任终评</title>
    <style type="text/css">

    </style>
</head>

<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param name="title" value="优秀班主任终评"/>
        <jsp:param name="icon" value="icon-glass"/>
        <jsp:param name="menuId" value="${param.dm}"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        优秀班主任终评
                        <p class="btn_link" style="float: right;">
                            <a href="javascript:void(0)" id="addBtn"  onclick="complate()" class="a4">完成终评</a>
                        </p>
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

                            <div class="select_div"><span>终评状态：</span>
                                <select id="finalState" name="finalState" class="chzn-select"
                                        style="width:120px;padding-top: 4px;" >
                                    <option selected value="0">待评审</option>
                                    <option  value="1">已通过</option>
                                    <option  value="2">未通过</option>
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
            layui.use([ 'table'], function () {
                table = layui.table;
                tableOption=getTableOption();
                tableOption.where.queryId=genQueryId();
                table.render(tableOption)

                //监听工具条
                table.on('tool(weekTable)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                    var layEvent = obj.event;
                    var data = obj.data; // 获得当前行数据
                    if (layEvent == 'pass') {
                        layer.confirm('确定【'+data.user_name+'】成为优秀班主任吗？',function(){
                            pass(data.id);
                        })
                    }
                });


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
        where.finalState=$("#finalState").val();
        if(where.queryId!=undefined && where.queryId!=null){
            where.previousQueryId=where.queryId;
        }
        where.queryId=genQueryId();
        table.render(tableOption);
    }


    function getTableOption(){
        var option= {
            elem: '#weekTable'
                , url: '/dy/excellentTm/list'
            , parseData: function (res) { //res 即为原始返回的数据
            return {
                "code": 0, //解析接口状态
                "msg": "success", //解析提示文本
                "count": res.page.totalRows, //解析数据长度
                "data": res.list //解析数据列表
            };
        }
        , where: {
                firstState:1,
            year: $("#xn").val(),
                xq:$("#xq").val(),
                finalState:$("#finalState").val(),
                queryId:genQueryId()
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
                {title: '排名', align: 'center',type:'numbers'},
                 {field: 'user_name', title: '班主任', align: 'center'},
                {field: 'user_team', title: '班级', align: 'center'},
            { title: '工作汇报材料', align: 'center', templet: function (d) {

                    return '<a style="color:blue;" href="${filePrefix}'+d.work_data_path+'">'+d.work_data_name+'</a>'
                }}

                , { title: '工作汇报材料得分', align: 'center',templet:function(d){
                        if(d.sum_score==null){
                            return "";
                        }
                        return d.applyFinalScore+"("+d.sum_score+"×10%)";
                    }}
                , {title: '班级量化得分', align: 'center',templet:function(d){
                        return d.dyFinalScore+"("+d.dySumScore+"×70%)";
                    }}
                , {field: 'finalScore', title: '总得分', align: 'center'}
            , {fixed: 'right', title: '操作', align: 'center', templet: function (d) {
                    var btnHtml="";
                    if(d.final_state==0){
                        btnHtml+='<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="pass">通过终评</a>';
                    }
                    return btnHtml;
                }}
        ]]
        }

        return option;
    }

    function pass(id){
        var li=layer.load(2);
        var param={id:id,finalState:1};
        $.post('/dy/excellentTm/updateToFinalPass',param,function(d){
            layer.close(li);
            d=JSON.parse(d);
            if(d.code==200) {
                layer.msg('操作成功', {
                    icon: 1,
                    time: 2000
                }, function () {
                    reloadTable();
                });
            }else{
                layer.msg(d.msg);
            }
        })

    }

    function complate() {
        layer.confirm('确定该学期优秀班主任评选已完成吗？',function(){
            var param={
                year: $("#xn").val(),
                xq:$("#xq").val()
            }
            var li=layer.load(2);
            $.post('/dy/excellentTm/complete',param,function(d){
                layer.close(li);
                d=JSON.parse(d);
                if(d.code==200) {
                    layer.msg('操作成功', {
                        icon: 1,
                        time: 2000
                    }, function () {
                        reloadTable();
                    });
                }else{
                    layer.msg(d.msg);
                }
            })
        })

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