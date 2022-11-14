<%@include file="/views/embedded/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="test-paper-right-top">
    <span class="show-answer">
        <i class=""></i>显示答案与解析
    </span>
</div>
<div class="test-paper-right-content" style="display:block;">
	<c:forEach items="${result }" var="questionGroup">
	    <div class="mgb20">
	    	<c:if test="${assemblyPaper.hasGroup==1 }">
	        	<p><label>${questionGroup.strPos }</label>：<label>${questionGroup.groupName }</label>（共<span>${questionGroup.score }</span>分）</p>
	        </c:if>
	        <ul class="dati">
	        <c:forEach items="${questionGroup.questionList }" var="question" varStatus="status">
	        	
		            <li id="${question.id}" class="mgb15" >
	                	 <c:choose>
	                	 	<c:when test="${question.questionType=='radio' }">
		                	 	<div class="timu">
					               <span class="timu-type">单选题</span>
					               <label>${status.count }</label>
					               <span style="line-height:26px;">、（${question.score}分）${question.content }</span>
					               <div class="timu-choose">
			                	 		<ul>
			                    			<c:forEach items="${question.answers }" var="answer">
						                        <li><span class="xxqq"></span>${answer }</li>
					                        </c:forEach>
					                    </ul>
					                </div>
                				</div>
	                    	</c:when>
	                    	<c:when test="${question.questionType=='checkbox' }">
	                    		<div class="timu">
					               <span class="timu-type">多选题</span>
					               <label>${status.count }</label>
					               <span>、（${question.score}分）${question.content }</span>
					               <div class="timu-choose">
			                	 		<ul>
			                    			<c:forEach items="${question.answers }" var="answer">
						                        <li><span class="xxqq"></span>${answer }</li>
					                        </c:forEach>
				                    	</ul>
			                    	</div>
                				</div>
	                    	</c:when>
	                    	<c:when test="${question.questionType=='multichoise' }">
		                    	<div class="timu">
					               <span class="timu-type">不定项</span>
					               <label>${status.count }</label>
					               <span>、（${question.score}分）${question.content }</span>
					               <div class="timu-choose">
		                	 		<ul>
		                    			<c:forEach items="${question.answers }" var="answer">
					                        <li><span class="xxqq"></span>${answer }</li>
				                        </c:forEach>
				                    </ul>
				                    </div>
                				</div>
	                    	</c:when>
	                    	<c:when test="${question.questionType=='trueOrFalse' }">
	                    		<div class="timu">
					               <span class="timu-type">判断题</span>
					               <label>${status.count }</label>
					               <span>、（${question.score}分）${question.content }</span>
					               <div class="timu-choose">
		                	 		<ul>
					                	<li><span class="unorder xxqq"></span>正确</li>
					                	<li><span class="unorder xxqq"></span>错误</li>
				                    </ul>
				                    </div>
                				</div>
	                    	</c:when>
	                    	<c:when test="${question.questionType=='blank' }">
	                    		<div class="timu">
					               <span class="timu-type">填空题</span>
					               <label>${status.count }</label>
					               <span>、（${question.score}分）${question.content }</span>
					               <div class="timu-choose">
			                	 		<ul>
			                    			<c:forEach items="${question.answers }" var="answer">
						                        <li><span class="xxqq"></span>${answer }</li>
					                        </c:forEach>
					                    </ul>
					                </div>
                				</div>
	                    	</c:when>
	                    	<c:when test="${question.questionType=='word' }">
	                    		<div class="timu">
					               <span class="timu-type">简答题</span>
					               <label>${status.count }</label>
					               <span>、（${question.score}分）${question.content }</span>
					               <div class="timu-choose">
			                	 		<ul>
			                    			<c:forEach items="${question.answers }" var="answer">
						                        <li><span class="xxqq"></span>${answer }</li>
					                        </c:forEach>
					                    </ul>
					                </div>
                				</div>
	                    	</c:when>
	                    	<c:when test="${question.questionType=='blank' }">
	                    		<div class="timu">
					               <span class="timu-type">填空题</span>
					               <label>${status.count }</label>
					               <span>、（${question.score}分）${question.content }</span>
					               <div class="timu-choose">
			                	 		<ul>
			                    			<c:forEach items="${question.answers }" var="answer">
						                        <li><span class="xxqq"></span>${answer }</li>
					                        </c:forEach>
					                    </ul>
					                </div>
                				</div>
	                    	</c:when>
	                    	<c:when test="${question.questionType=='complex' }">
	                    		<div class="timu">
					               <!-- <span class="timu-type">复合题</span> -->
					               <label>${status.count }</label>
					               <span class="manyTimuTitle"><span class="timu-type">复合题</span>（${question.score}分）${question.content }</span>
							       <p class="zk_sq" style="display:none;">
										<b class="zk" style="display: none">展开<i class="i1"></i></b> 
										<b class="sq"style="display: none">收起<i class="i2"></i></b>
									</p>
					               <div class="timu-choose">
		                	 		 
	                                 <ul class="many-timu">
	                                     <li>
	                                         <div>
	                                             <ul class="exz">
	                                             	<c:forEach items="${question.childrenQuestionVo }" var="child">
	                                                 	<li class="complex">（<label>1</label>）、${child.content }</li>
	                                                </c:forEach>
	                                             </ul>
	                                         </div>
	                                     </li>
                               		 </ul>
                               		</div>
                				</div>
	                    	</c:when>
	                    	<c:when test="${question.questionType=='cloze' }">
	                    		<div class="timu">
					              <!--  <span class="timu-type">完形填空</span> -->
					               <label>${status.count }</label>
					               <span class="manyTimuTitle" ><span class="timu-type">完形填空</span>（${question.score}分）${question.content }</span>
					               <p class="zk_sq" style="display:none;">
										<b class="zk" style="display: none">展开<i class="i1"></i></b> 
										<b class="sq"style="display: none">收起<i class="i2"></i></b>
									</p>
					               <div class="timu-choose">
			                	 		 
		                                 <ul class="many-timu">
		                                     <li>
		                                         <div>
	                                             	<c:forEach items="${question.childrenQuestionVo }" var="child">
	                                             		 <ul class="exz">
		                                                 	（<label>1</label>）、（${child.score}分）
		                                                 	<c:forEach items="${child.answers }" var="answer">
										                        <li><span class="xxqq">A</span>${answer }</li>
									                        </c:forEach>
									                         </br>
								                         </ul>
	                                                </c:forEach>
		                                         </div>
		                                     </li>
                                		 </ul>
                                	</div>
                				</div>
	                    	</c:when>
	                    </c:choose>
		                <div class="answer-and-analysis" style="display: none">
	                        <c:choose>
			                	 	<c:when test="${question.questionType=='radio' }">
			                	 		<div class="j1">
							               <span class="jx_wz">【答案】</span>
							                   
							               <div class=" jx_nr" >
							               	<p class="color1d9"> 
							               		<c:forEach items="${question.correctAnswers }" var="correctAnswer">
					                        		${correctAnswer}
					                        	</c:forEach></p>
										    
											</div>
										</div>
										<div class="j2" style="display: inline-block;">
									    	<span class="jx_wz">【解析】</span>
									    	<div class="jx_nr">
									    		<p>${question.explanation }</p>
									    		
									    	</div>
									    </div>
			                	 		
			                    	</c:when>
			                    	<c:when test="${question.questionType=='checkbox' }">
			                    		<div class="j1">
							               <span class="jx_wz">【答案】</span>
							                   
							               <div class=" jx_nr" >
							               	<p class="color1d9"> 
							               		<c:forEach items="${question.correctAnswers }" var="correctAnswer">
					                        		${correctAnswer}
					                        	</c:forEach>
					                        </p>
										    
											</div>
										</div>
										<div class="j2" style="display: inline-block;">
									    	<span class="jx_wz">【解析】</span>
									    	<div class="jx_nr">
									    		<p>${question.explanation }</p>
									    		
									    	</div>
									    </div>
			                    		
			                    	</c:when>
			                    	<c:when test="${question.questionType=='multichoise' }">
			                    		<div class="j1">
							               <span class="jx_wz">【答案】</span>
							                   
							               <div class=" jx_nr" >
							               	<p class="color1d9"> 
							               		<c:forEach items="${question.correctAnswers }" var="correctAnswer">
					                        		${correctAnswer}
					                        	</c:forEach>
					                        </p>
										    
											</div>
										</div>
										<div class="j2" style="display: inline-block;">
									    	<span class="jx_wz">【解析】</span>
									    	<div class="jx_nr">
									    		<p>${question.explanation }</p>
									    		
									    	</div>
									    </div>
			                    		
			                    	</c:when>
			                    	<c:when test="${question.questionType=='trueOrFalse' }">
			                    		<div class="j1">
							               <span class="jx_wz">【答案】</span>
							                   
							               <div class=" jx_nr" >
							               	<p class="color1d9"> 
							               		${question.correctAnswer }
					                        </p>
										    
											</div>
										</div>
										<div class="j2" style="display: inline-block;">
									    	<span class="jx_wz">【解析】</span>
									    	<div class="jx_nr">
									    		<p>${question.explanation }</p>
									    		
									    	</div>
									    </div>
			                    		
			                    	</c:when>
			                    	<c:when test="${question.questionType=='blank' }">
			                    		<div class="j1">
							               <span class="jx_wz">【答案】</span>
							                   
							               <div class=" jx_nr" >
							               	<p class="color1d9"> 
							               		<c:forEach items="${question.correctAnswers }" var="correctAnswer">
					                        		${correctAnswer}<br>
					                        	</c:forEach>
					                        </p>
										    
											</div>
										</div>
										<div class="j2" style="display: inline-block;">
									    	<span class="jx_wz">【解析】</span>
									    	<div class="jx_nr">
									    		<p>${question.explanation }</p>
									    		
									    	</div>
									    </div>
			                    	  	
			                    	</c:when>
			                    	<c:when test="${question.questionType=='word' }">
			                    		<div class="j1">
							               <span class="jx_wz">【答案】</span>
							                   
							               <div class=" jx_nr" >
							               	<p class="color1d9"> 
							               		<c:forEach items="${question.correctAnswers }" var="correctAnswer">
					                        		${correctAnswer}
					                        	</c:forEach>
					                        </p>
										    
											</div>
										</div>
										<div class="j2" style="display: inline-block;">
									    	<span class="jx_wz">【解析】</span>
									    	<div class="jx_nr">
									    		<p>${question.explanation }</p>
									    		
									    	</div>
									    </div>
			                    		
			                    	</c:when>
			                    	<c:when test="${question.questionType=='complex' }">
			                    		<div class="j1">
							               <span class="jx_wz">【答案】</span>
							                   
							               <div class=" jx_nr" >
							               	<p class="color1d9"> 
							               		<c:forEach items="${question.childrenQuestionVo }" var="child" varStatus="status">
			                	 		 		（${status.index+1}）
				                	 		 	<c:forEach items="${child.correctAnswers }" var="correctAnswer">
					                        		 ${correctAnswer} 
					                        	</c:forEach> 
					                        	</br>
			                	 		 		</c:forEach>
					                        </p>
										    
											</div>
										</div>
										<div class="j2" style="display: inline-block;">
									    	<span class="jx_wz">【解析】</span>
									    	<div class="jx_nr">
									    		<p>
													<c:forEach items="${question.childrenQuestionVo }" var="child" varStatus="status">
					                        	（${status.index+1}）
					                        	<span class="color666">${child.explanation }</span>
					                        	</br>
					                        </c:forEach>
												</p>
									    		
									    	</div>
									    </div>
			                    		
			                    	</c:when>
			                    	<c:when test="${question.questionType=='cloze' }">
			                    		<div class="j1">
							               <span class="jx_wz">【答案】</span>
							                   
							               <div class=" jx_nr" >
							               	<p class="color1d9"> 
							               		<c:forEach items="${question.childrenQuestionVo }" var="child" varStatus="status">
				                	 		 	<c:forEach items="${child.correctAnswers }" var="correctAnswer">
					                        		 ${correctAnswer} 
					                        	</c:forEach> 
			                	 		 	</c:forEach>
					                        </p>
										    
											</div>
										</div>
										<div class="j2" style="display: inline-block;">
									    	<span class="jx_wz">【解析】</span>
									    	<div class="jx_nr">
									    		<p>
													<c:forEach items="${question.childrenQuestionVo }" var="child" varStatus="status">
					                        	（${status.index+1}）
					                        	<span class="color666">${child.explanation }</span>
					                        	</br>
					                        </c:forEach>
												</p>
									    		
									    	</div>
									    </div>
			                    		
			                    	</c:when>
			                  	</c:choose>
		                </div>
		                <div class="timu-handle">
		                    <ul>
		                        <li>
		                            <span><i class="difficulty"></i>难度：<b>${question.difficulityString }</b></span>
		                        </li>
		                        <li>
		                            <span><i class="update-time"></i>更新时间：<b><fmt:formatDate pattern="yyyy/MM/dd" value="${question.modifyDate}" /></b></span>
		                        </li>
		                        <li>
		                            <span ><i class="collect"></i>收藏(<b class="sblue">${question.favCount }</b>)</span>
		                        </li>
		                        <li>
		                            <span ><i class="use"></i>使用(<b class="sblue">${question.usedCount }</b>)</span>
		                        </li>
		                        <li class="fr">
		                            <a href="javascript:void(0)" data-subjectcode="${question.subjectCode}" data-uuid="${question.id}" data-score="${question.score }"
		                               onclick="remove_question(this);" class="btn-red">移出试题篮</a>
		                        </li>
		                    </ul>
		                </div>
		            </li>
	        </c:forEach>
	        </ul>
	    </div>
    </c:forEach>
</div>
<div class="noQuestion" style="display:none;">
	<a href="javascript:void(0)" class="btn-orange" onclick="back()"><i></i>返回找题</a>
	<p></p>
</div>
<script>
$(function(){
	//收缩 展开
		$('  ul.dati li .timu ').each(function(){
			
			var aa = $(this).find('.timu-type').text();
			if(aa=='复合题' || aa=='完形填空'){
				var title_content =  $(this).find('.manyTimuTitle').height();
		        if(title_content>192){
		            $(this).find('.manyTimuTitle').height('192');
		            $(this).find('p.zk_sq').show();
		            $(this).find('.zk').show();
		        }
			}
		});
	
	
})

  
$('.zk').click(function(event){
    $(this).hide();
    $(this).parent().prev('.manyTimuTitle').height('auto');
    $(this).next('.sq').show();
    event.stopPropagation();
});

$('.sq').click(function(event){
	$(this).parent().prev('.manyTimuTitle').height('192');
    $(this).hide();
    $(this).prev('.zk').show();
    event.stopPropagation();
});
</script>