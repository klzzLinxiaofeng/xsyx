<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
    <title></title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="记录查询" name="title" />
        <jsp:param value="${param.dm}" name="menuId" />
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        记录查询列表
                        <p class="btn_link" style="float: right;">
                            <a href="javascript:void(0)" class="a4" onclick="toStatPage();">统计报表</a>
                        </p>
                    </h3>
                </div>
                <div class="light_grey"></div>
                <div class="content-widgets">
                    <div class="widget-container">
                        <div class="select_b">
                            <div class="select_div"><span>学年：</span><select id="xn" name="xn" class="chzn-select" style="width:120px;" ></select> </div>
                            <div class="select_div"><span style="padding-left:30px;">学期： </span> <select id="xq" name="xq" class="chzn-select" style="width:160px;"></select></div>
                            <div class="select_div"><span>年级：</span><select id="nj" name="nj" class="chzn-select" style="width:120px;"></select> </div>
                            <div class="select_div"><span>班级：</span><select id="bj" name="bj" class="chzn-select" style="width:120px;"></select> </div>
                            <div class="select_div"><span>项目：</span><select id="itemId" name="itemId" class="chzn-select" style="width:120px;"></select> </div>
                            <div class="select_div">
                                <span>姓名：</span>
                                <input type="text" id="studentName" name="studentName" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value="">
                            </div>
                            <button type="button" class="btn btn-primary" onclick="search()">搜索</button>
                            <div class="clear"></div>
                        </div>
                        <table class="responsive table table-striped" id="data-table">
                            <thead>
                            <tr role="row">
                                <th>序号</th>
                                <th>学期</th>
                                <th>年级</th>
                                <th>班级</th>
                                <th>姓名</th>
                                <th>项目</th>
                                <th>收入</th>
                                <th class="caozuo" style="max-width: 250px;">操作</th>
                            </tr>
                            </thead>
                            <tbody id="charge_list_content">
                            <jsp:include page="./list1.jsp" />
                            </tbody>
                        </table>
                        <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                            <jsp:param name="id" value="charge_list_content" />
                            <jsp:param name="url" value="/dn/charge/index?sub=list&type=inquire&dm=${param.dm}" />
                            <jsp:param name="pageSize" value="${page.pageSize}" />
                        </jsp:include>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    getItem();
    var n = 0;
    $(function(){
        $.initCascadeSelector({
            "type" : "team",
            "selectOne":true,
            "gradeFirstOptTitle" : "全部",
            "teamFirstOptTitle" : "全部",
            "yearChangeCallback" : function(year) {
                if(year != "") {
                    $.SchoolTermSelector({
                            "selector" : "#xq",
                            "condition" : {"schoolYear" : year},
                            "afterHandler" : function($this) {
                                $this.change();
                                $("#xq_chzn").remove();
                                $this.show().removeClass("chzn-done").chosen();
                            }
                        }
                    );
                } else {
                    $("#xq").val("");
                    $("#xq_chzn").remove();
                    $("#xq").show().removeClass("chzn-done").chosen();
                }
            },"teamCallback" : function() {
                if(n == 0){
                    search();
                    n++;
                }
            }
        });
    });
//    search();

    function getItem(){
        $.post("${pageContext.request.contextPath}/dn/charge/item/getAll", function(data, status){
            if("success" == status){
                data = eval("(" + data + ")");
                $("#itemId").html("");
                $("#itemId").append("<option value=''>全部</option>");
                for(var i=0; i<data.length; i++){
                    $("#itemId").append("<option value='" + data[i].id +"'>" + data[i].name + "</option>");
                }
                var selectObj = $("#itemId");
                selectObj.parent().children().remove('div');
                selectObj.removeClass();
                selectObj.addClass("chzn-select");
                selectObj.chosen();
            }
        });
    }

    function search() {
        var val = {};
        var name = $("#studentName").val();
        if (name != null && name != "") {
            val.studentName = name.trim();
        }
        var schoolYear = $("#xn").val();
        var termCode = $("#xq").val();
        var gradeId = $("#nj").val();
        var teamId = $("#bj").val();
        var itemId = $("#itemId").val();
        val.gradeId = gradeId;
        val.teamId = teamId;
        val.itemId = itemId;
        val.schoolYear = schoolYear;
        val.termCode = termCode;
        var id = "charge_list_content";
        var url = "${ctp}/dn/charge/index?sub=list&type=inquire&dm=${param.dm}";
        myPagination(id, val, url);
    }

    function toStatPage(){
        window.location.href="${pageContext.request.contextPath}/dn/charge/stat/index?dm=${param.dm}";
    }

    // 	删除对话框
    function deleteObj(obj, id) {
        $.confirm("确定执行此次操作？", function() {
            executeDel(obj, id);
        });
    }

    // 	执行删除
    function executeDel(obj, id) {
        $.post("${ctp}/dn/charge/" + id, {"_method" : "delete"}, function(data, status) {
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
</script>
</body>
</html>