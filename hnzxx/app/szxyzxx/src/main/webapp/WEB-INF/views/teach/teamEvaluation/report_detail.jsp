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
	<c:if test="${manager eq 'yes'}">
		 <input type="hidden" id="isvip" value="yes">
	</c:if>
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
							评价明细报表
<!-- 							<p class="btn_link" style="float: right;line-height:47px;margin-right:10px;margin-top: 5px;"> -->
<!-- 								<a href="javascript:void(0)" class="a3" onclick="$.refreshWin();"><i class="fa  fa-arrow-down"></i>导出</a> -->
<!-- 							</p> -->
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)"  class="a3" onclick="toIndex();"><i class="fa  fa-reply"></i>返回</a>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="check-rated">
							<div class="minutes-rated">
                            	<a href="javascript:void(0);" onclick="toReport();">评价报表</a>
                            	<a href="javascript:void(0);" class="see-rated">评价明细报表</a>
                        	</div>
                        <div class="card_detail">
                        <div class="project-rated">
                        <div class="content-widgets" style="margin:0">
                        <div class="widget-container" style="padding:20px 0 0 0">
                            <div class="select_b" id="sel_div">
								<div class="select_div"><span>学年：</span><select id="xn" name="xn" class="chzn-select" style="width:120px;" ></select> </div>
								<div class="select_div"><span style="padding-left:30px;">学期：</span> 
									<select id="xq" name="xq" class="chzn-select" style="width:160px;" onchange="getWeek();"></select>
								</div>
								<div class="select_div" style="display: none"><span>年级：</span> <select id="nj" style="width:120px;"></select></div>
								<div class="select_div" style="display: none"><span>班级：</span> <select id="bj" style="width:120px;"></select></div>
								<div class="select_div" id="select_div_week" >
									<span>周次：</span><select id="select_week" style="width:260px;" onchange="getDay();"></select>
								</div>
								<div class="select_div" >
									<span>星期：</span><select id="select_dayOfWeek" style="width:120px;"></select>
								</div>
								<button class="btn btn-primary" type="button" onClick="search()">查询</button>
								<div class="clear"></div>
							</div>
                        </div>
                    	</div>
                        
                        <div class="clear"></div>
                        <div id="kb_tb"></div>
                        
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
<script type="text/javascript">
	function toReport(){
		var s=$('#isvip').val();
		window.location.href="${pageContext.request.contextPath}/teach/teamEvaluation/pjbb?manager="+s;
	}
	
	function toIndex(){
		window.location.href="${pageContext.request.contextPath}/teach/teamEvaluation/index?dm=${param.dm}";
	}
	
	$(function(){
		$.initCascadeSelector({
			"type" : "team",
			"selectOne":true, 
			"enableRole":true,
			"yearChangeCallback" : function(year) {
				if(year != "") {
					$.SchoolTermSelector({
						"selector" : "#xq",
						"condition" : {"schoolYear" : year},
						"afterHandler" : function($this) {
							$this.change();
							$("#xq_chzn").remove();
							$this.show().removeClass("chzn-done").chosen();
						}
					}
				);
				} else {
					$("#xq").val("");
					$("#xq_chzn").remove();
					$("#xq").show().removeClass("chzn-done").chosen();
				}
			
			}
		});
		search();
	});
	
	function getWeek(){
		var term = $('#xq').val();
		if("" === term || "undefind" === term || term ==null){
			return false;
		}
		var $requestData = {};
		$requestData.code=$('#xq').val();
		$.get("${pageContext.request.contextPath}/teach/teamEvaluation/list/json", $requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				var begin=data.begin;
				var end=data.end;
				var today = new Date().Format("yyyy-MM-dd");
				$.getWeek({
					"selector" : "#select_week",
					"begin" : begin,
					"end" : end,
					"isClear" : false,
					"today" : today,
					"isSelectCurrentWeek" : true,
					"clearedOptionTitle" : "请选择学期"
				});
				getDay();
			}
		});
	}
	
	function getDay(){
		var week = $("#select_week").val();
		if("" === week || "undefind" === week || week ==null){
			return false;
		}
		var $requestData = {};
		$requestData.week = week;
		$.post("${pageContext.request.contextPath}/teach/teamEvaluation/report/daylist", $requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				var daylist = data.responseData;
				//console.log(daylist);
				$("#select_dayOfWeek").html("");
				
				for(var i=0; i<daylist.length;i++){
					if(daylist[i].isCurrent == "1"){
						var opt = "<option value='"+daylist[i].date+"' selected='selected'>"+daylist[i].dayOfWeek+"</option>";
					}else{
						var opt = "<option value='"+daylist[i].date+"'>"+daylist[i].dayOfWeek+"</option>";
					}
					$("#select_dayOfWeek").append(opt);
				}
				if(daylist.length == 0){
	 				$("#select_dayOfWeek").append("<option value=''>请选择</option>");
				}
				//$("#select_dayOfWeek").chosen();
				var selectObj = $("#select_dayOfWeek"); 
				selectObj.parent().children().remove('div'); 
				selectObj.removeClass(); 
				selectObj.addClass("chzn-select"); 
				selectObj.chosen(); 
			}
		});
		
	}
	
	function search(){
		var loader = new loadLayer();
		var year = $("#xn").val();
		var termCode = $("#xq").val();
		var week = $("#select_week").val();
		var date = $("#select_dayOfWeek").val();
		var day = $("#select_dayOfWeek").find("option:selected").text();
		var isAll = false;
		if($("#isvip").val() == "yes"){
			isAll = true;
		}
		//console.log(year+"  "+termCode+"  "+week+"  "+date+"  "+day+"  "+isAll);
		if ("" === year || "undefined" === year) {
			$.error("请选择学年");
			return false;
		}
		if ("" === termCode || "undefined" === termCode) {
			$.error("请选择学期");
			return false;
		}
		if ("" === week || "undefined" === week) {
			$.error("请选择周次");
			return false;
		}
		if ("" === date || "undefined" === date) {
			$.error("请选择星期");
			return false;
		}
		
		var $requestData = {};
		$requestData.year = year;
		$requestData.termCode = termCode;
		$requestData.week = week;
		$requestData.date = date;
		$requestData.day = day;
		$requestData.isAll = isAll;
		loader.show();
		$.post("${pageContext.request.contextPath}/teach/teamEvaluation/report/detail/list", $requestData, function(data, status) {
			if("success" === status) {
				$("#kb_tb").html(data);
			}
			loader.close();
		});
		
		
	}
</script>

</html>