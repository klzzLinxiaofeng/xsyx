<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
		 contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.education.commonResource.web.common.contants.SysContants"%>


<%@ include file="/views/embedded/common.jsp"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/qyjx/css/all.css">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/new_requirement.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/falgun/js/jquery-ui-1.10.1.custom.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js" ></script>
<style>
    .layui-layer-ico16, .layui-layer-loading .layui-layer-loading2:before{
        content:"正在保存，请稍后...";
    }
</style>
<div class="grouping">
	<div class="grouping_left">
        <div class="gl_div1">
            <div class="wjl_ts" style="position: relative;display: inline-block;">
                <i class="gth" style="top: 15px;left: 8px;"></i>
                    <span class="bg_tis" style="left: 7px;top: 27px;">
						<span style="left: 13px;top: 26px;">请注意：普通教师没有修改默认分组的权限。</span>
                        <span style="left: 13px;top: 26px;display: none">请注意：默认分组方案确定后，无法进行修改。</span>
                        <span style="left: 13px;display: none">请注意：普通教师没有修改默认分组的权限。<br>自定义分组将会新建一个分组方案。</span>
					</span>
            </div>
        </div>
        <div class="gl_div2">
            <ul>
                 <c:choose>
                 	<c:when test="${groupList.size() > 0 }">
                 		<c:forEach items="${groupList }" var="list" varStatus="num">
                			<li num="${num.index }">
<%-- 	                 			<a href="javascript:void(0)" class="" data-groupId="${list.id }" onclick="getStudentGroupList(${list.id });"> --%>
	                 			<a href="javascript:void(0)" class="" data-groupId="${list.id }">
						        <span class="dldiv2_title" style="display: inline-block;">${list.name }</span>
						        <input type="text" onblur="saveGroup($(this))" value="" class="dldiv2_input" style="height: 27px; display: none;">
							    </a>
			                </li>
                 		</c:forEach>
                 	</c:when>
                 	<c:otherwise>
                 		<li>
		               		<a href="javascript:void(0)" class="moren_fenzu on">
		                      <span class="dldiv2_title ">默认分组</span>
		                      <input type="text" value="默认分组" class="dldiv2_input" style="height: 27px;display: none"/>
		                  </a>
		               	</li>
                 	</c:otherwise>
                 </c:choose>
            </ul>
        </div>
        <div class="gl_div3">
            <button class="btn btn-orange" onclick="addGroupPlan()">添加分组方案</button>
        </div>
        <div class="clear"></div>
    </div>
	<div class="grouping_right">
        <div class="gr_div1">
            <button class="btn btn-lightGray fr saveGroup" style="margin: 11px;">保存</button>
            <div class="clear"></div>
        </div>
        <div class="gr_div1" style="display: none">
            <span class="gr_div1_tis">*拖动学生可以修改分组</span>
            <div class="fr">
                <button class="btn btn-blue saveGroup" style="margin: 11px;">保存分组方案</button>
                <button class="btn btn-blue canceSaveGroup" style="margin: 11px;">取消修改</button>
                <button class="btn btn-red" style="margin: 11px;" onclick="deletePlan()">删除方案</button>
            </div>

            <div class="clear"></div>
        </div>
        <div class="gr_div2">
<!--             <div class="clear"></div> -->
        </div>
    </div>
    <div class="clear"></div>
