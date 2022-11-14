<%-- 
    Document   : myMicro
    Created on : 2015-5-13, 11:03:09
    Author     : Administrator
--%>
<%@include file="/views/embedded/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <c:if test="${resList== null || fn:length(resList) == 0}">
        <div class="empty">
            <p>您暂未上传过此类资源</p>
        </div>
    </c:if>
<c:forEach items="${resList}" var="reslist">
    <dl>
        <dt>
            <a onclick="viewMicro('${reslist.id}')"  href="javascript:void(0)" >
                <img src="${thumbFn:getConvertedUrl(res.thumbnailUrl,reslist.iconType, pageContext.request.contextPath, pageContext.request.serverName)}">
            </a>
        </dt>
        <dd>
            <div class="item-msg">
                <div class="top_1">
                    <div class="item-title">
                        <a onclick="viewMicro('${reslist.id}')" id="title_${reslist.id}" title="${reslist.title}" href="javascript:void(0)" >${reslist.title}</a>
                        <input id="microId_${reslist.objectId}" type="hidden" value="${reslist.objectId}"/>
                        <input id="resourceId_${reslist.objectId}" type="hidden" value="${reslist.id}"/> 
                       
                    </div>
                    <div><span>上传时间：<fmt:formatDate value="${reslist.createDate}" type="date" pattern="yyyy-MM-dd HH:mm"/></span></div>
                </div>
                <div  class="item-comment">
                </div>
            </div>
            <div class="select"><input id="id_${reslist.id}" resourceId="${reslist.id}" onclick="chooseMicro('${reslist.id}')" type="checkbox">选择</div>
        </dd>
    </dl>
</c:forEach>
<script type="text/javascript">
	//弹窗预览
	function previewMicro(mid) {
		var mes = "预览";
		$.initWinOnTopFromLeft(mes,'${pageContext.request.contextPath}/learningPlan/viewer?id='+ mid, '805', '685');
	}							
	
	//不弹窗预览
    function viewMicro(id) {
        var url = "${pageContext.request.contextPath}/learningPlan/viewer?canReturn=true&id="+id;
			$(".weike").load(url, function() {
		});
	}
</script>