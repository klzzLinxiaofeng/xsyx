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
					<form class="form-horizontal tan_form" id="oanotice_form"
						action="javascript:void(0);">

						  <fieldset>
                      <div class="control-group">
                            <label class="control-label" for="content">会议主题<font style="color:red">*</font>:</label>
                            <div class="controls">
                                         
                              <input class="input-medium required" type="text" name="meetingName"
									placeholder="会议名称"   style="height:30px;line-height:30px;width:180px;">  
                                
                                
                            </div>
                        </div>
                        
                        <div class="control-group">
                            <label class="control-label" for="content">会议级别<font style="color:red">*</font>:</label>
                            <div class="controls">
                                        <div class="textarea">
                                   <input type="radio" name="meetingType" id="optionsRadios1" value="0" checked> 一般   
                                   <input type="radio" name="meetingType" id="optionsRadios" value="1"  > 重要
                                </div>
                                
                            </div>
                        </div>
                        
                        <div class="control-group">
                            <label class="control-label" for="content">主持人<font style="color:red">*</font>:</label>
                            <div class="controls">
                                        <div class="textarea">
                                     <input class="input-medium required" type="text" name="zhuchi"
									placeholder="主持人"   style="height:30px;line-height:30px;width:130px;">  
                               
                                </div>
                                
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="filePassword">会议时间<font style="color:red">*</font>:</label>
                            <div class="controls">
                            <input type="text" id="d4331" name="starttime" 
                            onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="span5 required"  style="width:160px;" />
                            
                            至<input type="text" id="d4332" name="endtime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="span5 required" style="width:160px;" />
                   
                            </div>
                        </div>
                        
                         <div class="control-group">
                            <label class="control-label" for="content">会议地点<font style="color:red">*</font>:</label>
                            <div class="controls">
                                        <div class="textarea">
                                     <input class="input-medium required" type="text" name="address"
									placeholder="会议地点"    style="height:30px;line-height:30px;width:280px;" >  
                               
                                </div>
                                
                            </div>
                        </div>
                        
                             <div class="control-group">
                            <label class="control-label" for="content">会议描述<font style="color:red">*</font>:</label>
                            <div class="controls">
                                        <div class="textarea">
                                     
                               
                               <textarea id="content" name="meetingContent" rows="5"  
										class="span8 required "  placeholder="备注"  > </textarea>
                                </div>
                                
                            </div>
                        </div>
                        
                        <div class="control-group">
							<label class="control-label"> 选择参会人<font style="color:red">*</font>： </label>
							<div class="controls">
								<div class="textarea">
									<input type="button" id="member_selector" value="选择参会人">
                                  <span id="teachName"> 
                                  <c:if test="${!empty  noticeUser}">已 选择参会人</c:if>
                                  <c:forEach items="${noticeUser}" var="entry" varStatus="statu">
                                  ${entry.teachName },
                                    </c:forEach> 
                                     </span>
                                     
                                     <input type="hidden" id="ids" name="ids" class="required">
                                     
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
		return $("#oanotice_form").validate({
			errorClass : "myerror",
			rules : {

			},
			messages : {

			}
		});
	}
	var ids="";
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
		 
			var $requestData = formData2JSONObj("#oanotice_form");

			 
			var url = "${ctp}/office/meeting/creator";
			 
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
	
	var teachName= "已选择参会人：";
	function selectedHandler(data) {
		$.each(data.ids, function(index, value) {
			if(ids.indexOf(value) == -1) {
				ids=ids + value + ",";
				teachName = teachName + data.names[index] + ",";
			}
		});
		 $("#teachName").html(teachName);
		 $("#ids").val(ids);
		$.success("选择参会人成功"); 
	}
</script>



</html>