<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/css/extra/coherent.css" rel="stylesheet">
<title>一卡通</title>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="一卡通" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="oa_top">
						<ul class="top_ul">
				             <li><a href="javascript:void(0)" >总体统计</a></li>
				            <li><a href="javascript:void(0)" class="on">清单查询</a></li>
				        </ul>
					</div>
					 <div class="part" style="margin-top: 30px;">
        <form class="choice">
            <input type="radio" value="" name="1" class="edge">按年级查询
            <input type="radio" value="" name="1" class="range">按班级查询
            <input type="radio" value="" name="1" class="range">按个人查询
        </form>
        <form class="left-align">
        <div class="control-group">
            <label class="control-label">时间段：</label>
            <div class="controls">
                <input type="text" class="span12" placeholder="2015/3/1"><p>至</p>
                <input type="text" class="span12" placeholder="2015/3/30">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">年级/班级：</label>
            <div class="controls">
                <div class="chzn-container">
                    <select class="span2"></select>
                </div>
                <div class="chzn-container">
                    <select class="span2"></select>
                </div>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">姓名：</label>
            <div class="controls">
                <input type="text" class="span12" placeholder="姓名关键字">
            </div>
        </div>
        <div class="btn-toolbar" style="margin-top:-2px;">
            <button type="button"class="btn btn-warning">审批</button>
        </div>
        </form>
    </div>
    <div class="part">
        <form class="choice">
            <input type="radio" value="" name="1" class="edge">按年级查询
            <input type="radio" value="" name="1" class="range">按班级查询
            <input type="radio" value="" name="1" class="range">按个人查询
        </form>
        <form class="left-align">
        <div class="control-group">
            <label class="control-label">时间段：</label>
            <div class="controls">
                <input type="text" class="span12" placeholder="2015/3/1"><p>至</p>
                <input type="text" class="span12" placeholder="2015/3/30">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">年级/班级：</label>
            <div class="controls">
                <div class="chzn-container">
                    <select class="span2"></select>
                </div>
                <div class="chzn-container">
                    <select class="span2"></select>
                </div>
            </div>
        </div>
        <div class="btn-toolbar" style="margin-top:50px;">
            <button type="button"class="btn btn-warning">审批</button>
        </div>
        </form>
    </div>
    <div class="part">
        <form class="choice">
            <input type="radio" value="" name="1" class="edge">按年级查询
            <input type="radio" value="" name="1" class="range">按班级查询
            <input type="radio" value="" name="1" class="range">按个人查询
        </form>
        <form class="left-align">
        <div class="control-group">
            <label class="control-label">时间段：</label>
            <div class="controls">
                <input type="text" class="span12" placeholder="2015/3/1"><p>至</p>
                <input type="text" class="span12" placeholder="2015/3/30">
            </div>
        </div>
            <div class="btn-toolbar" style="margin-top:103px;">
                <button type="button"class="btn btn-warning">审批</button>
            </div>
        </form>
    </div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>