</div>
<div class="add_group" style="display: none">
    <div class="ag_total">
        <div class="ag_div1 fl">
            <div class="ag_div1_left">
                <ul>
                    <li>
                        <a href="javascript:void(0)" class="on">组1</a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">组2</a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">组3</a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">组4</a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">组5</a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">组6</a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">组7</a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">组8</a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">组9</a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">组10</a>
                    </li>
                </ul>
            </div>
            <div class="ag_div1_right">
                <p class="title">组一</p>
                <div class="agr_div">
                    <table class="group_table">
                        <thead>
                            <tr><th><i class="ck"></i></th><th>序号</th><th>姓名</th> </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="agr_div" style="display: none">
                    <table class="group_table">
                        <thead>
                        <tr><th><i class="ck"></i></th><th>序号</th><th>姓名</th> </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="agr_div" style="display: none">
                    <table class="group_table">
                        <thead>
                        <tr><th><i class="ck"></i></th><th>序号</th><th>姓名</th> </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="agr_div" style="display: none">
                    <table class="group_table">
                        <thead>
                        <tr><th><i class="ck"></i></th><th>序号</th><th>姓名</th> </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="agr_div" style="display: none">
                    <table class="group_table">
                        <thead>
                        <tr><th><i class="ck"></i></th><th>序号</th><th>姓名</th> </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="agr_div" style="display: none">
                    <table class="group_table">
                        <thead>
                        <tr><th><i class="ck"></i></th><th>序号</th><th>姓名</th> </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="agr_div" style="display: none">
                    <table class="group_table">
                        <thead>
                        <tr><th><i class="ck"></i></th><th>序号</th><th>姓名</th> </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="agr_div" style="display: none">
                    <table class="group_table">
                        <thead>
                        <tr><th><i class="ck"></i></th><th>序号</th><th>姓名</th> </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="agr_div" style="display: none">
                    <table class="group_table">
                        <thead>
                        <tr><th><i class="ck"></i></th><th>序号</th><th>姓名</th> </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="agr_div" style="display: none">
                    <table class="group_table">
                        <thead>
                        <tr><th><i class="ck"></i></th><th>序号</th><th>姓名</th> </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
        <div class="change fl">
            <a href="javascript:void(0)" class="hui a1">-></a>
            <a href="javascript:void(0)" class="hui a2"><-</a>
        </div>
        <div class="no_group fl">
            <p>未分组</p>
            <div class="agr_div">
                <table class="group_table">
                    <thead>
                    <tr><th><i class="ck"></i></th><th>序号</th><th>姓名</th> </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="clear"></div>
    </div>
</div>
<div class="delete_div" style="display:none;">
    <p style="font-size: 16px;text-align: center;margin-top: 40px;">确定要删除吗？</p>
