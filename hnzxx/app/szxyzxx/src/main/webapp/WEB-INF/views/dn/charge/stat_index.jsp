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
<style>
    #title {
        margin: 15px 10px;
        text-align: center;
        font-size: 16px;
        font-family: "微软雅黑";
        font-weight: bold;
    }
    .table {
        width: 70%;
        margin: auto;
        font-size: 14px;
    }
</style>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="统计报表" name="title" />
        <jsp:param value="${param.dm}" name="menuId" />
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        统计报表
                        <p class="btn_link" style="float: right;">
                            <a href="javascript:void(0)" class="a4" onclick="history.go(-1);">返回</a>
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
                            <button type="button" class="btn btn-primary" onclick="search()">搜索</button>
                            <div class="clear"></div>
                        </div>
                        <div>
                            <p id="title">统计报表</p>
                        </div>
                        <table class="responsive table table-striped" id="data-table">
                            <thead>
                            <tr role="row">
                                <th>项目</th>
                                <th>收入</th>
                                <th>占比</th>
                            </tr>
                            </thead>
                            <tbody id="charge_list_content">
                            <jsp:include page="./stat_list.jsp" />
                            </tbody>
                        </table>
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
//            },"teamCallback" : function() {
//                if(n == 0){
//                    search();
//                    n++;
//                }
            }
        });
    });

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
        var url = "${ctp}/dn/charge/stat/index?sub=list&type=inquire&dm=${param.dm}";
        myPagination(id, val, url);
        var title = $("#xn option:selected").text() + $("#xq option:selected").text() + $("#bj option:selected").text() + " ";
        var itemName = $("#itemId option:selected").text();
        if(itemName == "全部"){
            title += "全部项目统计报表";
        }else{
            title += itemName + "统计报表";
        }
        $("#title").text(title);
    }


</script>
</body>
</html>