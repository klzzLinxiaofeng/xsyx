<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/css/layui.css" media="all">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/layui.all.js"></script>

    <title>接送管理</title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param name="title" value="接送管理"/>
        <jsp:param name="icon" value="icon-glass"/>
        <jsp:param name="menuId" value="${param.dm}"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        接送列表
                        <p class="btn_link" style="float: right;">
                            <a href="javascript:void(0)" onclick="$.refreshWin();"
                               class="a3"><i class="fa  fa-undo"></i>刷新列表</a>

                        </p>
                    </h3>
                </div>
                <div class="light_grey"></div>
                <div class="content-widgets">
                    <div class="widget-container">

                        <div class="select_b">
                            <div class="select_div"><span>学年：</span><select id="xn" name="xn" class="chzn-select"
                                                                            style="width:120px;"
                                                                            disabled="disabled"></select></div>
                            <div class="select_div"><span>年级：</span>
                                <select id="nj" name="nj" class="chzn-select"
                                        style="width:120px;"></select>
                            </div>
                            <div class="select_div"><span>班级：</span><select id="bj" name="bj" class="chzn-select"
                                                                            style="width:120px;"></select></div>

                            <div class="select_div mobile" id="stuNames">
                                <span>学生姓名：</span><input type="text" id="stuName" name="stuName"
                                                         data-id-container="mobile"
                                                         style="margin-bottom: 0; padding: 6px; width: 120px; margin-right: 3px;">
                            </div>
                            <button type="button" class="btn btn-primary" id="search">查询</button>

                            <button type="button" class="btn btn-primary" onclick="loadCreatePage()">导入</button>
                            <button style="float: right;" type="button" class="btn btn-red" id="dels">
                                批量删除
                            </button>

                            <div class="clear"></div>
                        </div>

                        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">

                            <div class="layui-tab-content" style="height: 100px;display: contents;">
                                <table class="layui-hide" id="test" lay-filter="test"></table>
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
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/javascript">

    // 所选标签页
    var systemId = 0;

    var position = '#test'
    var tool = '#barDemo'

    $(function () {
        // initCheckBtn("jc_subject");
        layui.use(['element', 'table'], function () {
            var $ = layui.jquery, table = layui.table, element = layui.element;

            $.initCascadeSelector({
                "type": "team", "yearCallback": function ($this) {
                    getList("#test", '#barDemo');
                }
            });

            function getList(position, tool) {
                var stuName = $("#stuName").val();
                table.render({
                    elem: position
                    , url: '/schoolBus/management/json?dm=${param.dm}'
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
                        systemId: systemId
                        , stuName: stuName
                        , schoolYear: $("#xn").val()
                        , gradeId: $("#nj").val()
                        , teamId: $("#bj").val()
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
                        {type: 'checkbox'}
                        , {type: 'numbers', title: '序号', align: 'center'}
                        , {field: 'userName', title: '学生ID', align: 'center'}
                        , {field: 'stuName', title: '学生姓名', sort: true, align: 'center'}
                        , {field: 'teamName', title: '班级', align: 'center'}
                        , {field: 'empCard', title: '卡号', align: 'center'}
                        , {field: 'name', title: '家长姓名', sort: true, align: 'center'}
                        , {field: 'mobile', title: '家长电话', align: 'center'}
                        , {fixed: 'right', title: '操作', width: 178, align: 'center', toolbar: tool}
                    ]]
                });
            }

            // 	执行删除
            function executeDel(id) {
                $.post("${ctp}/schoolBus/management/delete", {ids: id, systemId: systemId}, function (data, status) {
                    if ("success" === status) {
                        if ("success" === data) {
                            $.success("删除成功");
                            getList(position, tool);
                        } else if ("fail" === data) {
                            $.error("删除失败，系统异常");
                        }
                    }
                });
            }

            //监听工具条
            table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                $.confirm("确定执行此次操作？", function () {
                    executeDel(data.id);
                });
            });


            // 批量删除
            $("#dels").bind("click", function () {
                var checkStatus = table.checkStatus('test');
                if (systemId == 1) {
                    checkStatus = table.checkStatus('test2');
                }
                var listId = new Array();
                //获得所有选中行的数据
                var datas = checkStatus.data;
                $.each(datas, function (i, data) {
                    listId.push(data.id);
                });
                var ids = listId.join(",");
                if (listId.length <= 0) {
                    layer.msg("请选择要删除的行", {icon: 2})
                } else {
                    $.confirm("确定执行此次操作？", function () {
                        executeDel(ids);
                    });
                }
            });
            // 查询功能
            $("#search").bind("click", function () {
                getList(position, tool);
            });
        });

    });


    // 	加载创建对话框
    function loadCreatePage() {
        $.initWinOnTopFromLeft('批量添加学生到车队', '${ctp}/teach/teamStudent/indexStu?systemId=0', '1100', '790');
    }

</script>
</html>