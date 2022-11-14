<%@page import="platform.education.resource.contants.ResourceType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <%@ include file="/views/embedded/common.jsp"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${sca:getDefaultSchoolName()}</title>
        <style type="text/css">
            .form-horizontal .control-label{
            	width: 60px;text-align: left;
            }
            .form-horizontal .controls {
                margin-left: 40px;
            }
            .res_a{
            	cursor: pointer;
            }
            .fenban{
            	margin-left: 20px;
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
            
        </style>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="/views/embedded/navigation.jsp">
                <jsp:param value="fa-calculator" name="icon"/>
                <jsp:param value="布置试卷" name="title" />
                <jsp:param value="${param.dm}" name="menuId" />
            </jsp:include>
            <div class="row-fluid ">
                <div class="span12">
                    <div class="content-widgets white" style="margin-bottom: 0;">
                        <div class="widget-container">
                            <form id="dept_form" class="form-horizontal" novalidate="novalidate">
                                <div class="control-group">
                                    <label class="control-label">
                                        <font style="color:red">*</font>时间
                                    </label>
                                    <div class="controls">
                                        <input id="startDate" type="text"  onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '%y-%M-%d %H:%m:%s', maxDate: '#F{$dp.$D(\'finishedDate\')}'})" placeholder="开始日期" class="span2">
                                        
<!--                                         <select id="startClock" class="span1"> -->
<!--                                             <option>1</option> -->
<!--                                             <option>2</option> -->
<!--                                             <option>3</option> -->
<!--                                             <option>4</option> -->
<!--                                             <option>5</option> -->
<!--                                             <option>6</option> -->
<!--                                             <option>7</option> -->
<!--                                             <option>8</option> -->
<!--                                             <option>9</option> -->
<!--                                             <option>10</option> -->
<!--                                             <option>11</option> -->
<!--                                             <option>12</option> -->
<!--                                             <option>13</option> -->
<!--                                             <option>14</option> -->
<!--                                             <option>15</option> -->
<!--                                             <option>16</option> -->
<!--                                             <option>17</option> -->
<!--                                             <option>18</option> -->
<!--                                             <option>19</option> -->
<!--                                             <option>20</option> -->
<!--                                             <option>21</option> -->
<!--                                             <option>22</option> -->
<!--                                             <option>23</option> -->
<!--                                             <option>24</option> -->
<!--                                         </select> -->
                                        <span style="margin:0 10px 0 5px;">点</span><span style="margin:0 5px 0 10px;">至</span>
                                        <input id="finishedDate" type="text" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '#F{$dp.$D(\'startDate\')}', maxDate: ''})" placeholder="结束日期" class="span2">
