<%@page import="platform.education.resource.contants.ResourceType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加试卷</title>
<style type="text/css">
.form-horizontal .control-label {
	width: 60px;
	text-align: left;
	padding: 0;
}

.form-horizontal .controls {
	margin-left: 40px;
}

.res_a {

	cursor: pointer;
}

.fenban {
	margin-left: 20px;
}

.radio-left {
	float: left;
	height: 35px;
	line-height: 35px;
	margin-right: 20px;
}

.radio-left input {
	margin-right: 6px;
}

.radio-bj {
	border: 1px solid #45A9E7;
	height: 35px;
	margin-left: 59px;
	padding-left: 15px;
	margin-top: 10px;
}
</style>
</head>
<body>
	<input type="hidden" id="sign" value="school">
	<input type="hidden" id="personSign" value="myresource">
	<input type="hidden" id="stageSign" value="">
	<input type="hidden" id="subjectSign" value="">
	<input type="hidden" id="versionSign" value="">
	<input type="hidden" id="volumnSign" value="">
	<div class="row-fluid ">
		<div class="span12">
			<div class="content-widgets white" style="margin-bottom: 0;">
				<div class="widget-container" style="height:500px;overflow:auto;">
					<form id="dept_form" class="form-horizontal"
						novalidate="novalidate" style="margin:0;padding:0;">
						<div id="microMainDiv" class="control-group">
							<div class="controls" style="margin:0">
								<div class="banji">
									<ul class="nj">
										<li><a onclick="changeMicroTab($(this).attr('id'))" id="schoolMicro" href="javascript:void(0)">校本资源库</a></li>
										<li><a onclick="changeMicroTab($(this).attr('id'))" id="myExam" href="javascript:void(0)">个人资源库</a></li>
									</ul>
									<div class="back_tj">
										<div class="kemu_nav" style="display: none; float: left;">
											<ul>
												<li><a id="myresource" class="active res_a" onclick="chose($(this).attr('id'))">我上传的资源</a></li>
												<li><a id="favresource" class="res_a" onclick="chose($(this).attr('id'))">我收藏的资源</a></li>
											</ul>
										</div>

										<div class="ts">
											<div class="yx_weike" style="right: 0;top: 1px;">
												<div class="yx_num">
													<a id="clearButton" onclick="clearMicro();" href="javascript:void(0)" class="close_all" style="display: none"> <i class="fa fa-trash-o"></i>清空
													</a>
													<p class="yx">
														<a onclick="showChosenMicroDiv()" href="javascript:void(0)">已选试卷<span id="chosenMicroSize">0</span></a>
													</p>
												</div>
												<div id="chosenMicroDiv" class="yx_wk"
													style="display: none">
													<ul>
													</ul>
												</div>
											</div>
										</div>
									</div>
									<div class="clear"></div>
									<div class="weike" style=""></div>
									<div class="tishi"></div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
				<div style="text-align:center;margin-top:20px;">
					<button onclick="createUnitFile()" class="btn btn-primary">确认</button>
					<button onclick="cacel()" class="btn">取消</button>
				</div>
		</div>
	</div>
