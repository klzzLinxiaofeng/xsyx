<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
    <title></title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="考试日程安排" name="title"/>
        <jsp:param value="${param.dm}" name="menuId"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        考试日程安排列表
                        <p class="btn_link" style="float: right;">
                            <a href="javascript:void(0)" class="a3"
                               onclick="window.location.reload();"><i class="fa  fa-undo"></i>刷新列表</a>

                            <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
                                <a id="downLoadExcel" class="a2" href="" onclick="downLoadExcel();" class="a2"><i
                                        class="fa fa-plus"></i>导出考试日程记录</a>
                            </c:if>
                            <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">

                                <a href="javascript:void(0)" class="a4"
                                   onclick="loadCreatePage();"><i class="fa fa-plus"></i>添加考试日程</a>
                            </c:if>
                        </p>
                    </h3>
                </div>
                <div class="light_grey"></div>
                <div class="content-widgets">
                    <div class="widget-container">
                        <div class="select_b">
                            <div class="" id="examTeamSubject_form">

                                <div class="select_div"><span>学年： </span>
                                    <select id="xn" name="schoolYear" class="chzn-select" style="width:200px;"
                                            onchange="onChangeSchoolYear();">
                                        <option value="">请选择</option>
                                    </select>

                                    <!-- <select id="xn" name="schoolYear" onchange="onChangeSchoolYear();" class="chzn-select" style="width:160px;"> -->
                                </div>


                                <div class="select_div"><span>学期： </span>
                                    <select id="xq" name="term" class="chzn-select" style="width:160px;">
                                        <option value="">请选择</option>
                                    </select></div>

                                <div class="select_div"><span>年级： </span><select id="nj" name="gradeId"
                                                                                 class="chzn-select"
                                                                                 style="width:120px;">

                                </select></div>
                                <div class="select_div"><span>班级： </span><select id="bj" name="teamId"
                                                                                 class="chzn-select"
                                                                                 style="width:160px;">
                                    <input type="hidden" id="initSelected" name="initSelected" value="0">
                                </select></div>
                            </div>

                            <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">

                                <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                            </c:if>

                            <div class="clear"></div>
                        </div>
                        <table class="responsive table table-striped" id="data-table">
                            <thead>
                            <tr role="row">
                                <th>序号</th>
                                <th>学年</th>
                                <th>学期</th>
                                <th>考试名称</th>
                                <th>班级名称</th>
                                <th>考试类型</th>
                                <th>考试科目</th>
