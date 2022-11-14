<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小奇机器人</title>
<style type="text/css">
	.div{ margin:0 auto; width:400px; height:100px; border:1px solid #F00} 
</style>
<style type="text/css">  
      h1.fb-glitch {
            position: relative;
            color: #abff79;
            font-size:60px;
        }
        h1.fb-glitch:before {
            left: -2px;
            text-shadow: 2px 0 #0b391a;
            animation: glitch-anim-2 5s infinite linear alternate-reverse;
        }
        h1.fb-glitch:before, h1.fb-glitch:after {
            content: attr(data-text);
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            clip: rect(0, 0, 0, 0);
        }
        h1.fb-glitch:after {
            left: 2px;
            text-shadow: -1px 0 #1b5c16;
            animation: glitch-anim-1 2s infinite linear alternate-reverse;
        }
        @keyframes glitch-anim-1 {
            0% {
                clip: rect(82px, 820px, 98px, 0); }
            5.8823529412% {
                clip: rect(17px, 820px, 4px, 0); }
            11.7647058824% {
                clip: rect(24px, 820px, 44px, 0); }
            17.6470588235% {
                clip: rect(24px, 820px, 111px, 0); }
            23.5294117647% {
                clip: rect(29px, 820px, 45px, 0); }
            29.4117647059% {
                clip: rect(114px, 820px, 115px, 0); }
            35.2941176471% {
                clip: rect(103px, 820px, 22px, 0); }
            41.1764705882% {
                clip: rect(49px, 820px, 32px, 0); }
            47.0588235294% {
                clip: rect(2px, 820px, 10px, 0); }
            52.9411764706% {
                clip: rect(80px, 820px, 44px, 0); }
            58.8235294118% {
                clip: rect(70px, 820px, 30px, 0); }
            64.7058823529% {
                clip: rect(27px, 820px, 79px, 0); }
            70.5882352941% {
                clip: rect(82px, 820px, 112px, 0); }
            76.4705882353% {
                clip: rect(27px, 820px, 2px, 0); }
            82.3529411765% {
                clip: rect(47px, 820px, 104px, 0); }
            88.2352941176% {
                clip: rect(53px, 820px, 102px, 0); }
            94.1176470588% {
                clip: rect(2px, 820px, 90px, 0); }
            100% {
                clip: rect(88px, 820px, 56px, 0); } }

        @keyframes glitch-anim-2 {
            0% {
                clip: rect(88px, 820px, 68px, 0); }
            5.8823529412% {
                clip: rect(75px, 820px, 113px, 0); }
            11.7647058824% {
                clip: rect(80px, 820px, 40px, 0); }
            17.6470588235% {
                clip: rect(70px, 820px, 51px, 0); }
            23.5294117647% {
                clip: rect(47px, 820px, 78px, 0); }
            29.4117647059% {
                clip: rect(61px, 820px, 7px, 0); }
            35.2941176471% {
                clip: rect(94px, 820px, 1px, 0); }
            41.1764705882% {
                clip: rect(26px, 820px, 69px, 0); }
            47.0588235294% {
                clip: rect(91px, 820px, 62px, 0); }
            52.9411764706% {
                clip: rect(8px, 820px, 78px, 0); }
            58.8235294118% {
                clip: rect(17px, 820px, 97px, 0); }
            64.7058823529% {
                clip: rect(66px, 820px, 48px, 0); }
            70.5882352941% {
                clip: rect(66px, 820px, 85px, 0); }
            76.4705882353% {
                clip: rect(46px, 820px, 12px, 0); }
            82.3529411765% {
                clip: rect(69px, 820px, 68px, 0); }
            88.2352941176% {
                clip: rect(38px, 820px, 7px, 0); }
            94.1176470588% {
                clip: rect(83px, 820px, 32px, 0); }
            100% {
                clip: rect(110px, 820px, 95px, 0); } }

body{
	font-family:”Microsoft YaHei”,Arial,Helvetica,sans-serif,”宋体”; 
	text-align:center;
	margin-left:auto;
	margin-right:auto;
}

.btn{
    background-color: #3385ff;
    color: #FFF;
    width: 100px;
    height: 36px;
    margin-left: -71px; 
}

.inputClass{
	width: 220px;
    height: 22px;
    font: 16px/18px arial;
    line-height: 22px;
    margin: 6px 0 0 7px;
    padding: 0;
    background: transparent;
    border: 1;
    outline: 0;
    -webkit-appearance: none;
}

p{
	font-size:36px;
}

#liu{
font-size: 60px;
}

</style>  

</head>
<body>
		<h1 class="fb-glitch" data-text="欢迎来到机器人时代" >欢迎来到机器人时代</h1>
		<video src="http://www.maiqituo.com/r/cms/www2/www2/video/banner.mp4" width="100%" autoplay="autoplay">
        </video>
		<p>界面虽丑，但实用，不喜勿喷!</p>
		<p>试卷随机生成</p>
		<form action="/random/quan/demo/exam">
			公司：<input type="text" class="inputClass" name ="corporate" placeholder="版权公司名称"/><br/>
			年级：<input type="text" class="inputClass" name ="gradeId" placeholder="年级ID"/></br>
			任务：<input type="text" class="inputClass" name ="ownerId" placeholder="任务ID"/></br>
			试卷：<input type="text" class="inputClass" name ="paperId" placeholder="试卷ID"/><br/>
			<input type="submit" value="一键生成" class="btn"/>
		</form>
		<p>导学案随机生成</p>
		<form action="/random/quan/demo/learningPlan">
			公司：<input type="text" class="inputClass" name ="corporate" placeholder="版权公司名称"/><br/>
			任务：<input type="text" class="inputClass" name ="taskId" placeholder="任务ID"/><br/>
			<input type="submit" value="一键生成" class="btn"/>
		</form>
		
		<p>成绩分析随机生成</p>
		<form action="/random/quan/demo/score">
				考试类型：
				<select name="type" style="width: 220px; height: 35px;font: 16px/18px arial;line-height: 22px;margin: 6px 0 0 7px;padding: 0; background: transparent;">
					<option value = "1">班级测试</option>
					<option value = "2">年级统考</option>
				</select>
				</br>
			公司：<input type="text" class="inputClass" name ="corporate" placeholder="版权公司名称"/><br/>
			考务：<input type="text" class="inputClass" name ="examWorksId" placeholder="考务ID"/><br/>
			年级/班级：<input type="text" class="inputClass" name ="objectId" placeholder="年级/班级ID"/><br/>
			科目：<input type="text" class="inputClass" name ="subjectCode" placeholder="科目CODE，多个以“,”隔开"/><br/>
			<input type="submit" value="一键生成" class="btn"/>
		</form>
		
	</div>	
	
</body>
</html>