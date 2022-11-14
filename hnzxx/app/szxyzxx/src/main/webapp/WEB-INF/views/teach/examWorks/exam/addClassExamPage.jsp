<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/css/extra/kwgl.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
    <title>添加班级测试</title>
    <style>
        .ks_list{
            padding:20px 32px;
        }
        .table{
            border-radius:4px;}
        table thead tr th{
            width:25%;
        }
        table  tr td button{
            border:0 none;
        }
        table  tr td input[type="text"],table  tr td select{width:90%;margin:0;}
    </style>
</head>
<body style="background-color:#fff">
<div class="return_kw" style="margin-bottom: 16px;display: none">
    <p>班级测试</p>
    <a href="javascript:void(0)" class="btn btn-green" onclick="toExam();">返回</a>
</div>
<div class="ks_list">
    <table class="table " style="border:1px solid #e4e8eb;">
        <thead>
            <tr>
                <th>考试名称</th>
                <th>考试班级</th>
                <th>科目</th>
                <th>考试时间</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td><input type="text" id="name" placeholder="请输入考试名称" maxlength="10"></td>
                <td>
                    <select id="team" onchange="getSubject();">
                        <c:forEach items="${teamList}" var="item">
                            <option value="${item.teamId}" data-grade="${item.gradeId}" >${item.teamName}</option>
                        </c:forEach>
                    </select>
                </td>
                <td><select id="subject" onchange="getScores();"></select></td>
                <td><input type="text" id="begin" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'});" placeholder="请选择考试时间"></td>
            </tr>
            <tr>
                <td colspan="4"><button class="btn btn-orange gjsz" >高级设置</button></td>
            </tr>
            <tr style="background-color:#fffdf1;display: none">
                <td colspan="4">
                    <div class="slsz">
                        <h1>三率设定：</h1>
                        <div class="slf">
                            <div>
                                <p><span class="f_green">满分：</span><input type="text" id="fullScore" class="add_score">(含)分</p>
                                <p><span class="f_blue">优秀：</span><input type="text" id="highScore" class="add_score">(含)分</p><br/>
                                <p><span class="f_orange">良好：</span><input type="text" id="lowScore" class="add_score">(含)分</p>
                                <p><span class="f_red">及格：</span><input type="text" id="passScore" class="add_score">(含)分</p>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
