<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity" %>
    <%@ include file="/views/embedded/common.jsp" %>
    <%@ include file="/views/embedded/plugin/uploadify.jsp" %>
    <%@include file="/views/embedded/plugin/dept_selector_js.jsp" %>
    <%@ include file="/views/embedded/plugin/zTree.jsp" %>
    <link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
    <title>文印</title>
    <script src="${pageContext.request.contextPath}/res/js/common/plugin/editor/kindeditor-min.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/extra/add.js"></script>
    <style type="textbu/css">
        .row-fluid .span4 {
            width: 220px;
        }

        input[type="radio"] {
            margin: 0 5px 0 0px;
            position: relative;
            top: -1px;
        }

        .form-horizontal .controls #zp .img_1 {
            float: left;
            margin: 10px;
            position: relative;
            top: 0;
            width: 233px;
            height: 130px;
        }

        .form-horizontal .controls #zp .img_1 img {
            width: 233px;
            height: 130px;
        }

        .form-horizontal .controls #zp .img_1 a {
            position: absolute;
            font-size: 22px;
            font-weight: bold;
            color: #000;
            right: 0px;
            top: 0px;
            display: block;
            width: 16px;
            height: 16px;
            line-height: 16px;
            text-align: center;
            cursor: pointer;
        }

        .myerror {
            color: red !important;
            width: 22%;
            display: inline-block;
            padding-left: 10px;
        }

        .ke-container {
            display: inline-block
        }

        .chzn-container {
            top: 10px;
        }

        #dept_seleted_id {
            display: inline-block;
        }
    </style>

    <style type="text/css">
        .sq_list .clsq ul li .detail_1 {
            margin-left: 0;
        }
    </style>
    <script type="text/javascript">

        var checker;
        var editor;
        var ids = "";
        var str = "";
        var i = 0;
        var depts = new Array();

        var departmentsid = "";
        Array.prototype.indexOf = function (val) {
            for (var i = 0; i < this.length; i++) {
                if (this[i] == val) return i;
            }
            return -1;
        };

        Array.prototype.remove = function (val) {
            var index = this.indexOf(val);
            if (index > -1) {
                this.splice(index, 1);
            }
        };

        $(function () {
            $("#release_picture").on("click", "ul li a", function () {

                $(this).parent().remove();
                var id = $(this).attr('id') + ",";

                str = str.replace(id, "");
                i = i - 1;
                if (i < 9) {
                    $("#uploaderFile").uploadify("disable", false);
                }


            })

        });

        $(function () {
            $("#departments").on("click", "ul li a", function () {
                $(this).parent().remove();
                var deptsid = $(this).attr('id') + ",";
                departmentsid = departmentsid.replace(deptsid, "");
                depts.remove($(this).attr('id'));

            })
        });

        $(function () {
            $("#teach").on("click", "ul li a", function () {
                $(this).parent().remove();
                var t = $(this).attr('id') + ",";
                var tID = $("#teaId").val();
                tID = tID.replace(t, "");
                $("#teaId").val(tID);

            })
        });

        function back() {
            window.location.href = document.referrer;
        }

        // ==========================================================================================================
        $(function () {

            var checkType = $("input:radio[name='daike']:checked").attr("id");
            if (checkType == 'all') {
                $("#nowCheck").val("pj.school");
            } else if (checkType == 'person') {
                $("#nowCheck").val("pj.person");
            } else {
                $("#nowCheck").val("pj.dept");
            }
        });


        KindEditor.ready(function (K) {
            editor = K.create('textarea[id="content"]', {
                resizeType: 1,
                allowPreviewEmoticons: false,
                allowImageUpload: false,

                items: ['fontname', 'fontsize', '|', 'forecolor',
                    'hilitecolor', 'bold', 'italic', 'underline',
                    'removeformat', '|', 'justifyleft', 'justifycenter',
                    'justifyright', 'insertorderedlist',
                    'insertunorderedlist', '|', 'emoticons', 'image',
                    'link'],
                afterChange: function () {
// 									 alert("s");
                    $(".form-horizontal .textarea label").hide();

                    var limitNum = 500;  //设定限制字数

// 							     $('.word_surplus').html(pattern);
                    if (this.count('text') > limitNum) {
                        var pattern = ('字数超过限制，请适当删除部分内容');

                        //超过字数限制自动截取
                        var strValue = editor.text();
                        strValue = strValue.substring(0, limitNum);
                        editor.text(strValue);
                    } else {

                        var result = limitNum - this.count('text');
                        pattern = '还可以输入' + result + '字';
                    }
                    $('.word_surplus').html(pattern); //输入显示
                }
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
        //
        uploadFile();
        //
        <%--$.HqManySchool({--%>
        <%--    "selector": "#manyschool",--%>
        <%--    "condition": {ownerType: "pj.branch_school", ownerId: "${sessionScope[sca:currentUserKey()].schoolId}"},--%>
        <%--    "selectedVal": "",--%>

        <%--});--%>


        <%--getSchool()--%>
        <%--function getSchool(){--%>
        <%--    $("#schoolId").html("")--%>
        <%--    var condition = {ownerType: "pj.branch_school", ownerId: "${sessionScope[sca:currentUserKey()].schoolId}"};--%>
        <%--    $.get("${ctp}/teach/membership/list/json", condition, function(--%>
        <%--        data, status) {--%>
        <%--        if ("success" === status) {--%>
        <%--            data = eval("(" + data + ")");--%>
        <%--            console.log("-->",data)--%>
        <%--            $.each(data, function(index, value) {--%>
        <%--                console.log(value)--%>
        <%--                $("#schoolId").append("<option value='"+value.id+"'>"+value.name+"</option>");--%>

        <%--            });--%>
        <%--            selector.val(options.selectedVal);--%>
        <%--            options.afterHandler(selector);--%>

        <%--        }--%>
        <%--    });--%>
        <%--}--%>


        //教师筛选
        $.createMemberSelector({
            "inputIdSelector": "#member_selector",
            "enableMultiCampus": true,
            "isOnTopWindow": true,
            "ry_type": "teach",
            "layerOp": {
                "shift": "top",
                type: 2,
                offset: ['115px', ''],
                area: ["1000px", "500px"]
            }
        });
        <%--//部门--%>
        <%--$.MembershipSelector({--%>
        <%--    "selector": "#school_selected",--%>
        <%--    "selectedVal": "",--%>
        <%--    "condition": {ownerType: "pj.branch_school", ownerId: "${sessionScope[sca:currentUserKey()].schoolId}"}--%>
        <%--});--%>


        getDept()
        function getDept(){
            $("#deptId").html("")
            var condition = {schoolId: "${sessionScope[sca:currentUserKey()].schoolId}"};
            console.log("session user:${sessionScope[sca:currentUserKey()]}");
            $.get("${ctp}/teach/dept/getBySchoolId", condition, function(
                data, status) {
                if ("success" === status) {
                    data = eval("(" + data + ")");
                    console.log("-->",data)
                    $.each(data, function(index, value) {
                        console.log(value)
                        $("#deptId").append("<option value='"+value.id+"'>"+value.name+"</option>");

                    });
                }
            });
        }

        function contains(arr, obj) {
            var i = arr.length;
            while (i--) {
                if (arr[i] === obj) {
                    return false;
                }
            }
            return true;
        }

        // $("#school_selected").change(function () {
        //     var schoolId = $(this).val();
        //     $.createDeptSelector({
        //         "deptContainer": "#dept_seleted_id",
        //         "schoolId": schoolId,
        //         "enableBatch": false,
        //         "clickCallback": function () {
        //             var school_name = $("#school_selected_chzn .chzn-single span").html();//学校名称
        //             var school_id = $("#school_selected").val();//学校id
        //             var selectedDept = $("#dept_selected_name_span").html();//部门名称
        //             var dept_id = $("#dept_seleted_id").attr("data-id");//部门id
        //             $("input:radio[id='department']").prop("checked", true);
        //             if (!$(".drop_choice").is(":hidden")) {
        //                 $(".drop_choice").hide();
        //                 $(".drop_whole b").addClass("hide_btn")
        //             }
        //             if (dept_id.length != 0) {
        //                 var dept = "<li title='" + school_name + "(" + selectedDept + ")'>" + school_name + "(" + selectedDept + ")<a href='javascript:void(0);'  id='" + dept_id + "'></a></li>";
        //                 if (contains(depts, dept_id)) {
        //                     depts.push(dept_id);
        //                     $('#departments ul').append(dept);
        //                     departmentsid += dept_id + ",";
        //                 }
        //             }
        //         }
        //     });
        //
        // });
        isCheck();


        // ==========================================================================================================

        function addDepartment() {
            var depName = $("#depName").val();
            var depId = $("#depId").val();
            if (depName == "") {
                depName = "请选择";
            }

        }


        var teachName = "";

        function selectedHandler(data) {
            teachName = $("#teachName").val();
            ids = $("#teaId").val();
            $.each(data.ids, function (index, value) {
                if (ids.indexOf(value) == -1) {
                    ids = ids + value + ",";
                    var d = "<li>" + data.names[index] + "<a href='javascript:void(0);'  id='" + value + "'></a></li>";
                    $('#teach ul').append(d);
                    if ($.trim(teachName) == "") {
                        teachName = data.names[index];
                    } else {
                        teachName = teachName + "," + data.names[index];
                    }
                }
            });

            $("#teachName").val(teachName);
            $("#teaId").val(ids);
            $.success("设置成功");
            $.closeWindowByName(data.windowName);
        }

        //文档上传
        function uploadFile() {

            var obj = setTimeout(function () {
                $("#uploaderFile").uploadify({
                    swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
                    uploader: '${pageContext.request.contextPath}/uploader/common',
                    formData: {'jsessionId': '<%=request.getSession().getId()%>'},
                    fileObjName: 'file',
                    fileTypeDesc: "文件上传",
// 							fileTypeExts : "*.*", //默认*.*
                    fileTypeExts: '*.gif; *.jpg; *.png;*.jpeg;*.bmp',
                    method: 'post',
                    multi: true, // 是否能选择多个文件
                    auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
                    removeTimeout: 1,
                    queueSizeLimit: 9,
                    fileSizeLimit: 4 * 1024,
                    buttonText: "上传图片",
                    requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
                    height: 20,
                    width: 70,
// 							uploadLimit:9,
                    onUploadSuccess: function (file, data, response) {
                        var $jsonObj = eval("(" + data + ")");

                        i = i + 1;

                        var img1 = '<li><img src="' + $jsonObj.url + '" height="113" width="178"><a href="javascript:void(0);"id="' + $jsonObj.uuid + '"></a></li>';
                        str += $jsonObj.uuid + ",";
// 								  var img1='<li><img src="'+ $jsonObj.url+'" height="113" width="178"><a href="javascript:void(0);"id="'+$jsonObj.uuid+'"></a></li>';
                        $("#release_picture ul").append(img1);
// 									$.success("上传成功!", 9);
// 								$("#fj").append(img);
                        if (i == 9) {
                            $("#uploaderFile").uploadify("disable", true);
                            return;
                        }
                    },
                    onUploadStart: function (file) { //上传开始时触发（每个文件触发一次）
                        $("#infoBox").prev("p").css("display", "none");
                        $("#infoBox").css("display", "block");
                    }


            })
        }

        ,
        30
        )
        ;
        }


        function initValidator() {
            return $("#notice_form").validate({
                errorClass: "myerror",
                rules: {},
                messages: {}
            });
        }

        function Change(obj) {
            var imgSrc = $(obj).attr("src");
            window.open(imgSrc);
        }

        function reMove(obj) {
            $(obj).parent().remove();
        }

        //获取文件的缩略图路径
        function getFileUrls() {
            var imgs = $(".img_1");
            var urls = "";
            $.each(imgs, function (index, value) {
                urls += ($(value).find("img").attr("data-id") + ",");
            });
            if (urls != "" && urls != "undefined") {
                urls = urls.substring(0, urls.length - 1);
            }
            return urls;
        }

        //清除教师信息
        // 	function cleanTeacher(){
        // 		$("#ts").text("");
        // 		$("#teaId").val("");
        // 		$("#teachName").val("");
        // 	}

        //判断是否选中对应的按钮
        function isCheck() {
            //部门选择
            $("#dept_selected_name_span").click(function () {
// 		    $(".drop_choice").prop("disabled",none);
                $("input:radio[id='department']").prop("checked", true);
                $("#nowCheck").val("pj.dept");


            });
// 		多校区部门选择
            $("body").on("click", "#school_selected_chzn,#dept_seleted_id", function () {
                $("input:radio[id='department']").prop("checked", true);
                if (!$(".drop_choice").is(":hidden")) {
                    $(".drop_choice").hide();
                    $(".drop_whole b").addClass("hide_btn")
                }
                $("#nowCheck").val("pj.dept");
            })
// 		学校选择
//             $("#manyschool").click(function () {
//                 $("input:radio[id='all']").prop("checked", true);
//             })
            //人员选择
            $("#member_selector_select").click(function () {
                $("input:radio[id='person']").prop("checked", true);
                if (!$(".drop_choice").is(":hidden")) {
                    $(".drop_choice").hide();
                    $(".drop_whole b").addClass("hide_btn")
                }
                $("#nowCheck").val("pj.person");

            });
            //选择全部
            $("#all").click(function () {
                $("input:radio[id='all']").prop("checked", true);
                $("#nowCheck").val("pj.school");


            });
        }

        //保存或更新修改
        function saveOrUpdate() {

            console.log(checker);
             // if (checker.form()) {

                var loader = new loadLayer();
                var $id = $("#id").val();
                var tId = $("#teaId").val();
                var tName = $("#teachName").val();
                var departmentId = $("#dept").attr("data-id");
                //var schoolIds = $('.school_id').val();
                var schoolIds = $("#schoolId").val();//$("#schoolId option:selected").val()
                if (departmentId === undefined) {
                    departmentId = $("#depId").val();
                }
                var receiverType = $("#nowCheck").val();
                var $requestData = formData2JSONObj("#notice_form");
                $requestData.uuids = str;
                $requestData.uploadFile = $("#fjId").val();
                $requestData.content = editor.html();

                if ($.trim(editor.text()).length < 4) {
                    $(".form-horizontal .textarea label").show();
                    return;
                }

// 			if($.trim(editor.text())==null || $.trim(editor.text())==""){
// 				$(".form-horizontal .textarea #kong").show();
// 				return;
// 			}


                $requestData.departmentIds = departmentsid;
                $requestData.teacherIds = tId;
                $requestData.teacherNames = tName;
                $requestData.schoolIds = schoolIds;
                if (receiverType == "pj.dept") {
                    if (departmentsid == "" || departmentsid == null) {
                        $.alert("请选择接收信息的部门！");
                        return;
                    }
                } else if (receiverType == 'pj.person') {
                    if (tId == "" || tId == null) {
                        $.alert("请选择接收信息的人员！");
                        return;
                    }
                } else if (receiverType = 'pj.school') {
                    if (schoolIds == "" || schoolIds == null) {
                        $.alert("请选择接收信息的学校！");
                        return;
                    }
                }

                var url = "${pageContext.request.contextPath}/office/notice/addNotice";
                if ("" != $id) {
                    $requestData._method = "put";
                    url = "${pageContext.request.contextPath}/office/notice/addNotice/" + $id;
                }
                loader.show();
                $.post(url, $requestData, function (data, status) {
                    if ("success" === status) {

                        data = eval("(" + data + ")");
                        if ("success" === data.info) {
                            $.success('操作成功');
                            window.location.href = document.referrer;
                        } else {
                            $.error("操作失败");
                        }
                    } else {
                        $.error("操作失败");
                    }
                    loader.close();
                });
           // }
        }
    </script>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="公告" name="title"/>
        <jsp:param value="${param.dm}" name="menuId"/>
    </jsp:include>
    <!-- 香市一小学校在数据库中对应为为215,应需求写死 -->
    <c:set var="schoolId" value="${sessionScope[sca:currentUserKey()].schoolId}"/>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="oa_top">
                    <a href="javascript:void(0)" onclick="back();" class="back right"><i
                            class="fa fa-arrow-circle-left"></i>返回</a></h3>
                    <ul class="top_ul">
                        <li><a href="javascript:void(0)" class="on">发通知</a></li>
                    </ul>
                </div>
                <div class="yc_sq">
                    <form class="form-horizontal" id="notice_form" action="javascript:void(0);">
                        <div class="control-group">
                            <label class="control-label">标题<span style="color:red">*</span>：</label>
                            <div class="controls">
                                <input type="text" name="title" class="span8 left_red {required : true,maxlength:40}"
                                       placeholder="请输入标题，少于40个中文字符" value="${notice.title}"/>
                            </div>
                        </div>

                        <div class="control-group content">
                            <label class="control-label">正文<span style="color:red;">*</span>：</label>
                            <div class="textarea controls">
                                <textarea id="content"
                                          style="width:90%;height:250px;">${notice.content}</textarea>

                                <label generated="true" class="myerror" style="display:none">长度小于4</label>

                                <span class="word_surplus"></span>
                            </div>
                        </div>
                        <div class="control-group">
                            <div id="release_picture" style="margin-left: 180px;float: none;width: initial;">
                                <ul>

                                </ul>
                                <div class="clear"></div>
                            </div>
                            <label class="control-label">附件：</label>
                            <div class="controls">
                                <div id="fj" style="display:inline-block;">
                                    <input type="hidden" id="fjId" value="${notice.uuid}"/>
                                    <input type="hidden" id="uploaderFile" name="uploaderFile">
                                    <span id="tp_queue"></span>
                                    <div class="clear"></div>
                                </div>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" style="height:45px;line-height:45px;">发布对象：</label>
                            <input type="hidden" value="${empty notice.receiverType ? 0 : notice.receiverType}"
                                   id="nowCheck" name="receiverType"/>
                            <div class="controls" style="padding-top:5px;">

<%--                                <input type="radio" name="daike" id="all" style="margin-left:0;"--%>
<%--                                       <c:if test="${notice.receiverType eq 'pj.school' || empty notice}">checked="checked"</c:if>/>学校--%>
<%--                                <div class="drop_whole" id="manyschool">--%>

<%--                                </div>--%>
<%--                                <select id="schoolId" style="width:150px;" name="schoolId">--%>

<%--                                </select>--%>
                                <input type="hidden" name="schoolId" value="${schoolId}"/>

                                <input type="radio" name="daike" id="department" style=""
                                       <c:if test="${notice.receiverType eq 'pj.dept' }">checked="checked"</c:if>/>
                                <div style="display:inline-block;max-width:450px;">
                                    <span style="">部门：</span>
                                    <select id="deptId" name="deptId">

                                    </select>
<%--                                    <select id="school_selected" style="width:150px;"></select>--%>
<%--                                    <div id="dept_seleted_id"></div>--%>
<%--                                    <div class="department" id="departments"--%>
<%--                                         style="position:relative;left:-223px;margin:15px 0 0 15px;">--%>
<%--                                        <ul></ul>--%>
<%--                                    </div>--%>
<%--                                    <input type="hidden" value="${depId}" id="depId"/>--%>
<%--                                    <input type="hidden" value="${depName}" id="depName"/>--%>
                                </div>


                                <div style="display:inline-block;">
<%--                                    <input type="radio" name="daike" id="person"--%>
<%--                                           <c:if test="${notice.receiverType eq 'pj.person'}">checked="checked"</c:if>/>个人--%>
<%--                                    <input type="button" id="member_selector" value="">--%>
<%--                                    <input type="hidden" id="teaId" value="${tIds}"/>--%>
<%--                                    <input id="teachName" type="hidden" value="${tNames}">--%>
<%--                                    <div id="teach" class="department staff"--%>
<%--                                         style="position:relative;left:4px;margin:15px 0 0 15px;">--%>
<%--                                        <ul></ul>--%>
<%--                                    </div>--%>
                                </div>
                                <!--                                      <button class="btn btn-danger" onclick="cleanTeacher();">清除</button> -->
                                <div class="s_select" style="position:relative;display:inline-block;">
                                </div>
                            </div>
                        </div>

                        <div class="caozuo" style="text-align:center;">
                            <input type="hidden" id="id" value="${notice.id}"/>
                            <button class="btn btn-success" type="button" onclick="saveOrUpdate();">发布</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>