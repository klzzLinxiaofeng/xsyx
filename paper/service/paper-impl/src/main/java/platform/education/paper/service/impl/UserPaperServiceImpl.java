package platform.education.paper.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.paper.constants.PaperType;
import platform.education.paper.dao.UserPaperDao;
import platform.education.paper.model.UserPaper;
import platform.education.paper.model.UserRank;
import platform.education.paper.service.UserPaperService;
import platform.education.paper.util.MqtPaperUtil;
import platform.education.paper.vo.UserPaperCondition;
import platform.education.paper.vo.UserPaperCorrectVo;

public class UserPaperServiceImpl implements UserPaperService {

    private Logger log = LoggerFactory.getLogger(getClass());

    private UserPaperDao userPaperDao;

    public void setUserPaperDao(UserPaperDao userPaperDao) {
        this.userPaperDao = userPaperDao;
    }

    @Override
    public UserPaper findUserPaperById(Integer id) {
        try {
            return userPaperDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public UserPaper add(UserPaper userPaper) {
        if (userPaper == null) {
            return null;
        }
        Date createDate = userPaper.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        userPaper.setCreateDate(createDate);
        userPaper.setModifyDate(createDate);
        return userPaperDao.create(userPaper);
    }

    @Override
    public UserPaper modify(UserPaper userPaper) {
        if (userPaper == null) {
            return null;
        }
        Date modify = userPaper.getModifyDate();
        userPaper.setModifyDate(modify != null ? modify : new Date());
        return userPaperDao.update(userPaper);
    }

    @Override
    public void remove(UserPaper userPaper) {
        try {
            userPaperDao.delete(userPaper);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("删除数据库无存在ID为 {} ,异常为：{}", userPaper.getId(), e);
            }
        }
    }

    @Override
    public List<UserPaper> findUserPaperByCondition(UserPaperCondition userPaperCondition, Page page, Order order) {
        return userPaperDao.findUserPaperByCondition(userPaperCondition, page, order);
    }

    @Override
    public List<UserPaper> findUserPaperByCondition(UserPaperCondition userPaperCondition) {
        return userPaperDao.findUserPaperByCondition(userPaperCondition, null, null);
    }

    @Override
    public List<UserPaper> findUserPaperByCondition(UserPaperCondition userPaperCondition, Page page) {
        return userPaperDao.findUserPaperByCondition(userPaperCondition, page, null);
    }

    @Override
    public List<UserPaper> findUserPaperByCondition(UserPaperCondition userPaperCondition, Order order) {
        return userPaperDao.findUserPaperByCondition(userPaperCondition, null, order);
    }

    @Override
    public Long count() {
        return this.userPaperDao.count(null);
    }

    @Override
    public Long count(UserPaperCondition userPaperCondition) {
        return this.userPaperDao.count(userPaperCondition);
    }

    /* (非 Javadoc)
     * <p>Title: findUserPaperByPaperUuId</p>
     * <p>Description: </p>
     * @param paperUuid
     * @return
     * @see platform.education.paper.service.UserPaperService#findUserPaperByPaperUuId(java.lang.String)
     */
    @Override
    public List<UserRank> findUserPaperByPaperUuId(String paperUuId, Integer type, Integer ownerId, Integer teamId) {
        return userPaperDao.findUserPaperByPaperUuId(paperUuId, type, ownerId, teamId);
    }

    /* (非 Javadoc)
     * <p>Title: findPaperQuestionCorrectRateByPaperUuId</p>
     * <p>Description: </p>
     * @param paperUuid
     * @return
     * @see platform.education.paper.service.UserPaperService#findPaperQuestionCorrectRateByPaperUuId(java.lang.String)
     */
    @Override
    public List<UserRank> findPaperQuestionCorrectRateByPaperUuId(String paperUuId, Integer type, Integer ownerId, Integer teamId) {

        return userPaperDao.findPaperQuestionCorrectRateByPaperUuId(paperUuId, type, ownerId, teamId);
    }

    /* (非 Javadoc)
     * <p>Title: findUserPaperAnswerCountByCondition</p>
     * <p>Description: </p>
     * @param userPaperCondition
     * @return
     * @see platform.education.paper.service.UserPaperService#findUserPaperAnswerCountByCondition(platform.education.paper.vo.UserPaperCondition)
     */
    @Override
    public List<UserPaper> findUserPaperAnswerCountByCondition(UserPaperCondition userPaperCondition) {
        return userPaperDao.findUserPaperAnswerCountByCondition(userPaperCondition);
    }

    /* (非 Javadoc)
     * <p>Title: countUserPaperTeamTotalScore</p>
     * <p>Description: </p>
     * @param ownerId
     * @param team_id
     * @return
     * @see platform.education.paper.service.UserPaperService#countUserPaperTeamTotalScore(java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Float countUserPaperTeamTotalScore(Integer ownerId, Integer teamId, Integer unitId, Integer paperType) {
        return userPaperDao.countUserPaperTeamTotalScore(ownerId, teamId, unitId, paperType);
    }

    @Override
    public List<UserPaper> findUserPaperListByOwnerId(Integer ownerId, Integer objectId, Integer type) {
        return userPaperDao.findUserPaperListByOwnerId(ownerId, objectId, type);
    }

    @Override

    public List<UserPaperCorrectVo> findUserPaperCorrectByTaskId(Integer taskId, Integer objectId, Integer userId, String questionUuid, Page page) {
        List<UserPaperCorrectVo> volist = new ArrayList<UserPaperCorrectVo>();
        volist = userPaperDao.findUserPaperCorrectByTaskId(taskId, objectId, userId, questionUuid, page);
        if (volist != null && volist.size() > 0) {
            for (UserPaperCorrectVo vo : volist) {
                vo.setDbAnswer(MqtPaperUtil.fixOldDate(vo.getDbAnswer(), vo.getQuestionType(), vo.getContent(), vo.getQuestionUuid()));
            }
        }
        return volist;
    }

    @Override
    public UserPaper findUserPaperByPaperTaskId(Integer taskId, Integer userId) {
        return userPaperDao.findUserPaperByPaperTaskId(taskId, userId);
    }

    @Override
    public List<UserPaper> findUserPaperUserTotalScore(Integer unitId, Integer ownerId, Integer type, Integer teamId) {
        return userPaperDao.findUserPaperUserTotalScore(unitId, ownerId, type, teamId);
    }

    @Override
    public List<Map<Integer, Float>> countUserPaperTeamsTotalScore(Object[] obj) {

        List<Map<Integer, Float>> list = userPaperDao.findUserPaperUserTotalScore(obj);

        List<Map<Integer, Float>> listMap = new ArrayList<Map<Integer, Float>>();
        Map<Integer, Float> map = null;
        if (list != null && list.size() > 0) {
            for (int i = 0, len = list.size(); i < len; i++) {
                map = new HashMap<Integer, Float>();

                map.put(Integer.parseInt(list.get(i).get("teamId") + ""), list.get(i).get("totalScore"));
                listMap.add(map);
            }
        }
        return listMap;
    }

    @Override
    public List<Map<Integer, Integer>> findUserPaperAnswerCountByOwnerIdAndTeamId(Object[] obj) {

        List<Map<Integer, Integer>> list = userPaperDao.findUserPaperAnswerCountByOwnerIdAndTeamId(obj);

        return list;
    }

    @Override
    public Map<Integer, Float> countUserPaperTeamsTotalScoreObj(Object[] list, Integer ownerId, Integer unitId, Integer paperType) {

        Map<Integer, Float> map = null;
        if (list != null && list.length > 0) {
            int len = list.length;
            map = new HashMap<Integer, Float>();
            for (int i = 0; i < len; i++) {
                int teamId = (Integer) list[i];
                Float sumStudent = userPaperDao.countUserPaperTeamTotalScore(ownerId, teamId, unitId, paperType);
                map.put(teamId, sumStudent);
            }
        }


        return map;

    }

    @Override
    public Map<Integer, Integer> countUserPaperAnswerCount(Object[] list, Integer ownerId, Integer unitId, Integer paperType) {

        Map<Integer, Integer> map = null;
        if (list != null && list.length > 0) {
            int len = list.length;
            map = new HashMap<Integer, Integer>();
            UserPaperCondition userPaperCondition = new UserPaperCondition();
            for (int i = 0; i < len; i++) {
                int teamId = (Integer) list[i];
                userPaperCondition.setTeamId(teamId);
                userPaperCondition.setOwnerId(ownerId);
                userPaperCondition.setType(paperType);
                userPaperCondition.setObjectId(unitId);
                Integer sumStudent = userPaperDao.findUserPaperAnswerCountByCondition(userPaperCondition).size();
                map.put(teamId, sumStudent);
            }
        }

        return map;
    }

    @Override
    public List<UserPaper> findUserPaperScoreByOwnerId(String paperUuid, Integer ownerId, Integer type) {
        return userPaperDao.findUserPaperScoreByOwnerId(paperUuid, ownerId, type);
    }

    @Override
    public List<UserPaper> findUserPaperListByOwnerIds(Integer[] ownerIds,
                                                       Integer objectId, Integer type) {
        return userPaperDao.findUserPaperListByOwnerIds(ownerIds, objectId, type);
    }

    @Override
    public List<UserPaper> findUserPaperByOwnerIdsAndUserId(Integer[] ownerId, Integer objectId, Integer type, Integer userId) {
        //
        if (ownerId.length == 0) {
            return new ArrayList<UserPaper>();
        }
        return userPaperDao.findUserPaperByOwnerIdsAndUserId(ownerId, objectId, type, userId);
    }

    @Override
    public void deleteByOwnerIdAndType(Integer taskId, Integer type) {
        // TODO Auto-generated method stub
        userPaperDao.deleteByOwnerIdAndType(taskId, type);

    }

    @Override
    public List<UserPaper> findUserPaperByOwnerIdAndObjectId(Integer taskId, Integer unitId) {
        UserPaperCondition userPaperCondition = new UserPaperCondition();
        userPaperCondition.setOwnerId(taskId);
        userPaperCondition.setObjectId(unitId);
        if (unitId == null) {
            userPaperCondition.setType(PaperType.EXAM);
        }
        List<UserPaper> userPaperList = this.findUserPaperByCondition(userPaperCondition);
        return userPaperList;
    }

    @Override
    public UserPaper findUserPaperByCondition(Integer taskId, Integer unitId, Integer userId) {
        UserPaperCondition userPaperCondition = new UserPaperCondition();
        userPaperCondition.setOwnerId(taskId);
        userPaperCondition.setObjectId(unitId);
        userPaperCondition.setUserId(userId);
        if (unitId == null) {
            userPaperCondition.setType(2);
        }
        List<UserPaper> userPaperList = this.findUserPaperByCondition(userPaperCondition);
        if (userPaperList.size() > 0) {
            return userPaperList.get(0);
        }
        return null;
    }

    @Override
    public UserPaper findUniqueUserPaper(Integer taskId, Integer userId, Integer unitId) {
        UserPaperCondition userPaperCondition = new UserPaperCondition();
        userPaperCondition.setOwnerId(taskId);
        userPaperCondition.setUserId(userId);
        if (unitId != null) {
            userPaperCondition.setObjectId(unitId);
        } else {
            userPaperCondition.setType(2);
        }

        Page page = new Page();
        page.setPageSize(1);
        List<UserPaper> userPaperList = this.findUserPaperByCondition(userPaperCondition, page);
        if (userPaperList.size() > 0) {
            UserPaper userPaper = userPaperList.get(0);
            return userPaper;
        }
        return null;
    }

    @Override
    public List<UserPaper> findPaperResponseCheck(Integer taskId, Integer teamId) {
        if (taskId == null) {
            return new ArrayList<UserPaper>();
        }
        return userPaperDao.findPaperResponseCheck(taskId, teamId);
    }

    @Override
    public List<UserPaperCorrectVo> findUserPaperCorrectByTaskIdAndTeamId(
            Integer taskId, Integer objectId, Integer userId, Integer teamId,
            String questionUuid, Page page) {
        List<UserPaperCorrectVo> volist = userPaperDao.findUserPaperCorrectByTaskIdAndTeamId(taskId, objectId, userId, teamId, questionUuid, page);
        if (volist != null && volist.size() > 0) {
            for (UserPaperCorrectVo vo : volist) {
                vo.setDbAnswer(MqtPaperUtil.fixOldDate(vo.getDbAnswer(), vo.getQuestionType(), vo.getContent(), vo.getQuestionUuid()));
            }
        }
        return volist;
    }

    public static List<String> getblank(String htmlStr) {
        List<String> pics = new ArrayList<String>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
        //     String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
        String regEx_img = "<input.*id\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile
                (regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("id\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }

    @Override
    public void deleteNotInPaperUuid(String[] uuids) {
        userPaperDao.deleteNotInPaperUuid(uuids);

    }
}
