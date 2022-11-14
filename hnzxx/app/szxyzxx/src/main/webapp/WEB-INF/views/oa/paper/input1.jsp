<%@ page language="java"
         import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <%@ include file="/views/embedded/plugin/uploadify.jsp" %>
    <script src="${pageContext.request.contextPath}/res/js/common/plugin/editor/kindeditor-min.js"></script>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <%@ include file="/views/embedded/plugin/zTree.jsp" %>
    <%@ include file="/views/embedded/plugin/dept_selector_js.jsp" %>
    <link href="${pageContext.request.contextPath}/res/css/extra/oa.css"
          rel="stylesheet">
    <title>文印</title>
    <style type="text/css">
        .row-fluid .span4 {
            width: 220px;
        }

        input[type="radio"] {
            margin: 0 5px;
            position: relative;
            top: -1px;
        }

        .myerror {
            color: red !important;
            width: 22%;
            display: inline-block;
            padding-left: 10px;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="公文" name="title"/>
        <jsp:param value="${param.dm}" name="menuId"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="oa_top">
                    <a href="javascript:void(0);" class="back right" onclick="back();"><i
                            class="fa fa-arrow-circle-left"></i>返回</a>
                    <ul class="top_ul">
                        <li><a href="javascript:void(0)" class="on">发公文</a></li>
                    </ul>

                </div>
                <div class="yc_sq">
                    <form class="form-horizontal" action="javascript:void(0);"
                          id="paper_form">
                        <div class="control-group">
                            <label class="control-label">标题<span style="color: red">*</span>：
                            </label>
                            <div class="controls">
                                <input type="text"
                                       class="span8 left_red {required : true,maxlength:40}"
                                       placeholder="请输入标题，少于40个中文字符" id="title" name="title"
                                       value="${paper.title }"/>
                            </div>
                        </div>
<%--                        <div class="control-group">--%>
<%--                            <label class="control-label">发文单位<span--%>
<%--                                    style="color: red">*</span>：--%>
<%--                            </label>--%>
<%--                            <div class="controls">--%>
<%--                                <input type="text"--%>
<%--                                       class="span4 left_red {required : true,maxlength:40}"--%>
<%--                                       name="author" id="author" placeholder="发文单位，少于40个中文字符"--%>
<%--                                       value="${paper.author }"/>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="control-group content">--%>
<%--                            <label class="control-label">发布日期：</label>--%>
<%--                            <div class="controls">--%>
<%--                                <input type="text" class="span4 " placeholder="留空是当前时间"--%>
<%--                                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"--%>
<%--                                       id="publishDate" name="publishDate"--%>
<%--                                       value="${paper.publishDate }"/>--%>
<%--                            </div>--%>
<%--                        </div>--%>
                        <div class="control-group content">
                            <label class="control-label">摘要<span style="color: red">*</span>：
                            </label>
                            <div class="controls">
									<textarea
                                            class="span8 left_red {required : true,maxlength:300}"
                                            id="remark" name="remark"
                                            placeholder="摘要，少于300个中文字符">${paper.remark }</textarea>
                            </div>
                        </div>
                        <div class="control-group content">
                            <label class="control-label">正文<span style="color: red">*</span>：
                            </label>
                            <div class="textarea controls ">
									<textarea
                                            style="width: 800px; height: 400px; visibility: hidden;"
                                            id="content">${paper.content }</textarea>
                                <span style="color: #fff">必填字段</span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">附件：</label>
                            <div class="controls update_img">
                                <c:choose>
                                    <c:when test="${not empty paper.attachmentUuid  }">
                                        <p
                                                style='display: inline-block; margin-bottom: 0; width: 240px; overflow: hidden;'>
                                            <a target="_blank" id="a"
                                               href='<entity:getHttpUrl uuid="${paper.attachmentUuid }"/>'>${entity.fileName}</a>
                                            <button id="b" onclick="deleteFile();" class="btn btn-red"
                                                ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>删除
                                            </button>
                                        </p>
                                    </c:when>
                                    <c:otherwise>
                                        <p style='display: inline-block; margin-bottom: 0; width: 240px; overflow: hidden;'>
                                            <a taget="_blank" id="a"></a>
                                        </p>
                                        <c:choose>
                                            <c:when test="${isCK != null && isCk != '' }"></c:when>
                                            <c:otherwise>
                                                <input type="hidden" id="uploader" name="uploader"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:otherwise>
                                </c:choose>
                                <input type="hidden" id="entityId" name="attachmentUuid"
                                       value="${paper.attachmentUuid }"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" style="margin-top:10px;">发布对象<span
                                    style="color: red">*</span>：
                            </label> <input type="hidden"
                                            value="${empty paper.receiverType ? 0 : paper.receiverType}"
                                            id="nowCheck" name="receiverType"/>
                            <div class="controls" style="">
<%--                                <c:if test="${isSchoolOperator}">--%>
<%--                                    <input type="radio" name="receiverType" id="all" value="0"/>全员--%>
<%--                                </c:if>--%>
<%--                                <input type="radio" name="receiverType" id="department"--%>
<%--                                       value="1"--%>
<%--                                       <c:if test="${paper.receiverType==1 }">checked</c:if> /><span>部门</span>--%>
<%--                                <div id="dept"--%>
<%--                                     style="margin-left: 10px; display: inline-block; position: relative; top: 10px;"></div>--%>
                                <input type="hidden" value="${depId}" id="depId"/> <input
                                    type="hidden" value="${depName}" id="depName"/> <input
                                    type="radio" name="receiverType" id="person" value="2"
                                    <c:if test="${paper.receiverType==2 }">checked</c:if> /><span>个人</span>
                                <input type="button" id="member_selector" value="添加教师">
                                <input type="hidden" id="teaId" value="${tIds}"/> <span
                                    id="ts"></span>
                                <c:if test="${!empty  tIds}"><span id="yixuan">已选择的老师：</span></c:if>
                                <sapn id="teachName">${tNames}</sapn>
                                <button class="btn btn-danger" onclick="cleanTeacher();">清除</button>
                                <div class="s_select"
                                     style="position: relative; display: inline-block;"></div>
                            </div>
                        </div>
                        <div class="caozuo" style="text-align: center;">
                            <input type="hidden" id="id" name="id" value="${paper.id }"/>
                            <button class="btn btn-success" onclick="saveOrUpdate();">发布</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var editor;
    var checker;
    var ids = "";
    $(function () {

        $("input:radio[name='receiverType']").change(function () {
            var checkType = $("input:radio[name='receiverType']:checked").attr("id");
            if (checkType == 'all') {
                $("#nowCheck").val(0);
            } else if (checkType == 'person') {
                $("#nowCheck").val(2);
            } else {
                $("#nowCheck").val(1);
            }
        });

        KindEditor.ready(function (K) {
            editor = K.create('textarea[id="content"]', {
                resizeType: 1,
                allowPreviewEmoticons: false,
                allowImageUpload: false,
                items: [
                    'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                    'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                    'insertunorderedlist', '|', 'emoticons', 'image', 'link']
            });
            K('input[name=getHtml]').click(function (e) {
                alert(editor.html());
            });
            K('input[name=isEmpty]').click(function (e) {
                alert(editor.isEmpty());
            });
            K('input[name=getText]').click(function (e) {
                alert(editor.text());
            });
            K('input[name=selectedHtml]').click(function (e) {
                alert(editor.selectedHtml());
            });
            K('input[name=setHtml]').click(function (e) {
                editor.html('<h3>Hello KindEditor</h3>');
            });
            K('input[name=setText]').click(function (e) {
                editor.text('<h3>Hello KindEditor</h3>');
            });
            K('input[name=insertHtml]').click(function (e) {
                editor.insertHtml('<strong>插入HTML</strong>');
            });
            K('input[name=appendHtml]').click(function (e) {
                editor.appendHtml('<strong>添加HTML</strong>');
            });
            K('input[name=clear]').click(function (e) {
                editor.html('');
            });
        });
        checker = initValidator();
        uploadFile();
        //部门选择器
        $.createDeptSelector({
            "deptContainer": "#dept",
            enableBatch: true,
            selectedItemId: "${depId}",
            selectedItemTitle: "${depName}"
        });

// 			addDepartment();
        //教师筛选
        $.createMemberSelector({
            "inputIdSelector": "#member_selector",
            "ry_type": "teach",
            "layerOp": {
                "shift": "top",
                type: 2
            }
        });

        isCheck();

    });

    function addDepartment() {
        var depName = $("#depName").val();
        var depId = $("#depId").val();
        if (depName == "") {
            depName = "请选择";
        }
        $("#dept_selected_name_span").text(depName)
    }

    var ts = "已选择的老师：";
    var teachName = "";

    function selectedHandler(data) {
        $("#yixuan").text("");
        // teachName = $("#teachName").text();
        // ids = $("#teaId").val();
        // $.each(data.ids, function (index, value) {
        //     if (ids.indexOf(value) == -1) {
        //         ids = ids + value + ",";
        //         if ($.trim(teachName) == "") {
        //             teachName = data.names[index];
        //         } else {
        //             teachName = teachName + "," + data.names[index];
        //         }
        //     }
        // });

        if(typeof(data.ids)=="object"){
            ids=data.ids[0]
            teachName=data.names[0];
        }else{
            ids=data.ids;
            teachName=data.names;
        }

        $("#ts").text(ts);
        $("#teachName").text(teachName);
        $("#teaId").val(ids);
        $.success("设置成功");
        $.closeWindowByName(data.windowName);
    }

    //返回
    function back() {
        window.location.href = document.referrer;
    }

    //文件上传
    function uploadFile() {
        var obj = $("#uploader").uploadify({
            swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
            uploader: '${pageContext.request.contextPath}/uploader/common',
            formData: {
                'jsessionId': '<%=request.getSession().getId()%>'
            },
            fileObjName: 'file',
            fileTypeDesc: "文件上传",
            fileTypeExts: "*.*", //默认*.*
            method: 'post',
            multi: false, // 是否能选择多个文件
            auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
            removeTimeout: 1,
            queueSizeLimit: 1,
            fileSizeLimit: 4 * 1024,
            buttonText: "上传文件",
            requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
            height: 20,
            width: 70,
            onUploadSuccess: function (file, data, response) {
                var $jsonObj = eval("(" + data + ")");
                $("#entityId").val($jsonObj.uuid);
                $("#a").text($jsonObj.realFileName);
                $("#a").attr('href', $jsonObj.url);
                $("#a").attr('target', '_blank');

            },
            onUploadStart: function (file) { //上传开始时触发（每个文件触发一次）
                $("#infoBox").prev("p").css("display", "none");
                $("#infoBox").css("display", "block");
            },
            onUploadError: function (file, errorCode, errorMsg,
                                     errorString) {
                $.alert('The file ' + file.name
                    + ' could not be uploaded: '
                    + errorString);
            }
        });

    }

    function initValidator() {
        return $("#paper_form").validate({
            errorClass: "myerror",
            rules: {},
            messages: {}
        });
    }

    //清除已选的教师
    function cleanTeacher() {
        $("#yixuan").text("");
        $("#ts").text("");
        $("#teaId").val("");
        $("#teachName").text("");
    }

    //判断是否选中对应的按钮
    function isCheck() {
        var checkType = $("input:radio[name='receiverType']:checked")
            .attr("id");
        //部门选择
        $("#dept_selected_name_span").click(function () {
            cleanTeacher();
            $("input:radio[id='department']").prop("checked", true);
            $("#nowCheck").val(1);
        });
        //人员选择
        $("#member_selector_select").click(function () {
            $("input:radio[id='person']").prop("checked", true);
            $("#nowCheck").val(2);
            $("#dept_selected_name_span").text("请选择");
        });

        //全部选择
        $("#all").click(function () {
            $("input:radio[id='all']").prop("checked", true);
            $("#nowCheck").val(0);
            $("#dept_selected_name_span").text("请选择");
            cleanTeacher();
        });
    }

    //保存或更新
    function saveOrUpdate() {
        if (checker.form()) {
            var loader = new loadLayer();
            var $id = $("#id").val();
            var tId = $("#teaId").val();
            var tName = $("#teachName").text();
            var departmentId = $("#dept").attr("data-id");
            if (departmentId === undefined) {
                departmentId = $("#depId").val();
            }
            var receiverType = $("#nowCheck").val();
            var $requestData = formData2JSONObj("#paper_form");
            $requestData.content = editor.html();

            if ($.trim(editor.text()) == null || $.trim(editor.text()) == "") {
                $(".form-horizontal .textarea span").css("color", "red");
                return;
            }
            $requestData.departmentIds = departmentId;
            $requestData.teacherIds = tId;
            $requestData.teacherNames = tName;
            // 			alert($requestData.publishDate);
            if (receiverType == "1") {
                if (departmentId == "" || departmentId == null) {
                    $.alert("请选择接收信息的部门！");
                    return;
                }
            } else if (receiverType == "2") {
                if (tId == "" || tId == null) {
                    $.alert("请选择接收信息的人员！");
                    return;
                }
            }

            var url = "${pageContext.request.contextPath}/office/paper/creator1";
            if ("" != $id) {
                $requestData._method = "put";
                url = "${pageContext.request.contextPath}/office/paper/" + $id;
            }
            loader.show();
            $.post(url, $requestData, function (data, status) {
                if ("success" === status) {
                    $.success('发布成功');
                    data = eval("(" + data + ")");
                    if ("success" === data.info) {
                        window.location.href = document.referrer;
                    } else {
                        $.error("发布失败");
                    }
                } else {
                    $.error("发布失败");
                }
                loader.close();
            });
        }
    }

    function deleteFile() {
        $.confirm("确定执行此次操作？", function () {
            executeDel();
        });
    }

    function executeDel() {
        $("#a").text("");
        $("#a").attr('href', "");
        $("#entityId").val("");
        $("#b").remove();
        $(".update_img").children().append(
            "<input type='file' id='uploader' name='uploader'/>");
        uploadFile();
    }
</script>

</html>