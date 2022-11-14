<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<%@ include file="/views/embedded/common.jsp"%>

<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/css/dygl/dygl.css" rel="stylesheet">
<style>
	.evaluate-action{
    width: 140px;
    border-right: 1px solid #acb0b1;
}
.evaluate-action a{
    width: 120px;
    font-weight:bold;
    margin:0;
    padding:10px;
    border-bottom: 1px solid #D5D7D8;
}
.evaluate-div {
    margin-left: 200px;
}
a.evaluate-confirm{
    color: #ffffff;
    background-color: #125D97;
    text-align:center;
    font-weight: bold;
    border-radius: 0;
}
</style>
<script type="text/javascript">
$(function(){
/*鼠标移入左边浮现删除*/
$(".evaluate-projects ").on("mouseover mouseout","ul li",function(event){
 if(event.type == "mouseover"){
   $(this).find(".reveal").show();
 }else if(event.type == "mouseout"){
  $(this).find(".reveal").hide();
 }
})
/*删除这个li*/
    $(".evaluate-projects").on("click","ul li a",function(){
        var id=$(this).parent().data("id");
        console.log(id);
        $("#"+id).removeClass("on");
        $(this).parent().remove();
    });
/*右边选中取消*/
     $(".all-class-students ul li").click(function(){
        $(this).toggleClass("on");
    });
     /*右边内容过来*/
     $(".evaluate-aleft a").click(function(){
        var l;
        for(l=0;l<$(".evaluate-action a").length;l++){
            if($(".evaluate-action a").eq(l).hasClass("evaluate-confirm")){
                break;
            }
        }
        
        var s_lenght=$(".all-class-students .on").length;
            $(".prize-student").eq(l).children("ul").empty();
        for(var m=0;m<s_lenght;m++){
            var touxiang=$(".all-class-students .on").eq(m).children("img").attr("src");
            var name=$(".all-class-students .on").eq(m).children("p").text();
            var id=$(".all-class-students .on").eq(m).attr('id');
            $(".prize-student").eq(l).children("ul").append("<li data-id="+id+"><img src='"+touxiang+"'><p>"+name+"</p><a href='javascript:void(0);' class='reveal'><img src='${pageContext.request.contextPath}/res/css/dygl/images/evaluate_delete.png'></a></li>");
        }
     })

    $(".evaluate-div").children(".prize-student").eq(0).show();
    $(".evaluate-action a").click(function(){
        var j=$(this).index();
        $(this).siblings().removeClass("evaluate-confirm");
        $(this).addClass("evaluate-confirm");
        $(this).parent().next().children(".prize-student").hide();
        $(this).parent().next().children(".prize-student").eq(j).show();
        var s_shu=$(this).parent().next().children(".prize-student").eq(j).children("ul").children("li").length;
            $(".all-class-students ul li").removeClass("on");
        if(s_shu!=0){
            for(var n=0;n<s_shu;n++){
                var id=$(this).parent().next().children(".prize-student").eq(j).children("ul").children("li").eq(n).data("id");
                $("#"+id).addClass("on");
            }
        }
    })
});
</script>

	<div class="card_detail">
		<div class="get-card-student">
			<div class="evaluate-projects">
			<div class="evaluate-action" id="evaluate_action1">
				<c:forEach items="${dataList }" var="item" varStatus="index">
					<a href="javascript:void(0);" <c:if test="${index.first}">class="evaluate-confirm"</c:if> >${item.itemName}</a>			
				</c:forEach>
			</div>
			
			<div class="evaluate-div">
				<h3>获卡学生</h3>
				<c:forEach items="${dataList }" var="item" varStatus="index">
	                <div class="prize-student" >
	                	<input type="hidden" name="itemId" value="${item.itemId }">
	                    <ul>
	                    	<c:forEach items="${item.studentDates }" var="student">
	                    	 	<li id="${student.studentId }">
	                    	 		
	                    	 	</li>
	                    	 </c:forEach>
	                    </ul>
	                </div>	
				</c:forEach>
           </div>
           </div>
           </div>
	</div>
            
    <div class="evaluate-aleft">
    <a href="javascript:void(0);"></a>
	</div>
    <div class="all-class-students">
        <h3>全部班级学生</h3>
            <ul>
            	<c:forEach items="${studentList }" var="student">
	            	<li id="${student.studentId }">
	            		<img src="<avatar:avatar userId='${student.userId }'></avatar:avatar>"/>
						<p>${student.name}</p>
						<a href="javascript:void(0);" class="students-inside">
							<img src="${pageContext.request.contextPath}/res/css/dygl/images/evaluate_selected.png">
						</a>
	            	</li>
            	</c:forEach>
            </ul>
    </div>
    <div class="clear"></div>


<script type="text/javascript">
$(function(){
	
	$(".prize-student").each(function(){
		$(this).children("ul").children("li").each(function(){
			var studentId = $(this).attr("id");
			var $li = $(this);
			$(".all-class-students").children("ul").children("li").each(function(){
				var id = $(this).attr("id");
				if(studentId == id){
					var touxiang = $(this).children("img").attr("src");
		            var name = $(this).children("p").text();
		            var id = $(this).attr("id");
					$li.parent().append("<li data-id="+id+"><img src='"+touxiang+"'><p>"+name+"</p><a href='javascript:void(0);' class='reveal'><img src='${pageContext.request.contextPath}/res/css/dygl/images/evaluate_delete.png'></a></li>");
					$li.remove();
					return;
				}
			});
			
		});
 	});
	
	var s_shu=$(".prize-student").eq(0).children("ul").children("li").length;
	if(s_shu!=0){
	    for(var n=0;n<s_shu;n++){
	        var id=$(".prize-student").eq(0).children("ul").children("li").eq(n).data("id");
	        $("#"+id).addClass("on");
	    }
	}

	if(itemName != null && itemName != ""){
		$(".evaluate-action a").each(function(){
			if($(this).text() == itemName){
				var j=$(this).index();
		        $(this).siblings().removeClass("evaluate-confirm");
		        $(this).addClass("evaluate-confirm");
		        $(this).parent().next().children(".prize-student").hide();
		        $(this).parent().next().children(".prize-student").eq(j).show();
		        var s_shu=$(this).parent().next().children(".prize-student").eq(j).children("ul").children("li").length;
		            $(".all-class-students ul li").removeClass("on");
		        if(s_shu!=0){
		            for(var n=0;n<s_shu;n++){
		                var id=$(this).parent().next().children(".prize-student").eq(j).children("ul").children("li").eq(n).data("id");
		                $("#"+id).addClass("on");
		            }
		        }
			}
		});
		itemName = null;
	}
	
});

</script>

