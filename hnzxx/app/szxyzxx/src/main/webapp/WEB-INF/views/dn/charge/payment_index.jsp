<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
    <title>缴费</title>
    <style>
        .control-group{width:40%;float:left;}
        .form-horizontal .control-label{width:60px;}
        .control-group .controls{margin-left:80px;}
        .content-widgets{padding:10px 40px;}
        table input{width:90%;}
        .table th, .table td{text-align:center;}
    </style>

</head>
<body>
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets">
            <div class="yc_sq">
                <form class="form-horizontal" action="javascript:void(0);">
                    <div>
                        <div class="control-group">
                            <label class="control-label">年级：</label>
                            <div class="controls">
                                <select class="span11" id="nj" name="nj"></select>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">班级：</label>
                            <div class="controls">
                                <select class="span11" id="bj" name="bj"></select>
                            </div>
                        </div>
                        <button class="btn btn-primary" onclick="search()">搜索</button>
                    </div>
                    <div id="list_content">

                    </div>
                    <div class="caozuo" style="text-align:center;">
                        <button class="btn btn-warning" onclick="save();">确定</button>
                        <button class="btn" onclick="$.closeWindow();">取消</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function search() {
        var val = {};
        var teamId = $("#bj").val();
        if (teamId == null || teamId == ""){
            $.error("请选择班级");
            return;
        }
        val.teamId = teamId;
        var url = "/dn/charge/payment/index?sub=list&dm=${param.dm}";
        $.post(url, val, function (data, status) {
            if(status === "success"){
                $("#list_content").html(data);
            }
        });
    }
    var n = 0;
    $(function(){
        $.initNBSCascadeSelector({
            "type":"team",
            "teamCallback" : function() {
                if(n == 0){
                    search();
                    n++;
                }
            }
        });
//        search();
    });

    function save() {
        var loader = new loadLayer();
        var amounts = new Array();
        $(".amount").children().each(function (index) {
            amounts[index] = new Array();
            $(this).find("input").each(function (j) {
                if($(this).val()!="-"){
                    amounts[index][j] =   $(this).val();
                }else if($(this).val() == "-"){
                    amounts[index][j] = "";
                }
            });
        });
        amounts = JSON.stringify(amounts);
        loader.show();
        var url = "${ctp}/dn/charge/payment/save";
        var val = {};
        val.amounts = amounts;
        val.gradeId = $("#nj").val();
        val.teamId = $("#bj").val();
        val._method = "POST";
        $.post(url, val, function(data, status) {
            if("success" === status) {
                $.success('操作成功');

            }else{
                $.error("操作失败");
            }
            loader.close();
        });
    }

</script>
</body>
</html>