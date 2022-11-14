<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/css/extra/my.css" rel="stylesheet">
<title>会议</title>
<script>
$(function(){
    $(".figure").hide();//隐藏wenben
    $(".figure:eq(0)").show();//显示第一个wenben
    $("#dianji a").click(function(){
        $(".on").removeClass("on");//移除样式
        $(this).addClass("on");//添加样式
        var i=$(this).index();//获得下标
        $(".figure").hide();//隐藏wenben
        $(".figure:eq("+i+")").show();//显示第i个wenben
    });
})

 	//保存会议沟通
function saveComment(meetingId){
	var loader = new loadLayer();
	var $requestData = {};
	$requestData.comment = $("#meetingComment").val();
	if($requestData.comment==""){
		$.alert("请输入内容");
		return;
	}
	var url = "${pageContext.request.contextPath}/office/meeting/addMeetingComments?meetingId=" + meetingId;
	loader.show();
	$.post(url, $requestData, function(data, status) {
		if("success" === status) {
			$.success('操作成功');
			data = eval("(" + data + ")");
			if("success" === data.info) {
				location.reload();
			} else {
				$.error("操作失败");
			}
		}else{
			$.error("操作失败");
		}
		loader.close();
	});
}
    
//保存会议纪要
function saveSummary(meetingId){
	var loader = new loadLayer();
	var $requestData = {};
	$requestData.summaryContent = $("#meetingsummary").val();
	if($.trim($requestData.summaryContent)==""){
		$.alert("请输入内容");
		return;
	}
	var url = "${pageContext.request.contextPath}/office/meeting/addMeetingSummary?meetingId=" + meetingId;
	loader.show();
	$.post(url, $requestData, function(data, status) {
		if("success" === status) {
			$.success('操作成功');
			data = eval("(" + data + ")");
			if("success" === data.info) {
				location.reload();
			} else {
				$.error("操作失败");
			}
		}else{
			$.error("操作失败");
		}
		loader.close();
	});
}
	function back(){
		window.location.href=document.referrer; 
	}
</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="查看会议" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid ">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3 class="x-head content-top">
							<a href="javascript:void(0);" class="on">查看会议</a><a
								href="javascript:void(0);" onclick="back();" class="back right"><i
								class="fa fa-arrow-circle-left"></i>返回</a>
						</h3>
					</div>
					<div class="meeting" style="word-wrap:break-word; ">
						<c:if test="${status=='complete'}"> 
							<div class="m_title">
								<p>${meeting.meetingName}</p>
							</div>
						</c:if>
						<c:if test="${status=='notStarted'}"> 
							<div class="m_title wait" >
								<p>${meeting.meetingName}</p>
							</div>
						</c:if>
						<c:if test="${status=='underway'}"> 
							<div class="m_title meeting">
								<p>${meeting.meetingName}</p>
							</div>
						</c:if>
						<div class="message">
							<div class="p1">
							<i class="fa fa-clock-o"></i><p class="p_div">会议时间</p><span>
							${meeting.starttime}
								-
							${meeting.endtime}
						<c:if test="${status=='complete'}">
							&nbsp;&nbsp;&nbsp;&nbsp;已结束 
						</c:if>
						<c:if test="${status=='underway'}">
							&nbsp;&nbsp;&nbsp;&nbsp;进行中
						</c:if>
						<c:if test="${status=='notStarted'}">
							</span><b><span>${day>=0 ? day : 0}</span>天
							<span>${hour>=0 ? hour : 0}</span>小时后开始</b>
						</c:if>
							</div>
							<div class="p1">
								<i class="fa  fa-map-marker"></i><p class="p_div">会议地点</p><span>${meeting.address}</span>
							</div>
							<div class="p1">
								<i class="fa fa-user"></i><p class="p_div">主持人</p><span>${meeting.createname}</span>
							</div>
							<div class="p1">
								<i class="fa fa-users"></i><p class="p_div">参与人</p>
								<span title="${tNames}">
									<c:if test="${meeting.fanwei==2}">
										${tNames}
									</c:if>
									<c:if test="${meeting.fanwei==0}">
										所有人
									</c:if>
									<c:if test="${meeting.fanwei==1}">
										${depName}
									</c:if>
								</span>
							</div>
							<div class="p1">
								<i class="fa fa-paperclip"></i><p class="p_div">附件</p>
								<span>
									<c:if test="${meeting.uploadFile!=null && meeting.uploadFile!=''}">
										<a href='<entity:getHttpUrl uuid="${meeting.uploadFile}"/>'>
											${entity.fileName}
										</a>
									</c:if>
									<c:if test="${meeting.uploadFile==null || meeting.uploadFile==''}">
										无
									</c:if>
								 </span>
							</div>
							<div class="p1">
								<i class="fa fa-users"></i><p class="p_div">可见范围</p>
								<span>
									<c:if test="${meeting.fanwei==0}">
										所有人可见
									</c:if>
									<c:if test="${meeting.fanwei==1}">
										仅参与人员可见
									</c:if>
									<c:if test="${meeting.fanwei==2}">
										仅参与人员可见
									</c:if>
								 </span>
							</div>
							<div class="clear"></div>
						</div>
						<div class="goutong" style="word-wrap:break-word; ">
							<div class="title">会议沟通（${cListSize}）</div>
							<ul>
								<c:forEach items="${clist}" var="list">
				                    <li>
				                    	<img src="<avatar:avatar userId='${list.createuserId}'></avatar:avatar>"></img>
				                    	<strong>${list.createname}:</strong>
				                    	<span>${list.comment}</span>
				                    	<p>${list.distanceNowTime}</p>
				                    </li>
			                    </c:forEach>
			                </ul>
			              	<c:if test="${status=='notStarted'}"> 
				                <div class="huifu">
				                	<textarea class="span12" style="height:50px;" id="meetingComment"></textarea>
				                	<div class="cz_div">
				                		<div class="left">
				                		</div>
				                		<div class="right">
				                			<a href="javascript:void(0)" onclick="saveComment('${meeting.id}');" class="a1">回复</a>
				                		</div>
				                	</div>
				                </div>
			                </c:if>
						</div>
						<c:if test="${status=='complete'}">
						<div class="jilu">
							<div class="title">会议纪要</div>
							<ul>
								<c:forEach items="${slist}" var="list1">
								<li>
									<img src="<avatar:avatar userId='${list1.userId}'></avatar:avatar>"></img>
									<div class="record">
						                <p style="height:25px;"><strong>${list1.userName}</strong><span class="fb">发表了会议纪要</span></p>
						                <p>${list1.summaryContent}</p>
						            </div>
								</li>	
						         </c:forEach>
							</ul>
								<c:if test="${currrntUser==meeting.createuserId}">
									<c:if test="${slist.size()==0}">
										<div class="huifu">
						                	<textarea class="span12" style="height:50px;" id="meetingsummary"></textarea>
						                	<div class="cz_div">
						                		<div class="right">
						                			<a href="javascript:void(0)" onclick="saveSummary('${meeting.id}')" class="a1">提交</a>
						                		</div>
						                	</div>
						                </div>
						            </c:if>   
				                </c:if>
			                </c:if>
							<!--有会议纪要的时候才显示查阅的情况，没有纪要则不显示会议纪要 -->
			                <c:if test="${slist.size()>0}">
								<div class="substance" style="width: 100%;margin: 0;border: 0 none;padding: 0;box-shadow: 0 0 0 #fff;">
								<div class="chayue"><i class="fa fa-eye"></i>查阅情况<span>${num==0? 0:num}/${meeting.meetingNum}</span></div>
						        </div>
					        </c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>