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
</head>
<body style="background-color: cdd4d7 !important">
<div class="row-fluid">
	<div class="span12">
		<div class="content-widgets" style="margin-bottom: 0">
			<div class="widget-container" style="padding: 20px 0 0;">
				<form class="form-horizontal tan_form" id="bwsignage_form" action="javascript:void(0);">
					<div class="control-group">
						<label class="control-label">
							id：
						</label>
						<div class="controls">
							<input type="text" id="id" name="id" class="span13" placeholder="" value="${bwsignage.id}">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">
							学校：
						</label>
						<div class="controls">
							<input type="text" id="schoolId" name="schoolId" class="span13" placeholder="" value="${bwsignage.schoolId}">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">
							校内自编号 可空，但不可重复：
						</label>
						<div class="controls">
							<input type="text" id="num" name="num" class="span13" placeholder="" value="${bwsignage.num}">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">
							设备标识名称：
						</label>
						<div class="controls">
							<input type="text" id="name" name="name" class="span13" placeholder="" value="${bwsignage.name}">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">
							对应房间（可空， 当学校有实现房室管理时才有此ID）：
						</label>
						<div class="controls">
							<input type="text" id="roomId" name="roomId" class="span13" placeholder="" value="${bwsignage.roomId}">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">
							对应班级（可空，当班牌所在教室有具体班级时才有此ID）：
						</label>
						<div class="controls">
							<input type="text" id="teamId" name="teamId" class="span13" placeholder="" value="${bwsignage.teamId}">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">
							班牌操作账号名称：
						</label>
						<div class="controls">
							<input type="text" id="account" name="account" class="span13" placeholder="" value="${bwsignage.account}">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">
							工作状态: 0=开启 1=关闭 2=故障：
						</label>
						<div class="controls">
							<input type="text" id="workingState" name="workingState" class="span13" placeholder="" value="${bwsignage.workingState}">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">
							使用状态: 0=正常 1=报废 2=故障：
						</label>
						<div class="controls">
							<input type="text" id="usingState" name="usingState" class="span13" placeholder="" value="${bwsignage.usingState}">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">
							全校性通知和消息 用于全校通知模式、欢迎模式的显示内容：
						</label>
						<div class="controls">
							<input type="text" id="globalMessage" name="globalMessage" class="span13" placeholder="" value="${bwsignage.globalMessage}">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">
							背景文件 file.uuid：
						</label>
						<div class="controls">
							<input type="text" id="backgroundFile" name="backgroundFile" class="span13" placeholder="" value="${bwsignage.backgroundFile}">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">
							背景皮肤模式：
						</label>
						<div class="controls">
							<input type="text" id="backgroundMode" name="backgroundMode" class="span13" placeholder="" value="${bwsignage.backgroundMode}">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">
							记录创建时间：
						</label>
						<div class="controls">
							<input type="text" id="createDate" name="createDate" class="span13" placeholder="" value="${bwsignage.createDate}">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">
							记录修改时间：
						</label>
						<div class="controls">
							<input type="text" id="modifyDate" name="modifyDate" class="span13" placeholder="" value="${bwsignage.modifyDate}">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">
							记录是否删除：
						</label>
						<div class="controls">
							<input type="text" id="isDeleted" name="isDeleted" class="span13" placeholder="" value="${bwsignage.isDeleted}">
						</div>
					</div>
					<div class="form-actions tan_bottom">
						<input type="hidden" id="id" name="id" value="${bwsignage.id}" />
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
        return $("#bwsignage_form").validate({
            errorClass : "myerror",
            rules : {

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
            var $requestData = formData2JSONObj("#bwsignage_form");
            var url = "${ctp}/bbx/bpBwSignage/creator";
            if ("" != $id) {
                $requestData._method = "put";
                url = "${ctp}/bbx/bpBwSignage/" + $id;
            }
            loader.show();
            $.post(url, $requestData, function(data, status) {
                if("success" === status) {
                    $.success('操作成功');
                    data = eval("(" + data + ")");
                    if("success" === data.info) {
                        if(parent.core_iframe != null) {
                            parent.core_iframe.window.location.reload();
                        } else {
                            parent.window.location.reload();
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

</script>
</html>