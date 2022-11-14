package platform.education.paper.vo;

import java.util.List;

import platform.education.paper.model.PaQuestion;

public class PaperQuestionTree {
      
   private Integer parentId;
   
   private List<PaperQuestionTree> childrens;
   //题组显示
   private String name;
   //题目类型
   private String type;
   //树节点Id;
   private Integer treeId;
   
   private Integer count;
   
   //
   private Integer nodeOrder;
   public Integer getNodeOrder() {
	return nodeOrder;
}

public void setNodeOrder(Integer nodeOrder) {
	this.nodeOrder = nodeOrder;
}

public Integer getCount() {
	return count;
}

public void setCount(Integer count) {
	this.count = count;
}

//
   private List<String>anwerList;
   //
   private List<String>exp;
   



public List<String> getExp() {
	return exp;
}

public void setExp(List<String> exp) {
	this.exp = exp;
}

public List<String> getAnwerList() {
	return anwerList;
}

public void setAnwerList(List<String> anwerList) {
	this.anwerList = anwerList;
}

public Integer getTreeId() {
	return treeId;
}

public void setTreeId(Integer treeId) {
	this.treeId = treeId;
}

private PaQuestionVo  obj;

public Integer getParentId() {
	return parentId;
}

public void setParentId(Integer parentId) {
	this.parentId = parentId;
}


public List<PaperQuestionTree> getChildrens() {
	return childrens;
}

public void setChildrens(List<PaperQuestionTree> childrens) {
	this.childrens = childrens;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public PaQuestionVo getObj() {
	return obj;
}

public void setObj(PaQuestionVo obj) {
	this.obj = obj;
}
}
