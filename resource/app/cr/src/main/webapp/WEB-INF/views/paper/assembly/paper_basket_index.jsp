<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/test/zujuan.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery.fly.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/requestAnimationFrame.js"></script>
<title>试题篮</title>
</head>
<body style="overflow-y: hidden;">
<div class="floatKuang">
	<div class="leftIcon"> 
		<ul class="icon">
			<li class="sjk on"></li>
			<li class="stk"></li>
			<li class="stl"></li>
			<li class="our"></li>		
		</ul>
	</div>
	<div class="central-content">
		<iframe class="iframe_main" id="iframe_main" name="iframe_main" style="width:100%;border:0 none;overflow:auto" onload="i_height()"></iframe>
	</div>
	<div class="kuang">
		<div class="stl">
			<i class="right" style="display:none;"></i>
			<i class="left"></i>
			<p>试题篮</p>
			<span id="end">0</span>
		</div>
		<div class="stl-content" style="display:none;">
			<a href="javascript:void(0)" onclick="start();" class="startZj btn-orange"><i></i>开始组卷</a>
			<br>
			<c:if test="${hasRecord }">
				<a href="javascript:void(0)" onclick="resumeRecord();" class="recoveZj">恢复上一次组卷记录</a>
			</c:if>
		</div>
		<div class="stl-content" style="display:none;">
			<p class="stl-content-top">
				<a href="javascript:void(0)" class="btn-blue add-dt"><i></i>新增大题</a>
				<a href="javascript:void(0)" class="btn-orange volume-set" onclick="batchSetting();">批量设置</a>
				<a href="javascript:void(0)" class="all-delete" onclick="deleteAll();"><i></i></a>
			</p>
			<div class="stl-content-center">
				<ul>
					<c:if test="${hasRecord }">
						<c:forEach items="${assemblyPaper }" var="group">
							<c:if test="${group.groupName!='' }">
								 <li>
									<span class="tm-title">
										<b class="th">1</b>、
										<b class="bt">${group.groupName }</b>
									</span>
									<span class="del"></span>
									<span class="tm-num"><b style="float: left;color:#2299ee;font-weight: normal;margin-right: 3px;">${group.questionSize}</b>题</span>
								</li>
							</c:if>
						</c:forEach>
					</c:if>
				</ul>
			</div>
			<p class="stl-content-bottom">
				<a href="javascript:void(0)" onclick="goToBasket()" class="btn-green">进入试题篮<i></i></a>
			</p>
		</div>
	</div>
</div>
<div class="create_div" style="display:none;">
	<div style=" line-height: 97px;text-align: center;font-size: 14px;">
		开始组卷将放弃上次编辑的组卷
	</div>
</div>
<div class="join_basket" style="display:none;">
	<div class="jbas">
	<span style="font-size: 14px;">请选择大题：</span>
	<select id="selectGroup" style="border-radius: 4px;">
		<c:if test="${hasRecord }">
			<c:forEach items="${assemblyPaper }" var="group">
				<c:if test="${group.groupName!='' }">
					<option>${group.groupName }</option>
				</c:if>
			</c:forEach>
		</c:if>
	</select>
	</div>
</div>
<div class="basket_noNull_div" style="display:none;">
	<div style="margin-left: 53px;margin-top: 33px;">
		<i></i>
		<p>试题篮不为空，将整卷加入组卷会清空试题篮，是否继续？</p>
	</div>
</div>
<div class="deleteALL" style="display:none;">
	
		<p style="text-align:center;font-size: 14px;margin-top: 55px;">该操作即将删除全部大题，是否继续？</p>
	
</div>
<img class="u-flyer" src="/cr/res/qyjx/css/images/dxa_logo.png" style="margin-top: 0px; margin-left: 0px; position: fixed; width: 0px; height: 0px; font-size: 0px; left: 1897.5px; top: 393px;">
<script type="text/javascript">
var hasRecord = "${hasRecord }";
var stageCode = "${stageCode}";
var ownerModel = "${ownerModel}";

