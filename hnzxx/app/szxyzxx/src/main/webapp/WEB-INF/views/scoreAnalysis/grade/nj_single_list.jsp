<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="detail">
					<div class="all_km mokuai mgb12">
						<ul>
							<c:forEach items="${userSubjectList}" var="userSubject" varStatus="status">
								<c:choose>
									<c:when test="${status.index == 0}">
										<li class="on" code="${userSubject.subjectCode }">${userSubject.subjectName }</li>
									</c:when>
									<c:otherwise>
										<li code="${userSubject.subjectCode }">${userSubject.subjectName }</li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							
						</ul>
					</div>
					<div id="content">
						
					</div>
				</div>
		<script>
			$(function(){
				var subjectCode = $(".all_km ul li.on").attr("code");
				var loader=layer.load();
				var index = '${index}';
				var url = "";
				if(index == 1){
					url = "/scoreAnalysis/WeChat/loadSingleSubjectData/${examWorksId}/${schoolId}/${userId}/";
				}else{
					url = "/scoreAnalysis/WeChat/loadAnalysisData/${examWorksId}/${schoolId}/${userId}/${studentId}/";
				}
				$("#content").load(url + subjectCode,function(){
					layer.close(loader);
				});
				$('.xzfw span').click(function(){
					$(this).addClass('on');
					$(this).siblings().removeClass('on');
					var i = $(this).index();
					$('.big_detail').find('.detail').hide();
					$('.big_detail').find('.detail').eq(i).show();
					
				});
				$('.all_km ul li').click(function(){
					var subjectCode = $(this).attr("code");
					$(this).addClass('on');
					$(this).siblings().removeClass('on');
					$("#content").empty();
					var loader=layer.load();
					$("#content").load(url + subjectCode,function(){
						layer.close(loader);
					});
				});
			})
		</script>
	</body>
</html>
