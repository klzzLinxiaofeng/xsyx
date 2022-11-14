<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
    <meta name="format-detection" content="telphone=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <link rel="stylesheet" href="${ctp}/res/css/bbx/bp/style.css">
    <script type="text/javascript" src="${ctp}/res/js/common/jquery/jquery-1.11.0.min.js"></script>
    <title>考勤统计</title> 
</head>
<style>
    img{ vertical-align: top}
    .title{ background: #f6f6f6 }
    table,td,tr{ padding: 0; margin:0;}
    body{  display: flex;flex-direction:column }
    body,html{ margin:0;padding: 0; max-width: 640px; margin: 0 auto;height: 100%}
    .title .title_table td{width: 25%; padding: 5px 0}
    .title .title_table td div{ display: block;text-align: center; width: 100%; background: url("${ctp}/res/css/bbx/bp/images/xi.png") no-repeat right; font-size: 14px; color: #87868e}
    .concon{ height: 100%; width: 100%}
    .title{ width: 100%}
    .title_con{ position: absolute;z-index: 111; width: 100%; background:#f6f6f6; border-top:1px solid #e3e3e3  }
    .title_con>div{ padding: 10px;padding-top: 0; display: none;  max-height: 250px;  overflow: auto !important;}
    .title_con>div.bolck{ display: block;}
    .title_con>div>p{color: #87868e; padding:2%; }
    .title_con>div ul{ overflow: hidden; width: 102%}
    .title_con>div ul li{ float: left; width: 33%; text-align: center;}
    .title_con>div ul li span{ display: block; line-height: 30px; margin: 5%;background: #fff; border: 1px solid #e4e4e4; width: 90%;}
    .xir{ padding-top: 10px; padding-left: 2%; padding-right: 2%}
    .xir span{display:inline-block; line-height: 30px; margin-right: 10px}
    .avcit{border: 1px solid #e4e4e4; background: #2298ef !important; color: #fff; }
    .concon{ position: relative; overflow: auto;}
    .z{ background: rgba(0, 0, 0, 0.4); width: 100%; height: 1000%; position: absolute; top: 0;left:0; z-index: 22;display: none}
    .title_con{ max-width: 640px;}
    .now_s{display: none}
    /*定义滚动条高宽及背景 高宽分别对应横竖滚动条的尺寸*/
    ::-webkit-scrollbar
    {
        width: 1px;
        height: 1px;
        background-color: #F5F5F5;
    }

    /*定义滚动条轨道 内阴影+圆角*/
    ::-webkit-scrollbar-track
    {
        -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
        border-radius: 10px;
        background-color: #F5F5F5;
    }

    /*定义滑块 内阴影+圆角*/
    ::-webkit-scrollbar-thumb
    {
        border-radius: 10px;
        -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);
        background-color: #555;
    }
    .concon{background: #efeeec}
    .cavs{position: relative; width: 80%; padding:0 10%;}
    .p{position: absolute; width: 80%;bottom:0; height: 100%; }
    .p p{ width: 33.33%; float: left; text-align: center; height: 100%; vertical-align: bottom; position: relative;transition:100s;}
    .p p span{ background: red; height: 0%; display: inline-block; width: 40px; position: absolute;bottom: 0;  left: 50%;  margin-left: -20px; background: -moz-linear-gradient(top, #229bf2 0%, #e4f3fe 100%);
        background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#229bf2), color-stop(100%,#e4f3fe));
        background: -webkit-linear-gradient(top, #229bf2 0%,#e4f3fe 100%);
        background: -o-linear-gradient(top, #229bf2 0%,#e4f3fe 100%);
        background: -ms-linear-gradient(top, #229bf2 0%,#e4f3fe 100%);
        background: linear-gradient(to bottom, #229bf2 0%,#e4f3fe 100%);}
    .font_w td{ font-weight: bold; font-size: 16px; padding-top: 10px;}
    .textss{ text-align: center; line-height: 35px; color: #333333; font-size:14px;}
    .concon_big{ margin-top: 2%; background: #fff;}
    .cavs table{border-top: 1px solid #e6e6e6;  }
    .cavs td{ width: 15%; height:55px;background: #e8f5fe; border-bottom: 1px solid #e6e6e6; }
    .cavs tr{background:#ddd;}
    .cavs tr{background: #fff}
    .cavs tr td:nth-child(2n){ background: #fff}
</style>
<body>
<div class="title">
    <div class="title_header">
        <table class="title_table" cellspacing="0" width="100%">
            <tr>
                <td width="25%"><div>16-17学年<br>上学期<img src="${ctp}/res/css/bbx/bp/images/xia.png" alt=""></div></td>
                <td><div><span id="all_school">全校</span> <img src="${ctp}/res/css/bbx/bp/images/xia.png" alt=""></div></td>
                <td><div><span>月次</span> <img src="${ctp}/res/css/bbx/bp/images/xia.png" alt=""></div></td>
                <td><div style=" background: none"><span id="title_datas_data">月次</span> <img src="${ctp}/res/css/bbx/bp/images/xia.png" alt=""></div></td>
            </tr>
        </table>
    </div>
    <div class="title_con">
        <div class="title_con_c semester_div">
            <div class="xir"><span>16-17学年</span></div>
            <ul class="semester">
                <li><span>上学期</span></li>
                <li><span>下学期</span></li>
            </ul>
            <div class="xir"><span>16-17学年</span></div>
            <ul class="semester">
                <li><span>上学期</span></li>
                <li><span>下学期</span></li>
            </ul>
        </div>
        <div class="title_con_c">
            <p>选择年级</p>
            <ul class="l_class" id="l_class">
                <li class="add_class"><span class="avcit">全校</span></li>
            </ul>
            <p class="now_s">选择班级</p>
            <ul class="class_ul h_ul now_s">
                <li><span>1班</span></li>
                <li><span>2班</span></li>
            </ul>
        </div>
        <div class="title_con_c">
            <p>选择</p>
            <ul class="h_ul datas">
                <li><span class="avcit">月份</span></li>
                <li><span>周次</span></li>
            </ul>
        </div>
        <div class="title_con_c">
            <p>选择</p>
            <ul class="h_ul datas_data" id="select_week" data_id=''>

            </ul>
        </div>
    </div>
</div>
<div class="concon">
    <div class="concon_big">
        <div class="">
            <div class="textss">
                <table width="80%" style=" margin: 0 auto" class="font_w">
                    <tr>
                        <td width="33%" id="later_sun">20人</td>
                        <td width="33%" id="early_sun">2人</td>
                        <td id="absent_sun">4人</td>
                    </tr>
                </table>
            </div>
            <div class="cavs">
                <table width="100%" style=" margin: 0 auto">
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </table>
                <div class="p">
                    <p id="later"><span></span></p>
                    <p id="early"><span></span></p>
                    <p id="absent"><span></span></p>
                </div>
            </div>
            <div class="textss">
                <table width="80%" style=" margin: 0 auto">
                    <tr>
                        <td>迟到</td>
                        <td>早退</td>
                        <td>缺席</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <div class="z"></div>
</div>
<style>
</style>
</body>
</html>
<script>

var later =0;
var early = 0;
var absent = 0;
var laterPercent = later/(later+early+absent)*100;
var earlyPercent =  early/(later+early+absent)*100;
var absentPercent =  absent/(later+early+absent)*100;
$('#later_sun').text(later+'人')
$('#early_sun').text(early+'人')
$('#absent_sun').text(absent+'人')
$('#later').find('span').height(laterPercent+'%')
$('#early').find('span').height(earlyPercent+'%')
$('#absent').find('span').height(absentPercent+'%')

var begin = "00-00-00";
var end = "00-00-00";
var schoolId = "${schoolId}";
var userId = "${userId}";
var role = "${role}";
var schoolYear='';
var html_data;
var url  = "${url}";
$('.now_s').on('click','span',function(){
	getData($(this).parent().attr('data_id'),$('.l_class').find('.avcit').parent().attr('data-gradeid'));
})

$('.z').click(function () {
    $('.title_con>div').fadeOut(100);
    $('.z').fadeOut(100);
    $('.concon').css('overflow','auto')
})
//选项卡header
$('.title_table tr td').click(function(){
    $('.title_con>div').hide();
    $('.title_con>div').eq($(this).index()).show(50);
    $('.z').show();
    $('.concon').css('overflow','hidden')
    $('.title_table tr td').find('img').css('transform','rotate(0deg)')
    $(this).find('img').css('transform','rotate(-180deg)')
})
//内容选项卡
$('.title_con ul:not(".semester")').on('click','li span:not(".avcit")',function () {
    $(this).parent().parent().find('li').find('span').removeClass('avcit');
    $(this).addClass('avcit');
});
$('.h_ul').on('click','li span:not(".avcit")',function () {
	begin = $('.semester_div').find('li').find('.avcit').attr('data-begin');
	end = $('.semester_div').find('li').find('.avcit').attr('data-end');
    $('.title_con>div').fadeOut(100);
    $('.concon').css('overflow','auto')
    $('.z').fadeOut(100);
    if($(this).parent().parent().hasClass('datas')){
        console.log('开始时间='+begin+'结束时间=='+end)
        if($(this).parent().index()==0){
            //月份
            console.log('月次')
            $.getNewMonth({
                setId:'#select_week',
                begin:begin,
                end:end,
            });
            $('#select_week').attr('data_id','')
        }else{
            $('#select_week').attr('data_id','10')
            //周次
            console.log('周次')
            $.getWeek({
                "selector" : "#select_week",
                "begin" : begin,
                "end" : end,
                "isClear" : false,
                "today" : "",
                "isSelectCurrentWeek" : true,
                "clearedOptionTitle" : "请选择学期"
            });
        }
        $('.title_table tr td').eq($(this).parent().parent().parent().index()).find('span').text($(this).text());
        $('#title_datas_data').html($(this).text())
        setTimeout(function(){$('.title_table tr td').eq(3).click()},200)
    }else if($(this).parent().parent().hasClass('class_ul')){
        $('.title_table tr td').eq($(this).parent().parent().parent().index()).find('span').text($(this).parent().parent().parent().find('.l_class').find('.avcit').text());
    }else{
        $('.title_table tr td').eq($(this).parent().parent().parent().index()).find('span').text($(this).text());}
})

//选项卡-学年
$('.semester_div').on('click','li span:not(".avcit")',function () {
    begin = $(this).attr('data-begin');
    end = $(this).attr('data-end');
    $(this).parent().parent().parent().find('li').find('span').removeClass('avcit');
    $(this).addClass('avcit');
    $('.title_con>div').fadeOut(100);
    $('.z').fadeOut(100);
})

//选项卡-年级
$('.l_class').on('click','li:not(".add_class") span:not(".avcit")',function () {
    var con_html = '';
    var index  =$(this).parent().attr('data-data');
    $('.now_s').show();
    con_html +='<li data-data="" data_id=""><span data-teamId="">全年级</span></li>';
    $.each(html_data,function (i,v) {
        console.log(1)
        if(i==index){
            $.each(v.teamList,function (u,t) {
                con_html +='<li data-data="'+u+'" data_id="'+t.teamId+'"><span data-teamId="'+t.teamId+'">'+t.teamName+'</span></li>';
            });
        }
    });
    $('.class_ul').html(con_html);
})
//全校
$('#l_class').on('click','.add_class span',function(){
	getData('','')
        $('.now_s').hide();
        $('.title_table tr td').eq(1).find('span').text('全校 ')
        $('.title_con>div').fadeOut(100);
        $('.z').fadeOut(100);
        $('.concon').css('overflow','auto');
        
})
//周
$('#select_week').on('click','li span',function(){
     if($('#select_week').attr('data_id')!=''){
    	 //周
    	 begin = $(this).parent().attr('data-prev');
    	end = $(this).parent().attr('data-next');
   		getData($('.now_s').find('.avcit').parent().attr('data_id'),$('.l_class').find('.avcit').parent().attr('data-gradeid'));
     }else{
         getLastDay($(this).parent().attr('date-year'),$(this).parent().attr('data-month'))
    	getData($('.now_s').find('.avcit').parent().attr('data_id'),$('.l_class').find('.avcit').parent().attr('data-gradeid'));
     }
})

//ajax数据
//年级
function class_l() {
	var class_l_data ={schoolId:schoolId, schoolYear:schoolYear,userId:userId,role:role};
	console.log(class_l_data)
    $.ajax({
        type: "GET",
        url: url+"/school/teacher/team/list/jsonp",
        data:class_l_data ,
        jsonp:'jsonpCallback',
        dataType: "jsonp",
        success: function(data){
            console.log(data)
            html_data = data.data;
            class_l_list(html_data);
        }
    });
}
function class_l_list(data){
    var html  = '';
    $.each(data,function (i,v) {
        html +='<li data-data="'+i+'" data-gradeId = "'+v.gradeId+'"><span data-teamId="">'+v.gradeName+'</span></li>';
    });
    if(role=='CLASS_MASTER'){
    	setTimeout(function(){$('#l_class').find('li').eq(0).find('span').click();$('.now_s').find('li').eq(0).remove();$('.now_s').find('li').eq(0).find('span').click();},500)
    }else{
        html+='<li data-gradeId="" class="add_class"><span class="avcit">全校</span></li>';
    }
    $('#l_class').html(html)
}

//学期~~~学年
Semester()
function Semester() {
    $.ajax({
        type: "GET",
        url: url+"/school/basic/term/listAll/jsonp",
        data:{schoolId:schoolId} ,
        jsonp:'jsonpCallback',
        dataType: "jsonp",
        success: function(data){
        	console.log(data)
            Semester_list(data.data)
        }
    });
}
function Semester_list(data) {
    var html  = '';
    $.each(data,function (i,v) {
        html+='<div class="xir"><span>'+v.schoolYearName+'</span></div>';
        html+='<ul class="semester" data-schoolYear="'+v.schoolYear+'">'
        $.each(v.schoolTermlist,function (u,t) {
            if(t.isCurrent==1){
                html+='<li data-schoolTermCode="'+t.schoolTermCode+'"><span data-begin="'+t.beginDate+'" data-end="'+t.finishDate+'" class="avcit">'+t.termName+'</span></li>';
                begin = t.beginDate;
                end = t.finishDate;
            }else{
            html+='<li data-schoolTermCode="'+t.schoolTermCode+'"><span data-begin="'+t.beginDate+'" data-end="'+t.finishDate+'">'+t.termName+'</span></li>'
            }
        })
           html+=   ' </ul>';
    });
    $('.semester_div').html(html);
    schoolYear = $('.semester_div').find('li').find('.avcit').parent().parent().attr('data-schoolyear');
    class_l()
	getData('','');
    $.getNewMonth({
        setId:'#select_week',
        begin:begin,
        end:end,
    })
}

function getData(teamId,gradeId,startDay,endDay){
	 var teamIds =teamId; //班级
	 if(!teamIds){
		 teamIds='';
	 }
	 /*var gradeId =""; //年级
	var schoolId =""; //学校
	var startDay =""; //结束时间
	var endDay ="";	//开始时间 */
	console.log({teamId:teamIds, gradeId:gradeId, schoolId:schoolId, startDay:begin, endDay:end, termCode:$('.semester_div').find('.avcit').parent().attr('data-schooltermcode')})
	$.post(
		"/com/bpBwAttendances/attendanceStatistics", 
		{teamId:teamIds, gradeId:gradeId, schoolId:schoolId, startDay:begin, endDay:end, termCode:$('.semester_div').find('.avcit').parent().attr('data-schooltermcode')},
		function(data){
			console.log(data)
			var dataObj=eval("("+data+")");
			console.log(dataObj.lateNum)
			
			later = dataObj.lateNum;
			early = dataObj.outEarlyNum;
			absent = dataObj.absentNum;
			var laterPercent = later/(later+early+absent)*100;
			var earlyPercent =  early/(later+early+absent)*100;
			var absentPercent =  absent/(later+early+absent)*100;
			$('#later_sun').text(later+'人')
			$('#early_sun').text(early+'人')
			$('#absent_sun').text(absent+'人')
			$('#later').find('span').height(laterPercent+'%')
			$('#early').find('span').height(earlyPercent+'%')
			$('#absent').find('span').height(absentPercent+'%')
			console.log(laterPercent+'=='+earlyPercent+'=='+absentPercent)
		}
	); 	
}
function getLastDay(year,month) {
	//alert(4)
    var new_year = year;    //取当前的年份
    var new_month = month++;//取下一个月的第一天，方便计算（最后一天不固定）
    if(month>12) {
        new_month -=12;        //月份减
        new_year++;            //年份增
    }
    var new_date = new Date(new_year,new_month,1);                //取当年当月中的第一天
    begin = new_year+"-"+new_month+"-"+01;
    end = new_year+"-"+new_month+"-"+(new Date(new_date.getTime()-1000*60*60*24)).getDate();
    console.log("开始 一天"+begin+"最后一天"+end);//获取当月最后一天日期
   
}

//周次
$.getWeek = function(options) {
    var defOption = {
        "selector" : "#select_week",
        "begin" : "",
        "end" : "",
        "isClear" : false,
        "isSelectCurrentWeek" : true,
        "today" : "",
        "afterHandler" : function() {

        },
        "firstOptionTitle" : "",
        "clearedOptionTitle" : "",
    };
    options = $.extend({}, defOption, options || {});
    var selector = $(options.selector);
    selector.html("");
    if (options.isClear) {
        var chznSelector = selector.siblings(options.selector + "_chzn");
        if (chznSelector != null && chznSelector.length > 0) {
            selector.show().removeClass("chzn-done");

            var clearedOption = $("<option value=''>" + options.clearedOptionTitle + "</option>");
            selector.append(clearedOption);
            chznSelector.remove();
        }
        return;
    }
    var begin = options.begin;
    var end = options.end;
    var chznSelector = selector.siblings(options.selector + "_chzn");
    if (chznSelector != null && chznSelector.length > 0) {
        selector.show().removeClass("chzn-done");
        chznSelector.remove();
    }
//    selector.append("<option value=''>" + options.firstOptionTitle + "</option>");
    Date.prototype.format = function() {
        var s = '';
        s += this.getFullYear() + '-';// 获取年份。
        s += (this.getMonth() + 1) + "-"; // 获取月份。
        s += this.getDate(); // 获取日。
        return (s); // 返回日期。
    };
    var date = begin; //此处也可以写成 17/07/2014 一样识别    也可以写成 07-17-2014  但需要正则转换
    var day = new Date(Date.parse(date)); //需要正则转换的则 此处为 ： var day = new Date(Date.parse(date.replace(/-/g, '/')));
    var today = new Array('星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六');
    var week = today[day.getDay()];
    //alert(day.getDay()) /*求星期*/
    var i = 0;
// 			var today_data="2016-09-01"
    var ab = begin.split("-");
    var ae = end.split("-");
// 			var at = today_data.split("-");
    var db = new Date();
    db.setUTCFullYear(ab[0], ab[1] - 1, ab[2]);
    var de = new Date();
    de.setUTCFullYear(ae[0], ae[1] - 1, ae[2]);
// 			var dt = new Date();
// 			dt.setUTCFullYear(at[0], at[1] - 1, at[2]);
    var unixDb = db.getTime();
    var unixDe = de.getTime();
// 			var unixDt = de.getTime();
    for (var k = unixDb; k <= unixDe;) {
        i = i + 1;
        //console.log((new Date(parseInt(k))).format()); //输出全部天数
        k = k + 24 * 60 * 60 * 1000;
    }
    var j = 0;/*总共几周*/
    if (i > 7 - day.getDay()) {
        if ((i - (7 - day.getDay())) % 7 != 0) {
            var j = Math.floor((i - (7 - day.getDay())) / 7) + 2
        } else {
            var j = Math.floor((i - (7 - day.getDay())) / 7) + 1
        }
    } else {
        j = 1;
    }
    var date_start = new Date(begin);
    var date_end = new Date(end);
    var date_today;
    if(options.today != "") {
        date_today = new Date(options.today);
    } else {
        date_today = new Date();
    }
    var currentTime = date_today.getTime();

    var date1, date2, date_sun, date_sat;
    for (var k = 1; k <= j; k++) {
        if (k == 1) {
            date1 = (date_start.getFullYear() + "-" + (date_start.getMonth() + 1) + "-" + date_start .getDate())
            data_sat = new Date(date_start);
            data_sat.setDate(date_start.getDate() + 6 - day.getDay());
            date2 = data_sat.getFullYear() + "-" + (data_sat.getMonth() + 1) + "-" + data_sat.getDate();
// 					selector.append('<option data-prev=' + date1 + ' data-next=' + date2 + '>第' + k + '周(' + date1 + '~' + date2 + ')</option>');
        } else if (k < j) {
            date_sat = new Date(data_sat);
            date_sun = new Date(date_sat);
            date_sun.setDate(date_sat.getDate() + 1);
            date1 = (date_sun.getFullYear() + "-"
            + (date_sun.getMonth() + 1) + "-" + date_sun
                .getDate())
            data_sat = new Date(date_sun);
            data_sat.setDate(date_sun.getDate() + 6);
            date2 = data_sat.getFullYear() + "-"
                + (data_sat.getMonth() + 1) + "-"
                + data_sat.getDate();
// 					selector.append('<option data-prev=' + date1 + ' data-next=' + date2 + '>第' + k + '周(' + date1 + '~' + date2 + ')</option>');
        } else {
            date_sat = new Date(data_sat);
            date_sun = new Date(date_sat);
            date_sun.setDate(date_sat.getDate() + 1);
            date1 = (date_sun.getFullYear() + "-"
            + (date_sun.getMonth() + 1) + "-" + date_sun
                .getDate())
            data_sat = new Date(date_end);
            date2 = data_sat.getFullYear() + "-"
                + (data_sat.getMonth() + 1) + "-"
                + data_sat.getDate();
// 					selector.append('<option data-prev=' + date1 + ' data-next=' + date2 + '>第' + k + '周(' + date1 + '~' + date2 + ')</option>');
        }
// 				if (currentTime > new Date(date1).getTime() && currentTime < (new Date(date2).getTime()+86400000) && options.isSelectCurrentWeek) {
        if (currentTime > new Date(Date.parse(date1.replace(/-/g, "/"))).getTime()
            && currentTime < (new Date(Date.parse(date2.replace(/-/g, "/"))).getTime()+86400000)
            && options.isSelectCurrentWeek) {
            selector.append('<li data-prev=' + date1 + ' data-next=' + date2 + '><span class="avcit">第' + k + '周</span></li>');//(' + date1 + '~' + date2 + ')
        } else {
            selector.append('<li data-prev=' + date1 + ' data-next=' + date2 + '><span>第' + k + '周</span></li>');
        }
    }
    if(date_today.getTime() < date_start.getTime() || date_today.getTime() >= (date_end.getTime()+86400000)){
//        selector.find("option")[1].selected='selected';


    }
//    selector.chosen();
}


/* 简洁月份 */
$.getNewMonth = function(options) {
	var defOption = {
		"selector" : "#select_week",
		"begin" : "",
		"end" : "",
		"isClear" : false,
		"isSelectCurrentWeek" : true,
		"today" : "",
		"afterHandler" : function() {
		
		},
		"firstOptionTitle" : "请选择",
		"clearedOptionTitle" : "请选择",
	};
	options = $.extend({}, defOption, options || {});
	var selector = $(options.selector);
	selector.html("");
	if (options.isClear) {
		var chznSelector = selector.siblings(options.selector + "_chzn");
		if (chznSelector != null && chznSelector.length > 0) {
			selector.show().removeClass("chzn-done");
			
			var clearedOption = $("<li>" + options.clearedOptionTitle + "</li>");
			selector.append(clearedOption);
			chznSelector.remove();
		}
		return;
	}
	var begin = options.begin;
	var end = options.end;
	date1 = begin.split('-');
	date2 = end.split('-');
	if(date1[1]<10){
		date1[1]=parseInt(date1[1].substring(1,2));
	}
	var month=["一","二","三","四","五","六","七","八","九","十","十一","十二"];
	if(date1[1]<date2[1]){
	  for(var i=date1[1];i<=date2[1];i++){
		  j=month[i-1]
		  selector.append("<li date-year="+date1[0]+" data-month="+i+"><span>"+j+"月份</span></li>");
	  };
	}else{
	    for(var i=date1[1]; i<=12; i++){
	    	j=month[i-1]
	    selector.append("<li date-year="+date1[0]+" data-month="+i+"><span>"+j+"月份</span></li>");
	  };
	  for(var i=1; i<=date2[1]; i++){
		  j=month[i-1]
		  selector.append("<li date-year="+date2[0]+" data-month="+i+"><span>"+j+"月份</span></li>");
	  };
	}
}
	
</script>
