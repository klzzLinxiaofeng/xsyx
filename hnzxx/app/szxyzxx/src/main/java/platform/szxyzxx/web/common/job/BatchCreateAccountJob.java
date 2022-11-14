package platform.szxyzxx.web.common.job;

import javax.annotation.Resource;

import platform.education.user.service.AccountService;
import platform.szxyzxx.services.statistic.service.BatchCreateAccountService;

/**
 * @功能描述: 批量创建用户账户（库里不足10万空闲账户，夜晚 1点开始执行）
 * @author pantq
 * @eamail:pantuquan@gmail.com
 * @version:1.0
 * @创建时间:2018年4月3日下午6:50:52
 */
public class BatchCreateAccountJob {


	@Resource
	private AccountService accountService;
	
	@Resource
	private BatchCreateAccountService batchCreateAccountService;
	
	
	/**
	 * 批量创建用户账户
	 */
	public void batchCreateAccount() {
		
		try {
			batchCreateAccountService.batchCreateAccount(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
