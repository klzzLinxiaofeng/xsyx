<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/css/extra/coherent.css" rel="stylesheet">
<title>一卡通</title>
<style type="text/css">
	.table th, .table td{
		border-top:0 none;
		}
</style>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="一卡通" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="oa_top">
						<ul class="top_ul">
				             <li><a href="javascript:void(0)" class="on">总体统计</a></li>
				            <li><a href="javascript:void(0)" >清单查询</a></li>
				        </ul>
					</div>
					<div class="middle">
        <ul>
            <li>
                <h3>2015年3月份一卡通统计</h3>
                <table class="table">
                    <tr>
                        <td>时　　间：</td>
                        <td class="term">2015年3月1日-2015年3月22日</td>
                        <td>更新时间：</td>
                        <td class="term">2015年3月22日 16:35</td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>总  人 数：</td>
                        <td><b>499</b></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr><tr>
                        <td>充值人次：</td>
                        <td><b>502</b></td>
                        <td>充值金额：</td>
                        <td><b>¥150600</b></td>
                        <td>人均充值（月）：</td>
                        <td><b>¥301.9</b></td>
                    </tr>
                    <tr>
                        <td>消费人次：</td>
                        <td><b>6686</b></td>
                        <td>消费金额：</td>
                        <td><b>¥120153.5</b></td>
                        <td>人均消费（月）：</td>
                        <td><b>¥240.8</b></td>
                    </tr>
                    <tr>
                        <td>提现人次：</td>
                        <td><b>20</b></td>
                        <td>提现金额：</td>
                        <td><b>¥500</b></td>
                        <td>人均提现（人）：</td>
                        <td><b>¥25</b></td>
                    </tr>
                </table>
            </li>
            <li>
                <h3>2015年2月份一卡通统计</h3>
                <table class="table">
                    <tr>
                        <td>时　　间：</td>
                        <td class="term">2015年2月1日-2015年2月28日</td>
                        <td>更新时间：</td>
                        <td class="term">2015年3月1日 12:00</td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>总  人 数：</td>
                        <td><b>499</b></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr><tr>
                        <td>充值人次：</td>
                        <td><b>566</b></td>
                        <td>充值金额：</td>
                        <td><b>¥149700</b></td>
                        <td>人均充值（月）：</td>
                        <td><b>¥300</b></td>
                    </tr>
                    <tr>
                        <td>消费人次：</td>
                        <td><b>32934</b></td>
                        <td>消费金额：</td>
                        <td><b>¥149200</b></td>
                        <td>人均消费（月）：</td>
                        <td><b>¥298.9</b></td>
                    </tr>
                    <tr>
                        <td>提现人次：</td>
                        <td><b>20</b></td>
                        <td>提现金额：</td>
                        <td><b>¥500</b></td>
                        <td>人均提现（人）：</td>
                        <td><b>¥25</b></td>
                    </tr>
                </table>
            </li>
            <li>
                <h3>2015年1月份一卡通统计</h3>
                <table class="table">
                    <tr>
                        <td>时　　间：</td>
                        <td class="term">2015年1月1日-2015年1月30日</td>
                        <td>更新时间：</td>
                        <td class="term">2015年2月1日 12:00</td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>总  人 数：</td>
                        <td><b>499</b></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr><tr>
                        <td>充值人次：</td>
                        <td><b>566</b></td>
                        <td>充值金额：</td>
                        <td><b>¥149700</b></td>
                        <td>人均充值（月）：</td>
                        <td><b>¥300</b></td>
                    </tr>
                    <tr>
                        <td>消费人次：</td>
                        <td><b>32934</b></td>
                        <td>消费金额：</td>
                        <td><b>¥149200</b></td>
                        <td>人均消费（月）：</td>
                        <td><b>¥298.9</b></td>
                    </tr>
                    <tr>
                        <td>提现人次：</td>
                        <td><b>20</b></td>
                        <td>提现金额：</td>
                        <td><b>¥500</b></td>
                        <td>人均提现（人）：</td>
                        <td><b>¥25</b></td>
                    </tr>
                </table>
            </li>
        </ul>
    </div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>