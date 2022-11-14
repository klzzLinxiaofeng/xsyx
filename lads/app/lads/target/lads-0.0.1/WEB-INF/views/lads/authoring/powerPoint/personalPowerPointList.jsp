<%-- 
    Document   : personalPowerPointList
    Created on : 2013-8-24, 12:08:08
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib  prefix="resource" uri="/WEB-INF/resource" %>
<!DOCTYPE html>
<link rel="stylesheet" href="/css/common/lads/importXtjyResources/style.css" type="text/css" />
<s:i18n name="config/struts/locales/resources">
    <div class="totaltable tablediv0">
        <table style="width:100%">
            <thead>
                <tr>
                    <th width="1%"></th>
                    <th width="82%" >标题
                    </th>
                    <th width="9%" ><s:text name="updateTime" /></th>
                    <th width="8%"><s:text name="operation" /></th>
                </tr>
            </thead>
            <tbody>
                <s:if test="#request.pptList.isEmpty()">
                    <tr class="nothing">
                        <td align="center" colspan="5">
                            <s:text name="myxgxx" />
                        </td>
                    </tr>
                </s:if>
                <s:iterator status="st" value="#request.pptList" id="learningDesign">
                    <s:if test="#st.getIndex()%2!=0">
                        <tr>
                        </s:if>
                        <s:else>
                        <tr class="trbg">
                        </s:else>
                        <td align="center" valign="top"></td>
                        <td align="left" valign="middle">
                            <ul class="res-ctboxul">
                                <li class="mb">
                                    <b class="<resource:icon suffix="${learningDesign.resFile.suffix}"/>"></b>
                                    <a id="pptTitleA_${learningDesign.resFile.id}_<s:property value="#request.id"/>" href="/teacher/resources/frontResourcesAction_resourceDetail.action?resId=${learningDesign.id}" target="_blank" class="ress2">
                                        <s:property value="title"/>
                                    </a>
                                    <s:if test="#request.learningDesign.downloadFlag==1">
                                        <img alt="<s:text name='allowDownload' />" title="<s:text name='allowDownload' />" src="/css/teacher/skin/images/candownload.gif"/>
                                    </s:if>
                                    <p class="res-start res-val">
                                        <span><s:text name="preview" />：${learningDesign.viewCount}<s:text name="num" />&nbsp;&nbsp;</span>
                                        <span><s:text name="download" />：${learningDesign.downloadCount}<s:text name="num" /></span>
                                    </p>
                                    <p class="res-ctboxname" style="clear:left;"><span class="ct8"><s:text name="keywordLabel" />：</span>
                                        <resource:keyWord keyWord="${learningDesign.keyword}"/>
                                    </p>
                                </li>
                            </ul>            </td>
                        <td align="center" valign="middle"><s:date name="createTime" format="yyyy-MM-dd" ></s:date></td>
                        <td align="center" valign="middle">
                            <a href="javascript:void(0)" onclick="choosePowerPoint('${learningDesign.resFile.id}','<s:property value="#request.id"/>')" class="ress2 editfall">选择</a>
                            <a href="/teacher/resources/frontResourcesAction_resourceDetail.action?resId=${learningDesign.id}" target="_blank" class="ress2 editfall">预览</a>
                        </td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
    </div>
    <!--分页-->
        <s:include value="/module/common/lads/common/page.jsp">
            <s:param name="loadDiv" value='("#powerPointImportDiv_"+#request.id)'></s:param>
            <s:param name="url" value='"/common/lads/ladsPowerPointAction_loadPersonalPptList.action"'></s:param>
        </s:include>
</s:i18n>