$(function(){
	//左边选项的高度
	var clientHeight = $(window).height();
	$('.leftIcon').height(clientHeight);
	history.pushState(history.state, "", "${pageContext.request.contextPath}/paper/assembly/basket/index")
});
$(window).resize(function() {
	//左边选项的高度
	var clientHeight = $(window).height();
	$('.leftIcon').height(clientHeight);
	i_height();
});

$.ajaxSetup({
	async : false
});

var state = 0;
var data = new Array();
var offset = $(".floatKuang").find("#end").offset();
function addToBasket(obj) {
	 var type = $(obj).data("type");
	
	if("paper"==type) {
		if(hasRecord=="true") {
			basketNoNull(obj);
		} else {
			fly_basket();
			state = 1;
			var paperid = $(obj).data("paperid");
			start();
			addPaper(paperid);
			setToRedis(paperid);
			hasRecord = "true";
			stageCode = $(obj).data("stage");
		}
	} else {
		var questionId = $(obj).data("questionid");
		var count = parseInt($(obj).data("count"));
		
		if(state==0) {
			if(hasRecord=="true") {
				//开始组卷并放弃上次的组卷
				startPaper();
				return;
			} else {
				start();
			}
		}
		
		var questionSize = $(".stl span").text();
		var size = parseInt(questionSize)+count;
		
		if(data[0].groupName=="") {
			data[0].questionId.push(questionId);
			data[0].questionSize=data[0].questionSize+count;
			questionAdd(obj, questionId);
			$(".stl span").text(size);
			hasRecord = "true";
			fly_basket();
		} else {
			joinBasket(obj, questionId, size);
		}
	} 
}

function setToRedis(paperid) {
	var url = "${pageContext.request.contextPath}/paper/assembly/my/create";
	$.ajax({
	    url: url,
	    type: "POST",
	    data: {"paperId":paperid, "type":1},
	    async: true,
	    success: function(data) {
	    }
	});
}


function questionAdd(obj, questionId) {
	var param = JSON.stringify(data);
	
	$.ajax({
	    url: "${pageContext.request.contextPath}/paper/assembly/basket/add",
	    type: "POST",
	    data: {"questionId":questionId, data:param},
	    async: true,
	    success: function(data) {
	    	if(data=="success") {
	    		stageCode = $(obj).data("stage");
	    		basketDomDel(obj);
	    		checkStage();
	    	} else {
	    		$.alert("");
	    	}
	    }
	});
}

//将试题从试题篮中移除
function removeFromBasket(obj) {
	var questionId = $(obj).data("questionid");
	var count = parseInt($(obj).data("count"));
	
	for (var i = 0; i < data.length; i++) {
		var questionIds = data[i].questionId;
		for (var j = 0; j < questionIds.length; j++) {
			var temp = questionIds[j];
			if(temp==questionId) {
				//从数据中移除题目
				questionIds.remove(j);
				//试题篮题目总数
				var questionSize = $(".stl span").text();
				//试题篮题目总数减一
				$(".stl span").text(parseInt(questionSize)-count);
				//新的题组题目
				var groupQuestionSize = $(".stl-content-center .tm-num").eq(i).children("b").text();
				$(".stl-content-center .tm-num").eq(i).children("b").text(parseInt(groupQuestionSize)-count);
				var size = data[i].questionSize;
				data[i].questionSize = size-count;
				//后台执行移动操作
				removeQuestion(questionId, obj);
			}
		}
	}
}

function removeQuestion(questionId, obj) {
	var param = JSON.stringify(data);
	$.ajax({
	    url: "${pageContext.request.contextPath}/paper/assembly/basket/remove",
	    type: "POST",
	    data: {"questionId":questionId, data:param},
	    async: true,
	    success: function(data) {
	    	if(data=="success") {
	    		//移出成功
	    		basketDomAdd(obj);
	    		var size = $(".stl span").text();
	    		if(size=="0") {
	    			stageCode = "";
	    			hasRecord = "false";
	    			checkBtnState();
	    		}
	    	} else {
	    		$.alert("移出试题篮失败");
	    	}
	    }
	});
}

