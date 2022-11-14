<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/highcharts.js"></script>
<style type="text/css">
a {
	text-decoration: none;
}

.gdu {
	height: 60px;
	background: #f2f2f2;
	border-bottom: 1px solid #bfbfbf;
	border-top: 1px solid #bfbfbf;
}

.gdu a {
	height: 60px;
	float: left;
	line-height: 60px;
	color: #999;
	text-align: center;
	width: 110px;
	border-right: 1px solid #bfbfbf;
	font-weight: bold;
}

.gdu a:hover {
	background: #fff;
	color: #436b8e;
	width: 110px;
	font-weight: bold;
	height: 61px;
}
.bt h5{
    font-size: 14px;background-color: #4993b3;line-height: 30px;text-align: center;  height: 30px;color: #fff;width: 300px;margin:20px 0 0 0;display:block;white-space:nowrap; overflow:hidden; text-overflow:ellipsis;
}
.bt{
    border:1px solid #bfbfbf;margin: 20px;
}
a.tb {
	width: 110px;
	background: #fff;
	color: #436b8e;
	height: 61px;
}
.bd{
	margin:20px;
}
.bd h4{
	font-size: 14px;background-color: #4993b3;line-height: 30px;text-align: center;  height: 30px;color: #fff;margin:20px 0 0 0;
	
}
.bd table {
	border-collapse: collapse;
	width: 100%;
}
.widget-container{
	padding:0;
}
.bd table tr{
	height:30px;
}
.bd table thead tr{
	height:35px;
}
.bd table thead tr td{
	  font-weight: bold;
}
.bd table tr td {
	border: 1px solid #aaa;
	padding-left: 20px;
	
}

tbody #subjectList  tr {
	height: 30px;
}

.xl {
	margin-bottom: 20px;
}
</style>
<title></title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="资源统计" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
		<div class="span12">
		<div class="content-widgets white">
		<div class="content-widgets">
		<div>
		<div class="gdu" id="gdu">
			<a href="javascript:void(0)" class="tb">教学资源</a> <a
				href="javascript:void(0)">分科统计</a>
		</div>
		<div class="figure">
		
		<div class="bt">
			<h5 title="个人教学资源统计">个人教学资源统计</h5>
			<div id="container" style="  overflow: hidden;"></div>
			</div>
		</div>
		<div class="figure">
			<div class="content-widgets">
				<div class="widget-container">
					<div class="select_b" style="background-color: #fff;">
						
						
						<div class="clear"></div>
						<div class="bd">
						<h4>个人教学资源分科统计表</h4>
							<table>
								<thead>
									<tr>
										<td>科目</td>
										<td>普通微课</td>
										<td>微课笔微课</td>
										<td>教案</td>
										<td>作业</td>
										<td>试卷</td>
										<td>课件</td>
										<td>素材</td>
									</tr>
								</thead>
								<tbody id="subjectList">

								</tbody>

							</table>
						</div>
					</div>

				</div>
			</div>
			</div>
			</div>
		</div>
		</div>
		</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function () {
	getDatass();
	search();
});

function getDatass(){
	var url = "${ctp}/resource/getResByUserId";
	$.post(url,function(data){
		getData(data);
	},'json')
}

function getData(datas){
	var data = [];
	for(var key in datas){
		data.push([key,datas[key]]);
	}
	$('#container').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: ''
        },
        credits: {
            enabled: false
        },
        exporting: {
            enabled:false
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: '',
            data: data
        }]
    });
}

