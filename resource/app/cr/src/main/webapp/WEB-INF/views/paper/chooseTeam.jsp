<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
    <head>
        <%@ include file="/views/embedded/common.jsp"%>
        <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>试卷</title>
        <style type="text/css">
        	.container-fluid{
        		padding: 20px 30px;
        	}
        	.row-fluid{
        		border-bottom: 1px solid #e3e8ec;
			    font-size: 14px;
			    height: 48px;
			    line-height: 48px;
			    font-weight: bold;
			    background-color: #f8f8f9;
        	}
        	.breadcrumb{
        		background: none;
        		padding:0;
        		margin:0;
        	}
        	.breadcrumb li a{
        		padding-left: 20px;
    			color: #2f89fe;
    			line-height: 48px;
        	}
        	.white {
			    background-color: #f8f8f9;
			    border: none;
			}
            .form-horizontal .control-label{
            	width: 80px;text-align: right;margin-top: 8px;
			    font-size: 12px;
			    font-weight: normal;
            }
            .form-horizontal .controls {
                margin-left: 100px;
                font-size: 12px;
    			font-weight: normal;
            }
            .form-horizontal .controls span{
            	font-size: 12px;
    			font-weight: normal;
            }
            .form-horizontal .controls input[type='radio']{
            	width:15px;
            	height:15px;
            }
            .res_a{
            	cursor: pointer;
            }
          .form-horizontal .controls .banji .fenban {
			height:auto;
            }
            .radio-left{
            	float:left;
            	height: 35px;
			    line-height: 35px;
			    margin-right: 20px;
            }
            .radio-left input{
            	margin-right:6px;
            }
            .radio-bj{
            	border: 1px solid #45A9E7;
			    height: 35px;
			    margin-left: 59px;
			    padding-left: 15px;
			    margin-top: 10px;
            }
            .controls b{
            	width: 200px;
			    display: inline-block;
			    font-weight: normal;
            }
        </style>
    </head>
