<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<table>
	<tr>
		<td style="padding: 0; border: 0 none;"><input type="hidden"
			id="currentPage" name="currentPage" value="${page.currentPage}" /> <input
			type="hidden" id="totalPages" name="totalPages"
			value="${page.totalPages}" /></td>
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</tr>
</table>
<div class="entry" id="entry">
	<ul id="allMenuId">
		<li class="entry_li on" style="border: 0 none; padding: 0"><a
			href="javascript:void(0)" onclick="depentmentSeach('all',this)">全部请假（${empty totalCount ? '0' : totalCount}）</a></li>
<%--		<c:forEach items="${dList}" var="list" varStatus="i">--%>
<%--			<input type="hidden" id="departId" />--%>
<%--			<input type="hidden" value="${dList.size()}" id="dListLength" />--%>
<%--			<li class="entry_li li_${list.id}" style="border: 0 none; padding: 0"><a--%>
<%--				href="javascript:void(0)"--%>
<%--				onclick="depentmentSeach('${list.id}',this)">${list.name} （ <span--%>
<%--					class="people_num${i.index}"> <c:forEach items="${numList}"--%>
<%--							var="num">--%>
<%--							<c:if test="${num.departmentId==list.id}">${num.count}</c:if>--%>
<%--						</c:forEach>--%>
<%--				</span> ）--%>
<%--			</a></li>--%>
<%--		</c:forEach>--%>
	</ul>
	<div class="clear"></div>
</div>
<c:if test="${items.size()<=0}">
	<div class="empty">
		<p>搜索结果为空</p>
	</div>
</c:if>
<ul>
	<c:forEach items="${items}" var="item" varStatus="i">
	
	    <div class="shenhe" style="display:none" id="${item.id }">
		<div><a href="javascript:void(0)" class="close_div"></a></div>
		<div class="s_top" style="text-align:center;border-bottom:0 none">
			<p style="width:100%;padding:0;">教师请假条</p>
		</div>
		<div class="s_two" style="position:relative;">
			<img src="<avatar:avatar userId='${item.propserId}' ></avatar:avatar>" style="position:absolute;left:0px;top:17px;width:50px;height:50px;">
			<div class="p1"><span>${item.propserName }</span>的申请</div>
			<div class="p2">${item.title }</div>
			<div class="p5" style="word-wrap:break-word;">${item.detail }</div>
			<div class="p3"><i class="fa fa-clock-o"></i><p class="p_div">请假时间</p>
			<span>${item.startDate}
											-
				${item.endDate}
		                       共<b>${item.daySum}</b>天
		    </span></div>
			<div class="p3"><i class="fa fa-user"></i><p class="p_div">代课老师</p><span>${item.daikeName }</span></div>
			<input type="hidden" value="${item.propserDepartmentId }" id="propserDepartmentId" />
			<div class="p3" style="position:absolute;top:17px;right:0;"><i class="fa  fa-sitemap"></i><p class="p_div">所在部门</p><span>${item.propserDepartmentName }</span></div>
		</div>
		<div class="s_three">
			<div class="control-group">
				<label class="control-label">审核：</label>
				<div class="controls pz" >
					<button class="btn" onclick="pass('${item.id}');">通过</button>
					<button class="btn" onclick="noPass('${item.id}');">不通过</button>
					<input type="hidden" id="status${item.id}"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">备注：</label>
				<div class="controls">
					<textarea style="width:98%;height:38px;margin:0;" class="left_red {maxlength:120}" id="approveRemark"></textarea>
				</div>
			</div>
		</div>
		<div class="s_four">
			<a href="javascript:void(0)" class="btn-success" onclick="submit('${item.id}');">提交</a>
			<a href="javascript:void(0)" class="cancel">取消</a>
		</div>
	</div>
	<div class="zhezhao" style="display:none" id="zhezhao${item.id }"></div>
	
		<li >
			<div class="touxiang">
				<img
					src="<avatar:avatar userId='${item.propserId}' ></avatar:avatar>">
			</div>
			<div class="detail">
				<c:if test="${not empty item.propserName }">
					<div class="p1">
						<span>${item.propserName }</span>发出的申请
					</div>
				</c:if>
				<div class="p2">
					${item.title }
					<c:choose>
						<c:when test="${item.auditStatus==0 }">
							<span>[待审批]</span>
						</c:when>
						<c:otherwise>
							<span class="yichuli">[审批 <c:if
									test="${item.approveState!=null ||item.approveState!=''  }">
									<c:choose>
										<c:when test="${item.approveState==0 }">通过</c:when>
										<c:otherwise><span style="color:red">未通过</span></c:otherwise>
									</c:choose>
								</c:if>]
							</span>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="p5" style="word-wrap:break-word;">${item.detail }</div>
				<div class="p3"> <i class="fa fa-user"></i>
				<p class="p_div">申请人</p>
				<span>${item.propserName }</span>
			  </div> 
			<c:if test="${item.propserDepartmentId!=null }">
				<div class="p3">
					<i class="fa  fa-sitemap"></i>
					<p class="p_div">所在部门</p>
					<span>${item.propserDepartmentName }</span>
				</div>
			</c:if>
			<div class="p3">
				<i class="fa fa-clock-o"></i>
				<p class="p_div">请假时间</p>
				<span>${item.startDate } - ${item.endDate }共<b>${item.daySum }</b>天
				</span>
			</div> <c:choose>
				<c:when test="${item.auditStatus==0 }">
					<div class="p3">
						<i class="fa fa-eye"></i>
						<p class="p_div">审核人</p>
						<span>还没进行审核</span>
					</div>
					<div class="p3">
						<i class="fa fa-file-text-o"></i>
						<p class="p_div">审核结果</p>
						<span>待审批</span>
					</div>
				</c:when>
				<c:otherwise>
					<div class="p3">
						<i class="fa fa-eye"></i>
						<p class="p_div">审核人</p>
						<span>${item.approveName }</span>
					</div>
					<div class="p3">
						<i class="fa fa-file-text-o"></i>
						<p class="p_div">审核结果</p>
						<span>审批 
									<c:choose>
										<c:when test="${item.approveState==0 }">通过</c:when>
										<c:otherwise>未通过</c:otherwise>
									</c:choose></span>
					</div>
				</c:otherwise>
			</c:choose>

			<div class="p4">
				<fmt:formatDate pattern="yyyy年MM月dd日  HH:mm:ss "
					value="${item.createDate }"></fmt:formatDate>
			</div>
			</div>
			<div class="caozuo" style="width:60px;">
			<c:if test="${item.auditStatus==0}">
			    <c:if test="${item.isApprove==true }">
				<button class="btn btn-warning" onclick="shenhe('${item.id}')">审批</button>
				</c:if>
			</c:if>
			</div>
			<div class="clear"></div>
		</li>
	</c:forEach>
</ul>

<script type="text/javascript">
	$(function() {
		addData();
	})
	function addData() {
		var dListLength = $("#dListLength").val();
		dListLength = dListLength + 0;
		for (var j = 0; j < dListLength; j++) {
			if ($.trim($(".people_num" + j).text()) == ''
					|| $.trim($(".people_num" + j).text()) == '0') {
				$(".people_num" + j).text('0');
			}
		}
	}

	
</script>
