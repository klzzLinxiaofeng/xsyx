<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>教师编辑页面设置任课班级</title>
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
                            年级：
                        </label>
                        <div class="controls">
                            <select id="nj" name="gradeId" class="span4"
                                    style="width:200px;" >
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            班级：
                        </label>
                        <div class="controls">
                            <select id="bj" name="teamId" class="span4"
                                    style="width:200px;" >
                            </select>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">
                            科目：
                        </label>
                        <div class="controls">
                            <select id="km" name="subjectId" class="span4"
                                    style="width:200px;" >
                            </select>
                        </div>
                    </div>



                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${id}"/>
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

    var checker;

    $(function () {
        initSelect();
    });

    function initSelect() {
        var defaultTerm = '${sessionScope[sca:currentUserKey()].schoolYear}';

        //因查询年级不需学期，所以不需在学期填充后的回调中执行
        addOptionxq('/teach/grade/list/json?schoolYear=' + defaultTerm, "nj", "id", "name",function (d) {;
            if (d.length > 0) {
                //添加班级
                addOptionxq('/teach/team/list/json?enableRole=false&gradeId=' + d[0].id, "bj", "id", "name")
                //添加科目
                addOptionxq("/literacy/findExamSubject?nj="+d[0].id, "km", "code", "subjectName")
            }
        })
    }


    $("#nj").change(function(){
        $("#bj").html('');
        $("#km").html('');
        //添加班级
        addOptionxq('/teach/team/list/json?enableRole=false&gradeId=' +$("#nj").val(), "bj", "id", "name")

        //添加科目
        addOptionxq("/literacy/findExamSubject?nj="+$("#nj").val(), "km", "subjectId", "subjectName")

    })

    function addOptionxq(url,id,valProperty,namePropety,callback){
        var defaultTerm = '${sessionScope[sca:currentUserKey()].schoolTermCode}';
        $.get(url,function(d){
            d=JSON.parse(d);
            for(var i=0;i<d.length;i++){
                var obj=d[i];
                if(defaultTerm==obj[valProperty]) {
                    $("#" + id).append("<option selected=selected value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
                }else{
                    $("#" + id).append("<option  value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");

                }
            }
            if(callback!=null && callback!=undefined) {
                callback(d);
            }
        })
    }
    //保存或更新修改
    function saveOrUpdate() {
        var loader = new loadLayer();
        var id = $("#id").val();
        var $requestData = {};
        $requestData.gradeId=$("#nj").val();
        $requestData.teamId=$("#bj").val()
        $requestData.teacherId=id;
        $requestData.subjectCode=$("#km").val()
        var url = "${ctp}/teach/teacher/addOrModifyClassRoomTeacher";
        loader.show();
        $.post(url, $requestData, function (data, status) {
                if ("success" === data) {
                    $.success('操作成功');
                    parent.$("iframe").each(function () {
                        $(this).attr('src', $(this).attr('src'));//需要引用jquery
                    })
                    $.closeWindow();
                } else {
                    $.error("操作失败");
                }
            loader.close();
        });

    }
</script>
</html>