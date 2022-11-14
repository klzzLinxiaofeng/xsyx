//----------------------------------------------------------顶端选择控件-----------------------------------------------------//
var bgDate;
var edDate;
var teamId;
var gradeId;
var newyear;
var newmonth;
var selectmonth;
var getSchoolYear;
var termCode;
var beginDate;
var endDate;
var num=0;
var loader = new loadwkx();
$(function(){
	asdf();
});
	function asdf(){
	/* 年级班级切换 */
    $(".choice-class").click(function(){
    	$(".choice_prom").hide();
    	$(".choice_mon").hide();
    	$(".choice_wee").hide();
    	$(".choice_t").hide();
     $(".choice_show").toggle();
     if($(".choice_show").is(":visible")){
    	 $("#choice_bg").show();
     }else{
    	 $("#choice_bg").hide();
     }
    var $top=$(this).children("p").children(".tog")
    var t=$top.text();
    if(t=="﹀"){
    	$(".choice-term").css("color","#8f8f9e")
        $(".choice-term .tog").text("﹀").removeClass("tianjia");
    	$(".choice-type").css("color","#8f8f9e")
        $(".choice-type .tog").text("﹀").removeClass("tianjia");
    	$(".choice-month").css("color","#8f8f9e")
        $(".choice-month .tog").text("﹀").removeClass("tianjia");
    	$(".choice-week").css("color","#8f8f9e")
        $(".choice-week .tog").text("﹀").removeClass("tianjia");
        $(".choice-class .tog").addClass("show");
        $(this).css("color","#39a3ef")
            $top.text("︿").addClass("tianjia");
        }else{
            $(".tog").removeClass("show");
            $(this).css("color","#8f8f9e")
            $top.text("﹀").removeClass("tianjia");
    }
});
    $("body").on("click",".choice_remove ul #whole",function(){
   	 	select();
    });
     $(".menu-choice").on("click",".choice_remove ul li",function(){
        $(".choice_remove ul li").removeClass("grade");
        $(this).addClass("grade");
        gradeId = $(this).attr("data-gradeId");//获取gradeId
        var i=$(this).index();
        $(".minutes ul").hide();
        $(".minutes ul").eq(i).show();
        if($(this).hasClass("all")){
        	 var grade_name=$(this).text();
        	 $(".c_name").text("全校").prop('id',3);
        	 $(".tog").removeClass("show");
             $(".choice-class").css("color","#8f8f9e")
             $(".tog").text("﹀").removeClass("tianjia");
             $(".choice_show").hide();
           $("#choice_bg").hide();
//           select();
        }
     });
     $(".menu-choice").on("click",".minutes ul li",function(){//班级的点击
        $(".minutes ul li").addClass("det").removeClass("year");
        $(this).addClass("year").removeClass("det");
        var class_name=$(this).text();
        var grade_name=$(".choice_remove ul li.grade").text();
		if(class_name == "全部"){
        	$(".c_name").text(grade_name+"全部").prop('id',1);
        	select();
        }else{//获取teamId
        	$(".c_name").text(grade_name+class_name).prop('id',2);
        	teamId = $(this).attr("data-teamId");
        	select();
        }
        $(".tog").removeClass("show");
        $(".choice-class").css("color","#8f8f9e")
        $(".tog").text("﹀").removeClass("tianjia");
        $(".choice_show").hide();
        $("#choice_bg").hide();
        
     });

   //学年切换
     $(".choice-term").click(function(){
    	 $(".choice_show").hide();
    	 $(".choice_t").hide();
    	 $(".choice_mon").hide();
     	$(".choice_wee").hide();
         $(".choice_prom").toggle();
         if($(".choice_prom").is(":visible")){
        	 $("#choice_bg").show();
         }else{
        	 $("#choice_bg").hide();
         }
         
        var $top=$(this).children("p").children(".tog")
        var t=$top.text();
        if(t=="﹀"){
        	$(".choice-class").css("color","#8f8f9e")
            $(".choice-class .tog").text("﹀").removeClass("tianjia");
        	$(".choice-type").css("color","#8f8f9e")
            $(".choice-type .tog").text("﹀").removeClass("tianjia");
        	$(".choice-month").css("color","#8f8f9e")
            $(".choice-month .tog").text("﹀").removeClass("tianjia");
        	$(".choice-week").css("color","#8f8f9e")
            $(".choice-week .tog").text("﹀").removeClass("tianjia");
            $("choice-term .tog").addClass("show");
            $(this).css("color","#39a3ef")
                $top.text("︿").addClass("tianjia");
            
            }else{
                $(".tog").removeClass("show");
                $(this).css("color","#8f8f9e")
                $top.text("﹀").removeClass("tianjia");
        }
    });
   
   //月周切换
     $(".choice-type").click(function(){
    	 $(".choice_prom").hide();
    	 $(".choice_show").hide();
    	 $(".choice_mon").hide();
      	$(".choice_wee").hide();
         $(".choice_t").toggle();
         if($(".choice_t").is(":visible")){
        	 $("#choice_bg").show();
         }else{
        	 $("#choice_bg").hide();
         }
          
        var $top=$(this).children("p").children(".tog")
        var t=$top.text();
        if(t=="﹀"){
        	$(".choice-class").css("color","#8f8f9e")
            $(".choice-class .tog").text("﹀").removeClass("tianjia");
        	$(".choice-term").css("color","#8f8f9e")
            $(".choice-term .tog").text("﹀").removeClass("tianjia");
        	$(".choice-month").css("color","#8f8f9e")
            $(".choice-month .tog").text("﹀").removeClass("tianjia");
        	$(".choice-week").css("color","#8f8f9e")
            $(".choice-week .tog").text("﹀").removeClass("tianjia");
            $("choice-type .tog").addClass("show");
            $(this).css("color","#39a3ef")
                $top.text("︿").addClass("tianjia");
            
            }else{
                $(".tog").removeClass("show");
                $(this).css("color","#8f8f9e")
                $top.text("﹀").removeClass("tianjia");
        }
    });
     $(".menu-choice").on("click",".choice_y ul li",function(){
         $(".choice_y ul li").removeClass("grade");
         $(this).addClass("grade");
         var type_name=$(this).text();
         $(".c_type").text(type_name)
         
         $(".tog").removeClass("show");
         $(".choice-type").css("color","#8f8f9e")
         $(".tog").text("﹀").removeClass("tianjia");
         $(".choice_t").hide();
         $("#choice_bg").hide();
         
         var select = $(this).data("id");
         if(select == 1){
      		document.getElementById("select_div_month").style.display="block";
      		document.getElementById("select_div_week").style.display="none";
      		var $month = (new Date()).getMonth()+1;
			var $year = (new Date()).getFullYear();
			var today = new Date().Format("yyyy-MM-dd");
			if(bgDate<today && edDate>today){
				$("#select_month li[data-month='" + $month + "']").click();
			}else{
				$("#select_month li").eq(0).click();
			}
//      		var $month = (new Date()).getMonth();
//      		$("#select_month li[data-month='" + $month + "'] ").click();
//      		
//      		$("#select_month li").eq(0).click();
      	}else if(select == 2){
      		document.getElementById("select_div_week").style.display="block";
      		document.getElementById("select_div_month").style.display="none";
      		$("#select_week li").eq(0).click();
      	} 
  });
     
   //月份切换
     $(".choice-month").click(function(){
    	 $(".choice_prom").hide();
    	 $(".choice_show").hide();
    	 $(".choice_t").hide();
         $(".choice_mon").toggle();
         if($(".choice_mon").is(":visible")){
        	 $("#choice_bg").show();
         }else{
        	 $("#choice_bg").hide();
         }
          
        var $top=$(this).children("p").children(".tog")
        var t=$top.text();
        if(t=="﹀"){
        	$(".choice-class").css("color","#8f8f9e")
            $(".choice-class .tog").text("﹀").removeClass("tianjia");
        	$(".choice-term").css("color","#8f8f9e")
            $(".choice-term .tog").text("﹀").removeClass("tianjia");
        	$(".choice-type").css("color","#8f8f9e")
            $(".choice-type .tog").text("﹀").removeClass("tianjia");
            $("choice-month .tog").addClass("show");
            $(this).css("color","#39a3ef")
                $top.text("︿").addClass("tianjia");
            
            }else{
                $(".tog").removeClass("show");
                $(this).css("color","#8f8f9e")
                $top.text("﹀").removeClass("tianjia");
        }
    });
     $(".menu-choice").on("click",".choice_mo ul li",function(){//月份的点击事件
         $(".choice_mo ul li").removeClass("grade");
         $(this).addClass("grade");
         var type_name=$(this).text();
         $(".c_month").text(type_name)
         
         $(".tog").removeClass("show");
         $(".choice-month").css("color","#8f8f9e")
         $(".tog").text("﹀").removeClass("tianjia");
         $(".choice_mon").hide();
         $("#choice_bg").hide();
         newyear = $(this).attr("date-year");
         newmonth = $(this).text();
         selectmonth = $(this).attr("data-month");
         select();
  });
     
     //周次切换
     $(".choice-week").click(function(){
    	 $(".choice_prom").hide();
    	 $(".choice_show").hide();
    	 $(".choice_t").hide();
         $(".choice_wee").toggle();
         if($(".choice_wee").is(":visible")){
        	 $("#choice_bg").show();
         }else{
        	 $("#choice_bg").hide();
         }
          
        var $top=$(this).children("p").children(".tog")
        var t=$top.text();
        if(t=="﹀"){
        	$(".choice-class").css("color","#8f8f9e")
            $(".choice-class .tog").text("﹀").removeClass("tianjia");
        	$(".choice-term").css("color","#8f8f9e")
            $(".choice-term .tog").text("﹀").removeClass("tianjia");
        	$(".choice-type").css("color","#8f8f9e")
            $(".choice-type .tog").text("﹀").removeClass("tianjia");
            $("choice-week .tog").addClass("show");
            $(this).css("color","#39a3ef")
                $top.text("︿").addClass("tianjia");
            
            }else{
                $(".tog").removeClass("show");
                $(this).css("color","#8f8f9e")
                $top.text("﹀").removeClass("tianjia");
        }
    });
     $(".menu-choice").on("click",".choice_we ul li",function(){//周次点击
         $(".choice_we ul li").removeClass("grade");
         $(this).addClass("grade");
         var type_name=$(this).text();
         $(".c_week").text(type_name)
         
         $(".tog").removeClass("show");
         $(".choice-week").css("color","#8f8f9e")
         $(".tog").text("﹀").removeClass("tianjia");
         $(".choice_wee").hide();
         $("#choice_bg").hide();
         beginDate=$(this).data("prev");//获得周次开始的时间
		 endDate=$(this).data("next");//获得周次结束的时间   
         select();
  });
//   点击遮罩层
     $("#choice_bg").click(function(){
    	 $(".tog").removeClass("show");
         $(".choice-class,.choice-term,.choice-type,.choice-week,.choice-month").css("color","#8f8f9e")
         $(".tog").text("﹀").removeClass("tianjia");
         $(".choice_show,.choice_prom,.choice_t,.choice_mon,.choice_wee").hide();
       $("#choice_bg").toggle();
     });
    $(".menu-choice").on("click",".school-year ul li",function(){
            $(".school-year ul li").removeClass("year");
            $(this).addClass("year");
            var grade_name=$(this).parent().parent().children("span").text();
            var yearName = $(this).text();
            $(".xq_name").text(grade_name+yearName);

            $(".choice-term").css("color","#8f8f9e")
            $(".tog").text("﹀").removeClass("tianjia");
            $(".choice_prom").hide();
          	$("#choice_bg").hide();
        	var begin=$(".year").attr('data-beginDate');
        	
        	var end=$(".year").attr('data-endDate');
        	termCode = $(this).attr("data-termCode");
        	schoolYear = $(this).attr("data-schoolYear");
        	beginDate = $(this).attr("data-begindate");
			endDate = $(this).attr("data-enddate");
			bgDate = $(this).attr("data-begindate");
			edDate = $(this).attr("data-enddate");
        	classList(urlForClassList);
//        	select();
        	//zhouci
//			$.getNewWeek({
//				"selector" : "#select_week",
//				"begin" : begin,
//				"end" : end,
//				"isClear" : false,
//				"today" : "",
//				"isSelectCurrentWeek" : true,
//				"clearedOptionTitle" : "请选择周次"
//			});
//			$.getNewMonth({
//				"selector" : "#select_month",
//				"begin" : begin,
//				"end" : end,
//				"isClear" : false,
//				"today" : "",
//				"isSelectCurrentWeek" : true,
//				"clearedOptionTitle" : "请选择月份"
//			});
        	month();
			week();
			var today = new Date().Format("yyyy-MM-dd");
			if($("#select_div_week").is(':hidden')){
				var $month = (new Date()).getMonth()+1;
				var $year = (new Date()).getFullYear();
				if(bgDate<today && edDate>today){
					$("#select_month li[data-month='" + $month + "']").click();
				}else{
//					$("#select_month li").eq(0).addClass("grade");
					$("#select_month li").eq(0).click();
				}
			}else{
//				$("#select_week li").eq(0).addClass("grade");
				$("#select_week li").eq(0).click();
			}
          	//yuefen
//         	bgDate=$(".year").attr('data-beginDate');
//	     	edDate=$(".year").attr('data-endDate');
          	var date2="";
          	var myarray;
			if(today>begin&&today<end){
				myarray = today.split("-");
			}else{
				myarray = begin.split("-");
			}
			if (myarray[1].charAt(0) != '0') {
				date2 = myarray[0]+ "年"+ myarray[1]+ "月";
			} else {
				date2 = myarray[0]+ "年"+ myarray[1].charAt(1)+ "月";
			}
			$('#d4').val(date2);
      });
	}
