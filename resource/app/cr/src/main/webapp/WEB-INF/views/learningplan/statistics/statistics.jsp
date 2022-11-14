<%@ page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%> 
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/qyjx/css/statistics/zj_swiper.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/qyjx/css/statistics/swiper.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/qyjx/css/statistics/a3-3.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/qyjx/plugin/percircle/percircle.css" />
<script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/res/qyjx/js/swiper.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
<script src="${pageContext.request.contextPath}/res/qyjx/plugin/percircle/percircle.js"></script>
<meta name="viewport" content="user-scalable=no">
<meta name="viewport" content="initial-scale=1,maximum-scale=1">
<title>导学案统计</title>
    <style>
        .three_circle div,three_circle p{
            font-size: 117px;
        }
        .yxx{
        	color: #299ff6;
        	font-weight: bold;
        }
        .wxx{
        	color: #ff544b;
        	font-weight: bold;
        }
    </style>
</head>
<body >
<div class="a3-3" style="overflow: hidden">
	<!-- 	头部                  -->
	<div class="a3-3-top">
        <span class="fl banji">统计 - ${teamName}</span>
        <ul>
            <li class="select1"><a href="javascript:void(0)">完成情况</a></li>
            <li><a href="javascript:void(0)">答题统计</a></li>
            <li><a href="javascript:void(0)">单题分析</a></li>
        </ul>
        <span class="fr anniu">
        <c:if test="${!empty unitList }">
            <a href="javascript:void(0)" class="btn-blue mgr10"  id="btn" onclick="tj();">统计</a>
        </c:if>
<!--             <a href="javascript:void(0)" class="btn-lightGray">返回</a> -->
        </span>
    </div>
    <!-- 	下面统计滑动区域                  -->
    <div class="a3-3-content">
    	<!--完成情况 star-->
        <div class="mk finish-situation " style="display: block">
            <!-- Swiper -->
            <div class="" style="width: 1220px;margin:0 auto;max-width:100%;">
                <div class="swiper-wrapper" style="display:block;">
                    <div class=" swiper_1" style="width:100%;">
                    <div class="first_d">
                        <div class="pm_div">
                            <ul>
                                <li class="l1"><a href="javascript:void(0)"><p class="p2">年级排名</p><p class="p1">2</p></a></li>
                                <li class="l2"><a href="javascript:void(0)"><p class="p2">年级完成率</p><p class="p1">45%</p></a></li>
                                <li class="l3"><a href="javascript:void(0)"><p class="p2">本班完成率</p><p class="p1">80%</p></a></li>
                                <li class="l4"><a href="javascript:void(0)"><p class="p2">完成人数</p><p class="p1">52</p></a></li>
                            </ul>
                            <div class="clear"></div>
                        </div>
                        <div class="first_one">
                            <div class="wrapper-demo">
                                <div class="dd wrapper-dropdown-2" tabindex="1">预习自测</div>
                                <ul class="dropdown u">
                                    <li class="unit">所有任务</li>
                                </ul>
                            </div>
                            <div class="bjwcl_pmb xswcjd" >
                                <p class="title">学生完成进度</p>
                                <div class="wcrs"><p class="p1">已完成<span>12</span>人</p><p class="p2">未完成<span>8</span>人</p></div>
                                <div id="first_container_1" class="first_container_1" style="margin: 0 auto;min-height:400px">
                                	<ul>
                                	</ul>
                                </div>
                                <a href="javascript:void(0)" class="view_more" data-id="1" id="wcqk_seeMore" style="display:none">查看更多</a>
                            </div>
                        </div>
                        </div>
                    </div>
                    <div class=" swiper_2">
                        <div class="bjwcl_pmb">
                            <p class="title">班级完成率排名表</p>
                            <div class="table_1" id="table_1">
                                <table class="table">
                                    <thead><tr><th>名次</th><th>班级</th><th>完成率</th><th>完成人数</th></tr></thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                            <%--<a href="javascript:void(0)" class="view_more" data-id="2" >查看更多</a>--%>
                        </div>
                    </div>
                </div>
                <!-- Add 右侧分页 -->
                <%--<div class="swiper-pagination"></div>--%>
            </div>
        </div>
        <!--完成情况 end-->
        <!--答题统计 start-->
        <div class="mk answer-statistics">
        	<div class="as_top dttj_top" style="height:300px">
                <div class="wrapper-demo">
                    <div  class=" dd wrapper-dropdown-2" tabindex="1">按习题 </div>
                    <ul class="dropdown  " style="width:120px;left:0;">
                        <li class="unit_1">按学生</li>
                        <li class="unit_1">按习题</li>
                    </ul>
                </div>
                <div class="wrapper-demo">
                    <div  class=" dd wrapper-dropdown-2" tabindex="1">所有试卷 </div>
                  <ul class="dropdown u">
                    <c:forEach items="${unitList}" var="unit">
                    <li data-examId="${unit.examId}"  data-unitId="${unit.unitId}" data-code="${unit.joinExamCode}" class="unit">${unit.unitTitle}</li>
                    </c:forEach>
                      <li class="unit">所有试卷</li>
                    </ul>
                </div>
                <div class="clear"></div>
                <div class=" bjwcl_pmb" style="margin-top:25px;">
                    <div class="dt dt2" style="padding-top: 30px;">
                <div class="three_circle">
                </div>
                    </div>
                </div>
            </div>
            <div class="swiper-slide bjwcl_pmb" style="padding:0">
            <div class="dt dt2" style="padding-top: 30px;">
                <div class="all_div">
                    <div  class="div1"><table class="table"><thead></thead><tbody></tbody></table></div>
                    <div class="div1"  style="display:none;"><div id="second_container_1" style="margin: 0 auto"></div></div>
                </div>
                <%----%>
            </div>
            </div>
            <!-- Swiper -->
            <div class="dg_div swiper-slide" style="display: none">
                <div class="div2 bjwcl_pmb" ><table class="table"><thead><tr><th>序号</th><th>学生姓名</th><th>成绩</th><th>用时</th><th>作答情况</th></tr></thead><tbody></tbody></table></div>
                <div class="div2" style="display:none">
                    <div class=" dkdttj" >
                        <div class=" dttj_wapper">
                        </div>
                        <!-- Add Pagination -->
                        <div class="swiper-pagination" style="top:8%"></div>
                    </div>
                </div>
            </div>
        </div>
        <!--答题统计 end-->
        <!--单题分析 start-->
        <div class="mk single-analysis">
            <div class="as_top">
                <div class="subject_info">
                    <span>题目信息 —</span>
                    <ul>
                        <li>题号：<span id="th">1</span></li>
                        <li>类型：<span id="qtype">单选题</span></li>
                        <li>分值：<span id="qscore">5</span></li>
                        <li> 作答人数：<span  id="qcount">20人</span></li>
                    </ul>

                </div>
                <div class="wrapper-demo">
                    <div  class="small dd wrapper-dropdown-2" tabindex="1">1 </div>
                    <ul class="dropdown small-ul  unit2">
                        <li>1</li>
                        <li>2</li>
                        <li>3</li>
                    </ul>
                </div>
                <div class="wrapper-demo">
                    <div  class=" dd wrapper-dropdown-2" tabindex="1">试卷单元1
                    </div>
                    <ul class="dropdown u1">
                    <c:forEach items="${unitList}" var="unit">
                    <li data-examId="${unit.examId}" data-unitId="${unit.unitId}"  data-code="${unit.joinExamCode}"  class="unit1">${unit.unitTitle}</li>
                    </c:forEach>
                    </ul>
                </div>

                <div class="clear"></div>
            </div>
            <!-- Swiper -->
            <div class="swiper-container">
                <div class="swiper-wrapper">
                    <div class="swiper-slide" id="dttj">
                        <div class="dt dt4">
                            <p class="title">答题项应答次数分布图</p>
                            <div id="container1" style="width:876px;margin: 0 auto"  ></div>
                        </div>
                    </div>
                    <div class="swiper-slide">
                        <div class="dt dt4">
                            <p class="title">年级正答率对比图</p>
                            <div id="container2" style="margin: 0 auto"></div>
							<a href="javascript:void(0)" class="view_more" data-id="3" onclick="view_more()">查看更多</a>
                        </div>
                    </div>
                    <div class="swiper-slide">
                        <div class="dt dt3 dt_main">
                            <p class="title">题目详情</p>
                            <div class="tm tm_div">
                                <div class="timu">
                                <span>
                                    1、（5分）人道亲亲也。亲亲故尊祖，尊祖故敬宗，敬宗故收族，收族故宗庙严，宗庙严故重社稷，重社稷故爱百姓……”
