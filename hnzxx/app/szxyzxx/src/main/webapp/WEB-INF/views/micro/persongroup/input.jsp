<%@page import="platform.education.resource.contants.ResourceType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <%@ include file="/views/embedded/common.jsp"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <style type="text/css">
            .form-horizontal .control-label {
                width: 30px;
            }

            .form-horizontal .controls {
                margin-left: 40px;
            }
            #group_form{
            	padding:20px;
            }
            #gradeClassDiv{
            	padding:0px;
            }
            .myerror{
            	color:red;
            }
        </style>
    </head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<div id="gradeClassDiv" class="all_student">
						<form id="group_form" action="javascript:void(0);">
						<div class="control-group">
								<label class="control-label">
									组名<span class="red">*</span>：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13" placeholder="" value="${mimicropersongroup.name}">
								</div>
							</div>
							<c:choose>
								<c:when test="${ fn:length(teamStudents)<=0}">
								<div class="tishi">该用户没有分配任何班级</div>
								</c:when>
								<c:otherwise>
								<div class="a_top">
                                <ul class="nj">
                                    <c:forEach items="${teamStudents }" var="teamStudent" varStatus="st">
                                               <c:forEach items="${teamStudent}" var="map">
                                               <li>
													<a href="javascript:void(0)"
													onclick="changeClass('${st.index}');">
													<input data-count="${st.index}" name="ids" type="checkbox" onclick="checkStudents('${st.index}');" data-teamId="${fn:split(map.key,'&&')[2]}" value="${fn:split(map.key,'&&')[1]}">${fn:split(map.key,'&&')[0]}</a>
												</li>
												</c:forEach>
                                    </c:forEach>
                                </ul>
                            </div>
								</c:otherwise>
							</c:choose>
                            <div class="a_bottom">
                                            <c:forEach items="${teamStudents}" var="teamStudent" varStatus="st">
								<div class="fenban" style="display: none" id="teamId">
									<ul>
										<c:forEach items="${teamStudent}" var="map">
											<c:forEach items="${map.value }" var="sts">
												<li><img
													src="<avatar:avatar userId='${sts.id}'></avatar:avatar>">
													<input data-count="${st.index }" value="${sts.userId}" type="checkbox" name="students"
													onclick="checkStudent();" date-group="${fn:split(map.key,'&&')[1]}" date-teamId="${sts.teamId}">
													<p class="name">${sts.name}</p></li>
											</c:forEach>
										</c:forEach>
									</ul>
								</div>
							</c:forEach>
                                </div>
                                <div class="control-group">
								<label class="control-label">
									备注：
								</label>
								<div class="controls">
								<textarea id="description" name="description" style="width:500px;height:80px;"></textarea>
								</div>
							</div>
                                <div class="clear"></div>
                            </form>
						<div class="form-actions tan_bottom">
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
var checker;
var gradeId;
	$(function() {
		changeClass(0);
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#group_form").validate({
			errorClass : "myerror",
			rules : {
				"name":{
					required:true,
					maxlength:50
				}
			},
			messages : {
				"name":{
					maxlength:"超过指定字符"
				}
			}
		});
	}
	
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var url = "${ctp}/micropersongroup/creator/";
			var ids = new Array();
			var name = $("#name").val();
			var description = $("#description").val();
			var schecked = $("#gradeClassDiv .a_bottom input:checkbox[name='students']:checked");
					$.each(schecked, function(index, value){
					ids.push($(value).val());
					});
			loader.show();
			if(ids.length>0){
			$.post(url,{"ids":ids,"name":name,"description":description}, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						if(parent.core_iframe != null) {
 							parent.core_iframe.window.location.reload();
 						} else {
 							parent.window.location.reload();
 						}
						$.closeWindow();
					} else {
						$.error("操作失败");
					}
				}else{
					$.error("操作失败");
				}
				loader.close();
			});
			}else{
				$.error("请选择分组学生");
				loader.close();
			}
		}
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
	
	
	function checkStudents(index){
		 $("#gradeClassDiv .nj li  input[name='ids']").each(function(x) {
	            if (index == x) {
	            	if(checkGrade($(this).val())){
	            	if($(this).is(':checked')){
	            	$("#gradeClassDiv .fenban").each(function(i) {
	    	            if (index == i) {
	    	            	 $(this).find("input[type='checkbox']").each(function(){
	    	            		 //not ie
	    	                     $(this).prop('checked', true);
	    	                     //ie
	    	                     $(this).attr('checked',"checked");
	    	                 });
	    	            }
	    	        });
	            	}else{
	            		$("#gradeClassDiv .fenban").each(function(i) {
		    	            if (index == i) {
		    	            	 $(this).find("input[type='checkbox']").each(function(){
		    	            		 //not ie
		    	                     $(this).prop('checked', false);
		    	                     //ie
		    	                     $(this).removeAttr("checked");
		    	                 });
		    	            }
		    	        });
	            	}
	            }else{
	            	$.error("请选择相同年级");
	            	$(this).prop('checked', false);
	            }
	            }
	        });
	}

	function checkGrade(id){
		var flag = true;
		$("#gradeClassDiv .nj li  input[name='ids']").each(function() {
			if($(this).is(':checked')){
				if($(this).val() != id){
					flag = false;
				}
			}
		});
		return flag;
}
	
	function checkStudent(){
		$("#gradeClassDiv .fenban li input[name='students']:checked").each(function(x) {
				var teamId = $(this).attr("date-teamId");
			if(checkGradeStudent($(this).attr("date-group"))){
			 /* $("#gradeClassDiv .nj li input[name='ids']").each(function(x) {
				 if($(this).attr("data-teamId") == teamId){
					 $(this).prop('checked',true);
				 }
			 }); */
			}
			else{
				$.error("请选择相同年级");
				$(this).prop('checked', false);
			}
        });
	}
	
	
	function checkGradeStudent(id){
		var flag = true;
		$("#gradeClassDiv .fenban li input[name='students']").each(function() {
			if($(this).is(':checked')){
				if($(this).attr("date-group") != id){
					flag = false;
				}
			}
		});
		return flag;
}
</script>
</html>