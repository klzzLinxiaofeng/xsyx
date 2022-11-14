<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<script src="${ctp}/res/js/common/plugin/ui/jquery-ui.js" type="text/javascript"></script>
<style>
.table tbody tr{
	cursor:pointer;
}
</style>
<table class="table table-bordered responsive" id="short">
	<thead>
		<tr>
			<td colspan="7">
				<span style="color:red;margin-right:10px;font:bold 14px/32px '微软雅黑'">拖动可以进行排序</span>
	<button style="display: none;" id="sortButton" onclick="saveSort(this);" class="btn btn-primary" type="button"><i class="fa fa-plus"></i>保存排序</button>
			</td>
		</tr>
		<tr>
			<th>序号</th>
			<th>姓名</th>
			<th>性别</th>
			<th>联系方式</th>
<%--			<th>所在部门</th>--%>
			<th>在职状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${items}" var="item" varStatus="status">
			<tr id="${item.id}">
				<td class="index">${status.count}</td>
				<td>${item.name}</td>
				<td>
					${item.sex != null && item.sex != "" ? jcgcFn:getColValue("GB-XB", item.sex, "name") : "无"}
				</td>
				<td>${item.mobile != null ? item.mobile : "无"}</td>
<%--				<td>${item.departmentName != null ? item.departmentName : "无"}</td>--%>
				<td>${item.jobState != null && item.jobState != "" ? jcgcFn:getColValue("JY-JZGDQZT", item.jobState, "name") : "无"}</td>
				<td>
					<button class="btn btn-danger btn-cz" type="button" data-teach-id="${item.id}" data-dept-id="${item.departmentId}" onclick="removeTeacherFromDept(this)">移出</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<p class="btn_link">
	<a class="a4" href="javascript:void(0)" id="member_selector" data-id-container="member_selector"><i class="fa fa-plus"></i>添加成员</a>
	<input type="hidden" value="" id="sort"/>
	<!-- <a class="a3" href="javascript:void(0)"><i class="fa fa-plus"></i>新建个人档案</a> -->
</p>

<script type="text/javascript">
	$(function() {
		$.createMemberSelector({
			"inputIdSelector" : "#member_selector",
// 			"isOnTopWindow" : true
// 			"ry_type" : "stu"
		});
	});
	
	//是否显示按钮，只有排序的时候才显示
	function showButton(){
		$("#sortButton").show();
	}
	
	//将排序的顺序放入到文本框中
	function addSort(sortData){
		var data = $("#sort").val();
		if(data == ""){
			data = sortData;
			$("#sort").val(data);
		}else{
			data += "," + sortData;
			$("#sort").val(data);
		}
	}
	
	//保存排序
	function saveSort(obj){
		var $this = $(obj);
		var $requestData = {};
		$requestData.teachIds = $("#sort").val();
		$requestData.deptId = $("td .btn-cz").data("dept-id");
		$requestData._method = "post";
		$.post("${ctp}/teach/dept/teacher/saveSort", $requestData, function(data, status) {
			data = eval("(" + data + ")");
			if("success" === status) {
				if("success" === data.info) {
					$.success("排序成功");
					$("#sortButton").hide();
					$("#sort").val("");
				} else if("error" === data.info) {
					$.error("排序失败");
				}
			}
		})
	}
	
	var fixHelperModified = function(e, tr) {
		var $originals = tr.children();
		var $helper = tr.clone();
		var i=$(this).index();
		$helper.children().each(function(index) {
			$(this).width($originals.eq(index).width());
		});
		return $helper;
		
	},
	updateIndex = function(e, ui) {
		$("#sort").val("");
		$('td.index', ui.item.parent()).each(function (i) {
			$(this).html(i + 1);
			addSort(ui.item.parent().children().eq(i).attr("id"));
		});
		showButton();
	};
	$("#short tbody").sortable({
		helper: fixHelperModified,
		stop: updateIndex
	}).disableSelection();
</script>

