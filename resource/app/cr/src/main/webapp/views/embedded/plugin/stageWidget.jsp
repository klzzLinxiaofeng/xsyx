<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_dxa.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
<style>
ul, ol {
    padding: 0;
    margin: 0 0 0 0;
}
</style>

<div class="ku_select">
	<div class="xdkm_div">
		<div class="xd_km">
			<div class="xueduan">
				<label>学段：</label>
				<div id="stage" class="xd"></div>
				<div class="clear"></div>
			</div>
			<div class="xueduan">
				<label>科目：</label>
				<div id="subject" class="xd"></div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
</div>
<div class="neirong_zs">
	<a href="javascript:void(0)" class="open_left" ></a>
	<div class="nr_left" >
		<div class="zszj_select">
			<ul></ul>
		</div>
		<div class="banben" id="versionAndVolumn">
		</div>
		<div class="zj_mulu">
			<div class="jspContainer">
				<ul class="un_ul">
				</ul>
			</div>
		</div>
		<div class="no_mulu" style="display:none"></div>
	</div>
<div class="ml_tishi">
	<div class="i_know"></div>
	<a href="javascript:void(0)">我知道了</a>
</div>
</div>
<div class="zzc" ></div>
<script type="text/javascript">
var h = 0;
//滚动高度
function scrollHeight(){
	var nr_right_height = $('.nr_right').height();
		
		var windowHeight = $(window).height()-16-12;
		
		var windowHeight1 = $(window).height()-46-12;
		$(".nr_right .dxa_list").css("min-height",windowHeight1); 
		//学段科目高度
		var ku_select_height = $('.ku_select').height();
		var nr_left_top = ku_select_height+77;
		
		if(nr_right_height>windowHeight-nr_left_top){
			 $('.nr_left').css({"height":windowHeight-nr_left_top});
		}
		
		 var aa = $(window).scrollTop();
	     var dd=nr_left_top-aa;
	     if($("body").find(".qyzj_header").length>0){
	     	var cc = 136+$(".content_main .ku_select").height()+$(".qyzj_header").height()-aa;
	     }else{
	    	 var cc = 136+$(".content_main .ku_select").height()-aa;
	     }
	     if(aa<nr_left_top){
	    	 $('.nr_left').css({"height":windowHeight});
	    	 $('.nr_left').css({"position":"relative","height":windowHeight});
	    	 $(".ml_tishi").css("top",cc);
	     }else{
	    	 $('.nr_left').css({"position":"fixed","height":windowHeight,"top":"0"});
	    	 var c_h=aa+0.5*windowHeight;
	    	 $(".close_left,.open_left").css("top",aa);
	    	 $(".ml_tishi").css("top",'60px');
	     }
}
 $(window).scroll(scrollHeight);
window.onresize=scrollHeight; 

var redisKey = "";


