<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_dxa.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
<link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="/res/js/date/daterangepicker.css"/>
<script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="/res/js/date/moment.js"></script>
<script src="/res/js/date/daterangepicker.js"></script>

<div class="xd_km">
    <span class="select_div">
        <span>学年：</span>
        <select id="schoolYear"></select>
    </span>
    <span class="select_div">
        <span>学期：</span>
        <select id="termCode"></select>
    </span>
    <span class="select_div">
        <span>年级：</span>
        <select id="grade"></select>
    </span>
    <span class="select_div">
        <span>班级：</span>
        <select id="team"></select>
    </span>
    <span class="select_div">
        <span>科目：</span>
        <select id="subject"></select>
    </span>
    <span class="btn-search" onclick="$.getSelectDate()"></span>
    <br><br>
    <span class="select_div">
        <span>时间：</span>
        <input type="text" id="demo" class="form-control" style="width: 300px;margin-right: 20px"/>
        <input type="text" placeholder="试卷标题" id="title"/>
        <button class="btn-search" onclick="$.getSelectDate()">搜索</button>
    </span>
</div>
<script type="text/javascript">
    $(function () {


        $('.zk').click(function () {
            $(this).hide();
            $(this).parent().prev('#team').height('auto');
            $(this).next('.sq').show();
        });

        $('.sq').click(function () {
            $(this).parent().prev('#team').height('90');
            $(this).hide();
            $(this).prev('.zk').show();
        });

        var gradeWidgetHandler = null;

        $("body").on("click", ".xdkm_div .xd_km .xueduan .xd a", function () {
            $(this).siblings().removeClass("btn-blue");
            $(this).addClass("btn-blue");
        })

        $.gradeWidget = function (type, afterHandler) {
            gradeWidgetHandler = afterHandler;
            $.getAllGrade();
        }

        $.getAllGrade = function () {
            $.get("${ctp}/cr/base/widget/getSchoolGrade", {}, function (data, status) {
                if ("success" === status) {
                    var htmlSelect = "<option value=''>请选择</option><option value='0'>全部</option>";
                    var result = JSON.parse(data);
                    // var grade = "<a href=\"javascript:void(0)\" onclick=\"$.getTeamByGradeId(0)\">全部</a>";
                    for (var key in result) {
                        // grade += "<a href=\"javascript:void(0)\" onclick=\"$.getTeamByGradeId(" + result[key] + ")\">" + key + "</a>";
                        htmlSelect += "<option value='" + result[key] + "'>" + key + "</option>"
                    }
                    // $("#grade").html(grade);
                    // $("#grade").children("a").eq(0).click();

                    $("#grade").append(htmlSelect);
                    $("#grade").trigger("liszt:updated");

                }
            });
        }

        $.getTeamByGradeId = function (gradeId) {
            $('#team').height('auto');
            $.get("${ctp}/cr/base/widget/getTeamByGradeId", {"gradeId": gradeId}, function (data, status) {
                if ("success" === status) {
                    var htmlSelect = "<option value=''>全部</option>";
                    var result = JSON.parse(data);
                    // var team = "<a href=\"javascript:void(0)\" onclick=\"$.getSubjectByGradeId(" + gradeId + ",null)\">全部</a>";
                    for (var key in result) {
                        // team += "<a href=\"javascript:void(0)\" onclick=\"$.getSubjectByGradeId(" + gradeId + "," + result[key] + ")\">" + key + "</a>";
                        htmlSelect += "<option value='" + result[key] + "'>" + key + "</option>"
                    }
                    // $("#team").html(team);
                    $("#team").append(htmlSelect);
                    $("#team").trigger("liszt:updated");
                    $.getSubjectByGradeId(gradeId)

                    // //收縮 展開
                    // var team_content = $('#team').height();
                    // if (team_content > 103) {
                    //     $('#team').height('90');
                    //     $('p.zk_sq').show();
                    //     $('.sq').hide();
                    //     $('.zk').show();
                    // } else {
                    //
                    //     $('p.zk_sq').hide();
                    //     $('.zk,.sq').hide();
                    // }
                    // $("#team").children("a").eq(0).click();
                }
            });
        }

        $.getSubjectByGradeId = function (gradeId, teamId) {
            $.get("${ctp}/cr/base/widget/getSubjectByGradeId", {"gradeId": gradeId}, function (data, status) {
                if ("success" === status) {
                    var htmlSelect = "<option value=''>全部</option>";
                    var result = JSON.parse(data);
                    // var subject = "<a href=\"javascript:void(0)\" onclick=\"$.getSelectDate(" + gradeId + "," + teamId + ",null)\">全部</a>";
                    for (var key in result) {
                        // subject += "<a href=\"javascript:void(0)\" onclick=\"$.getSelectDate(" + gradeId + "," + teamId + "," + key + ")\">" + result[key] + "</a>";
                        htmlSelect += "<option value='" + key + "'>" + result[key] + "</option>"

                    }
                    // $("#subject").html(subject);
                    // $("#subject").children("a").eq(0).click();
                    $("#subject").append(htmlSelect);
                    $("#subject").trigger("liszt:updated");
                }
            });
        }

        $.getSelectDate = function () {
            var gradeId = $("#grade").val();
            var teamId = $("#team").val();
            var subjectCode = $("#subject").val();
            var title = $("#title").val();
            if (gradeId === "" && teamId === "" && subjectCode === "") {
                $.error("请输入筛选条件");
                return
            }
            var param = {"gradeId": gradeId, "teamId": teamId, "subjectCode": subjectCode};
            if (title !== null && title !== "") {
                param["title"] = title;
            }
            gradeWidgetHandler(param);
        }
        getSchoolYear()
        dateChangeInit();
    });

    //触发下拉框事件   根据学年改变获取学期
    function dateChangeInit() {
        $("#schoolYear").change("on", function () {
            var currentValue = $(this).val();
            if (currentValue == "") {
                $("#termCode").html("").append("<option value=''>请选择</option>");
                $("#termCode").trigger("liszt:updated");
            } else {
                $("#termCode").html("");
                getTermBySchoolYear(currentValue);
            }
        });
        $("#grade").change("on", function () {
            var currentValue = $(this).val();
            $("#team").html("");
            $.getTeamByGradeId(currentValue)
        });
        $("#team").change("on", function () {
            var currentValue = $(this).val();
            $("#subject").html("");
            $.getSubjectByGradeId(currentValue)
        });
    }

    //根据学年获取学期
    function getTermBySchoolYear(schoolYear) {
        $("#termCode").html("");
        var htmlSelect = "<option value=''>请选择</option>";
        var url = "${ctp}/generalTeachingAffair/lessonplan/getTermBySchoolYear?schoolYear=" + schoolYear;
        $.post(url, null, function (data) {
            for (var flag in data) {
                htmlSelect += "<option value='" + data[flag].code + "'>" + data[flag].name + "</option>"
            }
            $("#termCode").append(htmlSelect);
            $("#termCode").trigger("liszt:updated");
        }, 'json')
    }

    // 获取学年
    function getSchoolYear() {
        var htmlSelect = "<option value=''>请选择</option>";
        $("#termCode").append(htmlSelect);
        $("#team").append(htmlSelect);
        $("#subject").append(htmlSelect);
        $("#schoolYear").html("");
        var url = "${ctp}/teach/schoolYear/list/json";
        $.get(url, null, function (data) {
            for (var flag in data) {
                htmlSelect += "<option value='" + data[flag].year + "'>" + data[flag].name + "</option>"
            }
            $("#schoolYear").append(htmlSelect);
            $("#schoolYear").trigger("liszt:updated");
        }, 'json')
    }

    // 时间选择器
    $(document).ready(function () {
        $('#demo').daterangepicker();
    });

    function exports(id) {
        var gradeId = $("#grade").val();
        var teamId = $("#team").val();
        var subjectCode = $("#subject").val();
        var title = $("#title").val();
        if (gradeId === "" && teamId === "" && subjectCode === "") {
            $.error("请输入筛选条件");
        }
        var param = "gradeId=" + gradeId + "&teamId=" + teamId + "&subjectCode=" + subjectCode;
        if (title !== null && title !== "") {
            param = param + "&title=" + title;
        }
        window.location.href = "${pageContext.request.contextPath}/learningPlan/export?" + param;
    }
</script>