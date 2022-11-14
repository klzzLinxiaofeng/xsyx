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
.out{
border-top:40px #D6D3D6 solid;
width:0px;
height:0px;
border-left:120px #fff solid;
position:relative;
}
b{font-style:normal;display:block;position:absolute;top:-35px;left:-40px;}
em{font-style:normal;display:block;position:absolute;top:-22px;left:-104px;}
.t1{background:#BDBABD;}
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
			<a href="javascript:void(0)">按教师统计</a>
		</div>
		<div class="figure">
		
		<div class="bt">
			<h5 title="教学资源统计">教学资源统计</h5>
			<div id="container" style="  overflow: hidden;"></div>
			</div>
		</div>
		<div class="figure">
			<div class="content-widgets">
				<div class="widget-container">
					<div class="select_b" style="background-color: #fff;">
						<div class="select_div">
							<span>年级：</span><select id="nj" name="nj" class="chzn-select"
								style="width: 120px;"></select>
						</div>
						<button type="button" class="btn btn-primary" onclick="search()">查询</button>
						<div class="clear"></div>
						<div class="bd">
						<h4>教学资源分科统计表</h4>
							<table>
								<thead>
									<tr>
										<td>科目</td>
										<td>微课</td>
										 <td>课件</td>
										<td>作业</td>
										<td>试卷</td>
										<td>素材</td>
										<td>教案</td>
										
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
			
			<!--按教师分科统计开始 ===============================-->
			<div class="figure">
			<div class="content-widgets">
				<div class="widget-container">
					<div class="select_b" style="background-color: #fff;">
						<div class="clear"></div>
						<div class="bd">
						<h4>教学资源按教师分科统计表</h4>
							<table id="tb">
								<thead>
									<tr>
										<td style="padding:0;width:40px;">
											<div class="out">
												<b>科目</b>
												<em>教师</em>
											</div>
										</td>
										<td>微课 (微课笔)</td>
										<td>微课 (普通微课)</td>
										 <td>课件</td>
										<td>作业</td>
										<td>试卷</td>
										<td>素材</td>
										<td>教案</td>
										<td>其他</td>
										<td>总计</td>
									</tr>
								</thead>
								<tbody id="teacherList">
								</tbody>

							</table>
						</div>
					</div>
				</div>
			</div>
			</div>
			<!--按教师分科统计结束 ===============================-->
			
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
	//获取本校所有教师的资源并分科统计 2016-4-18
	getDateOfTeacher('allUser');
});

//获取本校所有教师的资源并分科统计 2016-4-18====================================
function getDateOfTeacher(type){
	//获取教师的userId 及 类型 并将表格展示
	//如果类型是allUser则查询所有的教师
	//如果是下拉选的教师ID 则查询指定的user
	var val = {};
	val.userType = type;
	var url = "${ctp}/resource/getResByTeacher";
	$.post(url,val,function(data){
		data = eval("(" + data + ")");
		if(data.length>0){
			$("#teacherList").html("");
			$.each(data,function(index,value){
				$("#teacherList").append('<tr data-teacher='+ value.userId +'>'+
						'<td>'+ value.name +'</td>'+
						'<td data-teacher='+ value.userId +' data-type=micro_course></td>'+
						'<td data-teacher='+ value.userId +' data-type=common_micro></td>'+
						'<td data-teacher='+ value.userId +'></td>'+
						'<td data-teacher='+ value.userId +'></td>'+
						'<td data-teacher='+ value.userId +'></td>'+
						'<td data-teacher='+ value.userId +'></td>'+
						'<td data-teacher='+ value.userId +'></td>'+
						'<td data-teacher='+ value.userId +'></td>'+
						'<td data-teacher='+ value.userId +'></td>'+
						'</tr>')
			});
			
			//获取微课数据 并填充
			getwkData();
			//获取教案数据 并填充
			getjaData();
			//获取试卷 并填充
			getsjData();
			//获取作业 并填充
			getzyData();
			//获取课件 并填充
			getkjData();
			//获取素材 并填充
			getscData();
			//获取其他 并填充
			getqtData();
			//获取总和 并填充
			getSumData();
		}
	});
}

//获取微课的数据
function getwkData(){
	var url = "${pageContext.request.contextPath}/resource/getMicroData";
	var aj = $.ajax({
	    url: url,
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.length>0){
	    		$.each(data,function(index,value){
		    		$("#teacherList tr td:nth-child(2)").each(function(){
			    		var userId = $(this).attr("data-teacher");
			    		var type = $(this).attr("data-type");
			    		if(value.userId == userId && value.micoType == type){
			    			$(this).html(value.countNumber);
			    		}else{
			    			var html = $(this).html()+"";
			    			if(html==""||html=="0"){
			    				$(this).html(0);
			    			}
			    		}
			    	})
			    	
			    	$("#teacherList tr td:nth-child(3)").each(function(){
			    		var userId = $(this).attr("data-teacher");
			    		var type = $(this).attr("data-type");
			    		if(value.userId == userId && value.micoType == type){
			    			$(this).html(value.countNumber);
			    		}else{
			    			var html = $(this).html()+"";
			    			if(html==""||html=="0"){
			    				$(this).html(0);
			    			}
			    		}
			    	})
		    	});
	    	}else{
	    		$("#teacherList tr td:nth-child(2)").each(function(){
		    		$(this).html(0);
		    	})
	    		$("#teacherList tr td:nth-child(3)").each(function(){
		    		$(this).html(0);
		    	})
	    	}
	     },    
	     error : function() {
//	         $.alert("异常！");
	     }    
	});
}

