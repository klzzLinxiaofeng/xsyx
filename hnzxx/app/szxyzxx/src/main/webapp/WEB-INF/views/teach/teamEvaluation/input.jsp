<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<title>添加检查项</title>
<script type="text/javascript">
$(function(){
    $(".define-item").hide();//隐藏wenben
    $(".define-item:eq(0)").show();//显示第一个wenben
    $(".plus-minus-nape a").click(function(){
        $(".plus-minus").removeClass("plus-minus");//移除样式
        $(this).addClass("plus-minus");//添加样式
        var i=$(this).index();//获得下标
        $(".define-item").hide();//隐藏wenben
        $(".define-item:eq("+i+")").show();//显示第i个wenben
    });

    $(".click-add").click(function(){
        $(this).prev("ul").append("<li class='add-nape'><input type='checkbox'><input type='text'class='add-name'placeholder='请输入项目标题'></li>");
  });
    $(".define-item ul li").hover(function(){
    	$(this).css("background-color","#f1f1f1")
    	$(this).children(".delete").show();
    },function(){
    	$(this).css("background-color","#fff")
    	$(this).children(".delete").hide();
    });
    $(".define-item ul li .delete").click(function(){
    	$(this).parent().remove();
    })
});

$(function() {
	$.initCascadeSelector({
		"type" : "team",
		"yearChangeCallback" : function(year) {
			if(year != "") {
				$.SchoolTermSelector({
					"selector" : "#xq",
					"condition" : {"schoolYear" : year},
					"afterHandler" : function($this) {
						$this.change();
						$("#xq_chzn").remove();
						$this.show().removeClass("chzn-done").chosen();
					},
				});
			} else {
				$("#xq").val("");
				$("#xq_chzn").remove();
				$("#xq").show().removeClass("chzn-done").chosen();
			}
		}
	});
// 	search();
	
});


</script>
<base target="_self">
</head>
<body style="background-color:#fff !important">
	<div id="show" class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
					<form class="form-horizontal" id="user_form" style="padding:0">
					<div class="choice-prom">
    	<div class="select_b">
    		<div class="select_div"><span>学年：</span><select id="xn" name="xn" class="chzn-select" style="width:150px;"></select></div>
    		<div class="select_div"><span>学期：</span><select id="xq" name="xq" class="chzn-select" onchange="search()" style="width:150px;"></select></div>
   		</div>
   		<div class="clear"></div>
    </div>
				<div class="check-list" id="input">
				
				</div>
 		
						<div class="form-actions tan_bottom">
								<button class="btn btn-warning" type="button" onclick="saveOrUpdate()">确定</button>
						</div>
					</form>
				</div>
			</div>
	</div>
</body>

<script type="text/javascript">

// function hidediv() {
// // 	 document.getElementById("bg").style.display ='none';
// 	 document.getElementById("show").style.display ='none';
// }


	

function search(){
	var termCode = $("#xq").val();
	if(termCode ==""){
		$("#input").html("");
		$.error("请选择学期");
		return false;
	}
	var url="${pageContext.request.contextPath}/teach/teamEvaluation/inputList";
	var $requestDate = {
			"termCode":termCode
	};
	$.post(url,$requestDate,function(data,status){
		if(status=="success"){
			$("#input").html(data);
		}
	})
}


function del(id){
// 	alert(id);
	var $requestDate = {
			"id":id
	};
	var url="${pageContext.request.contextPath}/teach/teamEvaluation/delete";
	$.post(url,$requestDate,function(data,status){
		if(status=="success"){
			data = eval("("+data+")");
			if(data.info === "success"){
				setTimeout(function () {
					$.success("删除成功");
				},500);
				var $parentSelectedTerm = $(window.parent.core_iframe.document).find("#xq").val();
				var $currentTerm = $("#xq").val();
				if ($parentSelectedTerm === $currentTerm){
					parent.core_iframe.search();
				} 
			}else{
				$.error("删除失败");
			}
		}
	})
}

function saveOrUpdate(){
	getData();
}

function getData(){
	var addData = "";
	var jianData = "";
	var taskId = $("#taskId").val();
	var termCode = $("#xq").val();
	if ("" === termCode || "undefind" === termCode) {
		$.error("请选择学期");
		return false;
	}
	var url = "${pageContext.request.contextPath}/teach/teamEvaluation/addCheck"
	$("#add").each(function(){
	    $(this).find(".add-name").each(function(){
	    	if(this.value != ""){
	    		if(addData != ""){
	    			addData += "," + this.value;
	    		}else{
	    			addData += this.value ;
	    		}
	    	}
	    });
	});

	$("#jian").each(function(){
	    $(this).find(".add-name").each(function(){
	    	if(this.value != ""){
	    		if(jianData != ""){
	    			jianData += "," + this.value;
	    		}else{
	    			jianData += this.value;
	    		}
	    	}
	    });
	});
	
	var $requestDate = {};
	$requestDate.taskId = taskId;
	$requestDate.addData = addData;
	$requestDate.jianData = jianData;
	$requestDate.termCode = termCode;
	$.post(url,$requestDate,function(data,status){
		if(status=="success"){
			data = eval("("+data+")");
			if(data.info === "success"){
				$.success("保存成功");
				setTimeout(function () {
					var $parentSelectedTerm = $(window.parent.core_iframe.document).find("#xq").val();
					var $currentTerm = $("#xq").val();
					if ($parentSelectedTerm === $currentTerm){
						parent.core_iframe.search();
					} 
					$.closeWindow();
				},1000);
			}else if(data.info === "fail"){
				$.error("“"+data.pk+"”项目重复");
			}else{
				$.error("操作失败");
			}
		}
	})
}

function change(item){
	var id = item.id;
	var isChange = $(item).is(':checked');
	var $requestDate = {};
	$requestDate.itemId = id;
	$requestDate.isChange = isChange;
	var url = "${pageContext.request.contextPath}/teach/teamEvaluation/change"
	$.post(url,$requestDate,function(){
		
	})
	parent.core_iframe.search();
}

</script>

</html>