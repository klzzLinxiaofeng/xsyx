<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/test/zujuan.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
<title>批量设置</title>
<style>
	.diyith .dth{
		 font-weight: normal; 
	}
</style>
</head>
<body>
<div class="b1-3 box-sizing">
        <div class="b1-3-top">
            <ul class="display-ib">
                <li><a href="javascript:void(0)" class="quanxuan">全选</a></li>
                <li style="display:none;"><a href="javascript:void(0)" class="buxuan" >取消选择</a></li>
                <li><a href="javascript:void(0)" class="fanxuan">反选</a></li>      
            </ul>
            <p>
                <span class="mgr20">将所选题目批量修改为<input type="text" class="border-r4-1s f8f8f9 mgrmgl12"/>分/题</span>
                <select class="border-r4-1s f8f8f9" id="questionType" onchange="changeQuestionType(this)">
                    <option value="0">题型</option>
                    <option value="radio">单选题/多选题</option>
                    <option value="multichoise">不定项选择题</option>
                </select>
            </p>
        </div>
        <div class="b1-3-center">
        	<c:forEach items="${result }"  var="questionGroup">
	            <table border="1">
	            <thead>
	                <tr class="f9f8fb">
	                    <th class="diyith" ><b class="dth"></b>、${questionGroup.groupName }</th>
	                    <th style="width: 148px;">分值</th>
	                    <th style="width: 162px;">题型</th>
	                </tr>
	            </thead>
	            <tbody>
	                <c:forEach items="${questionGroup.questionList }" var="question" varStatus="status">
	                	<c:choose>
	                		<c:when test="${question.questionType=='cloze' }">
	                			<c:forEach items="${question.childrenQuestionVo }" var="child" varStatus="childStatus">
		                			<tr class="question">
					                	<input type="hidden" value="${child.id }" />
					                    <td class="one">
					                    	<p class="dj">
					                    		<i></i>
					                    		<span><b style="font-weight:normal;"></b>
						                    	<strong class="cloze">
						                    		<c:forEach items="${child.answers }" var="answer">
						                    			<em style="margin-right:10px;">${answer }</em>
						                    		</c:forEach>
						                    	</strong>
					                    		</span>
					                    	</p>
					                    </td>
					                    <td>
					                        <input type="text" value="${child.score }" name="questionScore"/>
					                    </td>
					                    <td>
					                    	<c:choose>
												<c:when test="${child.questionType=='radio' }">
													<select>
														<option value="radio" data-answersCount="${child.answersCount}">单选题</option>
													       <option value="multichoise" data-answersCount="${child.answersCount}">不定项选择题</option>
													   </select>
													</c:when>
												<c:when test="${child.questionType=='checkbox' }">
													<select>
														<option value="checkbox" data-answersCount="${child.answersCount}">多选题</option>
													    <option value="multichoise" data-answersCount="${child.answersCount}">不定项选择题</option>
													</select>
												</c:when>
												<c:when test="${child.questionType=='multichoise' }">
													<select>
														<option value="radio" data-answersCount="${child.answersCount}">不定项选择题</option>
														<c:if test="${child.answersCount==1 }">
															<option value="radio" data-answersCount="${child.answersCount}">单选题</option>
														</c:if>
														<c:if test="${child.answersCount!=1 }">
															<option  value="checkbox" data-answersCount="${child.answersCount}">多选题</option>
														</c:if>
												   </select>
												</c:when>
												<c:otherwise>
													<select disabled="disabled">
														<c:if test="${child.questionType=='blank' }">
															<option value="blank">填空题</option>
														</c:if>
														<c:if test="${child.questionType=='trueOrFalse' }">
															<option value="trueOrFalse">判断题</option>
														</c:if>
														<c:if test="${child.questionType=='selectedfill' }">
															<option value="selectedfill">选择填空题</option>
														</c:if>
														<c:if test="${child.questionType=='word' }">
															<option value="word">简答题</option>
														</c:if>
														<c:if test="${child.questionType=='cloze' }">
															<option value="cloze">完形填空</option>
														</c:if>
														<c:if test="${child.questionType=='complex' }">
															<option value="complex">复合题</option>
														</c:if>
													   </select>
												</c:otherwise>
											</c:choose>
					                    </td>
					                </tr>
				                </c:forEach>
	                		</c:when>
	                		<c:when test="${question.questionType=='complex' }">
	                			<c:forEach items="${question.childrenQuestionVo }" var="child">
		                			<tr class="question">
					                	<input type="hidden" value="${child.id }" />
					                    <td class="one">
					                    	<p class="dj">
					                    		<i></i>
					                    		<span><b style="font-weight:normal;"></b>.${child.content }</span>
					                    	</p>
					                    </td>
					                    <td>
					                        <input type="text" value="${child.score }" name="questionScore"/>
					                    </td>
					                    <td>
					                    	<c:choose>
												<c:when test="${child.questionType=='radio' }">
													<select>
														<option value="radio" data-answersCount="${child.answersCount}">单选题</option>
													       <option value="multichoise" data-answersCount="${child.answersCount}">不定项选择题</option>
													   </select>
													</c:when>
												<c:when test="${child.questionType=='checkbox' }">
													<select>
														<option value="checkbox" data-answersCount="${child.answersCount}">多选题</option>
													    <option value="multichoise" data-answersCount="${child.answersCount}">不定项选择题</option>
													</select>
												</c:when>
												<c:when test="${child.questionType=='multichoise' }">
													<select>
														<option value="radio" data-answersCount="${child.answersCount}">不定项选择题</option>
														<c:if test="${child.answersCount==1 }">
															<option value="radio" data-answersCount="${child.answersCount}">单选题</option>
														</c:if>
														<c:if test="${child.answersCount!=1 }">
															<option  value="checkbox" data-answersCount="${child.answersCount}">多选题</option>
														</c:if>
												   </select>
												</c:when>
												<c:otherwise>
													<select disabled="disabled">
														<c:if test="${child.questionType=='blank' }">
															<option value="blank">填空题</option>
														</c:if>
														<c:if test="${child.questionType=='trueOrFalse' }">
															<option value="trueOrFalse">判断题</option>
														</c:if>
														<c:if test="${child.questionType=='selectedfill' }">
															<option value="selectedfill">选择填空题</option>
														</c:if>
														<c:if test="${child.questionType=='word' }">
															<option value="word">简答题</option>
														</c:if>
														<c:if test="${child.questionType=='cloze' }">
															<option value="cloze">完形填空</option>
														</c:if>
														<c:if test="${child.questionType=='complex' }">
															<option value="complex">复合题</option>
														</c:if>
													   </select>
												</c:otherwise>
											</c:choose>
					                    </td>
					                </tr>
				                </c:forEach>
	                		</c:when>
	                		<c:otherwise>
	                			<tr class="question">
				                	<input type="hidden" value="${question.id }" />
				                    <td class="one">
				                    	<p class="dj">
				                    		<i></i>
				                    		<span><b style="font-weight:normal;"></b>.${question.content }</span>
				                    	</p>
				                    </td>
				                    <td>
				                        <input type="text" value="${question.score }" name="questionScore"/>
				                    </td>
				                    <td>
				                    	<c:choose>
											<c:when test="${question.questionType=='radio' }">
												<select>
													<option value="radio" data-answersCount="${question.answersCount}">单选题</option>
												       <option value="multichoise" data-answersCount="${question.answersCount}">不定项选择题</option>
												   </select>
												</c:when>
											<c:when test="${question.questionType=='checkbox' }">
												<select>
													<option value="checkbox" data-answersCount="${question.answersCount}">多选题</option>
												    <option value="multichoise" data-answersCount="${question.answersCount}">不定项选择题</option>
												</select>
											</c:when>
											<c:when test="${question.questionType=='multichoise' }">
												<select>
													<option value="radio" data-answersCount="${question.answersCount}">不定项选择题</option>
													<c:if test="${question.answersCount==1 }">
														<option value="radio" data-answersCount="${question.answersCount}">单选题</option>
													</c:if>
													<c:if test="${question.answersCount!=1 }">
														<option  value="checkbox" data-answersCount="${question.answersCount}">多选题</option>
													</c:if>
											   </select>
											</c:when>
											<c:otherwise>
												<select disabled="disabled">
													<c:if test="${question.questionType=='blank' }">
														<option value="blank">填空题</option>
													</c:if>
													<c:if test="${question.questionType=='trueOrFalse' }">
														<option value="trueOrFalse">判断题</option>
													</c:if>
													<c:if test="${question.questionType=='selectedfill' }">
														<option value="selectedfill">选择填空题</option>
													</c:if>
													<c:if test="${question.questionType=='word' }">
														<option value="word">简答题</option>
													</c:if>
													<c:if test="${question.questionType=='cloze' }">
														<option value="cloze">完形填空</option>
													</c:if>
													<c:if test="${question.questionType=='complex' }">
														<option value="complex">复合题</option>
													</c:if>
												   </select>
											</c:otherwise>
										</c:choose>
				                    </td>
				                </tr>
	                		</c:otherwise>
	                	</c:choose>
	                </c:forEach>
	                </tbody>
	            </table>
            </c:forEach>
        </div>
        <div class="b1-3-bottom">
            <ul>
                <li class="mgr20">
                    <span class="total-points">总分：</span>
                    <span class="color2ba" style="font-weight: bold;">${assemblyPaper.totalScore}</span>
                </li>
                <li>
                    <span class="subject-number">题目数量：</span>
                    <span class="color2ba" style="font-weight: bold;">${assemblyPaper.questionSize }</span>
                </li>
            </ul>
        </div>
        <div class="last_ope" style="text-align: center;">
            <a href="javascript:void(0)" class="btn-blue" onclick="submit();">是</a>
            <a href="javascript:void(0)" class="btn-lightGray" onclick="$.closeWindow();">否</a>
        </div>
    </div>
