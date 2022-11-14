<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>班级体评价</title>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
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
			<jsp:param value="班级体评价" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							班集体评价
							
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
							<div class="check-rated">
							<div class="minutes-rated">
                            <a href="javascript:void(0);" class="see-rated">查看班集体评价</a>
                            <a href="javascript:void(0);">添加班集体评价</a>
                        </div>
                        <div class="card_detail">
                        <div class="project-rated">
                        <div class="content-widgets">
                        <div class="widget-container" style="padding:20px 0 0 0">
                            <div class="select_b">
								<div class="select_div"><span>学年：</span><select id="xn" name="xn" class="chzn-select" style="width:120px;"></select> </div>
								<div class="select_div"><span>年级：</span><select id="nj" name="nj" class="chzn-select" style="width:120px;"></select> </div>
								<div class="select_div"><span>班级：</span><select id="bj" name="bj" class="chzn-select" style="width:120px;"></select> </div>
								<div class="select_div"><span>日期：</span><input type=text" style="width:120px;"/> </div>
								  <p class="btn_link" style="float: right;line-height:47px;margin-right:10px;">
									<a href="javascript:void(0)" class="a3" onclick="$.refreshWin();"><i class="fa  fa-arrow-down"></i>导出</a>
									<a href="javascript:void(0)" class="a2" onclick=""><i class="fa  fa-bar-chart"></i>评价报表</a>
									</p>
								<div class="clear"></div>
							</div>
                        </div>
                    </div>
                    <div class="clear"></div>
                            <p>2015-2016学年下学期 三年级二班 2015-03-25 星期五</p>
                            <div class="plus-minus-rated">
                                <a href="javascript:void(0);" class="current-rated" style="border-right: 1px solid #ddd;">扣分项目（<span class="red">4</span>）</a>
                                <a href="javascript:void(0);">加分项目（<span style="color:#2399dc;">0</span>）</a>
                            </div>
                            <div class="points-content">
                            <div class="points">
                            <table class="responsive table table-striped" >
                            <thead>
                                <tr role="row">
                                   <th>星期</th>
                                   <th>检查项</th>
                                   <th>扣分数</th>
                                   <th>备注</th>
                                   <th class="caozuo">图片</th>
                                </tr>
                            </thead>
                            <tbody id="module_list_content">
                                 <tr class="">
                                     <td>星期五</td>
                                     <td>室内操</td>
                                     <td><span>1</span></td>
                                     <td>女</td>
                                <td class="caozuo"><img src="images/deyu_evaluate.jpg"><img src="images/deyu_evaluate.jpg"><img src="images/deyu_evaluate.jpg"></td>
                                </tr>
                                 <tr class="">
                                     <td>星期五</td>
                                      <td>室内操</td>
                                     <td><span>1</span></td>
                                     <td>男</td>
                                 <td class="caozuo"></td>
                                </tr>
                                 <tr class="">
                                     <td>星期五</td>
                                      <td>室内操</td>
                                     <td><span>1</span></td>
                                     <td>女</td>
                                <td class="caozuo"></td>
                                </tr>
                                 <tr class="">
                                     <td>星期五</td>
                                      <td>室内操</td>
                                     <td><span>1</span></td>
                                     <td>女</td>
                                <td class="caozuo"></td>
                                </tr>
                                 <tr class="">
                                     <td>星期五</td>
                                      <td>室内操</td>
                                     <td><span>1</span></td>
                                     <td>女</td>
                                 <td class="caozuo"></td>
                                </tr>
                                 <tr class="blue_1">
                                     <td>星期五</td>
                                      <td>室内操</td>
                                     <td><span>1</span></td>
                                     <td>女</td>
                                     <td class="caozuo"></td>
                                 </tr>
                                 <tr class="">
                                     <td>星期五</td>
                                      <td>室内操</td>
                                     <td><span>1</span></td>
                                     <td>女</td>
                                     <td class="caozuo"></td>
                                 </tr>
                                 <tr class="">
                                     <td>星期五</td>
                                      <td>室内操</td>
                                     <td><span>1</span></td>
                                     <td>女</td>
                                     <td class="caozuo"></td>
                                 </tr>
                                 <tr class="">
                                     <td>星期五</td>
                                      <td>室内操</td>
                                     <td><span>1</span></td>
                                     <td>女</td>
                                     <td class="caozuo"></td>
                                 </tr>
                                 <tr>
                                     <td>统计：</td>
                                     <td>扣分<span>4</span>笔</td>
                                     <td colspan="3">扣分总数：<span>4</span>分</td>
                                 </tr>
                            </tbody>
                        </table>
                        </div>
                        </div>

                        <div class="points-content">
                            <table class="responsive table table-striped" >
                            <thead>
                                <tr role="row">
                                   <th>星期111</th>
                                   <th>检查项</th>
                                   <th>扣分数</th>
                                   <th>备注</th>
                                   <th class="caozuo">图片</th>
                                </tr>
                            </thead>
                            <tbody id="module_list_content">
                                 <tr class="">
                                     <td>星期五</td>
                                     <td>室内操</td>
                                     <td><span>1</span></td>
                                     <td>女</td>
                                <td class="caozuo"><img src="images/deyu_evaluate.jpg"><img src="images/deyu_evaluate.jpg"><img src="images/deyu_evaluate.jpg"></td>
                                </tr>
                                 <tr class="">
                                     <td>星期五</td>
                                      <td>室内操</td>
                                     <td><span>1</span></td>
                                     <td>男</td>
                                 <td class="caozuo"></td>
                                </tr>
                                 <tr class="">
                                     <td>星期五</td>
                                      <td>室内操</td>
                                     <td><span>1</span></td>
                                     <td>女</td>
                                <td class="caozuo"></td>
                                </tr>
                                 <tr class="">
                                     <td>星期五</td>
                                      <td>室内操</td>
                                     <td><span>1</span></td>
                                     <td>女</td>
                                <td class="caozuo"></td>
                                </tr>
                                 <tr class="">
                                     <td>星期五</td>
                                      <td>室内操</td>
                                     <td><span>1</span></td>
                                     <td>女</td>
                                 <td class="caozuo"></td>
                                </tr>
                                 <tr class="blue_1">
                                     <td>星期五</td>
                                      <td>室内操</td>
                                     <td><span>1</span></td>
                                     <td>女</td>
                                     <td class="caozuo"></td>
                                 </tr>
                                 <tr class="">
                                     <td>星期五</td>
                                      <td>室内操</td>
                                     <td><span>1</span></td>
                                     <td>女</td>
                                     <td class="caozuo"></td>
                                 </tr>
                                 <tr class="">
                                     <td>星期五</td>
                                      <td>室内操</td>
                                     <td><span>1</span></td>
                                     <td>女</td>
                                     <td class="caozuo"></td>
                                 </tr>
                                 <tr class="">
                                     <td>星期五</td>
                                      <td>室内操</td>
                                     <td><span>1</span></td>
                                     <td>女</td>
                                     <td class="caozuo"></td>
                                 </tr>
                                 <tr>
                                     <td>统计：</td>
                                     <td>扣分<span>4</span>笔</td>
                                     <td colspan="3">扣分总数：<span>4</span>分</td>
                                 </tr>
                            </tbody>
                        </table>
                        </div>
                        </div>

                        <div class="project-rated">
                            <div class="content-widgets">
                        <div class="widget-container" style="padding:20px 0 0 0">
                            <div class="select_b">
								<div class="select_div"><span>学期：</span><select id="xn" name="xn" class="chzn-select" style="width:120px;"></select> </div>
								<div class="select_div"><span>年级：</span><select id="nj" name="nj" class="chzn-select" style="width:120px;"></select> </div>
								<div class="select_div"><span>日期：</span><input type=text" style="width:120px;"/> </div>
								  <p class="btn_link" style="float: right;margin:10px 10px 0 0;">
									<a href="javascript:void(0)" class="a4" onclick="">保存</a>
									</p>
								<div class="clear"></div>
							</div>
                        </div>
                    </div>
                            <div class="plus-minus-rated" style="border:1px solid #ddd;">
                                <a href="javascript:void(0);" class="current-rated" style="border-right: 1px solid #ddd;">减分项</a>
                                <a href="javascript:void(0);">加分项</a>
                            </div>

                    <div class="clear"></div>
                            <p>2015-2016年下学期，高中一年级，2016-06-24</p>
                            <div class="points-content">
                            <div class="points" style="position: relative;">
                                <table class="responsive table table-striped reflective-evaluate left_table"  style="width:70px;position: absolute;z-index:1">
                                    <thead style="background:#daf0fb;">
                                        <tr role="row" style="color:#4c708a;">
                                            <th>班级</th>
                                        </tr>
                                    </thead>
                                    <tbody id="module_list_content">
                                        <tr class="">
                                             <td>1班</td>
                                        </tr>
                                        <tr class="">
                                             <td>2班</td>
                                        </tr>
                                    </tbody>
                                </table>

                                <div style="position: relative;left: 70px;margin-right: 305px;overflow:hidden" class="table_div">
                                <table class="responsive table table-striped table-limit reflective-evaluate" >
                                    <thead style="background:#daf0fb;">
                                        <tr role="row" style="color:#4c708a;" class="middle_table">
                                            <th>多获得的心智卡加分</th>
                                            <th>早卫包干区</th>
                                            <th>午卫教师</th>
                                            <th>多获得的心智卡加分</th>
                                            <th>早卫包干区</th>
                                            <th>午卫教师</th>
                                            <th>多获得的心智卡加分</th>
                                            <th>早卫包干区</th>
                                            <th>午卫教师</th>
                                            <th>多获得的心智卡加分</th>
                                            <th>早卫包干区</th>
                                            <th>午卫教师</th>
                                            <th>多获得的心智卡加分</th>
                                            <th>早卫包干区</th>
                                            <th>午卫教师</th>
                                        </tr>
                                    </thead>
                                    <tbody id="module_list_content">
                                        <tr class="">
                                         <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                        </tr>
                                       <tr class="">
                                            <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                            <td><input type="text" class="NaN_reduce" ></td>
                                        </tr>
                                    </tbody>
                                </table>
                                </div>

                                <table class="responsive table table-striped reflective-evaluate" id="data-table" style="width:100%;position: absolute;right:0;top:0;">
                                    <thead style="background:#daf0fb;">
                                        <tr role="row" style="color:#4c708a;">
                                            <th class="caozuo"><button>添加坚持项</button><a href="javascript:void(0);" class="turn_right">>></a><a href="javascript:void(0);" class="turn_left"><<</a></th>
                                        </tr>
                                    </thead>
                                    <tbody id="module_list_content">
                                        <tr class="">
                                             <td></td>
                                        </tr>
                                        <tr class="">
                                             <td></td>
                                        </tr>
                                        <tr class="">
                                             <td></td>
                                        </tr>
                                        <tr class="">
                                             <td></td>
                                        </tr>
                                    </tbody>
                                </table>
                        </div>
                        </div>

                        <div class="points-content">
                            <div class="points" style="position: relative;">
                                <table class="responsive table table-striped reflective-evaluate left_table" style="width:70px;position: absolute;z-index:1">
                                    <thead style="background:#daf0fb;">
                                        <tr role="row" style="color:#4c708a;">
                                            <th>班级111</th>
                                        </tr>
                                    </thead>
                                    <tbody id="module_list_content">
                                        <tr class="">
                                             <td>1班</td>
                                        </tr>
                                        <tr class="">
                                             <td>2班</td>
                                        </tr>
                                        <tr class="">
                                             <td>3班</td>
                                        </tr>
                                        <tr class="">
                                             <td>4班</td>
                                        </tr>
                                    </tbody>
                                </table>

                                <div style="position: relative;left: 70px;margin-right: 305px;overflow:hidden" class="table_div">
                                <table class="responsive table table-striped table-limit reflective-evaluate" >
                                    <thead style="background:#daf0fb;">
                                        <tr role="row" style="color:#4c708a;" class="middle_table">
                                            <th>多获得的心智卡加分</th>
                                            <th>早卫包干区</th>
                                            <th>午卫教师</th>
                                        </tr>
                                    </thead>
                                    <tbody id="module_list_content">
                                        <tr class="">
                                            <td><input type="text" class="NaN_add" ></td>
                                            <td><input type="text" class="NaN_add" ></td>
                                            <td><input type="text" class="NaN_add" ></td>
                                        </tr>
                                        <tr class="">
                                           <td><input type="text" class="NaN_add" ></td>
                                            <td><input type="text" class="NaN_add" ></td>
                                            <td><input type="text" class="NaN_add" ></td>
                                          </tr>
                                        <tr class="">
                                            <td><input type="text" class="NaN_add" ></td>
                                            <td><input type="text" class="NaN_add" ></td>
                                            <td><input type="text" class="NaN_add" ></td>
                                        </tr>
                                        <tr class="">
                                           <td><input type="text" class="NaN_add" ></td>
                                            <td><input type="text" class="NaN_add" ></td>
                                            <td><input type="text" class="NaN_add" ></td>
                                          </tr>
                                    </tbody>
                                </table>
                                </div>

                                <table class="responsive table table-striped reflective-evaluate"  style="width:100%;position: absolute;right:0;top:0;">
                                    <thead style="background:#daf0fb;">
                                        <tr role="row" style="color:#4c708a;">
                                            <th class="caozuo"><button>添加坚持项</button><a href="javascript:void(0);" class="turn_right">>></a><a href="javascript:void(0);" class="turn_left"><<</a></th>
                                        </tr>
                                    </thead>
                                    <tbody id="module_list_content">
                                        <tr class="">
                                             <td></td>
                                        </tr>
                                        <tr class="">
                                             <td></td>
                                        </tr>
                                        <tr class="">
                                             <td></td>
                                        </tr>
                                        <tr class="">
                                             <td></td>
                                        </tr>
                                    </tbody>
                                </table>
                        </div>
                        </div>
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