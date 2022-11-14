/**
 * author clouds 2014/03/02
 * 互动模块通用js
 */
function delHistory(url, jsonData, callback) {
	jsonData._method = "delete";
	$.ajax({
		url    : url + '/study/management/resource/history/' + jsonData.resId,
		method : 'post',
		cache  : false,
		data   : jsonData,
		success : callback
	});
}

function delCourseHistory(url, jsonData, callback) {
	jsonData._method = "delete";
	$.ajax({
		url    : url + '/study/course/history/' + jsonData.c_id,
		method : 'post',
		cache  : false,
		data   : jsonData,
		success : callback
	});
}

