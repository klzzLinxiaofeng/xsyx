
function setXtjyTree(toolId){
    $('#subjectCatalog'+toolId).tree({
        data : treeData(null),
        onClick: function(node){
//            var sparent
//            var pparent;
//            var gparent
//            if(node.attributes.type == 'subject'){
//                fileSearch(node.attributes.code,"","","","","","",$('#xtjySearchResourcesSuffix_'+toolId).val(),toolId);
//            }else if(node.attributes.type == 'publish'){
//                sparent = $(this).tree('getParent', node.target);
//                fileSearch(sparent.attributes.code,node.attributes.code,"","","","","",$('#xtjySearchResourcesSuffix_'+toolId).val(),toolId);
//            }else if(node.attributes.type == 'grade'){
//                pparent = $(this).tree('getParent', node.target);
//                sparent = $(this).tree('getParent', pparent.target);
//                fileSearch(sparent.attributes.code,pparent.attributes.code,node.attributes.code,"","","","",$('#xtjySearchResourcesSuffix_'+toolId).val(),toolId);
//            }else if(node.attributes.type == 'volume'){
//                gparent = $(this).tree('getParent', node.target);
//                pparent = $(this).tree('getParent', gparent.target);
//                sparent = $(this).tree('getParent', pparent.target);
//                fileSearch(sparent.attributes.code,pparent.attributes.code,gparent.attributes.code,node.attributes.code,"","","",$('#xtjySearchResourcesSuffix_'+toolId).val(),toolId);
//            }
            if(node.attributes.type != 'volume'){
                var chlidren = $(this).tree('getChildren', node.target);
                if(chlidren.length <= 0){
                    var trees = treeData(node);
                    if(trees.length>0){
                        append(node,trees,'subjectCatalog'+toolId,false);
                        $(this).tree('toggle',node.target);
                    }
                }
            }
            if(node.attributes.type == "volume"){
                unitSearch(node.id,toolId);
            }
        },
        onBeforeExpand: function(node){
            if (node.attributes.type != "volume") {
                var children = $(this).tree('getChildren', node.target);
                if(children.length<=0){
                    var trees = treeData(node);
                    if(trees.length>0){
                        append(node,trees,'subjectCatalog'+toolId,false);
                        $(this).tree('toggle',node.target);
                    }
                }
            }
        }
    });
}


//树的数据
function treeData(currentNode,manager){
    var cNodeId = "";
    if(currentNode != null){
        cNodeId = currentNode.id;
    }
    var url;
    if(manager!=null&&manager!=""){
        url ="/manager/catalogjson/catalogCommonAction_findCataByParId.action";
    }else{
        url ="/common/portal/json/frontCommonAction_findCataByParId.action";
    }
    var data = {
        "formMap.parentId":cNodeId
    };
    var data = ajaxSubmit(url,data);
    var nodes = new Array();
    if(data.dataList!=null){
        $.each(data.dataList,function(i,node){
            var stateName = "open";
            if(node.hasChild)stateName = "closed";
            var newNode = {
                id:node.id,
                text:node.name,
                attributes:{
                    type:node.type,
                    infoId:node.infoId,
                    sort:node.sort,
                    code:node.code
                },
                state:stateName
            };
            nodes[i] = newNode;
        });
    }
    return nodes;
}
//扩充节点
function append(node,trees,treeId,isComboTree){
    if(isComboTree!=null&&isComboTree==true){
        $('#'+treeId).combotree('tree').tree('append',{
            parent: (node?node.target:null),
            data:trees
        });
    }else{
        $('#'+treeId).tree('append',{
            parent: (node?node.target:null),
            data:trees
        });
    }
}

/**
AJAX同步请求，不带数据缓存功能，返回请求数据
 */
function ajaxSubmit(url,data){
    var msg = null;
    jQuery.ajax({
        type: "POST",
        url: url,
        cache: false,
        async: false,
        data: data,
        success: function(treeHtml){
            msg = treeHtml;
        }
    });
    return msg;
}
function unitSearch(cNodeId,toolId){
    firstLevel(cNodeId,toolId);
}

