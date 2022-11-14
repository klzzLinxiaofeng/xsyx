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
    <title>优秀副班主任</title>
    <style type="text/css">

    </style>
</head>

<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param name="title" value="优秀副班主任"/>
        <jsp:param name="icon" value="icon-glass"/>
        <jsp:param name="menuId" value="${param.dm}"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        优秀副班主任
                        <p class="btn_link" style="float: right;">
                            <a href="javascript:void(0)" id="addBtn"  onclick="addPage()" class="a4">导入</a>
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
       table.render(option)
    }


    function getTableOption(){
        var option= {
            elem: '#weekTable'
                , url: '/dy/excellentSm/list'
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
                 {field: 'team_name', title: '班级', align: 'center'},
                {field: 'score', title: '考核总分数', align: 'center'},
                {
                    field: 'createDate', title: '导入时间', align: 'center', templet: function (d) {
                        return util.toDateString(d.createDate);
                    }
                },
                {field: 'import_user_name', title: '上传者', align: 'center'},
        ]]
        }

        return option;
    }

    function addPage() {
        $.initWinOnTopFromLeft('导入优秀副班主任', '/dy/excellentSm/add', '500', '350');
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