<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_newSchool.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js" ></script>
    <title></title>
</head>
<body style="background-color: #ffffff;">
<div class="tis_edit tta_edit">
    <div>
        <span><i style="font-weight: normal;color:red">*</i>年级班级：</span>
        <select class="span2" disabled="disabled"><option>${map.gradeName}</option></select>
        <select class="span2" disabled="disabled"><option>${map.teamNumber}班</option></select>

        <span><i style="font-weight: normal;color:red">*</i>科目：</span>
        <select class="span2" disabled="disabled"><option>${map.subjectName}</option></select>
    </div>
    <div class="bjrkjs">
        <div>
            <span style=" width: auto;margin-left: 20px;">姓名：</span>
            <input type="text" id="name"/>
            <button class="btn btn-blue" style="margin-top: -11px;" onclick="search();">搜索</button>
        </div>
        <div class="f_right">
            <span style="margin-right: 20px; width: auto;margin-bottom: 5px;">搜索结果（8个结果）</span>
        </div>
        <div class="bjrkjs_table">
            <div id="tmp_list">
                <jsp:include page="./tmp_edit_list.jsp"/>
            </div>
            <div class="page_div">
                <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                    <jsp:param name="id" value="tmp_list" />
                    <jsp:param name="url" value="/team/teacher/init/manage/edit/list" />
                    <jsp:param name="pageSize" value="${page.pageSize}" />
                </jsp:include>
                <div class="clear"></div>
            </div>
        </div>
    </div>
    <div style="text-align: center; margin-top: 20px;"><button class="btn btn-blue gb"  onclick="$.closeWindow()">关闭</button></div>

</div>
<script>
    $(".bjrkjs_table").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});

    search();
    function search() {
        var val = {
            "name" : $("#name").val()
        }
        var url = "/team/teacher/init/manage/edit/list";
        var id = "tmp_list";
        myPagination(id, val, url);
    }

    function save(id, name, mobile) {
        var loader = new loadLayer();
        var subjectCode = "${map.subjectCode}"; //可能为空，须提出
        var type = "${map.type}";
        var val = {
            "gradeId" : ${map.gradeId},
            "teamId" : ${map.teamId},
            "subjectCode" : subjectCode,
            "teacherId" : id,
        }
        var url = "";
        if (type == "1") {
            url = "${pageContext.request.contextPath}/teach/classTeacher/addOrModifyClassTeacher";
        } else if (type == "2") {
            url = "${pageContext.request.contextPath}/teach/classRoomTeacher/addOrModifyClassRoomTeacher";
        }

        var subjectTeacherId = "${map.id}"; //可能为空（新增），须提出
        if (subjectTeacherId == null || subjectTeacherId == "") {

        }
        var value = {
            "subjectTeacherId" : subjectTeacherId,
            "gradeId" : ${map.gradeId},
            "teamId" : ${map.teamId},
            "subjectCode" : subjectCode,
            "type" : type,
            "name" : name,
            "mobile" : mobile
        }
        loader.show();
        $.post(url, val, function(data, status) {
            if ("success" === status) {
                if("success" === data) {
                    $.success("保存成功");

                    if (subjectTeacherId != null && subjectTeacherId != "") {
                        //更新team_teacher后，需更新tmp表，新增时不需要
                        $.post("${pageContext.request.contextPath}/tmp/team/teacher/update", value, function (data1, status1) {
                            if ("success" === status1) {
                                $.success("保存成功");
                                var $a = $("#iframe_first",window.parent.document).contents().find('#iframe_sencond').contents().find('.select_c a.on');
                                $a.click();
                               // $("#iframe_first",window.parent.document).contents().find('#iframe_sencond').window.location.reload();
//                            if(parent.core_iframe != null) {
//                                parent.core_iframe.window.location.reload();
//                            } else {
//                                parent.window.location.reload();
//                            }
                                $.closeWindow();
                            }
                        });
                    } else {
                        $.success("保存成功");
                        $("#iframe_first",window.parent.document).contents().find('#iframe_sencond').contents().find('.select_c a.on').click();
                        $.closeWindow();
                    }

                } else {
                    $.error("保存失败");
                }
            } else {
                $.error("服务器异常");
            }
            loader.close();
        });

    }
</script>
</body>
</html>