<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="d5d5d5">
<head>
    <meta charset="UTF-8">
	<%-- <%@ include file="/views/embedded/common.jsp"%> --%>
    <title>组卷记录</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/zujuan.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/button.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery-1.9.1.js"></script>
</head>
<body class="d5d5d5">
    <div class="b1-4-0 d5d5d5">
        <div class="b1-4-0-top">
            <span>组卷记录</span>
            <a href="javascript:void(0)" class="btn-lightGray mgr10">返回</a>
        </div>
        <div class="b1-4-0-bottom">
            <div class="zjjl box-sizing">
                <p class="mc">组卷记录</p>
                <ul class="mgb20">
                    <li class="last-border" style="display: none">
                        <div class="info fl">
                            <p class="title">
                                罗定邦中学高三语文模拟卷1
                            </p>
                            <ul>
                                <li>
                                    <span class="mgr20"><i class="update-time"></i>创建试卷：<b>2017/09/13 13:55</b></span>
                                </li>
                                <li>
                                    <span class="mgr20"><i class="update-time"></i>更新时间：<b>2017/09/13 13:55</b></span>
                                </li>
                            </ul>
                        </div>
                        <p class="cz fr">
                            <a href="javascript:void(0)" class="btn-green">恢复</a>
                            <a href="javascript:void(0)" class="btn-red">清空</a>
                        </p>

                        <div class="clear"></div>
                    </li>
                    <li class="no-notice last-border" style="display: block">
                        <div >
                            暂无历史组卷记录
                        </div>

                    </li>
                </ul>
            </div>

            <div class="zjjl box-sizing">
                <p class="mc">我的试卷</p>
                <ul class="mgb20">
                    <li style="display: none">
                        <div class="info fl">
                            <p class="title">
                                罗定邦中学高三语文模拟卷1
                            </p>
                            <ul>
                                <li>
                                    <span class="mgr20"><i class="update-time"></i>创建试卷：<b>2017/09/13 13:55</b></span>
                                </li>
                                <li>
                                    <span class="mgr20"><i class="update-time"></i>更新时间：<b>2017/09/13 13:55</b></span>
                                </li>
                                <li>
                                    <span class="mgr20"><i class="collect"></i>收藏(<b class="blue">152</b>)</span>
                                </li>
                                <li>
                                    <span class="mgr20"><i class="use"></i>使用(<b class="blue">20</b>)</span>
                                </li>
                            </ul>
                        </div>
                        <p class="cz fr">
                            <a href="javascript:void(0)" class="btn-blue">编辑</a>
                            <a href="javascript:void(0)" class="btn-red">删除</a>
                        </p>

                        <div class="clear"></div>
                    </li>
                    <li class="last-border" style="display: none">
                        <div class="info fl">
                            <p class="title">
                                罗定邦中学高三语文模拟卷1
                            </p>
                            <ul>
                                <li>
                                    <span class="mgr20"><i class="update-time"></i>创建试卷：<b>2017/09/13 13:55</b></span>
                                </li>
                                <li>
                                    <span class="mgr20"><i class="update-time"></i>更新时间：<b>2017/09/13 13:55</b></span>
                                </li>
                                <li>
                                    <span class="mgr20"><i class="collect"></i>收藏(<b class="blue">152</b>)</span>
                                </li>
                                <li>
                                    <span class="mgr20"><i class="use"></i>使用(<b class="blue">20</b>)</span>
                                </li>
                            </ul>
                        </div>
                        <p class="cz fr">
                            <a href="javascript:void(0)" class="btn-blue">编辑</a>
                            <a href="javascript:void(0)" class="btn-red">删除</a>
                        </p>

                        <div class="clear"></div>
                    </li>
                    <li class="no-notice last-border" style="display: block">
                        <div >
                            你还没有组过试卷哦~
                        </div>

                    </li>
                </ul>
            </div>


        </div>
    </div>

	

</body>
</html>