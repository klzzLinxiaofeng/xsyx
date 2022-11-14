<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dwr/engine.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dwr/util.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dwr/interface/schoolInitDwr.js"></script>
    <link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_newSchool.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
    <title></title>
</head>
<body style="background-color: #e3e3e3;">
<div class="sjcsh_xx_detail" style="background: #ffffff;padding: 0;border: none;margin: 0 20px 20px">
    <p class="zhuyi">注：请确保教师信息和学生信息已经导入，否则信息匹配异常，导入将不成功。</p>
    <div class="begin">
        <a href="javascript:void(0)" class="importTeacher" style="position: relative;">
            <p class="p1 tta"><span class="drjs"></span></p>
            <p class="p2 tta_word" style="margin-bottom: 13px;">导入教师任课</p>
            <input type="file" id="teamTeacher" style="width:180px;height:180px;position:absolute;top:0;left:0;opacity:0" onchange="updateAndImport();">
        </a>
        <a href="javascript:exportExcel();" class="jsdrmb">任课安排导入模板.xls</a>
    </div>
    <c:if test="${!empty value }">
        <p class="lsdrjl">
            <span><fmt:formatDate value="${value.data }" pattern="yyyy-MM-dd HH:mm"/></span>有一份导入记录
            <a href="${pageContext.request.contextPath}/tmp/team/teacher/index">点击查看</a></p>
    </c:if>
</div>
<div class="tjing" style="display:none">
    <p class="word"></p>
    <p class="word1">正在导入，请稍等...</p>
    <div class="jdt_tu"><p style="width:0%"></p><span>0%</span></div>
</div>
<div class="tis_import_teacher" style="display:none;text-align: center;padding-top:16px;">
    <p>创建教师任课记录:</p>
    <%--<div class="drjl" style="height:80px;overflow-y: scroll;margin: 10px 0;">--%>
        <%--<p>一年级8条8个任课教师</p>--%>
        <%--<p>一年级8条8个任课教师</p>--%>
    <%--</div>--%>
    <%--<p>科目错误记录 <span  style="color:#ff5252"> 285 </span> 条</p>--%>
    <%--<p>任课教师错误记录 <span style="color:#ff5252"> 10 </span> 条</p>--%>
    <%--<p>班主任 <span> 25 </span> 条</p>--%>
    <%--<p>错误记录 <span style="color:#ff5252"> 0 </span> 条</p>--%>
    <p>成功数据 <span></span>条</p>
    <p>警告数据 <span style="color: #fda32f;"></span>条</p>
    <p>错误数据 <span style="color:#ff5252"></span>条</p>
</div>
<div class="tis_import_student_parents" style="display:none;text-align: center;padding-top:36px;">
    <p style="color:#ff5252">无法导入教师任课安排.xls文件</p>
    <p style="color:#ff5252">请确定导入文件是否正确</p>
</div>
<script>
    $(function () {
        var iframeHeight=840;
        $("#iframe_sencond",window.parent.document).height(iframeHeight+'px');
        $("#iframe_first",window.parent.parent.document).height((iframeHeight+71)+'px');
    })
    var timer = null;
    schoolInitDwr.onPageLoad("${userId}");

    function exportExcel() {
        location.href = "${pageContext.request.contextPath}/team/teacher/init/export";
    }
    
    function updateAndImport() {
        var file=dwr.util.getValue("teamTeacher");
        openProcess();
        var schoolId = ${schoolId};
        var schoolYear = ${schoolYear};
        var userId = ${userId};
        schoolInitDwr.importTeamTeacherByFile(file, file.value, schoolId, schoolYear, userId, "showMessage", function(result) {
            var data = JSON.parse(result);
            var status = data.status;
            if(status=="EXCEL_HEADER_ERROR" || status=="FILE_SUFFIX_ERROR" || "EXCEL_DATA_NULL"==status) {
                layer.closeAll();
                errorInfo();
            } else if(status=="success") {
                showMessage(100);
                successInfo(data.successCount, data.warnCount, data.errorCount);
            }
        });

    }

    function successInfo(successCount, warnCount, errorCount) {
        $(".tis_import_teacher").find("span").eq(0).text(successCount);
        $(".tis_import_teacher").find("span").eq(1).text(warnCount);
        $(".tis_import_teacher").find("span").eq(2).text(errorCount);
    }

    function errorInfo() {
        layer.open({
            type: 1,
            shade: false,
            area: ['337px', '191px'],
            title: '导入教师任课安排', //不显示标题
            content: $('.tis_import_student_parents'),
            cancel: function(){
                layer.close();
            },
            btn: ['确定'],//按钮
            btn1: function(index, layero){

            }
        });
    }

    function success() {
        layer.open({
            type: 1,
            shade: false,
            area: ['337px', '325px'],
            title: '导入教师任课安排', //不显示标题
            content: $('.tis_import_teacher'),
            cancel: function(){
                layer.close();
            },
            btn: ['确定'],//按钮
            btn1: function(index, layero){
                $(window.parent.document).find("#iframe_sencond").attr("src", "${pageContext.request.contextPath}/tmp/team/teacher/index")
            }
        });
    }

    function openProcess() {
        timer = window.setInterval('getProccess()',100);
        layer.open({
            type: 1,
            shade: false,
            area: ['400px', '272px'],
            title: '导入教师任课安排', //不显示标题
            content: $('.tjing'),
            cancel: function(){
                layer.close();
            },
        });
    }

    function getProccess() {
        schoolInitDwr.getProccess();
    }

    function showMessage(message) {
        var proccess = message + "%";
        console.log(message);
        $(".jdt_tu").children("p").css("width", proccess);
        $(".jdt_tu").children("span").text(proccess);
        $('.jdt_tu span').css('left',proccess);
        if("100"==message) {
            layer.closeAll();
            $(".jdt_tu").children("p").css("width", "0%");
            $(".jdt_tu").children("span").text("0%");
            success();
        }
    }

</script>
</body>
</html>