<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <%@ include file="/views/embedded/common.jsp" %>
    <style>
        .form-horizontal .control-label {
            width: 60px
        }

        .form-horizontal .controls {
            margin-left: 80px;
        }

        .form-horizontal.tan_form {
            padding-bottom: 100px;
        }

        .form-horizontal .seach {
            float: left;
        }

        .chzn-container {
            float: left
        }

        .close_1 {
            color: #C5C5C5;
            position: absolute;
            font-size: 12px;
            font-weight: bold;
            cursor: pointer;
            top: 5px;
            right: 20px;
        }

        .cancelBubble {
            border: 1px solid #DFDFDF;
            padding: 0 20px;
            border-radius: 15px;
            margin-right: 20px;
            margin-bottom: 10px;
            display: inline-block;
            width: 138px;
            height: 24px;
            line-height: 24px;
            position: relative;
        }

        .cancelBubble span {
            display: block;
            width: 125px;
            text-overflow: ellipsis;
            white-space: nowrap;
            overflow: hidden;
            display: block;
            word-wrap: normal;
        }

        #clear_button {
            position: relative;
            top: -10px;
        }

        .table th, .table td {
            padding-left: 15px;
        }
    </style>
</head>
<body style="background-color: #ffffff !important">
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 10px 0 0;">
                <form class="form-horizontal tan_form" id="role_form"
                      action="javascript:void(0);">
					<div class="control-group"
						 style="border-bottom: 1px solid #999; padding-bottom: 10px; margin-bottom: 10px;">
						<div id="batch_data" class="controls">
						</div>
					</div>
                    <div class="clear"></div>
                    <div class="select_view" id="teacher_list"></div>
                    <div class="form-actions tan_bottom" style="">
						<button class="btn" type="button" onclick="batchAdd();">确认</button>
						<button class="btn" type="button" onclick="$.closeWindow();">取消</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>

<script type="text/javascript">

    var isTop = "${param.isTop}";
    $(function () {
        initCheckBtn();
        search();
    });

    function batchClear() {
        $("#batch_data").empty();
    }


    function batchAdd() {

		var ids = new Array();
		var names = new Array();

		$('#ryxx_trs input[name="ids"]:checked').each(function(index, value) {
			var value = $(this).val().split(":");
			ids.push(value[0]);
			names.push(value[1]);
		});

        var data = {
            "ids": ids,
            "names": names,
            "windowName": window.name,
            "btnId": "${param.id}",
            "frameIndex": parent.layer.getFrameIndex(window.name),
            "targetWindowName": "${param.targetWN}"
        };
        var isSuc;
        if ("true" === isTop) {
            var $ifm = $("#core_iframe", window.parent.document);
            if ($ifm.length > 0) {
                var ifm = $ifm.get(0);
                isSuc = ifm.contentWindow.selectedTimeHandler(data);
            }
        } else {
            isSuc = window.parent.selectedTimeHandler(data);
        }
        if (isSuc) {
            $.success("设置成功");
        }
    }

    function search() {
        var requestData = {
            "type": "0"
        };
        var excludeSelf = "${excludeSelf}";
        var enableMultiCampus = "${enableMultiCampus}";
        requestData.enableMultiCampus = enableMultiCampus;
        requestData.excludeSelf = excludeSelf;
        requestData.name = $("#name").val();
        var subjectCode = $("#subject").val();
        var deptId = $("#dept_seleted_id").attr("data-id");
        var schoolId = $("#school_selected").val();
        if (subjectCode != null && subjectCode != ""
            && subjectCode != "undefinded") {
            requestData.subjectCode = subjectCode;
        }

        if (deptId != null && deptId != "" && deptId != "undefinded") {
            requestData.deptId = deptId;
        }

        if (schoolId != null && schoolId != "" && schoolId != "undefinded") {
            requestData.schoolId = schoolId;
        }

        requestData.enableBatch = "${param.enableBatch}";
        requestData.usePage = true;
        $("#teacher_list").load("${ctp}/teach/teacher/time", requestData,
            function () {
            });
    }

    function initCheckBtn() {

        $(".select_view").on("click", "#checkAll", function () {
            if (this.checked) {
                $("#ryxx_trs tr td").find("input:checkbox:checked").click();
            }
            $("#teacher_list #ryxx_trs tr td input").click();
            $("#ryxx_trs tr td input:checkbox[name='ids']").prop("checked", this.checked);
            if (!this.checked) {
                $("#teacher_list #ryxx_trs tr td").click();
                $("#ryxx_trs tr td input:checkbox[name='ids']").prop("checked", this.checked);
                $(".cancelBubble").remove();
            }
        });
    }
</script>
</html>