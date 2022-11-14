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
    top: -56px;
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
<div class="no_resource" style="display: none;min-height: 618px;"></div>

<ul>
    <c:forEach items="${questionlist}" var="question">
        <li data-time="2017/09/13" data-sc="152" data-sy="18" class="tt" id="${question.id}">
            <div class="timu"  style="cursor: pointer;"  onclick="showQuestionDetail(${question.id},'${question.uuid}')">
            
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
                                    
                                     <li class="cloze-answer" style="min-height:28px;">
                                   	 <label style="display:inline-block;float: left;margin-top: 3px;">（${status.index+1}）</label>
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

            <div class="jiexi" onclick="showQuestionDetail(${question.id},'${question.uuid}')" style="cursor: pointer;">
                <div class="j1" style="min-height:32px">
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
			    <div class="j2" style="display: inline-block;">
			    	<span class="jx_wz">【解析】</span>
			    	<div class="jx_nr">
			    		<c:choose>
			    			<c:when test="${question.questionType eq 'cloze' or question.questionType eq 'complex'}">
			    				<c:forEach items="${question.childrenQuestionVo}" var="childrenQuestion" varStatus="status">
			    					<p>(${status.index+1})、${childrenQuestion.explanation }</p>
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
        <div class="cz_btn">
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
					<span class="grey_mj" style="position:relative"> 
					<btn class="btn btn-orange basket " 
						data-stage="${question.stageCode}"
						data-questionId="${question.id}"
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
            
        </div>
    </div>
    </li>
    </c:forEach>
</ul>

<div><a href="javascript:void(0)" class="gotop" onclick="goTop();" style="display: block;">顶部</a></div>
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

	
	
  
//收缩 展开
$(' ul li .timu .title').each(function(){
	var aa = $(this).find('.tm_type').html();
	
	if(aa=='复合题' || aa=='完形填空'){
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

$(function(){
	var zm = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
	$(".xxqq").each(function(){
		var index = $(this).data("a");
		$(this).text(zm[index]);
	});
});

/* layer.ready(function(){ //为了layer.ext.js加载完毕再执行
	var s=$("ul li").size();
	for(var i=0;i<s;i++){
		var id=$("ul li").eq(i).attr("id"); 		
	    layer.photos({
	    	shade:0.5,
	    	area:'auto',
	    	maxWidth:"800",
	    	shift : 5,//3.0之前版本设置动画的属性
	        photos: '#'+id
	    });
	   
	}
	}); */
</script>