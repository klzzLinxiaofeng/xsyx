<%@page import="platform.education.resource.contants.ResourceType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加微课</title>
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
						novalidate="novalidate" style="margin:0;padding:0">
						<div id="microMainDiv" class="control-group">
							<div class="controls" style="margin:0">
								<div class="banji">
									<ul class="nj">
										<li><a onclick="changeMicroTab($(this).attr('id'))" id="schoolMicro" href="javascript:void(0)">校本资源库</a></li>
										<li><a onclick="changeMicroTab($(this).attr('id'))" id="myMicro" href="javascript:void(0)">个人资源库</a></li>
									</ul>
									<div class="back_tj">
										<div class="kemu_nav" style="display: none; float: left;">
											<ul>
												<li><a id="myresource" class="active res_a" onclick="chose($(this).attr('id'))">我上传的资源</a></li>
												<li><a id="favresource" class="res_a" onclick="chose($(this).attr('id'))">我收藏的资源</a></li>
												<li><a id="myshare" class="res_a" onclick="chose($(this).attr('id'))">我的共享</a></li>
											</ul>
										</div>

										<div class="ts">
											<div class="yx_weike" style="right: 0;top: 1px;">
												<div class="yx_num">
													<a id="clearButton" onclick="clearMicro();" href="javascript:void(0)" class="close_all" style="display: none"> <i class="fa fa-trash-o"></i>清空
													</a>
													<p class="yx">
														<a onclick="showChosenMicroDiv()" href="javascript:void(0)">已选微课<span id="chosenMicroSize">0</span></a>
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
					<button onclick="cacel();" class="btn">取消</button>
				</div>
		</div>
	</div>
