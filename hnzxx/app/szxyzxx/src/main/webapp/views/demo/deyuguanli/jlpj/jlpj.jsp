<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dygl/dygl.css" rel="stylesheet">
<title>激励评价</title>
<script type="text/javascript">
$(function(){
	/* 切换选项卡 */
	$(".minutes-rated a").click(function(){
	    $(".minutes-rated a").removeClass("see-rated");
	    $(this).addClass("see-rated");
	    var i=$(this).index();
	    $(".project-rated").hide();
	    $(".project-rated").eq(i).show();
	});
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
        var k,l;
        for(k=0;k<$(".evaluate-option a").length;k++){
            if($(".evaluate-option a").eq(k).hasClass("evaluate-called")){
                var second_menu=$(".evaluate-projects").eq(k).children(".evaluate-action").children("a");
                for(l=0;l<second_menu.length;l++){
                    if(second_menu.eq(l).hasClass("evaluate-confirm")){
                        break;
                    }
                }
            break;
            }
        }
        var s_lenght=$(".all-class-students .on").length;
            $(".evaluate-projects").eq(k).children(".evaluate-div").children(".prize-student").eq(l).children("ul").empty();
        for(var m=0;m<s_lenght;m++){
            var touxiang=$(".all-class-students .on").eq(m).children("img").attr("src");
            var name=$(".all-class-students .on").eq(m).children("p").text();
            var id=$(".all-class-students .on").eq(m).attr('id');
            $(".evaluate-projects").eq(k).children(".evaluate-div").children(".prize-student").eq(l).children("ul").append("<li data-id="+id+"><img src='"+touxiang+"'><p>"+name+"</p><a href='javascript:void(0);' class='reveal'><img src='${pageContext.request.contextPath}/res/css/dygl/images/evaluate_delete.png'></a></li>");
        }
     })

    $(".evaluate-div").children(".prize-student").eq(0).show();
    $(".evaluate-option a").click(function(){
         $(".all-class-students ul li").removeClass("on");
        $(".evaluate-option a").removeClass("evaluate-called");
        $(this).addClass("evaluate-called");
        var i=$(this).index();
        $(".evaluate-projects").hide();
        $(".evaluate-projects").eq(i).show();
        var a_length=$(".evaluate-projects").eq(i).children(".evaluate-action").children("a").length;
        for(var o=0;o<a_length;o++){
            if($(".evaluate-projects").eq(i).children(".evaluate-action").children("a").eq(o).hasClass("evaluate-confirm")){
                $(".evaluate-projects").eq(i).find(".prize-student").eq(o).show();
                var s_shu=$(".evaluate-projects").eq(i).find(".prize-student").eq(o).children("ul").children("li").length;
                if(s_shu!=0){
                    for(var n=0;n<s_shu;n++){
                        var id=$(".evaluate-projects").eq(i).find(".prize-student").eq(o).children("ul").children("li").eq(n).data("id");
                        $("#"+id).addClass("on");
                    }
                }
            }
        }
    });
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
    /* 第一次进来第一个选项里面有没有学生 */
    var s_shu=$(".prize-student").eq(0).children("ul").children("li").length;
	if(s_shu!=0){
	    for(var n=0;n<s_shu;n++){
	        var id=$(".prize-student").eq(0).children("ul").children("li").eq(n).data("id");
	        $("#"+id).addClass("on");
	    }
	}
});
</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="激励评价" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							激励评价
						</h3>
					</div>
					<div class="content-widgets">
						<div class="check-rated">
						<div class="minutes-rated">
	                            <a href="javascript:void(0);" class="see-rated">激励评价卡查看</a>
	                            <a href="javascript:void(0);">激励评价卡录入</a>
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
														<span>月次：</span> <select></select>
													</div>
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
										<div  style="display: block;">
                            <p class="unreviewed ">三年级（2）班 5月份<span class="reviewed-cease">（已审核）</span> 录入时间：2016-07-25</p>
                           <!--  <p class="unreviewed ">三年级（2）班 5月份<span class="reviewed-no">（未审核）</span> 录入时间：2016-07-25</p> -->
						<div class="points limit-high" style="position: relative;">
                            <table class="responsive table table-striped reflective-evaluate" id="data-table">
                            <thead>
                                <tr role="row">
                                   <th>学号</th>
                                   <th>姓名</th>
                                   <th style="width:35%;">评价卡</th>
                                </tr>
                            </thead>
                            <tbody id="module_list_content">
                                 <tr class="">
                                     <td>1</td>
                                     <td>张小龙</td>
                                     <td>15</td>
                                </tr>
                                 <tr class="">
                                     <td>2</td>
                                     <td>张小龙</td>
                                     <td>15</td>
                                </tr>
                                 <tr class="">
                                     <td>3</td>
                                     <td>张小龙</td>
                                     <td>15</td>
                                </tr>
                                 <tr class="">
                                     <td>4</td>
                                     <td>张小龙</td>
                                     <td>15</td>
                                </tr>
                                 <tr class="">
                                     <td>5</td>
                                     <td>张小龙</td>
                                     <td>15</td>
                                </tr>
                                <tr class="">
                                     <td>6</td>
                                     <td>张小龙</td>
                                     <td>15</td>
                                </tr>
                                <tr class="">
                                     <td>7</td>
                                     <td>张小龙</td>
                                     <td>15</td>
                                </tr>
                                <tr class="">
                                     <td>8</td>
                                     <td>张小龙</td>
                                      <td>15</td>
                                </tr>
                                <tr class="">
                                     <td>9</td>
                                     <td>张小龙</td>
                                     <td>15</td>
                                </tr>
                                <tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                      <td>15</td>
                                </tr><tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                      <td>15</td>
                                </tr><tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                     <td>15</td>
                                </tr><tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                      <td>15</td>
                                </tr></tr><tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                      <td>15</td>
                                </tr>
                                </tr><tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                      <td>15</td>
                                </tr>
                                </tr><tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                      <td>15</td>
                                </tr>
                                </tr><tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                      <td>15</td>
                                </tr>
                                </tr><tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                      <td>15</td>
                                </tr>
                             </tbody>
                        </table>
                        </div>
                        <div class="pj_Table">
                        	<div class="pj_top"><ul><li>学号</li><li>姓名</li><li>评价卡</li></ul></div>
                        	<div class="pj_bottom">
                        		<ul>
                        			<li><p>1</p><p>张小龙</p><p>5</p></li>
                        			<li><p>2</p><p>张小龙</p><p>5</p></li>
                        			<li><p>3</p><p>张小龙</p><p>5</p></li>
                        			<li><p>4</p><p>张小龙</p><p>5</p></li>
                        			<li><p>5</p><p>张小龙</p><p>5</p></li>
                        			<li><p>6</p><p>张小龙</p><p>5</p></li>
                        			<li><p>7</p><p>张小龙</p><p>5</p></li>
                        			<li><p>8</p><p>张小龙</p><p>5</p></li>
                        			<li><p>9</p><p>张小龙</p><p>5</p></li>
                        			<li><p>10</p><p>张小龙</p><p>5</p></li>
                        			<li><p>11</p><p>张小龙</p><p>5</p></li>
                        			<li><p>12</p><p>张小龙</p><p>5</p></li>
                        			<li><p>13</p><p>张小龙</p><p>5</p></li>
                        			<li><p>14</p><p>张小龙</p><p>5</p></li>
                        			<li><p>15</p><p>张小龙</p><p>5</p></li>
                        		</ul>
                        	</div>
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
									<span>日期：</span> <input type="text" />
								</div>
								<p class="btn_link" style="float: right; margin: 10px 10px 0 0;">
									<a href="javascript:void(0)" class="a4" onclick="">保存</a>
								</p>
								<div class="clear"></div>
							</div>
							</div>
							</div>
							 <div class="evaluation-card">
        <div class="get-card-student">
            <div class="evaluate-option">
                <a href="javascript:void(0);" class="evaluate-called">品德发展评价卡</a>
                <a href="javascript:void(0);">学业发展评价卡</a>
                <a href="javascript:void(0);">身心发展评价卡</a>
                <a href="javascript:void(0);">兴趣特长评价卡</a>
                <a href="javascript:void(0);">实践操作评价卡</a>
            </div>
            <div class="card_detail">
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
                        <li data-id="6"><img src="/szxyzxx/res/images/no_pic.jpg"><p>刘六</p><a href="javascript:void(0);" class="reveal" style="display: none;"><img src="/szxyzxx/res/css/dygl/images/evaluate_delete.png"></a></li>
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

            <div class="evaluate-projects">
                <div class="evaluate-action" >
                    <a href="javascript:void(0);" class="evaluate-confirm">诚实守信1</a>
                    <a href="javascript:void(0);">举止文明1</a>
                    <a href="javascript:void(0);">尊敬老师1</a>
                    <a href="javascript:void(0);">知礼行礼1</a>
                    <a href="javascript:void(0);">关心集体1</a>
                    <a href="javascript:void(0);">爱护环境1</a>
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

            <div class="evaluate-projects">
                <div class="evaluate-action" >
                    <a href="javascript:void(0);" class="evaluate-confirm">诚实守信2</a>
                    <a href="javascript:void(0);">举止文明2</a>
                    <a href="javascript:void(0);">尊敬老师2</a>
                    <a href="javascript:void(0);">知礼行礼2</a>
                    <a href="javascript:void(0);">关心集体2</a>
                    <a href="javascript:void(0);">爱护环境2</a>
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

            <div class="evaluate-projects">
                <div class="evaluate-action" >
                    <a href="javascript:void(0);" class="evaluate-confirm">诚实守信3</a>
                    <a href="javascript:void(0);">举止文明3</a>
                    <a href="javascript:void(0);">尊敬老师3</a>
                    <a href="javascript:void(0);">知礼行礼3</a>
                    <a href="javascript:void(0);">关心集体3</a>
                    <a href="javascript:void(0);">爱护环境3</a>
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

            <div class="evaluate-projects">
                <div class="evaluate-action" >
                    <a href="javascript:void(0);" class="evaluate-confirm">诚实守信4</a>
                    <a href="javascript:void(0);">举止文明4</a>
                    <a href="javascript:void(0);">尊敬老师4</a>
                    <a href="javascript:void(0);">知礼行礼4</a>
                    <a href="javascript:void(0);">关心集体4</a>
                    <a href="javascript:void(0);">爱护环境4</a>
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
	</div>
</body>
</html>