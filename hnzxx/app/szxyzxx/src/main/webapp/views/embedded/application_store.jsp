<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <%@ include file="/views/embedded/common.jsp"%> --%>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/jquery-1.9.1.min.js"></script>
<title>应用商店</title>
</head>
<style type="text/css">
body {
    margin: 0;
    padding: 0;
}
p{
    margin: 0;
}
ol, ul {
    list-style: none ;
    margin: 0;
    padding: 0;
}
table {
    border-collapse: collapse;
    border-spacing: 0;
}
a{
    text-decoration: none;
}
img{
    padding: 0;
    margin: 0;
}
html, body, div, span, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, pre, abbr, address, cite, code, del, dfn, em, img, ins, kbd, q, samp, small, strong, sub, sup, var, b, i, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, table, caption, tbody, tfoot, thead, tr, th, td, article, aside, canvas, details, figcaption, figure, footer, header, hgroup, menu, nav, section, summary, time, mark, audio, video {
    background: repeat ;
    border: 0 none;
    font-size: 100%;
    margin: 0;
    outline: 0 none;
    padding: 0;
    vertical-align: baseline;
}
.clear {
    clear: both;
    display: block;
    font-size: 0;
    line-height: 0;
    visibility: hidden;
}
.header{
	height: 70px;
	background-color: #ffffff;
	border-bottom: 1px solid #D4D4D4;
}
.header .head{
	width:1000px;
	margin: 0 auto;
	height: 70px;
}
.header .logo{
	background: url("images/logo.png") no-repeat;
	width: 275px;
	height: 45px;
	float: left;
	margin: 13px 0 0 10px;
}
.header .search{
	float: right;
	background: url("images/search_1.png") no-repeat;
	width: 400px;
	height: 30px;
	position: relative;
	float: right;
	margin: 20px 30px 0 0;
}
.header .search input[type="text"]{
	border: 0 none;
	width: 350px;
	height: 28px;
	padding-left: 9px;
	padding: 0;
	line-height: 28px;
	margin: 1px 0 0 1px;
}
.header .search a{
	display: block;
	width: 40px;
	height: 30px;
	float: right;
}
.content{
	background-color: #F2F2F2;
	padding-top: 40px;
}
.content .nav{
	border-bottom: 1px solid #14ABE0;
	height: 40px;
}
.content .nav ul{
	width: 1000px;
	margin: 0 auto;
}
.content .nav ul li{
	width: 100px;
	height: 40px;
	float: left;
	line-height: 40px;
	text-align: center;
	font-family: "微软雅黑";
}
.content .nav ul li a{
	display: block;
	color: #323232;
	font-size: 18px;
}
.content .nav ul li.on{
	background-color: #14ABE0;
}
.content .nav ul li.on a{
	color: #ffffff;
}
.content .ruanjian{
	min-height: 550px;
}
.content .ruanjian ul{
	width: 990px;
	padding: 0 5px;
	margin: 0 auto;
}
.content .ruanjian ul li{
	margin: 30px 15px 0;
	width: 300px;
	height: 150px;
	background-color: #FFFFFF;
	box-shadow: 0px 1px 2px #d6d6d6;
	border-bottom: 2px solid #14ABE0;
	float: left;
	position: relative;
}
.content .ruanjian ul li .top{
	height: 95px;
}
.content .ruanjian ul li .top img{
	float: left;
	width: 70px;
	height: 70px;
	margin: 15px 10px 10px;
}
.content .ruanjian ul li .top .title{
	float: left;
	margin-top: 10px;
	font-family: "微软雅黑";
	margin-right: 30px;
}
.content .ruanjian ul li .top .title .t1{
	color: #323232;
	font-size: 16px;
	width: 105px;
	height: 44px;
}
.content .ruanjian ul li .top .title .t2{
	font-size: 14px;
	color: #B3B3B3;
	margin-top: 15px;
}
.content .ruanjian ul li .top .btn{
	float: left;
	
}
.content .ruanjian ul li .top .btn a{
	font-family: "微软雅黑";
	font-size: 14px;
	color: #FFFFFF;
	text-align: center;
	display: block;
	width: 65px;
	height: 25px;
	line-height: 25px;
	margin-top: 10px;
}
.content .ruanjian ul li .top .btn .android{
	background-color: #14ABE0;
}
.content .ruanjian ul li .top .btn .pc,.content .ruanjian ul li .top .btn .ios{
	background-color: #22AC38;
}
.content .ruanjian ul li .middle{
	font-family: "微软雅黑";
	font-size: 14px;
	color: #B3B3B3;
	margin-left: 10px;
}
.content .ruanjian ul li .bottom{
	font-family: "微软雅黑";
	font-size: 12px;
	margin: 15px 10px 0;
	height: 19px;
	line-height: 19px;
}
.content .ruanjian ul li .bottom span{
	color: #B3B3B3;
	float: left;
}
.content .ruanjian ul li .bottom .saomiao{
	color: #666666;
	float: right;
	height: 19px;
	line-height: 19px;
}
.content .ruanjian ul li .bottom .saomiao span{
	float: left;
	font-size: 12px;
	font-family: "微软雅黑";
	color: #666666;
}
.content .ruanjian ul li .bottom .saomiao .ewm{
	cursor: pointer;
	float: left;
}
.content .ruanjian ul li .bottom .saomiao .ewm_1{
	cursor: pointer;
	float: left;
}
.content .ruanjian ul li .bottom .saomiao  .ewm_view,.content .ruanjian ul li .bottom .saomiao  .ewm_view_1{
	display: none;
	position: absolute;
	left: 0;
	bottom: -312px;
	z-index: 101
}
.content .ruanjian ul li .bottom .saomiao  .ewm_view img,.content .ruanjian ul li .bottom .saomiao  .ewm_view_1 img{
	width: 252px;
	height: 251px;
	padding: 23px 24px;
	background-color: #FFFFFF;
}
.content .ruanjian ul li .bottom .saomiao  .ewm_view .close,.content .ruanjian ul li .bottom .saomiao  .ewm_view_1 .close{
	background: url("${pageContext.request.contextPath}/res/images/close.png") no-repeat;
	display: block;
    height: 32px;
    position: absolute;
    right: -15px;
    top: -9px;
    width: 32px;
}
.zhe{
	display:none;
    z-index:100;
    background-image:linear-gradient(rgba(30, 30, 30, 0.7) 0px, rgba(30, 30, 30, 0.6) 100%);
    filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#B3303030', endColorstr='#B3303030') ;
    width:100%;
    position:fixed;
    top:0;
    left:0;
   
}
.footer{
	text-align: center;
}
.footer p{
	border-top: 2px solid #CCCCCC;
	line-height: 80px;
	font-size: 16px;
	font-family: "微软雅黑";
	color: #808080;
}
/* .get_top{ */
/* 	background: url("images/get_top.png") no-repeat; */
/* 	width: 42px; */
/* 	height: 45px; */
/* 	display: block; */
/* 	position: fixed; */
/* 	bottom: 130px; */
/* }    */
</style>
<body style="background-color: #F3F3F3 !important">

    <div class="content">

        <div class="ruanjian">
            <ul>
                <li>
                    <div class="top">
                        <img alt="" src="${pageContext.request.contextPath}/res/images/yy_5.png">
                        <div class="title">
                            <p class="t1">教育云家长端</p>
                            <p  class="t2">v1.0  &nbsp; 8.3MB</p>
                        </div>
                        <div class="btn">
                            <a href="http://101.200.190.215:8888/education/szxyjz.apk" class="android">Android</a>
                        </div>
                    </div>
                    <div class="middle">教育云家长端，随时随地，想学就学</div>
                    <div class="bottom">
                        <span>更新于2015-10-13</span>
                        <div class="saomiao"><span>扫描二维码下载</span><img class="ewm" src="${pageContext.request.contextPath}/res/images/eweima.png" />
                            <div class="ewm_view">
                                <img src="${pageContext.request.contextPath}/res/images/eweima1.png">
                                <a href="javascript:void(0)" class="close"></a>
                            </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="top">
                        <img alt="" src="${pageContext.request.contextPath}/res/images/yy_6.png">
                        <div class="title">
                            <p class="t1">教育云教师端</p>
                            <p  class="t2">v1.0  &nbsp; 7.5MB</p>
                        </div>
                        <div class="btn">
                            <a href="http://101.200.190.215:8888/education/szxyjs.apk" class="android">Android</a>
                        </div>
                    </div>
                    <div class="middle">移动学习软件，随时随地，想学就学</div>
                    <div class="bottom">
                        <span>更新于2015-1-13</span>
                        <div class="saomiao"><span>扫描二维码下载</span><img class="ewm" src="${pageContext.request.contextPath}/res/images/eweima.png" />
                            <div class="ewm_view">
                                <img src="${pageContext.request.contextPath}/res/images/eweima2.png">
                                <a href="javascript:void(0)" class="close"></a>
                            </div>
                        </div>
                    </div>
                </li>
                
            </ul>
        </div>
    </div>
