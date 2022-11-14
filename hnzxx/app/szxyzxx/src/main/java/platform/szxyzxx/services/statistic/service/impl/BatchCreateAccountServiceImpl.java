package platform.szxyzxx.services.statistic.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import platform.education.user.dao.AccountDao;
import platform.education.user.model.Account;
import platform.education.user.vo.AccountCondition;
import platform.szxyzxx.services.statistic.service.BatchCreateAccountService;

/**
 * @功能描述: 批量创建账号实现类
 * @author pantq
 * @eamail:pantuquan@gmail.com
 * @version:1.0
 * @创建时间:2018年4月9日上午10:36:09
 */
public class BatchCreateAccountServiceImpl implements BatchCreateAccountService{
	
	private Integer maxNum = 10000;
	
	private AccountDao accountDao;
	
	@Override
	public void batchCreateAccount(Integer createNum) throws Exception {
		Long s = System.currentTimeMillis();
		Date nowDate = new Date();
		//判断账号库是否够库存
		Map<String,Object> result = isEnoughMap();
		if(result != null) {
			//判断是否需要创建
			Boolean flag = (Boolean) result.get("flag");
			if(flag != null && flag) { //生成账号
				//需要创建账号个数
				int num = (int) result.get("num");
				int index = maxNum - num;
				if(createNum != null) {
					index = createNum;
				}
				System.out.println("库里空闲账号个数："+num);
				System.out.println("需要生成的账号个数："+index);
				List<Account> accountList = new ArrayList<Account>();
				for(int i =0 ;i<index;i++) { //创建账号
					String username = returnCard();
					Account account = new Account();
					account.setForAppId(1);
					account.setState(0);
					account.setUserName(username);
					account.setCreateDate(nowDate);
					account.setModifyDate(nowDate);
					System.out.println(username);
					accountList.add(account);
				}
				//批量插入
				if(accountList != null && accountList.size() > 0) {
					this.accountDao.batchCreateAccount(accountList.toArray());
				}
				
				Long e = System.currentTimeMillis();
				System.out.println(e-s);
			}
		}
		
	}
	
	/**
	 * 随机生成10位数的账号
	 * @return
	 */
	 public String returnCard(){
	       String cardNnumer=getCard();//调用生成随机数的方法：这里以10位为例
	       Account  account = this.accountDao.findByUsername(cardNnumer);
	       if(account != null){//如果有相同的数据
	          return returnCard();//再次调用方法，生成一个随机数
	       }else{//否则
	           return cardNnumer;//这个数据我就要
	       }
	   }
	   //生成随机数
	   public static String getCard(){
	       Random rand=new Random();//生成随机数
	        String cardNnumer="";
	        for(int a=0;a<10;a++){
	        cardNnumer+=rand.nextInt(10);//生成10位数字
	        }
	       return cardNnumer;


	  }
	
	   /**
	    * 判断空还有多少空闲账号
	    * @return
	    */
		
		private Map<String,Object> isEnoughMap() {
			Map<String,Object> result = new HashMap<String,Object>();
			Boolean flag = false; 
			AccountCondition accountCondition = new AccountCondition();
			accountCondition.setForAppId(1);
			accountCondition.setState(0);
			List<Account> accountList = accountDao.findAccountByCondition(accountCondition, null, null);
			if(accountList != null && accountList.size() < maxNum) {
				flag = true;
				result.put("flag", flag);
				result.put("num", accountList.size());
			}
			return result;
	}

		
		public void setAccountDao(AccountDao accountDao) {
			this.accountDao = accountDao;
		}
}
