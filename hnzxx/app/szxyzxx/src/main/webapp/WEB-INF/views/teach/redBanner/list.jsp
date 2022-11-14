<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/views/embedded/common.jsp"%>

 <div class="points-content table-spacing" style="display: block;">
                            <p>流动红旗评比  <span>${week}</span>
	                            <c:if test="${flag == true }"><span style="color: green;">（已评定）</span></c:if>
	                            <c:if test="${flag == false }"><span style="color: red;">（未评定）</span></c:if>
                            </p>
                            <div class="points limit-cancel" style="position: relative;">
                            <table class="responsive table table-striped reflective-evaluate" >
                            <thead>
                                <tr role="row">
                                   <th>班级排名</th>
                                   <th>班级</th>
                                   <th>班主任</th>
                                   <th>扣分</th>
                                   <th>加分</th>
                                   <th>得分</th>
<!--                                    <th>恶性事件</th> -->
                                   <th>流动红旗</th>
                                    <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
                                   <th>班级详情</th>
                                   </c:if>
                                </tr>
                            </thead>
                            <tbody >
<!--                                  <tr class=""> -->
<!--                                      <td>1班</td> -->
<!--                                      <td>张小龙</td> -->
<!--                                      <td><span>1.5</span></td> -->
<!--                                      <td><span>0</span></td> -->
<!--                                      <td><span>99</span></td> -->
<!--                                      <td></td> -->
<!--                                      <td>3</td> -->
<!--                                      <td>红旗班级</td> -->
<!--                                      <td><a href="javascript:void(0);">查看</a></td> -->
<!--                                 </tr> -->
                         <c:forEach items="${redlist}" var="item">
                          <tr class="">
                                     <td>${item.rank}</td>
                                     <td>${item.teamName}</td>
                                     <td>${item.teamTeacherName}</td>
                                     <td><span>${item.reduceScore}</span></td>
                                     <td><span>${item.addScore}</span></td>
                                     <td><span>${item.totalScore}</span></td>
<%--                                      <td>${item.remark}</td> --%>
                                     <c:if test="${item.isRed==1}">
                                     <td><i class="fa fa-flag" style="color:red;font-size:14px;margin-right:10px;"></i>红旗班级</td>
                                     </c:if>
                                     <c:if test="${item.isRed==0}">
                                     <td></td>
                                     </c:if>
                                     <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
                                     <td><a href="javascript:void(0)" onclick="toTeamEva(${item.teamId})">查看</a></td>
                                     </c:if>
                                </tr>
                         
                         </c:forEach>
                             </tbody>
                        </table>
                        </div>
                        </div>

		<div style="font-size: 15px;font-weight:bold">
			<p style="color: red; margin: 0;"><b>评分机制说明：</b></p>
			<p><b>每个班级总分设置为100分，总分-扣分分数+加分分数=得分。</b></p>
			<p style="color: red; margin: 0;"><b>流动红旗评比说明：</b></p>
			<p><b>流动红旗评比结果以设置评分标准分值为准,通过在设置评分标准里面设置获取流动红旗分数线，评比出班级流动红旗。</b></p>
		</div>

<script type="text/javascript">
	if(${flag} == true){
		$("#button_pd").text("重新评定");
	}else{
		$("#button_pd").text("评定");
	}
</script>