<div class="qd-btn"><button class="btn btn-blue" onclick="save();">添加</button></div>
</div>
</body>
<script>
    $(function(){
        $(".gjsz").click(function(){
            $(this).parents("tr").next("tr").toggle();
        })
        $(".ks_list table tbody tr a,.cjbgmczx a").click(function(){
            $(this).toggleClass("on");
        });
        // 	设置分数0-150，一个小数点
        $(".add_score").keyup(function(event){
            var keycode = event.which;
            if (keycode != 37&&keycode != 38&&keycode != 39&&keycode != 40) {
                //匹配负号，数字，小数点
                this.value = this.value.replace(/[^\d.]/g, "");
                //匹配第一个输入的字符不是小数点
                this.value = this.value.replace(/^\./g, "");
                //保证.-只出现一次，而不能出现两次以上
                this.value = this.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
//	                   匹配一位小数点
                this.value = this.value.replace(/^(\-)*(\d+)\.(\d).*$/,'$1$2.$3');
                if(this.value>150){
                    var atr=this.value.substring(0,2);
                    var atr1=this.value.substring(0,3);
                    if(atr>=15){
                        this.value=atr;
                    }else{
                        this.value=atr1;
                    }
                }
            }
        });

        var type = "${type}";
        if (type == "pc") {
            $(".return_kw").show();
        }
    })

    getSubject();
    function getSubject() {
        var val = {
            "year" : "${year}",
            "teamId" : $("#team").val()
        };
        $.get("${ctp}/teach/examWorks/subject/list/json", val, function(data, status) {
            if ("success" === status) {
                data = eval("(" + data + ")");
                var $subject = $("#subject");
                $subject.html("");
                $.each(data, function(index, value) {
                    $subject.append("<option value='" + index + "'>" + value+ "</option>")
                });
                getScores();
            }
        });

    }

    function getScores() {
        var val = {
            "gradeId" : $("#team option:selected").data("grade"),
            "subjectCode" : $("#subject").val()
        }
        $.get("${ctp}/teach/examWorks/subject/score/json", val, function(data, status) {
            if ("success" === status) {
                data = eval("(" + data + ")");
                $("#fullScore").val(data.fullScore);
                $("#highScore").val(data.highScore);
                $("#lowScore").val(data.lowScore);
                $("#passScore").val(data.passScore);
            }
        });
    }


    function save() {
        var loader = new loadLayer();
        var name = $("#name").val();
        var gradeId = $("#team option:selected").data("grade");
        var teamId = $("#team").val();
        var subjectCode = $("#subject").val();
        var begin = $("#begin").val();
        var end = $("#begin").val();
        var fullScore = $("#fullScore").val();
        var highScore = $("#highScore").val();
        var lowScore = $("#lowScore").val();
        var passScore = $("#passScore").val();

        if (teamId =="" || teamId == null) {
            $.error("该教师无相应的班级，无法进行添加操作");
            return;
        }
        if (subjectCode =="" || subjectCode == null) {
            $.error("该教师无相应的科目，无法进行添加操作");
            return;
        }

        if (name == "" || name.trim() == "") {
            $.error("请输入考试名称");
            return;
        }
        if (begin == "") {
            $.error("请选择考试时间段");
            return;
        }

        var termCode = "${termCode}";
        var type = "${type}";
        if (type != null && type != ""){
            var split = termCode.split("?");
            termCode = split[0];
        }

        var val = {
            "type" : "20",
            "year" : "${year}",
            "termCode" : termCode,
            "name" : name,
            "gradeId" : gradeId,
            "teamId" : teamId,
            "subjectCode" : subjectCode,
            "begin" : begin,
            "end" : end,
            "isShow" : true,
            "fullScore" : fullScore,
            "highScore" : highScore,
            "lowScore" : lowScore,
            "passScore" : passScore
        };

        loader.show();
        var url = "${pageContext.request.contextPath}/teach/examWorks/addExamWorks";
        $.post(url, val, function(data, status) {
            if ("success" === status) {
                data = eval("(" + data + ")");
                if("success" === data.info) {
                    $.success("保存成功");
                    if (type == null || type == "") {
                        if(parent.core_iframe != null) {
                            parent.core_iframe.window.location.reload();
                        } else {
                            parent.window.location.reload();
                        }
                    } else {
                        send(data.responseData);
                    }
                    $.closeWindow();
                } else {
                    $.error("保存失败");
                }
            } else {
                $.error("保存失败");
            }
            loader.close();
        });
    }

    function send(id){
        var teamName = $("#team option:selected").text();
        var json = {
            "type" : "addExam",
            "name" : $("#name").val(),
            "examWorksId" :id,
            "begin" : $("#begin").val(),
            "end" : $("#begin").val(),
            "gradeId" : $("#team option:selected").data("grade"),
            "gradeName" : teamName.substring(0, teamName.indexOf('(')),
            "teamId" : $("#team").val(),
            "teamName" : teamName.substring(teamName.indexOf('(')+1, teamName.indexOf(')')) + "班",
            "subjectCode" : $("#subject").val(),
            "subjectName" : $("#subject option:selected").text(),
            "fullScore" : $("#fullScore").val(),
            "highScore" : $("#highScore").val(),
            "lowScore" : $("#lowScore").val(),
            "passScore" : $("#passScore").val()
        }
        json = JSON.stringify(json);
        var msg = WPFObj.ShowImportScoreWindow(json);
    }

    //跳回导入页
    function toExam(){
        var json = { "type": "backExam" };
        json = JSON.stringify(json);
        var msg = WPFObj.ShowImportScoreWindow(json);
    }
</script>
</html>