<script type="text/javascript">
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
	    	$("#personSign").val(dataType);
	 		url = url + "/learningPlan/micro/myList?index=index&resType=1&personType=res_person";
	    }else if(dataType == "favresource"){//我收藏的资源
	    	$("#personSign").val(dataType);
	    	url = url + "/learningPlan/micro/myList?index=index&resType=1&personType=fav_resource";
	    }else if(dataType == "myshare"){//我的共享
	    	$("#personSign").val(dataType);
	    	url = url + "/learningPlan/micro/myList?index=index&resType=1&personType=res_share";
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
        
        if (type == "myMicro") {
        	$("#sign").val("person");
            //我的作业
            url = url + "/learningPlan/micro/myList?index=index&resType=1&personType=res_person&unitId=${unitId}";
            source = "来自我的资源库";
            $(".kemu_nav").css("display","block");
            $(".back_tj").css({"float":"left","width":"100%"})
        }else if(type == "schoolMicro") {
        	$("#sign").val("school");
       		var subjectCode = $(".fenban input[type='checkbox']").first().next().val();
       		var gradeCode = $(".nj .active input[type='hidden']").val();
       		url = url + "/learningPlan/resource/list?personType=res_school&unitId=${unitId}&resourceType=1";
			source = "来自校本资源库";
			$(".kemu_nav").css("display", "none");
			$(".back_tj").css({"float" : "right","width" : "auto"})
		} 
		$(".weike").load(url, function() {
			loader.close();
		});
	}

	function chooseMicro(mid) {
		var removeFlag = null;
		if (chosenMicroJson.microList.length > 0) {
			for (var i = 0; i < chosenMicroJson.microList.length; i++) {
				var micro = chosenMicroJson.microList[i]
				if (micro.mid == mid) {
					removeFlag = i;
				}
			}
		}
		if (removeFlag != null) {
			chosenMicroJson.microList.splice(removeFlag, 1);
			$("#chosenMicroLi_" + mid).remove();
		} else {
			var title = $("#title_" + mid).attr("title");
			var resourceId = $("#resourceId_" + mid).val();
			addMicro(mid, title, resourceId);
		}
		setSize();
		limitNigth();
	}
	
	function limitNigth() {
		var selected = $("#chosenMicroSize").text();
		if(selected==9) {
			$("input[type=checkbox]").each(function(){
				var check = $(this).prop('checked');
				if(!check) {
					$(this).attr("disabled","disabled");
				}
			});
		} else {
			$("input[type=checkbox]").each(function(){
				$(this).removeAttr("disabled");
			});
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
			$.confirm("确定清空已选导学案吗?", function() {
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
		limitNigth();
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
		limitNigth();
	}

	function createUnitFile() {
		var name = $('input[name="type"]:checked').attr("id");
		var data = {};
		data["learningPlanList"] = chosenMicroJson.microList;
		var unitId = '${unitId}';
		
		if(data["learningPlanList"]==null || ""==data["learningPlanList"]) {
			 $.alert("请选择微课");
			 return false;
		}
		
		var loader = new loadDialog();
		var deleteMicro = window.parent.changeMicro;
		
		loader.show();
		$.ajax({
			url : "${pageContext.request.contextPath}/learningPlan/unit/file/add",
			type : "POST",
			data : {
				"resourceIds" : JSON.stringify(data),
				"lpUnitId":unitId,
				"deleteMicro": deleteMicro
			},
			async : false,
			success : function(data) {
				var html = "";
				var info = JSON.parse(data);
				var resources = info.resourceList;
				for(var i=0; i<resources.length; i++) {
					var resource = resources[i];
					
					html += '<dl id="unit_file_'+resource.lpFileId+'"><dt><a href="javascript:void(0);" onclick="previewMicro(\''+resource.id+'\')">'+
                    '<img src="/cr/res/images/video.png">'+
                    '</a></dt><dd><div class="item-msg"><div class="item-title"><span class="res-mp4 icon-file res-iconb"></span>'+
                    '<span style="overflow:hidden;width:338px;float:left;display:block; white-space:nowrap; text-overflow:ellipsis; word-wrap: normal;">'+
                    '<a href="javascript:void(0)" onclick="previewMicro(\''+resource.id+'\')" title="'+resource.title+'">'+resource.title+'</a></span></div><span class="i1">教材目录：'+resource.catalogName+'</span>'+ 
                    '<div class="i1">上传时间：'+resource.createDate+'</div><div class="cz_btn"><button class="btn btn-primary" onclick="previewMicro(\''+resource.id+'\')">预览</button>';
					if(deleteMicro==1 && resources.length==1) {
						html += '<button class="btn btn-danger" data-file_id='+resource.lpFileId+' data-type="3" onclick="addMicro('+unitId+', 1)">更改</button></div></div></dd></dl>';
					} else {
						html += '<button class="btn btn-danger" data-file_id='+resource.lpFileId+' data-type="3" onclick="lpUnitFileDelete('+resource.lpFileId+',this,'+unitId+',\'确定删除该微课吗？\')">删除</button></div></div></dd></dl>';
					}
                    
				}
				
				var num = 0;
				if(deleteMicro==1) {
					$(window.parent.document).find("#xkzy_list_"+unitId).html("");
				} else {
					var file_id = $(window.parent.document).find("#xkzy_list_"+unitId).find("button").eq(1).data("file_id");
					var button = '<button class="btn btn-danger" data-file_id='+file_id+' data-type="3" onclick="lpUnitFileDelete('+file_id+',this,'+unitId+',\'确定删除该微课吗？\')">删除</button>';
					$(window.parent.document).find("#xkzy_list_"+unitId).find("button").eq(1).remove();
					$(window.parent.document).find("#xkzy_list_"+unitId).find(".cz_btn").eq(0).append(button);
					num = $(window.parent.document).find(".micronum_"+unitId).text();
				}
				$(window.parent.document).find(".micronum_"+unitId).text(parseInt(num)+resources.length);
				$(window.parent.document).find("#xkzy_list_"+unitId).append(html);
				$(window.parent.document).find("#"+unitId).parent().parent().show();
				$(window.parent.document).find("#uuid"+unitId).show();
				$(window.parent.document).find("input").focus();
				$(window.parent.document).find(".three_creat .c_top .folder_div ul li a").removeClass("light_blue on");
				$(window.parent.document).find("#"+unitId).parent().addClass("light_blue on");
				deleteMicro = 0;
				loader.close();
				$.success("添加成功");
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
		        	window.parent.changeMicro = 0;
		        	$.closeWindow()
		        }
		    });
		} else {
			$.closeWindow()
		}
	}
	
	
	function previewMicro(mid) {
		var mes = "预览";
		$.initWinOnTopFromLeft(mes,'${pageContext.request.contextPath}/learningplan/preview?objId='+ mid, '700', '500');
	}						

	$(function() {
		changeClass(0);
		changeMicroTab("schoolMicro");
	});
</script>
</body>
</html>