<!--     <a href="javascript:void(0);" onclick="goTop();" id="top-link" class="get_top"></a> -->
    <div class="zhe"></div>
    <script type="text/javascript">
     var h = document.documentElement.clientHeight;
     var w = document.documentElement.clientWidth*0.5+500;
        $(function(){
            $(".content .nav ul li a").click(function(){
                var i=$(this).parent().index();
                $(".content .nav ul li").removeClass("on");
                $(this).parent().addClass("on");
                $(".content .ruanjian ul").hide();
                $(".content .ruanjian ul").eq(i).show();
            })

            $(".zhe").css("height",h);
            $(".content .ruanjian ul li .bottom .saomiao .ewm").click(function(){
                $(".zhe").show();
                $(this).siblings(".ewm_view").show();
            });
            $(".content .ruanjian ul li .bottom .saomiao .ewm_1").click(function(){
                $(".zhe").show();
                $(this).siblings(".ewm_view_1").show();
            });
            $(".content .ruanjian ul li .bottom .saomiao .ewm_view .close,.content .ruanjian ul li .bottom .saomiao .ewm_view_1 .close").click(function(){
                $(this).parent().hide();
                $(".zhe").hide();
            });

            $(".get_top").css("left",w);
        });

    /**
 * 回到页面顶部
 * @param acceleration 加速度
 * @param time 时间间隔 (毫秒)
 **/
