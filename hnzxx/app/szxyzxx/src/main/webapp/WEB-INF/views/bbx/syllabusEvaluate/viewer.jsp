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
.content-widgets p{margin-bottom:4px;}
.clearfix{padding-bottom:4px;}
.color_ab{ color:#9e9e9e; padding-left:0px !important;}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;padding:0;">
					<form class="form-horizontal tan_form" id="syllabusevaluate_form" action="javascript:void(0);" style="margin-top: 15px;">
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
							
							<div class="content" style="padding-bottom:5px;">
							
						      <div class="block clearfix" style="padding-bottom:23px;overflow: hidden;">
						    <div style="float:left;padding-top:2px; width:100%; padding-left:171px;font-size:12px; font-style:oblique; "> <span>平均分</span>      <span style=" border-bottom: 1px solid #eb3838;padding-top:3px;padding-left: 0;color: #eb3838;font-size: 25px; font-weight: bold;">${syllabusEvaluate.score}分</span>
							   </div>
							 </div>
							
						    <div id="startone"  class="block clearfix" >
						    <div style="float:left;padding-top:2px; font-size:14px; font-weight: bold;">教学目标与内容：</div>
<!-- 						          <div class="star_score"></div> -->
						          <div class="atar_Show">
			                   	 		<p tip="${syllabusEvaluate.item1}"></p>
			                		</div>
						          <p style="float:left; padding-top:3px;">您的评分：${syllabusEvaluate.item1}分</p>
								<p class="color_ab">(目标设定符合课标、教材和学生实际，利于学生发展，重难点把握准确，精讲略讲恰如其分。)</p>
						    </div>
						    <div id="startone2" class="block clearfix">
						    <div style="float:left;padding-top:2px; font-size:14px; font-weight: bold;">教学过程与方法：</div>
<!-- 						          <div class="star_score"></div> -->
						          <div class="atar_Show">
			                   	 		<p tip="${syllabusEvaluate.item2}"></p>
			                		</div>
						          <p style="float:left; padding-top:3px;">您的评分：${syllabusEvaluate.item2}分</p>
									<p class="color_ab">(课堂结构合理，安排科学，充分运用教学智慧，恰当使用教学媒体辅助教学。)</p>
						    </div>
						    <div id="startone3" class="block clearfix">
						    <div style="float:left;padding-top:2px; font-size:14px; font-weight: bold;">基本素质与能力：</div>
<%-- 						          <div class="star_score" tip="${syllabusEvaluate.item3}"></div> --%>
						          <div class="atar_Show">
			                   	 		<p tip="${syllabusEvaluate.item3}"></p>
			                		</div>
						          <p style="float:left; padding-top:3px;">您的评分：${syllabusEvaluate.item3}分</p>
									<p  class="color_ab">(语言准备，教态自然大方，板书规范合理，富有教学智慧，课堂控制能力强。)</p>
						    </div>
						    <div id="startone4" class="block clearfix">
						    <div style="float:left;padding-top:2px; font-size:14px; font-weight: bold;">教学效果与特色：</div>
						          <div class="atar_Show">
			                   	 		<p tip="${syllabusEvaluate.item4}"></p>
			                		</div>
<!-- 			                		<div class="star_score" ></div> -->
						          <p style="float:left; padding-top:3px;">您的评分：${syllabusEvaluate.item4}分</p>
									<p class="color_ab">(达到预期教学目标，不同层次的学生都能在原有基础上有所收获，体现独特、创新的教学风格与特色。)</p>
						    </div>
							
						    <div class="block clearfix">
						    <div style="float:left;padding-top:2px; font-size:14px; font-weight: bold;">评价内容：</div>
							  <p style="float:left; padding-top:3px;">${syllabusEvaluate.content}</p>
							  </div>
							  
						  </div>
					<div class="form-actions tan_bottom"> 
							<%-- <input type="hidden" id="id" name="id" value="${syllabusEvaluate.id}" />  --%>
								<button class="btn btn-warning" type="button" onclick="outwindow()">关闭</button>
					</div> 
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

function outwindow(){
	var iddiv = $(window.parent.document).find('.layui-layer-shade').last().attr("times");
$(window.parent.document).find('#layui-layer-shade'+iddiv).remove();
$(window.parent.document).find('#layui-layer'+iddiv).remove();
}
// scoreFun($("#startone"))
// scoreFun($("#startone2"))
// scoreFun($("#startone3"))
// scoreFun($("#startone4"))
// scoreFun($("#starttwo"),{
//       fen_d:18,//每一个a的宽度
//       ScoreGrade:5//a的个数5
//     });
//     $('.content>div').each(function(index,e){
//     	   $(this).find('.star_score').find('a').last().click()
//     })
//显示分数
 $(".atar_Show p").each(function(index, element) {
   var num=$(this).attr("tip");
   var www=num*2*16-20;//
   $(this).css("width",www);
   $(this).parent(".atar_Show").siblings("span").text(num+"分");
});
// =====
</script>
</html>