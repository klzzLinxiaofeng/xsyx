<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学校详细信息</title>
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<style>
	.myerror {
	color: red !important;
	width: 34%;
	display: inline-block;
	padding-left: 10px;
}
</style>
</head>
<body>
	
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
            <div class="content-widgets white">
	            <div class="widget-head">
					<h3>学校基本信息</h3>
				</div>
				<div class="light_grey"></div>
                <div class="content-widgets">
                    <div class="widget-container">
                       	<div class="form-horizontal_1">
                       	<form action="javascript:void(0);" id="modifySchoolForm" name="modifySchoolForm">
                       		<table class="table table-bordered responsive">
                       			<tbody>
                       				<tr>
                       					<td rowspan="6">基本信息</td>
                       					<td>
                       						<div class="control-group">
												<label class="control-label"><span class="red">*</span>学校名称：</label>
												<div class="controls">
													${school.name}
												</div>
											</div>
											<div class="control-group">
												<label class="control-label"><span class="red">*</span>英文名称：</label>
												<div class="controls">
													${school.englishName}
												</div>
											</div>
                       					</td>
                       				</tr>
                       				<tr><td><div class="control-group">
												<label class="control-label"><span class="red">*</span>学校代码：</label>
												<div class="controls">
													${school.code}
												</div>
											</div></td>
										</tr>
										<tr>
                       					<td>
                       						<div class="control-group">
												<label class="control-label"><span class="red">*</span>学校类别：</label>
												<div class="controls">
													<jcgc:cache tableCode="JY-XXLB" value="${school.schoolType}"></jcgc:cache>
												</div>
											</div>
											<div class="control-group">
												<label class="control-label"><span class="red">*</span>办学类别:</label>
												<div class="controls">
													<jcgc:cache tableCode="JY-BXLB" value="${school.runningType}"></jcgc:cache>
												</div>
											</div>
                       					</td>
                       					</tr>
                       					<tr>
                       					<td>
                       						<div class="control-group">
												<label class="control-label">所在地城乡类型:</label>
												<div class="controls">
													<jcgc:cache tableCode="JY-SZDCXLB" value="${school.cityType}"></jcgc:cache>
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">所在地经济属性：</label>
												<div class="controls">
													<jcgc:cache tableCode="JY-SZDJJSX" value="${school.econonyType}"></jcgc:cache>
												</div>
											</div>
                       					</td>
                       				</tr>
                       				<tr>
                       					<td>
                       						<div class="control-group">
												<label class="control-label"><span class="red">*</span>学段:</label>
												<div class="controls">
													${school.stageScope}
												</div>
											</div>
											<div class="control-group">
												<label class="control-label"><span class="red">*</span>学制：</label>
												<div class="controls">
													<jcgc:cache tableCode="XY-JY-XZ" value="${school.schoolSystem}"></jcgc:cache>
												</div>
											</div>
                       					</td>
                       				</tr>
                       				<tr>
                       					<td>
                       						<div class="control-group" style="width:100%;">
												<label class="control-label"><span class="red">*</span>所在地：</label>
												<div class="controls" style="width:100%">
													${school.province}
													${school.city}
													${school.district}
													${school.street}
												</div>
											</div>
                       					</td>
                       				</tr>
                       				<tr>
                       					<td rowspan="6">联系信息</td>
                       					<td>
                       						<div class="control-group">
												<label class="control-label">电子邮箱：</label>
												<div class="controls">
													${school.email}
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">邮编：</label>
												<div class="controls">
													${school.zipcode}
												</div>
											</div>
                       					</td>
                       				</tr>
                       				<tr><td>
                       					<div class="control-group">
												<label class="control-label"><span class="red">*</span>联系电话：</label>
												<div class="controls">
													${school.telephone}
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">传真：</label>
												<div class="controls">
													${school.fax}
												</div>
											</div>
                       				</td></tr>
                       				<tr>
                       					<td>
                       						<div class="control-group" style="width:100%">
												<label class="control-label">学校主页：</label>
												<div class="controls">
													${school.websit}
												</div>
											</div>
										</td>
									</tr>
									<tr><td><div class="control-group" style="width:100%">
												<label class="control-label"><span class="red">*</span>学校地址：</label>
												<div class="controls">
													${school.address}
												</div>
											</div></td>
										</tr>
										<tr><td>
                       					<div class="control-group">
												<label class="control-label">校长姓名：</label>
												<div class="controls">
													${school.schoolMaster}
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">校庆日：</label>
												<div class="controls">
													<fmt:formatDate pattern="yyyy-MM-dd" value="${school.decorationDay}"></fmt:formatDate>
												</div>
											</div>
                       				</td></tr>
                       				<tr><td>
                       					<div class="control-group">
												<label class="control-label">党委负责人:</label>
												<div class="controls">
													${school.partyCommittee}
												</div>
											</div>
											<div class="control-group">
												<label class="control-label"></label>
												<div class="controls"></div>
											</div>
                       				</td></tr>
                       				<tr>
                       					<td rowspan="7">其他信息</td>
                       					<td>
                       						<div class="control-group">
												<label class="control-label">建校年月:</label>
												<div class="controls">
													${school.buildDate}
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">招生范围:</label>
												<div class="controls">
													${school.enrollScope}
												</div>
											</div>
                       					</td>
                       				</tr>
                       				
                       				<tr>
                       					<td>
                       						<div class="control-group">
												<label class="control-label">法定代表人：</label>
												<div class="controls">
													${school.corporation}
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">法人证书号：</label>
												<div class="controls">
													${school.certificate}
												</div>
											</div>
                       					</td>
                       				</tr>
                       				<tr>
                       					<td>
                       						<div class="control-group">
												<label class="control-label">主教学语言：</label>
												<div class="controls">
													${school.mainLanguage}
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">行政区划代码：</label>
												<div class="controls">
													${school.regionCode}
												</div>
											</div>
                       					</td>
                       				</tr>
                       				<tr>
                       					<td>
                       						<div class="control-group">
												<label class="control-label">组织机构代码：</label>
												<div class="controls">
													${school.institutionCode}
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">学校主管部门代码：</label>
												<div class="controls">
													${school.authority}
												</div>
											</div>
                       					</td>
                       				</tr>
                       				<tr>
                       					<td>
                       						<div class="control-group" style="width:100%">
												<label class="control-label">学校介绍：</label>
												<div class="controls">
													<textarea rows="4" cols="5" id="remark" name="remark" class="span12" disabled="disabled">${school.remark}</textarea>
												</div>
											</div>
										</td>
									</tr>
                       				<tr>
                       					<td>
                       						<div class="control-group" style="width:100%;">
												<label class="control-label">校徽：</label>
												<div class="controls">
													<div class="up_images">
														<img src="${pageContext.request.contextPath}/res/images/500.jpg" />
														<div class="right">
															<p>支持jpg、gif、png、bmp格式，大小：400*260</p>
															<a href="javascript:void(0)" class="up_img"></a>
														</div>
													</div>
												</div>
											</div>
                       					</td>
                       				</tr>
                       				<tr>
                       					<td>
                       						<div class="control-group" style="width:100%;">
												<label class="control-label">学校照片：</label>
												<div class="controls">
													<div class="up_images">
														<img src="${pageContext.request.contextPath}/res/images/500.jpg" />
														<div class="right">
															<p>支持jpg、gif、png、bmp格式，大小：400*260</p>
															<a href="javascript:void(0)" class="up_img"></a>
														</div>
													</div>
												</div>
											</div>
                       					</td>
                       				</tr>
                       			</tbody>
                       		</table>
                       	   </form>
                       	</div>
						<div class="clear"></div>
                    </div>
                </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>