function checkBtnState() {
	if(state==0) {
		return;
	}
	checkAdd();
}

function checkAdd() {
	$("#iframe_main").contents().find(".basket").each(function(index) {
		basketDomAdd(this);
	})
	checkDel();
} 

function checkDel() {
	$("#iframe_main").contents().find(".basket").each(function(index) {
		for (var i = 0; i < data.length; i++) {
			var questionIds = data[i].questionId;
			for (var j = 0; j < questionIds.length; j++) {
				var questionId = questionIds[j];
				if(questionId==$(this).data("questionid")) {
					basketDomDel(this);
				}
			}
		}
		if(data[0].groupName=="" && data[0].questionSize==0) {
			basketDomAdd(this);
		}
	});
	checkStage();
}

function checkStage() {
	if(""!=stageCode) {
		$("#iframe_main").contents().find(".basket").each(function(index) {
			var stage = $(this).data("stage");
			if(stage!=stageCode) {
				$(this).removeClass();
				$(this).addClass("btn btn-lightGray basket");
				$(this).attr("onclick","javascript:void(0)");
				$(this).after("<span class='dxa_ts'>该试题与试题篮已有试题的学段不同，不能加入</span>");
			}
		})
	}
} 

function basketDomAdd(obj) {
	$(obj).text("加入试题篮");
	$(obj).removeClass();
	$(obj).addClass("btn btn-orange basket");
	$(obj).attr("onclick","window.parent.addToBasket(this)");
	$(obj).next().remove();
}

function basketDomDel(obj) {
	$(obj).text("移出试题篮");
	$(obj).removeClass();
	$(obj).addClass("btn btn-red basket")
	$(obj).attr("onclick","window.parent.removeFromBasket(this)")
}

//放弃上一次组卷记录
function clearPaper() {
	$.ajax({
        url: "${pageContext.request.contextPath}/paper/assembly/remove",
        type: "POST",
        data: {},
        async: false,
        success: function() {
        	$(".recoveZj").remove();
        	$(".stl-content-center ul").html("");
        	$("#selectGroup").html("");
        	$('.stl-content').eq(0).hide();
        	$('.stl-content').eq(1).show();
        	$(".stl span").text(0);
        	stageCode = "";
        	hasRecord = "false";
        	initData();
        	checkBtnState();
        }
    });
}

//恢复组卷记录
function resumeRecord() {
	$('.stl-content').eq(0).hide();
	$('.stl-content').eq(1).show();

	groupOrder();
	
	var groups = JSON.parse('${assemblyPaperJson}');
	data = groups;
	
	var length = 0;
	for (var i = 0; i < groups.length; i++) {
		length+=groups[i].questionSize;
	}
	
	$(".stl span").text(length);
	
	state = 1;
	
	checkBtnState();
};

function showBasket() {
	$('.stl-content').eq(0).hide();
	$('.stl-content').eq(1).show();
	state = 1;
}

//开始组卷
function start() {
	if(hasRecord=="true") {
		startPaper();
	} else {
		state++;
		initData();
		$('.stl-content').eq(0).hide();
		$('.stl-content').eq(1).show();
	}
}

function initData() {
	var group = {"groupName":"", "pos":1, "questionId":new Array(), "questionSize":0}
	data = new Array();
	data[0] = group;
	//saveGroup();
}

//放弃上一次组卷记录
function saveGroup(paramters) {
	var param = JSON.stringify(data);

	$.ajax({
        url: "${pageContext.request.contextPath}/paper/assembly/group/save",
        type: "POST",
        data: {"data":param},
        async: false,
        success: function(data) {
        	checkBtnState();
        }
    });
}

function startPaper(type){
	layer.closeAll();
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['370px', '192px'],
		  title: '恢复组卷记录', //不显示标题
		  content: $('.create_div'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['重新组卷','恢复组卷'],//按钮
		  btn1: function(index, layero){
			  clearPaper();
			  state++;
			  initData();
			  if(type==1) {
				  $(".sjk").click();
			  }
		  },
		  btn2: function(index, layero){
			  if(type==1) {
				  var display =$('.stl-content').eq(0).css('display');
				  if(display == 'none'){
					  $(".sjk").click();
				  } else {
					  resumeRecord();
					  $(".sjk").click();
				  }
			  } else {
				  resumeRecord();
			  }
		  }
	});
}
 
