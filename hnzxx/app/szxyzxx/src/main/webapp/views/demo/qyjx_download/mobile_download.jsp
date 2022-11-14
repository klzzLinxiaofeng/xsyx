<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>pc端下载</title>
<meta name="apple-touch-fullscreen" content="YES">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="pragram" content="no-cache">
<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">
<link href="${pageContext.request.contextPath}/res/css/sygb/download.css" rel="stylesheet">
</head>
<style>
    .w1200 {
        width: 1200px;
        margin: 0 auto;
    }

</style>
<body>
<div class="w1200">
    <div class="three" id="appDownload">
        <div class="a-download clear">
            <div class="down-ietm android android_teacher">
                <div class="down-ietm-inner">
                    <div class="logo-area">
                        <div class="itemlogo">
                        </div>
                    </div>
                    <div class="info-area">
                        <div class="d-item-title">Android教师版</div>
                        <div class="d-item-desc">版本<span class="a_teacher_bb"></span> <span class="update-time">2017-12-28</span></div>
                        <div class="down-btn">
                            <a href="javascript:void(0)" class="btn">下载</a>
                        </div>
                        <div class="scan">请用手机扫码下载</div>
                    </div>
                </div>
            </div>
            <div class="down-ietm android android_student">
                <div class="down-ietm-inner">
                    <div class="logo-area">
                        <div class="itemlogo">
                        </div>
                    </div>
                    <div class="info-area">
                        <div class="d-item-title">Android学生版</div>
                        <div class="d-item-desc">版本<span class="a_student_bb"></span>  <span class="update-time">2017-12-28</span></div>
                        <div class="down-btn">
                            <a href="javascript:void(0)" class="btn">下载</a>
                        </div>
                        <div class="scan">请用手机扫码下载</div>
                    </div>
                </div>
            </div>
            <div class="down-ietm iphone">
                <div class="down-ietm-inner">
                    <div class="logo-area">
                        <div class="itemlogo"></div>
                    </div>
                    <div class="info-area">
                        <div class="d-item-title">iOS学生版</div>
                        <div class="d-item-desc">版本2.1.0 <span class="update-time">2018-01-05</span></div>
                        <div class="down-btn"><a href="javascript:void(0)" class="btn">下载</a></div>
                        <div class="scan">请用手机扫码下载</div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/statistics/jquery-2.1.1.js"></script>
<script>

    //滚动高度

    $(window).on("load",function(){
        $.ajax({
            type : "POST",
            async:false,
            url : "http://api.studyo.cn/public/app/release/getCurrent/jsonp",
            dataType : "jsonp",//数据类型为jsonp
            jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
            data:{
                appKey : "maiqituo#qyjx#educloud#mobile#android#teacher",
            },
            success : function(data){
                $(".a_teacher_bb").text(data.data[0].version);
                $(".android_teacher .update-time").text(data.data[0].releaseDate);
            },
            error:function(){
                alert('fail');
            }
        });
        $.ajax({
            type : "POST",
            async:false,
            url : "http://api.studyo.cn/public/app/release/getCurrent/jsonp",
            dataType : "jsonp",//数据类型为jsonp
            jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
            data:{
                appKey : "maiqituo#qyjx#educloud#mobile#android#student",
            },
            success : function(data){
                $(".a_student_bb").text(data.data[0].version);
                $(".android_student .update-time").text(data.data[0].releaseDate);
            },
            error:function(){
                alert('fail');
            }
        });
    })

</script>
</html>