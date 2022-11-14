<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<script src="${pageContext.request.contextPath}/res/qyjx/js/jquery-ui.min.js"></script>
<title>试题篮</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/zujuan.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/button.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
<style>
body{
    background-color: #d5d5d5;
    padding: 9px 20px 17px 11px;
}
#blankAnswer* {
	color:red;
}
.test-paper-right-content ul li .timu input[type='text']{
	border:0 none;
	border-bottom:1px solid #666;
	color: #666666;
	width: 80px;
}
.test-paper-right-content ul{
    margin-bottom: 15px;
}
@media (max-width: 960px){
    body{
        background: #d5d5d5;
    }

}
@media screen and (min-width:960px){
    body{
        background:#d5d5d5;
    }
}
label{
	display: inline-block;
}
.test-paper-top ul{
	margin-top: 13px;
}
.test-paper-title ul {
    width: auto;
    margin: 0 auto;
    text-align: center;
    padding-top: 5px;
}
.test-paper-left{
	overflow:auto;
}
</style>
</head>
<body>
<input type="hidden" value="${assemblyPaper.model }" id="model">
<div class="test-paper-all">
    <div class="test-paper-top">
        <ul class="fr">
            <li><a href="javascript:void(0)" class="btn-lightGray getback" onclick="back();">返回</a></li>
            <li><a href="javascript:void(0)" class="btn-blue" onclick="batchSetting()">批量设置</a></li>
            <li><a href="javascript:void(0)" class="btn-blue" onclick="propertiesSetting(0)">属性设置</a></li>
            <li><a href="javascript:void(0)" class="btn-orange" onclick="saveDraftWithNotice();">保存草稿</a></li>
            <li><a href="javascript:void(0)" class="btn-green mgr20" onclick="checkAndFinish()">完成组卷</a></li>
        </ul>
    </div>
    <div class="test-paper-title">
        <p>
            <span class="title">${assemblyPaper.title }</span>
            <input type="text" maxlength="20" value="${assemblyPaper.title }" id="input-title" style="display: none;padding-top: 0px;padding-bottom: 0px;margin-bottom: 0px;"/>
            <i id="titleEdit"></i>
        </p>
        <ul>
            <li>
                <span class="total-points">总分：</span>
                <span id="totalScore">${assemblyPaper.totalScore}</span>
            </li>
            <li>
                <span class="subject-number">题目数量：</span>
                <span id="questionSize">${assemblyPaper.questionSize }</span>
            </li>
            <li>
                <span class="subject">科目：</span>
                <c:forEach items="${assemblyPaper.subject }" var="entity">
                	<span class="mgr12">${entity.subjectName }(<b id="subject_${entity.subjectCode }"
                	data-name="${entity.subjectName }" data-code="${entity.subjectCode }"
                	data-count="${entity.questionCount }">${entity.score }</b>)</span>
                </c:forEach>
            </li>
        </ul>
    </div>

    <div style="background: #fff;">
        <div class="test-paper-left" style="position: fixed;max-height:845px;">
            <div class="part-one wz">
                <p>大题模式选择：</p>
                <ul>
                	<c:if test="${assemblyPaper.model=='global' }">
                		<li class="choose">全局题号</li>
                    	<li>分组题号</li>
                	</c:if>
                    <c:if test="${assemblyPaper.model=='group' }">
                		<li>全局题号</li>
                    	<li class="choose">分组题号</li>
                	</c:if>
                </ul>
            </div>
            <div class="part-two wz">
                <select id="subjectSelector">
                	<option value="0">科目</option>
                	<c:forEach items="${assemblyPaper.subject }" var="subject">
                    	<option value="${subject.subjectCode }">${subject.subjectName }</option>
                    </c:forEach>
                </select>
            </div>
            <div class="part-three" id="sortable">
            	<c:if test="${assemblyPaper.hasGroup==1 }">
	            	<c:forEach items="${assemblyPaper.groups }" var="group">
		                <div class="xh">
		                    <p class="tmTitle" style="display: block">
		                        <label></label>、<span class="bb" >${group.groupName }</span>
		                        <span class="fr">
		                            <i class="edit"></i>
		                            <i class="delete mgl7" ></i>
		                        </span>
		                    </p>
		                    <input type="text" value="${group.groupName }" style="display: none" class="big-title" maxlength="20"/>
		                    <ul class="sortable">
		                        <c:forEach items="${group.question }" var="question" varStatus="status">
		                       		<c:if test="${fn:length(question.children)>0}">
		                       			<c:forEach items="${question.children }" var="child">
			                       			<li ><a href="#${question.questionId }" data-code="${question.subjectCode }" data-uuid="${question.questionId }" >11</a></li>
			                       		</c:forEach>
		                       		</c:if>
		                       		<c:if test="${fn:length(question.children)==0}">
		                       			<li ><a href="#${question.questionId }" data-code="${question.subjectCode }" data-uuid="${question.questionId }">${status.count }</a></li>
		                       		</c:if>
		                        </c:forEach>
		                    </ul>
		                    
		                </div>
	                </c:forEach>
                </c:if>
                <c:if test="${assemblyPaper.hasGroup==0 }">
	            	<c:forEach items="${assemblyPaper.groups }" var="group">
		                <div class="xh">
		                    <ul class="sortable">
		                    	<c:forEach items="${group.question }" var="question" varStatus="status">
		                       		<c:if test="${fn:length(question.children)>0}">
		                       			<c:forEach items="${question.children }" var="child">
			                       			<li ><a href="#${question.questionId }" data-code="${question.subjectCode }" data-uuid="${question.questionId }" >2</a></li>
			                       		</c:forEach>
		                       		</c:if>
		                       		<c:if test="${fn:length(question.children)==0}">
		                       			<li ><a href="#${question.questionId }" data-code="${question.subjectCode }" data-uuid="${question.questionId }">${status.count }</a></li>
		                       		</c:if>
		                        </c:forEach>
		                    </ul>
		                </div>
	                </c:forEach>
                </c:if>
                <p class="xian"></p>
            </div>
            <div class="part-four wz">
                <a href="javascript:void(0)" class="btn-blue new-dati"><i></i>新建大题</a>
                <a href="javascript:void(0)" class="clear-all">清除所有大题结构</a>
            </div>
        </div>
        <div class="test-paper-right" id="question_list">
        	<jsp:include page="./paper_assembly_list.jsp"></jsp:include>
        </div>
    </div>
