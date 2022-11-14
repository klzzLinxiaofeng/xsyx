<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/views/embedded/common.jsp"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/css/layui.css"
          media="all">
    <script type="text/javascript" defer="defer" src="${pageContext.request.contextPath}/res/plugin/My97DatePicker/WdatePicker.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/extra/jquery.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/layui.all.js"></script>
    <title>全镇成绩</title>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        全镇成绩

                        <p class="btn_link" style="float: right;">
                            <a class="a1" href="/res/excel/TwonTemplate.xlsx"   >下载模板</a>
                                <a href="javascript:void(0)"  id="addBtn"  onclick="addPage()" class="a4">导入</a>
                            <a id="downLoadExcel"  class="a2" href="#" onclick="downLoadExcel();" class="a2" >导出全镇成绩</a>
                        </p>
                    </h3>
                </div>
                <div class="content-widgets">
                    <div class="widget-container">

                        <div class="select_b">
                            <div class="select_div"><span>学年：</span>
                                <select id="xn" name="xn" class="chzn-select"
                                        style="width:200px;">

                                </select>
                            </div>
                            <div class="select_div"><span>学期：</span>
                                <select id="xq" name="xq" class="chzn-select"
                                        style="width:200px;padding-top: 4px;">

                                </select>
                            </div>
                            <div class="select_div"><span>考试类型：</span>
                                <select id="testName" name="testName" class="chzn-select"
                                        style="width:200px;padding-top: 4px;">
                                    <option value="">请选择</option>
                                    <option>期中考试</option>
                                    <option>期末考试</option>
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
<script type="text/javascript">

        //不提供调用，请勿调用（绿色title）
        initWindowBase = function (onWhere, title, url, width, height, topVal, shift) {
            if ("top" === onWhere) {
                return window.top.layer.open({
                    skin: 'layui-layer-lvse', //样式类名
                    type: 2,
                    title: title,
                    //closeBtn: false, //显示关闭按钮
                    shadeClose: true,
                    shade: 0.8,
                    area: [width + 'px', height + 'px'],
                    /*  offset:[ topVal + 'px', '' ], */
                    maxmin: false, //开启最大化最小化按钮
                    shift: shift,
                    content: url //iframe的url，no代表不显示滚动条
                    // time: 2000, //2秒后自动关闭
                });
            } else if ('cur' === onWhere) {
                return layer.open({
                    /* extend: ['skin/myskin/style.css'], //加载您的扩展样式
                    skin: 'layer-ext-yourskin', //一旦设定，所有弹层风格都采用此主题。 */
                    skin: 'layui-layer-lvse', //样式类名
                    type: 2,
                    title: title,
                    //closeBtn: false, //显示关闭按钮
                    shadeClose: true,
                    shade: 0.8,
                    area: [width + 'px', height + 'px'],
                    /*  offset:[ top + 'px', '' ], */
                    maxmin: false, //开启最大化最小化按钮
                    shift: shift,
                    content: url //iframe的url，no代表不显示滚动条
                });
            }
        }

        //在最顶层窗口的左侧显示窗口 title:窗口标题；url：加载的页面url；width：窗口宽度；height：窗口高度；top：距离顶部的距离
       initWinOnTopFromLeft = function (title, url, width, height, top) {
            if (width === undefined) {
                width = $(parent.window).width() - 50;
            }
            if (height == undefined) {
                height = $(parent.window).height() - 50;
            }
            if (top == undefined) {
                /* top = '20'; */
            }
            return initWindowBase('top', title, url, width, height, top, 'left');
        }

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
        if($("#testName").val()==""){
            layer.msg("请选择考试类型");
            return;
        }
        reloadTable();
    });

    function addPage() {
        initWinOnTopFromLeft('导入全镇成绩', '/performance/analysis/addPage', '500', '500');
    }
    //导出对话框
    function downLoadExcel(){
        var xn = $("#xn").val();
        var xq = $("#xq").val();
        var testName = $("#testName").val();
        if(testName==""){
            layer.msg("请选择考试类型");
            return;
        }
        var param = "xn="+xn+"&"+"xq="+xq+"&"+"testName="+testName;
        var url = "${pageContext.request.contextPath}/performance/analysis/downLoadExcel?";
        url = url+param;
        $("#downLoadExcel").attr("href", url);
        }

    function reloadTable(){
        var option=getTableOption();
        table.render(option)
    }

    function getTableOption(){
        var option= {
            elem: '#weekTable'
            , url: '/performance/analysis/getall'
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
                testName:$("#testName").val(),
            }, page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                , groups: 3 //只显示 1 个连续页码
            }
            , cols: [[
                {field:'grade', title: '年级',  align: 'center'}
                ,{field:'subjects', title: '科目', align: 'center'}
                ,{field:'testName', title: '考试名称', align: 'center'}
                ,{field:'average', title: '全镇平均分', align: 'center'}
                ,{field:'percentPass', title: '全镇合格率',align: 'center'}
                ,{field:'proficiency', title: '全镇优秀率', align: 'center'}
            ]]
        }
        return option;
    }
    function initXnXq(xnId,xqId,callback){
        $("#"+xnId).change(function(){
            $("#"+xqId).html("");
            addOption('/teach/schoolTerm/list/json?schoolYear='+$(this).val(), xqId, "code", "name")
        })
        addOption('/teach/schoolYear/list/json',xnId,"year","name",function(d){
            if(d.length>0) {
                addOption('/teach/schoolTerm/list/json?schoolYear='+d[0].year, xqId, "code", "name", callback)
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
</body>
</html>
