package platform.basis.esb.service.common.contants;

import java.io.Serializable;

/**
 * <p>
 * Title:EsbConfig.java
 * </p>
 * <p>
 * Description:教育基础管理平台
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010-2015
 * </p>
 * <p>
 * Company: 广州迅云教育科技有限公司
 * </p>
 * 
 * @Function 功能描述：
 * @Author 潘维良
 * @Version 1.0
 * @Date 2016年6月6日
 */
public class EsbConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5861110935134040593L;

	private static EsbConfig instance;

	public String serializationPackagePath;

	public String thresholdVal;

	public String salt;

	public EsbConfig() {
		instance = this;
	}

	public static EsbConfig getInstance() {
		return instance;
	}

	public String getSerializationPackagePath() {
		return serializationPackagePath;
	}

	public void setSerializationPackagePath(String serializationPackagePath) {
		this.serializationPackagePath = serializationPackagePath;
	}

	public String getThresholdVal() {
		return thresholdVal;
	}

	public void setThresholdVal(String thresholdVal) {
		this.thresholdVal = thresholdVal;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
