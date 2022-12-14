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
     * ????????????lads??????
     * ????????? ids ??????????????????id??????
     */
    @Override
    public void deleteLearningDesigns(String ids[]) {
        for (String id : ids) {
            //??????????????????????????????
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
     * ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????
     * ????????? ldId
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
     * ????????????toolId?????????
     */
    @Override
    public String toolIdCreater() {
        StringBuilder buf = new StringBuilder("1234567890");
        int preId = Integer.parseInt(RandomStringUtils.random(5, buf.toString().toCharArray()));
        int sufId = (int) new Date().getTime();
        return String.valueOf(preId ^ sufId);
    }

    /*
     * ??????toolId????????????
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
     * ????????????json
     */
    @Override
    public void copyJson(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            //???????????????toolId????????????
            String toolId = obj.getString("id");
            obj.put("id", this.toolIdCreater());
            String activityType = obj.getString("activityType");
            //????????????????????????????????????????????????????????????toolId
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
     * ?????????????????????????????????????????????
     * ldId
     * userId
     */
    @Override
    public int getFinishedByUser(String ldId, Integer userId) {
        LadsLearningdesign ld = this.ladsLearningdesignService.findLadsLearningdesignByUuid(ldId);
        LadsActivity firstAct = this.ladsActivityService.findLadsActivityByUuid(ld.getFirstActivity());
        //?????????????????????
        List<Integer> finishedList = this.assembleJsonFromAct(firstAct, userId);
        //??????????????????
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
                //???????????????????????????
                LadsActivity firstSubAct = subActs.get(0);
                //??????
                finishedList.addAll(this.assembleJsonFromAct(firstSubAct, embedUserId));
                //??????????????????
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
            //????????????
            finishedList.add(this.assembleJsonFromAct(toAct, embedUserId));
            //??????
            finishedList = putActsToArray(finishedList, toAct, embedUserId);
        }
        return finishedList;
    }

}
