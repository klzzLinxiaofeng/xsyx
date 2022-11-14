<%--
  Created by IntelliJ IDEA.
  User: panfei
  Date: 2017/4/18
  Time: 09:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href=" ${ctp}/res/css/extra/add.css" rel="stylesheet">
    <title></title>
    <style type="text/css">
        .div_top{padding: 5px;border:1px solid #ddd;}
        .div_bottom{margin-top: 15px;padding: 5px;border:1px solid #666;}
        .div_bottom p{margin: 0;}
        .cr_style p.describe{text-align:left;color:#666;border-bottom: 0 none;}
    </style>
</head>
<body>
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 20px 0 0;">
                <div class="cr_style" style="padding: 0;border:1px solid #fff">
                	<div class="div_top">
                    <p class="btn_link" style="border:0 none;margin-left:0;margin-bottom:3px;">
	            		<a href="javascript:void(0)"  class="a3" onclick="goBackIndex()" style="float:left"><i class="fa fa-reply"></i>&nbsp; 返回</a>
	            	</p>
                    <p id="title" style="text-align:left">请选择</p>
                    <ul>
                        <%--<li>微课：<span id="micro">0</span></li>--%>
                        <%--<li>课件：<span id="design">0</span></li>--%>
                        <%--<li>作业：<span id="homework">0</span></li>--%>
                        <%--<li>试卷：<span id="exam">0</span></li>--%>
                        <%--<li>教案：<span id="plan">0</span></li>--%>
                        <%--<li>素材：<span id="material">0</span></li>--%>
                        <%--<li>导学案：<span id="learning">0</span></li>--%>
                        <%--<li>其他：<span id="other">0</span></li>--%>
                        <li>资源数量：<span id="allCount">0</span></li>
                        <li title="点击这里可以查看具体详细信息" style="width: 100%;"><a id="detailed" onclick="getDetailed()" href="javascript:void(0)" style="cursor: pointer;text-align: left;display: block">查看详细资源信息</a></li>
                    </ul>
                    <div class="clear"></div>
                    </div>
                    <div class="div_bottom">
                    <p style="text-align:left;margin-bottom: 0" id="describeTitle">知识点描述</p>
                    <p id="describeMess" class="describe">暂无相关描述</p>
                    </div>
                    </div>
            </div>
        </div>
    </div>
    <div id="zylb" style="margin-right:160px;margin-left: 40px;"></div>
</div>
<input type="hidden" id="msgNodeId"/>
</body>
<script charset="utf-8">
    function getDetailed(){
        var nodeId = $("#msgNodeId").val();
        if(nodeId == ""){
            $.alert("请点击需要查看的节点之后，在查看统计！");
            return;
        }
        loadDetailedPage(nodeId);
    }

    // 	加载资源详细信息对话框
    function loadDetailedPage(nodeId) {
        $.initWinOnTopFromLeft('资源详情', '${ctp}/generalcode/knowledgetree/toDetailedPage?nodeId='+nodeId);
    }

    function goBackIndex(){
        window.location.href = "${ctp}/generalcode/knowledgetree/index?dm=${param.dm}";
    }
</script>
</html>
