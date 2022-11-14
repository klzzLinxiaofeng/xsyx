<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<script src="${ctp}/res/js/starScore/starScore.js"></script>
<style>
/* starScore */
.check_con{width: 90%; margin:0 auto; color: #444}
.check_con .check_con_top{ overflow: hidden; line-height: 30px; margin-bottom: -5px;}
.check_con_top .check_con_top_left{ float: left; line-height: 30px;}
.check_con_top .check_con_top_left span{line-height: 30px;
    display: inline-block;
    margin-right: 20px;}
.check_con_top .check_con_top_right{ float: right;overflow:hidden; line-height: 30px;}
.check_con_top .check_con_top_right>div{ float:left; margin-left: 10px; line-height: 30px;}
.check_con_top .check_con_top_right>div input{ line-height: 28px; }
.check_con_top .check_con_top_right>div select{ height: 28px; width: 140px}
.check_but{background: #e68923; border: none; color:#fff; padding:0 15px; cursor:pointer}
.check_con_bottom_title{border-bottom:1px solid #ebebeb; overflow: hidden; margin-bottom: 10px;}
.check_con_bottom_title .check_con_title_left{ float: left; font-weight: bold}
.check_con_bottom_title .check_con_title_right{float: right}
.check_con_bottom_title .check_con_title_right{ display: inline-block;}
.check_con_bottom_list ul li{ width: 25%; float: left; }
.check_con_bottom_list ul li span{text-align: center; background: #ebebeb;display: block; width: 90%; margin: 10px auto;}
#table_check{ width: 100%}
    #table_check th{ font-weight: bold; border-bottom: 1px solid #ebebeb; text-align: left;  padding: 15px 0;}
#table_check td{ line-height: 1.5;    padding: 10px 0; border-bottom: 1px solid #e6e6e6; vertical-align: top;}
#table_check td:nth-child(2){ color: #0fa228}
#table_check td:nth-child(2).red{ color: red}

/*星星样式*/
.content{
}
.title{
    font-size:14px;
    background:#dfdfdf;
    padding:10px;
    margin-bottom:10px;
}
.block{
    width:85%;
    margin:0 auto;
    padding-top:34px;
    line-height:21px;
}
.block .star_score{
    float:left;
}
.star_list{
    height:21px;
    margin:50px;
    line-height:21px;
}
.block p,.block .attitude{
    padding-left:20px;
    line-height:21px;
    display:inline-block;
}
.block p span{
    color:#C00;
    font-size:16px;
    font-family:Georgia, "Times New Roman", Times, serif;
}

.star_score {
    background:url(${pageContext.request.contextPath}/res/images/bbx/bp/starScore/stark2.png);
    width:160px;
    height:21px;
    position:relative;
}

.star_score a{
    height:21px;
    display:block;
    text-indent:-999em;
    position:absolute;
    left:0;
}

.star_score a:hover{
    background:url(${pageContext.request.contextPath}/res/images/bbx/bp/starScore/stars2.png);
    left:0;
}

.star_score a.clibg{
    background:url(${pageContext.request.contextPath}/res/images/bbx/bp/starScore/stars2.png);
    left:0;
}

#starttwo .star_score {
    background:url(${pageContext.request.contextPath}/res/images/bbx/bp/starScore/starky.png);
}

#starttwo .star_score a:hover{
    background:url(${pageContext.request.contextPath}/res/images/bbx/bp/starScore/starsy.png);
    left:0;
}

#starttwo .star_score a.clibg{
    background:url(${pageContext.request.contextPath}/res/images/bbx/bp/starScore/starsy.png);
    left:0;
}
/* ${ctp}/res/images/bbx/bp/starScore */
/*星星样式*/

.show_number li{
    width:240px;
}

.atar_Show{
    background:url(${pageContext.request.contextPath}/res/images/bbx/bp/starScore/stark2.png);
    width:160px; height:21px;
    position:relative;
    float:left;
}

.atar_Show p{
    background:url(${pageContext.request.contextPath}/res/images/bbx/bp/starScore/stars2.png);
    left:0;
    height:21px;
    width:134px;
}

.show_number li span{
    display:inline-block;
    line-height:21px;
}
    .show_number{ padding-top: 5px}
.titme_pj{ text-align: right; color: #999;font-size: 12px}
/* ===== */
.row-fluid .span13 {
	width: 75%;
}

.row-fluid .span4 {
	width: 75%;
}

.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}

.title-group {
	margin-left: 28px;
    margin-top: -21px
}
.font-group {
	font-size: 13px;
}
.title-group span{
	display:inline-block; 
	margin-left: 30px;
	display: inline;
	float : initial;
	/* padding-right:35px; */
}
.star_score{
outline:none }
.block{padding-top:0;}
.content-widgets p{margin-bottom:3px;}
.clearfix{padding-bottom:3px;}
.color_ab{ color:#9e9e9e; padding-left:0px !important;}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="syllabusevaluate_form" action="javascript:void(0);" style="margin-top: 15px;">
						<%-- <input type="text" id="dayOfWeek" name="dayOfWeek" class="span13" placeholder="" value="${dayOfWeek}"> <br> --%>
						<%-- <input type="text" id="lesson" name="lesson" class="span13" placeholder="" value="${lesson}">  <br> --%>
						<%-- <input type="text" id="subjectName" name="subjectName" class="span13" placeholder="" value="${subjectName}">  <br> --%>
						<%-- <input type="text" id="teacherName" name="teacherName" class="span13" placeholder="" value="${teacherName}">  <br> --%>
						<div class="title-group" style="width:650px;" >
							<span class="font-group">星期 : ${dayOfWeek} </span>
							<span class="font-group">课节 : ${lesson}   </span> 
							<span class="font-group">科目 : ${subjectName}</span>
							<span class="font-group">教师 : ${teacherName}</span>
							<span class="font-group"> 
								选择周数 : 
								<select id="week" name="week" style="width: 140px; height: 30px; vertical-align: middle; padding-top: 0px;padding-bottom:0;" >
				                    <option value ="volvo">选择周数</option>
				                    <c:forEach items="${weeks}" var="week">
				                    	<c:choose>
				                    		<c:when test="${week.isCurrent eq true}">
				                    			<option value ="${week.week }" selected="selected">第${week.week }周</option>
				                    		</c:when>
				                    		<c:otherwise>
							                    <option value ="${week.week }">第${week.week }周</option>
				                    		</c:otherwise>
				                    	</c:choose>
				                    </c:forEach>
				                </select>
							</span>
						</div>
						
							<div class="control-group" style="display: none;">
								<label class="control-label">
									id：
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13" placeholder="" value="${syllabusEvaluate.id}">
								</div>
							</div>
							<div class="control-group" style="display: none;">
								<label class="control-label">
									bw_syllabus_lesson主键：
								</label>
								<div class="controls">
								<input type="text" id="lessionId" name="lessionId" class="span13" placeholder="" value="${syllabusEvaluate.lessionId}">
								</div>
							</div>
							<%-- <div class="control-group">
								<label class="control-label">
									第几周：
								</label>
								<select id="week" name="week" >
				                    <option value ="volvo">选择周数</option>
				                    <c:forEach items="${weeks}" var="week">
				                    	<c:choose>
				                    		<c:when test="${week.isCurrent eq true}">
				                    			<option value ="${week.week }" selected="selected">第${week.week }周</option>
				                    		</c:when>
				                    		<c:otherwise>
							                    <option value ="${week.week }">第${week.week }周</option>
				                    		</c:otherwise>
				                    	</c:choose>
				                    </c:forEach>
				                </select>
								<div class="controls">
								<input type="text" id="week" name="week" class="span13" placeholder="" value="${syllabusEvaluate.week}">
								</div>
							</div> --%>
						
							<%-- <div class="control-group" style="margin-left: -43px; margin-top: 30px;">
								<label class="control-label">
									评价分数：
								</label>
								<div class="controls">
								<input type="text" id="score" name="score" class="span13" placeholder="" value="${syllabusEvaluate.score}">
								</div>
							</div> --%>
							
							<div class="content" style="padding-bottom:5px;padding-top:15px;">
						    <div id="startone"  class="block clearfix" >
						    <div style="float:left;padding-top:2px; font-size:14px; font-weight: bold;">教学目标与内容：</div>
						          <div class="star_score"></div>
						          <p style="float:left; padding-top:3px;">您的评分：<span class="fenshu score1" style="display: inline-block;"></span> 分</p>
								<p class="color_ab">(目标设定符合课标、教材和学生实际，利于学生发展，重难点把握准确，精讲略讲恰如其分。)</p>
						    </div>
						    <div id="startone2" class="block clearfix">
						    <div style="float:left;padding-top:2px; font-size:14px; font-weight: bold;">教学过程与方法：</div>
						          <div class="star_score"></div>
						          <p style="float:left; padding-top:3px;">您的评分：<span class="fenshu score2" style="display: inline-block;"></span> 分</p>
									<p class="color_ab">(课堂结构合理，安排科学，充分运用教学智慧，恰当使用教学媒体辅助教学。)</p>
						    </div>
						    <div id="startone3" class="block clearfix">
						    <div style="float:left;padding-top:2px; font-size:14px; font-weight: bold;">基本素质与能力：</div>
						          <div class="star_score"></div>
						          <p style="float:left; padding-top:3px;">您的评分：<span class="fenshu score3" style="display: inline-block;"></span> 分</p>
									<p  class="color_ab">(语言准备，教态自然大方，板书规范合理，富有教学智慧，课堂控制能力强。)</p>
						    </div>
						    <div id="startone4" class="block clearfix">
						    <div style="float:left;padding-top:2px; font-size:14px; font-weight: bold;">教学效果与特色：</div>
						          <div class="star_score"></div>
						          <p style="float:left; padding-top:3px;">您的评分：<span class="fenshu score4" style="display: inline-block;"></span> 分</p>
									<p class="color_ab">(达到预期教学目标，不同层次的学生都能在原有基础上有所收获，体现独特、创新的教学风格与特色。)</p>
						    </div>
						  </div>
						  
							<div class="control-group" style="margin-left: -43px; ">
								<label class="control-label">
									评价内容：
								</label>
								<div class="controls">
								<textarea style="height:150px;resize: none;" id="content" name="content" class="span13"></textarea>
								</div>
							</div>
							<%-- <div class="control-group">
								<label class="control-label">
									发布者userId：
								</label>
								<div class="controls">
								<input type="text" id="postUserId" name="postUserId" class="span13" placeholder="" value="${syllabusEvaluate.postUserId}">
								</div>
							</div> --%>
							<%-- <div class="control-group">
								<label class="control-label">
									记录创建时间：
								</label>
								<div class="controls">
								<input type="text" id="createDate" name="createDate" class="span13" placeholder="" value="${syllabusEvaluate.createDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									记录修改时间：
								</label>
								<div class="controls">
								<input type="text" id="modifyDate" name="modifyDate" class="span13" placeholder="" value="${syllabusEvaluate.modifyDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									记录是否删除：
								</label>
								<div class="controls">
								<input type="text" id="isDeleted" name="isDeleted" class="span13" placeholder="" value="${syllabusEvaluate.isDeleted}">
								</div>
							</div> --%>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${syllabusEvaluate.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

scoreFun($("#startone"))
scoreFun($("#startone2"))
scoreFun($("#startone3"))
scoreFun($("#startone4"))
scoreFun($("#starttwo"),{
      fen_d:18,//每一个a的宽度
      ScoreGrade:5//a的个数5
    });
    $('.content>div').each(function(index,e){
    	   $(this).find('.star_score').find('a').last().click()
    })
//显示分数
 $(".show_number li p").each(function(index, element) {
   var num=$(this).attr("tip");
   var www=num*2*16;//
   $(this).css("width",www);
   $(this).parent(".atar_Show").siblings("span").text(num+"分");
});
// =====
	var checker;
	$(function() {
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#syllabusevaluate_form").validate({
			errorClass : "myerror",
			rules : {
				"week":{
					required:true,
				},
				"content":{
					required:false,
					maxlength : 150
				}
				
			},
			messages : {
				"week":{
					required:"周数必需"
				}
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#syllabusevaluate_form");
			$requestData.item1 = $('.score1').html();
			$requestData.item2 = $('.score2').html();
			$requestData.item3 = $('.score3').html();
			$requestData.item4 = $('.score4').html();
			if ( $requestData.item1 == null || $requestData.item1 == "" 
					||$requestData.item2 == null || $requestData.item2 == ""
					||$requestData.item3 == null || $requestData.item3 == ""
					||$requestData.item4 == null || $requestData.item4 == "") {
				$.error("请选择评分!");
				return null;
			}
			if ( $requestData.week == "volvo" || $requestData.week == null ) {
				$.error("请选择周数!");
				return null;
			}
			var url = "${ctp}/bbx/syllabusEvaluate/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/bbx/syllabusEvaluate/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						if(parent.core_iframe != null) {
 							parent.core_iframe.window.location.reload();
 						} else {
 							parent.window.location.reload();
 						}
						$.closeWindow();
					} else if("dataRepeat" === data.info){
						$.error("请勿重复评分");
					}else {
						$.error("操作失败");
					}
				}else{
					$.error("操作失败");
				}
				loader.close();
			}); 
		}
	}
	
</script>
</html>