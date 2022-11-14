<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>课堂优化</title>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/css/dygl/dygl.css" rel="stylesheet">
<style>
.evaluate-action {
    width: 130px;
    height: 100%;
    float: left;
    background: #f4f4f4;
}
.evaluate-action a {
    line-height: 40px;
    width: 120px;
    color: #535353;
    font-size: 12px;
    border-bottom: 1px solid #acb0b1;
    float: left;
    padding-left: 10px;
}
a.evaluate-confirm {
    color: #0073d6;
    background: #fff;
    margin-left: 1px;
    width: 120px;
    font-weight: bold;
}
</style>
<script type="text/javascript">
$(function(){
/*鼠标移入左边浮现删除*/
$(".evaluate-projects ").on("mouseover mouseout","ul li",function(event){
 if(event.type == "mouseover"){
   $(this).find(".reveal").show();
 }else if(event.type == "mouseout"){
  $(this).find(".reveal").hide();
 }
})
/*删除这个li*/
    $(".evaluate-projects").on("click","ul li a",function(){
        var id=$(this).parent().data("id");
        console.log(id)
         $("#"+id).removeClass("on");
        $(this).parent().remove();
    });
/*右边选中取消*/
     $(".all-class-students ul li").click(function(){
        $(this).toggleClass("on");
    });
     /*右边内容过来*/
     $(".evaluate-aleft a").click(function(){
        var l;
        for(l=0;l<$(".evaluate-action a").length;l++){
            if($(".evaluate-action a").eq(l).hasClass("evaluate-confirm")){
                break;
            }
        }
        
        var s_lenght=$(".all-class-students .on").length;
            $(".prize-student").eq(l).children("ul").empty();
        for(var m=0;m<s_lenght;m++){
            var touxiang=$(".all-class-students .on").eq(m).children("img").attr("src");
            var name=$(".all-class-students .on").eq(m).children("p").text();
            var id=$(".all-class-students .on").eq(m).attr('id');
            $(".prize-student").eq(l).children("ul").append("<li data-id="+id+"><img src='"+touxiang+"'><p>"+name+"</p><a href='javascript:void(0);' class='reveal'><img src='${pageContext.request.contextPath}/res/css/dygl/images/evaluate_delete.png'></a></li>");
        }
     })

    $(".evaluate-div").children(".prize-student").eq(0).show();
    $(".evaluate-action a").click(function(){
        var j=$(this).index();
        $(this).siblings().removeClass("evaluate-confirm");
        $(this).addClass("evaluate-confirm");
        $(this).parent().next().children(".prize-student").hide();
        $(this).parent().next().children(".prize-student").eq(j).show();
        var s_shu=$(this).parent().next().children(".prize-student").eq(j).children("ul").children("li").length;
            $(".all-class-students ul li").removeClass("on");
        if(s_shu!=0){
            for(var n=0;n<s_shu;n++){
                var id=$(this).parent().next().children(".prize-student").eq(j).children("ul").children("li").eq(n).data("id");
                $("#"+id).addClass("on");
            }
        }
    })
});
</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="课堂优化" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							课堂优化
						</h3>
					</div>
					<div class="content-widgets">
							<div class="check-rated">
							<div class="minutes-rated">
	                            <a href="javascript:void(0);" class="see-rated">课堂优化查看</a>
	                            <a href="javascript:void(0);">课堂优化</a>
	                        </div>
                        <div class="card_detail">
									<div class="project-rated">
										<div class="content-widgets">
											<div class="widget-container" style="padding: 20px 0 0 0">
												<div class="select_b">
													<div class="select_div">
														<span>学期：</span> <select></select>
													</div>
													<div class="select_div">
														<span>年级：</span> <select></select>
													</div>
													<div class="select_div">
														<span>班级：</span> <select></select>
													</div>
													<div class="select_div">
														<span>周次：</span> <select></select>
													</div>
													<div class="select_div">
														<span>学生：</span><input type="text" style="width:120px;" maxlength="10"/>
													</div>
													<button type="button" class="btn btn-primary" onclick="search()">查询</button>
													<p class="btn_link"
														style="float: right; line-height: 47px; margin-right: 10px;">
														<a href="javascript:void(0)" class="a3"
															onclick="$.refreshWin();"><i
															class="fa  fa-plus"></i> 导出</a> <a
															href="javascript:void(0)" class="a2" onclick=""><i
															class="fa  fa-bar-chart"></i> 评价报表</a>
													</p>
													<div class="clear"></div>
												</div>
											</div>
										</div>
										<div class="points-content table-spacing" style="display: block;">
                            <p>2015-2016年下学期 三年级2班   <span>第1周</span></p>
                            <div class="points limit-cancel" style="position: relative;">
                            <table class="responsive table table-striped reflective-evaluate optimization" id="data-table">
                            <thead>
                                <tr role="row">
                                   <th>星期</th>
                                   <th>学号</th>
                                   <th>姓名</th>
                                   <th>节次</th>
                                   <th class="bad">不良行为类型</th>
                                   <th>科目</th>
                                   <th>任课老师</th>
                                   <th>修改</th>
                                </tr>
                            </thead>
                            <tbody id="module_list_content">
                                 <tr class="">
                                     <td>星期一</td>
                                     <td>215566554</td>
                                     <td>张小龙</td>
                                     <td>第二节</td>
                                     <td><span>上课说闲话（2） 上课瞌睡  上课开小差上课说闲话（2） 上课瞌睡  上课开小差</span></td>
                                     <td>语文</td>
                                     <td>邓诗怡</td>
                                     <td><a href="javascript:void(0);">重新编辑</a></td>
                                </tr>
                                 <tr class="">
                                     <td>星期二</td>
                                     <td>215566554</td>
                                     <td>张小龙</td>
                                     <td>第二节</td>
                                     <td><span>上课说闲话（2） 上课瞌睡  上课开小差</span></td>
                                     <td>语文</td>
                                     <td>邓诗怡</td>
                                     <td><a href="javascript:void(0);">重新编辑</a></td>
                                </tr>
                                 <tr class="">
                                     <td>星期三</td>
                                     <td>215566554</td>
                                     <td>张小龙</td>
                                     <td>第二节</td>
                                     <td><span>上课说闲话（2）</span></td>
                                     <td>语文</td>
                                     <td>邓诗怡</td>
                                     <td><a href="javascript:void(0);">重新编辑</a></td>
                                </tr>
                                 <tr class="">
                                     <td>星期四</td>
                                     <td>215566554</td>
                                     <td>张小龙</td>
                                     <td>第二节</td>
                                     <td><span>上课说闲话（2） 上课瞌睡</span></td>
                                     <td>语文</td>
                                     <td>邓诗怡</td>
                                     <td><a href="javascript:void(0);">重新编辑</a></td>
                                </tr>
                                 <tr class="">
                                     <td>星期五</td>
                                     <td>215566554</td>
                                     <td>张小龙</td>
                                     <td>第二节</td>
                                     <td><span>上课说闲话（2） 上课瞌睡  上课开小差</span></td>
                                     <td>语文</td>
                                     <td>邓诗怡</td>
                                     <td><a href="javascript:void(0);">重新编辑</a></td>
                                </tr>
                                <tr class="">
                                     <td>星期六</td>
                                     <td>215566554</td>
                                     <td>张小龙</td>
                                     <td>第二节</td>
                                     <td><span>上课说闲话（2） 上课瞌睡  上课开小差</span></td>
                                     <td>语文</td>
                                     <td>邓诗怡</td>
                                     <td><a href="javascript:void(0);">重新编辑</a></td>
                                </tr>
                             </tbody>
                                                    </table>
                        </div>
                    </div>
									</div>
									<div class="project-rated">
										<div class="content-widgets">
											<div class="widget-container" style="padding: 20px 0 0 0">
													<div class="select_b">
														<div class="select_div">
															<span>学期：</span> <select></select>
														</div>
														<div class="select_div">
															<span>年级：</span> <select></select>
														</div>
														<div class="select_div">
															<span>班级：</span> <select></select>
														</div>
														<div class="select_div">
															<span>日期：</span> <input type="text"/>
														</div>
														<p class="btn_link"
															style="float: right; margin: 10px 10px 0 0;">
															<a href="javascript:void(0)" class="a4" onclick="">保存</a>
														</p>
														<div class="clear"></div>
													</div>
											</div>
										</div>
										<div class="evaluation-card" style="margin:0">
										<div class="card_detail">
										<div class="get-card-student">
                           					<div class="evaluate-projects">
							                <div class="evaluate-action" id="evaluate_action1">
							                    <a href="javascript:void(0);" class="evaluate-confirm">诚实守信</a>
							                    <a href="javascript:void(0);">举止文明</a>
							                    <a href="javascript:void(0);">尊敬老师</a>
							                    <a href="javascript:void(0);">知礼行礼</a>
							                    <a href="javascript:void(0);">关心集体</a>
							                    <a href="javascript:void(0);">爱护环境</a>
							                </div>
							                <div class="evaluate-div">
							                <h3>获卡学生</h3>
							                <div class="prize-student">
							                    <ul>
							                        
							                    </ul>
							                </div>
							                <div class="prize-student">
							                    <ul>
							                        
							                    </ul>
							                </div>
							                <div class="prize-student">
							                    <ul>
							                        
							                    </ul>
							                </div>
							                <div class="prize-student">
							                    <ul>
							                        
							                    </ul>
							                </div>
							                <div class="prize-student">
							                    <ul>
							                        
							                    </ul>
							                </div>
							                <div class="prize-student">
							                    <ul>
							                       
							                    </ul>
							                </div>
							            </div>
							            </div>
							            </div>
