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
    <title>品格发展评语模板类容设置</title>
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
                    品格发展评语模板
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
                        <div class="select_div"><span>学年：</span>
                            <select id="schoolYear" name="schoolYear" class="chzn-select"
                                    style="width:200px;">
                            </select>
                        </div>
                        <div class="select_div"><span>学期：</span>
                            <select id="schoolTrem" name="schoolTrem" class="chzn-select"
                                    style="width:200px;">
                            </select>
                        </div>
                        <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                        <button type="button" class="btn btn-primary" onclick="loadViewerPage()">新建模板内容</button>
                        <button type="button" class="btn btn-primary" onclick="donwload()">下载评语模板</button>
                        <button type="button" class="btn btn-primary" onclick="upload()">导入评语模板</button>
                        <div class="clear"></div>
                    </div>
                    <table class="responsive table table-striped" id="data-table">
                        <thead>
                        <tr role="row">
                            <th style="text-align:center;">序号</th>
                            <th style="text-align:center;">品格发展指标名称</th>
                            <th style="text-align:center;">模板类型</th>
                            <th style="text-align:center;">等次</th>
                            <th style="text-align:center;">模板内容</th>
                            <th class="caozuo" style="  max-width: 250px;text-align:center">操作</th>
                        </tr>
                        </thead>
                        <tbody id="publicClass_list_content">
                        <jsp:include page="./list.jsp"/>
                        </tbody>
                    </table>
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="publicClass_list_content"/>
                        <jsp:param name="url" value="/pingyumoban/allType?sub=list"/>
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
        initSelect();
    })
    function initSelect() {
        //初始填充学年、学期、年级
        $("#grade").html('<option value="">请选择</option>');
        addOption('/teach/schoolYear/list/json', "schoolYear", "year", "name", function (d) {
            if (d.length > 0) {
                addOptionxq('/teach/schoolTerm/list/json?schoolYear='+d[0].year, "schoolTrem", "code", "name");
                //因查询年级不需学期，所以不需在学期填充后的回调中执行
                addOption('/teach/grade/list/json?schoolYear=' + d[0].year, "grade", "id", "name");
            }
        })
        $("#pingType").html('<option value="">请选择</option>');
                //因查询年级不需学期，所以不需在学期填充后的回调中执行
        addOption('/pingyumoban/findBypingyu', "pingType", "id", "name");
    }

    $("#schoolYear").change(function(){
        $("#schoolTrem").html("");
        $("#grade").html('<option value="">请选择</option>');

        //添加学期
        addOptionxq('/teach/schoolTerm/list/json?schoolYear='+$(this).val(), "schoolTrem", "code", "name")
        //添加年级
        addOption('/scoreFenXi/findByGradeId?schoolYear='+$(this).val(), "grade", "id", "name")
    })

    $("#grade").change(function(){
        $("#subjectId").html('<option value="">请选择</option>');
        //添加科目
        addOption("/literacy/findExamSubject?nj="+$("#grade").val(), "subjectId", "subjectId", "subjectName");
    })
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
    function addOption(url, id, valProperty, namePropety, callback) {
        $.get(url, function (d) {
            d = JSON.parse(d);
            for (var i = 0; i < d.length; i++) {
                var obj = d[i];
                $("#" + id).append("<option value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
            }
            if (callback != null && callback != undefined) {
                callback(d);
            }
        })
    }
    function search() {
       var garde= $("#grade").val();
       var pingid= $("#pingType").val();
        var schoolYear= $("#schoolYear").val();
        var schoolTrem= $("#schoolTrem").val();
        var subjectId= $("#subjectId").val();
        var id = "publicClass_list_content";
        var val={};
        val.schoolYear=schoolYear;
        val.schoolTrem=schoolTrem;
        var url = "/pingyumoban/allType?sub=list";
        if(garde!=null && garde!=""){
            val.gradeId=garde;
        }
        if(pingid!=null && pingid!=""){
            val.pingyuId=pingid;
        }
        if(subjectId!=null && subjectId!=""){
            val.subjectId=subjectId;
        }
        myPagination(id, val, url);
    }
    //添加模板
    function loadViewerPage() {
        initWinOnTopFromLeft('添加模板内容', '/pingyumoban/createOrUpdate', '1200', '650');
    }
    //删除
    function ddelete(id) {
        $.get("${ctp}/pingyumoban/delete?id="+id, function (data, status) {
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
        initWinOnTopFromLeft('编辑模板内容', '/pingyumoban/createOrUpdate?id='+id, '1200', '650');
    }
</script>
<script type="text/javascript">
  function  donwload(){
        initWinOnTopFromLeft('下载评语模板', '/pingyumoban/XiaZaiView', '1200', '650');
    }
  function  upload(){
      initWinOnTopFromLeft('导入评语模板', '/pingyumoban/DaoRuView', '1200', '650');
  }
</script>

</body>
</html>
