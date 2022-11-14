<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="cache-control" content="max-age=0"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="/res/css/bp/h5/login/style.css">
    <%@ include file="/views/embedded/common.jsp"%>
   <!--  <script src="js/jq.js"></script> -->
    <title>选课页面</title>
    <style>
        .head{
            background: -webkit-linear-gradient(115deg, #75cfff, #06a2f5); /* Safari 5.1 - 6.0 */
            background: -o-linear-gradient(115deg,#75cfff, #06a2f5); /* Opera 11.1 - 12.0 */
            background: -moz-linear-gradient(115deg, #75cfff, #06a2f5); /* Firefox 3.6 - 15 */
            background: linear-gradient(115deg,#75cfff, #06a2f5); /* 标准的语法（必须放在最后） */
            padding-bottom:12px;padding-top: 0px;;
            position: relative;
        }
        .head p{ color: #fff; text-align: center;font-size: 16px; line-height: 2;}
        .title_con{ color: #666666; padding: 5% 0 3% 3%;background: #f1f7fa;border-bottom:1px solid #cdcdcd; font-size: 12px;}
        .list_span { color: #06a2f5; font-size: 16px; line-height: 25px;}
        .list_ul ul{  padding: 0 ;list-style: none; margin: 0}
        .list_ul ul li{ overflow: hidden;background: url("/res/images/bp/h5/course/but.png") no-repeat 4% 50%; background-size: 17px}
        .list_ul ul li.sect{background-image: url("/res/images/bp/h5/course/butok.png")}
        .list_ul ul li.sectss{background-image: url("/res/images/bp/h5/course/butok.png")}
        .list_ul ul li .libox{ overflow: hidden; border-bottom: 1px solid #dfdfdf; padding: 12px 0; width: 87%; float: right; position: relative}
        .list_ul ul li .left{ float: left; line-height: 25px; color: #333333; font-size: 18px;}
        .list_ul ul li .right{ float: right; padding-right: 10px; line-height: 25px; color: #999999}
        .list_ul ul li.sect .but_right{ right: 0 }
        .list_ul ul li .but_right{ font-size: 18px; background: #16a8f6; color: #fff;  line-height: 25px; padding: 12px 0; position: absolute;z-index: 66; top: 0;right: -150px;  width: 140px; text-align: center}
  		
  		.newspan span{display:bolck;}
  		  		    </style>
</head>
<body>
    <div class="head">
        <p style="position: absolute; left: 8%; top: 12px;">
        	<a href="${ctp}/bp/h5/home"><img src="/res/images/bp/h5/course/index_ico.png" alt="" width="20px"></a>
        </p>
        <p style=" padding: 8px 0; font-weight: bold; font-size: 18px;padding-bottom:0;">${gradeName}自主选课</p>
        <p>选课时间</p>
        <p style="font-size: 14px;line-height: 1.2" class="newspan">
        <fmt:formatDate value="${config.signupStartDate }" pattern="yyyy年MM月dd日 HH:mm" />~
        	<fmt:formatDate value="${config.signupFinishDate }" pattern="yyyy年MM月dd日 HH:mm" />
        </p>
    </div>
    <div class="title_con">
        请勾选您的意向科目
    </div>
<div class="list_ul">
    <ul>
    	 <c:forEach items="${items}" var="item">
    		<li id="${item.id}" <c:if test="${item.selected == true}">class="sectss"</c:if> >
	            <div class="libox" >
	                <div class="left">${item.courseNames}</div>
	                <div class="right">
	                	<span class="list_span"><c:if test="${config.isLimited == true}">名额${item.maxNum},</c:if>
	                		已报名${item.enrollCount}</span>
	                </div>
	                <div class="but_right">确定选择</div>
	            </div>
       	 	</li>
    	 </c:forEach>        
    </ul>
</div>
</body>
</html>
<script>
    $('.list_ul').on('click','li:not(".sectss")',function(){
        if($(this).hasClass('sect')){
//            $(this).removeClass('sect')
        }else{
            $('.list_ul ul li').eq($(this).index()).addClass('sect').siblings().removeClass('sect');
        }
    })
    $('.list_ul').on('click','.but_right',function(){

        
        
        save(this);
       

    })
    
    function save(obj){
		var $requestData = {};
		var courseConfigDetailId = $(obj).parent().parent().attr("id");	
		//alert(courseConfigDetailId);
		//alert($(obj).parent().parent().html());
		//$('.list_ul li').removeClass('sectss');
       // $(this).parent().parent().addClass('sectss');
     	// setTimeout(function(){ $('.list_ul li').removeClass('sect');},200)
        //alert('选择成功')
		//return;
		$requestData.courseConfigDetailId = courseConfigDetailId;	
		var url = "${ctp}/bp/h5/courseStudent/creator";
		$.post(url, $requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				if("success" === data.info) {
					$.success(data.responseData);
					$.refreshWin();
				} else if("fail" === data.info){
					$.error(data.responseData);
				}else {
					$.error("操作失败");
				}
			}else{
				$.error("操作失败");
			}		
		});
	}
</script>