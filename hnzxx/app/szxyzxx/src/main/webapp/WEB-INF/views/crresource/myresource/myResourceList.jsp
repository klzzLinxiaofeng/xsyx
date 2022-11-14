<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/views/embedded/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<input type="hidden" id="personType" value="${personType }">
<input type="hidden" id="totalRows" value="${page.totalRows }">

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="zy_list">
    <c:if test="${(resList== null || fn:length(resList) == 0)&&(personType eq 'res_school'||personType eq 'res_person')}">
        <div class="empty">
            <p>暂无资源</p>
        </div>
    </c:if>

    <div class="xkzy_list" >
        <c:forEach items="${resList}" var="res">
            <dl>
                <dt>
                    <c:choose>
                        <c:when test="${personType=='res_person'}">
                            <a href="#">
                                <img attai="${res.iconType }" src="${thumbFn:getConvertedUrl('',res.iconType, pageContext.request.contextPath, pageContext.request.serverName)}">
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/crresource/Play?id=${res.id}">
                                <img attai="${res.iconType }" src="${thumbFn:getConvertedUrl('',res.iconType, pageContext.request.contextPath, pageContext.request.serverName)}">
                            </a>
                        </c:otherwise>
                    </c:choose>

                </dt>
                <dd>
                    <div class="item-msg">
                        <div class="item-title">
		                    	<span class="${iconFn:getClassName(res.iconType)} icon-file res-iconb"></span>
                           		<span style="overflow:hidden;width:338px;float:left;display:block; white-space:nowrap; text-overflow:ellipsis; word-wrap: normal;">
                                    <c:choose>
                                        <c:when test="${personType=='res_person'}">
                                            <a href="#" title="${res.title}">${res.title}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="${pageContext.request.contextPath}/crresource/Play?id=${res.id}" title="${res.title}">${res.title}</a>
                                        </c:otherwise>
                                    </c:choose>

                            </span>
                        </div>
<%--                        <span class="i1"> --%>
<%--                           	 简介：${fn:length(res.description) > 30?"点开查看":res.description}--%>
<%--                        </span> --%>
                        <div class="i1">
                          	  上传时间：<fmt:formatDate value="${res.createDate}" pattern="yyyy-MM-dd"/>
                        </div>
                        <div class="i2">
                            <span class="left"></span>
                            <span class="right"></span>
                        </div>
                        <div class="cz_btn">
							<c:if test="${personType eq 'res_person'}">
                                    <a class="shoucan" src="#" onclick="download2(${res.id})" href="#" >下载</a>
									<a class="shoucan" onclick="editMicro('${res.id}');" >编辑</a>
									<a class="qingchu" onclick="deleteMicro('${res.verify}','${res.id}');">删除</a>
							</c:if>
                            <c:if test="${personType eq 'res_school'}">
                                <a class="shoucan" src="#" onclick="download2(${res.id})" href="#" >下载</a>
                            </c:if>
                        </div>
                    </div>
                </dd>
            </dl>
        </c:forEach>
    </div>
</div>
<script type="text/javascript">

    function download2(resId){
        location.href="${pageContext.request.contextPath}/crresource/download?Id=" + resId;

    }

function downloadRes(integral, resId, title) {
	//if(integral==null || integral==0) {
		window.location.href="${pageContext.request.contextPath}/crresource/download?Id=" + resId;
		//return;
	<%--}--%>
   <%-- $.ajax({--%>
   <%--     url: "${pageContext.request.contextPath}/crresource/getFileSize",--%>
   <%--     type: "POST",--%>
   <%--     data: {"resId": resId},--%>
   <%--     async: true,--%>
   <%--     success: function(data) {--%>
   <%--     	if(data==0) {--%>
   <%--     		$.alert("资源已经被删除");--%>
   <%--     	} else {--%>
   <%--     		var url = "${pageContext.request.contextPath}/crresource/integral?resId="+resId+"&integral="+integral;--%>
   <%--     		$.initWinOnTopFromLeft_bbx(title, url, "380", "230")--%>
   <%--     	}--%>
   <%--     }--%>
   <%--});--%>
}

$(function() {
	$("#total").text($("#totalRows").val());
})

function editMicro(id) {
    var mes = "编辑";
    var url = "${pageContext.request.contextPath}/crresource/edit?personType=${personType}&dm=${dm}&id="+id;
    $.initWinOnTopFromLeft(mes, url, '700', '400');
}
</script>