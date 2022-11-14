<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>组卷中心</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery-1.9.1.js"></script>
    <%@ include file="/views/embedded/common.jsp"%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/zujuan.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/button.css">
<%-- <!-- 窗体控件 -->
<%@include file="/views/embedded/plugin/layer.jsp" %>
<%@include file="/views/embedded/plugin/szxy_window_js.jsp" %> --%>


    <style>
        @media (max-width: 960px){
            body{
                background: #fbfbfc;
            }
            /*.test-paper-left{
                border-right: 1px solid #cfcfcf;
            }*/
        }
        @media screen and (min-width:960px){
            body{
                background:#fbfbfc;
            }
           /* .test-paper-left{
                border-right: 1px solid #cfcfcf;
            }*/
        }
    </style>
</head>
<body>
    <div class="test-paper-all">
        <div class="test-paper-top">
            <ul class="fr">
                <li >
                    <a href="javascript:void(0)" class="btn-lightGray getback" onclick="">返回</a>
                </li>
                <li>
                    <a href="javascript:void(0)" class="btn-blue" onclick="">试卷分析</a>
                </li>
                <li>
                    <a href="javascript:void(0)" class="btn-blue" onclick="batchesSetting();">批量设置</a>
                </li>
                <li>
                    <a href="javascript:void(0)" class="btn-blue" onclick="propertySetting();">属性设置</a>
                </li>
                <li>
                    <a href="javascript:void(0)" class="btn-orange" onclick="">保存草稿</a>
                </li>
                <li>
                    <a href="javascript:void(0)" class="btn-green mgr20" onclick="">完成组卷</a>
                </li>

            </ul>
        </div>
        <div class="test-paper-title">
            <p>
                <span class="title">一元二次不等式的解法(一)</span>
                <input type="text" value="一元二次不等式的解法(一)" id="input-title" style="display: none"/>
                <i id="titleEdit"></i>
            </p>

            <ul>
                <li>
                    <span class="total-points">总分：</span>
                    <span>300</span>
                </li>
                <li>
                    <span class="subject-number">题目数量：</span>
                    <span>44</span>
                </li>
                <li>
                    <span class="subject">科目：</span>
                    <span class="mgr12">物理(<b>150</b>)</span>
                    <span class="mgr12">化学(<b>108</b>)</span>
                    <span class="mgr12">生物(<b>72</b>)</span>
                </li>
            </ul>

        </div>

        <div>
            <div class="test-paper-left">
                <div class="part-one wz">
                    <p>大题模式选择：</p>
                    <ul>
                        <li class="choose">
                            全局题号
                        </li>
                        <li>
                            分组题号
                        </li>
                    </ul>
                </div>
                <div class="part-two wz">
                    <select>
                        <option selected="selected">科目</option>
                        <option>2</option>
                    </select>
                </div>
                <div class="part-three">

                    <div class="xh">
                        <p style="display: block">
                            <span class="bb">一、阅读理解</span>
                            <span class="fr">
                                <i class="edit" ></i>
                                <i class="delete mgl7" ></i>
                            </span>
                        </p>
                        <input type="text" value="一、阅读理解" style="display: none" class="big-title"/>
                        <ul>
                            <li>
                                <a href="javascript:void(0)">100</a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">2</a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">3</a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">9</a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">5</a>
                            </li>
                        </ul>
                    </div>
                    <div class="xh">
                        <p style="display: block">
                            <span class="bb">2、阅读理解</span>
                            <span class="fr">
                                        <i class="edit" ></i>
                                        <i class="delete mgl7" ></i>
                                </span>
                        </p>
                        <input type="text" value="一、阅读理解" style="display: none" class="big-title"/>
                    <ul>
                        <li>
                            <a href="javascript:void(0)"></a>
                        </li>
                        <li>
                            <a href="javascript:void(0)">8</a>
                        </li>
                        <li>
                            <a href="javascript:void(0)">3</a>
                        </li>
                        <li>
                            <a href="javascript:void(0)">9</a>
                        </li>

                    </ul>
                </div>

                    <p class="xian"></p>
                </div>
                <div class="part-four wz">
                    <a href="javascript:void(0)" class="btn-blue new-dati"><i></i>新建大题</a>
                    <a href="javascript:void(0)" class="clear-all">清除所有大题结构</a>
                </div>
            </div>
            <div class="test-paper-right">
                <div class="test-paper-right-top">
                    <span class="show-answer">
                        <i class=""></i>
                        显示答案与解析
                    </span>
                </div>
                <div class="test-paper-right-content">
                  <div class="mgb22">
                    	<c:forEach items="${allTypeList}" var="atl" varStatus="vs">
	                        <c:forEach items="${wenZiSort}" var="wzs" varStatus="wz" begin="${vs.index}" end="${vs.index}">
	                        	<p>${wzs}：${atl}<span>（每题2分；共20分）</span></p>
	                        </c:forEach>
	                        <ul>
	                        <c:forEach items="${keyTypeValueVo}" var="ktvv" varStatus="vss">
	                            <c:if test="${ktvv.key eq atl}">
	                            	<c:forEach items="${ktvv.value}" var="kv" varStatus="vv">
			                            <li>
			                                <div class="timu">
			                                <span>
			                                	
			                                   	${vv.index+1}、（${kv.score}分）${kv.content}
			                                </span>
			                                    <div class="timu-choose">
			                                        <ul>
			                                        	<c:forEach items="${kv.answer}" var="an" varStatus="status">
			                                        		<li>${an}</li><br/><br/>
			                                        	</c:forEach>
			                                        </ul>
			                                    </div>
			                                </div>
			                                <div class="answer-and-analysis" style="display: none">
			                                    <p>
			                                        <span>【答案】</span>
			                                        <span class="color1d9">${kv.correctAnswer}</span>
			                                    </p>
			                                    <p>
			                                        <span>【解析】</span>
			                                        <span class="color666">${kv.explanation}</span>
			                                    </p>
			                                </div>
			                                <div class="timu-handle">
			                                    <ul>
			                                        <li>
			                                            <span><i class="difficulty"></i>难度：<b>${kv.difficulityString}</b></span>
			                                        </li>
			                                        <li>
			                                            <span><i class="update-time"></i>更新时间：<b>${kv.modifyDate}</b></span>
			                                        </li>
			                                        <li>
			                                            <span ><i class="use"></i>使用(<b class="blue">20</b>)</span>
			                                        </li>
			                                        <li class="fr">
			                                            <!-- <a href="javascript:void(0)" class="btn-lightGray">取消收藏</a> -->
			                                            <%-- <a href="javascript:void(0)" class="btn-red" onclick="removeFromPaper(${kvvt.key.id})" >移出试题篮</a> --%>
			                                            <a href="javascript:void(0)" class="btn-red removeFromPaper"  data-id="${kv.id}" >移出试题篮</a>
			                                        </li>
			                                    </ul>
			                                </div>
			                            </li>
		                            </c:forEach>
	                            </c:if>
	                        </c:forEach>
	                        </ul>
                        </c:forEach>
                    </div>

                </div>
            </div>
        </div>

    </div>

