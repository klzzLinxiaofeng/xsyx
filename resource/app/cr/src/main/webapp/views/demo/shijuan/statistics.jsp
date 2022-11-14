<%@ page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%> 
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/res/qyjx/css/statistics/zj_swiper.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/qyjx/css/statistics/swiper.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/qyjx/css/statistics/a3-3.css">
<script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/res/qyjx/js/swiper.min.js"></script>
<title>试卷统计</title>
</head>
<body style="overflow: hidden">
<div class="a3-3">
	<!-- 	头部                  -->
	<div class="a3-3-top">
        <span class="fl banji">统计 -｛七年级（1）班｝</span>
        <ul>
            <li class="select1"><a href="javascrip:void(0)">完成情况</a></li>
            <li><a href="javascrip:void(0)">答题统计</a></li>
            <li><a href="javascrip:void(0)">单题分析</a></li>
        </ul>
        <span class="fr anniu">
            <a href="javascrip:void(0)" class="btn-blue mgr10">统计</a>
            <a href="javascrip:void(0)" class="btn-lightGray">返回</a>
        </span>
    </div>
    <!-- 	下面统计滑动区域                  -->
    <div class="a3-3-content">
    	<!--完成情况 star-->
        <div class="mk finish-situation " style="display: block">
            <!-- Swiper -->
            <div class="swiper-container">
                <div class="swiper-wrapper">
                    <div class="swiper-slide swiper_1">
                        <div class="pm_div">
                            <ul>
                                <li class="l1"><a href="javascript:void(0)"><p class="p1"></p><p class="p2">年级排名</p></a></li>
                                <li class="l2"><a href="javascript:void(0)"><p class="p1"></p><p class="p2">年级平均分</p></a></li>
                                <li class="l3"><a href="javascript:void(0)"><p class="p1"></p><p class="p2">班级平均分</p></a></li>
                            </ul>
                            <div class="clear"></div>
                        </div>
                        <div class="bjwcl_pmb">
                            <p class="title">年级各班平均分对比图</p>
                            <div id="container" style="width:80%;margin:0 auto"></div>
                        </div>
                    </div>
                    <div class="swiper-slide swiper_2">
                        <div class="bjwcl_pmb">
                            <p class="title">总体完成情况</p>
                            <div class="table_div">
                                <table class="table" id="table_1">
                                    <thead><tr><th>名次</th><th>班级</th><th>平均分</th><th>应答人数</th></tr></thead>
                                    <tbody>
                                      <!--   <tr><td>1</td><td>初一（1）班</td><td class="f_green">95</td><td>45</td></tr>
                                        <tr><td>1</td><td>初一（1）班</td><td class="f_blue">82</td><td>45</td></tr>
                                        <tr><td>1</td><td>初一（1）班</td><td class="f_red">54</td><td>45</td></tr>
                                        <tr><td>1</td><td>初一（1）班</td><td class="f_red">51</td><td>45</td></tr>
                                        <tr><td>1</td><td>初一（1）班</td><td class="f_orange">60</td><td>45</td></tr>
                                        <tr><td>1</td><td>初一（1）班</td><td class="f_green">95</td><td>45</td></tr>
                                        <tr><td>1</td><td>初一（1）班</td><td class="f_blue">82</td><td>45</td></tr>
                                        <tr><td>1</td><td>初一（1）班</td><td class="f_red">54</td><td>45</td></tr>
                                        <tr><td>1</td><td>初一（1）班</td><td class="f_red">51</td><td>45</td></tr>
                                        <tr><td>1</td><td>初一（1）班</td><td class="f_orange">60</td><td>45</td></tr>  -->
                                    </tbody>
                                </table>
                            </div>
                            <div class="more"><a href="javascript:void(0)">更多 »</a></div>
                        </div>
                    </div>
                    <div class="swiper-slide swiper_2">
                        <div class="bjwcl_pmb">
                            <p class="title">总体完成情况</p>
                            
                            <div class="table_div">
                                <table class="table" id="table_2">
                                    <thead><tr><th>学号</th><th>姓名</th><th>成绩</th></tr></thead>
                                    <tbody>
                                        <!-- <tr><td>201202513</td><td>张三</td><td class="f_green">95</td></tr>
                                        <tr><td>201202511</td><td>李四</td><td class="f_blue">82</td></tr>
                                        <tr><td>201202511</td><td>李四</td><td class="f_red">54</td></tr>
                                        <tr><td>201202512</td><td>李四班</td><td class="f_red">51</td></tr>
                                        <tr><td>201202511</td><td>李四</td><td class="f_orange">60</td></tr>
                                        <tr><td>201202511</td><td>李四</td><td class="f_green">95</td></tr>
                                        <tr><td>201202511</td><td>李四</td><td class="f_blue">82</td></tr> -->
                                    </tbody>
                                </table>
                            </div>
                            <div class="more"><a href="javascript:void(0)">更多 »</a></div>
                        </div>
                    </div>
                </div>
                <!-- Add 右侧分页 -->
                <div class="swiper-pagination"></div>
            </div>
        </div>
        <!--完成情况 end-->
        <!--答题统计 start-->
        <div class="mk answer-statistics">
            <!-- Swiper -->
            <div class="swiper-container">
                <div class="swiper-wrapper dttj_wapper">
                   <!--  <div class="swiper-slide swiper_3">
                        <div class="dt dt2">
                            <p>1-15题得分率对比图</p>
                            <div id="container_1" class="container" style="width:876px;margin: 0 auto"></div>
                        </div>
                    </div> -->
                </div>
                <!-- Add Pagination -->
                <div class="swiper-pagination"></div>
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
                        <li>类型：<span>单选题</span></li>
                        <li>分值：<span>5</span></li>
                        <li> 作答人数：<span>20人</span></li>
                    </ul>

                </div>
                <div class="wrapper-demo">
                    <div  class="small dd wrapper-dropdown-2" tabindex="1">1 </div>
                    <ul class="dropdown small-ul">
                        <li>1</li>
                        <li>2</li>
                        <li>3</li>
                    </ul>
                </div>
                <div class="clear"></div>
            </div>
            <!-- Swiper -->
            <div class="swiper-container">
                <div class="swiper-wrapper">
                    <div class="swiper-slide">
                        <div class="dt dt4">
                            <p>答题项应答次数分布图</p>
                            <div id="container1" style="width:876px;margin: 0 auto"></div>
                        </div>
                    </div>
                    <div class="swiper-slide">
                        <div class="dt dt4">
                            <p>年级正答率对比图</p>
                            <div id="container2" style="width:876px;margin: 0 auto"></div>
                        </div>

                    </div>
                    <div class="swiper-slide">
                        <div class="dt dt3">
                            <p>题目详情</p>
                            <div class="tm ">
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
                        </div>
                    </div>
                </div>
                <!-- Add Pagination -->
                <div class="swiper-pagination"></div>
            </div>
        </div>
        <!--单题分析 end-->
    </div>
