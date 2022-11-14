<%--
  Created by IntelliJ IDEA.
  User: zhangyong
  Date: 2021/10/25
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp" %>
<html>
<head>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <title>评语模板类型</title>
    <style>
        #publicClass_list_content {
            font-size: 100px;
        }
    </style>
</head>
<body>
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets white">
            <div class="widget-head">
                <h3>
                    评语模板类型
                    <p class="btn_link" style="float: right;">
                        <a href="javascript:void(0)" class="a3"
                           onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
                    </p>
                </h3>
            </div>
            <div class="light_grey"></div>
            <div class="content-widgets">
                <div class="widget-container">
                    <div class="select_b">
                        <div class="select_div">
                            <span>评语类型名称：</span>
                           <input type="text" id="name" />
                        </div>
                        <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                        <button type="button" class="btn btn-primary" onclick="loadViewerPage()">新建类型</button>
                        <div class="clear"></div>
                    </div>
                    <table class="responsive table table-striped" id="data-table">
                        <thead>
                        <tr role="row">
                            <th style="text-align:center;">序号</th>
                            <th style="text-align:center;">名称</th>
                            <th class="caozuo" style="max-width: 250px;text-align:center">操作</th>
                        </tr>
                        </thead>
                        <tbody id="publicClass_list_content">
                        <jsp:include page="./list.jsp"/>
                        </tbody>
                    </table>
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="publicClass_list_content"/>
                        <jsp:param name="url" value=""/>
                        <jsp:param name="pageSize" value="${page.pageSize}"/>
                    </jsp:include>
                    <div class="clear"></div>
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
                id:'wer',
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

    $(function () {
    });
    function search() {
        var name=$("#name").val();
            var id = "publicClass_list_content";
            var url = "/pingyutype/allType?sub=list";
            var val={};
            if(name!=null && name!=""){
                 val.name=name;
            }
            myPagination(id, val, url);
    }
    //添加类型
    function loadViewerPage() {
        initWinOnTopFromLeft('添加类型', '/pingyutype/createOrUpdate', '1200', '650');
    }
    //删除
    function ddelete(id) {
        $.get("${ctp}/pingyutype/delete?id="+id, function (data, status) {
            if ("success" === status) {
                if ("success" === data) {
                    $.success("删除成功");
                    $("#" + id + "_tr").remove();
                } else if ("fail" === data) {
                    $.error("删除失败，系统异常", 1);
                }
            }
        });
    }
    // 	删除对话框
    function shanchu(id) {
        $.confirm("确定执行此次操作？", function () {
            ddelete(id);
        });
    }

    //编辑
    function bianji(id) {
        initWinOnTopFromLeft('编辑类型', '/pingyutype/createOrUpdate?id='+id, '1200', '650');
    }
</script>

</body>
</html>
