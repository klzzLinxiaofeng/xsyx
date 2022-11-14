package platform.education.lads.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.RandomStringUtils;
import platform.education.lads.contants.ActivityType;
import platform.education.lads.contants.FinishedStatus;
import platform.education.lads.contants.ToolName;
import platform.education.lads.model.LadsActivity;
import platform.education.lads.model.LadsActivityTransition;
import platform.education.lads.model.LadsEditorTool;
import platform.education.lads.model.LadsLearningdesign;
import platform.education.lads.model.LadsMediaTool;
import platform.education.lads.service.DiscussToolService;
import platform.education.lads.service.EditorToolService;
import platform.education.lads.service.LadsActivityService;
import platform.education.lads.service.LadsActivityTransitionService;
import platform.education.lads.service.LadsLearningdesignService;
import platform.education.lads.service.LadsService;
import platform.education.lads.service.MediaToolService;
import platform.education.lads.vo.LadsActivityCondition;
import platform.education.lads.vo.LadsActivityTransitionCondition;
import platform.education.lads.vo.LadsLearningdesignCondition;
import platform.education.resource.utils.UUIDUtil;

/**
 *
 * @author Administrator
 */
public class LadsServiceImpl implements LadsService {

    private LadsLearningdesignService ladsLearningdesignService;
    private LadsActivityService ladsActivityService;
    private LadsActivityTransitionService ladsActivityTransitionService;
    private EditorToolService editorToolService;
    private MediaToolService mediaToolService;
    private DiscussToolService discussToolService;

    public void setLadsLearningdesignService(LadsLearningdesignService ladsLearningdesignService) {
        this.ladsLearningdesignService = ladsLearningdesignService;
    }

    public void setLadsActivityService(LadsActivityService ladsActivityService) {
        this.ladsActivityService = ladsActivityService;
    }

    public void setLadsActivityTransitionService(LadsActivityTransitionService ladsActivityTransitionService) {
        this.ladsActivityTransitionService = ladsActivityTransitionService;
    }

    public void setEditorToolService(EditorToolService editorToolService) {
        this.editorToolService = editorToolService;
    }

    public void setMediaToolService(MediaToolService mediaToolService) {
        this.mediaToolService = mediaToolService;
    }

    public void setDiscussToolService(DiscussToolService discussToolService) {
        this.discussToolService = discussToolService;
    }

    /*
     * 完整删除lads课件
     * 参数是 ids 要删除的课件id数组
     */
    @Override
    public void deleteLearningDesigns(String ids[]) {
        for (String id : ids) {
            //删除原有的活动和顺序
            LadsActivityTransitionCondition atc = new LadsActivityTransitionCondition();
            atc.setLearningdesign(id);
            this.ladsActivityTransitionService.remove(atc);
            //String deleteTransiton = "delete from LadsActivityTransition la where la.ladsLearningdesign.id = '" + id + "'";

            this.ladsActivityService.removeChildrenActivity(id);
            //String deleteSonActivity = "delete from LadsActivity la where la.ladsActivity is not null and la.ladsLearningdesign.id = '" + id + "'";

            LadsActivityCondition ac = new LadsActivityCondition();
            ac.setLearningdesign(id);
            this.ladsActivityService.remove(ac);
            //String deleteParentActivity = "delete from LadsActivity la where la.ladsLearningdesign.id = '" + id + "'";

            LadsLearningdesignCondition ldc = new LadsLearningdesignCondition();
            ldc.setUuid(id);
            this.ladsLearningdesignService.remove(ldc);
            //String deleteLearningDesign = "delete from LadsLearningdesign la where la.id = '" + id + "'";
        }
    }


    /*
     * 复制一份课件，新复制的课件为已发布课件，不会因为修改原课件而改变发布后的状态
     * 参数是 ldId
     */
    @Override
    public LadsLearningdesign copyToPublishedLearningDesign(String ldId) {
        LadsLearningdesign ld = this.ladsLearningdesignService.findLadsLearningdesignByUuid(ldId);
        if (ld != null) {
            LadsLearningdesign newLd = new LadsLearningdesign();
            newLd.setDescription(ld.getDescription());
            newLd.setTitle(ld.getTitle());
            newLd.setUserId(ld.getUserId());
            newLd.setCreateDate(new Date());
            newLd.setUuid(UUIDUtil.getUUID());
            return newLd;
        } else {
            return null;
        }
    }

