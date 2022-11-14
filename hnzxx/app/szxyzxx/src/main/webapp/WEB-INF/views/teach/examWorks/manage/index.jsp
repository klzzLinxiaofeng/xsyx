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
    <%@ include file="/views/embedded/message_push.jsp" %>
    <title>年级统考</title>
</head>
<body >
<div class="kwgl">
    <div class="return_kw">
        <div class="njtk">
            <a href="javascript:void(0)" class="on">年级统考</a>
            <a href="javascript:void(0)" onclick="toClassIndex();">班级测试</a>
        </div>
        <%--<a href="javascript:void(0)" class="btn btn-green">返回</a>--%>
        <a id="have_publish" href="javascript:void(0)" class="btn" style="display:none;">发布成绩报告</a>
        <a id="can_publish" href="javascript:void(0)" class="btn btn-orange" style="display:none;">发布成绩报告</a>
        <div class="cjbg" id="not_publish" style="display:none;">
            <a href="javascript:void(0)" class="btn">发布成绩报告</a>
            <span style="display:none;">该班级尚有成绩未导入不可发布成绩报告</span>
        </div>
    </div>
    <div class="pd20">
        <div class="kw_select">
            <select id="schoolYear"></select>
            <select id="schoolTerm" onchange="getExam();"></select>
        </div>
        <div class="kwgl_main">
            <div class="ksmc_select">
                <div class="xiazai_div">
                    <span>考试名称：</span>
                    <select style="width:180px" id="examWorks" onchange="search();">
                        <%--<c:forEach items="${list}" var="item">--%>
                            <%--<option value="${item.id}">${item.name}</option>--%>
                        <%--</c:forEach>--%>
                    </select>
                </div>
                <div class="xiazai_div"><span>年级：</span><select id="grade" onchange="getTeam();"></select></div>
                <div class="xiazai_div"><span>班级：</span><select id="team" onchange="search();"></select></div>
                <div class="clear"></div>
            </div>
            <div class="ks_list" style="padding:20px;" id="content">
                <table class="table table-striped" style="border:1px solid #e4e8eb;">
                    <thead><tr><th>考试名称</th><th>考试类型</th><th>科目</th><th>考试时间</th><th>导入状态</th><th>导入人</th><th>发布状态</th><th>操作</th></tr></thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="qrfb" style="display:none;">
    <p id="fbts">确定发布一年级1班“<span class="f_blue">18年第二次期末考试</span>”成绩报告至家长端？</p>
</div>
<div class="yfb" style="display:none;">
    <p class="f_red">您已发布过该班成绩报告，不可重新发布</p>
</div>
<div class="wdr" style="display:none;">
    <p class="f_red">该班级尚有成绩未导入，暂不可发布成绩报告</p>
