/**
 * author panweiliang 2014/02/20
 * 用户界面域值操作
 */
function user_login(url, username, password, remember, info_panel) {
	$.post(url+"/login", {
		username : $(username).val(),
		password : $(password).val(),
		isRememberMe : $(remember).prop("checked")
	}, function(data, status) {
		if("success" === status) {
			if("1002" === data) {
				location.href = url + "/home";
			} else if ("1004" === data){
				$(info_panel).html("<front style=''>*账号不存在</>");
			} else if("2002" === data ) {
				$(info_panel).html("<front style=''>*您的账号被禁用</>");
			} else if("2003" === data) {
				$(info_panel).html("<front style=''>*您还未激活账号</>");
			} else if("2004" === data) {
				$(info_panel).html("<front style=''>*您的账号已失效</>");
			}else {
				$(info_panel).html("<front style=' '>*账号或者密码错误</>");
			}
		} else {
			$(info_panel).html("<front style=' '>*系统异常 无响应</>");
		}
	});
}


function xw_login(url, username, password, remember, info_panel) {
	$.post(url+"/xw/login", {
		username : $(username).val(),
		password : $(password).val(),
		isRememberMe : $(remember).prop("checked")
	}, function(data, status) {
		if("success" === status) {
			if("1002" === data) {
				location.href = url + "/home";
			} else if ("1004" === data){
				$(info_panel).html("<front style=''>*账号不存在</>");
			} else if("2002" === data ) {
				$(info_panel).html("<front style=''>*您的账号被禁用</>");
			} else if("2003" === data) {
				$(info_panel).html("<front style=''>*您还未激活账号</>");
			} else if("2004" === data) {
				$(info_panel).html("<front style=''>*您的账号已失效</>");
			}else {
				$(info_panel).html("<front style=' '>*账号或者密码错误</>");
			}
		} else {
			$(info_panel).html("<front style=' '>*系统异常 无响应</>");
		}
	});
}

function bp_login(url, username, password, remember, info_panel) {
	$.post(url+"/login", {
		username : $(username).val(),
		password : $(password).val(),
		isRememberMe : $(remember).prop("checked")
	}, function(data, status) {
		if("success" === status) {
			if("1002" === data) {
				location.href = url + "/bp/h5/home";
			} else if ("1004" === data){
				alert("账号不存在");
				//$(info_panel).html("<front style=''>*账号不存在</>");
			} else if("2002" === data ) {
				alert("您的账号被禁用");
				//$(info_panel).html("<front style=''>*您的账号被禁用</>");
			} else if("2003" === data) {
				alert("您还未激活账号");
				//$(info_panel).html("<front style=''>*您还未激活账号</>");
			} else if("2004" === data) {
				alert("您的账号已失效");
				//(info_panel).html("<front style=''>*您的账号已失效</>");
			}else {
				alert("账号或者密码错误");
				//$(info_panel).html("<front style=' '>*账号或者密码错误</>");
			}
		} else {
			alert("系统异常无响应");
			//$(info_panel).html("<front style=' '>*系统异常 无响应</>");
		}
	});
}