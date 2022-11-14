<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <%--弃用uploadify插件改为uploadifive插件--%>
    <%--<%@ include file="/views/embedded/plugin/uploadify.jsp"%>--%>

    <link rel="stylesheet" type="text/css" href="${ctp}/res/css/uploadifive/uploadifive.css">
    <script src="${ctp}/res/js/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>
<%--    <%@include file="/views/embedded/plugin/dept_selector_js.jsp" %>
    <%@ include file="/views/embedded/plugin/zTree.jsp"%>--%>
    <%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
    <link href="${pageContext.request.contextPath}/res/css/extra/my.css" rel="stylesheet">
    <%-- <script src="${pageContext.request.contextPath}/res/plugin/falgun/js/bootstrap-datetimepicker.min.js"></script> --%>
    <script src="${pageContext.request.contextPath}/res/js/common/plugin/editor/kindeditor-min.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/extra/add.js"></script>
    <title>报修</title>
    <style type="text/css">
        .form-horizontal .controls #zpa .img_1 {
            float: left;
            margin: 10px;
            position: relative;
            top: 0;
            width: 233px;
            height: 130px;
        }
        .form-horizontal .controls #zpa .img_1 img {
            width: 233px;
            height: 130px;
        }
        .form-horizontal .controls #zpa .img_1 a {
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
        .form-horizontal .controls{
            margin-left: 100px;
        }
        .myerror {
            color: red !important;
            width: 22%;
            display: inline-block;
            padding-left: 10px;
        }
        .table tr th,.table tr td{text-align:center;}
    </style>
    <script type="text/javascript">
        $(function () {
            /*   $('#datetimepicker1').datetimepicker({
                  language: 'pt-BR'
              }); */

            //收起展开
            $(".x-collapse").click(function(){
                if($(this).hasClass("fold-on")){
                    $(this).prev().find(".x-up").hide();
                    $(this).text("展开↑").removeClass("fold-on");
                }
                else{
                    $(this).prev().find(".x-up").show();
                    $(this).text("收起↓").addClass("fold-on")
                }
            });
        });
    </script>
</head>
<body>
<div class="container-fluid">
    <%--<jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="报修" name="title" />
        <jsp:param value="${param.dm}" name="menuId" />
    </jsp:include>--%>
    <div class="row-fluid ">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                  <%--  <h3 class="x-head content-top"><a href="javascript:void(0);" class="on">申请报修</a><a href="javascript:history.go(-1);" class="back right"><i class="fa fa-arrow-circle-left"></i>返回</a></h3>--%>
                </div>
                <div class="space20"></div>
                <div class="x-main" >
                    <div class="clearfix x-mright">
                        <div class="list">
                            <form class="form-horizontal" id="applyrepair_form">
                                <div class="control-group">
                                    <label class="control-label">标题<span style="color:red">*</span></label>
                                    <div>
                                        <input type="text" name="title" value="${weixiuList.title}" disabled="disabled" class="span6 left-stripe {required : true,maxlength:40}">
                                    </div>
                                </div>
                                <div class="x-up">
                                    <div class="control-group">
                                        <label class="control-label">
                                            报修类型<span style="color:red">*</span>
                                        </label>
                                        <select id="typeId" name="typeId" disabled>

                                        </select>
                                        </select>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">
                                            审核人<span style="color:red">*</span>
                                        </label>
                                        <select id="shenheren" disabled name="we  <option value="">请选择</option>
                                        </select>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label"><font style="color: red">*</font>
                                            保修图片：
                                        </label>
                                        <div class="controls">
                                            <div>
                                                <input type="hidden" id="pictureUuid" name="pictureId" value="${weixiuList.pictureId}">
                                            </div>
                                            <div id="zpa" style="display:inline-block;">
                                                    <div class="img_1"  style="margin: 5px;">
                                                        <img style="width:233px;height:130px;" class="ims"  src="${weixiuList.pictureUrl}"/>&nbsp;&nbsp;&nbsp;
                                                    </div>
                                            </div>

                                        </div>
                                        <c:if test="${isCK==''||isCK==null}">
                                            <input type="hidden" id="uploader" name="uploader">
                                        </c:if>
                                        <span id="tp_queue"></span>

                                        <div class="clear"></div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">故障描述<span style="color:red">*</span></label>
                                        <div class="left">
                                            <div class="textarea">
                                                <textarea name="details" style="width:800px;height:200px;" disabled>${weixiuList.details}</textarea>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="control-group">
                                    <label class="control-label">维修地点<span style="color:red">*</span></label>
                                    <div>

                                        <input style="position:relative;top:-12px;" disabled type="text" id="place" name="place" value="${weixiuList.place}" placeholder="" class="span3 left-stripe {maxlength:20}">
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>
                    </p>
                    <input type="hidden" id="applyrepari_id" value="${weixiuList.id}"/>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        initSelect();
    })
    function initSelect() {
        //初始填充审核人，维修类型，
        addOption('/oa/applyrepair/findshenheren', "shenheren", "user_id", "name",${weixiuList.shenherenId})
        addOption('/oa/applyrepair/typeAll', "typeId", "id", "name",${weixiuList.typeId});

    }

    function addOption(url, id, valProperty, namePropety,addd, callback) {
        $.get(url, function (d) {
            d = JSON.parse(d);
            for (var i = 0; i < d.length; i++) {
                var obj = d[i];
                if(obj[valProperty]==addd){
                    $("#" + id).append("<option selected value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
                }else{
                    $("#" + id).append("<option value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");

                }
                 }
            if (callback != null && callback != undefined) {
                callback(d);
            }
        })
    }
</script>
</html>