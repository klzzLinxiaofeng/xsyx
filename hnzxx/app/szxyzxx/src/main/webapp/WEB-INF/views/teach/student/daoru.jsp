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
                        批量导入学生信息
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

                                                <div class="uneditable-input span3">
                                                    <i class="icon-file fileupload-exists"></i><span class="fileupload-preview"></span>
                                                </div>

                                                <span class="btn btn-file">
	                                                <span class="fileupload-new" onclick="" id="upFile">请选择上传文件</span>
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
                                            <th>学年</th>
                                            <th>年级id</th>
                                            <th>年级名称</th>
                                            <th>学生id</th>
                                            <th>学生姓名</th>
                                            <th>班级号</th>
                                            <th>新学号</th>
                                            <th>结果</th>
                                            </thead>
                                            <tbody id="successResultId">
                                            </tbody>
                                        </table>
                                        <table class="table table-bordered responsive" >
                                            <thead id="error">
                                            <th>学年</th>
                                            <th>年级id</th>
                                            <th>年级名称</th>
                                            <th>学生id</th>
                                            <th>学生姓名</th>
                                            <th>班级号</th>
                                            <th>新学号</th>
                                            <th>结果</th>
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

    });

    layui.use('upload', function(){
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#upFile' //绑定元素
            ,url: '${pageContext.request.contextPath}/teach/student/upLoadStudentXiuGai' //上传接口
            ,acceptMime:'application/vnd.ms-excel'
            ,exts:'xls'
            ,field:"fileUpload"
            ,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                layer.load(); //上传loading
            }
            ,done: function(map){
                layer.closeAll('loading'); //关闭loading
                var resultStatus = map['status'];
                var errorList =  map['error'];
                var successList =  map['success'];
                if(resultStatus=="success"){
                    $.success("导入完成");
                    loadTable(successList,errorList);
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
    function loadTable(listSuccess,listFail){
        if (listSuccess.length == 0) {
            $("#successResultId").html("<tr><td colspan='10'>暂无成功成绩信息</td></tr>");
        }else{
            for (var i = 0, len = listSuccess.length; i < len; i++) {
                $("#successResultId").append("<tr id='data"+i+"'>" +
                    "<td>"+listSuccess[i]['schoolYear']+"</td>" +
                    "<td>"+listSuccess[i]['gradeId']+"</td>" +
                    "<td>"+listSuccess[i]['gradeName']+"</td>" +
                    "<td>"+listSuccess[i]['stuId']+"</td>" +
                    "<td>"+listSuccess[i]['stuName']+"</td>" +
                    "<td>"+listSuccess[i]['teamId']+"</td>" +
                    "<td>"+listSuccess[i]['newNumber']+"</td>" +
                    "<td>"+listSuccess[i]['message']+"</td></tr>");
            }
        }

        if(listFail.length == 0){
            $("#saveButton").prop("disabled",false);
            $("#failResultId").html("<tr><td colspan='10'>暂无失败成绩信息</td></tr>");
        }else{
            $("#saveButton").prop("disabled",true);
            for(var k = 0, lenTemp = listFail.length; k < lenTemp; k++){
                $("#failResultId").append("<tr id='data2"+k+"'>" +
                    "<td>"+listFail[k]['schoolYear']+"</td>" +
                    "<td>"+listFail[k]['gradeId']+"</td>" +
                    "<td>"+listFail[k]['gradeName']+"</td>" +
                    "<td>"+listFail[k]['stuId']+"</td>" +
                    "<td>"+listFail[k]['stuName']+"</td>" +
                    "<td>"+listFail[k]['teamId']+"</td>" +
                    "<td>"+listFail[k]['newNumber']+"</td>" +
                    "<td>"+listFail[k]['message']+"</td></tr>");
            }
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
