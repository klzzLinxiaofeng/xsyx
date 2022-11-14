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
    <%@include file="/views/embedded/plugin/dept_selector_js.jsp" %>
    <%@ include file="/views/embedded/plugin/zTree.jsp"%>
    <%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
    <link href="${pageContext.request.contextPath}/res/css/extra/my.css" rel="stylesheet">
    <%-- <script src="${pageContext.request.contextPath}/res/plugin/falgun/js/bootstrap-datetimepicker.min.js"></script> --%>
    <script src="${pageContext.request.contextPath}/res/js/common/plugin/editor/kindeditor-min.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/extra/add.js"></script>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <title>会务详情</title>
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
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="会务详情" name="title" />
        <jsp:param value="${param.dm}" name="menuId" />
    </jsp:include>
    <div class="row-fluid ">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3 class="x-head content-top"><a href="javascript:void(0);" class="on">会务详情</a><a href="javascript:history.go(-1);" class="back right"><i class="fa fa-arrow-circle-left"></i>返回</a></h3>
                </div>
                <div class="space20"></div>
                <div class="x-main" >
                    <div class="clearfix x-mright">
                        <div class="list">
                            <form class="form-horizontal" id="applyrepair_form">
                                <div class="control-group">
                                    <label class="control-label">主题<span style="color:red">*</span></label>
                                    <div>
                                        <input type="text" readonly="readonly" id="theme" name="theme" value="${huiyi.theme}" placeholder="请输入标题，少于20个中文字符" class="span6 left-stripe {required : true,maxlength:40}"/>
                                    </div>
                                </div>
                                <div class="x-up">
                                    <div class="control-group">
                                        <label class="control-label">
                                            申请部门<span style="color:red">*</span>
                                        </label>
                                        <select  disabled="disabled" id="departmentId" name="departmentId">

                                        </select>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">
                                            申请人<span style="color:red">*</span>
                                        </label>
                                        <c:if test="${user!=null}">
                                            <input type="text" id="applicant" style="display: none"  disabled  name="applicant" value="${user.id}" class="span6 left-stripe {required : true,maxlength:40}">
                                            <input type="text"  readonly="readonly" id="applicantName"  disabled  name="applicantName" value="${user.realName}" class="span6 left-stripe {required : true,maxlength:40}">
                                        </c:if>
                                        <c:if test="${huiyi!=null}">
                                            <input type="text" id="applicant" style="display: none"  disabled  name="applicant" value="${huiyi.applicant}" class="span6 left-stripe {required : true,maxlength:40}">
                                            <input type="text"  readonly="readonly" id="applicantName"  disabled  name="applicantName" value="${huiyi.applicantName}" class="span6 left-stripe {required : true,maxlength:40}">
                                        </c:if>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            会务负责人<span style="color:red">*</span>
                                        </label>
                                        <input type="text" id="huiwufuzeId" style="display: none"   name="huiwufuzeId" value="${huiyi.huiwufuzeId}" class="span6 left-stripe {required : true,maxlength:40}"/>
                                        <input type="text" id="huiwufuzeName"  readonly="readonly"  value="${huiyi.huiwufuzeName}" name="huiwufuzeName"  class="span6 left-stripe {required : true,maxlength:40}"/>

                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            活动负责人<span style="color:red">*</span>
                                        </label>
                                        <input type="text" style="display: none" id="eventManager"   name="eventManager" value="${huiyi.eventManager}" class="span6 left-stripe {required : true,maxlength:40}"/>
                                        <input type="text"  readonly="readonly" value="${huiyi.eventManagerName}"  id="eventManagerName" name="eventManagerName" class="span6 left-stripe {required : true,maxlength:40}"/>

                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            审批人<span style="color:red">*</span>
                                        </label>
                                        <input type="text" id="reviewer"  name="reviewer" style="display:none;" value="${huiyi.reviewer}"/>
                                        <input type="text" readonly="readonly"   id="reviewerName" name="reviewerName" value="${huiyi.reviewerName}" class="span6 left-stripe {required : true,maxlength:40}"/>

                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            活动时间<span style="color:red">*</span>
                                        </label>
                                        <input type="text" id="kaishiTime" name="kaishiTime" class="chzn-select" autocomplete="off" readonly="readonly"
                                               style="width:200px;padding-top: 4px;"
                                               onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"
                                               placeholder="xxxx-xx-xx xx:xx"
                                               value="${huiyi.kaishiTime}"
                                               >
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            活动场地<span style="color:red">*</span>
                                        </label>
                                         <select id="changdiId" name="changdiId" disabled>

                                         </select>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">领导席位</label>
                                        <div class="left">
                                            <div class="textarea">
                                                <textarea id="leadership" name="leadership" disabled="disabled" style="width:800px;height:100px;">${huiyi.leadership}</textarea>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">活动物资</label>
                                        <div class="left">
                                            <div class="textarea">
                                                <textarea id="activitySupplies" name="activitySupplies" disabled style="width:800px;height:100px;">${huiyi.activitySupplies}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">场地设备</label>
                                        <div class="left">
                                            <div class="textarea">
                                                <textarea id="equipment" disabled name="equipment" style="width:800px;height:100px;">${huiyi.equipment}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">会务细则</label>
                                        <div class="left">
                                            <div class="textarea">
                                                <textarea id="meeting" readonly="readonly" name="meeting" style="width:800px;height:100px;">${huiyi.meeting}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            附件：
                                        </label>
                                        <div class="controls">
                                            <div id="zpa" style="display:inline-block;">
                                                <c:forEach items="${huiyi.fujian}" var="item" varStatus="i">
                                                    <a href="${item['url']}">${item['fileName']}</a>
                                                </c:forEach>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">工作人员</label>
                                        <div class="left">
                                            <div class="textarea">
                                                <textarea id="staff" readonly="readonly" name="staff" style="width:800px;height:100px;">${huiyi.staff}</textarea>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">其他人员</label>
                                        <div class="left">
                                            <div class="textarea">
                                                <textarea id="qitaRenYuan" readonly="readonly" name="qitaRenYuan" style="width:800px;height:100px;">${huiyi.qitaRenYuan}</textarea>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="control-group">
                                    <label class="control-label">参会人数</label>
                                    <div>
                                        <input style="position:relative;top:-12px;" type="text" id="attendanceNumber" name="attendanceNumber" value="${huiyi.attendanceNumber}" readonly="readonly" class="span3 left-stripe {maxlength:500}">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    </p>
                    <input type="hidden" id="huiyi_id" value="${huiyi.id}"/>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function() {
        addOption('/juzuAndjiaozhigong/depentAll', "departmentId", "id", "name",${huiyi.departmentId})
        addOption('/huiyi/findByChangdi', "changdiId", "id", "changdi_name",${huiyi.changdiId})
    });
    // 阻止点击冒泡
    function stopPropagation(e) {
        e = e || window.event;
        if (e.stopPropagation) {
            e.stopPropagation();
        } else {
            e.cancelBubble = true;
        }
    }

    function addOption(url, id, valProperty, namePropety,add, callback) {
        $.get(url, function (d) {
            d = JSON.parse(d);
            for (var i = 0; i < d.length; i++) {
                var obj = d[i];
                if(add==obj[valProperty]){
                    $("#" +id).append("<option selected value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
                }else{
                    $("#" +id).append("<option value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
                }

            }
            if (callback != null && callback != undefined) {
                callback(d);
            }
        })
    }



</script>
</html>