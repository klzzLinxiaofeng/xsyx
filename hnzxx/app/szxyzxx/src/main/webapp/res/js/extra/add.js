$(function(){
	//导航
   $("#navigation li").click(function(){
		$(this).addClass("active").siblings().removeClass("active");
	});
 //tab 切换  
$(".x-head a").click(function(){
	$(this).addClass("on").siblings().removeClass("on");
	});
	//星星等级评分  
//	$(".x-star i").click(function(){
//		$(".x-star i").removeClass("active");
//		$(this).addClass("active").prevAll().addClass("active");
//	});
	//搜索文字清空  
	$(".icon-remove").click(function(){$(".input-medium").val('');});
	
//		多校区选择
	$("body").on("click",".drop_whole a",function(){
	            $(".drop_choice").toggle();
	            $(".drop_whole b").toggleClass("hide_btn");
	        });
	        $("body").on("click",".drop_confirm input",function(){
	            var dxq="",id="";
	            $( ".drop_choice ul li" ).each(function( index ) {
	              if($(this ).children("input").is(':checked' )){
	            	  if(dxq==""||dxq==null){
	            		  id=id+$(this ).children("p").attr("value-id")
	            	  }else{
	            		  id=id+","+$(this ).children("p").attr("value-id")
	            	  }
	            	  if(dxq==""||dxq==null){
	            		  dxq=dxq+$(this ).children("p").text();
	            	  }else{
	            		  dxq=dxq+","+$(this ).children("p").text();
	            	  }
	             }
	            });
	            if(dxq!=""||dxq!=null){
	                $(".school_id").val(id);
	            }
	            if(id==""||id==null){
	                $(".drop_box").text("请选择校区");
	                $(".drop_box").attr("title","请选择校区")
	            }else{
	                 $(".drop_box").text(dxq);
	                  $(".drop_box").attr("title",dxq);
	            }
	            $(".drop_choice").toggle();
	            $(".drop_whole b").toggleClass("hide_btn");
	        })
});