</div>
</body>
<script>
$(function(){
// 	滑动插件初始化
	var swiper = new Swiper('.swiper-container', {
	    pagination: '.swiper-pagination',
	    direction: 'vertical',
	    observer:true,
	    observeParents:true,
	    slidesPerView: 1,
	    paginationClickable: true,
	    spaceBetween: 30,
	    mousewheelControl: true
	});
	//顶部点击菜单
	 $('.a3-3-top ul li').click(function(){
	        var aa = $(this).index();
	        $('ul li').siblings().removeClass('select1');
	        $(this).addClass('select1');
	        $('.a3-3-content').find('.mk').css('display','none');
	        $('.a3-3-content').find('.mk').eq(aa).css('display','block');
	    });

	//下拉框
    $(".dd").click(function(){
        $(this).toggleClass('active');
        $(this).next().toggleClass("on");
        return false;
    });
    $(" .dropdown li").click(function(){

        var h=$(this).html();
        $(this).parent().prev().html(h);
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
    });
    var timer;
    $(" .dropdown").mouseout(function(){
        timer = setTimeout(function () {
            $(" .dropdown").removeClass('on');
                    }, 100);

    });
    $(" .dropdown").mouseover(function(){
        clearInterval(timer);
        $(this).addClass('on');

    });
    $(document).click(function()
    {
        $(".dropdown").removeClass("on");
        $(".dd").removeClass("active");
    })
// 	屏幕变化各div高度
	var first_height,first_table,max_table,second_height,three_height;
	function lookHeight(){
         swiper_height=$(window).height()-$('.a3-3-top').height();
        $(".swiper-container").css("height",swiper_height);
       	//完成情况第一个统计图高度
       	 first_height=swiper_height-214-77-120;
       	//完成情况table白色高度
       	 first_table=swiper_height-120;
       	//table最大高度
       	 max_table=Math.floor((first_table-77-50)/50)*50;
       	//答题统计 统计图高度
       	 second_height=swiper_height-40-77-40-80;
       	//单题分析第一第二统计图高度
       	 three_height=swiper_height-80-77-130-80
        $("#container").css("height",first_height);
        $(".container").css("height",second_height);
        $("#container1,#container2").css("height",three_height);
        $(".swiper_2 .bjwcl_pmb").css("height",first_table);
        $(".swiper_2 .bjwcl_pmb .table_div").css("max-height",max_table);
    }
    window.onresize= lookHeight;
    lookHeight();
	//     调用json文件
	 $.getJSON("./json/wcqk.json", function (data){
		var first_x,first_y;
		$(".pm_div ul .l1 a .p1").text(data[0].rank);
		$(".pm_div ul .l2 a .p1").text(data[0].gradeAvg);
		$(".pm_div ul .l3 a .p1").text(data[0].teamAvg);
		var gs1=Math.round(first_height/50);
		first_x=data[0].xList.slice(0,gs1);
		first_y=data[0].yList.slice(0,gs1);
		//	     完成情况第一个统计图（横向条形图）
	    var colors = ['#08aaf5'];
	    Highcharts.getOptions().colors = Highcharts.map(colors, function (color) {
	        return {
	            radialGradient: { cx:0, cy: 1.2,r:2.3 },
	            stops: [[0, color], [2, Highcharts.Color(color).brighten(14).get('rgb')] // darken
	            ]
	        };
	    });
	    $('#container').highcharts({
	        chart: {
	            type: 'bar',
	            borderRadius:5,
	          /*  spacing: [29, 208, 126, 208]*/
	        },
	        title: {
	            text: null
	        },
	        xAxis: {
	            categories: first_y,
	            title: {
	                text: null
	            },
	            tickWidth:0,
	            gridLineDashStyle:'Dash',
	            lineWidth: 0
	        },
	        yAxis: {
	            min: 0,
	            //max:100,
	            title: {
	                text: null
	            },
	            labels: {
	                overflow: 'justify',
	                format:'{value}%'
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
	            data: [340, 335, 333, 344, 342],
	        }]
	    });
	    $(data[0].table).each(function(){
	    	if($(this)[0].percent<60){
	    		f_color='f_red';
	    	}else if($(this)[0].percent<80){
	    		f_color='f_orange';
	    	}else if($(this)[0].percent<90){
	    		f_color='f_blue';
	    	}else{
	    		f_color='f_green';
	    	}
	    	$("<tr><td>"+$(this)[0].teamRank+"</td><td>"+$(this)[0].teamName+"</td><td class="+f_color+">"+$(this)[0].averageScore+"</td><td>"+$(this)[0].studentCount+"</td></tr>").appendTo($("#table_1 tbody"));
	    });
	    $(data[0].table2).each(function(){
	    	if($(this)[0].percent<60){
	    		f_color='f_red';
	    	}else if($(this)[0].percent<80){
	    		f_color='f_orange';
	    	}else if($(this)[0].percent<90){
	    		f_color='f_blue';
	    	}else{
	    		f_color='f_green';
	    	}
	    	$("<tr><td>"+$(this)[0].number+"</td><td>"+$(this)[0].name+"</td><td class="+f_color+">"+$(this)[0].score+"</td></tr>").appendTo($("#table_2 tbody"));
	    })
	}); 
// 	 答题统计json
	 $.getJSON("./json/dttj.json", function (data){
		//答题统计的柱状图
		    var colors = ['#0babf5', '#ff778c'];
		    Highcharts.getOptions().colors = Highcharts.map(colors, function (color) {
		        return {
		            radialGradient: { cx:-0.3, cy: 1.2,r:2 },
		            stops: [[0, color], [2, Highcharts.Color(color).brighten(14).get('rgb')] // darken
		            ]
		        };
		    });
		    var categories_number,categories_rank,grade_number,team_number,k;
		    k=Math.ceil(data[0].number.length/15);
		    for(var i=0;i<k;i++){
		    	var k1=15*i;
		    	var k2=15*(i+1);
		    	var k3=data[0].number.length;
		    	categories_number=data[0].number.slice(k1,k2);
		    	categories_rank=data[0].rank.slice(k1,k2);
		    	grade_number=data[0].gradeRate.slice(k1,k2);
		    	team_number=data[0].teamRate.slice(k1,k2);
		    	var categories1=categories_number;
			    var categories2=categories_rank;
			    if(i==k-1){
			    	$("<div class='swiper-slide swiper_3'><div class='dt dt2'><p>"+(k1+1)+"-"+k3+"题得分率对比图</p><div id='container_"+i+"' class='container' style='width:876px;margin: 0 auto'></div></div></div>").appendTo(".dttj_wapper")
			    }else{
			    	$("<div class='swiper-slide swiper_3'><div class='dt dt2'><p>"+(k1+1)+"-"+k2+"题得分率对比图</p><div id='container_"+i+"' class='container' style='width:876px;margin: 0 auto'></div></div></div>").appendTo(".dttj_wapper")
			    }
			    $(".container").css("height",second_height);
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
			                    x:-25, 
			                    y:-19
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
			                    x:-45,
			                    y:-20
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
		    
	 })
	 //单题分析饼图
    $.getJSON("./json/dtfx_1.json", function (data){
	     var jsonArr=[];
	     for (var key in data[0]) 
	     {
	    	 jsonArr.push([key,data[0][key]])
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
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
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
            type: 'pie',
            name: '作答',
            innerSize: '70%',
            data: jsonArr
        }],

        });
    })
//   	单体分析柱状图
    $.getJSON("./json/dtfx_2.json", function (data){
    	 //横向条形图(单题分析第二屏)
        var colors = ['#08aaf5'];
        Highcharts.getOptions().colors = Highcharts.map(colors, function (color) {
            return {
                radialGradient: { cx:0, cy: 1.2,r:2.3 },
                stops: [[0, color], [2, Highcharts.Color(color).brighten(14).get('rgb')] // darken
                ]
            };
        });
        var gs2,three_x,three_y;
        gs2=Math.round(three_height/50);
		three_x=data[0].names.slice(0,gs2);
		three_y=data[0].avg.slice(0,gs2);
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
                    format:'{value}%'
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
    })
 
})

</script>
</html>