//获取教案的数据
function getjaData(){
	var url = "${pageContext.request.contextPath}/resource/getTeachingPlanData";
	var aj = $.ajax({
	    url: url,
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.length>0){
	    		$.each(data,function(index,value){
		    		$("#teacherList tr td:nth-child(8)").each(function(){
			    		var userId = $(this).attr("data-teacher");
			    		if(value.userId == userId){
			    			$(this).html(value.countNumber);
			    		}else{
			    			var html = $(this).html()+"";
			    			if(html==""||html=="0"){
			    				$(this).html(0);
			    			}
			    		}
			    	})
		    	});
	    	}else{
	    		$("#teacherList tr td:nth-child(8)").each(function(){
		    		$(this).html(0);
		    	})
	    	}
	     },    
	     error : function() {
//	         $.alert("异常！");
	     }    
	});
}

//获取试卷的数据
function getsjData(){
	var url = "${pageContext.request.contextPath}/resource/getExamData";
	var aj = $.ajax({
	    url: url,
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.length>0){
	    		$.each(data,function(index,value){
		    		$("#teacherList tr td:nth-child(6)").each(function(){
			    		var userId = $(this).attr("data-teacher");
			    		if(value.userId == userId){
			    			$(this).html(value.countNumber);
			    		}else{
			    			var html = $(this).html()+"";
			    			if(html==""||html=="0"){
			    				$(this).html(0);
			    			}
			    		}
			    	})
		    	});
	    	}else{
	    		$("#teacherList tr td:nth-child(6)").each(function(){
		    		$(this).html(0);
		    	})
	    	}
	     },    
	     error : function() {
//	         $.alert("异常！");
	     }    
	});
}

//获取作业的数据
function getzyData(){
	var url = "${pageContext.request.contextPath}/resource/getHomeWorkData";
	var aj = $.ajax({
	    url: url,
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.length>0){
	    		$.each(data,function(index,value){
		    		$("#teacherList tr td:nth-child(5)").each(function(){
			    		var userId = $(this).attr("data-teacher");
			    		if(value.userId == userId){
			    			$(this).html(value.countNumber);
			    		}else{
			    			var html = $(this).html()+"";
			    			if(html==""||html=="0"){
			    				$(this).html(0);
			    			}
			    		}
			    	})
		    	});
	    	}else{
	    		$("#teacherList tr td:nth-child(5)").each(function(){
		    		$(this).html(0);
		    	})
	    	}
	     },    
	     error : function() {
//	         $.alert("异常！");
	     }    
	});
}

//获取课件的数据
function getkjData(){
	var url = "${pageContext.request.contextPath}/resource/getLearningDesingData";
	var aj = $.ajax({
	    url: url,
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.length>0){
	    		$.each(data,function(index,value){
		    		$("#teacherList tr td:nth-child(4)").each(function(){
			    		var userId = $(this).attr("data-teacher");
			    		if(value.userId == userId){
			    			$(this).html(value.countNumber);
			    		}else{
			    			var html = $(this).html()+"";
			    			if(html==""||html=="0"){
			    				$(this).html(0);
			    			}
			    		}
			    	})
		    	});
	    	}else{
	    		$("#teacherList tr td:nth-child(4)").each(function(){
		    		$(this).html(0);
		    	})
	    	}
	     },    
	     error : function() {
//	         $.alert("异常！");
	     }    
	});
}

//获取素材的数据
function getscData(){
	var url = "${pageContext.request.contextPath}/resource/getmaTerialData";
	var aj = $.ajax({
	    url: url,
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.length>0){
	    		$.each(data,function(index,value){
		    		$("#teacherList tr td:nth-child(7)").each(function(){
			    		var userId = $(this).attr("data-teacher");
			    		if(value.userId == userId){
			    			$(this).html(value.countNumber);
			    		}else{
			    			var html = $(this).html()+"";
			    			if(html==""||html=="0"){
			    				$(this).html(0);
			    			}
			    		}
			    	})
		    	});
	    	}else{
	    		$("#teacherList tr td:nth-child(7)").each(function(){
		    		$(this).html(0);
		    	})
	    	}
	     },    
	     error : function() {
//	         $.alert("异常！");
	     }    
	});
}

//获取其他的数据
function getqtData(){
	var url = "${pageContext.request.contextPath}/resource/getOtherData";
	var aj = $.ajax({
	    url: url,
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.length>0){
	    		$.each(data,function(index,value){
		    		$("#teacherList tr td:nth-child(9)").each(function(){
			    		var userId = $(this).attr("data-teacher");
			    		if(value.userId == userId){
			    			$(this).html(value.countNumber);
			    		}else{
			    			var html = $(this).html()+"";
			    			if(html==""||html=="0"){
			    				$(this).html(0);
			    			}
			    		}
			    	})
		    	});
	    	}else{
	    		$("#teacherList tr td:nth-child(9)").each(function(){
		    		$(this).html(0);
		    	})
	    	}
	     },    
	     error : function() {
//	         $.alert("异常！");
	     }    
	});
}