</div>
<div><a href="javascript:void(0)" class="gotop" onclick="goTop();" style="display: block;">顶部</a></div>
<div class="finish_paper" style="display:none;">
	<p style="margin-top: 46px;">主人你好~小奇即将帮您在<span style="color:#2ba0f7;">校本库</span>添加一份名为</p>
	<p><span style="color:#2ba0f7;">罗定邦中学高三综合模拟卷1</span>的试卷</p>
	<p>如果需要修改属性，可以到<span style="color:red;">属性设置</span>里边进行修改~</p>
	
</div>
<script type="text/javascript">
var ownerModel = '${type}';
var h = 0;

//定时保存草稿（1min保存一次）
window.setInterval(function(){
	saveDraft();
},1000*60);

//滚动条
$(document).ready(function() {
	/**处理波浪线*/
	$("span").each(function () {
        var style = $(this).attr("style");
        if(style != undefined && style != ""){
            if(style.indexOf("symbol:waveline") != -1){
                 $(this).addClass("waveline");
            }
        }
    })

	$(".test-paper-left").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5","railalign": "right"});
	saveDraft();
});
//滚动高度
function scrollHeight(){
	 var aa = $(window).scrollTop();
     var dd=124-aa;
     $('.test-paper-left').css('height',h-124);
     if(aa<124){
         $('.test-paper-left').css('top',dd);
     }else{
    	 $('.test-paper-left').css('top',0);
    	 $('.test-paper-left').css('height',h);
     }
}
$(window).scroll(scrollHeight);

