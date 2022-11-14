package platform.education.rest.jw.service.vo;

import java.util.List;



public class StudentArchiveInformation {
	//基本信息
		private Basic basic;
		
		//学籍信息
		private Archive archive;
		
		//辅助信息
		private Assist assist;
		
		//扩展信息
		private Extra extra;
		
		//家长信息
		private Parent parent;
		
		//联系信息
		private Relation relation;
		
		//交通方式
		private Traffic traffic;
		
		//备注信息
		private Remarks remarks;
		
		public Basic getBasic() {
			return basic;
		}

		public void setBasic(Basic basic) {
			this.basic = basic;
		}

		public Archive getArchive() {
			return archive;
		}

		public void setArchive(Archive archive) {
			this.archive = archive;
		}

		public Assist getAssist() {
			return assist;
		}

		public void setAssist(Assist assist) {
			this.assist = assist;
		}

		public Extra getExtra() {
			return extra;
		}

		public void setExtra(Extra extra) {
			this.extra = extra;
		}

		public Parent getParent() {
			return parent;
		}

		public void setParent(Parent parent) {
			this.parent = parent;
		}

		public Relation getRelation() {
			return relation;
		}

		public void setRelation(Relation relation) {
			this.relation = relation;
		}

		public Traffic getTraffic() {
			return traffic;
		}

		public void setTraffic(Traffic traffic) {
			this.traffic = traffic;
		}

		public Remarks getRemarks() {
			return remarks;
		}

		public void setRemarks(Remarks remarks) {
			this.remarks = remarks;
		}



		//基本信息类
		public class Basic{
			private String name;
			
			private String sex;
			
			private String birthday;
			
			private String birthPlace;
			
			private String nativePlace;
			
			private String race;
			
			private String nationality;
			
			private String idCardType;
			
			private String idCardNumber;
			
			private String abroadCode;
			
			private String politicalStatus;
			
			private String healthStatus;
			
			private String bloodType;
			
			private String photoUuid;
			
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getSex() {
				return sex;
			}
			public void setSex(String sex) {
				this.sex = sex;
			}
			public String getBirthday() {
				return birthday;
			}
			public void setBirthday(String birthday) {
				this.birthday = birthday;
			}
			public String getBirthPlace() {
				return birthPlace;
			}
			public void setBirthPlace(String birthPlace) {
				this.birthPlace = birthPlace;
			}
			public String getNativePlace() {
				return nativePlace;
			}
			public void setNativePlace(String nativePlace) {
				this.nativePlace = nativePlace;
			}
			public String getRace() {
				return race;
			}
			public void setRace(String race) {
				this.race = race;
			}
			public String getNationality() {
				return nationality;
			}
			public void setNationality(String nationality) {
				this.nationality = nationality;
			}
			public String getIdCardType() {
				return idCardType;
			}
			public void setIdCardType(String idCardType) {
				this.idCardType = idCardType;
			}
			public String getIdCardNumber() {
				return idCardNumber;
			}
			public void setIdCardNumber(String idCardNumber) {
				this.idCardNumber = idCardNumber;
			}
			public String getAbroadCode() {
				return abroadCode;
			}
			public void setAbroadCode(String abroadCode) {
				this.abroadCode = abroadCode;
			}
			public String getPoliticalStatus() {
				return politicalStatus;
			}
			public void setPoliticalStatus(String politicalStatus) {
				this.politicalStatus = politicalStatus;
			}
			public String getHealthStatus() {
				return healthStatus;
			}
			public void setHealthStatus(String healthStatus) {
				this.healthStatus = healthStatus;
			}
			public String getBloodType() {
				return bloodType;
			}
			public void setBloodType(String bloodType) {
				this.bloodType = bloodType;
			}
			public String getPhotoUuid() {
				return photoUuid;
			}
			public void setPhotoUuid(String photoUuid) {
				this.photoUuid = photoUuid;
			}
		}
		
		//辅助信息类
		public class Assist{
			private String residenceAddress;
			
			private String residenceType;
			
			private String specialSkill;
			
			private String pinyinName;
			
