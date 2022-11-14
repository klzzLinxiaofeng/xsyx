<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
	<%-- <%@ include file="/views/embedded/common.jsp"%> --%>
    <title>试卷属性设置</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/zujuan.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/button.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery-1.9.1.js"></script>
</head>
<body>
<div class="test-setting">
    <div class="selected_radio pdb27">
        <label>创建到</label>
        <ul>
            <li class="choose"><input type="radio" >公共库</li>
            <li><input type="radio" checked="checked">校本库</li>
            <li><input type="radio" >个人库</li>

        </ul>
    </div>
    <div class="jjmu">
        <p>教材目录：</p>
        <ul>
            <li>
                <i>*</i>
                <span>学段</span>
                <select>
                    <option selected="selected">小学</option>
                    <option >初中</option>
                </select>
            </li>
            <li>
                <span class="mgr8">科目</span>
                <ul class="kemu">
                    <li class="choose"><input type="radio" >语文</li>
                    <li ><input type="radio" checked="checked">数学</li>
                    <li><input type="radio" >英语</li>
                </ul>
            </li>
            <li class="jiaocai">

                <span class="mgr8">教材</span>
                <select>
                    <option selected="selected">无</option>
                    <option ></option>
                </select>
                <select>
                    <option selected="selected"></option>
                    <option ></option>
                </select>
            </li>
            <li class="mulu">
                <i></i>
                <span class="mgr8">目录</span>
                <select>
                    <option selected="selected">无</option>
                    <option ></option>
                </select>
                <select>
                    <option selected="selected"></option>
                    <option ></option>
                </select>
                <select>
                    <option selected="selected"></option>
                    <option ></option>
                </select>
            </li>
        </ul>
    </div>

    <div class="last_ope">
        <a href="javascript:void(0)" class="btn-blue">确定</a>
        <a href="javascript:void(0)" class="btn-lightGray">取消</a>
    </div>

    <script>

        $(function(){
            $('.selected_radio ul li').click(function(){
                $(this).siblings("li").removeClass("choose");
                $(this).addClass("choose");
            });
            $('.jjmu ul li ul.kemu li').click(function(){
                $(this).siblings("li").removeClass("choose");
                $(this).addClass("choose");
            });
        })
    </script>
</div>
</body>
</html>