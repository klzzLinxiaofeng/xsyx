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
    <title>周明星学生</title>
    <style type="text/css">

    </style>
</head>

<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param name="title" value="周明星学生"/>
        <jsp:param name="icon" value="icon-glass"/>
        <jsp:param name="menuId" value="${param.dm}"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        周明星学生
                        <p class="btn_link" style="float: right;">
                            <a href="javascript:void(0)" onclick="$.refreshWin();" class="a3"><i
                                    class="fa  fa-undo"></i>刷新列表</a>
                        </p>
                    </h3>
                </div>
                <%-- <div class="light_grey">
            </div>--%>
                <div class="content-widgets">
                    <div class="widget-container">
                        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">

                            <ul class="layui-tab-title">
                                <li class="layui-this" style="font-size: 15px" data-status="0">周之星</li>
                                <li data-status="1" style="margin-left: 10px;font-size: 15px;">月之星</li>
                                <li data-status="2" style="margin-left: 10px;font-size: 15px;">学期之星</li>
                            </ul>
                            <div class="layui-tab-content" style="height: 100px;display: contents;">
                                <div class="layui-tab-item layui-show">
                                    <div class="select_b">
                                        <div class="select_div"><span>学年：</span>
                                            <select id="xn" name="xn" class="chzn-select"
                                                    style="width:120px;"></select>
                                        </div>

                                        <div class="select_div"><span>学期：</span>
                                            <select id="xq" name="xq" class="chzn-select"
                                                    style="width:150px;padding-top: 4px;">
                                                <option value=''> 请选择</option>

                                            </select>
                                        </div>
                                        <div class="select_div"><span>年级：</span>
                                            <select id="nj" name="nj" class="chzn-select"
                                                    style="width:120px;"></select>
                                        </div>
                                        <div class="select_div"><span>班级：</span><select id="bj" name="bj"
                                                                                        class="chzn-select"
                                                                                        style="width:120px;"></select>
                                        </div>

                                        <div class="select_div mobile" id="names">
                                            <span>周数：</span><input type="text" id="name" name="name"
                                                                   data-id-container="mobile"
                                                                   style="margin-bottom: 0; padding: 6px; width: 120px; margin-right: 3px;">
                                        </div>


                                        <button type="button" class="btn btn-primary" id="search">查询</button>


                                        <div class="clear"></div>
                                    </div>
                                    <table class="layui-hide" id="weekTable" lay-filter="weekTable"></table>
                                </div>
                                <div class="layui-tab-item">
                                    <div class="select_b">
                                        <div class="select_div"><span>学年：</span>
                                            <select id="xn2" name="xn2" class="chzn-select"
                                                    style="width:120px;"></select>
                                        </div>

                                        <div class="select_div"><span>学期：</span>
                                            <select id="xq2" name="xq2" class="chzn-select"
                                                    style="width:150px;padding-top: 4px;">
                                                <option value=''> 请选择</option>

                                            </select>
                                        </div>
                                        <div class="select_div"><span>月份：</span>
                                            <input type="text" id="month"
                                                   style="font-size: 14px; width: 100px;height: 17px;">
                                        </div>
                                        <div class="select_div"><span>年级：</span>
                                            <select id="nj2" name="nj2" class="chzn-select"
                                                    style="width:120px;"></select>
                                        </div>
                                        <div class="select_div"><span>班级：</span>
                                            <select id="bj2" name="bj2" class="chzn-select"
                                                    style="width:120px;"></select>
                                        </div>
                                        <button type="button" class="btn btn-primary" id="search2">查询</button>
                                        <div class="clear"></div>
                                    </div>
                                    <table class="layui-hide" id="weekTable2" lay-filter="weekTable2"></table>

                                </div>
                                <div class="layui-tab-item">

                                    <div class="select_b">
                                        <div class="select_div"><span>学年：</span>
                                            <select id="xn3" name="xn3" class="chzn-select"
                                                    style="width:120px;"></select>
                                        </div>

                                        <div class="select_div"><span>学期：</span>
                                            <select id="xq3" name="xq3" class="chzn-select"
                                                    style="width:150px;padding-top: 4px;">
                                                <option value=''> 请选择</option>

                                            </select>
                                        </div>

                                        <div class="select_div"><span>年级：</span>
                                            <select id="nj3" name="nj3" class="chzn-select"
                                                    style="width:120px;"></select>
                                        </div>
                                        <div class="select_div"><span>班级：</span>
                                            <select id="bj3" name="bj3" class="chzn-select"
                                                    style="width:120px;"></select>
                                        </div>
                                        <button type="button" class="btn btn-primary" id="search3">查询</button>
                                        <div class="clear"></div>
                                    </div>
                                    <table class="layui-hide" id="weekTable3" lay-filter="weekTable3"></table>
                                </div>
                            </div>
                        </div>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>


