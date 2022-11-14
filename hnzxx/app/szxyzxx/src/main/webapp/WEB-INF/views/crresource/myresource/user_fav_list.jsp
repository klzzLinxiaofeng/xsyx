<%@include file="/views/embedded/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<c:if test="${fn:length(result)!=0 }">
	<ul>
		<c:forEach items="${result }" var ="userFav">
			<li>
				<a href="${ctp }/resource/owner/list?ownerId=${userFav.favUserId}" style="display:block;height:100%;">
					<img src="<avatar:avatar userId='${userFav.favUserId}'></avatar:avatar>">
					<div class="nr_right">
						<p class="name">${douFn:getFieldVal('teacher', userFav.favUserId)}</p>
						<p class="p1"><span>学校：</span>${userFav.schoolName }</p>
						<p class="p1"><span>资源数：</span>${resFn:getResCount(userFav.favUserId) }</p>
						<div class="integral" data-integral="${resFn:getIntegralCount(userFav.favUserId) }"><p></p></div>
					</div>
				</a>
			</li>
		</c:forEach>
	</ul>
</c:if>
<c:if test="${fn:length(result)==0 }">
	<div class="no_wdgz"></div>
</c:if>
<div class="clear"></div>
<script type="text/javascript">

$(function() {
	$(".integral").each(function() {
		var integral = $(this).data("integral");
		if(3<integral && integral<=250) {
			$(this).addClass("x1");
			if(3<integral && integral<=10) {
				$(this).children("p").css("width","20%");
			} else if(10<integral && integral<=40) {
				$(this).children("p").css("width","40%");
			} else if(40<integral && integral<=90) {
				$(this).children("p").css("width","60%");
			} else if(90<integral && integral<=150) {
				$(this).children("p").css("width","80%");
			} else if(150<integral && integral<=250) {
				$(this).children("p").css("width","100%");
			}
		} else if(250<integral && integral<=10000) {
			$(this).addClass("x2");
			if(250<integral && integral<=500) {
				$(this).children("p").css("width","20%");
			} else if(500<integral && integral<=1000) {
				$(this).children("p").css("width","40%");
			} else if(1000<integral && integral<=2000) {
				$(this).children("p").css("width","60%");
			} else if(2000<integral && integral<=5000) {
				$(this).children("p").css("width","80%");
			} else if(5000<integral && integral<=10000) {
				$(this).children("p").css("width","100%");
			}
		} else if(10000<integral && integral<=500000) {
			$(this).addClass("x3");
			if(10000<integral && integral<=20000) {
				$(this).children("p").css("width","20%");
			} else if(20000<integral && integral<=50000) {
				$(this).children("p").css("width","40%");
			} else if(50000<integral && integral<=100000) {
				$(this).children("p").css("width","60%");
			} else if(100000<integral && integral<=200000) {
				$(this).children("p").css("width","80%");
			} else if(200000<integral && integral<=500000) {
				$(this).children("p").css("width","100%");
			}
		} else if(integral>500000) {
			$(this).addClass("x4");
			if(500000<integral && integral<=1000000) {
				$(this).children("p").css("width","20%");
			} else if(1000000<integral && integral<=2000000) {
				$(this).children("p").css("width","40%");
			} else if(2000000<integral && integral<=5000000) {
				$(this).children("p").css("width","60%");
			} else if(5000000<integral && integral<=10000000) {
				$(this).children("p").css("width","80%");
			} else {
				$(this).children("p").css("width","100%");
			}
		}
	})
})
</script>