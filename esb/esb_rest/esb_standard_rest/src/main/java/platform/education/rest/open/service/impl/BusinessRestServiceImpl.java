package platform.education.rest.open.service.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.generalTeachingAffair.model.Parent;
import platform.education.generalTeachingAffair.model.ParentStudent;
import platform.education.generalTeachingAffair.service.ParentProxyService;
import platform.education.generalTeachingAffair.service.ParentService;
import platform.education.generalTeachingAffair.service.ParentStudentService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.vo.ParentVo;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.open.service.BusinessRestService;
import platform.education.rest.open.service.util.VerificationUtil;
import platform.education.user.model.Group;
import platform.education.user.model.Role;
import platform.education.user.model.User;
import platform.education.user.model.UserBinding;
import platform.education.user.service.GroupService;
import platform.education.user.service.RoleService;
import platform.education.user.service.UserBindingService;
import platform.education.user.service.UserRoleService;
import platform.education.user.service.UserService;
import platform.education.user.utils.holder.PwdEncoderHolder;
import platform.education.user.vo.UserBindingCondition;

import java.io.File;
import java.util.*;

/**
 * Created by Administrator on 2017/12/4.
 */
public class BusinessRestServiceImpl implements BusinessRestService {

    @Autowired
    @Qualifier("studentService")
    private StudentService studentService;

    @Autowired
    @Qualifier("parentProxyService")
    private ParentProxyService parentProxyService;

    @Autowired
    @Qualifier("roleService")
    private RoleService roleService;

    @Autowired
    @Qualifier("userRoleService")
    private UserRoleService userRoleService;
    
    @Autowired
    @Qualifier("userBindingService")
    private UserBindingService userBindingService;
    
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("groupService")
    private GroupService groupService;

    @Autowired
    @Qualifier("parentService")
    private ParentService parentService;

    @Autowired
    @Qualifier("parentStudentService")
    private ParentStudentService parentStudentService;