</div>
<script>
	//默认或者第一个分组的保存
	$(".saveGroup").on("click",function(){
		var gradeId = $("#team").find("a.on").attr("gradeId");
        var teamId = $("#team").find("a.on").attr("teamId");
		var groupId = $(".gl_div2 ul li a.on").attr("data-groupId");
		var groupName = $(".gl_div2 ul li a.on span").text();
		var groupList = [];
		$(".grouping_right .gr_div2").eq($(".gl_div2 ul li a.on").parent("li").attr("num")).find("ul.ul1>li").each(function(index){
			var studentIdList = "";
			$(this).find("div.ul2_div ul.ul2").find("li").each(function(){
				var studentId = $(this).attr("studentId") + "";
				studentIdList = studentIdList + studentId + "-"
			});
			groupList.push(studentIdList)
		});
		var l = JSON.stringify(groupList);
		console.log(l)
        var loader = new loadDialog();
        loader.show();
		$.post("${ctp}/learningPlan/saveStudentGroup", {
			"groupId":groupId, 
			"groupName":groupName, 
			"groupList":l, 
			"gradeId":gradeId,
			"teamId":teamId
			}, function(data,status){
			$.success("保存成功");
            loader.close();
		});
	})
	
	//取消保存
	$(".canceSaveGroup").on("click", function(){
		var num = $(".gl_div2 ul li a.on").parent("li").attr("num");
		var groupId = $('.gl_div2 ul li a.on').attr("data-groupId");
    	$.post("${ctp}/learningPlan/getStudentGroup", {"groupId":groupId}, function(data, status){
   			$('.grouping_right .gr_div2').eq(num).html(data)
		});
	});
	
	//默认第一个加选中样式
	$(".gl_div2 ul li a").eq(0).addClass("on");
	$(".gl_div2 ul li a").eq(0).addClass("moren_fenzu");
	
    //滚动条
    $(".ul2_div").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
    $(".agr_div").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});

    $(function() {
        //小ul移动
        $( ".connectedSortable" ).sortable({
            connectWith: ".connectedSortable"
        }).disableSelection();
        //大ul移动
        $( ".sortul" ).sortable({
            cursor: "move",
            items :"li",                        //只是li可以拖动
            update : function(event, ui){       //更新排序之后
                var left_index = $('.gl_div2 li a.on').parent('li').index();
                $('.grouping_right .gr_div2').eq(left_index).find('.sortul>li').each(function(i){
                    $(this).find('b').text(i+1)
                })
            }
        });
        var top_height = $('.ku_select').height()+16+14+60;

        $('.gl_div2').height(document.documentElement.clientHeight-top_height-195)
        $(".gl_div2").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
    });

    window.onresize=function(){
    	var top_height = $('.ku_select').height()+16+14+60;
        $('.gl_div2').height(document.documentElement.clientHeight-top_height-195);
     }
     var h = 0;
     //滚动高度
     function scrollHeight(){
    	 var top_height = $('.ku_select').height()+16+14+60;
         var aa = $(window).scrollTop();
         var dd=top_height-aa;
         var dd1 = (195+top_height)-aa
        // $('.grouping_left').css('height',h-top_height);
         if(aa<top_height){
             $('.grouping_left').css('top',dd);
             $('.gl_div2').height(document.documentElement.clientHeight-dd1)
         }else{
             $('.grouping_left').css('top',0);
             $('.grouping_left').css('height',h);
             $('.gl_div2').height(document.documentElement.clientHeight-195)
         }
     }
     $(window).scroll(scrollHeight);


    //删除方案
    function deletePlan(){
        layer.closeAll();
        layer.open({
            type: 1,
            area: ['351px', '190px'],
            title: '提示', //不显示标题
            content: $('.delete_div'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(){
                layer.close();
            },
            btn: ['确定','取消'],//按钮
            btn1: function(index, layero){
            	var groupId = $('.gl_div2 li a.on').attr("data-groupid");
            	$.post("${ctp}/learningPlan/deleteGroup", {"groupId":groupId}, function(data, status){
            		if(status == "error"){
            			$.error("删除失败")
            		}
            	});
            	
                var left_index = $('.gl_div2 li a.on').parent('li').index();
                $('.grouping_right .gr_div2').eq(left_index).remove();
                $('.gl_div2 li ').eq(left_index).remove();
            }
        });
    }

    //左侧编辑名字
    $('body').on('dblclick','.dldiv2_title',function(){
        $(this).hide();
        $(this).next('.dldiv2_input').show()
        $(this).next('.dldiv2_input').focus();
    })
    //左侧编辑名字失去焦点
    <%--$('body').on('blur','.dldiv2_input',function(){--%>
        <%--console.log($(this));--%>
        <%--var newTitle = $(this).val();--%>
        <%--if(newTitle==null || newTitle==""){--%>
            <%--layer.confirm("请输入1~20个字符",--%>
                <%--{ btn: '确定',cancel:function(){--%>
                        <%--$(".dldiv2_input").focus();--%>
                        <%--layer.closeAll();--%>
                    <%--}},function () {--%>
                    <%--$(".dldiv2_input").focus();--%>
                    <%--layer.closeAll();--%>
                <%--});--%>
            <%--return false;--%>
        <%--}else{--%>
            <%--$(this).prev('.dldiv2_title').text(newTitle)--%>
        <%--}--%>
        <%--$(this).hide();--%>
        <%--$(this).prev('.dldiv2_title').show();--%>
        <%----%>
        <%--var groupId = $(this).parent().attr("data-groupid")--%>
        <%--var groupName = $(this).siblings().text()--%>
        <%--var gradeId = $("#team").find("a.on").attr("gradeId");--%>
        <%--var teamId = $("#team").find("a.on").attr("teamId");--%>
        <%--$.post("${ctp}/learningPlan/saveGroup", {--%>
        	<%--"groupId":groupId,--%>
        	<%--"groupName":groupName,--%>
        	<%--"gradeId":gradeId,--%>
        	<%--"teamId":teamId--%>
        <%--}, function(data,status){--%>
        	<%--if(status == "error"){--%>
        		<%--$.error("添加失败")--%>
        	<%--}--%>
        <%--});--%>
        <%----%>
    <%--} );--%>
    function saveGroup($this){
        console.log(1);
        var newTitle = $($this).val();
        if(newTitle==null || newTitle==""){
            layer.confirm("请输入1~20个字符",
                { btn: '确定',cancel:function(){
                        $(".dldiv2_input").focus();
                        layer.closeAll();
                    }},function () {
                    $(".dldiv2_input").focus();
                    layer.closeAll();
                });
            return false;
        }else{
            $($this).prev('.dldiv2_title').text(newTitle)
            console.log(2);
        }
        $($this).hide();
        $($this).prev('.dldiv2_title').show();

        console.log(3);
        var groupId = $($this).parent().attr("data-groupid")
        var groupName = $($this).siblings().text()
        var gradeId = $("#team").find("a.on").attr("gradeId");
        var teamId = $("#team").find("a.on").attr("teamId");
        $.post("${ctp}/learningPlan/saveGroup", {
            "groupId":groupId,
            "groupName":groupName,
            "gradeId":gradeId,
            "teamId":teamId
        }, function(data,status){
            if(status == "error"){
                $.error("添加失败")
            }
        });

    }
    //左侧选择
    $('body').on('click','.gl_div2 li a',function(){
        $(this).parent('li').siblings().find('a').removeClass('on')
        $(this).addClass('on')
        var index = $(this).parent('li').index();
        $('.grouping_right .gr_div2').hide();
        $('.grouping_right .gr_div2').eq(index).show();
        $('.no_group tbody tr').remove();
        $('.no_group thead .ck').removeClass('onn');
        if(!$(this).hasClass('moren_fenzu')){
            $('.grouping_right .gr_div1').eq(0).hide();
            $('.grouping_right .gr_div1').eq(1).show();
        }else{
            $('.grouping_right .gr_div1').eq(0).show();
            $('.grouping_right .gr_div1').eq(1).hide();
        }
    })
    
    //获取分组列表
    $(".gl_div2 ul li").each(function(index){
    	var groupId = $(this).children("a").attr("data-groupId")
    	$.post("${ctp}/learningPlan/getStudentGroup", {"groupId":groupId}, function(data, status){
   			if(index==0){
   				$('.grouping_right .gr_div2').eq(0).html(data)
   			}else{
	    		$('.grouping_right .gr_div2:last-child').after('<div class="gr_div2" style="display: none">'+data +'<div class="clear"></div></div>')
   			}
		});
    	//小ul移动
        $( ".connectedSortable" ).sortable({
            connectWith: ".connectedSortable"
        }).disableSelection();
        //大ul移动
        $( ".sortul" ).sortable({
            cursor: "move",
            items :"li",                        //只是li可以拖动
            update : function(event, ui){       //更新排序之后
                var left_index = $('.gl_div2 li a.on').parent('li').index();
                $('.grouping_right .gr_div2').eq(left_index).find('.sortul>li').each(function(i){
                    $(this).find('b').text(i+1)
                })
            }
        });
    });

    //添加分组方案
    function addGroupPlan(){
    	var num = $(".gl_div2 ul li:last-child").attr("num");
    	num++
        var add = '<li num="'+num+'"><a href="javascript:void(0)"> <span class="dldiv2_title" style="display: none"></span>' +
                  '<input type="text" onblur="saveGroup($(this))" value="" class="dldiv2_input" style="height: 27px;"/> </a></li>'
        $('.gl_div2 ul').append(add);
        $('.dldiv2_input').focus();
        var right_add = '';
        for(var i =1;i<=10;i++){
            right_add = right_add + ' <li class="ui-state-default"><div class="title_area"><span>组<b>'+ i+'</b></span> <i class="fr add02"></i></div>' +
                '                    <a href="javascript:void(0)" class="add01">点击右上角按钮添加学生</a><div class="ul2_div">' +
                '                    <ul class="ul2 connectedSortable"> </ul> </div> <div class="clear"></div></li>'
        }
        $('.grouping_right .gr_div2:last-child').after(' <div class="gr_div2" style="display: none"><ul class="ul1 sortul">'+right_add +'</ul><div class="clear"></div></div>')
        $( ".sortul" ).sortable({
            cursor: "move",
            items :"li",
            update : function(event, ui){       //更新排序之后
                var left_index = $('.gl_div2 li a.on').parent('li').index();
                $('.grouping_right .gr_div2').eq(left_index).find('.sortul>li').each(function(i){
                    $(this).find('b').text(i+1)
                })
            }
        });
    }
    //添加学生
    $("body").on('click','.add02',function(){
    	
    	$('.no_group .ck.onn').removeClass('onn');
        $('.no_group tbody tr').remove();
        $('.a1').addClass('hui')
        $('.a2').addClass('hui')
        $('.agr_div .ck.onn').removeClass('onn')
        var left_index = $('.gl_div2 li a.on').parent('li').index();
        var index = $(this).parents('li').index()
        $('.ag_div1_left li').eq(index).find('a').click()
        var tr='',tbody='',no_group_tbody='';
        
    	//获取学生
//         var studentName = ['李晓明','钟美玲','王晓红','何仙姑','朱玲','谢晓琪','吴宇凡','连衣','王东明','吴晓晓','王大崔','毕海生','张蝶梦'];
        var studentName =[];
    	var gradeId = $("#team").children("a.on").attr("gradeId");
    	var teamId = $("#team").children("a.on").attr("teamId");
    	$.post("${ctp}/learningPlan/getStudentList", {"gradeId":gradeId, "teamId":teamId}, function(data, status){
    		var d = eval("("+data+")")
    		for(var i in d){
    			studentName.push(i+","+d[i])
    		}
	        $('.grouping_right .gr_div2').eq(left_index).find('.ul1>li').each(function(i){
	            if($(this).find('.ul2 li').length>0){
	                tr=''
	                $(this).find('.ul2 li').each(function(j){
	                    if($(this).length>0){
	                        var name = $(this).text();
	                        var studentId = $(this).attr("studentId");
	                        tr = tr +'<tr><td><i class="ck"></i></td><td>'+(j+1)+' </td><td studentId="'+studentId+'">'+ name +'</td></tr>';
                            console.log(studentName);
                            console.log($.inArray(name+","+studentId,studentName));
	                        studentName.splice($.inArray(name+","+studentId,studentName),1);
	                    }
	                    tbody=tr;
	                })
	            }else{
	                tbody=''
	            }
	            $('.ag_div1_right .agr_div').eq(i).find('tbody tr').remove()
	            $('.ag_div1_right .agr_div').eq(i).find('.group_table tbody').append(tbody)
	        })
	        for ( var i = 0; i <studentName.length; i++){
                console.log(studentName);
	            no_group_tbody =no_group_tbody+'<tr><td><i class="ck"></i></td><td>'+ (i+1)  +'</td><td studentId="'+studentName[i].split(",")[1]+'">'+ studentName[i].split(",")[0] +'</td></tr>'
	        }
	        $('.no_group tbody').append(no_group_tbody);
    	});
    	
        layer.open({
            type: 1,

            area: ['710px', '610px'],
            title: '添加分组', //不显示标题
            content: $('.add_group'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(){
                layer.close();
            },
            shade:0,
            btn: ['确定'],//按钮
            yes: function(index, layero){
                if($('.ag_div1_right .group_table').find('tbody tr').length>0){
                    var li02='',ul02='',li01='';
                    $('.ag_div1_right .agr_div ').each(function(i){
                        if($(this).find('.group_table tbody tr').length>0){
                            li02=''
                            $(this).find('.group_table tbody tr').each(function(){

                                if($(this).length>0){
                                    var name = $(this).find('td:last-child').text();
                                    var studentId = $(this).find('td:last-child').attr("studentId");
                                    li02 = li02 +' <li class="ui-state-default" studentId="'+studentId+'">'+ name +'</li>'
                                }
                                ul02=li02
                            })
                        }else{
                            ul02='';
                        }

                        //加到大的ul
                        li01= li01+'<li> <div class="title_area"><span>组<b>'+(i+1)+'</b></span> <i class="fr add02" style="display: block"></i></div>'+
                            '<a href="javascript:void(0)" class="add01" style="display: none"></a><div class="ul2_div" style="display: block">'+
                            '<ul class="ul2 connectedSortable">'+ul02 +'</ul></div><div class="clear"></div></li>'
                    })
                    $('.grouping_right .gr_div2').eq(left_index).find('.ul1').find('li').remove();
                    $('.grouping_right .gr_div2').eq(left_index).find('.ul1').append(li01)
                   // $('.ul1').append(li01)
                    layer.closeAll();
                    $( ".connectedSortable" ).sortable({
                        connectWith: ".connectedSortable"
                    }).disableSelection();
                    $(".ul2_div").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
                }else{
                    layer.msg('还没有学生选中');
                }

            }
        });
    	
    })
    //复选
    $('.add_group').on('click','.group_table tbody i.ck',function(){
        if($(this).hasClass('onn')){
            $(this).removeClass('onn');
            $(this).parents('.group_table').find('tbody i.ck')
            if( $(this).parents('.group_table').find('tbody i.ck').length!=$(this).parents('.group_table').find('tbody i.ck.onn').length){
                $(this).parents('.group_table').find('thead i.ck').removeClass('onn');
            }
        }else{
            $(this).addClass('onn');
            if( $(this).parents('.group_table').find('tbody i.ck').length==$(this).parents('.group_table').find('tbody i.ck.onn').length){
                $(this).parents('.group_table').find('thead i.ck').addClass('onn');
            }
        }
        if($('.no_group .group_table .onn').length>0){
            $('.a2').removeClass('hui')
        }else{
            $('.a2').addClass('hui')
        }
        var i = $('.ag_div1_left li a.on').parents('li').index();
        if($('.ag_div1_right .agr_div').eq(i).find('.onn').length>0){
            $('.a1').removeClass('hui')
        }else{
            $('.a1').addClass('hui')
        }

    });
    //全选
    $('.add_group').on('click','.group_table thead i.ck',function(){
        if($(this).hasClass('onn')){
            $(this).removeClass('onn');
            $(this).parents('.group_table').find('.ck').removeClass('onn');

        }else{
            $(this).addClass('onn');
            $(this).parents('.group_table').find('.ck').addClass('onn');
        }
        if($('.no_group .group_table  .onn').length>0){
            $('.a2').removeClass('hui')
        }else{
            $('.a2').addClass('hui')
        }
        var i = $('.ag_div1_left li a.on').parents('li').index();
        if($('.ag_div1_right .agr_div').eq(i).find('.onn').length>0){
            $('.a1').removeClass('hui')
        }else{
            $('.a1').addClass('hui')
        }
    });
    $('body').on('click','.ag_div1_left li a',function(){

        $(this).parents('li').siblings().find('a').removeClass('on')
        $(this).addClass('on');
        var i = $(this).parents('li').index();
        var text = $(this).text();
        $('.ag_div1_right .agr_div').hide()
        $('.ag_div1_right .agr_div').eq(i).show();
        $('.ag_div1_right .title').text(text)
        if($('.ag_div1_right .agr_div').eq(i).find('.onn').length>0){
            $('.a1').removeClass('hui')
        }else{
            $('.a1').addClass('hui')
        }
    })
    /*转移*/
    $('body').on('click','.a1',function(){
        $('.no_group thead .ck.onn').removeClass('onn');
        $('.agr_div thead .ck.onn').removeClass('onn')
        var i = $('.ag_div1_left li a.on').parents('li').index();
        var add=""
        $('.ag_div1_right .agr_div tbody').eq(i).find('.onn').parents('tr').each(function(){
            var text = $(this).find('td:last-child').text()
            var studentId = $(this).find('td:last-child').attr("studentId")
            add =  ' <tr><td><i class="ck"></i></td> <td></td><td studentId="'+studentId+'">'+text+'</td></tr>'+add
        })
        $('.ag_div1_right .agr_div tbody').eq(i).find('.onn').parents('tr').remove()
        $('.no_group').find('tbody').append(add)
        $(this).addClass('hui');
        //排序
        $('.ag_div1_right .agr_div').eq(i).find('tbody tr').each(function(i){
            $(this).find('td:nth-child(2)').text(i+1)
        })
        $('.no_group .group_table tbody tr').each(function(i){
            $(this).find('td:nth-child(2)').text(i+1)
        })

    })
    $('body').on('click','.a2',function(){
        $('.no_group thead .ck.onn').removeClass('onn');
        $('.agr_div thead .ck.onn').removeClass('onn')
        var i = $('.ag_div1_left li a.on').parents('li').index();
        var add=""
        $('.no_group .group_table tbody').find('.onn').parents('tr').each(function(){
            var text = $(this).find('td:last-child').text()
            var studentId = $(this).find('td:last-child').attr("studentId")
            add = ' <tr><td><i class="ck"></i></td> <td></td><td studentId="'+studentId+'">'+text+'</td></tr>' +add
        })
        $('.no_group .group_table tbody').find('.onn').parents('tr').remove()
        $('.ag_div1_right .agr_div').eq(i).find('.group_table tbody').append(add)
        $(this).addClass('hui');
        //排序
        $('.no_group .group_table tbody tr').each(function(i){
            $(this).find('td:nth-child(2)').text(i+1)
        })
        $('.ag_div1_right .agr_div').eq(i).find('tbody tr').each(function(i){
            $(this).find('td:nth-child(2)').text(i+1)
        })

    })

</script>

