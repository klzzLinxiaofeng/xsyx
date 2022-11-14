<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/views/embedded/taglib.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<table>
    <tr>
        <td style="padding:0;border:0 none;">
            <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
            <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
            <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
        </td>
    </tr>
</table>
<input type="hidden" value="${type}" id="type">
<ul>
    <c:forEach items="${complainList}" var="item" varStatus="status">
        <li id="${item.id}_li">
            <div class="touxiang">
                <img src="<avatar:avatar userId='${item.complainantId }'></avatar:avatar>"/>
                <%--<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">--%>
            </div>
            <div class="detail">
                <div class="p1"><span>${item.complainName}</span>的投诉</div>
                <div class="p2">
                    <c:if test="${!item.isDispose}"><span>[待处理]</span></c:if>
                    <c:if test="${item.isDispose}"><span style="color:#34b545;">[已处理]</span></c:if>
                </div>
                <div class="p3"><i class="fa  fa-sitemap"></i><p class="p_div">所在部门</p><span>${item.departName}</span></div>
                <div class="p3"><i class="fa fa-clock-o"></i><p class="p_div">投诉时间</p>
                    <span><fmt:formatDate value="${item.createDate}" type="both"></fmt:formatDate></span>
                </div>
                <div class="p3"><i class="fa fa-clock-o"></i><p class="p_div">问题类别</p><span>${item.type}</span></div>
                <div class="p3"><i class="fa fa-align-center"></i><p class="p_div">问题描述</p><span>${item.description}</span></div>
                <div class="p7">
                    <c:forEach items="${item.fileUUIDList}" var="uuid" >
                        <img src="<entity:getHttpUrl uuid='${uuid}'/>" onclick="Change(this);">
                    </c:forEach>
                </div>
                <c:choose>
                    <c:when test="${item.isDispose}">
                        <div class="p3"><i class="fa fa-user"></i><p class="p_div">处理人</p><span>${item.disposeName}</span></div>
                        <div class="p3"><i class="fa fa-check-circle"></i><p class="p_div">处理进度</p><span>已处理</span></div>
                        <div class="p3"><i class="fa fa-retweet"></i><p class="p_div">处理方式</p><span>${item.disposeWay}</span></div>
                    </c:when>
                    <c:otherwise>
                        <div class="p3"><i class="fa fa-user"></i><p class="p_div">处理人</p><span>还没有处理</span></div>
                        <div class="p3"><i class="fa fa-check-circle"></i><p class="p_div">处理进度</p><span>待处理</span></div>
                        <div class="p3"><i class="fa fa-retweet"></i><p class="p_div">处理方式</p><span>暂无</span></div>
                    </c:otherwise>
                </c:choose>
                <div class="p3"><i class="fa fa-anchor"></i><p class="p_div">处理评价</p>
                    <span>
                        <c:if test="${item.isEvaluate}">${item.content}</c:if>
                        <c:if test="${!item.isEvaluate}">暂无</c:if>
                    </span>
                </div>
                <div class="p7"><div class="pf"><p style="width:${item.percent}"></p></div></div>
            </div>
            <div class="caozuo" style="width:auto">
                <c:if test="${!item.isEvaluate && item.isDispose}">
                    <button class="btn btn-success" onclick="loadEvaluatePage('${item.id}');">评价</button>
                </c:if>
                <c:if test="${!item.isDispose}">
                    <button class="btn btn-primary" onclick="loadEditPage('${item.id}');">编辑</button>
                    <button class="btn btn-danger" onclick="deleteObj(this, '${item.id}');">删除</button>
                </c:if>
            </div>
            <div class="clear"></div>
        </li>
    </c:forEach>
</ul>
<script type="text/javascript">
    $(function () {
        var type = "${type}";
        var size = "${size}";
        if("" == size){
            size = 0;
        }
        if(type == "all"){
            $("#num").text("全部投诉（"+size+"）");
        } else {
            $("#num").text("我的投诉（"+size+"）");
        }
    });
    function Change(obj) {
        var imgSrc = $(obj).attr("src");
        window.open(imgSrc);
    }
</script>