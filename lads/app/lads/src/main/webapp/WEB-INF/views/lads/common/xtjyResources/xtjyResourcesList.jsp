<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="#request.list!=null&&#request.list.size>0">
    <div class="inner" style="padding-top:0">
        <br/>
        <table width="98%" class="rs_platformTable" cellspacing="0" cellpadding="0">
            <tbody>
                <tr class="head">
                    <th class="gap" style="width: 475px">
                        标题
                    </th>
                    <th style="width: 100px" class="gap">
                        来源
                    </th>
                    <th style="width: 100px" class="gap">
                        下载次数
                    </th>
<!--                    <th style="width: 100px" class="gap">
                        大小
                    </th>-->
                    <th style="width: 190px" class="gap">
                        更新日期
                    </th>
                    <th style="width: 150px">
                        操作
                    </th>
                </tr>
                <s:iterator value="#request.list" id="file">
                    <tr>
                        <td class="title">
                            <b class="res-iconb icon-file res-<s:property value="fileType"/>" style="float:left;"></b>
                            <div style="width:320px; overflow: hidden; text-overflow: ellipsis; white-space:nowrap;float:left;">
                                <a target="_blank" href="/common/resources/frontResourcesAction_resourceDetail.action?resId=<s:property value="id" />">
                                    <s:property value="title" escape="false" />
                                    <s:if test='fileType.equals("jpg")||fileType.equals("jpeg")||fileType.equals("gif")||fileType.equals("png")'>
                                        <img onerror="thumbImgError(this,'<s:property value="@com.gzxtjy.resources.util.DocPathUtil@converHttpUrl(#request.httpUrl)"/>')"
                                             width="75px" height="75px" src="<s:property value="@com.gzxtjy.resources.util.DocPathUtil@converHttpUrl(#request.httpUrl)"/>"/>
                                    </s:if>
                                </a>
                            </div>
                        </td>
                        <td>
                            <a href="#"> 公共库 </a>
                        </td>
                        <td>
                            <a href="#"> <s:property value="downloadCount" /> </a>
                        </td>
<%--                        <td>
                            <s:property value="fileSize/1024" /> k
                        </td>--%>
                        <td>
                            <s:date name="updateTime" format="yyyy年MM月dd日" />
                        </td>
                        <td>
                            <a href="javascript:void(0)" onclick="chooseXtjyResources('<s:property value="fileId" />','<s:property value="#request.toolId"/>','<s:property value="#request.httpUrl"/>')" title="选择" >选择</a>
                            <a href="/common/resources/frontResourcesAction_resourceDetail.action?resId=<s:property value="id" />" target="_blank" title="预览" >预览</a>
                        </td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
    </div>
    <div class="totalpage" style="overflow:visible">
        <s:include value="/module/common/lads/common/page.jsp">
            <s:param name="loadDiv" value='("#xtjySearchResources_"+#request.toolId)'></s:param>
            <s:param name="url" value='"/common/lads/ladsAction_resourcesHouse.action"'></s:param>
        </s:include>
    </div>
</s:if>
<s:else>
    没有搜索到相关内容
</s:else>