<!--                                         <select id="finishedClock" class="span1"> -->
<!--                                             <option>1</option> -->
<!--                                             <option>2</option> -->
<!--                                             <option>3</option> -->
<!--                                             <option>4</option> -->
<!--                                             <option>5</option> -->
<!--                                             <option>6</option> -->
<!--                                             <option>7</option> -->
<!--                                             <option>8</option> -->
<!--                                             <option>9</option> -->
<!--                                             <option>10</option> -->
<!--                                             <option>11</option> -->
<!--                                             <option>12</option> -->
<!--                                             <option>13</option> -->
<!--                                             <option>14</option> -->
<!--                                             <option>15</option> -->
<!--                                             <option>16</option> -->
<!--                                             <option>17</option> -->
<!--                                             <option>18</option> -->
<!--                                             <option>19</option> -->
<!--                                             <option>20</option> -->
<!--                                             <option>21</option> -->
<!--                                             <option>22</option> -->
<!--                                             <option>23</option> -->
<!--                                             <option selected>24</option> -->
<!--                                         </select> -->
<!--                                         <span style="margin:0 10px 0 5px;">点</span> -->
                                    </div>
                                </div>
                                <div class="control-group">
                                 <input type="radio" name="type" id="subject" onclick="subjectOn()"checked="checked" style="margin: 0 5px 0 0;">按科目发布
                                        <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
                                         <input type="radio" name="type" id="grade"    onclick="gradeOn()"style="margin: 0 5px 0 20px;">按年级发布
	                                    </c:if>
                                    <label class="control-label"><font style="color:red">*</font>班级</label>
                                    <div id="gradeClassDiv" class="controls">
                                        <div class="banji">
                                            <c:choose>
                                                <c:when test="${fn:length(classGradeMap)<=0}">
                                                    <div class="tishi">该用户没有分配任何班级</div>  
                                                </c:when>
                                                <c:otherwise>
                                                    <ul class="nj" style="margin: 10px 0 0 0;">
                                                        <c:forEach varStatus="st" items="${classGradeMap}" var="cm">
                                                            <li>
                                                                <a onclick="changeClass('${st.index}');" href="javascript:void(0)">${fn:split(cm.key,"&&")[0]}</a>
                                                                <input type="hidden" value="${fn:split(cm.key,'&&')[1]}" />
                                                            </li>
                                                            </c:forEach>
                                                        <!--                                                <li><a href="javascript:void(0)">小组</a></li>-->
                                                    </ul>
                                                    
                                                    <div class="clear"></div>
                                                    <c:forEach items="${classGradeMap}" var="cm">
                                                        <div class="fenban" style="display:none" >
                                                            <ul>
                                                                <c:forEach items="${cm.value}" var="teamMap">
                                                                    <c:forEach items="${teamMap}" var="team">
                                                                        <li style="font-size: 14px;">
                                                                            <input data-type="${fn:split(team.value,'&&')[1]}"
                                                                             value="${team.key}" onclick="checkClassSubject(this)" type="checkbox" style="width:16px;height:16px;"/>
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
												<ul class="nj" style="margin: 10px 0 0 0;">
                                                   <c:forEach varStatus="st" items="${allClassMap}" var="cm">
                                                       <li>
                                                           <a onclick="changeClass('${st.index}');" href="javascript:void(0)">${fn:split(cm.key,"&&")[0]}</a>
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
                                                                        <input data-type="01" value="${team.key}" onclick="" type="checkbox" style="width:16px;height:16px;"/>
                                                                        ${fn:split(team.value,"&&")[0]}
                                                                        <input type="hidden" value="${fn:split(team.value,'&&')[1]}" />
                                                                    </li>
                                                                    </c:forEach>
                                                            </c:forEach>
                                                        </ul>
                                                        
                                                        <div class="clear"></div>
                                                    </div>
                                                </c:forEach>
                                     		 
                                     		</div>
