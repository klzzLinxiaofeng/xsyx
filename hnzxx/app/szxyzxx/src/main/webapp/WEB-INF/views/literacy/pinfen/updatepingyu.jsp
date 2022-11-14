<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>修改评分评语</title>
    <%@ include file="/views/embedded/common.jsp" %>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>

    <style>

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
                            分值：
                        </label>
                        <div class="controls">
                            <input onblur="xiugai()" value="${literacyStudent.fenshu}" type="text" id="score" name="score" class="span4" autocomplete="off"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            评语：
                        </label>
                        <div class="controls">
                            <textarea  id="pingyu" name="pingyu" class="span4" autocomplete="off" style="width: 350px;height: 200px">${literacyStudent.pingyu}</textarea>
                        </div>
                    </div>


                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${literacyStudent.id}"/>
                        <input type="hidden" id="maxscore" name="id" value="${literacyStudent.score}"/>
                        <%--此处studentId为年级id--%>
                        <input type="hidden" id="gradeId" name="id" value="${literacyStudent.studentId}"/>
                        <input type="hidden" id="subjectId" name="id" value="${literacyStudent.subjectId}"/>
                        <input type="hidden" id="literacyId" name="id" value="${literacyStudent.literacyId}"/>
                        <input type="hidden" id="schoolYear" name="id" value="${literacyStudent.stuName}"/>
                        <input type="hidden" id="schoolTrem" name="id" value="${literacyStudent.subName}"/>
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
    function xiugai() {
        var  gradeId=$("#gradeId").val();
        var  subjectId=$("#subjectId").val();
        var  literacyId=$("#literacyId").val();
        var  schoolYear=$("#schoolYear").val();
        var  schoolTrem=$("#schoolTrem").val();
        var  score=$("#score").val();
        $.get("/Literacy/student/findByPingMoBan?gradeId="+gradeId+"&subjectId="+subjectId+"&licatyId="+literacyId+"&fenshu="+score+"&schoolYear="+schoolYear+"&schoolTrem="+schoolTrem, function (dd) {
            var dd=JSON.parse(dd);
            if(dd!=null && dd!=""){
                $("#pingyu").text(dd['text']);
            }else{
                $.error("当前指标无对应模板或当前分数无对应区间");
            }
        })

    }
    //保存或更新修改
    function saveOrUpdate() {
        var loader = new loadLayer();
        var $id = $("#id").val();
        var text= $("#pingyu").val();
        var score=$("#score").val();
        loader.show();
        $.get("${ctp}/literacy/pingjias?id="+$id+"&score="+score+"&pingyu="+text, function (data, status) {
            if (status=="success") {
                $.success('操作成功');
                } else {
                    $.error("操作失败");
                }
            loader.close();
        });

    }
    function getBack() {
        if(parent.core_iframe != null) {
            parent.core_iframe.window.location.reload();
        } else {
            parent.window.location.reload();
        }
        $.closeWindow();
    }
</script>
</html>