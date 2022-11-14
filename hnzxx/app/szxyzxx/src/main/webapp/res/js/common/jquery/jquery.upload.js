/**
 * jQuery upload v1.2
 * http://www.ponxu.com
 *
 * @author xwz
 */
(function($) {
	var noop = function(){ return true; };
	var frameCount = 0;
	
	$.uploadDefault = {
		url: '',
		fileName: 'filedata',
		dataType: 'json',
		params: {},
		onSend: noop,
		onSubmit: noop,
		onComplate: noop
	};

	$.upload = function(options) {
		var opts = $.extend(jQuery.uploadDefault, options);
		if (opts.url == '') {
			return;
		}
		
		var canSend = opts.onSend();
		if (!canSend) {
			return;
		}
		
		var form = $($("iframe").contents().get(0)).find("body form");
		var formHtml = $('<input type="file" name="' + opts.fileName + '" onchange="onChooseFile(this,{onSubmit:'+opts.onSubmit+'})" accept="application/msexcel">');
		form.attr('action', opts.url);
		var $input="";
		for (key in opts.params) {
			$input += '<input type="hidden" name="' + key + '" value="' + opts.params[key] + '">';
		}
		form.append($input);
		formHtml.appendTo(form);
		
		formHtml.click();
		var iframe = $($("iframe").contents().get(0)).find("body iframe");
		// iframe 在提交完成之后
		iframe.unbind("load").load(function() {
			var contents = $(this).contents().get(0);
			var data = $(contents).find('body').text();
			if ('json' == opts.dataType) {
				data = window.eval('(' + data + ')');
			}
			opts.onComplate(data);
			setTimeout(function() {
				form.empty();
			}, 100);
		});
		
//		// 文件框
//		var fileInput = $('input[type=file][name=' + opts.fileName + ']', form);
//		fileInput.click();
	};
})(jQuery);

// 选中文件, 提交表单(开始上传)
var onChooseFile = function(fileInputDOM,param) {
	param.onSubmit();
	var form = $(fileInputDOM).parent();
	form.submit();
};
