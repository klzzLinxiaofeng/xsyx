<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_jspj.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
<title>${name}</title>
<style>
.pjsz_1 .p2 input[type="text"] {
    width:150px;
}
</style>
</head>
<body style="background-color:#e3e3e3">
<div class="container-fluid">
	<%--<p class="top_link">首页  >  ${name}  > <span>评价设置</span></p>--%>
	<div class="content_main white">
		<div class="content_top">
			<div class="f_left"><p>评价设置</p></div>
		</div>
		<div class="pjsz grey">
			<div class="pjsz_1">
							<p class="p1">评价周期：</p>
				<p  class="p2"><span><input type="radio" name="pjzq" <c:if test="${task.period == 5}">checked="checked" </c:if>>每日一评</span><span><input type="radio" name="pjzq" <c:if test="${task.period == 4}">checked="checked" </c:if>>每周一评</span><span><input type="radio" name="pjzq"<c:if test="${task.period == 3}">checked="checked" </c:if>>每月一评</span></p>
				
			</div>
			<div class="pjsz_1">
				<p class="p1">评价模式：</p>
				<p class="p2"><span><input type="radio" name="pjms" <c:if test="${task.scoringType == 15}">checked="checked" </c:if>>评五星模式</span><span><input type="radio" name="pjms" <c:if test="${task.scoringType == 11}">checked="checked" </c:if>>点赞模式</span></p>
			</div>
			<div class="pjsz_2" style="display:none">注：这部分暂时不允许点击</div>
		</div>
		<div class="pjsz">
		<div class="pjsz_1"  <c:if test="${task.evType ==1}"> style="display: none;" </c:if>>
				<p class="p1">有效评价：</p>
				<p class="p2">
	
			 <input <c:if test="${task.evType ==1}">type="hidden"</c:if>
			   <c:if test="${task.evType ==2}">type="text"</c:if> value="${person}"  class="person " style="display:inline-block;margin-right:5px">%
			</div>
			<div class="pjsz_1">
				<p class="p1">评价项目：</p>
				<p class="p2">
					<input type="text" placeholder="1" class="data1" maxlength='6'>
					<input type="text" placeholder="2" class="data2" maxlength='6'>
					<input type="text" placeholder="3" class="data3" maxlength='6'>
					<input type="text" placeholder="4" class="data4" maxlength='6'>
					<input type="text" placeholder="5" class="data5" maxlength='6'>
					<input type="text" placeholder="6" class="data6" maxlength='6'>
				</p>
			</div>
			<div class="tx_div">
				<a href="javascript:void(0)" style="z-index:9999"><i class="fa fa-question-circle"></i></a>
				<div class="ts_div1" style="display:none;z-index:1">
					<div class="ts_div">
						<p>注：每课一评，评价老师当天上课的效果与意见反馈。</p>
			            <p>防止无课评价的误操作。误操作的数据将不会纳入到教师评价的统计数据中。</p>
		            </div>
		            <p class="sjx"></p>
	            </div>
            </div>
			<div class="pjsz_2">注：评价项修改以后，下一个周期生效。</div>
		</div>
		<div class="pjsz">
			<button class="btn btn-yellow" style="width:100px;height:38px;margin:30px" onclick="save()">保存</button>
		</div>
	</div>
</div>
<script>
	$(function(){
		$(".pjsz .tx_div a").hover(function(){
			$(this).next().show();
		},function(){
			$(this).next().hide()
		});
		$(".pjsz.grey input[type='radio']").attr("disabled", true);
		var val1={};
		val1.taskId=${task.id}
		$.ajax({
		    url: "${pageContext.request.contextPath}/assessment/statistics/findItem",
		    type: "POST",
		    data: val1,
		    async: false,
		    success: function(data) {
		    	data = eval("(" + data + ")");
		    	for(var  z=0;z<data.length;z++){
		    		if(data[z].listOrder+1==1){
		    			$('.data1').val(data[z].name);
		    		}
		    		if(data[z].listOrder+1==2){
		    			$('.data2').val(data[z].name);
		    		}
		    		if(data[z].listOrder+1==3){
		    			$('.data3').val(data[z].name);
		    		}
		    		if(data[z].listOrder+1==4){
		    			$('.data4').val(data[z].name);
		    		}
		    		if(data[z].listOrder+1==5){
		    			$('.data5').val(data[z].name);
		    		}
		    		if(data[z].listOrder+1==6){
		    			$('.data6').val(data[z].name);
		    		}
		    	}
		    }
		});
	})
	function save(){
		var strAry="";
		var numAry="";
		 var title1 = $.trim($(".data1").val());
		 var title2 = $.trim($(".data2").val());
		 var title3 = $.trim($(".data3").val());
		 var title4 = $.trim($(".data4").val());
		 var title5 = $.trim($(".data5").val());
		 var title6 = $.trim($(".data6").val());
		if(title1==''&&title2==''&&title3==''&&title4==''&&title5==''&&title6==''){
		   $.error("评价项不能全部为空");
		   return false;
		}
		if(title1.length>6||title2.length>6||title3.length>6||title4.length>6||title5.length>6||title6.length>6){
			   $.error("评价项长度不能大于6");
			   return false;
		}
		if(title1!=''){
			strAry=title1+"------";
			numAry=0+"------";
		}
		if(title2!=''){
           strAry=strAry+title2+"------";
           numAry=numAry+1+"------";
		}
		if(title3!=''){
			    strAry=strAry+title3+"------";
	           numAry=numAry+2+"------";
		}
		if(title4!=''){
			  strAry=strAry+title4+"------";
	           numAry=numAry+3+"------";
		}
		if(title5!=''){
			  strAry=strAry+title5+"------";
	           numAry=numAry+4+"------";
		}
		if(title6!=''){
			   strAry=strAry+title6+"------";
	           numAry=numAry+5+"------";
		}
		var person =$('.person').val();
		if(isIntNum(person)){
			
			
		}else{
			$.error("有效比值只能是0到80的整数");
			return false;
		}
		var val={};
		val.taskId=${task.id};
		val.name=strAry;
		val.number=numAry;
		val.percent=person;
		$.ajax({
		    url: "${pageContext.request.contextPath}/assessment/statistics/saveOrUpdate",
		    type: "POST",
		    data: val,
		    async: false,
		    success: function(data) {
		    if(data==='success'){
		    	$.success("保存成功");
		    }
		    if(data==='timeout'){
		    	alert("评价项一个周期只能保存一次");
		    }
		    }
		});
		
	}
	function isIntNum(val){
		var reg = new RegExp("^(\\d|[1-9]\\d|100)$");  
		if(!reg.test(val)) {  
		       return false;
		}
		if(val>80){
			 return false;
		}
		return true;
	}
</script>
</body>
</html>