function leftRightHeight() {
	h=document.documentElement.clientHeight;
    $('.test-paper-right').css("min-height",h);
    scrollHeight();
}
window.onresize=leftRightHeight;

$(function () {
    leftRightHeight();
    global();
   // $(".part-one ul .choose").click();
    leftOrder();
    rightOrder();
    disableBtn();
    noQuestion(); 
    
})


function noQuestion(){
	var length = $(".part-three .xh").find("a").length;
	if(length==0){
		window.parent.stageCode = "";
		$('.test-paper-right-content').hide();
		$('.noQuestion').show();
	}
}


function batchSetting(){
	saveDraft();
	var url = "${pageContext.request.contextPath}/paper/assembly/question/batchSetting";
	$.initWinOnTopFromLeft_qyjx("批量设置", url, '700', '495');
}

//type=0可以直接点击确定  type=1要选完目录才能点击确定
function propertiesSetting(type){
	saveDraft();
	finishType = type;
	if(type==1) {
		layer.closeAll();
	}
	var url = "${pageContext.request.contextPath}/paper/assembly/properties/setting";
	$.initWinOnTopFromLeft_qyjx("试卷属性设置", url, '542', '500');

}

var chinaeseOrder = ["一","二","三","四","五","六","七","八","九","十","十一",
                     "十二","十三","十四","十五","十六","十七","十八","十九","二十"];
                     
var letterCase = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T",
	"U","V","W","X","Y","Z"];
//小题
$('.timu-choose ul li').click(function(){
    $(this).siblings("li").removeClass("choose");
    $(this).addClass("choose");

});

 //移出试题篮
function remove_question(obj) {
	var uuid = $(obj).data("uuid");
	//重新计算总分
	var totalScore = $("#totalScore").text();
	var score = $(obj).data("score");
	totalScore = parseFloat(totalScore-score).toFixed(1);
	$("#totalScore").text(totalScore);
	
	//题目减速一
	var questionSize = $("#questionSize").text();
	var size = $(obj).parents("ul").parents("li").find(".many-timu").children("li").find("li").length;
	if(size==0) {
		$("#questionSize").text(questionSize-1);
	} else {
		$("#questionSize").text(questionSize-size);
	}
	
	//计算单科的分数
	var subjectCode = $(obj).data("subjectcode");
	var codeScore = $("#subject_"+subjectCode).text();
	var codeScore = codeScore-score;
	
	if(codeScore==0) {
		//如果不存在该科目了, 则把科目移除
		$("#subject_"+subjectCode).parent("span").remove();
		$("#subjectSelector option[value='"+subjectCode+"']").remove();
	} else {
		//重新计算科目分数
		$("#subject_"+subjectCode).text(codeScore);
	}
	
	//重新计算当前组的总分
	var groupScore = $(obj).parents(".mgb20").children("p").children("span").text()
	groupScore = parseFloat(groupScore-score).toFixed(1);
	$(obj).parents(".mgb20").children("p").children("span").text(groupScore);
	
	//把左边的题号移除
	$(".part-three ul li").each(function(){
		if($(this).children().data("uuid")==uuid){
			$(this).remove();
		}
	})
	
	//把试题移除
	$(obj).parents(".mgb15").remove();
	//重新排序
	var who_index = $(".part-one ul .choose").index();
    if(who_index==0){
        global();
    }else {
    	group();
    }
	disableBtn();
	noQuestion();
} 

function removeAll() {
	$.ajax({
	    url: "${pageContext.request.contextPath}/paper/assembly/removeAllKey",
	    type: "POST",
	    data: {},
	    async: false,
	    success: function() {
	    }
	});
}

function saveDraft() {
	var data = handleData();
	$.ajax({
	    url: "${pageContext.request.contextPath}/paper/assembly/saveDraft",
	    type: "POST",
	    data: {"data":data},
	    async: false,
	    success: function() {
	    }
	});
}

function saveDraftWithNotice() {
	var data = handleData();
	$.ajax({
	    url: "${pageContext.request.contextPath}/paper/assembly/saveDraft",
	    type: "POST",
	    data: {"data":data},
	    async: false,
	    success: function() {
	    	$.success("保存成功");
	    }
	});
}

