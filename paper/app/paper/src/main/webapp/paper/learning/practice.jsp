<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!doctype html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="/css/common/paper/bag.css">
<title>随堂练</title>
<script>
function show(){
var date = new Date(); //日期对象
var now = "";
now=date.getHours();
now= now + date.getSeconds();
document.getElementById("time").innerHTML = now; //div的html是now这个字符串
}
</script> 
</head>

<body>
	<div class="main_content_2">
		<div class="left">
			
			<div class="shijuan"><div class="title_2">
				<p>考试</p>
				<div class="course">
					<h1 class="i" style="width:10%"></h1>
					<span>题目已完成2/15</span>
				</div>
			</div>
			<div class="test">
				<p>2013年普通高等学校招生全国统一考试（广东卷）：</p>
				<span>语文</span>
			</div>
			</div>
			<div class="paper" >
			<div class="option1" style="display:none;">
				<div class="problem">
					<span>2</span>
					<p><strong>【单选题】</strong>四大名著中《红楼梦》的作者是？</p>
				</div>
				<div class="option">
					<ul>
						<li class="i1">
							<a href="">
							<span class="option1"></span>
							<p>1</p>
							罗贯中
							</a>
						</li>
						<li class="i1">
							<a href="">
							<span class="option1"></span>
							<p>2</p>
							曹雪芹
							</a>
						</li>
						<li class="i1">
							<a href="">
							<span class="option1"></span>
							<p>3</p>
							施耐庵
							</a>
						</li>
						<li class="i1">
							<a href="">
							<span class="option1"></span>
							<p>4</p>
							吴承恩
							</a>
						</li>
					</ul>
				</div>
				</div>
				<div class="filling1" >
				<div class="problem">
					<span>2</span>
					<p><strong>【填空题】</strong>四大名著的作者分别是？</p>
				</div>
				<div class="filling">
					<div class="gap">罗贯中、曹雪芹、施耐庵、吴承恩</div>
					<div class="refer"><input type="button" value="提交" id="commentBtn"></div>
				</div>
				</div>
				<div class="answer1" style="display:none">
				<div class="problem">
					<span>2</span>
					<p><strong>【简答题】</strong>四大名著的作者分别是？</p>
				</div>
				
				<div class="answer">
					<div class="result">答：罗贯中、曹雪芹、施耐庵、吴承恩</div>
					<div class="refer"><input type="button" value="提交" id="commentBtn"></div>
				</div>
				</div>
				<div class="end">
					<div class="e1">
						<a href=""><h1 class="pre"></h1></a>
						
						<a href=""><h1 class="next"></h1></a>
					</div>
					
				</div>
			</div>
			
		
		</div>
		<div class="right">
			<div class="time" style="background-color: #E6F9FD;">
				<p id="time">11:20</p>
			</div>
			<div class="choose">
				<ul>
					<a href="#"><li class="a1 " style="background-color: #FFFFFF;">
						<div class="active">
							<p>多题模式</p>
						</div>
					</li></a>
					<a href="#"><li class="a2" style="background-color: #FFFFFF;">
						<div>
							<p>暂停</p>
						</div>
					</li></a>
					<a href="#"><li class="a3" style="background-color: #FFFFFF;">
						<div>
							<p>我要交卷</p>
						</div>
					</li></a>
					<a href="#"><li class="a4" style="background-color: #FFFFFF;">
						<div>
							<p>回到顶部</p>
						</div>
					</li></a>
				</ul>
			</div>
		</div>
	<div class="clear"></div>
	<div class="bottom">
		<a class="a" href="#"><div class="close"></div></a>
		<div class="num">
			<ul>
				<li class="active"><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				<li><a href="#">6</a></li>
				<li><a href="#">7</a></li>
				<li><a href="#">8</a></li>
				<li><a href="#">9</a></li>
				<li><a href="#">10</a></li>
				<li><a href="#">11</a></li>
				<li><a href="#">12</a></li>
				<li><a href="#">13</a></li>
				<li><a href="#">14</a></li>
				<li><a href="#">15</a></li>
				<li><a href="#">16</a></li>
				<li><a href="#">17</a></li>
				<li><a href="#">18</a></li>
				<li><a href="#">19</a></li>
				<li><a href="#">20</a></li>
				<li><a href="#">21</a></li>
				<li><a href="#">22</a></li>
				<li><a href="#">23</a></li>
				<li><a href="#">24</a></li>
				<li><a href="#">25</a></li>
				<li><a href="#">26</a></li>
				<li><a href="#">27</a></li>
				<li><a href="#">28</a></li>
				<li><a href="#">29</a></li>
				<li><a href="#">30</a></li>
			</ul>
		</div>

	</div>
	</div>	
</body>
</html>

