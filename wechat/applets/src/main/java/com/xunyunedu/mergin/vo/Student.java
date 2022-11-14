package com.xunyunedu.mergin.vo;

import framework.generic.dao.Model;

import java.util.Date;



public class Student implements Model<Integer> {
/*    public class Student implements Model<Integer> {*/
        private static final long serialVersionUID = 1L;
        /**
         * 主健ID
         */
        private Integer id;
        /**
         * 所属学校
         */
        private Integer schoolId;
        /**
         * 对应的人
         */
        private Integer personId;
        /**
         * 用户帐号
         */
        private Integer userId;
        /**
         * 当前最后所在班ID
         */
        private Integer teamId;
        /**
         * 用户名
         */
        private String userName;
        /**
         * 当前最后所在班级名称
         */
        private String teamName;
        /**
         * 姓名
         */
        private String name;

        /**
         * 性别
         */
        private String sex;
        /**
         * 全国统一学籍号
         */
        private String studentNumber;
        /**
         * 学籍辅号
         */
        private String studentNumber2;
        /**
         * 是否住宿
         */
        private String isBoarded;

        /**
         * 入学时间
         */
        private Date enrollDate;
        /**
         * 离校时间
         */
        private Date leaveDate;
        /**
         * 手机号码
         */
        private String mobile;
        /**
         * 学生在读状态:01=在读。02=休学。03=退学。04=停学。07=毕业。08=结业。09=肄业。10=转学.11=死亡 14=开除。99=其它
         */
        private String studyState;
        /**
         * 学生类别
         */
        private String studentType;
        /**
         * 职务
         */
        private String position;
        /**
         * 删除标志
         */
        private boolean isDelete;
        /**
         * 创建时间
         */
        private Date createDate;
        /**
         * 修改时间
         */
        private Date modifyDate;

        /**
         * 图片ID
         */

        private String entityId;

        private String stuNum;

        private String alias;

        private String photoUuid;
        /**
         *食堂充值卡号，用到地方食堂、图书馆、闸机、班牌
         */
        private String empCard;
        /**
         *对应食堂工号，用到地方食堂、图书馆、闸机、班牌
         */
        private String empCode;
        /**
         *学生信息是否发送到图书馆，1：已发送(发送成功)，0：没有发送(包含发送失败，失败也需要重试)
         */
        private Integer isSendLibrary;
        /**
         *学生信息是否发送到海康，1：已发送(发送成功)，0：没有发送(包含发送失败，失败也需要重试)
         */
        private Integer isSendHikdoor;

        // 海康是否绑卡成功1：已绑卡，0：没有& 需要修改
        private Integer isHikvisionBindCard;


        /**
         * 学生信息是否发送到食堂，1：已发送(发送成功)，0：没有发送(包含发送失败，失败也需要重试)
         */
        private Integer isSendCanteen;

        private Integer isSendSeewo;

        /**
         * 发送到食堂的部门名称
         */
        private String deptName;


        public Integer getIsHikvisionBindCard() {
            return isHikvisionBindCard;
        }

        public void setIsHikvisionBindCard(Integer isHikvisionBindCard) {
            this.isHikvisionBindCard = isHikvisionBindCard;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }


        public Integer getIsSendCanteen() {
            return isSendCanteen;
        }

        public void setIsSendCanteen(Integer isSendCanteen) {
            this.isSendCanteen = isSendCanteen;
        }

        public Integer getIsSendSeewo() {
            return isSendSeewo;
        }

        public void setIsSendSeewo(Integer isSendSeewo) {
            this.isSendSeewo = isSendSeewo;
        }

        public String getAlias() {
            return alias;
        }


        public void setAlias(String alias) {
            this.alias = alias;
        }


        public String getStuNum() {
            return stuNum;
        }


        public void setStuNum(String stuNum) {
            this.stuNum = stuNum;
        }


        public String getEntityId() {
            return entityId;
        }


        public void setEntityId(String entityId) {
            this.entityId = entityId;
        }

        public String getEmpCard() {
            return empCard;
        }

        public void setEmpCard(String empCard) {
            this.empCard = empCard;
        }

        public String getEmpCode() {
            return empCode;
        }

        public void setEmpCode(String empCode) {
            this.empCode = empCode;
        }

        public Integer getIsSendLibrary() {
            return isSendLibrary;
        }

        public void setIsSendLibrary(Integer isSendLibrary) {
            this.isSendLibrary = isSendLibrary;
        }

        public Integer getIsSendHikdoor() {
            return isSendHikdoor;
        }

        public void setIsSendHikdoor(Integer isSendHikdoor) {
            this.isSendHikdoor = isSendHikdoor;
        }

        public Student() {

        }


        /**
         * 获取主健ID
         *
         * @return java.lang.Integer
         */
        public Integer getId() {
            return this.id;
        }

        /**
         * 设置主健ID
         *
         * @param id
         * @type java.lang.Integer
         */
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         * 获取所属学校
         *
         * @return java.lang.Integer
         */
        public Integer getSchoolId() {
            return this.schoolId;
        }

        /**
         * 设置所属学校
         *
         * @param schoolId
         * @type java.lang.Integer
         */
        public void setSchoolId(Integer schoolId) {
            this.schoolId = schoolId;
        }

        /**
         * 获取对应的人
         *
         * @return java.lang.Integer
         */
        public Integer getPersonId() {
            return this.personId;
        }

        /**
         * 设置对应的人
         *
         * @param personId
         * @type java.lang.Integer
         */
        public void setPersonId(Integer personId) {
            this.personId = personId;
        }

        /**
         * 获取用户帐号
         *
         * @return java.lang.Integer
         */
        public Integer getUserId() {
            return this.userId;
        }

