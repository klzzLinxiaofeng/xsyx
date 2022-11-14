function uploadevent(status, picUrl, callbackdata) {
	status += '';
	switch (status) {
	case '1':
		error("修改成功！");
		window.location.reload();
		break;
	case '-1':
		window.location.reload();
		break;
	case '-2':
		error("修改失败！");
		window.location.reload();
		break;
	default:
		window.location.reload();
	}
}