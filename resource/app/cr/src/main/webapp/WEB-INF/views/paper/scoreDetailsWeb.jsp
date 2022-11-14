<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>试卷预览</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/scoreDetails/css/pp2.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/scoreDetails/css/button.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/scoreDetails/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/scoreDetails/js/layer/layer.js" ></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/scoreDetails/js/layer/theme/default/layer.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
    <style type="text/css">
       .timu input[type="text"]{
            border: 0 none;
            border-bottom: 1px solid #999;
            background-color: #fafafa;
        }
       
       	.sj_view .paper_title .p1 {
		    padding-top: 30px;
		}
        .go_top {
           background: url(${pageContext.request.contextPath}/res/scoreDetails/css/images/top.png) no-repeat;
            width: 72px;
            height: 72px;
            position: fixed;
            right: 44px;
            bottom: 10px;
            z-index: 999;
            border-radius: 50px;
		}
		.go_top:hover {
	       background: url(${pageContext.request.contextPath}/res/scoreDetails/css/images/top_deep.png) no-repeat;
		}
       .go_top1 {
           width: 111px;
           height: 30px;
           line-height: 30px;
           position: fixed;
           right: 0;
           bottom:0;
           border-radius:0;
           padding-left:89px;
           font-size:12px;
           color: #999999;
           box-shadow: 0px 1px 6px 0px
           rgba(0, 0, 0, 0.1);
           background: url(${pageContext.request.contextPath}/res/scoreDetails/css/images/g_top.png) no-repeat 71px center #fff;
       }
       .go_top1:hover {
           background-color:#efefef;
           color:#888;
       }
    </style>
</head>
<body>

<!-- <div class="top"> -->
<!--     <span>预览模式</span> -->
<!--     <a class="btn-lightGray">返回</a> -->
<!-- </div> -->
<div class="question_num">
</div>
<div class="sj_view">
    <div class="paper_title">
        <div class="p1"><span class="title"></span>(满分<span class="manfen"></span>分)</div>
        <div class="p2">
            <ul>
            	<li class="xs"><b></b>学生：<span></span></li>
                <li class="zf"><b></b>班级：<span>高一（1）班</span></li>
                <li class="bh"><b></b>得分：<span>95</span></li>
                <li class="tm"><b></b>用时：<span>01:05:20</span></li>
            </ul>
        </div>
    </div>

</div>
<a class="go_top" href="javascript:void(0)" onclick="goTop()" style="display: none"></a>
<a class="go_top1" href="javascript:void(0)" onclick="goTop()">回到顶部</a>
<a href="javascript:void(0)" class="close_right"></a>
<script>
/**
 * 回到页面顶部
 * @param acceleration 加速度
 * @param time 时间间隔 (毫秒)
 **/
function goTop(acceleration, time) {
  
  acceleration = acceleration || 0.1;
  time = time || 16;
 
  var x1 = 0;
  var y1 = 0;
  var x2 = 0;
  var y2 = 0;
  var x3 = 0;
  var y3 = 0;
 
  if (document.documentElement) {
    x1 = document.documentElement.scrollLeft || 0;
    y1 = document.documentElement.scrollTop || 0;
  }
  if (document.body) {
    x2 = document.body.scrollLeft || 0;
    y2 = document.body.scrollTop || 0;
  }
  var x3 = window.scrollX || 0;
  var y3 = window.scrollY || 0; 
  
   
   
  // 滚动条到页面顶部的水平距离
  var x = Math.max(x1, Math.max(x2, x3));
  // 滚动条到页面顶部的垂直距离
  var y = Math.max(y1, Math.max(y2, y3));
 
  // 滚动距离 = 目前距离 / 速度, 因为距离原来越小, 速度是大于 1 的数, 所以滚动距离会越来越小
  var speed = 1 + acceleration;
  window.scrollTo(Math.floor(x / speed), Math.floor(y / speed));
  
 
  // 如果距离不为零, 继续调用迭代本函数
  if(x!= 0 || y != 0) {
    var invokeFunction = "goTop(" + acceleration + ", " + time + ")";
    window.setTimeout(invokeFunction, time);
    
  }
}


