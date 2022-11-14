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
    <title>修改考试科目设置</title>
</head>
<body style="background-color:#fff">
<div class="ks_list">
    <table class="table table-sz" style="border:1px solid #e4e8eb;">
        <thead><tr><th>科目</th><th>满分</th><th>优秀</th><th>良好</th><th>及格</th><th>参与统计</th></tr></thead>
        <tbody>
        <c:forEach items="${list}" var="item">
            <tr class="kmsl" data-code="${item.subjectCode}">
                <td>${item.subjectName}</td>
                <td><input type="text" class="add_score" value="${item.fullScore}"> 分</td>
                <td>≥ <input type="text" class="add_score" value="${item.highScore}"> 分</td>
                <td>≥ <input type="text" class="add_score" value="${item.lowScore}"> 分</td>
                <td><input type="text" class="add_score" value="${item.passScore}"> 分</td>
                <td><a href="javascript:void(0)" <c:if test="${item.isStat}">class="on"</c:if>><input type="checkbox"></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="qd-btn">
        <button class="btn btn-lightGray">应用</button>
        <button class="btn btn-green" onclick="save();" style="display: none">应用</button>
    </div>
</div>
<div class="qxts" style="display:none;"><p>您已取消勾选科目“<span class="f_red"></span>”参与统计，一旦确定应用修改将不参与本次考试统计。<br>请谨慎操作</p></div>
</body>
<script>
    $(function(){
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
        $("tbody input").bind('input propertychange',function(){
            $(".btn-lightGray").hide();
            $(".btn-green").show();
        });
        $("a").click(function () {
            $(".btn-lightGray").hide();
            $(".btn-green").show();
        });

        //点击弹出层  -- 点击弹出"取消"提示
//        $(".qd-btn .btn").click(function(){
//            layer.open({
//                type: 1,
//                shade: false,
//                area: ['380px', '230px'],
//                title: '提示', //不显示标题
//                content: $('.qxts'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
//                cancel: function(){
//                    layer.close();
//                },
//                btn: ['确定应用','取消'],//按钮
//                btn1: function(index, layero){
//
//                }
//            });
//        })

    })

    function save(){
        //用于限制参与统计的科目
        var isChoose = false;
        $(".kmsl a").each(function () {
            if ($(this).hasClass("on")) {
                isChoose = true;
            }
        });
        if (!isChoose) {
            $.error("必须选择参与统计的科目");
            return;
        }

        var loader = new loadLayer("", 60000);
        var examWorksId = "${examWorksId}";
        var gradeId = "${gradeId}";
        var gradeCode = "${gradeCode}";
        var subjectList = "";
        var statCount = 0;  //需统计的科目总数

        var cancel = "";    //取消统计的科目
        $.each(${json}, function (index, item) {
            var $tr = $(".kmsl:eq(" + index + ")");
            var subjectCode = $tr.data("code");
            var scores = $tr.find(".add_score");
            var fullScore = scores[0].value;
            var highScore = scores[1].value;
            var lowScore = scores[2].value;
            var passScore = scores[3].value;
            var statNeeded = $tr.find("a").hasClass("on");
            //数据有变动才存入
            if(item.subjectCode != subjectCode || item.isStat != statNeeded || item.fullScore != fullScore
                || item.highScore != highScore || item.lowScore != lowScore || item.passScore != passScore) {
                var subject = "{subjectCode:'" + subjectCode + "',fullScore:'" + fullScore + "',highScore:'" + highScore +
                    "',lowScore:'" + lowScore + "',passScore:'" + passScore + "',statNeeded:'" + statNeeded + "'}";
                if (subjectList != ""){
                    subjectList += "," + subject;
                } else {
                    subjectList += "[" + subject;
                }
            }
            if (statNeeded) {
                statCount++;
            }

            if (item.isStat && !statNeeded) {
                cancel += "，" + item.subjectName;
            }
        });
        if (subjectList != ""){
            subjectList += "]";
        }
        cancel = cancel.substr(1, cancel.length);

//        if (cancel != "") {
//            $(".qxts .f_red").html("").html(cancel);
//            layer.open({
//                type: 1,
//                shade: false,
//                area: ['380px', '230px'],
//                title: '修改提示',
//                content: $('.qxts'),
//                cancel: function(){
//                    layer.close();
//                },
//                btn: ['确定应用','取消'],//按钮
//                btn1: function(index, layero){
//
//                }
//            });
//        }
//        $(".kmsl").each(function (index) {
//            var subjectCode = $(this).data("code");
//            var scores = $(this).find(".add_score");
//            var fullScore = scores[0].value;
//            var highScore = scores[1].value;
//            var lowScore = scores[2].value;
//            var passScore = scores[3].value;
//            var statNeeded = $(this).find("a").hasClass("on");
//            var subject = "{subjectCode:'" + subjectCode + "',fullScore:'" + fullScore + "',highScore:'" + highScore +
//                "',lowScore:'" + lowScore + "',passScore:'" + passScore + "',statNeeded:'" + statNeeded + "'}";
//            if (subjectList != ""){
//                subjectList += "," + subject;
//            } else {
//                subjectList += "[" + subject;
//            }
//
//            if (statNeeded) {
//                statCount++;
//            }
//
//        });
//        if (subjectList != ""){
//            subjectList += "]";
//        }

        var val = {
            "examWorksId" : examWorksId,
            "gradeId" : gradeId,
            "gradeCode" : gradeCode,
            "subjectList" : subjectList,
            "statCount" : statCount
        };

        loader.show();
        var url = "${pageContext.request.contextPath}/teach/examWorks/modifyExamWorksSubject";
        $.post(url, val, function(data, status) {
            if ("success" === status) {
                data = eval("(" + data + ")");
                if("success" === data.info) {
                    $.success("保存成功");
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