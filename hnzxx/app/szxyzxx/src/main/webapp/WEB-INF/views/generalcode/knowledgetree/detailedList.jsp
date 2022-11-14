<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
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
    .empty p{
        font-size: 30px;
        font-family: "微软雅黑";
        margin-top: 100px;
        text-align: center;
    }
    .pagination{margin-top:10px;}
</style>
<tr style="display: none;"><td>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
</td></tr>
<c:forEach items="${items}" var="item">
    <dl>
        <dt>
            <c:choose>
                <c:when test="${item.resType==7 }">
                    <a href="javascript:void(0)" onclick="previewLp(${item.id})"><img src="${thumbFn:getConvertedUrl(item.thumbnailUrl, item.iconType, pageContext.request.contextPath, pageContext.request.serverName)}"></a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/resource/viewer/${item.id}"><img src="${thumbFn:getConvertedUrl(item.thumbnailUrl, item.iconType, pageContext.request.contextPath, pageContext.request.serverName)}"></a>
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
								<a href="javascript:void(0)" onclick="previewLp(${item.id})" title="${item.title}">${item.title}</a>
							</span>
                        </c:when>
                        <c:otherwise>
							<span
                                    style="overflow: hidden; width: 338px; float: left; display: block; text-overflow: ellipsis; word-wrap: normal;white-space: nowrap;">
								<a href="${pageContext.request.contextPath}/resource/viewer/${item.id}" title="${item.title}">${item.title}</a>
							</span>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="i1">
                    上传时间：
                    <fmt:formatDate value="${item.createDate}" pattern="YYYY-MM-dd" />
                </div>
                <div class="i2">
                    <span class="right">已有<span id="faved_count_${item.id}">${item.favCount == null ? 0 : item.favCount}</span>人收藏<b class="zan">${item.likeCount == null ? 0 : item.likeCount}</b></span>
                </div>
                <div class="cz_btn">
                    <c:if test="${item.resType!=7 }">
                        <a target="_blank" href="${pageContext.request.contextPath}/resource/download?Id=${item.id}" >下载</a>
                    </c:if>
                </div>
            </div>
        </dd>
    </dl>
</c:forEach>

<jsp:include page="./myJqpagination.jsp" flush="true">
    <jsp:param name="id" value="data_knowledge" />
    <jsp:param name="url"  value="/generalcode/knowledgetree/getKnowledgeDetailed?dm=${param.dm}&sub=list" />
    <jsp:param name="pageSize" value="${page.pageSize}" />
</jsp:include>

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