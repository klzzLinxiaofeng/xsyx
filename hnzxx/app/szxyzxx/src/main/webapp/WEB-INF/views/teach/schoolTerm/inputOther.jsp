<%@ page language="java"
	import="platform.education.user.contants.UserContants"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>学期创建</title>
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
</head>
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<input type="hidden" id="schoolId" name="schoolId"
						value="${schoolId}" />
					<form class="form-horizontal" id="schoolTerm_form"
						action="javascript:void(0);">
						<input type="hidden" id="schoolYear" name="schoolYear" <c:if test="${not empty schoolYear.year }">value="${schoolYear.year }"</c:if> value="${schoolTerm.schoolYear }"/>
						<input type="hidden" id="yearBeginDate" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${schoolYear.beginDate}"></fmt:formatDate>'/>
						<input type="hidden" id="yearFinishDate" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${schoolYear.finishDate}"></fmt:formatDate>'/>
						<input type="hidden" id="schoolYearId" name="schoolYearId" <c:if test="${not empty schoolYear.id }">value="${schoolYear.id }"</c:if> value="${schoolTerm.schoolYearId }">
						<input type="hidden" id="nameTemp" value="${schoolTerm.name }">
						<input type="hidden" id="gbCodeTemp" value="${schoolTerm.gbCode }" >
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>国标学期名称</label>
							<div class="controls">
								<select class="span4" id="gbCode" name="gbCode"
									onchange="setCode();"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>校内学期名称</label>
							<div class="controls">
								<input type="text" id="name" name="name" class="span4"
									placeholder="请输入学期名称, 如秋季学期" value="${schoolTerm.name}"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>开始时间</label>
							<div class="controls">
								<input type="text" id="beginDate" name="beginDate" class="span4"
									placeholder="请输入开始时间 如2014-09-01"
									value='<fmt:formatDate pattern="yyyy-MM-dd" value="${schoolTerm.beginDate}"></fmt:formatDate>'
									onFocus="WdatePicker({minDate:'#F{$dp.$D(\'yearBeginDate\')}', maxDate:'#F{$dp.$D(\'finishDate\')}', readOnly:true})"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>结束时间</label>
							<div class="controls">
								<input type="text" id="finishDate" name="finishDate"
									class="span4" placeholder="请输入结束时间 如2015-07-01"
									value='<fmt:formatDate pattern="yyyy-MM-dd" value="${schoolTerm.finishDate}"></fmt:formatDate>'
									onFocus="WdatePicker({minDate:'#F{$dp.$D(\'beginDate\')}', maxDate:'#F{$dp.$D(\'yearFinishDate\')}', readOnly:true})"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<input type="hidden" id="code" name="code"
									class="span4" placeholder="唯一标识符, 如02-2014-1"
									value="${schoolTerm.code}">
							</div>
						</div>
						<div class="form-actions tan_bottom"
							style="padding-left: 0; background-color: #eee; text-align: center">
							<c:if test="${isCK == null || isCk == ''}">
								<input type="hidden" id="id" name="id" value="${schoolTerm.id}" />
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
	
		//国际学期码
		$.jcGcSelector("#gbCode", {
			"tc" : "XY-JY-XQ"
		}, '${schoolTerm.gbCode}');
		
		$("#gbCode").on("change", function() {
			var $gbCode = $("#gbCode").val();
			var gbCodeTemp = $("#gbCodeTemp").val();
			var nameTemp = $("#nameTemp").val();
			var text = $("#gbCode option:selected").text();
			if($gbCode != "" && $gbCode != null){
				if(nameTemp != null && nameTemp != "" && $gbCode == gbCodeTemp) {
					$("#name").val(nameTemp);
				}else {
					$("#name").val(text);
				}
			}
// 			$("#code").focus();
		});
		
	});

	function setCode() {
		var $gbCode = $("#gbCode").val();
		var $schoolYear = $("#schoolYear").val();
		if("" != $schoolYear && null != $schoolYear){
			var $code = $("#schoolId").val() + "-" + $("#schoolYear").val();
			if ("" != $gbCode && null != $gbCode) {
				$code = $code + "-" + $gbCode;
			}
		}
		$("#code").val($code);
	}

	function getAvailableTimes() {
        var termList = ${schoolTermList};
        var begin = ${schoolYear.beginDate.time};
        var end = ${schoolYear.finishDate.time};
        var times = [];
        if (termList != null && termList != "" && termList.length > 0) {
            var lastBegin = begin;
            var lastEnd = end;
            for (var i=0; i<termList.length; i++) {
                var termBegin = termList[i].beginDate.time;
                var termEnd = termList[i].finishDate.time;

                if (termBegin > lastBegin) {
                    times.push([lastBegin, termBegin]);
                    lastBegin = termEnd;
                } else if (termBegin = lastBegin) {
                    lastBegin = termEnd;
                }
                if (i== termList.length-1 && termEnd < lastEnd) {
                    times.push([termEnd, lastEnd+1]);
                }
            }
        } else {
            times.push([begin, end]);
        }
        return times;
    }


    function judgeTimes(obj) {
	    var pass = false;
        var beginDate = $("#beginDate").val();
        beginDate = new Date(beginDate + " 00:00:00").getTime();
        var finishDate = $("#finishDate").val();
        finishDate = new Date(finishDate +" 00:00:00").getTime();
        //可以等于学年第一天，不能等于学期第一天
		var isFirst = false;
		if (beginDate == ${schoolYear.beginDate.time}){
		    isFirst = true;
        }
        var isEnd = false;
        if (finishDate == ${schoolYear.finishDate.time}){
            isEnd = true;
        }
		for (var i=0; i<obj.length; i++) {
			var begin = obj[i][0];
		    var end = obj[i][1];
			if (begin < beginDate && finishDate < end) {
			    pass = true;
			}
			if (isFirst && begin <= beginDate && finishDate < end) {
			    pass = true;
			}
		}
		return pass;
    }

	function initValidator() {
		return $("#schoolTerm_form")
				.validate(
						{
							errorClass : "myerror",
							rules : {
// 								"code" : {
// 									required : true,
// 									remote : {
// 										async : false,
// 										type : "GET",
// 										url : "${pageContext.request.contextPath}/teach/schoolTerm/checker",
// 										data : {
// 											'dxlx' : 'code',
// 											'code' : function() {
// 												return $("#code").val();
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
								"gbCode" : {
									selectNone : true
								},
								"beginDate" : {
									required : true
								},
								"finishDate" : {
									required : true
								}
							},
							messages : {
// 								"code" : {
// 									remote : "该识别代码已经存在"
// 								}
							}
						});
	}

	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {

		    var times = getAvailableTimes();
		    if (!judgeTimes(times)) {
		        $.error("与其他学期起止时间重叠");
		        return;
			}

			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#schoolTerm_form");
			var url = "${pageContext.request.contextPath}/teach/schoolTerm/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${pageContext.request.contextPath}/teach/schoolTerm/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if ("success" === status) {
					$.success('保存成功');
					data = eval("(" + data + ")");
					if ("success" === data.info) {
						
						//--------添加德育管理评价任务---------------
						var termId = data.responseData;
						$requestData.termId = termId;
						url = "${pageContext.request.contextPath}/teach/teamEvaluation/addTask";
						$.post(url, $requestData, function() {
							
						});
						//------------------------------------------
						
						if (parent.core_iframe != null) {
							parent.core_iframe.window.location.reload();
						} else {
							parent.window.location.reload();
						}
						$.closeWindow();
					}else if("fail" === data.info){
						$.error("已经存在相同的学期记录，不允许再添加");
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