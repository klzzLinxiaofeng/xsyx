<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dxa/arrange.css" rel="stylesheet">
<title>错题本</title>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="错题本" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="ctb">
				<div class="wrong_question">
					<div class="execution_class">
				        <a href="javascript:void(0);" class="execution">一年级3班错题本</a>
				        <a href="javascript:void(0);">年级错题本</a>
				    </div>
				    <div class="ct_div">
				    	<div class="ct_list">
				    		<div class="top">
				    			<p>正确率</p>
				    			<ul>
				    				<li class="a1"><a href="javascript:void(0)"><i></i><30%</a></li>
				    				<li class="a2"><a href="javascript:void(0)"><i></i>30~50%</a></li>
				    				<li class="a3"><a href="javascript:void(0)"><i></i>50~65%</a></li>
				    				<li class="a4"><a href="javascript:void(0)"><i></i>65~85%</a></li>
				    			</ul>
				    			<span>温馨提示：点击色块即可显示或隐藏对应题目</span>
				    			<div class="clear"></div>
				    		</div>
				    		<div class="question_main">
				    			<div class="d_top">
				    				<p>难度：<span>困难</span></p>
				    				<p>正确率：<span class="zql_1">10%</span></p>
				    				<p>考试时间：<span>2017/04/26</span></p>
				    				<div class="bj_btn">
				    					<a href="javascript:void(0)" class="a1">标记为经典错题</a> 
			    					<a href="javascript:void(0)" class="a2" style="display:none">已标记</a>
			    					<a href="javascript:void(0)" class="a3" style="display:none">取消标记</a>
				    				</div>
				    			</div>
				    			<div class="question_bottom">
				    				<div class="q_title">山东简称齐鲁，起源于（     ）</div>
				    				<div class="xzt">
				    					<ul><li class="x_right">A. 远古传说</li><li>B. 西周分封</li><li>C.甲骨文记载</li><li>D. 古代地名</li></ul>
				    				</div>
				    				<div class="q_jiexi">
				    					解析：<span>根据所学知识可以判断，当前中国很多省份的简称都是与西周时期分封制有关。山东省在西周时期属于鲁国和齐国的封地，因此被称为齐鲁大地。</span>
				    				</div>
				    			</div>
				    		</div>
				    		<div class="question_main">
				    			<div class="d_top">
				    				<p>难度：<span>困难</span></p>
				    				<p>正确率：<span class="zql_2">40%</span></p>
				    				<p>考试时间：<span>2017/04/26</span></p>
				    				<div class="bj_btn">
			    					<a href="javascript:void(0)" class="a1">标记为经典错题</a> 
			    					<a href="javascript:void(0)" class="a2" style="display:none">已标记</a>
			    					<a href="javascript:void(0)" class="a3" style="display:none">取消标记</a>
				    				</div>
				    			</div>
				    			<div class="question_bottom">
				    				<div class="q_title">山东简称齐鲁，起源于（     ）</div>
				    				<div class="xzt">
				    					<ul><li class="x_right">A. 远古传说</li><li>B. 西周分封</li><li>C.甲骨文记载</li><li>D. 古代地名</li></ul>
				    				</div>
				    				<div class="q_jiexi">
				    					解析：<span>根据所学知识可以判断，当前中国很多省份的简称都是与西周时期分封制有关。山东省在西周时期属于鲁国和齐国的封地，因此被称为齐鲁大地。</span>
				    				</div>
				    			</div>
				    		</div>
				    		<div class="question_main">
				    			<div class="d_top">
				    				<p>难度：<span>困难</span></p>
				    				<p>正确率：<span class="zql_3">60%</span></p>
				    				<p>考试时间：<span>2017/04/26</span></p>
				    				<div class="bj_btn">
			    					<a href="javascript:void(0)" class="a1">标记为经典错题</a> 
			    					<a href="javascript:void(0)" class="a2" style="display:none">已标记</a>
			    					<a href="javascript:void(0)" class="a3" style="display:none">取消标记</a>
				    				</div>
				    			</div>
				    			<div class="question_bottom">
				    				<div class="q_title">山东简称齐鲁，起源于（     ）</div>
				    				<div class="xzt">
				    					<ul><li class="x_right">A. 远古传说</li><li>B. 西周分封</li><li>C.甲骨文记载</li><li>D. 古代地名</li></ul>
				    				</div>
				    				<div class="q_jiexi">
				    					解析：<span>根据所学知识可以判断，当前中国很多省份的简称都是与西周时期分封制有关。山东省在西周时期属于鲁国和齐国的封地，因此被称为齐鲁大地。</span>
				    				</div>
				    			</div>
				    		</div>
				    		<div class="question_main">
				    			<div class="d_top">
				    				<p>难度：<span>困难</span></p>
				    				<p>正确率：<span class="zql_4">70%</span></p>
				    				<p>考试时间：<span>2017/04/26</span></p>
				    				<div class="bj_btn">
			    					<a href="javascript:void(0)" class="a1">标记为经典错题</a> 
			    					<a href="javascript:void(0)" class="a2" style="display:none">已标记</a>
			    					<a href="javascript:void(0)" class="a3" style="display:none">取消标记</a>
				    				</div>
				    			</div>
				    			<div class="question_bottom">
				    				<div class="q_title">山东简称齐鲁，起源于（     ）</div>
				    				<div class="xzt">
				    					<ul><li class="x_right">A. 远古传说</li><li>B. 西周分封</li><li>C.甲骨文记载</li><li>D. 古代地名</li></ul>
				    				</div>
				    				<div class="q_jiexi">
				    					解析：<span>根据所学知识可以判断，当前中国很多省份的简称都是与西周时期分封制有关。山东省在西周时期属于鲁国和齐国的封地，因此被称为齐鲁大地。</span>
				    				</div>
				    			</div>
				    		</div>
				    	</div>
				    	<div class="ct_list" style="display:none;">
				    		<div class="top">
				    			<p>正确率</p>
				    			<ul>
				    				<li class="a1"><a href="javascript:void(0)"><i></i><30%</a></li>
				    				<li class="a2"><a href="javascript:void(0)"><i></i>30~50%</a></li>
				    				<li class="a3"><a href="javascript:void(0)"><i></i>50~65%</a></li>
				    				<li class="a4"><a href="javascript:void(0)"><i></i>65~85%</a></li>
				    			</ul>
				    			<span>温馨提示：点击色块即可显示或隐藏对应题目</span>
				    			<div class="clear"></div>
				    		</div>
				    		<div class="question_main">
				    			<div class="d_top">
				    				<p>难度：<span>困难</span></p>
				    				<p>正确率：<span class="zql_1">15%</span></p>
				    				<p>考试时间：<span>2017/04/26</span></p>
				    				<div class="bj_btn">
				    					<a href="javascript:void(0)" class="a1">标记为经典错题</a> 
			    					<a href="javascript:void(0)" class="a2" style="display:none">已标记</a>
			    					<a href="javascript:void(0)" class="a3" style="display:none">取消标记</a>
				    				</div>
				    			</div>
				    			<div class="question_bottom">
				    				<div class="q_title">山东简称齐鲁，起源于（     ）</div>
				    				<div class="xzt">
				    					<ul><li class="x_right">A. 远古传说</li><li>B. 西周分封</li><li>C.甲骨文记载</li><li>D. 古代地名</li></ul>
				    				</div>
				    				<div class="q_jiexi">
				    					解析：<span>根据所学知识可以判断，当前中国很多省份的简称都是与西周时期分封制有关。山东省在西周时期属于鲁国和齐国的封地，因此被称为齐鲁大地。</span>
				    				</div>
				    			</div>
				    		</div>
				    		<div class="question_main">
				    			<div class="d_top">
				    				<p>难度：<span>困难</span></p>
				    				<p>正确率：<span class="zql_1">10%</span></p>
				    				<p>考试时间：<span>2017/04/26</span></p>
				    				<div class="bj_btn">
			    					<a href="javascript:void(0)" class="a1">标记为经典错题</a> 
			    					<a href="javascript:void(0)" class="a2" style="display:none">已标记</a>
			    					<a href="javascript:void(0)" class="a3" style="display:none">取消标记</a>
				    				</div>
				    			</div>
				    			<div class="question_bottom">
				    				<div class="q_title">山东简称齐鲁，起源于（     ）</div>
				    				<div class="xzt">
				    					<ul><li class="x_right">A. 远古传说</li><li>B. 西周分封</li><li>C.甲骨文记载</li><li>D. 古代地名</li></ul>
				    				</div>
				    				<div class="q_jiexi">
				    					解析：<span>根据所学知识可以判断，当前中国很多省份的简称都是与西周时期分封制有关。山东省在西周时期属于鲁国和齐国的封地，因此被称为齐鲁大地。</span>
				    				</div>
				    			</div>
				    		</div>
				    	</div>
				    </div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(function(){
// 			标记按钮
			$(".question_main .d_top .bj_btn .a1").click(function(){
				$(this).hide().next(".a2").show();
			});
			$(".question_main .d_top .bj_btn .a2").hover(function(){
				$(this).hide().next(".a3").show();
			});
			$(".question_main .d_top .bj_btn .a3").hover(function(){
				
			},function(){
				$(this).hide().prev(".a2").show();
			});
			$(".question_main .d_top .bj_btn .a3").click(function(){
				$(this).hide(function(){
					$(this).siblings(".a2").hide();
				})
				$(this).siblings(".a1").show();
			});
			//顶部tab切换
			$(".execution_class a").click(function(){
				$(".execution_class a").removeClass("execution");
				$(this).addClass("execution");
				var i=$(this).index();
				$(".ct_list").hide();
				$(".ct_list").eq(i).show();
			});
			//点击正确率
			$(".ct_div .ct_list .top ul li a").click(function(){
				var i=$(this).parent().index()+1;
				if($(this).parent().hasClass("a0")){
					$(this).parent().parent().parent().parent().children().each(function(j){
						if($(this).children().children().children().hasClass("zql_"+i)){
							$(this).show();
						}
					})
					$(this).parent().removeClass("a0");
				}else{
					$(this).parent().parent().parent().siblings(".question_main").each(function(){
						if($(this).children().children().children().hasClass("zql_"+i)){
							$(this).hide();
						}
					})
					$(this).parent().addClass("a0");
				}
			});
		})
	</script>
</body>
</html>