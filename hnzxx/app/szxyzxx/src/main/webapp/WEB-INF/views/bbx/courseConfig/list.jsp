<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%-- <link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet"> --%>
<style>
.biao-ul .item-right {
    margin-left: 150px;
}
</style>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<div class="biao-ul" >
		<ul id="ul">
		<c:forEach items="${items}" var="item">
			<li id="${item.id}_li">
				<div class="item">
					<div class="item-left">
						<span class="sign ban-acitve">${item.gradeName}</span>
					</div>
					<div class="item-right">
						<h4><strong>${item.courseArrStr}</strong></h4>
						<p class="texta"><b>选科时间：</b><fmt:formatDate value="${item.signupStartDate}" pattern="yyyy年MM月dd日" /> - 
							<fmt:formatDate value="${item.signupFinishDate}" pattern="yyyy年MM月dd日" /></p>
						<p class="texta"><b>科目总数：</b>${item.totalElectiveCount}</p>
						<p class="texta"><b>学生可选科目数：</b>${item.allowedElectiveCount}</p>
						<p class="texta"><b>开放选课组合：</b>${item.coursesCombinationStr}</p>
					</div>
				</div>
				<c:if test="${item.status == 0}">
					<div class="item-bottom"><span><t:showTime createTime="${item.createDate }"></t:showTime></span>
						<a href="javascript:void(0)"  onclick="deleteObj(this, '${item.id}');"><i class="fa fa-trash-o" style="font-size:16px;"></i> &nbsp;删除</a>
						<%-- <a href="javascript:void(0)" onclick="loadEditPage('${item.id}');"><i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;编辑</a> --%>
						<a href="javascript:void(0)" onclick="startCourseConfig('${item.id}');"><i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;开展</a>
						<a href="javascript:void(0)" onclick="loadViewerPage('${item.id}');"><i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;查看</a>
						<c:if test="${item.isLimited == true}">
							<a href="javascript:void(0)" onclick="setLimitedNumPage('${item.id}');">
								<i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;设置选科人数上限</a>
						</c:if>
					</div>
					<em  class="label label-star"></em>
				</c:if>
				<c:if test="${item.status == 1}">
					<c:if test="${item.timeFlag == '1'}">
						<div class="item-bottom"><span><t:showTime createTime="${item.createDate }"></t:showTime></span>
							<a href="javascript:void(0)"  onclick="deleteObj(this, '${item.id}');"><i class="fa fa-trash-o" style="font-size:16px;"></i> &nbsp;删除</a>
							<%-- <a href="javascript:void(0)" onclick="loadViewerPage('${item.id}');"><i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;查看</a> --%>
							<a href="javascript:void(0)" onclick="loadEditPage('${item.id}');"><i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;编辑</a>
							<a href="javascript:void(0)" onclick="stopCourseConfig('${item.id}');"><i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;停止选课</a>
							<c:if test="${item.isLimited == true}">
								<a href="javascript:void(0)" onclick="setLimitedNumPage('${item.id}');">
									<i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;设置选科人数上限</a>
							</c:if>
						</div>
						<em  class="label label-star"></em>
					</c:if>
					<c:if test="${item.timeFlag == '2'}">
						<div class="item-bottom"><span><t:showTime createTime="${item.createDate }"></t:showTime></span>
							<a href="javascript:void(0)"  onclick="deleteObj(this, '${item.id}');"><i class="fa fa-trash-o" style="font-size:16px;"></i> &nbsp;删除</a>
							<%-- <a href="javascript:void(0)" onclick="loadViewerPage('${item.id}');"><i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;查看</a> --%>							
							<a href="javascript:void(0)" onclick="loadStatisticsCourseStudentPage('${item.id}');"><i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;科目组选科人数</a>
							<a href="javascript:void(0)" onclick="loadNotSignUpStudentPage('${item.id}');"><i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;未选科名单</a>
							<a href="javascript:void(0)" onclick="loadStatisticsCourseGroupStudentPage('${item.id}');"><i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;选科名单</a>
							<a href="javascript:void(0)" onclick="publishedResults('${item.id}');"><i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;公布选课结果</a>
						</div>
						<em  class="label label-end"></em>		
					</c:if>
					<c:if test="${item.timeFlag == '3'}">
						<div class="item-bottom"><span><t:showTime createTime="${item.createDate }"></t:showTime></span>
							<a href="javascript:void(0)"  onclick="deleteObj(this, '${item.id}');"><i class="fa fa-trash-o" style="font-size:16px;"></i> &nbsp;删除</a>
							<%-- <a href="javascript:void(0)" onclick="loadViewerPage('${item.id}');"><i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;查看</a> --%>
							<a href="javascript:void(0)" onclick="loadStatisticsCourseStudentPage('${item.id}');"><i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;科目组选科人数</a>
							<a href="javascript:void(0)" onclick="loadNotSignUpStudentPage('${item.id}');"><i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;未选科名单</a>
							<a href="javascript:void(0)" onclick="loadStatisticsCourseGroupStudentPage('${item.id}');"><i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;选科名单</a>
							<c:if test="${item.isLimited == true}">
								<a href="javascript:void(0)" onclick="setLimitedNumPage('${item.id}');">
									<i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;设置选科人数上限</a>
							</c:if>
						</div>
						<em  class="label label-ing"></em>				
					</c:if>
				</c:if>
				<c:if test="${item.status == 2}">
					<div class="item-bottom"><span><t:showTime createTime="${item.createDate }"></t:showTime></span>
						<a href="javascript:void(0)"  onclick="deleteObj(this, '${item.id}');"><i class="fa fa-trash-o" style="font-size:16px;"></i> &nbsp;删除</a>
						<%-- <a href="javascript:void(0)" onclick="loadViewerPage('${item.id}');"><i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;查看</a>	 --%>						
						<a href="javascript:void(0)" onclick="loadStatisticsCourseStudentPage('${item.id}');"><i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;科目组选科人数</a>
						<a href="javascript:void(0)" onclick="loadNotSignUpStudentPage('${item.id}');"><i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;未选科名单</a>
						<a href="javascript:void(0)" onclick="loadStatisticsCourseGroupStudentPage('${item.id}');"><i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;选科名单</a>
						<a href="javascript:void(0)" ><i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;已公布选课结果</a>
					</div>
					<em  class="label label-end"></em>
				</c:if>
			</li>
		</c:forEach>
	</ul>
</div>