材料主要反映了中国古代（      ）
                                </span>
                                    <div class="timu-choose">
                                        <ul>
                                            <li class="choose">
                                                A、分封制度
                                            </li>
                                            <li>
                                                B、分封制度
                                            </li>
                                            <li>
                                                C、分封制度
                                            </li>
                                            <li>
                                                D、分封制度
                                            </li>
                                        </ul>

                                    </div>
                                </div>
                                <div class="answer-and-analysis" >
                                    <p>
                                        <span class="color-2ba0f7">【答案】</span>
                                        <span class="color1d9">A</span>
                                    </p>
                                    <p>
                                        <span class="color-2ba0f7">【解析】</span>
                                        <span class="color666">巴拉巴拉</span>
                                    </p>
                                </div>
                            </div>
                        <a href="javascript:void(0)" class="view_more" data-id="4" onclick="view_more()">查看更多</a>
                        </div>
                    </div>
                </div>
                <!-- Add Pagination -->
                <div class="swiper-pagination"></div>
            </div>
            <%--错题排行榜--%>
            <div class="wrong_question">
                <p class="p1">错题排行榜</p>
                <table class="ct_1"><thead><tr><th>题号</th><th>错误率</th></tr></thead></table>
                <div class="ct_div">
                    <table class="ct_2">
                        <tbody>
                        </tbody></table>
                </div>
            </div>
        </div>
        <!--单题分析 end-->
    </div>
        <div class="no_data paper" style="display:none;">
    	<button class="btn btn-blue">统计</button>
    	<p>暂无数据，点击统计按钮统计一下</p>
    </div>
</div>
<div class="more_div" style="display:none;">
	<!-- 	头部                  -->
	<div class="a3-3-top">
        <span class="fl banji"></span>
        <span class="fr anniu">
            <a href="javascript:void(0)" class="btn-lightGray" onclick="back_current();">返回</a>
        </span>
    </div>
    <div class="swiper-slide">
        <div class="bjwcl_pmb">
            <p class="title">学生完成进度</p>
            <div id="first_container1" class="first_container_1" style="margin: 0 auto;min-height:400px;display:block">
            	<ul></ul>
            </div>
        </div>
	    <div class="bjwcl_pmb">
             <p class="title">班级完成率排名表</p>
              <table class="table" id="table_3">
                  <thead><tr><th>学号</th><th>姓名</th><th>完成率</th><th>完成人数</th></tr></thead>
                  <tbody>
                  </tbody>
              </table>
	    </div>
	    <div class="bjwcl_pmb">
	    <div class="dt dt4">
	    	 <p >&nbsp;</p>
             <div id="Container2" style="margin: 0 auto;min-height:400px;"></div>
             </div>
	    </div>
	    <div class="bjwcl_pmb">
	    	 <div class="dt dt3">
              <p>&nbsp;</p>
              <div class="tm ">
                 
              </div>
          </div>
	    </div>
        <div class="bjwcl_pmb dxa_wcqk">
            <p class="title"><span style="color:#2da1f8">赵志刚</span>的导学案完成情况</p>
            <table class="table" id="table_5">
                <thead><tr><th>序号</th><th>学习任务</th><th>完成状态</th><th>完成时间</th></tr></thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
    <div class=" stxq" style="display:none">
        <iframe src="" allowFullScreen  marginheight="0" marginwidth="0"
                frameborder="0" scrolling="yes" style="width:100%;"></iframe>
    </div>
</div>
<!-- 统计失败了 -->
<div class="tj_fail" style="display:none">
	<p>统计失败了啦~~</p>
</div>
<!-- 五分钟内只能一次 -->
<div class="only_one" style="display:none"><p>统计在五分钟之内只能执行一次请稍后再试</p></div>
<!-- 重新统计 -->
<div class="again_tj" style="display:none"><p>重新统计需要等待较长时间统计后如果有数据改动（如学生提交答卷、重新打分等）才进行重新统计是否继续？</p></div>
<!-- 正在统计中 -->
<div class="tjing" style="display:none">
	<p class="word"><span>统计进行中</span>，请稍候</p>
	<div class="jdt_tu"><p style="width:0%"></p><span>0%</span></div>
</div>
<!-- 统计完成 -->
<div class="tj_success" style="display:none">
	<p class="word"><span>统计成功</span></p>
	<div class="jdt_tu"><p style="width:100%"></p><span style="left:96%">100%</span></div>
</div>
<%--饼图提示--%>
<div class="bt_ry" style="display: none">
    <p class="p1">统计项：<span class="s1">错误</span> &nbsp;&nbsp;作答人数：<span class="s2">10</span>人 &nbsp;&nbsp;作答比例：<span class="s3">10</span>%</p>
    <div class="p2"><span>潘子</span><span>治刚</span><span>尹亮</span></div>
</div>
<%--第一次进入提示--%>
<div class="first_ts">
    <a href="javascript:void(0)"></a>
