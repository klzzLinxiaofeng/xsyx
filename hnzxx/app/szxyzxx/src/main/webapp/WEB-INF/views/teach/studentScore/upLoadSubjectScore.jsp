<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/views/embedded/common.jsp"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/res/plugin/falgun/js/bootstrap-fileupload.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/common/jquery/ajaxfileupload.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/css/layui.css" media="all">
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/layui.all.js"></script>
    <title>${sca:getDefaultSchoolName()}</title>
    <style type="text/css">
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid ">

    </div>
    <div class="row-fluid ">
        <div class="span12">
            <div class="content-widgets white" style="margin-bottom: 0;">
                <div class="widget-head">
                    <h3>
                        批量导入学科素养成绩数据
                        <p style="float: right;" class="btn_link">
                            <a class="a4" href="javascript:void(0)" onclick="getBack();"><i
                                    class="fa fa-mail-reply"></i>返回列表</a>
                        </p>
                    </h3>
                </div>
            </div>
            <div class="stepy-widget">
                <div class="widget-head clearfix bondi-blue">
                    <div id="stepy_tabby1">
                        <ul id="stepy_form-titles" class="stepy-titles">
                        </ul>
                    </div>
                    <button href="javascript:void(0)" onclick="saveUploadInformation();" id="saveButton" class="btn btn-warning finish" disabled="disabled"  style="position:absolute;right:25px;top:11px;">退出</button>
                </div>

                <div class="widget-container gray ">
                    <div class="form-container">
                        <form id="uploadForm"
                              name="uploadForm"
                              method="post"
                              enctype="multipart/form-data"
                              class="form-horizontal left-align form-well"
                              novalidate="novalidate">

                            <fieldset title="第1步">
                                <legend style="display: none;">上传文件导入</legend>
                                <div class="control-group">
                                    <div class="controls" style="margin-left:0;">
                                        <div class="fileupload fileupload-new" data-provides="fileupload">

                                            <div class="input-append">
                                                <div class="control-group">
                                                    <label class="control-label">
                                                        学年：
                                                    </label>
                                                    <div class="controls">
                                                        <select id="xn" name="xn" class="span4"
                                                                style="width:200px;" value="${literacyVo.xn}">

                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="control-group">
                                                    <label class="control-label">
                                                        年级：
                                                    </label>
                                                    <div class="controls">
                                                        <select id="nj" name="gradeId" class="span4"
                                                                style="width:200px;" value="${literacyVo.gradeId}">
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="control-group">
                                                    <label class="control-label">
                                                        科目：
                                                    </label>
                                                    <div class="controls">
                                                        <select id="km" name="subjectId" class="span4"
                                                                style="width:200px;" value="${literacyVo.subjectId}">
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="uneditable-input span3">
                                                    <i class="icon-file fileupload-exists"></i><span class="fileupload-preview"></span>
                                                </div>

                                                <span class="btn btn-file">
	                                                <span class="fileupload-new" onclick="" id="upFile">请选择上传文件</span>
