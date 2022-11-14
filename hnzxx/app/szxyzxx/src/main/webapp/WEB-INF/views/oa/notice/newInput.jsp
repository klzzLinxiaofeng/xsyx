<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity" %>

    <%@ include file="/views/embedded/common.jsp" %>
    <%--<%@ include file="/views/embedded/plugin/uploadify.jsp" %>--%>
    <link rel="stylesheet" type="text/css" href="${ctp}/res/css/uploadifive/uploadifive.css">
    <script src="${ctp}/res/js/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>
    <%@include file="/views/embedded/plugin/dept_selector_js.jsp" %>
    <%@include file="/views/embedded/plugin/ryxx_selector_js.jsp"%>
    <link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
    <title>文印</title>
    <script src="${pageContext.request.contextPath}/res/js/common/plugin/editor/kindeditor-min.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/extra/add.js"></script>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
    <script type="text/javascript">
        var editor;
        var ids = "";
        var str = "";
        var i = 0;
        var depts = new Array();
        var isSchoolOperator=${isSchoolOperator};
        var schoolId=${sessionScope[sca:currentUserKey()].schoolId};
        var teamIsLoad=false;
        var deptIsLoad=false;
        var sendDeptIsLoad=false;

        function back(){
            history.back(-1);
        }

        function onTypeSelect(obj){
            var val=obj.val();
            var checked=obj[0].checked;
            if(checked) {
                if (val == "pj.allTeacher") {
                    $("#dept,#teacher").attr("disabled", true);
                    $("#dept,#teacher").prop("checked", false);
                }else if(val == "pj.dept"){
                    $("#noticeTarget_dept").show();
                    getDept();
                }else if(val == "pj.team"){
                    getTeam();
                    $("#team").prop("checked", true);
                }else if(val == "pj.person"){
                    selectTeacher();
                    $("#noticeTarget_person").show();
                }
            }else{
                if(val == "pj.dept"){
                    $("#noticeTarget_dept").hide();
                }else if(val == "pj.person"){
                    $("#noticeTarget_person").hide();
                }

            }
            if($("[name=targetTypes]:checked").length==0 || $("#allStudent")[0].checked){
                $("#sfhzDiv").hide();
            }else{
                $("#sfhzDiv").show();
            }

            if(!$("#allTeacher")[0].checked){
                $("#dept,#teacher").attr("disabled",false);
            }
        }

        function selectTeacher() {

            $("#noticeTarget_person").html('&nbsp;&nbsp;<button class="btn btn-blue"  id="selectTeacher" type="button" >新增</button>' +
                '<input type="hidden" id="teacherId" name="teacherIds"/>' +
                '<span id="teacherName"></span>');

            $.createMemberSelector({
                "inputIdSelector": "#selectTeacher",
                //"isOnTopWindow": true,
                "enableBatch": true
            });

        }

        function getTeam() {
            if(teamIsLoad){
                return;
            }
            $("#noticeTarget_team").html("<span>班级：</span> <select id='teamId' name='teamId'></select>")
            $("#teamId").html("");

            var teamIdsStr = "${teamIds}".replace("[","").replace("]","").trim()
            if(teamIdsStr.length>0){
                $.get("${ctp}/teach/team/getByIds?ids="+teamIdsStr, function(
                    data, status) {
                    if ("success" === status) {
                        teamIsLoad=true;
                        data = eval("(" + data + ")");
                        $.each(data, function(index, value) {
                            $("#teamId").append("<option value='"+value.id+"'>"+value.name+"</option>");
                        });
                    }
                });


            }

            $("#teamId").append("<option value='111'>测试班级</option>");
        }


        function getSendDept() {
            if(sendDeptIsLoad){
                return;
            }
            var depHtml="";
            $.get("${ctp}/teach/dept/getMyDept", function(
                data, status) {
                if ("success" === status) {
                    sendDeptIsLoad=true;
                    data = eval("(" + data + ")");
                    $.each(data, function(index, value) {
                        depHtml+='<option value="'+value.name+'">'+value.name+'</option>';
                    });
                    $("#fqbm").html(depHtml);
                }
            });


        }


        //获取部门
        function getDept(){
            if(deptIsLoad){
                return;
            }
            var depHtml="&nbsp;&nbsp;&nbsp;&nbsp;<span>部门：</span>"

            $.get("${ctp}/teach/dept/getBySchoolId?schoolId="+schoolId, function(
                data, status) {
                if ("success" === status) {
                    deptIsLoad=true;
                    data = eval("(" + data + ")");
                    $.each(data, function(index, value) {
                        depHtml+="&nbsp;&nbsp;&nbsp;&nbsp;<span><a style='color: black;' href='javascript:void(0)' onclick='selectCheck(this)' >"+value.name+"</a>"+"<input class='depIdCheckbox' type='checkbox' value='"+value.id+"'/><span>";
                    });
                    $("#noticeTarget_dept").html(depHtml);
                }
            });
        }


        var teacherId;
        //已选择教师
        function selectedHandler(data) {
            
            if(typeof (data.ids) == "object"){
                $.each(data.ids, function(index, value) {
                    teacherId = value;
                    teachName = data.names[index];
                });
            }else{
                teacherId=data.ids;
                teachName=data.names;
            }

            $("#teacherId").val(teacherId);
            $("#teacherName").html(teachName);
            $("#selectTeacher_select").text("修改");
            $.success("设置成功");
            $.closeWindowByName(data.windowName);
        }

        function saveOrUpdate() {

            var $requestData = formData2JSONObj("#notice_form");
            $requestData.uuids = $("#pictureUuid").val();
            $requestData.uploadFile = $("#fjId").val();
            $requestData.content = editor.html();
            $requestData.textContent =editor.text().replace(/\s+/g,"");
            //$requestData.schoolIds =  $("#schoolIds").val();


            var loader = new loadLayer();

            if($requestData.content==""){
                $.error("请输入正文");
                return;
            }

            if($("input[name=title]").val()==""){
                $.error("请输入标题");
                return;
            }

            //目标类型
            if($("[name=targetTypes]:checked").length==0 ){
                $.error("请选择发送对象");
                return;
            }





            var depIdsArr=new Array();
            $.each($(".depIdCheckbox:checked"),function(ind,val){
                depIdsArr.push(val.value);
            })
            $requestData.deptIds=depIdsArr.join(",");

            if($requestData.targetTypes.indexOf('pj.dept')!=-1 && depIdsArr.length==0){
                $.error("请选择部门");
                return;
            }

            if($requestData.targetTypes.indexOf('pj.person')!=-1 && $requestData.teacherId==""){
                $.error("请选择老师");
                return;
            }

            if($requestData.targetTypes.indexOf('pj.team')!=-1 && $requestData.teamId==""){
                $.error("请选择班级");
                return;
            }

            //管理员通知类型固定为school
            if(isSchoolOperator){
                $requestData.receiverType="school";
            }

            if ($.trim(editor.text()).length < 4) {
                $(".form-horizontal .textarea label").show();
                return;
            }

            //var $id = $("#id").val();


            var url = "${pageContext.request.contextPath}/office/notice/addNotice";
            <%--if ("" != $id) {--%>
            <%--    $requestData._method = "put";--%>
            <%--    url = "${pageContext.request.contextPath}/office/notice/addNotice1/" + $id;--%>
            <%--}--%>
            loader.show();
            $.post(url, $requestData, function (data, status) {
                if ("success" === status) {

                    console.info(data);
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



        }


        function initValidator() {
            return $("#notice_form").validate({
                errorClass: "myerror",
                rules: {},
                messages: {}
            });
        }


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


        function selectCheck(obj){
            var jobj=$(obj).parent().children("input");
            var checked=jobj[0].checked;
            var diabled=jobj[0].disabled;
            if(!diabled) {
                jobj.prop("checked", !checked)
            }

        }

        function typeClick(obj){
            selectCheck(obj);
            onTypeSelect($(obj).parent().children("input"));
        }




        function rmFile(id){
            $("."+id).remove();
            var reg="/"+id+",/"
            str=str.replace(new RegExp(id+",",'g'),"");
        }


        $(function () {



            if(isSchoolOperator){
                sendTypeChanged("school");
                $("#tzlxDiv").hide();
            }

            $("[name=targetTypes]").click(function(){
                onTypeSelect($(this));
            })


            uploadFile();
            function uploadFile() {
                $('#uploader').uploadifive({
                    'auto': true,
                    'fileObjName' : 'file',
                    //'queueID': 'queue',
                    'buttonText': '上传附件',
                    removeCompleted:true,
                    formData: {
                        'jsessionId': '<%=request.getSession().getId()%>'
                    },
                    'uploadScript': '/uploader/common',
                    'onUploadComplete': function (file,data) {
                        var $jsonObj = eval("(" + data + ")");
                        var img = '<div id="div_'+$jsonObj.uuid+'"><input type="text" style="display:none;" name="fujianUuid" class="fujianUuid" value="'+$jsonObj.uuid+'" /><a href="'+$jsonObj.url+'">'+$jsonObj.realFileName+'</a><button onclick="chehui(div_'+$jsonObj.url+')" type="button">撤销</button></div>';
                        $("#pictureUuid").val($jsonObj.uuid);

                        $("#zpa").append(img);
                    },
                    onUpload:function (file) { //上传开始时触发（每个文件触发一次）
                        $("#infoBox").prev("p").css("display", "none");
                        $("#infoBox").css("display", "block");
                    },
                    onFallback : function() {
                        alert("该浏览器无法使用!");
                    },
                });
            }

            //文档上传
            function chehui(id) {
                $("#"+id).remove();
            }

            setNoticeTarget()
            $("#targetType").change(function () {
                $("#noticeTarget").html("")
                setNoticeTarget()
                var type=$(this).val();


            })




            function setNoticeTarget(){
                
                var targetTypeVal = $("#targetType option:selected").val();

                if(targetTypeVal != null && targetTypeVal != undefined){
                    if(targetTypeVal == "dept"){
                        getDept();
                    }else if(targetTypeVal == "team"){
                        getTeam();
                    }else if(targetTypeVal == "teacher"){
                        selectTeacher();
                    }
                }
            }




        })

        function sendTypeChanged(type){

            $(".targetDiv").hide();
            $(".targetDiv").children("input").prop("checked", false);
            if(type=="school"){
                $("#lsxsDiv,#bmDiv,#grDiv").show();
            }else if(type=="person"){
                $("#bmDiv,#grDiv").show();
                $("#lsxsDiv  input,#bjDiv  input").prop("checked", false);
            }else if(type=="dept"){
                $("#deptSpan").hide();
                $("#dept").prop("checked", false);
                $("#dept").click();
                $("#fqbmDiv,#bmDiv").show();
                $("#lsxsDiv input,#grDiv input,#bjDiv input").prop("checked", false);
                getSendDept();
            }else if(type=="team"){
                getTeam();
                $("#team").prop("checked", true);
                $("#bjDiv").show();
                $("#lsxsDiv input,#grDiv input,#bmDiv input").prop("checked", false);
            }

            if(type!="dept"){
                $("#deptSpan").show();
                $("#fqbmDiv").hide();
                $("#dept").prop("checked", true);
                $("#dept").click();
            }

        }

        function changeSendType(obj){
            var val=$(obj).val();
            sendTypeChanged(val);
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
                            <label class="control-label">
                                附件：
                            </label>
                            <div class="controls">
                                <c:if test="${isCK==''||isCK==null}">
                                    <input type="hidden" id="uploader" name="uploader">
                                </c:if>
                                <span id="tp_queue"></span>
                                <div id="zpa" style="display:inline-block;">
                                    <c:forEach items="${huiyi.fujian}" var="item" varStatus="i">
                                        <div id="div_${item['uuid']}">
                                            <input type="text" style="display:none;" name="fujianUuid" class="fujianUuid" value="${item['uuid']}" />
                                            <a href="${item['url']}">${item['fileName']}</a>
                                            <button onclick="chehui('div_${item['uuid']}')" type="button">撤销</button>
                                        </div>
                                    </c:forEach>
                                    <input type="hidden" id="pictureUuid"/>
                                </div>

                            </div>
                        </div>


                        <div class="control-group" id="tzlxDiv">
                            <label class="control-label">通知类型：</label>
                            <div class="controls">
                               <select name="receiverType" onchange="changeSendType(this)">
<%--                                   <option value="school">admin</option>--%>
                                   <option value="person">个人</option>
                                   <option value="dept">部门</option>
                                    <c:if test="${not empty teamIds}">
                                        <option value="team">班级</option>
                                    </c:if>
                               </select>
                            </div>
                        </div>


                        <div class="control-group" style="display: none" id="fqbmDiv">
                            <label class="control-label">发起部门：</label>
                            <div class="controls">
                                <select id="fqbm" name="appKey">

                                </select>
                            </div>
                        </div>


                        <div class="control-group">
                            <label class="control-label" style="height:45px;line-height:45px;">发布对象：</label>

                            <div class="controls" style="padding-top:15px;">

                                    <div id="lsxsDiv" class="targetDiv" style="display: none;margin-bottom: 20px;">
                                        <span>
                                            <a href="javascript:void(0);" onclick="typeClick(this)">所有教师</a>
                                            <input type="checkbox" name="targetTypes" id="allTeacher" value="pj.allTeacher">
                                        </span>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        <span>
                                            <a href="javascript:void(0);" onclick="typeClick(this)">所有学生</a>
                                            <input type="checkbox" name="targetTypes" id="allStudent" value="pj.allStudent">
                                        </span>
                                    </div>

                                    <div style="margin-bottom: 20px;" class="targetDiv" id="bmDiv">
                                        <span id="deptSpan">
                                            <a href="javascript:void(0);" onclick="typeClick(this)">部门</a>
                                            <input type="checkbox" name="targetTypes" id="dept" value="pj.dept">
                                        </span>
                                        <div style="display:inline-block;max-width:100%;" id="noticeTarget_dept">

                                        </div>
                                    </div>


                                    <div style="margin-bottom: 20px;" class="targetDiv" id="grDiv">
                                        <span>
                                            <a href="javascript:void(0);" onclick="typeClick(this)">个人</a>
                                            <input type="checkbox" name="targetTypes" id="teacher" value="pj.person">
                                        </span>
                                        <div style="display:inline-block;max-width:800px;" id="noticeTarget_person">

                                        </div>
                                    </div>

                                <div style="margin-bottom: 20px;" class="targetDiv" id="bjDiv">
                                    <input style="display: none" type="checkbox" name="targetTypes" id="team" value="pj.team">
                                    <div style="display:inline-block;max-width:800px;" id="noticeTarget_team">

                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="control-group" id="sfhzDiv" style="display: none">
                            <label class="control-label" style="height:45px;line-height:45px;">需要回执：</label>
                            <div class="controls" style="padding-top:15px;">
                                <input type="checkbox"  name="sfhz" value="true">
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