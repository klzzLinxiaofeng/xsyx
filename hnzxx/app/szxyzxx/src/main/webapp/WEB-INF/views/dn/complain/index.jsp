<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
    <title>我的投诉</title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="客户投诉" name="title" />
        <jsp:param value="${param.dm}" name="menuId" />
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="oa_top">
                    <ul class="top_ul">
                        <li><a href="javascript:void(0)" id="mine_page" onclick="toIndex('mine')" >我的投诉</a></li>
                        <li><a href="javascript:void(0)" id="all_page" onclick="toIndex('all')">全部投诉</a></li>
                    </ul>
                </div>
                <div class="sq_list ">
                    <div class="select_div" style="float: left;margin-right: 10px;">
                        <span style="padding-left:10px;">部门：</span>
                        <select id="dept" name="dept" class="chzn-select" style="width:120px;margin-bottom: 0;"></select>
                    </div>
                    <div class="search_1" style="float: left">
                        <input type="text" placeholder="问题描述关键字/问题类别" id="description">
                        <a class="sea_s" id="mine_search"><i class="fa fa-search"></i></a>
                    </div>
                    <div class="clear"></div>
                    <div class="entry">
                        <ul>
                            <li id="num"><a href="javascript:void(0)"></a></li>
                        </ul>
                        <button class="btn btn-success" onclick="toCreatePage();">投诉</button>
                    </div>
                    <div class="clsq" id="list_content">
                        <jsp:include page="./list.jsp" />
                    </div>
                    <div style="padding-right:14px;margin-top: 14px">
                        <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                            <jsp:param name="id" value="list_content" />
                            <jsp:param name="url" value="/dn/complain/index?sub=list&dm=${param.dm}&type=${type}" />
                            <jsp:param name="pageSize" value="${page.pageSize}"/>
                        </jsp:include>
                    </div>
                    <div class="clear"></div>
                </div>

            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $("#mine_search").click(function () {
            search();
        });

        addClassOn();
        getDept();
        search();
    });

    function addClassOn(){
        var type = $("#type").val();
        if (type == "all") {
            $("#all_page").addClass("on");
            $("#dept").parent().show();
        } else {
            $("#mine_page").addClass("on");
            $("#dept").parent().hide();
        }
    }

    function getDept() {
        $.get("${pageContext.request.contextPath}/teach/dept/list/json", function(data, status){
            if("success" == status){
                data = eval("(" + data + ")");
                $("#dept").html("");
                $("#dept").append("<option value=''>全部</option>");
                for(var i=0; i<data.length; i++){
                    $("#dept").append("<option value='" + data[i].id +"'>" + data[i].name + "</option>");
                }
            }
        });
    }

    function toIndex(type){
        window.location.href="${ctp}/dn/complain/index?dm=${param.dm}&type="+type;
    }

    function search(){
        var type = $("#type").val();
        var val = {};
        var departId = $("#dept").val();
        var description = $("#description").val();
        if(description != null && description != ""){
            description = description.trim();
        }
        val.description = description;
        val.departId = departId;
        var url = "/dn/complain/index?sub=list&dm=${param.dm}&type="+type;
        var id = "list_content";
        myPagination(id, val, url);
    }

    function toCreatePage() {
        $.initWinOnTopFromLeft('投诉', '${ctp}/dn/complain/creator', '600', '400');
    }
    function loadEditPage(id) {
        $.initWinOnTopFromLeft('编辑', '${ctp}/dn/complain/editor?type=editor&id=' + id , '600', '400');
    }
    function loadEvaluatePage(id) {
        $.initWinOnTopFromLeft('评价', '${ctp}/dn/complain/editor?type=evaluate&id=' + id , '600', '330');
    }
    function deleteObj(obj, id) {
        $.confirm("确定执行此次操作？", function() {
            executeDel(obj, id);
        });
    }

    // 	执行删除
    function executeDel(obj, id) {
        $.post("${ctp}/dn/complain/" + id, {"_method" : "delete"}, function(data, status) {
            if ("success" === status) {
                if ("success" === data) {
                    $.success("删除成功");
                    $("#" + id + "_li").remove();
                } else if ("fail" === data) {
                    $.error("删除失败，系统异常", 1);
                }
            }
        });
    }


</script>
</body>
</html>