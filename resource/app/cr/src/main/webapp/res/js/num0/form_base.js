$(document).ready(function() {
	/**
	 * $(function() { $("#dialog").dialog({ resizable : false, width:400, height :
	 * 200, modal : true, buttons : { "注册" : function() {
	 * $(this).dialog("close"); }, Cancel : function() {
	 * $(this).dialog("close"); } } }); });
	 */
});

/** 初始化 */
function initialise() {
	var input_value, input_old_value;
	$("input[data-auto='true']").focus(function() {
		input_value = $(this).val();
		if (input_old_value === input_value) {
			$(this).val(input_value);
			return;
		}
		$(this).val("");
	});

	$("input").change(function() {
		input_old_value = $(this).val();
	});
}