$(function(){	
	scrollHeight();
	$('.nr_left').css('min-height','800px');
	//$('.dxa_list').css('min-height','540px');
	
	$(".ml_tishi a").click(function(){
		$(".ml_tishi,.zzc").hide();
	})
	
	$(".neirong_zs").hide();
	var stageWidgetHandler = null;
	var versionAndVolumnHandler = null;
	$(".neirong_zs .nr_left").on("click",".zszj_select li input[type='radio']",function(){
		$(".neirong_zs .nr_left .zszj_select li").removeClass("on");
		$(this).parent().addClass("on")
	});
	
	$(".lesson-link").click(function(){
		$(".lesson-link").removeClass("on font-blue");
		$(".un-link").removeClass("font-blue")
		$(this).addClass("on");
		$(this).parents(".second_ul").prev(".title").children(".lesson-link").addClass("font-blue");
		$(this).parents(".second_ul").prev(".title").children(".un-link").addClass("font-blue");
	})
	
	$("body").on("click",".xdkm_div .xd_km .xueduan .xd a",function(){
		$(this).siblings().removeClass("btn-blue");
		$(this).addClass("btn-blue");
	})
	
	/*学段组件(第一次默认选择老师任教学段和科目，下次记录老师最后一次的选择进行回显)
	 *type 1公共库、2校本库、3收藏夹
	 *knowledge是否展示知识点选项  true显示  false不显示
	 *afterHandler 回调函数
	 *saveTab 存储到redis自己自定义的key
	 */
	$.stageWidget = function(type, knowledge, afterHandler, saveTab) {
		//如果不传自定义key, 则为空
		if("undefined"!=typeof(saveTab)){
			redisKey = saveTab;
		}
		
		//回显标志 0回显  1不回显
		var tabs = 0;
		//如果type=2为收藏夹，则不需要回显科学段目记录/
		if(type==2) {
			tabs=1;
		}
		//记录回调函数(因为要跨函数，避免回调函数多次被当作参数传入)
		stageWidgetHandler = afterHandler;
		//请求学校的学段
		$.get("${ctp}/base/widget/getStage", {"key":redisKey}, function(data, status) {
			if ("success" === status) {
				var xd = "<a href='#' data-code='0' tabs='1' onclick='$.getSubject("+type+","+knowledge+",0)'>全部</a>";
				var param = JSON.parse(data);
				//回显到那个位置标志
				var index = 0;
				for(var i=0; i<param.length; i++) {
					//如果该学段有选择记录
					if(param[i].selected) {
						//标志回显的位置
						index = i+1;
						xd+="<a href='#' data-code='"+param[i].code+"' tabs='0' onclick='$.getSubject("+type+","+knowledge+","+param[i].code+")' class='btn-blue'>"+param[i].name+"</a>";
					} else {
						xd+="<a href='#' data-code='"+param[i].code+"' tabs='1' onclick='$.getSubject("+type+","+knowledge+","+param[i].code+")'>"+param[i].name+"</a>";
					}
				}
				//添加学段
				$("#stage").html(xd);
				//回显轨迹 0为需要回显轨迹 不为0则不需要
				if(tabs!=0) {
					$("#stage").children("a").eq(0).click();
				}else {
					$("#stage").children("a").eq(index).click();
				}
				tabs++;
			}
		});
	}
	
	/*获取科目组件(被学段组件所调用)
	 *type 1公共库、2校本库、3收藏夹
	 *knowledge是否展示知识点选项  true显示  false不显示
	 *id 学段的id id为0则显示全部学段
	 */
	$.getSubject = function(type, knowledge, id) {
		//请求科目的参数
		var param = {"stageCode":id, "subjectCode":null, "key":redisKey};
		
		var tabs = $("#stage .btn-blue").attr("tabs");
		//除收藏夹外和当前学段(第一次触发回显学段会让科目处于全部状态，此时存储，会丢失上次存储的科目信息)外其它的情况需要存储轨迹
		if(type!=2 && tabs==1) {
			//存储轨迹
			$.saveTrail(param);
		} else {
			//取消第一次回显学段标志
			$("#stage .btn-blue").attr("tabs", "1");
		}
		
		var url = "";
		if(type==1) {
			//公共科目
			url = "${ctp}/base/widget/getSubject";
		}else {
			//学校科目
			url = "${ctp}/base/widget/getResSubject";
		}
		
		//请求获取科目
		$.get(url, param, function(data, status) {
			if ("success" === status) {
				var km = "<a href='#' data-code='"+0+"' onclick='$.getSelectData("+id+",0,"+type+","+knowledge+")'>全部</a>";
				var subject = JSON.parse(data);
				var index = 0;
				for(var i=0; i<subject.length; i++) {
					//如果该科目有选择记录
					if(subject[i].selected) {
						//标志回显的位置
						index = i+1;
					}
					km+="<a href='#' data-code='"+subject[i].code+"' onclick='$.getSelectData("+id+","+subject[i].code+","+type+","+knowledge+")'>"+subject[i].name+"</a>";
				}
				//添加科目
				$("#subject").html(km);
				
				//收藏夹不需要回显轨迹
				if(type==2) {
					$("#subject").children("a").eq(0).click();
				} else {
					//如果学段处于全部状态并且不是第一次点击学段则选择全部
					if(id==0 && tabs!=1) {
						$("#subject").children("a").eq(0).click();
					}else {
						//回显科目位置
						$("#subject").children("a").eq(index).click();
					}
				}
				tabs++;
			}
		});
	}
	
	//获取科目和学段信息并存储轨迹，把参数回传到回调函数
	$.getSelectData = function(stage, subject, type, knowledge) {
		
		$(".jspContainer .un_ul").html("");
		//存储轨迹参数
		var data = {"stageCode":stage, "subjectCode":subject}
		var li = "";
		
		if(knowledge) {
			//需要知识点情况
			li+="<li><input name='selectType' stype='knowledge' type='radio' onclick='$.getKnowledgeTree("+stage+","+subject+")'> 按知识点</li>";
		}
		if(type==1) {
			//公共
			li += "<li class=\"on\"><input name='selectType' stype='catalog' type='radio' onclick='$.getCatalogTree("+type+")'> 按章节目录</li>";
		} else {
			//校本
			li += "<li class=\"on\"><input name='selectType' stype='catalog' type='radio' onclick='$.getCatalogTree("+type+")'> 按章节目录</li>";
		}
		$(".banben").show();
		$(".zszj_select").children("ul").eq(0).html(li);
		//收藏夹不需要存储轨迹
		if(type!=2) {
			$.saveTrail(data);
		}
		//组件的回调函数(点击科目时触发回调)
		stageWidgetHandler(data);
	}
	
	//存储轨迹
	$.saveTrail = function(data) {
		if(redisKey!=null) {
			data["key"] = redisKey;
		}
		$.get("${ctp}/base/widget/saveTrail", data);
	}
	
	//获取书籍版本册次信息
	$.getVersionAndVolumn = function(type, info, afterHandler){
		$(".neirong_zs").show();
		//点击目录时的回调
		versionAndVolumnHandler = afterHandler;
		var banben = "";
		var url = "";
		if(type==1) {
			//公共
			url = "${ctp}/base/widget/getVersionAndVolumn";
			banben+="<select id='version' onchange='$.getCatalogTree("+type+")'>";
		} else {
			//校本
			url = "${ctp}/base/widget/getResVersionAndVolumn";
			banben+="<select id='version' onchange='$.getCatalogTree("+type+")'>";
		}
		//请求版本册次信息
		$.get(url, info, function(data, status) {
			if ("success" === status) {
				var param = JSON.parse(data);
				//版本册次为空
				if(param.length==0) {
					banben+="<option value=>无</option>";
					banben+="</select>"
					$("#versionAndVolumn").html(banben);
					$(".un_ul").html("");
					$(".zj_mulu").hide();
					$(".no_mulu").show();
					return;
				} else {
					//不为空的情况
					$(".zj_mulu").show();
					$(".no_mulu").hide();
				}
				banben+="<option value=>全部</option>"
				for(var i=0; i<param.length; i++) {
					banben+="<option value="+param[i].id+">"+param[i].info+"</option>"
				}
				banben+="</select>"
				//设置版本科目信息
				$("#versionAndVolumn").html(banben);
			}
		});
	}
	
	//获取知识点的树形结构
	$.getKnowledgeTree = function(stage, subject) {
		$(".banben").hide();
		//请求知识点的参数
		var param = {"stageCode":stage, "subjectCode":subject};
		var textbookId = $("#version").val();
		//请求知识点
		$.get("${ctp}/base/widget/getKnowledgeTree", param, function(data, status) {
			if ("success" === status) {
				//没有知识点
				if(data==null || ""==data) {
					$(".zj_mulu").hide();
					$(".no_mulu").show();
					return ;
				} else {
					//有知识点的情况
					$(".no_mulu").hide();
					$(".zj_mulu").show();
				} 
				$(".no_mulu").show();
				//数据解析和拼装
				var result = JSON.parse(data);
				var li = "<li class='un-item'>";
				li+="<div class=\"title\"><a href=\"javascript:void(0)\" class=\"li_close\"></a>";
				li+="<a href='#' data-code='"+result.treeId+"' onclick='$.getResCatalogId("+result.treeId+",1)' class='un-link'>全部</a>";
				li+="</div>";
				var children = result.childrens;
				//递归获取知识点
				li+=$.showTree(children, 1);
				//设置知识点
				$(".un_ul").html(li);
			}
		});
		
	}
	
	//获取目录的树形结构
	$.getCatalogTree = function(type) {
		$(".banben").show();
		var url = "";
		if(type==1) {
			url = "${ctp}/base/widget/getCatalogTree";
		} else {
			url = "${ctp}/base/widget/getResCatalogTree";
		}
		//获取书的id
		var textbookId = $("#version").val();
		//如果书为空，刚回调点击目录的函数
		if(textbookId==null || ""==textbookId) {
			$("#subject .btn-blue").click();
			return;
		} else {
			$(".no_mulu").hide();
			$(".zj_mulu").show();
		}
		
		//请求目录
		$.get(url, {"textbookId":textbookId}, function(data, status) {
			if ("success" === status) {
				//数据解析及数据的拼装
				var param = JSON.parse(data);
				var li="<li class='un-item'><div class=\"title\"><a href=\"javascript:void(0)\" class=\"li_close\"></a>";
				li+="<a href='#' data-code='"+param.textbookId+"' onclick='$.getResCatalogId("+param.textbookId+",1)' class='un-link'>全部</a></div>";
				li+="<li class='un-item'><div class=\"title\"><a href=\"javascript:void(0)\" class=\"li_close\"></a>";
				li+="<a href='#' data-code='"+param.textbookId+"' onclick='$.getResCatalogId(\""+param.code+"\",0)' class='un-link'>"+param.name+"</a></div>";
				var children = param.childrens;
				//递归获取目录
				li+=$.showTree(children, 1);
				//设置目录
				$(".un_ul").html(li);
				//直接触发点击事件获取参数
				$(".jspContainer ul .un-item .title a").eq(1).click();
				$(".un-item").each(function(){
					console.log($(this).find(".second_ul").length)
					if($(this).find(".second_ul").length==0){
						$(this).children('div').children(':first-child').addClass('bluey');
					}
				})
			}
		});
	}
	
	//递归获取树结构的信息
	$.showTree = function(children, level){
		var li = "";
		if(level!=1) {
			li+="<div class='second_ul'><ul class='le-list'>";
		}
		for(var i=0; i<children.length; i++) {
			var child = children[i].childrens;
			if(level==1) {
				li+="<li class='un-item'><div class=\"title\"><a href=\"javascript:void(0)\" class=\"li_close\"></a>";
				li+="<a href='#' data-code='\""+children[i].code+"\"' onclick='$.getResCatalogId(\""+children[i].code+"\",0)' class='un-link'>"+children[i].name+"</a></div>";
			}else {
				if(child.length>0) {
					li+="<li><div class=\"title\"><a href=\"javascript:void(0)\" class=\"li_close\"></a>";
				} else {
					li+="<li><div class=\"title\">";
				}
				li+= "<a href='#' data-code='\""+children[i].code+"\"' onclick='$.getResCatalogId(\""+children[i].code+"\",0)' class='lesson-link'>"+children[i].name+"</a></div>";
				
			}
			if(child.length>0){
				var count = level+1;
				li+=$.showTree(child, count);
				li+="</li>";
			}
		}
		if(level!=1) {
			li+="</div>"
		}
		return li;
	}
	
	/*获取目录或者知识点的信息
	 *id 目录的id
	 *type 0 id为目录id  1 id为书箱id
	 */
	$.getResCatalogId = function(id, type) {
		var selectType = $(".zszj_select").find("ul .on input").attr("stype");
		if(type==0) {
			//目录或者知识点的参数
			var data = {"code":id, "textbookId":0, "type":selectType};
			versionAndVolumnHandler(data);
		} else {
			//目录或者知识点的参数
			var data = {"code":0, "textbookId":id, "type":selectType};
			versionAndVolumnHandler(data);
		}
	}
	
	//目录树打开关闭
	$(".jspContainer").on("click"," ul .li_open",function(){
		$(this).parent().next().show();
		$(this).removeClass("li_open").addClass("li_close");
	});
	$(".jspContainer").on("click"," ul .li_close",function(){
		$(this).parent().next().hide();
		$(this).removeClass("li_close").addClass("li_open");
	});
	$(".jspContainer ul .li_close").each(function(){
		if($(this).parent().next().length==0){
			$(this).hide();
		}
	});
	$(".jspContainer ul .li_open").each(function(){
		if($(this).parent().next().length==0){
			$(this).hide();
		}
	});
	$("body").on("click",".lesson-link",function(){
		$(".lesson-link,.un-link").removeClass("on font-blue");
		$(this).addClass("on");
		$(this).parents(".second_ul").prev(".title").children(".lesson-link").addClass("font-blue")
		$(this).parents(".second_ul").prev(".title").children(".un-link").addClass("font-blue")
	});
	$("body").on("click",".un-link",function(){
		$(".lesson-link,.un-link").removeClass("on font-blue");
		$(this).addClass("on");
	});
	$(".open_left").click(function(){
		scrollHeight()
	})
});
//滚动条
$(document).ready(function() {  
	$(".jspContainer").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
}); 
</script>