//---------------------------------------------------------生成选择列表---------------------------------------------------------------//
function schoolYearList(urlForSchoolYearList,urlForClassList,urlForTableList,urlSearchForGrade){
	$(".choice_prom").html("");
	$(".school-year").children("ul").html("");
	schoolId = $(".controllerData").attr("data-schoolId");
	$.ajax({
		type : "GET",  
		url : urlForSchoolYearList,
		dataType : "jsonp",//数据类型为jsonp  
		jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
		data:{
			schoolId  : schoolId //这里需要传schoolId
		},
		success : function(data){  //console.log(JSON.stringify(data));//data = eval("("+data+")");
			var data=data.data;
			for(var i=0;i<data.length;i++){
				$(".choice_prom").append("<div class='school-year'><span>"+data[i].yearName+"</span><ul class='schoolTerm'></ul></div>");
				for(var j=0;j<data[i].schoolTermlist.length;j++){
					if(data[i].schoolTermlist[j].isCurrent=='1'){
						schoolYear = data[i].schoolYear;
						$(".school-year").eq(i).children("ul").append("<li class='year' data-schoolYear='"+data[i].schoolYear+"' data-termCode='"+data[i].schoolTermlist[j].schoolTermCode+"' data-beginDate='"+data[i].schoolTermlist[j].beginDate+"' data-endDate='"+data[i].schoolTermlist[j].finishDate+"'>"+data[i].schoolTermlist[j].termName+"</li>");
						$('.xq_name').text(data[i].yearName+data[i].schoolTermlist[j].termName);
						beginDate = data[i].schoolTermlist[j].beginDate;
						endDate = data[i].schoolTermlist[j].finishDate;
						bgDate=data[i].schoolTermlist[j].beginDate;
						edDate=data[i].schoolTermlist[j].finishDate;
						var myarray = beginDate.split("-");
						var date2="";
						if (myarray[1].charAt(0) != '0') {
							date2 = myarray[0]+ "年"+ myarray[1]+ "月";
						} else {
							date2 = myarray[0]+ "年"+ myarray[1].charAt(1)+ "月";
						}
						$('#d4').val(date2);
						termCode = $(".year").attr("data-termCode");
					}else{
						$(".school-year").eq(i).children("ul").append("<li data-schoolYear='"+data[i].schoolYear+"' data-beginDate='"+data[i].schoolTermlist[j].beginDate+"' data-endDate='"+data[i].schoolTermlist[j].finishDate+"' data-termCode='"+data[i].schoolTermlist[j].schoolTermCode+"' >"+data[i].schoolTermlist[j].termName+"</li>");
					}
				}
			}
			////是否是从红旗公示跳转到此页面
			if(flag == "true"){
				var schoolyearname;
				var termname;//当前学年的学期
				for(var i=0;i<data.length;i++){
					for(var j=0;j<data[i].schoolTermlist.length;j++){
						if(appGetSchoolYear == data[i].schoolYear){
							schoolyearname = data[i].yearName;
							termname = data[i].schoolTermlist[j].termName;
						}
					}
				}
				for(var l=0;l<$(".school-year").length;l++){//给学期添加选中样式
					for(var m=0;m<$(".school-year").eq(l).children("ul").children().length;m++){
						if($(".school-year").eq(l).children("ul").children().eq(m).attr("data-termcode")==appGetTermCode){
							$(".school-year").eq(l).children("ul").children().eq(m).click();
						}
					}
				}
				$(".xq_name").text(schoolyearname+termname);
			}
			classList(urlForClassList);
			
			if(flag!="true"){
				month();
				week();
			}
			//是否是从红旗公示跳转到此页面
//			var $month = (new Date()).getMonth();
//			$("#select_month li[data-month'" + $month + "'] ").click();//让月份暂时隐藏，周次选中
			var today = new Date().Format("yyyy-MM-dd");
			var $month = (new Date()).getMonth()+1;
			var $year = (new Date()).getFullYear();
			if(bgDate<today && edDate>today){
				$("#select_month li[data-month='" + $month + "']").click();
			}else{
//				$("#select_month li").eq(0).addClass("grade");
				$("#select_month li").eq(0).click();
			}
			
//			$("#select_month li").eq(0).click();
			if(flag=="true"){
		    	$(".choice_y ul li").eq(1).click();
		    	var zc_time=appGetWeekTime-1;
		    	$(".choice_we ul li").eq(zc_time).click();
//		    	tableList(urlForTableList);
			}loader.close();
//			return termCode;
//			return schoolYear;
//			return beginDate;
//			return endDate;
		},
		error:function(){  
		    alert('fail：获取学年学期列表错误');  
		    loader.close();
		}  
	}); 
}
function classList(urlForClassList){
//	$(".choice_remove").children("ul").html("");
//	$(".minutes").children("ul").html("");
	schoolId = $(".controllerData").attr("data-schoolId");
	var userId = $(".controllerData").attr("data-userId");
	var role = $(".controllerData").attr("data-role");
	$.ajax({  
		type : "GET",  
		url : urlForClassList,
		dataType : "jsonp",//数据类型为jsonp  
		jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
		data:{
			schoolId : schoolId,
			schoolYear : schoolYear, //2015 schoolYear
			userId : userId, //17 userId
			role : role //"SCHOOL_LEADER"  role
		},
		success : function(data){   //console.log(JSON.stringify(data));
			var data=data.data;
//			teamId = data[0].teamList[0].teamId;
			$(".choice_remove").children("ul").html("");
			$(".minutes").children("ul").html("");
			if(data.length>0){
				$(".minutes ul").hide();
				for(var i=0;i<data.length;i++){
					$(".choice_remove").children("ul").append("<li data-gradeId='"+data[i].gradeId+"'>"+data[i].gradeName+"</li>");// 
					$(".minutes").append("<ul style='display:none'></ul>");
					for(var j=0;j<data[i].teamList.length;j++){
						$(".minutes").children("ul").eq(i).append("<li class='datateamId' data-teamId='"+data[i].teamList[j].teamId+"'>"+data[i].teamList[j].name+"</li>");
					} 
					if(role != "CLASS_MASTER" && role != "SUBJECT_TEACHER"){
						$(".minutes").children("ul").eq(i).append("<li>全部</li>");//班级全部
					}
				}
				if(role != "CLASS_MASTER" && role != "SUBJECT_TEACHER"){
					$(".choice_remove").children("ul").append("<li class='all' data-schoolId='"+schoolId+"' id='whole'>全校</li>");//年级全部
					$(".choice_remove .all").addClass("grade");
//					$(".all").click();
					teamH5searchForSchool(newyear,newmonth,urlSearchForSchool)
					$(".choice_show").hide();
					$("#choice_bg").hide();
				}
				if(role == "CLASS_MASTER" || role == "SUBJECT_TEACHER"){
					$(".choice_remove li").eq(0).addClass("grade");
					$(".minutes ul").eq(0).show();
					$(".minutes ul").eq(0).children().eq(0).addClass("year");
					if(data.length>0){
						$(".c_name").html(data[0].teamList[0].teamName);
						$(".minutes ul li").eq(0).click();
						$(".choice_show").hide();
						$("#choice_bg").hide();
					}else{
						$(".c_name").html("全部");
					}
					gradeId = $(".choice_remove ul li").eq(0).attr("data-gradeId");//获取gradeId
					teamId = $(".minutes ul li").eq(0).attr("data-teamid");
				}
				//			$(".c_name").text("全校").prop('id',3);
//				select();
				//是否是从红旗公示跳转到此页面
			}else{
//				teamId = "";
				$(".tank").html("");
				$(".tank").append("<div id='bg'></div>"+
									"<div id='show'>"+
										"<img src='/res/css/dygl/images/unhappy.png'>"+
										"<h3>查询失败</h3>"+
										"<p>该学年学期没有班级数据</p>"+
										"<button id='btnclose' onclick='hidediv();'>返回</button>"+
									"</div>");
				$(".tank").show();
			}
			if(flag=="true"){
				if(num<2){
					var gradeid;//年级ID
					var teamname;//班级名称
					for(var i=0;i<data.length;i++){
						for(var j=0;j<data[i].teamList.length;j++){
							if(data[i].teamList[j].teamId == appGetTeamId){
								gradeid = data[i].gradeId;//获取同级的gradeId
								teamname = data[i].teamList[j].teamName//获取与teamId匹配的班级名称
							}
						}
					}
					for(var k=0;k<$(".choice_remove ul li").length;k++){//给年级添加选中样式
						if($(".choice_remove ul li").eq(k).attr("data-gradeid")==gradeid){
							$(".choice_remove ul li").eq(k).click();
						}
					}
					for(var l=0;l<$(".minutes ul li").length;l++){//给班级添加选中样式
						if($(".minutes ul li").eq(l).attr("data-teamid")==appGetTeamId){
							$(".minutes ul li").eq(l).click();
							
						}
					}
					//				flag = "false";
					$(".c_name").text(teamname).prop('id',2);//把班级名称写入到选择项中
					tableList(urlForTableList);
					num++;
					//				   select();
				}
				//			   return teamId;
			}loader.close();
		},  
		error:function(){  
		    alert('fail：获取班级列表错误');  
		    loader.close();
		}  
	});
}
function hidediv(){//recordSchoolYear
	var userId = $(".controllerData").attr("data-userId");
	var role = $(".controllerData").attr("data-role");
//	alert(schoolId+"////"+recordSchoolYear);
	schoolYearList(urlForSchoolYearList,urlForClassList,urlForTableList,urlSearchForGrade);
	$(".tank").hide();
}
//function tableList(urlForTableList){//从红旗公示跳转进来才触发
//	//console.log(appGetTermCode+"////"+appGetTeamId+"////"+appGetBeginDate+"////"+appGetEndDate);
//	if(urlForTableList != null){
//		$.ajax({  
//			type : "GET",  
//			async : false,
//			url : urlForTableList,
//			dataType : "jsonp",//数据类型为jsonp  
//			jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
//			data:{
//				termCode : appGetTermCode, //根据学期接口得到termCode
//				teamId : appGetTeamId,  //64
//				beginDate : appGetBeginDate,  //"2016-10-16"
//				endDate : appGetEndDate  // "2016-10-22"
//			},
//			success : function(data){   //console.log("班级"+JSON.stringify(data));
//			var data=data.data;
//			var napededuct=0;
//			var napeawarded=0;
//			var napepercent=0;
////			var naperatio=0;
//			$(".table-list").html("");
//			if(data!=null && data.length>0){
//				for(var i=0;i<data.length;i++){
//					var ratio = 0;
//					var percent = 0;
//					if(data[i].totalScore<0){
//						ratio = data[i].deductRatio;
//					}else if(data[i].totalScore>0){
//						ratio = data[i].addRatio;
//					}
//					if(data[i].totalScore<0){
//						$(".table-list").append('<tr><td class="project"><p class="name">'+data[i].objectName+'</p></td>'+
//								'<td><span class="punctuation">'+data[i].totalScore+'</span>'+//总分
//								'<div class="schedule">'+
//								'<p class="punctuation" style="width:'+percent+'";></p></div></td></tr>');
//					}else{
//						$(".table-list").append('<tr><td class="project"><p class="name">'+data[i].objectName+'</p></td>'+
//								'<td><span class="green">'+data[i].totalScore+'</span>'+//总分
//								'<div class="schedule">'+
//								'<p class="green" style="width:'+percent+'";></p></div></td></tr>');
//					}
//					napededuct += parseFloat(data[i].deductScore);
//					napeawarded += parseFloat(data[i].addScore);
//// 					naperatio += data[i].deductRatio;
//				}
//				teamTotal = parseFloat(napededuct + napeawarded).toFixed(1);
//				$(".details_awarded").html("总分"+parseFloat(teamTotal).toFixed(1));
//				$(".nape_deduct").html("扣分项"+parseFloat(napededuct).toFixed(1));
//				$(".nape_awarded").html("加分项+"+parseFloat(napeawarded).toFixed(1));
//			}else{
//				$(".table-list").html("").append('<tr><td class="project"><p class="name">'+'暂无数据'+'</p></td>'+
//						'<td><span class="punctuation">'+0+'</span>'+
//						'<div class="schedule">'+
//				'<p class="punctuation" style="width:0%";></p></div></td></tr>');
//				$(".details_awarded").html("总分+"+0);
//				$(".nape_deduct").html("扣分项"+0);
//				$(".nape_awarded").html("加分项"+0);
//			}
//			},
//			error:function(){  
//				alert('fail');  
//			}  
//		});
//	}
//}
//-----------------------------------------------------其他-----------------------------------------------------------//
function getmonth(){
	var myDate = new Date().getFullYear();
}
function week(){
	var term=$('#xq').val();
	if("" === term || "undefind" === term){
		return false;
	}
	var begin = bgDate;
	var end = edDate;
	$.getNewWeek({
		"selector" : "#select_week",
		"begin" : begin,
		"end" : end,
		"isClear" : false,
		"today" : "",
		"isSelectCurrentWeek" : true,
		"clearedOptionTitle" : "请选择周次"
	});
}
function month(){
	var term=$('#xq').val();
	if("" === term || "undefind" === term){
		return false;
	}
	var begin = bgDate;
	var end = edDate;
	$.getNewMonth({
		"selector" : "#select_month",
		"begin" : begin,
		"end" : end,
		"isClear" : false,
		"today" : "",
		"isSelectCurrentWeek" : true,
		"clearedOptionTitle" : "请选择月份"
	});
}
//-------------------------------------------------------跨域获取数据------------------------------------------------------------//
function teamH5searchForTeam(newyear,newmonth,urlSearchForTeam){
//	termCode = $(".year").attr("data-termCode");
	var napededuct=0;//扣分项
	var napeawarded=0;//加分项
	var napepercent=0;//年级占比
	var naperatio=0;//扣分占比（貌似用不到.....）
	var teamTotal=0;//班级总分
	var gradeTotal=0;//年级总分
	$(".details_awarded").html("");
	$(".nape_deduct").html("");
	$(".nape_awarded").html("");
	loader.show();
	if($("#select_div_week").is(":hidden")){
		var month = newmonth;
		var year = newyear;
		beginDate = year+"-"+selectmonth+"-1";
		var lastdate  = new Date(year,selectmonth,0);   
		endDate = year + '-' + selectmonth + '-' + lastdate.getDate();//alert("beginDate_month:"+beginDate);alert("endDate_month:"+endDate);
		$.ajax({  
			type : "GET",  
			url : urlSearchForTeam,
			dataType : "jsonp",//数据类型为jsonp  
			jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
			data:{
				termCode  : termCode,
				teamId : teamId,//teamId在选择年级班级的时候获取
				beginDate : beginDate,
				endDate : endDate
			},
			success : function(data){    //console.log("班级"+JSON.stringify(data));
				var data=data.data;
				$(".table-list").html("");
				$(".project").html("项目");
				if(data!=null && data.length>0){
					for(var i=0;i<data.length;i++){
						var ratio = 0;
						var percent = 0;
						if(data[i].totalScore<0){
							ratio = data[i].deductRatio;
						}else if(data[i].totalScore>0){
							ratio = data[i].addRatio;
						}
						var percent =  parseFloat(ratio * 100).toFixed(1) + "%";
						if(data[i].totalScore<0){
							$(".table-list").append('<tr><td class="project"><p class="name">'+data[i].objectName+'</p></td>'+
													'<td><span class="punctuation">'+data[i].totalScore+'</span>'+//总分
													'<div class="schedule">'+
															'<p class="punctuation" style="width:'+percent+'";></p></div></td></tr>');
						}else{
							$(".table-list").append('<tr><td class="project"><p class="name">'+data[i].objectName+'</p></td>'+
													'<td><span class="green">'+data[i].totalScore+'</span>'+//总分
														'<div class="schedule">'+
													'<p class="green" style="width:'+percent+'";></p></div></td></tr>');
						}
						napededuct += parseFloat(data[i].deductScore);
						napeawarded += parseFloat(data[i].addScore);
//	 					naperatio += data[i].deductRatio;
					}
					teamTotal = parseFloat(napededuct + napeawarded).toFixed(1);
					$(".details_awarded").html("总分"+parseFloat(teamTotal).toFixed(1));
					$(".nape_deduct").html("扣分项"+parseFloat(napededuct).toFixed(1));
					$(".nape_awarded").html("加分项+"+parseFloat(napeawarded).toFixed(1));
					loader.close();
				}else{
					$(".table-list").html("").append('<tr><td class="project"><p class="name">'+'暂无数据'+'</p></td>'+
											'<td><span class="punctuation">'+0+'</span>'+
											'<div class="schedule">'+
													'<p class="punctuation" style="width:0%";></p></div></td></tr>');
					$(".details_awarded").html("总分+"+0);
					$(".nape_deduct").html("扣分项"+0);
					$(".nape_awarded").html("加分项"+0);
					loader.close();
				}
			},
			error:function(){  
			    alert('fail：获取班级数据错误');  
			    loader.close();
			}  
		});
	}else if($("#select_div_month").is(":hidden")){
//			beginDate=$(this).data("prev");//获得周次开始的时间
//			endDate=$(this).data("next");//获得周次结束的时间   
//			console.log(termCode+"////"+teamId+"////"+beginDate+"////"+endDate);
			$.ajax({  
				type : "GET",  
				url : urlSearchForTeam,
				dataType : "jsonp",//数据类型为jsonp  
				jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
				data:{
					termCode  : termCode,
					teamId : teamId,//teamId在选择年级班级的时候获取
					beginDate : beginDate,
					endDate : endDate
				},
				success : function(data){   //console.log("班级"+JSON.stringify(data));
					var data=data.data;
					$(".table-list").html("");
					$(".project").html("项目");
					if(data!=null && data.length>0){
						for(var i=0;i<data.length;i++){
							var ratio = 0;
							var percent = 0;
							if(data[i].totalScore<0){
								ratio = data[i].deductRatio;
							}else if(data[i].totalScore>0){
								ratio = data[i].addRatio;
							}
							var percent =  parseFloat(ratio * 100).toFixed(1) + "%";
							if(data[i].totalScore<0){
								$(".table-list").append('<tr><td class="project"><p class="name">'+data[i].objectName+'</p></td>'+
														'<td><span class="punctuation">'+data[i].totalScore+'</span>'+//总分
														'<div class="schedule">'+
																'<p class="punctuation" style="width:'+percent+'";></p></div></td></tr>');
							}else{
								$(".table-list").append('<tr><td class="project"><p class="name">'+data[i].objectName+'</p></td>'+
										'<td><span class="green">'+data[i].totalScore+'</span>'+//总分
										'<div class="schedule">'+
												'<p class="green" style="width:'+percent+'";></p></div></td></tr>');
							}
							napededuct += parseFloat(data[i].deductScore);
							napeawarded += parseFloat(data[i].addScore);
//		 					naperatio += data[i].deductRatio;
						}
						teamTotal = parseFloat(napededuct + napeawarded).toFixed(1);
						$(".details_awarded").html("总分"+parseFloat(teamTotal).toFixed(1));
						$(".nape_deduct").html("扣分项"+parseFloat(napededuct).toFixed(1));
						$(".nape_awarded").html("加分项+"+parseFloat(napeawarded).toFixed(1));
						loader.close();
					}else{
						$(".table-list").html("").append('<tr><td class="project"><p class="name">'+'暂无数据'+'</p></td>'+
												'<td><span class="punctuation">'+0+'</span>'+
												'<div class="schedule">'+
														'<p class="punctuation" style="width:0%";></p></div></td></tr>');
						$(".details_awarded").html("总分+"+0);
						$(".nape_deduct").html("扣分项"+0);
						$(".nape_awarded").html("加分项"+0);
						loader.close();
					}
				},
				error:function(){  
					alert('fail：获取班级数据错误');  
					loader.close();
				}  
			});
	}
}
function teamH5searchForGrade(newyear,newmonth,urlSearchForGrade){//跨域获取数据
//	termCode = $(".year").attr("data-termCode");
	var napededuct=0;//扣分项
	var napeawarded=0;//加分项
	var napepercent=0;//年级占比
	var naperatio=0;//扣分占比（貌似也用不到.....）
	var gradeTotal=0;//年级总分
	var schoolTotal=0;//全校总分
	loader.show();
	$(".details-table").find(".dataPercent").html("年级占比");
	if($("#select_div_week").is(":hidden")){
		var month = newmonth;
		var year = newyear;
		beginDate = year+"-"+selectmonth+"-1";
		var lastdate  = new Date(year,selectmonth,0);   
	    endDate = year + '-' + selectmonth + '-' + lastdate.getDate();//alert("beginDate_month:"+beginDate);alert("endDate_month:"+endDate);
	    $.ajax({  
			type : "GET",  
			url : urlSearchForGrade,
			dataType : "jsonp",//数据类型为jsonp  
			jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
			data:{
				termCode  : termCode,
				gradeId : gradeId,//gradeId在选择年级班级的时候获取
				beginDate : beginDate,
				endDate : endDate
			},
			success : function(data){   //console.log("年级"+JSON.stringify(data));
			$(".table-list").html("")
				var data=data.data;
			$(".project").html("班级");
				if(data!=null && data.length>0){
					for(var i=0;i<data.length;i++){
						var ratio = 0;
						var percent = 0;
						if(data[i].totalScore<0){
							ratio = data[i].deductRatio;
						}else if(data[i].totalScore>0){
							ratio = data[i].addRatio;
						}
						var percent =  parseFloat(ratio * 100).toFixed(1) + "%";
						if(data[i].totalScore<0){
							$(".table-list").append('<tr><td class="project"><p class="name">'+data[i].objectName+'</p></td>'+
													'<td><span class="punctuation">'+data[i].totalScore+'</span>'+//总分
													'<div class="schedule">'+
													'<p class="punctuation" style="width:'+percent+'";></p></div></td></tr>');
						}else{
							$(".table-list").append('<tr><td class="project"><p class="name">'+data[i].objectName+'</p></td>'+
													'<td><span class="green">'+data[i].totalScore+'</span>'+//总分
													'<div class="schedule">'+
														'<p class="green" style="width:'+percent+'";></p></div></td></tr>');
						}
						napededuct += parseFloat(data[i].deductScore);
						napeawarded += parseFloat(data[i].addScore);
	 					naperatio += data[i].deductRatio;
					}
					gradeTotal = napededuct + napeawarded;
					var all = parseFloat(napededuct + napeawarded).toFixed(1);
					$(".details_awarded").html("总分"+all);
					$(".nape_deduct").html("扣分项"+parseFloat(napededuct).toFixed(1));
					$(".nape_awarded").html("加分项+"+parseFloat(napeawarded).toFixed(1));
					loader.close();
				}else{
					$(".table-list").html("").append('<tr><td class="project"><p class="name">'+'暂无数据'+'</p></td>'+
											'<td><span class="punctuation">'+0+'</span>'+
											'<div class="schedule">'+
													'<p class="punctuation" style="width:0%";></p></div></td></tr>');
					$(".details_awarded").html("总分+"+0);
					$(".nape_deduct").html("扣分项"+0);
					$(".nape_awarded").html("加分项"+0);
					loader.close();
				}
			},
			error:function(){  
				alert('fail：获取年级数据错误');  
				loader.close();
			}  
		});
	}else if($("#select_div_month").is(":hidden")){
//			beginDate=$(this).data("prev");//获得周次开始的时间
//			endDate=$(this).data("next");//获得周次结束的时间   alert("beginDate_week:"+beginDate);alert("endDate_week:"+endDate);
			$.ajax({  
				type : "GET",  
				url : urlSearchForGrade,
				dataType : "jsonp",//数据类型为jsonp  
				jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
				data:{
					termCode  : termCode,
					gradeId : gradeId,//gradeId在选择年级班级的时候获取
					beginDate : beginDate,
					endDate : endDate
				},
				success : function(data){   //console.log("年级"+JSON.stringify(data));
				$(".table-list").html("")
					var data=data.data;
				$(".project").html("班级");
					if(data!=null && data.length>0){
						for(var i=0;i<data.length;i++){
							var ratio = 0;
							var percent = 0;
							if(data[i].totalScore<0){
								ratio = data[i].deductRatio;
							}else if(data[i].totalScore>0){
								ratio = data[i].addRatio;
							}
							var percent =  parseFloat(ratio * 100).toFixed(1) + "%";
							if(data[i].totalScore<0){
								$(".table-list").append('<tr><td class="project"><p class="name">'+data[i].objectName+'</p></td>'+
														'<td><span class="punctuation">'+data[i].totalScore+'</span>'+//总分
														'<div class="schedule">'+
																'<p class="punctuation" style="width:'+percent+'";></p></div></td></tr>');
							}else{
								$(".table-list").append('<tr><td class="project"><p class="name">'+data[i].objectName+'</p></td>'+
										'<td><span class="green">'+data[i].totalScore+'</span>'+//总分
										'<div class="schedule">'+
												'<p class="green" style="width:'+percent+'";></p></div></td></tr>');
							}
							napededuct += parseFloat(data[i].deductScore);
							napeawarded += parseFloat(data[i].addScore);
		 					naperatio += data[i].deductRatio;
						}
						gradeTotal = napededuct + napeawarded;
						var all = parseFloat(napededuct + napeawarded).toFixed(1);//总分
						$(".details_awarded").html("总分"+all);
						$(".nape_deduct").html("扣分项"+parseFloat(napededuct).toFixed(1));
						$(".nape_awarded").html("加分项+"+parseFloat(napeawarded).toFixed(1));
						loader.close();
					}else{
						$(".table-list").html("").append('<tr><td class="project"><p class="name">'+'暂无数据'+'</p></td>'+
												'<td><span class="punctuation">'+0+'</span>'+
												'<div class="schedule">'+
														'<p class="punctuation" style="width:0%";></p></div></td></tr>');
						$(".details_awarded").html("总分+"+0);
						$(".nape_deduct").html("扣分项"+0);
						$(".nape_awarded").html("加分项"+0);
						loader.close();
					}
				},
				error:function(){  
					alert('fail：获取年级数据错误');  
					loader.close();
				}  
			});
	}
}
function teamH5searchForSchool(newyear,newmonth,urlSearchForSchool){//跨域获取数据
	var role = $(".controllerData").attr("data-role");
	if(role == "CLASS_MASTER" || role == "SUBJECT_TEACHER"){
		return false;
	}
//	termCode = $(".year").attr("data-termCode");
	$(".details-table").find(".dataPercent").html("全校占比");
	loader.show();
	if($("#select_div_week").is(":hidden")){
		var month = newmonth;
		var year = newyear;
		beginDate = year+"-"+selectmonth+"-1";
		var lastdate  = new Date(year,selectmonth,0);   
	    endDate = year + '-' + selectmonth + '-' + lastdate.getDate();//alert("beginDate_month:"+beginDate);alert("endDate_month:"+endDate);
	    $.ajax({  
			type : "GET",  
			async : false,
			url : urlSearchForSchool,
			dataType : "jsonp",//数据类型为jsonp  
			jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
			data:{
				termCode  : termCode,
				schoolId : schoolId,
				beginDate : beginDate,
				endDate : endDate
			},
			success : function(data){   //console.log(JSON.stringify(data));
			$(".table-list").html("")
				var data=data.data;
				var napededuct=0;
				var napeawarded=0;
				var napepercent=0;
				var naperatio=0;
				$(".project").html("年级");
				if(data!=null && data.length>0){
					for(var i=0;i<data.length;i++){
						var ratio = 0;
						var percent = 0;
						if(data[i].totalScore<0){
							ratio = data[i].deductRatio;
						}else if(data[i].totalScore>0){
							ratio = data[i].addRatio;
						}
						var percent =  parseFloat(ratio * 100).toFixed(1) + "%";
						if(data[i].totalScore<0){
							$(".table-list").append('<tr><td class="project"><p class="name">'+data[i].objectName+'</p></td>'+
													'<td><span class="punctuation">'+data[i].totalScore+'</span>'+//总分
													'<div class="schedule">'+
															'<p class="punctuation" style="width:'+percent+'";></p></div></td></tr>');
						}else{
							$(".table-list").append('<tr><td class="project"><p class="name">'+data[i].objectName+'</p></td>'+
									'<td><span class="green">'+data[i].totalScore+'</span>'+//总分
									'<div class="schedule">'+
											'<p class="green" style="width:'+percent+'";></p></div></td></tr>');
						}
						napededuct += data[i].deductScore;//扣分
						napeawarded += parseFloat(data[i].addScore);//加分
						naperatio += data[i].deductRatio;
					}
					napepercent = parseFloat((naperatio * 100).toFixed(1)) + "%";
					var all = parseFloat(napededuct + napeawarded).toFixed(1);//总分
					$(".details_awarded").html("总分"+all);
					$(".nape_deduct").html("扣分项"+parseFloat(napededuct).toFixed(1));
					$(".nape_awarded").html("加分项+"+parseFloat(napeawarded).toFixed(1));
					loader.close();
				}else{
					$(".table-list").html("").append('<tr><td class="project"><p class="name">'+'暂无数据'+'</p></td>'+
											'<td><span class="punctuation">'+0+'</span>'+
											'<div class="schedule">'+
													'<p class="punctuation" style="width:0%";></p></div></td></tr>');
					$(".details_awarded").html("总分+"+0);
					$(".nape_deduct").html("扣分项"+0);
					$(".nape_awarded").html("加分项"+0);
					loader.close();
				}
			},
			error:function(){  
				alert('fail：获取全校数据错误');  
				loader.close();
			}  
		});
	}else if($("#select_div_month").is(":hidden")){
//			beginDate=$(this).data("prev");//获得周次开始的时间
//			endDate=$(this).data("next");//获得周次结束的时间   
//			alert("beginDate_week:"+beginDate+"//////"+"endDate_week:"+endDate);
		c_name = $(".c_name").attr("id");
			if(c_name==3){
			$.ajax({  
				type : "GET",  
				async : false,
				url : urlSearchForSchool,
				dataType : "jsonp",//数据类型为jsonp  
				jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
				data:{
					termCode  : termCode,
					schoolId : schoolId,
					beginDate : beginDate,
					endDate : endDate
				},
				success : function(data){   //console.log(JSON.stringify(data));
				$(".table-list").html("")
					var data=data.data;
					var napededuct=0;
					var napeawarded=0;
					var napepercent=0;
					var naperatio=0;
					$(".project").html("年级");
					if(data!=null && data.length>0){
						for(var i=0;i<data.length;i++){
							var ratio = 0;
							var percent = 0;
							if(data[i].totalScore<0){
								ratio = data[i].deductRatio;
							}else if(data[i].totalScore>0){
								ratio = data[i].addRatio;
							}
							var percent =  parseFloat(ratio * 100).toFixed(1) + "%";
							if(data[i].totalScore<0){
								$(".table-list").append('<tr><td class="project"><p class="name">'+data[i].objectName+'</p></td>'+
														'<td><span class="punctuation">'+data[i].totalScore+'</span>'+//总分
														'<div class="schedule">'+
																'<p class="punctuation" style="width:'+percent+'";></p></div></td></tr>');
							}else{
								$(".table-list").append('<tr><td class="project"><p class="name">'+data[i].objectName+'</p></td>'+
										'<td><span class="green">'+data[i].totalScore+'</span>'+//总分
										'<div class="schedule">'+
												'<p class="green" style="width:'+percent+'";></p></div></td></tr>');
							}
							napededuct += parseFloat(data[i].deductScore);
							napeawarded += parseFloat(data[i].addScore);
							naperatio += data[i].deductRatio;
						}
						napepercent = parseFloat((naperatio * 100).toFixed(1)) + "%";
						var all = parseFloat(napededuct + napeawarded).toFixed(1);
						$(".details_awarded").html("总分"+all);
						$(".nape_deduct").html("扣分项"+parseFloat(napededuct).toFixed(1));
						$(".nape_awarded").html("加分项+"+parseFloat(napeawarded).toFixed(1));
						loader.close();
					}else{
						$(".table-list").html("").append('<tr><td class="project"><p class="name">'+'暂无数据'+'</p></td>'+
												'<td><span class="punctuation">'+0+'</span>'+
												'<div class="schedule">'+
														'<p class="punctuation" style="width:0%";></p></div></td></tr>');
						$(".details_awarded").html("总分+"+0);
						$(".nape_deduct").html("扣分项"+0);
						$(".nape_awarded").html("加分项"+0);
						loader.close();
					}
				},
				error:function(){ 
					alert('fail：获取全校数据错误');  
					loader.close();
				}  
			});
			}
	}
}