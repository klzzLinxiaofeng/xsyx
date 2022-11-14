<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
 
<table class="table table-bordered table-hover" >
       <thead>
    <tr class="warning" >
       
     
      <th width="12%">申请人</th>
      <th width="12%">请假类型</th> 
       <th width="12%">请假天数</th> 
       <th width="30%">请假描述</th>  
      <th width="10%">申请时间</th>
      <th width="8%" >操作</th> 
    </tr>
  </thead>
   <tbody id="oaLeaveAPP_list_content">
   
								<jsp:include page="./list.jsp" />
								 
  
  </tbody>
   </table>


								
						 
								
								
								<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="oaLeaveAPP_list_content" />
								<jsp:param name="url" value="/office/ajax/appr/leaveList?sub=list&dm=${param.dm}" />
								<jsp:param name="pageSize" value="${page.pageSize}" />
							</jsp:include>
							<div class="clear"></div>
							
	<script type="text/javascript">
 
  
	// 	加载创建对话框
	function loadApprPage(id) {
		 
		$.initWinOnTopFromTop('审批处理', '${ctp}/office/appr/shenpi/'+id, '900');
	}
 
 

 
</script>						
							