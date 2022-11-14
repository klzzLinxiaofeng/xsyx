<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/szxy/css/szxy_common.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/szxy/css/banji_info_manage.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_newSchool.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js" ></script>
    <title>家长信息-导入结果</title>
    <style>
        .sjcsh_xx_detail ul li.on {
            border: solid 1px #e3e8ec;
            border-bottom: none;
        }
        .sjcsh_xx_detail ul li {
            height: 40px;
            line-height: 39px;
            border: 1px solid #ffffff;
        }
        .content_main {
            box-shadow: none;
        }
    </style>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">

    <p class="top_link"><a href="/teach/parent/parentList?dm=JIA_ZHANG_XIN_XI">家长信息列表</a> > <a href="/teach/parent/import">批量导入家长信息</a> > <span>查看导入结果</span></p>
    <div class="content_main white"  style="border-radius: 0;">
        <div>
            <p class="banji_info_zt banji_info_bg_lan" id="one"><span><i>2</i></span>导入结果</p>
        </div>
        <div class="sjcsh_xx_detail" style="border-top:none">
            <div class="f_left">
                <ul>
                    <li class="on" id="tii_daoru_ok" data-type="0">导入成功</li>
                    <li id="tii_wrong_data" data-type="2">错误数据（${errorSize}）</li>
                </ul>
            </div>
            <c:if test="${!empty value }">
                <div class="f_right">
                    <span>导入时间：<fmt:formatDate value="${value.data }" pattern="yyyy-MM-dd HH:mm"/></span>
                </div>
            </c:if>

        </div>
        <div style="background-color: #ffffff;padding:20px;margin: 0 20px 20px;border: solid 1px #e3e8ec;">
            <div class="sjcsh_xx_table content_main">
                <div id="tmp_list">
                    <jsp:include page="./tmp_list.jsp"/>
                </div>
                <div class="page_div">
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="tmp_list" />
                        <jsp:param name="url" value="/teach/parent/tmp/list?status=${status}" />
                        <jsp:param name="pageSize" value="${page.pageSize}" />
                    </jsp:include>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $('.sjcsh_xx_detail ul li').click(function(){
        $(this).siblings().removeClass('on');
        $(this).addClass('on');
        toTmpList($(this).data("type"));
    });

    toTmpList("0");
    function toTmpList(type) {
        var val = {
            "status" : type
        }
        var url = "/teach/parent/tmp/list";
        var id = "tmp_list";
        myPagination(id, val, url);
    }

</script>
</body>
</html>