</div>
<script>
function isStatisticsSuccess(id) {
    var flag;
    //定时去检查状态。
    $.ajax({
        async: false,
        type: "post",
        url: "${pageContext.request.contextPath}/statistics/check/tj",
        data: {"stId": id},
        success: function (data) {
            flag = data;
//             alert(data);
            if (data == 1) {
//                     clearInterval(timer1);

                   	layer.open({
		  type: 1,
		  shade: false,
		  area: ['510px', '255px'],
		  title: '统计', //不显示标题
// 		  time: 2000,
		  content: $('.tj_success'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
              var taskId=${param.taskId};
              var number=$('.a3-3-top ul li.select1').index()+1;
              window.location.href="${pageContext.request.contextPath}/statistics/lp/tj/index?taskId="+taskId+"&number="+number;
		  },
		});
                var taskId=${param.taskId};
                var number=$('.a3-3-top ul li.select1').index()+1;
                setTimeout('window.location.href="${pageContext.request.contextPath}/statistics/lp/tj/index?taskId='+taskId+'&number='+number+'"',2000);
            }
        }
    });
    return flag;
}
//查看更多以后返回
function back_current(){
    $('.stxq').hide();
	$(".more_div").hide();
	$(".more_div .bjwcl_pmb").hide();
	$(".a3-3").show();
	//$(".a3-3-top .banji").text("统计 - ${teamName}");
}
// 查看更多
/* function view_more(){
	var i=$(this).attr("data-id");
	$(".more_div").show();
	alert(i)
	$(".more_div .bjwcl_pmb").eq(i-1).show();
	$(".a3-3").hide();
} */
$(".view_more").click(function(){
	var i=$(this).attr("data-id");
	var name = $(this).siblings('.title').text();
	$(".more_div").show();
	$(".more_div .bjwcl_pmb").eq(i-1).show();
	$(".more_div .bjwcl_pmb").parent().prev().find('.banji').text(name);
	$(".a3-3").hide();
})
var unitId=${unitId};
var code='${code}';
$(function(){
    var stId=${stId};
    var unitId=${unitId};
    if(stId==0&&unitId!=0){
        $('.first_ts').show();
    }
    $('.first_ts a').click(function(){
        $('.first_ts').hide();
    })
    // 第一屏完成跟未完成人数切换
    $('body').on('click','.wcry_list .wcry_top a',function () {
        $(this).siblings().removeClass('on');
        $(this).addClass('on');
        var i=$(this).index();
        $(this).parent().next().children('ul').hide();
        $(this).parent().next().children('ul').eq(i).show();
    });
    //第一屏点击下拉切换所有任务
    $('.first_one ').on('click','.dropdown li',function(){
        var i=$(this).index();
        $('.first_one .bjwcl_pmb').hide();
        $('.bjwcl_pmb').eq(i).show();
        if(i>0){
            $(".wcry_list .wcry_bottom").css('height',first_height+10);
        }
        $('.first_one .wrapper-dropdown-2').text($(this).text())
    });
// 	滑动插件初始化
	var swiper = new Swiper('.swiper-container', {
	    pagination: '.swiper-pagination',
	    direction: 'vertical',
	    observer:true,
	    observeParents:true,
	    slidesPerView: 1,
	    paginationClickable: true,
	    spaceBetween: 30,
	    threshold : 100,
	     mousewheelControl: true
	});
	//顶部点击菜单
	 $('.a3-3-top ul li').click(function(){
		 var i=0;
		 <c:if test="${empty unitList}">
		 i=1;
		 </c:if>
		 if(i==1){
			 return false;
		 }
			if($(this).hasClass("disabled")){
		 		
		 	}else{
		 		var aa = $(this).index();
		        $('ul li').siblings().removeClass('select1');
		        $(this).addClass('select1');
		        $('.a3-3-content').find('.mk').css('display','none');
		        $('.a3-3-content').find('.mk').eq(aa).css('display','block');
		 	}
		 	$('.a3-3-top ul li').addClass("disabled");
			setTimeout(function() {
				$('.a3-3-top ul li').removeClass("disabled");
			}, 300);//0.3秒后才能点击
	    });

	//下拉框
    $(".dd").click(function(){
    	if($(this).hasClass("active")){
    		$(".wrapper-dropdown-2").removeClass('active');
        	$(".dropdown").removeClass('on');
    	}else{
    		$(".wrapper-dropdown-2").removeClass('active');
        	$(".dropdown").removeClass('on');
        	$(this).addClass('active');
            $(this).next().addClass("on");
    	}
        return false;
    });
    $(".unit1").click(function(){
        var h=$(this).html();
        $(this).parent().prev().html(h);
    	var unit2=$('.unit2');
    	unit2.html("");
    	var examId=$(this).attr("data-examId");
    	var val={};
    	val.examId=examId;
    	unitId=$(this).attr("data-unitId");
    	code=$(this).attr("data-code");
    	 $.ajax({
	           url: "${pageContext.request.contextPath}/statistics/th",
	           type: "POST",
	           data: val,
	           async: false,
	           success: function(data) {
	        	   data=eval(data);
	        	   var array=new Array();
	        	   array=data;
	        	   for(var i=0;i<array.length;i++){
// 	        		   console.log(array[i]);
	        		   unit2.append("<li class='unit3'  data-id='"+array[i].id+"' data-uuid='"+array[i].questionUuid+"' data-score='"+array[i].fullScore+"' data-count='"+array[i].answerCount+"' data-type='"+array[i].questionType+"'  data-examId='"+array[i].examId+"'>"+array[i].pos+"</li>");
	        	   }
	        	   $('.unit2 li').eq(0).click();
                   //$('.wrong_question .ct_div').niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
	           }
	       });
        $.getJSON("${pageContext.request.contextPath}/statistics/tj/questionRank?examId="+examId, function (wrong_data){
            var tbody_list=''
            $('.wrong_question .ct_2 tbody').empty();
            for(var i=0;i<wrong_data.list.length;i++){
                tbody_list=tbody_list+'<tr onclick="go_question('+wrong_data.list[i].pos+')"><td>'+wrong_data.list[i].pos+'</td><td>'+wrong_data.list[i].wrong+'</td></tr>'
            }
            $(tbody_list).appendTo($('.wrong_question .ct_2 tbody'));
        })
    });
    $(".dttj_top .unit").click(function(){
        $(".dttj_top .unit_1").eq(0).click();
        var h=$(this).html();
        $(this).parent().prev().html(h);
        //$('.swiper_3').remove();
        var examId=$(this).attr("data-examId");
        unitid=$(this).attr("data-unitid");;
        var colors = ['#0babf5', '#FF4B66'];
        Highcharts.getOptions().colors = Highcharts.map(colors, function (color) {
            return {
                radialGradient: { cx:-0.3, cy: 1.2,r:2 },
                stops: [[0, color], [2, Highcharts.Color(color).brighten(14).get('rgb')] // darken
                ]
            };
        });
        if(h=='所有试卷'){
            $('.swiper-slide.bjwcl_pmb').show();
            $('.dg_div').hide();
            var taskId=${param.taskId}
            $.getJSON("${pageContext.request.contextPath}/statistics/tj/all/question?taskId="+taskId, function (data){
                $('.three_circle').empty()
                var njbfb=Math.floor(data.gradeAvg/data.sumScore*100);
                var bjbfb=Math.floor(data.teamAvg/data.sumScore*100);
                var rsbfb=Math.floor(data.count/data.studentCount*100);
                $('<div class="yq_div"><div class="c100 p'+njbfb+'" id="bluecircle"><p class="p1"><span>'+data.gradeAvg+'</span>分</p><div class="slice"><div class="bar" style="transform: rotate('+(360*njbfb/100)+'deg);"></div><div class="fill"></div></div></div><p class="s2">年级总分平均分</p></div>'+
                    '<div class="yq_div"><div class="c100 p'+bjbfb+' red_div" id="redcircle"><p class="p1"><span>'+data.teamAvg+'</span>分</p><div class="slice"><div class="bar" style="transform: rotate('+(360*bjbfb/100)+'deg);"></div><div class="fill"></div></div></div><p class="s2">班级总分平均分</p></div>'+
                    '<div class="yq_div"><div class="c100 p'+rsbfb+' green" id="greencircle"><p class="p1"><span>'+data.count+'</span>人</p><div class="slice"><div class="bar" style="transform: rotate('+(360*rsbfb/100)+'deg);"></div><div class="fill"></div></div></div><p class="s2">已提交人数</p></div>').appendTo($('.three_circle'));
                var categories_1='',grade_number_1='',team_number_1='';
                categories_1=data.names;
                grade_number_1=data.gradeList;
                team_number_1=data.teamList;
                $('#second_container_1').highcharts({
                    chart: {
                        borderRadius:5,
                        alignTicks: false,
                        type: 'column',
                        spacing: [29, 50, 0, 0]
                    },
                    title: {
                        text: null
                    },
                    //图例
                    legend:{
                        align:'right',
                        verticalAlign:'top',
                        symbolHeight: 16,
                        symbolWidth: 16,
                        symbolRadius: 60,
                    },
                    exporting:{
                        enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示
                    },
                    credits: {
                        enabled:false
                    },
                    xAxis: [
                        {
                            type: "category",
                            categories:categories_1,
                            useHTML:true,
                            title: {
                                text:'',
                                align: 'low',
                                x:-70,
                                y:-20
                            },
                            lineWidth: 0,
                            labels:{
                                y:25
                            },
                            tickWidth:0,
                            gridLineWidth:0
                        }
                    ],
                    yAxis: [
                        {
                            title: {
                                "text": "分数",
                                align: 'high',
                                rotation:0,
                                x:45,
                                y:-20
                            },
                            min:0,
                            // labels: {
                            //     format: '{value} %'
                            // },
                            gridLineDashStyle:'Dash',
                            gridLineColor:'#d6d6d6'
                        }
                    ],
                    //数据点提示框
                    // tooltip: {
                    //     headerFormat: '<span style="font-size:10px">第{point.key}题</span><table>',
                    //     pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    //     '<td style="padding:0"><b>{point.y:.1f} %</b></td></tr>',
                    //     footerFormat: '</table>',
                    //     shared: true,
                    //     useHTML: true
                    // },
                    //针对不同类型图表的配置
                    plotOptions: {
                        column: {
                            pointPadding: 0.2,
                            borderWidth: 0,
                            groupPadding:0.2
                        },
                        series: {
                            borderRadius: 7,
                        }
                    },
                    //数据列
                    series: [
                        {
                            name: "年级",
                            data: grade_number_1
                        },
                        {
                            name: "本班",
                            data: team_number_1
                        }
                    ]
                });
                var tab_list=data.tab;
                $('.all_div .div1 table thead,.all_div .div1 table tbody').empty();
                for(var i=0;i<tab_list.length;i++){
// 		    console.log(array1[i].teamName);
                    if(i==0){
                        var thead_list='';
                        for(var j=0;j<tab_list[0].length;j++){
                            thead_list=thead_list+'<th>'+tab_list[0][j]+'</th>'
                        }
                        $('<tr>'+thead_list+'</tr>').appendTo($('.all_div .div1 table thead'));
                    }else{
                        var tbody_list='';
                        for(var k=0;k<tab_list[i].length;k++){
                            if(tab_list[i][k]=='-1.0'){
                                tab_list[i][k]='<span style="color:red">缺考</span>';
                            }
                            tbody_list=tbody_list+'<td>'+tab_list[i][k]+'</td>'
                        }
                        $('<tr>'+tbody_list+'</tr>').appendTo($('.all_div .div1 table tbody'));
                    }
                }
            })
        }else{
        	//答题统计 单科
            $('.div2 .swiper_3').remove();
            $('.swiper-slide.bjwcl_pmb').hide();
            $('.dg_div').show();
            $.getJSON("${pageContext.request.contextPath}/statistics/tj/question?examId="+examId, function (data){
                $('.three_circle').empty()
                var njbfb=Math.floor(data.gradeAvgScore/data.sumScore*100);
                var bjbfb=Math.floor(data.teamAvgScore/data.sumScore*100);
                var rsbfb=Math.floor(data.count/data.studentCount*100);
                $('<div class="yq_div"><div class="c100 p'+njbfb+'" id="bluecircle"><p class="p1"><span>'+data.gradeAvgScore+'</span>分</p><div class="slice"><div class="bar" style="transform: rotate('+(360*njbfb/100)+'deg);"></div><div class="fill"></div></div></div><p class="s2">年级平均分</p></div>'+
                    '<div class="yq_div"><div class="c100 p'+bjbfb+' red_div" id="redcircle"><p class="p1"><span>'+data.teamAvgScore+'</span>分</p><div class="slice"><div class="bar" style="transform: rotate('+(360*bjbfb/100)+'deg);"></div><div class="fill"></div></div></div><p class="s2">班级平均分</p></div>'+
                    '<div class="yq_div"><div class="c100 p'+rsbfb+' green" id="greencircle"><p class="p1"><span>'+data.count+'</span>人</p><div class="slice"><div class="bar" style="transform: rotate('+(360*rsbfb/100)+'deg);"></div><div class="fill"></div></div></div><p class="s2">已提交人数</p></div>').appendTo($('.three_circle'));
                // $('#bluecircle')
                //答题统计的柱状图
                var categories_number,categories_rank,grade_number,team_number,k;
                k=Math.ceil(data.number.length/15);
                for(var i=0;i<k;i++){
                    var k1=15*i;
                    var k2=15*(i+1);
                    var k3=data.number.length;
                    categories_number=data.number.slice(k1,k2);
                    categories_rank=data.rank.slice(k1,k2);
                    grade_number=data.gradeRate.slice(k1,k2);
                    team_number=data.teamRate.slice(k1,k2);
                    var categories1=categories_number;
                    var categories2=categories_rank;
                    if(i==k-1){
                        $("<div class='swiper-slide swiper_3'><div class='dt dt2' style='margin-top:0;padding-bottom: 10px;'><p>【<span class='color-2ba0f7 test-name'>"+h+"</span>】"+(k1+1)+"-"+k3+"题得分率对比图</p><div id='container_"+i+"' class='container' style='width:876px;margin: 0 auto'></div></div></div>").appendTo(".dttj_wapper")
                    }else{
                        $("<div class='swiper-slide swiper_3'><div class='dt dt2' style='margin-top:0;padding-bottom: 10px;'><p>【<span class='color-2ba0f7 test-name'>"+h+"</span>】"+(k1+1)+"-"+k2+"题得分率对比图</p><div id='container_"+i+"' class='container' style='width:876px;margin: 0 auto'></div></div></div>").appendTo(".dttj_wapper")
                    }
                    $(".container").css("height",'400px');
                    $('#container_'+i).highcharts({
                        chart: {
                            borderRadius:5,
                            alignTicks: false,
                            type: 'column',
                            spacing: [29, 50, 0, 0]
                        },
                        title: {
                            text: null
                        },
                        //图例
                        legend:{
                            align:'right',
                            verticalAlign:'top',
                            symbolHeight: 16,
                            symbolWidth: 16,
                            symbolRadius: 60,
                        },
                        exporting:{
                            enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示
                        },
                        credits: {
                            enabled:false
                        },
                        xAxis: [
                            {
                                type: "category",
                                categories:categories1,
                                useHTML:true,
                                title: {
                                    text:'题号',
                                    align: 'low',
                                    x:-60,
                                    y:-20
                                },
                                lineWidth: 0,
                                labels:{
                                    y:25
                                },
                                tickWidth:0,
                                gridLineWidth:0
                            },{
                                type: "category",
                                categories:categories2,
                                title: {
                                    text: '年级排名',
                                    align: 'low',
                                    x:-80,
                                    y:-21
                                },
                                lineWidth: 0,
                                labels: {
                                    y:5,
                                    style:{
                                        color:'#1795ef',
                                        fontWeight:'bold',
                                    }
                                },
                                tickWidth:0,
                                linkedTo:0
                            }
                        ],
                        yAxis: [
                            {
                                title: {
                                    "text": "正答率",
                                    align: 'high',
                                    rotation:0,
                                    x:45,
                                    y:-20
                                },
                                min:0,
                                max:100,
                                labels: {
                                    format: '{value} %'
                                },
                                gridLineDashStyle:'Dash',
                                gridLineColor:'#d6d6d6'
                            }
                        ],
                        //数据点提示框
                        tooltip: {
                            headerFormat: '<span style="font-size:10px">第{point.key}题</span><table>',
                            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                            '<td style="padding:0"><b>{point.y:.1f} %</b></td></tr>',
                            footerFormat: '</table>',
                            shared: true,
                            useHTML: true
                        },
                        //针对不同类型图表的配置
                        plotOptions: {
                            column: {
                                pointPadding: 0.2,
                                borderWidth: 0
                            },
                            series: {
                                borderRadius: 7
                            }
                        },
                        //数据列
                        series: [
                            {
                                name: "年级",
                                data: grade_number
                            },
                            {
                                name: "本班",
                                data: team_number
                            }
                        ]
                    });
                }
                var tab_list=data.studentList;
                $('.dg_div .div2 table tbody').empty();
                for(var i=0;i<tab_list.length;i++){
                    if(tab_list[i].score=='-1.0'){
                        tab_list[i].score='缺考';
                    }
                    if(tab_list[i].sum_time=='-1.0'){
                        tab_list[i].sum_time='缺考';
                    }else{
                        /**
                         * 时间秒数格式化
                         * @param s 时间戳（单位：秒）
                         * @returns {*} 格式化后的时分秒
                         */
                        var s=tab_list[i].sum_time;
                        var t;
                        if(s > -1){
                            var hour = Math.floor(s/3600);
                            var min = Math.floor(s/60) % 60;
                            var sec = s % 60;
                            if(hour < 10) {
                                t = '0'+ hour + ":";
                            }else{
                                t = hour + ":";
                            }
                            if(min < 10){t += "0";}
                            t += min + ":";
                            if(sec < 10){t += "0";}
                            t += sec.toFixed(0);
                        }
                        tab_list[i].sum_time=t;
                    }
                    if(tab_list[i].score=='缺考'){
                    	$('<tr><td>'+tab_list[i].number+'</td><td>'+tab_list[i].name+'</td><td style="color:red">'+tab_list[i].score+'</td><td style="color:red">'+tab_list[i].sum_time+'</td><td><a href="javascript:void(0)" style="color:#999999;cursor: not-allowed;">点击进入</a></td></tr>').appendTo($('.dg_div .div2 table tbody'));
                    }else{
                    	$('<tr><td>'+tab_list[i].number+'</td><td>'+tab_list[i].name+'</td><td>'+tab_list[i].score+'</td><td>'+tab_list[i].sum_time+'</td><td><a href="javascript:void(0)" data-id='+tab_list[i].userId+' onclick="preview_paper('+tab_list[i].userId+')">点击进入</a></td></tr>').appendTo($('.dg_div .div2 table tbody'));
                    }
                    
                }
            })
        }
    });
    $('.dttj_top .unit_1').click(function(){
        $(this).parent().prev().text($(this).text());
            var i=$(this).index();
            if($('.dttj_top .wrapper-demo').eq(1).children('.dd').text()=='所有试卷'){
                $('.all_div .div1').hide();
                $('.all_div .div1').eq(i).show();
            }else{
                $('.dg_div .div2').hide();
                $('.dg_div .div2').eq(i).show();
            }
    });

    $("body").on('click','.unit2  li',function(){
        var h=$(this).html();
        $(this).parent().prev().html(h);
        $('.swiper-pagination span').eq(0).trigger('click');
        var aa = $(this).parents('.answer-statistics').attr('class');
        if(aa){
           var bb =  aa.substr(3);
        }
       if(bb=='answer-statistics'){
          $('.test-name').html(h);
       }
       var cc = $(this).parent().prev().attr('class');
       if(cc){
           var dd = cc.substr(0,5);
           if(dd=='small'){
                $('#th').html(h);
           }
       }
        $(".dropdown").removeClass("on");
        $(".dd").removeClass("active");
        var questionUuid=$(this).attr("data-uuid");
        var eqId=$(this).attr("data-id");
        var count=$(this).attr("data-count");
        var score=$(this).attr("data-score");
        var type=$(this).attr("data-type");
        var examId=$(this).attr("data-examId");
        $('#qcount').html(count+"人");
        $('#qscore').html(score);
        $('#qtype').html(type);
        $(this).addClass("on");
        $(this).siblings().removeClass("on")
        findPos(questionUuid,examId);
        // if(type.indexOf('选')!=-1||type.indexOf('判')!=-1){
        //	$('#dttj').css('display','block');
//    	 单题分析饼图
        $.getJSON("${pageContext.request.contextPath}/statistics/tj/anwers?examQuestionId="+eqId+"&unitId="+unitId, function (data){
            var jsonArr=[];
    	     for (var key in data) 
    	     {
    	    	 jsonArr.push([key,data[key].length])
    	     }
        	//单题分析第一屏
            var color1= ['#03a9f5', '#ff6a88', '#16b2eb', '#6e7cff', '#999999'];
            var color2=['#90caf8', '#fca599', '#2af598', '#fd83ec', '#999999'];
                // Radialize the colors
                Highcharts.getOptions().colors = Highcharts.map(color1, function (color) {
                    var v=color;
                    var j=0;
                    var color_end='#fff';
                    for(var i=0;i<color1.length;i++){
                        if(color1[i]==v){
                            j=i;
                            color_end=color2[j]
                        }
                    }
                    return {
                        linearGradient: { x1: 0, y1: 1, y2: 0, x2: 0 }, //横向渐变效果 如果将x2和y2值交换将会变成纵向渐变效果
                        stops: [
                            [0, color],
                            [1, color_end] // darken
                        ]
                    };
                });
            $('#container1').highcharts({
                chart : {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false
                },
             title : {
                text: ''
            },
            tooltip : {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
           credits:{
                enabled:false // 禁用版权信息
            },
            plotOptions :{
                // pie: {
                //     allowPointSelect: true,
                //     cursor: 'pointer',
                //     dataLabels: {
                //         enabled: true,
                //         format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                //         style: {
                //             color: '#2a9ff7',
                //             fontWeight: 'bold',
                //             fontSize:'18px',
                //             fontFamily:"微软雅黑",
                //             textShadow: '0px 0px 0px #fff'
                //         }
                //     }
                // },
                series: {
                    cursor: 'pointer',
                    events: {
                        click: function (e) {
                            $('.bt_ry .p1 .s1').text(e.point.name);
                            $('.bt_ry .p1 .s2').text(e.point.y);
                            $('.bt_ry .p1 .s3').text(e.point.percentage.toFixed(1));
                            $('.bt_ry .p2').empty();
                            $('.bt_ry .p2').css({'height':'auto','margin-top':'0'})
                            for (var key in data[e.point.name])
                            {
                                $('<span>'+data[e.point.name][key]+'</span>').appendTo($('.bt_ry .p2'))
                            }
                            var index=layer.open({
                                type: 1,
                                shade: [0.01, '#fff'],
                                shadeClose:true,
                                closeBtn: 0,
                                 area: ['50%', '50%'],
                                title: false, //不显示标题
                                content: $('.bt_ry'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
                            });
                            layer.style(index,{
                                backgroundColor:'rgba(255,255,255,0)'
                            });
                            var height_1=$('.bt_ry').height()-60;
                            var height_2=$('.bt_ry .p2').height();
                            if(height_1>height_2){
                                var height_3=(height_1-height_2)/2;
                                $('.bt_ry .p2').css('margin-top',height_3)
                            }else{
                                $('.bt_ry .p2').height(height_1);
                                $('.bt_ry .p2').niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
                            }

                        }
                    }
                }
            },
            series: [{
                type: 'pie',
                name: '作答',
                innerSize: '70%',
                data: jsonArr
            }],

            });
        })
        // }else{
        // 	$('#dttj').css('display','none');
        // }
       //   	单体分析柱状图
   $.getJSON("${pageContext.request.contextPath}/statistics/tj/questionRate?questionUuid="+questionUuid+"&code="+code+"&unitId="+unitId, function (data){
   	 //横向条形图(单题分析第二屏)
       var colors = ['#08aaf5'];
       Highcharts.getOptions().colors = Highcharts.map(colors, function (color) {
           return {
               radialGradient: { cx:0, cy: 1.2,r:2.3 },
               stops: [[0, color], [2, Highcharts.Color(color).brighten(14).get('rgb')] // darken
               ]
           };
       });
       var gs2,three_x,three_y,three_X,three_Y;
       gs2=Math.round(three_height/50);
		three_x=data.names.slice(0,gs2);
		three_y=data.avg.slice(0,gs2);
		three_X=data.names;
		three_Y=data.avg;
		var gs3=three_X.length;
		$("#Container2").css("height",50*gs3);
       $('#container2').highcharts({
           chart: {
               type: 'bar',
               borderRadius:5,
             /*  spacing: [29, 208, 126, 208]*/
           },
           title: {
               text: null
           },
           xAxis: {
               categories: three_x,
               title: {
                   text: null
               },
               tickWidth:0,
               gridLineDashStyle:'Dash',
               lineWidth: 0
           },
           yAxis: {
               min: 0,
               max:100,
               title: {
                   text: null
               },
               labels: {
                   overflow: 'justify',
                   format:'{value}%',
                   step:2,
                   x:-4
               },
               gridLineDashStyle:'Dash',
               gridLineColor: '#d6d6d6'
           },
           legend:{
               enabled:false
           },
           tooltip: {
               valueSuffix: '%'
           },
           plotOptions: {
               bar: {
                   dataLabels: {
                       enabled: true,
                       allowOverlap: true
                   }
               },
               series:{
                   borderRadius:7,
                   pointPadding:0.3
               },
               bar:{
                   dataLabels:{
                       enabled:false, // dataLabels设为true
                   }
               }
           },
           exporting:{
               enabled:false
           },
           credits: {
               enabled: false
           },
           series: [{
               name: '正答率',
               data: three_y,
           }]
       });
       $('#Container2').highcharts({
           chart: {
               type: 'bar',
               borderRadius:5,
             /*  spacing: [29, 208, 126, 208]*/
           },
           title: {
               text: null
           },
           xAxis: {
               categories: three_X,
               title: {
                   text: null
               },
               tickWidth:0,
               gridLineDashStyle:'Dash',
               lineWidth: 0
           },
           yAxis: {
               min: 0,
               max:100,
               title: {
                   text: null
               },
               labels: {
                   overflow: 'justify',
                   format:'{value}%',
                   step:2,
                   x:-4
               },
               gridLineDashStyle:'Dash',
               gridLineColor: '#d6d6d6'
           },
           legend:{
               enabled:false
           },
           tooltip: {
               valueSuffix: '%'
           },
           plotOptions: {
               bar: {
                   dataLabels: {
                       enabled: true,
                       allowOverlap: true
                   }
               },
               series:{
                   borderRadius:7,
                   pointPadding:0.3
               },
               bar:{
                   dataLabels:{
                       enabled:false, // dataLabels设为true
                   }
               }
           },
           exporting:{
               enabled:false
           },
           credits: {
               enabled: false
           },
           series: [{
               name: '正答率',
               data: three_Y,
           }]
       });
   })
    });
    var timer;
    var timer1;
//     $(" .dropdown").mouseout(function(){
//         timer = setTimeout(function () {
//             $(" .dropdown").removeClass('on');
//             $(".dd").removeClass("active");
//                     }, 100);

//     });
//     $(" .dropdown").mouseover(function(){
//         clearInterval(timer);
//         $(this).addClass('on');
//         $(".dd").addClass("active");
//     });
    $(document).click(function()
    {
        $(".dropdown").removeClass("on");
        $(".dd").removeClass("active");
    })
// 	屏幕变化各div高度
	var first_height,first_table,max_table,second_height,three_height;
	function lookHeight(){
         swiper_height=$(window).height()-$('.a3-3-top').height()-10;
         $('.first_ts').height($(window).height())
         $('.stxq iframe').height(swiper_height+3)
        $(".swiper-container").css("height",swiper_height);
        $(".div2 .swiper-container").css("height",(swiper_height-335));
        $(".single-analysis .swiper-container").css("height",(swiper_height-80));
       	//完成情况第一个统计图高度
       	 first_height=swiper_height-214-77-50-30;
       	 // $("#first_container_1").css('height',first_height+40);
        $(".wcry_list .wcry_bottom").css('height',first_height+10);
       	//完成情况table白色高度
       	 first_table=swiper_height-120-80;
       	//table最大高度
       	max_table1=Math.floor((first_table-27)/50)*51+1;
       	 max_table2=Math.floor((first_table-77-10)/50)*51+1;
       	//答题统计 统计图高度
       	 second_height=swiper_height-40-77-40-80-200;
       	//单题分析第一第二统计图高度
       	 three_height=swiper_height-80-77-50
        // $(".table_1").css("max-height",max_table1);
        $(".table_2").css("max-height",max_table2);
       // $(".container").css("height",second_height);
        $("#container1,#container2").css({"height":three_height,"max-height":"500px"});
        $(".tm_div").css("max-height",three_height);
        //$(".swiper_2 .bjwcl_pmb").css("height",first_table);
    }
    window.onresize= lookHeight;
    lookHeight();
	//     调用json文件
	 $.getJSON("${pageContext.request.contextPath}/statistics/lp/tj/stat?taskId=${param.taskId}", function (data){
         wcqk_data=data;
		var first_x=[],first_y=[],first_X=[],first_Y=[],gs1,li;
		var totalPercent=data.totalPercent+"%";
		$(".pm_div ul .l1 a .p1").text(data.teamOrder);
		$(".pm_div ul .l2 a .p1").text(totalPercent);
		$(".pm_div ul .l3 a .p1").text(data.teamPercent+"%");
		$(".pm_div ul .l4 a .p1").text(data.teamCount);
		$('.xswcjd .p1 span').text(data.finishCount);
         $('.xswcjd .p2 span').text(data.unFinishCount);
         $(data.list).each(function(){
        	 if($(this)[0].totalPercent==100){
        		 li = "<li onclick='check_wcqk("+$(this)[0].userId+")'><p class='p_1'>"+ $(this)[0].studentName +"</p><div class='div_2'><p class='p_2 all_ok_jdt' style='width:100%'></p></div><p class='p_3 all_ok_wz'>100%</p></li>";
        	 }else if($(this)[0].totalPercent==0){
        		 li = "<li onclick='check_wcqk("+$(this)[0].userId+")'><p class='p_1'>"+ $(this)[0].studentName +"</p><div class='div_2'><p class='p_2'></p></div><p class='p_3 no_ok_wz'>0%</p></li>";
        	 }else{
        		 li = "<li onclick='check_wcqk("+$(this)[0].userId+")'><p class='p_1'>"+ $(this)[0].studentName +"</p><div class='div_2'><p class='p_2 part_ok_jdt' style='width:"+ $(this)[0].totalPercent +"%'></p></div><p class='p_3 part_ok_wz'>"+$(this)[0].totalPercent+"%</p></li>"; 
        	 }
        	 $('.first_container_1 ul ').append(li);
            // first_X.push($(this)[0].studentName);
            // first_Y.push($(this)[0].totalPercent);
         });
         if($('#first_container_1 ul').height()>=550){
        	 $('#first_container_1 ul').height('550px');
        	 $('#wcqk_seeMore').show();
         }
          var gs1=Math.round(first_height/50);
          if(gs1<8){
              gs1=8;
          }
        // $("#first_container1").css('height',50*first_X.length)
         
          first_x=first_X.slice(0,gs1);
          first_y=first_Y.slice(0,gs1);
		
         // 第一屏切换小测
         for(j in data.unitBoard)
         {
             xc_name=data.unitBoard[j];
             var wc_list='',wwc_list='';
             for(var i=0;i< xc_name.hasFinishList.length;i++){
                 wc_list=wc_list+'<li><a href="javascript:void(0)" onclick="check_wcqk('+xc_name.hasFinishUserIdList[i]+')">'+xc_name.hasFinishList[i]+'</a></li>'
             }
             for(var i=0;i< xc_name.noFinishList.length;i++){
                 wwc_list=wwc_list+'<li><a href="javascript:void(0)" onclick="check_wcqk('+xc_name.noFinishUserIdList[i]+')">'+xc_name.noFinishList[i]+'</a></li>'
             }
             $('<li class="unit">'+j+'</li>').appendTo($('.first_one .wrapper-demo ul'));
             $('<div class="bjwcl_pmb" style="display: none"><p class="title">学生名单</p><div class="wcrs"><p class="p1">已完成<span>'+xc_name.hasFinish+'</span>人</p><p class="p2">未完成<span>'+xc_name.noFinish+'</span>人</p></div><div class="wcry_list">\n' +
                 '                 <div class="wcry_top"><a href="javascript:void(0)" class="on">已完成</a><a href="javascript:void(0)">未完成</a></div>\n' +
                 '             <div class="wcry_bottom">\n' +
                 '                 <ul>'+wc_list+'</ul>' +
                     '             <ul style="display:none">'+wwc_list+'</ul>' +
                 '             </div>\n' +
                 '             </div></div>').appendTo($('.first_one'));
         }

		var color1= ['#03a9f5', '#ff6a88', '#16b2eb', '#6e7cff', '#999999'];
        var color2=['#90caf8', '#fca599', '#2af598', '#fd83ec', '#999999'];
            // Radialize the colors
            Highcharts.getOptions().colors = Highcharts.map(color1, function (color) {
                var v=color;
                var j=0;
                var color_end='#fff';
                for(var i=0;i<color1.length;i++){
                    if(color1[i]==v){
                        j=i;
                        color_end=color2[j]
                    }
                }
                return {
                    linearGradient: { x1: 0, y1: 1, y2: 0, x2: 0 }, //横向渐变效果 如果将x2和y2值交换将会变成纵向渐变效果
                    stops: [
                        [0, color],
                        [1, color_end] // darken
                    ]
                };
            });
	    /*$('#container').highcharts({
            chart : {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: '共'+(unFinishCount+finishCount)+'人',
                align: 'center',
                verticalAlign: 'middle',
                y: 10,
                style:{
    	            "color":"#62bff7",
    	            "fontSize": "24px",
    	            "fontWeight":"bold",
    	            "fontFamily":"微软雅黑"
                }
            },
        tooltip : {
            pointFormat: '{series.name}: <b>{point.y}</b>'
        },
       credits:{
            enabled:false // 禁用版权信息
        },
        plotOptions :{
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                	distance:15,
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.y}',
                    style: {
                        color: '#2a9ff7',
                        fontWeight: 'bold',
                        fontSize:'18px',
                        fontFamily:"微软雅黑",
                        textShadow: '0px 0px 0px #fff'
                    }
                }
            }
        },
        series: [{
        	size: '100%',
            type: 'pie',
            name: '作答',
            innerSize: '70%',
            data:  [
                    ['已完成',   finishCount],
                    ['未完成',   unFinishCount]
                ]
        }],

        });*/

// 	    alert(unFinishCount);
	    var array1=data.teamsOrder;
	    for(var i=0;i<array1.length;i++){
	    	if(array1[i].percent<60){
	    		f_color='f_red';
	    	}else if(array1[i].percent<80){
	    		f_color='f_orange';
	    	}else if(array1[i].percent<90){
	    		f_color='f_blue';
	    	}else{
	    		f_color='f_green';
	    	}
// 		    console.log(array1[i].teamName);
	    	$("<tr><td>"+array1[i].order+"</td><td>"+array1[i].teamName+"</td><td class="+f_color+">"+array1[i].percent+"%</td><td>"+array1[i].finishCount+"</td></tr>").appendTo($("#table_1 tbody,#table_3 tbody"));
	    }
        var array2=data.list;
        for(var i=0;i<array2.length;i++){
        	if(array2[i].hasFinish==='未完成'){
	    		f_color='f_red';
	    	}else {
	    		f_color='f_blue';
	    	}
	    	$("<tr><td>"+array2[i].studentNum+"</td><td>"+array2[i].studentName+"</td><td class="+f_color+">"+array2[i].hasFinish+"</td><td>"+array2[i].finishTime+"</td></tr>").appendTo($("#table_2 tbody,#table_4 tbody"));
        }
	}); 
})
function findPos(uuid,examId){
	var val = {};
	val.questionUuid=uuid;
	val.examId=examId;
    $.ajax({
        url: "${pageContext.request.contextPath}/statistics/tj/qContent",
        type: "POST",
        data: val,
        async: false,
        success: function(data) {
     	 $('.tm').html("").html(data);
        }
    });
}
function tj(){
	   var stId=${stId};
	if(stId==0){
	   var val={};
	   val.objectId=${param.taskId};
	   val.type=4;
	   val.stId=stId;
	     $.ajax({
	           url: "${pageContext.request.contextPath}/statistics/paper/tj",
	           type: "POST",
	           data: val,
	           async: false,
	           success: function(data) {
                   go1();
	        	   timer = window.setInterval(go, speed);
	        	   timer1 = window.setInterval("isStatisticsSuccess("+data+")", 2000);
	           }
	       });
	}else{
		layer.open({
			  type: 1,
			  shade: false,
			  area: ['460px', '255px'],
			  title: '统计', //不显示标题
			  content: $('.again_tj'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
			  cancel: function(){
			    layer.close();
			  },
			  btn: ['确定','取消'],//按钮
				  btn1: function(index, layero){
						var val={};
						val.stId=stId;
						 $.ajax({
					           url: "${pageContext.request.contextPath}/statistics/check/five",
					           type: "POST",
					           data: val,
					           async: false,
					           success: function(data) {
					        	  if(data==='success'){
					        		  val={};
					        		   val.objectId=${param.taskId};
					        		   val.type=4;
					        		   val.stId=stId;
					        		     $.ajax({
					        		           url: "${pageContext.request.contextPath}/statistics/paper/tj",
					        		           type: "POST",
					        		           data: val,
					        		           async: false,
					        		           success: function(data) {
//					        		        	   alert('统计成功');
// 					        		        	   window.location.reload();
                                                   go1();
					        		        	   timer = window.setInterval(go, speed);
					        		        	   timer1 = window.setInterval("isStatisticsSuccess("+data+")", 2000);
					        		           }
					        		       });
					        	  }else{
					        		  layer.open({
					        			  type: 1,
					        			  shade: false,
					        			  area: ['460px', '235px'],
					        			  title: '统计', //不显示标题
					        			  content: $('.only_one'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
					        			  cancel: function(){
					        			    layer.close();
					        			  },
					        			  btn: ['确定'],//按钮
					        				  btn1: function(index, layero){
					        					  layer.close();
					        				  }
					        			});
					        	  }
					           }
					       });
				  },
				  btn2:function(index, layero){
					  layer.close();
				  }
			}); 
		
	}
}
var state=${go};
var j = 0, w = 0, w1 = 0,timer,speed = 2000;
if(state==0){
    go1();
	timer = window.setInterval(go, speed);
	timer1 = window.setInterval("isStatisticsSuccess(${stId})", 2000);
}
function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}
function changeURLPar(destiny, par, par_value)
{
    var pattern = par+'=([^&]*)';
    var replaceText = par+'='+par_value;
    if (destiny.match(pattern))
    {
        var tmp = '/\\'+par+'=[^&]*/';
        tmp = destiny.replace(eval(tmp), replaceText);
        return (tmp);
    }
    else
    {
        if (destiny.match('[\?]'))
        {
            return destiny+'&'+ replaceText;
        }
        else
        {
            return destiny+'?'+replaceText;
        }
    }
    return destiny+'\n'+par+'\n'+par_value;
}
$(function(){
    var unitid='';
	$(" .u li").eq(0).click();
	$('.dttj_top .unit').eq(0).click();
    $('.as_top .unit1').eq(0).click();
    if(getQueryString("number")!=undefined){
        $('.a3-3-top ul li').eq(getQueryString("number")-1).click();
    };
});

function go() {
	w = (j + 1) + '%';
    w1 = (j + 1 - 5) + '%';
    $(".tjing .jdt_tu p").css("width", w);
    $(".tjing .jdt_tu span").text(w);
    $(".tjing .jdt_tu span").css('left', w1)
    if (w == "96%") {
        speed = 9999999;
        window.clearInterval(timer); //进度为100时清除定时器
    }
    if (w == "100%") {
        window.clearInterval(timer); //进度为100时清除定时器
    }
    j = j + 5;

}
function go1(){
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['510px', '255px'],
		  title: '统计', //不显示标题
		  content: $('.tjing'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		});
}
/* //滚动条
$(document).ready(function() {  
	$(".dropdown").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
});  */


function preview_paper(id){
	$('.more_div .banji').text('作答情况');
    var taskId=${param.taskId};
    $('.stxq iframe').attr('src','/cr/statistics/score/details?taskId='+taskId+'&userId='+id+'&unitId='+unitid);
    $(".more_div").show();
    $(".more_div .stxq").show();
    $(".a3-3").hide();
}
var wcqk_data='';
function check_wcqk(id){
    $(".more_div .banji").text("完成进度 - ${teamName}");
    $('.more_div .bjwcl_pmb').hide();
    $(".more_div .bjwcl_pmb.dxa_wcqk tbody").empty();
        $(wcqk_data.list).each(function(i){
            if(id==wcqk_data.list[i].userId){
                $('.dxa_wcqk .title span').text(wcqk_data.list[i].studentName);
                $(wcqk_data.list[i].unitList).each(function(j){
                	if(wcqk_data.list[i].unitList[j].state=='已学习'){
                		$('<tr><td>'+wcqk_data.list[i].unitList[j].number+'</td><td>'+wcqk_data.list[i].unitList[j].name+'</td><td class="yxx">'+wcqk_data.list[i].unitList[j].state+'</td><td>'+wcqk_data.list[i].unitList[j].finishTime+'</td></tr>').appendTo($(".more_div .bjwcl_pmb.dxa_wcqk tbody"))
                	}else{
                		$('<tr><td>'+wcqk_data.list[i].unitList[j].number+'</td><td>'+wcqk_data.list[i].unitList[j].name+'</td><td class="wxx">'+wcqk_data.list[i].unitList[j].state+'</td><td>'+wcqk_data.list[i].unitList[j].finishTime+'</td></tr>').appendTo($(".more_div .bjwcl_pmb.dxa_wcqk tbody"))
                	}
                    
                })
            }
        })
    $(".more_div").show();
    $(".more_div .bjwcl_pmb.dxa_wcqk").show();
    $(".a3-3").hide();
}
function go_question(num){
    $('.unit3').eq(num-1).click();
}

<c:if test="${empty unitList}">
 	$(".a3-3-top ul li").eq(2).hover(function(){
 		layer.tips('<span style="color:#2ca0f8">导学案暂无试卷单元，无统计信息</span>', '.a3-3-top ul li:nth-child(3)', {
 			tips: [1, '#e7faff'], 			  time: 4000
			});
 	})
 	 	$(".a3-3-top ul li").eq(1).hover(function(){
 		layer.tips('<span style="color:#2ca0f8">导学案暂无试卷单元，无统计信息</span>', '.a3-3-top ul li:nth-child(2)', {
 			tips: [1, '#e7faff'], 			  time: 4000
			});
 	})
 	$('#btn').attr("disabled",false); 
</c:if>
</script>
</body>
</html>