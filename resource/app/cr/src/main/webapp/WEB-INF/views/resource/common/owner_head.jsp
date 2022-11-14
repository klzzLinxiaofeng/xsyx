<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="douFn" uri="http://www.jiaoxueyun.com/resource/cache/dou/functions"%>
<%@taglib prefix="resFn" uri="http://www.jiaoxueyun.com/resource/number/functions"%>
<%@ taglib prefix="avatar" uri="http://www.jiaoxueyun.com/resource/avatar"%>
<div class="commond_top">
	<ul>
		<li><p class="p1">积分值</p><p class="p2">${resFn:getIntegralCount(param.userId) }</p></li>
		<li><p class="p1">资源数</p><p class="p2">${resFn:getResCount(param.userId) }</p></li>
		<li class="main">
			<div style="width:250px;margin:0 auto;">
				<img src="<avatar:avatar userId='${param.userId}'></avatar:avatar>">
				<div class="nr_right">
					<p class="name">${douFn:getFieldVal('teacher', param.userId)}</p>
					<div class="jfpj">
						<p class="p3">积分评级</p>
						<div id="integral"><p></p></div>
					</div>
				</div>
			</div>
		</li>
		<li><p class="p1">浏览量</p><p class="p2">${resFn:getClickCount(param.userId) }</p></li>
		<li><p class="p1">下载量</p><p class="p2">${resFn:getDownloadCount(param.userId) }</p></li>
	</ul>
</div>
<script type="text/javascript">
var integral = '${resFn:getIntegralCount(param.userId) }';
$(function() {
	if(3<integral && integral<=250) {
		$("#integral").addClass("x1");
		if(3<integral && integral<=10) {
			$("#integral").children("p").css("width","20%");
		} else if(10<integral && integral<=40) {
			$("#integral").children("p").css("width","40%");
		} else if(40<integral && integral<=90) {
			$("#integral").children("p").css("width","60%");
		} else if(90<integral && integral<=150) {
			$("#integral").children("p").css("width","80%");
		} else if(150<integral && integral<=250) {
			$("#integral").children("p").css("width","100%");
		}
	} else if(250<integral && integral<=10000) {
		$("#integral").addClass("x2");
		if(250<integral && integral<=500) {
			$("#integral").children("p").css("width","20%");
		} else if(500<integral && integral<=1000) {
			$("#integral").children("p").css("width","40%");
		} else if(1000<integral && integral<=2000) {
			$("#integral").children("p").css("width","60%");
		} else if(2000<integral && integral<=5000) {
			$("#integral").children("p").css("width","80%");
		} else if(5000<integral && integral<=10000) {
			$("#integral").children("p").css("width","100%");
		}
	} else if(10000<integral && integral<=500000) {
		$("#integral").addClass("x3");
		if(10000<integral && integral<=20000) {
			$("#integral").children("p").css("width","20%");
		} else if(20000<integral && integral<=50000) {
			$("#integral").children("p").css("width","40%");
		} else if(50000<integral && integral<=100000) {
			$("#integral").children("p").css("width","60%");
		} else if(100000<integral && integral<=200000) {
			$("#integral").children("p").css("width","80%");
		} else if(200000<integral && integral<=500000) {
			$("#integral").children("p").css("width","100%");
		}
	} else if(integral>500000) {
		$("#integral").addClass("x4");
		if(500000<integral && integral<=1000000) {
			$("#integral").children("p").css("width","20%");
		} else if(1000000<integral && integral<=2000000) {
			$("#integral").children("p").css("width","40%");
		} else if(2000000<integral && integral<=5000000) {
			$("#integral").children("p").css("width","60%");
		} else if(5000000<integral && integral<=10000000) {
			$("#integral").children("p").css("width","80%");
		} else {
			$("#integral").children("p").css("width","100%");
		}
	}
})
</script>