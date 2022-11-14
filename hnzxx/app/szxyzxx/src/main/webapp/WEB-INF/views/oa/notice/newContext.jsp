<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity" %>
    <link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/css/extra/my.css" rel="stylesheet">
    <title>查看通知</title>
    <script type="text/javascript">
        function back() {
            history.back(-1);
        }
    </script>
    <style type="text/css">
        .tznr span{
            line-height: 40px;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="查看通知" name="title"/>
        <jsp:param value="${param.dm}" name="menuId"/>
    </jsp:include>
    <div class="row-fluid ">
        <div class="span12">
            <div class="content-widgets white" style="word-wrap:break-word; ">
                <div class="widget-head">
                    <h3 class="x-head content-top">
                        <a href="javascript:void(0);">查看通知</a>
                        <a href="javascript:void(0)" onclick="back();" class="back right"><i
                                class="fa fa-arrow-circle-left"></i>返回</a>
                    </h3>
                </div>
                <div class="substance">
                    <div class="title">${notice.title}</div>

                    <div class="time">
                        ${notice.posterName}&nbsp;&nbsp;<fmt:formatDate value="${notice.createDate}" pattern="yyyy 年  MM 月 dd 日 HH:mm"/>
                    </div>
                    <c:if test="${sfhz}">
                        <div style="color: red">此通知需要回复</div>
                    </c:if>
                    <div class="tznr">
                    ${notice.content}
                    </div>
                    <c:forEach items="${entity}" var="item">

                        <div id="release_picture" style="float: none;width: initial;">
                            <ul>
                                <a target="_blank" href="<entity:getHttpUrl uuid="${item.uuid}"/>">
                                        ${item.fileName}
                                </a>
                            </ul>
                        </div>
                    </c:forEach>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>