function secondToDate(s) {
	 var hour = Math.floor(s/3600);
    var min = Math.floor(s/60) % 60;
    var sec = s % 60;
    if(hour < 10) {
        t = '0'+ hour + ":";
    }else{
        t = hour + ":";
    }
    if(min < 10){t += "0";}
    t += min + ":";
    if(sec < 10){t += "0";}
    t += sec.toFixed(0);
   return t ;
}
	
    $(function () {
        var h = document.documentElement.clientHeight;
        $('.question_num').height(h-30);
        $(window).resize(function(){
            var h = document.documentElement.clientHeight;
            $('.question_num').height(h-30);
        });
        // 点击题号跳转变蓝
        $(".question_num").on('click','.xh ul li a',function(){
            $(".question_num .xh ul li").removeClass("on");
            $(this).parent().addClass("on");
            $("html, body").animate({
                scrollTop: $($(this).attr("href")).offset().top + "px"
            }, {
                duration: 500,
                easing: "swing"
            });
            return false;
        });
        $('body').on('click','.close_right',function(){
            $(this).removeClass('close_right').addClass('open_right')
            $('.question_num').hide();
            $('.go_top').show();
            $('.go_top1').hide();
        });
        $('body').on('click','.open_right',function(){
            $(this).removeClass('open_right').addClass('close_right')
            $('.question_num').show();
            $('.go_top1').show();
            $('.go_top').hide();
        });
        $("body,.question_num").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
       var data=eval(json_sj); 
       		//试卷题目
            $(".sj_view .paper_title .p1 span.title").text(data.paper.title);
            //满分
            $(".sj_view .paper_title .p1 span.manfen").text(data.paper.score);
            //年级
            $(".sj_view .paper_title .p2 li.zf span").text(data.studentInfo.teamName);
            //用时
            $(".sj_view .paper_title .p2 li.tm span").text(secondToDate(data.studentInfo.sumTime));
            //得分
            $(".sj_view .paper_title .p2 li.bh span").text(data.studentInfo.studentScore);
            //学生
            $(".sj_view .paper_title .p2 li.xs span").text(data.studentInfo.studentName);
            
            var group=data.body.group;
            var question_num=0;
            for(var i=0;i<group.length;i++){
            	for(var j=0;j<group[i].questions.length;j++){
            		if(group[i].questions[j].questions.length==0){
            			question_num++;
            		}else{
            			for(var k=0;k<group[i].questions[j].questions.length;k++){
            				question_num++;
            			}
            		}
            	}
            }
            $(group).each(function(index){
                $('<div class="xh"><p class="tmTitle"><label>'+group[index].pos+'</label>、<span class="bb">'+group[index].name+'</span></p><ul></ul></div>').appendTo($('.question_num'));
                var questions=group[index].questions;
                var li="";
                var isSubjective="";
                var pos=0;
                $(questions).each(function(j){
                    var answer_class=''
                    if(questions[j].questions==null||questions[j].questions==''){
	                    if(questions[j].isCorrect==0){
	                        answer_class='answer_wrong'
	                    }else if(questions[j].isCorrect==1){
	                        answer_class='answer_right'
	                    }
                        $('<li class="'+answer_class+'"><a href="#'+questions[j].id+'"  data-uuid="'+questions[j].id+'">'+questions[j].num+'</a></li>').appendTo($('.question_num .xh:last-child ul'));
                    }else{
                        $(questions[j].questions).each(function(k){
	                    if(questions[j].questions[k].isCorrect==0){
	                        answer_class='answer_wrong'
	                    }else {
	                        answer_class='answer_right'
	                    }
                            $('<li class="'+answer_class+'"><a href="#'+questions[j].questions[k].id+'"  data-uuid="'+questions[j].questions[k].id+'">'+questions[j].questions[k].num+'</a></li>').appendTo($('.question_num .xh:last-child ul'));
                        })
                    }
                	if(questions[j].userAnwer==""){
                		questions[j].userAnwer="未作答";
                	}
                    var xuanxiang="",difficulty="",cognition="",zimu="A",zhishidian="";
                    //单选题，不定项
                    if(questions[j].type=='radio'||questions[j].type=='multichoise'){
                        $(questions[j].answer).each(function(k){
                            if(k==0){
                                zimu='A'
                            }else if(k==1){
                                zimu='B'
                            }else if(k==2){
                                zimu='C'
                            }else if(k==3){
                                zimu='D'
                            }else if(k==4){
                                zimu='E'
                            }else if(k==5){
                                zimu='F'
                            }else if(k==6){
                                zimu='G'
                            }else if(k==7){
                                zimu='H'
                            }
                            xuanxiang=xuanxiang+"<li><span class='xuanxiang'>"+zimu+"</span>"+questions[j].answer[k]+"</li>"
                        })
                       
                       
                        var daan=questions[j].correctAnswer;
                        var jiexi=questions[j].explanation;
                        if(questions[j].isCorrect== 1){
                        	//正确 客观题
                        	var you_defen = "<p class='correct'>回答正确，学生得分："+ questions[j].studentScore +"</p>";
                        	var you_daan = "<p class='color9'>学生答案：<span class='correct'>"+ questions[j].userAnwer +"</span></p>"
                        }else{
                        	var you_defen = "<p class='error'>回答错误，学生得分："+ questions[j].studentScore +"</p>";
                        	var you_daan = "<p class='color9'>学生答案：<span class='error'>"+ questions[j].userAnwer +"</span></p>"
                        }
                        var jiexi_1="<div class='jiexi' ><div class='j3'>"+ you_defen +"</div>"
                        +"<div class='j1'>"+ you_daan +"<p class='color9'>正确答案：<span class='color6'>"+ daan +"</span></p></div>"
                        +"</div>"
                        li=li+"<li class='st layer-photos-demo_"+questions[j].pos+"' id="+questions[j].id+"><div class='timu'> <span class='timuname'>"+ (questions[j].num) +"、"+questions[j].content+"</span><div class='timu-choose'><ul>"+xuanxiang+"</ul></div></div>"+jiexi_1+"</li>"
                    }
                    //多选题
                    if(questions[j].type=='checkbox'){
                        $(questions[j].answer).each(function(k){
                            if(k==0){
                                zimu='A'
                            }else if(k==1){
                                zimu='B'
                            }else if(k==2){
                                zimu='C'
                            }else if(k==3){
                                zimu='D'
                            }else if(k==4){
                                zimu='E'
                            }else if(k==5){
                                zimu='F'
                            }else if(k==6){
                                zimu='G'
                            }else if(k==7){
                                zimu='H'
                            }
                            xuanxiang=xuanxiang+"<li><span class='xuanxiang' style='border-radius: 6px;'>"+zimu+"</span>"+questions[j].answer[k]+"</li>"
                        })
                        
                        var daan=questions[j].correctAnswer;
                        if(questions[j].isCorrect== 1){
                        	//正确 客观题
                        	var you_defen = "<p class='correct'>回答正确，学生得分："+ questions[j].studentScore +"</p>";
                        	var you_daan = "<p class='color9'>学生答案：<span class='correct'>"+ questions[j].userAnwer +"</span></p>"
                        }else{
                        	var you_defen = "<p class='error'>回答错误，学生得分："+ questions[j].studentScore +"</p>";
                        	var you_daan = "<p class='color9'>学生答案：<span class='error'>"+ questions[j].userAnwer +"</span></p>"
                        }
                        var jiexi_1="<div class='jiexi'><div class='j3'>"+ you_defen +"</div>"
                        +"<div class='j1'>"+ you_daan +"<p class='color9'>正确答案：<span class='color6'>"+ daan +"</span></p></div>"
                        +"</div>"
                        li=li+"<li class='st layer-photos-demo_"+questions[j].pos+"' id="+questions[j].id+"><div class='timu'> <span class='timuname'>"+ (questions[j].num) +"、"+questions[j].content+"</span><div class='timu-choose'><ul>"+xuanxiang+"</ul></div></div>"+jiexi_1+"</li>"
                    }
                    //判断题
                    if(questions[j].type=='trueOrFalse'){
                        $(questions[j].answer).each(function(k){
                            xuanxiang=xuanxiang+"<li><span class='xuanxiang'></span>"+questions[j].answer[k]+"</li>"
                        })
                        if(questions[j].isCorrect== 1){
                        	//正确 客观题
                        	var you_defen = "<p class='correct'>回答正确，学生得分："+ questions[j].studentScore +"</p>";
                        	var you_daan = "<p class='color9'>学生答案：<span class='correct'>"+ questions[j].userAnwer +"</span></p>"
                        }else{
                        	var you_defen = "<p class='error'>回答错误，学生得分："+ questions[j].studentScore +"</p>";
                        	var you_daan = "<p class='color9'>学生答案：<span class='error'>"+ questions[j].userAnwer +"</span></p>"
                        }
                        var daan=questions[j].correctAnswer;
                       var jiexi_1="<div class='jiexi'><div class='j3'>"+ you_defen  +"</div>"
                         +"<div class='j1'>"+ you_daan +"<p class='color9'>正确答案：<span class='color6'>"+ daan +"</span></p></div></div>"
                        li=li+"<li class='st layer-photos-demo_"+questions[j].pos+"' id="+questions[j].id+"><div class='timu'> <span class='timuname'>"+ (questions[j].num) +"、"+questions[j].content+"</span><div class='timu-choose'><ul>"+xuanxiang+"</ul></div></div>"+jiexi_1+"</li>"
                    }
                    //填空题
                    if(questions[j].type=='blank'){
                    	var daan ="",you_daan="";
                      
                        var you_defen = "<p>学生得分：<span class='answer'>"+ questions[j].studentScore +"</span></p>";
                        //主观题还是填空题
                        if(questions[j].isSubjective==1){ //客观题
                        	 //用户答案
		                    if(questions[j].isCorrect== 1){
		                    	//主观题
		                		$(questions[j].userAnwer).each(function(i){
		                			if(questions[j].userAnwer[i]==""){
		                					you_daan= you_daan +"<p class='error'>未作答</p>";
		                				}else{
		                					you_daan =you_daan + "<p class='correct'>"+ questions[j].userAnwer[i] +"</p>"
		                				}
		                		})
		                    	you_daan =  "<p style='margin-bottom: 0;'>学生答案：</p>"+ you_daan ;
		                	}else{
		                		if(questions[j].userAnwer=="未作答"){
		                			you_daan= "<span class='error'>未作答</span>";
		                		}else{
		                			$(questions[j].userAnwer).each(function(i){
		                				if(questions[j].userAnwer[i]==""){
		                					you_daan= you_daan +"<p class='error'>未作答</p>";
		                				}else{
		                					you_daan =you_daan + "<p class='error'>"+ questions[j].userAnwer[i] +"</p>"
		                				}
		                				
		                    		})
		                		}
		                    	
		                    	you_daan =  "<p style='margin-bottom: 0;'>学生答案：</p>"+ you_daan ;
		                	}
                        	
                        }else{  //主观填空
                        	if(questions[j].imgList==""){
                        		you_daan =  "<p style='margin-bottom: 0;'>学生答案：</p><p class='error'>无作答</p>";
                        	}else{
                        		//用户答案
		                        $(questions[j].imgList).each(function(i){
	                    			you_daan = you_daan+ "<span> <img src="+questions[j].imgList[i] +"></span>";
	                    		})
	                    		you_daan =  "<p style='margin-bottom: 0;'>学生答案：</p><div class='lpd'>"+you_daan+"</div>";
                        	}
                        	
                        }
                        $(questions[j].correctAnswer).each(function(i){
                        	daan = daan + "<p>" + questions[j].correctAnswer[i] +"</p>"
                        })
                        var jiexi=questions[j].explanation
                        var jiexi_1="<div class='jiexi'><div class='j3'>"+ you_defen +"</div>"
                        +"<div class='j1'>"+ you_daan +"<span class='jx_wz'>正确答案</span><div class='color1d9 jx_nr' ><div><div style='color: #666666;'>"+ daan +"</div></div></div></div>"
                        +"</div>"
                        li=li+"<li class='st layer-photos-demo_"+questions[j].pos+"' id="+questions[j].id+"><div class='timu'> <span  class='timuname'style='margin-bottom: 0;'>"+ (questions[j].num) +"、"+questions[j].content+"</span></div>"+jiexi_1+"</li>"
                    }
                    //简答题
                    if(questions[j].type=='word'){
                    	var daan ="",you_daan="";
                       
                        //用户得分
                        var you_defen = "<p>学生得分：<span class='answer'>"+ questions[j].studentScore +"</span></p>";
                        //用户答案
                        if(questions[j].isCorrect== 1){
	                    	//主观题
	                    	if(questions[j].imgList==''){ //图片
	                    		$(questions[j].userAnwer).each(function(i){
	                    		 	you_daan = you_daan + "<p class='correct'>"+ questions[j].userAnwer[i] +"</p>"
		                    	})
		                    	you_daan =  "<p class='color9'>学生答案：</p>"+ you_daan ;
	                    	}else{
	                    		$(questions[j].imgList).each(function(i){
	                    			you_daan = you_daan+ "<span> <img src="+questions[j].imgList[i] +"></span>";
	                    		})
	                    		you_daan =  "<p class='color9'>学生答案：</p><div class='lpd'>"+you_daan+"</div>";
	                    	}
	                    	
                    	}else{
                    		if(questions[j].imgList==''){
                    			if(questions[j].userAnwer =='未作答'){
                    				you_daan= "<span class='error'>未作答</span>";
                    			}else{
                    				$(questions[j].userAnwer).each(function(i){
	                    			 	you_daan = you_daan + "<p class='error'>"+ questions[j].userAnwer[i] +"</p>";
	                    			})
                    			}
	                    		you_daan =  "<p class='color9' style='margin-bottom: 0;'>学生答案：</p>"+ you_daan;
                    		}else{
                    			$(questions[j].imgList).each(function(i){
	                    			you_daan =  you_daan+"<span> <img src="+questions[j].imgList[i] +"></span>";
	                    		})
	                    		you_daan =  "<p class='color9'>学生答案：</p><div class='lpd'>"+you_daan+"</div>";
                    		}
	                    	
                    	}
                        $(questions[j].correctAnswer).each(function(i){
                        	daan = daan + "<p>" + questions[j].correctAnswer[i] +"</p>"
                        })
                        var jiexi=questions[j].explanation;
                        var jiexi_1="<div class='jiexi'><div class='j3'>"+ you_defen +"</div>"
                        +"<div class='j1'>"+ you_daan +"<span class='jx_wz'>正确答案</span><div class='color1d9 jx_nr' ><div><div style='color: #666666;'>"+ daan +"</div><div class='clear'></div></div></div></div>"
                        +"</div>"
                        li=li+"<li class='st layer-photos-demo_"+questions[j].pos+"' id="+questions[j].id+"><div class='timu'> <span class='timuname' style='margin-bottom: 0;'>"+ (questions[j].num) +"、"+questions[j].content+"</span></div>"+jiexi_1+"</li>"
                    	
                    }
                    //复合题
                    if(questions[j].type=='complex'){
                        var xt_li="",xt_li_more="",daan="",jiexi="",jiexi_1="",kaodian="",you_defen="",you_daan="",you_yongshi="",jx="",xsda="",you_daan1="",num="",daan9="";
                        $(questions[j].questions).each(function(l){
                        	daan9="";
                            xt_li=""
                            var xt_question=questions[j].questions;
                            if(xt_question[l].type=='radio'||xt_question[l].type=='checkbox'||xt_question[l].type=='multichoise'){
	                            $(xt_question[l].answer).each(function(m){
		                            if(m==0){
		                                zimu='A'
		                            }else if(m==1){
		                                zimu='B'
		                            }else if(m==2){
		                                zimu='C'
		                            }else if(m==3){
		                                zimu='D'
		                            }else if(m==4){
		                                zimu='E'
		                            }else if(m==5){
		                                zimu='F'
		                            }else if(m==6){
		                                zimu='G'
		                            }else if(m==7){
		                                zimu='H'
		                            }
		                            xt_li=xt_li+"<p><span class='xuanxiang'>"+zimu+"</span>"+xt_question[l].answer[m]+"</p>"
	
	                            })
                            xt_li_more=xt_li_more+"<li id="+xt_question[l].id+"><label style='display:inline-block;float: left;'>"+(xt_question[l].num)+"、</label>"+xt_question[l].content+"<div>"+xt_li+"</div></li>"
                            
                            }else if(xt_question[l].type=='blank'||xt_question[l].type=='word'||xt_question[l].type=='trueOrFalse'){
                                xt_li_more=xt_li_more+"<li id="+xt_question[l].id+"><label style='display:inline-block;float: left;'>"+(xt_question[l].num)+"、</label>"+xt_question[l].content+"</li>"
                            }
                            
						 	//用户答案
						 	if(xt_question[l].type=='radio'||xt_question[l].type=='checkbox'||xt_question[l].type=='multichoise'||xt_question[l].type=='trueOrFalse'){
						 	you_daan="";
	                           	//客观题
	                           	if(xt_question[l].isCorrect== 1){
		                        	you_defen = "<p class='correct'>回答正确，学生得分："+ xt_question[l].studentScore +"</p>";
		                        	 you_daan = "<p class='color9'>学生答案：<span class='correct'>"+ xt_question[l].userAnwer +"</span></p>"
		                        }else{
		                        	if(xt_question[l].userAnwer==""){
		                        		xt_question[l].userAnwer='未作答'
		                        	}
		                        	you_defen = "<p class='error'>回答错误，学生得分："+ xt_question[l].studentScore +"</p>";
		                        	you_daan = "<p class='color9'>学生答案：<span class='error'>"+ xt_question[l].userAnwer +"</span></p>"
		                        }
                            
                            }else if(xt_question[l].type=='blank'){  //填空
                           		 you_daan="";
                            	//主观题
                            	you_defen = "<p>学生得分：<span class='answer'>"+ xt_question[l].studentScore +"</span></p>";
                            	
                            	//主观题还是填空题
		                        if(xt_question[l].isSubjective==1){ //客观题
		                        	you_daan="";
				                    if(xt_question[l].isCorrect== 1){
				                		$(xt_question[l].userAnwer).each(function(i){
				                			if(xt_question[l].userAnwer[i]==""){
				                					you_daan= you_daan +"<p class='error' style='margin-left:35px'>未作答</p>";
				                				}else{
				                					you_daan =you_daan + "<p class='correct' style='margin-left:35px'>"+ xt_question[l].userAnwer[i] +"</p>"
				                				}
				                		})
				                    	you_daan =  "<p style='color:#999999'>学生答案：</p>"+ you_daan ;
				                	}else{
				                		if(xt_question[l].userAnwer=="未作答"){
				                			you_daan= "<span class='error'>未作答</span>";
				                		}else{
				                			$(xt_question[l].userAnwer).each(function(i){
				                				if(xt_question[l].userAnwer[i]==""){
				                					you_daan= you_daan +"<p class='error' style='margin-left:35px'>未作答</p>";
				                				}else{
				                					you_daan =you_daan + "<p class='error' style='margin-left:35px'>"+ xt_question[l].userAnwer[i] +"</p>"
				                				}
				                    		})
				                		}
				                    	you_daan =  "<p style='color:#999999'>学生答案：</p>"+ you_daan ;
				                	}
		                        }else{  //主观填空
		                        	you_daan="";
		                        	if(xt_question[l].imgList==""){
		                        		you_daan =  "<p style='color:#999999'>你的答案：</p><p class='error' style='margin-left:35px'>无作答</p>";
		                        	}else{
				                        $(xt_question[l].imgList).each(function(i){
			                    			you_daan = you_daan+ "<span> <img src="+xt_question[l].imgList[i] +"></span>";
			                    		})
			                    		you_daan =  "<p style='color:#999999'>你的答案：</p><div class='lpd'>"+you_daan+"</div>";
		                        	}
		                        }
                            }else if(xt_question[l].type=='word'){ //简答
                            you_daan="";
                            	you_defen = "<p>学生得分：<span class='answer'>"+ xt_question[l].studentScore +"</span></p>";
                            	if(xt_question[l].isCorrect== 1){
                            	you_daan1="";
			                    	//主观题
			                    	if(xt_question[l].imgList==''){ //图片
			                    		 $(xt_question[l].userAnwer).each(function(i){
			                    		 	you_daan1 = you_daan1 + "<p class='correct'>"+ xt_question[l].userAnwer[i] +"</p>"

				                    	}) 
										you_daan =  "<p class='color9'>学生答案：</p>"+you_daan1 ;

			                    	}else{
			                    		$(xt_question[l].imgList).each(function(i){
			                    			you_daan1 = you_daan1+ "<span> <img src="+xt_question[l].imgList[i] +"></span>";
			                    		})
			                    		you_daan =  " <p class='color9'>学生答案：</p><div class='lpd'>"+you_daan1+"</div>";
			                    	}
			                    	
		                    	}else{
		                    	you_daan1="";
		                    		if(xt_question[l].imgList==''){
		                    			if(xt_question[l].userAnwer==""){
		                        			 you_daan1 = "<p class='error' style='margin-left: 35px;'>未作答</p>";
				                       }else{
				                       		$(xt_question[l].userAnwer).each(function(i){
			                    			 you_daan1 = you_daan1 + "<p class='error'>"+ xt_question[l].userAnwer[i] +"</p>"
			                    			})
				                       }
			                    		you_daan =  "<p class='color9'>学生答案：</p>"+you_daan1 ;
		                    		}else{
		                    			$(xt_question[l].imgList).each(function(i){
			                    			you_daan1 =  you_daan1+"<span> <img src="+xt_question[l].imgList[i] +"></span>";
			                    		})
			                    		you_daan =  "<p class='color9'>学生答案：</p><div class='lpd'>"+you_daan1+"</div>";
		                    		}
			                    	
		                    	}
                            }
						 	jx = jx+"<div><span class='fh_xh'>"+(xt_question[l].num)+"、</span>"+you_defen +"</div>";
						 	xsda = xsda+"<div><span class='fh_xh'>"+(xt_question[l].num)+"、</span>"+you_daan +"</div>";
						 	  $(xt_question[l].correctAnswer).each(function(i){
		                        	daan9 = daan9 + "<p>" + xt_question[l].correctAnswer[i] +"</p>"
		                        }) 
                          // daan=daan+"<div><span class='fh_xh'>"+(xt_question[l].num)+"、</span><div style='margin-left: 45px;'><span class='fh_zd'>"+xt_question[l].correctAnswer+"</span></div></div>";
                           daan=daan+"<div style='margin-bottom: 10px;'><span class='fh_xh'>"+(xt_question[l].num)+"、</span><div style='margin-left: 45px;'><span class='fh_zd'>"+daan9+"</span></div></div>";
                       		num=xt_question[l].pos;
                        })
						//console.log('daan'+xsda)
                         var jiexi_1="<div class='jiexi' ><div class='j3'>"+ jx+"</div>"+"<div class='j3'>"+xsda+"</div>"
                        +"<div class='j1'><span class='jx_wz'>正确答案</span><div class='color1d9 jx_nr' ><div><div style='color: #666666;'>"+daan+"</div></div></div></div>"
                        //+"</div>"
                        
                        li=li+"<li class='st layer-photos-demo_"+num+"' id="+questions[j].id+"><div class='timu'><div class='title'><div class='title-content' >"+questions[j].content+"</div></div><p class='zk_sq' style='display:none;'><b class='zk'  style='display:none;'>展开<i class='i1'></i></b><b class='sq' style='display: none'>收起<i class='i2'></i></b></p><div class='timu-choose'><ul class='complex'>"+xt_li_more+"</ul></div></div>"+jiexi_1+"</li>"
                    }
                    //完型填空
                    if(questions[j].type=='cloze'){
                        var xt_li="",xt_li_more="",daan="",jiexi="",jiexi_1="",kaodian="",you_defen="",you_daan="",zhishidian="",i=0,qq=0;
                         $(questions[j].questions).each(function(q){
                       	    var xt_question=questions[j].questions;
                       		if(xt_question[q].userAnwer==""){
		                           qq++;
		                    }
                       })
                        $(questions[j].questions).each(function(l){

                            xt_li=""
                            var xt_question=questions[j].questions;
                            //选项
                            $(xt_question[l].answer).each(function(m){
                            if(m==0){
                                zimu='A'
                            }else if(m==1){
                                zimu='B'
                            }else if(m==2){
                                zimu='C'
                            }else if(m==3){
                                zimu='D'
                            }else if(m==4){
                                zimu='E'
                            }else if(m==5){
                                zimu='F'
                            }else if(m==6){
                                zimu='G'
                            }else if(m==7){
                                zimu='H'
                            }
                            xt_li=xt_li+"<p><span class='xuanxiang'>"+zimu+"</span>"+xt_question[l].answer[m]+"</p>"
                            })
                            xt_li_more=xt_li_more+"<li id="+xt_question[l].id+"><label style='display:inline-block;float: left;'>"+(xt_question[l].num)+"、</label><div>"+xt_li+"</div></li>";
                           
                            //用户答案
                            var changdu = xt_question.length;
                            //用户答案
                            if(qq==changdu){
                            	//全部为空
                            	you_daan = "<span class='error'>未作答</span>"
                            }else{
                            	//有题目为空
                            	if(xt_question[l].isCorrect== 1){ //正确
                            		if(xt_question[l].userAnwer==''){
                            			you_daan=you_daan + "<span class='error'>空</span>"
                            		}else{
                            			you_daan = you_daan + "<span class='correct'>"+xt_question[l].userAnwer+"</span>";
                            		}
		                    	}else{
		                    		if(xt_question[l].userAnwer==''){
                            			you_daan=you_daan +"<span class='error'>空</span>"
                            		}else{
                            			you_daan = you_daan + "<span class='error'>"+xt_question[l].userAnwer+"</span>";
                            		}
		                    	}
		                    	 if((l+1)%5==0){
		                    	 	you_daan = you_daan+ '、';
		                    	 }
                            }
                            
							 //正确答案
							$(xt_question[l].correctAnswer).each(function(m){
                            	daan=daan+"<span>"+xt_question[l].correctAnswer+"</span>";
		                    	if((l+1)%5==0){
		                    	 	daan = daan+ '、';
		                    	 }
                            })
                            
                        })
                       //用户得分studentScore
                       var you_defen = "<p>学生得分：<span class='answer'>"+ questions[j].studentScore +"</span></p>";
                        var jiexi_1="<div class='jiexi'><div class='j3'>"+ you_defen +"</div>"
                        +"<div class='j1'><p class='color9'>学生答案："+ you_daan +"</p><p class='color9'>正确答案：<span class='color6'>"+daan+"</p></div>"
                        +"</div>" 
                        li=li+"<li class='st layer-photos-demo_"+(questions[j].num)+"'><div class='timu'><div class='title'><div class='title-content' >"+questions[j].content+"</div></div><p class='zk_sq' style='display:none;'><b class='zk'  style='display:none;'>展开<i class='i1'></i></b><b class='sq' style='display: none'>收起<i class='i2'></i></b></p><div class='timu-choose'><ul class='cloze'>"+xt_li_more+"</ul></div></div>"+jiexi_1+"</li>"
                    }
             		
                })
         		 $("<div class='test-paper-right-content' style='padding: 0;'><p style='font-weight: bold;'>"+group[index].pos+"、"+group[index].name+"</p><p>"+group[index].memo+"</p><ul>"+li+"</ul></span>").appendTo($(".sj_view"));
           		
            })
            for(var i=0;i<=question_num;i++){
            	layer.photos({
					  photos: '.layer-photos-demo_'+i
					  ,anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
					});
            }
            
		   
    });
