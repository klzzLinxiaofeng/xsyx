<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<c:forEach items="${items}" var="item" varStatus="count">
	<tr class="pj_tr">
		<td>${count.count}</td>
		<td>${item.accepterName}</td>
		<td>${item.phone}</td>
		<td>${item.number}</td>
		<td>
			<div class="x-mark">
				<input type="hidden" value="${item.appraiseNum}" id="appraiseNum${count.count}"/>
				<em class="m-star"> <i class="fa fa-star" title="差"></i>
					<i class="fa fa-star " title="一般"></i> <i
					class="fa fa-star " title="较好"></i> <i class="fa fa-star"
					title="好"></i> <i class="fa fa-star" title="非常好"></i>
				</em> <span class="s_result fl pj">较好</span>
			</div>
		</td>
	</tr>
</c:forEach>
<script>
	$(function(){
		var i=$(".pj_tr").length;
		for(var j=0;j<i;j++){
			var k=$(".pj_tr").eq(j).children().children().children("input").val();
			var name = $(".pj_tr").eq(j).children().children();
			if(k==1){
				name.children("span").text("差");
			}else if(k==2){
				name.children("span").text("较好");
			}else if(k==3){
				name.children("span").text("一般");
			}else if(k==4){
				name.children("span").text("好")
			}else if(k==5){
				name.children("span").text("非常好")
			}else{
				name.children("span").text("该人未被评价过")
			}
			for(var l=0;l<k;l++){
				name.children("em").children().eq(l).addClass("active");
				//$(".pj_tr").eq(j).$("td .x-mark .m-star i").eq(l).addClass("active");
			} 
		}
		
	});
</script>