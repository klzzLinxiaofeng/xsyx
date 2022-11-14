package platform.szxyzxx.web.statistic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import platform.szxyzxx.web.common.util.DesUtil;

/**
 * Created by panfei on 2017/5/27.
 * 子系统管理调转控制器，目前仅用于沿河校园广播的登录调转
 */
@Controller
@RequestMapping("/yhzxgb")
public class SubsystemController {

    private final static String username = "admin";
    private final static String password = "admin";
    private final static String desKey = "jlddeskey";

    @RequestMapping(value = "toJumpPage")
    public ModelAndView toJumpPage(Model model) throws Exception{
        //默认密码：admin 默认key:jlddeskey
        String pwd = DesUtil.encryptAndUrlEncode(password,desKey);
        model.addAttribute("uid",username);
        model.addAttribute("pwd",pwd);
        return new ModelAndView("exam/statistics/JumpPage",model.asMap());
    }
}