function checkAndFinish() {
	$.ajax({
	    url: "${pageContext.request.contextPath}/paper/assembly/properties/check",
	    type: "POST",
	    data: {},
	    async: false,
	    success: function(data) {
	    	if(data=="true") {
	    		var title = $(".test-paper-title span.title").text();
	    		var model = "";
	    		if(ownerModel==1) {
	    			model = "校本库"
	    		} else if(ownerModel==2){
	    			model = "个人库"
	    		}
	    		$(".finish_paper").children("p").eq(0).children("span").text(model);
	    		$(".finish_paper").children("p").eq(1).children("span").text(title);
	    		windowOpen();
	    	} else {
	    		propertiesSetting(1);
	    	}
	    }
	});
}

function windowOpen() {
	layer.open({
		 type: 1,
		 shade: false,
		 area: ['457px', '240px'],
		 title: '提示', 
		 content: $('.finish_paper'),
		 cancel: function(){
		    layer.close();
		 },
		 btn: ['确定','取消','属性设置'],//按钮
		 btn1: function(index, layero){
			 finish();
		 },
		 btn2: function(index, layero){
			 layer.close();
		 },
		 btn3: function(index, layero){
			 layer.close();
			 propertiesSetting(1);
		}
	});
}

function finish() {
	var loader = new loadDialog();
    loader.show();
	saveDraft();
	$.ajax({
	    url: "${pageContext.request.contextPath}/paper/assembly/finish",
	    type: "POST",
	    data: {},
	    async: true,
	    success: function(data) {
	    	loader.close();
	    	window.parent.location.href="${pageContext.request.contextPath}/paper/assembly/basket/index?ownerModel="+ownerModel;
	    }
	});
}

function flushQuestion() {
	$("#question_list").load("${pageContext.request.contextPath}/paper/assembly/question/list")
}

//左边数据的拼装
function handleData() {
	var data = {};
	var index = $(".part-one ul li").index($(".part-one ul .choose"));
	//大题模式
	if(index==0) {
		data["model"]="global";
	} else {
		data["model"]="group";
	}
	
	data["questionSize"] = parseInt($("#questionSize").text());
	data["title"] = $(".title").text();
	data["totalScore"] = parseFloat($("#totalScore").text());
	
	var subjects=new Array();
	$(".mgr12").find("b").each(function(index) {
		var subject = {};
		subject["subjectCode"] = $(this).data("code");
		subject["subjectName"] = $(this).data("name");
		subject["questionCount"] = parseInt($(this).data("count"));
		subject["score"] = parseFloat($(this).text());
		subjects[index] = subject;
	})
	data["subject"] = subjects;
	
	var groups = new Array();
	
	var length = $(".part-three div input[type='text']").length;
	if(length!=0) {
		$(".part-three .xh").each(function(index) {
			var group = {};
			group["strPos"] = chinaeseOrder[index];
			group["groupName"] = $(this).find(".bb").text();
			group["pos"] = index+1;
			
			var tab = 0;
			var questions = new Array();
			var preQuestionId = 0;
			$(this).find("li").children("a").each(function(index) {
				var questionId = $(this).data("uuid");
				var question = {};
				if(index==0) {
					preQuestionId = questionId;
					question["questionId"] = questionId;
					questions[tab] = question;
					tab++;
				}
				if(preQuestionId-questionId!=0) {
					preQuestionId = questionId;
					question["questionId"] = questionId;
					questions[tab] = question;
					tab++;
				}
			})
			group["question"] = questions;
			groups[index] = group;
		})
		data["hasGroup"] = 1; 
	} else {
		var group = {};
		group["strPos"] = "一";
		group["groupName"] = "";
		group["pos"] = 0;
		var tab = 0;
		var preQuestionId = 0;
		
		var questions = new Array();
		
		$(".part-three .xh li a").each(function(index) {
			var question = {};
			var questionId = $(this).data("uuid");
			if(index==0) {
				preQuestionId = questionId;
				question["questionId"] = questionId;
				questions[tab] = question;
				tab++;
			}
			if(preQuestionId-questionId!=0) {
				preQuestionId = questionId;
				question["questionId"] = questionId;
				questions[tab] = question;
				tab++;
			}
		})
		group["question"] = questions;
		groups[0] = group;
		data["hasGroup"] = 0;
	}
	data["groups"] = groups;
	
	return JSON.stringify(data);
}

