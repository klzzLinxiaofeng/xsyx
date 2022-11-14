<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0,  user-scalable=0;" name="viewport" />
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <script type="text/javascript" src="${ctp}/res/js/common/jquery/jquery-1.11.0.min.js"></script>
    <link rel="stylesheet" href="${ctp}/res/css/bbx/bp/evaluateDetailStyle.css">
    <title></title>
</head>
<style>
    .circle {
        transition: 0.8s;
        width: 160px;
        height: 160px;
        position: relative;
        border-radius: 50%;
        overflow: hidden;
        margin: 25px auto;
        margin-bottom:15px;
    }
    .pie_left, .pie_right {
        width: 160px;
        transition: 0.8s;
        height: 160px;
        position: absolute;
        top: 0;left: 0;
    }
    .left, .right {
        width:160px;
        transition: 0.8s;
        height:160px;
        border-radius: 50%;
        position: absolute;
        top: 0;
        left: 0;
        background: #fff;

    }
    .right{
        /*  transform: rotate(30deg);*/
    }
    .pie_right, .right {
        clip:rect(0,auto,auto,80px);
    }
    .pie_left, .left {
        clip:rect(0,80px,auto,0);
    }
    .theValue {
        width: 126px;
        height: 126px;
        border-radius: 50%;
        top: 18px;
        left: 18px;
        background: white;
        position: absolute;
        text-align: center;
        line-height: 20px;
        font-size: 20px;
        color: #2ba9ee;

    }
    .theValue span{ margin-top: 40px; display: inline-block; color: #2ba9ee;  font-size: 40px; font-weight: bold; font-family: Arial}
    .theValue p{ padding: 0; margin: 0; color: #888888; margin-top: 0px;}
    .circle_left{ position: absolute;  top:0;left:0;  width: 50%;  height: 100%;
        background:-moz-linear-gradient(top,#178ff9,#f96);/*Mozilla*/
        background:-webkit-linear-gradient(top,#2cb0f8,#178ff9);/*new gradient for Webkit*/
        background:-o-linear-gradient(top,#178ff9,#f96); /*Opera11*/}
    .circle_right{ position: absolute;  top:0;right:0; width: 50%;  height: 100%;
        background:-moz-linear-gradient(top,#0470fa,#178ff9);/*Mozilla*/
        background:-webkit-linear-gradient(top,#0470fa,#178ff9);/*new gradient for Webkit*/
        background:-o-linear-gradient(top,#0470fa,#178ff9); /*Opera11*/}
    .top p{ text-align: center;  border-bottom: 1px solid #e5e5e5}
    .top p span img{ margin-top: 14px; margin-left: 8px; width: 13px}
    .top p span{ color: #222; font-size: 12px; line-height: 40px;}
    .iaol div{ float: left; width: 50%}
    .iaol div span{ display:block; text-align: center}
    .iaol{ padding-bottom: 20px;  border-bottom:8px solid #f6f6f6; overflow: hidden}
    .list_title{ margin: 10px 4%; padding-bottom: 6px}
    .list_div{ width: 100%; margin: 0 auto}
    .Cla{ overflow: hidden; padding-bottom: 10px;}
    .Cla p{ float: left; font-size: 12px; color: #999999}
    .Cla p span{ font-size: 12px; margin-left: 10px;}
    .wjx img{ width: 20px;}
    .wjx{ float: right}
    .xl{ overflow: hidden}
    .list li>div span{ line-height: 21px;}
    .list li>div{ float: left; font-size: 14px; line-height: 21px}
    .pic_title{width: 50px; height: 50px; background: red;border-radius:100px; overflow: hidden}
    .name_a{font-size: 18px; float: left}
    .pjs{color: #999999; font-size: 12px; margin-top: 5px;}
    .list_title{ border-bottom: 1px solid #e9e9e9}
    /*æææ ·å¼*/

    .show_number li{
        width:100px;
        border:1px solid #ccc;
        padding:10px;
        margin-right:5px;
        margin-bottom:20px;
    }

    .atar_Show{
        background:url(${ctp}/res/images/bbx/bp/syllabusEvaluate/starky.png);
        width:110px; height:21px;
        position:relative;
        float:left;
        overflow: hidden;
    }

    .atar_Show p{
        background:url(${ctp}/res/images/bbx/bp/syllabusEvaluate/starsy.png);
        left:0;
        height:21px;
        width:0px;
        transition: 0.5s;
    }

    .show_number li span{
        display:inline-block;
        line-height:21px;
    }

    .list td{ vertical-align: middle; line-height: 21px; padding: 5px 0}
    .list td span{ line-height: 21px;}
    .list{ padding:0 10px;}
    .list table{ width: 100%;}
    .tip{ font-size:12px; line-height: 21px;}
    .tip span{font-size:12px;}
    .show_number{ overflow: hidden; width: 110px}
</style>
<body>
<%-- <div class="top">
    <p><span>本周<img src="${ctp}/res/images/bbx/bp/syllabusEvaluate/iconpn.png"></span></p>
</div> --%>

<div class="list_title"><span style="font-size: 12px; border-left:3px solid #2ba9ee; padding-left: 10px;color: #222222">${staTeacherSyllabusEvaluateVo.teacherName}</span></div>
<div class="circle">
    <div class="circle_left"></div>
    <div class="circle_right"></div>
    <div class="pie_left"><div class="left"></div></div>
    <div class="pie_right"><div class="right"></div></div>
    <div class="theValue"><span class="vauNum"></span><p></p><p style="font-size: 12px">平均分</p></div>
</div>
<div class="iaol">
<div style=" text-align: center;width: 100%;"><span style="display: inline-block;color:#888888; font-size:12px;">累计评价人数：</span><span style="display: inline-block;color:#2baef8;">${staTeacherSyllabusEvaluateVo.evaluateNum}人</span></div>
  <!--   <div class="a_left">
        <p style="border-right:1px solid #eee ">
        <span style="font-size: 16px;color: #2ea8ed;">68人</span>
        <span style="color: #888888;font-size: 12px;line-height: 30px;">已评(58%)</span>
        </p>
    </div>
    <div class="a_right">
        <span style="font-size: 16px;color: #f57e76;">40人</span>
        <span style="color: #888888;font-size: 12px;line-height: 30px;">未评(42%)</span>
    </div> -->
</div>

<div class="list_div">
    <div class="list_title"><span style="font-size: 12px; border-left:3px solid #2ba9ee; padding-left: 10px;color: #222222">评价内容</span></div>
    <div class="list">
        <table>
            <tr>
                <td>教学目标与内容</td>
                <td>
                    <div class="show_number clearfix">
                        <div class="atar_Show clearfix">
                            <p></p>
                        </div>
                    </div>
                </td>
                <td><p class="tip" tip ="${staTeacherSyllabusEvaluateVo.avgItem1}"><span></span>分</p></td>
            </tr>
            <tr>
                <td>教学过程与方法</td>
                <td>
                    <div class="show_number clearfix">
                        <div class="atar_Show clearfix">
                            <p></p>
                        </div>
                    </div>
                </td>
                <td><p class="tip" tip ="${staTeacherSyllabusEvaluateVo.avgItem2}"><span></span>分</p></td>
            </tr>
            <tr>
                <td>基本素质与能力</td>
                <td>
                    <div class="show_number clearfix">
                        <div class="atar_Show clearfix">
                            <p></p>
                        </div>
                    </div>
                </td>
                <td><p class="tip" tip ="${staTeacherSyllabusEvaluateVo.avgItem3}"><span></span>分</p></td>
            </tr>
            <tr>
                <td>教学效果与特色</td>
                <td>
                    <div class="show_number clearfix">
                        <div class="atar_Show clearfix">
                            <p></p>
                        </div>
                    </div>
                </td>
                <td><p class="tip" tip ="${staTeacherSyllabusEvaluateVo.avgItem1}"><span></span>分</p></td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
<script>
    $(function() {
    	//alert("${staTeacherSyllabusEvaluateVo.teacherName}")
		console.log("${staTeacherSyllabusEvaluateVo}")
        //显示分数
        function isInteger(obj) {
            return Math.floor(obj) === obj
        }

        $(".list tr").each(function(index, element) {
            var num=$(this).find('.tip').attr("tip");
            var  sungin = num
            if(num>1&&num<2){
                num=1.5
            }else if(num>2&&num<3){

                num=2.5
            }else if(num>3&&num<4){
                num=3.5

            }else if(num>4&&num<5){
                num=4.5
            }else{

            }
            var www=num*2*11;//
            $(this).find('.atar_Show p').css("width",www);
            $(this).find(".tip span").html(sungin);
        });

        var vauNum = "${staTeacherSyllabusEvaluateVo.avgScore}" //分数;
        $('.vauNum').html(vauNum);
        if(vauNum==5){
			$('.circle_left').css("background","#0470fa")
			$('.circle_right').css("background","#0470fa")

        }else if(vauNum==0){
			$('.circle_left').css("background","#2cb0f8")
			$('.circle_right').css("background","#2cb0f8")
        }else{
			$('.circle_left').css("background","linear-gradient(top,#2cb0f8,#178ff9)")
			$('.circle_right').css("background","linear-gradient(top,#0470fa,#178ff9)")

        }
             $('.circle').each(function(index, el) {
            var num = 100 * 3.6;
            if (num<=180) {
                $(this).find('.right').css('transform', "rotate(" +num + "deg)");
            } else{
                var that=$(this).find('.right');
                that.css('transform', "rotate(180deg)");
                setTimeout(function () {
                    $('.pie_left').find('.left').css('transform', "rotate(" + (num-180) + "deg)")
                },800)
            };
        });
    });
</script>