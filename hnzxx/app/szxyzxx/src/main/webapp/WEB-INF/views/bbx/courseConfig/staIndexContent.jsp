<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <!-- <title>ECharts</title> -->
    <script type="text/javascript" src="${ctp}/res/js/echarts-all.js"></script>
</head>
<style>
body{background:#fff;}
    table{

        border:0;
        cellspacing:0;
        cellpadding:0;
    }
    .dinc span{ 
    display: inline-block;
    padding: 5px 9px;
    margin: 10px;
    background: red;
    color: #fff;
    margin-top: 40px;
    font-size: 14px;}
</style>
<body>
<!--Step:1 Prepare a dom for ECharts which (must) has size (width & hight)-->
<!--Step:1 为ECharts准备一个具备大小（宽高）的Dom-->
<div class="dinc" style="text-align:center;"><span id="z_sum" style="background:#coral;">总人数：</span><span id="y_sum" style="background:#009e13;">已报名人数：</span><span id="w_sum" style="background:#a9afa9;">未报名人数：</span></div>
<table style="width:100%; padding: 0; margin:  0"  collapse="0" spellcheck="0">
<tr>
<td  colspan=2><p class="title_tu" style="text-align:center;font-size: 22px;padding-top: 40px;font-weight: bold;">科目学生选科统计图</p></td>
</tr>
    <tr>
    <td width=50%><div id="ss_a" style="height:400px;"></td>
    <td width=50%><div id="main_a" style="height:400px;margin:0 auto; width:500px;"></div></td>
    </tr>
        <td colspan=2><p class="title_tu" style="text-align:center;font-size: 22px;padding-top: 40px;font-weight: bold;">科目组学生选科统计图</p><div id="main" style="height:400px;"></div></td>
    <tr>
    <tr>
        <td  colspan=2><div id="ss" style="height:550px;"></div></td>
    </tr>
</table>
<!--Step:2 Import echarts.js-->
<!--Step:2 引入echarts.js-->

<script type="text/javascript">

    // Step:3 conifg ECharts's path, link to echarts.js from current page.
    // Step:3 为模块加载器配置echarts的路径，从当前页面链接到echarts.js，定义所需图表路径
    var myChart = echarts.init(document.getElementById('main'));
    var myChart_a = echarts.init(document.getElementById('main_a'));
    var ss = echarts.init(document.getElementById('ss'));
    var ss_a = echarts.init(document.getElementById('ss_a'));
    var json_data=[{courseNames:'1年级',num:'1.3'},{courseNames:'2年级',num:'13'},{courseNames:'3年级',num:'1'},{courseNames:'4年级',num:'1'}];
    
   /*  var json_data =eval("("+json_data_obj+")"); */
    var json_data_a=[{courseNames:'12年级',num:'1.3'},{courseNames:'2年级',num:'13'},{courseNames:'3年级',num:'1'},{courseNames:'4年级',num:'1'}]
	var data_ay =  [];
	var data_ax = [];
    var data_axdata='科目组';
    var data_aydata='人数';
    var data_atitle = "科目组学生选科统计图";
    var data_by =  [];
    var data_bx = [];
    var data_bxdata='科目';
    var data_bydata='人数';
    var data_btitle = "科目学生选科统计图";
    var data_a_r = "[]";
    var data_a_rdata = eval('('+data_a_r+')')
    var data_b_r = "[]";
    var data_b_rdata = eval('('+data_b_r+')')
    $.each(json_data,function(i,v){
    	data_ay.push(v.courseNames)
    	data_ax.push(v.num)
    	var arr  =
    	     {
    	         "name" : v.courseNames,
    	         "value" : v.num
    	     }
    	data_a_rdata.push(arr);
    })
    $.each(json_data_a,function(i,v){
    	data_by.push(v.courseNames)
    	data_bx.push(v.num)
    	var arr  =
	     {
	         "name" : v.courseNames,
	         "value" : v.num
	     }
	data_b_rdata.push(arr);
    })
    var data_x = [];

    function grid_t(title_c,xname,xdata,yname,ydata,index){
		console.log(title_c,xname,xdata,yname,ydata,index)
        var option = {
            title : {
                text: '',
            },
            color:[
              '#229bf2'
            ],
            grid:{
                borderColor:'#fff',
                x:'30',
                y:'35',
                x2:'30',
                y2:'80',
                backgroundColor:'rgb(255, 255, 255)'
            },
            categoryAxis:{
                axisLine:{
                    lineSytle:{
                        color:['red']
                    }
                },
                spilArea:{
                    lineSytle:{
                        color:['red']
                    }
                }
            },
            tooltip : {
                trigger: 'axis',
            },
            xAxis : [
                {
                    name:xname,
                    axisTick:{
                        show:true,
                        lineStyle:{
                            color: 'red',
                        }
                    },
                    axisLabel:{
                        show:true,
                        lineStyle:{
                            color: '#000',
                            width: 2,
                        },
                         interval:0,//横轴信息全部显示  
                         rotate:-45//-30度角倾斜显示
                    },
                    type : 'category',
                    data :ydata,
                    splitArea : {show : false},


                }
            ],
            yAxis : [
                {splitLine:{show: false},
                    name:yname,
                    axisLabel:{
                        show:true,
                        lineStyle:{
                            color: 'red',
                            width: 2,
                        }
                    },
                    type : 'value',
                }
            ],
            series : [
                {
                    clickable:false,
                    type:'bar',
                    itemStyle : { normal: {label : {show: true, position: 'inside'}}},
                    data:xdata
                }
                ]

        }
		if(index==1){
	        myChart.setOption(option);
		}else{

	        myChart_a.setOption(option);
		}
    }
    //grid_ddt(2)
    function grid_ddt(index){
        var option = {
            title : {
                text: ''
            },
            color:[
              '#229bf2'
            ],
            grid:{
                borderColor:'#fff',
                x:'50',
                y:'60',
                x2:'50',
                y2:'50',
                backgroundColor:'rgb(255, 255, 255)'
            },
            categoryAxis:{
                axisLine:{
                    lineSytle:{
                        color:['red']
                    }
                },
                spilArea:{
                    lineSytle:{
                        color:['red']
                    }
                }
            },
            tooltip : {
                trigger: 'axis',
            },
            xAxis : [
                {
                    name:'人数',
                    type : 'value',
                    axisTick:{
                        show:true,
                        lineStyle:{
                            color: 'red',
                        }
                    },
                    axisLabel:{
                        show:true,
                        lineStyle:{
                            color: '#000',
                            width: 2,
                        }
                    },
                    splitArea : {show : false},


                }
            ],
            yAxis : [
                {splitLine:{show: false},
                    name:'yyy',
                    axisLabel:{
                        show:true,
                        lineStyle:{
                            color: 'red',
                            width: 2,
                        }
                    },
                    type : 'category',
                    data : ["物理化学生物", "物理化学地理", "物理化学历史", "物理化学政治", "物理生物地理", "物理生物历史", "物理生物政治", "物理地理历史", "物理地理政治", "物理历史政治", "化学生物地理", "化学生物历史", "化学生物政治", "化学地理历史", "化学地理政治", "化学历史政治", "生物地理历史", "生物地理政治", "生物历史政治", "地理历史政治"]
                }
            ],
            series : [
                {
                    clickable:false,
                    type:'bar',
                    itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
                    data:[2, 0, 0, 3, 1, 0, 0, 0, 1, 3, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0]
                }
                ]

        }
		if(index==1){
			alert(2)
	        myChart.setOption(option);
		}else{
			alert(33)

	        myChart_a.setOption(option);
		}
	}
   
    function option_two(data,index){
		console.log(data+index+"-------")
		var bbb=[
                 {"value":335, "name":'直接访问'},
                 {"value":310, "name":'邮件营销'},
                 {"value":234, "name":'联盟广告'},
                 {"value":135, "name":'视频广告'},
                 {"value":1548, "name":'搜索引擎'}
             ];
		var aaa=data;
		var P_option = {
	            title : {
	                text: '',
	            },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    calculable : false,
		    series : [
		        {
		            title:"aa",
		            type:'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:aaa,
		            itemStyle:{
		                normal:{
		                    label:{
		                        show: true,
		                        formatter: '{b} : {c} ({d}%)'
		                    },
		                    labelLine :{show:true}
		                }
		            }
		        }
		    ]
		};

       if(index==1){
        ss.setOption(P_option);
       }else{
         ss_a.setOption(P_option);
       }
    	
    }

	function getStaContent(){
		var termCode = $("#xq").val();
		if(termCode == ""){
			$.error("请选择学期");
			return;
		}	
		var gradeId = $("#nj").val();
		if(gradeId == ""){
			$.error("请选择年级");
			return;
		}	
		var $requestData = {};
		$requestData.termCode = termCode;
		$requestData.gradeId = gradeId;
		$.post('${ctp}/bbx/courseConfig/getStaContent', $requestData, function(data) {
			 var datay =  [];
			 var datax = [];
			 var dataya =  [];
			 var dataxa= [];
			 var datas  = JSON.parse(data);

			var data_a_r = "[]";
		    var data_a_rdata = eval('('+data_a_r+')')
			var data_b_r = "[]";
			 var data_b_rdata = eval('('+data_b_r+')');
			 $('#z_sum').html('总人数：'+datas.studentNum);
			 $('#y_sum').html('已报名人数：'+datas.courseStudentNum);
			 $('#w_sum').html('未报名人数：'+(parseInt(datas.studentNum)-parseInt(datas.courseStudentNum)));
			 
		 	 console.log(datas)
			$.each(datas.comCourseStudentList,function(i,v){
		    	datay.push(v.courseName)
		    	datax.push(v.num)
		    	var arr  =
	    	     {
	    	         "name" : v.courseName,
	    	         "value" : v.num
	    	     }
	    	data_a_rdata.push(arr);
			})
			$.each(datas.courseStudentList,function(i,v){
				dataya.push(v.courseName)
		    	dataxa.push(v.num)
		    	var arr  =
	    	     {
	    	         "name" : v.courseName,
	    	         "value" : v.num
	    	     }
	    	data_b_rdata.push(arr);
			})
			console.log(data_a_rdata)
			console.log(data_b_rdata)
			
			grid_t('科目组学生选科统计图','人数',datax,'科目组',datay,1);
			grid_t('科目学生选科统计图','人数',dataxa,'科目',dataya,2);
			option_two(data_a_rdata,1)
			option_two(data_b_rdata,2)

		});
	}
	
	getStaContent();

</script>
</body>
</html>