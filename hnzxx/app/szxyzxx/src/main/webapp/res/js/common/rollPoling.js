function RollPoling(requestUrl, getParamFun, intervalTime, callbackFun) {
	this.requestUrl = requestUrl;
	this.getParamFun = getParamFun;
	this.intervalTime = intervalTime;
	this.callbackFun = callbackFun;
	this.intervalObj = null;
}

RollPoling.prototype = {
		
	start : function() {
		if(this.intervalObj == null) {
			this.intervalObj = setInterval("sendByAjax('"+ this.requestUrl +"', "+ this.getParamFun +", "+ this.callbackFun +");", this.intervalTime);
		}
	},
	
	stop : function() {
		if(this.intervalObj != null) {
			window.clearInterval(this.intervalObj);
			this.intervalObj = null;
		} 
	}
	
};

function sendByAjax(requestUrl, getParamFun, callbackFun) {
	var data = getParamFun();
	var random = Math.random();
	$.ajax({
		url  : requestUrl + "?" + random,
		data : data,
		type : "POST",
		success : callbackFun
	}); 
}