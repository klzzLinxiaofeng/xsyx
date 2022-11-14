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
								href="javascript:void(0);" class="back right"><i
								class="fa fa-arrow-circle-left"></i>返回</a>
						</h3>
					</div>
					<div class="meeting">
						<div class="m_title" style="display:none">
							<p>关于认真做好2014—2015年度学校“优秀教师”评选表彰工作会议通知</p>
						</div>
						<div class="m_title wait" >
							<p>关于认真做好2014—2015年度学校“优秀教师”评选表彰工作会议通知</p>
						</div>
						<div class="m_title meeting" style="display:none">
							<p>关于认真做好2014—2015年度学校“优秀教师”评选表彰工作会议通知</p>
						</div>
						<div class="message">
							<div class="p1">
								<i class="fa fa-clock-o"></i><p class="p_div">会议时间</p><span>6月21日 14：00 - 6月21日 16:00 </span><b><span>2</span>天<span>3</span>小时后开始</b>
							</div>
							<div class="p1">
								<i class="fa  fa-map-marker"></i><p class="p_div">会议地点</p><span>东教学楼大会议室2层 </span>
							</div>
							<div class="p1">
								<i class="fa fa-user"></i><p class="p_div">主持人</p><span>刘艳青 </span>
							</div>
							<div class="p1">
								<i class="fa fa-users"></i><p class="p_div">参与人</p><span>周津、罗静淼、潘伟良 </span>
							</div>
							<div class="p1">
								<i class="fa fa-paperclip"></i><p class="p_div">附件</p><span>相关会议资料.doc <a href="javascript:void(0)" class="add_fj">上传附件</a></span>
								
							</div>
							<div class="p1">
								<i class="fa fa-users"></i><p class="p_div">可见范围</p><span>仅参与人员可见 </span>
							</div>
							<div class="clear"></div>
						</div>
						<div class="goutong">
							<div class="title">会议沟通（2）</div>
							<ul>
			                    <li><img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg"><strong>罗志明:</strong><span>21号下午有课不能参加本次会议。</span><p>30分钟前</p></li>
			                    <li><img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg"><strong>罗志明:</strong><span>这次会议主要是讨论“优秀教师”评选的细则。</span><p>33秒前</p></li>
			                </ul>
			                <div class="huifu">
			                	<textarea class="span12" style="height:50px;"></textarea>
			                	<div class="cz_div">
			                		<div class="left">
			                			<a href="javascript:void(0)" class="a1">文件/文件</a><a href="javascript:void(0)" class="a2">同事</a>
			                		</div>
			                		<div class="right">
			                			<a href="javascript:void(0)">取消</a><a href="javascript:void(0)" class="a1">回复</a>
			                		</div>
			                	</div>
			                </div>
						</div>
						<div class="jilu">
							<div class="title">会议纪要（1）</div>
							<ul>
							<li>
								<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
								<div class="record">
					                <p style="height:25px;"><strong>刘艳青</strong><span class="fb">发表了会议纪要</span></p>
					                <p>(一)正确集中会议内容</p>
					                <p>会议对某些问题的讨论，有的意见一致，有的意见不尽一致。起草会议纪要时应认真研究各种意见，并根据会议确定的宗旨进行综合归纳。如果讨论中意见有分歧没有取得统一，一般不要写入纪要。倘若要写，可把纪要草稿交给大家讨论修改，经过上下沟通之后完稿。</p>
					                <p>(二)要抓住会议的中心和要点</p>
					                <p>会议突出的重点问题，是会议主要明确和解决的问题，即会议的中心和要点。会议纪要，就是要记叙会议的中心内容和会议研究讨论的要点。</p>
					                <p>(三)要条理化、理论化</p>
					                <p>会议纪要是具有指导作用的文书。因此，对讨论的问题(意见)，要分类别、分层次、分顺序加以归纳，使其条理清晰。还应尽力给予理论上的概括。</p>
					                <p>(四)语言简明概括</p>
					                <p>会议纪要是对会议内容的归纳整理和提炼，为此，语言必须简明概括，切忌冗长杂乱，拖泥带水。</p>
					              </div>
							</li>	
							</ul>
							<div class="substance" style="width: 100%;margin: 0;border: 0 none;padding: 0;box-shadow: 0 0 0 #fff;">
							<div class="chayue"><i class="fa fa-eye"></i>查阅情况<span>8/10</span></div>
							<div class="change">
								<div id="dianji">
						            <a href="javascript:void(0)"  class="on">未查阅（3）</a>
						            <a href="javascript:void(0)">已查阅（1）</a>
						        </div>
						        <div class="figure">
						            <ul>
						                <li><img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg" style=" border-radius:50%;"><span>刘艳青</span><p>13800138000</p></li>
						                <li><img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg" style=" border-radius:50%;"><span>刘艳青</span><p>13800138000</p></li>
						                <li><img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg" style=" border-radius:50%;"><span>刘艳青</span><p>13800138000</p></li>
						            </ul>
						        </div>
						        <div class="figure">
						            <ul>
						                <li><img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg" style=" border-radius:50%;"><span>刘艳青</span><p>13800138000</p></li>
						            </ul>
						        </div>
					        </div>
					        </div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>