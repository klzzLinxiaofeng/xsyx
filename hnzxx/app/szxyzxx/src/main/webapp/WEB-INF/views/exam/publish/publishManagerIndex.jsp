<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
        <%@ include file="/views/embedded/common.jsp"%>
        <title>试卷管理</title>
    </head>
    <style>
    	.widget-container .kemu a{
            	background-color:#fff;
            	color:#000;
            }
    </style>
    <body>
        <div class="container-fluid">
            <jsp:include page="/views/embedded/navigation.jsp">
                <jsp:param value="fa-asterisk" name="icon"/>
                <jsp:param value="试卷管理" name="title" />
                <jsp:param value="${param.dm}" name="menuId" />
            </jsp:include>
            <div class="row-fluid">
                <div class="span12">
                    <div class="content-widgets white">
                        <div class="widget-head">
                            <h3>
                                试卷管理
                            </h3>
                        </div>
<%-- <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" /> --%>
<%-- <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" /> --%>
                        <div class="content-widgets">
                            <div class="widget-container">
                            <c:if test="${!acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
                                <div class="kemu" style="float: left;">
                                <div class="select_div" style="line-height: 37px;height: 37px; float:left;" >
                                <span style="float:left;">班级</span>
                                <select class="chzn-select" id="bj" name="bj">
                                    <c:forEach items="${classGradeMap}" var="cm">
                                        <c:forEach items="${cm.value}" var="teamMap">
                                            <c:forEach items="${teamMap}" var="team">
<%--                                                 <a onclick="changeClass('${team.key}','${fn:split(team.value,'&&')[1]}');" href="javascript:void(0);" id="class_${team.key}" class="active" style="width:126px" data-type="${fn:split(team.value,'&&')[1]}">${fn:split(team.value,"&&")[0]}</a> --%>
                                             <option value="${team.key}">${fn:split(team.value,"&&")[0]}</option>
                                            </c:forEach>
                                        </c:forEach>
                                    </c:forEach>
                                 </select>
                                </div>
                                <button type="button" class="btn btn-primary" onclick="changeAllClass()">查询</button>
                                </div>
                                </c:if>
                                <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
                                         <div class="kemu" >
<%--                                     <c:forEach items="${allClassMap}" var="cm"> --%>
<%--                                         <c:forEach items="${cm.value}" var="teamMap"> --%>
<%--                                             <c:forEach items="${teamMap}" var="team"> --%>
<%--                                                 <a onclick="changeClass('${team.key}','01');" href="javascript:void(0);" id="class_${team.key}" class="active" style="width:126px" data-type="01">${fn:split(team.value,"&&")[0]}</a> --%>
<%--                                             </c:forEach> --%>
<%--                                         </c:forEach> --%>
<%--                                     </c:forEach> --%>
                                        <div class="select_div" style="display: none;">
								<span>学年：</span>
								<select id="xn" name="xn" class="chzn-select"
										style="width: 120px;"></select>
								</div>
                                        <div class="select_div" style="line-height: 37px;height: 37px;float: left;">
											<span style="float:left;">年级：</span><select id="nj" name="nj" class="chzn-select" style="width: 120px;"></select>
										</div>
										<div class="select_div" style="line-height: 37px;height: 37px;float: left;">
											<span style="float:left;">班级：</span><select id="bj" name="bj" class="chzn-select" style="width: 120px;"></select>
										</div>
<!--                                                           <ul class="nj" style="margin: 10px 0 0 0;"> -->
<%--                                                         <c:forEach varStatus="st" items="${allClassMap}" var="cm"> --%>
<!--                                                             <li> -->
<%--                                                                 <a onclick="changeClass('${st.index}');" href="javascript:void(0)">${fn:split(cm.key,"&&")[0]}</a> --%>
<%--                                                                 <input type="hidden" value="${fn:split(cm.key,'&&')[1]}" /> --%>
<!--                                                             </li> -->
<%--                                                             </c:forEach> --%>
<!--                                                                                                        <li><a href="javascript:void(0)">小组</a></li> -->
<!--                                                     </ul> -->
                                                    
