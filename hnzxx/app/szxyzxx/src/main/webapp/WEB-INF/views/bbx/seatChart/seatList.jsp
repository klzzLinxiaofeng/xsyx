<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<span><p>讲台</p></span>
<input type="hidden" id="seatId" name="seatId" value="${seatId }" />
<ul id="seat">
<c:forEach items="${list }" var="seat" varStatus="i">
	<li class='li'>
		<c:forEach items="${seat }" var="item" varStatus="j">
			<a id="${i.index }_${j.index }" class="drop">
				<ul style='margin:0'>
					<c:if test="${item!=null }">
						<c:choose>
							<c:when test="${item.studentSex==2 }">
								<li data-obj-id="${item.studentId}" data-obj-name="${item.studentName}" class='female item'><img src="<avatar:avatar userId='${item.userId }'></avatar:avatar>"/><p>${item.studentName }</p></li>
							</c:when>
							<c:otherwise>
								<li data-obj-id="${item.studentId}" data-obj-name="${item.studentName}" class='male item'><img src="<avatar:avatar userId='${item.userId }'></avatar:avatar>"/><p>${item.studentName }</p></li>
							</c:otherwise>
						</c:choose>
					</c:if>
				</ul>
			</a>
		</c:forEach>
	</li>
</c:forEach>
</ul>

<script>
$('.male,.female').draggable({
    revert:true,
});
$('.left .drop').droppable({
    onDrop:function(e,source){
    	var aa=$(this).find('li').attr('class');
		if(aa=='male item assigned'||aa=='female item assigned'){
			$("#number").html($("#student li").length)
			$.alert('已经存在');
        } else {
            var c = $(source).addClass('assigned');
            $(this).empty().append(c);
            c.draggable({
                revert:true
            });
            $("#number").html($("#student li").length);
        }
    }
});
$('.right').droppable({
    onDrop:function(r,source){
    	
    	//alert($("#student div").length+1)
        if ($(source).hasClass('set')){
            $(this).children("ul").append(source);
            $("#number").html($("#student li").length);
        } else {
            var c = $(source).addClass('set');
            $(this).children("ul").append(c);
            c.draggable({
                revert:true
            });
            $("#number").html($("#student li").length);
        }
    }
});
</script>