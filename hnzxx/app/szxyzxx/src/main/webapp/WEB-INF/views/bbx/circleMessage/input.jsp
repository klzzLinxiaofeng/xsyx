<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/bbx/bbx.css" rel="stylesheet">
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

.btn{
    font-weight: bold;
}
.btn-white{
    background: #fff;color: #666;border:0px;
}

</style>
</head>



<body style="background-color:#f3f3f3 !important; overflow-x:hidden;">
<div class="row-fluid">
        <div class="span12">
            <div class="content-widgets" style="margin-bottom:0">
                <div class="widget-container" style="padding: 0 0 40px;">
                <form class="form-horizontal tan_form" id="circleMessage_form" action="javascript:void(0);">
                <input type="hidden" id="id" name="id" value="${circleMessage.id}"/>
                <input type="hidden" id="circleId" name="circleId" value="${!empty circleId?circleId:circleMessage.circleId}"/>
                <input type="hidden" id="readState" name="readState" value="01"/>
                <input type="hidden" id="allowComment" name="allowComment" value="0"/>
                <input type="hidden" id="entityId" name="entityId"/>
                <input type="hidden" id="thumbId" name="thumbId"/>
                <input type="hidden" id="readRoleCode" name="readRoleCode" value="${circleMessage.readRoleCode}"/>
                <input type="hidden" id="likes" name="likes" value="${circleMessage.likes}"/>
	
<div class="trend">
    <div class="edit">
        <textarea id="content" name="content" placeholder="请说说班级趣闻吧">${circleMessage.content}</textarea>
        <ul class="zpzs-box" id="zpzs_box" style="padding-top: 10px; padding-left: 20px;">
        <c:forEach items="${thumbs}" var="thumb">
        <li id="${thumb.uuid}" thumbId="${thumb.thumbUuid}"><div class="img"><img src="${thumb.url}"/></div><a href="#" class="tp" onclick="delDiv(this)" style="display:none;"><img src="${pageContext.request.contextPath}/res/css/bbx/images/x.png" class="ww"></a></li>
        </c:forEach>
        	
            <%-- <li>
            <div class="img"><img src="${pageContext.request.contextPath}/res/css/bbx/images/print.jpg" /></div>
            <a href="#" class="tp"  onclick="delDiv(this)" style="display:none;" id="sc">
            <img src="${pageContext.request.contextPath}/res/css/bbx/images/x.png" class="ww">
            </a>
            </li> --%>
            
</ul>
        <%-- <div style="margin-left:20px;"><a href="#" class="tianjia"><img src="${pageContext.request.contextPath}/res/css/bbx/images/add.jpg"> </a></div> --%>
    </div>
    <div class="clear"></div>
</div>


							<div style="padding-left: 25px;" class="edit">
										<div id="uploads"></div>
									</div>
						<div class="form-actions tan_bottom_1">
								<a href="javascript:void(0)" onclick="saveOrUpdate();" class="yellow">发布</a>
								<a href="javascript:void(0)" onclick="$.closeWindow();">取消</a>
						</div>
						</form>

                </div>
            </div>


                    <%-- <div class="form-actions tan_bottom_1">
                                <a href="javascript:void(0)" class="yellow">发布</a>
                                <a href="javascript:void(0)">取消</a>
                        </div> --%>
                </div>
            </div>
