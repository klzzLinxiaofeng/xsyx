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
    <title>班级测试</title>
</head>
<body >
<div class="kwgl">
    <div class="return_kw">
        <div class="njtk">
            <a href="javascript:void(0)" onclick="toIndex();">年级统考</a>
            <a href="javascript:void(0)" class="on">班级测试</a>
        </div>
        <%--<a href="javascript:void(0)" class="btn btn-green">返回</a>--%>
        <a href="javascript:void(0)" class="btn btn-orange" onclick="publish();">批量发布成绩报告</a>
    </div>
    <div class="pd20">
        <div class="kw_select">
            <select id="schoolYear" onchange="getTerm();"></select>
            <select id="schoolTerm" onchange="search();"></select>
        </div>
        <div class="kwgl_main">
            <div class="ksmc_select">
                <div class="xiazai_div"><span>我的班级：</span>
                    <select id="team" onchange="search();">
                        <c:forEach items="${teamList}" var="item">
                            <option value="${item.teamId}">${item.teamName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="clear"></div>
            </div>
            <div class="ks_list" style="padding:20px;">
                <table class="table table-striped" style="border:1px solid #e4e8eb;">
                    <thead>
                        <tr>
                            <th style="position:relative;top:-6px;"><a href="javascript:void(0)"><input type="checkbox"></a></th>
                            <th style="">考试名称</th>
                            <th>考试类型</th>
                            <th>科目</th>
                            <th>考试时间</th>
                            <th>导入状态</th>
                            <th>导入人</th>
                            <th>发布状态</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody id="module_list_content">
                        <jsp:include page="./list_teacher.jsp" />
                    </tbody>
                </table>
                <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                    <jsp:param name="id" value="module_list_content" />
                    <jsp:param name="url"  value="/teach/examWorks/manage/class/list"/>
                    <jsp:param name="pageSize" value="${page.pageSize}" />
                </jsp:include>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
<div class="qrfb" style="display:none;">
    <p id="fbts">确定发布选中的“<span class="f_blue"></span>”考试成绩报告至家长端？</p>
</div>
</body>
<script>
    $(function(){
        $(".ks_list table thead  tr a").click(function(){
            $(this).toggleClass("on");
            if($(this ).hasClass("on")){
                $(".ks_list table tbody tr a").addClass("on")
                $(".ks_list table tbody tr a.unClick").removeClass("on")
            } else{
                $(".ks_list table tbody tr a").removeClass("on")
            }
        });
        getYear();

    });

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
        });

        var teamId = "${teamId}";
        $.get("${ctp}/teach/examWorks/team/list/json", {"year":year}, function(data, status) {
            if ("success" === status) {
                data = eval("(" + data + ")");
                var $team = $("#team");
                $team.html("");
                $.each(data, function(index, value) {
                    if (teamId == value.teamId){
                        $team.append("<option selected='selected' value='" + value.teamId + "'>" + value.teamName+ "</option>")
                    } else {
                        $team.append("<option value='" + value.teamId + "'>" + value.teamName+ "</option>")
                    }
                });
                search();
            }
        });

    }

    function search(){
        var val = {
            "year" : $("#schoolYear").val(),
            "termCode" : $("#schoolTerm").val(),
            "teamId" : $("#team").val()
        };
        var url = "${pageContext.request.contextPath}/teach/examWorks/manage/class/list";
        var id = "module_list_content";
        myPagination(id, val, url);

    }

    function publish(id) {
        var ids = "";
        var name = "";
        if (id == undefined) {
            $(".ks_list table tbody tr a").each(function () {
               if ($(this).hasClass("on")){
                   ids += "," + $(this).parent().parent().attr("id");
                   name += "，" + $(this).parent().next().text();
               }
            });
            ids = ids.substr(1, ids.length);
            name = name.substr(1, name.length);
        } else {
            ids = id;
            name = $("#"+id).children('td').eq(1).text();
        }
        if (ids == "") {
            $.error("请选择要发布的考试");
            return;
        }
        $("#fbts").html('确定发布选中的 “<span class="f_blue">' + name + '</span>” 考试成绩报告至家长端？');
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
                publishReport(ids);
            }
        });
    }

    function publishReport(ids){
        var val = {
            "teamId" : $("#team").val(),
            "ids" : ids
        };
        var url = "${pageContext.request.contextPath}/teach/examWorks/manage/publish";
        $.post(url, val, function(data, status) {
            if ("success" === status) {
                $.success("发布成功！");
                $.teamScoresMessageSend({
                    "className" : $("#team option:selected").text()
                });
                search();
            } else {
                $.error("发布失败！");
            }
        });
    }

    function toIndex(){
        var isMain = "${isMain}";
        if (isMain == "true") {
            window.location.href="${pageContext.request.contextPath}/teach/examWorks/manage/index";
        } else {
            window.location.href="${pageContext.request.contextPath}/teach/examWorks/manage/teacher/index";
        }
    }

    function toView(examWorksId, teamId, ewtsId){
        window.location.href="${pageContext.request.contextPath}/teach/examWorks/manage/achievement?examWorksId="
            + examWorksId +"&teamId=" + teamId + "&ewtsId=" +ewtsId + "&category=${category}&isMain=${isMain}";
    }

    function lead(schoolYear,termCode,examWorksId,subjectCode,gradeId,teamId,examType,beginDate,endDate,
                  fullScore,highScore,lowScore,passScore,subjectName
    ) {
        var isMain = "${isMain}";
        var category = 3;
        if (isMain == "true") {
            category = 2;
        }
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
            "category":category
        };
        var json = JSON.stringify(val);
        var msg = WPFObj.ShowImportScoreWindow(json);
        if (msg == "success") {
            search();
        }
    }

    function reLead(schoolYear,termCode,examWorksId,subjectCode,gradeId,teamId,examType,beginDate,endDate,
                    fullScore,highScore,lowScore,passScore,subjectName,publisherId) {
//        if (publisherId == "" || publisherId == null){
            lead(schoolYear,termCode,examWorksId,subjectCode,gradeId,teamId,examType,beginDate,endDate, fullScore,highScore,lowScore,passScore,subjectName);
//        } else {
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
//                    lead(schoolYear,termCode,examWorksId,subjectCode,gradeId,teamId,examType,beginDate,endDate, fullScore,highScore,lowScore,passScore,subjectName);
//                }
//            });
//        }
    }
</script>
</html>