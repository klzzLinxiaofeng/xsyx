package platform.education.rest.user.service.vo;

import java.io.Serializable;

public class UserDetailInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//用户ID
	private Integer userId;
	
	//用户名
	private String userName;
	
	//密码
	private String password;
	
	//姓名
	private String name;
	
	//二维码
	private String qrCodeFile;
	
	//性别
	private String sex;
	
	//所在区域
	private Area area;
	
	//联系电话
	private String mobile;
	
	//邮箱
	private String email;
	
	//个性前面
	private String signature;
	
	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getQrCodeFile() {
		return qrCodeFile;
	}


	public void setQrCodeFile(String qrCodeFile) {
		this.qrCodeFile = qrCodeFile;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public Area getArea() {
		return area;
	}


	public void setArea(Area area) {
		this.area = area;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSignature() {
		return signature;
	}


	public void setSignature(String signature) {
		this.signature = signature;
	}

	//所在地区
	public class Area{
		
		//所在省
		private String province;
		
		//所在市
		private String city;
		
		//所在区
		private String district;
		
		//所在街道
		private String street;

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

		public String getDistrict() {
			return district;
		}

		public void setDistrict(String district) {
			this.district = district;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}
		
	}
	
}
