package platform.szxyzxx.web.common.contants;

public final class VehicleStatus {
	/**
	 * 待审批  或  车辆库存中的空闲状态
	 */
	public static final String audit = "0";
	/**
	 * 使用中  或车辆库存中的借出状态
	 */
	public static final String using = "1";
	/**
	 * 归还中
	 */
	public static final String returning = "2";
	/**
	 * 已归还（通过了审批）
	 */
	public static final String returnOver = "3";
	/**
	 * 不同意
	 */
	public static final String notAudit = "4";
}