<body style="background-color: #e3e3e3;">
        <div class="container-fluid">
            <jsp:include page="/views/embedded/navigation.jsp">
                <jsp:param value="fa-calculator" name="icon"/>
                <jsp:param value="布置试卷" name="title" />
                <jsp:param value="${param.dm}" name="menuId" />
            </jsp:include>
            <div class="row-fluid ">
                <div class="span12">
                    <div class="content-widgets white" style="margin-bottom: 0;">
                        <div class="widget-container" style="margin: 48px auto;
    width: 75%;">
                            <form id="dept_form" class="form-horizontal" novalidate="novalidate">
                                <div class="control-group">
                                    <label class="control-label">
                                        <font style="color:red">*</font>时间
                                    </label>
                                    <div class="controls">
                                        <input id="startDate" type="text"  onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '%y-%M-%d %H:%m:%s', maxDate: '#F{$dp.$D(\'finishedDate\')}'})" placeholder="开始日期" class="span2">
                                        <span style="margin:0 10px 0 5px;">点</span><span style="margin:0 5px 0 10px;">至</span>
                                        <input id="finishedDate" type="text" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '#F{$dp.$D(\'startDate\')}', maxDate: ''})" placeholder="结束日期" class="span2">
                                    </div>
                                </div>
                                <div class="control-group">
                                 
                                    <label class="control-label"><font style="color:red">*</font>班级</label>
                                    <div class="controls"><input type="radio" name="type" id="subject" onclick="subjectOn()"checked="checked" style="margin: 0 5px 0 0;">按科目发布
                                     <if test="${!acl:hasPermission(sessionScope[sca:currentUserKey()].userId, 'KE_JIAN_ZI_YUAN_SCHOOL', 0)}">
                                    <input type="radio" name="type" id="grade"    onclick="gradeOn()" style="margin: 0 5px 0 20px;">按年级发布
                                    </if></div>
                                    <div id="gradeClassDiv" class="controls">
                                        <div class="banji">
                                            <c:choose>
                                                <c:when test="${fn:length(classGradeMap)<=0}">
                                                    <div class="tishi">该用户没有分配任何班级</div>  
                                                </c:when>
                                                <c:otherwise>
                                                    <ul class="nj" style="margin: 10px 0 10px 0;">
                                                        <c:forEach varStatus="st" items="${classGradeMap}" var="cm">
                                                            <li>
                                                                <a onclick="changeClass('${st.index}',1);" href="javascript:void(0)">${fn:split(cm.key,"&&")[0]}</a>
                                                                <input type="hidden" value="${fn:split(cm.key,'&&')[1]}" />
                                                            </li>
                                                            </c:forEach>
                                                    </ul>
                                                    
                                                    <div class="clear"></div>
                                                    <c:forEach items="${classGradeMap}" var="cm">
                                                        <div class="fenban" style="display:none" >
                                                            <ul>
                                                                <c:forEach items="${cm.value}" var="teamMap">
                                                                    <c:forEach items="${teamMap}" var="team">
                                                                        <li style="font-size: 14px;">
                                                                            <input data-type="${fn:split(team.value,'&&')[1]}"
                                                                             value="${team.key}" data-id='${fn:split(team.value,"&&")[0]} ' onclick="checkClassSubject(this)" type="checkbox" style="width:16px;height:16px;"/>
                                                                            ${fn:split(team.value,"&&")[0]}
                                                                            <input type="hidden" value="${fn:split(team.value,'&&')[1]}" />
                                                                        </li>
                                                                        </c:forEach>
                                                                    </c:forEach>
                                                            </ul>
                                                            
                                                            <div class="clear"></div>
                                                        </div>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                           
                                            <div class="tishi" ></div>
                                        </div>
                                    </div>
                                     <div id="allclass" style="display:none;" class="controls">
											<div class="banji">
												<ul class="nj" style="margin: 10px 0 10px 0;">
                                                   <c:forEach varStatus="st" items="${allClassMap}" var="cm">
                                                       <li>
                                                           <a onclick="changeClass('${st.index}',2);" href="javascript:void(0)">${fn:split(cm.key,"&&")[0]}</a>
                                                           <input type="hidden" value="${fn:split(cm.key,'&&')[1]}" />
                                                       </li>
                                                  </c:forEach>
                                                </ul>
                                     		 	
                                     		 	<div class="clear"></div>
                                                <c:forEach items="${allClassMap}" var="cm">
                                                    <div class="fenban" >
                                                    <ul style="height:42px;">
                                                    	<li style="font-size: 14px;">
                                                         <input class="all_select1" type="checkbox" style="width:16px;height:16px;"/>全选
                                                        </li>
                                                    </ul>
                                                        <ul >
                                                            <c:forEach items="${cm.value}" var="teamMap">
                                                                <c:forEach items="${teamMap}" var="team">
                                                                    <li style="font-size: 14px;">
                                                                        <input data-type="01" value="${team.key}" onclick="" type="checkbox" data-id='${fn:split(team.value,"&&")[0]}' style="width:16px;height:16px;"/>
                                                                        ${fn:split(team.value,"&&")[0]}
                                                                        <input type="hidden" value="${fn:split(team.value,'&&')[1]}" />
                                                                    </li>
                                                                    </c:forEach>
                                                            </c:forEach>
                                                            <div class="clear"></div>
                                                        </ul>
                                                        <div class="clear"></div>
                                                    </div>
                                                </c:forEach>
                                     		 
                                     		</div>
                                  </div>
                                </div>
                                <div class="control-group">
                                	<lebel class="control-label" style="padding: 0;margin: 0;width: 86px;"><font style="color:red">*</font>答案权限限制</lebel>
                                	<div class="controls"><input type="radio" name="isCheck" value="0" checked="checked"  style="margin: 0 5px 0 20px;"><b>不允许查看答案 </b>
									<input type="radio" name="isCheck" value="1"  style="margin: 0 5px 0 20px;"><b>允许随时查看答案</b><br> 
									<input type="radio" name="isCheck" value="2"  style="margin: 0 5px 0 20px;"><b>允许提交后查看答案 </b>
									<input type="radio" name="isCheck" value="3"  style="margin: 0 5px 0 20px;"><b>允许测试结束后查看答案</b></div>
                                </div>
							<div style="text-align:center">
								<button type="button" onclick="pub()" class="btn btn-blue" style="width: 85px;height: 35px;">布置</button>
<!-- 								<button type="button" onclick="back()" class="btn btn-lightGray" style="width: 85px;height: 35px;">取消</button> -->
							</div>
							</div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</body>
