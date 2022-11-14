/**
 * @param sjXzqhDmID		省级行政区划对应的selectID
 * @param shijXzqhDmID		市级行政区划对应的selectID
 * @param qxjXzqhDmID		区县级行政区划对应的selectID
 */
function initXzqhSelect2(sjXzqhDmID,shijXzqhDmID,qxjXzqhDmID,isSelected,sjXzqhDm,shijXzqhDm,qxjXzqhDm) {
	$("#" + sjXzqhDmID).on("change", function(event) {
		var $this = $(this);
		var currentXzqhDm = $this.val();
		createXzqhSelect2("#" + shijXzqhDmID, currentXzqhDm, 2,isSelected,shijXzqhDm);
	});
	$("#" + shijXzqhDmID).on("change", function(event) {
		var $this = $(this);
		var currentXzqhDm = $this.val();
		createXzqhSelect2("#" + qxjXzqhDmID, currentXzqhDm, 3,isSelected,qxjXzqhDm);
	});
	createXzqhSelect2("#" + sjXzqhDmID, "", 1,isSelected,sjXzqhDm);
}
/**
 * @param selector		下一级selectID选择器
 * @param val			上级行政区划代码，通过上级行政区划代码查找下级行政区划代码
 * @param lv			等级1,2,3
 * @param isSelected 	是否选中true|false字符串
 * @param xzqhDm	 	行政区划代码值
 */
function createXzqhSelect2(selector, val, lv,isSelected,xzqhDm) {
	var $requestData = {};
	$requestData.tn = "t_dm_gy_xzqh";
	$requestData.pn = "sjxzqhDm";
	$requestData.value = val;
	$.get(ctp()+"/ehcache/findByParam",$requestData, function(data, status) {
		if ("success" === status) {
			data = eval("(" + data + ")");
			var currentSelect = $(selector);
			currentSelect.html("");
			currentSelect.append("<option value=''>请选择</option>");
			$.each(data, function(index, value) {
				currentSelect.append("<option value='"
						+ value.xzqhDm + "'>" + value.mc
						+ "</option>");
			});
			if ("true" === isSelected) {
				if (lv == 1) {
					currentSelect.val(xzqhDm);
				} else if (lv == 2) {
					currentSelect.val(xzqhDm);
				} else {
					currentSelect.val(xzqhDm);
				}
				currentSelect.trigger("change");
			}
		}
	});
}
/**
 * @param selector		选择器
 * @param requestData	请求json数据
 * @param isSelected	true|flase 字符串
 * @param selectDm		选中的代码值
 */
function createSelect(selector,requestData,isSelected,selectDm){
	$.get("/ehcache/findByParam",requestData, function(data, status) {
		if ("success" === status) {
			data = eval("(" + data + ")");
			var currentSelect = $(selector);
			currentSelect.html("");
			$.each(data, function(index, value) {
				currentSelect.append("<option value='" + value.xzqhDm + "'>" + value.mc + "</option>");
			});
			if ("true" === isSelected) {
				if (lv == 1) {
					currentSelect.val(selectDm);
				} else if (lv == 2) {
					currentSelect.val(selectDm);
				} else {
					currentSelect.val(selectDm);
				}
				currentSelect.trigger("change");
			}
		}
	});
}