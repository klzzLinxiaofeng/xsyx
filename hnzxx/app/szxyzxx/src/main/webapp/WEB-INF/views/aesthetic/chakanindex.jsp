<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>艺术审美详情</title>
    <%@ include file="/views/embedded/common.jsp" %>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet">

</head>
<body>
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets white">
            <div class="widget-head">
                <h3>
                    艺术详情
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
                        <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                        <button type="button" class="btn btn-primary" onclick="add('${studentId}')">新增</button>
                        <div class="clear"></div>
                    </div>
                    <table class="responsive table table-striped" id="data-table">
                        <thead>
                        <tr role="row">
                            <th>序号</th>
                            <th>姓名</th>
                            <th>点评</th>
                            <th>评分</th>
                            <th>学习评语</th>
                            <th class="caozuo" style="max-width: 250px;">操作</th>
                        </tr>
                        </thead>
                        <tbody id="publicClass_list_content">
                        <jsp:include page="./chakanlist.jsp"/>
                        </tbody>
                    </table>
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="publicClass_list_content"/>
                        <jsp:param name="url" value="/aesthetic/chakan?sub=list&studentId=${studentId}"/>
                        <jsp:param name="pageSize" value="${page.pageSize}"/>
                    </jsp:include>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>



<script type="text/javascript">
    var studentId=${studentId};
    function search() {
        var val = {};
        var xn = $("#xn").val();
        var xq = $("#xq").val();
        var url = "/aesthetic/chakan?sub=list";
        val.studentId=studentId;
        if (xn != null && xn != "") {
            val.schoolYear=xn;
        }
        if (xq != null && xq != "") {
            val.schoolTrem=xq;
        }
        var id = "publicClass_list_content";
        myPagination(id, val, url);
    }
    $(function () {
        initSelect();
    });
    function initSelect() {
        //初始填充学年、学期、年级
        addOption('/teach/schoolYear/list/json', "xn", "year", "name", function (d) {
            if (d.length > 0) {
                addOptionxq('/teach/schoolTerm/list/json?schoolYear='+d[0].year, "xq", "code", "name");
                }
        })

    }
    function add(id) {
        $.initWinOnTopFromLeft('添加美术作品','/aesthetic/add?studentId='+id,'1000','550')
    }
    //绑定下拉框改变事件
    $("#xn").change(function(){
        $("#xq").html('');
        addOptionxq('/teach/schoolTerm/list/json?schoolYear='+$("#xn").val(), "xq", "code", "name");
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

    function bianji(id){
        $.initWinOnTopFromLeft('编辑', '/aesthetic/inputOrchakan?sub=inputs&id='+id, '1000', '550');
    }
    function chakan(id){
        $.initWinOnTopFromLeft('详情', '/aesthetic/inputOrchakan?sub=chakan&id='+id, '1000', '550');
    }
</script>
</body>

</html>