<script type="text/javascript">
   var isFirst = 0;
   function subjectOn(){
	   $('#gradeClassDiv').css("display","block");
	   $('#allclass').css("display","none");
   }
   function gradeOn(){
	   $('#gradeClassDiv').css("display","none");
	   $('#allclass').css("display","block");
   }
  
	function chose(dataType){
	    $(".kemu_nav ul li a").each(function(){
	    	if($(this).attr("id") == dataType){
	    		$(this).addClass("active");
	    	}else{
	    		$(this).removeClass("active");
	    	}
	    });
	    var url = "${pageContext.request.contextPath}";
	    if(dataType == "myresource"){//我上传的资源
	 		url = url + "/learningPlan/exam/myList?index=index&personType=res_person";
	    }else if(dataType == "favresource"){//我收藏的资源
	    	url = url + "/learningPlan/exam/myList?index=index&personType=fav_resource";
	    }
		$(".weike").load(url,function(){});
	}

    var chosenMicroJson = {"microList": []};
    //作业的来源
    var source;
    
    function changeMicroTab(type) {
        var loader = new loadDialog();
        loader.show();
        $("#microMainDiv .controls .banji .nj li a").each(function() {
            if ($(this).attr("id") == type) {
                $(this).parent().addClass("active")
            } else {
                $(this).parent().removeClass("active");
            }
        });
        var url = "${pageContext.request.contextPath}";
        
        if (type == "myExam") {
        	$("#sign").val("person");
            //我的作业
            url = url + "/learningPlan/exam/myList?index=index&personType=res_person";
            source = "来自我的资源库";
            $(".kemu_nav").css("display","block");
            $(".back_tj").css({"float":"left","width":"100%"})
        }else if(type == "schoolMicro") {
        	$("#sign").val("school");
       		var subjectCode = $(".fenban input[type='checkbox']").first().next().val();
       		var gradeCode = $(".nj .active input[type='hidden']").val();
       		url = url + "/learningPlan/resource/list?personType=res_school&unitId=${unitId}&resourceType=4";
			source = "来自校本资源库";
			$(".kemu_nav").css("display", "none");
			$(".back_tj").css({"float" : "right","width" : "auto"})
		} 
		$(".weike").load(url, function() {
			loader.close();
		});
	}

	function chooseMicro(mid) {
		var flag = null;
        if (chosenMicroJson.microList.length > 0) {
            for (var i = 0; i < chosenMicroJson.microList.length; i++) {
            	var micro = chosenMicroJson.microList[i];
                if(micro.mid == mid && !$("#id_"+mid).is(':checked')) {
                	flag = i;
                }
                if (micro.mid != mid) {
                    chosenMicroJson.microList.splice(i, 1);
                    $("#chosenMicroLi_" + micro.mid).remove();
                    $("#id_"+micro.mid).attr("checked",false);
                }
            }
        }
        
        if(flag!=null) {
        	chosenMicroJson.microList.splice(flag, 1);
            $("#chosenMicroLi_" + mid).remove();
            setSize();
        } else {
        	var title = $("#title_" + mid).attr("title");
            var resourceId=$("#resourceId_"+mid).val();
            addMicro(mid, title,resourceId);
            setSize();
        }
	}

	function addMicro(mid, title, resourceId) {
		var micro = {
			"title" : title,
			"id" : mid,
			"mid" : mid
		};
		chosenMicroJson.microList.push(micro);
		var html = "<li id=\"chosenMicroLi_" + mid + "\"><a onclick=\"previewMicro('"
				+ mid
				+ "')\" href=\"javascript:void(0)\" class=\"name\">"
				+ title
				+ "</a><p>"
				+ source
				+ "</p><a onclick=\"clearMicro('"
				+ mid
				+ "')\" href=\"javascript:void(0)\" class=\"close_1\"></a></li>";
		$("#chosenMicroDiv ul").prepend(html);
	}

	function removeUploadMicro() {
		changeMicroTab("upload");
	}

	function clearMicro(mid) {
		if (mid != null && mid != "") {
			for (var x = 0; x < chosenMicroJson.microList.length; x++) {
				var id = chosenMicroJson.microList[x].mid;
				if (id == mid) {
					chosenMicroJson.microList.splice(x, 1);
					$("#chosenMicroLi_" + mid).remove();
					//去除勾选
					$("#id_" + mid).attr('checked', false);
					break;
				}
			}
			setSize();
		} else {
			$.confirm("确定清空已选试卷?", function() {
				chosenMicroJson.microList = [];
				$("#chosenMicroDiv ul").html("");
				//去除所有勾选
				$("input[type='checkbox']").each(
						function() {
							$(this).attr('checked', false);
						});
				setSize();
			});
		}
	}

	function showChosenMicroDiv(always) {
		if (always != null) {
			//持久显示,不toggle
			$("#chosenMicroDiv").show();
			$(".yx_weike").css("width", "300px");
			$("#clearButton").show();
		} else {
			if ($("#chosenMicroDiv").is(":hidden")) {
				$("#chosenMicroDiv").show();
				$(".yx_weike").css("width", "300px");
			} else {
				$("#chosenMicroDiv").hide();
				$(".yx_weike").css("width", "117px");
			}
			$("#clearButton").toggle();
		}
	}

	function setSize() {
		$("#chosenMicroSize").text(chosenMicroJson.microList.length);
	}

	function checkSelected() {
		$("input[type='checkbox']").each(function() {
			for (var i = 0; i < chosenMicroJson.microList.length; i++) {
				var micro = chosenMicroJson.microList[i];
				if (micro.id == $(this).attr("resourceId")) {
					$(this).attr('checked', true);
					break;
				}
			}
		});
	}

	function createUnitFile() {
		var name = $('input[name="type"]:checked').attr("id");
		var data = {};
		data["learningPlanList"] = chosenMicroJson.microList;
		var unitId = '${unitId}';
		
		if(data["learningPlanList"]==null || ""==data["learningPlanList"]) {
			 $.alert("请选择试卷");
			 return false;
		}
		
		var loader = new loadDialog();
		loader.show();
		$.ajax({
			url : "${pageContext.request.contextPath}/learningPlan/unit/file/add",
			type : "POST",
			data : {
				"resourceIds" : JSON.stringify(data),
				"lpUnitId":unitId
			},
			async : false,
			success : function(info) {
				var data = JSON.parse(info);
				var warn = $(window.parent.document).find("#add_"+unitId).text();
				if(data.personal) {
					var html = '<ul><li>试卷名称：<span>'+data.title+'</span></li>'+
					'<li>适用科目：<span>'+data.subject+'</span></li>'+
					'<li>上传时间：<span>'+data.createTime+'</span></li>'+
					'<li>试卷总分：<span>'+data.score+'</span></li>'+
					'</ul>';
					$(window.parent.document).find("#preview_"+unitId).remove();
					$(window.parent.document).find("#add_"+unitId).text("更改");
					$(window.parent.document).find("#add_"+unitId).parent().append("<button id=\"preview_"+unitId+"\" class=\"btn btn-primary\" onclick=\"previewExam("+data.paperId+")\">预览</button>");
					$(window.parent.document).find("#yxzc_bottom_"+unitId).html(html);
				} else {
					var html = '<ul><li>试卷名称：<span>'+data.title+'</span></li>'+
					'<li>适用科目：<span>'+data.subject+'</span></li>'+
					'<li>上传时间：<span>'+data.createTime+'</span></li>'+
					'<li>试卷总分：<span>'+data.score+'</span></li>'+
					'</ul>';
					$(window.parent.document).find("#preview_"+unitId).remove();
					$(window.parent.document).find("#add_"+unitId).text("更改");
					$(window.parent.document).find("#add_"+unitId).parent().append("<button id=\"preview_"+unitId+"\" class=\"btn btn-primary\" onclick=\"previewExam("+data.paperId+")\">预览</button>");
					$(window.parent.document).find("#yxzc_bottom_"+unitId).html(html);
				}
				loader.close();
				if("添加"==warn){
					$.success("添加成功");
				} else {
					$.success("更改成功");
				}
				$(window.parent.document).find("#"+unitId).parent().parent().show();
				$(window.parent.document).find("#uuid"+unitId).show();
				$(window.parent.document).find("input").focus();
				$(window.parent.document).find(".three_creat .c_top .folder_div ul li a").removeClass("light_blue on");
				$(window.parent.document).find("#"+unitId).parent().addClass("light_blue on");
				setTimeout("$.closeWindow()",1000)
			}
		});
	}

	function getSubjectName(text) {
		var subjectName = text.substring(text.indexOf("[") + 1);
		subjectName = subjectName.substring(0, subjectName.length - 1);
		return subjectName;
	}

	function checkClassSubject(obj) {
		var text = $(obj).parent().text();
		var subjectName = getSubjectName(text);
		$("#gradeClassDiv .fenban li").each(function() {
			var esn = getSubjectName($(this).text());
			if (subjectName == esn) {
				$(this).find("input[type='checkbox']").removeAttr(
						"disabled");
			} else {
				$(this).find("input[type='checkbox']").attr(
						"disabled", true);
			}
		});
		if ($("#gradeClassDiv .fenban li input[type='checkbox']:checked").size() <= 0) {
			$("#gradeClassDiv .fenban li input[type='checkbox']").each(function() {
				$(this).removeAttr("disabled");
			});
		}
	}

	function getSelectedClasses() {
		var classes = new Array();
		$("#gradeClassDiv .fenban input[type='checkbox']:checked").each(
				function() {
					var cj = {
						"relateId" : $(this).val(),
						"relateName" : $.trim($(this).parent().text()),
						"relateType" : $(this).attr("data-type")
					};
					classes.push(cj);
				});
		return classes;
	}
		
	function getGradeClasses() {
		var classes = new Array();
		var name = $("#allclass input[type='radio']:checked").attr(
				"data-key");
		$("#" + name + " input").each(function() {
			var cj = {
				"relateId" : $(this).attr("data-id"),
				"relateName" : $(this).attr("data-name"),
				"relateType" : $(this).attr("data-type")
			};
			classes.push(cj);

		});
		return classes;
	}

	function changeClass(index) {
		$("#gradeClassDiv .nj li").each(function(x) {
			if (index == x) {
				$(this).addClass("active");
			} else {
				$(this).removeClass("active");
			}
		});
		$("#gradeClassDiv .fenban").each(function(i) {
			if (index == i) {
				$(this).show();
			} else {
				$(this).hide();
			}
		});
	}
	
	function cacel() {
		var unitid = '${unitId}';
		var isNew = '${isNew}';
		if(isNew=="true") {
			//删除导学案单元
			$.ajax({
		        url: "${pageContext.request.contextPath}/learningPlan/unit/delete?id="+unitid,
		        type: "DELETE",
		        data: {},
		        async: true,
		        success: function(data) {
		        	$.closeWindow()
		        }
		    });
		} else {
			$.closeWindow()
		}
	}

	$(function() {
		changeClass(0);
		changeMicroTab("schoolMicro");
	});
	
</script>
</body>
</html>