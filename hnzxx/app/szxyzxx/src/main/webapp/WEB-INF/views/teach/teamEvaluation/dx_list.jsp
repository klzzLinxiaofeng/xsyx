<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>

<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<style>
	.table{border:1px solid #ddd}
	.table tbody tr.grey_1 td{background-color:#f9f9f9}
	.table tr{height:42px;}
	.table tbody tr.grey_1 td{background-color:#f9f9f9}
	.project-rated p{color:#535353;line-height:25px;margin:20px 0 10px;position:relative;}
	.project-rated p span{display:block;color:#8E8C8C;font-size:12px;}
	.project-rated p .go_back{position:absolute;width:70px;height:30px;line-height:30px;font-size:12px;display:block;background-color:#A3ABAF;color:#fff;text-align:center;left:5px;bottom:5px;}
</style>
<script>
	$(function(){
		$(".points table tbody tr").hover(function() {
	        var i=$(this).index();
	        for(var j=0;j<3;j++){
	        	$(this).parent().parent().eq(j).children("tbody").children("tr").eq(i).addClass("grey_1");
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
	            
	            $(".NaN_add").keyup(function(){
	            	if($(this).val()>100 && $(this).val()!=1000){
	 		    		$(this).val($(this).val().substr(0, 2));
	 		    	}else if($(this).val()==1000){
	 		    		$(this).val($(this).val().substr(0, 3));
	 		    	}else  if($(this).val()=="."||$(this).val()=="-."){
			    		$(this).val("0.");
			    	}
	            	this.value = this.value.replace(/[^\d.]/g, "");
	                //必须保证第一个为数字而不是.
	                this.value = this.value.replace(/^\.{2}/g, "");
	                //保证只有出现一个.-而没有多个.
	                this.value = this.value.replace(/\.{2,}/g, ".");
	                //保证.-只出现一次，而不能出现两次以上
	                this.value = this.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
	                /* 小数点后只能一位 */
	                var arr = $(this).val().split(".");
			     	if(arr[1].length>1){
			     		$(this).val($(this).val().substring(0,$(this).val().length-(arr[1].length-1)))
			     	}
	            });
			    $(".NaN_add").blur(function(){
			    	$( ".NaN_add" ).each(function( index ) {
			    		  if(Math.abs($(this).val())=="0"){
			    			  $(this).val("");
			    		  }
			    		});
			      });
			    $(".NaN_reduce").keyup(function(){
			        if($(this).val()==""){
			    		$(this).val("-")
			    	}
			         if($(this).val()>0){
			    		$(this).val(-$(this).val());
			    	} 
			         if($(this).val()<-100 && $(this).val()!=-1000){
		 		    		$(this).val($(this).val().substr(0, 3));
		 		    	} else if($(this).val()==-1000){
		 		    		$(this).val($(this).val().substr(0, 4));
		 		    	}
			         if($(this).val()=="."||$(this).val()=="-."){
				    		$(this).val("-0.");
				    	} 
			         if(isNaN($(this).val())){
			        	 var arj = $(this).val().split("-");
			        	 $(this).val("-"+arj[1]);
			         }
			    	this.value = this.value.replace(/[^\-\d.]/g, "");
			        //必须保证第一个为数字而不是.
			        this.value = this.value.replace(/^\.{2}/g, "");
			        //保证只有出现一个.-而没有多个.
			        this.value = this.value.replace(/\.{2,}/g, ".");
			        this.value = this.value.replace(/\-{2,}/g, "-");
			        //保证.-只出现一次，而不能出现两次以上
			        this.value = this.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
			        this.value = this.value.replace("-", "$#$").replace(/\-/g, "").replace("$#$", "-");
			        /* 小数点后只能一位 */
			        var arr = $(this).val().split(".");
			     	if(arr[1].length>1){
			     		$(this).val($(this).val().substring(0,$(this).val().length-(arr[1].length-1)))
			     	}
			    });
			    $(".NaN_reduce").keydown(function(){
			    	if($(this).val()=="0"){
			    		$(this).val("-")
			    	}
			    })
			    $(".NaN_reduce").click(function(){
			    	if($(this).val()==""){
			    		$(this).val("-")
			    	}
			    })
			    $(".NaN_reduce").blur(function(){
			    	$( ".NaN_reduce" ).each(function( index ) {
			    		  if($(this).val()=="-"||Math.abs($(this).val())=="0"){
			    			  $(this).val("");
			    		  }
			    		});
			      });
	})
</script>
<p><a href="javascript:void(0)" class="go_back">< 返回</a>${item.name}<span>${msg }</span></p>
<input type="hidden" id="danx" value="${item.id }">
	<table class="responsive table reflective-evaluate left_table"
				style="width: 70px; position: absolute; z-index: 1">
				<thead style="background: #daf0fb;">
					<tr role="row" style="color: #4c708a;">
						<th>班级</th>
					</tr>
				</thead>
				<tbody id="module_list_content">
				<c:forEach items="${teams}" var="team">
					<tr class="">
						<td>${team.teamNumber }班</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>

			<div style="position: absolute; left: 70px; margin-right: 305px; overflow: hidden"
				class="table_div">
				<table
					class="responsive table table-limit reflective-evaluate">
					<thead style="background: #daf0fb;">
						<tr role="row" style="color: #4c708a;" class="middle_table">
						<input type="hidden" id="itemName" value="${item.name }">
								<th>${item.name}</th>
						</tr>
					</thead>
					<tbody id="module_list_content" class="dx">
						<c:forEach items="${teamScore}" var="team">
							<tr class="">
							<c:choose>
								<c:when test="${reduceList.size()>0}">
										<c:choose>
											<c:when test="${team.score!=0.0}">
												<td><input type="text" value="${team.score}" class="NaN_reduce"></td>
											</c:when>
											<c:otherwise>
												<td><input type="text" class="NaN_reduce"></td>
											</c:otherwise>
										</c:choose>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${team.score!=0.0}">
											<td><input type="text" value="${team.score}" class="NaN_add"></td>
										</c:when>
										<c:otherwise>
											<td><input type="text" class="NaN_add"></td>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<table class="responsive table reflective-evaluate"
				id="data-table"
				style="width: 100%; position: relative; right: 0; top: 0;">
				<thead style="background: #daf0fb;">
 					<tr role="row" style="color: #4c708a;"> 
						<th class="caozuo" style="padding:0">
							<!-- <button onclick="addCheck()">添加检查项</button> 
							<a href="javascript:void(0);" class="turn_right">>></a>
							<a href="javascript:void(0);" class="turn_left"><<</a> -->
						</th>
					</tr> 
				</thead>
				<tbody id="module_list_content">
				<c:forEach items="${teams}" var="team">
					<tr class="">
						<td></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
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
	$(".go_back").click(function(){
		$(".dan_all").show();
		$(".dan_one").hide();
   })
 
})





</script>