$(window).load(function(){
    $(".timu input[type='text']").prop("disabled","disabled");
    $(' .test-paper-right-content ul li .timu .title-content').each(function(){
        var title_content =  $(this).height();
        if(title_content>175){
            $(this).height('175');
            $(this).parent('.title').next('p.zk_sq').show();
            $(this).parent('.title').next('p.zk_sq').find('.zk').show();
        }
	});
	
	$('.zk').click(function(){
	    $(this).hide();
	    $(this).parent('.zk_sq').prev('.title').find('.title-content').height('auto');
	    $(this).next('.sq').show();
	});
	
	$('.sq').click(function(){
	    $(this).parent('.zk_sq').prev('.title').find('.title-content').height('175');
	    $(this).hide();
	    $(this).prev('.zk').show();
	});
	// 页面加载完，计算每道题距离顶部高度
    var div_top=[];
    $('.test-paper-right-content ul li.st').each(function(){
        if($(this).find('.timu .timu-choose .complex li').length>1||$(this).find('.timu .timu-choose .cloze li').length>1){
            $(this).find('.timu .timu-choose ul li').each(function(j){
                 div_top.push($(this).offset().top-10);
            })
        }else{
            div_top.push($(this).offset().top-50);
        }
    });
    $(window).scroll(function(){
        var this_scrollTop = $(this).scrollTop();
        for(i=(div_top.length-1);i>=0;i--){
            if(this_scrollTop>=div_top[i]){
                $('.question_num .xh ul li').removeClass('on');
                $('.question_num .xh ul li').eq(i).addClass('on');
                return;
            }
        }
    });
})

var json_sj=${json}
		
   

</script>
</body>
</html>