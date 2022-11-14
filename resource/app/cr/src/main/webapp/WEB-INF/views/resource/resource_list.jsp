 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<c:choose>
	<c:when test='${topType == "top"}'>
		<jsp:include page="/views/embedded/resource/resource_type.jsp" >
			<jsp:param value="${page.totalRows}" name="resourceCount"/>
			<jsp:param value="${condition.stageCode}" name="stageCode"/>
			<jsp:param value="${condition.subjectCode}" name="subjectCode"/>
			<jsp:param value="${condition.versionCode}" name="versionCode"/>
			<jsp:param value="${condition.gradeCode}" name="gradeCode"/>
			<jsp:param value="${condition.volumnCode}" name="volumnCode"/>
			<jsp:param value="${condition.catalogCode}" name="catalogCode"/>
			<jsp:param value="${condition.resType}" name="resType"/>
			<jsp:param value="${param.isMicro}" name="isMicro"/>
			<jsp:param value="${order.ascending}" name="ascending"/>
			<jsp:param value="${order.property}" name="property"/>
			<jsp:param value="${personType}" name="personType"/>
		</jsp:include>
	</c:when>
	<c:otherwise>
		<jsp:include page="/views/embedded/resource/resource_sort.jsp">
			<jsp:param value="${page.totalRows}" name="resourceCount"/>
			<jsp:param value="${condition.title}" name="title"/>
			<jsp:param value="${order.ascending}" name="ascending"/>
			<jsp:param value="${order.property}" name="property"/>
			<jsp:param value="${personType}" name="personType"/>
		</jsp:include>
	</c:otherwise>
</c:choose>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<c:forEach items="${items}" var="item">
	<dl>
		<dt>
			<c:choose>
				<c:when test="${item.resType==7 }">
					<a href="javascript:void(0)" onclick="previewLp(${item.id})"><img src="${thumbFn:getConvertedUrl(item.thumbnailUrl, item.iconType, pageContext.request.contextPath, pageContext.request.serverName)}"></a>
				</c:when>
				<c:otherwise>
					<a onclick="viewById(${item.id},this)" href="javascript:void(0)"><img src="${thumbFn:getConvertedUrl(item.thumbnailUrl, item.iconType, pageContext.request.contextPath, pageContext.request.serverName)}"></a>
				</c:otherwise>
			</c:choose>
		</dt>
		<dd>
			<div class="item-msg">
				<div class="item-title">
					<span class="${iconFn:getClassName(item.iconType)} icon-file res-iconb"></span>
					<c:choose>
						<c:when test="${item.resType==7 }">
							<span
								style="overflow: hidden; width: 338px; float: left; display: block; text-overflow: ellipsis; word-wrap: normal;white-space: nowrap;">
								<a href="javascript:void(0)" onclick="previewLp(${item.id})">${item.title}</a>
							</span>
						</c:when>
						<c:otherwise>
							<span
								style="overflow: hidden; width: 338px; float: left; display: block; text-overflow: ellipsis; word-wrap: normal;white-space: nowrap;">
								<a onclick="viewById(${item.id},this)">${item.title}</a>
							</span>
						</c:otherwise>
					</c:choose>
				</div>
				<c:choose>
					<c:when test="${personType eq 'res_school'}">
						<div class="i1">教材目录：${tbFn:getCatalogName(item.catalogCode,'0')}</div>
					</c:when>
					<c:otherwise>
						<div class="i1">教材目录：${tbFn:getCatalogName(item.catalogCode,'0')}</div>
					</c:otherwise>
				</c:choose>
				<%--<div class="i1">--%>
					<%--上传时间：--%>
					<%--<fmt:formatDate value="${item.createDate}" pattern="YYYY-MM-dd" />--%>
				<%--</div>--%>
				<div class="i2">
					<span class="right">已有<span id="faved_count_${item.id}">${item.favCount == null ? 0 : item.favCount}</span>人收藏<b class="zan">${item.likeCount == null ? 0 : item.likeCount}</b></span>
				</div>
				<div class="cz_btn">
				<c:if test="${item.resType!=7 }">
					<a onclick="downloadRes(${item.integral}, ${item.id}, '${item.title}');" href="javascript:void(0)" >下载
					(<c:if test="${item.integral==null ||  item.integral==0}"><span style="color:green">免费</span></c:if>
					 <c:if test="${item.integral!=null &&  item.integral!=0}"><span style="color:#ff0000">${item.integral}积分</span></c:if>)
					</a>
				</c:if>
					<c:choose>
						<c:when test="${item.fav}">
							<a data-isFaved="true" data-id="${item.id}"  class="shoucan">取消收藏</a>
						</c:when>
						<c:otherwise>
							<a data-isFaved="false" data-id="${item.id}" class="shoucan">收藏</a>
						</c:otherwise>
					</c:choose>
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
<script type="text/javascript">
function downloadRes(integral, resId, title) {
	if(integral==null || integral==0) {
		window.location.href="${pageContext.request.contextPath}/resource/download?Id=" + resId;
		return;
	}
    $.ajax({
        url: "${pageContext.request.contextPath}/resource/getFileSize",
        type: "POST",
        data: {"resId": resId},
        async: true,
        success: function(data) {
        	if(data==0) {
        		$.alert("资源已经被删除");
        	} else {
        		var url = "${pageContext.request.contextPath}/resource/integral?resId="+resId+"&integral="+integral;
        		$.initWinOnTopFromLeft_bbx(title, url, "380", "230")
        	}
        }
   });
}

function viewById(id,obj) {
    var url = "${pageContext.request.contextPath}/resource/viewer/"+id+"?stageCode=${condition.stageCode}&subjectCode=${condition.subjectCode}&gradeCode=${condition.gradeCode}&versionCode=${condition.versionCode}&resType=${condition.resType}";
	window.location.href = url;
}

</script>