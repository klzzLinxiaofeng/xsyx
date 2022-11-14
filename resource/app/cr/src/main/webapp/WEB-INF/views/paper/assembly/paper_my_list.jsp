<%@include file="/views/embedded/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="zjjl box-sizing">
    <p class="mc">我的试卷</p>
    <ul class="mgb20">
    	<c:if test="${size==0 }">
	      	<li class="no-notice last-border">
	      		<div>你还没有组过试卷哦~</div>
	        </li>
     	</c:if>
     <c:if test="${size>0 }">
     	<c:forEach items="${result }" var="paper">
          <li>
              <div class="info fl">
                  <p class="title">${paper.title }</p>
                  <ul>
                      <li>
                          <span class="mgr20"><i class="update-time"></i>创建时间：<b><fmt:formatDate pattern="yyyy/MM/dd HH:mm" value="${paper.createDate}" /></b></span>
                      </li>
                      <li>
                          <span class="mgr20"><i class="update-time"></i>更新时间：<b><fmt:formatDate pattern="yyyy/MM/dd HH:mm" value="${paper.modifyDate}" /></b></span>
                      </li>
                      <li>
                          <span class="mgr20"><i class="collect"></i>收藏(<b class="blue">${paper.favCount }</b>)</span>
                      </li>
                      <li>
                          <span class="mgr20"><i class="use"></i>使用(<b class="blue">${paper.usedCount }</b>)</span>
                      </li>
                  </ul>
              </div>
              <p class="cz fr">
                  <a href="javascript:void(0)" class="btn-blue" onclick="editPaper(${paper.id}, ${paper.hasTask },1)">编辑</a>
                  <a href="javascript:void(0)" class="btn-red" onclick="delPaper(${paper.id}, ${paper.hasTask })">删除</a>
              </p>
              <div class="clear"></div>
          </li>
         </c:forEach>
        </c:if>
    </ul>
</div>