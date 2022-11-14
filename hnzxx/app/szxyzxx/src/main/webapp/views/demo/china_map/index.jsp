<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height: 100%">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>中国地图</title>
</head>
<body style="height: 100%; margin: 0;background:url('${pageContext.request.contextPath}/res/images/map_bg.png') no-repeat 50% 50%">
<div id="container" style="height: 100%"></div>
<script src="${pageContext.request.contextPath}/res/plugin/echarts/js/echarts.min.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/echarts/js/china.js"></script>
<script type="text/javascript">

	var dom = document.getElementById("container");
	var myChart = echarts.init(dom);
	var app = {};
	option = null;
	function randomData() {
		return Math.round(Math.random() * 1000);
	}

	var option = {
		title : {
			text : '用户数量',
			subtext : '',
			textStyle:{    //图例文字的样式
		        color:'#fff',
		        fontSize:24 ,
		        lineHeight:120
		    },
			left : 'center'
		},
		tooltip : {
			trigger : 'item'
		},
		legend : {
			orient : 'vertical',
			left : 'left',
			data : [ '' ]
		},
		visualMap : {
			min : 0,
			max : 40000,
			left : 'left',
			top : 'bottom',
			textStyle:{    //图例文字的样式
		        color:'#fff',
		        fontSize:12
		    },
			text : [ '高', '低' ], // 文本，默认为数值文本
			inRange: {
                color: ['#70d9ff','#29b7f7', '#2196f5','#1665c1']
            },
			calculable : true
		},
		toolbox : {
			show : false,
			orient : 'vertical',
			left : 'right',
			top : 'center',
			feature : {
				dataView : {
					readOnly : false
				},
				restore : {},
				saveAsImage : {}
			}
		},
		series : [ {
			name : '人数',
			type : 'map',
			mapType : 'china',
			label : {
				normal : {
					show : true
				},
				emphasis : {
					show : true
				}
			},
			itemStyle:{
				normal: {
                    borderWidth: .5,//区域边框宽度
                    borderColor: '#13bfff',//区域边框颜色
                    color:'#fff',//圆点颜色
                    areaColor:"#a1e3ff",//区域颜色
                },
	           },
	           
			data : [ {
				name : '广东',
				value : '8954'
			}, {
				name : '云南',
				value : '8063'
			}, {
				name : '江西',
				value : '16728'
			}, {
				name : '贵州',
				value : '3125'
			}, {
				name : '广西',
				value : '2943'
			}, {
				name : '河南',
				value : '33400'
			}, {
				name : '宁夏',
				value : '8184'
			} ]
		} ]
	};
	if (option && typeof option === "object") {
		myChart.setOption(option, true);
	}

	myChart.on("click", function(param) {
		var wid=$("body").width()*0.9;
		var hei=$("body").height()*0.9;
		if(param.name=="宁夏"){
			$.initWinOnTopFromLeft("学校用户统计",'ningxia.jsp', wid, hei);
		}else if(param.name=="广东"){
			$.initWinOnTopFromLeft("学校用户统计",'guangdong.jsp', wid, hei);
		}else if(param.name=="云南"){
			$.initWinOnTopFromLeft("学校用户统计",'yunnan.jsp', wid,  hei);
		}else if(param.name=="江西"){
			$.initWinOnTopFromLeft("学校用户统计",'jiangxi.jsp', wid,  hei);
		}else if(param.name=="贵州"){
			$.initWinOnTopFromLeft("学校用户统计",'guizhou.jsp', wid, hei);
		}else if(param.name=="广西"){
			$.initWinOnTopFromLeft("学校用户统计",'guangxi.jsp', wid,  hei);
		}else if(param.name=="河南"){
			$.initWinOnTopFromLeft("学校用户统计",'henan.jsp', wid,  hei);
		}
		/*console.log(param);*/
		
		//alert(param.name)
	});
</script>
</body>
</html>