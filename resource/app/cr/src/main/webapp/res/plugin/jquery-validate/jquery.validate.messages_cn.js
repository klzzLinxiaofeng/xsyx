/*
 * Translated default messages for the jQuery validation plugin.
 * Language: CN
 * Author: Fayland Lam <fayland at gmail dot com>
 */
jQuery.extend(jQuery.validator.messages, {
        required: "必填字段",
		remote: "请修正该字段",
		email: "请输入正确格式的电子邮件",
		url: "请输入合法的网址",
		date: "请输入合法的日期",
		dateISO: "请输入合法的日期 (ISO).",
		number: "请输入合法的数字",
		digits: "只能输入整数",
		creditcard: "请输入合法的信用卡号",
		equalTo: "请再次输入相同的值",
		accept: "请输入拥有合法后缀名的字符串",
		maxlength: jQuery.format("字符串长度最多 {0} 个字符"),
		minlength: jQuery.format("字符串长度最少{0} 个字符"),
		rangelength: jQuery.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
		range: jQuery.format("请输入一个介于 {0} 和 {1} 之间的值"),
		max: jQuery.format("最大为 {0} "),
		min: jQuery.format("最小为 {0} ")
});

// 判断整数value是否等于0 
jQuery.validator.addMethod("isIntEqZero", function(value, element) { 
     value=parseInt(value);  
     return this.optional(element) || value==0;       
}, "整数必须为0"); 
  
// 判断整数value是否大于0
jQuery.validator.addMethod("isIntGtZero", function(value, element) { 
     value=parseInt(value);      
     return this.optional(element) || value>0;       
}, "整数必须大于0"); 
  
// 判断整数value是否大于或等于0
jQuery.validator.addMethod("isIntGteZero", function(value, element) { 
     value=parseInt(value);      
     return this.optional(element) || value>=0;       
}, "整数必须大于或等于0");   

// 判断整数value是否不等于0 
jQuery.validator.addMethod("isIntNEqZero", function(value, element) { 
     value=parseInt(value);      
     return this.optional(element) || value!=0;       
}, "整数必须不等于0");  

// 判断整数value是否小于0 
jQuery.validator.addMethod("isIntLtZero", function(value, element) { 
     value=parseInt(value);      
     return this.optional(element) || value<0;       
}, "整数必须小于0");  

// 判断整数value是否小于或等于0 
jQuery.validator.addMethod("isIntLteZero", function(value, element) { 
     value=parseInt(value);      
     return this.optional(element) || value<=0;       
}, "整数必须小于或等于0");  

// 判断浮点数value是否等于0 
jQuery.validator.addMethod("isFloatEqZero", function(value, element) { 
     value=parseFloat(value);      
     return this.optional(element) || value==0;       
}, "浮点数必须为0"); 
  
// 判断浮点数value是否大于0
jQuery.validator.addMethod("isFloatGteZero", function(value, element) { 
     value=parseFloat(value);      
     return this.optional(element) || value>0;       
}, "浮点数必须大于0"); 
  
// 判断浮点数value是否大于或等于0
jQuery.validator.addMethod("isFloatGteZero", function(value, element) { 
     value=parseFloat(value);      
     return this.optional(element) || value>=0;       
}, "浮点数必须大于或等于0");   

// 判断浮点数value是否不等于0 
jQuery.validator.addMethod("isFloatNEqZero", function(value, element) { 
     value=parseFloat(value);      
     return this.optional(element) || value!=0;       
}, "浮点数必须不等于0");  

// 判断浮点数value是否小于0 
jQuery.validator.addMethod("isFloatLtZero", function(value, element) { 
     value=parseFloat(value);      
     return this.optional(element) || value<0;       
}, "浮点数必须小于0");  

// 判断浮点数value是否小于或等于0 
jQuery.validator.addMethod("isFloatLteZero", function(value, element) { 
     value=parseFloat(value);      
     return this.optional(element) || value<=0;       
}, "浮点数必须小于或等于0");  

// 判断浮点型  
jQuery.validator.addMethod("isFloat", function(value, element) {       
     return this.optional(element) || /^[-\+]?\d+(\.\d+)?$/.test(value);       
}, "只能包含数字、小数点等字符"); 
 
// 匹配integer
jQuery.validator.addMethod("isInteger", function(value, element) {       
     return this.optional(element) || (/^[-\+]?\d+$/.test(value) && parseInt(value)>=0);       
}, "请输入整数");  
 
// 判断数值类型，包括整数和浮点数
jQuery.validator.addMethod("isNumber", function(value, element) {       
     return this.optional(element) || /^[-\+]?\d+$/.test(value) || /^[-\+]?\d+(\.\d+)?$/.test(value);       
}, "匹配数值类型，包括整数和浮点数");  

