 
CREATE TABLE `oa_notice` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) DEFAULT  NULL  COMMENT '公告标题',
  `content` text COMMENT '公告描述',
  `type` varchar(50)  DEFAULT NULL COMMENT '公告类型',
  `user_id` int(10) DEFAULT  NULL COMMENT '发布者ID',
  `user_name` varchar(50) DEFAULT  NULL COMMENT '发布者用户名',  
   `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间', 
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 
CREATE TABLE `oa_notice_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键', 
  `user_id` int(10) DEFAULT  NULL COMMENT '用户ID',
  `notice_id` int(10) DEFAULT  NULL COMMENT '关联的公告ID',  
   `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间', 
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 
CREATE TABLE `oa_leave` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `createname` varchar(32) DEFAULT NULL COMMENT '创建人名字',
  `createuser_id` int(10) DEFAULT NULL COMMENT '创建人id',
  `day` float DEFAULT NULL COMMENT '创建人名字',
  `department_id` int(10) DEFAULT NULL COMMENT '部门ID',
  `starttime` varchar(25) DEFAULT NULL COMMENT '开始时间',
  `endtime` varchar(25) DEFAULT NULL COMMENT '结束时间',
  `kewuanpai` varchar(500) DEFAULT NULL COMMENT '课务安排',
  `leaveinfo` varchar(500) DEFAULT NULL COMMENT '请假说明',
  `leavestate` int(11) DEFAULT NULL COMMENT '0为正在审批中，1为同意，2为不同意',
  `leavetype` varchar(40) DEFAULT NULL COMMENT '请假类型',
  `userimage` varchar(150) DEFAULT NULL COMMENT '创建人头像',
  `school_id` int(11) DEFAULT NULL COMMENT '学校ID',
  `sel_appr_name` varchar(1000) DEFAULT NULL COMMENT '审批人名字列表',
  `sel_cc_name` varchar(1000) DEFAULT NULL COMMENT '抄送人名字列表',
  `uuid` varchar(36) DEFAULT NULL COMMENT 'UUID',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 
CREATE TABLE `oa_leave_appr` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `leave_id` int(11) DEFAULT NULL COMMENT '关联的请假ID',
  `approvation_id` int(11) DEFAULT NULL COMMENT '审批人ID',
  `approvation_name` varchar(50) DEFAULT NULL COMMENT '审批人',
  `approvation` varchar(500) DEFAULT NULL COMMENT '审批意见',
  `leavestate` int(11) DEFAULT NULL COMMENT '状态',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


 
CREATE TABLE `oa_leave_appr_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `leave_id` int(11) DEFAULT NULL COMMENT '关联的请假ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `appr_state` int(11) DEFAULT NULL COMMENT '审批状态,为0时审批中，为1已审批',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


 
CREATE TABLE `oa_meeting` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `school_id` int(11) DEFAULT NULL COMMENT '学校ID',
  `createuser_id` int(10) DEFAULT NULL COMMENT '创建人id',
  `createname` varchar(32) DEFAULT NULL COMMENT '创建人名字',
  `userimage` varchar(150) DEFAULT NULL COMMENT '创建人头像',
  `department_id` int(10) DEFAULT NULL COMMENT '部门ID',
  `starttime` varchar(25) DEFAULT NULL COMMENT '开始时间',
  `endtime` varchar(25) DEFAULT NULL COMMENT '结束时间',
  `meeting_name` varchar(200) DEFAULT NULL COMMENT '会议名称',
  `meeting_type`  int(10) DEFAULT NULL COMMENT '会议类别,1:重要，0:一般',
  `address` varchar(200) DEFAULT NULL COMMENT '会议地点',
   `zhuchi` varchar(100) DEFAULT NULL COMMENT '主持人',
   `meeting_content` varchar(1000) DEFAULT NULL COMMENT '会议说明', 
    `quanbu` int(10) DEFAULT NULL COMMENT '1：全部人参加，0：选择的人参加', 
  `uuid` varchar(36) DEFAULT NULL COMMENT 'UUID',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


 
CREATE TABLE `oa_meeting_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `meeting_id` int(11) DEFAULT NULL COMMENT '会议ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID', 
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 




	 

 