//获取总数
function getSumData(){
	var url = "${pageContext.request.contextPath}/resource/getSumData";
	var aj = $.ajax({
	    url: url,
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.length>0){
	    		$.each(data,function(index,value){
		    		$("#teacherList tr td:nth-child(10)").each(function(){
			    		var userId = $(this).attr("data-teacher");
			    		if(value.userId == userId){
			    			$(this).html(value.countNumber);
			    		}else{
			    			var html = $(this).html()+"";
			    			if(html==""||html=="0"){
			    				$(this).html(0);
			    			}
			    		}
			    	})
		    	});
	    	}else{
	    		$("#teacherList tr td:nth-child(10)").each(function(){
		    		$(this).html(0);
		    	})
	    	}
	     },    
	     error : function() {
//	         $.alert("异常！");
	     }    
	});
}
//获取本校所有教师的资源并分科统计结束 2016-4-18====================================
	
function getDatass(){
	var url = "${ctp}/resource/getResBySchool";
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
		var url = "${ctp}/resource/getSubjectBygrade";
		$.post(url,val,function(data){
			data = eval("(" + data + ")");
			if(data.length>0){
				$("#subjectList").html("");
				$.each(data,function(index,value){
					$("#subjectList").append('<tr data-subject='+ value.subjectCode +'><td>'+ value.subjectName +'</td><td data-subject='+ value.subjectCode +' data-resType=1></td><td data-subject='+ value.subjectCode +' data-resType=2></td><td data-subject='+ value.subjectCode +' data-resType=3></td><td data-subject='+ value.subjectCode +' data-resType=4></td><td data-subject='+ value.subjectCode +' data-resType=5></td><td data-subject='+ value.subjectCode +' data-resType=6></td></tr>')
				});
				
				getMi();//微课
				getLd();//课件
				getHw();//作业
				 getEm();//试卷
				 getMl();//素材
				
				
				 getTp();//教案
				 
				
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

function getMi(){
	var url = "${pageContext.request.contextPath}/resource/getMi";
	var aj = $.ajax({
	    url: url,
	    data:"gradeCode="+$("#nj").val(),    
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
//	         $.alert("异常！");
	     }    
	});
}
function getEm(){
	var url = "${pageContext.request.contextPath}/resource/getEm";
	var aj = $.ajax({
	    url: url,
	    data:"gradeCode="+$("#nj").val(),    
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.length>0){
	    		$.each(data,function(index,value){
		    		$("#subjectList tr td:nth-child(5)").each(function(){
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
	    		$("#subjectList tr td:nth-child(5)").each(function(){
		    		$(this).html(0);
		    	})
	    	}
	     },    
	     error : function() {
//	         $.alert("异常！");
	     }    
	});
}
function getHw(){
	var url = "${pageContext.request.contextPath}/resource/getHw";
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
	    		$("#subjectList tr td:nth-child(4)").each(function(){
		    		$(this).html(0);
		    	})
	    	}
	     },    
	     error : function() {
//	         $.alert("异常！");
	     }    
	});
}
function getLd(){
	var url = "${pageContext.request.contextPath}/resource/getLd";
	var aj = $.ajax({
	    url: url,
	    data:"gradeCode="+$("#nj").val(),    
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.length>0){
	    		$.each(data,function(index,value){
		    		$("#subjectList tr td:nth-child(3)").each(function(){
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
	    		$("#subjectList tr td:nth-child(3)").each(function(){
		    		$(this).html(0);
		    	})
	    	}
	     },    
	     error : function() {
//	         $.alert("异常！");
	     }    
	});
}
function getMl(){
	var url = "${pageContext.request.contextPath}/resource/getMl";
	var aj = $.ajax({
	    url: url,
	    data:"gradeCode="+$("#nj").val(),    
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.length>0){
	    		$.each(data,function(index,value){
		    		$("#subjectList tr td:nth-child(6)").each(function(){
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
	    		$("#subjectList tr td:nth-child(6)").each(function(){
		    		$(this).html(0);
		    	})
	    	}
	     },    
	     error : function() {
//	         $.alert("异常！");
	     }    
	});
}
function getTp(){
	var url = "${pageContext.request.contextPath}/resource/getTp";
	var aj = $.ajax({
	    url: url,
	    data:"gradeCode="+$("#nj").val(),    
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.length>0){
	    		$.each(data,function(index,value){
		    		$("#subjectList tr td:nth-child(7)").each(function(){
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
	    		$("#subjectList tr td:nth-child(7)").each(function(){
		    		$(this).html(0);
		    	})
	    	}
	     },    
	     error : function() {
//	         $.alert("异常！");
	     }    
	});
}

function getOther(){
	var url = "${ctp}/resource/getOther";
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
//	         $.alert("异常！");
	     }    
	});
}
</script>
</html>