function joinBasket(obj, questionId, questionSize){
	var count = parseInt($(obj).data("count"));
	
	layer.closeAll();
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['379px', '219px'],
		  title: '加入试题篮', //不显示标题
		  content: $('.join_basket'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['确定','取消'],//按钮
		  btn1: function(index, layero){
			  $(".stl span").text(questionSize);
			  hasRecord = "true";
			  //选中分组的位置
			  var selectIndex = $("#selectGroup").get(0).selectedIndex;
			  //原来分组题目的个数
			  var size = data[selectIndex].questionSize;
			  //题目个数加一
			  data[selectIndex].questionSize = size+count;
			  //设置题目
			  data[selectIndex].questionId.push(questionId);
			  //试题篮里面分组试目加一
			  $(".stl-content-center li").eq(selectIndex).children(".tm-num").children("b").eq(0).text(size+count);
			  questionAdd(obj, questionId);
			  fly_basket();
		  },
		  btn2: function(index, layero){
			  layer.close();
		  }
	});
} 

function basketNoNull(obj){
	layer.closeAll();
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['411px', '237px'],
		  title: '注意', //不显示标题
		  content: $('.basket_noNull_div'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['是','否'],//按钮
		  btn1: function(index, layero){
			  fly_basket();
			 state = 1;
			 var paperid = $(obj).data("paperid");
			 clearPaper();
			 addPaper(paperid);
			 setToRedis(paperid);
			 stageCode = $(obj).data("stage");
			 hasRecord="true";
		  },
		  btn2: function(index, layero){
			  layer.close();
		  }
	});
}

function addPaper(paperid) {
	$.ajax({
        url: "${pageContext.request.contextPath}/paper/basket/getBasketJson",
        type: "POST",
        data: {"paperId":paperid},
        async: false,
        success: function(data) {
        	initBasket(data);
        }
    });
}

function getBasket() {
	$.ajax({
        url: "${pageContext.request.contextPath}/paper/assembly/group/get",
        type: "POST",
        data: {},
        async: false,
        success: function(data) {
        	initBasket(data);
        }
    });
}

function initBasket(param) {
	if(param==null || ""==param) {
		return;
	}

	var paper = JSON.parse(param);
	var li = "";
	var option = "";
	var length = 0;
	
	if(paper.length==1 && paper[0].groupName=="" && paper[0].questionSize==0) {
		hasRecord="false";
	}
	for (var i = 0; i < paper.length; i++) {
		var group = paper[i];
		var questionSize = group.questionSize;
		length+=questionSize;
		if(group.groupName!="") {
			li+="<li><span class=\"tm-title\">";
			li+="<b class=\"th\">"+chinaeseOrder[i]+"</b>、<b class=\"bt\">"+group.groupName+"</b></span>";
			li+="<span class=\"del\"></span>";
			var b = "<b style=\"float: left;color:#2299ee;font-weight: normal;margin-right: 3px;\">"+questionSize+"</b>";
			li+="<span class=\"tm-num\">"+b+"题</span></li>"
		}
		option+="<option>"+group.groupName+"</option>"
	}
	$("#selectGroup").html(option);
	$(".stl-content-center ul").html(li);
	$(".stl span").text(length);
	data = paper;
	saveGroup();
}

function goToBasket() {
	$("ul.icon li").removeClass('on');
	$("ul.icon li").eq(2).addClass('on');
	$(".kuang").hide();
	var loader = new loadDialog();
    loader.show();
    $("#iframe_main").attr("src","${pageContext.request.contextPath}/paper/assembly/index");
	loader.close();
}

//滚动条
$(document).ready(function() {  
	$(".stl-content-center").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
	$('ul.icon li').eq(0).click();
	$('.stl-content').eq(0).show();
});

function i_height(){
	// 	ifre高度高度
	var h =  document.documentElement.clientHeight;
	$("#iframe_main").attr("height",h);
}