//显示第一层
function firstLevel(id,toolId){
    var url ="/common/portal/json/frontCommonAction_findCataByParId.action";
    var data = {
        "formMap.parentId":id
    };
    var data = ajaxSubmit(url,data);             
    $("#unitList"+toolId).html(createFirstLevel(data,toolId));
}
//创建第二层
function createFirstLevel(data,toolId){
    var firstHtml="";
    $.each(data.dataList,function(i,note){
        if(note.hasChild) {
            firstHtml+="<li id='unitList_li_"+note.id+"'><a href=\"javascript:listUrl('"+note.id+"','"+note.code+"','"+toolId+"');\""+">"+note.name+"</a><ul id='unitList_li_ul_"+note.id+"'></ul></li>";
        }else{
            firstHtml+="<li><a href=\"javascript:fileSearch('','','','','"+note.code+"','','','"+$('#xtjySearchResourcesSuffix_'+toolId).val()+"','"+toolId+"');\">"+note.name+"</a></li>";
        }	
    });
    return firstHtml;
}
//打开第二层
function listUrl(id,code,toolId){
    hideAllfirst(toolId);
    secLevel(id,toolId);
    var menu_list_li_id="#unitList_li_"+id;
    $(menu_list_li_id+" ul").show();
    $(menu_list_li_id).addClass("cur");
    fileSearch("","","","",code,"","",$('#xtjySearchResourcesSuffix_'+toolId).val(),toolId);
}
//显示第二层
function secLevel(id,toolId){
	
    var url ="/common/portal/json/frontCommonAction_findCataByParId.action";
    var data = {
        "formMap.parentId":id
    };
    var data = ajaxSubmit(url,data);
    var selectUl="#unitList_li_ul_"+id;
    $(selectUl).html(createSecLevel(data,toolId));
}
   
//创建第二层
function createSecLevel(data,toolId){
  
    var firstHtml="";
    $.each(data.dataList,function(i,note){
        firstHtml+="<li id='unitList_li_li_"+note.id+"'><a href=\"javascript:fileSearch('','','','','','"+note.code+"','','"+$('#xtjySearchResourcesSuffix_'+toolId).val()+"','"+toolId+"');\""+">"+note.name+"</a></li>";
  		
    });
    
    return firstHtml;
}

function hideAllfirst(toolId){
    $("#unitList"+toolId+" li ul").each(function(){
        $(this).hide();
    });
	  
    $("#unitList"+toolId+" .cur").each(function(){
        $(this).removeClass("cur");
    });
}

//全文检索
function fileSearch(subjectCode,publishCode,gradeCode,volumeCode,unitCode,sectionCode,keyWord,suffix,toolId){
    var url = '/common/lads/ladsAction_resourcesHouse.action?r='+Math.random()+"&sysType="+$("#sysType").val();
    var param ='';
    var data = {};
    if(subjectCode!=null&&subjectCode!=""){
        param = param+'&subjectCode='+subjectCode;
    }
    if(gradeCode!=null&&gradeCode!=""){
        param = param+'&gradeCode='+gradeCode;
    }
    if(volumeCode!=null&&volumeCode!=""){
        param = param+'&volumeCode='+volumeCode;
    }
    if(unitCode!=null&&unitCode!=""){
        param = param+'&unitCode='+unitCode;
    }
    if(sectionCode!=null&&sectionCode!=""){
        param = param+'&sectionCode='+sectionCode;
    }
    if(publishCode!=null&&publishCode!=""){
        param = param+'&publishCode='+publishCode;
    }
    if(keyWord!=null&&keyWord!=""){
        data["keyWord"] = $.trim(keyWord);
    }
    if(suffix!=null&&suffix!=""){
        data["suffix"] = suffix
    }
    data["toolId"]= toolId;
    $("#xtjySearchResources_"+toolId).html('<img src="/image/teacher/loading.gif" alt="加载中"/>文件加载中,请耐心等待');
    $("#xtjySearchResources_"+toolId).load(url+param,data);
}