<script type="text/html" id="barDemo">
    {{# if((d.studentName != '' && d.studentName != null)){ }}
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="getall">查看排名</a>
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="down">下载文件</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    {{# } }}

</script>

<script type="text/html" id="barDemo2">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="getall">查看排名</a>
</script>
<script type="text/html" id="barDemo3">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="getall">查看排名</a>
</script>

<script type="text/javascript">

    // 所选标签页
    var systemId = 0;

    var position = '#weekTable';
    var tool = '#barDemo';
    $(function () {


        layui.use(['element', 'table', 'rate'], function () {
            var $ = layui.jquery, table = layui.table, element = layui.element, rate = layui.rate;
            var laydate = layui.laydate;

            //执行一个laydate实例
            laydate.render({
                elem: '#month' //指定元素
                , type: 'month'
            });

            var util = layui.util;


            $.initCascadeSelector({
                "type": "team",
                "yearSelectId": "xn", //学年select标签ID 默认为xn
                "gradeSelectId": "nj", //年级select标签ID 默认为nj
                "teamSelectId": "bj"  //班级select标签ID 默认为bj
                , "yearCallback": function ($this) {
                    getList(position, tool);
                }
                , "yearChangeCallback": function (year) {
                    if (year != "") {
                        $.SchoolTermSelector({
                            "selector": "#xq",
                            "condition": {"schoolYear": year},
                            "afterHandler": function ($this) {
                                $("#xq_chzn").remove();
                                $this.show().removeClass("chzn-done").chosen();
                            }
                        });
                    } else {
                        $("#xq").val("");
                        $("#xq_chzn").remove();
                        $("#xq").show().removeClass("chzn-done").chosen();
                    }
                }
            });

            $.initCascadeSelector({
                "type": "team",
                "yearSelectId": "xn2", //学年select标签ID 默认为xn
                "gradeSelectId": "nj2", //年级select标签ID 默认为nj
                "teamSelectId": "bj2"  //班级select标签ID 默认为bj
                , "yearChangeCallback": function (year) {
                    if (year != "") {
                        $.SchoolTermSelector({
                            "selector": "#xq2",
                            "selectedVal": $("#xn2").val(),
                            "condition": {"schoolYear": year},
                            "afterHandler": function ($this) {
                                $("#xq2_chzn").remove();
                                $this.show().removeClass("chzn-done").chosen();
                            }
                        });
                    } else {
                        $("#xq2").val("");
                        $("#xq2_chzn").remove();
                        $("#xq2").show().removeClass("chzn-done").chosen();
                    }
                }
            });


            $.initCascadeSelector({
                "type": "team",
                "yearSelectId": "xn3", //学年select标签ID 默认为xn
                "gradeSelectId": "nj3", //年级select标签ID 默认为nj
                "teamSelectId": "bj3"  //班级select标签ID 默认为bj
                , "yearChangeCallback": function (year) {
                    if (year != "") {
                        $.SchoolTermSelector({
                            "selector": "#xq3",
                            "selectedVal": $("#xn3").val(),
                            "condition": {"schoolYear": year},
                            "afterHandler": function ($this) {
                                $("#xq3_chzn").remove();

                                $this.show().removeClass("chzn-done").chosen();
                            }
                        });
                    } else {
                        $("#xq3").val("");
                        $("#xq3_chzn").remove();
                        $("#xq3").show().removeClass("chzn-done").chosen();
                    }
                }
            });

            // 标签切换监听
            element.on('tab(docDemoTabBrief)', function (data) {
                var status = $(this).attr('data-status');
                switch (status) {
                    case '0':
                        position = '#weekTable';
                        tool = '#barDemo';
                        getList(position, tool);
                        break;
                    case '1':
                        position = '#weekTable2';
                        tool = '#barDemo2';
                        getList2(position, tool);
                        break;
                    case '2':
                        position = '#weekTable3';
                        tool = '#barDemo3';
                        getList3(position, tool);
                        break;
                    default:
                        position = '#weekTable';
                        tool = '#barDemo';
                }

            })

            function getList(position, tool) {
                table.render({
                    elem: position
                    , url: '/student/star/student/json?dm=${param.dm}'
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
                        , term: $("#xq").val()
                        , gradeId: $("#nj").val()
                        , teamId: $("#bj").val()
                        , name: $("#name").val()
                        , type: 1
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
                        {field: 'yearName', title: '学年', align: 'center'}
                        , {field: 'term', title: '学期', align: 'center'}
                        , {field: 'teamName', title: '班级', align: 'center'}
                        , {field: 'name', title: '周数', align: 'center'}
                        , {
                            field: 'studentName', title: '周明星学生', align: 'center'
                            , templet: function (d) {
                                if (d.gradeNumber == 1) {
                                    return "(探询少年) " + d.studentName;
                                } else if (d.gradeNumber == 2) {
                                    return "(探察少年) " + d.studentName;
                                } else if (d.gradeNumber == 3) {
                                    return "(探听少年) " + d.studentName;
                                } else if (d.gradeNumber == 4) {
                                    return "(探究少年) " + d.studentName;
                                } else if (d.gradeNumber == 5) {
                                    return "(探奇少年) " + d.studentName;
                                } else if (d.gradeNumber == 6) {
                                    return "(探真少年) " + d.studentName;
                                }
                                return "暂无数据！";
                            }
                        }
                        , {
                            field: 'createDate', title: '创建时间', sort: true, align: 'center', templet: function (d) {
                                return util.toDateString(d.createDate);
                            }
                        }
                        , {fixed: 'right', title: '操作', align: 'center', toolbar: tool}
                    ]]
                });
            }

            //监听工具条

            table.on('tool(weekTable)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                var layEvent = obj.event;
                //获得当前行数据
                var data = obj.data;
                if (layEvent == 'getall') {
                    watchAllUser(data.id, 0, 1);
                } else if (layEvent == 'del') {
                    layer.confirm('确认要删除吗？', {
                        btn: ['确定', '取消']//按钮
                    }, function (index) {
                        //此处请求后台程序，下方是成功后的前台处理……

                        $.ajax({
                            url: '${ctp}/student/star/student/deleteWeekStarStu',
                            type: "POST",
                            data: {"id": data.id},
                            success: function (data) {
                                if ("success" === data) {
                                    $.success('操作成功');
                                    getList(position, tool);
                                } else {
                                    $.error("操作失败");
                                }
                            },
                            error: function (res) {
                                $.error("操作失败");
                            }
                        });
                        layer.close(index);
                    });
                } else if (layEvent == 'down') {
                    window.open(data.href);
                }


            });

            table.on('tool(weekTable2)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                watchAllUser(0, data.ids, 2);
            });
            table.on('tool(weekTable3)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                watchAllUser(0, data.ids, 2);
            });
            // 查询功能
            $("#search").bind("click", function () {
                getList(position, tool);
            });
            $("#search2").bind("click", function () {
                getList2(position, tool);
            });
            $("#search3").bind("click", function () {
                getList3(position, tool);
            });

            function getList2(position, tool) {
                table.render({
                    elem: position
                    , url: '/student/star/student/json?dm=${param.dm}'
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
                        year: $("#xn2").val()
                        , term: $("#xq2").val()
                        , gradeId: $("#nj2").val()
                        , teamId: $("#bj2").val()
                        , month: $("#month").val()
                        , type: 2
                    }
                    , request: {
                        pageName: 'currentPage',
                        limitName: 'pageSize'
                        //页码和显示数量
                    }
                    , page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                        layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                        , groups: 3 //只显示 1 个连续页码
                    }
                    , cols: [[
                        // 显示参数需要修改
                        {field: 'yearName', title: '学年', align: 'center'}
                        , {field: 'term', title: '学期', align: 'center'}
                        , {field: 'month', title: '月份', align: 'center'}
                        , {field: 'teamName', title: '班级', align: 'center'}
                        , {
                            field: 'studentName', title: '月明星学生', align: 'center'
                            , templet: function (d) {
                                if (d.gradeNumber == 1) {
                                    return "(探询少年) " + d.studentName;
                                } else if (d.gradeNumber == 2) {
                                    return "(探察少年) " + d.studentName;
                                } else if (d.gradeNumber == 3) {
                                    return "(探听少年) " + d.studentName;
                                } else if (d.gradeNumber == 4) {
                                    return "(探究少年) " + d.studentName;
                                } else if (d.gradeNumber == 5) {
                                    return "(探奇少年) " + d.studentName;
                                } else if (d.gradeNumber == 6) {
                                    return "(探真少年) " + d.studentName;
                                }

                                return "暂无数据！";
                            }
                        }
                        , {
                            field: 'createDate', title: '创建时间', sort: true, align: 'center', templet: function (d) {
                                return util.toDateString(d.createDate);
                            }, edit: true
                        }
                        , {fixed: 'right', title: '排名', width: 178, align: 'center', toolbar: tool}
                    ]]
                });
            }

            function getList3(position, tool) {
                table.render({
                    elem: position
                    , url: '/student/star/student/json?dm=${param.dm}'
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
                        year: $("#xn3").val()
                        , term: $("#xq3").val()
                        , gradeId: $("#nj3").val()
                        , teamId: $("#bj3").val()
                        , type: 3
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
                        {field: 'yearName', title: '学年', align: 'center'}
                        , {field: 'term', title: '学期', align: 'center'}
                        , {field: 'teamName', title: '班级', align: 'center'}
                        , {
                            field: 'studentName', title: '学期明星学生', align: 'center'
                            , templet: function (d) {
                                if (d.gradeNumber == 1) {
                                    return "(探询少年) " + d.studentName;
                                } else if (d.gradeNumber == 2) {
                                    return "(探察少年) " + d.studentName;
                                } else if (d.gradeNumber == 3) {
                                    return "(探听少年) " + d.studentName;
                                } else if (d.gradeNumber == 4) {
                                    return "(探究少年) " + d.studentName;
                                } else if (d.gradeNumber == 5) {
                                    return "(探奇少年) " + d.studentName;
                                } else if (d.gradeNumber == 6) {
                                    return "(探真少年) " + d.studentName;
                                }
                                return "暂无数据！";
                            }
                        }
                        , {
                            field: 'createDate', title: '创建时间', sort: true, align: 'center', templet: function (d) {
                                return util.toDateString(d.createDate);
                            }, edit: true
                        }
                        , {fixed: 'right', title: '排名', width: 178, align: 'center', toolbar: tool}
                    ]]
                });
            }

            function watchAllUser(id, ids, type) {
                layui.use('table', function () {
                    var layer = layui.layer;
                    var table = layui.table;
                    layer.open({
                        type: 1,
                        area: ["600px", '430px'],
                        title: '周明星学生',
                        content: '<div style="margin: 10px;"><table id="templateTable"></table></div>',
                        success: function () {
                            table.render({
                                elem: '#templateTable'
                                // ,height: 312
                                , url: '/student/star/student/stuScoreList?dm=${param.dm}'
                                , parseData: function (res) { //res 即为原始返回的数据
                                    return {
                                        "code": 0, //解析接口状态
                                        "msg": "success", //解析提示文本
                                        "data": res.list //解析数据列表
                                    };
                                }
                                , where: {id: id, ids: ids, type: type}
                                , page: false //开启分页
                                , cols: [[
                                    // 显示参数需要修改
                                    {type: 'numbers', title: '序号', align: 'center'}
                                    , {field: 'name', title: '姓名', align: 'center'}
                                    , {field: 'sumScore', title: '分数', align: 'center'}
                                ]]
                            });
                        },
                        btn: ['关闭'],
                        yes: function (index) {
                            layer.close(index);
                        }
                    });
                });
            }

        });

        $("#xn").on("change", function () {
            getTermData();

        })
        $("#xn2").on("change", function () {
            getTermData();

        })
        $("#xn3").on("change", function () {
            getTermData();

        })

    });

    // 清空下拉框
    function getTermData() {
        $("#xq").empty();
        $("#xq").append("<option value=''> 请选择</option>");

        $("#xq2").empty();
        $("#xq2").append("<option value=''> 请选择</option>");

        $("#xq3").empty();
        $("#xq3").append("<option value=''> 请选择</option>");

        if ($("#xn").val() == "") {
            $("#nj").empty();
            $("#nj").append("<option value=''> 请选择</option>");
            $("#bj").empty();
            $("#bj").append("<option value=''> 请选择</option>");
        }
        if ($("#xn2").val() == "") {
            $("#nj2").empty();
            $("#nj2").append("<option value=''> 请选择</option>");
            $("#bj2").empty();
            $("#bj2").append("<option value=''> 请选择</option>");
        }
        if ($("#xn3").val() == "") {
            $("#nj3").empty();
            $("#nj3").append("<option value=''> 请选择</option>");
            $("#bj3").empty();
            $("#bj3").append("<option value=''> 请选择</option>");
        }
    }


</script>

</html>