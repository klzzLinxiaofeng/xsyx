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
            <p>暂无资源</p>
        </div>
    </c:if>
<c:forEach items="${resList}" var="exam">
    <dl>
        <dt>
            <a onclick="viewMicro('${exam.id}')"  href="javascript:void(0)" >
                <img src="${thumbFn:getConvertedUrl(exam.thumbnailUrl, exam.iconType, pageContext.request.contextPath, pageContext.request.serverName)}">
            </a>
        </dt>
        <dd>
            <div class="item-msg">
                <div class="top_1">
                    <div class="item-title">
                        <a onclick="viewMicro('${exam.id}')" id="title_${exam.id }" title="${exam.title }"  href="javascript:void(0)" >${exam.title}</a>
                        <input id="examId_${exam.objectId}" type="hidden" value="${exam.objectId}"/>
                        <input id="resourceId_${exam.objectId}" type="hidden" value="${exam.id}"/>
                    </div>
                    <div><span>上传时间：  <fmt:formatDate value="${exam.createDate}" pattern="yyyy-MM-dd" type="date" dateStyle="long" /></span></div>
                </div>
                <div  class="item-comment">
                </div>
            </div>
            <div class="select"><input id="id_${exam.id}" resourceId="${exam.id}" onclick="chooseMicro('${exam.id}')" type="checkbox">选择</div>
        </dd>
    </dl>
</c:forEach>
<script type="text/javascript">
	//弹窗预览
	function previewMicro(mid) {
		var mes = "预览";
		$.initWinOnTopFromLeft(mes,'${pageContext.request.contextPath}/learningPlan/viewer?id='+ mid, '700', '500');
	}							
	
	//不弹窗预览
    function viewMicro(id) {
        var url = "${pageContext.request.contextPath}/learningPlan/paper/viewer?paperId="+id;
			$(".weike").load(url, function() {
		});
	}
</script>