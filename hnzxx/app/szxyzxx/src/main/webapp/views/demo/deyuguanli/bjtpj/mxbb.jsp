<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>明细报表</title>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<script type="text/javascript">
$(function(){
//     $(".points-content").eq(0).show();
//     $(".points-content").eq(2).show();
//     $(".minutes-rated a").click(function(){
//         $(".minutes-rated a").removeClass("see-rated");
//         $(this).addClass("see-rated");
//         var i=$(this).index();
//         $(".project-rated").hide();
//         $(".project-rated").eq(i).show();
        // if($(".project-rated").eq(i).children(".plus-minus-rated").children("a").eq(0).hasClass("current-rated")){
        //     $(".project-rated").eq(i).find(".points").eq(0).show();
        // }
//     });
//     $(".plus-minus-rated a").click(function(){
//         var j=$(this).index();
//         $(this).siblings().removeClass("current-rated");
//         $(this).addClass("current-rated");
//         $(this).parent().parent().children(".points-content").hide();
//         $(this).parent().parent().children(".points-content").eq(j).show();
//     });
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
                //必须保证第一个为数字而不是.
                this.value = this.value.replace(/^\.{2}/g, "");
                //保证只有出现一个.-而没有多个.
                this.value = this.value.replace(/\.{2,}/g, ".");
                //保证.-只出现一次，而不能出现两次以上
                this.value = this.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
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
		         if($(this).val()=="."||$(this).val()=="-."){
			    		$(this).val("-0.");
			    } 
		         if(isNaN($(this).val())){
		        	 var w=$(this).val().indexOf("-")
		        	 this.value = this.value.substring(w) 
			    	} 
		    	this.value = this.value.replace(/[^\-?\d.]/g, "");
		        //必须保证第一个为数字而不是.
		        this.value = this.value.replace(/^\.{2}/g, "");
		        //保证只有出现一个.-而没有多个.
		        this.value = this.value.replace(/\.{2,}/g, ".");
		        this.value = this.value.replace(/\-{2,}/g, "-");
		        //保证.-只出现一次，而不能出现两次以上
		        this.value = this.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
		        this.value = this.value.replace("-", "$#$").replace(/\-/g, "").replace("$#$", "-");
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
});
</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="评价报表" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head" style="margin-bottom: 15px;">
						<h3>
							评价报表
							<p class="btn_link" style="float: right;line-height:47px;margin-right:10px;margin-top: 5px;">
									<a href="javascript:void(0)" class="a3" onclick="$.refreshWin();"><i class="fa  fa-arrow-down"></i>导出</a>
									</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
							<div class="check-rated">
							<div class="minutes-rated">
                            <a href="javascript:void(0);">评价报表</a>
                            <a href="javascript:void(0);" class="see-rated">评价报表明细</a>
                        </div>
                        <div class="card_detail">
                        <div class="project-rated">
                            <p>2016-2017学年上学期14周-星期三     日常规评比报表</p>
							<div class="mxbb">
								<table border="0" cellpadding="0" cellspacing="1">
									<thead>
										<tr>
											<th style="width: 130px;">
												<div class="out">
								                    <b>项目</b> <em>班级</em>
								                </div>
								            </th>
											<th>晨诵读</th>
											<th>午习字</th>
											<th>课前准备</th>
											<th>课堂巡视</th>
											<th>眼保健操</th>
											<th>跑操</th>
											<th>晨练</th>
											<th>放学路队</th>
											<th>课间活动</th>
											<th>仪容仪表</th>
											<th>卫生清校</th>
											<th>文明礼仪生</th>
											<th>文明纠察队</th>
											<th>文明小卫士</th>
											<th>备注</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td class="gray">一年级（1）班</td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
										<tr>
											<td class="gray">一年级（2）班</td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
										<tr>
											<td class="gray">一年级（3）班</td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
										<tr>
											<td class="gray">一年级（4）班</td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
										<tr>
											<td class="gray">一年级（5）班</td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
										<tr>
											<td class="gray">二年级（1）班</td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
										<tr>
											<td class="gray">二年级（2）班</td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
										<tr>
											<td class="gray">二年级（3）班</td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
										<tr>
											<td class="gray">二年级（4）班</td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
										<tr>
											<td class="gray">二年级（5）班</td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
										<tr>
											<td class="gray">二年级（6）班</td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
									</tbody>
								</table>
							</div>
                       
                        </div>
                    <div class="clear"></div>
                        </div>
                    </div>
                    </div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>