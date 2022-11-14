<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/qyjx/css/all.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery/jquery-1.11.0.min.js"></script>
<title>A3-2-3创建导学案 </title>
</head>
<body>
<div class="tsk_dxa">
    <div class="selected_radio pdb27">
        <label>创建到</label>
        <ul>
            <li class="choose"><input type="radio" checked="checked">校本库</li>
            <li><input type="radio" >个人库</li>
        </ul>
    </div>
    <div class="ku_main">
			<div class="ku_d">
				<div class="jjmu">
					<p>教材版本：</p>
					<ul>
						<li><i>*</i> <span>学段</span> <select>
								<option selected="selected">小学</option>
								<option>初中</option>
						</select></li>
						<li><i>*</i> <span>科目</span> <select>
								<option selected="selected">道德与法制</option>
								<option>语文</option>
						</select></li>
						<li><i>*</i> <span>版本</span> <select>
								<option selected="selected">长春版</option>
								<option>人教版</option>
						</select></li>
						<li><i>*</i> <span>册次</span> <select>
								<option selected="selected">一年级必修</option>
								<option>2年级必修</option>
						</select></li>
						<li><i>*</i> <span>目录</span> <select>
								<option selected="selected">第一</option>
								<option>2</option>
						</select></li>
					</ul>
				</div>

				<div class="jjmu">
					<p>教材目录：</p>
					<ul>
						<li><i>*</i> <span>目录</span> <select>
								<option selected="selected">第一</option>
								<option>2</option>
						</select></li>
					</ul>
				</div>

				<div class="dxamu">
					<p>导学案目录：</p>
					<ul>
						<li><i>*</i> <span>标题</span> <input type="text" value="罗定邦中学"
							id="title" maxlength="50" /> <b><b id="num">5</b>/50</b></li>
						<li><span class="fl mgl9">提要</span> <textarea></textarea></li>

					</ul>
				</div>

				<div class="last_ope">
					<a href="javascript:void(0)" class="btn-blue">确定</a> <a
						href="javascript:void(0)" class="btn-lightGray">取消</a>
				</div>
			</div>
			<div class="ku_d" style="display: none">
				<div class="jjmu">
					<p>教材目录：</p>
					<ul>
						<li><i>*</i> <span>科目</span> <select>
								<option selected="selected">第一</option>
								<option>2</option>
						</select></li>
					</ul>
				</div>

				<div class="dxamu">
					<p>导学案目录：</p>
					<ul>
						<li><i>*</i> <span>标题</span> <input type="text" value="罗定邦中学"
							id="title" maxlength="50" /> <b><b id="num">5</b>/50</b></li>
						<li><span class="fl mgl9">提要</span> <textarea></textarea></li>

					</ul>
				</div>
				<div class="last_ope">
					<a href="javascript:void(0)" class="btn-blue">确定</a>
				</div>
			</div>
		</div>

<script>
	$(function() {
		$('.selected_radio ul li').click(function() {
			$(this).siblings("li").removeClass("choose");
			$(this).addClass("choose");
			var i=$(this).index();
			$(".ku_d").hide();
			$(".ku_d").eq(i).show();
		});

		$('#title').on('input propertychange keydown change', function() {
			setTimeout(function() {
				var len = $('#title').val().length;
				$('#num').text(len);
			})
		})

	})
</script>
</div>
</body>
</html>