<script>
	$(function () {
	    leftRightHeight();
	    quanJu();
	    showQuestionData();
	})
/* $(function() {
	var paQuestionList = "${paQuestionList}";
	for(var vo in paQuestionList) {
		console.log(vo);
	}
	
}) */

	//显示问题数据
	function showQuestionData (){
		var parentDiv = $('.mgb21');
		var timu = $(".timu-content");
		//alert(timu.text());
		$.ajax({
	      url:"${pageContext.request.contextPath}/paperConstruct/index/list",
	      type:"POST",
	      //data:"name=eric&pass=123"
	      data:{name:"eric",pass:"123"},
	      dataType:"json",
	      success:function(result){
	    	  //console.log(JSON.stringify(result));
	          /* alert(result.allTypeList[0]);
	          alert(result.paQuestionList[0].id); */
	          show(result);
	      }
	     });
	}
	
	
	//删除指定题目
    $('body').on('click','.removeFromPaper',function(){
    	var id=$(this).attr('data-id');
    	var a = $(this);
        /*$(this).parent().parent().parent().parent().remove(); */
        $.ajax({
			url:"${pageContext.request.contextPath}/paperConstruct/index/removeQuestion",
		    type:"POST",
		    //data:"name=eric&pass=123"
		    data:{id:id},
		    success:function(result){
		    	if(result == "success"){
		    		console.log(result);
		            a.parent().parent().parent().parent().remove();
		    	}
		    }
		});
    });
	
	function show(result){
		var div = null;
		//alert(result.allTypeList[0]);
		var allTypeList = result.allTypeList;
		var paQuestionList = result.paQuestionList;
		for(var i=0; i<allTypeList.length;i++){
			//alert(allTypeList[i]);
			var tprc = $(".test-paper-right-content");
			
		}
		$(".test-paper-right-content").html();
	}
	
	function batchesSetting(){
		$.initWinOnTopFromLeft_qyjx("批量设置", '${pageContext.request.contextPath}/paperConstruct/batchSetting', '674', '530');
	}
	function propertySetting(){
		$.initWinOnTopFromLeft_qyjx("属性设置", '${pageContext.request.contextPath}/paperConstruct/propertySetting', '545', '425');
	}
	
    function  leftRightHeight() {

        var leftHeight = $('.test-paper-left').height();
        var rightHeight = $('.test-paper-right').height();
        if(leftHeight<rightHeight){
            $('.test-paper-left').css('height',rightHeight);
        }else if(leftHeight>rightHeight){
            $('.test-paper-right').css('height',leftHeight);
        }
    }
    window.onresize=leftRightHeight;

    
    //显示答案
    $('.show-answer').click(function(){
        var aa = $(this).children().attr('class');
        if(aa.length==0){
            $(this).children().addClass('choose-answer');
            $('.answer-and-analysis').slideDown();
        }else if(aa=='choose-answer'){
            $(this).children().removeClass('choose-answer');
            $('.answer-and-analysis').slideUp();
        }
    });
    //小题
    $('.timu-choose ul li').click(function(){
        $(this).siblings("li").removeClass("choose");
        $(this).addClass("choose");

    });
    function quanJu(){
        var s = 0;
        $('.part-three').children('.xh').each(function () {

            $(this).find('li').each(function () {
                s++;
                $(this).children().text(s);
            });

        });
    }
    //大题模式选择
    $('.part-one ul li ').click(function(){
        var yy = $('.part-three').children().length;
        if(yy>2){
            $(this).siblings("li").removeClass("choose");
            $(this).addClass("choose");
            var who = $(this).index();
            var total_num =0;

            if(who==0){

                quanJu();


            }else {
                $('.part-three').children('.xh').each(function () {
                    total_num += $(this).find('li').length;

                    $(this).find('li').each(function (index) {
                        $(this).children().text(index+1);
                    });

                });

            }
        }



    });
    //试卷标题
    $('#titleEdit').click(function(){
        $(this).prev().css('display','none');
        $(this).prev().prev().css('display','none');
        $(this).prev().css('display','block').select();
    });
    $('#input-title').blur(function(){
        var newTitle = $(this).val();
        $(this).value=newTitle;
        $(this).prev().text(newTitle);
        $(this).css("display","none");
        $(this).prev().css('display','block');
    });
    //一块题目选中
    $('body').on('click','.part-three .xh',function(){

        $(this).siblings(".xh").removeClass("choose");
        $(this).addClass("choose");
    });

    //编辑
    $('body').on('click','.edit',function(){
        $(this).parents(".xh").removeClass('choose');
        $(this).parent().parent('p').css('display','none');
        $(this).parent().parent().next().css('display','block').select();
        return false;
    });
    $('body').on('click','.big-title',function(){
        $(this).parents(".xh").removeClass('choose');

        return false;
    });


    //小题目input失去焦点
    $('body').on('blur','.big-title',function(){
        var newTitle = $(this).val();
        $(this).value=newTitle;
        $(this).prev().children('.bb').text(newTitle);

        $(this).css("display","none");
        $(this).prev().css('display','block');
    } );
    //删除指定大题
    $('body').on('click','.delete',function(){
        $(this).parent().parent().parent().remove();
        $('.part-one ul li ').each(function(){
            var aa;
            if($(this).hasClass('choose')){
                aa = $(this).index();
            }
            if(aa==0){
                quanJu();
            }

        });
    });

    //新增大题

    var qq = 1;
    $('.new-dati').click(function(){
        var aa = $('<div class="xh">\n' +
            '                        <p style="display: none">\n' +
            '                            <span class="bb"></span>\n' +
            '                            <span class="fr">\n' +
            '                                <i class="edit" ></i>\n' +
            '                                <i class="delete mgl7" ></i>\n' +
            '                            </span>\n' +
            '                        </p>\n' +
            '                        <input type="text" value="" style="display: block" class="big-title"/>\n' +
            '                    </div>');
        var bb = $('<p style="display: none">\n' +
            '                            <span class="bb"></span>\n' +
            '                            <span class="fr">\n' +
            '                                <i class="edit" ></i>\n' +
            '                                <i class="delete mgl7" ></i>\n' +
            '                            </span>\n' +
            '                        </p>\n' +
            '                        <input type="text" value="" style="display: block" class="big-title"/>');
        var tt = $('.part-three').children(':first-child').children().length;
        if(tt==1){
            qq=1;
            var tt = $('.part-three').children(':first-child').children(':last-child').before(bb);
            $('.big-title').focus().val('新建大题'+(qq++)).select();
        }else{
            $('.part-three').children(':last-child').before(aa);
            $('.big-title').focus().val('新建大题'+(qq++)).select();
        }
        /*if($('.part-three').children().hasClass('xh')){
            alert('存在');
            $('.part-three').children(':last-child').before(aa);
            $('.big-title').focus().val('新建大题'+(qq++)).select();
        }*//*else {
            alert('不存在');
            $('.part-three').children(':first-child').children(':first-child').before(bb);
        }
*/


    })


    //清除大题结构
    $('.clear-all').click(function(){
        var total_num =0;
        $('.part-three').children('.xh').each(function () {
            total_num += $(this).find('li').length;
            $(this).remove();

        });
        var rr = $('<div class="xh">\n'+'<ul>\n'+'</ul>' +'</div>');
        $('.part-three').children(':last-child').before(rr);
        for(var i=0;i<total_num;i++){
           var ee =  $('<li>\n' + '<a href="javascript:void(0)">'+(i+1)+'</a>\n' + '</li>');
            $('.part-three').children(':first-child').children().append(ee);
        }

    });



</script>
</body>
</html>


