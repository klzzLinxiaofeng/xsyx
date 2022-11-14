<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="apple-touch-fullscreen" content="YES">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="pragram" content="no-cache">
<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dygl/report_forms.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/highcharts.js"></script>
<title>激励评价数据分析</title>
<style>
th.project, td.project{
    width: 190px;
}
.details-table table thead{
	border-top: 1px solid #d3d3d3;
}
</style>
<script type="text/javascript">
$(function(){
    $('#container').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
        },
        colors:[
            '#3ac982',
            '#2299ee',
        ],
        title: {
            text: '',
        },
        credits:{
     enabled:false // 禁用版权信息
        },
        exporting: {
            enabled:false//关闭设置按钮
        },
        tooltip: {
            pointFormat: true,
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false,
                    color: '#323232',
                    connectorColor: 'none',
                    // format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {fontSize:"18px",}
                }
            }
        },
        series: [{
            type: 'pie',
            name: '',
            data: [
                ['',   25],
                ['',   75],

            ]
        }]
    });


//     $(".analysis").hide();//隐藏wenben
//     $(".analysis:eq(0)").show();//显示第一个wenben
//     $(".classify a").click(function(){
//         $(".check").removeClass("check");//移除样式
//         $(this).addClass("check");//添加样式
//         var i=$(this).index();//获得下标
//         $(".analysis").hide();//隐藏wenben
//         $(".analysis:eq("+i+")").show();//显示第i个wenben
//     });

$(".choice-class").click(function(){
    	$(".choice_prom").hide();
     $(".choice_show").toggle();
     if($(".choice_show").is(":visible")){
    	 $("#choice_bg").show();
     }else{
    	 $("#choice_bg").hide();
     }
    var $top=$(this).children("p").children(".tog")
    var t=$top.text();
    if(t=="﹀"){
    	$(".choice-term").css("color","#000")
        $(".choice-term .tog").text("﹀").removeClass("tianjia");
        $(".choice-class .tog").addClass("show");
        $(this).css("color","#39a3ef")
            $top.text("︿").addClass("tianjia");
        }else{
            $(".tog").removeClass("show");
            $(this).css("color","#000")
            $top.text("﹀").removeClass("tianjia");
    }
});


        $(".choice_remove ul li").click(function(){
            $(".choice_remove ul li").addClass("default").removeClass("grade");
            $(this).addClass("grade").removeClass("default");
     });
     $(".minutes ul li").click(function(){
        $(".minutes ul li").addClass("det").removeClass("year");
        $(this).addClass("year").removeClass("det");
        var class_name=$(this).text();
        var grade_name=$(".choice_remove ul li.grade").text();
        $(".c_name").text(grade_name+class_name)
        
        $(".tog").removeClass("show");
        $(".choice-class").css("color","#000")
        $(".tog").text("﹀").removeClass("tianjia");
        $(".choice_show").toggle();
      $("#choice_bg").toggle();
     });
//      点击遮罩层
     $("#choice_bg").click(function(){
    	 $(".tog").removeClass("show");
         $(".choice-class,.choice-term").css("color","#000")
         $(".tog").text("﹀").removeClass("tianjia");
         $(".choice_show,.choice_prom").hide();
       $("#choice_bg").toggle();
     });
   //学年切换
     $(".choice-term").click(function(){
    	 $(".choice_show").hide();
         $(".choice_prom").toggle();
         if($(".choice_prom").is(":visible")){
        	 $("#choice_bg").show();
         }else{
        	 $("#choice_bg").hide();
         }
          
        var $top=$(this).children("p").children(".tog")
        var t=$top.text();
        if(t=="﹀"){
        	$(".choice-class").css("color","#000")
            $(".choice-class .tog").text("﹀").removeClass("tianjia");
            $("choice-term .tog").addClass("show");
            $(this).css("color","#39a3ef")
                $top.text("︿").addClass("tianjia");
            
            }else{
                $(".tog").removeClass("show");
                $(this).css("color","#000")
                $top.text("﹀").removeClass("tianjia");
        }
    });
    $(".school-year ul li").click(function(){
            $(".school-year ul li").removeClass("year");
            $(this).addClass("year");
            var grade_name=$(this).parent().prev().text();
            $(".xq_name").text(grade_name);

            $(".choice-term").css("color","#000")
            $(".tog").text("﹀").removeClass("tianjia");
            $(".choice_prom").toggle();
          	$("#choice_bg").toggle();
         });
    });
