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
    <title></title>
            <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
            <link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
        </head>
<body>
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets white">
            <div class="widget-head">
                <h3>
                    学科素养指标管理
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
                            <select id="xn" name="xn" class="chzn-select"
                                    style="width:200px;">
                            </select>
                        </div>
                        <div class="select_div"><span>学期：</span>
                            <select id="xq" name="xq" class="chzn-select"
                                    style="width:200px;padding-top: 4px;">
                            </select>
                        </div>
                        <div class="select_div"><span>年级：</span>
                            <select id="nj" name="gradeId" class="chzn-select"style="width:200px;padding-top: 4px;">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div class="select_div"><span>科目：</span>
                            <select id="km" name="subjectCode" class="chzn-select" style="width:200px;padding-top: 4px;">
                                <option value="">请选择</option>
                            </select>
                        </div>

                        <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                        <button type="button" class="btn btn-primary" onclick="pingjia()">添加学科素养指标</button>
                        <button type="button" class="btn btn-primary" onclick="donwload()">下载指标导入模板</button>
                        <button type="button" class="btn btn-primary" onclick="upload()">导入学科素养指标 </button>
                        <div class="clear"></div>
                    </div>
                    <table class="responsive table table-striped" id="data-table">
                        <thead>
                        <tr role="row">
                            <th><input class="user_checkbox" type="checkbox" name="ids"></th>
                            <th>序号</th>
                            <th>年级</th>
                            <th>科目</th>
                            <th>学科素养指标</th>
                            <th>最大分值</th>
                            <th class="caozuo" style="max-width: 250px;">操作</th>
                        </tr>
                        </thead>
                        <tbody id="publicClass_list_content">
                        <jsp:include page="./list.jsp"/>
                        </tbody>
                    </table>
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="publicClass_list_content"/>
                        <jsp:param name="url" value="/literacy/list?sub=list"/>
                        <jsp:param name="pageSize" value="${page.pageSize}"/>
                    </jsp:include>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
</div>




<script type="text/javascript">
    function search() {
        var val = {};
        var xn = $("#xn").val();
        var xq = $("#xq").val();
        var nj = $("#nj").val();
        var km = $("#km").val();
        if (xn != null && xn != "") {
            val.xn = xn;
        }
        if (xq != null && xq != "") {
            val.xq = xq;
        }
        if (nj != null && nj != "") {
            val.nj = nj;
        }
        if (km != null && km != "") {
            val.km = km;
        }
        var id = "publicClass_list_content";
        var url = "/literacy/list?sub=list";
        myPagination(id, val, url);
    }
    $(function () {
        initSelect();
    });
    // 新增学科素养指标
    function pingjia() {
        $.initWinOnTopFromLeft('添加学科素养指标', '/literacy/input', '1000', '550');
    }
    //编辑学科素养指标
    function bianji(id) {
        $.initWinOnTopFromLeft('编辑学科素养指标', '/literacy/inputmode?id='+id, '1000', '550');
    }
    //查看学科素养指标
    function chakan(id) {
        $.initWinOnTopFromLeft('查看学科素养指标', '/literacy/chakan?id='+id, '1000', '550');
    }
    // 	执行删除
    function yichu(id) {
        $.post("${ctp}/literacy/delete?id="+id, function (data, status) {
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
    function deleteComment(id) {
        $.confirm("确定执行此次操作？", function () {
            yichu(id);
        });
    }
    function initSelect() {
        //初始填充学年、学期、年级
        addOption('/teach/schoolYear/list/json', "xn", "year", "name", function (d) {
            if (d.length > 0) {
                addOptionxq('/teach/schoolTerm/list/json?schoolYear='+d[0].year, "xq", "code", "name");
                //因查询年级不需学期，所以不需在学期填充后的回调中执行
                addOption('/teach/grade/list/json?schoolYear=' + d[0].year, "nj", "id", "name")
            }
        })
    }
        //绑定下拉框改变事件
        $("#xn").change(function(){
            $("#xq").html('');
            $("#nj").html('<option value="">请选择</option>');
            $("#km").html('<option value="">请选择</option>');
            //添加学期
            addOptionxq('/teach/schoolTerm/list/json?schoolYear='+$(this).val(), "xq", "code", "name")
            //添加年级
            addOption('/teach/grade/list/json?schoolYear='+$(this).val(), "nj", "id", "name")
        })
        $("#xq").change(function(){
            $("#km").html('<option value="">请选择</option>');
            var bjId=$("#nj").val();
            if(bjId!=""){
                //$("#ks").html('<option value="">请选择</option>');
                //添加科目
                addOption("/literacy/findExamSubject?nj="+$("#nj").val(), "km", "subjectId", "subjectName")
            }


        })

        $("#nj").change(function(){
            //$("#bj").html('<option value="">请选择</option>');
            $("#km").html('<option value="">请选择</option>');
            //添加班级
            if($(this).val()!="") {
                //添加科目
                addOption("/literacy/findExamSubject?nj="+$("#nj").val(), "km", "subjectId", "subjectName");
            }
        })

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
</script>
<script type="text/javascript">
    function  donwload(){
        $.initWinOnTopFromLeft('下载指标模板', '/literacy/XiaZaiView', '1200', '650');
    }
    function  upload(){
        $.initWinOnTopFromLeft('导入学科素养指标', '/literacy/DaoRuView', '1200', '650');
    }
</script>

</body>
</html>
