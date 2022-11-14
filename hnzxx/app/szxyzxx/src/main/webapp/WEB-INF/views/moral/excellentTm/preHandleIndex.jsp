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
    <title>优秀班主任初评</title>
    <style type="text/css">

    </style>
</head>

<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param name="title" value="优秀班主任初评"/>
        <jsp:param name="icon" value="icon-glass"/>
        <jsp:param name="menuId" value="${param.dm}"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        优秀班主任初评
                        <p class="btn_link" style="float: right;">
                            <a href="javascript:void(0)" id="addBtn"  onclick="openAddPage()" class="a4">设置通过初审人员</a>
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

                            <div class="select_div"><span>初评状态：</span>
                                <select id="firstState" name="firstState" class="chzn-select"
                                        style="width:120px;padding-top: 4px;" onchange="changeState(this.value)">
                                    <option selected value="0">待评审</option>
                                    <option  value="1">已通过</option>
                                    <option  value="2">未通过</option>
                                </select>
                            </div>

                            <div class="select_div" id="sfpf"><span>是否评分：</span>
                                <select id="sumScore" name="sumScore" class="chzn-select"
                                        style="width:120px;padding-top: 4px;">
                                    <option  value="1" selected>未评分</option>
                                    <option  value="2">已评分</option>
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
                table.render(tableOption);

                //监听工具条
                table.on('tool(weekTable)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                    var layEvent = obj.event;
                    var data = obj.data; // 获得当前行数据
                    if (layEvent == 'updateScore') {
                        $.initWinOnTopFromLeft('【'+data.user_name+'】【'+data.user_team+'】班主任评分', '/dy/excellentTm/updateScoreIndex?id='+data.id, '500', '300');
                    }
                });


            });
        });

    });

    function changeState(val){
        if(val=="0"){
            $("#sfpf").show();
        }else{
            $("#sfpf").hide();
        }
    }

    // 查询功能
    $("#search").bind("click", function () {
        reloadTable();
    });


    function reloadTable(){
        var where=tableOption.where;
        where.firstState=$("#firstState").val();

        if(where.firstState=="0"){
            where.sumScore=$("#sumScore").val();
        }else{
            where.sumScore=null;
        }

        var cols=tableOption.cols[0];
        if(where.firstState=="1" || (where.firstState=="0" && where.sumScore=="2")){
            if(cols[0].title!="排名") {
                cols.splice(0, 0, {title: '排名', align: 'center', type: 'numbers'});
            }
        }else{
            if(cols[0].title=="排名") {
                cols.splice(0,1);
            }
        }
        where.xn=$("#xn").val();
        where.xq=$("#xq").val();
        if(where.queryId!=undefined && where.queryId!=null){
            where.previousQueryId=where.queryId;
        }

        where.queryId=genQueryId();
       table.render(tableOption)
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
            year: $("#xn").val(),
                xq:$("#xq").val(),
                firstState:$("#firstState").val(),
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
                    if(d.sum_score==null){
                        btnHtml+='<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="updateScore">评分</a>';
                    }
                    return btnHtml;
                }}
        ]]
        }

        if(option.where.firstState=="0"){
            option.where.sumScore=$("#sumScore").val();
        }
        return option;
    }


    function genQueryId(){
        return Math.random().toString(36).slice(-8)
    }

    function delById(id){
        var li=layer.load(2);
        $.post('/dy/excellentTm/delete',{id:id},function(d){
            layer.close(li);
            d=JSON.parse(d);
            if(d.code==200) {
                layer.msg('删除成功', {
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

    function openAddPage() {
        layer.prompt({
            formType: 0,
            title: '请输入通过人数（前x名进入终审）'
        },function(value, index, elem){

            var n=parseInt(value);
            if(isNaN(n)){
                layer.msg("请输入有效的数字");
                return;
            }

            if(n<1){
                layer.msg("请输入有效的数字");
                return;
            }

            layer.confirm('确定让排名前【'+n+'】名进入终审吗？',function(){

                var param={
                    year: $("#xn").val(),
                        xq:$("#xq").val(),
                    num:n,
                    type:0
                }
                var li=layer.load(2);
                $.post('/dy/excellentTm/pass',param,function(d){
                    layer.close(li);
                    d=JSON.parse(d);
                    if(d.code==200) {
                        layer.msg('初审成功', {
                            icon: 1,
                            time: 2000
                        }, function () {
                            layer.close(index);
                            reloadTable();
                        });
                    }else{
                        layer.msg(d.msg);
                    }
                })


            })

        });

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