</script>
<style>
	.details-table table tbody td div.schedule{
    width: 87%;
}
td p.name{
    width: 230px;
}
.default{
    background: #fff;
}
span.green{background:#fff}
</style>
</head>
<body>
<div class="menu-choice">
<div class="choice-term">
        <p><span  class="xq_name">14-13学年</span><span class="tog">﹀</span></p>
    </div>
     <div class="choice_prom">
            <div class="school-year">
                <span>15-16学年</span>
                <ul>
                    <li>上学期</li>
                    <li>下学期</li>
                </ul>
            </div>
            <div class="school-year">
                <span>14-15学年</span>
                <ul>
                    <li>上学期</li>
                    <li>下学期</li>
                </ul>
            </div>
            <div class="school-year">
                <span>13-14学年</span>
                <ul>
                    <li>上学期</li>
                    <li>下学期</li>
                </ul>
            </div>
        </div>
    <i></i>
   <div class="choice-class" id="btnshow" style="width: 165px;">
        <p><span class="c_name">三年级二班</span><span class="tog">﹀</span></p>
    </div>
    <div class="choice_show">
        <div class="choice_remove">
            <p>选择年级、班级</p>
            <ul>
                <li>一年级</li>
                <li>二年级</li>
                <li>三年级</li>
                <li>四年级</li>
                <li>五年级</li>
                <li>六年级</li>
            </ul>
        </div>
        <div class="minutes">
            <p>选择班</p>
            <ul>
                <li>1班</li>
                <li>2班</li>
                <li>3班</li>
                <li>4班</li>
            </ul>
        </div>
    </div>
    <i></i>
    <select style="width:159px;float: left;">
        <option>月份</option>
    </select>
    <i></i>
    <select style="width:159px;float: left;">
        <option>第一周</option>
    </select>
    <div id="choice_bg"></div>
</div>
<div class="overall-score" style="height:130px;">
    <h1>三年（2）班发展评价数据分析</h1>
</div>
<!-- <div class="classify"> -->
<!--     <a href="javascript:void(0);" class="check" style="border-right: 1px solid #d3d3d3;">全部</a> -->
<!--     <a href="javascript:void(0);">个人</a> -->
<!-- </div> -->
<div class="analysis" style="background:#fff;">
    <div style="height:500px;padding:50px 0;" id="container"></div>
    <div class="specific">
        <h2>总数量60</h2>
        <p>全年级占比30%</p>
    </div>
    <div class="details-table">
    <table>
        <thead>
            <tr>
                <th style="width: 180px;" class="project">班级</th>
                <th>数量</th>
                <th style="width: 145px;">全班级占比</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td class="project"><p class="name her">（1）班</p></td>
                <td>
                    <span class="green">5</span>
                    <div class="schedule">
                        <p class="green" style="width:100%";></p>
                    </div>
                </td>
                <td class="range">100%</td>
            </tr>
            <tr>
                <td class="project"><p class="name her">（2）班</p></td>
                <td>
                    <span class="green">5</span>
                    <div  class="schedule">
                        <p class="green" style="width: 50%;"></p>
                    </div>
                </td>
                <td class="range">50%</td>
            </tr>
            <tr>
                <td class="project"><p class="name her">（3）班</p></td>
                <td>
                    <span class="green">5</span>
                    <div  class="schedule">
                        <p class="green" style="width: 50%;"></p>
                    </div>
                </td>
                <td class="range">50%</td>
            </tr>
            <tr>
                <td class="project"><p class="name her">（4）班</p></td>
                <td>
                    <span class="green">5</span>
                    <div  class="schedule">
                        <p class="green" style="width: 50%;"></p>
                    </div>
                </td>
                <td class="range">50%</td>
            </tr>
            <tr>
            <td class="project"><p class="name her">（5）班</p></td>
                <td>
                    <span class="green">5</span>
                    <div  class="schedule">
                        <p class="green" style="width: 50%;"></p>
                    </div>
                </td>
                <td class="range">50%</td>
            </tr>
        </tbody>
    </table>
</div>
</div>
<!-- <div class="analysis"> -->
<!-- <div class="personal"> -->
<!--     <ul> -->
<!--         <li> -->
<!--             <div class="default"> -->
<%--                 <img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg"> --%>
<!--                 <p>张三</p> -->
<!--             </div> -->
<!--             <p class="called"></p> -->
<!--         </li> -->
<!--         <li> -->
<!--             <div class="default"> -->
<%--                 <img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg"> --%>
<!--                 <p>林枫</p> -->
<!--             </div> -->
<!--             <p class="called"></p> -->
<!--         </li> -->
<!--         <li> -->
<!--             <div class="default"> -->
<%--                 <img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg"> --%>
<!--                 <p>李四</p> -->
<!--             </div> -->
<!--             <p class="called"></p> -->
<!--         </li> -->
<!--         <li> -->
<!--             <div class="default"> -->
<%--                 <img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg"> --%>
<!--                 <p>司徒三人</p> -->
<!--             </div> -->
<!--             <p class="called"></p> -->
<!--         </li> -->
<!--         <li> -->
<!--             <div class="default"> -->
<%--                 <img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg"> --%>
<!--                 <p>慕容晓晓</p> -->
<!--             </div> -->
<!--            <p class="called"></p> -->
<!--         </li> -->
<!--         <li> -->
<!--             <div class="default"> -->
<%--                 <img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg"> --%>
<!--                 <p>赵子龙</p> -->
<!--             </div> -->
<!--             <p class="called"></p> -->
<!--         </li> -->
<!--         <li> -->
<!--             <div class="default"> -->
<%--                 <img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg"> --%>
<!--                 <p>王五</p> -->
<!--             </div> -->
<!--             <p class="called"></p> -->
<!--         </li> -->
<!--         <li> -->
<!--             <div class="default"> -->
<%--                 <img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg"> --%>
<!--                 <p>赵六</p> -->
<!--             </div> -->
<!--             <p class="called"></p> -->
<!--         </li> -->
<!--         <li> -->
<!--             <div class="default"> -->
<%--                 <img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg"> --%>
<!--                 <p>奥特曼</p> -->
<!--             </div> -->
<!--             <p class="called"></p> -->
<!--         </li> -->
<!--     </ul> -->
<!-- </div> -->

<!-- <div class="details-table"> -->
<!--     <table> -->
<!--         <thead> -->
<!--             <tr> -->
<!--                 <th style="width: 180px;" class="project">评价卡</th> -->
<!--                 <th>数量</th> -->
<!--                 <th style="width: 145px;">全班级占比</th> -->
<!--             </tr> -->
<!--         </thead> -->
<!--         <tbody> -->
<!--             <tr> -->
<!--                 <td class="project"><p class="name her">品德发展评价卡</p></td> -->
<!--                 <td> -->
<!--                     <span class="green">5</span> -->
<!--                     <div class="schedule"> -->
<!--                         <p class="green" style="width:100%";></p> -->
<!--                     </div> -->
<!--                 </td> -->
<!--                 <td class="range">100%</td> -->
<!--             </tr> -->
<!--             <tr> -->
<!--                 <td class="project"><p class="name her">学业发展评价卡</p></td> -->
<!--                 <td> -->
<!--                     <span class="green">5</span> -->
<!--                     <div  class="schedule"> -->
<!--                         <p class="green" style="width: 50%;"></p> -->
<!--                     </div> -->
<!--                 </td> -->
<!--                 <td class="range">50%</td> -->
<!--             </tr> -->
<!--             <tr> -->
<!--                 <td class="project"><p class="name her">身心发展评价卡</p></td> -->
<!--                 <td> -->
<!--                     <span class="green">5</span> -->
<!--                     <div  class="schedule"> -->
<!--                         <p class="green" style="width: 50%;"></p> -->
<!--                     </div> -->
<!--                 </td> -->
<!--                 <td class="range">50%</td> -->
<!--             </tr> -->
<!--             <tr> -->
<!--                 <td class="project"><p class="name her">兴趣特长评价卡</p></td> -->
<!--                 <td> -->
<!--                     <span class="green">5</span> -->
<!--                     <div  class="schedule"> -->
<!--                         <p class="green" style="width: 50%;"></p> -->
<!--                     </div> -->
<!--                 </td> -->
<!--                 <td class="range">50%</td> -->
<!--             </tr> -->
<!--             <tr> -->
<!--             <td class="project"><p class="name her">实践操作评价卡</p></td> -->
<!--                 <td> -->
<!--                     <span class="green">5</span> -->
<!--                     <div  class="schedule"> -->
<!--                         <p class="green" style="width: 50%;"></p> -->
<!--                     </div> -->
<!--                 </td> -->
<!--                 <td class="range">50%</td> -->
<!--             </tr> -->
<!--         </tbody> -->
<!--     </table> -->
<!-- </div> -->
<!-- </div> -->
</body>
</html>