function back() {
	 saveDraft();
	 window.parent.showBasket();
	 $(".sjk", window.parent.document).click(); 
}
 
//显示答案
$('.show-answer').click(function(){
    var aa = $(this).children().attr('class');
    if(aa.length==0){
        $(this).children().addClass('choose-answer');
        $('.answer-and-analysis').slideDown();
    }else if(aa=='choose-answer'){
        $(this).children().removeClass('choose-answer');
        $('.answer-and-analysis').slideUp();
    }
});

//小题
$('.timu-choose ul li').click(function(){
    $(this).siblings("li").removeClass("choose");
    $(this).addClass("choose");
});

//大题模式选择
$('.part-one ul li').click(function(){
    var length = $('.tmTitle').length;
    if(length>0){
    $(this).siblings("li").removeClass("choose");
    $(this).addClass("choose");
    	
        var who = $(this).index();
        if(who==0){
            global();
        }else {
        	group();
        }
    }
});

function group(obj) {
	 $('.part-three').children('.xh').each(function() {
         $(this).find('li').each(function (index) {
             $(this).children().text(index+1);
         });
     });
	 groupOrder();
}

//左边全局题号排序
function global(){
    var s = 0;
    $('.part-three').children('.xh').each(function () {
        $(this).find('li').each(function () {
            s++;
            $(this).children().text(s);
        });
    });
    globalOrder();
}

//左边的大题题号排序
function leftOrder() {
	$(".part-three .xh").each(function(index){
		$(this).children("p").children("label").text(convertToChinese(index+1));
	})
}

//右边的大题题号排
function rightOrder() {
	$(".mgb20").each(function(index){
		$(this).children("p").children("label").eq(0).text(convertToChinese(index+1));
	})
} 

//全局排序
function globalOrder() {
	var j=1;
	$(".test-paper-right-content ul li.mgb15").each(function(index) {
		if($(this).find(".many-timu").length!=1){
			$(this).find(".timu").children("label").text(j);
			j++;
		}else{
			$(this).find(".timu").children("label").text("");
			$(this).find(".many-timu").find("ul.exz label").each(function(i) {
				$(this).text(j);
				j++;
			});
		}
	})
}

//分组排序
 function groupOrder() {
	$(".mgb20 .dati").each(function(index){
		var j=1;
		$(this).find(".mgb15").each(function(i){
			if($(this).find(".many-timu").length!=1){
				$(this).find(".timu").children("label").text(j);
				j++;
			}else{
				$(this).find(".timu").children("label").text("");
				$(this).find(".many-timu").find("ul.exz label").each(function(i) {
					$(this).text(j);
					j++;
				});
			}
		});
		
	})
} 
    
//试卷标题
$('#titleEdit').click(function(){
    $(this).prev().css('display','none');
    $(this).prev().prev().css('display','none');
    $(this).prev().css('display','block').select();
});

$('#input-title').blur(function(){
    var newTitle = $(this).val();
    if(newTitle==null || newTitle==""){
		layer.confirm("请输入1~20个字符", { btn: '确定'},function () {
			$("#input-title").focus();
			layer.closeAll();
 		},function(){ 
 			$("#input-title").focus();
		  });
		return false;
	}
    $(this).value=newTitle;
    $(this).prev().text(newTitle);
    $(this).css("display","none");
    $(this).prev().css('display','block');
});

//双击试卷标题
$('.title').dblclick(function(){
	$(this).next().next('#titleEdit').click();
});
//一块题目选中
$('body').on('click','.part-three .xh',function(){
    $(this).siblings(".xh").removeClass("choose");
    $(this).addClass("choose");
});

