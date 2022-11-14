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
<body style="background-color: #e3e3e3;">
<div class="sjcsh_xx_detail">
    <div class="f_left">
        <ul>
            <li class="on" id="tta_daoru_ok">导入成功</li>
            <li id="tta_warn_data">警告数据（${warnSize}）</li>
            <li id="tta_wrong_data">错误数据（${errorSize}）</li>
        </ul>
    </div>
    <div class="f_right">
        <button class="btn btn-blue jxImport">继续导入</button>
        <%--<button class="btn btn-lightGray">批量删除</button>--%>
    </div>
    <div class="f_right" style="display:none">
        <button class="btn btn-blue jxImport">继续导入</button>
        <%--<button class="btn btn-red">批量删除</button>--%>
        <div class="plsc_ts">
            <span class="ts">注：确定批量导入将覆盖冲突的数据</span>
            <span class="tu"><i class="fa fa-exclamation"></i></span>
        </div>
    </div>
    <div class="f_right"  style="display:none">
        <button class="btn btn-blue jxImport">继续导入</button>
    </div>
</div>
<div style="background-color: #ffffff;padding:20px;margin: 0 20px 20px;">
    <iframe src="${pageContext.request.contextPath}/tmp/team/teacher/list?index=index" width="100%" frameborder="0" id="iframe_third" name="iframe_third" style="min-height:441px;max-height:690px;"></iframe>
</div>
<div class="tis_jxdr" style="display:none;text-align: center;padding-top:20px;">
    <p>继续导入将覆盖当前临时数据</p>
    <p>是否继续导入</p>
</div>
<script>
    $('.sjcsh_xx_detail ul li').click(function(){
        $(this).siblings().removeClass('on');
        $(this).addClass('on');
        $('.f_right').hide();
        $('.f_right').eq($(this).index()).show();
        var id = $(this).attr('id');
        var url = "${pageContext.request.contextPath}/tmp/team/teacher/list?index=index&status=" + $(this).index();
        $('#iframe_third').attr('src', url);
    });
    $('#iframe_third').load(function(){
        var iframeHeight=$(this).contents().height();
        $(this).height(iframeHeight+'px');
    });
    $('.jxImport').click(function(){
        layer.open({
            type: 1,
            shade: false,
            area: ['337px', '191px'],
            title: '继续导入提示', //不显示标题
            content: $('.tis_jxdr'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(){
                layer.close();
            },
            btn: ['确定','取消'],//按钮
            btn1: function(index, layero){
                $(window.parent.parent.document).find("#iframe_first").attr("src", "${pageContext.request.contextPath}/team/teacher/init/index")
            }
        });
    });
</script>
</body>
</html>