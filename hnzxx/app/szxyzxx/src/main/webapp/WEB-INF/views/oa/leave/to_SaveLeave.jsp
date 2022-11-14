<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%> 
<style>
.row-fluid .span13 {
	width: 75%;
}

.row-fluid .span4 {
	width: 75%;
}

.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
</style>

<script type="text/javascript">
	$(function() {
		$.createMemberSelector({
			"inputIdSelector" : "#member_selector",
// 			"isOnTopWindow" : true,
			"layerOp" : {
				"shift" : "top"
			}
		});
	});
</script>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="leave_form" name="leave_form" 
						action="javascript:void(0);">

						  <fieldset>
                      <div class="control-group">
                            <label class="control-label" for="content">请假类别<font style="color:red">*</font>:</label>
                            <div class="controls">
                                         
                                <select class="span3" name="leavetype">
               <option value="年假">年假</option>
              <option value="事假">事假</option>
              <option value="病假">病假</option>
              <option value="婚假">婚假</option>
              <option value="产假">产假</option>
              <option value="丧假">丧假</option>
              <option value="公假">公假</option>
              <option value="其他">其他</option>
            </select>
                                
                                
                            </div>
                        </div>
                        
                        <div class="control-group">
                            <label class="control-label" for="content">请假事由<font style="color:red">*</font>:</label>
                            <div class="controls">
                                        <div class="textarea">
                                    <textarea  rows="5" cols="215" name="leaveinfo" class="input-xlarge required" placeholder="请输入请假说明！"> </textarea>
                                </div>
                                
                            </div>
                        </div>
                        
                        <div class="control-group">
                            <label class="control-label" for="content">请假天数<font style="color:red">*</font>:</label>
                            <div class="controls">
                                        <div class="textarea">
                                    <input type="number" name="days" id="days" step="1"    min="0"  max="30" class="span2 required">
                                
                                </div>
                                
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="filePassword">请假时间<font style="color:red">*</font>:</label>
                            <div class="controls">
                            <input type="text" id="d4331" name="starttime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="span3 required"  />
                            
                            至<input type="text" id="d4332" name="endtime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="span3 required"  />
                   
                            </div>
                        </div>
                        
                        <div class="control-group">
							<label class="control-label"> 选择审批人<font style="color:red">*</font>： </label>
							<div class="controls">
								<div class="textarea">
									<input type="button" id="member_selector" value="选择审批人">
                                  <span id="teachName">
                                  <c:if test="${!empty  noticeUser}">已选择的审批人</c:if>
                                  <c:forEach items="${noticeUser}" var="entry" varStatus="statu">
                                  ${entry.teachName },
                                    </c:forEach> 
                                     </span>
                                     
                                     <input type="hidden" id="ids" name="userid_list" class="required">
                                     <input type="hidden" id="tname" name="teachName">  
								</div>
							</div>
						</div>
                        
                          
                        
                        
                         
                    </fieldset>
						<div class="form-actions tan_bottom">
							 
							<button class="btn btn-warning" type="button"
								onclick="saveOrUpdate();">确定</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
 
<script type="text/javascript">
var checker;
$(function() {
	checker = initValidator();
});

function initValidator() {
	return $("#leave_form").validate({
		errorClass : "myerror",
		rules : {
			"days":{
				digits:true
				 
			} 
		},
		messages : {
			"days":{
				digits:"只能是整数！"	 
		}}
	});
}
	var ids="";
	var teachName= "已选择的审批人：";
	var tname="";
	var teacherid='${teacherid}';
	function selectedHandler(data) {
		 
		$.each(data.ids, function(index, value) {
			if(ids.indexOf(value) == -1) {
				
				if(  teacherid!=value){
					ids=ids + value + ",";
					teachName = teachName + data.names[index] + ",";
					tname=tname+ data.names[index] + ",";	
					$.success("审批人选择成功"); 
				}else{
					$.error("审批人不能为自己！"); 	
				}
				
			}
		});
		 $("#teachName").html(teachName);
		 $("#ids").val(ids);
		 $("#tname").val(tname);
		
	}
	
 
	
	//保存或更新修改
	function saveOrUpdate() {
	 
		 
		if (checker.form()) {	 
			var loader = new loadLayer();
			

			var $requestData = formData2JSONObj("#leave_form");

		 
			var url = "${ctp}/office/leave/creator";
			var d=$("#days").val();
			$requestData.day=d;
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if ("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if ("success" === data.info) {
						if (parent.core_iframe != null) {
							parent.core_iframe.window.location.reload();
						} else {
							parent.window.location.reload();
						}
						$.closeWindow();
					} else {
						$.error("操作失败");
					}
				} else {
					$.error("操作失败");
				}
				loader.close();
			});
		}
	}
</script>


</html>