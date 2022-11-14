package platform.education.paper.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import platform.education.paper.model.PaPaper;
import platform.education.paper.model.PaperResult;

public interface PaPaperHandleService {


	PaperResult paperSplit(Integer userId, String filePath, String targerPath, String fileUuid, String schoolUuid,
			Integer relId, Integer type, Integer isCover) throws Exception;

	PaperResult addPaperAndQuestion(Integer userId, String content, String targerPath, String fileUuid,
			String schoolUuid, Integer relId, Integer type, Integer isCover)throws Exception;

	Integer addParentPaperTree(PaPaper paPaper,String memo);

	Integer addPaperTree(JSONObject group, Integer parentId, Integer paperId);

	void addKnowledgeRelation(Integer paQuestionId, JSONArray knowledges, String subjectCode);

	void addPaperRelation(Integer paperId, String[] catalogCodes, JSONArray subjects, String code);
	/**
	 * 返回拼接好的正答率等的paperJson
	 * @param paperId
	 * @return
	 */
	com.alibaba.fastjson.JSONObject findPaperJsonByPaperId(Integer paperId,Integer taskId,Integer userId,Integer uintId);

	void addQuestions(JSONArray questions, Integer paperTreeParentId, Integer questionParentId, Integer paperId,
			Integer userId, Integer level, String[] catalogCodes, JSONArray subjects, String stageCode, Integer type,
			String schoolUuid);
	com.alibaba.fastjson.JSONObject findFullPaperJsonByPaperId(Integer paperId,Integer taskId,Integer userId,Integer uintId);
}
