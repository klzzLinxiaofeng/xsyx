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
                        批量导入成绩数据
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
                                                <div class="uneditable-input span3">
                                                    <i class="icon-file fileupload-exists"></i><span class="fileupload-preview"></span>
                                                </div>

                                                <span class="btn btn-file">
	                                                <span class="fileupload-new" id="upFile">请选择上传文件</span>
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
                                            <thead>
                                            <tr>
                                                <th>作业标识</th>
                                                <th>学生标识</th>
                                                <th>学生姓名</th>
                                                <th>学生状态</th>
                                                <th>评分</th>
                                                <th>评价</th>
                                                <th>是否成功</th>
                                            </tr>
                                            </thead>
                                            <tbody id="successResultId">
                                            </tbody>
                                        </table>
                                        <table class="table table-bordered responsive" >
                                            <thead>
                                            <tr>
                                                <th>作业标识</th>
                                                <th>学生标识</th>
                                                <th>学生姓名</th>
                                                <th>学生状态</th>
                                                <th>评分</th>
                                                <th>评价</th>
                                                <th>失败原因</th>
                                            </tr>
                                            </thead>
                                            <tbody id="failResultId">
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </fieldset>
                            <button href="javascript:void(0)" class="btn btn-warning finish" style="display:none;"></button>
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
    });


    layui.use('upload', function(){
        var upload = layui.upload;

        //执行实例
        var uploadInst = upload.render({
            elem: '#upFile' //绑定元素
            ,url: '${pageContext.request.contextPath}/student/home/woke/upLoadScoreInfo' //上传接口
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




    });



    function loadTable(listSuccess,listFail){
        var parSucc = "";
        var parFail = "";
        if (listSuccess.length == 0) {
            $("#successResultId").html("<tr><td colspan='4'>暂无成功成绩信息</td></tr>");

        }else{
            for (var i = 0, len = listSuccess.length; i < len; i++) {
                parSucc+="<tr><td>"+listSuccess[i].jobId+"</td><td>"+listSuccess[i].studentId+"</td><td>"+listSuccess[i].studentName+"</td><td>"+listSuccess[i].zhuangzhongwen+"</td><td>"+listSuccess[i].fenzhi+"</td><td>"+listSuccess[i].pingyu+"</td><td>"+'成功'+"</td></tr>";
            }
            $("#successResultId").html(parSucc);
        }

        if(listFail.length == 0){
            $("#saveButton").prop("disabled",false);
            $("#failResultId").html("<tr><td colspan='4'>暂无失败成绩信息</td></tr>");
        }else{
            $("#saveButton").prop("disabled",true);
            for(var k = 0, lenTemp = listFail.length; k < lenTemp; k++){
                parFail+="<tr><td>"+listSuccess[i].jobId+"</td><td>"+listSuccess[i].studentId+"</td><td>"+listSuccess[i].studentName+"</td><td>"+listSuccess[i].zhuangzhongwen+"</td><td>"+listSuccess[i].fenzhi+"</td><td>"+listSuccess[i].pingyu+"</td><td>"+listFail[k].errorInfo+"</td></tr>";
            }
            $("#failResultId").html(parFail);
        }
    }

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
