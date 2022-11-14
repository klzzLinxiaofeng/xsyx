<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp" %>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctp}/res/css/uploadifive/uploadifive.css">
    <script src="${ctp}/res/js/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>
    <style>
        div#zpa div, div#zp div{
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
                            姓名：
                        </label>
                        <div class="controls">
                            <input value="${practiceInnovations.studentName}" type="text" id="studentName" name="studentName" class="span4" autocomplete="off" disabled/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            班级节假日课程：
                        </label>
                        <div class="controls">
                            <input value="${practiceInnovations.teamJiaDay}" type="text" id="teamJiaDay" name="teamJiaDay" class="span4" autocomplete="off" disabled/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            学生素质拓展课程：
                        </label>
                        <div class="controls">
                            <table>
                                <thead>
                                <tr role="row">
                                    <th>课程名称</th>
                                    <th>教师</th>
                                </tr>
                                </thead>
                                <tbody id="publicClass_list_content">
                                <%-- <c:if test="${practiceInnovations.publicClassList.size()}>0">--%>
                                <c:forEach items="${practiceInnovations.publicClassList}" var="item" varStatus="i">
                                    <tr id="${item.name}_tr">
                                        <td>${item.name}</td>
                                        <td>${item.teacherName}</td>
                                    </tr>
                                </c:forEach>
                                <%-- </c:if>--%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            科创类社团活动：
                        </label>

                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            社团评分：
                        </label>
                        <div class="controls">
                            <input disabled value="${practiceInnovations.score}" type="text" id="score" name="score" class="span4" autocomplete="off">分
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            学习或比赛图片：
                        </label>
                        <div class="controls">
                            <div>
                                <input type="hidden" id="pctreId" name="pctreId" value="${practiceInnovations.pctreId}">
                            </div>
                            <div id="zpa" style="display:inline-block;">
                                <c:forEach items="${practiceInnovations.picter}" var="item" varStatus="i">
                                    <div class="img_1" style="margin: 5px;">
                                        <img style="width:233px;height:130px;" class="ims" onclick="change(this);" src='${item.value}'/>
                                    </div>

                                </c:forEach>



                                <%--<div class="img_1">
                                    <c:if test="${(isCK==''||isCK==null) && (practiceInnovations.jiangzhuanUrl !=null && practiceInnovations.jiangzhuanUrl != '') }">
                                        <a data-id="${practiceInnovations.jiangzhuanUrl}" onclick="reMove(this);">取消</a>
                                    </c:if>
                                    <c:if test="${practiceInnovations.jiangzhuanUrl !=null && practiceInnovations.jiangzhuanUrl != ''}">
                                        <img src="${practiceInnovations.jiangzhuanUrl}" onclick="Change(this);"
                                             style="width: 260px; height: 130px;">
                                    </c:if>
                                    <c:if test="${practiceInnovations.jiangzhuanUrl ==null || practiceInnovations.jiangzhuanUrl == ''}">
                                        <img src="${ctp}/res/images/no_picture.jpg" onclick="Change(this);"
                                             style="width: 233px; height: 130px;">
                                    </c:if>
                                </div>--%>
                            </div>

                        </div>
                        <div class="clear"></div>
                    </div>

                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            个人奖状：
                        </label>
                        <div class="controls">
                            <div>
                                <input type="hidden" id="jiangzhuanId" name="jiangzhuanId" value="${practiceInnovations.jiangzhuanId}">
                            </div>
                            <div id="zp" style="display:inline-block;">
                                <c:forEach items="${practiceInnovations.jiangzhuanPicter}" var="item" varStatus="i">

                                    <div class="img_1" style="margin: 5px;">
                                        <img style="width:233px;height:130px;" class="ims" onclick="change(this);" src='${item.value}'/>
                                    </div>

                                </c:forEach>



                                <%--<div class="img_1">
                                    <c:if test="${(isCK==''||isCK==null) && (practiceInnovations.jiangzhuanUrl !=null && practiceInnovations.jiangzhuanUrl != '') }">
                                        <a data-id="${practiceInnovations.jiangzhuanUrl}" onclick="reMove(this);">取消</a>
                                    </c:if>
                                    <c:if test="${practiceInnovations.jiangzhuanUrl !=null && practiceInnovations.jiangzhuanUrl != ''}">
                                        <img src="${practiceInnovations.jiangzhuanUrl}" onclick="Change(this);"
                                             style="width: 260px; height: 130px;">
                                    </c:if>
                                    <c:if test="${practiceInnovations.jiangzhuanUrl ==null || practiceInnovations.jiangzhuanUrl == ''}">
                                        <img src="${ctp}/res/images/no_picture.jpg" onclick="Change(this);"
                                             style="width: 233px; height: 130px;">
                                    </c:if>
                                </div>--%>
                            </div>
                            <span id="tp_queue"></span>

                            <div class="clear"></div>
                        </div>

                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            科创相关图书借阅：
                        </label>
                        <div class="controls">
                            <input  disabled value="${practiceInnovations.bookNumer}" type="text" id="bookNumer" name="bookNumer" class="span4" autocomplete="off">本
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            学习评语：
                        </label>
                        <div class="controls">
                            <input disabled="disabled" value="${practiceInnovations.pingyu}" type="text" id="pingyu" name="pingyu" class="span4" autocomplete="off" >
                        </div>
                    </div>


                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${practiceInnovations.id}"/>
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
/*
    var checker;
    $(function () {
  /!*      checker = initValidator();*!/
    });*/
    function Change(obj) {
        var imgSrc = $(obj).attr("src");
        window.open(imgSrc);
    }

function saveOrUpdate() {
    $.closeWindow();
}
</script>
</html>