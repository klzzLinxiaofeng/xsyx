//@AUTHOR panweiliang
function formData2JSONObj(selector) {
	var txt = '';
	// input text
	$(selector).find('input[type="text"]').each(
			function() {
				if ($(this).attr('name')) {
					txt += '"' + $(this).attr('name') + '":"'
							+ serialize($(this).val()) + '",';
				}
			});
	
	//input hidden
	$(selector).find('input[type="hidden"]').each(
			function() {
				if ($(this).attr('name')) {
					txt += '"' + $(this).attr('name') + '":"'
							+ serialize($(this).val()) + '",';
				}
			});
	
	// checkbox
	var chknames = '';
	$(selector).find('input[type="checkbox"]').each(
			function() {
				if (this.name) {
					if (this.checked) {
						if (chknames.toUpperCase().indexOf(
								this.name.toUpperCase()) >= 0) {
							var reg = new RegExp(
									'"' + this.name + '":' + '"(.+?)",', 'i');
							txt = txt.replace(reg, '"' + this.name + '":'
									+ '"$1,' + this.value + '",');
						} else {
							chknames += this.name + ',';
							txt += '"' + this.name + '":"' + this.value + '",';
						}
					}
				}
			});
	// radio
	var rdnames = '';
	$(selector).find('input[type="radio"]').each(
			function() {
				if (this.name) {
					if (this.checked) {
						if (rdnames.toUpperCase().indexOf(
								this.name.toUpperCase()) >= 0) {
							var reg = new RegExp(
									'"' + this.name + '":' + '"(.+?)",', 'i');
							txt = txt.replace(reg, '"' + this.name + '":' + '"'
									+ this.value + '",');
						} else {
							rdnames += this.name + ',';
							txt += '"' + this.name + '":"' + this.value + '",';
						}
					}
				}
			});

	// textArea
	$(selector).find('textArea').each(
			function() {
				if ($(this).attr('name')) {
					txt += '"' + $(this).attr('name') + '"' + ':"'
							+ serialize($(this).val()) + '",';
				}
			});
	// select
	$(selector).find('select').each(function() {
		if ($(this).attr('name')) {
			var value = '';
			if (this.selectedIndex >= 0) {
				if (this.options[this.selectedIndex].value) {
					value = this.options[this.selectedIndex].value=='请选择'?'':this.options[this.selectedIndex].value;
				} else {
					value = this.options[this.selectedIndex].text=='请选择'?'':this.options[this.selectedIndex].text;
				}
			}
			txt += $(this).attr('name') + ':"' + serialize(value) + '",';
		}
	});
	// file
	txt = txt.replace(/\\n/g, "\\n");
	var jsonObj = null;
	eval('jsonObj={' + txt + '}');
	return jsonObj;
}

function serialize(text) {
	//text = text.replace(/(")/g, "\\\"");
	text = text.replace(/\\n/g, "\\n");
	return text;
}