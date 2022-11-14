<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
    <%@ include file="/views/embedded/common.jsp" %>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet"/>

</head>
<body>
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets white">
            <div class="widget-head">
                <h3>
                    品格养成
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
                       <%-- <div class="select_div"><span>学期：</span>
                            <select id="xq" name="xq" class="chzn-select"
                                    style="width:200px;padding-top: 4px;">
                            </select>
                        </div>--%>
                        <div class="select_div"><span>年级：</span>
                            <select id="nj" name="gradeId" class="chzn-select"style="width:200px;padding-top: 4px;">

                            </select>
                        </div>
                        <div class="select_div"><span>班级：</span>
                            <select id="bj" name="teamId" class="chzn-select" style="width:200px;padding-top: 4px;">
                            </select>
                        </div>
                        <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                        <button type="button" class="btn btn-primary" onclick="pingjia()">评价指标预设</button>
                        <button type="button" class="btn btn-primary" onclick="shanchu()">删除图片</button>
                        <div class="clear"></div>
                    </div>
                    <table class="responsive table table-striped" id="data-table">
                        <thead>
                        <tr id="row" role="row">
                        </tr>
                        </thead>
                        <tbody id="publicClass_list_content">
                        <jsp:include page="./list.jsp"/>
                        </tbody>
                    </table>
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="publicClass_list_content"/>
                        <jsp:param name="url" value="/character/cultivation/list?sub=list"/>
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
    var nj = $("#nj").val();
    var bj = $("#bj").val();
    var xq = $("#xn").val();
    if (xq != null && xq != "") {
        val.xq = xq;
    }
    if (bj != null && bj != "") {
        val.bg = bj;
    }
    if (nj != null && nj != "") {
        val.nj = nj;
    }
    var id = "publicClass_list_content";
    var url = "/character/cultivation/list?sub=list";
    myPagination(id, val, url);
    }
    $(function () {
        biaoge();
        initSelect();
    });
    //删除图片
    function shanchu() {
        $.initWinOnTopFromLeft('删除图片', '/character/cultivation/evaluationIndexasdaa', '1200', '650');
    }
    //贫家记录
    function loadViewerPage(id) {
        $.initWinOnTopFromLeft('评价记录', '/character/cultivation/findBypingjiaAll?sub=sss&studentId='+id, '1200', '650');
    }
    //biaoge显示
    function biaoge() {
        $("#row").append("<th>序号</th> <th>姓名</th>");
        $.get("/character/cultivation/list/indexx", function (d) {
            d = JSON.parse(d);
            for (var i = 0; i < d.length; i++) {
                var obj = d[i];
                $("#row").append("<th>" + obj['name'] + "</th>");
            }
            $("#row").append("  <th class='caozuo' style='max-width: 250px;'>操作</th>");

        })
    }
    // 新增评价指标
    function pingjia() {
        $.initWinOnTopFromLeft('评价指标预设', '/character/cultivation/evaluationIndex?sub=qwer', '1000', '550');
    }

    function initSelect() {

        //初始填充学年、学期、年级
        addOption('/teach/schoolYear/list/json', "xn", "year", "name", function (d) {
            $("#nj").html('<option value="">请选择</option>');
            $("#bj").html('<option value="">请选择</option>');
            if (d.length > 0) {
                //addOption('/teach/schoolTerm/list/json?schoolYear='+d[0].year, "xq", "code", "name");
                //因查询年级不需学期，所以不需在学期填充后的回调中执行
                addOption('/teach/grade/list/json?schoolYear=' + d[0].year, "nj", "id", "name")
            }
        })

        //绑定下拉框改变事件
        $("#xn").change(function(){
            $("#nj").html('<option value="">请选择</option>');
            $("#bj").html('<option value="">请选择</option>');
            //添加年级
            addOption('/teach/grade/list/json?schoolYear='+$(this).val(), "nj", "id", "name")
        })
        $("#nj").change(function(){
            $("#bj").html('<option value="">请选择</option>');
            //添加班级
            if($(this).val()!="") {
                addOption('/teach/team/list/json?enableRole=false&gradeId=' + $(this).val(), "bj", "id", "name")
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

</script>
</body>

</html>
