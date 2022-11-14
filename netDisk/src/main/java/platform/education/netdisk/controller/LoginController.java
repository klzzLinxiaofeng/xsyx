package platform.education.netdisk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import platform.education.annotation.AuthIgnore;
import platform.education.constant.Constant;
import platform.education.netdisk.common.ResponseBean;
import platform.education.netdisk.common.Result;
import platform.education.netdisk.entity.LoginEntity;
import platform.education.user.model.Profile;
import platform.education.user.model.User;
import platform.education.user.service.ProfileService;
import platform.education.user.service.UserService;
import platform.education.util.RedisUtils;
import platform.education.util.TokenUtils;
import platform.service.storage.service.FileService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
//@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/")
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	RedisUtils redisUtils;

	@Autowired
	TokenUtils tokenUtils;

	@Autowired
//	@Qualifier("teacherService")
	private UserService userService;

	@Autowired
//	@Qualifier("profileService")
	private ProfileService profileService;

	@Autowired
	@Qualifier("fileService")
	private FileService fileService;
	/**
	 * 登录
	 */
	@AuthIgnore
	@PostMapping("/login")
//	@RequestMapping(value = "/login")
	@ResponseBody
	public ResponseBean login(@RequestBody LoginEntity loginEntity) {

		logger.info("用户登录");
		// 用户信息
//		FrameworkAccountEntit user = frameworkAccountService.getByUserAccount(frameworkAccountEntity.getUserAccount());

		try {

//			String content = JsEncryptionKey.decryptByPublic(loginEntity.getValidation());
//			loginEntity = JSON.parseObject(content, LoginEntity.class);
			if( !loginEntity.valid() ){
			// 账号不存在、密码错误
	//		if (user == null
	//				|| !user.getUserPsw().equals(DigestUtils.md5Hex(frameworkAccountEntity.getUserPsw() + Constant.SALT))) {
				return new ResponseBean(ResponseBean.FAIL, "账号或密码不正确");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseBean(ResponseBean.FAIL, "账号或密码解析失败");
		}

//		UserCondition userCondition = new UserCondition();
//		userCondition.setId(userId);
		User user = this.userService.findUserById(loginEntity.getUserId());
		if(user == null){
			return new ResponseBean(ResponseBean.FAIL, "该用户不存在，请检查userId");
		}
		Integer userId = user.getId();
		String token = UUID.randomUUID().toString().replaceAll("-", "");
		// 保存用户id
//		long userId = user.getId();
		redisUtils.set(token, userId, Constant.DEFAULT_EXPIRE);

//		TeacherCondition teacherCondition = new TeacherCondition();
//		teacherCondition.setUserId(userId);
//		List<Teacher> teacherList = teacherService.findTeacherByUserId(userId);
//		if(teacherList != null && teacherList.size() > 0){
//			HttpSession session = request.getSession();
//			session.setAttribute(userId.toString(), teacherList.get(0));
//		}

		/**
		// 保存用户对象
		String frameworkAccountKey = RedisKeys.getOnlineUserKey(userId + "");
		// 该user后期要修改
		OnlineUserEntit onlineUserEntity = new OnlineUserEntit();
		BeanUtils.copyBeanProp(onlineUserEntity, user);
		redisUtils.set(frameworkAccountKey, onlineUserEntity, Constant.SEVEN_DAY_EXPIRE);
		 */
		Map<String, Object> data = new HashMap<>();
		Profile profile = this.profileService.findByUserId(user.getId());
		String icon = profile.getIcon();

		String username = profile.getName();
		// 返回数据
		data.put("token", token);
		data.put("userId", user.getId());
		data.put("username", username==null || username.isEmpty()? user.getUserName() : username);
		data.put("avatar", fileService.relativePath2HttpUrlByUUID(icon));
//		data.put("userId", userId);
		return new ResponseBean(data);
	}

	/**
	 * 退出
	 * 
	 * @return
	 */
	@AuthIgnore
	@RequestMapping("/logout")
	public Result Logout() {
		tokenUtils.deleteUser();
		return Result.ok();
	}

	@PostMapping("/userInfo")
	public ResponseBean getUserInfo(HttpServletRequest request) {
		Integer userId = Integer.valueOf(request.getAttribute("userId").toString());

		User user = this.userService.findUserById(userId);

		Map<String, Object> data = new HashMap<>();
		Profile profile = this.profileService.findByUserId(user.getId());

		String icon = profile.getIcon();

		String username = profile.getName();

		data.put("userId", user.getId());
		data.put("username", username==null || username.isEmpty()? user.getUserName() : username);
		data.put("avatar", fileService.relativePath2HttpUrlByUUID(icon));

		return new ResponseBean(data);
	}
}