$(function(){
    $(".figure").hide();//隐藏wenben
    $(".figure:eq(0)").show();//显示第一个wenben
    $("#gdu a").click(function(){
        $(".tb").removeClass("tb");//移除样式
        $(this).addClass("tb");//添加样式
        var i=$(this).index();//获得下标
        $(".figure").hide();//隐藏wenben
        $(".figure:eq("+i+")").show();//显示第i个wenben
    });
});
$(function(){
	var $requestData = {"schoolYear" : "${sessionScope[sca:currentUserKey()].schoolYear}"};
	$.GradeValueSelector({
		   "selector" : "#nj",
		   "condition" : $requestData
	});
})
function search(){
	var val = {
			"gradeCode" : $("#nj").val()
	};
	var gradeCode = $("#nj").val()+"";
	if($("#nj").val()!=0){
		var url = "${ctp}/resource/getSubjectByuserId";
		$.post(url,val,function(data){
			data = eval("(" + data + ")");
			if(data.length>0){
				$("#subjectList").html("");
				$.each(data,function(index,value){
					$("#subjectList").append('<tr data-subject='+ value.subjectCode +'><td>'+ value.subjectName +'</td><td data-subject='+ value.subjectCode +' data-resType=1></td><td data-subject='+ value.subjectCode +' data-resType=2></td><td data-subject='+ value.subjectCode +' data-resType=3></td><td data-subject='+ value.subjectCode +' data-resType=4></td><td data-subject='+ value.subjectCode +' data-resType=5></td><td data-subject='+ value.subjectCode +' data-resType=6></td><td data-subject='+ value.subjectCode +' data-resType=7></td></tr>')
				});
				getCommonMi();//普通微课
				getMicroMi();//微课笔微课
				
				getCountTp();//教案
				getCountHw();//作业
				getCountEm();//试卷
				
				getCountLd();//课件
				getCountMl();//素材
				
			}
		});
	}else if(gradeCode=="0"){
		$("#subjectList").html("");
		$("#subjectList").append('<tr><td>其他</td><td data-subject="mi"></td><td data-subject="ld"></td><td data-subject="hw"></td><td data-subject="em"></td><td data-subject="tp"></td><td data-subject="ml"></td></tr>')
		getOther();
	}else{
		$("#subjectList").html("");
	}
}

$.GradeValueSelector = function(options) {
	var defOption = {
		"selector" : "#schoolYear",
		"condition" : {},
		"selectedVal" :  "",
		"afterHandler" : function() {},
		"firstOptionTitle" : "请选择",
		"isUseChosen" : true
	};
	options = $.extend({}, defOption, options || {});
	var selector = $(options.selector);
	selector.html("");
	selector.append("<option value=''>" + options.firstOptionTitle + "</option>");
	$.getGrade(options.condition, function(data) {
		$.each(data, function(index, value) {
			selector.append("<option value='" + value.uniGradeCode + "'>" + value.name + "</option>")
		});
		selector.append("<option value='0'>其他</option>")
		selector.val(options.selectedVal);
		options.afterHandler(selector);
		if(options.isUseChosen == null || options.isUseChosen) {
			selector.chosen();
		}
	});
}

function getCommonMi(){
	var url = "${pageContext.request.contextPath}/resource/getCommonMi";
	var aj = $.ajax({
	    url: url,
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.length>0){
	    		$.each(data,function(index,value){
		    		$("#subjectList tr td:nth-child(2)").each(function(){
			    		var subject = $(this).attr("data-subject");
			    		if(value.subjectCode==subject){
			    			$(this).html(value.miCount);
			    		}else{
			    			var html = $(this).html()+"";
			    			if(html==""||html=="0"){
			    				$(this).html(0);
			    			}
			    			
			    		}
			    	})
		    	});
	    	}else{
	    		$("#subjectList tr td:nth-child(2)").each(function(){
		    		$(this).html(0);
		    	})
	    	}
	    	
	     },    
	     error : function() {
// 	         $.alert("异常！");    
	     }    
	});
}