    /*
     * 工具随机toolId生成器
     */
    @Override
    public String toolIdCreater() {
        StringBuilder buf = new StringBuilder("1234567890");
        int preId = Integer.parseInt(RandomStringUtils.random(5, buf.toString().toCharArray()));
        int sufId = (int) new Date().getTime();
        return String.valueOf(preId ^ sufId);
    }

    /*
     * 通过toolId获取活动
     */
    @Override
    public LadsActivity getActivityByToolId(String toolId) {
        LadsActivityCondition ac = new LadsActivityCondition();
        ac.setToolId(toolId);
        List<LadsActivity> atList = this.ladsActivityService.findLadsActivityByCondition(ac);
        if (atList.size() > 0) {
            return atList.get(0);
        } else {
            return null;
        }
    }

    /*
     * 复制课件json
     */
    @Override
    public void copyJson(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            //记录原来的toolId方便调用
            String toolId = obj.getString("id");
            obj.put("id", this.toolIdCreater());
            String activityType = obj.getString("activityType");
            //如果是工具类型是组，必修把组内工具也修改toolId
            if (activityType.equals(ActivityType.GROUP)) {
                JSONArray subArray = obj.getJSONArray("subActivitys");
                copyJson(subArray);
            } else if (activityType.equals(ActivityType.TOOL)) {
                String toolName = (String) obj.get("toolName");
                if (toolName.equals(ToolName.QUIZ_TOOL)) {
//                    LadsQuizTool quiz = this.authoringAction.quizToolServiceImpl.getQuizByToolId(toolId);
//                    String quizXmlContent = quiz.getXmlContent();
//                    String http = quiz.getHttpUrl();
//                    String quizUploadPath = http.substring(http.lastIndexOf("/exam"), http.lastIndexOf("/") + 1);
//                    obj.put("quizXmlContent", quizXmlContent);
//                    obj.put("quizUploadPath", quizUploadPath);
                } else if (toolName.equals(ToolName.SURVEY_TOOL)) {
//                    JSONArray questionsArray = new JSONArray();
//                    List<LadsSurveyQuestionTool> questionList = this.authoringAction.surveyToolServiceImpl.getSurveyQuestionList(toolId);
//                    for (LadsSurveyQuestionTool qt : questionList) {
//                        JSONObject question = new JSONObject();
//                        question.put("type", qt.getQuestionType());
//                        question.put("content", qt.getContent());
//                        question.put("answer", qt.getAnswer());
//                        question.put("pos", qt.getPos());
//                        questionsArray.add(question);
//                    }
//                    obj.put("surveyQuestions", questionsArray);
                } else if (toolName.equals(ToolName.EDITOR_TOOL)) {
                    LadsEditorTool editor = this.editorToolService.getEditorByToolId(toolId);
                    if (editor != null) {
                        obj.put("editorContent", editor.getContent());
                        obj.put("editorUploadList", editor.getUploadList());
                    }
                } else if (toolName.equals(ToolName.MEDIA_TOOL)) {
                    LadsMediaTool media = this.mediaToolService.getMediaByToolId(toolId);
                    if (media != null) {
                        obj.put("mediaEntityId", media.getEntityId());
                        obj.put("mediaUploadList", media.getUploadList());
                    }
                }
            }
        }
    }

    /*
     * 获取某用户对某份课件的学习状态
     * ldId
     * userId
     */
    @Override
    public int getFinishedByUser(String ldId, Integer userId) {
        LadsLearningdesign ld = this.ladsLearningdesignService.findLadsLearningdesignByUuid(ldId);
        LadsActivity firstAct = this.ladsActivityService.findLadsActivityByUuid(ld.getFirstActivity());
        //添加第一个活动
        List<Integer> finishedList = this.assembleJsonFromAct(firstAct, userId);
        //添加其它活动
        finishedList.addAll(this.putActsToArray(finishedList, firstAct, userId));
        if (finishedList.size() > 0) {
            if (finishedList.contains(FinishedStatus.NOT_FINISHED)) {
                return FinishedStatus.NOT_FINISHED;
            } else {
                return FinishedStatus.FINISHED;
            }
        } else {
            return FinishedStatus.NOT_FINISHED;
        }
    }

    public List<Integer> assembleJsonFromAct(LadsActivity act, Integer embedUserId) {
        List<Integer> finishedList = new ArrayList();
        String activityName = this.ladsActivityService.getActivityType(act).getActivityName();
        if (activityName.equals(ActivityType.TOOL)) {
            if (act.getToolLibrary().equals(ToolName.EDITOR_TOOL)) {
                finishedList.add(this.editorToolService.getFinishedStatus(act.getToolId(), embedUserId));
            } else if (act.getToolLibrary().equals(ToolName.MEDIA_TOOL)) {
                finishedList.add(this.mediaToolService.getFinishedStatus(act.getToolId(), embedUserId));
            } else if (act.getToolLibrary().equals(ToolName.FACE_TEACHING_TOOL)) {
//                jAct.put("finished", this.faceTeachingToolServiceImpl.getFinishedStatus(act.getToolId(), this.ladsServiceImpl.getEmbedUserId(sysType)));
//                jAct.put("userScore", this.faceTeachingToolServiceImpl.getUserScore(act.getToolId(), this.ladsServiceImpl.getEmbedUserId(sysType)));
            } else if (act.getToolLibrary().equals(ToolName.DISCUSS_TOOL)) {
                finishedList.add(this.discussToolService.getFinishedStatus(act.getToolId(), embedUserId));
            } else if (act.getToolLibrary().equals(ToolName.QUIZ_TOOL)) {
//                jAct.put("finished", this.quizToolServiceImpl.getFinishedStatus(act.getToolId(), this.ladsServiceImpl.getEmbedUserId(sysType)));
//                jAct.put("userScore", this.quizToolServiceImpl.getUserScore(act.getToolId(), this.ladsServiceImpl.getEmbedUserId(sysType)));
            } else if (act.getToolLibrary().equals(ToolName.SURVEY_TOOL)) {
//                jAct.put("finished", this.surveyToolServiceImpl.getFinishedStatus(act.getToolId(), this.ladsServiceImpl.getEmbedUserId(sysType)));
//                jAct.put("userScore", this.surveyToolServiceImpl.getUserScore(act.getToolId(), this.ladsServiceImpl.getEmbedUserId(sysType)));
            } else if (act.getToolLibrary().equals(ToolName.POWER_POINT_TOOL)) {
//                jAct.put("finished", this.powerPointToolServiceImpl.getFinishedStatus(act.getToolId(), this.ladsServiceImpl.getEmbedUserId(sysType)));
//                jAct.put("userScore", this.powerPointToolServiceImpl.getUserScore(act.getToolId(), this.ladsServiceImpl.getEmbedUserId(sysType)));
            }
        } else if (activityName.equals(ActivityType.GROUP)) {
            LadsActivityCondition ac = new LadsActivityCondition();
            ac.setParentActivity(act.getUuid());
            List<LadsActivity> subActs = this.ladsActivityService.findLadsActivityByCondition(ac);
            if (subActs.size() > 0) {
                //添加组内第一个活动
                LadsActivity firstSubAct = subActs.get(0);
                //递归
                finishedList.addAll(this.assembleJsonFromAct(firstSubAct, embedUserId));
                //添加其它活动
                finishedList = this.putActsToArray(finishedList, firstSubAct, embedUserId);
            }
        }
        return finishedList;
    }

    public List<Integer> putActsToArray(List finishedList, LadsActivity fromAct, Integer embedUserId) {
        LadsActivityTransitionCondition atc = new LadsActivityTransitionCondition();
        atc.setFromActivity(fromAct.getUuid());
        List<LadsActivityTransition> atList = this.ladsActivityTransitionService.findLadsActivityTransitionByCondition(atc);
        LadsActivity toAct;
        if (atList.size() > 0) {
            toAct = this.ladsActivityTransitionService.getToActivity(atList.get(0));
            //添加活动
            finishedList.add(this.assembleJsonFromAct(toAct, embedUserId));
            //递归
            finishedList = putActsToArray(finishedList, toAct, embedUserId);
        }
        return finishedList;
    }

}
