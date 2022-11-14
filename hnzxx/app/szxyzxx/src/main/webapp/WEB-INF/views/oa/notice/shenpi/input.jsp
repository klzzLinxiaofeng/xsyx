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
                                       placeholder="请输入标题，少于40个中文字符" value="${notices.title}"/>
                            </div>
                        </div>

                        <div class="control-group content">
                            <label class="control-label">正文<span style="color:red;">*</span>：</label>
                            <div class="textarea controls">
                                <textarea id="content"
                                          style="width:90%;height:250px;">${notices.content}</textarea>

                                <label generated="true" class="myerror" style="display:none">长度小于4</label>

                                <span class="word_surplus"></span>
                            </div>
                        </div>

                        <div class="caozuo" style="text-align:center;">
                            <input type="hidden" id="id" value="${notices.id}"/>
                            <button class="btn btn-success" type="button" onclick="saveOrUpdate();">修改</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var editor;
    var ids = "";
    var str = "";
    var i = 0;
    var depts = new Array();
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

    function saveOrUpdate() {

        var $requestData = formData2JSONObj("#notice_form");
        $requestData.uuids = $("#pictureUuid").val();
        $requestData.uploadFile = $("#fjId").val();
        $requestData.content = editor.html();
        $requestData.id=$("#id").val();
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

       var url = "${pageContext.request.contextPath}/office/notice/updateNotice";
        loader.show();
        $.post(url, $requestData, function (d) {
                if ("success" ==d) {
                    $.success('操作成功');
                    window.location.href = document.referrer;
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



        $("[name=targetTypes]").click(function(){
            onTypeSelect($(this));
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
</body>
</html>