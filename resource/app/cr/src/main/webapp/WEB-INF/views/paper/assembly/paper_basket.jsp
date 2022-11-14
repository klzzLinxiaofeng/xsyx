<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="background-color: #d5d5d5;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/test/zujuan.css" rel="stylesheet">
<title>试题篮</title>
</head>
<body style="background-color:#d5d5d5">
    <div class="a1-0">
        <ul>
        	<li>
                <a href="javascript:void(0)" onclick="goToSjk()">
                	<div class="a1-0-2">
                		<p class="p1">+</p>
                		<p class="p2">新建试卷</p>
                	</div>
                </a>
            </li>
            <c:if test="${!empty assemblyPaper}">
            <li>
               <a href="javascript:void(0)" onclick="assemblePaper()"> 
               <div class="draft">
               		<p class="paper-title" style="width: 120px;">${assemblyPaper.title }</p>
               		<p>
               			<span><i></i>创建时间：</span><br>
               			<span class="time"><fmt:formatDate pattern="yyyy/MM/dd" value="${assemblyPaper.createDate}" /></span>
               		</p>
               		<p>
               			<span><i></i>修改时间：</span><br>
               			<span class="time"><fmt:formatDate pattern="yyyy/MM/dd" value="${assemblyPaper.modifyDate}" /></span>
               		</p>
               </div>
               </a>
            </li>
             </c:if>
        	<c:forEach items="${result}" var="paper" varStatus="tab">
	            <li>
	                <a href="javascript:void(0)" onclick="editPaper(${paper.id}, ${paper.hasTask },1)">
	                	<div class="a1-0-1">
	                		<p class="paper-title">${paper.title }</p>
	                		<p>
		               			<span><i></i>创建时间：</span><br>
		               			<span class="time"><fmt:formatDate pattern="yyyy/MM/dd" value="${paper.createDate}" /></span>
		               		</p>
		               		<p>
		               			<span><i></i>修改时间：</span><br>
		               			<span class="time"><fmt:formatDate pattern="yyyy/MM/dd" value="${paper.modifyDate}" /></span>
		               		</p>
	                		<p class="last">
	                			<span style="margin-right: 19px;"><i class="sc" ></i>收藏（<b>${paper.favCount }</b>）</span>
	                			<span><i class="sy"></i>使用（<b>${paper.usedCount }</b>）</span>
	                		</p>
	                	</div>
	                </a>
	            </li>
            </c:forEach>
            <div class="clear">
            </div>
        </ul>
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
function goToSjk(){
	 var length = $(".a1-0 a div.draft").length;
	 if(length==0) {
		 window.parent.start();
		 $(".sjk", window.parent.document).click();
	 } else {
		 window.parent.startPaper(1);
	 }
	 
}
function assemblePaper() {
	window.location.href="${pageContext.request.contextPath}/paper/assembly/index";
}
//type 0编辑试卷  1新建试卷
function editPaper(paperid, hasTask, type) {
	if(hasTask) {
		hasTaskWindow(paperid);
		return;
	}
	var url = "";
	if(type==1) {
		url = "${pageContext.request.contextPath}/paper/assembly/my/edit?paperId="+paperid;
	} else if(type==2){
		url = "${pageContext.request.contextPath}/paper/assembly/my/create?paperId="+paperid;
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
        data: {"paperid":paperId},
        async: false,
        success: function(data) {
        	loader.close();
        	if(data=="success") {
        		window.parent.state=1;
        		window.location.href="${pageContext.request.contextPath}/paper/assembly/index";
        	} else if(data=="hasTask"){
        		hasTaskWindow(paperId);
        	} else if(data=="noExit") {
        		$.alert("该试卷不存在, 无法进行该操作");
        	} else {
        		$.alert("一个意外的错误发生了, 请与管理员联系");
        	}
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

//清空试题篮 type0:清空不跳转 1:清空并编辑 2:清空并新建
function clearPaper(paperid, type) {
	$.ajax({
        url: "${pageContext.request.contextPath}/paper/assembly/remove",
        type: "POST",
        data: {},
        async: false,
        success: function(data) {
        	if(data=="success") {
        		if(type==1) {
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
</script>
</html>