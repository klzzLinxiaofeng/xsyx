<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<%-- <jsp:include page="/views/embedded/resource/resource_type.jsp"> --%>
<%-- 	<jsp:param value="${fn:length(items)}" name="resourceCount" /> --%>
<%-- 	<jsp:param value="${condition.stageCode}" name="stageCode" /> --%>
<%-- 	<jsp:param value="${condition.subjectCode}" name="subjectCode" /> --%>
<%-- 	<jsp:param value="${condition.versionCode}" name="versionCode" /> --%>
<%-- 	<jsp:param value="${condition.gradeCode}" name="gradeCode" /> --%>
<%-- 	<jsp:param value="${condition.volumnCode}" name="volumnCode" /> --%>
<%-- 	<jsp:param value="${condition.catalogCode}" name="catalogCode" /> --%>
<%-- 	<jsp:param value="${condition.resType}" name="resType" /> --%>
<%-- 	<jsp:param value="${order.ascending}" name="ascending" /> --%>
<%-- 	<jsp:param value="${order.property}" name="property" /> --%>
<%-- </jsp:include> --%>
<jsp:include page="/views/embedded/resource/resource_sort.jsp">
    <jsp:param value="${page.totalRows}" name="resourceCount"/>
    <jsp:param value="${condition.title}" name="title"/>
    <jsp:param value="${condition.stageCode}" name="stageCode" />
    <jsp:param value="${condition.subjectCode}" name="subjectCode" />
    <jsp:param value="${condition.versionCode}" name="versionCode" />
    <jsp:param value="${condition.gradeCode}" name="gradeCode" />
    <jsp:param value="${condition.volumnCode}" name="volumnCode" />
    <jsp:param value="${condition.catalogCode}" name="catalogCode" />
    <jsp:param value="${condition.resType}" name="resType" />
    <jsp:param value="${order.ascending}" name="ascending"/>
    <jsp:param value="${order.property}" name="property"/>
</jsp:include>
<style>
    .xkzy_list dl dd .select{
        top:13px;
    }
      .xkzy_list dl dd .select input[type="checkbox"]{
                 margin: 0 12px;
    position: relative;
    top: -1px;
            }
    .xkzy_list dl dd .item-msg .item-title {
    min-height: 40px; 
    height: inherit;
    display: table;
}
</style>
<input type="hidden" id="currentPage" name="currentPage"
       value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages"
       value="${page.totalPages}" />
<c:forEach items="${items}" var="item">
    <dl>
        <dt>
        <a onclick="previewMicro('${item.objectId}');" href="javascript:void(0)">
            <img src="${thumbFn:getConvertedUrl(item.thumbnailUrl, item.iconType, pageContext.request.contextPath, pageContext.request.serverName)}">
        </a>
        </dt>
        <dd>
            <div class="item-msg">
                <div class="item-title">
                    <span
                        class="${iconFn:getClassName(item.iconType)} icon-file res-iconb"></span>
                    <!-- 				<span class="res-doc icon-file res-iconb"></span>  -->
                    <!-- 				res-ppt -->
                    <!-- 				res-doc -->
                    <!-- 				res-rar -->
                    <!-- 				res-html -->
                    <span style="overflow: hidden; width: 338px; float: left; display: block; text-overflow: ellipsis; word-wrap: normal;">
                        <a  onclick="previewMicro('${item.objectId}')" href="javascript:void(0)">${item.title}</a>
                        <input id="microId_${item.objectId}" type="hidden" value="${item.objectId}"/>
                         <input id="resourceId_${item.objectId}" type="hidden" value="${item.id}"/>
                    </span>
                </div>
                <c:if test="${condition.isPublish==1}">
                 <div class="i1">教材目录：${tbFn:getCatalogName(item.catalogCode,'1')}</div>
                </c:if>
                <c:if test="${condition.isPublish==0}">
                 <div class="i1">教材目录：${tbFn:getCatalogName(item.catalogCode,'0')}</div>
                </c:if>
                <div class="i1">
                    上传时间：
                    <fmt:formatDate value="${item.createDate}" pattern="YYYY-MM-dd" />
                </div>
                <div class="i2">
                    <%-- 					<span class="left"><img src="${ctp}/res/images/noPhoto.jpg">参考库</span> --%>
                    <span class="right">已有${item.favCount == null ? 0 : item.favCount}人收藏<b class="zan">${item.likeCount == null ? 0 : likeCount}</b></span>
                </div>
                <div class="select">
                    <input id="microCheck_${item.objectId}" onclick="chooseMicro('${item.objectId}')" type="checkbox">选择
                   
                </div>
            </div>
        </dd>
    </dl>
</c:forEach>

<c:choose>
    <c:when test="${fn:length(items) <= 0}">
        <div class="empty">
            <p>当前资源为空</p>
        </div>

        <script type="text/javascript">
                    $(function() {
                        $(".pagination").hide();
                    });
        </script>
    </c:when>
    <c:otherwise>
        <script type="text/javascript">
            $(function() {
                $(".pagination").show();
            });
        </script>
    </c:otherwise>
</c:choose>


