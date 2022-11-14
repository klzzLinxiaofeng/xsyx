<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="background: #d5d5d5;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/test/zujuan.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
<title>试题详情</title>
<style>

	/*三角形*/
.dxa_ts:before {
    position: absolute;
    top: 35px;
    left: calc(60% - 5px);
    width: 10px;
    height: 10px;
    background: #e7faff;
    box-shadow: 2px 2px 0 -1px #c4c4c4;
    content: "";
    transform: rotate(45deg);
    border-bottom: 1px solid #9ad3ff;
    border-right: 1px solid #9ad3ff;
}
/*自适应居中*/
.dxa_ts {
    position: absolute;
    width:252px;
    top: -58px;
    left: 22%;
    z-index: 3;
    visibility: hidden;
    padding: 10px;
    line-height:20px;
    min-height: 12px;
    border-radius: 4px;
    background: #e7faff;
    box-shadow: 0 2px 8px rgba(0,0,0,.3);
    color: #2ca0f8;
    font-size: 12px;
    opacity: 0;
    transition: visibility 0s linear .2s,opacity .2s linear 0s;
    transform: translateX(-50%);
    backface-visibility: visible!important;
    backface-visibility: hidden;
    border: 1px solid #9ad3ff;
}
/*hover效果*/
.grey_mj:hover .dxa_ts {
    visibility: visible;
    opacity: 1;
    transition: visibility 0s linear 0s,opacity .4s linear;
    animation: fade-top;
    animation-duration: .4s;
    -webkit-animation: fade-top .4s;
}
/*hover动画*/
@keyframes fade-top {
    0% {
        opacity: .1;
        top: 0
    }
 
    100% {
        opacity: 1;
        top: -56px
    }
}
 
@-webkit-keyframes fade-top {
     0% {
        opacity: .1;
        top: 0
    }
 
    100% {
        opacity: 1;
        top: -56px
    }
}
</style>
</head>
<body>
<div class="a1-1">
        <div class="a1-1-top">
            <a href="javascript:history.back();" class="btn-lightGray mgr10">返回</a>
