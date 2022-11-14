//----------------------------------------------------------顶端选择控件-----------------------------------------------------//
var bgDate;
var edDate;
var teamId;
var termCode;
var gradeId;
var newyear;
var newmonth;
var studentId;
var selectmonth;
var appGetTermCode = new Array();
var getSchoolYear;
var beginDate;
var endDate;
var recordSchoolYear;
var prompt;
var loader = new loadwkx();
$(function(){
	
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
    	$(".choice-term").css("color","#8f8f9e");
        $(".choice-term .tog").text("﹀").removeClass("tianjia");
    	$(".choice-type").css("color","#8f8f9e");
        $(".choice-type .tog").text("﹀").removeClass("tianjia");
    	$(".choice-month").css("color","#8f8f9e");
        $(".choice-month .tog").text("﹀").removeClass("tianjia");
    	$(".choice-week").css("color","#8f8f9e");
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
     $("body").on("click",".choice_remove ul li",function(){//选择年级时获取gradeId
    	 var role = $(".controllerData").attr("data-role");
    	 $(".choice_remove ul li").removeClass("grade");
    	 $(this).addClass("grade");
    	 if(role != "PARENT"){
    		 gradeId = $(this).attr("data-gradeId");//获取gradeId
    		 var i=$(this).index();
    		 $(".minutes ul").hide();
    		 $(".minutes ul").eq(i).show();
    		 if($(this).hasClass("all")){
    			 $(".c_name").html("");
    			 var grade_name=$(this).text();
    			 if(role == 'CLASS_MASTER'){
    				 $(".c_name").text("全校").prop('id',2);
    			 }else{
    				 $(".c_name").text("全校").prop('id',3);
    			 }
    			 $(".tog").removeClass("show");
    			 $(".choice-class").css("color","#8f8f9e")
    			 $(".tog").text("﹀").removeClass("tianjia");
    			 $(".choice_show").toggle();
    			 $("#choice_bg").hide();
    		 }else if($(this).hasClass("xs_name")){
    			 var grade_name=$(this).text();
    			 $(".c_name").text($(this).text());
    			 $(".tog").removeClass("show");
    			 $(".choice-class").css("color","#8f8f9e")
    			 $(".tog").text("﹀").removeClass("tianjia");
    			 $(".choice_show").toggle();
    			 $("#choice_bg").hide();
    		 }
    	 }
    	if(role == "PARENT"){//家长角色进入时获取查询子女的条件
    		$(".c_name").html($(this).text());
       		termCode = $(this).attr("data-termCode");
    		teamId = $(this).attr("data-teamId");
    		studentId = $(this).attr("data-studentId");
    		termName = $(this).attr("data-termName");
    		$(".xq_name").html(termName);
    		$(".choice_prom").hide();
    		XQname=$(this).attr("data-termName");//学年学期的名称从学生属性里获取
    		bgDate=$(this).attr("data-beginDate");//开始和结束时间从学生属性里获取
			edDate=$(this).attr("data-finishDate");//同上
			month();
			week();
			var today = new Date().Format("yyyy-MM-dd");
			var $month = (new Date()).getMonth()+1;
			var $year = (new Date()).getFullYear();
			if(bgDate<today && edDate>today){//默认当前
				$("#select_month li[data-month='" + $month + "']").click();
			}else{
				$("#select_month li").eq(0).click();
			}
			parentFindStudentList(newyear,newmonth,teamId,termCode,studentId);
			$('.xq_name').text(XQname);
			 $(this).addClass("grade");
			 $(".choice-class").css("color","#8f8f9e")
			$(".choice_show").hide();
			$("#choice_bg").hide();
        }
     });
     $("body").on("click",".minutes ul li",function(){//选择班级时获取teamId
        $(".minutes ul li").addClass("det").removeClass("year");
        $(this).addClass("year").removeClass("det");
        var class_name=$(this).text();
        var grade_name=$(".choice_remove ul li.grade").text();
		if(class_name == "全部"){
			$(".c_name").html("");
			$(".specific").find(".count").html("");
			$(".grade_class").html("<p>全年级占比 </p>");
			//if(role != "CLASS_MASTER" && role != "PARENT"){
			if(role != "CLASS_MASTER" && role != 'PARENT'){
        		$(".c_name").text(grade_name+"全部").prop('id',1);
        		$('#container').html("");
            	select();
            	var message = $(".message").attr("data-message");
            	if(message=='normal'){
            		bystu();
            	}else{
            		myself(newmonth);
            	}
            }
        }else{//获取teamId
        	$(".specific").find(".count").html("");
			$(".grade_class").html("<p>全年级占比 </p>");
        	if(role != "CLASS_MASTER" && role != 'PARENT'){
        		$(".c_name").text($(this).attr("data-teamName")).prop('id',2); //班级（1年级（2）班）
        		teamId = $(this).attr("data-teamId");
        		$('#container').html("");
            	select();
            	var message = $(".message").attr("data-message");
            	if(message=='normal'){
            		bystu();
            	}else{
            		myself(newmonth);
            	}
            }
        }
        $(".tog").removeClass("show");
        $(".choice-class").css("color","#8f8f9e")
        $(".tog").text("﹀").removeClass("tianjia");
        $(".choice_show").toggle();
        $("#choice_bg").toggle();
        
     });

   //学年切换
     $(".choice-term").click(function(){
    	 var role = $(".controllerData").attr("data-role");
      	if(role != "PARENT"){
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
          
        var $top=$(this).children("p").children(".tog");
        var t=$top.text();
        if(t=="﹀"){
        	$(".choice-class").css("color","#8f8f9e");
            $(".choice-class .tog").text("﹀").removeClass("tianjia");
        	$(".choice-type").css("color","#8f8f9e");
            $(".choice-type .tog").text("﹀").removeClass("tianjia");
        	$(".choice-month").css("color","#8f8f9e");
            $(".choice-month .tog").text("﹀").removeClass("tianjia");
        	$(".choice-week").css("color","#8f8f9e");
            $(".choice-week .tog").text("﹀").removeClass("tianjia");
            $("choice-term .tog").addClass("show");
            $(this).css("color","#39a3ef");
                $top.text("︿").addClass("tianjia");
            
            }else{
                $(".tog").removeClass("show");
                $(this).css("color","#8f8f9e");
                $top.text("﹀").removeClass("tianjia");
        }
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
          
        var $top=$(this).children("p").children(".tog");
        var t=$top.text();
        if(t=="﹀"){
        	$(".choice-class").css("color","#8f8f9e");
            $(".choice-class .tog").text("﹀").removeClass("tianjia");
        	$(".choice-term").css("color","#8f8f9e");
            $(".choice-term .tog").text("﹀").removeClass("tianjia");
        	$(".choice-month").css("color","#8f8f9e");
            $(".choice-month .tog").text("﹀").removeClass("tianjia");
        	$(".choice-week").css("color","#8f8f9e");
            $(".choice-week .tog").text("﹀").removeClass("tianjia");
            $("choice-type .tog").addClass("show");
            $(this).css("color","#39a3ef");
                $top.text("︿").addClass("tianjia");
            
            }else{
                $(".tog").removeClass("show");
                $(this).css("color","#8f8f9e");
                $top.text("﹀").removeClass("tianjia");
        }
   	 
    });
     $("body").on("click",".choice_y ul li",function(){
//    	 $(this).css("display","block");
//    	 $(".personal li div").removeClass("pitch-on");
         $(".choice_y ul li").removeClass("grade");
         $(this).addClass("grade");
         var type_name=$(this).text();
         $(".c_type").text(type_name)
         
         $(".tog").removeClass("show");
         $(".choice-type").css("color","#8f8f9e")
         $(".tog").text("﹀").removeClass("tianjia");
         $(".choice_t").toggle();
         $("#choice_bg").toggle();
         
         var select = $(this).data("id");
     	if(select == 1){
     		document.getElementById("select_div_month").style.display="block";
     		document.getElementById("select_div_week").style.display="none";
     		var today = new Date().Format("yyyy-MM-dd");
			var $month = (new Date()).getMonth()+1;
			var $year = (new Date()).getFullYear();
			if(bgDate<today && edDate>today){
				$("#select_month li[data-month='" + $month + "']").click();
			}else{
				$("#select_month li").eq(0).click();
			}
     	}else if(select == 2){
     		document.getElementById("select_div_week").style.display="block";
     		document.getElementById("select_div_month").style.display="none";
     		$("#select_week li").eq(0).click();
     	} 
     	var message = $(".message").attr("data-message");
    	if(message=='normal'){
    		bystu();
    	}else{
    		myself(newmonth)//周次和月份切换时刷新学生列表以达到去除样式的效果
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
     $("body").on("click",".choice_mo ul li",function(){////点击月份
         $(".choice_mo ul li").removeClass("grade");
         $(this).addClass("grade");
         var type_name=$(this).text();
         $(".c_month").text(type_name)
         
         $(".tog").removeClass("show");
         $(".choice-month").css("color","#8f8f9e");
         $(".tog").text("﹀").removeClass("tianjia");
         $(".choice_mon").hide();
         $("#choice_bg").hide();
         newyear = $(this).attr("date-year");
         newmonth = $(this).text();
         selectmonth = $(this).attr("data-month");
         $('#container').html("");
         var role = $(".controllerData").attr("data-role");
         if(role=="PARENT"){
//        	 if(teamCode!="" && teamCode!=null){
        	 $(".choice_prom").hide();
        		 parentFindStudentList(newyear,newmonth,teamId,termCode,studentId);
//        	 }
         }else{
        	 select();
         }
         var message = $(".message").attr("data-message");
     	if(message=='normal'){
     		bystu();
     	}else{
     		myself(newmonth);
     	}
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
        	$(".choice-class").css("color","#8f8f9e");
            $(".choice-class .tog").text("﹀").removeClass("tianjia");
        	$(".choice-term").css("color","#8f8f9e");
            $(".choice-term .tog").text("﹀").removeClass("tianjia");
        	$(".choice-type").css("color","#8f8f9e");
            $(".choice-type .tog").text("﹀").removeClass("tianjia");
            $("choice-week .tog").addClass("show");
            $(this).css("color","#39a3ef");
                $top.text("︿").addClass("tianjia");
            
            }else{
                $(".tog").removeClass("show");
                $(this).css("color","#8f8f9e");
                $top.text("﹀").removeClass("tianjia");
        }
    });
     $("body").on("click",".choice_we ul li",function(){//周次点击
         $(".choice_we ul li").removeClass("grade");
         $(this).addClass("grade");
         var type_name=$(this).text();
         $(".c_week").text(type_name);
         
         $(".tog").removeClass("show");
         $(".choice-week").css("color","#8f8f9e");
         $(".tog").text("﹀").removeClass("tianjia");
         $(".choice_wee").hide();
         $("#choice_bg").hide();
         var role = $(".controllerData").attr("data-role");
         newyear = $(this).attr("date-year");
         newmonth = $(this).text();
         selectmonth = $(this).attr("data-month");
         beginDate=$(this).data("prev");//获得周次开始的时间
		 endDate=$(this).data("next");//获得周次结束的时间   
         if(role!="PARENT"){//如果不是家长角色进入此页面，正常查询对应的默认数据
        	 $('#container').html("");
        	 select();
         }else{
        	 $(".choice_prom").hide();
        	 parentFindStudentList(newyear,newmonth,teamId,termCode,studentId);
         }
         var message = $(".message").attr("data-message");
     	if(message=='normal'){
     		bystu();
     	}else{
     		myself(newmonth);
     	}
  });
//   点击遮罩层
     $("#choice_bg").click(function(){
    	 $(".tog").removeClass("show");
         $(".choice-class,.choice-term,.choice-type,.choice-week,.choice-month").css("color","#8f8f9e");
         $(".tog").text("﹀").removeClass("tianjia");
         $(".choice_show,.choice_prom,.choice_t,.choice_mon,.choice_wee").hide();
       $("#choice_bg").toggle();
     });
    $("body").on("click",".school-year ul li",function(){
//    		recordSchoolYear = $(this).attr("data-schoolYear");//记录每一次选中的学年是多少
            $(".school-year ul li").removeClass("year");
            $(this).addClass("year");
            var grade_name=$(this).parent().parent().children("span").text();
            var yearName = $(this).text();
            $(".xq_name").text(grade_name+yearName);

            $(".choice-term").css("color","#8f8f9e")
            $(".tog").text("﹀").removeClass("tianjia");
            $(".choice_prom").toggle();
          	$("#choice_bg").toggle();
        	var begin=$(".year").attr('data-beginDate');
        	
        	var end=$(".year").attr('data-endDate');
        	termCode = $(this).attr("data-termCode");
        	getSchoolYear = $(this).attr("data-schoolYear");
        	schoolYear = $(this).attr("data-schoolYear");
        	beginDate = $(this).attr("data-begindate");
			endDate = $(this).attr("data-enddate");
        	bgDate = $(this).attr("data-begindate");
			edDate = $(this).attr("data-enddate");
			classList(urlForClassList,urlStudent);
//        	getClassList(urlForClassList,urlStudent);
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
			if($("#select_div_week").is(':hidden')){
				var today = new Date().Format("yyyy-MM-dd");
				var $month = (new Date()).getMonth()+1;
				var $year = (new Date()).getFullYear();
				if(bgDate<today && edDate>today){
					$("#select_month li[data-month='" + $month + "']").addClass("grade");
				}else{
//					$("#select_month li").eq(0).click();
					$("#select_month li").eq(0).addClass("grade");
				}
//				$("#select_month li").eq(0).click();
			}else{
				$("#select_week li").eq(0).addClass("grade");
			}
          	//yuefen
//         	bgDate=$(".year").attr('data-beginDate');
//	     	edDate=$(".year").attr('data-endDate');
          	var date2="";
          	var myarray;
			var today = new Date().Format("yyyy-MM-dd");
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
});
//---------------------------------------------------------生成选择列表---------------------------------------------------------------//	
function schoolYearList(urlForSchoolYearList,urlForClassList,urlForTableList,urlStudent){
	var role = $(".controllerData").attr("data-role");
	if(role != "PARENT"){
		schoolId = $(".controllerData").attr("data-schoolId");
		$.ajax({
			type : "GET",  
			url : urlForSchoolYearList,  ////url : "${sca:getESBServerUrl()}/school/basic/term/listAll/jsonp"
			dataType : "jsonp",//数据类型为jsonp  
			jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
			data:{
				schoolId  : schoolId //这里需要传schoolId
			},
			success : function(data){  //console.log(JSON.stringify(data));//data = eval("("+data+")");
				$(".choice_prom").html("");
				$(".school-year ul").html("");
				var data=data.data;
				var role = $(".controllerData").attr("data-role");
//			if(role != "PARENT"){
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
							recordSchoolYear = data[i].schoolYear;
							var myarray = beginDate.split("-");
							var date2="";
							if (myarray[1].charAt(0) != '0') {
								date2 = myarray[0]+ "年"+ myarray[1]+ "月";
							} else {
								date2 = myarray[0]+ "年"+ myarray[1].charAt(1)+ "月";
							}
							$('#d4').val(date2);
						}else{
							$(".school-year").eq(i).children("ul").append("<li data-schoolYear='"+data[i].schoolYear+"' data-beginDate='"+data[i].schoolTermlist[j].beginDate+"' data-endDate='"+data[i].schoolTermlist[j].finishDate+"' data-termCode='"+data[i].schoolTermlist[j].schoolTermCode+"' >"+data[i].schoolTermlist[j].termName+"</li>");
						}
					}
				}
//			}
				classList(urlForClassList,urlStudent);
				month();
				week();
				var today = new Date().Format("yyyy-MM-dd");
				var $month = (new Date()).getMonth()+1;
				var $year = (new Date()).getFullYear();
				if(bgDate<today && edDate>today){
					$("#select_month li[data-month='" + $month + "']").click();
				}else{
					$("#select_month li").eq(0).click();
				}
				loader.close();
				//var month_current=$("#select_month li").eq(0).text();
//			$("#select_month li").eq(0).click();
				//$(".c_month").text(month_current)
				return termCode;
				return schoolYear;
				return beginDate;
				return endDate;
			},
			error:function(){  
				alert('fail:获取学年学期列表错误');  
				loader.close();
			}  
		}); 
	}else{
		$(".choice_prom").append("");
		$(".school-year").eq(0).children("ul").append("");
		$('.xq_name').text("");
		classList(urlForClassList,urlStudent);
		loader.close();
	}
}
function classList(urlForClassList,urlStudent){
	$(".choice_remove").children("ul").html("");
	$(".minutes").children("ul").html("");
	schoolId = $(".controllerData").attr("data-schoolId");
	var userId = $(".controllerData").attr("data-userId");
	var role = $(".controllerData").attr("data-role");
	if(role=="PARENT"){
		$.ajax({  
			type : "GET",  
			url : urlStudent,
			dataType : "jsonp",//数据类型为jsonp  
			jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
			data:{
				userId : userId, //17 userId
			},
			success : function(data){   //console.log("单个学生"+JSON.stringify(data));
				var data = data.data;
				if(data!=null && data.length>0){
					for(var i=0;i<data.length;i++){
						$(".choice_remove").children("ul").append("<li id='whole' data-gradeId='"+data[i].gradeId+
								"' data-teamId='"+data[i].teamId+"' class='xs_name' data-termCode='"+data[i].termCode+
								"' data-studentId='"+data[i].studentId+"' data-termName='"+data[i].termName+
								"' data-beginDate='"+data[i].beginDate+"' data-finishDate='"+data[i].finishDate+"' id='all'>"+data[i].name+"</li>");
						appGetTermCode.push(data[i].termCode);
					}
					$(".choice-class").children("p").children(".c_name").html(data[0].name);
				}else{
					$(".choice-class").children("p").children(".c_name").html("选择学生");
				}
				var xq_name=$(".school-year ul li.year").text();
				var xn_name=$(".school-year ul li.year").parent().prev().text();
				$('.xq_name').text(xn_name+xq_name);
				$("#all").addClass("grade");
				$(".choice_show").hide();
				$("#choice_bg").hide();
				$("#whole").click();
				loader.close();
			},  
			error:function(){  
				alert('fail：获取学生列表错误');  
				loader.close();
			}  
		});
	}else{
		$(".personal").html("").append("<ul></ul>");
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
			success : function(data){   //console.log("班级列表"+JSON.stringify(data));
				var data=data.data;
				$(".choice_remove").children("ul").html("");
				$(".c_name").text("全校").prop('id',3);
				if(data.length>0){
					$(".minutes").children("ul").html("");
					for(var i=0;i<data.length;i++){
						$(".choice_remove").children("ul").append("<li data-gradeId='"+data[i].gradeId+"'>"+data[i].gradeName+"</li>");//年级列表
						$(".minutes").append("<ul style='display:none'></ul>");
						for(var j=0;j<data[i].teamList.length;j++){//班级列表
							$(".minutes").children("ul").eq(i).append("<li class='datateamId' data-teamName='"+data[i].teamList[j].teamName+"' data-teamId='"+data[i].teamList[j].teamId+"'>"+data[i].teamList[j].name+"</li>");
						}
						if(role != "CLASS_MASTER" && role != "SUBJECT_TEACHER"){
							$(".minutes").children("ul").eq(i).append("<li>全部</li>");//班级全部
						}
					}
					$(".minutes ul").hide();
					if(role != "CLASS_MASTER" && role != "SUBJECT_TEACHER"){
						$(".choice_remove").children("ul").append("<li class='all' id='whole'>全校</li>");//年级全部
						$(".choice_remove .all").addClass("grade");
//						$(".all").click();
						searchForSchool(newyear,newmonth,urlSearchForSchool);
						$(".choice_show").hide();
						$("#choice_bg").hide();
					}
					if(role == "CLASS_MASTER" || role == "SUBJECT_TEACHER"){
						$(".c_name").html("");
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
						if(teamId != "" && teamId != null){
//							var message = $(".message").attr("data-message");
//			            	if(message=='normal'){
//			            		bystu();
//			            	}else{
			            		myself(newmonth);
//			            	}
						}
					}
				}else{//提示框
					teamId = "";
					$(".tank").html("");
					$(".tank").append("<div id='bg'></div>"+
										"<div id='show'>"+
											"<img src='/res/css/dygl/images/unhappy.png'>"+
											"<h3>查询失败</h3>"+
											"<p>该学年学期没有班级数据</p>"+
											"<button id='btnclose' onclick='hidediv();'>返回</button>"+
										"</div>");
					$(".tank").show();
					prompt = true;
					myself(newmonth);
				}
				loader.close();
//				$(".c_name").text("全部").prop('id',3);
//			tableList(urlForTableList); //这个方法跑起来的时候带teamId参数了
			},  
			error:function(){  
				alert('fail：获取班级列表错误');  
				loader.close();
			}  
		});
	}
}
function hidediv(){//recordSchoolYear
	var userId = $(".controllerData").attr("data-userId");
	var role = $(".controllerData").attr("data-role");
//	alert(schoolId+"////"+recordSchoolYear);
	schoolYearList(urlForSchoolYearList,urlForClassList,urlForTableList,urlStudent)
	$(".tank").hide();
}
//function tableList(){
//	$(".normaltablelist").html("");
//	$.ajax({  
//		type : "GET",  
//		url : urlForTableList,
//		dataType : "jsonp",//数据类型为jsonp  
//		jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
//		data:{
//			termCode : termCode, //根据学期接口得到termCode
//			teamId : teamId,  //64
//			beginDate : beginDate,  //"2016-10-16"
//			endDate : endDate  // "2016-10-22"
//		},
//		success : function(data){  //console.log("默认"+JSON.stringify(data));
//			var data = data.data;
//			var percent =0;
//			var napepercent = 0;
//			if(data.length>0){
//				for(var i=0;i<data.length;i++){
//					napepercent = data[i].ratio;
//					percent = parseFloat((napepercent * 100).toFixed(1)) + "%";
//					$(".normaltablelist").append("<tr><td class='project'><p class='name her'>"+data[i].objectName+"</p></td><td>"+
//													"<span class='green'>"+data[i].count+"</span>"+
//													  	"<div class='schedule'>"+
//													  		"<p class='green' style='width:"+percent+"';></p>"+
//													  	"</div>"+
//													"</td>"+
//													"<td class='range'>"+percent+"</td></tr>");
//				}
//			}else{
//				$(".normaltablelist").html("").append("<tr><td class='project'><p class='name her'>"+"暂无数据"+"</p></td><td>"+
//													"<span class='green'>"+"0"+"</span>"+
//													  	"<div class='schedule'>"+
//													  		"<p class='green' style='width:"+"0%"+"';></p>"+
//													  	"</div>"+
//													"</td>"+
//													"<td class='range'>"+"0%"+"</td></tr>");
//			}
//		},  
//		error:function(){  
//		    alert('fail');
//		}
//	});
//}
//-------------------------------------------------------跨域获取数据-----------------------------------------------------------//
function searchForTeam(newyear,newmonth,urlSearchForTeam,urlSearchForGrade){
	var getTeamId;
	$(".normaltablelist").html("");
	termCode = $(".year").attr("data-termCode");
	var percent =0;//本班占比
	var napepercent = 0;//本班占比（取值用的变量）
	var gradePercent=0;//班级在年级的占比
	var count = 0;//发展卡总数
	$(".details-table").find(".dataPercent").html("班级占比");
	var message = $(".message").attr("data-message");
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
				teamId : teamId,
				beginDate : beginDate,
				endDate : endDate
			},
			success : function(data){   //console.log("班级"+JSON.stringify(data));
				var data = data.data;
				//$(".project").html("项目");///////////////////////////////////////////////////////////////
				if(data.length>0){
					$(".normaltablelist").html("");
					$("#container").css("display","block");
					$(".specific").show();
					for(var i=0;i<data.length;i++){
						napepercent = data[i].ratio;
						percent = parseFloat((napepercent * 100).toFixed(1)) + "%";
						count += data[i].count;
						$(".normaltablelist").append("<tr><td class='project'><p class='name her'>"+data[i].objectName+"</p></td><td>"+
								"<span class='green'>"+data[i].count+"</span>"+
								"<div class='schedule'>"+
								"<p class='green' style='width:"+percent+"';></p>"+
								"</div>"+
								"</td>"+"<td class='range'>"+percent+"</td></tr>");
					}
					if(count == "0"){
						$("#container").css("display","none");
					}
					$(".tst").html("");
				}else{
					$("#container").css("display","none");
					$(".specific").hide();
					var role = $(".controllerData").attr("data-role");
					if(message=="class"){
						if(role == "CLASS_MASTER" || role == "SUBJECT_TEACHER"){
							$(".tst").html("<div style='background: #fff;width:100%;height:700px;position: absolute;top: 375px;left: 0;right: 0;'><img src='/res/css/dygl/images/kaixin.png' style='margin: 20px auto 50px;display: block;'><p style='text-align: center;font-size:24px;color:#000'>孩子们表现很好，课堂上没有不良行为记录~</p></div>");
						}else{
							$(".tst").html("<div style='background: #fff;width:100%;height:700px;position: absolute;top: 240px;left: 0;right: 0;'><img src='/res/css/dygl/images/kaixin.png' style='margin: 20px auto 50px;display: block;'><p style='text-align: center;font-size:24px;color:#000'>孩子们表现很好，课堂上没有不良行为记录~</p></div>");
						}
					}else if(message=="incentive" || message=="normal"){
						if(role == "CLASS_MASTER" || role == "SUBJECT_TEACHER"){
							$(".tst").html("<div style='background: #fff;width:100%;height:700px;position: absolute;top: 375px;left: 0;right: 0;'><img src='/res/css/dygl/images/bukaixin.png' style='margin: 20px auto 50px;display: block;'><p style='text-align: center;font-size:24px;color:#000'>孩子们当前没有受到表扬，请继续努力~</p></div>");
						}else{
							$(".tst").html("<div style='background: #fff;width:100%;height:700px;position: absolute;top: 240px;left: 0;right: 0;'><img src='/res/css/dygl/images/bukaixin.png' style='margin: 20px auto 50px;display: block;'><p style='text-align: center;font-size:24px;color:#000'>孩子们当前没有受到表扬，请继续努力~</p></div>");
						}
					}
				}
				$(".grade_class").html("");
				loader.close();
//				var message = $(".message").attr("data-message");
//				if(message=="incentive" || message=="normal"){
//					$(".project").html("评价卡");
//				}else if(message=="class"){
//					$(".project").html("不良行为");
//				}
				if(gradeId != null && gradeId != ""){
					$.ajax({  
						type : "GET",  
						url : urlSearchForGrade,
						dataType : "jsonp",//数据类型为jsonp  
						jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
						data:{
							termCode  : termCode,
							gradeId : gradeId,
							beginDate : beginDate,
							endDate : endDate
						},
						success : function(data){   //console.log("年级"+JSON.stringify(data));
							var data = data.data;
							for(var i=0;i<data.length;i++){
								if(teamId == data[i].objectId){
									gradePercent = data[i].ratio;//班级在年级的占比
								}
							}
							$(".specific").find(".count").html("");
							$(".specific").find(".count").html("总数量"+count);
							if(count != 0){
								var green = gradePercent * 100;
								var blue = 100 - green;
								var dper = (gradePercent * 100).toFixed(1)+"%";
								if(message=="incentive" || message=="normal"){
									$(".grade_class").html("<p>全年级占比 "+dper+"</p>");//判断课堂优化不显示///////////////////////////////////////////////////////
								}
								if(data != null && data != ""){
									$('#container').highcharts({
										chart: {
											plotBackgroundColor: null,
											plotBorderWidth: null,
											plotShadow: false,
										},
										colors:[
										        '#3ac982',
										        '#2299ee',
										        ],
										        title: {
										        	text: '',
										        },
										        credits:{
										        	enabled:false // 禁用版权信息
										        },
										        exporting: {
										        	enabled:false//关闭设置按钮
										        },
										        tooltip: {
										        	pointFormat: true,
										        },
										        plotOptions: {
										        	pie: {
										        		allowPointSelect: true,
										        		cursor: 'pointer',
										        		dataLabels: {
										        			enabled: false,
										        			color: '#323232',
										        			connectorColor: 'none',
										        			 format: '<b>{point.name}</b>: {point.percentage:.1f} %',
										        			style: {fontSize:"18px",}
										        		}
										        	}
										        },
										        series: [{
										        	type: 'pie',
										        	name: '',
										        	data: [
										        	       ['',   green],//发展卡数量，绿色块
										        	       ['',   blue],//年级总数，蓝色块
										        	       
										        	       ]
										        }]
									});
									loader.close();
							}else{
								$('#container').html("");
								loader.close();
							}
							}
						},
						error:function(){  
							alert('fail：获取年级参数错误');  //获取部分参数填充饼图
							loader.close();
						} 
					});
				}
			},
			error:function(){  
			    alert('fail：获取班级数据错误');  
			    loader.close();
			}  
		});
	}else if($("#select_div_month").is(":hidden")){
//		$(".specific").find(".count").html("");
//			beginDate=$(this).data("prev");//获得周次开始的时间
//			endDate=$(this).data("next");//获得周次结束的时间   
//			alert("beginDate_week:"+beginDate+"///endDate_week:"+endDate);
			$.ajax({  
				type : "GET",  
				url : urlSearchForTeam,
				dataType : "jsonp",//数据类型为jsonp  
				jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
				data:{
					termCode  : termCode,
					teamId : teamId,
					beginDate : beginDate,
					endDate : endDate
				},
				success : function(data){   //console.log("班级"+JSON.stringify(data));
					loader.close();
					var data = data.data;
//					$(".tst").html("");
//					$(".project").html("项目");
					if(data.length>0){
						$(".normaltablelist").html("");
						$("#container").css("display","block");
						$(".normaltablelist").show();
//						$(".specific").show();
						for(var i=0;i<data.length;i++){
							napepercent = data[i].ratio;
							percent = parseFloat((napepercent * 100).toFixed(1)) + "%";
							count += data[i].count;
							$(".normaltablelist").append("<tr><td class='project'><p class='name her'>"+data[i].objectName+"</p></td><td>"+
									"<span class='green'>"+data[i].count+"</span>"+
									"<div class='schedule'>"+
									"<p class='green' style='width:"+percent+"';></p>"+
									"</div>"+
									"</td>"+"<td class='range'>"+percent+"</td></tr>");
						}
						if(count == "0"){
							$("#container").css("display","none");
						}
						$(".tst").html("");
					}else{
						$(".normaltablelist").hide();
//						$(".specific").hide();
						$("#container").css("display","none");
						var role = $(".controllerData").attr("data-role");
						if(message=="class"){
							if(role == "CLASS_MASTER" || role == "SUBJECT_TEACHER"){
								$(".tst").html("<div style='background: #fff;width:100%;height:700px;position: absolute;top: 375px;left: 0;right: 0;'><img src='/res/css/dygl/images/kaixin.png' style='margin: 20px auto 50px;display: block;'><p style='text-align: center;font-size:24px;color:#000'>孩子们表现很好，课堂上没有不良行为记录~</p></div>");
							}else{
								$(".tst").html("<div style='background: #fff;width:100%;height:700px;position: absolute;top: 240px;left: 0;right: 0;'><img src='/res/css/dygl/images/kaixin.png' style='margin: 20px auto 50px;display: block;'><p style='text-align: center;font-size:24px;color:#000'>孩子们表现很好，课堂上没有不良行为记录~</p></div>");
							}
						}else if(message=="incentive" || message=="normal"){
							if(role == "CLASS_MASTER" || role == "SUBJECT_TEACHER"){
								$(".tst").html("<div style='background: #fff;width:100%;height:700px;position: absolute;top: 375px;left: 0;right: 0;'><img src='/res/css/dygl/images/bukaixin.png' style='margin: 20px auto 50px;display: block;'><p style='text-align: center;font-size:24px;color:#000'>孩子们当前没有受到表扬，请继续努力~</p></div>");
							}else{
								$(".tst").html("<div style='background: #fff;width:100%;height:700px;position: absolute;top: 240px;left: 0;right: 0;'><img src='/res/css/dygl/images/bukaixin.png' style='margin: 20px auto 50px;display: block;'><p style='text-align: center;font-size:24px;color:#000'>孩子们当前没有受到表扬，请继续努力~</p></div>");
							}
						}
					}
					loader.close();
					if(gradeId != null && gradeId != ""){
					$.ajax({  
						type : "GET",  
						url : urlSearchForGrade,
						dataType : "jsonp",//数据类型为jsonp  
						jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
						data:{
							termCode  : termCode,
							gradeId : gradeId,
							beginDate : beginDate,
							endDate : endDate
						},
						success : function(data){   //console.log("年级"+JSON.stringify(data));
							var data = data.data;
							for(var i=0;i<data.length;i++){
								if(teamId == data[i].objectId){
									gradePercent = data[i].ratio;//班级在年级的占比
								}
							}
							var green = gradePercent * 100;
							var blue = 100 - green;
							var dper = (gradePercent * 100).toFixed(1)+"%";
							$(".specific").find(".count").html("");
							$(".specific").find(".count").html("总数量"+count);
							if(message=="incentive" || message=="normal"){
								$(".grade_class").html("<p>全年级占比 "+dper+"</p>");//判断课堂优化不显示///////////////////////////////////////////////////////
							}
							if(count != 0){
								if(data != null && data != ""){
									$('#container').highcharts({
										chart: {
											plotBackgroundColor: null,
											plotBorderWidth: null,
											plotShadow: false,
										},
										colors:[
										        '#3ac982',
										        '#2299ee',
										        ],
										        title: {
										        	text: '',
										        },
										        credits:{
										        	enabled:false // 禁用版权信息
										        },
										        exporting: {
										        	enabled:false//关闭设置按钮
										        },
										        tooltip: {
										        	pointFormat: true,
										        },
										        plotOptions: {
										        	pie: {
										        		allowPointSelect: true,
										        		cursor: 'pointer',
										        		dataLabels: {
										        			enabled: false,
										        			color: '#323232',
										        			connectorColor: 'none',
										        			 format: '<b>{point.name}</b>: {point.percentage:.1f} %',
										        			style: {fontSize:"18px",}
										        		}
										        	}
										        },
										        series: [{
										        	type: 'pie',
										        	name: '',
										        	data: [
										        	       ['',   green],//发展卡数量，绿色块
										        	       ['',   blue],//年级总数，蓝色块
										        	       
										        	       ]
										        }]
									});
									loader.close();
								}else{
									$('#container').html("");
									loader.close();
								}
							}
						},
						error:function(){  
							alert('fail：获取年级参数错误');  //获取部分参数填充饼图
							loader.close();
						} 
					});
					}
				},
				error:function(){  
					alert('fail：获取班级数据错误');  
					loader.close();
				}  
			});
	}
	
}
function searchForGrade(newyear,newmonth,urlSearchForGrade,urlSearchForSchool){
	if(termCode == "" || termCode == null || termCode == "0" || termCode == 0){
		return false;
	}
//	alert("grade");
	$(".normaltablelist").html("");
	$("#container").css("display","block");
	$(".specific").find(".count").html("");
	$(".grade_class").html("");
	$("#container").html("");
	$(".details-table").find(".dataPercent").html("年级占比");
	termCode = $(".year").attr("data-termCode");
	var message = $(".message").attr("data-message");
	var percent =0;//年级占比
	var napepercent = 0;//年级占比（取值用的变量）
	var schoolPercent=0;//年级在全校的占比
	var count =0;
	loader.show();
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
				gradeId : gradeId,
				beginDate : beginDate,
				endDate : endDate
			},
			success : function(data){   //console.log("年级"+JSON.stringify(data));
				var data = data.data;
				$(".project").html("班级");
				$(".tst").html("");
				if(data.length>0){
					$(".specific").show();
					for(var i=0;i<data.length;i++){
						napepercent = data[i].ratio;
						percent = parseFloat((napepercent * 100).toFixed(1)) + "%";
						count += data[i].count;
						$(".normaltablelist").append("<tr><td class='project'><p class='name her'>"+data[i].objectName+"</p></td><td>"+
								"<span class='green'>"+data[i].count+"</span>"+
								"<div class='schedule'>"+
								"<p class='green' style='width:"+percent+"';></p>"+
								"</div>"+
								"</td>"+"<td class='range'>"+percent+"</td></tr>");
					}
					if(count == "0"){
						$("#container").css("display","none");
					}
					$(".tst").html("");
				}else{
					$(".specific").hide();
					if(message=="class"){
						$(".tst").html("<div style='background: #fff;width:100%;height:700px;position: absolute;top: 240px;left: 0;right: 0;'><img src='/res/css/dygl/images/kaixin.png' style='margin: 20px auto 50px;display: block;'><p style='text-align: center;font-size:24px;color:#000'>孩子们表现很好，课堂上没有不良行为记录~</p></div>");
					}else if(message=="incentive" || message=="normal"){
						$(".tst").html("<div style='background: #fff;width:100%;height:700px;position: absolute;top: 240px;left: 0;right: 0;'><img src='/res/css/dygl/images/bukaixin.png' style='margin: 20px auto 50px;display: block;'><p style='text-align: center;font-size:24px;color:#000'>孩子们当前没有受到表扬，请继续努力~</p></div>");
					}
				}
				$.ajax({  
					type : "GET",  
					url : urlSearchForSchool,
					dataType : "jsonp",//数据类型为jsonp  
					jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
					data:{
						termCode  : termCode,
						schoolId : schoolId,
						beginDate : beginDate,
						endDate : endDate
					},
					success : function(data){   //console.log("全校"+JSON.stringify(data));
						var data = data.data;
						for(var i=0;i<data.length;i++){
							if(gradeId == data[i].objectId){
								schoolPercent = data[i].ratio;
							}
						}
						$(".specific").find(".count").html("");
						$(".specific").find(".count").html("总数量"+count);
						if(count != 0){
							var green = schoolPercent * 100;
							var blue = 100 - green;
							var dper = (schoolPercent * 100).toFixed(1)+"%";
							if(message=="incentive" || message=="normal"){
								$(".grade_class").html("<p>全校占比 "+dper+"</p>");//判断课堂优化不显示///////////////////////////////////////////////////////
							}
							$('#container').highcharts({
								chart: {
									plotBackgroundColor: null,
									plotBorderWidth: null,
									plotShadow: false,
								},
								colors:[
								        '#3ac982',
								        '#2299ee',
								        ],
								        title: {
								        	text: '',
								        },
								        credits:{
								        	enabled:false // 禁用版权信息
								        },
								        exporting: {
								        	enabled:false//关闭设置按钮
								        },
								        tooltip: {
								        	pointFormat: true,
								        },
								        plotOptions: {
								        	pie: {
								        		allowPointSelect: true,
								        		cursor: 'pointer',
								        		dataLabels: {
								        			enabled: false,
								        			color: '#323232',
								        			connectorColor: 'none',
								        			 format: '<b>{point.name}</b>: {point.percentage:.1f} %',
								        			style: {fontSize:"18px",}
								        		}
								        	}
								        },
								        series: [{
								        	type: 'pie',
								        	name: '',
								        	data: [
								        	       ['',   green],//发展卡数量，绿色块
								        	       ['',   blue],//年级总数，蓝色块
								        	       
								        	       ]
								        }]
							});
							loader.close();
						}else{
							$('#container').html("");
							loader.close();
						}
					},
					error:function(){  
					    alert('fail：获取全校参数错误');  //参数用作填充饼图
					    loader.close();
					}  
				});
			},
			error:function(){  
			    alert('fail：获取年级数据错误');  
			    loader.close();
			}  
		});
	}else if($("#select_div_month").is(":hidden")){
//			beginDate=$(this).data("prev");//获得周次开始的时间
//			endDate=$(this).data("next");//获得周次结束的时间   
//		alert("beginDate_week:"+beginDate);alert("endDate_week:"+endDate);
			$.ajax({  
				type : "GET",  
				url : urlSearchForGrade,
				dataType : "jsonp",//数据类型为jsonp  
				jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
				data:{
					termCode  : termCode,
					gradeId : gradeId,
					beginDate : beginDate,
					endDate : endDate
				},
				success : function(data){   //console.log("年级"+JSON.stringify(data));
					var data = data.data;
					$(".project").html("班级");
					$(".tst").html("");
					if(data.length>0){
						$(".specific").show();
						for(var i=0;i<data.length;i++){
							napepercent = data[i].ratio;
							percent = parseFloat((napepercent * 100).toFixed(1)) + "%";
							count += data[i].count;
							$(".normaltablelist").append("<tr><td class='project'><p class='name her'>"+data[i].objectName+"</p></td><td>"+
									"<span class='green'>"+data[i].count+"</span>"+
									"<div class='schedule'>"+
									"<p class='green' style='width:"+percent+"';></p>"+
									"</div>"+
									"</td>"+"<td class='range'>"+percent+"</td></tr>");
						}
						if(count == "0"){
							$("#container").css("display","none");
						}
						$(".tst").html("");
					}else{
						$(".specific").hide();
//						var message = $(".message").attr("data-message");
						if(message=="class"){
							$(".tst").html("<div style='background: #fff;width:100%;height:700px;position: absolute;top: 240px;left: 0;right: 0;'><img src='/res/css/dygl/images/happy.png' style='margin: 20px auto 50px;display: block;'><p style='text-align: center;font-size:24px;color:#000'>孩子们表现很好，课堂上没有不良行为记录~</p></div>");
							return false;
						}else if(message=="incentive" || message=="normal"){
							$(".tst").html("<div style='background: #fff;width:100%;height:700px;position: absolute;top: 240px;left: 0;right: 0;'><img src='/res/css/dygl/images/unhappy.png' style='margin: 20px auto 50px;display: block;'><p style='text-align: center;font-size:24px;color:#000'>孩子们当前没有受到表扬，请继续努力~</p></div>");
							return false;
						}
					}
					$.ajax({  
						type : "GET",  
						url : urlSearchForSchool,
						dataType : "jsonp",//数据类型为jsonp  
						jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
						data:{
							termCode  : termCode,
							schoolId : schoolId,
							beginDate : beginDate,
							endDate : endDate
						},
						success : function(data){   //console.log("全校"+JSON.stringify(data));
							var data = data.data;
							for(var i=0;i<data.length;i++){
								if(gradeId == data[i].objectId){
									schoolPercent = data[i].ratio;
								}
							}
							$(".specific").find(".count").html("");
							$(".specific").find(".count").html("总数量"+count);
							if(count != 0){
								var green = schoolPercent * 100;
								var blue = 100 - green;
								var dper = (schoolPercent * 100).toFixed(1)+"%";
								if(message=="incentive" || message=="normal"){
									$(".grade_class").html("<p>全校占比 "+dper+"</p>");//判断课堂优化不显示///////////////////////////////////////////////////////
								}
								$('#container').highcharts({
									chart: {
										plotBackgroundColor: null,
										plotBorderWidth: null,
										plotShadow: false,
									},
									colors:[
									        '#3ac982',
									        '#2299ee',
									        ],
									        title: {
									        	text: '',
									        },
									        credits:{
									        	enabled:false // 禁用版权信息
									        },
									        exporting: {
									        	enabled:false//关闭设置按钮
									        },
									        tooltip: {
									        	pointFormat: true,
									        },
									        plotOptions: {
									        	pie: {
									        		allowPointSelect: true,
									        		cursor: 'pointer',
									        		dataLabels: {
									        			enabled: false,
									        			color: '#323232',
									        			connectorColor: 'none',
									        			 format: '<b>{point.name}</b>: {point.percentage:.1f} %',
									        			style: {fontSize:"18px",}
									        		}
									        	}
									        },
									        series: [{
									        	type: 'pie',
									        	name: '',
									        	data: [
									        	       ['',   green],//发展卡数量，绿色块
									        	       ['',   blue],//年级总数，蓝色块
									        	       
									        	       ]
									        }]
								});
								loader.close();
							}else{
								$('#container').html("");
								loader.close();
							}
						},
						error:function(){  
							alert('fail：获取全校参数错误');  //参数用作填充饼图
							loader.close();
						}  
					});
				},
				error:function(){  
					alert('fail：获取年级数据错误');  
					loader.close();
				}  
			});
	}
}
function searchForSchool(newyear,newmonth,urlSearchForSchool){//跨域获取数据
	termCode = $(".year").attr("data-termCode");
	var role = $(".controllerData").attr("data-role");
	var message = $(".message").attr("data-message");
	if(role == "CLASS_MASTER" || role == "SUBJECT_TEACHER"){
		return false;
	}
	if(termCode == "" || termCode == null || termCode == "0" || termCode == 0){
		return false;
	}
	var role = $(".controllerData").attr("data-role");
	if(role != "CLASS_MASTER" && role != "PARENT"){
	$(".normaltablelist").html("");
	$(".tst").html("");
	$(".details-table").find(".dataPercent").html("全校占比");
	var percent =0;//每个年级占比
	var napepercent = 0;//年级占比（取值用的变量）
	var schoolPercent=0;//年级在全校的占比
	var count =0;//总数
	var ary = new Array();//所有年级的占比集合（整数）
	loader.show();
	if($("#select_div_week").is(":hidden")){
		var month = newmonth;
		var year = newyear;
		beginDate = year+"-"+selectmonth+"-1";
		var lastdate  = new Date(year,selectmonth,0);   
	    endDate = year + '-' + selectmonth + '-' + lastdate.getDate();//alert("beginDate_month:"+beginDate);alert("endDate_month:"+endDate);
	    
	    $.ajax({  
			type : "GET",  
			url : urlSearchForSchool,
			dataType : "jsonp",//数据类型为jsonp  
			jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
			data:{
				termCode  : termCode,
				schoolId : schoolId,
				beginDate : beginDate,
				endDate : endDate
			},
			success : function(data){   //console.log("全校"+JSON.stringify(data));
				$(".normaltablelist").html("");
				var data = data.data;
				$(".project").html("年级");
				if(data.length>0){
					for(var i=0;i<data.length;i++){
						napepercent = data[i].ratio;
						ary.push(parseFloat((napepercent * 100).toFixed(1)));//把每个占比放入集合（整数）
						percent = parseFloat((napepercent * 100).toFixed(1)) + "%";
						count += data[i].count;
						schoolPercent += napepercent;
						$(".normaltablelist").append("<tr><td class='project'><p class='name her'>"+data[i].objectName+"</p></td><td>"+
								"<span class='green'>"+data[i].count+"</span>"+
								"<div class='schedule'>"+
								"<p class='green' style='width:"+percent+"';></p>"+
								"</div>"+
								"</td>"+
								"<td class='range'>"+percent+"</td></tr>");
					}
					$("#container").css("display","none");
					$(".specific").find(".count").html("");
					$(".specific").find(".count").html("总数量"+count);
					$(".grade_class").html("");
//					if(schoolPercent>0){
//						$(".grade_class").html("<p>全校占比 "+"100%"+"</p>");
//					}else{
//						$(".grade_class").html("<p>全校占比 "+"0%"+"</p>");
//					}
					loader.close();
				}else{
					if(message=="class"){
						$(".tst").html("<div style='background: #fff;width:100%;height:700px;position: absolute;top: 240px;left: 0;right: 0;'><img src='/res/css/dygl/images/happy.png' style='margin: 20px auto 50px;display: block;'><p style='text-align: center;font-size:24px;color:#000'>孩子们表现很好，课堂上没有不良行为记录~</p></div>");
					}else if(message=="incentive" || message=="normal"){
						$(".tst").html("<div style='background: #fff;width:100%;height:700px;position: absolute;top: 240px;left: 0;right: 0;'><img src='/res/css/dygl/images/unhappy.png' style='margin: 20px auto 50px;display: block;'><p style='text-align: center;font-size:24px;color:#000'>孩子们当前没有受到表扬，请继续努力~</p></div>");
					}
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
//			endDate=$(this).data("next");//获得周次结束的时间   alert("beginDate_week:"+beginDate);alert("endDate_week:"+endDate);
			$.ajax({  
				type : "GET",  
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
					$(".normaltablelist").html("");
					var data = data.data;
					$(".project").html("年级");
					if(data.length>0){
						for(var i=0;i<data.length;i++){
							napepercent = data[i].ratio;
							ary.push(parseFloat((napepercent * 100).toFixed(1)));//把每个占比放入集合（整数）
							percent = parseFloat((napepercent * 100).toFixed(1)) + "%";
							count += data[i].count;
							schoolPercent += napepercent;
							$(".normaltablelist").append("<tr><td class='project'><p class='name her'>"+data[i].objectName+"</p></td><td>"+
									"<span class='green'>"+data[i].count+"</span>"+
									"<div class='schedule'>"+
									"<p class='green' style='width:"+percent+"';></p>"+
									"</div>"+
									"</td>"+
									"<td class='range'>"+percent+"</td></tr>");
						}
						loader.close();
					}else{
						if(message=="class"){
							$(".tst").html("<div style='background: #fff;width:100%;height:700px;position: absolute;top: 240px;left: 0;right: 0;'><img src='/res/css/dygl/images/happy.png' style='margin: 20px auto 50px;display: block;'><p style='text-align: center;font-size:24px;color:#000'>孩子们表现很好，课堂上没有不良行为记录~</p></div>");
						}else if(message=="incentive" || message=="normal"){
							$(".tst").html("<div style='background: #fff;width:100%;height:700px;position: absolute;top: 240px;left: 0;right: 0;'><img src='/res/css/dygl/images/unhappy.png' style='margin: 20px auto 50px;display: block;'><p style='text-align: center;font-size:24px;color:#000'>孩子们当前没有受到表扬，请继续努力~</p></div>");
						}
						loader.close();
					}
					$("#container").css("display","none");
					$(".specific").find(".count").html("");
					$(".specific").find(".count").html("总数量"+count);
//					if(schoolPercent>0){
//						$(".grade_class").html("<p>全校占比 "+"100%"+"</p>");
//					}else{
//						$(".grade_class").html("<p>全校占比 "+"0%"+"</p>");
//					}
				},
				error:function(){  
				    alert('fail：获取全校数据错误');  
				    loader.close();
				}  
			});
		}
	}
}

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

//--------------------------------------------------单个学生查询------------------------------------------------//
function oneStudent(newyear,newmonth,urlForStudent){//单个学生跨域获取数据
	loader.show();
	termCode = $(".year").attr("data-termCode");//	var teamId = $(".datateamId").attr("data-teamId");
	if($("#select_div_week").is(":hidden")){
		var year = newyear;
		beginDate = year+"-"+selectmonth+"-1";
		var lastdate  = new Date(year,selectmonth,0);   
	    endDate = year + '-' + selectmonth + '-' + lastdate.getDate();//alert("beginDate_month:"+beginDate);alert("endDate_month:"+endDate);
	    $.ajax({  
			type : "GET",
			url : urlForStudent,
			dataType : "jsonp",//数据类型为jsonp  
			jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
			data:{
				termCode  : termCode,
				teamId : teamId,
				studentId : studentId,
				beginDate : beginDate,
				endDate : endDate
			},
			success : function(data){   //console.log(JSON.stringify(data));
			$("#details-table2").html("");
			var message = $(".message").attr("data-message");
				var data = data.data;
				var percent =0;
				var napepercent = 0;
				$("#details-table2").append("<table><thead><tr>"+
			             "<th style='width: 180px;' class='project'>评价卡</th>"+
			             "<th>数量</th>"+
			             "<th style='width: 145px;'>全班级占比</th></tr></thead>"+
			        "<tbody class='details-table2'></tbody></table>");
				if(message=="incentive" || message=="normal"){
					$(".project").html("评价卡")
				}else if(message == "class"){
					$(".project").html("不良行为")
				}
				if(data.length>0){
					for(var i=0;i<data.length;i++){
						napepercent = data[i].ratio;
						percent = parseFloat((napepercent * 100).toFixed(1)) + "%";
						$(".details-table2").append("<tr><td class='project'><p class='name her'>"+data[i].objectName+"</p></td><td>"+
														"<span class='green'>"+data[i].count+"</span>"+
														  	"<div class='schedule'>"+
														  		"<p class='green' style='width:"+percent+"';></p>"+
													"</div></td><td class='range'>"+percent+"</td></tr>");
					}
				}else{
					$(".details-table2").append("<tr><td class='project'><p class='name her'>暂无数据</p></td><td>"+
													"<span class='green'>0</span>"+
													  	"<div class='schedule'>"+
													  		"<p class='green' style='width:0%';></p>"+
												"</div></td><td class='range'>0%</td></tr>");
				}loader.close();
			},
			error:function(){  
			    alert('fail：获取学生数据错误');  
			    loader.close();
			}  
		});
	}else if($("#select_div_month").is(":hidden")){
		$("#details-table2").html("");
//			beginDate=$(this).data("prev");//获得周次开始的时间
//			endDate=$(this).data("next");//获得周次结束的时间   alert("beginDate_week:"+beginDate);alert("endDate_week:"+endDate);
			$.ajax({  
				type : "GET",
				url : urlForStudent,
				dataType : "jsonp",//数据类型为jsonp  
				jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
				data:{
					termCode  : termCode,
					teamId : teamId,
					studentId : studentId,
					beginDate : beginDate,
					endDate : endDate
				},
				success : function(data){   //console.log(JSON.stringify(data));
				$("#details-table2").html("");
				var message = $(".message").attr("data-message");
					var data = data.data;
					var percent =0;
					var napepercent = 0;
					$("#details-table2").append("<table><thead><tr>"+
				             "<th style='width: 180px;' class='project'>评价卡</th>"+
				             "<th>数量</th>"+
				             "<th style='width: 145px;'>全班级占比</th></tr></thead>"+
				        "<tbody class='details-table2'></tbody></table>");
					if(message=="incentive" || message=="normal"){
						$(".project").html("评价卡")
					}else if(message == "class"){
						$(".project").html("不良行为")
					}
					if(data.length>0){
						for(var i=0;i<data.length;i++){
							napepercent = data[i].ratio;
							percent = parseFloat((napepercent * 100).toFixed(1)) + "%";
							$(".details-table2").append("<tr><td class='project'><p class='name her'>"+data[i].objectName+"</p></td><td>"+
															"<span class='green'>"+data[i].count+"</span>"+
															  	"<div class='schedule'>"+
															  		"<p class='green' style='width:"+percent+"';></p>"+
														"</div></td><td class='range'>"+percent+"</td></tr>");
						}
					}else{
						$(".details-table2").append("<tr><td class='project'><p class='name her'>暂无数据</p></td><td>"+
														"<span class='green'>0</span>"+
														  	"<div class='schedule'>"+
														  		"<p class='green' style='width:0%';></p>"+
													"</div></td><td class='range'>0%</td></tr>");
					}loader.close();
				},
				error:function(){  
				    alert('fail：获取学生数据错误');  
				    loader.close();
				}  
			});
	}
	
}