<script>
//滚动条
$(document).ready(function() {  
	$('table thead .dth').each(function(index){
		$(this).text(convertToChinese(index+1));
	});
	$(".b1-3-center").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
}); 

var letterCase = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T",
              	"U","V","W","X","Y","Z"];

$(function(){
// 	设置分数0-100，一个小数点
$(".b1-3-center table td input[type='text'],.b1-3-top input[type='text']").keyup(function(event){
               var keycode = event.which;
                  if (keycode != 37&&keycode != 38&&keycode != 39&&keycode != 40) {
                    //匹配负号，数字，小数点
                    this.value = this.value.replace(/[^\d.]/g, "");
                  //匹配第一个输入的字符不是小数点
                  this.value = this.value.replace(/^\./g, "");
                  //保证.-只出现一次，而不能出现两次以上
                  this.value = this.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
//                   匹配一位小数点
                  this.value = this.value.replace(/^(\-)*(\d+)\.(\d).*$/,'$1$2.$3');
                  if(this.value>100){
                	  var atr=this.value.substring(0,3);
                	  if(atr==100){
                		  this.value=atr;
                	  }else{
                		  this.value=atr.substring(0,2)
                	  }
                  }
                  /* if(this.value==0){
                	  var atr=this.value;
                	  this.value=atr.substring(0,atr.length-1);
                  } */
                  }
         });

	$(".cloze").each(function(index) {
		$(this).find("em").each(function(i) {
			var answer = $(this).text();
			answer = letterCase[i] + "、" + answer;
			$(this).text(answer);
		})
	});
	//小题排序
	$('tbody tr td p').each(function(index){
		$(this).find('b').text((index+1)+'、');
	});
	//图片替换
	$("tr td img").each(function(){
		$(this).prev().append("<b style='font-weight:normal;color:#b0b0b0;'>(图)</b>");
		$(this).remove();
	});
	//文本框替换
	  $("tr td span input").each(function(){
		$(this).before("<b style='font-weight:normal;color:#b0b0b0;line-height: 9px;display: inline-block;border-bottom: 1px solid #d0d0d0;margin-left: 5px;'>____</b>");
		$(this).remove();
	});  
	$('.dj').click(function(){
        var aa = $(this).children('i').attr('class');
        if(aa=="" || aa==null){
            $(this).children().addClass('choose-answer');
                 	
        }else{
            $(this).children().removeClass('choose-answer');
               
        }
        checkQuestionType();
    });
	
	$(".quanxuan").click(function(){
		$(".b1-3-center table td i").addClass('choose-answer');
		checkQuestionType();
		$(this).parent().hide();
		$('.buxuan').parent().show();
		
	});
	
	$(".buxuan").click(function(){
		$(".b1-3-center table td i").removeClass('choose-answer');
		checkQuestionType();
		$(this).parent().hide();
		$('.quanxuan').parent().show();
	});
	
	$(".fanxuan").click(function(){
		$(".b1-3-center table td i").each(function(){
			if($(this).hasClass('choose-answer')){
				$(this).removeClass('choose-answer');
			}else{
				$(this).addClass('choose-answer');
			}
		})
		checkQuestionType();
	});
	
	//没有选择题目，修改分数
	$(".b1-3-top input[type='text']").click(function() {
		 var a =0;
	    $('tbody tr td.one').find('i').each(function(){
	    	 if($(this).hasClass('choose-answer')){
	         	a++;
	         }
	    }); 
	    if(a==0){
	    	layer.confirm("请选择题目", { btn: '确定'},function () {
					layer.closeAll();
		 		});
	    }else{
	    	$(this).select();
	    }
	});

		
	 //更改分数
	 $(".b1-3-top input[type='text']").blur(function() {	 
		 var a =0;
         $('tbody tr td.one').find('i').each(function(){
         	 if($(this).hasClass('choose-answer')){
              	a++;
              }
         });
         if(a!=0){
        	 if($(this).val()==""||$(this).val()==0){
     			var This=$(this);
     			layer.confirm("请输入大于0的数字，最多一位小数", { btn: '确定'},function () {
     				layer.closeAll();
     				This.focus();
     	 		},function(){ 
     	 			This.focus();
     			 });
     		}else{
     			var score = $(this).val();
     			if(score=="") {
     				return;
     			}
     			$(".b1-3-center table td i.choose-answer").each(function(index) {
     				var value = $(this).parents("tr").children("td").children("input").val();
     				$(this).parents("tr").children("td").children("input").val(score);
     			})
     			updateTotalScore();
     		}
         }
		
	})  
	
	$(".b1-3-center table td input[name='questionScore']").blur(function() {
		if($(this).val()==""||$(this).val()==0){
			var This=$(this);
			layer.confirm("请输入大于0的数字，最多一位小数", { btn: '确定'},function () {
				layer.closeAll();
				This.focus();
	 		},function(){ 
	 			This.focus();
			 });
		}else{
			updateTotalScore();
		}
	})
})

