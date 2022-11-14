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
    <title>班级一日常规</title>
    <style type="text/css">
    </style>
</head>

<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param name="title" value="班级一日常规"/>
        <jsp:param name="icon" value="icon-glass"/>
        <jsp:param name="menuId" value="${param.dm}"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        班级一日常规
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


                            <div class="select_div"><span>学期月：</span>
                                <input type="number" name="xqy" id="xqy" style="width: 80px;">
                            </div>

                            <div class="select_div"><span>学期周：</span>
                                <input type="number" name="xqz" id="xqz" style="width: 80px;">
                            </div>

                            <div class="select_div">
                                <span>日期：</span>
                                <input type="text" id="date"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate: '%y-%M-%d'});" id="date" style="margin-bottom: 0; padding: 6px; width: 100px;" value="${date}"/>
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
                , url: '/dy/ex/yrcg/list'
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
                xqz:$("#xqz").val(),
                xqy:$("#xqy").val(),
                teamName:$("#teamName").val(),
                ofDate:$("#date").val()
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
                 {field: 'team_name', title: '班级',width:100, align: 'center'},
                {field: 'of_date', title: '日期', width:120,align: 'center'},
                {field: 'xqy', title: '学期月', width:100,align: 'center'},
                {field: 'xqz', title: '学期周', width:100,align: 'center'},
                {field: 'sum_score', title: '总分', width:100,align: 'center'},
                {field: 'pdxk', title: '佩戴校卡得分',width:120, align: 'center'},
                {field: 'rszj', title: '仪容整洁得分', width:120,align: 'center'},
                {field: 'wmlm', title: '文明礼貌得分', width:120,align: 'center'},
                {field: 'zjyx', title: '自觉有序得分', width:120,align: 'center'},
                {field: 'yskz', title: '有事可做得分', width:120,align: 'center'},
                {field: 'qymb', title: '轻言慢步得分', width:120,align: 'center'},
                {field: 'wmyx', title: '文明游戏得分', width:120,align: 'center'},
                {field: 'zj', title: '书包柜、黑板、讲台、地面整洁得分',width:270, align: 'center'},
                {field: 'grzj', title: '个人物品整洁得分', width:150,align: 'center'},
                {field: 'mckg', title: '门窗及时开关得分', width:150,align: 'center'},
                {field: 'ybjc', title: '眼保健操有序得分', width:150,align: 'center'},
                {field: 'dxhd', title: '大型活动有序得分',width:150, align: 'center'},
                {field: 'dkj', title: '大课间有序得分', width:130,align: 'center'},
                {field: 'xdjsd', title: '校车接送点得分', width:130,align: 'center'},
                {field: 'zjjsd', title: '自驾接送点得分', width:130,align: 'center'},
                {
                    field: 'createDate', title: '导入时间', width:160,align: 'center', templet: function (d) {
                        return util.toDateString(d.create_time);
                    }
                },
                {field: 'creator', title: '导入者', width:100,align: 'center'},
        ]]
        }

        return option;
    }

    function addPage() {
        $.initWinOnTopFromLeft('导入班级一日常规', '/dy/ex/yrcg/addPage', '500', '500');
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
            $.post('/dy/ex/yrcg/delete',{ids:ids},function(d){
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