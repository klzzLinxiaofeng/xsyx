<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/szxy/css/szxy_common.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/szxy/css/banji_info_manage.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_newSchool.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dwr/engine.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dwr/util.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dwr/interface/schoolInitDwr.js"></script>
    <title>批量导入家长信息</title>

</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">
    <p class="top_link"><a href="/teach/parent/parentList?dm=JIA_ZHANG_XIN_XI">家长信息列表 ></a><span>批量导入家长信息</span></p>

    <div class="content_main white"  style="border-radius: 0;">
        <div>
            <p class="banji_info_zt banji_info_bg_lan" id="one"><span><i>1</i></span>导入家长信息文件</p>
        </div>
        <div class="sjcsh_xx_detail" style="background: #ffffff;padding: 0;border: none;margin:0 20px 20px;">
            <div class="begin">
                <a href="javascript:void(0)" class="importTeacher" style="position: relative;">
                    <p class="p1 student"><span class="drjs"></span></p>
                    <p class="p2 student_word" style="margin-bottom: 13px;">选择上传文件</p>
                    <input type="file" id="fileUpload" name="fileUpload" style="width:180px;height:180px;position:absolute;top:1px;left:0;opacity:0" onchange="fileOnchange();">
                </a>
                <div class="geiwomoban">
                    <p>没有模板？</p>
                    <div class="select_div">
                        <span>年级：</span>
                        <select class="span2" id="nj" name="gradeId"></select>
                        <span>班级：</span>
                        <select class="span2" id="bj" name="teamId"></select>
                        <button class="btn btn-blue" onclick="downLoadModel()">导出模板</button>
                    </div>
                </div>
            </div>
            <c:if test="${!empty value }">
                <p class="lsdrjl">
                    <span><fmt:formatDate value="${value.data }" pattern="yyyy-MM-dd HH:mm"/></span>有一份导入记录
                    <a href="${pageContext.request.contextPath}/teach/parent/tmp/index">点击查看</a></p>
            </c:if>
        </div>
    </div>
    <div class="tjing" style="display:none">
        <p class="word"></p>
        <p class="word1">正在导入，请稍等...</p>
        <div class="jdt_tu"><p style="width:0%"></p><span>0%</span></div>
    </div>
    <div class="tis_import" style="display:none;text-align: center;padding-top:36px;">
        <p style="color:#ff5252">无法导入家长信息模板.xls文件</p>
        <p style="color:#ff5252">请确定导入文件是否正确</p>
    </div>
    <div class="tis_import_parent" style="display:none;text-align: center;padding-top:16px;">
        <p>创建家长记录:</p>
        <p>成功数据 <span></span>条</p>
        <p>错误数据 <span style="color:#ff5252"></span>条</p>
    </div>
</div>

<script>

    $.initNBSCascadeSelector({
        "type" : "team",
        "isUseChosen" : false,
        "gradeSelectId" : "nj",
        "teamSelectId" : "bj",
        "teamFirstOptTitle" : "全部"
    });

    function downLoadModel() {
        var gradeId = $("#nj").val();
        var teamId = $("#bj").val();
		if (gradeId == null || gradeId == "") {
		    $.error("请选择年级");
		    return;
		}
        var url = "${pageContext.request.contextPath}/teach/parent/downLoadExcel";
        location.href = url + "?gradeId=" + gradeId + "&teamId=" + teamId;
    }

    var timer = null;
    schoolInitDwr.onPageLoad("${sessionScope[sca:currentUserKey()].id}");
    function fileOnchange() {
        var file=dwr.util.getValue("fileUpload");
        openProcess();
        var schoolId = "${sessionScope[sca:currentUserKey()].schoolId}";
        var schoolYear = "${sessionScope[sca:currentUserKey()].schoolYear}";
        var userId = "${sessionScope[sca:currentUserKey()].id}";
        schoolInitDwr.importParentByFile(file, file.value, schoolId, schoolYear, userId, "showMessage", function(result) {
            var data = JSON.parse(result);
            var status = data.status;
            if (status=="EXCEL_HEADER_ERROR" || status=="FILE_SUFFIX_ERROR" || "EXCEL_DATA_NULL"==status) {
                layer.closeAll();
                errorInfo();
            } else if(status=="success") {
                showMessage(100);
                $(".tis_import_parent").find("span").eq(0).text(data.successCount);
                $(".tis_import_parent").find("span").eq(1).text(data.errorCount);
            }
        });
    }

    function errorInfo() {
        layer.open({
            type: 1,
            shade: false,
            area: ['337px', '191px'],
            title: '导入家长信息', //不显示标题
            content: $('.tis_import'),
            cancel: function(){
                layer.close();
            },
            btn: ['确定'],//按钮
            btn1: function(index, layer){

            }
        });
    }

    function success() {
        layer.open({
            type: 1,
            shade: false,
            area: ['337px', '325px'],
            title: '导入家长信息', //不显示标题
            content: $('.tis_import_parent'),
            cancel: function(){
                layer.close();
            },
            btn: ['确定'],//按钮
            btn1: function(index, layer){
                window.location.href = "${pageContext.request.contextPath}/teach/parent/tmp/index";
            }
        });
    }


    function openProcess() {
        timer = window.setInterval('getProccess()',100);
        layer.open({
            type: 1,
            closeBtn: 0,
            shade:  [0.01, '#fff'],
            shadeClose : false,
            area: ['400px', '272px'],
            title: '导入家长信息', //不显示标题
            content: $('.tjing'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
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