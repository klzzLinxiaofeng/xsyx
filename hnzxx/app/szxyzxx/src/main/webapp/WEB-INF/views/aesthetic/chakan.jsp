<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp" %>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <%--文件上传插件--%>
    <link rel="stylesheet" type="text/css" href="${ctp}/res/css/uploadifive/uploadifive.css">
    <script src="${ctp}/res/js/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>
    <style>
        div#zpi div, div#zp div,div#zpo div{
            display: inline-block;
        }
    </style>
</head>
<body style="background-color: #cdd4d7 !important">
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 20px 0 0;">
                <form class="form-horizontal tan_form" id="publicClass_time_form" action="javascript:void(0);">
                    <div class="control-group">
                        <label class="control-label">
                            学生姓名：
                        </label>
                        <div class="controls">
                            <input type="hidden" value="${aestheticPojo.studentId}" id="studentId" name="studentId"/>
                            <input value="${aestheticPojo.studentName}" disabled type="text" id="studentName" name="studentName" class="span4" autocomplete="off">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            个人优秀美术作品：
                        </label>
                        <div class="controls">
                            <div>
                                <input type="hidden" id="fineArtId" name="fineArtId" value="${aestheticPojo.fineArtId}">
                            </div>
                            <div id="zp" style="display:inline-block;">
                                <c:forEach items="${aestheticPojo.fineArtPicter}" var="item" varStatus="i">
                                    <div class="img_1" style="margin: 5px;">
                                       <img style="width:233px;height:130px;" class="ims" onclick="change(this);" src='${item.value}'/>
                                    </div>

                                </c:forEach>
                            </div>
                            <div class="clear"></div>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">
                            作品点评：
                        </label>
                        <div class="controls">
                            <input value="${aestheticPojo.review}" disabled="disabled" type="text" id="review" name="review" class="span4" autocomplete="off">分
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">
                            作品评分：
                        </label>
                        <div class="controls">
                            <input value="${aestheticPojo.reviewSore}" disabled="disabled" type="text" id="reviewSore" name="reviewSore" class="span4" autocomplete="off">分
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            学习或比赛图片：
                        </label>
                        <div class="controls">
                            <div>
                                <input type="hidden" id="gameWorksId" name="gameWorksId" value="${aestheticPojo.gameWorksId}">
                            </div>
                            <div id="zpi" style="display:inline-block;">
                                <c:forEach items="${aestheticPojo.gameWorksPicter}" var="item" varStatus="i">
                                    <div class="img_1" style="margin: 5px;">
                                        <img style="width:233px;height:130px;" class="ims" onclick="change(this);" src='${item.value}'/>
                                    </div>

                                </c:forEach>
                            </div>

                            <div class="clear"></div>
                        </div>

                    </div>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            个人奖状：
                        </label>
                        <div class="controls">
                            <div>
                                <input type="hidden" id="jiangzhuanId" name="jiangzhuanId" value="${aestheticPojo.jiangzhuanId}">
                            </div>
                            <div id="zpo" style="display:inline-block;">
                                <c:forEach items="${aestheticPojo.jiangzhuanPicter}" var="item" varStatus="i">
                                    <div class="img_1" style="margin: 5px;">
                                        <img style="width:233px;height:130px;" class="ims" onclick="change(this);" src='${item.value}'/>
                                    </div>

                                </c:forEach>
                            </div>
                            <div class="clear"></div>
                        </div>

                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            艺术相关图书借阅：
                        </label>
                        <div class="controls">
                            <input value="${aestheticPojo.bookNumber}" type="text" id="bookNumber" name="bookNumber" disabled="disabled" class="span4" autocomplete="off">本
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            学习评语：
                        </label>
                        <div class="controls">
                            <input disabled="disabled" value="${aestheticPojo.comments}" type="text" id="comments" name="comments" class="span4" autocomplete="off">本
                        </div>
                    </div>


                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${aestheticPojo.id}"/>
                        <button class="btn btn-warning" type="button"
                                onclick="saveOrUpdate();">确定
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">

    function Change(obj) {
        var imgSrc = $(obj).attr("src");
        window.open(imgSrc);
    }
    //保存或更新修改
    function saveOrUpdate() {
                        $.closeWindow();
    }

</script>
</html>