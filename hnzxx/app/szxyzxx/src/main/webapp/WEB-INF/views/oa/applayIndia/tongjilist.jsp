<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
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
		<li class="entry_li on" style="border: 0 none; padding: 0"><a 
			href="javascript:void(0)" onclick="depentmentSeach('all',this)">全部文印（${empty totalCount ? '0' : totalCount}）</a></li>
<%--		<c:forEach items="${dList}" var="list" varStatus="i">--%>
<%--			<input type="hidden" id="departId" />--%>
<%--			<input type="hidden" value="${dList.size()}" id="dListLength" />--%>
<%--			<li class="entry_li li_${list.id}" style="border: 0 none; padding: 0"><a --%>
<%--				href="javascript:void(0)"--%>
<%--				onclick="depentmentSeach('${list.id}',this)">${list.name} （ <span--%>
<%--					class="people_num${i.index}"> <c:forEach items="${numList}"--%>
<%--							var="num">--%>
<%--							<c:if test="${num.departmentId==list.id}">${num.number}</c:if>--%>
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
		<li>
			<div class="touxiang">
				<img
					src="<avatar:avatar userId='${item.proposerId}' ></avatar:avatar>">
			</div>
			<div class="detail">
				<c:if test="${not empty item.proposerName }">
					<div class="p1">
						<span>${item.proposerName }</span>发出的申请
					</div>
				</c:if>
				<div class="p2">
					${item.title }
					<c:choose>
						<c:when test="${item.indiaStatus==0 }">
							<span class="daichuli">[待处理]</span>
						</c:when>
						<c:when test="${item.indiaStatus==1 }">
							<span class="weichuli">[已驳回]</span>
						</c:when>
<%--						<c:when test="${item.indiaStatus==2 }">--%>
<%--							<span class="chulizhong">[处理中]</span>--%>
<%--						</c:when>--%>
						<c:when test="${item.indiaStatus==3 }">
							<span class="yichuli">[已同意]</span>
						</c:when>
					</c:choose>
				</div>
				<div class="p5" title="${item.remark}">
				  <c:choose>
						<c:when test="${fn:length(item.remark)>60}">
							<c:out value="${fn:substring(item.remark,0,60)}"></c:out>
							<strong>. . .</strong>
						</c:when>
						<c:otherwise>${item.remark}</c:otherwise>
					</c:choose> 
				</div>
				<c:if test="${not empty item.uploadId }">
					<div class="p6">
						<i class="fa fa-paperclip"></i>
						<p>附件</p>
						<a target="_blank" id="a"
							href='<entity:getHttpUrl uuid="${item.uploadId}"/>'>${item.realFileName}</a>
					</div>
				</c:if>
				<c:if test="${not empty item.departmentName }">
					<div class="p3">
						<i class="fa fa-flag"></i>
						<p class="p_div">部门</p>
						<span>${item.departmentName}</span>
					</div>
				</c:if>
				<c:if test="${not empty item.proposerName }">
					<div class="p3">
						<i class="fa fa-user"></i>
						<p class="p_div">联系人</p>
						<span>${item.proposerName }</span>
					</div>
				</c:if>
				<c:if test="${not empty item.mobile}">
					<div class="p3">
						<i class="fa  fa-phone"></i>
						<p class="p_div">联系电话</p>
						<span>${item.mobile}</span>
					</div>
				</c:if>
				<!-- <div class="p3">
					<i class="fa fa-truck"></i>
					<p class="p_div">提货方式</p>
					<span>自提</span>
				</div> -->
				<c:if test="${item.indiaStatus==1 }">
					<div class="p3">
						<i class="fa fa-comment-o"></i>
						<p class="p_div">驳回理由</p>
						<span>${item.nonTreatmentCause}</span>
					</div>

				</c:if>
				<div class="p4">
					<fmt:formatDate pattern="yyyy年MM月dd日  HH:mm:ss "
						value="${item.publishDate }"></fmt:formatDate>
				</div>
			</div>
			<div class="sz_style">
			<c:if test="${item.indiaStatus!=3 }">
				<p class="sz_0">请点击按钮，选择当前的处理状态</p>
			</c:if>
				<div class="sz_1">
					<c:choose>
						<c:when test="${item.indiaStatus==0 }">
<%--							<a href="javascript:void(0)" class="a1 on" id="p_${item.id }"--%>
<%--								onclick="chuli('pending','${item.id}');">处理中</a>--%>
							<a href="javascript:void(0)" class="a2 on" id="y_${item.id }"
								onclick="chuli('deal','${item.id}');">同意</a>
							<a href="javascript:void(0)" class="a3 on" id="w_${item.id }"
								onclick="chuli('undisposed','${item.id }');">驳回</a>
						</c:when>
<%--						<c:when test="${item.indiaStatus==1 }">--%>
<%--							<a href="javascript:void(0)" class="a1 on" id="p_${item.id }"--%>
<%--								onclick="chuli('pending','${item.id}');">处理中</a>--%>
<%--							<a href="javascript:void(0)" class="a2 on" id="y_${item.id }"--%>
<%--								onclick="chuli('deal','${item.id}');">已处理</a>--%>
<%--						</c:when>--%>
<%--						<c:when test="${item.indiaStatus==2 }">--%>
<%--							<a href="javascript:void(0)" class="a2 on" id="y_${item.id }"--%>
<%--								onclick="chuli('deal','${item.id}');">已处理</a>--%>
<%--						</c:when>--%>
<%--						<c:when test="${item.indiaStatus==3 }">--%>
<%--							--%>
<%--						</c:when>--%>
					</c:choose>
				</div>
				<div id="chuli_${item.id }"></div>
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

	function chuli(type, id) {
		if(type=="pending"){
			/* $("#p_"+id).addClass("on");
			$("#y_"+id).removeClass("on");
			$("#w_"+id).removeClass("on"); */
		}else if(type=="deal"){
			/* $("#p_"+id).removeClass("on");
			$("#y_"+id).addClass("on");
			$("#w_"+id).removeClass("on"); */
			$.post(
					  "${pageContext.request.contextPath}/office/applayIndia/submit",
					  {"type":type,"id":id},
					  function(data,status){
						  if ("success" === status) {
						window.location.reload();
								$.success('已处理');
							} else {
								$.error("处理失败");
							}
					  }
					);
		}else if(type=="undisposed"){
			/* $("#p_"+id).removeClass("on");
			$("#y_"+id).removeClass("on");
			$("#w_"+id).addClass("on"); */
		}
		$.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/office/applayIndia/chuli",
			data:{"type":type,"id":id},
			dataType : "html", 
			success : function(data) {
				$("#chuli_"+id).empty();
				$("#chuli_"+id).append(data);
			}
		});
		
	}
</script>
