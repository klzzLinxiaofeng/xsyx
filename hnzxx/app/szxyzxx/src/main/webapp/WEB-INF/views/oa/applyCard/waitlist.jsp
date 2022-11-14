<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<table>
<tr>
	<td style="padding: 0; border: 0 none;"><input type="hidden"
		id="currentPage" name="currentPage" value="${page.currentPage}" /> <input
		type="hidden" id="totalPages" name="totalPages"
		value="${page.totalPages}" /></td>
</tr>
</table>
<div class="entry" id="entry">
							<ul id="allMenuId">
									<li class="on entry_li" style="border:0 none;padding:0"><a href="javascript:void(0)" onclick="depentmentSeach('all',this)">全部申请（${empty tatolCount ? '0' : tatolCount}）</a></li>
									<c:forEach items="${dList}" var="list" varStatus="i">
									<input type="hidden" value="${dList.size()}" id="dListLength"/>
										<li class="entry_li" style="border:0 none;padding:0"><a href="javascript:void(0)" onclick="depentmentSeach('${list.id}',this)">${list.name}
										（
											<span class="people_num${i.index}">
											<c:forEach items="${numList}" var="num">
												<c:if test="${num.departmentId==list.id}">${num.number}</c:if>
											</c:forEach>
											</span>
										）
										</a></li>
									</c:forEach>
							</ul>
							<div class="clear"></div>
						</div>
<c:if test="${items.size()<=0}">
<div class="empty">
	<p>搜索结果为空</p>
</div>
</c:if>
<ul>
	<c:forEach items="${items}" var="list">
		<div class="shenhe" id="${list.id}" style="display:none">
		<div><a href="javascript:void(0)" class="close_div"></a></div>
		<div class="s_top">
			<img src="<avatar:avatar userId='${list.proposer}' ></avatar:avatar>"></img>
			<p>${list.proposerName}的用车申请</p>
		</div>
		<div class="s_two">
			<div class="p2">${list.title}<span>
			<c:if test="${list.auditStatus==0}"><span>[待审批]</span></c:if>
			<c:if test="${list.auditStatus==4}"><span>[审批不通过]</span></c:if>
			<c:if test="${list.auditStatus==1}"><span style="color: green;">[审批通过]</span></c:if></span></div>
			<div class="p3"><i class="fa fa-truck"></i><p class="p_div">申请车辆</p><span>${list.cardName}【${list.plateNumber}】</span></div>
			<div class="p3"><i class="fa fa-user"></i><p class="p_div">申请人</p><span>${list.proposerName}</span></div>
			<div class="p3"><i class="fa  fa-phone"></i><p class="p_div">联系电话</p><span>${list.phone}</span></div>
			<div class="p3"><i class="fa fa-clock-o"></i><p class="p_div">使用时间</p>
			<span>
				<fmt:formatDate value="${list.beginDate}" pattern="yyyy年MM月dd日"/>
											-
				<fmt:formatDate value="${list.endDate}" pattern="yyyy年MM月dd日" />
				 共<b>${list.tolDay}</b>天
			</span></div>
		</div>
		<div class="s_three">
			<div class="control-group">
				<label class="control-label">审核：</label>
				<div class="controls pz">
					<button class="btn" onclick="pass('${list.id}');">通过</button>
					<button class="btn" onclick="noPass('${list.id}');">不通过</button>
					<input type="hidden" id="status${list.id}"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">备注：</label>
				<div class="controls">
					<textarea style="width:98%;height:38px;margin:0;"></textarea>
				</div>
			</div>
		</div>
		<div class="s_four">
			<a href="javascript:void(0)" class="btn-success" onclick="submit('${list.id}');">提交</a>
			<a href="javascript:void(0)" class="cancel">取消</a>
		</div>
	</div>
	<div class="zhezhao" id="zhezhao${list.id}" style="display:none"></div>
	
		<li>
			<div class="touxiang"> 
				<img src="<avatar:avatar userId='${list.proposer}' ></avatar:avatar>"></img>
			</div>
			<div class="detail">
				<div class="p1"><span>${list.proposerName}</span>的申请</div>
				<div class="p2">${list.title}
				<c:if test="${list.auditStatus==0}"><span>[待审批]</span></c:if>
				<c:if test="${list.auditStatus==4}"><span style="color: red;">[审批不通过]</span></c:if>
				<c:if test="${list.auditStatus==1}"><span style="color: green;">[审批通过]</span></c:if>
				</div>
				<div class="p3"><i class="fa fa-truck"></i><p class="p_div">申请车辆</p><span>${list.cardName}【${list.plateNumber}】</span></div>
				<div class="p3"><i class="fa fa-user"></i><p class="p_div">申请人</p><span>${list.proposerName}</span></div>
				<div class="p3"><i class="fa  fa-phone"></i><p class="p_div">联系电话</p><span>${list.phone}</span></div>
				<div class="p3"><i class="fa fa-clock-o"></i><p class="p_div">使用时间</p>
				<span>
					<fmt:formatDate value="${list.beginDate}" pattern="yyyy年MM月dd日"/>
					-
					<fmt:formatDate value="${list.endDate}" pattern="yyyy年MM月dd日" />
					 共<b>${list.tolDay}</b>天
				</span></div>
				<div class="p3"><i class="fa fa-eye"></i><p class="p_div">审核人</p>
					<span>
						<c:if test="${list.auditName==''||list.auditName==null}">
							暂时没审核哦
						</c:if>
						<c:if test="${list.auditName!='' && list.auditName!=null}">
							${list.auditName}
						</c:if>
					</span>
				</div>
				<div class="p3"><i class="fa fa-file-text-o"></i><p class="p_div">审核结果</p>
					<span>
						<c:if test="${list.auditStatus==0}">待审批</c:if>
						<c:if test="${list.auditStatus==1}">审批通过</c:if>
						<c:if test="${list.auditStatus==4}">审批不通过</c:if>
					</span>
				</div>
				<c:if test="${list.auditStatus==1}">
				<div class="p3">
					<i class="fa fa-eye"></i><p class="p_div">备注</p>
						<span>${list.remark}</span>
				</div>
				</c:if>
				<div class="p4"><fmt:formatDate value="${list.createDate}" pattern="yyyy年MM月dd日  HH : mm : ss"/></div>
			</div>
			<div class="caozuo" style="width:60px;">
			<c:if test="${list.auditStatus==0}">
				<button class="btn btn-warning" onclick="shenhe('${list.id}')">审批</button>
			</c:if>
			</div>
			<div class="clear"></div>
		</li>
	</c:forEach>
</ul>
<script type="text/javascript">
$(function(){
	addData();
})
function addData(){
	var dListLength = $("#dListLength").val();
	dListLength = dListLength + 0;
	for(var j=0;j<dListLength;j++){
		if($.trim($(".people_num"+j).text())=='' || $.trim($(".people_num"+j).text())=='0'){
			$(".people_num"+j).text('0');
		}
	}
}
</script>