<script type="text/javascript">
	function subjectOn() {
		$('#gradeClassDiv').css("display", "block");
		$('#allclass').css("display", "none");
		changeClass(0,1);
	}
	function gradeOn() {
		$('#gradeClassDiv').css("display", "none");
		$('#allclass').css("display", "block");
		changeClass(0,2);
	}
	function getSubjectName(text) {
		var subjectName = text.substring(text.indexOf("[") + 1);
		subjectName = subjectName.substring(0, subjectName.length - 1);
		return subjectName;
	}

	function checkClassSubject(obj) {
		var text = $(obj).parent().text();
		var subjectName = getSubjectName(text);
// 		$("#gradeClassDiv .fenban li").each(function() {
// 			var esn = getSubjectName($(this).text());
// 			alert(esn);
// 			if (subjectName == esn) {
// 				$(this).find("input[type='checkbox']").removeAttr("disabled");
// 			} else {
// 				$(this).find("input[type='checkbox']").attr("disabled", true);
// 			}
// 		});
		if ($("#gradeClassDiv .fenban li input[type='checkbox']:checked")
				.size() <= 0) {
			$("#gradeClassDiv .fenban li input[type='checkbox']").each(
					function() {
						$(this).removeAttr("disabled");
					});
		}
	}

	function getSelectedClasses() {
		var classes = new Array();
		$("#gradeClassDiv .fenban input[type='checkbox']:checked").each(function() {
			var t=new Object();
			t.teamId=$(this).val();
			t.name=$(this).attr('data-id');
			classes.push(t);
	});
		return classes;
	}
	function getGradeClasses() {
		var classes = new Array();
		$("#allclass .fenban input[type='checkbox']:checked").each(function() {
			if (!$(this).hasClass("all_select1")) {
				var t=new Object();
				t.teamId=$(this).val();
				t.name=$(this).attr('data-id');
				classes.push(t);
			}
		});
		return classes;
	}
	function changeClass(index,type) {
		if(type == 1){
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
				$(this).find("input").removeAttr("checked");
				$(this).hide();
			}
		});
		}else{
		$("#allclass .nj li").each(function(x) {
			if (index == x) {
				$(this).addClass("active");
			} else {
				$(this).removeClass("active");
			}
		});
		$("#allclass .fenban").each(function(i) {
			if (index == i) {
				$(this).show();
			} else {
				$(this).find("input").removeAttr("checked");
				$(this).hide();
			}
		});
		}
	}
	function pub() {
	var objs=new Object();
	var data=new Object();
	var tName="";
     var tt=new Array();
     var teamIds= new Array();
     if($('#startDate').val()===""){
    	 $.alert("请填写开始时间");
    	 return false;
     }
     if($('#finishedDate').val()===""){
    	 $.alert("请填写结束时间");
    	 return false;
     }
     var val=$('input:radio[name="type"]:checked').attr("id");
     var isCheck=$('input:radio[name="isCheck"]:checked').val();
     tt=${teamIds};
     if(val==='subject'){
    	 objs=getSelectedClasses();
     }else{
    	 objs=getGradeClasses();
     }
     $.each(objs, function (index, obj) {
    	for(var i=0;i<tt.length;i++){
    		var own=tt[i];
    		 if(own==obj.teamId){
    			 tName=tName+obj.name+",";
    		 }
    	 }
    	 teamIds.push(obj.teamId);
     });
     if(teamIds.length===0){
    	 $.alert("请选择班级");
    	 return false;
     }
        if(tName.length>0){
        var r=confirm(tName.substring(0,tName.length-1)+"已经发布该试卷，是否再次发布");
        	if(r==true){
        	
        	}else{
        		return false;
        	}
        }
     var task=new Object();
     task.paperId=${param.paperId};
     task.startTime=Date.parse($('#startDate').val().replace(/-/g,"/"));
     task.finishTime=Date.parse($('#finishedDate').val().replace(/-/g,"/"));
     if(task.startTime>=task.finishTime){
    	 $.alert("开始时间不能大于等于结束时间");
    	 return false;
     }
     task.isCheck=isCheck;
     data.task=task;
     data.teamIds=teamIds;
     var data=JSON.stringify(data);
     var loader = new loadLayer();
 	loader.show();
	 $.ajax({  
		    async:false,  
		    type:"post",  
		    url:"${pageContext.request.contextPath}/paperTask/addTask",  
		    data:{"data":data},  
		    ContentType: "application/json;charset=UTF-8",
		    success:function(data){  
		    	if(data==='success'){
		    		$.alert("布置成功");
		    		
			    		<c:choose>
				    		<c:when test="${not empty isPC}">
				    			back();
		    			}
				    		</c:when>
				    		<c:otherwise>
				    		 	window.location.href="${pageContext.request.contextPath}/paperTask/team/task?dm=SHI_JUAN_GUAN_LI";
					    }else if(data==='timeout'){
				    		$.alert("您的浏览器版本过低，请升级");
				    	}
				    		</c:otherwise>
			    		</c:choose>
		    },  
		    error:function(data){  
		    	console.log(data.responseText);
		    	$.alert(data.responseText);  
		    }  
		})
		loader.close();
	}
	   $(function(){
		   $('#gradeClassDiv .banji .nj li a').eq(0).click();
		      $(".all_select1").click(function(){
		           if($(this ).is(':checked' )){
		                  $(this).parent().parent().siblings().children().children().prop("checked" , "checked");
		             } else{
		                  $(this).parent().parent().siblings().children().children().removeAttr("checked");
		             }
		       });
	   });
	   function back(){
		   history.go(-1);
	   }
</script>
</html>