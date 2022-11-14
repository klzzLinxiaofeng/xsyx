<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<!-- 班班星样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/bbx/bbx.css">
</head>
<body>

<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:0px 0 0;">
					<form class="form-horizontal padding15" id="praise_form" style="padding:5px 0 0 15px;">
					<input type="hidden" id="id" name="id" value="${praise.id}"/>
					<input type="hidden" id="receiverId" name="receiverId" />
					<input type="hidden" id="receiverName" name="receiverName" />
					<input type="hidden" id="teamId" name="teamId" value="${empty teamId?praise.teamId:teamId}">
						<div class="control-group" style="margin-bottom:10px;">
							<dl class="biao-add-head span12 left" style="width:440px;height:267px;background-color:#fff;margin-bottom:0">
								<c:forEach items="${students}" var="student">
								<dd>
									<span>
										<c:choose>
											<c:when test="${empty student.headUrl}">
												<img src="${ctp}/res/images/no_pic.jpg"/> 
											</c:when>
											<c:otherwise>
												<img src="${student.headUrl}"/>
											</c:otherwise>
										</c:choose>
									<%-- <img src="${student.headUrl}"/> --%>
									<input name="student" type="checkbox" title="${student.name}" value="${student.userId}"
									<c:if test='${not empty praise.id}'>disabled='disabled'</c:if> />  
									</span>
									<em>${student.name}</em>
								</dd>
								</c:forEach>
							</dl>
							
							<!--勋章-->
							<div class="biao-ban">
								<ul>
								<%-- <c:forEach items="${praiseMedals}" var="praiseMedal">
									<li>
										<div class="ban-star">
											<span class="left bg-radius"><img src="${praiseMedal.imgUrl}"/></span>
											<strong class="left sigin">${praiseMedal.rank}</strong>
											<input name="medalId" type="radio" value="${praiseMedal.id}" class="xuan">
										</div>
									</li>
								</c:forEach> --%>
									<li>
										<div class="ban-star">
											<span class="left bg-radius">
											<img src="${pageContext.request.contextPath}/res/images/ban_starta.png"/>
											</span>
											<strong class="left sigin">德</strong>
											<input name="medalId" type="radio" value="1" class="xuan">
										</div>
									</li>
									<li>
										<div class="ban-star">
											<span class="left bg-radius">
											<img src="${pageContext.request.contextPath}/res/images/ban_starte.png"/>
											</span>
											<strong class="left sigin">智</strong>
											<input name="medalId" type="radio" value="2" class="xuan">
										</div>
									</li>
									<li>
										<div class="ban-star">
											<span class="left bg-radius">
											<img src="${pageContext.request.contextPath}/res/images/ban_startc.png"/>
											</span>
											<strong class="left sigin">美</strong>
											<input name="medalId" type="radio" value="3" class="xuan">
										</div>
									</li>
									<li>
										<div class="ban-star">
											<span class="left bg-radius">
											<img src="${pageContext.request.contextPath}/res/images/ban_startd.png"/>
											</span>
											<strong class="left sigin">体</strong>
											<input name="medalId" type="radio" value="4" class="xuan">
										</div>
									</li>
									<li>
										<div class="ban-star">
											<span class="left bg-radius">
											<img src="${pageContext.request.contextPath}/res/images/ban_startb.png"/>
											</span>
											<strong class="left sigin">劳</strong>
											<input name="medalId" type="radio" value="5" class="xuan">
										</div>
									</li>
								</ul>
							</div>
						</div>
						<div class="control-group" style="margin-bottom:10px;padding-right:15px;">
							<select id="quickContent" style="width:100%;" onchange="changeQuickContent(this);">
							<option value="">请选择</option>
							<option value="1">你在本周表现优异，上课认真听讲，是同学们的好榜样，戒骄戒躁，在以后的日子里继续坚持。</option>
							<option value="2">我非常高兴，最近几个月你的进步非常快。</option>
							<option value="3">不知是什么力量使你改变这么大，从上课爱吵爱闹到学会静静思考，学会暗暗努力，真为你高兴！</option>
							<option value="4">你知道的真多！知识真丰富！我们大家要向你学习！</option>
							<option value="5">学以致用，你的本领真不一般。</option>
							<option value="6">你的表现很出色，老师特别欣赏你！</option>
							<option value="7">你这节课的表现给大家留下了深刻的印象！</option>
							</select>
						</div>
						<div class="control-group" style="padding-right:15px;" id='posc'>
							<textarea type="text" id="content" name="content" class="span5" style="width:100%;height:70px;" placeholder="请表扬一下学生吧">
							${praise.content}
							</textarea>
						</div>
						
					</form>
					<div class="form-actions tan_bottom_1">
                                <a href="javascript:void(0)" onclick="saveOrUpdate();" class="yellow">提交</a>
                                <a href="javascript:void(0)"onclick="$.closeWindow();" >取消</a>
                        </div>
				</div>
			</div>
		</div>
	</div>
	
