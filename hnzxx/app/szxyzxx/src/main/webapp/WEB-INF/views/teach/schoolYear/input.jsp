<%@ page language="java"
	import="platform.education.user.contants.UserContants"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>学年管理</title>
<%@ include file="/views/embedded/common.jsp"%>
<style>
.row-fluid .span13 {
	width: 75%;
}

.myerror {
	color: red !important;
	width: 34%;
	display: inline-block;
	padding-left: 10px;
}

.row-fluid .span4 {
	width: 227px;
}
</style>
	<script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal" id="schoolYear_form"
						action="javascript:void(0);">

						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>开始时间</label>
							<div class="controls">
								<input type="text" id="beginDate" name="beginDate" class="span4"
									placeholder="请输入开始时间 如2014-09-01"
									value='<fmt:formatDate pattern="yyyy-MM-dd" value="${schoolYear.beginDate}"></fmt:formatDate>'
									onclick="getBeginYear();"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>结束时间</label>
							<div class="controls">
								<input type="text" id="finishDate" name="finishDate"
									class="span4" placeholder="请输入结束时间 如2015-07-01"
									value='<fmt:formatDate pattern="yyyy-MM-dd" value="${schoolYear.finishDate}"></fmt:formatDate>'
									onFocus="getEndYear();"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>学年名称</label>
							<div class="controls">
								<input type="text" id="name" name="name" class="span4"
									placeholder="请输入学年名称, 如2014-2015学年" value="${schoolYear.name}"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<input type="hidden" id="year" name="year" class="span4"
									placeholder="请输入学年的开始年份, 如2014" value="${schoolYear.year}">
								<input type="hidden" id="beginYear">
								<input type="hidden" id="endYear">
							</div>
						</div>
						
						<div class="form-actions tan_bottom"
							style="padding-left: 0; background-color: #eee; text-align: center">
							<c:if test="${isCK == null || isCk == ''}">
								<input type="hidden" id="id" name="id" value="${schoolYear.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
							</c:if>
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

// 	onFocus="WdatePicker({minDate:'#F{$dp.$D(\'beginDate\')}', maxDate:'#F{$dp.$D(\'beginDate\', {y:1});}', readOnly:true})"
//  onFocus="WdatePicker({minDate:'#F{$dp.$D(\'beginDate\')}', readOnly:true})"
	function setYear() {
		WdatePicker({maxDate:'#F{$dp.$D(\'finishDate\')}', readOnly:true});
// 		WdatePicker({minDate:'#F{$dp.$D(\'finishDate\', {y:-1});}', maxDate:'#F{$dp.$D(\'finishDate\')}', readOnly:true});
		var begin = $("#beginDate").val();
		if (begin != null && begin != "" && begin != undefined) {
			var arr = [];
			arr = begin.split("-");
			$("#year").val(arr[0]);
			setName();
		}
	}
	
	function getBeginYear() {
		WdatePicker({minDate:'#F{$dp.$D(\'finishDate\', {y:-1});}', maxDate:'#F{$dp.$D(\'finishDate\')}', readOnly:true});
		var begin = $("#beginDate").val();  //获得开始时间
		if (begin != null && begin != "" && begin != undefined) {
			var arr = [];
			arr = begin.split("-");
			$("#year").val(arr[0]);
			$("#beginYear").val(arr[0]);
		}
	}

	function getEndYear() {
		var begin = $("#beginDate").val();  //获得开始时间
		if (begin != null && begin != "" && begin != undefined) {
			//2016-5-20 添加判断 如果在编辑的时候 不先设置开始时间  先设置结束时间  本句生效
			if (begin != null && begin != "" && begin != undefined) {
				var arr = [];
				arr = begin.split("-");
				$("#year").val(arr[0]);
				$("#beginYear").val(arr[0]);
			}
			WdatePicker({minDate:'#F{$dp.$D(\'beginDate\')}', maxDate:'#F{$dp.$D(\'beginDate\', {y:1});}', readOnly:true});
		} else {
			$.alert("请先设置开始日期！");
			return;
		}
		var end = $("#finishDate").val();  //获得结束时间
		if (end != null && end != "" && end != undefined) {
			var arr = [];
			arr = end.split("-");
			$("#endYear").val(arr[0]);	
			setName();
		}
	}
	
	function setName() {
		var begin = $("#beginYear").val();
		var end = $("#endYear").val();
// 		var end = parseInt(begin) + 1;
		var name;
		if(begin == end) {
			name = begin + "学年";
		}else if((parseInt(begin) + 1) == end) {
			name = begin + "-" + end + "学年";
		}else {
			
		}
// 		var name = begin + "-" + end + "学年";
		$("#name").val(name);
	}

	function initValidator() {
		return $("#schoolYear_form")
				.validate(
						{
							errorClass : "myerror",
							rules : {
// 								"year" : {
// 									required : true,
// 									accCheck : true,
// 									remote : {
// 										async : false,
// 										type : "GET",
// 										url : "${pageContext.request.contextPath}/teach/schoolYear/checker",
// 										data : {
// 											'dxlx' : 'year',
// 											'year' : function() {
// 												return $("#year").val();
// 											},
// 											'id' : function() {
// 												return $("#id").val();
// 											}
// 										}
// 									}
// 								},
								"name" : {
									required : true,
									maxlength : 30
								},
								"beginDate" : {
									required : true,
								},
								"finishDate" : {
									required : true,
								}
							},
							messages : {
// 								"year" : {
// 									remote : "该学年值已存在"
// 								}
							}
						});
	}

	function judgeTimes() {
	    var yearList = ${yearList};
        var beginDate = $("#beginDate").val();
        beginDate = new Date(beginDate + " 00:00:00").getTime();
        var finishDate = $("#finishDate").val();
        finishDate = new Date(finishDate +" 00:00:00").getTime();
        var pass = false;
        var lastEnd = 0;
        if (yearList.length > 0) {
            for (var i=0; i<yearList.length; i++) {
                var begin = yearList[i].beginDate.time;
                var end = yearList[i].finishDate.time;
                if (i == 0 && finishDate < begin) {
                    pass = true;
                    lastEnd = end;
                }
                if (i > 0) {
                    if (lastEnd < beginDate && finishDate < begin) {
                        pass = true;
                    }
                }
                if (i == yearList.length-1 && end < beginDate) {
                    pass = true;
                }
                lastEnd = end;
            }
        } else {
            pass = true;
        }
		return pass;
	}

	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {

		    if (!judgeTimes()) {
		        $.error("与其他学年的起止时间重叠");
		        return;
			}

			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#schoolYear_form");
			var url = "${pageContext.request.contextPath}/teach/schoolYear/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${pageContext.request.contextPath}/teach/schoolYear/"
						+ $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if ("success" === status) {
					$.success('保存成功');
					data = eval("(" + data + ")");
					if ("success" === data.info) {
						if (parent.core_iframe != null) {
							parent.core_iframe.window.location.reload();
						} else {
							parent.window.location.reload();
						}
						$.closeWindow();
					}else if("fail" === data.info){
						$.error("已经存在相同年限的学年段，不允许再添加");
					} else {
						
					}
				} else {
					$.error("保存失败");
				}
				loader.close();
			});
		}
	}
</script>
</html>