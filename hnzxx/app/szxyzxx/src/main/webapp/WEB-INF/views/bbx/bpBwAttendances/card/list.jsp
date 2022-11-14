<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${items}" var="item">
	<tr id="${item.userId}_tr">
				<td><span class="icon"><img alt="" src="${item.icon}"></span><span class="name">${item.name}</span></td>
				<td>
					<fmt:formatDate value="${item.attendanceTime}" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
				<td>
				<ul id="layer-photos-demo" class="layer-photos-demo">
					<li><img alt="" layer-src="${item.photoUrl}" src="${item.photoUrl}" style="width:119px;"></li>
				</ul>
				</td>
		
		<td class="caozuo">
			<%-- <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}"> --%>
			<%-- <button class="btn btn-blue" type="button" onclick="loadEditPage('${item.gradeId}');">编辑</button> --%>
			<%-- </c:if> --%>
			<%-- <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
				<button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.gradeId}');">删除</button>
			</c:if> --%>
		</td>
	</tr>
</c:forEach>
<div class="z"></div>
<div class="layer_div_img"><img alt="" layer-src="http://119.23.222.108:88/develop/test/2017-11-22/277da54186de590eeecf3929d16a6034.jpg" src="http://119.23.222.108:88/develop/test/2017-11-22/277da54186de590eeecf3929d16a6034.jpg" style="width:0%"></div>
<script>
$('.layer-photos-demo').on('click','img',function(){
	$('.z').show();
	$('.layer_div_img').find('img').attr('src',$(this).attr('src'))
	$('.layer_div_img').find('img').animate({'width':'65%'})
})
$('.z,.layer_div_img').click(function(){
	$('.layer_div_img').find('img').animate({'width':'0'},250,function(){
		$('.z').fadeOut(400);
		$('.layer_div_img').find('img').attr('src','')
	})
})
</script>
<style>
.z{ width:100%; height:100%;background:#000;
    position: fixed;
    top: 0;
    left: 0;
    display:none;
  }
  .layer_div_img{
    position: fixed;
    width: 100%;
    text-align: center;}
</style>