//小标题编辑
$('body').on('click','.edit',function(){
    $(this).parents(".xh").removeClass('choose');
    $(this).parent().parent('p').css('display','none');
    $(this).parent().parent().next().css('display','block').select();
    return false; 
});
$('body').on('dblclick','.tmTitle',function(){
	$(this).find('.edit').click();
});
$('body').on('click','.big-title',function(){
    $(this).parents(".xh").removeClass('choose');
    return false;
});


//小题目input失去焦点
$('body').on('blur','.big-title',function(){
    var newTitle = $(this).val();
    
    if(newTitle==null || newTitle==""){
		layer.confirm("请输入1~20个字符", { btn: '确定'},function () {
			$(".big-title").focus();
			layer.closeAll();
 		},function(){ 
 			$(".big-title").focus();
		  });
		return false;
	}
    
    $(this).value=newTitle;
    $(this).prev().children('.bb').text(newTitle);
    
    //改变右边题目的标题
    var index = $(".part-three div input[type='text']").index(this);
    $(".mgb20").eq(index).children("p").children("label").eq(1).text(newTitle);
    

    $(this).css("display","none");
    $(this).prev().css('display','block');
  	//重新排序左边
    leftOrder();
    //重新排序右边
    rightOrder();
} );

//删除指定大题
$('body').on('click','.delete',function(){
	$(this).parents(".xh").children("ul").find("a").each(function (){
		$("a[data-uuid='"+$(this).data("uuid")+"']").each(function() {
			$(this).click();
		})
	});
	
	//删除右边相应的区域
	var index = $(".part-three .xh").index($(this).parents(".xh"));
	$(".mgb20").eq(index).remove();
	
    $(this).parent().parent().parent().remove();
    $('.part-one ul li').each(function(){
        var aa;
        if($(this).hasClass('choose')){
            aa = $(this).index();
        }
        if(aa==0){
            global();
        }
    });
    
    //重新排序左边
    leftOrder();
    //重新排序右边
    rightOrder();
});

//新增大题
 var qq = 1;
 $('.new-dati').click( function() {
    var aa = $('<div class="xh">\n'
        + '                        <p class="tmTitle" style="display: none">\n'
        + '                            <label></label>、<span class="bb"></span>\n'
        + '                            <span class="fr">\n'
        + '                                <i class="edit" ></i>\n'
        + '                                <i class="delete mgl7" ></i>\n'
        + '                            </span>\n'
        + '                        </p>\n'
        + '                        <input type="text" value="" style="display: block" class="big-title"/>\n'
        + '<ul class="sortable ui-sortable">\n'
        + '</ul>\n' + '                    </div>');
    var bb = $('<p class="tmTitle" style="display: none">\n'
        + '                            <label></label>、<span class="bb"></span>\n'
        + '                            <span class="fr">\n'
        + '                                <i class="edit" ></i>\n'
        + '                                <i class="delete mgl7" ></i>\n'
        + '                            </span>\n'
        + '                        </p>\n'
        + '                        <input type="text" value="" style="display: block" class="big-title"/>');
    var cc = $('<p><label></label>：<label></label></p>');
    var dd = $('<div class="mgb20">\n'
        + '<p><label></label>：<label></label></p>\n'
        + '<ul class="dati"></ul>' + '</div>');
    var tt = $('.part-three').children(':first-child')
        .children().length;

    if (tt == 1) {
        //所有大题结构清除后点击 新增大题
        qq = 1;
        $('.part-three').children(':first-child').children(
            ':last-child').before(bb);

        var newdati = '新建大题' + (qq++);
        $('.big-title').focus().val(newdati).select();

        $('.test-paper-right-content').children('.mgb20')
            .children().before(cc);

        $('.test-paper-right-content .mgb20 p ').children()
            .eq(1).text(newdati);

        $('.big-title').on('input propertychange keydown change',
            function() {
                setTimeout(function() {
                    var bb_val = $('.big-title').val();
                    $('.test-paper-right-content .mgb20 p ').children().eq(1).text(bb_val);
                })
            })

    } else {
        //单纯的新增大题
        var newdati2 = '新建大题' + (qq++);
        $('.part-three').children(':last-child').before(aa);
        $('.part-three').children(':last-child').prev().children('.big-title').focus().val(newdati2).select();

        //右边新增
        $('.test-paper-right-content').children(':last-child').after(dd);

        $('.test-paper-right-content ').children(':last-child').children('p').children().eq(1).text(newdati2);

        $('.part-three').children(':last-child').prev().children('.big-title').on('input propertychange keydown change',function() {

            setTimeout(function() {
                var bb_val = $(	'.part-three').children(':last-child').prev().children('.big-title').val();
                $('.test-paper-right-content ').children(':last-child').children('p').children().eq(1).text(bb_val);
            })
        })
    }
    drag();

})                                  


