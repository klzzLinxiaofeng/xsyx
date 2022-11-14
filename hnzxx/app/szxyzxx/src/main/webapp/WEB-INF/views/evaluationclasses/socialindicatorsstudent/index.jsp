<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
    <title>课件行为记录</title>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        课件行为评价记录
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

                                </select>
                            </div>
                            <div class="select_div"><span>班级：</span>
                                <select id="bj" name="teamId" class="chzn-select" style="width:200px;padding-top: 4px;">
                                </select>
                            </div>
                            <div class="select_div"><span>学生姓名：</span>
                                <input type="text"  name="studentName" class="span4" id="studentName"/>
                            </div>
                            <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                            <button type="button" class="btn btn-primary" onclick="pingjia()">评价指标预设</button>
                            <div class="clear"></div>
                        </div>
                        <table class="responsive table table-striped" id="data-table">
                            <thead>
                            <tr role="row">
                                <th><input type="checkbox" id="checkAlls"></th>
                                <th>序号</th>
                                <th>学生名称</th>
                                <th>班级名称</th>
                                <th>指标名称</th>
                                <th>评价时间</th>
                                <th>点评教师</th>
                                <th>点评分数</th>
                                <th>评语</th>
                            </tr>
                            </thead>
                            <tbody id="time_list_content">
                            <jsp:include page="./list.jsp"/>
                            </tbody>
                        </table>
                        <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                            <jsp:param name="id" value="time_list_content"/>
                            <jsp:param name="url" value="/classesBehavior/findByAll/list?sub=list"/>
                            <jsp:param name="pageSize" value="${page.pageSize}"/>
                        </jsp:include>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function search() {
        var val = {};
        var xn = $("#xn").val();
        var xq = $("#xq").val();
        var nj = $("#nj").val();
        var bj = $("#bj").val();
        var studentName = $("#studentName").val();
        if (xn != null && xn != "") {
            val.schoolYear= xn;
        }
        if (xq != null && xq != "") {
            val.schoolTrem = xq;
        }
        if (bj != null && bj != "") {
            val.teamId = bj;
        }
        if (nj != null && nj != "") {
            val.gradeId = nj;
        }
        if (studentName != null && studentName != "") {
            val.studentName = studentName;
        }
        var id = "time_list_content";
        var url = "/classesBehavior/findByAll?sub=list";
        myPagination(id, val, url);
    }
    function pingjia(){
        $.initWinOnTopFromLeft('评价指标预设', '/classesIndicator/findByAll?sub=index', '1000', '550');
    }



    $(function () {
        initSelect();
    });
    function initSelect() {

        //初始填充学年、学期、年级
        addOption('/teach/schoolYear/list/json', "xn", "year", "name", function (d) {
            $("#nj").html('<option value="">请选择</option>');
            $("#bj").html('<option value="">请选择</option>');
            if (d.length > 0) {
                addOptionxq('/teach/schoolTerm/list/json?schoolYear='+d[0].year, "xq", "code", "name");
                //因查询年级不需学期，所以不需在学期填充后的回调中执行
                addOption('/teach/grade/list/json?schoolYear=' + d[0].year, "nj", "id", "name")
            }
        })

        //绑定下拉框改变事件
        $("#xn").change(function(){
            $("#xq").html('');
            $("#nj").html('<option value="">请选择</option>');
            $("#bj").html('<option value="">请选择</option>');
            //添加年级
            addOptionxq('/teach/schoolTerm/list/json?schoolYear='+$(this).val(), "xq", "code", "name");

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
    function addOptionxq(url, id, valProperty, namePropety, callback) {
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
</html>
