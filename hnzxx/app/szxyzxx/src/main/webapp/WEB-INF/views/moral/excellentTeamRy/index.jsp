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
    <title>班级荣誉</title>
    <style type="text/css">

    </style>
</head>

<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param name="title" value=""/>
        <jsp:param name="icon" value="icon-glass"/>
        <jsp:param name="menuId" value="${param.dm}"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        <c:if test="${type==0}">
                            班级个人荣誉
                        </c:if>
                        <c:if test="${type==1}">
                            班级团体荣誉
                        </c:if>
                        <p class="btn_link" style="float: right;">
                            <a href="javascript:void(0)"   onclick="delCheck()" class="a3">删除勾选</a>
                            <c:if test="${not empty userId}">
                                <a href="javascript:void(0)" id="addBtn"  onclick="addPage()" class="a4">导入</a>
                            </c:if>
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

                            <div class="select_div" ><span>班级：</span>
                                <input type="text"  id="teamName" style="width: 120px;">
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
    var userId='${userId}';
    var type=${type};
    var position = '#weekTable';
    var util = layui.util;
    var table=null
    $(function () {
        initXnXq("xn","xq",function(){
            layui.use(['element', 'table', 'rate'], function () {
                var $ = layui.jquery, element = layui.element, rate = layui.rate;
                var laydate = layui.laydate;
                table = layui.table;
                //执行一个laydate实例
                laydate.render({
                    elem: '#month' //指定元素
                    , type: 'month'
                });
                table.render(getTableOption())
            });
        });

    });

    // 查询功能
    $("#search").bind("click", function () {
        reloadTable();
    });


    function reloadTable(){
        var option=getTableOption();
        if(userId!=""){
            option.where.creatorId=userId;
        }
       table.render(option)
    }


    function getTableOption(){
        var option= {
            elem: '#weekTable'
                , url: '/dy/ex/ry/list'
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
                xq:$("#xq").val(),
                teamName:$("#teamName").val(),
                type:${type}
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
                {type:'checkbox'},
                 {field: 'team_name', title: '班级', align: 'center'},
                {field: 'gjj', title: '国家级得分', align: 'center'},
                {field: 'shengji', title: '省级得分', align: 'center'},
                {field: 'shiji', title: '市级得分', align: 'center'},
                {field: 'zj', title: '镇级得分', align: 'center'},
                {field: 'xj', title: '校级得分', align: 'center'},
                {field: 'sum_score', title: '总分', align: 'center'},
                {
                    field: 'createDate', title: '导入时间', align: 'center', templet: function (d) {
                        return util.toDateString(d.create_time);
                    }
                },
                {field: 'creator', title: '导入者', align: 'center'},
        ]]
        }

        return option;
    }

    function addPage() {
        var title="导入个人荣誉";
        if(type==1){
            title="导入团体荣誉";
        }
        $.initWinOnTopFromLeft(title, '/dy/ex/ry/addPage?type=${type}', '500', '350');
    }

    function delCheck(){
        var checkStatus = table.checkStatus('weekTable'); //idTest 即为基础参数 id 对应的值
        var selected=checkStatus.data;
        if(selected.length==0){
            layer.msg("请选择要删除的数据");
            return;
        }
        var ids="";
        for(var i=0;i<selected.length;i++){
            if(i!=0){
                ids+=",";
            }
            ids+=selected[i].id;
        }

        layer.confirm('确定删除所选数据？', function(index){
            var li=layer.load(2);
            $.post('/dy/ex/ry/delete',{ids:ids},function(d){
                layer.close(li);
                layer.close(index);
                d=JSON.parse(d);
                if(d.code==200) {
                    layer.msg('删除成功', {
                        icon: 1,
                        time: 2000
                    }, function () {
                        reloadTable();
                    });
                }else{
                    layer.alert(d.msg);
                    layer.close(index);
                }
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