<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_dxa.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
</head>
<body>
<div class="qyzj_header">
	<div class="logo" onclick="pubPaper();"></div>
	<div class="qyzj_right">
		<ul>
		    <li><a href="javascript:void(0)"  class="a1" onclick="pcDownload();">创建试卷</a></li>
<%-- 			<li><a href="javascript:void(0)" class="a1 <c:if test="${param.dm eq 'BU_ZHI_SHI_JUAN'}">btn-blue</c:if>" onclick="pubPaper();">发布试卷</a></li> --%>
			<li><a href="javascript:void(0)" class="a2 <c:if test="${param.dm eq 'WO_FA_BU_DE_SHI_JUAN'}">btn-blue</c:if>" onclick="teamPub();">我布置的</a></li>
			<li><a href="javascript:void(0)" class="a3 <c:if test="${param.dm eq 'SHI_JUAN_GUAN_LI'}">btn-blue</c:if>" onclick="myPub();">我接收的</a></li>
		</ul>
		 <script type="text/javascript">
		function pubPaper(){
			window.location.href="${pageContext.request.contextPath}/paper/index?dm=BU_ZHI_SHI_JUAN&dm1=KE_JIAN_ZI_YUAN_SCHOOL";
		}
		function myPub(){
			window.location.href="${pageContext.request.contextPath}/paperTask/team/task?dm=SHI_JUAN_GUAN_LI&dm1=KE_JIAN_ZI_YUAN_SCHOOL";
		}
		function teamPub(){
			window.location.href="${pageContext.request.contextPath}/paperTask/my/task?dm=WO_FA_BU_DE_SHI_JUAN&dm1=KE_JIAN_ZI_YUAN_SCHOOL";
		}
		function pcDownload(){
			layer.closeAll();
			layer.open({
				  type: 1,
				  shade: false,
				  area: ['470px', '296px'],
				  title: 'PC端奇云教学下载', //不显示标题
				  content: $('.pcDownload'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
				  cancel: function(){
				    layer.close();
				  }
			});
		}
		$(function () {
            $.ajax({
                type : "POST",
                async:false,
                url : "http://api.studyo.cn/public/app/release/getCurrent/jsonp",
                dataType : "jsonp",//数据类型为jsonp
                jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                data:{
                    appKey : "maiqituo#qyjx#educloud#mobile#pc",
                },
                success : function(data){

                    $("#pc_download").attr("href",data.data[0].setupFile);
                },
                error:function(){
                    alert('fail');
                }
            });
        })
		 </script>
	</div>
</div>
<div class="pcDownload" style="display:none;">
	<p class="p1"></p>
	<p class="p2 btn-blue"><a id="pc_download" href="http://jiaoxueyun.oss-cn-qingdao.aliyuncs.com/education/mqt/%E5%A5%87%E4%BA%91%E6%95%99%E5%AD%A61.0.712.2124.exe" download="奇云教学.exe">立即下载</a></p>

	<p class="layui-layer-close p3" style="width:100%;text-align:center"><a href="javascript:void(0)">继续访问试卷web端</a></p>
</div>
</body>
</html>