    @Override
    public Object addParent(String sign, String appKey, String timeStamp, Integer schoolId, String data) {
//        Object verify = VerificationUtil.verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }

        Object o_schoolId = VerificationUtil.judgeNull(schoolId, "schoolId", "??????id");
        if (o_schoolId != null) {
            return o_schoolId;
        }
        Object o_data = VerificationUtil.judgeNull(data, "data", "data");
        if (o_data != null) {
            return o_data;
        }


        List<Object> list = null;
        try {
            list = new ArrayList<>();
            JSONArray jsonArray = JSONArray.fromObject(data);
            if (jsonArray != null && jsonArray.size() > 0) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String name = jsonObject.getString("name");
                    String mobile = jsonObject.getString("mobile");
                    String relation = jsonObject.getString("relation");
                    String rank = jsonObject.getString("rank");
                    String email = jsonObject.getString("email");
                    int studentUserId = jsonObject.getInt("studentUserId");

                    //??????studentId???????????????
                    Integer studentId = studentService.findUniqueStudentId(studentUserId, schoolId);
                    if (studentId == null) {
                        ResponseInfo info = new ResponseInfo();
                        info.setMsg("????????????????????????");
                        info.setDetail("??????????????????????????????studentUserId=" + studentUserId);
                        info.setParam("schoolId, studentUserId");
                        return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
                    }
                    Group group = groupService.findUnique(schoolId, "1");
                    if (group != null) {
                        Role role = roleService.findUniqueInGroup(1, group.getId(), "PARENT");
                        if (role != null) {
                            ParentVo parentVo = new ParentVo();
                            parentVo.setSchoolId(schoolId);
                            parentVo.setSystem_app_id(1);
                            parentVo.setStudentUserId(studentUserId);
                            parentVo.setStudentId(studentId);
                            parentVo.setRoleId(role.getId());
                            parentVo.setParentRelation(relation);
                            parentVo.setRank(rank);
                            parentVo.setName(name);
                            parentVo.setMobile(mobile);
                            parentVo.setEmail(email);
                            ParentVo vo = parentProxyService.addInfoForParent(parentVo);

                            if (vo != null) {
                                //?????????????????????????????????
                                if (vo.getErrorInfo() != null && !"".equals(vo.getErrorInfo().trim())) {
                                    String detail = "";
                                    if (vo.getDetail() != null && !"".equals(vo.getDetail())) {
                                        String[] errorInfos = vo.getDetail().split("\\|");
                                        List<String> codeList = userRoleService.findRoleCodesByUserIdAndSchool(Integer.valueOf(errorInfos[0]), schoolId, 1);
                                        String userType = "";
                                        if (codeList.contains("TEACHER")) {
                                            userType += "??????";
                                        }
                                        if (codeList.contains("PARENT")) {
                                            userType += "??????";
                                        }
                                        detail = "????????????????????????[" + userType + "]??????????????????[" + errorInfos[1] +"]";
                                    }
                                    ResponseInfo info = new ResponseInfo();
                                    info.setDetail(detail);
                                    info.setMsg("???????????????????????????????????????");
                                    info.setParam("name");
                                    return new ResponseError(CommonCode.$MOBILE_NUMBER_HAS_REGISTERED, info);
                                } else {
                                    //??????????????????????????????????????????????????????????????????
                                    changeMajor(studentUserId, rank, vo.getUserId());
                                    changeParent(vo.getUserId());

                                    Map<String, Object> map = new HashMap<>();
                                    map.put("userId", vo.getUserId());
                                    map.put("userName", vo.getUserName());
                                    map.put("name", vo.getName());
                                    map.put("mobile", vo.getMobile());
                                    map.put("relation", vo.getParentRelation());
                                    map.put("rank", vo.getRank());
                                    map.put("email", vo.getEmail());
                                    map.put("studentUserId", vo.getStudentUserId());
                                    list.add(map);
                                }
                            }

                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("????????????", "????????????????????????", ""));
        }
        return new ResponseVo<Object>("0", list);
    }

    @Override
    public Object modifyParent(String sign, String appKey, String timeStamp, Integer schoolId, Integer studentUserId, Integer parentUserId, String rank, String relation) {
//        Object verify = VerificationUtil.verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }

        Object o_schoolId = VerificationUtil.judgeNull(schoolId, "schoolId", "??????id");
        if (o_schoolId != null) {
            return o_schoolId;
        }
        Object o_parentUserId = VerificationUtil.judgeNull(parentUserId, "parentUserId", "????????????id");
        if (o_parentUserId != null) {
            return o_parentUserId;
        }
        Object o_studentUserId = VerificationUtil.judgeNull(studentUserId, "studentUserId", "????????????id");
        if (o_studentUserId != null) {
            return o_studentUserId;
        }
        Object o_rank = VerificationUtil.judgeNull(rank, "rank", "????????????");
        if (o_rank != null) {
            return o_rank;
        }
        Object o_relation = VerificationUtil.judgeNull(relation, "relation", "????????????");
        if (o_relation != null) {
            return o_relation;
        }


        try {
            ParentStudent parentStudent = parentStudentService.findUnique(parentUserId, studentUserId);
            if (parentStudent != null) {
                parentStudent.setRank(rank);
                parentStudent.setParentRelation(relation);
                parentStudent.setModifyDate(new Date());
                parentStudentService.modify(parentStudent);

                changeParent(parentStudent.getParentUserId());
                changeMajor(studentUserId, rank, parentUserId);
            }
            return new ResponseVo<Object>("0", "????????????");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("????????????", "????????????????????????", ""));
        }
    }

    /**
     *  ???????????????
     */
    private void changeMajor(Integer studentUserId, String rank, Integer parentUserId) {
        if ("1".equals(rank)) {
            List<ParentStudent> psList = parentStudentService.findParentStudentByStudentUserId(studentUserId);
            if (psList != null && psList.size() > 0) {
                for (ParentStudent ps : psList) {
                    if ("1".equals(ps.getRank()) && !ps.getParentUserId().equals(parentUserId)) {
                        ps.setRank("0");
                        ps.setModifyDate(new Date());
                        parentStudentService.modify(ps);

                        changeParent(ps.getParentUserId());
                    }
                }
            }
        }
    }

    /**
     * ???????????????parent???
     */
    private void changeParent(Integer parentUserId) {
        Parent parent = parentService.findUniqueByUserId(parentUserId);
        if (parent != null) {
            parent.setModifyDate(new Date());
            parentService.modify(parent);
        }
    }


