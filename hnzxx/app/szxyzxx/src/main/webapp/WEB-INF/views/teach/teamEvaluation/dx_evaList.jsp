<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>

<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<style>
	.table{border:1px solid #ddd}
	.table tbody tr.grey_1 td{background-color:#f9f9f9}
	.table tr{height:42px;}
	.project-rated p{color:#535353;line-height:25px;margin:20px 0 10px;position:relative;}
	.project-rated p span{display:block;color:#8E8C8C;font-size:12px;}
	.project-rated p .go_back{position:absolute;width:70px;height:30px;line-height:30px;font-size:12px;display:block;background-color:#A3ABAF;color:#fff;text-align:center;left:5px;bottom:5px;}
</style>
<script>
	$(function(){
		$(".points table tbody tr").hover(function() {
	        var i=$(this).index();
	        for(var j=0;j<3;j++){
	             $(".points table").eq(j).children("tbody").children("tr").eq(i).addClass("grey_1");
	        }
	    }, function() {
	        $(".points table tbody tr").removeClass("grey_1");
	    });
		for(var l=0;l<2;l++){
	        var k=$(".middle_table").eq(l).children("th").length;
	        var z_width=$(".card_detail").width()-355;
	        if(k*126>z_width){
	            $(".table_div").eq(l).css("width",z_width);
	            $(".table th.caozuo .turn_right").eq(l).addClass("turn_blue");
	        }else{
	            $(".table_div").eq(l).css("width",k*126);
	        }
	    }
	    $(window).resize(function(){
	         for(var l=0;l<2;l++){
	            var k=$(".middle_table").eq(l).children("th").length;
	            var z_width=$(".card_detail").width()-355;
	            if(k*126>z_width){
	            $(".table_div").eq(l).css("width",z_width);
	        }else{
	            $(".table_div").eq(l).css("width",k*127);
	        }
	    }
	    });
	    /* $(".turn_left").click(function(){
	    	var $_this=$(this).parent().parent().parent().parent().prev().children();
	         var table_left=$_this.css("left");
	         if(parseInt(table_left)<0){
	        	 $_this.animate({
		                left:"+=127px"  
		            },65);
	        //$_this.css("left",parseInt(table_left)+127);
	        $(this).prev().addClass("turn_blue");
	         }
	         if(parseInt(table_left)==-127){
	        	$(this).removeClass("turn_blue");
	          }
	    })
	    $(".turn_right").click(function(){
	    	 var $_this=$(this).parent().parent().parent().parent().prev().children();
		        var table_left=$_this.css("left");
		        var k=$_this.children().children().children("th").length;
		        var z_width=$(".card_detail").width()-355;
		        var gs=parseInt(z_width/127)
		        if(parseInt(table_left)>-(k-gs)*127){
		       // $_this.css("left",parseInt(table_left)-127);
		        $_this.animate({
	                left:"-=127px"  
	            },65);
		        $(this).next().addClass("turn_blue");
		        }
		        if(parseInt(table_left)==((1-k+gs)*127)){
		       		$(this).removeClass("turn_blue");
		         }
	    }) */
	})
</script>

<div class="plus-minus-rated" style="border: 1px solid #ddd;">
		<a href="javascript:void(0);" class="current-rated jia"
			style="border-right: 1px solid #ddd;"><span class="bj">扣分项</span></a> 
		<a href="javascript:void(0);" class="jian"><span class="bj">加分项</span></a>
	</div>
	<div class="clear"></div>
	<div class="dan_all">
	<div class="points-content" >
		<div class="points" style="position: relative;">
			<ul>
				<c:forEach items="${reduceItems}" var="reduce">
					<c:choose>
						<c:when test="${reduce.listOrder=='0' }">
							<li><a href="javascript:void(0)" onclick="list(${reduce.id},'${reduce.name }')" class="new_img"><img src="${pageContext.request.contextPath}/res/css/dygl/images/icon/17.png" alt="新增项">${reduce.name }</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="javascript:void(0)" onclick="list(${reduce.id},'${reduce.name }')" class="old_img"><img src="${pageContext.request.contextPath}/res/css/dygl/images/icon/${reduce.code }.png" alt="${reduce.name }">${reduce.name }</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${!isOnDuty}">
					<li><a href="javascript:void(0)" class="add_img"><img src="${pageContext.request.contextPath}/res/css/dygl/images/icon/16.png" alt="新增项" onclick="addCheck()">新增项</a></li>
				</c:if>
			</ul>
			<div class="clear"></div>
		</div>
	</div>

	<div class="points-content">
		<div class="points" style="position: relative;">
			
			<ul>
				<c:forEach items="${addItems}" var="add">
					<c:choose>
						<c:when test="${add.listOrder=='0' }">
							<li><a href="javascript:void(0)" onclick="list(${add.id},'${add.name }')" class="new_img"><img src="${pageContext.request.contextPath}/res/css/dygl/images/icon/17.png" alt="新增项">${add.name }</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="javascript:void(0)" onclick="list(${add.id},'${add.name }')" class="old_img"><img src="${pageContext.request.contextPath}/res/css/dygl/images/icon/${add.code }.png" alt="${add.name }">${add.name }</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${!isOnDuty}">
					<li><a href="javascript:void(0)" class="add_img" onclick="addCheck()"><img src="${pageContext.request.contextPath}/res/css/dygl/images/icon/16.png" alt="新增项">新增项</a></li>
				</c:if>
			</ul>
			<div class="clear"></div>
			
		</div>
	</div>
</div>
<div style="position:relative;display:none" class="dan_one" >

</div>
<script type="text/javascript">

function addCheck(){
	var gradeId = $("#nj").val();
	var termCode = $("#xq").val();
	var checkDate = $("#startDate").val();
	var year = $("#xn").val();
	
	$.initWinOnTopFromTop('添加集体评价检查项',
			"${pageContext.request.contextPath}/teach/teamEvaluation/input?termCode="+termCode+"&year="+year+"&gradeId="+gradeId+"&checkDate="+checkDate,'800','500');
}






$(function(){
    $(".points-content").eq(0).show();
    $(".points-content").eq(2).show();
    $(".minutes-rated a").click(function(){
        $(".minutes-rated a").removeClass("see-rated");
        $(this).addClass("see-rated");
        var i=$(this).index();
//         $(".project-rated").hide();
//         $(".project-rated").eq(i).show();
        // if($(".project-rated").eq(i).children(".plus-minus-rated").children("a").eq(0).hasClass("current-rated")){
        //     $(".project-rated").eq(i).find(".points").eq(0).show();
        // }
    });
    $(".plus-minus-rated a").click(function(){
        var j=$(this).index();
        $(this).siblings().removeClass("current-rated");
        $(this).addClass("current-rated");
        $(this).parent().parent().children().children(".points-content").hide();
        $(this).parent().parent().children().children(".points-content").eq(j).show();
    });
    for(var l=0;l<2;l++){
        var k=$(".middle_table").eq(l).children("th").length;
        var z_width=$(".card_detail").width()-355;
        if(k*126>z_width){
            $(".table_div").eq(l).css("width",z_width);
        }else{
            $(".table_div").eq(l).css("width",k*126);
        }
    }
   /*  新增的图标按首字 */
    $(".new_img").each(function(){
    	$("<span>"+$(this).text().slice(0,1)+"</span>").appendTo($(this))
    })
    /* 点击图标弹出详细 */
    $(".old_img,.new_img").click(function(){
    	$(".dan_all").hide();
    	$(".dan_one").show();
    });
    $(".jia,.jian").click(function(){
    	$(".dan_all").show();
		$(".dan_one").hide();
    })
});
</script>