function changeQuestionType(obj) {
	var option = $(obj).val();
	if("0"==option) {
		return ;
	}
	$(".b1-3-center table td i.choose-answer").each(function(index) {
		if("multichoise"==option) {
			$(this).parents("tr").children("td").children("select").val("multichoise");
		} else {
			var answersCount = $(this).parents("tr").children("td").children("select").find("option:selected").data("answerscount");
			console.log(answersCount);
			if(answersCount==1){
				$(this).parents("tr").children("td").children("select").val("radio");
			} else {
				$(this).parents("tr").children("td").children("select").val("checkbox");
			}
		}
	})
}

function checkQuestionType() {
	var option = "<option value='0'>题型</option>";
	option += "<option value=\"radio\">单选题/多选题</option>";
	option += "<option value=\"multichoise\">不定项选择题</option>";
	
	var length = $(".b1-3-center table td i.choose-answer").length;
	if(length==0) {
		$("#questionType").html(option);
		$("#questionType").removeAttr("disabled");
		return;
	}
	
	var check = 0;
	
	$(".b1-3-center table td i.choose-answer").each(function(index) {
		var value = $(this).parents("tr").children("td").children("select").val();
		if("checkbox"==value || "multichoise"==value || "radio"==value) {
		} else {
			check = 1;
		}

	})
	if(check==0) {
		$("#questionType").html(option);
		$("#questionType").removeAttr("disabled");
	} else {
		$("#questionType").html("<option>当前题目不能更改题型</option>");
		$("#questionType").attr("disabled", "disabled")
	}
}

function updateTotalScore() {
	var totalScore = 0.0;
	$(".b1-3-center table td input[name='questionScore']").each(function() {
		totalScore += parseFloat($(this).val());
	})
	$(".b1-3-bottom").find(".color2ba").eq(0).text(totalScore);
}

function submit() {
	var questions = new Array();
	$(".question").each(function(index) {
		var question = {};
		var id = $(this).children("input").val();
		question["id"] = id;
		var score = $(".b1-3-center table td input[name='questionScore']").eq(index).val();
		question["score"] = score;
		var type = $(".b1-3-center table td select").eq(index).val();
		question["type"] = type;
		questions[index] = question;
	})
	
	$.ajax({
	    url: "${pageContext.request.contextPath}/paper/assembly/question/batchModify",
	    type: "POST",
	    data: {"questions":JSON.stringify(questions)},
	    async: false,
	    success: function() {
	    	var type = "${type}";
	    	if("center"==type) {
	    		$("#iframe_main", window.parent.document).attr("src", "${pageContext.request.contextPath}/paper/assembly/index");
	    	}
	    	$.closeWindow();
	    }
	});
}
//阿拉伯数字改中文数字
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