<!-- 							            <div class="clear"></div> -->
              </div>
            
             <div class="evaluate-aleft">
            <a href="javascript:void(0);"></a>
        </div>
        <div class="all-class-students">
            <h3>全部班级学生</h3>
                <ul>
                    <li id="1"><img src="${pageContext.request.contextPath}/res/images/no_pic.jpg"><p>张一</p><a href="javascript:void(0);" class="students-inside"><img src="${pageContext.request.contextPath}/res/css/dygl/images/evaluate_selected.png"></a></li>
                    <li id="2"><img src="${pageContext.request.contextPath}/res/images/no_pic.jpg"><p>李二</p><a href="javascript:void(0);" class="students-inside"><img src="${pageContext.request.contextPath}/res/css/dygl/images/evaluate_selected.png"></a></li>
                    <li id="3"><img src="${pageContext.request.contextPath}/res/images/no_pic.jpg"><p>张三</p><a href="javascript:void(0);" class="students-inside"><img src="${pageContext.request.contextPath}/res/css/dygl/images/evaluate_selected.png"></a></li>
                    <li id="4"><img src="${pageContext.request.contextPath}/res/images/no_pic.jpg"><p>李四</p><a href="javascript:void(0);" class="students-inside"><img src="${pageContext.request.contextPath}/res/css/dygl/images/evaluate_selected.png"></a></li>
                    <li id="5"><img src="${pageContext.request.contextPath}/res/images/no_pic.jpg"><p>王五</p><a href="javascript:void(0);" class="students-inside"><img src="${pageContext.request.contextPath}/res/css/dygl/images/evaluate_selected.png"></a></li>
                    <li id="6"><img src="${pageContext.request.contextPath}/res/images/no_pic.jpg"><p>刘六</p><a href="javascript:void(0);" class="students-inside"><img src="${pageContext.request.contextPath}/res/css/dygl/images/evaluate_selected.png"></a></li>
                    <li id="7"><img src="${pageContext.request.contextPath}/res/images/no_pic.jpg"><p>王七</p><a href="javascript:void(0);" class="students-inside"><img src="${pageContext.request.contextPath}/res/css/dygl/images/evaluate_selected.png"></a></li>
                    <li id="8"><img src="${pageContext.request.contextPath}/res/images/no_pic.jpg"><p>刘八</p><a href="javascript:void(0);" class="students-inside"><img src="${pageContext.request.contextPath}/res/css/dygl/images/evaluate_selected.png"></a></li>
                    <li id="9"><img src="${pageContext.request.contextPath}/res/images/no_pic.jpg"><p>张九</p><a href="javascript:void(0);" class="students-inside"><img src="${pageContext.request.contextPath}/res/css/dygl/images/evaluate_selected.png"></a></li>
                    <li id="10"><img src="${pageContext.request.contextPath}/res/images/no_pic.jpg"><p>李十</p><a href="javascript:void(0);" class="students-inside"><img src="${pageContext.request.contextPath}/res/css/dygl/images/evaluate_selected.png"></a></li>
                    <li id="11"><img src="${pageContext.request.contextPath}/res/images/no_pic.jpg"><p>王十一</p><a href="javascript:void(0);" class="students-inside"><img src="${pageContext.request.contextPath}/res/css/dygl/images/evaluate_selected.png"></a></li>
                    <li id="12"><img src="${pageContext.request.contextPath}/res/images/no_pic.jpg"><p>刘十二</p><a href="javascript:void(0);" class="students-inside"><img src="${pageContext.request.contextPath}/res/css/dygl/images/evaluate_selected.png"></a></li>
                    <li id="13"><img src="${pageContext.request.contextPath}/res/images/no_pic.jpg"><p>刘十三刘十三</p><a href="javascript:void(0);" class="students-inside"><img src="${pageContext.request.contextPath}/res/css/dygl/images/evaluate_selected.png"></a></li>
                </ul>
        </div>
        <div class="clear"></div>
        </div>
                        				</div>
									</div>
								</div>
						</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(".minutes-rated a").click(function(){
	    $(".minutes-rated a").removeClass("see-rated");
	    $(this).addClass("see-rated");
	    var i=$(this).index();
	    $(".project-rated").hide();
	    $(".project-rated").eq(i).show();
	});
	</script>
</body>
</html>