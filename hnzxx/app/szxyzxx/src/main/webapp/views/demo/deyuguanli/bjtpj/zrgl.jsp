<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>值日管理</title>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<style type="text/css">
.select_b .select_div{
	margin: 7px 0 0 0;
}
</style>
<script type="text/javascript">
$(function(){
	$(".personnel").on("click","tbody tr td",function(){
        if($(this).hasClass("cannot")){
        }else{
            $(this).toggleClass("confirm-staff");
        }
    })
    
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
			<jsp:param value="值日管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							班集体评价>>值日管理
							<p class="btn_link" style="float: right;">
                                <a href="javascript:void(0)" onclick="$.refreshWin();" class="a4" style="padding: 0 20px;">保存</a>
                            </p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
							<div class="check-rated">
                        <div class="card_detail">
                        <div class="project-rated" style="margin:0;">
                        <div class="content-widgets">
                        <div class="widget-container" style="padding:0;">
                            <div class="select_b" style="border-bottom: 1px #cccdcd solid;">
								<div class="select_div"><span>年级：</span><select id="nj" name="nj" class="chzn-select" style="width:120px;"></select> </div>
								<div class="select_div"><span>周次：</span><input type=text" style="width:120px;height: 23px;"/> </div>
								<div class="select_div" style="margin-left: 40px;width: 160px;"><span></span><input type=text" style="width:150px;height: 23px;"placeholder="请输入老师姓名进行查找"/> </div>
								<button type="button" class="btn btn-primary" onclick="search()">搜索</button>
								<div class="clear"></div>
							</div>
                        </div>
                    </div>
                    <div class="clear"></div>
                        
                        </div>
                        <div class="attendant">
                                <div class="time-period"><span>2015-2016年下学期</span>，<span>高中一年级</span>，<span>第一周</span></div>
                                <div class="personnel">
                                <ul style="width:8%;" class="restrict">
                                        <li class="cannot">编号</li>
                                        <li class="cannot">1</li>
                                        <li class="cannot">2</li>
                                        <li class="cannot">3</li>
                                        <li class="cannot">4</li>
                                        <li class="cannot">5</li>
                                    </ul>
                                    <ul class="restrict">
                                        <li class="cannot">星期一</li>
                                        <li>张三</li>
                                        <li>张三</li>
                                        <li>张三</li>
                                        <li>张三</li>
                                        <li>张三</li>
                                    </ul>
                                    <ul class="restrict">
                                        <li class="cannot">星期二</li>
                                        <li>张三</li>
                                        <li>张三</li>
                                        <li>张三</li>
                                        <li>张三</li>
                                        <li>张三</li>
                                    </ul>
                                    <ul class="restrict">
                                        <li class="cannot">星期三</li>
                                        <li>张三</li>
                                        <li>张三</li>
                                        <li>张三</li>
                                        <li>张三</li>
                                        <li>张三</li>
                                    </ul>
                                    <ul class="restrict">
                                        <li class="cannot">星期四</li>
                                        <li>张三</li>
                                        <li>张三</li>
                                        <li>张三</li>
                                        <li>张三</li>
                                        <li>张三</li>
                                    </ul>
                                    <ul class="restrict">
                                        <li class="cannot">星期五</li>
                                        <li>张三</li>
                                        <li>张三</li>
                                        <li>张三</li>
                                        <li>张三</li>
                                        <li>张三</li>
                                    </ul>
                                    <div class="clear"></div>
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