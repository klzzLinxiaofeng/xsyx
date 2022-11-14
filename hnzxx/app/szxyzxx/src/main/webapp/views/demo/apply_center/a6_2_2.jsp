<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_jspj.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/address.js" ></script>
<title>已注册成功未上架的应用管理界面</title>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">
	<p class="top_link">应用中心  >  应用管理  > 平台应用管理  >   <span>管理-排课管理</span></p>
	<div class="content_main white">
		<div class="content_top">
			<div class="f_left"><p>管理-排课管理</p></div>
			<button class="btn btn-lightGray" style="float: right;margin-top: 14px;margin-right: 10px;"><i class="fa fa-arrow-left" ></i>返回</button>
		</div>
		
		<div class="apply_detail">
			<p class="p1 fl"><img src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></p>
			<div class="cz_btn fr">
				<button class="btn btn-orange apply_cxsj">上架</button>
			</div>
			<div class="jianjie">
				<p class="p2"><strong>办公OA</strong><span class="banben">版本：1.0</span><span class="qk wxj_green">未上架</span></p>
				<p class="p3">说明：<span>保修服务只限于一般正常使用下有效。一切人为损坏例如接入不适当电源，使用不适当配件，不依说明书使用；因运输及其它意外而造成之损坏；非经本公司认可的维修和改造，错误使用或疏忽而造成损坏；不适当之安装等，保修服务立即失效。此保修服务并不包括运输费及维修人员上门服务费。保修服务只限于一般正常使用下有效。一切人为损坏例如接入不适当电源，使用不适当配件，不依说明书使用；因运输及其它意外而造成之损坏；非经本公司认可的维修和改造，错误使用或疏忽而造成损坏；不适当之安装等，保修服务立即失效。此保修服务并不包括运输费及维修人员上门服务费。 </span></p>
			</div>
		</div>
		<div class="page_div">
			<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
				<jsp:param name="id" value="studentAid_list_content" />
				<jsp:param name="url" value="/teach/studentaid/studentAidList?sub=list&dm=${param.dm}" />
				<jsp:param name="pageSize" value="${page.pageSize}" />
			</jsp:include>
			<div class="clear"></div>
		</div>
	</div>
</div>
<div class="scts_cxsj" style="display:none;text-align: center;padding-top:20px;">
	<p style="margin-top: 20px;">您确定将“<span style="color:#2299ee">排课管理</span>”小应用重新上架吗？</p>
	<p>一旦确定则原上架的学校可立即使用。</p>
</div>

<script>
//重新上架
$('.apply_cxsj').click(function(){
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['390px', '200px'],
		  title: '提示', //不显示标题
		  content: $('.scts_cxsj'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['确定上架','取消'],//按钮
		  btn1: function(index, layero){
			 
		  }
	});
	
});


</script>
</body>
</html>