<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp" %>
    <%--<%@ include file="/views/embedded/plugin/uploadify.jsp" %>--%>
    <link rel="stylesheet" type="text/css" href="${ctp}/res/css/uploadifive/uploadifive.css">
    <script src="${ctp}/res/js/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <style>
        .row-fluid .span13 {
            width: 75%;
        }

        .row-fluid .span4 {
            width: 227px;
        }

        .myerror {
            color: red !important;
            width: 30%;
            display: inline-block;
            padding-left: 10px;
        }

        .control-group {
            float: left;
            width: 50%;
        }

        .grede {
            position: absolute;
        }

        .grede_ul {
            display: none;
            position: relative;
            background-color: #fff;

        }

        .grede_ul > li {
            border-bottom: 1px rgb(127, 127, 127) solid;
            padding-left: 1rem;
        }

        .grede_ul > li > span {
            padding-left: 1rem;

        }

        #grede_btn {
            border: none;
            margin-left: 2rem;


        }

        #grede_btn:hover {
            background: #a0b569;
        }

        .grede_btn2 {
            border: none;
        }

        #grede_btn2:hover {
            background: #a0b569;
        }

        #grede_all, #all, #grede_btn, #grede_btn2 {
            /*display: none;*/
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
                            ???????????????
                        </label>
                        <div class="controls">
                            <input type="text" id="name" name="name" class="span4" placeholder=""
                                   value="${publicClass.name}">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            ???????????????
                        </label>
                        <div class="controls">
                            <div>
                                <input type="hidden" id="coverUuid" name="coverUuid" value="${publicClass.coverUuid}">
                            </div>
                            <div id="zp" style="display:inline-block;">
                                <div class="img_1">
                                    <c:if test="${(isCK==''||isCK==null) && (publicClass.coverUrl !=null && publicClass.coverUrl != '') }">
                                        <a data-id="${publicClass.coverUrl}" onclick="reMove(this);">??????</a>
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
                            <div><span>??????jpg???gif???png???bmp???????????????3:4</span></div>
                            <c:if test="${isCK==''||isCK==null}">
                                <input type="hidden" id="uploader" name="uploader">
                            </c:if>
                            <span id="tp_queue"></span>

                            <div class="clear"></div>
                        </div>


                    </div>

                    <div class="control-group">
                        <label class="control-label">
                            ???????????????
                        </label>
                        <div class="controls">
                            <label class="radio" style="float:left;padding-top: 5px; margin-left: 5px;">
                                <input type="radio" name="classType" value="0"
                                       Checked ${publicClass.classType==0 ?'Checked':''} >
                                ???????????? </label>
                            <label class="radio" style="float: left; padding-top: 5px; margin-left: 5px;">
                                <input type="radio" name="classType"
                                       value="1" ${publicClass.classType==1 ?'Checked':''} >
                                ??????5+2?????? </label>
                            <label class="radio" style="float: left; padding-top: 5px; margin-left: 5px;">
                                <input type="radio" name="classType"
                                       value="2" ${publicClass.classType==2 ?'Checked':''}>
                                ????????? </label>
                        </div>
                    </div>
                    <%--??????--%>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            ???????????????
                        </label>
                        <div class="controls">
                            <input value="${publicClass.xuefei}" type="text" id="xuefei" name="xuefei" class="span4" placeholder="">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            ??????????????????
                        </label>
                        <div class="controls">
                            <label class="radio" style="float:left;padding-top: 5px; margin-left: 5px;">
                                <input type="radio" name="isCailiao" id="isCailiao" value="0" onclick="xianshi()"
                                        ${publicClass.isCailiao==0 ?'Checked':''} >
                                ??? </label>
                            <label class="radio" style="float: left; padding-top: 5px; margin-left: 5px;">
                                <input type="radio" name="isCailiao" id="isCailiao2" onclick="yincang()"
                                          value="1" ${publicClass.isCailiao==1 ?'Checked':''}>
                                ??? </label>
                        </div>
                    </div>
                    <div class="control-group" id="cailiaodiv" style="display: none">
                        <label class="control-label"><font style="color: red">*</font>
                            ????????????
                        </label>
                        <div class="controls">
                            <input value="${publicClass.cailiaofei}" type="text" id="cailiaofei" name="cailiaofei" class="span4" placeholder="">
                        </div>
                    </div>


                    <div class="control-group" id="cailiaodiv">
                        <label class="control-label"><font style="color: red">*</font>
                            ?????????
                        </label>
                        <div class="controls">
                           <select id="leixing" name="leixing">
                               <option value="1"  ${publicClass.leixing==1?'selected':''}>???????????????</option>
                               <option value="2"   ${publicClass.leixing==2 ?'selected':''}>???????????????</option>
                               <option value="3" ${publicClass.leixing==3 ?'selected':''}>???????????????</option>
                               <option  value="4" ${publicClass.leixing==4 ?'selected':''}>???????????????</option>
                               <option value="5" ${publicClass.leixing==5 ?'selected':''}>???????????????</option>
                               <option value="6" ${publicClass.leixing==6 ?'selected':''}>???????????????</option>
                               <option value="7" ${publicClass.leixing==7 ?'selected':''}>???????????????</option>
                               <option value="8" ${publicClass.leixing==8 ?'selected':''}>???????????????</option>
                           </select>
                            </div>
                    </div>
                    <%--			1.0??????????????????			--%>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            ?????????
                        </label>
                        <div class="controls grede">
                            <input type="text" id="grede" name="fullNameArr" class="span4"
                                   placeholder="???????????????"
                                   value="${publicClass.fullName}" unselectable="on" readonly>
                            <ul class="grede_ul" id="grede_ul">
                                <li>
                                    <input type="checkbox" value="??????" id="grede_all"><span id="all"> ??????</span>
                                    <button type="button" id="grede_btn">??????</button>
                                    <button type="button" id="grede_btn2">??????</button>
                                </li>

                            </ul>

                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            ???????????????
                        </label>
                        <div class="controls teacher">
                            <input type="text" id="teacherId" name="teacherId" class="span4" placeholder=""
                                   data-id="${publicClass.teacherId}" value="${publicClass.teacherName}">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            ?????????????????????
                        </label>
                        <div class="controls">
                            <input type="text" id="beginDate" name="beginDate" class="span4" autocomplete="off"
                                   onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"
                                   placeholder="xxxx-xx-xx xx:xx"
                                   value='<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${publicClass.beginDate}"></fmt:formatDate>'>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            ??????????????????
                        </label>
                        <div class="controls">
                            <input type="text" id="classNumber" name="classNumber" class="span4" placeholder=""
                                   value="${publicClass.classNumber}">
                        </div>
                    </div>


                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            ???????????????
                        </label>
                        <div class="controls">
                            <input type="text" id="maxMember" name="maxMember" class="span4" placeholder=""
                                   value="${publicClass.maxMember}">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            ?????????????????????
                        </label>
                        <div class="controls">
                            <input type="text" id="expiryDate" name="expiryDate" class="span4" autocomplete="off"
                                   onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"
                                   placeholder="xxxx-xx-xx xx:xx"
                                   value='<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${publicClass.expiryDate}"></fmt:formatDate>'>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            ???????????????
                        </label>

                        <div class="controls teacher">
                            <input type="text" id="timeId" name="timeId" class="span4" placeholder=""
                                   data-id="${publicClass.timeId}" value="${publicClass.classTime}">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">
                            ???????????????
                        </label>
                        <div class="controls">
                            <textarea id="classDesc" name="classDesc" class="span4" placeholder="" rows="3"
                                      onclick="edit()"
                                      cols="1">${publicClass.classDesc}</textarea>
                        </div>
                    </div>
                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${publicClass.id}"/>
                        <button class="btn btn-warning" type="button"
                                onclick="saveOrUpdate();">??????
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function yincang(){
        $("#cailiaodiv").hide()//??????
        $("#cailiaofei").val("")
    }
    function xianshi(){
       $("#cailiaodiv").show()//??????
    }

    //??????????????????????????????
    function youWu() {
        var res=$("input[name='isCailiao']:checked").val();
        if(res==null || res=="" || res==undefined){
            $("#isCailiao2").prop("checked",true);
            $("#cailiaodiv").hide()//??????
            $("#cailiaofei").val("")
        }else{
            if(res==0){
                $("#cailiaodiv").show();
            }else{
                $("#cailiaodiv").hide()//??????
                $("#cailiaofei").val("")
            }
        }

    }


    $(function () {
        $("#classDesc").html('${publicClass.classDesc}');
        uploadFile();
        $.createTeacheinfoSelector({
            "inputIdSelector": "#teacherId",
            "isOnTopWindow": true,
            "enableBatch": false
        });

        $.createTimeSelector({
            "inputIdSelector": "#timeId",
            "isOnTopWindow": true,
            "enableBatch": false
        });
        youWu();
    });


    function edit() {
        $("#grede_btn2").hide()
    }

    var grede = document.getElementsByName("grede")
    var gredeAll = $("#grede_all")
    var gredeDate = [];
    $("#grede_btn2").click(function () {
        if (gredeDate.length == 1) {
            $(".grede_ul").hide()
            $("#grede").prop("value", gredeDate)
        }
        //	????????????????????????
        if (gredeDate.length == 0) {
            alert("??????????????????")
        }

    })

    //??????
    $("#grede_btn").click(function () {
        //??????
        //	?????????????????????
        if (gredeDate.length > 0) {
            $(".grede_ul").hide()
            $("#grede").prop("value", gredeDate)
        }
        //	????????????????????????
        else {
            alert("??????????????????")
        }

    })
    <%--????????????--%>
    var gredeAll = $("#grede_all")
    gredeAll.click(function () {
        gredeDate = []
        if (gredeAll.is(':checked')) {
            for (var i = 0; i < grede.length; i++) {
                //???[]???????????????
                grede[i].checked = true
                gredeDate.push(grede[i].value)

            }
        } else {
            <%--????????????--%>
            for (var i = 0; i < grede.length; i++) {
                grede[i].checked = false
            }
        }

    })

    function gredeckecked() {
        gredeDate = []
        count = 0;
        for (var i = 0; i < grede.length; i++) {
            if (grede[i].checked) {
                gredeDate.push(grede[i].value)
                count++;
            }
        }
        <%--???????????????????????????--%>
        if (count == grede.length) {
            gredeAll.prop("checked", 'true');
        }
            <%--???????????????????????????--%>
        else {
            gredeAll.removeAttr("checked")
        }
    }

    var checker;
    $(function () {
        var count = 1;
        $("#grede").click(function () {
            //??????
            $(".grede_ul").show()
            var url = "${ctp}/teach/publicClass/grade"
            var requestData = ""
            if (count == 1) {
                $.get(url, requestData, function (data) {
                    var grades = $.parseJSON(data)
                    if (grades.grade[0].status == 2) {
                        edit()
                    } else {
                        $("#grede_btn2").hide()
                        $("#grede_btn").show()
                        $("#grede_all").show()
                        $("#all").show()
                    }
                    var s = "";
                    for (var i = 0; i < grades.grade.length; i++) {
                        s += "<li> <input type='checkbox' name='grede' value='" + grades.grade[i].fullName + "' onclick='gredeckecked()'><span>" + grades.grade[i].fullName + " </span></li>"
                    }
                    $("#grede_ul").append(s)
                })
            }
            count++
        })
        checker = initValidator();

    });

    function initValidator() {
        return $("#publicClass_form").validate({
            errorClass: "myerror",
            rules: {
                "name": {
                    required: true,
                    maxlength: 15,
                    stringCheck: false,
                    isContainsSpecialChar: false
                },

                //???????????????gradeId?????????
                "fullName": {
                    required: true
                },
                "teachers": {
                    required: true,
                },
                "beginDate": {
                    required: true
                },
                "timeId": {
                    required: true
                },
                "teacherId": {
                    required: true
                },
                "classNumber": {
                    required: true,
                    digits: true,
                    min: 1,
                    maxlength: 3
                },
                "maxMember": {
                    required: true,
                    digits: true,
                    min: 1,
                    maxlength: 3
                },
                "expiryDate": {
                    required: true
                },
                "enrollDesc": {
                    maxlength: 200
                },
                "classDesc": {
                    maxlength: 200
                },

            },
            messages: {}
        });
    }

    //?????????????????????
    function saveOrUpdate() {
        if (checker.form()) {
            //alert($("#leixing").val())
            var loader = new loadLayer();
            var $id = $("#id").val();
            $("#classDesc").val($("#classDesc").val().replace(/\n/g, "&#10;").replace(/\s/g, '&nbsp;'));
            var $requestData = formData2JSONObj("#publicClass_form");
            // ??????textarea??????
            var url = "${ctp}/teach/publicClass/creator";
            if ("" != $id) {
                $requestData._method = "put";
                url = "${ctp}/teach/publicClass/" + $id;
            }
            loader.show();
            $.post(url, $requestData, function (data, status) {
                if ("success" === status) {
                    $.success('????????????');
                    data = eval("(" + data + ")");
                    if ("success" === data.info) {
                        if (parent.core_iframe != null) {
                            parent.core_iframe.window.location.reload();
                        } else {
                            parent.window.location.reload();
                        }
                        $.closeWindow();
                    } else {
                        $.error("????????????");
                    }
                } else {
                    $.error("????????????");
                }
                loader.close();
            });
        }
    }


    function uploadFile() {
        $('#uploader').uploadifive({
            'auto': true,
            'fileObjName' : 'file',
            //'queueID': 'queue',
            'buttonText': '????????????',
            'removeCompleted':true,
            'height' : 20,
            'width' : 70,
            'formData': {
                'jsessionId': '<%=request.getSession().getId()%>'
            },
            'uploadScript': '/uploader/common',
            'onUploadComplete': function (file,data) {
                var $jsonObj = eval("(" + data + ")");
                var img = '<div class="img_1"><a data-id="'
                    + $jsonObj.uuid
                    + '" onclick="reMove(this);">??????</a><img style="width:233px;height:130px;" class="ims" onclick="Change(this);" src="'
                    + $jsonObj.url
                    + '"/>&nbsp;&nbsp;&nbsp;</div>';
                $("#coverUuid").val($jsonObj.uuid);
                $.success("????????????!", 9);
                $("#zp").html(img);
            },
            onUpload:function (file) { //???????????????????????????????????????????????????
                $("#infoBox").prev("p").css("display", "none");
                $("#infoBox").css("display", "block");
            },
            onFallback : function() {
                alert("????????????????????????!");
            },
        });





  /*      var obj = $("#uploader").uploadify({
            swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
            uploader: '${pageContext.request.contextPath}/uploader/common',
            formData: {
                'jsessionId': '<%=request.getSession().getId()%>'
            },
            fileObjName: 'file',
            fileTypeDesc: "????????????",
            fileTypeExts: '*.gif; *.jpg; *.png;*.jpeg;*.bmp',
            method: 'post',
            multi: false, // ???????????????????????????
            auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
            removeTimeout: 1,
            queueSizeLimit: 1,
            fileSizeLimit: 4 * 1024,
            buttonText: "????????????",
            requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
            height: 20,
            width: 70,
            onUploadSuccess: function (file, data, response) {
                var $jsonObj = eval("(" + data + ")");
                var img = '<div class="img_1"><a data-id="'
                    + $jsonObj.uuid
                    + '" onclick="reMove(this);">??????</a><img style="width:233px;height:130px;" class="ims" onclick="Change(this);" src="'
                    + $jsonObj.url
                    + '"/>&nbsp;&nbsp;&nbsp;</div>';
                $("#coverUuid").val($jsonObj.uuid);
                $.success("????????????!", 9);
                $("#zp").html(img);
            },
            onUploadStart: function (file) { //???????????????????????????????????????????????????
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
*/
    }

    function Change(obj) {
        var imgSrc = $(obj).attr("src");
        window.open(imgSrc);
    }

    function reMove(obj) {
        $(obj).parent().remove();
        $("#coverUuid").val("");
        $("#zp").append('<div class="img_1"><img src="${ctp}/res/images/no_picture.jpg" onclick="Change(this);" style="width: 233px; height: 130px;"></div>');
    }

    //??????????????????????????????
    function getFileUrls() {
        var imgs = $(".img_1");
        var urls = "";
        $.each(imgs, function (index, value) {
            urls += ($(value).find("img").attr("src") + ",");
        });
        if (urls != "") {
            urls = urls.substring(0, urls.length - 1);
        }
        return urls;
    }

</script>
</html>