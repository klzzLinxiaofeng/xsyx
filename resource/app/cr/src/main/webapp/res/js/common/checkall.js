//初始化
$(function() {
		//全选
		$("#CheckedAll").unbind().click(function() {
			//所有checkbox跟着全选的checkbox走。
			//$('[name=items]:checkbox').prop("checked", this.checked);
			checkAllItem('items',this);
		});
		$('[name=items]:checkbox').unbind().click(
				function() {
					checkItem("CheckedAll","items");
				});
		//全选
		$("#bjCheckedAll").unbind().click(function() {
			//所有checkbox跟着全选的checkbox走。
			//$('[name=items]:checkbox').prop("checked", this.checked);
			checkAllItem('bjitems',this);
		});
		$('[name=bjitems]:checkbox').unbind().click(
				function() {
					checkItem("bjCheckedAll","bjitems");
				});
		
	});
//选中单个的项目
function checkItem(CheckedAll,items) {
	//定义一个临时变量，避免重复使用同一个选择器选择页面中的元素，提升程序效率。
	var $tmp = $('[name='+items+']:checkbox');
	//用filter方法筛选出选中的复选框。并直接给CheckedAll赋值。
	$('#'+CheckedAll).prop('checked',$tmp.length == $tmp.filter(':checked').length);
}
//选中所有的项目
function checkAllItem(items,t){
	//alert(t.checked);
	//alert($(t).checked);
	$('[name='+items+']:checkbox').prop("checked", t.checked);
}
//选中所有
function checkAll(){
	//全选
	$("#CheckedAll").unbind().click(function() {
		//所有checkbox跟着全选的checkbox走。
		$('[name=items]:checkbox').prop("checked", this.checked);
	});
}
//给单个选项绑定事件
function checkItems(){
	$('[name=items]:checkbox').unbind().click(
			function() {
				//定义一个临时变量，避免重复使用同一个选择器选择页面中的元素，提升程序效率。
				var $tmp = $('[name=items]:checkbox');
				//用filter方法筛选出选中的复选框。并直接给CheckedAll赋值。
				$('#CheckedAll').prop('checked',
						$tmp.length == $tmp.filter(':checked').length);
			});
}
//输出值
function allValue(name){
	if(name==''||name==undefined){
		name='items';
	}
	var arrayObj = new Array();
	$('[name='+name+']:checkbox:checked').each(function(){
		arrayObj.push($(this).val());
	});
	return arrayObj;
}