        /**
         * 设置用户帐号
         *
         * @param userId
         * @type java.lang.Integer
         */
        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        /**
         * 获取当前最后所在班ID
         *
         * @return java.lang.Integer
         */
        public Integer getTeamId() {
            return this.teamId;
        }

        /**
         * 设置当前最后所在班ID
         *
         * @param teamId
         * @type java.lang.Integer
         */
        public void setTeamId(Integer teamId) {
            this.teamId = teamId;
        }

        /**
         * 获取用户名
         *
         * @return java.lang.String
         */
        public String getUserName() {
            return this.userName;
        }

        /**
         * 设置用户名
         *
         * @param userName
         * @type java.lang.String
         */
        public void setUserName(String userName) {
            this.userName = userName;
        }

        /**
         * 获取当前最后所在班级名称
         *
         * @return java.lang.String
         */
        public String getTeamName() {
            return this.teamName;
        }

        /**
         * 设置当前最后所在班级名称
         *
         * @param teamName
         * @type java.lang.String
         */
        public void setTeamName(String teamName) {
            this.teamName = teamName;
        }

        /**
         * 获取姓名
         *
         * @return java.lang.String
         */
        public String getName() {
            return this.name;
        }

        /**
         * 设置姓名
         *
         * @param name
         * @type java.lang.String
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * 获取必别
         *
         * @return java.lang.String
         */
        public String getSex() {
            return this.sex;
        }

        /**
         * 设置必别
         *
         * @param sex
         * @type java.lang.String
         */
        public void setSex(String sex) {
            this.sex = sex;
        }

        /**
         * 获取全国统一学籍号
         *
         * @return java.lang.String
         */
        public String getStudentNumber() {
            return this.studentNumber;
        }

        /**
         * 设置全国统一学籍号
         *
         * @param studentNumber
         * @type java.lang.String
         */
        public void setStudentNumber(String studentNumber) {
            this.studentNumber = studentNumber;
        }

        /**
         * 获取入学时间
         *
         * @return java.util.Date
         */
        public Date getEnrollDate() {
            return this.enrollDate;
        }

        /**
         * 设置入学时间
         *
         * @param enrollDate
         * @type java.util.Date
         */
        public void setEnrollDate(Date enrollDate) {
            this.enrollDate = enrollDate;
        }

        /**
         * 获取离校时间
         *
         * @return java.util.Date
         */
        public Date getLeaveDate() {
            return this.leaveDate;
        }

        /**
         * 设置离校时间
         *
         * @param leaveDate
         * @type java.util.Date
         */
        public void setLeaveDate(Date leaveDate) {
            this.leaveDate = leaveDate;
        }

        /**
         * 获取手机号码
         *
         * @return java.lang.String
         */
        public String getMobile() {
            return this.mobile;
        }

        /**
         * 设置手机号码
         *
         * @param mobile
         * @type java.lang.String
         */
        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        /**
         * 获取学生在读状态:01=在读。02=休学。03=退学。04=停学。07=毕业。08=结业。09=肄业。10=转学.11=死亡 14=开除。99=其它
         *
         * @return java.lang.String
         */
        public String getStudyState() {
            return this.studyState;
        }

        /**
         * 设置学生在读状态:01=在读。02=休学。03=退学。04=停学。07=毕业。08=结业。09=肄业。10=转学.11=死亡 14=开除。99=其它
         *
         * @param studyState
         * @type java.lang.String
         */
        public void setStudyState(String studyState) {
            this.studyState = studyState;
        }

        /**
         * 获取学生类别
         *
         * @return java.lang.String
         */
        public String getStudentType() {
            return this.studentType;
        }

        /**
         * 设置学生类别
         *
         * @param studentType
         * @type java.lang.String
         */
        public void setStudentType(String studentType) {
            this.studentType = studentType;
        }

        /**
         * 获取职务
         *
         * @return java.lang.String
         */
        public String getPosition() {
            return this.position;
        }

        /**
         * 设置职务
         *
         * @param position
         * @type java.lang.String
         */
        public void setPosition(String position) {
            this.position = position;
        }

        /**
         * 获取删除标志
         *
         * @return java.lang.Integer
         */
        public boolean getIsDelete() {
            return this.isDelete;
        }

        /**
         * 设置删除标志
         *
         * @param isDelete
         * @type java.lang.Integer
         */
        public void setIsDelete(boolean isDelete) {
            this.isDelete = isDelete;
        }

        /**
         * 获取创建时间
         *
         * @return java.util.Date
         */
        public Date getCreateDate() {
            return this.createDate;
        }

        /**
         * 设置创建时间
         *
         * @param createDate
         * @type java.util.Date
         */
        public void setCreateDate(Date createDate) {
            this.createDate = createDate;
        }

        /**
         * 获取修改时间
         *
         * @return java.util.Date
         */
        public Date getModifyDate() {
            return this.modifyDate;
        }

        /**
         * 设置修改时间
         *
         * @param modifyDate
         * @type java.util.Date
         */
        public void setModifyDate(Date modifyDate) {
            this.modifyDate = modifyDate;
        }

        public Student(Integer id) {
            this.id = id;
        }

        public Integer getKey() {
            return this.id;
        }

        public void setDelete(boolean isDelete) {
            this.isDelete = isDelete;
        }

        public String getStudentNumber2() {
            return studentNumber2;
        }

        public String getIsBoarded() {
            return isBoarded;
        }

        public void setIsBoarded(String isBoarded) {
            this.isBoarded = isBoarded;
        }


        public void setStudentNumber2(String studentNumber2) {
            this.studentNumber2 = studentNumber2;
        }


        public String getPhotoUuid() {
            return photoUuid;
        }


        public void setPhotoUuid(String photoUuid) {
            this.photoUuid = photoUuid;
        }


    }
