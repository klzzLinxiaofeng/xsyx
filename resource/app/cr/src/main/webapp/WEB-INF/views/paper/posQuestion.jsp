<%@ page language="java"
	import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style type="text/css">
.waveline {
    position: relative;
}
.waveline::before {
    content: '';
    position: absolute;
    bottom: -0.125em;
    width: 100%;
    height: 0.25em;
    background: -webkit-linear-gradient(315deg, transparent, transparent 45%, #000, transparent 55%, transparent 100%), -webkit-linear-gradient(45deg, transparent, transparent 45%, #000, transparent 55%, transparent 100%);
    background: linear-gradient(135deg, transparent, transparent 45%, #000, transparent 55%, transparent 100%), linear-gradient(45deg, transparent, transparent 45%, #000, transparent 55%, transparent 100%);
    background-size: 0.5em 0.5em;
    background-repeat: repeat-x, repeat-x;
}
</style>
<div class="timu layer-photos-demo_1" >
	<span style="display: inline-block;margin-bottom: 15px;line-height: 26px;"> (${vo.pos})<c:if test="${vo.parentId eq 0}">
	  ${vo.content}
	</c:if>
		<c:if test="${vo.parentId ne 0}">${vo.ecContent}<br>
		 (${vo.nodeOrder+1})${vo.content} 
		</c:if>
	</span>
	<div class="timu-choose">
<%-- 	<c:set value="${ fn:split(vo.answer, ',') }" var="str" /> --%>
		<c:choose>
			<c:when
				test="${vo.questionType eq 'checkbox' || vo.questionType eq 'radio'}">
				<ul>
					<c:forEach items="${vo.answers}" var="name" varStatus="status">
						<li><span class="xxqq" data-a="${status.index}"></span>${name }</li>
					</c:forEach>
				</ul>
			</c:when>
			<c:when test="${vo.questionType eq 'trueOrFalse' }">
				<ul>
					<c:forEach items="${vo.answers}" var="name" varStatus="status">
						<li><span  class="xxqq" data-a="-1"></span>${name }</li>
					</c:forEach>
				</ul>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
		<div class="answer-and-analysis">
			<div class="j1">
	                <span class="jx_wz">【答案】</span>
	                    
	                <div class=" jx_nr" >
	                    <c:if test="${vo.questionType ne 'blank'}">
	                	<p class="color1d9"> ${vo.correctAnswer}</p>
	                	</c:if>
	                	 <c:if test="${vo.questionType eq 'blank'}">
	                	 <c:forEach items="${vo.correctAnswers}" var="name">
	                	 <p class="color1d9"> ${name}</p>
	                	 </c:forEach>
	                	 </c:if>
					</div>
				</div>
				<div class="clear"></div>
			<div class="j2" style="display: inline-block;">
			    	<span class="jx_wz">【解析】</span>
			    	<div class="jx_nr">
			    		<p>${vo.explanation}<c:if test="${empty vo.explanation }">无</c:if></p>
			    		
			    	</div>
			    </div>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
		var zm = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
		$(".xxqq").each(function(){
			var index = $(this).data("a");
			if(index!=-1){
			$(this).text(zm[index]);
			}
		});
	});
		$("span").each(function () {
	        var style = $(this).attr("style");
	        if(style != undefined && style != ""){
	            if(style.indexOf("symbol:waveline") != -1){
	                 $(this).addClass("waveline");
	            }
	        }
	    })
	    layer.ready(function(){ //为了layer.ext.js加载完毕再执行
	     layer.photos({
	    	shade:0.5,
	    	/* area:'auto',
	    	maxWidth:"600", */
	    	shift : 5,//3.0之前版本设置动画的属性
	        photos: '.dt_main .layer-photos-demo_1'
	    }); 
	    layer.photos({
	    	shade:0.5,
	    	/* area:'auto',
	    	maxWidth:"600", */
	    	shift : 5,//3.0之前版本设置动画的属性
	        photos: '.bjwcl_pmb .layer-photos-demo_1'
	    }); 
	});
	</script>