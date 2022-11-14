<%--
  Created by IntelliJ IDEA.
  User: zhangyong
  Date: 2022/1/8
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
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
    <title>作业详情</title>
    <style>
        .content-widgets {
            padding: 10px;
        }
    </style>
</head>
<body>
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets white">
            <div class="widget-head">
                <h3>
                    作业管理
                    <p class="btn_link" style="float: right;">
                        <a href="javascript:void(0)" class="a3"
                           onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
                        <a id="downLoadExcel"  class="a2" href="#" onclick="daochu();" class="a2" >导出学生作业详情</a>
                        <a id="xiazai"  class="a2" href="#" onclick="xiazai();" class="a2" >下载作业模板</a>
                        <a id="daoru"  class="a2" href="#" onclick="daoru();" class="a2" >导入作业模板</a>

                    </p>
                </h3>
            </div>
            <div class="light_grey"></div>
            <div class="content-widgets">
                <div class="widget-container">
                        <div class="select_div"><span>状态：</span>
                            <select id="zhuantai" name="zhuantai" class="chzn-select" style="width:200px;padding-top: 4px;">
                                <option>请选择</option>
                                <option value="0">待提交</option>
                                <option value="1">已提交</option>
                                <option value="2">缺交</option>
                                <option value="3">补交</option>
                            </select>
                        </div>

                        <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                    <div class="clear"></div>
                    </div>
                    <table class="responsive table table-striped" id="data-table" >
                        <thead>
                        <tr role="row" >
                            <th style="text-align:center;">序号</th>
                            <th style="text-align:center;">姓名</th>
                            <th style="text-align:center;">状态</th>
                            <th style="text-align:center;">作业评级</th>
                            <th style="text-align:center;">作业评语</th>
                            <th class="caozuo" style="max-width: 250px;text-align:center;">操作</th>
                        </tr>
                        </thead>
                        <tbody id="publicClass_list_content">
                        <jsp:include page="./xiangqingList.jsp"/>
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
<input type="text" style="display: none" id="jobid" value="${id}"/>

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



    function search() {
        var val = {};
    var zhuantai=$("#zhuantai").val();
    var id=$("#jobid").val();
        var ids = "publicClass_list_content";
    if(zhuantai=="请选择"){
        val.id=id;
    }else{
        val.id=id;
        val.zhuangtai=zhuantai;
    }
        myPagination(ids , val, "/home/woke/xiangqing?sub=list");
    }
    //返回
    function backFun(){
        history.back(-1);
    }

   function pingfen(id){
       var jobid=$("#jobid").val();
       initWinOnTopFromLeft('评价作业', '/home/woke/pingfenView?id='+id+"&jobid="+jobid, '800', '650');
   }
   function bujiao(id) {
       $.get("${ctp}/home/woke/bujiao?id="+id, function (data, status) {
           if ("success" === status) {
               if ("success" === data) {
                   $.success("补交成功");
                   var val = {};
                   var id=$("#jobid").val();
                       val.id=id;
                       var ids = "publicClass_list_content";
                       myPagination(ids , val, "/home/woke/xiangqing?sub=list");
               } else if ("fail" === data) {
                   $.error("删除失败，系统异常", 1);
               }
           }
       });
   }
    // 	补交对话框
    function shanchu(id) {
        $.confirm("确定执行此次操作？", function () {
            bujiao(id);
        });
    }

    //导出
    function daochu() {
        var id=$("#jobid").val();
        var url="/student/home/woke/daochu?id="+id;
        $("#downLoadExcel").attr("href", url);
    }
    //下载
    function xiazai() {
        var id=$("#jobid").val();
        $.get("/home/woke/banduan?id="+id,function (d) {
            if(d==="success"){
                var url="/student/home/woke/xiazai?id="+id;
                window.open(url);
            }else{
                $.error("作业未收")
            }
        })

    }
    function daoru() {
        var id=$("#jobid").val();
        $.get("/home/woke/banduan?id="+id,function (d) {
            if(d==="success"){
                initWinOnTopFromLeft('导入作业详情', '/student/home/woke/daoruView', '800', '650');
            }else{
                $.error("作业未收")
            }
        })

    }
</script>

</body>
</html>
