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
    <title>修改班级测试</title>
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
            <td><input type="text" id="name" value="${examWorks.name}" maxlength="10"></td>
            <td><input type="text" id="team" data-id="${examWorks.teamId}" value="${teamName}" readonly="readonly"></td>
            <td><input type="text" id="subject" data-id="${examWorks.subjectCode}" value="${subjectName}" readonly="readonly"></td>
            <td>
                <input type="text" id="begin" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'});"
                       value='<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${examWorks.examDateBegin}"></fmt:formatDate>'>
            </td>
        </tr>
        <tr>
            <td colspan="4"><button class="btn btn-orange gjsz" >高级设置</button></td>
        </tr>
        <tr style="background-color:#fffdf1;">
            <td colspan="4">
                <div class="slsz">
                    <h1>三率设定：</h1>
                    <div class="slf">
                        <div>
                            <p><span class="f_green">满分：</span><input type="text" id="fullScore" class="add_score" value="${worksSubject.fullScore}">(含)分</p>
                            <p><span class="f_blue">优秀：</span><input type="text" id="highScore" class="add_score" value="${worksSubject.highScore}">(含)分</p><br/>
                            <p><span class="f_orange">良好：</span><input type="text" id="lowScore" class="add_score" value="${worksSubject.lowScore}">(含)分</p>
                            <p><span class="f_red">及格：</span><input type="text" id="passScore" class="add_score" value="${worksSubject.passScore}">(含)分</p>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
    <div class="qd-btn"><button class="btn btn-blue" onclick="save();">修改</button></div>
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


    })

    function save() {
        var loader = new loadLayer();
        var id = "${examWorks.id}";
        var name = $("#name").val();
        var gradeId = "${examWorks.gradeId}";
        var teamId = "${examWorks.teamId}";
        var subjectCode = "${examWorks.subjectCode}";
        var begin = $("#begin").val();
        var end = $("#begin").val();
        var fullScore = $("#fullScore").val();
        var highScore = $("#highScore").val();
        var lowScore = $("#lowScore").val();
        var passScore = $("#passScore").val();

        if (name == "" || name.trim() == "") {
            $.error("请输入考试名称");
            return;
        }
        if (begin == "") {
            $.error("请选择考试时间段");
            return;
        }
        var val = {
            "id" : id,
            "type" : "20",
            "year" : "${year}",
            "termCode" : "${termCode}",
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
        var url = "${pageContext.request.contextPath}/teach/examWorks/modifyExamWorks";
        $.post(url, val, function(data, status) {
            if ("success" === status) {
                data = eval("(" + data + ")");
                if("success" === data.info) {
                    $.success("保存成功");
                    if(parent.core_iframe != null) {
                        parent.core_iframe.window.location.reload();
                    } else {
                        parent.window.location.reload();
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

</script>
</html>