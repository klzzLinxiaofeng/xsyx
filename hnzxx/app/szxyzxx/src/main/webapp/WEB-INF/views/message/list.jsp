<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="avatar" uri="http://www.jiaoxueyun.com/avatar"%>

<tr id="sm" style="">
	<td>
		<c:if test="${empty voList}">
		<dl class="x-list">
			<dd>
				<div style="text-align: center;">
					目前暂时没有消息！
				</div>
			</dd>
		</dl>
		</c:if>
	</td>
	<td>
	<dl class="x-list">
		<c:forEach items="${voList}" var="item">
			<dd class="clearfix ${item.id}">
				<input id="id" type="hidden" value="${item.id}"/>
				<span class="fl iocn-a ${item.tag}"></span>
				<div class="fl text">
					<h4 class="h4"></h4>
					<p <c:if test='${item.readStatus == 0}'>class="bold"</c:if>>
						<input id="code" type="hidden" value="${item.tag}"/>
						${item.content}<c:choose>
							<c:when test='${item.newPageNum == 1}'><a href="javascript:void(0);" data-new="new" onclick="sendCode(this);" <c:if test='${item.readStatus == 0}'>class="bold"</c:if>>查看</a></c:when>
							<c:otherwise><a href="javascript:void(0);" data-new="old" <c:if test='${item.readStatus == 0}'>class="bold"</c:if>>点击查看</a></c:otherwise>
						</c:choose>
					</p>
				</div>
				<div class="fr m-time">
					<em>${item.ago}</em>
				</div>
			</dd>
		</c:forEach>
		</dl>
	</td>
</tr>
<!--老师消息-->
<tr id="sm" style="display:none">
	<td>
		<c:if test="${empty teacherMessages}">
		<dl class="x-list">
		<dd>
			<div style="text-align: center;">
				目前暂时没有消息！
			</div>
		</dd>
		</dl>
	</c:if>
	</td>
	<td>
	<dl class="x-list">
		<c:forEach items="${teacherMessages}" var="message">
		<dd class="clearfix ${message.id}">
			<span class="fl"><img alt="头像" src="<avatar:avatar userId='${message.posterId}'/>"></span>
			<div class="fl text">
				<h4>
					${message.posterName}<i>老师</i>
				</h4>
				<p class="bold">${message.content}</p>
			</div>
			<div class="fr m-time">
				<em>${message.ago}</em>
				<p>
					<a href="javascript:reply('${message.id}');" class="huifu">回复 </a>|<a href="javascript:setReaded('${message.id}');">标记为已读</a>
				</p>
			</div>
		</dd>
	</c:forEach>
	</dl>
	</td>
</tr>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<script type="text/javascript">
	$(function(){
		$(".x-list").eq(0).children("dd").each(function(value,index){
 			var code = $(index).find("#code").val();
			var l=$(".navbar .nav li", window.top.document).length;
			for(var k=0;k<l-1;k++){
				var menu_top_id=$('.navbar .nav li', window.top.document).eq(k+1).attr("data-menu-id");
				var li_html=$(".panel-body", window.top.document).eq(k).children().children();
				var l1=li_html.length;
				for(var m=0;m<l1;m++){
					var menu_id=li_html.eq(m).attr("data-menu-id");
					var ul_html = li_html.eq(m).html() + "";
					if(ul_html.indexOf(code) > 0){
						
						var second_name=li_html.eq(m).children().children("span").text();
						$(index).find("h4").text(second_name);
					}
					if(menu_id === code){
						var second_name=li_html.eq(m).children().children("span").text();
						$(index).find("h4").text(second_name);
					}
				}
			}
		});
		var dataSize = "${fn:length(voList)}";
		if(dataSize == 0){
			$("#page_div").hide();
		}
		//消息切换
		$(".x-tab a").click(function(){
			var _num = $(this).index();
			$(this).addClass("on").siblings().removeClass("on");
			$("tr").eq(_num).show().siblings().hide();
			$(".x-tab .send-new").hide();
			$("#page_div").hide();
			if( _num == 0){
				if(dataSize != 0){
					$("#page_div").show();
				}
			}
			if( _num == 1){
				$(".x-tab .send-new").show();
			}
		});
		
		if('${param.teaMes}' == "true"){
			$("#teaMes").click();
		}
	});
	
	function sendCode(obj){
		//parent.parent.window.location.href = "/";
		//$(".man_right", window.top.document).css("margin-left","0");
		var code = $(obj).parent().children("#code").val();
		var urlCode = "/app?key=&targetDm="+code;
		window.open("${ctp}/app?key=&targetDm="+code);
	}
	
</script>