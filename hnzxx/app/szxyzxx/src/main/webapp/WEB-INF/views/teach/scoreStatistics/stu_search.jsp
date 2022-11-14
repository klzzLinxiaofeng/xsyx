<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height:100%;">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/css/extra/kwgl.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
    <title>查询</title>
    <style>
        body{box-sizing:border-box;
            -moz-box-sizing:border-box; /* Firefox */
            -webkit-box-sizing:border-box; /* Safari */
            height:100%;
            padding:20px;
        }
        label.myerror {
            color: #f90000 !important;
            padding-left: 10px;
            text-align: center;
            position: relative;
            top:10px;
        }
    </style>
</head>
<body>
<div class="name_cx">
    <div class="cx_top">
        <div class="name-cx"><a href="javascript:void(0)" class="on">姓名查询</a><a href="javascript:void(0)">选项查询</a></div>
        <a href="javascript:void(0)" class="btn btn-lightGray" onclick="toIndex();">返回</a>
    </div>
    <div class="xx_s1">
        <div class="xs_select" >
            <form class="form-horizontal tan_form" id="form">
                <div class="xsxm"><span>学生姓名：</span><input type="text" id="name" name="name"></div>
                <a href="javascript:void(0)" class="btn btn-green" onclick="find('single');">查询</a>
            </form>
        </div>
        <div class="xx_select" style="display:none">
            <div class="xxcx"><span>学年：</span><select id="schoolYear" onchange="getTeam();"></select></div>
            <div class="njbj">
                <span class="b1">年级/班级：</span>
                <div class="bj_all">
                    <ul>
                        <%--<li>--%>
                            <%--<a href="javascript:void(0)" class="a1">一年级</a>--%>
                            <%--<div class="bj_div" >--%>
                                <%--<ul>--%>
                                    <%--<li><a href="javascript:void(0)">一班</a></li>--%>
                                    <%--<li><a href="javascript:void(0)">二班</a></li>--%>
                                    <%--<li><a href="javascript:void(0)">三班</a></li>--%>
                                    <%--<li><a href="javascript:void(0)">四班</a></li>--%>
                                    <%--<li><a href="javascript:void(0)">五班</a></li>--%>
                                <%--</ul>--%>
                            <%--</div>--%>
                        <%--</li>--%>
                    </ul>
                </div>
                <div class="clear"></div>
            </div>
            <div style="text-align:center"><a href="javascript:void(0)" class="btn btn-green" onclick="find('team');">查询</a></div>
        </div>
    </div>
</div>
</body>
<script>
    var checker;
    $(function(){
        checker = initValidator();
        $(".name_cx .cx_top .name-cx a").click(function(){
            $(".name_cx .cx_top .name-cx a").removeClass("on");
            $(this).addClass("on");
            var i=$(this).index();
            $(".xx_s1>div").hide();
            $(".xx_s1>div").eq(i).show();
        });
        $("body").on("click",".njbj .bj_all>ul>li .a1",function(){
            $(".njbj .bj_all>ul>li .a1").removeClass("on");
            $(".njbj .bj_all .bj_div ul li a").removeClass("hover");
            $(this).addClass("on");
            $(".njbj .bj_all .bj_div").hide();
            $(this).next().show();
        })
        $("body").on("click",".njbj .bj_all .bj_div ul li a",function(){
            $(".njbj .bj_all .bj_div ul li a").removeClass("hover");
            $(this).addClass("hover");
        })
        /*$(".njbj .bj_all>ul>li .a1").hover(function(){
            $(".njbj .bj_all .bj_div").hide();
            $(this).next().show();
        });*/
        getYear();
    });

    function getYear() {
        var schoolId = "${sessionScope[sca:currentUserKey()].schoolId}";
        var year = "${sessionScope[sca:currentUserKey()].schoolYear}";
        $.getSchoolYear({"schoolId" : schoolId}, function (data, status) {
            var $year = $("#schoolYear");
            $year.html("");
            $.each(data, function(index, value) {
                if (year == value.year) {
                    $year.append("<option selected='selected' value='" + value.year + "'>" + value.name + "</option>");
                } else {
                    $year.append("<option value='" + value.year + "'>" + value.name + "</option>");
                }
            });
            getTeam();
        });
    }

    function getTeam(){
        $.get("${ctp}/teach/scoreStatistics/team/list/json", {"year": $("#schoolYear").val()}, function(data, status) {
            if ("success" === status) {
                data = eval("(" + data + ")");
                console.log(data);
                $(".bj_all ul").html("");
                $.each(data, function(index, value) {
                    $(".bj_all>ul").append('<li><a href="javascript:void(0)" class="a1" data-id="' + value.gradeId + '">' + value.gradeName + '</a><div class="bj_div"><ul></ul></div></li>');
                    $.each(value.teamList, function (i, v) {
                        $(".bj_all>ul>li:eq("+index+") ul").append('<li><a href="javascript:void(0)" data-id="' + v.teamId + '">' + v.name + '</a></li>');
                    });
                });
            }
        });
    }

    function initValidator() {
        return $("#form").validate({
            errorClass : "myerror",
            rules : {
                "name" : {
                    required : true
                }
            },
            messages : {
                "name" : {
                    required : "请输入学生姓名"
                }
            }
        });
    }

    function find(type){
        var loader = new loadDialog();
        var name = $("#name").val();
        var teamId = $(".hover").data("id");
        if (type == "team" && (teamId == null || teamId == "null")){
            $.error("请选择班级");
            return;
        }
        if (type == "single" && !checker.form()) {
            return;
        }
        loader.show();
        window.location.href="${pageContext.request.contextPath}/teach/scoreStatistics/student/list?type="
            + type + "&name=" + name + "&teamId=" + teamId;
    }

    function toIndex() {
        window.location.href="${pageContext.request.contextPath}/teach/scoreStatistics/index";
    }

</script>
</html>