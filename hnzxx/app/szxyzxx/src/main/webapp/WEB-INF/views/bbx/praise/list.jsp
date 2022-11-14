<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<input type="hidden" id="currentPage" name="currentPage"
	value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages"
	value="${page.totalPages}" />
<div class="biao-title widget-container white" style="border:1px solid #fff">
	<span>全班：<em>${studentCount}</em> 人
	</span> <span>表扬次数：<em>${praiseCount}</em> 人次
	</span> <span>表扬最多的学生：
	<c:forEach items="${praiseStars}" var="praiseStar">
	<em class="nan">${praiseStar.name}</em><em>(${praiseStar.count})</em>
	</c:forEach>
	</span>
</div>
<div class="biao-ul">
	<ul>
		<c:forEach items="${items}" var="item">
		<li id="li_${item.id }">
			<div class="item">
				<div class="item-left">
					<span class="sign">表扬栏</span>
					<div class="stu-head">
						<c:forEach items="${item.students}" var="userInfo">
						<c:choose>
							<c:when test="${empty userInfo.iconUrl}">
								<img src="${ctp}/res/images/no_pic.jpg" title="${userInfo.name}" style="cursor: pointer;" alt="${userInfo.name}"/> 
							</c:when>
							<c:otherwise>
								<img src="${userInfo.iconUrl}" title="${userInfo.name}" style="cursor: pointer;" alt="${userInfo.name}"/>
							</c:otherwise>
						</c:choose>
<%-- 						<img src="${empty userInfo.iconUrl?pageContext.request.contextPath:userInfo.iconUrl}${empty userInfo.iconUrl?'/res/images/w100.jpg':''}" title="${userInfo.name}" style="cursor: pointer;" alt="${userInfo.name}"/> --%>
						</c:forEach>
					</div>
				</div>
				<div class="item-right">
					<h4>
					<c:if test="${!empty item.medalId}">
						<i>
						<c:choose>
									<c:when test="${item.medalId==1}">
									<c:set var="suffix" value="a"></c:set>
									<c:set var="rank" value="德"></c:set>
									</c:when>
									<c:when test="${item.medalId==2}">
									<c:set var="suffix" value="e"></c:set>
									<c:set var="rank" value="智"></c:set>
									</c:when>
									<c:when test="${item.medalId==3}">
									<c:set var="suffix" value="c"></c:set>
									<c:set var="rank" value="美"></c:set>
									</c:when>
									<c:when test="${item.medalId==4}">
									<c:set var="suffix" value="d"></c:set>
									<c:set var="rank" value="体"></c:set>
									</c:when>
									<c:when test="${item.medalId==5}">
									<c:set var="suffix" value="b"></c:set>
									<c:set var="rank" value="劳"></c:set>
									</c:when>
									<%-- <c:when test="${item.medalId==6}">
									<c:set var="suffix" value="f"></c:set>
									</c:when> --%>
						</c:choose>
						<c:choose>
						   <c:when test="${item.medalId==0 || item.medalId==-1 }"></i></c:when>
						   <c:otherwise>
						      <img style="width: 50px;" src="${pageContext.request.contextPath}/res/images/ban_start${suffix}.png"/>						
								</i><strong>${rank}</strong>—
						   </c:otherwise>
						</c:choose>
						</c:if>
						${item.receiverName}
					</h4>
					<p>${item.content}</p>
				</div>
			</div>
			<div class="item-bottom">
				<span>${item.nickTime}</span>
				<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
				<a onclick="deleteObj(this, '${item.id}');" href="javascript:void(0);"><i class="fa fa-trash-o" style="font-size:16px;"></i> &nbsp;删除</a>
				</c:if>
				<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
				<a onclick="loadEditPage('${item.id}');" href="javascript:void(0);"><i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;编辑</a>
                </c:if>
			</div>
		</li>
		</c:forEach>
		<%-- <li>
			<div class="item">
				<div class="item-left">
					<span class="sign">表扬栏</span>
					<div class="stu-head">
						<img src="${pageContext.request.contextPath}/res/images/w100.jpg" />
					</div>
				</div>
				<div class="item-right">
					<h4>
						<i class="star"></i><strong>每周之星</strong>—罗志明
					</h4>
					<p>你在本周表现优异，上课认真听讲，是同学们的好榜样，戒骄戒躁，在以后的日子里继续坚持。</p>
				</div>
			</div>
			<div class="item-bottom">
				<span>2小时前</span><a href="./icons/pencil-square-o"><i
					class="fa fa-pencil-square-o"></i>编辑</a><a href="./icons/trash-o"><i
					class="fa fa-trash-o"></i> 删除</a>
			</div>
		</li> --%>
	</ul>
</div>
