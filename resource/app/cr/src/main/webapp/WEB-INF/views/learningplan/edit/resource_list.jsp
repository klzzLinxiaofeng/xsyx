<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<jsp:include page="./resource_sort.jsp">
	<jsp:param value="${page.totalRows}" name="resourceCount"/>
	<jsp:param value="${condition.title}" name="title"/>
	<jsp:param value="${order.ascending}" name="ascending"/>
	<jsp:param value="${order.property}" name="property"/>
	<jsp:param value="${personType}" name="personType"/>
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

<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />

<c:forEach items="${items}" var="item">
    <dl>
        <dt>
	        <a onclick="viewMicro('${item.id}',${item.resType });" href="javascript:void(0)">
	            <img src="${thumbFn:getConvertedUrl(item.thumbnailUrl, item.iconType, pageContext.request.contextPath, pageContext.request.serverName)}">
	        </a>
        </dt>
        <dd>
            <div class="item-msg">
                <div class="item-title" style="height:40px;">
					<span class="${iconFn:getClassName(item.iconType)} icon-file res-iconb"></span>
					<span
						style="overflow: hidden; width: 338px; float: left; display: block; text-overflow: ellipsis; word-wrap: normal;white-space: nowrap;">
						<a href="javascript:void(0);" onclick="viewMicro(${item.id},${item.resType });" id="title_${item.id }" title="${item.title}">${item.title}</a>
					</span>
				</div>
				
				<c:choose>
					<c:when test="${condition.isPublish==1}">
						<div class="i1">教材目录：${tbFn:getCatalogName(item.catalogCode,'1')}</div>
					</c:when>
					<c:otherwise>
						<div class="i1">教材目录：${tbFn:getCatalogName(item.catalogCode,'0')}</div>
					</c:otherwise>
				</c:choose>
                <div class="i1">
                   	 上传时间：
                    <fmt:formatDate value="${item.createDate}" pattern="YYYY-MM-dd" />
                </div>
                <div class="i2">
                    <span class="right">已有${item.favCount == null ? 0 : item.favCount}人收藏<b class="zan">${item.likeCount == null ? 0 : likeCount}</b></span>
                </div>
                <div class="select">
                	<input id="id_${item.id}" resourceId="${item.id}" name="resources" onclick="chooseMicro('${item.id}')" type="checkbox">选择
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
                checkSelected();
            });
        </script>
    </c:otherwise>
</c:choose>