function goTop(acceleration, time) {
    
    acceleration = acceleration || 0.1;
    time = time || 16;
 
    var x1 = 0;
    var y1 = 0;
    var x2 = 0;
    var y2 = 0;
    var x3 = 0;
    var y3 = 0;
 
    if (document.documentElement) {
        x1 = document.documentElement.scrollLeft || 0;
        y1 = document.documentElement.scrollTop || 0;
    }
    if (document.body) {
        x2 = document.body.scrollLeft || 0;
        y2 = document.body.scrollTop || 0;
    }
    var x3 = window.scrollX || 0;
    var y3 = window.scrollY || 0; 
    
     
     
    // 滚动条到页面顶部的水平距离
    var x = Math.max(x1, Math.max(x2, x3));
    // 滚动条到页面顶部的垂直距离
    var y = Math.max(y1, Math.max(y2, y3));
 
    // 滚动距离 = 目前距离 / 速度, 因为距离原来越小, 速度是大于 1 的数, 所以滚动距离会越来越小
    var speed = 1 + acceleration;
    window.scrollTo(Math.floor(x / speed), Math.floor(y / speed));
    
 
    // 如果距离不为零, 继续调用迭代本函数
    if(x!= 0 || y != 0) {
        var invokeFunction = "goTop(" + acceleration + ", " + time + ")";
        window.setTimeout(invokeFunction, time);
        
    }
}
    </script>
</body>
<script type="text/javascript">
	
</script>
</html>