<!--             <a href="javascript:window.opener=null;window.open('','_self');window.close();" class="btn-lightGray mgr10">返回</a> -->
        </div>
        
                <div class="a1-1-content">
            <div class="a1-1-content-left">
                <div class="a1-1-content-left-top">
                    <p>
                        看了这道题的人最近还看了...
                    </p>
                    <ul>

						<c:forEach items="${historyQuestionList}" var="item">
							<li>
								<span>${item.questionTypeString}</span>
								<a href="javascript:showQuestionDetail(${item.id},'${item.uuid}')">${item.content}</a>
							</li>
						</c:forEach>
                    </ul>
                </div>
                <div class="a1-1-content-left-top">
                    <p>
                        相关试题推荐
                    </p>
                   
                    <c:if test="${not empty relatedQuestion}">
                     <ul>
                        <li>
                            <span>${relatedQuestion.questionTypeString}</span>
                            <a href="javascript:showQuestionDetail(${relatedQuestion.id},'${relatedQuestion.uuid}')">${relatedQuestion.content}</a>
                        </li>
                       </ul>
                    </c:if>
                   
                </div>
                <div class="clear"></div>
            </div>
        
        
            <div class="a1-1-content-right">
                <div class="part-top">
                    <p>
                        <span class="difficult">${question.difficulityString}</span>
                        <span class="accuracy mgl10">正答率：${question.rightRate}%</span>
                    </p>
                   
                    <div>
                        <span class="knowledge-point">知识点</span>
                        <ul>
                         <c:if test="${empty question.knowledge}">
                         <li>暂无</li>
                         </c:if> 
                        <c:forEach items="${question.knowledge}" var="item">
                        	<li>${item}</li>
                        </c:forEach>
                        </ul>
                    </div>
                    
                </div>
                <div class="part-center">
                    <ul>
                        <li>
                            <div class="timu">
                             <span class="single-choice mgr6">${question.questionTypeString}</span>
								<c:choose>
									<c:when test="${question.questionType eq 'cloze' or question.questionType eq 'complex'}">
										<span>${question.content}</span>
									</c:when>
									<c:otherwise>
										<span>${question.content}</span>
									</c:otherwise>
								</c:choose>
 								<!--  答案区  -->
                                <div class="timu-choose">
                                    <ul class="tc-answer">
                                    <c:choose>
	                                    <c:when test="${question.questionType eq 'trueOrFalse'}">
	                                    	<c:forEach items="${question.answers}" var="answer">
                                   				 <li><span class="xxqq"></span>${answer}</li>
                               				</c:forEach>
	                                    </c:when>
	                                    
	                                      <c:when test="${question.questionType eq 'cloze'}">
				                            	<c:forEach items="${question.childrenQuestionVo}" var="item"
				                                           varStatus="status">
				                                    
				                                     <li class="cloze-answer">
				                                   	 <label style="float: left;margin-top: 8px;">（${status.index+1}）</label>
				                                     <div>
					                                    <c:forEach items="${item.answers}" var="answer" varStatus="status1">
		                                   					 <p>
				                                   			 		<span class="xxqq" data-a="${status1.index}"></span>
				                                   			 		${answer}
			                                   		 		</p>
			                               				</c:forEach>
				                                    </div>
				                                    </li> 
				                                </c:forEach>
				                            </c:when>
	                                    
	                                    <c:when test="${question.questionType eq 'complex'}">
	                                    	<c:forEach items="${question.childrenQuestionVo}" var="item" varStatus="status">

                                    				<li>（${status.index+1}）、${item.content}</li>
			                                				<c:forEach items="${item.answers}" var="answer" varStatus="status1">
			                                   				 <li>
			                                   			 			<span class="xxqq" 
				                                   			 			<c:if test="${item.questionType ne 'trueOrFalse'}">
				                                   			 					data-a="${status1.index}"
				                                   					 	</c:if>
			                                   						 >
			                                   						 </span>
			                                   						 ${answer}
			                                   		 		</li>
			                               					</c:forEach>

                                			</c:forEach>             
                                		 </c:when>

											<c:otherwise>
												<c:forEach items="${question.answers}" var="answer"
													varStatus="status1">
													<li><span class="xxqq" data-a="${status1.index}"></span>
														${answer}</li>
												</c:forEach>
											</c:otherwise>
											
										</c:choose>
                                    </ul>
                                </div>
                            </div>

							<div class="answer-and-analysis">
								<div class="j1" style="margin-top: 10px;">
					                <span class="jx_wz" style="line-height: 22px;">【答案】</span>
					                    
					                <div class="color1d9 jx_nr" >
					                	
										<c:choose>
											<c:when test="${question.questionType eq 'cloze'}">
												<c:forEach items="${question.childrenQuestionVo}" var="childQuestion" varStatus="status">
													<c:forEach items="${childQuestion.correctAnswers}" var="correctAnswer">${correctAnswer}</c:forEach>
													<c:if test="${status.count%5==0}">&nbsp&nbsp</c:if>
												</c:forEach>
											</c:when>

											<c:when test="${question.questionType eq 'complex'}">
													<c:forEach items="${question.childrenQuestionVo}" var="childrenQuestion" varStatus="status">
									                	<div ><label style="display:inline-block;float:left;margin:0;line-height:32px">（${status.index+1}）、</label>
									                	<div style="margin-left: 45px;line-height: 30px;">
									                    <c:choose>
									                        <c:when test="${childrenQuestion.questionType eq 'checkbox' or childrenQuestion.questionType eq 'multichoise'}">
									                            <c:forEach items="${childrenQuestion.correctAnswers}" var="correctAnswer">
									                                ${correctAnswer}
									                            </c:forEach>
									                        </c:when>
									
									                        <c:when test="${childrenQuestion.questionType eq 'blank'}">
									                        	
									                            <c:forEach items="${childrenQuestion.correctAnswers}" var="correctAnswer">
									                                <span >${correctAnswer}</span><br>
									                            </c:forEach>
									                          
									                        </c:when>

																<c:otherwise>
																	<c:forEach items="${childrenQuestion.correctAnswers}" var="correctAnswer">
						                              					  ${correctAnswer}
						                           					 </c:forEach>
																</c:otherwise>
															</c:choose>
									                    </div>
									                    <div class="clear"></div>
									                	</div>
												    </c:forEach>
												  </c:when>
					
										    <c:when test="${question.questionType eq 'blank'}">
										        <c:forEach items="${question.correctAnswers}" var="correctAnswer">
										            ${correctAnswer}<br/>
										        </c:forEach>
										    </c:when>
										
										    <c:otherwise>
										        <c:forEach items="${question.correctAnswers}" var="correctAnswer">
						              				${correctAnswer}
						        			</c:forEach>
										    </c:otherwise>
										    </c:choose>
									    
									</div>
								</div>
								<div class="j2" style="display: inline-block;">
							    	<span class="jx_wz">【解析】</span>
							    	<div class="jx_nr">
								    	
								    		<c:choose>
												<c:when test="${question.questionType eq 'cloze' or question.questionType eq 'complex'}">
													<c:forEach items="${question.childrenQuestionVo}" var="item"
														varStatus="status">
														<p >（${status.index+1}）、${item.explanation}</p>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<c:if test="${empty question.explanation}">无</c:if>
					                               		<p>${question.explanation}</p>
					                            </c:otherwise>
											</c:choose>
										
							    	</div>
							    </div>
								
							</div>

							<div class="timu-handle">
								<ul>
									<li><span><i class="difficulty"></i>难度：<b>${question.difficulityString}</b></span>
									</li>
									<li><span><i class="update-time"></i>更新时间：<b><fmt:formatDate
													value="${question.modifyDate}" pattern="yyyy/MM/dd" /></b></span></li>
									<li><span><i class="collect"></i>收藏( <b class="blue">${question.favCount}</b> )</span></li>
									<li><span><i class="use"></i>使用( <b class="blue">${question.usedCount}</b> )</span></li>
									<li class="fr">
									<c:choose>
											<c:when test="${question.isFav}">
												<btn style="position: relative; top: -5px;" class="btn btn-lightGray" onclick="fav(${question.id},this)" fav="${!question.isFav}">取消收藏</btn>
											</c:when>
											<c:otherwise>
												<btn style="position: relative; top: -5px;" class="btn btn-blue" onclick="fav(${question.id},this)" fav="${!question.isFav}">收藏</btn>
											</c:otherwise>
									</c:choose> 
									<span class="grey_mj" style="position:relative"> 
									<btn style="position: relative; top: -5px;" class="btn btn-orange basket" data-stage="${question.stageCode}" data-questionId="${question.id}" 
										data-count="
										<c:choose>
											<c:when test="${question.questionType eq 'cloze' or question.questionType eq 'complex'}">
												${fn:length(question.childrenQuestionVo)}
											</c:when>
											 <c:otherwise>
											 	1
											 </c:otherwise>
										</c:choose>
										"
										onclick="window.parent.addToBasket(this)">加入试题篮</btn>
										</span>
									
									</li>
								</ul>
							</div>
						</li>
                    </ul>
                </div>
                <div class="part-bottom"></div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
    </body>
