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
    <title>获奖详情</title>
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
    <style type="text/css">
        #groupList,#groupLists{
            background: #f5f5f8;
            position: fixed;
            text-align:center;
            width: 60%;
            height: 50%;
            box-shadow: 0 3px 4px 0 #00000020, 0 4px 12px #00000020;
            border: 1px solid #d9d9d9;
        }
        .select_div{
            margin:30px;
            margin-bottom:130px;
        }
        .off{
            position:absolute;
            top:5px;
            right:10px;
            color: white;
        }
        /*屏幕中间*/
        #groupList,#groupLists{
            right: 20%;
            top: 20%;
        }
    </style>
    <style type="text/css">
        table {
            /*设置相邻单元格的边框间的距离*/
            border-spacing: 0;
            /*表格设置合并边框模型*/
            border-collapse: collapse;
            text-align: center;
        }
        /*关键设置 tbody出现滚动条*/
        table tbody {
            display: block;
            height: 220px;
            overflow-y: scroll;
        }

        table thead tr,
        tbody tr {
            display: table;
            width: 100%;
            table-layout: fixed;
        }
        /*关键设置：滚动条默认宽度是16px 将thead的宽度减16px*/
        table thead {
            width: calc( 100% - 1em)
        }
        table thead th {
            background: #ccc;
        }

    </style>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="获奖详情" name="title" />
        <jsp:param value="${param.dm}" name="menuId" />
    </jsp:include>
    <div class="row-fluid ">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                        <h3 class="x-head content-top"><a href="javascript:void(0);" class="on">获奖详情</a><a href="javascript:history.go(-1);" class="back right"><i class="fa fa-arrow-circle-left"></i>返回</a></h3>
                </div>
                <div class="space20"></div>
                <div class="x-main" >
                    <div class="clearfix x-mright">
                        <div class="list">
                            <form class="form-horizontal" id="applyrepair_form">
                                <div class="control-group">
                                    <label class="control-label">活动主题<span style="color:red">*</span></label>
                                    <div>
                                        <input type="text" readonly id="theme" value="${huoJiang.theme}" name="theme"  placeholder="请输入标题，少于20个中文字符" class="span6 left-stripe {required : true,maxlength:40}"/>
                                    </div>
                                </div>
                                <div class="x-up">
                                    <div class="control-group">
                                        <label class="control-label">
                                            获奖类型<span style="color:red">*</span>
                                        </label>
                                       <div>
                                           <input style="position:relative;top:-12px;" readonly value="${huoJiang.type}" type="text" id="types" name="types"  class="span3 left-stripe {maxlength:500}"/>
                                       </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            获奖属性<span style="color:red">*</span>
                                        </label>
                                        <div>
                                            <input style="position:relative;top:-12px;" readonly value="${huoJiang.shuXing}" type="text" id="shuXing" name="attractName"  class="span3 left-stripe {maxlength:500}"/>
                                        </div>

                                    </div>
                                    <c:if test="${type==1}">
                                        <div class="control-group">
                                            <label class="control-label">获奖学生</label>
                                            <div class="left">
                                                <div class="textarea">
                                                    <textarea id="huojiangStudent" readonly name="huojiangStudent" style="width:800px;height:100px;">${huoJiang.studentNames}</textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="control-group">
                                        <label class="control-label">获奖教师</label>
                                        <div class="left">
                                            <div class="textarea">
                                                <textarea  id="huojiangTeacher" readonly name="huojiangTeacher" style="width:800px;height:100px;">${huoJiang.teacherNames}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">作品名称</label>
                                        <div>
                                            <input style="position:relative;top:-12px;" readonly type="text" id="attractName" value="${huoJiang.nameWoke}" name="attractName"  class="span3 left-stripe {maxlength:500}"/>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            获奖级别<span style="color:red">*</span>
                                        </label>
                                        <div>
                                            <input style="position:relative;top:-12px;" readonly type="text" id="huojiangjibie" value="${huoJiang.winningLevelName}" name="huojiangjibie"  class="span3 left-stripe {maxlength:500}"/>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            等次或发表刊物<span style="color:red">*</span>
                                        </label>
                                        <div>
                                            <input style="position:relative;top:-12px;" readonly type="text" id="dengci" value="${huoJiang.dengciName}" name="dengci"  class="span3 left-stripe {maxlength:500}"/>
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">
                                            获奖日期<span style="color:red">*</span>
                                        </label>
                                        <input type="text" readonly id="huojiangTime" name="huojiangTime" class="chzn-select" autocomplete="off"
                                               style="width:200px;padding-top: 4px;"
                                               onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                               placeholder="xxxx-xx-xx"
                                               value="${huoJiang.winningTime}">
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">发奖单位</label>
                                        <div>
                                            <input style="position:relative;top:-12px;" readonly type="text" id="fajiangDanwei" name="fajiangDanwei" value="${huoJiang.allocated}" class="span3 left-stripe {maxlength:500}">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            图片：
                                        </label>
                                        <div class="controls">
                                            <div id="zpa" style="display:inline-block;">
                                              <c:forEach items="${huoJiang.pictureList}" var="item" varStatus="i">
                                                <div class="img_1"  style="margin: 5px;">
                                                    <input name="pictureId" style="display: none" type="text" value="${item.uuid}"/>
                                                    <img style="width:233px;height:130px;" class="ims" onclick="change(this);" src="${item.url}"/>&nbsp;&nbsp;&nbsp;</div>
                                            </c:forEach>
                                                <c:if test="${huoJiang.pictureList.size()<=0}">
                                                    暂无图片
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function change(obj) {
        var imgSrc = $(obj).attr("src");
        window.open(imgSrc);
    }
</script>
</body>
</html>