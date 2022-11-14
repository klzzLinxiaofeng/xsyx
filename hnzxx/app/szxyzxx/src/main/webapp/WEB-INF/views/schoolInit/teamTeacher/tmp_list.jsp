<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_newSchool.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
    <title></title>
</head>
<body style="background-color: #ffffff;">
<div class="select_div">
    <select class="span2" id="nj"></select>
    <select class="span2" id="bj" onchange="search();"></select>
</div>
<div class="sjcsh_xx_table content_main">
    <div id="tmp_list">
        <c:if test="${status==0 }">
            <jsp:include page="./tmp_ok.jsp"/>
        </c:if>
        <c:if test="${status==1 }">
            <jsp:include page="./tmp_warn.jsp"/>
        </c:if>
        <c:if test="${status==2 }">
            <jsp:include page="./tmp_error.jsp"/>
        </c:if>
    </div>
    <div class="page_div">
        <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
            <jsp:param name="id" value="tmp_list" />
            <jsp:param name="url" value="/tmp/team/teacher/list?status=${status}" />
            <jsp:param name="pageSize" value="${page.pageSize}" />
        </jsp:include>
        <div class="clear"></div>
    </div>
</div>
<div class="tis_error" style="display:none;text-align: center;padding-top:35px;">
    <p>修改的数据有误，无法保存</p>
    <p style="color:#ff5252">异常提示：手机号录入错误</p>
</div>
<div class="tis_warn" style="display:none;text-align: center;padding-top:35px;">
    <p>您修改的数据有冲突，是否保存</p>
    <p style="color:#ff5252">异常提示：任课教师姓名重复，无别名。</p>
</div>
<script>


    $.initNBSCascadeSelector({
        "type" : "team",
        "selectOne" : false,
        "gradeFirstOptTitle":"年级",
        "teamFirstOptTitle" : "班级"
    });

    function search(){
        var gradeId = $("#nj").val();
        var teamId = $("#bj").val();
        var val = {
            "gradeId" : gradeId,
            "teamId" : teamId,
            "status": "${status}"
        }
        var url = "/tmp/team/teacher/list";
        var id = "tmp_list";
        myPagination(id, val, url);
    }

    function editTeacher(id){
        $.initWinOnTopFromLeft_qyjx("编辑任课教师",  '${pageContext.request.contextPath}/tmp/team/teacher/edit?id=' + id, '713', '619');
    }

    function delTeacher(id, teamTeacherId){
        var val = {
            "id" : id,
            "teamTeacherId" : teamTeacherId
        }
        var url = "${pageContext.request.contextPath}/tmp/team/teacher/delete";
        $.post(url, val, function(data, status) {
            if ("success" === status) {
                if("success" === data) {
                    $.success("删除成功");
                    var $li = $("#iframe_sencond",window.parent.parent.document).contents().find('.f_left li.on')
                    if ($li.index() == 2) {
                        var text = $li.html();
                        var num = text.substring(text.indexOf("（")+1, text.indexOf("）"))-1;
                        $li.html("错误数据（" + num + "）")
                    }
                    search();
                } else {
                    $.error("删除失败");
                }
            } else {
                $.error("服务器异常");
            }
        });
    }

//    $('.btn-red').click(function(){
//        layer.open({
//            type: 1,
//            shade: false,
//            area: ['337px', '191px'],
//            title: '错误提示', //不显示标题
//            content: $('.tis_error'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
//            cancel: function(){
//                layer.close();
//            },
//            btn: ['重新编辑','取消'],//按钮
//            btn1: function(index, layero){
//
//            }
//        });
//        layer.open({
//            type: 1,
//            shade: false,
//            area: ['337px', '191px'],
//            title: '警告提醒', //不显示标题
//            content: $('.tis_warn'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
//            cancel: function(){
//                layer.close();
//            },
//            btn: ['继续保存','取消'],//按钮
//            btn1: function(index, layero){
//
//            }
//        });
//    });

    $('.table tbody i.ck').click(function(){
        if($(this).hasClass('on')){
            $(this).removeClass('on');
            if( $('tbody i.ck').length!=$('tbody i.ck.on').length){
                $('.table thead i.ck').removeClass('on');
            }
        }else{
            $(this).addClass('on');
            if($('tbody i.ck').length==$('tbody i.ck.on').length){
                $('.table thead i.ck').addClass('on');
            }
        }
    });
    $('.table thead i.ck').click(function(){
        if($(this).hasClass('on')){
            $(this).removeClass('on');
            $('.ck').removeClass('on');

        }else{
            $(this).addClass('on');
            $('.ck').addClass('on');
        }
    });
</script>
</body>
</html>