    @Override
    public Object unbindParent(String sign, String appKey, String timeStamp, Integer schoolId, Integer parentUserId, String data) {
//        Object verify = VerificationUtil.verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }

        Object o_schoolId = VerificationUtil.judgeNull(schoolId, "schoolId", "??????id");
        if (o_schoolId != null) {
            return o_schoolId;
        }
        Object o_parentUserId = VerificationUtil.judgeNull(parentUserId, "parentUserId", "????????????id");
        if (o_parentUserId != null) {
            return o_parentUserId;
        }
        Object o_data = VerificationUtil.judgeNull(data, "data", "data");
        if (o_data != null) {
            return o_data;
        }

        try {
            JSONArray jsonArray = JSONArray.fromObject(data);
            if (jsonArray != null && jsonArray.size() > 0) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int studentUserId = jsonObject.getInt("studentUserId");

                    ParentStudent parentStudent = parentStudentService.findUnique(parentUserId, studentUserId);
                    if (parentStudent != null) {
                        parentStudentService.remove(parentStudent);
                        changeParent(parentStudent.getParentUserId());

                        ParentStudent ps = null;
                        //?????????????????????????????????????????????????????????????????????????????????
                        if ("1".equals(parentStudent.getRank())) {
                            List<ParentStudent> psList = parentStudentService.findParentStudentByStudentUserId(studentUserId);
                            if (psList != null && psList.size() > 0) {
                                //???????????????????????????????????????????????????????????????
                                if (!"1".equals(psList.get(0).getRank())) {
                                    ps = psList.get(0);
                                    ps.setRank("1");
                                    ps.setModifyDate(new Date());
                                    parentStudentService.modify(ps);
                                    changeParent(ps.getParentUserId());
                                }
                                for (int j = 1; j < psList.size(); j++) {
                                    ps = psList.get(j);
                                    if ("1".equals(ps.getRank())) {
                                        ps.setRank("0");
                                        ps.setModifyDate(new Date());
                                        parentStudentService.modify(ps);
                                        changeParent(ps.getParentUserId());
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return new ResponseVo<>("0", "????????????");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("????????????", "????????????????????????", ""));
        }
    }

    @Override
    public Object uploadIcon(String sign, String appKey, String timeStamp, Integer userId, File icon) {
        return null;
    }

	@Override
	public Object changePassword(String sign, String appKey, String timeStamp, String userName, String oldPass,
			String newPass) {
//      Object verify = VerificationUtil.verifySign(sign, appKey, timeStamp);
//      if (verify != null) {
//          return verify;
//      }
		Object o_userName = VerificationUtil.judgeNull(userName, "userName", "?????????");
        if (o_userName != null) {
            return o_userName;
        }
        Object o_oldPass = VerificationUtil.judgeNull(oldPass, "oldPass", "?????????");
        if (o_oldPass != null) {
            return o_oldPass;
        }
        Object o_newPass = VerificationUtil.judgeNull(newPass, "newPass", "?????????");
        if (o_newPass != null) {
            return o_newPass;
        }
        
        try {
        	//????????????
        	UserBindingCondition con = new UserBindingCondition();
        	con.setIsDeleted(false);
        	con.setBindingName(userName);
        	List<UserBinding> li = userBindingService.findUserBindingByCondition(con, null, null);
        	//User user = userService.findUserByUsername(userName);
        	if (li.size() == 0) {
        		return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("????????????", "????????????userName", "userName"));
			}
        	String oldMD5 = PwdEncoderHolder.getInstance().getPwdEncoder().encodePassword(oldPass);
        	//???????????????userName?????????????????????????????????user?????????user???pass????????????????????????????????????????????????????????????
        	boolean flag = false;
        	for (UserBinding ub : li) {
        		User us = userService.findUserById(ub.getUserId());
        		if (us.getPassword().equals(oldMD5)) {
            		//???????????????
        			flag = true;
            	}
			}
        	if (!flag) {
        		//??????????????????
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("????????????", "??????????????????", "oldPass"));
        	}
        	
        	UserBindingCondition condition = new UserBindingCondition();
        	condition.setUserId(li.get(0).getUserId());
        	condition.setBindingType(1);
        	List<UserBinding> list = userBindingService.findUserBindingByCondition(condition, null, null);
        	if(list != null && list.size() > 0){
        		//?????????user?????????????????????,?????????????????????????????????user?????????
        		String telNum = list.get(0).getBindingName();
        		UserBindingCondition condition1 = new UserBindingCondition();
            	condition1.setBindingName(telNum);
            	condition1.setIsDeleted(false);
            	List<UserBinding> list2 = userBindingService.findUserBindingByCondition(condition1, null, null);
        		for (UserBinding ub : list2) {
        			//userId???????????????????????????ID
					Integer userId = ub.getUserId();
					User changePassUser = userService.findUserById(userId);
					//???????????????????????????????????????
					String newMD5 = PwdEncoderHolder.getInstance().getPwdEncoder().encodePassword(newPass);
					changePassUser.setPassword(newMD5);
					userService.modify(changePassUser);
				}
        	}else {
				//???????????????user?????????????????????,????????????user?????????
        		String newMD5 = PwdEncoderHolder.getInstance().getPwdEncoder().encodePassword(newPass);
        		User user = userService.findUserById(li.get(0).getUserId());
        		user.setPassword(newMD5);
        		userService.modify(user);
			}
        	
        	return new ResponseVo<>("0", "????????????");
        }catch (Exception e) {
        	e.printStackTrace();
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("????????????", "????????????????????????", ""));
		}
	}
}
