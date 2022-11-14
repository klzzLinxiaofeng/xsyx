<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/stepy.jquery.js"></script>
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">
table thead th{width:24px;}
.table thead th, .table tbody td{text-align:center;vertical-align:middle}
.table tbody .red{background-color:#df5a5a;color:#fff;}
.table{margin-bottom:0}
.table tbody td,.table thead th{width:30px;}
.jiaqi{padding:5px;float:left;widt:270px;}
.yf{
	width: 29px;float:left;padding:3px;border-top:1px solid #dddddd;
}
.yf p{
  height: 76px;
    margin-bottom: 0;
    padding-top: 52px;px;}
</style>
</head>
<body>
	<div class="container-fluid">
	<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-calculator" name="icon"/>
			<jsp:param value="校历管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid ">
			<div class="span12"  style="height: 43px;">
				<div class="content-widgets white" style="margin-bottom: 0;">
					<div class="widget-head">
						<h3>
							<span>新建校历</span>
							<span style="padding-left:20px;">选择：<select style="margin-bottom:0"  disabled="disabled"><option>xx学校2014-2015学年下学期校历</option><option>xx学校2014-2015学年下学期校历</option><option>xx学校2014-2015学年下学期校历</option></select></span>
							<p style="float:right;" class="btn_link">
						<a class="a2" href="javascript:void(0)"><i class="fa fa-plus"></i>导出EXCEL</a>
						<a class="a4" href="javascript:void(0)"><i class="fa fa-plus"></i>新建校历</a>
					</p>
						</h3>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="calculator">
				<div class="cal_top">
					<div class="cal_3">
						<p><input type="text" value="XX学校2014-2015学年下学期校历" style="width:206px;"></p>
						<p>本学期时间段：<input type="text" value="2015-03-01" />-<input type="text" value="2015-07-18" /></p>
					</div>
					<div class="cal_2">
						<button class="btn btn-warning">保存</button>
						<button class="btn">取消</button>
					</div>
				</div>
				<div class="cal_main">

					<table class="table responsive table-bordered">
						<thead>
							<tr>
								<th>周序</th>
								<th>月份</th>
								<th>一</th>
								<th>二</th>
								<th>三</th>
								<th>四</th>
								<th>五</th>
								<th>六</th>
								<th>七</th>
								<th style="width: 286px;">备注</th>
							</tr>
						</thead>
					</table>
					<div class="white">
					<div style="float:left;width:311px;">
					<div class="yue">
						<table class="table responsive table-bordered" style="width: 35px;float:left">
							<tbody class="white">
								<tr><td >1</td></tr>
								<tr><td>2</td></tr>
								<tr><td>3</td></tr>
								<tr><td>4</td></tr>
								<tr><td>5</td></tr>
							</tbody>
						</table>
						<div class="yf" >
							<p>三月</p>
						</div>
						<table class="table responsive table-bordered" style="width:241px;float:right">
							<tbody class="white">
								<tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td></tr>
								<tr><td>8</td><td>9</td><td>10</td><td>11</td><td>12</td><td>13</td><td>14</td></tr>
								<tr><td>15</td><td>16</td><td>17</td><td>18</td><td>19</td><td>20</td><td>21</td></tr>
								<tr><td>22</td><td>23</td><td>24</td><td>25</td><td>26</td><td>27</td><td>28</td></tr>
								<tr><td>29</td><td>30</td><td>31</td><td></td><td></td><td></td><td></td></tr>
							</tbody>
						</table>
						<div class="clear"></div>
					</div>
					<div class="yue">
						<table class="table responsive table-bordered" style="width: 35px;float:left">
							<tbody class="white">
								<tr><td >6</td></tr>
								<tr><td>7</td></tr>
								<tr><td>8</td></tr>
								<tr><td>9</td></tr>
								<tr><td>10</td></tr>
							</tbody>
						</table>
						<div class="yf" >
							<p>四月</p>
						</div>
						<table class="table responsive table-bordered" style="width:241px;float:right">
							<tbody class="white" >
								<tr><td></td><td></td><td></td><td>1</td><td>2</td><td>3</td><td>4</td></tr>
								<tr><td class="red">5</td><td class="red">6</td><td>7</td><td>8</td><td>9</td><td>10</td><td>11</td></tr>
								<tr><td>12</td><td>13</td><td>14</td><td>15</td><td>16</td><td>17</td><td>18</td></tr>
								<tr><td>19</td><td>20</td><td>21</td><td>22</td><td>23</td><td>24</td><td>25</td></tr>
								<tr><td>26</td><td>27</td><td>28</td><td>29</td><td>30</td><td></td><td></td></tr>
							</tbody>
						</table>
						<div class="clear"></div>
					</div>
					<div class="yue">
						<table class="table responsive table-bordered" style="width: 35px;float:left">
							<tbody class="white">
								<tr><td >11</td></tr>
								<tr><td>12</td></tr>
								<tr><td>13</td></tr>
								<tr><td>14</td></tr>
								<tr><td>15</td></tr>
								<tr><td>16</td></tr>
							</tbody>
						</table>
						<div class="yf"  >
							<p>五月</p>
						</div>
						<table class="table responsive table-bordered" style="width:241px;float:right">
							<tbody class="white">
								<tr><td></td><td></td><td></td><td></td><td></td><td class="red">1</td><td class="red">2</td></tr>
								<tr><td class="red">3</td><td>4</td><td>5</td><td>6</td><td>7</td><td>8</td><td>9</td></tr>
								<tr><td>10</td><td>11</td><td>12</td><td>13</td><td>14</td><td>15</td><td>16</td></tr>
								<tr><td>17</td><td>18</td><td>19</td><td>20</td><td>21</td><td>22</td><td>23</td></tr>
								<tr><td>24</td><td>25</td><td>26</td><td>27</td><td>28</td><td>29</td><td>30</td></tr>
								<tr><td>31</td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
							</tbody>
						</table>
						<div class="clear"></div>
					</div>
					</div>
						<div class="jiaqi">
							<textarea style="height: 107px; width: 227px; margin-bottom: 0"></textarea>
						</div>
						<div class="clear"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
	</script>
</body>
</html>
