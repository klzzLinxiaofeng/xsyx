package platform.szxyzxx.web.bbx.client.vo;

public class ClientSchoolVo {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 学校Id
	 */
	private Integer  id;
	
	/**
	 * 学校名称 
	 */
	private String  name;
	
	/**
	 * 学校校徽图片存放路径
	 */
	private String badgeUrl;
	
	/**
	 * 学校所在省
	 */
	private String province;
	
	
	/**
	 * 学校所在市
	 */
	private String city;
	
	
	/**
	 * 学校所在行政区域
	 */
	private String regionCode;
	
	/**
	 * 学校标识码
	 */
	private String schoolCode;
	
	public ClientSchoolVo(){
		
	}
	
	public ClientSchoolVo(Integer id, String name, String badgeUrl,
			String province, String city, String regionCode,String schoolCode) {
		super();
		this.id = id;
		this.name = name;
		this.badgeUrl = badgeUrl;
		this.province = province;
		this.city = city;
		this.regionCode = regionCode;
		this.schoolCode = schoolCode;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getBadgeUrl() {
		return badgeUrl;
	}


	public void setBadgeUrl(String badgeUrl) {
		this.badgeUrl = badgeUrl;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getRegionCode() {
		return regionCode;
	}


	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}
	
}