			private String usedName;
			
			private String idCardDate;

			public String getResidenceAddress() {
				return residenceAddress;
			}

			public void setResidenceAddress(String residenceAddress) {
				this.residenceAddress = residenceAddress;
			}

			public String getResidenceType() {
				return residenceType;
			}

			public void setResidenceType(String residenceType) {
				this.residenceType = residenceType;
			}

			public String getSpecialSkill() {
				return specialSkill;
			}

			public void setSpecialSkill(String specialSkill) {
				this.specialSkill = specialSkill;
			}

			public String getPinyinName() {
				return pinyinName;
			}

			public void setPinyinName(String pinyinName) {
				this.pinyinName = pinyinName;
			}

			public String getUsedName() {
				return usedName;
			}

			public void setUsedName(String usedName) {
				this.usedName = usedName;
			}

			public String getIdCardDate() {
				return idCardDate;
			}

			public void setIdCardDate(String idCardDate) {
				this.idCardDate = idCardDate;
			}
		}
		
		//学籍信息类
		public class Archive{
			private String studentType;
			
			private String enrollType;
			
			private String attendSchoolType;
			
			private String studentSource;
			
			private String studentNumber;
			
			private String number;
			
			private String gradeId;
			
			private String teamId;
			
			private String enrollDate;
			
			private String leaveDate;

			public String getStudentType() {
				return studentType;
			}

			public void setStudentType(String studentType) {
				this.studentType = studentType;
			}

			public String getEnrollType() {
				return enrollType;
			}

			public void setEnrollType(String enrollType) {
				this.enrollType = enrollType;
			}

			public String getAttendSchoolType() {
				return attendSchoolType;
			}

			public void setAttendSchoolType(String attendSchoolType) {
				this.attendSchoolType = attendSchoolType;
			}

			public String getStudentSource() {
				return studentSource;
			}

			public void setStudentSource(String studentSource) {
				this.studentSource = studentSource;
			}

			public String getStudentNumber() {
				return studentNumber;
			}

			public void setStudentNumber(String studentNumber) {
				this.studentNumber = studentNumber;
			}

			public String getNumber() {
				return number;
			}

			public void setNumber(String number) {
				this.number = number;
			}

			public String getGradeId() {
				return gradeId;
			}

			public void setGradeId(String gradeId) {
				this.gradeId = gradeId;
			}

			public String getTeamId() {
				return teamId;
			}

			public void setTeamId(String teamId) {
				this.teamId = teamId;
			}

			public String getEnrollDate() {
				return enrollDate;
			}

			public void setEnrollDate(String enrollDate) {
				this.enrollDate = enrollDate;
			}

			public String getLeaveDate() {
				return leaveDate;
			}

			public void setLeaveDate(String leaveDate) {
				this.leaveDate = leaveDate;
			}

		}
		
		//联系信息类
		public class Relation{
			private String address;
			
			private String homeAddress;
			
			private String resideAddress;
			
			private String mobile;
			
			private String telephone;
			
			private String zipCode;
			
			private String email;
			
			private String homepage;

			public String getAddress() {
				return address;
			}

			public void setAddress(String address) {
				this.address = address;
			}

			public String getHomeAddress() {
				return homeAddress;
			}

			public void setHomeAddress(String homeAddress) {
				this.homeAddress = homeAddress;
			}

			public String getResideAddress() {
				return resideAddress;
			}

			public void setResideAddress(String resideAddress) {
				this.resideAddress = resideAddress;
			}

			public String getMobile() {
				return mobile;
			}

			public void setMobile(String mobile) {
				this.mobile = mobile;
			}

			public String getTelephone() {
				return telephone;
			}

			public void setTelephone(String telephone) {
				this.telephone = telephone;
			}

			public String getZipCode() {
				return zipCode;
			}

			public void setZipCode(String zipCode) {
				this.zipCode = zipCode;
			}

			public String getEmail() {
				return email;
			}

			public void setEmail(String email) {
				this.email = email;
			}

			public String getHomepage() {
				return homepage;
			}

			public void setHomepage(String homepage) {
				this.homepage = homepage;
			}
			
		}
		