</div>
</body>
<script>
    $(function(){
        $(".cjbg .btn").hover(function(){
            $(this).next().show();
        },function(){
            $(this).next().hide();
        })
        $(".ks_list table  tr a").click(function(){
            $(this).toggleClass("on");
        });
    });

    $(function(){
//        var defOption = {
//            type : "team",
////            isUseChosen : false,
//            selectOne : true,
//            yearSelectId : "schoolYear",
//            gradeSelectId : "grade",
//            teamSelectId : "team"
//        }
//        $.initCascadeSelector(defOption);
        $("#have_publish").click(function () {
            layer.open({
                type: 1,
                shade: false,
                area: ['380px', '160px'],
                title: '提示', //不显示标题
                content: $('.yfb'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
                cancel: function(){
                    layer.close();
                },
                btn: ['确定'],//按钮
                btn1: function(index, layero){
                    layer.close();
                }
            });
        });

        $("#can_publish").click(function () {
            var examName = $("#examWorks option:selected").text();
            var teamName = $("#team option:selected").text();
            $("#fbts").html('确定发布 ' + teamName + ' “<span class="f_blue">' + examName + '</span>” 成绩报告至家长端？');
            layer.open({
                type: 1,
                shade: false,
                area: ['380px', '230px'],
                title: '确定发布', //不显示标题
                content: $('.qrfb'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
                cancel: function(){
                    layer.close();
                },
                btn: ['确定发布','取消'],//按钮
                btn1: function(index, layero){
                    publish();
                }
            });
        });
        getYear();
        $("#schoolYear").change(function () {
            getTerm();
            getGrade();
        });
    });

    function publish(){
        var examWorksId = $("#examWorks").val();
        var teamId = $("#team").val();
        var ids = "";
        $(".table tbody tr").each(function () {
            ids += "," + $(this).attr("id");

        });
        ids = ids.substr(1, ids.length);
        var val = {
            "examWorksId" : examWorksId,
            "teamId" : teamId,
            "ids" : ids
        };
        var url = "${pageContext.request.contextPath}/teach/examWorks/manage/publish";
        $.post(url, val, function(data, status) {
            if ("success" === status) {
                $.success("发布成功！");
                //发布成功后需调用成绩推送到微信端
                $.teamScoresMessageSend({
                    "className" : $("#team option:selected").text()
                });
                search();
            } else {
                $.error("发布失败！");
            }
        });
    };

    function getYear() {
        var schoolId = "${sessionScope[sca:currentUserKey()].schoolId}";
        var year = "${year}";
        if (year == "" || year == null) {
            year = "${sessionScope[sca:currentUserKey()].schoolYear}";
        }
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
        });
        getTerm();
        getGrade();
    }

    function getGrade(){
        var year = $("#schoolYear").val();
        if (year == "" || year == null) {
            year = "${year}";
        }
        if(year == null || year == ""){
            year = "${sessionScope[sca:currentUserKey()].schoolYear}";
        }
        var gradeId = "${gradeId}";
        $.getGrade({"schoolYear" : year}, function(data) {
            var $grade = $("#grade");
            $grade.html("");
            $.each(data, function(index, value) {
                if (gradeId == value.id) {
                    $grade.append("<option selected='selected' value='" + value.id + "'>" + value.name+ "</option>");
                } else {
                    $grade.append("<option value='" + value.id + "'>" + value.name+ "</option>");
                }
            });
            getTeam();
        });
    }

    function getTeam(){
        var gradeId = $("#grade").val();
        var teamId = "${teamId}";
        $.getTeam({"gradeId" : gradeId}, false, function(data) {
            var $team = $("#team");
            $team.html("");
            $.each(data, function(index, value) {
                if (teamId == value.id) {
                    $team.append("<option selected='selected' value='" + value.id + "'>" + value.name+ "</option>");
                } else {
                    $team.append("<option value='" + value.id + "'>" + value.name+ "</option>");
                }
            });
            search();
        });
    }

    function getTerm() {
        var year = $("#schoolYear").val();
        if (year == "" || year == null) {
            year = "${year}";
        }
        if(year == null || year == ""){
            year = "${sessionScope[sca:currentUserKey()].schoolYear}";
        }
        var termCode = "${termCode}";
        if (termCode == "" || termCode == null) {
            termCode = "${sessionScope[sca:currentUserKey()].schoolTermCode}";
        }
        <%--var termCurrent = "${sessionScope[sca:currentUserKey()].schoolTermCode}";--%>
        $.getSchoolTerm({"schoolYear" : year}, function(data, status) {
            var $term = $("#schoolTerm");
            $term.html("");
            $.each(data, function(index, value) {
                if (termCode == value.code) {
                    $term.append("<option selected='selected' value='" + value.code + "'>" + value.name + "</option>");
                } else {
                    $term.append("<option value='" + value.code + "'>" + value.name + "</option>");
                }
            });
            getExam();
        });
    }

    function getExam(){
        var val = {
            "year" : $("#schoolYear").val(),
            "termCode" : $("#schoolTerm").val()
        };
        var examWorksId = "${examWorksId}";
        $.get("${ctp}/teach/examWorks/list/json", val, function(data, status) {
            if ("success" === status) {
                data = eval("(" + data + ")");
                var $examWorks = $("#examWorks");
                $examWorks.html("");
                $.each(data, function(index, value) {
                    if (examWorksId == value.id){
                        $examWorks.append("<option selected='selected' value='" + value.id + "'>" + value.name+ "</option>");
                    } else {
                        $examWorks.append("<option value='" + value.id + "'>" + value.name+ "</option>");
                    }
                });
                search();
            }
        });
    }

    function search(){
        var loader = new loadLayer();
        var val = {
            "examWorksId" : $("#examWorks").val(),
            "teamId" : $("#team").val()
        }
        loader.show();
        var url = "${pageContext.request.contextPath}/teach/examWorks/manage/list";
        $.get(url, val, function(data, status) {
            if ("success" === status) {
                $("#content").html("").html(data);
                loader.close();
            }
        });
    }

    function toView(examWorksId, teamId, ewtsId){
        window.location.href="${pageContext.request.contextPath}/teach/examWorks/manage/achievement?examWorksId="
            + examWorksId +"&teamId=" + teamId + "&ewtsId=" +ewtsId + "&category=${category}";
    }

    function toClassIndex(){
        window.location.href="${pageContext.request.contextPath}/teach/examWorks/manage/class/index";
    }

    function lead(schoolYear,termCode,examWorksId,subjectCode,gradeId,teamId,examType,beginDate,endDate,
        fullScore,highScore,lowScore,passScore,subjectName
    ) {
        var val = {
            "schoolYear":schoolYear,
            "termCode":termCode,
            "examWorksId":examWorksId,
            "subjectCode":subjectCode,
            "gradeId":gradeId,
            "teamId":teamId,
            "examType":examType,
            "beginDate":new Date(beginDate).Format("yyyy-MM-dd"),
            "endDate":new Date(endDate).Format("yyyy-MM-dd"),
            "fullScore":fullScore,
            "highScore":highScore,
            "lowScore":lowScore,
            "passScore":passScore,
            "subjectName":subjectName,
            "teamName":$("#team option:selected").text(),
            "category":0
        };
        var json = JSON.stringify(val);
        var msg = WPFObj.ShowImportScoreWindow(json);
        if (msg == "success") {
            search();
        }
    }

    //重新导入
    function reLead(schoolYear,termCode,examWorksId,subjectCode,gradeId,teamId,examType,beginDate,endDate,
                    fullScore,highScore,lowScore,passScore,subjectName,isPublish) {
//        if (isPublish == "true") {
//            //重新导入提示
//            $("#fbts").html("该班级的成绩报告已经发布，您确定要重新导入成绩吗？<br>一旦确定重新导入后，原成绩文件将被替换。")
//            layer.open({
//                type: 1,
//                shade: false,
//                area: ['380px', '230px'],
//                title: '重新导入提示',
//                content: $('.qrfb'),
//                cancel: function(){
//                    layer.close();
//                },
//                btn: ['确定导入','再考虑下'],//按钮
//                btn1: function(index, layero){
//                    layer.close();
//                    lead(schoolYear,termCode,examWorksId,subjectCode,gradeId,teamId,examType,beginDate,endDate, fullScore,highScore,lowScore,passScore,subjectName);
//                }
//            });
//        } else {
            lead(schoolYear,termCode,examWorksId,subjectCode,gradeId,teamId,examType,beginDate,endDate, fullScore,highScore,lowScore,passScore,subjectName);
//        }
    }

    function reload(){
        if(parent.core_iframe != null) {
            parent.core_iframe.window.location.reload();
        } else {
            parent.window.location.reload();
        }
    }

</script>
</html>