<!--                                                     <div class="clear"></div> -->
<%--                                                     <c:forEach items="${allClassMap}" var="cm"> --%>
<!--                                                         <div class="fenban"  > -->
<!--                                                             <ul> -->
<%--                                                                 <c:forEach items="${cm.value}" var="teamMap"> --%>
<%--                                                                     <c:forEach items="${teamMap}" var="team"> --%>
<!--                                                                         <li style="font-size: 14px;"> -->
<%--                                                                             <input data-type="${fn:split(team.value,'&&')[1]}" --%>
<%--                                                                              value="${team.key}" onclick="checkClassSubject(this)" type="checkbox" style="width:16px;height:16px;"/> --%>
<%--                                                                             ${fn:split(team.value,"&&")[0]} --%>
<%--                                                                             <input type="hidden" value="${fn:split(team.value,'&&')[1]}" /> --%>
<!--                                                                         </li> -->
<%--                                                                         </c:forEach> --%>
<%--                                                                     </c:forEach> --%>
<!--                                                                     </ul> -->
									<button type="button" class="btn btn-primary" onclick="changeAllClass()">查询</button>
                                </div>
                                </c:if>
                                 
                                <div id="microDataDiv">
                                    <jsp:include page="./publishManager.jsp" />
                                </div>
                                <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                                    <jsp:param name="id" value="microDataDiv" />
                                    <jsp:param name="url" value="/exampublish/publishManager" />
                                    <jsp:param name="pageSize" value="" />
                                </jsp:include>

                                <div class="clear"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script type="text/javascript">
                                                    function changeAllClass() {
                                                    	var classId=$('#bj').val();
                                                    	var gradeId=$('#nj').val();
//                                                     	alert(classId)
                                                        if(classId===""){
                                                        	$.error("请选择班级");
                                                        	return false;
                                                        }
                                                        var url = "/exampublish/publishManager";
                                                        var data = {"relateId": classId,"gradeId":gradeId,"relateType":"01","dm":"${param.dm}"};
                                                        myPagination("microDataDiv", data, url);
                                                    }

                                                    function resetDate(mid, startDate, finishedDate) {
                                                        var mes = "修改时间";
                                                        var relateId = $("#bj").val();
                                                        var gradeId = $("#nj").val();
                                                        $.initWinOnTopFromLeft(mes, '${pageContext.request.contextPath}/exampublish/resetDate?dm=${param.dm}&publishId=' + mid + '&relateId=' + relateId + '&startDate=' + startDate + '&finishedDate=' + finishedDate+'&gradeId='+gradeId+'&dm=${param.dm}', '700', '190');
                                                    }
                                                    function mark(examId,paperId,ownerId,flag){
//                                                     
                                                          var loader = new loadLayer();
                                                          $.success("正在进行统计，请稍后查看统计信息!");
                                                          loader.show();
                                                    	 $.ajax({
                                                             url: "${pageContext.request.contextPath}/statistic/index",
                                                             type: "POST",
                                                             data: {"examId": examId,"paperId":paperId, "ownerId": ownerId},
                                                             async: true,
                                                             success: function(data) {
                                                            	 loader.close();
                                                            	 $.success("正在进行统计，请稍后查看统计信息!");
                                                             }
                                                    });
                                                    }
                                                    function volume(pjExamId,relateId,gradeId,flag,paperId,taskId){
//                                                     	if(flag==="false"){
//                                                     		$.error("该测试未开始或进行中，请在测试结束后进行统计。");
//                                                     		return false;
//                                                     	}
                                                    	window.location.href="${pageContext.request.contextPath}/statistic/volume?examIdString="+ pjExamId+"&relateId="+ relateId+"&gradeId="+ gradeId+"&paperId="+paperId+"&taskId="+taskId;
                                                    }
                                                    function  question(pjExamId,relateId,gradeId,taskId,paperId,flag){
//                                                     	if(flag==="false"){
//                                                     		$.error("该测试未开始或进行中，请在测试结束后进行统计。");
//                                                     		return false;
//                                                     	}
                                                    	window.location.href="${pageContext.request.contextPath}/statistic/question?examIdString="+ pjExamId+"&relateId="+ relateId+"&gradeId="+ gradeId+"&taskId="+taskId+"&paperId="+ paperId;
                                                    }
                                                    function deletePublish(mid,userId,publisherUserId,pjExamId) {
                                                    	if(userId!=publisherUserId){
                                                    		$.error("你不是发布教师，不能删除");
                                                    		return false;
                                                    	}
                                                        $.confirm("确定删除这条已发布的试卷吗?", function() {
                                                            $.ajax({
                                                                url: "${pageContext.request.contextPath}/exampublish/deletePublished",
                                                                type: "POST",
                                                                data: {"publishId": mid, "relateId": $("#bj").val(),"pjExamId":pjExamId},
                                                                async: false,
                                                                success: function(html) {
                                                                	$.success("删除成功");
                                                                	changeAllClass()
                                                                }
                                                            });
                                                        });
                                                    }

                                                    $(function() {
        <c:if test="${!acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
         $('#bj').chosen();
          </c:if> 
                                                    });
            <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
                                                    $.initCascadeSelector({
                                        				"type" : "team",
                                        				"selectOne":true,
                                        				<c:if test="${!empty param.gradeId}">
                                        				"isEchoSelected" : true, //是否回显伊选中的选项，默认是false
                                  	                    "yearSelectedVal" : "${sessionScope[sca:currentUserKey()].schoolYear}",
                                        			   	"gradeSelectedVal" : "${param.gradeId}", //要回显的年级唯一标识
                                        			  	"teamSelectedVal" : "${param.relateId}" //要回显的班级唯一标识
                                        				</c:if>
                                        			});
                                                    </c:if>
//                                    changeAllClass();
                                   setTimeout("changeAllClass();",200);
    </script>
</html>