// 只能输入[0-9]数字
jQuery.validator.addMethod("isDigits", function(value, element) {       
     return this.optional(element) || /^\d+$/.test(value);       
}, "只能输入0-9数字"); 




// 字符验证，只能包含中文、英文、数字、下划线等字符。    
jQuery.validator.addMethod("stringCheck", function(value, element) {       
     return this.optional(element) || /^[a-zA-Z0-9\u4e00-\u9fa5-_]+$/.test(value);       
}, "只能包含中文、英文、数字、下划线等字符");
//字符验证，只能包含英文、数字、下划线等字符。    
jQuery.validator.addMethod("accCheck", function(value, element) {       
     return this.optional(element) || /^[a-zA-Z0-9_]+$/.test(value);       
}, "只能包含英文、数字、下划线等字符");
// 联系电话(手机/电话皆可)验证   
jQuery.validator.addMethod("isTel", function(value,element) {   
    var length = value.length;   
    var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;   
    var tel = /^(\d{3,4}-?)?\d{7,9}$/g;       
    return this.optional(element) || tel.test(value) || (length==11 && mobile.test(value));   
}, "请正确填写您的联系方式"); 
jQuery.validator.addMethod("phone", function(value, element, param) {
    return this.optional(element) || /^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}(-\d{1,4})?$/.test(value)|| /^1(\d){10}$/.test(value);
}, "请输入合法的电话号码");
jQuery.validator.addMethod("mobile", function(value, element, param) {
    return this.optional(element) || /^1(\d){10}$/.test(value);
}, "请输入合法的移动电话");
jQuery.validator.addMethod("postCode", function(value, element, param) {
    return this.optional(element) || /^[1-9]{1}(\d){5}$/.test(value);
}, "请输入合法的邮政编码");

//验证学籍号
jQuery.validator.addMethod("checkStudentNum", function(value, element) {
	var card_ = value.substr(0, 1);
	var re =new RegExp("G");
	var cardTemp = value.substr(1,value.length);
	var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
	return this.optional(element) || (reg.test(cardTemp) && re.test(card_));
}, "请输入合法的学籍号,学籍号正确的格式：G+身份证号码");

//QQ号码验证   
jQuery.validator.addMethod("qq", function(value, element) {
    var tel = /^[1-9]\d{4,9}$/;
    return this.optional(element) || (tel.test(value));
}, "qq号码格式错误");
//IP地址验证
jQuery.validator.addMethod("ip", function(value, element) {
    var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
    return this.optional(element) || (ip.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256));
}, "Ip地址格式错误");
// 字符验证，只能包含中文、英文、数字、下划线等字符。    
jQuery.validator.addMethod("stringCheck", function(value, element) {       
     return this.optional(element) || /^[a-zA-Z0-9\u4e00-\u9fa5-_]+$/.test(value);       
}, "只能包含中文、英文、数字、下划线等字符");
// 匹配english  
jQuery.validator.addMethod("isEnglish", function(value, element) {       
     return this.optional(element) || /^[A-Za-z]+$/.test(value);       
}, "只能为英文字母");  
// 字母和数字的验证
jQuery.validator.addMethod("chrnum", function(value, element) {
    var chrnum = /^([a-zA-Z0-9]+)$/;
    return this.optional(element) || (chrnum.test(value));
}, "只能输入数字和字母(字符A-Z, a-z, 0-9)");

// 中文的验证
jQuery.validator.addMethod("chinese", function(value, element) {
    var chinese = /^[\u4e00-\u9fa5]+$/;
    return this.optional(element) || (chinese.test(value));
}, "只能输入中文");

jQuery.validator.addMethod("noChinese", function(value, element) {
    var chinese = /^[\u4e00-\u9fa5]+$/;
    return this.optional(element) || (!chinese.test(value));
}, "只能输入中文");