//清除大题结构
$('.clear-all').click(function(){
	$(".part-one ul li").eq(0).click();
	 var li = "";
     $('.part-three').children('.xh').find("ul").each(function() {
         li += $(this).html();
     });

     $('.part-three').children('.xh').remove();

     var rr = $('<div class="xh"><ul class="sortable">' + li
         + '</ul></div>');
     $('.part-three').children(':last-child').before(rr);
     $(".part-three ul li a").each(function(index) {
         $(this).text(index + 1);
     })
     drag();
     //右边清楚大题结构
     var aa = "";
     $('.test-paper-right-content').children('.mgb20').find(
         'ul.dati').each(function() {
         aa += $(this).html();
     });
     $('.test-paper-right-content').children('.mgb20').remove();
     var qq = $('<div class="mgb20" ><ul class="dati">' + aa
         + '</ul></div>');
     $('.test-paper-right-content').append(qq);
});

function sortAnswer() {
	$(".test-paper-right-content ul li .timu").each(function(index) {
		var length = $(this).children(".timu-choose").children(".many-timu").length;
		if(length==0) {
			$(this).children(".timu-choose").find(".xxqq").each(function(i) {
				if(!$(this).hasClass("unorder")) {
					$(this).text(letterCase[i]);
				}
			});
		}
		
		$(this).find(".timu-choose ul.many-timu li ul").each(function(index) {
			$(this).find("li").each(function(i) {
				if(!$(this).hasClass("complex")) {
					$(this).find("span").text(letterCase[i]);
				}
			})
		})
	})
}

function disableBtn() {
	var length = $(".part-three .xh").find("a").length;
	if(length==0) {
		$(".test-paper-top ul li").each(function(index) {
			if(index==0 || index==3) {
			} else {
				$(this).children("a").removeAttr("onclick");
				$(this).children("a").removeClass();
				$(this).children("a").addClass("btn-forbidGray");
			}
		})
	}
}


