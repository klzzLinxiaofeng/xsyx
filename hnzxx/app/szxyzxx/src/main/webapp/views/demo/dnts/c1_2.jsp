<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
<title>请假</title>
<style type="text/css">
 a:hover{
  transition: none 0s ease-in-out;
} 
</style>
<script>
//star
$(document).ready(function(){
    var stepW = 30;
    var description = new Array("太差","不好用","一般","还不错","完美解决");
    var stars = $(".stars > li");
    var descriptionTemp;
    var option = $(".option");
    $(".showb").css("width",0);
    stars.each(function(i){
        $(stars[i]).click(function(e){
            var n = i+1;
            $(".showb").css({"width":stepW*n});
            descriptionTemp = description[i];
            $(this).find('a').blur();
            return stopDefault(e);
            return descriptionTemp;
        });
    });
    stars.each(function(i){
        $(stars[i]).hover(
            function(){
                $(".description").text(description[i]);
                option.find(".option-con:eq(" + $(this).index() + ")").show().siblings().hide();
                $(".prompt").hide();
            },
            function(){
                if(descriptionTemp != null){
                    $(".description").text(descriptionTemp);
                }else{
                   $(".description").text(" "); 
                   option.find(".option-con").hide();
                  $(".prompt").show();
                }

                    
            }
        );
    });
});
function stopDefault(e){
    if(e && e.preventDefault)
           e.preventDefault();
    else
           window.event.returnValue = false;
    return false;
};
</script>
</head>
<body>
<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="yc_sq">
						<form class="form-horizontal" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">评分：</label>
								<div class="controls p7">
									<div class="xzw_starSys">
	<div class="xzw_starBox">
		<ul class="star stars">
			<li><a href="javascript:void(0)" title="1" class="one-star">1</a></li>
			<li><a href="javascript:void(0)" title="2" class="two-stars">2</a></li>
			<li><a href="javascript:void(0)" title="3" class="three-stars">3</a></li>
			<li><a href="javascript:void(0)" title="4" class="four-stars">4</a></li>
			<li><a href="javascript:void(0)" title="5" class="five-stars">5</a></li>
		</ul>
		<div class="current-rating showb" style="width: 0px;"></div>
	</div>
	<!--评价文字-->
	<div class="description"> </div>
	
 </div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">处理方式：</label>
								<div class="controls">
									<textarea class="span8" style="height:75px;padding:5px;"></textarea>
								</div>
							</div>
							<div class="caozuo" style="text-align:center;">
								<button class="btn btn-primary">确定</button> <button class="btn">取消</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>