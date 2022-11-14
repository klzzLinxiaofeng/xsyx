package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.MoralEvaluationItem;
/**
 * MoralEvaluationItem
 * @author AutoCreate
 *
 */
public class MoralEvaluationItemCondition extends MoralEvaluationItem {
	private static final long serialVersionUID = 1L;
	
	/*
	 * 关联pj_moral_evaluation_result.item_id
	 */
	private Integer itemId;
	
	/*
	 * 关联pj_moral_evaluation_result.evaluation_id
	 */
	private Integer evaluationId;
	
	/*
	 * 关联pj_moral_evaluation_result.result
	 */
	private String result;
	
	/*
	 * 关联pj_moral_evaluation_result.remark
	 */
	private String resultRemark;

	/**
	 * 获得itemId
	 * @return
	 */
	public Integer getItemId() {
		return itemId;
	}

	/**
	 * 设置itemId
	 * @param itemId
	 */
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	/**
	 * 获得evaluationId
	 * @return
	 */
	public Integer getEvaluationId() {
		return evaluationId;
	}

	/**
	 * 设置evaluationId
	 * @param evaluationId
	 */
	public void setEvaluationId(Integer evaluationId) {
		this.evaluationId = evaluationId;
	}

	/**
	 * 获得result
	 */
	public String getResult() {
		return result;
	}
	
	/**
	 * 设置result
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * 获得resultRemark
	 */
	public String getResultRemark() {
		return resultRemark;
	}

	/**
	 * 设置resultRemark
	 */
	public void setResultRemark(String resultRemark) {
		this.resultRemark = resultRemark;
	}

}