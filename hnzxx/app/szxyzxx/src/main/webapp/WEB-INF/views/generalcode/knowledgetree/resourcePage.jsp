<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<html>
<head>
    <%--<%@ include file="/views/embedded/common.jsp"%>--%>
        <%@include file="/views/embedded/taglib.jsp"%>
    <title>Title</title>
</head>
<body>
<table class="responsive table table-striped table-bordered" style="position:relative;top:-10px;">
    <thead>
        <tr>
            <th style="width: 34px;">序号</th>
            <th>标题</th>
            <%--<th style="text-align: center">缩略图</th>--%>
            <th>上传时间</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${resourceMessageVoList}" var="res" step="1" begin="0" varStatus="index">
            <tr>
                <td>${index.index + 1}</td>
                <td>
                    <%--<img src="${thumbFn:getConvertedUrl("", res.type, pageContext.request.contextPath, pageContext.request.serverName)}" style="width:60px;height:30px;"> &nbsp;&nbsp;&nbsp;&nbsp;${res.title}--%>
                    <c:choose>
                        <c:when test="${res.type==7 }">
                            <a href="javascript:void(0)" onclick="previewLp(${res.resId})"><img src="${thumbFn:getConvertedUrl("", res.type, pageContext.request.contextPath, pageContext.request.serverName)}" style="width:60px;height:30px;">&nbsp;&nbsp;&nbsp;&nbsp;${res.title}</a>
                        </c:when>
                        <c:otherwise>
                            <a href="javascript:void(0)" onclick="previewLp1(${res.resId})"><img src="${thumbFn:getConvertedUrl("", res.type, pageContext.request.contextPath, pageContext.request.serverName)}" style="width:60px;height:30px;">&nbsp;&nbsp;&nbsp;&nbsp;${res.title}</a>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td><fmt:formatDate value="${res.createDate}" pattern="yyyy/MM/dd" /></td>
            </tr>
        </c:forEach>
    <tr id="ms"><td colspan="3" style="text-align: center">暂无数据</td></tr>
    </tbody>
</table>
<div class="clear"></div>
</body>
<script>
    $(function(){
        if(${empty resourceMessageVoList}){
            $("#ms").show();
        }else{
            $("#ms").hide();
        }
    });

    function previewLp(lpId) {
        var url = "${ctp}/generalcode/knowledgetree/toShowPage?type=vd&resId="+lpId
        // 	加载创建对话框
            $.initWinOnTopFromLeft('资源查看',
                    url, '846', '500');

    }

    function previewLp1(lpId) {
        var url = "${ctp}/generalcode/knowledgetree/toShowPage?type=doc&resId="+lpId;
        // 	加载创建对话框
        $.initWinOnTopFromLeft('资源查看', url);

    }

</script>
</html>
