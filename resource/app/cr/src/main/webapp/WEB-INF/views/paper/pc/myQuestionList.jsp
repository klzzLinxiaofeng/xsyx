<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@include file="/views/embedded/taglib.jsp"%>

<input type="hidden" id="currentPage" name="currentPage"
       value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages"
       value="${page.totalPages}" />
<input type="hidden" id="total" name="total"
       value="${page.totalRows}" />

<div class="no_resource" style="display: none"></div>

<ul style="padding-top: 3px;">
    <c:forEach items="${questionlist}" var="question">
        <li data-time="2017/09/13" data-sc="152" data-sy="18" class="yy">
            <div class="timu"  style="cursor: pointer;" onclick="showQuestionDetail(${question.id},'${question.uuid}')">
            
            	<c:choose>
            		<c:when test="${question.questionType eq 'cloze' or question.questionType eq 'complex'}">
						<div class="title">
							<div class="title-content" >
								<span class="tm_type">${question.questionTypeString}</span>
								${question.content} 
							</div>
						</div>
						<p class="zk_sq" style="display:none;">
							<b class="zk" style="display: none">展开<i class="i1"></i></b> 
							<b class="sq"style="display: none">收起<i class="i2"></i></b>
						</p>
					</c:when>
            		<c:otherwise>
            			<div class="title">
							<div class="title-content">
								<span class="tm_type">${question.questionTypeString}</span>
								${question.content}
							</div>
						</div>
            		</c:otherwise>
            	</c:choose>
            

                
               <div class="timu-choose">

                    <ul>
                        <c:choose>
                            <c:when test="${question.questionType eq 'trueOrFalse'}">
                                	<c:forEach items="${question.answers}" var="answer">
                                   		 <li><span class="xxqq"></span>${answer}</li>
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
                           <c:when test="${question.questionType eq 'cloze'}">
                            	<c:forEach items="${question.childrenQuestionVo}" var="item"
                                           varStatus="status">
                                    
                                     <li class="cloze-answer">
                                   	 <label style="display:inline-block;float: left;">（${status.index+1}）</label>
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

                            <c:otherwise>
                                <c:forEach items="${question.answers}" var="answer" varStatus="status1">
		                               <li>
		                                   <span class="xxqq" data-a="${status1.index}"></span>
		                                   		${answer}
		                                </li>
		                        </c:forEach>
                            </c:otherwise>

                        </c:choose>
                    </ul>
                </div>

            </div>


            <div class="jiexi" onclick="showQuestionDetail(${question.id},'${question.uuid}')" style="display:block;">
                <div class="j1">
	                <span class="jx_wz">【答案】</span>
	                    
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
						                	<div style="margin-left: 45px;">
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
				<div class="clear"></div>
			    <div class="j2" style="display: inline-block;">
			    	<span class="jx_wz">【解析】</span>
			    	<div class="jx_nr">
			    		<c:choose>
			    			<c:when test="${question.questionType eq 'cloze' or question.questionType eq 'complex'}">
			    				<c:forEach items="${question.childrenQuestionVo}" var="childrenQuestion" varStatus="status">
			    					(${status.index+1})、${childrenQuestion.explanation }
			    				</c:forEach>
			    			</c:when>
			    			<c:otherwise>
			    				<p>${question.explanation }</p>
			    			</c:otherwise>
			    		</c:choose>
			    	</div>
			    </div>
		    </div>
    <div class="detail">
        <p class="nd">
            <b class=""></b>难度：${question.difficulityString}
        </p>
        <p class="gx">
            <b class=""></b>更新时间：<fmt:formatDate
                value="${question.modifyDate}" pattern="yyyy/MM/dd" />
        </p>
        <p class="sc">
            <b class=""></b>收藏（<span>${question.favCount}</span>）
        </p>
        <p class="sy">
            <b class=""></b>使用（<span>${question.usedCount}</span>）
        </p>
        <div class="cz_btn" style="top:10px;">
            <c:choose>
                <c:when test="${question.isFav}">
                    <btn class="btn btn-lightGray" onclick="fav(${question.id},this)"
                         fav="${!question.isFav}">取消收藏</btn>
                </c:when>
                <c:otherwise>
                    <btn class="btn btn-blue" onclick="fav(${question.id},this)"
                         fav="${!question.isFav}">收藏</btn>
                </c:otherwise>
            </c:choose>
            
        </div>
    </div>
    </li>
    </c:forEach>
    <ul>

<script type="text/javascript">
//无资源
$(function() {
    var total = $("#total").val();
    if(total==0) {
        $(".no_resource").show();
        $(".page").hide();
    } else {
        $(".no_resource").hide();
        $(".page").show();
    }
    
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

    
//收縮 展開
$(' ul li .timu .title').each(function(){
	var aa = $(this).find('.tm_type').html();
	
	if(aa=='复合题' || aa=='完型填空'){
		var title_content =  $(this).find('.title-content').height();
        if(title_content>103){
            $(this).find('.title-content').height('103');
            $(this).next('p.zk_sq').show();
            $(this).next().find('.zk').show();
        }
	}
});
  
$('.zk').click(function(event){
    $(this).hide();
    $(this).parent().prev().find('.title-content').height('auto');
    $(this).next('.sq').show();
    event.stopPropagation();
});

$('.sq').click(function(event){
	$(this).parent().prev().find('.title-content').height('103');
    $(this).hide();
    $(this).prev('.zk').show();
    event.stopPropagation();
});     

$(function(){
	var zm = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
	$(".xxqq").each(function(){
		var index = $(this).data("a");
		$(this).text(zm[index]);
	});
});
    
    
})


</script>