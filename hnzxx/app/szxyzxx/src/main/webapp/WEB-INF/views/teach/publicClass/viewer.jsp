<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp" %>
    <style>
        .row-fluid .span13 {
            width: 75%;
        }

        .row-fluid .span4 {
            width: 227px;
        }

        .myerror {
            color: red !important;
            width: 22%;
            display: inline-block;
            padding-left: 10px;
        }

        .control-group {
            float: left;
            width: 50%;
        }
    </style>
</head>
<body style="background-color: #cdd4d7 !important">
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 20px 0 0;">
                <form class="form-horizontal tan_form" id="publicClass_form" action="javascript:void(0);">
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            课程名称：
                        </label>
                        <div class="controls">
                            <input type="text" id="name" name="name" class="span4" placeholder=""
                                   value="${publicClass.name}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            课程封面：
                        </label>
                        <div class="controls">
                            <div>
                                <input type="hidden" id="coverUuid" name="coverUuid" value="${publicClass.coverUuid}">
                            </div>
                            <div id="zp" style="display:inline-block;">
                                <div class="img_1">
                                    <c:if test="${(isCK==''||isCK==null) && (publicClass.coverUrl !=null && publicClass.coverUrl != '') }">
                                        <a data-id="${publicClass.coverUrl}" onclick="reMove(this);">取消</a>
                                    </c:if>
                                    <c:if test="${publicClass.coverUrl !=null && publicClass.coverUrl != ''}">
                                        <img src="${publicClass.coverUrl}" onclick="Change(this);"
                                             style="width: 260px; height: 130px;">
                                    </c:if>
                                    <c:if test="${publicClass.coverUrl ==null || publicClass.coverUrl == ''}">
                                        <img src="${ctp}/res/images/no_picture.jpg" onclick="Change(this);"
                                             style="width: 233px; height: 130px;">
                                    </c:if>
                                </div>
                            </div>
                            <div><span>支持jpg、gif、png、bmp格式，宽高3:4</span></div>
                            <c:if test="${isCK==''||isCK==null}">
                                <input type="hidden" id="uploader" name="uploader">
                            </c:if>
                            <span id="tp_queue"></span>

                            <div class="clear"></div>
                        </div>


                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            课程类型：
                        </label>
                        <div class="controls">
                            <label class="radio" style="float:left;padding-top: 5px; margin-left: 5px;">
                                <input type="radio" name="classType"
                                       value="0" ${publicClass.classType==0 ?'Checked':''} ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
                                学校社团 </label>
                            <label class="radio" style="float: left; padding-top: 5px; margin-left: 5px;">
                                <input type="radio" name="classType"
                                       value="1" ${publicClass.classType==1 ?'Checked':''} ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
                                课后5+2课程 </label>

                            <label class="radio" style="float: left; padding-top: 5px; margin-left: 5px;">
                                <input type="radio" name="classType"
                                       value="2" ${publicClass.classType==2 ?'Checked':''} ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
                                寒暑假 </label>
                        </div>
                    </div>
                    <%--价格--%>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            课程费用：
                        </label>
                        <div class="controls">
                            <input  value="${publicClass.xuefei}" type="text" id="xuefei" name="xuefei" class="span4" placeholder=""
                            ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            有无材料费：
                        </label>
                        <div class="controls">
                            <label class="radio" style="float:left;padding-top: 5px; margin-left: 5px;">
                                <input type="radio" name="isCailiao" value="0" onclick="xianshi()"
                                ${publicClass.isCailiao==0 ?'Checked':''} ${isCK != null && isCk != '' ? "disabled='disabled'" : ""} >
                                有 </label>
                            <label class="radio" style="float: left; padding-top: 5px; margin-left: 5px;">
                                <input type="radio" name="isCailiao" onclick="yincang()"
                                       value="1" ${publicClass.isCailiao==1 ?'Checked':''} ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
                                无 </label>
                        </div>
                    </div>
                    <div class="control-group" id="cailiaodiv" style="display: none">
                        <label class="control-label"><font style="color: red">*</font>
                            材料费：
                        </label>
                        <div class="controls">
                            <input  value="${publicClass.cailiaofei}" type="text" id="cailiaofei" name="cailiaofei" class="span4" placeholder=""
                            ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
                        </div>
                    </div>
                    <div class="control-group" id="cailiaodiv">
                        <label class="control-label"><font style="color: red">*</font>
                            类别：
                        </label>
                        <div class="controls">
                            <select id="leixing" name="leixing" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
                                <option value="1" ${publicClass.leixing==1?'selected':''}>基本托管类</option>
                                <option value="2"   ${publicClass.leixing==2 ?'selected':''}>科技益智类</option>
                                <option value="3" ${publicClass.leixing==3 ?'selected':''}>音乐表演类</option>
                                <option  value=4" ${publicClass.leixing==4 ?'selected':''}>美术艺术类</option>
                                <option value="5" ${publicClass.leixing==5 ?'selected':''}>体育运动类</option>
                                <option value="6" ${publicClass.leixing==6 ?'selected':''}>综合素质类</option>
                                <option value="7" ${publicClass.leixing==7 ?'selected':''}>阅读鉴赏类</option>
                                <option value="8" ${publicClass.leixing==8 ?'selected':''}>户外拓展类</option>
                            </select>
                        </div>
                    </div>
                    <%--						新增年级--%>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            年级：
                        </label>
                        <div class="controls">
                            <input type="text" id="gradeId" name="grade" class="span4" placeholder=""
                                   value="${publicClass.fullName}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
                        </div>
                    </div>
                    <%--						--%>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            上课教师：
                        </label>
                        <div class="controls">
                            <input type="text" id="teacherId" name="teacherId" class="span4" placeholder=""
                                   data-id="${publicClass.teacherId}"
                                   value="${publicClass.teacherName}"  ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            选课开始日期：
                        </label>
                        <div class="controls">
                            <input type="text" id="beginDate" name="beginDate" class="span4" placeholder=""
                                   value='<fmt:formatDate pattern="yyyy-MM-dd" value="${publicClass.beginDate}"></fmt:formatDate>' ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            课程总节数：
                        </label>
                        <div class="controls">
                            <input type="text" id="classNumber" name="classNumber" class="span4" placeholder=""
                                   value="${publicClass.classNumber}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            人数上限：
                        </label>
                        <div class="controls">
                            <input type="text" id="maxMember" name="maxMember" class="span4" placeholder=""
                                   value="${publicClass.maxMember}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            已报名人数：
                        </label>
                        <div class="controls">
                            <input type="text" id="enrollNumber" name="enrollNumber" class="span4" placeholder=""
                                   value="${publicClass.enrollNumber}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            选课截止日期：
                        </label>
                        <div class="controls">
                            <input type="text" id="expiryDate" name="expiryDate" class="span4" placeholder=""
                                   value='<fmt:formatDate pattern="yyyy-MM-dd" value="${publicClass.expiryDate}"></fmt:formatDate>' ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
                        </div>
                    </div>
                    <%--							<div class="control-group">--%>
                    <%--								<label class="control-label">--%>
                    <%--									报名详情：--%>
                    <%--								</label>--%>
                    <%--								<div class="controls">--%>
                    <%--								<textarea id="enrollDesc" name="enrollDesc" class="span4" placeholder="" rows="3" cols="1" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>${publicClass.enrollDesc}</textarea>--%>
                    <%--								</div>--%>
                    <%--							</div>--%>

                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            上课时间：
                        </label>

                        <div class="controls teacher">

                            <input type="text" id="timeId" name="timeId" class="span4" placeholder=""
                                   data-id="${publicClass.timeId}"
                                   value="${publicClass.classTime}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            课程详情：
                        </label>
                        <div class="controls">
                            <textarea id="classDesc" name="classDesc" class="span4" placeholder="" rows="3"
                                      cols="1" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>${publicClass.classDesc}</textarea>
                        </div>
                    </div>


                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${publicClass.id}"/>

                        <button class="btn btn-warning" type="button" id="addStudentBtn">添加学生
                        </button>

                        <button class="btn btn-warning" type="button"
                                onclick="checkEnrollStudent('${publicClass.id}')">查看已报名学生
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var checker;
    $(function () {
        checker = initValidator();

        $.createStudentSelector2({
            "inputIdSelector": "#addStudentBtn",
            "ry_type": "stu"
        });
        youWu()
    });


    //加载页面材料费是否有
    function youWu() {
        var res=$("input[name='isCailiao']:checked").val();
        if(res==0){
            $("#cailiaodiv").show();
        }else{
            $("#cailiaodiv").hide()//隐藏
        }
    }
    // 保存已经添加的学生信息
    function selectedStuHandler(data) {
        var publicId = '${publicClass.id}';
        console.log(publicId);
        if (publicId != null && publicId != 'undefined') {
            var ids = new Array();
            $(data.ids).each(function (index, value) {
                ids.push(value);
            });
            var url = "${ctp}/teach/publicClass/addPublicClassStu";
            $.post(url, {studentId: ids.join(","), id: publicId}, function (data, status) {
                if ("success" === status) {
                    $.success('添加学生成功');
                    data = eval("(" + data + ")");
                    if ("success" === data.info) {
                        if (parent.core_iframe != null) {
                            parent.core_iframe.window.location.reload();
                        } else {
                            // parent.window.location.reload();
                        }
                        // $.closeWindow();
                    } else {
                        $.error("添加学生失败");
                    }
                } else {
                    $.error("操作失败");
                }
                loader.close();
            });
        }
    }

    function initValidator() {
        return $("#publicClass_form").validate({
            errorClass: "myerror",
            rules: {},
            messages: {}
        });
    }

    function Change(obj) {
        var imgSrc = $(obj).attr("src");
        window.open(imgSrc);
    }


    //保存或更新修改
    function saveOrUpdate() {
        if (checker.form()) {
            var loader = new loadLayer();
            var $id = $("#id").val();
            var $requestData = formData2JSONObj("#publicClass_form");
            var url = "${ctp}/teach/publicClass/creator";
            if ("" != $id) {
                $requestData._method = "put";
                url = "${ctp}/teach/publicClass/" + $id;
            }
            loader.show();
            $.post(url, $requestData, function (data, status) {
                if ("success" === status) {
                    $.success('操作成功');
                    data = eval("(" + data + ")");
                    if ("success" === data.info) {
                    } else {
                        $.error("操作失败");
                    }
                } else {
                    $.error("操作失败");
                }
                loader.close();
            });
        }
    }


    //查看已报名学生信息
    function checkEnrollStudent(id) {
        $.initWinOnTopFromLeft('已报名学生', '${ctp}/teach/publicClass/checkEnrollStu?id=' + id, '900', '600');
    }
</script>
</html>