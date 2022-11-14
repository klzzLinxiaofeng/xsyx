<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="background: #d5d5d5;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/test/zujuan.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">

<style type="text/css">
.blue {
    background: none;
}
</style>
<title>组卷记录</title>
</head>
<body class="d5d5d5">
    <div class="b1-4-0 d5d5d5">
        <div class="b1-4-0-top">
            <span>组卷记录</span>
            <a href="javascript:void(0)" onclick="goBack();" class="btn-lightGray mgr10">返回</a>
        </div>
        <div class="b1-4-0-bottom">
            <div class="zjjl box-sizing">
                <p class="mc">组卷记录</p>
                <ul class="mgb20">
                	<c:if test="${empty assemblyPaper}">
		                <li class="no-notice">
		                	<div >暂无历史组卷记录</div>
		                </li>
	                </c:if>
	                <c:if test="${!empty assemblyPaper}">
	                    <li>
	                        <div class="info fl">
	                            <p class="title">${assemblyPaper.title}</p>
	                            <ul>
	                                <li>
	                                    <span class="mgr20"><i class="update-time"></i>创建试卷：<b><fmt:formatDate pattern="yyyy/MM/dd HH:mm" value="${assemblyPaper.createDate}" /></b></span>
	                                </li>
	                                <li>
	                                    <span class="mgr20"><i class="update-time"></i>更新时间：<b><fmt:formatDate pattern="yyyy/MM/dd HH:mm" value="${assemblyPaper.modifyDate}" /></b></span>
	                                </li>
	                            </ul>
	                        </div>
	                        <p class="cz fr">
	                            <a href="javascript:void(0)" class="btn-green" onclick="resume()">恢复</a>
	                            <a href="javascript:void(0)" class="btn-red" onclick="clearPaper(0,0)">清空</a>
	                        </p>
	                        <div class="clear"></div>
	                    </li>
                    </c:if>
                </ul>
            </div>

            <div id="paper_list">
            	<jsp:include page="./paper_my_list.jsp"></jsp:include>
            </div>
        </div>
    </div>
	<div class="create_div" style="display:none;">
		<div style="margin-left: 53px;margin-top: 33px;">
			<i></i>
			<p>该份试卷已被布置过，无法进行重复编辑操作 如果要继续，只能进行再次创建，是否继续？</p>
        </div>
	</div>
	<div class="delete_div" style="display:none;">
		<p style="width: 236px;margin: 40px auto 0;">该份试卷已被布置过，无法进行删除操作</p>
	</div>
	<div class="clear_item_box" style="display:none;">
		<p style="margin: 40px auto 0;">当前试题篮内已有试题，无法直接编辑这份试卷</p>
		<p style="margin: 0 auto;">你可以<span style="color:red;">清空试题篮</span>直接进行编辑操作：</p>
	</div>
</body>
<script type="text/javascript">
$(function() {
	$(".kuang", window.parent.document).hide();
})

function goBack() {
	$(".kuang", window.parent.document).show();
	$(".icon .sjk", window.parent.document).eq(0).click();
}

function resume() {
	window.location.href="${pageContext.request.contextPath}/paper/assembly/index";
}

//清空试题篮 type0:清空不跳转 1:清空并编辑 2:清空并新建
function clearPaper(paperid, type) {
	$.ajax({
        url: "${pageContext.request.contextPath}/paper/assembly/remove",
        type: "POST",
        data: {},
        async: false,
        success: function(data) {
        	if(data=="success") {
        		if(type==0) {
        			var li = "<li class=\"no-notice\"><div>暂无历史组卷记录</div></li>"
                	$(".zjjl .mgb20").eq(0).html(li);
        		} else if(type==1) {
        			url = "${pageContext.request.contextPath}/paper/assembly/my/edit?paperId="+paperid;
        			createOrEdit(url, paperid);
        		} else if(type==2) {
        			url = "${pageContext.request.contextPath}/paper/assembly/my/create?paperId="+paperid;
        			createOrEdit(url, paperid);
        		}
        	} else {
        		console.log("清空试卷失败");
        	}
        }
    });
}

//type 0编辑试卷  1新建试卷
function editPaper(paperid, hasTask, type) {
	if(hasTask) {
		hasTaskWindow(paperid);
		return;
	}
	var url = "";
	if(type==1) {
		url = "${pageContext.request.contextPath}/paper/assembly/my/edit";
	} else if(type==2){
		url = "${pageContext.request.contextPath}/paper/assembly/my/create";
	}
	$.ajax({
        url: "${pageContext.request.contextPath}/paper/assembly/my/record/count",
        type: "POST",
        data: {},
        async: false,
        success: function(data) {
        	if(data==1) {
        		hasCacheWindow(paperid, type);
        	} else {
        		createOrEdit(url, paperid);
        	}
        }
    });
}

function createOrEdit(url, paperId) {
	var loader = new loadDialog();
    loader.show();
	$.ajax({
        url: url,
        type: "POST",
        data: {"paperId":paperId},
        async: false,
        success: function(data) {
        	layer.close();
        	if(data=="success") {
        		window.location.href="${pageContext.request.contextPath}/paper/assembly/index";
        	} else if(data=="hasTask"){
        		hasTaskWindow(paperId);
        	} else if(data=="noExit") {
        		$.alert("该试卷不存在, 无法进行该操作");
        	} else {
        		$.alert("一个意外的错误!");
        	}
        }
    });
}

function delPaper(paperid, hasTask) {
	if(hasTask) {
		delWindow();
	} else {
		var loader = new loadDialog();
	    loader.show();
		$.ajax({
	        url: "${pageContext.request.contextPath}/paper/assembly/delete",
	        type: "POST",
	        data: {"paperId":paperid},
	        async: true,
	        success: function(data) {
	        	loader.close();
	        	$("#paper_list").load("${pageContext.request.contextPath}/paper/assembly/my/index", {"index":"list"})
	        }
	    });
	}
}

function delWindow() {
	layer.closeAll();
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['370px', '192px'],
		  title: '提示', //不显示标题
		  content: $('.delete_div'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['确定'],//按钮
		  btn1: function(index, layero){
			 
		  }
	});
}

function hasCacheWindow(paperid, type) {
	layer.closeAll();
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['391px', '220px'],
		  title: '提示', //不显示标题
		  content: $('.clear_item_box'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['清空试题篮','取消'],//按钮
		  btn1: function(index, layero){
			  clearPaper(paperid, type);
		  },
		  btn2: function(index, layero){
			 layer.close();
		  }
	});
}

function hasTaskWindow(paperid) {
	layer.closeAll();
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['410px', '237px'],
		  title: '提示', //不显示标题
		  content: $('.create_div'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['确定','取消'],//按钮
		  btn1: function(index, layero){
			  editPaper(paperid, false, 2);
		  },
		  btn2: function(index, layero){
			  layer.close();
		  }
	});
}
</script>
</html>