</body>
<script type="text/javascript">
	var checker;
	$(function() {
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#circleMessage_form").validate({
			errorClass : "myerror",
			rules : {
				content : {required:true, maxlength:120/* , max:'20', min:'1' */}
			},
			messages : {
				
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			// 组拼文件id
			var entityId = [];
			//var thumbId = [];
			$('.zpzs-box li').each(function(){
				entityId.push(this.id);
				//thumbId.push($(this).attr('thumbId'));
			});
			$('#entityId').val(entityId.join(','));
			//$('#thumbId').val(thumbId.join(','));
			
			var $requestData = $("#circleMessage_form").serializeArray();
			var url = "${ctp}/bbx/circleMessage/creator";
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						if(parent.core_iframe != null) {
 							parent.core_iframe.window.switchTeam();
 						} else {
 							parent.window.switchTeam();
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
		}
	}
	
	$(function(){
	    $(".zpzs-box li").hover(function(){
	        $(this).find(".tp").show();
	    },function(){
	        $(".zpzs-box li .tp").hide();
	    })
	});

	function delDiv(a){
	   $(a).closest('li').remove();
	   var divLen = $('#zpzs_box').find('li').length;
	    var sunLen = 9;
	    if(divLen>=sunLen){
	    	$('#uploads').hide();
	    }else{
	    	$('#uploads').show();
	    }
	  }


	  function initUploader() {
          var divLen = $('#zpzs_box').find('li').length;
          var sunLen = 9;
          if(divLen>=sunLen){
              $('#uploads').hide();
          }else{

              var uploadify_onSelectError = function(file, errorCode, errorMsg) {
                  var msgText = "上传失败\n";
                  switch (errorCode) {
                      case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
                          //this.queueData.errorMsg = "每次最多上传 " + this.settings.queueSizeLimit + "个文件";
                          msgText += "每次最多上传 " + this.settings.queueSizeLimit + "个文件";
                          break;
                      case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
                          msgText += "文件大小超过限制( " + this.settings.fileSizeLimit + " )";
                          break;
                      case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
                          msgText += "文件大小为0";
                          break;
                      case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
                          msgText += "文件格式不正确，仅限 " + this.settings.fileTypeExts;
                          break;
                      default:
                          msgText += "错误代码：" + errorCode + "\n" + errorMsg;
                  }
                  alert(msgText);
                  $("#imgUpload").append("<p><font color='green'>上传失败："+msgText+"</font></p>")
              };

              var uploadify_onSelect = function(){
                  //$('#confirmUpload').show();
              };

              //上传成功回调方法
              var uploadify_onUploadSuccess = function(file, data, response) {
                  var imageStr = '';
                  data = eval("("+data+")");
                  // 显示已上传的图片
                  if(data != null){
                      imageStr += '<li id="'+data.uuid+'"><div class="img"><img src="'+data.url+'"/></div><a href="#" class="tp" onclick="delDiv(this)" style="display:none;"><img src="${pageContext.request.contextPath}/res/css/bbx/images/x.png" class="ww"></a></li>';
                      // 给新建的li绑定事件.
                      $(".zpzs-box").append(imageStr);
                      $(".zpzs-box li").hover(function(){
                          $(this).find(".tp").show();
                      },function(){
                          $(".zpzs-box li .tp").hide();
                      })

                  }
                  var divLen = $('#zpzs_box').find('li').length;
                  var sunLen = 9;
                  if(divLen>=sunLen){
                      $('#uploads').hide();
                      $('#uploads-queue').html('')
                  }else{
                      $('#uploads').show();
                  }
              };

              var uploadify_config = {
                  'auto'  : true,
                  'multi' : true,
                  'swf'   : '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
                  'uploader' : '${pageContext.request.contextPath}/uploader/common',
                  //'script' : '/admin/familyschoolOfficeDocument/upload',
                  'hideButton':true,
                  'wmode' : 'transparent',
                  'width' : '80' ,
                  'height' : '32',
                  'buttonCursor':'hand',
                  //'buttonClass':'some-class',
                  'removeTimeout' : 0,
                  'buttonText' : '添加图片',
                  //'buttonImage' : '${pageContext.request.contextPath}/res/css/bbx/images/add.jpg',
                  'hideButton' : 'true',
                  'fileTypeExts':'*.png;*.jpg;*.jpeg;*.gif;*.bmp',
                  //'buttonImage':'${ctxStatic}/images/btn.png',
                  'queueSizeLimit': '9',
                  'fileSizeLimit' : '10MB',
                  'formData' : {jsessionId:"${pageContext.session.id}"},  //由于FLASH的BUG导致在FF中上传时获取不到SESSION，可以使用formData来传值
                  'overrideEvents' : [ 'onDialogClose', 'onUploadSuccess', 'onUploadError', 'onSelectError' ],
                  'onSelect' : uploadify_onSelect,
                  'onSelectError' : uploadify_onSelectError,
                  //'onUploadError' : uploadify_onUploadError,
                  'onUploadSuccess' : uploadify_onUploadSuccess,
                  'onDialogClose'  : function(queueData) {
                  },
                  onCancel:function(file){
                  },
                  onClearQueue:function(){
                  },
                  onQueueComplete:function(queueData){
                  }
              };

              //调用插件上传方法
              $("#uploads").click(function(){
              })
              $("#uploads").uploadify(uploadify_config);
              //---------------------- 以上是uploadify上传插件代码----------------------

              $('#uploads').show();
          }
      }
	  
	  $(function(){
          initUploader();
	  })
</script>
</html>