<%--	                                                <span class="fileupload-exists">重新选择</span>--%>
<%--	                                                <input type="file" id="fileUpload" name="fileUpload"  accept=".xls"  onchange="fileOnchange();"/>--%>
                                                </span>
                                            </div>
                                            <span style="color:#009A00">
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                            <fieldset title="第2步">
                                <legend style="display: none;">查看导入结果</legend>
                                <div class="control-group">
                                    <div class="select_success">
                                        <ul>
                                            <li><a href="javascript:void(0)"><i class="fa  fa fa-comments"></i>成功</a></li>
                                            <li class="on"><a href="javascript:void(0)"><i class="fa  fa-envelope-o"></i>失败</a></li>
                                        </ul>
                                    </div>
                                    <div class="select_table">
                                        <table class="table table-bordered responsive" style="display:none">
                                            <thead id="success">
                                            </thead>
                                            <tbody id="successResultId">
                                            </tbody>
                                        </table>
                                        <table class="table table-bordered responsive" >
                                            <thead id="error">

                                            </thead>
                                            <tbody id="failResultId">
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </fieldset>
                            <button href="javascript:void(0)" class="btn btn-warning finish" style="display:none;">保存</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(function(){
        $('#uploadForm').stepy({
            backLabel: '上一步',
            nextLabel: '下一步',
//             backLabel: 'Back',
//             nextLabel: 'Next',
            block: true,
            description: true,
            legend: false,
            titleClick: true,
            titleTarget: '#stepy_tabby1'
        });
        //初始填充学年、学期、年级
        addOption('/teach/schoolYear/list/json', "xn", "year", "name", function (d) {
            if (d.length > 0) {
                $("#nj").html('<option value="">请选择</option>');
                //因查询年级不需学期，所以不需在学期填充后的回调中执行
                addOption('/teach/grade/list/json?schoolYear=' + d[0].year, "nj", "id", "name");
            }
        })
    });

    //绑定下拉框改变事件
    $("#xn").change(function(){
        //添加年级
        $("#nj").html('<option value="">请选择</option>');
        addOption('/teach/grade/list/json?schoolYear='+$(this).val(), "nj", "id", "name")
        $("#km").html('<option value="">请选择</option>');
    })
    $("#nj").change(function(){
        //$("#bj").html('<option value="">请选择</option>');
        $("#km").html('<option value="">请选择</option>');
        //添加科目
        addOption("/literacy/findExamSubject?nj="+$("#nj").val(), "km", "subjectId", "subjectName")

    })

    function addOption(url, id, valProperty, namePropety, callback) {
        $.get(url, function (d) {
            d = JSON.parse(d);
            for (var i = 0; i < d.length; i++) {
                var obj = d[i];
                $("#" + id).append("<option   value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");


            }
            if (callback != null && callback != undefined) {
                callback(d);
            }
        })
    }

    layui.use('upload', function(){
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#upFile' //绑定元素
            ,url: '${pageContext.request.contextPath}/teach/studentScore/upLoadSubjectScore' //上传接口
            ,acceptMime:'application/vnd.ms-excel'
            ,exts:'xls'
            ,field:"fileUpload",
            data:{
               gradeId:$("#nj").val(),
               subjectId: $("#km").val()}
            ,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                this.data={"gradeId": $("#nj").val(),"subjectId":$("#km").val()}

                layer.load(); //上传loading
            }
            ,done: function(map){
                layer.closeAll('loading'); //关闭loading
                var resultStatus = map['status'];
                var errorList =  map['error'];
                var successList =  map['success'];
                var biaotou =  map['biaotou'];
                if(resultStatus=="success"){
                    $.success("导入完成");
                    loadTable(successList,errorList,biaotou);
                    $("#uploadForm-title-1").click();
                }else{
                    layer.alert("导入失败");
                }
            }
            ,error: function(){
                layer.closeAll('loading'); //关闭loading
                layer.alert("导入失败");
            }
        });
    });

    $(function(){
        $(".select_success li a").click(function(){
            $(".select_success li a").parent().removeClass("on");
            $(this).parent().addClass("on");
            var i=$(this).parent().index();
            $(".select_table table").hide();
            $(".select_table table").eq(i).show()
        })
        //获取角色 JSON 数据  1为教师 2为管理员 4为学生的下拉列表3为家长
        $.RoleSelector({
            "condition" : {
                "userType" : "3"
            }
        });



        /* $("#uploadForm-title-2").click(function(){
            loadTable(successList,errorList);
        }); */



    });



    function ajaxFileUpload(){
        var fileUpload = $("#fileUpload").val();
        $.ajaxFileUpload({
            //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
            url : "${pageContext.request.contextPath}/teach/student/upLoadStudentInfo",
            secureuri : false, //是否启用安全提交,默认为false
            fileElementId : 'fileUpload', //文件选择框的id属性
            dataType : 'text', //服务器返回的格式,可以是json或xml等
            success : function(data, status) { //服务器响应成功时的处理函数
                alert(data+"=="+status);
            },
            error : function(data, status, e) { //服务器响应失败时的处理函数
                alert(data+"==="+status+"=="+e);
            }
        });
    }
    function loadTable(listSuccess,listFail,biaotou){
        var biaotouLength=biaotou.length;
       // layer.alert(biaotouLength+"++++"+listSuccess.length+"----"+listFail.length);
        var changdu=parseInt(biaotouLength)+2;
        document.getElementById("success").innerHTML = "";
        $("#success").append("<tr id='biaotou'><th>学生程序标识(不可修改)</th><th>姓名</th></tr>")
        for(var e=0;e<biaotou.length;e++){
            $("#biaotou").append("<td>"+biaotou[e].literacyName+"</td>");
        }
        $("#biaotou").append("<td>结果</td>");
        document.getElementById("error").innerHTML = "";
        $("#error").append("<tr id='biaotou2'><th>学生程序标识(不可修改)</th><th>姓名</th></tr>")
        for(var e=0;e<biaotou.length;e++){
            $("#biaotou2").append("<td>"+biaotou[e].literacyName+"</td>");
        }
        $("#biaotou2").append("<td>结果</td>");

        /*var parSucc = "";
        var parFail = "";*/
        if (listSuccess.length == 0) {
            $("#successResultId").html("<tr><td colspan='"+changdu+"'>暂无成功成绩信息</td></tr>");

        }else{
            for (var i = 0, len = listSuccess.length; i < len; i++) {
                $("#successResultId").append("<tr id='data"+i+"'><td>"+listSuccess[i]['studentId']+"</td><td>"+listSuccess[i]['stuName']+"</td>");
                for(var e=0;e<biaotou.length;e++){
                    $("#data"+i).append("<td>"+listSuccess[i]['data'+e]+"</td>");
                }
                $("#data"+i).append("<td>"+listSuccess[i]['success']+"</td>")
                //parSucc+="<tr id='data"+i+"'><td>"+listSuccess[i]['studentId']+"</td><td>"+listSuccess[i]['stuName']+"</td><td>"+listSuccess[i].studentName+"</td><td>"+listSuccess[i].score+"</td><td>"+'成功'+"</td></tr>";
            }
           // $("#successResultId").html(parSucc);
        }

        if(listFail.length == 0){
            $("#saveButton").prop("disabled",false);
            $("#failResultId").html("<tr><td colspan='"+changdu+"'>暂无失败成绩信息</td></tr>");
        }else{
            $("#saveButton").prop("disabled",true);
            for(var k = 0, lenTemp = listFail.length; k < lenTemp; k++){
                $("#failResultId").append("<tr id='data2"+i+"'><td>"+listFail[k]['studentId']+"</td><td>"+listFail[k]['stuName']+"</td>");
                for(var e=0;e<biaotou.length;e++){
                    $("#data2"+i).append("<td>"+listFail[k]['data'+e]+"</td>");
                }
                $("#data2"+i).append("<td>"+listFail[k]['error']+"</td>")
            }
/*
                parFail+="<tr><td>"+listFail[k].studentId+"</td><td>"+listFail[k].studentNumber+"</td><td>"+listFail[k].studentName+"</td><td>"+listFail[k].score+"</td><td>"+listFail[k].errorInfo+"</td></tr>";
            }
            $("#failResultId").html(parFail);*/
        }
    }
    //退出
    function saveUploadInformation(){
        $.closeWindow();
    }

    //返回列表
    function getBack() {
        if(parent.core_iframe != null) {
            parent.core_iframe.window.location.reload();
        } else {
            parent.window.location.reload();
        }
        $.closeWindow();
    }

</script>
</body>
</html>