<!--                                      <div class="radio-bj"> -->
<%--                                                <c:forEach items="${allClassMap}" var="item"> --%>
<%--                                                <div class="radio-left"><input type="radio" name="gradeName"data-key="${fn:split(item.key,'&&')[1]}" >${fn:split(item.key,'&&')[0]} --%>
<%--                                                          <div id="${fn:split(item.key,'&&')[1]}"> --%>
<%--                                                             <c:forEach items="${item.value}" var="ems"> --%>
<%--                                                               <c:forEach items="${ems}" var="em"> --%>
<%--                                                             <input data-id="${em.key}" data-name="${em.value}" data-type="01" type="hidden"> --%>
<%--                                                               </c:forEach> --%>
<%--                                                             </c:forEach> --%>
<!--                                                          </div> -->
<!--                                                         </div> -->
<%--                                                      </c:forEach> --%>
<!--                                                      </div> -->
                                  </div>
                                </div>
                                <div id="microMainDiv" class="control-group">
                                    <label class="control-label"><font style="color:red">*</font>试卷</label>
                                    <div class="controls">
                                        <div class="banji">
                                            <ul class="nj">
                                                <li><a onclick="changeMicroTab($(this).attr('id'))" id="commonMicro" href="javascript:void(0)">公共资源库</a></li>
                                                <li><a onclick="changeMicroTab($(this).attr('id'))" id="schoolMicro" href="javascript:void(0)">校本资源库</a></li>
                                                <li><a onclick="changeMicroTab($(this).attr('id'))" id="myMicro" href="javascript:void(0)">个人资源库</a></li>
                                                <li><a onclick="changeMicroTab($(this).attr('id'))" id="upload" href="javascript:void(0)">上传试卷</a></li>
                                            </ul>
                                            <div class="back_tj">
                                            <div class="kemu_nav" style="display: none;float:left;">
									    		<ul>
									        		<li><a id="myresource" class="active res_a" onclick="chose($(this).attr('id'))">我上传的资源</a></li>
									        		<li><a id="favresource" class="res_a" onclick="chose($(this).attr('id'))">我收藏的资源</a></li>
									        		<li><a id="myshare" class="res_a" onclick="chose($(this).attr('id'))">我的共享</a></li>
									    		</ul>
											  </div>
                                            
                                            <div class="ts" style="width: 420px;">
                                            <select id="isCheck" style="height:38px;width: 170px;">
                                            <option value="1">允许随时查看答案</option>
                                            <option value="0">不允许查看答案</option>
                                            <option value="2" selected>允许提交答案后查看答案</option>
                                            <option value="3">允许测试结束后查看答案</option>
                                            </select>
                                                <div class="yx_weike"style="margin-right: 5px;">
                                                    <div class="yx_num">
                                                    
                                                        <a id="clearButton" onclick="clearMicro();" href="javascript:void(0)" class="close_all" style="display:none" >
                                                            <i class="fa fa-trash-o"></i>清空
                                                        </a>
                                                        <p class="yx">
                                                            <a onclick="showChosenMicroDiv()" href="javascript:void(0)">已选试卷<span id="chosenMicroSize">0</span></a>
                                                        </p>
                                                    </div>
                                                    <div id="chosenMicroDiv" class="yx_wk" style="display:none">
                                                        <ul>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div onclick="publishLesson();" class="btn btn-danger" style="float: right;position:relative;top:5px;">布置已选试卷</div>

                                            </div>
                                             </div>
                                            <div class="clear"></div>
                                            <div class="weike" style="margin-left: 20px;">

                                            </div>
                                            <div class="tishi" ></div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
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
				        	url = url + "/exampublish/myResource?index=index&resType=4&personType=res_person";
			            }else if(dataType == "favresource"){//我收藏的资源
			            	url = url + "/exampublish/favResource?index=index&resType=4&personType=fav_resource";
			            }else if(dataType == "myshare"){//我的共享
			            	url = url + "/exampublish/myResource/myShare?index=index&resType=4&personType=res_share";
			            }
			        	$(".weike").load(url,function(){});
			        }
        
                                            var chosenMicroJson = {"microList": []};
                                            //试卷的来源
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
                                                    //我的试卷
                                                    url = url + "/exampublish/myResource?index=index&personType=res_person&resType=4&dm=${param.dm}";
                                                    source = "来自我的资源库";
                                                    $(".kemu_nav").css("display","block");
                                                    $(".back_tj").css({"float":"left","width":"99%","margin-left":"20px"})
                                                } else if (type == "commonMicro") {
                                                    //公共试卷库
                                                    var subjectCode = $(".fenban input[type='checkbox']").first().next().val();
                                                    var gradeCode = $(".nj .active input[type='hidden']").val();
                                                    url = url + "/resource/searcher/index?isPublish=1&stageCode=2&subjectCode="+subjectCode+"&gradeCode="+gradeCode+"&resType=<%=ResourceType.EXAM%>";
                                                    source = "来自公共资源库";
                                                    $(".kemu_nav").css("display","none");
                                                    $(".back_tj").css({"float":"right","width":"auto"})
                                                } else if(type == "schoolMicro") {
                                	            	//校本微课库
                                	                var subjectCode = $(".fenban input[type='checkbox']").first().next().val();
                                	                var gradeCode = $(".nj .active input[type='hidden']").val();
                                	                url = url + "/resource/searcher/index?isPublish=0&stageCode=2&subjectCode=" + subjectCode + "&gradeCode=" + gradeCode + "&resType=<%=ResourceType.EXAM%>";
                                	                source = "来自校本资源库";
                                	                $(".kemu_nav").css("display","none");
                                	                $(".back_tj").css({"float":"right","width":"auto"})
                                	            } else if (type == "upload") {
                                                    //本地上传
                                                    url = url + "/examhouse/upload?publish=publish";
                                                    source = "来自我的资源库";
                                                    $(".kemu_nav").css("display","none");
                                                    $(".back_tj").css({"float":"right","width":"auto"})
                                                }

                                                $(".weike").load(url, function() {
                                                    loader.close();
                                                });
                                            }

                                            function chooseMicro(mid) {
	                                            $(".select input:checkbox:checked").prop("checked", false);
                                            	 chosenMicroJson.microList = [];
                                                 $("#chosenMicroDiv ul").html("");
                                                 $("#microCheck_"+mid).prop("checked", true);
                                                var removeFlag = null;
                                                if (chosenMicroJson.microList.length > 0) {
                                                    for (var i = 0; i < chosenMicroJson.microList.length; i++) {
                                                        var micro = chosenMicroJson.microList[i]
                                                        if (micro.id == mid) {
                                                            removeFlag = i;
                                                        }
                                                    }
                                                }
                                                if (removeFlag != null) {
                                                    chosenMicroJson.microList.splice(removeFlag, 1);
                                                    $("#chosenMicroLi_" + mid).remove();
                                                } else {
                                                    var title = $("#microId_" + mid).prev().text();
                                                    var resourceId = $("#resourceId_" + mid).val();
                                                    addMicro(mid, title,resourceId);
                                                }
                                                setSize();
                                            }

                                            function addMicro(mid, title,resourceId) {
                                                var micro = {"id": mid, "title": title,"resourceId":resourceId};
                                                chosenMicroJson.microList.push(micro);
                                                var html = "<li id=\"chosenMicroLi_" + mid + "\"><a onclick=\"previewMicro('"+mid+"')\" href=\"javascript:void(0)\" class=\"name\">" + title 
                                                        + "</a><p>" + source + "</p><a onclick=\"clearMicro('" + mid + "')\" href=\"javascript:void(0)\" class=\"close_1\"></a></li>";
                                                $("#chosenMicroDiv ul").prepend(html);
                                            }

                                            function removeUploadMicro() {
                                                changeMicroTab("upload");
                                            }

                                            function clearMicro(mid) {
                                                if (mid != null && mid != "") {
                                                    for (var x = 0; x < chosenMicroJson.microList.length; x++) {
                                                        var id = chosenMicroJson.microList[x].id;
                                                        if (id == mid) {
                                                            chosenMicroJson.microList.splice(x, 1);
                                                            $("#chosenMicroLi_" + mid).remove();
                                                            //去除勾选
                                                            $("#microCheck_" + mid).attr('checked', false);
                                                            break;
                                                        }
                                                    }
                                                    setSize();
                                                } else {
                                                    $.confirm("确定清空已选试卷吗?", function() {
                                                        chosenMicroJson.microList = [];
                                                        $("#chosenMicroDiv ul").html("");
                                                        //去除所有勾选
                                                        $("#microLessonDiv input[type='checkbox']").each(function() {
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
                                                //检查跳页后是否有已选试卷
                                                $("#microLessonDiv input[type='checkbox']").each(function() {
                                                    for (var i = 0; i < chosenMicroJson.microList.length; i++) {
                                                        var micro = chosenMicroJson.microList[i];
                                                        if (micro.id == $(this).attr("id").split("_")[1]) {
                                                            $(this).attr('checked', true);
                                                            break;
                                                        }
                                                    }
                                                });
                                            }

                                            function publishLesson() {
                                            	var name=$('input[name="type"]:checked').attr("id");
                                            	var isCheck=$("#isCheck option:selected").val();
                                                var data = {};
                                                data["startDate"] = $("#startDate").val();
                                                data["finishedDate"] = $("#finishedDate").val();
                                                data["startClock"] = $("#startClock option:selected").text();
                                                data["finishedClock"] = $("#finishedClock option:selected").text();
                                                data["microList"] = chosenMicroJson.microList;
                                                data["isCheck"]=isCheck;
				                                if(name==="subject"){
				                                	data["classList"] = getSelectedClasses();
				                                	data["gradeId"]=$(".nj .active input").val();
				                                }else{
				                               	  	data["classList"] = getGradeClasses();
				                               	 	data["gradeId"]=$(".nj .active input").val();
// 				                               		data["gradeId"]=$('input[name="gradeName"]:checked').attr("data-key");
				                                }
                                              
                                                if (data["startDate"] == null || data["startDate"] == "") {
                                                    $.alert("请输入开始日期");
                                                    return;
                                                }
                                                if (data["finishedDate"] == null || data["finishedDate"] == "") {
                                                    $.alert("请输入结束日期");
                                                    return;
                                                }
                                                if (data["classList"] == null || data["classList"].length <= 0) {
                                                    $.alert("请选择要发布的班级");
                                                    return;
                                                }
                                                if (data["microList"] == null || data["microList"].length <= 0) {
                                                    $.alert("请选择要发布的试卷");
                                                    return;
                                                }
                                                
                                                var loader = new loadDialog();
                                                loader.show();
                                                $.ajax({
                                                    url: "${pageContext.request.contextPath}/exampublish/publishLesson",
                                                    type: "POST",
                                                    data: {"publishData": JSON.stringify(data)},
                                                    async: true,
                                                    success: function() {
                                                    	loader.close();
                                                        $(window.parent.document).find("body li[data-menu-id='BU_ZHI_SHI_JUAN'] a").removeClass("blue_1");
                                                        $(window.parent.document).find("body li[data-menu-id='SHI_JUAN_GUAN_LI'] a").addClass("blue_1");
                                                        var relateId = data["classList"][0].relateId;
                                                        var relateType = data["classList"][0].relateType;
                                                        location.href = "${pageContext.request.contextPath}/exampublish/publishManagerIndex?dm=${param.dm}&relateId="+relateId
                                                        +"&relateType="+relateType;
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
                                                        $(this).find("input[type='checkbox']").removeAttr("disabled");
                                                    } else {
                                                        $(this).find("input[type='checkbox']").attr("disabled", true);
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
                                                $("#gradeClassDiv .fenban input[type='checkbox']:checked").each(function() {
                                                	var teamName = $(this).parent().text();
                                                	teamName = teamName.substring(0, teamName.indexOf("[") != -1 ? teamName.indexOf("[") : teamName.length);
                                                    var cj = {"relateId": $(this).val(), "relateName": $.trim(teamName),"relateType":$(this).attr("data-type")};
                                                    classes.push(cj);
                                                });
                                                return classes;
                                            }
                                            function getGradeClasses(){
                                            	var classes = new Array();
//                                             	var name=$("#allclass input[type='radio']:checked").attr("data-key");
//                                             	 $("#"+name+" input").each(function() {
//                                             		 var cj = {"relateId": $(this).attr("data-id"), "relateName": $(this).attr("data-name"),"relateType":$(this).attr("data-type")};
//                                                      classes.push(cj);
//                                             	 });
                                            	 $("#allclass .fenban input[type='checkbox']:checked").each(function() {
                                            		 if(!$(this).hasClass("all_select1")){
                                                     	var cj = {"relateId": $(this).val(), "relateName": $.trim($(this).parent().text()),"relateType":$(this).attr("data-type")};
	                                            		classes.push(cj);
                                            		 }
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

                                            function previewMicro(mid) {
                                                var mes = "预览";
                                                $.initWinOnTopFromLeft(mes, '${pageContext.request.contextPath}/exampublish/preview?objId=' + mid, '700', '500');
                                            }

                                            $(function() {
//                                                 //默认开始时间为当前日期时间
//                                                 $("#startDate").val(new Date().Format("yyyy-MM-dd hh:mm"))
//                                                 //默认结束时间为当前日期24点
//                                                 $("#finishedDate").val(new Date().Format("yyyy-MM-dd hh:mm"))
                                                changeClass(0);
                                                changeMicroTab("commonMicro");
                                                
//                                                全选
                                                $(".all_select1").click(function(){
                                                    if($(this ).is(':checked' )){
                                                           $(this).parent().parent().siblings().children().children().prop("checked" , "checked");
                                                      } else{
                                                           $(this).parent().parent().siblings().children().children().removeAttr("checked");
                                                      }
                                                });
                                            });

        </script>
    </body>
</html>
