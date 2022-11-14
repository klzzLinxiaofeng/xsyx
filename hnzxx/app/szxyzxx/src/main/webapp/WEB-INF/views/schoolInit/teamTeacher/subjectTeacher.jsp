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
    <title>科任教师管理</title>
    <style type="text/css">
        table th, table td {
            height: 29px;
            line-height: 29px;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3 style="height: 20px;"></h3>
                    <div class="light_grey"></div>
                    <div class="content-widgets">
                        <div class="content-widgets">
                            <input type="hidden" id="schoolId" name="schoolId" value="${schoolId }" />
                            <div class="select_c">
                                <c:forEach items="${sessionScope[sca:currentUserKey()].stageCodes}" var="stage" varStatus="status">
                                    <c:if test="${stage != -1}">
                                        <a data-obj-code="${stage}" href="javascript:void(0)" onclick="ajaxFunction('${stage}')"
                                            <c:if test='${status.index==0}'>class="on"</c:if>>
                                            <jc:cache echoField="name" tableName="jc_stage" paramName="code" value="${stage}"></jc:cache>
                                        </a>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <div class="select_d" style="" id="subject"></div>
                            <div class="widget-container" id="code_Nation" style="position: relative;">
                                <div class="widget-head" style="border-bottom: 0 none;">
                                    <h3 id="krTeacher">
                                        科任教师
                                        <p style="float: right; margin-bottom: 3px;" class="btn_link">
                                            <a href="javascript:void(0)" class="a3" style="position: absolute; right: 130px; top: 3px; border-radius: 0; height: 30px;" onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
                                            <a class="a4" href="javascript:void(0)" onclick="loadCreateSubjectTeacherPage();"><i class="fa fa-plus"></i>新增科任教师</a>
                                        </p>
                                    </h3>
                                </div>
                                <table class="table  table-striped responsive" id="tbody">
                                    <tbody id="tbodyId">

                                    </tbody>
                                </table>
                                <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                                    <jsp:param name="id" value="tbodyId" />
                                    <jsp:param name="url" value="/teach/subjectTeacher/getSubjectTeacherList?sub=list" />
                                    <jsp:param name="pageSize" value="${page.pageSize}" />
                                </jsp:include>
                                <div class="clear"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<script type="text/javascript">
    $(function() {
        var iframeHeight=840;
        $("#iframe_sencond",window.parent.document).height(iframeHeight+'px');
        $("#iframe_first",window.parent.parent.document).height((iframeHeight+71)+'px');
        $(".select_c a").click(function() {
            $(".select_c a").removeClass("on");
            $(this).addClass("on");
        });

        var stageCode = $(".select_c a").attr("data-obj-code");
        $("#stageCode").val(stageCode);
        ajaxFunction(stageCode);

        $.createMemberSelectorByClass({
            "inputClassSelector" : ".a4"
        });
    });

    //根据学段科目列表
    function ajaxFunction(stageCode) {
        var subjectList = '${subjectList}';
        var url = "${pageContext.request.contextPath}/teach/subjectTeacher/getSubjectList";
        var aj = $.ajax({
            url : url,
            data : 'stageCode=' + stageCode,
            type : 'post',
            cache : false,
            dataType : 'json',
            success : function(data) {
                subjectList = data.subjectList;
                var krTeacher = document.getElementById("krTeacher");
                if (subjectList.length == 0) {
                    krTeacher.style.display = "none";
                    document.getElementById("tbody").style.display = "none";

                } else {
                    krTeacher.style.display = "";
                }
                loadDiv(subjectList, stageCode);
                afterHandler();

            },
            error : function() {
                $.alert("异常！");
            }
        });
    }

    //加载科目列表
    function loadDiv(subjectList, stageCode) {
        var div = $("#subject");
        div.html('');
        if (stageCode == null) {
            div.html('<p id="noStage">暂无数据</p>');
        } else {
            if (subjectList.length == 0) {
                div.html('<p id="noSubject">暂无科目数据</p>');

            } else {
                for (var i = 0; i < subjectList.length; i++) {
                    div
                        .append('<input type="hidden" id="subjectCode" name="code" value="'+subjectList[i].code+'"/>');
                    if (i == 0) {

                        div
                            .append('<a data-subjectCode="'
                                + subjectList[i].code
                                + '"id="'
                                + subjectList[i].id
                                + '"name="'
                                + subjectList[i].name
                                + 'href="javascript:void(0) onclick="ajaxSubjectFunction('
                                + stageCode + ',' + subjectList[i].code
                                + ')"' + 'class="on"' + 'style="cursor:pointer;">'
                                + subjectList[i].name + '</a>');
                    } else {
                        div
                            .append('<a data-subjectCode="'
                                + subjectList[i].code
                                + '"id="'
                                + subjectList[i].id
                                + '"name="'
                                + subjectList[i].name
                                + 'href="javascript:void(0) onclick="ajaxSubjectFunction('
                                + stageCode + ',' + subjectList[i].code
                                + ')"' + 'style="cursor:pointer;"' + '>'
                                + subjectList[i].name + '</a>');
                    }

                }
            }

        }

        var subjectCode = $(".select_d .on").attr("data-subjectCode");
        $("#subjectCode").val(subjectCode);
        ajaxSubjectFunction(stageCode, subjectCode);

        $(".select_d a").click(function() {
            $(".select_d a").removeClass("on");
            $(this).addClass("on");
        });

    }

    //加载科任教师
    function ajaxSubjectFunction(stageCode, subjectCode) {
        var subjectTeacherList = '${subjectTeacherList}';
        var url = "/teach/subjectTeacher/getSubjectTeacherList";
        myPagination("tbodyId", {
            "stageCode" : stageCode,
            "subjectCode" : subjectCode
        }, url);
    }

    $(function() {
        var stageCode = document.getElementById("stageCode");
        var krTeacher = document.getElementById("krTeacher");
        if (stageCode == null) {
            krTeacher.style.display = "none";
        }
    });


    function selectedHandler(data) {
        var loader = new loadLayer();
        var $requestData = {};
        var $requestEditData = {};
        var $thisBtn = $("#" + data.ids);
        var $btnSelect = $("#" + data.ids + "_select");
        var tit = $btnSelect.attr("title");
        var jsonInfo = getAllInfo($thisBtn);
        $requestData.stageCode = jsonInfo.stageCode;
        $requestData.subjectCode = jsonInfo.subjectCode;
        $requestData.ids = jsonInfo.ids;
        var url = "${pageContext.request.contextPath}/teach/subjectTeacher/addSubjectTeacher";

        saveOrUpdate(url, $requestData, tit, data);
    }

    // 	此方法以JSON格式返回点击表格后对应的科目ID跟教师ID等等数据
    function getAllInfo(elem) {
        /* alert(JSON.stringify(data)); */
        var stageCode = $(".select_c .on").attr("data-obj-code");
        var subjectCode = $(".select_d .on").attr("data-subjectCode");
        return {
            stageCode : stageCode,
            subjectCode : subjectCode,
            ids : elem.selector
        };
    }

    function saveOrUpdate(url, $requestData, tit, data) {
        $
            .ajax({
                type : "GET",
                url : "${pageContext.request.contextPath}/teach/subjectTeacher/checkTeacher",
                data : {
                    ids : $requestData.ids,
                    stageCode : $requestData.stageCode,
                    subjectCode : $requestData.subjectCode
                },
                success : function(data) {
                    var response = data.substring(data.length - 9,
                        data.length - 2);
                    var isTeacher = data.substring(2, data.length - 9);
                    if ("success" == response) {
                        $.error(isTeacher + "这些科任教师已存在,请重新选择!");
                    } else {
                        $.post(url, $requestData, function(responseData,
                                                           status) {
                            if ("success" === status) {
                                if ("success" === responseData) {
                                    ajaxSubjectFunction(
                                        $requestData.stageCode,
                                        $requestData.subjectCode);
                                    $.success("保存成功");
                                    // 								$.closeWindowByName(data.windowName);
                                    // 								window.location.reload();
                                } else {
                                    $.error(responseData);
                                }

                            } else {
                                $.error("服务器异常");
                            }
                            loader.close();

                        });
                    }
                }

            });
    }
    //修改科任教师信息
    function loadModifySubjectTeacherPage(id) {
        var stageCode = $(".select_c .on").attr("data-obj-code");
        var subjectCode = $(".select_d .on").attr("data-subjectCode");
        $
            .initWinOnTopFromLeft(
                "修改科任教师",
                '${pageContext.request.contextPath}/teach/subjectTeacher/modifySubjectTeacherPage?id='
                + id
                + ' &stageCode='
                + stageCode
                + ' &subjectCode=' + subjectCode, '600', '300');

    }

    //删除科任教师信息
    function deleteSubjectTeacher(id) {
        $.confirm("确定执行此次操作？", function() {
            executeDel(id);
        });
    }

    function executeDel(id) {
        $
            .post(
                "${pageContext.request.contextPath}/teach/subjectTeacher/deleteSubjectTeacher?id="
                + id, {
                    "_method" : "delete"
                }, function(data, status) {
                    if ("success" === status) {
                        data = eval("(" + data + ")");
                        if ("success" === data.info) {
                            $.success("删除成功");
                            $("#" + id).remove();
                        } else if ("fail" === data) {
                            $.error("删除失败，系统异常", 1);
                        }
                    }
                });
    }
</script>
</html>
