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
    <title>任课教师（语数英）</title>
    <style type="text/css">

    </style>
</head>

<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param name="title" value="任课教师（语数英）"/>
        <jsp:param name="icon" value="icon-glass"/>
        <jsp:param name="menuId" value="${param.dm}"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        <c:if test="${type==0}">
                        任课教师（语数英）
                        </c:if>
                        <c:if test="${type==1}">
                            任课教师（综合科）
                        </c:if>
                        <p class="btn_link" style="float: right;">
                            <a href="javascript:void(0)" onclick="$.refreshWin();" class="a3"><i
                                    class="fa  fa-undo"></i>刷新列表</a>
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

                            <div class="select_div" ><span>姓名：</span>
                                <input type="text"  id="name" style="width: 120px;">
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

<script type="text/html" id="barDemo">

    {{# if(d.sumScore!=undefined){ }}
    <a class="layui-btn layui-btn-xs" lay-event="down">下载评分表</a>
    {{# } else { }}
    <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="upload">上传评分表</a>
    {{# } }}
</script>
<script type="text/javascript">
    var position = '#weekTable';
    var tool = '#barDemo';
    var util=null;
    var table=null;

    function getList(position, tool) {
        table.render({
            elem: position
            , url: '/student/star/student/theTeacherScoreList'
            , parseData: function (res) { //res 即为原始返回的数据
                return {
                    "code": 0, //解析接口状态
                    "msg": "success", //解析提示文本
                    "count": res.page.totalRows, //解析数据长度
                    "data": res.list //解析数据列表
                };
            }
            , where: {
                //where里是可以传到后台的参数
                year: $("#xn").val()
                , xq: $("#xq").val()
                , name: $("#name").val()
                , type: ${type}
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
                // 显示参数需要修改
                {type:'numbers', title: '排名', align: 'center'}
                ,{field: 'name', title: '姓名', align: 'center'}
                , {field: 'subject', title: '科目', align: 'center'}
                , {field: 'teamName', title: '班级', align: 'center'}
                , {
                    field: 'selfScore', title: '自评', sort: true, align: 'center', templet: function (d) {
                        if (d.selfScore != '' && d.selfScore != null) {
                            return d.selfScore;
                        } else {
                            return "暂无"
                        }
                    }
                }, {
                    field: 'teamScore', title: '小组评', sort: true, align: 'center', templet: function (d) {
                        if (d.teamScore != '' && d.teamScore != null) {
                            return d.teamScore;
                        } else {
                            return "暂无"
                        }
                    }
                }, {
                    field: 'schoolScore', title: '校评', sort: true, align: 'center', templet: function (d) {
                        if (d.schoolScore != '' && d.schoolScore != null) {
                            return d.schoolScore;
                        } else {
                            return "暂无"
                        }
                    }
                }
                , {
                    field: 'createDate', title: '创建时间', sort: true, align: 'center', templet: function (d) {
                        return util.toDateString(d.createDate);
                    }
                }
                , {field: 'sumScore', title: '总分', align: 'center'}
                , {fixed: 'right', title: '操作', width: 178, align: 'center', toolbar: tool}
            ]]
        });
    }

    // 查询功能
    $("#search").bind("click", function () {
        getList(position, tool);
    });

    $(function () {
        initXnXq("xn","xq",function(){
            layui.use(['table'], function () {
                table = layui.table;

                util = layui.util;

                //监听工具条
                table.on('tool(weekTable)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                    var layEvent = obj.event;
                    var data = obj.data; // 获得当前行数据
                    if (layEvent == 'down') {
                        window.open(data.filePath);
                    } else if (layEvent == 'upload') {
                        // 	导入excel
                        $.initWinOnTopFromLeft('上传任课教师绩效评分', '${ctp}/student/star/student/creator?teacherId=' + data.id, '500', '450');
                    }
                });
                getList(position,tool);
            });
        });
    });

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