<%--                                <th>是否在线</th>--%>
                                <th>考试时间</th>
                                <th>考试人数</th>
                                <th class="caozuo" style="max-width: 250px;">操作</th>
                            </tr>
                            </thead>
                            <tbody id="examTeamSubject_list_content">
                            <jsp:include page="./list.jsp"/>
                            </tbody>
                        </table>
                        <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                            <jsp:param name="id" value="examTeamSubject_list_content"/>
                            <jsp:param name="url" value="/teach/examTeamSubject/index?sub=list&dm=${param.dm}"/>
                            <jsp:param name="pageSize" value="${page.pageSize}"/>
                        </jsp:include>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var initTerm = 0;
    var initSelected = 0;
    $(function () {
        $.initCascadeSelector({
            "type": "team",
            "yearSelectId": "xn", //学年select标签ID 默认为xn
            "gradeSelectId": "nj", //年级select标签ID 默认为nj
            "teamSelectId": "bj"  //班级select标签ID 默认为bj

        });

        onChangeSchoolYear();
        initSelected = 0;
    });


    function onChangeSchoolYear() {

        var xn = $("#xn").val();
        var defaultTermForserch;
        if ('${sessionScope[sca:currentUserKey()].schoolYear}' != $("#xn").val() ||
            '${sessionScope[sca:currentUserKey()].schoolTermCode}' != $("#xq").val()
        ) {
            initSelected = Number(initSelected) + 1;
            $("#initSelected").val(initSelected);
        }
        if ((xn == null || xn == "") && initTerm != 0) {
            var $xq = $("#xq");
            $xq.html("");
            $xq.append("<option value=''>请选择 </option>");

        } else {

            if ((xn == null || xn == "") && initTerm == 0) {
                xn = '${sessionScope[sca:currentUserKey()].schoolYear}';
            }
            var defaultTerm = '${sessionScope[sca:currentUserKey()].schoolTermCode}';
            $.getSchoolTerm({"schoolYear": xn}, function (data, status) {
                var $xq = $("#xq");
                if (data.length == 0) {
                    $xq.html("");
                    $xq.append("<option value=''>请选择 </option>");
                } else {
                    $xq.html("");
                    $.each(data, function (index, value) {
                        var code = value.code;
                        if (defaultTerm == code && initTerm == 0) {

                            $xq.append("<option value='" + value.code + "'  selected='selected'>" + value.name + "</option>");
                        } else {
                            $xq.append("<option value='" + value.code + "' '>" + value.name + "</option>");
                        }

                    });
                    if (initTerm == 0) {
                        search();
                    }

                    initTerm = Number(initTerm) + 1;

                }

            });
        }

    }

    function search() {

        var val = {};
        var teamId = $("#bj").val();
        var schoolYear = $("#xn").val();
        var gradeId = $("#nj").val();
        var term = $("#xq").val();

        if (gradeId != null && gradeId != "") {
            val.gradeId = gradeId;
        }

        if (term != null && term != "") {
            val.term = term;
        }
        if (gradeId != null && gradeId != "") {
            val.gradeId = gradeId;
        }
        if (teamId != null && teamId != "") {
            val.teamId = teamId;
        }
        if (schoolYear != null && schoolYear != "") {
            val.schoolYear = schoolYear;
        }


        var id = "examTeamSubject_list_content";
        var url = "/teach/examTeamSubject/index?sub=list&dm=${param.dm}";
        myPagination(id, val, url);
    }

    // 	加载创建对话框
    function loadCreatePage() {
        $.initWinOnTopFromLeft('创建', '${ctp}/teach/examTeamSubject/creator', '800', '450');
    }

    //  加载编辑对话框
    function loadEditPage(id) {
        $.initWinOnTopFromLeft('编辑', '${ctp}/teach/examTeamSubject/editor?id=' + id, '800', '450');
    }

    function loadViewerPage(id) {
        $.initWinOnTopFromLeft('详情', '${ctp}/teach/examTeamSubject/viewer?id=' + id, '800', '450');
    }

    // 	删除对话框
    function deleteObj(obj, id) {
        $.confirm("确定执行此次操作？", function () {
            executeDel(obj, id);
        });
    }


    //导出对话框
    function downLoadExcel() {
        var teamId = $("#bj").val();
        var term = $("#xq").val();
        var schoolYear = $("#xn").val();
        var gradeId = $("#nj").val();
        var param = "term=" + term + "&" + "teamId=" + teamId + "&" + "schoolYear=" + schoolYear + "&" + "gradeId=" + gradeId;
        var url = "${pageContext.request.contextPath}/teach/examTeamSubject/downLoadExcel?";
        url = url + param;
        $("#downLoadExcel").attr("href", url);

    }

    // 	执行删除
    function executeDel(obj, id) {
        $.post("${ctp}/teach/examTeamSubject/" + id, {"_method": "delete"}, function (data, status) {
            if ("success" === status) {
                if ("success" === data) {
                    $.success("删除成功");
                    $("#" + id + "_tr").remove();
                } else if ("fail" === data) {
                    $.error("删除失败，系统异常", 1);
                }
            }
        });
    }

    // 	锁定对话框
    function suoding(id) {
        $.confirm("确定执行此次操作？", function () {
            updateSuoding(id,1);
        });
    }
    // 	锁定对话框
    function jiesuo(id) {
        $.confirm("确定执行此次操作？", function () {
            updateSuoding(id,0);
        });
    }

    function updateSuoding(id,zhuantai) {
        $.get("${ctp}/scoreFenXi/updateSuoDing?examId="+id+"&zhuangtai="+zhuantai,function (data) {
                if ("success" === data) {
                    $.success("操作成功");
                } else{
                    $.error("操作失败");
                }
        });
    }

    //设置分值
    function shezhi(id) {
        $.initWinOnTopFromLeft('题目分值设置', '${ctp}/examquestion/viewMdel?examId='+id, '1150', '950');
    }
    //导入成绩
    function daoru(id) {
        $.initWinOnTopFromLeft('导入成绩', '${ctp}/examquestion/findByDonwNoled?examId='+id, '800', '450');
    }
</script>
</html>