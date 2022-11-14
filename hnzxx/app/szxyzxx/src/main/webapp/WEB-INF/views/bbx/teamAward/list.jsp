<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<input type="hidden" id="currentPage" name="currentPage"
	value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages"
	value="${page.totalPages}" />

<div>
	<div class="span12">
		<div class="content-widgets white"
			style="border: 1px solid #fff; margin-bottom: 20px;">
			<div class="content-widgets">
				<img
					src="${pageContext.request.contextPath}/res/css/bbx/images/icon.jpg"
					class="zp">
				<p>
					获得荣誉：<span id="totalNum" class="a1"
						style="font-size: 18px; font-family: '微软雅黑'; color: #62BE4F">${ totalNum}</span>
					个
				</p>
			</div>
		</div>
	</div>
</div>

<div class="biao-ul" style="margin-top: 3px; padding-top: 66px;">
	<ul>
		<c:choose>
			<c:when test="${items.size()>0 }">
				<c:forEach items="${items}" var="item">
					<li id="li_${item.id }">
						<div class="item">
							<div class="item-left left">
								<span class="sign ban-rong">班级荣誉</span>
							</div>
							<div class="photo-a left">
								<%-- <img src="${ctp }/res/images/w200.png" /> --%>
								<c:choose>
								  <c:when test="${item.awardImageURL !=null && item.awardImageURL !=''}">
								      <img src="${item.awardImageURL }" alt="img" />
								  </c:when>
								  <c:otherwise>
								  		<div class="jiangzhuang" style="position:relative;">
								        	<img src="${ctp }/res/css/bbx/images/bbx_rongyu.png" alt="img" />
								        	<p class="p1" style="position:absolute;text-align:center;left:16px;width:168px;height:40px;overflow:hidden;top:58px;color:#69521E;font-family:'STXingkai';font-weight:bold;font-size:18px;padding-top:0">${item.name }</p>
								        	<p class="p2" style="position:absolute;font-size:10px;-webkit-transform:scale(0.8);padding-top:0;top:97px;right:27px;"><fmt:formatDate pattern="yyyy年MM月dd日   " value="${item.awardTime }"></fmt:formatDate></p>
								        </div>
								  </c:otherwise>
								</c:choose>
								 <%-- <c:if
									test="${item.awardImageURL !=null && item.awardImageURL !=''}">

									<img src="${item.awardImageURL }" alt="img" />

								</c:if>  --%>
							</div>
							<div class="item-right">
								<h4>
									<strong>${item.name }</strong>
								</h4>
								<p class="texta">
									获得时间：
									<fmt:formatDate pattern="yyyy年MM月dd日   "
										value="${item.awardTime }"></fmt:formatDate>
								</p>

							</div>
						</div>
						<div class="item-bottom">
							<span>${item.bbx_createDate } <%-- <t:showTime createTime="${item.createDate }"></t:showTime> --%>
							</span> 
							<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
							<a href="javascript:void(0)" onclick="deleteObj(this,'${item.id}')"> 
								<i class="fa fa-trash-o" style="font-size: 16px;"></i> &nbsp;删除
							</a>
							</c:if>
							<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
							<a href="javascript:void(0)" onclick="loadEditPage('${item.id}')"> 
								<i class="fa fa-pencil-square-o" style="font-size: 16px;"></i> &nbsp;编辑
							</a> 
							</c:if>
						</div>
					</li>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<li>
					<div class="empty">
						<p class="empty_text">暂无相关数据</p>
					</div>
				</li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>
<%--  <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
	<jsp:param name="id" value="teamAward_list_content" />
	<jsp:param name="url"
		value="/bbx/teamAward/index?sub=list&dm=${param.dm}" />
	<jsp:param name="pageSize" value="${page.pageSize}" />
</jsp:include>
<div class="clear"></div>  --%>
