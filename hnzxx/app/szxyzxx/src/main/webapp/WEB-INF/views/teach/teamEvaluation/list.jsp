<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="/views/embedded/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<style>
	.table td{font-size:12px;}
	.caozuo img{
	height:30px;
	width:45px;
	margin-right:6px;
	float:right;
}
.table th.caozuo, .table td.caozuo{
	width:150px;
}
</style>
</head>
<script type="text/javascript">
$(function(){
    $(".points-content").eq(0).show();
    $(".points-content").eq(2).show();
    $(".minutes-rated a").click(function(){
        $(".minutes-rated a").removeClass("see-rated");
        $(this).addClass("see-rated");
        var i=$(this).index();
        $(".project-rated").hide();
        $(".project-rated").eq(i).show();
        // if($(".project-rated").eq(i).children(".plus-minus-rated").children("a").eq(0).hasClass("current-rated")){
        //     $(".project-rated").eq(i).find(".points").eq(0).show();
        // }
    });
    $(".plus-minus-rated a").click(function(){
        var j=$(this).index();
        $(this).siblings().removeClass("current-rated");
        $(this).addClass("current-rated");
        $(this).parent().parent().children(".points-content").hide();
        $(this).parent().parent().children(".points-content").eq(j).show();
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
    $(".turn_left").click(function(){
                var $_this=$(this).parent().parent().parent().parent().prev().children();
                var table_left=$_this.css("left");
                var k=$_this.children().children().children("th").length;
                var z_width=$(".card_detail").width()-355;
                var gs=parseInt(z_width/127)
                if(parseInt(table_left)>-(k-gs)*127){
                $_this.css("left",parseInt(table_left)-127);
                }
            })
            $(".turn_right").click(function(){
                var $_this=$(this).parent().parent().parent().parent().prev().children();
                 var table_left=$_this.css("left");
                 if(parseInt(table_left)<0){
                $_this.css("left",parseInt(table_left)+127);
                 }
            })
            
            $(".NaN_add").keyup(function(){
            	this.value = this.value.replace(/[^\?\d.]/g, "");
                //???????????????????????????????????????.
                this.value = this.value.replace(/^\.{2}/g, "");
                //????????????????????????.-???????????????.
                this.value = this.value.replace(/\.{2,}/g, ".");
                //??????.-?????????????????????????????????????????????
                this.value = this.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
            });
		    $(".NaN_reduce").keyup(function(){
		        if($(this).val()==""){
		    		$(this).val("-")
		    	}
		    	this.value = this.value.replace(/[^\-?\d.]/g, "");
		        //???????????????????????????????????????.
		        this.value = this.value.replace(/^\.{2}/g, "");
		        //????????????????????????.-???????????????.
		        this.value = this.value.replace(/\.{2,}/g, ".");
		        this.value = this.value.replace(/\-{2,}/g, "-");
		        //??????.-?????????????????????????????????????????????
		        this.value = this.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
		        this.value = this.value.replace("-", "$#$").replace(/\-/g, "").replace("$#$", "-");
		    });
		    $(".NaN_reduce").keydown(function(){
		    	if($(this).val()==""){
		    		$(this).val("-")
		    	}
		    })
		    $(".NaN_reduce").click(function(){
		    	if($(this).val()==""){
		    		$(this).val("-")
		    	}
		    })
});


</script>
<body>
<p>${msg }</p>
					<div class="plus-minus-rated">
                                <a href="javascript:void(0);" class="current-rated" style="border-right: 1px solid #ddd;">???????????????<span class="red">${minusnum}</span>???</a>
                                <a href="javascript:void(0);">???????????????<span style="color:#2399dc;">${addnum}</span>???</a>
                            </div>
                            <div class="points-content">
                            <div class="points">
                            <table class="responsive table table-striped" >
                            <thead>
                                <tr role="row">
                                   <th>??????</th>
                                   <th>?????????</th>
                                   <th>?????????</th>
                                   <th>????????????</th>
                                   <th>????????????</th>
                                   <th class="remarks">??????</th>
                                   <th class="caozuo">??????</th>
                                </tr>
                            </thead>
                            <tbody id="layer-photos-jia">
                            <c:forEach items="${minusitem}" var="item" >
                            <tr>
                            <td><fmt:formatDate value="${week}" pattern="E" /></td>
                            <td> ${item.itemName}</td>
                            <td><span> ${item.score}</span></td>
                            <td><span>${item.teacherName}</span></td>
                            <td>${item.judgeDate}</td>
                            <td>${item.remark }</td>
                            <td class="caozuo">
                            	<c:forEach items="${item.fileUUIDs}" var="uuid" >
                            		<img src="<entity:getHttpUrl uuid='${uuid}'/>">
                            	</c:forEach>
                            </td>
                            </tr>
                            
                            </c:forEach>
     
                                 <tr style="font-weight:bold;">
                                     <td>?????????</td>
                                     <td>??????<span>${minusnum}</span>???</td>
                                     <td colspan="5">???????????????<span>
                                     <fmt:formatNumber type="number" value="${minussum}" pattern="0.0" />
                                     </span>???</td>
                                 </tr>
                            </tbody>
                        </table>
                        </div>
                        </div>

                        <div class="points-content">
                            <div class="points">
                            <table class="responsive table table-striped" >
                            <thead>
                                <tr role="row">
                                   <th>??????</th>
                                   <th>?????????</th>
                                   <th>?????????</th>
                                   <th>????????????</th>
                                   <th>????????????</th>
                                   <th class="remarks">??????</th>
                                   <th class="caozuo">??????</th>
                                </tr>
                            </thead>
                            <tbody id="layer-photos-jian">
                            <c:forEach items="${additem}" var="item" >
                            <tr>
                            <td><fmt:formatDate value="${week}" pattern="E" /></td>
                            <td> ${item.itemName}</td>
                            <td><span> ${item.score}</span></td>
                            <td><span>${item.teacherName}</span></td>
                            <td>${item.judgeDate}</td>
                            <td>${item.remark }</td>
                            <td class="caozuo">
                            	<c:forEach items="${item.fileUUIDs}" var="uuid" >
                            		<img src="<entity:getHttpUrl uuid='${uuid}'/>">
                            	</c:forEach>
                            </td>
                            </tr>
                            
                            </c:forEach>
                                
                                 <tr style="font-weight:bold;">
                                     <td>?????????</td>
                                     <td>??????<span>${addnum}</span>???</td>
                                     <td colspan="5">???????????????<span><fmt:formatNumber type="number" value="${addsum}" pattern="0.0" /></span>???</td>
                                 </tr>
                            </tbody>
                        </table>
                        </div>
                        </div>

   <script type="text/javascript">
layer.ready(function(){ //??????layer.ext.js?????????????????????
	  layer.photos({
	    photos: '#layer-photos-jia'
	  });
	  layer.photos({
		    photos: '#layer-photos-jian'
		  });
	}); 
</script>           
</body>

</html>