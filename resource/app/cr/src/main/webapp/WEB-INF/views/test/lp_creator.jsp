<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/all.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<title>A3-2-3创建导学案</title>
<style>
html{background-color:#fff;}
</style>
</head>
<body>
<div class="tsk_dxa">
    <div class="selected_radio pdb27">
        <label style="    float: left;">创建到</label>
        <ul>
            <li class="choose"><input type="radio" checked="checked">校本库</li>
            <li><input type="radio" >个人库</li>
        </ul>
    </div>
   <div class="ku_main">
		<div class="ku_d">
			<jsp:include page="./textbook.jsp">
				<jsp:param value="lp_create" name="type"/>
			</jsp:include>
		    <div class="jjmu">
		        <p>教材目录：</p>
		        <ul>
		            <li>
		                <i>*</i><span>目录</span>
		                <div id="dvTextBookCatalog" style="display: inherit">
			                <select id="catalog1" name="catalog1">
			                    <option value="">请选择</option>
			                </select>
		                </div>
		            </li>
		        </ul>
		    </div>
		    <div class="dxamu">
		        <p>导学案目录：</p>
		        <ul>
		            <li>
		                <i>*</i> <span>标题</span>
		                <input type="text" value="罗定邦中学" id="res_title" maxlength="50"/>
		                <b><b id="num">5</b>/50</b>
		            </li>
		            <li>
		                <span class="fl mgl9">提要</span>
		                <textarea id="res_description"></textarea>
		            </li>
		        </ul>
		    </div>
		    <div class="last_ope">
		        <a href="javascript:void(0)" class="btn-blue" onclick="createLearningPlan('res');">确定</a>
		        <a href="javascript:void(0)" class="btn-lightGray" onclick="cancel()">取消</a>
		    </div>
		</div>
		<div class="ku_d" style="display: none">
			<div class="jjmu">
				<p>教材目录：</p>
				<ul>
					<li>
						<i>*</i> <span>科目</span>
						<select id="per_subjectCode">
							<c:forEach items="${subjectList }" var="subject">
								<option value="${subject.code }">${subject.name }</option>
							</c:forEach>
						</select>
					</li>
				</ul>
			</div>
			<div class="dxamu">
				<p>导学案目录：</p>
				<ul>
					<li><i>*</i> <span>标题</span> 
					<input type="text" id="title" maxlength="50" /><b><b id="num">5</b>/50</b></li>
					<li><span class="fl mgl9">提要</span>
					<textarea id="description"></textarea></li>
				</ul>
			</div>
			<div class="last_ope">
				<a href="javascript:void(0)" class="btn-blue" onclick="createLearningPlan('person');">确定</a>
				<a href="javascript:void(0)" class="btn-lightGray" onclick="cancel()">取消</a>
			</div>
		</div>
	</div>
<script>
var catalogEnd = 0
$(function(){
	$('.selected_radio ul li').click(function() {
		$(this).siblings("li").removeClass("choose");
		$(this).addClass("choose");
		var i=$(this).index();
		$(".ku_d").hide();
		$(".ku_d").eq(i).show();
	});

	$('#title').on('input propertychange keydown change', function() {
		setTimeout(function() {
			var len = $('#title').val().length;
			$('#num').text(len);
		})
	})
    
    $("#stageCode option:nth-child(2)").prop("selected", 'selected');
	findTextBook('subjectCode', 'other');
	//选中第一个科目
	$("#subjectCode option:nth-child(2)").prop("selected", 'selected');	
	findTextBook('publisherId', 'other');
	//选中第一个版本
	$("#publisherId option:nth-child(2)").prop("selected", 'selected');
	findTextBook('gradeCodeVolumn', 'other');
	//选中第一个年级册次
	$("#gradeCodeVolumn option:nth-child(2)").prop("selected", 'selected');
	//查找一级目录
	$("#gradeCodeVolumn").trigger("change");
	//遍历子目录
	selectCatalog(1);
	
})
// 取消
function cancel(){
	$.closeWindow();
}

function selectCatalog(id) {
	$("#catalog"+id+" option:nth-child(2)").prop("selected", 'selected');
	$("#catalog"+id).trigger("change");
	id = id+1;
	var info = $("#catalog"+id).val();
	if(""==info) {
		selectCatalog(id);
	} else {
		$("#res_title").val("");
	}
}

function createLearningPlan(type) {
	window.location.href="${pageContext.request.contextPath}/learningPlan/index";
	return;
	
	var data = {};
	var title = "";
	data["type"] = type;
       
	if("person"==type) {
		title = $("#title").val();
		data["subjectCode"] = $("#per_subjectCode").val();
		data["description"] = $("#description").val();
	}else {
		title = $("#res_title").val();
		data["description"] = $("#res_description").val(); 
		data["version"] = $("#publisherId").val();
		data["stageCode"] = $("#stageCode").val();
        data["stageName"] = $("select[id=stageCode] option:selected").text();
		data["subjectCode"] = $("#subjectCode").val();
		data["subjectName"] = $("select[id=subjectCode] option:selected").text();
        data["versionName"] = $("select[id=publisherId] option:selected").text();
        
        var gradeCodeVolumnValue = $("#gradeCodeVolumn").val();
        var array = gradeCodeVolumnValue.split("-");
        if (array.length == 2) {
        	data["gradeCode"] = array[0];
        	data["volumn"] = array[1];
        }
        
        var aa = $("#dvTextBookCatalog").children("select").length;
        var catalogTemp;
        for (var num = aa; num >= 1; num--) {
            var catalogname = "catalog" + num;
            catalogTemp = $("#" + catalogname).val();
            if (Number(catalogTemp) > 0) {
                catalogEnd = catalogTemp;
                catalogEnd = $("#" + catalogname).find("option:selected").attr("data-code");
                break;
            } else {
                catalogEnd = 0;
            }
        }
        
        data["catalogCode"] = catalogEnd;
        
        if (data["volumn"] == null || data["volumn"] == "") {
            $.alert("请选择完整学段信息");
            return;
        }
        if (data["catalogCode"] == null || data["catalogCode"] == "") {
            $.alert("选择教材目录");
            return;
        }
	}

    title = title.replace(/(^\s*)|(\s*$)/g, "");
    
    if (title == null || title == "") {
        $.alert("请输入标题");
        return;
    }
    
    if (data["subjectCode"] == null || data["subjectCode"] == "") {
        $.alert("选择科目");
        return;
    }
    
    var loader = new loadDialog();
    loader.show();
    $.ajax({
        url: "${pageContext.request.contextPath}/learningPlan/creator",
        type: "POST",
        data: {
            "catalogResource": JSON.stringify(data),
            "title":title
        },
        async: true,
        success: function(data) {
        	var info = JSON.parse(data);
        	loader.close();
        	$.success("创建成功");
        	var url = "${pageContext.request.contextPath}/learningPlan/edit?id="+info.lpId;
        	window.open(url, "编辑-"+title);
        	parent.$(".content_main  .ku_div li a.a3",parent.doucment).click();
        	//$(".content_main  .ku_div li a.a3",parent.document).click();
        	alert(parent.$("body",parent.doucment).html())
        	cancel();
        }
   });
}
</script>
</div>
</body>
</html>