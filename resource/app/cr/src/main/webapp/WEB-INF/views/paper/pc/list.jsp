<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>

<input type="hidden" id="currentPage" name="currentPage"
	value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages"
	value="${page.totalPages}" />
<input type="hidden" id="total" name="total"
	value="${page.totalRows}" />
<style>
	.u-flyer{display: block;width: 50px;height: 50px;border-radius: 50px;position: fixed;z-index: 9999;}
	
</style>

<div class="no_resource" style="display:none"></div>

<ul>
	<c:forEach items="${paperlist}" var="item">
		<li
			data-time=<fmt:formatDate value="${item.modifyDate}" pattern="yyyy/MM/dd" />
			data-sc=${item.favCount } data-sy=${item.usedCount}>
			<p class="title">${item.title}</p>
			<div class="detail">
				<p class="zf">
					<b class=""></b>总分：${item.score}
				</p>
				<p class="tm">
					<b class=""></b>题目数量：${item.questionCount}
				</p>
				<p class="gx">
					<b class=""></b>更新时间：
					<fmt:formatDate value="${item.modifyDate}" pattern="yyyy/MM/dd" />
				</p>
				<p class="sc">
					<b class=""></b>收藏（<span>${item.favCount}</span>）
				</p>
				<p class="sy">
					<b class=""></b>使用（<span>${item.usedCount}</span>）
				</p>
			</div>
			<div class="cz_btn">
				<btn class="btn btn-green" onclick="look(${item.id})")>预览</btn>
				<c:choose>
					<c:when test="${item.isDeleted}">
					<btn class="btn btn-lightGray" onclick="fav(${item.id},this)"
							fav="${!item.isDeleted}">取消收藏</btn>
					</c:when>
					<c:otherwise>
						<btn class="btn btn-blue" onclick="fav(${item.id},this)" fav="${!item.isDeleted}">收藏</btn>
					</c:otherwise>
				</c:choose>
				<btn class="btn btn-orange addBasket" data-type="paper" data-paperid="${item.id }" data-stage="${item.stageCode}" onclick="window.parent.addToBasket(this)">加入试题篮</btn>
			</div>
		</li>
	</c:forEach>
</ul>

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
	window.parent.checkBtnState();
	czBtnTop();
	
})

window.onresize=czBtnTop;

function czBtnTop(){
	var nrRight = $('.nr_right').width();
	
	if(nrRight<925){
		$('.cz_btn').css('top','17px');
	}else{
		$('.cz_btn').css('top','36px');
	}
}
</script>
