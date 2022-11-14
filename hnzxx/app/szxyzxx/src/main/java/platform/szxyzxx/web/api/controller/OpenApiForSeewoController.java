package platform.szxyzxx.web.api.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/seewo")
@RestController
public class OpenApiForSeewoController {


    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private BasicSQLService basicSQLService;

    @RequestMapping("/getUserInfoByToken")
    public Object getUserInfoByToken(String token){
        Map<String,Object> map=new HashMap<>(3,1);
        if(StringUtils.isEmpty(token)){
            map.put("code",400);
            map.put("msg","参数不可为空");
            return map;
        }

        try {
            Session session=sessionManager.getSession(new DefaultSessionKey(token));
            UserInfo currentUser=(UserInfo)session.getAttribute("SZXY_CURRENT_USER");
            if(currentUser==null){
                map.put("code",400);
                map.put("msg","token未登录");
                return map;
            }

           // if(basicSQLService.findUniqueLong("select exists(select 1 from yh_user_role ur left join yh_role r on r.id=ur.role_id where ur.user_id="+currentUser.getId()+" and ur.is_deleted=0 and (r.name='学校管理员' or r.name='运营人员')) e")>0){
                map.put("code",200);
                //所有有权限用户都对应同一个希沃用户
                Map<String,Object> dataMap=new HashMap<>();
                dataMap.put("userId",1);
                map.put("data",dataMap);
//            }else{
//                map.put("code",400);
//                map.put("msg","没有查看希沃数据权限");
//            }

        } catch (SessionException e) {
            map.put("code",400);
            map.put("msg","token无效");

        }catch (Exception e){
            map.put("code",500);
            map.put("msg","服务器内部错误");
        }
        return map;


    }




}