</body>
<script type="text/javascript">
	var checker;
	$(function() {
		if($('#id').val()!=''){
			// 编辑时显示已选的学生和勋章
			var studentUserIds = '${praise.receiverId}'.split(',');
			for(var i=0; i<studentUserIds.length; i++){
				$('input[name="student"][value="'+studentUserIds[i]+'"]').prop("checked", true);
			}
			$('input[name="medalId"][value="${praise.medalId}"]').prop("checked", true);
		}else{
			$('input[name="medalId"][value="1"]').prop("checked", true);
		}
		$('#content').text($.trim($('#content').text()));
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#praise_form").validate({
			errorClass : "myerror"/* ,
			 rules : {
				"medalId" : {required : true}
			},
			messages : {
			} */
		});
	}
	
	// 改变常用表扬时触发
	function changeQuickContent(obj){
		var value = obj.value;
		if(value!=''){
			$('#posc').html('<textarea type="text" id="content" name="content" class="span5" style="width:100%;height:70px;" placeholder="请表扬一下学生吧">${praise.content}</textarea>')
			var content = $(obj).find('option:selected').text();
			$('#content').html(content);
			obj.value = '';
		}
	}
	
	function strlen(str){
		var len = 0;
	    for (var i=0; i<str.length; i++) { 
	     var c = str.charCodeAt(i); 
	    //单字节加1 
	     if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) { 
	       len++; 
	     } 
	     else { 
	      len+=2; 
	     } 
	    } 
	    return len;
	}
	
	// 保存或更新修改
	function saveOrUpdate() {
		$('#content').text($.trim($('#content').text()));
		if (checker.form()) {
			// 处理被表扬的学生
			var receiverIds = [];
			var receiverNames = [];
			$('input[name="student"]:checked').each(function(){
				receiverIds.push(this.value);
				receiverNames.push(this.title);
			});
			if(receiverIds.length==0){
				$.error("请选择要表扬的学生!");
				return;
			}
			$('#receiverId').val(receiverIds.join(','));
			$('#receiverName').val(receiverNames.join(','));
			// 勋章
			/* if($("[name='medalId']:checked").length==0){
				$.error("请选择一个勋章!");
				return;
			} */
			
			//表扬内容
			if($("#content").val()==''){
				$.error("表扬内容不能为空");
				return;
			}
			var str = $("#content").val();
			if(str.length>200){
				$.error("超过200字");
				return;
			}
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = $("#praise_form").serializeArray();
			var url = "${ctp}/bbx/praise/creator";
			loader.show();
			$.post(url, $requestData, function(data, status) {
				loader.close();
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						if(parent.core_iframe != null) {
 							parent.core_iframe.window.search();
 						} else {
 							parent.window.search();
 						}
						$.closeWindow();
					} else {
						$.error("操作失败");
					}
				}else{
					$.error("操作失败");
				}
			});
		}
	}
	
</script>
</html>