//左边选项
$('ul.icon li').click(function(index){
	$(this).addClass('on');
	$(this).siblings("li").removeClass('on');
	var url = "";
	$(".kuang").show();
	if($(this).hasClass("sjk")) {
		if("2"==ownerModel) {
			ownerModel = 1;
			url="${pageContext.request.contextPath}/paper/index?sub=pc&isPersonal=true";
		} else {
			url="${pageContext.request.contextPath}/paper/index?sub=pc";
		}
		if(state!=0) {
			getBasket();
		}
	} else if($(this).hasClass("stk")) {
		url="${pageContext.request.contextPath}/paper/question/index";
		if(state!=0) {
			getBasket();
		}
	} else if($(this).hasClass("stl")) {
		url="${pageContext.request.contextPath}/paper/assembly/my/index?type=basket";
		$(".kuang").hide();
	} else {
		$(".kuang").hide();
		url="${pageContext.request.contextPath}/paper/myIndex";
	}
	if(""!=url) {
		var loader = new loadDialog();
	    loader.show();
	    $("#iframe_main").attr("src",url);
 		loader.close();
	}
	
});

//点击试题篮弹出弹出。。。
$('.kuang .stl').click(function(){
	if($(".stl i.left").is(":hidden")){
		$(".stl i.left").show();
		$(".stl i.right").hide();
		$('.kuang').animate({right:'-303px'},"slow");
	}else{
		$(".stl i.left").hide();
		$(".stl i.right").show();
		$('.kuang').animate({right:'0'},"slow");
	}
});

var chinaeseOrder = ["一","二","三","四","五","六","七","八","九","十","十一",
                     "十二","十三","十四","十五","十六","十七","十八","十九","二十"];
                     
function groupOrder() {
	var xtm_num = $('.stl-content-center').find('li').length;
	if(xtm_num>0){
		$('.stl-content-center ul li').each(function(i){
			 $(this).find('.th').text(chinaeseOrder[i]);
		})
	}
}

//删除小题
$('body').on('click','.del',function(){
	if($(this).prev().children("input").val()==""){
		clearTimeout(timer);
		var index = $(".stl-content-center .del").index(this);
		$(this).parent('li').remove();
		return false;
	}
	
	var index = $(".stl-content-center .del").index(this);
	$("#selectGroup").find("option").eq(index).remove();
	
	//移除分组
	$("#selectGroup option[index='"+index+"']").remove();
	$(this).parent('li').remove();
	
	//重新排序
	var xtm_num = $('.stl-content-center').find('li').length;
	if(xtm_num>0){
		$('.stl-content-center ul li').each(function(i){
			 $(this).find('.th').text(chinaeseOrder[i]);
		})
	}
	
	var size = data[index].questionSize;
	var totalSize = $(".stl span").text();
	$(".stl span").text(parseInt(totalSize)-size);
	
	data.remove(index);
	if(data.length==0) {
		hasRecord = "false";
		stageCode = "";
		clearPaper();
	}
	
	saveGroup();
});

//删除所有
function deleteAll(){
	layer.closeAll();
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['411px', '237px'],
		  title: '注意', //不显示标题
		  content: $('.deleteALL'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['是','否'],//按钮
		  btn1: function(index, layero){
			  	stageCode = "";
				clearPaper();
				$('.stl-content-center').children('ul').find('li').remove();
				$(".stl span").text(0);
		  },
		  btn2: function(index, layero){
			  layer.close();
		  }
	});
}
/* $('.all-delete').click(function(){
	stageCode = "";
	clearPaper();
	$('.stl-content-center').children('ul').find('li').remove();
	$(".stl span").text(0);
	hasRecord="false";
}); */

