<%@ page language="java"
         import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>知识点目录管理</title>
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${ctp}/res/plugin/falgun/css/add.css" rel="stylesheet">
    <link rel="stylesheet" href="../../../../res/layui/css/layui.css">
    <script type="text/javascript" src="../../../../res/layui/layui.js"></script>
</head>
<body>

<div class="container-fluid">
    <div class="row-fluid ">
        <div class="span12">
            <ul class="breadcrumb">
                <li><a href="javascript:void(0);"><i class="fa icon-glass"
                                                     name="dashboard"></i>知识点目录管理</a></li>
            </ul>
        </div>
    </div>
    <div class="row-fluid ">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3 class="page-header" style="height: 34px">
                        知识点目录列表
                        <p style="float: right;" class="btn_link">
                            <a onclick="$.refreshWin();" class="a3" href="javascript:void(0)"><i class="fa  fa-undo"></i>刷新列表</a>
                            <a onclick="loadCreateTopMenuPage()" class="a4" href="javascript:void(0)"><i class="fa fa-plus"></i>创建顶级资源菜单</a>
                        <a href="javascript:history.go(-1);" class="a3"><i class="fa fa-arrow-circle-left"></i>返回</a>
                        </p>
                    </h3>
                </div>
            </div>
        </div>
    </div>
    <div id="demo"></div>
</div>
</body>
<script type="text/javascript">
    //删除菜单
    function del(nodeId){
        $.confirm("确定执行此次操作？", function () {
            ddelete(nodeId);
        });
    }
    function ddelete(id) {
        $.get("${ctp}/knowLedge/updateDeleteMenu?id="+id, function (data, status) {
            if ("success" === status) {
                if ("success" === data) {
                    $.success("删除成功");
                    $("#demo").html("")
                    init()
                } else if ("error" === data) {
                    $.error("删除失败，系统异常", 1);
                }
            }
        });
    }
    function loadCreateTopMenuPage(){
        $.initWinOnTopFromLeft('创建顶级菜单','/knowLedge/createOrUpdateMenu?type=0&knowId=${id}&parentMenu=0','800','650')
    }
    $(function () {
        init();
    })
    var layout = [
        {name: '菜单名称', treeNodes: true, headerClass: 'value_col', colClass: 'value_col', style: 'width: 60%'},
        {name: '操作', headerClass: 'value_col', colClass: 'value_col', style: 'width: 20%', render: function(row) {
               var str= '<a class="layui-btn layui-btn-danger layui-btn-mini" onclick="del('+row.id+')"><i class="layui-icon">&#xe640;</i> 删除</a>'+
                '<a class="layui-btn layui-btn-danger layui-btn-mini" onclick="bianjiMenu('+row.id+','+row.leven+')"><i class="layui-icon">&#xe640;</i> 修改</a>';
               if(row.leven<1){
                   //列渲染
                   str+='<a class="layui-btn layui-btn-danger layui-btn-mini" onclick="parentMenus('+row.id+','+row.leven+')"><i class="layui-icon">&#xe640;</i>添加子菜单</a>';
               }
               return str;
            }},
    ];
    function init(){
        $.get("/knowLedge/findByAllMenu?id="+${id},function (d) {
            var list=JSON.parse(d);
            layui.use(['tree', 'layer', 'form'], function(){
                var layer = layui.layer, $ = layui.jquery;
                var form = layui.form();

                layui.treeGird({
                    elem: '#demo',   //传入元素选择器
                    nodes: list,
                    layout:layout
                });
            });
        })
    }
    function parentMenus(id,leven) {
        var lecen=leven+1;
        $.initWinOnTopFromLeft('创建子菜单','/knowLedge/createOrUpdateMenu?type='+lecen+'&parentMenu=0&knowId=${id}&id='+id,'800','650')
    }
    function bianjiMenu(id,leven) {
        var lecen=leven+1;
        $.initWinOnTopFromLeft('编辑菜单','/knowLedge/createOrUpdateMenu?type='+lecen+'&parentMenu=1&knowId=${id}&id='+id,'800','650')

    }


</script>
</html>