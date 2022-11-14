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
    <title>作业管理</title>
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
                    作业管理
                    <p class="btn_link" style="float: right;">
                        <a href="javascript:void(0)" class="a3"
                           onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
                        <a id="downLoadExcel"  class="a2" href="#" onclick="daochu();" class="a2" >导出作业列表</a>
                        <a id="downLoadExcel2"  class="a2" href="#" onclick="daochuGongshi();" class="a2" >作业公示导出</a>
                        <a id="downLoadExcel3"  class="a2" href="#" onclick="daochuSubject();" class="a2" >作业评价导出</a>
                        <a id="seeting"  class="a3" href="#" onclick="settingtime();" class="a2" >作业布置时间设置</a>
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
                                    style="width:200px;">
                            </select>
                        </div>

                        <div class="select_div"><span>年级：</span>
                        <select id="nj" name="gradeId" class="chzn-select"style="width:200px;padding-top: 4px;">
                            <option value="">全年级</option>
                        </select>
                    </div>
                        <div class="select_div"><span>班级：</span>
                            <select id="bj" name="gradeId" class="chzn-select"style="width:200px;padding-top: 4px;">
                                <option value="">全校各班</option>
                            </select>
                        </div>
                        <div class="select_div">
                            <span>科目：</span>
                            <select id="km" name="subjectCode" class="chzn-select" style="width:200px;padding-top: 4px;">
                                <option value="">全科目</option>
                            </select>
                        </div>
                        <div class="select_div">
                            <span>布置时间：</span>
                            <input type="text" id="startDate" name="startDate" class="chzn-select" autocomplete="off"
                                   style="width:200px;padding-top: 4px;"  onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})" placeholder="开始时间"
                                   value="">-
                            <input type="text" id="endDate" name="startDate" class="chzn-select" autocomplete="off"
                                                    style="width:200px;padding-top: 4px;"  onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})" placeholder="结束时间"
                                                    value="">
                        </div>
                        <div class="select_div">
                            <span>是否家庭作业：</span>
                            <select id="isHome" name="isHome" class="chzn-select" style="width:200px;padding-top: 4px;">
                                <option value="">全部</option>
                                <option value="0">否</option>
                                <option value="1">是</option>
                            </select>
                        </div>
                        <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                        <button type="button" class="btn btn-primary" onclick="loadViewerPage()">布置作业</button>
                       <div class="clear"></div>
                    </div>
                    <table class="responsive table table-striped" id="data-table">
                        <thead>
                        <tr role="row">
                            <th style="text-align:center;">序号</th>
                            <th style="text-align:center;">班级</th>
                            <th style="text-align:center;">科目</th>
                            <th style="text-align:center;">作业内容</th>
                            <th style="text-align:center;">作业图片</th>
                            <th style="text-align:center;">布置时间</th>
                            <th class="caozuo" style="max-width: 250px;text-align:center;">操作</th>
                        </tr>
                        </thead>
                        <tbody id="publicClass_list_content">
                        <jsp:include page="./list.jsp"/>
                        </tbody>
                    </table>
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="publicClass_list_content"/>
                        <jsp:param name="url" value="/home/woke/homeWokeAll?sub=list"/>
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
    });
    function search() {
        var val = {};
        var nj = $("#nj").val();
        var bj = $("#bj").val();
        var xn = $("#xn").val();
        var km =$("#km").val();
        var xq=$("#xq").val();
        var text=$("#startDate").val();
        var text2=$("#endDate").val();
        var isHome=$("#isHome").val();
        if (xn != null && xn != "") {
            val.schoolYear = xn;
            if (nj != null && nj != "") {
                val.gradeId = nj;
            }
            if (km != null && km != "") {
                val.subjectId = km;
            }
            if (xq != null && xq != "") {
                val.schoolTrem = xq;
                }
            if(text!=null && text !=""){
                val.text=text;
            }
            if(text2!=null && text2 !=""){
                var data=new Date(text2);
                var year= data.getFullYear();
                var mounth=data.getMonth()+1;
                var datas=new Date(year,mounth, 0).getDate();
                if(data.getDate()<datas){
                    var day=data.getDate()+1;
                    val.text2=year+"-"+mounth+"-"+day;
                }else{
                    val.text2=year+"-"+Number(mounth+1)+"-01";
                }
            }
            if(isHome!=null && isHome!=""){
                       val.isHome=isHome;
                    }
            if (bj != null && bj != "") {
                    val.teamId = bj;
                }
                    var id = "publicClass_list_content";
                    var url = "/home/woke/homeWokeAll?sub=list";
                    myPagination(id, val, url);
        }else{
            $.error("请选择学年");
        }
    }

    function initSelect() {
        //初始填充学年、学期、年级
        addOption('/teach/schoolYear/list/json', "xn", "year", "name", function (d) {
            if (d.length > 0) {
                addOption2('/teach/schoolTerm/list/json?schoolYear='+d[0].year, "xq", "code", "name");
                //因查询年级不需学期，所以不需在学期填充后的回调中执行
                addOption('/teach/grade/list/json?schoolYear=' + d[0].year, "nj", "id", "name")
            }
        })
    }
    //绑定下拉框改变事件
    $("#xn").change(function(){
        $("#nj").html('<option value="">全年级</option>');
        $("#xq").html('');
        $("#bj").html('<option value="">全校各班</option>');
        addOption2('/teach/schoolTerm/list/json?schoolYear='+$(this).val(), "xq", "code", "name");
       //添加年级
        addOption('/teach/grade/list/json?schoolYear='+$(this).val(), "nj", "id", "name")
    })
    $("#nj").change(function(){
        $("#bj").html('<option value="">全校各班</option>');
        $("#km").html('<option value="">全科目</option>');
        //添加班级
        if($(this).val()!="") {
            //添加班级
            addOption('/teach/team/list/json?enableRole=false&gradeId=' + $(this).val(), "bj", "id", "name")
        }
    })
    $("#bj").change(function(){
        $("#km").html('<option value="">全科目</option>');
        //添加kemu
        if($(this).val()!="") {
            //添加科目
            addOption("/literacy/findExamSubject?nj="+$("#nj").val(), "km", "subjectId", "subjectName")
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
    function addOption2(url, id, valProperty, namePropety, callback) {
        var defaultTerm = '${sessionScope[sca:currentUserKey()].schoolTermCode}';
        $.get(url, function (d) {
            d = JSON.parse(d);
            for (var i = 0; i < d.length; i++) {
                var obj = d[i];
                if(defaultTerm==obj[valProperty]){
                    $("#" + id).append("<option selected=selected value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
                }else{
                    $("#" + id).append("<option value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
                }
             }
            if (callback != null && callback != undefined) {
                callback(d);
            }
        })
    }

   /* //添加作业
    function loadViewerPage() {
          var fag=timeCompare();
          alert(fag);
          if(fag) {
              initWinOnTopFromLeft('添加作业', '/home/woke/createOrUpdate', '1200', '650');
          }else{
               $.error("已停止布置作业");
          }
    }*/
    //判断时间是否大于18:30
    function loadViewerPage() {
        $.get("/home/woke/findTime",function (dates) {
            var d = JSON.parse(dates);
            var date = new Date();
            var hour = date.getHours();
            var minute = date.getMinutes();
            var second = date.getSeconds();
            var times=hour+":"+minute+":"+second;
            var deadlineStr =d['datetime'];
            if(Number(time_to_sec(deadlineStr))-Number(time_to_sec(times))>0){
                initWinOnTopFromLeft('添加作业', '/home/woke/createOrUpdate', '1200', '650');
            }else {
                $.error("已停止布置作业");
            }
        })
    }

    //将时分秒转为时间戳
    function time_to_sec(time) {
        if (time !== null) {
            var s = "";
            var hour = time.split(":")[0];
            var min = time.split(":")[1];
            var sec = time.split(":")[2];
            s = Number(hour * 3600) + Number(min * 60) + Number(sec);
            return s;
        }
    }
    //作业公示导出
    function daochuGongshi() {
        initWinOnTopFromLeft('作业公示导出', '/home/wokeGongShi/homeWokeGongshi', '1200', '650');
    }
    function daochuSubject() {
        initWinOnTopFromLeft('作业评价导出', '/home/xueke/homeWokeXueKe', '1200', '650');

    }
    //导出
    function daochu() {
        var nj = $("#nj").val();
        var bj = $("#bj").val();
        var xn = $("#xn").val();
        var xq = $("#xq").val();
        var km =$("#km").val();
        var text=$("#startDate").val();
        var text2=$("#endDate").val();
        var isHome=$("#isHome").val();
        if (xn != null && xn != "") {
            var url = "/home/woke/daochu?schoolYear=" + xn
            if (nj != null && nj != "") {
                url+="&gradeId=" + nj;
            }
                if (bj != null && bj != "") {
                    url+="&teamId="+bj;
                }
                if (km != null && km != "") {
                    url+="&subjectId="+km;
                }
                if(text!=null && text!=""){
                    url+="&text="+text;
                }
            if(text2!=null && text2 !=""){
                var data=new Date(text2);
                var year= data.getFullYear();
                var mounth=data.getMonth()+1;
                var day=data.getDate()+1;
                url+="&text2="+year+"-"+mounth+"-"+day;
            }
                if(xq!=null && xq!=""){
                    url+="&schoolTrem="+xq;
                }
                if(isHome!=null && isHome!=""){
                    url+="&isHome="+isHome;
                }
                $("#downLoadExcel").attr("href", url);

        }else{
            $.error("请选择学年");
        }
    }
    //删除
    function ddelete(id) {
        $.get("${ctp}/home/woke/deleteHomeWoke?id="+id, function (data, status) {
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
        initWinOnTopFromLeft('编辑作业', '/home/woke/createOrUpdate?schoolYear='+$("#xn").val()+"&gradeId="+$("#nj").val()+"&id="+id, '1200', '650');
    }
    //时间设置
    function settingtime() {
        initWinOnTopFromLeft('作业布置时间设置', '/home/woke/findByTimeView', '900', '650');
    }

    //详情
    function chakan(id) {
        //$.get("/home/woke/xiangqing?sub=haha&id="+id);
        window.open("/home/woke/xiangqing?sub=haha&id="+id);
    }

    //收作业
    function shouzuoye(id) {
        //$.get("/home/woke/shouye?sub=haha&id="+id);
        window.open("/home/woke/shouye?sub=haha&id="+id);
    }
</script>

</body>
</html>