//新增大题
$('.add-dt').click(function(){
	var bb = $('.stl-content-center').children('ul').children('li').length;
	 var aa = $('<li><span class="tm-title" ><b class="th">'+convertToChinese(bb+1)+'</b>、<b class="bt" style="display:none;"></b>\n'+
 			   ' <input type="text" value="" style="display: inline-block" class="sr-title" maxlength="20"/></span>'+
			   '<span class="del"></span>\n'+
			   '<span class="tm-num"><b style="float: left;color:#2299ee;font-weight: normal;margin-right: 3px;">0</b>题</span>\n'+
			   '</li>'); 
	
	if(bb>0){
			$('.stl-content-center').children('ul').children(':last-child').after(aa);
	}else{
		$('.stl-content-center').children('ul').append(aa);
	}
	$('.sr-title').focus();
	
});
	 
//新增大题文本框失去焦点
var timer;
$('body').on('blur','.sr-title',function(){
	var newTitle = $(this).val();
	if(newTitle==null || newTitle==""){
	    layer.confirm("请输入1~20个字符", {
				btn: '确定',
				cancel:function(){
					$(".sr-title").focus();
					layer.closeAll();
				}},
				function () {
					$(".sr-title").focus();
					layer.closeAll();
				});
	    return false;
	} 
	
	$(this).hide();
	$(this).prev('.bt').show();
	
	$(this).prev('.bt').text(newTitle);
	if(data[0].groupName=="") {
		data[0].groupName = newTitle;
		$(this).parents("li").children('.tm-num').children('b').eq(0).text(data[0].questionSize);
	} else {
		var pos = data.length;
		pos++;
		var group = {"groupName":newTitle, "pos":pos, "questionId":new Array(), "questionSize":0}
		data.push(group);
	}
	var option = "<option>"+newTitle+"</option>";
	$("#selectGroup").append(option);
	saveGroup();
});

Array.prototype.remove = function(dx) { 
	if(isNaN(dx) || dx>this.length) {
		return false;
	}
	for(var i=0,n=0;i<this.length;i++) { 
	  if(this[i]!=this[dx]) { 
	    this[n++]=this[i] 
	  } 
	} 
	this.length-=1 
}

function batchSetting(){
	var size = $(".stl span").text();
	if(size==0) {
		return;
	}
	var url = "${pageContext.request.contextPath}/paper/assembly/question/batchSetting?type=basket";
	$.initWinOnTopFromLeft_qyjx("批量设置", url, '700', '495');
}
//阿拉伯数字改中文数字
function convertToChinese(num) { 
	var AA = new Array("零", "一", "二", "三", "四", "五", "六", "七", "八", "九"); 
	var BB = new Array("", "十", "百", "千", "萬", "億", "点", ""); 
	var a = ("" + num).replace(/(^0*)/g, "").split("."), k = 0, re = ""; 
	for (var i = a[0].length - 1; i >= 0; i--) { 
	switch (k) { 
	case 0: re = BB[7] + re; break; 
	case 4: if (!new RegExp("0{4}\\d{" + (a[0].length - i - 1) + "}$").test(a[0])) 
	re = BB[4] + re; break; 
	case 8: re = BB[5] + re; BB[7] = BB[5]; k = 0; break; 
	} 
	if (k % 4 == 2 && a[0].charAt(i + 2) != 0 && a[0].charAt(i + 1) == 0) re = AA[0] + re; 
	if (a[0].charAt(i) != 0){
		if(k % 4==1){
			if(a[0].charAt(i)==1){
			AA[a[0].charAt(i)]=""
			}
		}
		re = AA[a[0].charAt(i)] + BB[k % 4] + re;}  k++; 
	} 
	
	return re; 
	} 
	
	function fly_basket(){
		var left_x=$("body").width()/2;
		  var img = '${pageContext.request.contextPath}/res/qyjx/css/images/dxa_logo.png';
			var flyer = $('<img class="u-flyer" src="'+img+'">');
			if($(".u-flyer").length>1){
				$(".u-flyer").eq(1).remove();
			}
			flyer.fly({
				start: {
					left: left_x,
					top: 300,
					width: 50,
					height: 50
				},
				end: {
					left: offset.left+10,
					top: offset.top+10,
					width: 0,
					height: 0
				}
			});
			if($(".kuang").css("right")!="0px"){	
				setTimeout("$('.stl i.left').click()",1230);
			}
	}
</script>
</body>
</html>