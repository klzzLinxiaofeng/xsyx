<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>试卷</title>
    <%@ include file="/views/embedded/common.jsp" %>
</head>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_dxa.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
<link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="/res/js/date/daterangepicker.css"/>
<script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="/res/js/date/moment.js"></script>
<script src="/res/js/date/daterangepicker.js"></script>
<style>
    ul, ol {
        padding: 0;
        margin: 0 0 0 0;
    }
</style>
<body>
<c:choose>
    <c:when test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id,'KE_JIAN_ZI_YUAN_SCHOOL', 0)}">
        <c:set var="tList" scope="request" value="${allList}"/>
    </c:when>
    <c:otherwise>
        <c:set var="tList" scope="request" value="${classList}"/>
    </c:otherwise>
</c:choose>
<div class="content_main">
<%--    <div class="ku_select">--%>
<%--        <div class="xdkm_div">--%>
<%--            <div class="xd_km">--%>
<%--                <div class="xueduan" style="display: none;">--%>
<%--                    <label>学年：</label>--%>
<%--                    <div class="xd">--%>
<%--                        <c:forEach items="${tList}" var="sy" varStatus="i">--%>
<%--                            <div class="xn" style="display: block; float:left">--%>
<%--                                <a data-id="${sy.id }" data-i="${i.index }">${sy.name }</a>--%>
<%--                            </div>--%>
<%--                        </c:forEach>--%>
<%--                        <div class="clear"></div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="xueduan">--%>
<%--                    <label>年级</label>--%>
<%--                    <div class="xd">--%>
<%--                        <c:forEach items="${tList}" var="sy1" varStatus="i">--%>
<%--                            <div class="nj" style="display:none" data-i="${i.index}">--%>
<%--                                <c:forEach items="${sy1.childrens}" var="grade" varStatus="j">--%>
<%--                                    <div style="float:left">--%>
<%--                                        <a data-id="${grade.id}" data-j="${j.index}"--%>
<%--                                           data-i="${i.index}">${grade.name}</a>--%>
<%--                                    </div>--%>
<%--                                </c:forEach>--%>
<%--                            </div>--%>
<%--                        </c:forEach>--%>
<%--                        <div class="clear"></div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="xueduan">--%>
<%--                    <div class="clear"></div>--%>
<%--                    <label>班级</label>--%>
<%--                    <div class="xd">--%>
<%--                        <c:forEach items="${tList}" var="sy2">--%>
<%--                            <div class="nj-team" style="display:none">--%>
<%--                                <c:forEach items="${sy2.childrens }" var="grade">--%>
<%--                                    <div class="team" style="display:none;float:left">--%>
<%--                                        <c:forEach items="${grade.childrens }" var="team">--%>
<%--                                            <div style="float:left">--%>
<%--                                                <a data-id="${team.id}" class="teamObj">${team.name}</a>--%>
<%--                                            </div>--%>
<%--                                        </c:forEach>--%>
<%--                                    </div>--%>
<%--                                </c:forEach>--%>
<%--                            </div>--%>
<%--                        </c:forEach>--%>
<%--                        <div class="clear"></div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="xueduan">--%>
<%--                    <div class="clear"></div>--%>
<%--                    <label>科目</label>--%>
<%--                    <div class="xd" id="subject">--%>
<%--                        <div style="float:left"><a data-id="" class="btn-blue">全部</a></div>--%>
<%--                        <c:forEach items="${sList}" var="s">--%>
<%--                            <div style="float: left;" class=""><a data-id="${s.code}">${s.name}</a></div>--%>
<%--                        </c:forEach>--%>
<%--                    </div>--%>
<%--                    <div class="clear"></div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="search_div">--%>
<%--                <label>搜索：</label>--%>
<%--                <div class="ss">--%>
<%--                    <input type="text" placeholder="试卷标题" id="title"/>--%>
<%--                    <a href="javascript:void(0)" class="btn-blue" onclick="go()">搜索</a>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>

    <div class="xdkm_div">
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
    </div>
    <div class="neirong_zs">
        <div class="nr_right" style="margin-left:0;">
            <div class="dxa_list" style="margin-bottom:20px;">
                <div id="emExamPublish_list_content">
                    <jsp:include page="./teamTaskList.jsp"/>
                </div>
            </div>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
    </div>
</div>
<script type="text/javascript">
    var frist = 0;

//     function go() {
//         var val = {};
//         var name = $("#title").val();
//         if (name != null && name != "") {
//             val.title = name;
//         }
//         val.teamId = $(".team div .btn-blue").attr("data-id");
//         val.subjectCode = $("#subject div .btn-blue").attr("data-id");
//         var id = "emExamPublish_list_content";
//         var url = "/paper/list?";
//         myPagination(id, val, url);
// //         alert("ss");
//     }

    // $('.xn a').on('click', function () {
    //     $('.nj').css("display", "none");
    //     var i = $(this).attr("data-i");
    //     $(".nj").eq(i).css("display", "block");
    //     $(".nj").eq(i).find('div a').eq(0).click();
    // })
    // $('.nj div a').on('click', function () {
    //     $('.team').css("display", "none");
    //     $(".nj-team").css("display", "none");
    //     var i = $(this).attr("data-i");
    //     var j = $(this).attr("data-j");
    //     $(".nj-team").eq(i).css("display", "block");
    //     $(".nj-team").eq(i).find(".team").eq(j).css("display", "block");
    //     $('.teamObj').removeClass("btn-blue");
    //     $(".nj-team").eq(i).find(".team").eq(j).find('a').eq(0).click();
    // })
    // $('.team a').on('click', function () {
    //     var val = {};
    //     val.teamId = $(this).attr("data-id");
    //     val.subjectCode = $("#subject div .btn-blue").attr("data-id");
    //     var id = "emExamPublish_list_content";
    //     var url = "/paper/list?";
    //     myPagination(id, val, url);
    // })


    $(function () {
        $(".xdkm_div .xd_km .xueduan .xd a").click(function () {
            $(this).parent().siblings().children("a").removeClass("btn-blue");
            $(this).addClass("btn-blue");
        })
        $('#subject div a').on('click', function () {
            go();
        })
        $('.xn').eq(-1).find('a').click();

        getSchoolYear()
        dateChangeInit();
        $.getAllGrade();
    })

    // 时间选择器
    $(document).ready(function () {
        $('#demo').daterangepicker();
    });

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

    $.getAllGrade = function () {
        $.get("${ctp}/cr/base/widget/getSchoolGrade", {}, function (data, status) {
            if ("success" === status) {
                var htmlSelect = "<option value=''>请选择</option><option value='0'>全部</option>";
                var result = JSON.parse(data);
                for (var key in result) {
                    htmlSelect += "<option value='" + result[key] + "'>" + key + "</option>"
                }
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
                for (var key in result) {
                    htmlSelect += "<option value='" + result[key] + "'>" + key + "</option>"
                }
                $("#team").append(htmlSelect);
                $("#team").trigger("liszt:updated");
                $.getSubjectByGradeId(gradeId)
            }
        });
    }

    $.getSubjectByGradeId = function (gradeId, teamId) {
        $.get("${ctp}/cr/base/widget/getSubjectByGradeId", {"gradeId": gradeId}, function (data, status) {
            if ("success" === status) {
                var htmlSelect = "<option value=''>全部</option>";
                var result = JSON.parse(data);
                for (var key in result) {
                    htmlSelect += "<option value='" + key + "'>" + result[key] + "</option>"

                }
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

        var id = "emExamPublish_list_content";
        var url = "/paper/list?";
        myPagination(id, param, url);
    }

</script>
</body>
</html>