//拖拽
function drag() {
    var index = $(".part-one ul li").index($(".part-one ul .choose"));
    $("ul.sortable").sortable({
        connectWith: "ul",
        helper: function(event, ui) {
            var drag_info_box = $("<ul></ul>").addClass("drag_info_box");
            return drag_info_box;
        },
        start: function(event, ui) {
            var _drag_ele = ui.helper;
            var uuid;
            $(this).children().each(function(i) {
                if ($(this).children().text() == ui.item.context.innerText) {
                    uuid = $(this).children().attr("data-uuid");
                }
            });
            $(this).children().children("a").each(function(j) {
                if ($(this).attr("data-uuid") == uuid) {
                    _drag_ele.append("<li>" + $(this).parent().html() + "</li>");
                    $(this).parent().hide();
                }
            })
        },
        sort: function(event, ui) {
            if ($(".ui-sortable-placeholder").prev().children().attr("data-uuid") == $(".ui-sortable-placeholder").next().children().attr("data-uuid")) {
                $(".ui-sortable-placeholder").hide();
            } else {
                $(".ui-sortable-placeholder").show();
            }
        },
        stop: function(event, ui) {
            var uuid, index;
            $(".part-three ul li").each(function(i) {
                if ($(this).html() == ui.item.context.innerHTML) {
                    uuid = $(this).children().attr("data-uuid");
                    index = i;
                }
            });
            $(".part-three ul li a").each(function(j) {
                if ($(this).attr("data-uuid") == uuid) {
                    $(".part-three ul li").eq(index).after($(this).parent())
                }
            });
            $(".part-three ul li").show();
            $(".sortable").each(function(index) {
                $(this).children().each(function(i) {
                    if ($(this).html() == ui.item.context.innerHTML) {
                        var dragTarget = $(this).children().attr("data-uuid");

                        var gs = 0;
                        for (var k = 0; k < i; k++) {
                            if ($(".part-three ul").eq(index).children().children().eq(k).attr("data-uuid") != $(".part-three ul").eq(index).children().children().eq(k + 1).attr("data-uuid")) {
                                gs = gs + 1;
                            }
                        }
                        i = gs;
                        var timu;
                        $('.test-paper-right-content').find('li').each(function() {
                            var aa = $(this).attr('id');
                            if (aa == dragTarget) {
                                timu = $(this);
                                $(this).remove();
                            }
                        });

                        if (i == 0) {
                            var cc = $('.test-paper-right-content').find('.mgb20').eq(index).children('ul');
                            var bb = cc.children('li').length;

                            if (bb == 0) {
                                //拖动到没有小题的大题中
                                cc.append(timu);
                            } else {
                                //拖动到有小题的大题中
                                cc.children(':first-child').before(timu);
                            }
                        } else {

                            $('.test-paper-right-content').find('.mgb20').eq(index).children('ul').children('li').eq(i - 1).after(timu);
                        }
                    }
                })
            })
            var who_index = $(".part-one ul .choose").index();
            if (who_index == 0) {
                global();
            } else {
                group();
            }
        }
    });

    $(".sortable").disableSelection();
}

$(function(){
	drag();
	sortAnswer();
	scrollHeight();
	
	 //如果题目包含图片的高度过于大的话
    $('.timu span img').each(function(){
        if($(this).width()>500){
            $(this).width('200');
        }
    });
	// 	 切换科目时
	$("#subjectSelector").change(function(){
		var km_id=$(this).val();
		$(".part-three ul li a").css("background-color","#fff");
		 $(".part-three ul li a").each(function(){
			var code=$(this).data("code");
			if(code==km_id){
				$(this).css("background-color","#FEF156")
			}
		}); 
	})
})
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
/* var N = [
    "零", "一", "二", "三", "四", "五", "六", "七", "八", "九"
];
var M = ["十","百","千"];
function convertToChinese(num){
    var str = num.toString();
    var len = num.toString().length;
    var C_Num = [];
    for(var i = 0; i < len; i++){
        C_Num.push(N[str.charAt(i)]);
    }
    return C_Num.join('');
} */

// 阿拉伯数字改中文数字
function convertToChinese(num) { 
	var AA = new Array("零", "一", "二", "三", "四", "五", "六", "七", "八", "九"); 
	var BB = new Array("", "十", "百", "千", "萬", "億", "点", ""); 
	var a = ("" + num).replace(/(^0*)/g, "").split("."), k = 0, re = ""; 
	for (var i = a[0].length - 1; i >= 0; i--) { 
	switch (k) { 
	case 0: re = BB[7] + re; break; 
	case 4: if (!new RegExp("0{4}\\d{" + (a[0].length - i - 1) + "}$").test(a[0])) 
	re = BB[4] + re; break; 
	case 8: re = BB[5] + re; BB[7] = BB[5]; k = 0; break; 
	} 
	if (k % 4 == 2 && a[0].charAt(i + 2) != 0 && a[0].charAt(i + 1) == 0) re = AA[0] + re; 
	if (a[0].charAt(i) != 0){
		if(k % 4==1){
			if(a[0].charAt(i)==1){
			AA[a[0].charAt(i)]=""
			}
		}
		re = AA[a[0].charAt(i)] + BB[k % 4] + re;}  k++; 
	} 
	
	return re; 
	} 
</script>
</body>
</html>