function getMicroMi(){
	var url = "${pageContext.request.contextPath}/resource/getMicroMi";
	var aj = $.ajax({
	    url: url,
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.length>0){
	    		$.each(data,function(index,value){
		    		$("#subjectList tr td:nth-child(3)").each(function(){
			    		var subject = $(this).attr("data-subject");
			    		if(value.subjectCode==subject){
			    			$(this).html(value.miCount);
			    		}else{
			    			var html = $(this).html()+"";
			    			if(html==""||html=="0"){
			    				$(this).html(0);
			    			}
			    			
			    		}
			    	})
		    	});
	    	}else{
	    		$("#subjectList tr td:nth-child(3)").each(function(){
		    		$(this).html(0);
		    	})
	    	}
	    	
	     },    
	     error : function() {
// 	         $.alert("异常！");    
	     }    
	});
}
function getCountEm(){
	var url = "${pageContext.request.contextPath}/resource/getCountEm";
	var aj = $.ajax({
	    url: url,    
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.length>0){
	    		$.each(data,function(index,value){
		    		$("#subjectList tr td:nth-child(6)").each(function(){
			    		var subject = $(this).attr("data-subject");
			    		if(value.subjectCode==subject){
			    			$(this).html(value.emCount);
			    		}else{
			    			var html = $(this).html()+"";
			    			if(html==""||html=="0"){
			    				$(this).html(0);
			    			}
			    			
			    		}
			    	})
		    	});
	    	}else{
	    		$("#subjectList tr td:nth-child(6)").each(function(){
		    		$(this).html(0);
		    	})
	    	}
	     },    
	     error : function() {
// 	         $.alert("异常！");    
	     }    
	});
}
function getCountHw(){
	var url = "${pageContext.request.contextPath}/resource/getCountHw";
	var aj = $.ajax({
	    url: url,    
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.length>0){
	    		$.each(data,function(index,value){
		    		$("#subjectList tr td:nth-child(5)").each(function(){
			    		var subject = $(this).attr("data-subject");
			    		if(value.subjectCode==subject){
			    			$(this).html(value.hwCount);
			    		}else{
			    			var html = $(this).html()+"";
			    			if(html==""||html=="0"){
			    				$(this).html(0);
			    			}
			    		}
			    	})
		    	});
	    	}else{
	    		$("#subjectList tr td:nth-child(5)").each(function(){
		    		$(this).html(0);
		    	})
	    	}
	     },    
	     error : function() {
// 	         $.alert("异常！");    
	     }    
	});
}
function getCountLd(){
	var url = "${pageContext.request.contextPath}/resource/getCountLd";
	var aj = $.ajax({
	    url: url,   
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.length>0){
	    		$.each(data,function(index,value){
		    		$("#subjectList tr td:nth-child(7)").each(function(){
			    		var subject = $(this).attr("data-subject");
			    		if(value.subjectCode==subject){
			    			$(this).html(value.ldCount);
			    		}else{
			    			var html = $(this).html()+"";
			    			if(html==""||html=="0"){
			    				$(this).html(0);
			    			}
			    		}
			    	})
		    	});
	    	}else{
	    		$("#subjectList tr td:nth-child(7)").each(function(){
		    		$(this).html(0);
		    	})
	    	}
	     },    
	     error : function() {
// 	         $.alert("异常！");    
	     }    
	});
}
function getCountMl(){
	var url = "${pageContext.request.contextPath}/resource/getCountMl";
	var aj = $.ajax({
	    url: url,
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.length>0){
	    		$.each(data,function(index,value){
		    		$("#subjectList tr td:nth-child(8)").each(function(){
			    		var subject = $(this).attr("data-subject");
			    		if(value.subjectCode==subject){
			    			$(this).html(value.mlCount);
			    		}else{
			    			var html = $(this).html()+"";
			    			if(html==""||html=="0"){
			    				$(this).html(0);
			    			}
			    		}
			    	})
		    	});
	    	}else{
	    		$("#subjectList tr td:nth-child(8)").each(function(){
		    		$(this).html(0);
		    	})
	    	}
	     },    
	     error : function() {
// 	         $.alert("异常！");    
	     }    
	});
}
function getCountTp(){
	var url = "${pageContext.request.contextPath}/resource/getCountTp";
	var aj = $.ajax({
	    url: url,
	    data:"gradeCode="+$("#nj").val(),    
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.length>0){
	    		$.each(data,function(index,value){
		    		$("#subjectList tr td:nth-child(4)").each(function(){
			    		var subject = $(this).attr("data-subject");
			    		if(value.subjectCode==subject){
			    			$(this).html(value.tpCount);
			    		}else{
			    			var html = $(this).html()+"";
			    			if(html==""||html=="0"){
			    				$(this).html(0);
			    			}
			    		}
			    	})
		    	});
	    	}else{
	    		$("#subjectList tr td:nth-child(4)").each(function(){
		    		$(this).html(0);
		    	})
	    	}
	     },    
	     error : function() {
// 	         $.alert("异常！");    
	     }    
	});
}

function getOther(){
	var url = "${ctp}/resource/getCountOther";
	var aj = $.ajax({
	    url: url,
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.length>0){
	    		$.each(data,function(index,value){
	    			$("#subjectList tr td:gt(0)").each(function(){
	    				var subject = $(this).attr("data-subject");
	    				if((value.subjectName+"")==subject){
	    					$(this).html(value.count);
	    				}
	    			});
	    		});
	    	}
	     },    
	     error : function() {
// 	         $.alert("异常！");    
	     }    
	});
}
</script>
</html>