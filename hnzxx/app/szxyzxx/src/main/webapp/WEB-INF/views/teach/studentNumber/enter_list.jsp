<%@ page language="java"
	import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/css/dygl/dygl.css" rel="stylesheet">
<style>
	.pj_Table .pj_bottom {
	    max-height: 94200px;
	    overflow: auto;
	        overflow-x: auto;
	        overflow-y: auto;
	}
</style>
</head>
<script type="text/javascript">
$(".td-spacing").keyup(function(){
	/* if($(this).val()>100 && $(this).val()!=1000){
 		$(this).val($(this).val().substr(0, 2));
 	}else if($(this).val()==1000){
 		$(this).val($(this).val().substr(0, 3));
 	}else  if($(this).val()=="."){
		$(this).val("");
	} */
	this.value = this.value.replace(/[^\d.]/g, "");
    //必须保证第一个为数字而不是.
    this.value = this.value.replace(/^\.{2}/g, "");
    //保证只有出现一个.-而没有多个.
    this.value = this.value.replace(/\.{2,}/g, ".");
    //保证.-只出现一次，而不能出现两次以上
    this.value = this.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", "");
    /* 小数点后只能一位 */
    var arr = $(this).val().split(".");
 	if(arr[1].length>1){
 		$(this).val($(this).val().substring(0,$(this).val().length-(arr[1].length-1)))
 	}
});
$(".td-spacing").blur(function(){
	$( ".td-spacing" ).each(function( index ) {
	  if(Math.abs($(this).val())=="0"){
		  $(this).val("");
	  }
	});
});

</script>
	<body>
	<div class="pj_Table">
    	<div class="pj_top"><ul><li>用户名</li><li>姓名</li><li>学号</li></ul></div>
    	<div class="pj_bottom">
    		<ul>
    		<c:forEach items="${studentVoList}" var="student">
					<li class="data">
					<p>${student.userName }</p>
						<p>${student.name }</p>
						<c:choose>
							<c:when test="${student.number==null}">
								<p>
									<input id="studentId" type="hidden" value="${student.id }">
									<input type="text" class="td-spacing">
								</p>
							</c:when>
							<c:otherwise>
								<p>
									<input id="studentId" type="hidden" value="${student.id }">
									<input type="text" value="${student.number }" class="td-spacing">
								</p>
							</c:otherwise>
						</c:choose>
					</li>
				</c:forEach>
    		</ul>
    	</div>
    </div>
	</body>
</html>