		//扩展信息类
		public class Extra{
			private String isOnlyChild;
			
			private String isPreeducated;
			
			private String isUnattendedChild;
			
			private String isCityLabourChild;
			
			private String isOrphan;
			
			private String isMartyrChild;
			
			private String followStudy;
			
			private String disabilityType;
			
			private String isBuyedDegree;
			
			private String isSponsored;
			
			private String isFirstRecommended;
			
			private String houseAuthority;
			
			private String needSpecialCare;

			public String getIsOnlyChild() {
				return isOnlyChild;
			}

			public void setIsOnlyChild(String isOnlyChild) {
				this.isOnlyChild = isOnlyChild;
			}

			public String getIsPreeducated() {
				return isPreeducated;
			}

			public void setIsPreeducated(String isPreeducated) {
				this.isPreeducated = isPreeducated;
			}

			public String getIsUnattendedChild() {
				return isUnattendedChild;
			}

			public void setIsUnattendedChild(String isUnattendedChild) {
				this.isUnattendedChild = isUnattendedChild;
			}

			public String getIsCityLabourChild() {
				return isCityLabourChild;
			}

			public void setIsCityLabourChild(String isCityLabourChild) {
				this.isCityLabourChild = isCityLabourChild;
			}

			public String getIsOrphan() {
				return isOrphan;
			}

			public void setIsOrphan(String isOrphan) {
				this.isOrphan = isOrphan;
			}

			public String getIsMartyrChild() {
				return isMartyrChild;
			}

			public void setIsMartyrChild(String isMartyrChild) {
				this.isMartyrChild = isMartyrChild;
			}

			public String getFollowStudy() {
				return followStudy;
			}

			public void setFollowStudy(String followStudy) {
				this.followStudy = followStudy;
			}

			public String getDisabilityType() {
				return disabilityType;
			}

			public void setDisabilityType(String disabilityType) {
				this.disabilityType = disabilityType;
			}

			public String getIsBuyedDegree() {
				return isBuyedDegree;
			}

			public void setIsBuyedDegree(String isBuyedDegree) {
				this.isBuyedDegree = isBuyedDegree;
			}

			public String getIsSponsored() {
				return isSponsored;
			}

			public void setIsSponsored(String isSponsored) {
				this.isSponsored = isSponsored;
			}

			public String getIsFirstRecommended() {
				return isFirstRecommended;
			}

			public void setIsFirstRecommended(String isFirstRecommended) {
				this.isFirstRecommended = isFirstRecommended;
			}

			public String getHouseAuthority() {
				return houseAuthority;
			}

			public void setHouseAuthority(String houseAuthority) {
				this.houseAuthority = houseAuthority;
			}

			public String getNeedSpecialCare() {
				return needSpecialCare;
			}

			public void setNeedSpecialCare(String needSpecialCare) {
				this.needSpecialCare = needSpecialCare;
			}
			
		}
		
		//交通方式信息类
		public class Traffic{
			private String schoolDistance;
			
			private String trafficType;
			
			private String bySchoolBus;

			public String getSchoolDistance() {
				return schoolDistance;
			}

			public void setSchoolDistance(String schoolDistance) {
				this.schoolDistance = schoolDistance;
			}

			public String getTrafficType() {
				return trafficType;
			}

			public void setTrafficType(String trafficType) {
				this.trafficType = trafficType;
			}

			public String getBySchoolBus() {
				return bySchoolBus;
			}

			public void setBySchoolBus(String bySchoolBus) {
				this.bySchoolBus = bySchoolBus;
			}
		}
		
		//家庭成员信息类
		public class Parent{
			private List<ParentMess> parentMess;

			public List<ParentMess> getParentMess() {
				return parentMess;
			}

			public void setParentMess(List<ParentMess> parentMess) {
				this.parentMess = parentMess;
			}
			
		}
		
		//备注信息
		public class Remarks{
			private String remark;

			public String getRemark() {
				return remark;
			}

			public void setRemark(String remark) {
				this.remark = remark;
			}

		}
		
}