</html>

<script type="text/javascript">

$(function (){
 	window.parent.checkBtnState();
 	
 	//波浪线
	$("span").each(function () {
        var style = $(this).attr("style");
        if(style != undefined && style != ""){
            if(style.indexOf("symbol:waveline") != -1){
                 $(this).addClass("waveline");
            }
        }
    })

 	
});



//收藏
function fav(id, obj) {
	var fav = $(obj).attr("fav");
    $.ajax({
        url: "${pageContext.request.contextPath}/paper/doFav",
        type: "POST",
        data: {"id":id, "fav":fav,"type":1},
        async: false,
        success: function() {
        	if(fav=="true") {
        		$.success("收藏成功！");
				var sc=$(obj).parent().prev().prev().children("span").children("b");
				sc.text((parseInt(sc.text())+1));
        		$(obj).text("取消收藏");
        		$(obj).attr("fav",false);
        		$(obj).attr("class","btn btn-lightGray")
        	} else {
        		$.success("取消收藏成功！");
        		var sc=$(obj).parent().prev().prev().children("span").children("b");
//         		var sc=$(obj).parent().prev(".detail").children(".sc").children("span");
				sc.text((parseInt(sc.text())-1));
        		$(obj).text("收藏");
        		$(obj).attr("fav",true);
        		$(obj).attr("class","btn btn-blue")
        	}
        }
    });
}

function showQuestionDetail(id,uuid){
	window.location.href="${pageContext.request.contextPath}/paper/question/detail?dm=${param.dm}&id="+id+"&uuid="+uuid;
// 	window.open("${pageContext.request.contextPath}/paper/question/detail?dm=${param.dm}&id="+id+"&uuid="+uuid, '_blank');

}

$(function(){
	var zm = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
	$(".xxqq").each(function(){
		var index = $(this).data("a");
		$(this).text(zm[index]);
	});
});
</script>