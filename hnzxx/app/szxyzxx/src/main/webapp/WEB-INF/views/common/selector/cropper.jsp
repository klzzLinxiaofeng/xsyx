<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/views/embedded/plugin/cropper_js.jsp"%>
<%@ include file="/views/embedded/common.jsp"%>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title></title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/cropper/assets/css/font-awesome.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/cropper/assets/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/cropper/dist/cropper.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/cropper/assets/css/main.css">
</head>
<body>
  <script type="text/javascript">
	function saveFileUpload(){
			var fileUpload = $("#inputImage").val() + "";
			if(fileUpload==""){
		      	$.alert("请先上传图片！");
		      	return;
		      }
			var url = "${pageContext.request.contextPath}/uploader/cropAvatarUpload";
			var loader = new loadLayer();
			var $requestData = {};
			var extension = fileUpload.split(".")[1];
			$requestData.extension = extension;
			$requestData.image = $("#image_data").val();
			$requestData.jsessionId = $("#jsessionId").val();
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					data = eval("(" + data + ")");
					if("" !== data.id) {
						if(parent.core_iframe != null) {
 							parent.core_iframe.window.$avatarCropCallback(data);
 						} else {
	 						parent.window.$avatarCropCallback(data);
 						}
						$.closeWindow();
					} else if("noLogin" === data.info){
						$.error("请先登录！");
					} else {
						$.error("操作失败");
					}
				}else{
					$.error("操作失败");
				}
				loader.close();
			});
	}
	$(function(){
		var jsessionId = $("#jsessionId").val() + "";
		if(jsessionId == ""){
			$.alert("请先登录！");
		}
	});
  </script>
  <!-- Content -->
  <div class="container">
    <div class="row">
      <div class="col-md-9">
        <!-- <h3 class="page-header">Demo:</h3> -->
        <div class="img-container">
          <img src="${pageContext.request.contextPath}/res/plugin/cropper/assets/img/picture.jpg" alt="Picture">
        </div>
      </div>
      <div class="col-md-3">
        <!-- <h3 class="page-header">Preview:</h3> -->
        <div class="docs-preview clearfix">
          <div class="img-preview preview-lg"></div>
          <div class="img-preview preview-md"></div>
          <div class="img-preview preview-sm"></div>
          <div class="img-preview preview-xs"></div>
        </div>

        <!-- <h3 class="page-header">Data:</h3> -->
        <div class="docs-data" style="display: none;">
          <div class="input-group input-group-sm">
            <label class="input-group-addon" for="dataX">X</label>
            <input type="text" class="form-control" id="dataX" placeholder="x">
            <span class="input-group-addon">px</span>
          </div>
          <div class="input-group input-group-sm">
            <label class="input-group-addon" for="dataY">Y</label>
            <input type="text" class="form-control" id="dataY" placeholder="y">
            <span class="input-group-addon">px</span>
          </div>
          <div class="input-group input-group-sm">
            <label class="input-group-addon" for="dataWidth">Width</label>
            <input type="text" class="form-control" id="dataWidth" placeholder="width">
            <span class="input-group-addon">px</span>
          </div>
          <div class="input-group input-group-sm">
            <label class="input-group-addon" for="dataHeight">Height</label>
            <input type="text" class="form-control" id="dataHeight" placeholder="height">
            <span class="input-group-addon">px</span>
          </div>
          <div class="input-group input-group-sm">
            <label class="input-group-addon" for="dataRotate">Rotate</label>
            <input type="text" class="form-control" id="dataRotate" placeholder="rotate">
            <span class="input-group-addon">deg</span>
          </div>
          <div class="input-group input-group-sm">
            <label class="input-group-addon" for="dataScaleX">ScaleX</label>
            <input type="text" class="form-control" id="dataScaleX" placeholder="scaleX">
          </div>
          <div class="input-group input-group-sm">
            <label class="input-group-addon" for="dataScaleY">ScaleY</label>
            <input type="text" class="form-control" id="dataScaleY" placeholder="scaleY">
          </div>
        </div>
      </div>
    </div>
    <div class="row" id="actions">
      <div class="col-md-9 docs-buttons">
        <!-- <h3 class="page-header">Toolbar:</h3> -->
        <div class="btn-group">
          <button type="button" style="display: none" class="btn btn-primary" data-method="setDragMode" data-option="move" title="Move">
            <span class="docs-tooltip" data-toggle="tooltip" title="cropper.setDragMode(&quot;move&quot;)">
              <span class="fa fa-arrows"></span>
            </span>
          </button>
          <button type="button" style="display: none" class="btn btn-primary" data-method="setDragMode" data-option="crop" title="Crop">
            <span class="docs-tooltip" data-toggle="tooltip" title="cropper.setDragMode(&quot;crop&quot;)">
              <span class="fa fa-crop"></span>
            </span>
          </button>
        </div>

        <div class="btn-group">
          <button type="button" class="btn btn-primary" data-method="zoom" data-option="0.1" title="Zoom In">
            <span class="docs-tooltip" data-toggle="tooltip" title="放大">
              <span class="fa fa-search-plus"></span>
            </span>
          </button>
          <button type="button" class="btn btn-primary" data-method="zoom" data-option="-0.1" title="Zoom Out">
            <span class="docs-tooltip" data-toggle="tooltip" title="缩小">
              <span class="fa fa-search-minus"></span>
            </span>
          </button>
        </div>

        <div class="btn-group">
          <button type="button" class="btn btn-primary" data-method="move" data-option="-10" data-second-option="0" title="Move Left">
            <span class="docs-tooltip" data-toggle="tooltip" title="左移">
              <span class="fa fa-arrow-left"></span>
            </span>
          </button>
          <button type="button" class="btn btn-primary" data-method="move" data-option="10" data-second-option="0" title="Move Right">
            <span class="docs-tooltip" data-toggle="tooltip" title="右移">
              <span class="fa fa-arrow-right"></span>
            </span>
          </button>
          <button type="button" class="btn btn-primary" data-method="move" data-option="0" data-second-option="-10" title="Move Up">
            <span class="docs-tooltip" data-toggle="tooltip" title="上移">
              <span class="fa fa-arrow-up"></span>
            </span>
          </button>
          <button type="button" class="btn btn-primary" data-method="move" data-option="0" data-second-option="10" title="Move Down">
            <span class="docs-tooltip" data-toggle="tooltip" title="下移">
              <span class="fa fa-arrow-down"></span>
            </span>
          </button>
        </div>

        <div class="btn-group">
          <button type="button" class="btn btn-primary" data-method="rotate" data-option="-45" title="Rotate Left">
            <span class="docs-tooltip" data-toggle="tooltip" title="-45°旋转">
              <span class="fa fa-rotate-left"></span>
            </span>
          </button>
          <button type="button" class="btn btn-primary" data-method="rotate" data-option="45" title="Rotate Right">
            <span class="docs-tooltip" data-toggle="tooltip" title="45°旋转">
              <span class="fa fa-rotate-right"></span>
            </span>
          </button>
        </div>

        <div class="btn-group">
          <button type="button" class="btn btn-primary" data-flip="horizontal" data-method="scaleX" data-option="-1" title="Flip Horizontal">
            <span class="docs-tooltip" data-toggle="tooltip" title="横     向">
              <span class="fa fa-arrows-h"></span>
            </span>
          </button>
          <button type="button" class="btn btn-primary" data-flip="vertical" data-method="scaleY" data-option="-1" title="Flip Vertical">
            <span class="docs-tooltip" data-toggle="tooltip" title="竖     向">
              <span class="fa fa-arrows-v"></span>
            </span>
          </button>
        </div>

        <div class="btn-group">
          <button type="button" style="display: none;" class="btn btn-primary" data-method="crop" title="Crop">
            <span class="docs-tooltip"  data-toggle="tooltip" title="cropper.crop()">
              <span class="fa fa-check"></span>
            </span>
          </button>
          <button type="button" style="display: none;" class="btn btn-primary" data-method="clear" title="Clear">
            <span class="docs-tooltip" data-toggle="tooltip" title="cropper.clear()">
              <span class="fa fa-remove"></span>
            </span>
          </button>
        </div>

        <div class="btn-group">
          <button type="button" style="display: none;" class="btn btn-primary" data-method="disable" title="Disable">
            <span class="docs-tooltip" data-toggle="tooltip" title="cropper.disable()">
              <span class="fa fa-lock"></span>
            </span>
          </button>
          <button type="button" style="display: none;" class="btn btn-primary" data-method="enable" title="Enable">
            <span class="docs-tooltip" data-toggle="tooltip" title="cropper.enable()">
              <span class="fa fa-unlock"></span>
            </span>
          </button>
        </div>
		
        <input class="avatar-src" id="jsessionId" name="jsessionId" type="hidden" value="<%=request.getSession().getId()%>">
        <input id="image_data" name="image" type="hidden">
        <div class="btn-group">
          <button type="button" class="btn btn-primary" data-method="reset" title="Reset">
            <span class="docs-tooltip" data-toggle="tooltip" title="重置">
              <span class="fa fa-refresh"></span>
            </span>
          </button>
          <label class="btn btn-primary btn-upload" for="inputImage" title="本地上传">
            <input type="file" class="sr-only" id="inputImage" name="file" accept="image/*">
            <span class="docs-tooltip" data-toggle="tooltip" title="本地上传">
              <span class="fa fa-upload"></span>
            </span>
          </label>
          <button type="button" style="display: none;" class="btn btn-primary" data-method="destroy" title="Destroy">
            <span class="docs-tooltip" data-toggle="tooltip" title="cropper.destroy()">
              <span class="fa fa-power-off"></span>
            </span>
          </button>
        </div>
        <div class="btn-group">
            <button class="btn btn-primary" onclick="saveFileUpload();">保存</button>
        </div>

        <div class="btn-group btn-group-crop">
          <button type="button" style="display: none;" class="btn btn-primary" data-method="getCroppedCanvas">
            <span class="docs-tooltip" data-toggle="tooltip" title="cropper.getCroppedCanvas()">
              Get Cropped Canvas
            </span>
          </button>
          <button type="button" style="display: none;" class="btn btn-primary" data-method="getCroppedCanvas" data-option="{ &quot;width&quot;: 160, &quot;height&quot;: 90 }">
            <span class="docs-tooltip" data-toggle="tooltip" title="cropper.getCroppedCanvas({ width: 160, height: 90 })">
              160&times;90
            </span>
          </button>
          <button type="button" style="display: none;" class="btn btn-primary" data-method="getCroppedCanvas" data-option="{ &quot;width&quot;: 320, &quot;height&quot;: 180 }">
            <span class="docs-tooltip" data-toggle="tooltip" title="cropper.getCroppedCanvas({ width: 320, height: 180 })">
              320&times;180
            </span>
          </button>
        </div>

        <!-- Show the cropped image in modal -->
        <div class="modal fade docs-cropped" id="getCroppedCanvasModal" role="dialog" aria-hidden="true" aria-labelledby="getCroppedCanvasTitle" tabindex="-1">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" style="display: none;" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="getCroppedCanvasTitle">Cropped</h4>
              </div>
              <div class="modal-body"></div>
              <div class="modal-footer">
                <button type="button" style="display: none;" class="btn btn-default" data-dismiss="modal">Close</button>
                <a class="btn btn-primary" id="download" href="javascript:void(0);" download="cropped.png">Download</a>
              </div>
            </div>
          </div>
        </div><!-- /.modal -->

        <button type="button" style="display: none;" class="btn btn-primary" data-method="getData" data-option data-target="#putData">
          <span class="docs-tooltip" data-toggle="tooltip" title="cropper.getData()">
            Get Data
          </span>
        </button>
        <button type="button" style="display: none;" class="btn btn-primary" data-method="setData" data-target="#putData">
          <span class="docs-tooltip" data-toggle="tooltip" title="cropper.setData(data)">
            Set Data
          </span>
        </button>
        <button type="button" style="display: none;" class="btn btn-primary" data-method="getContainerData" data-option data-target="#putData">
          <span class="docs-tooltip" data-toggle="tooltip" title="cropper.getContainerData()">
            Get Container Data
          </span>
        </button>
        <button type="button" style="display: none;" class="btn btn-primary" data-method="getImageData" data-option data-target="#putData">
          <span class="docs-tooltip" data-toggle="tooltip" title="cropper.getImageData()">
            Get Image Data
          </span>
        </button>
        <button type="button" style="display: none;" class="btn btn-primary" data-method="getCanvasData" data-option data-target="#putData">
          <span class="docs-tooltip" data-toggle="tooltip" title="cropper.getCanvasData()">
            Get Canvas Data
          </span>
        </button>
        <button type="button" style="display: none;" class="btn btn-primary" data-method="setCanvasData" data-target="#putData">
          <span class="docs-tooltip" data-toggle="tooltip" title="cropper.setCanvasData(data)">
            Set Canvas Data
          </span>
        </button>
        <button type="button" style="display: none;" class="btn btn-primary" data-method="getCropBoxData" data-option data-target="#putData">
          <span class="docs-tooltip" data-toggle="tooltip" title="cropper.getCropBoxData()">
            Get Crop Box Data
          </span>
        </button>
        <button type="button" style="display: none;" class="btn btn-primary" data-method="setCropBoxData" data-target="#putData">
          <span class="docs-tooltip" data-toggle="tooltip" title="cropper.setCropBoxData(data)">
            Set Crop Box Data
          </span>
        </button>
        <button type="button" style="display: none;" class="btn btn-primary" data-method="moveTo" data-option="0">
          <span class="docs-tooltip" data-toggle="tooltip" title="cropper.moveTo(0)">
            0,0
          </span>
        </button>
        <button type="button" style="display: none;" class="btn btn-primary" data-method="zoomTo" data-option="1">
          <span class="docs-tooltip" data-toggle="tooltip" title="cropper.zoomTo(1)">
            100%
          </span>
        </button>
        <button type="button" style="display: none;" class="btn btn-primary" data-method="rotateTo" data-option="180">
          <span class="docs-tooltip" data-toggle="tooltip" title="cropper.rotateTo(180)">
            180°
          </span>
        </button>

      </div><!-- /.docs-buttons -->

      <div class="col-md-3 docs-toggles">
        <!-- <h3 class="page-header">Toggles:</h3> -->
        <div class="btn-group btn-group-justified" data-toggle="buttons">
          <label class="btn btn-primary active">
            <input type="radio" class="sr-only" id="aspectRatio0" name="aspectRatio" value="1.7777777777777777" checked>
            <span class="docs-tooltip" data-toggle="tooltip" title="16/9">
              16:9
            </span>
          </label>
          <label class="btn btn-primary">
            <input type="radio" class="sr-only" id="aspectRatio1" name="aspectRatio" value="1.3333333333333333">
            <span class="docs-tooltip" data-toggle="tooltip" title="4 / 3">
              4:3
            </span>
          </label>
          <label class="btn btn-primary">
            <input type="radio" class="sr-only" id="aspectRatio2" name="aspectRatio" value="1">
            <span class="docs-tooltip" data-toggle="tooltip" title="1 / 1">
              1:1
            </span>
          </label>
          <label class="btn btn-primary">
            <input type="radio" class="sr-only" id="aspectRatio3" name="aspectRatio" value="0.6666666666666666">
            <span class="docs-tooltip" data-toggle="tooltip" title="2 / 3">
              2:3
            </span>
          </label>
          <label class="btn btn-primary">
            <input type="radio" class="sr-only" id="aspectRatio4" name="aspectRatio" value="NaN">
            <span class="docs-tooltip" data-toggle="tooltip" title="NaN">
              Free
            </span>
          </label>
        </div>

        <div class="btn-group btn-group-justified" data-toggle="buttons">
          <label style="display: none;" class="btn btn-primary active">
            <input type="radio" style="display: none;" class="sr-only" id="viewMode0" name="viewMode" value="0" checked>
            <span class="docs-tooltip" style="display: none;" data-toggle="tooltip" title="View Mode 0">
              VM0
            </span>
          </label>
          <label style="display: none;" class="btn btn-primary">
            <input type="radio" style="display: none;" class="sr-only" id="viewMode1" name="viewMode" value="1">
            <span class="docs-tooltip" style="display: none;" data-toggle="tooltip" title="View Mode 1">
              VM1
            </span>
          </label>
          <label style="display: none;" class="btn btn-primary">
            <input type="radio" style="display: none;" class="sr-only" id="viewMode2" name="viewMode" value="2">
            <span class="docs-tooltip" style="display: none;" data-toggle="tooltip" title="View Mode 2">
              VM2
            </span>
          </label>
          <label style="display: none;" class="btn btn-primary">
            <input type="radio" style="display: none;" class="sr-only" id="viewMode3" name="viewMode" value="3">
            <span class="docs-tooltip" style="display: none;"  data-toggle="tooltip" title="View Mode 3">
              VM3
            </span>
          </label>
        </div>

        <div class="dropdown dropup docs-options">
          <button type="button" style="display: none;" class="btn btn-primary btn-block dropdown-toggle" id="toggleOptions" data-toggle="dropdown" aria-expanded="true">
            Toggle Options
            <span class="caret"></span>
          </button>
          <ul class="dropdown-menu" role="menu" aria-labelledby="toggleOptions">
            <li role="presentation">
              <label class="checkbox-inline">
                <input type="checkbox" name="responsive" checked>
                responsive
              </label>
            </li>
            <li role="presentation">
              <label class="checkbox-inline">
                <input type="checkbox" name="checkCrossOrigin" checked>
                checkCrossOrigin
              </label>
            </li>

            <li role="presentation">
              <label class="checkbox-inline">
                <input type="checkbox" name="modal" checked>
                modal
              </label>
            </li>
            <li role="presentation">
              <label class="checkbox-inline">
                <input type="checkbox" name="guides" checked>
                guides
              </label>
            </li>
            <li role="presentation">
              <label class="checkbox-inline">
                <input type="checkbox" name="center" checked>
                center
              </label>
            </li>
            <li role="presentation">
              <label class="checkbox-inline">
                <input type="checkbox" name="highlight" checked>
                highlight
              </label>
            </li>
            <li role="presentation">
              <label class="checkbox-inline">
                <input type="checkbox" name="background" checked>
                background
              </label>
            </li>

            <li role="presentation">
              <label class="checkbox-inline">
                <input type="checkbox" name="autoCrop" checked>
                autoCrop
              </label>
            </li>
            <li role="presentation">
              <label class="checkbox-inline">
                <input type="checkbox" name="movable" checked>
                movable
              </label>
            </li>
            <li role="presentation">
              <label class="checkbox-inline">
                <input type="checkbox" name="rotatable" checked>
                rotatable
              </label>
            </li>
            <li role="presentation">
              <label class="checkbox-inline">
                <input type="checkbox" name="scalable" checked>
                scalable
              </label>
            </li>
            <li role="presentation">
              <label class="checkbox-inline">
                <input type="checkbox" name="zoomable" checked>
                zoomable
              </label>
            </li>
            <li role="presentation">
              <label class="checkbox-inline">
                <input type="checkbox" name="zoomOnTouch" checked>
                zoomOnTouch
              </label>
            </li>
            <li role="presentation">
              <label class="checkbox-inline">
                <input type="checkbox" name="zoomOnWheel" checked>
                zoomOnWheel
              </label>
            </li>
            <li role="presentation">
              <label class="checkbox-inline">
                <input type="checkbox" name="cropBoxMovable" checked>
                cropBoxMovable
              </label>
            </li>
            <li role="presentation">
              <label class="checkbox-inline">
                <input type="checkbox" name="cropBoxResizable" checked>
                cropBoxResizable
              </label>
            </li>
            <li role="presentation">
              <label class="checkbox-inline">
                <input type="checkbox" name="toggleDragModeOnDblclick" checked>
                toggleDragModeOnDblclick
              </label>
            </li>
          </ul>
        </div><!-- /.dropdown -->
      </div><!-- /.docs-toggles -->
    </div>
  </div>

  <!-- Footer -->

  <!-- Scripts -->
<%--   <script src="${pageContext.request.contextPath}/res/plugin/cropper/assets/js/jquery.min.js"></script> --%>
<%--   <script src="${pageContext.request.contextPath}/res/plugin/cropper/assets/js/bootstrap.min.js"></script> --%>
  <script src="${pageContext.request.contextPath}/res/plugin/cropper/dist/cropper.js"></script>
  <script src="${pageContext.request.contextPath}/res/plugin/cropper/assets/js/main.js"></script>
</body>
</html>
