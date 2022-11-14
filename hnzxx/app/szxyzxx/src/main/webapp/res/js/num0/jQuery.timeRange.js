﻿
$(function(){
	//
//	$('#kb_tb .timeRange').attr("disabled", "disabled");
	
	$('#kb_tb').on("click", ".timeRange", function() {
		
	});
	
	$('#kb_tb').on("click", ".timeRange", function(){
		$('#timeRange_div').remove();
		
		var hourOpts = '';
		for (i=0;i<24;i++) {
			hourOpts += '<option>'+ (i < 10 ? '0' + i : i) +'</option>';
		}
		
		var minuteOpts = '';
		for (i=0; i <= 60 ;i++) {
			minuteOpts += '<option>'+ (i < 10 ? '0' + i : i) +'</option>';
		}
//		var minuteOpts = '<option>00</option><option>10</option><option>20</option><option>30</option><option>40</option><option>50</option>';
		
		var html = $('<div id="timeRange_div"><select id="timeRange_a" style="width : 60px">'+hourOpts+
			'</select> ：<select id="timeRange_b" style="width : 60px">'+minuteOpts+
			'</select> -- <select id="timeRange_c" style="width : 60px">'+hourOpts+
			'</select> ：<select id="timeRange_d" style="width : 60px">'+minuteOpts+
			'</select>&nbsp&nbsp<input type="button" value="确定" id="timeRange_btn" /></div>')
			.css({
				"position": "absolute",
				"z-index": "999",
				"padding": "5px",
				"border": "1px solid #AAA",
				"background-color": "#FFF",
				"box-shadow": "1px 1px 3px rgba(0,0,0,.4)"
			})
			.click(function(){return false});
		// 如果文本框有值
		var v = $(this).val();
		if (v) {
			v = v.split(/:|-/);
			html.find('#timeRange_a').val(v[0]);
			html.find('#timeRange_b').val(v[1]);
			html.find('#timeRange_c').val(v[2]);
			html.find('#timeRange_d').val(v[3]);
		}
		// 点击确定的时候
		var pObj = $(this);
		html.find('#timeRange_btn').click(function(){
			var str = html.find('#timeRange_a').val()+':'
				+html.find('#timeRange_b').val()+'-'
				+html.find('#timeRange_c').val()+':'
				+html.find('#timeRange_d').val();
			pObj.val(str);
			$('#timeRange_div').remove();
		});
		
		$(this).after(html);
		return false;
	});
	//
	$(document).click(function(){
		$('#timeRange_div').remove();
	});
	//
});