// 匹配中文(包括汉字和字符) 
jQuery.validator.addMethod("isChineseChar", function(value, element) {       
     return this.optional(element) || /^[\u0391-\uFFE5]+$/.test(value);       
}, "匹配中文(包括汉字和字符) "); 
// 下拉框验证
$.validator.addMethod("selectNone", function(value, element) {
    return !(value == ""||value=="请选择");
}, "必须选择一项");
jQuery.validator.addMethod("pattern", function(value, element, param) {
    return this.optional(element) || param.test(value);
}, "无效格式.");
//字节长度验证(字节范围)
jQuery.validator.addMethod("byteRangeLength", function(value, element, param) {
    var length = value.length;
    for (var i = 0; i < value.length;i++) {
        if (value.charCodeAt(i) > 127) {
            length++;
        }
    }
    return this.optional(element) || (length >= param[0] && length <= param[1]);
}, $.validator.format("请确保输入的值在{0}-{1}个字节之间(一个中文字算2个字节)"));
jQuery.validator.addMethod("maxByteLength", function(value, element, param) {
	if (this.optional(element)) return "dependency-mismatch";
	var byteLength=value.length;
	var regRet=value.match(/[^\x00-\xff]/g);
	if(regRet!=null){
		byteLength+=regRet.length;
	}
    return byteLength<=param;
}, jQuery.format("请输入一个字节长度不大于 {0} 的字符串"));
jQuery.validator.addMethod("lessEqualTo", function(value, element, param) {
	var target = $(param.targetId).unbind(".validate-lessEqualTo").bind("blur.validate-lessEqualTo", function() {
		$(element).valid();
	});
	if(typeof(param.dataType)==='string'){
		if('int'==param.dataType){
			return parseInt(value) <= parseInt(target.val());
		}
	}
	return value <= target.val();
}, jQuery.format("输入值大于目标值{0}"));
// 判断是否包含中英文特殊字符，除英文"-_"字符外
jQuery.validator.addMethod("isContainsSpecialChar", function(value, element) {  
     var reg = RegExp(/[(\ )(\`)(\~)(\!)(\@)(\#)(\$)(\%)(\^)(\&)(\*)(\()(\))(\+)(\=)(\|)(\{)(\})(\')(\:)(\;)(\')(',)(\[)(\])(\.)(\<)(\>)(\/)(\?)(\~)(\！)(\@)(\#)(\￥)(\%)(\…)(\&)(\*)(\（)(\）)(\—)(\+)(\|)(\{)(\})(\【)(\】)(\‘)(\；)(\：)(\”)(\“)(\’)(\。)(\，)(\、)(\？)]+/);   
     return this.optional(element) || !reg.test(value);       
}, "含有中英文特殊字符"); 
jQuery.validator.addMethod("serverValid", function(value, element, param) {
	if (this.optional(element)) return "dependency-mismatch";
	var previous = this.previousValue(element);
	
	if (!this.settings.messages[element.name] )
		this.settings.messages[element.name] = {};
	if ( previous.old !== value ) {
		previous.old = value;
		var validator = this;
		this.startRequest(element);
		var data = {};
		data[element.name] = value;
		var paramData=param.data.call(this);
		$.extend(data,paramData);
		$.post(param.url,data,function(response){
			if (response.state==1) {//验证通过
				var submitted = validator.formSubmitted;
				validator.prepareElement(element);
				validator.formSubmitted = submitted;
				validator.successList.push(element);
				validator.showErrors();
				previous.valid = true;
				previous.message=null;
				validator.settings.messages[element.name].serverValid=undefined;
			} else {
				var errors = {};
				errors[element.name] =  response.msg || "服务端验证不通过";
				validator.showErrors(errors);
				previous.valid = false;
				validator.settings.messages[element.name].serverValid=errors[element.name];
			}
			validator.stopRequest(element, response);
		},'json');
		return "pending";
	} else if( this.pending[element.name] ) {
		return "pending";
	}
	return previous.valid;
}, "服务端验证不通过");

jQuery.validator.addMethod("lrunlv", function(value, element) {
    return this.optional(element) || /^\d{1,9}(\.\d{1,2})?$/.test(value);         
}, "请输入数字，且小数位不能超过三位");   
$.validator.setDefaults({
	ignore:"",
	errorPlacement: function(error, element) {
		var TeElement=element.parent();
		TeElement.append(error);
	}
});
/*$.metadata.setType("attr","validate");
$.validator.setDefaults({
	errorElement: "em",
	errorPlacement: function(error, element) {
		var TeElement=element.parent("td");
		TeElement.append("<br/>");
		TeElement.append(error);
	}
});*/

jQuery.validator.addMethod("lessThanEndDate", function(value, element, param) {
	var result = true;
	var $this = $(element);
	var endId = $this.attr("end-id");
	var begin = new Date(value.replace(/-/g, "/"));
    var end = new Date($("#" + endId).val().replace(/-/g, "/"));
    if(begin - end>0){
    	result = false;
    }
    return this.optional(element) || result;
}, "开始时间要结束时间之前");

//jQuery.validator.addMethod("greaterThanDate", function(value, element, param) {
//	var result = true;
//	var $this = $(element);
//	var startId = $this.attr("start-id");
//	var begin = new Date($("#" + startId).val().replace(/-/g, "/"));
//	var end = new Date(value.replace(/-/g, "/"));
//    if(begin - end < 0){
//    	result = false;
//    }
//    return this.optional(element) || result;
//}, "结束时间必须大于开始时间");

