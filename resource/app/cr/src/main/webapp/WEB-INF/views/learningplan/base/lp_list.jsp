<%@include file="/views/embedded/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<input type="hidden" id="source" name="source" value="${source}" />
<input type="hidden" id="total" name="total" value="${total }" />
<style>

	/*三角形*/
.dxa_ts:before {
   position: absolute;
    top: 35px;
    left: calc(50% - 5px);
    width: 10px;
    height: 10px;
    background: #e7faff;
    box-shadow: 2px 2px 0 -1px #c4c4c4;
    content: "";
    transform: rotate(45deg);
     border-bottom: 1px solid #9ad3ff;
    border-right: 1px solid #9ad3ff;
}
.dxa_ts1:before{
	left: calc(75% - 5px);
}
/*自适应居中*/
.dxa_ts {
     position: absolute;
    width:232px;
    top: -56px;
    left: 50%;
    z-index: 3;
    visibility: hidden;
    padding: 10px;
    height: auto;
    min-height: 12px;
    border-radius: 4px;
    background: #e7faff;
    box-shadow: 0 2px 8px rgba(0,0,0,.3);
    color: #2ca0f8;
    font-size: 12px;
    opacity: 0;
    transition: visibility 0s linear .2s,opacity .2s linear 0s;
    transform: translateX(-50%);
    backface-visibility: visible!important;
    backface-visibility: hidden;
    border: 1px solid #9ad3ff;
}
.dxa_ts.dxa_ts1{
	left: -30%;
}
/*hover效果*/
.grey_mj:hover .dxa_ts {
    visibility: visible;
    opacity: 1;
    transition: visibility 0s linear 0s,opacity .4s linear;
    animation: fade-top;
    animation-duration: .4s;
    -webkit-animation: fade-top .4s;
}
/*hover动画*/
@keyframes fade-top {
    0% {
        opacity: .1;
        top: 0
    }
 
    100% {
        opacity: 1;
        top: -56px
    }
}
 
@-webkit-keyframes fade-top {
     0% {
        opacity: .1;
        top: 0
    }
 
    100% {
        opacity: 1;
        top: -56px
    }
}
</style>
<div class="no_resource" style="display:none"></div>
<ul>
	<c:forEach items="${result}" var="vo">
		<li>
			<p class="title">${vo.title }</p>
			<div class="detail">
				<p class="gx"><b class="fa"></b>更新时间：<fmt:formatDate pattern="yyyy/MM/dd" value="${vo.modifyDate}" /></p>
				<c:choose>
					<c:when test="${vo.fav }">
						<p class="sc_own"><b class="fa"></b>收藏（<span>${vo.favCount }</span>）</p>
					</c:when>
					<c:otherwise>
						<p class="sc"><b class="fa"></b>收藏（<span>${vo.favCount }</span>）</p>
					</c:otherwise>
				</c:choose>
				<p class="sy"><b class="fa"></b>使用（<span>${vo.usedCount }</span>）</p>
			</div>
			<div class="cz_btn">
				<button class="btn btn-green" onclick="preview(${vo.id})">查看</button>
				<c:choose>
					<c:when test="${vo.fav }">
						<button class="btn btn-lightGray" onclick="fav(${vo.id},this)" fav="${!vo.fav }">取消收藏</button>
					</c:when>
					<c:otherwise>
						<button class="btn btn-blue" onclick="fav(${vo.id},this)" fav="${!vo.fav }">收藏</button>
					</c:otherwise>
				</c:choose>
				<button class="btn btn-orange" onclick="publish(${vo.id});">布置</button>
				<span class="zwbj grey_mj" style="position:relative">
				<c:choose>
					<c:when test="${vo.mySelf }">
						<c:if test="${!vo.hasTask }">
							<button class="btn btn-peaGreen" onclick="edit(${vo.id})">编辑</button>
						</c:if>
						<c:if test="${vo.hasTask }">
							<button class="btn btn-forbidGray">编辑</button>
							<span class="dxa_ts">该份导学案已被布置过，无法进行编辑操作</span>
						</c:if>
					</c:when>
					<c:otherwise>
						<button class="btn btn-forbidGray">编辑</button>
						<span class="dxa_ts">该导学案不是你创建的，不能进行编辑</span>
					</c:otherwise>
				</c:choose>
				</span>
				<span class="zwbj grey_mj" style="position:relative">
				<c:choose>
					<c:when test="${vo.mySelf }">
						<c:if test="${!vo.hasTask }">
							<button class="btn btn-red" onclick="deleteLearningPlan(${vo.id},'person');">删除</button>
						</c:if>
						<c:if test="${vo.hasTask }">
							<button class="btn btn-forbidGray">删除</button>
							<span class="dxa_ts dxa_ts1">该份导学案已被布置过，无法进行删除操作</span>
						</c:if>
					</c:when>
					<c:otherwise>
						<button class="btn btn-forbidGray">删除</button>
						<span class="dxa_ts dxa_ts1">该导学案不是你创建的，不能进行删除</span>
					</c:otherwise>
				</c:choose>
				</span>
			</div>
		</li>
	</c:forEach>
</ul>
<div style="margin:20px;padding:0" class="page">
<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
	<jsp:param name="id" value="lpList" />
	<jsp:param name="url" value="/learningPlan/${source }?type=${type }&property=${property }&ascending=${ascending }" />
	<jsp:param name="pageSize" value="${page.pageSize}" />
</jsp:include>
</div>
<div class="clear"></div>
<script type="text/javascript">
$(function() {
	var total = $("#total").val();
	if(total==0) {
		$(".no_resource").show();
		$(".page").hide();
	} else {
		$(".no_resource").hide();
		$(".page").show();
	}
})
</script>