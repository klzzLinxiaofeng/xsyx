<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">

</style>
</head>
<body>
	<div class="container-fluid">
	<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-calculator" name="icon"/>
			<jsp:param value="新生分班" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid ">
			<div class="span12"  style="height: 43px;">
				<div class="content-widgets white" style="margin-bottom: 0;">
					<div class="widget-head">
						<h3>
							<span>新生分班</span>
							<p style="float:right;" class="btn_link">
								<a class="a2" href="javascript:void(0)">保存</a>
							</p>
						</h3>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid white">
			<div class="fenban_left">
				<div class="top">
					<span>索引</span>
				</div>
				<div class="bottom">
					<div class="open"><a href="javascript:void(0)" class="school">广州市第三中学</a></div>
					<div class="bj_rs">
					<ul>
						<li class="li_div open_2">
							<a href="javascript:void(0)" class="nj">初一</a>
							<ul class="ban_ul">
								<li><a class="on" href="javascript:void(0)">初一（1）班（0）</a></li>
								<li><a href="javascript:void(0)">初一（2）班（40）</a></li>
								<li><a href="javascript:void(0)">初一（3）班（40）</a></li>
								<li><a href="javascript:void(0)">初一（4）班（40）</a></li>
								<li><a href="javascript:void(0)">初一（5）班（40）</a></li>
								<li><a href="javascript:void(0)">初一（6）班（40）</a></li>
								<li><a href="javascript:void(0)">未分班新生（50）</a></li>
							</ul>
						</li>
						<li class="li_div close_2">
							<a href="javascript:void(0)" class="nj">初二</a>
							<ul style="display:none" class="ban_ul">
							<li><a class="on" href="javascript:void(0)">初二（1）班（0）</a></li>
							<li><a href="javascript:void(0)">初二（2）班（40）</a></li>
							<li><a href="javascript:void(0)">初二（3）班（40）</a></li>
							<li><a href="javascript:void(0)">初二（4）班（40）</a></li>
							<li><a href="javascript:void(0)">初二（5）班（40）</a></li>
							<li><a href="javascript:void(0)">初二（6）班（40）</a></li>
							<li><a href="javascript:void(0)">未分班新生（50）</a></li>
						</ul>
						</li>
						<li class="li_div close_2">
							<a href="javascript:void(0)" class="nj">初三</a>
							<ul style="display:none" class="ban_ul">
							<li><a class="on" href="javascript:void(0)">初三（1）班（0）</a></li>
							<li><a href="javascript:void(0)">初三（2）班（40）</a></li>
							<li><a href="javascript:void(0)">初三（3）班（40）</a></li>
							<li><a href="javascript:void(0)">初三（4）班（40）</a></li>
							<li><a href="javascript:void(0)">初三（5）班（40）</a></li>
							<li><a href="javascript:void(0)">初三（6）班（40）</a></li>
							<li><a href="javascript:void(0)">未分班新生（50）</a></li>
						</ul>
						</li>
					</ul>
						
					</div>
				</div>
			</div>
			<div class="fenban_right">
				<div class="xs_div">
					<div class="top"><p>当前班级：初一（1）班</p><p>班级人数：41</p></div>
					<div class="xs_list">
						<table class="table table-bordered responsive  white">
							<thead>
								<tr><th>男女比例</th><th>民族比例</th><th>成绩平均分</th></tr>
								<tr><th><span class="nan">男</span>：22%（11人）<span class="nv">女</span>：78%（39人）</th><th><span>汉</span>：78%（39人）<span class="shao">少数民族</span>：22%（11人）</th><th style="position:relative">
								<div class="fs_div">语文:80  数学:80  英语:80 科学:80  历史:80  地理:80</div><a href="javascript:void(0)" style="position:absolute" class="op">展开</a> </th></tr>
							</thead>
						</table>
						<div class="xuesheng">
						<table class="table table-bordered responsive white">
							<thead>
								<tr><th style="width:34px;"><input type="checkbox"></th><th style="width:34px;">序号</th><th>学号</th><th style="width:62px;">姓名</th><th style="width:34px;">性别</th><th>国际学籍号</th></tr>
							</thead>
							<tbody>
								<tr><td><input type="checkbox"></td><td>1</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>2</td><td>201503250101</td><td>李卓函</td><td class="nv">女</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>3</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>4</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>5</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>6</td><td>201503250101</td><td>李卓函</td><td class="nv">女</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>7</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>8</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>9</td><td>201503250101</td><td>李卓函</td><td class="nv">女</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>10</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>11</td><td>201503250101</td><td>李卓函</td><td class="nv">女</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>12</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>13</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>14</td><td>201503250101</td><td>李卓函</td><td class="nv">女</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>15</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>16</td><td>201503250101</td><td>李卓函</td><td class="nv">女</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>17</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>18</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>19</td><td>201503250101</td><td>李卓函</td><td class="nv">女</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>20</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>21</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
							</tbody>
						</table>
						</div>
					</div>
				</div>
				 <div class="hj_hc">
					<a href="javascript:void(0)">换出  &gt;</a>
					<a href="javascript:void(0)">换进  &lt;</a>
				</div> 
				<div class="xs_div">
					<div class="top"><p>当前学生：<select style="margin-bottom:0"><option>初一（1）班</option><option>初一（2）班</option><option>初一（3）班</option></select></p><p>班级人数：0</p></div>
					<div class="xs_list">
						<table class="table table-bordered responsive  white">
							<thead>
								<tr><th>男女比例</th><th>民族比例</th><th>成绩平均分</th></tr>
								<tr><th><span class="nan">男</span>：22%（11人）<span class="nv">女</span>：78%（39人）</th><th><span>汉</span>：78%（39人）<span class="shao">少数民族</span>：22%（11人）</th><th style="position:relative">
								<div class="fs_div">语文:80  数学:80  英语:80 科学:80  历史:80  地理:80</div><a href="javascript:void(0)" style="position:absolute" class="op">展开</a> </th></tr>
							</thead>
						</table>
						<div class="xuesheng">
						<table class="table table-bordered responsive white">
							<thead>
								<tr><th style="width:34px;"><input type="checkbox"></th><th style="width:34px;">序号</th><th>学号</th><th style="width:62px;">姓名</th><th style="width:34px;">性别</th><th>学籍号</th></tr>
							</thead>
							<tbody>
							<tr><td><input type="checkbox"></td><td>1</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>2</td><td>201503250101</td><td>李卓函</td><td class="nv">女</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>3</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>4</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>5</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>6</td><td>201503250101</td><td>李卓函</td><td class="nv">女</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>7</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>8</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>9</td><td>201503250101</td><td>李卓函</td><td class="nv">女</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>10</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>11</td><td>201503250101</td><td>李卓函</td><td class="nv">女</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>12</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>13</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>14</td><td>201503250101</td><td>李卓函</td><td class="nv">女</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>15</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>16</td><td>201503250101</td><td>李卓函</td><td class="nv">女</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>17</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>18</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>19</td><td>201503250101</td><td>李卓函</td><td class="nv">女</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>20</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
								<tr><td><input type="checkbox"></td><td>21</td><td>201503250101</td><td>李卓函</td><td class="nan">男</td><td>G330402200706250018</td></tr>
							</tbody>
						</table>
						</div>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<script>
	$(function(){
		$(".fenban_left .bottom .bj_rs .ban_ul li a").click(function(){
			$(".fenban_left .bottom .bj_rs .ban_ul li a").removeClass("on");
			$(this).addClass("on");
		});
		$(".fenban_left .bottom .bj_rs ul").on("click",".open_2 .nj",function(event){
			event.stopPropagation();
			$(this).next().hide();
			$(this).parent().removeClass("open_2").addClass("close_2")
		});
		$(".fenban_left .bottom .bj_rs ul").on("click",".close_2 .nj",function(event){
			event.stopPropagation();
			$(this).next().show();
			$(this).parent().removeClass("close_2").addClass("open_2")
		});
		$(".fenban_left .bottom").on("click",".open .school",function(event){
			event.stopPropagation();
			$(this).parent().next().hide();
			$(this).parent().removeClass("open").addClass("close_1")
		});
		$(".fenban_left .bottom").on("click",".close_1 .school",function(event){
			event.stopPropagation();
			$(this).parent().next().show();
			$(this).parent().removeClass("close_1").addClass("open")
		});
		var wid=$(".fenban_right").css("width");
		var w=(parseInt(wid)-140)*0.5;
		$(".fenban_right .xs_div").css("width",w);
	});
	</script>
</body>
</html>
