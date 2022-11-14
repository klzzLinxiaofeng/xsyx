#####===================宿舍表=============================####
#将floor_code与floor_name对换，则floor_code表示楼层COde码，floor_name表示原来的宿舍楼编号
UPDATE xw_hq_dormitory c1,xw_hq_dormitory c2 SET c1.floor_code = c2.floor_name,c1.floor_name=c2.floor_code WHERE c1.id=c2.id;
#####===================宿舍表=============================####

#####===================宿舍人员表=============================####
#查询出在人员表中没有宿舍的学生
SELECT a.id FROM(
	SELECT
			hd.id as hid,
			dp.*
		FROM
			xw_hq_dormitory_person dp
		LEFT JOIN xw_hq_dormitory hd ON dp.school_id = hd.school_id
		AND dp.floor_code = hd.floor_name
		AND dp.dormitory_code = hd.dormitory_code GROUP BY dp.id
) a WHERE a.hid IS NULL;

#删除宿舍人员表中 在宿舍找不到对应没有宿舍的学生
DELETE FROM xw_hq_dormitory_person WHERE id IN (SELECT a.id FROM(
	SELECT
			hd.id as hid,
			dp.*
		FROM
			xw_hq_dormitory_person dp
		LEFT JOIN xw_hq_dormitory hd ON dp.school_id = hd.school_id
		AND dp.floor_code = hd.floor_name
		AND dp.dormitory_code = hd.dormitory_code GROUP BY dp.id
) a WHERE a.hid IS NULL);  

#更新学生数据
UPDATE xw_hq_dormitory_person dp,(SELECT
			hd.id AS hdid,
			dp.*
		FROM
			xw_hq_dormitory_person dp
		LEFT JOIN xw_hq_dormitory hd ON dp.school_id = hd.school_id
		AND dp.floor_code = hd.floor_name
		AND dp.dormitory_code = hd.dormitory_code GROUP BY dp.id) a SET dp.floor_code = a.hdid WHERE dp.school_id = a.school_id
		AND dp.floor_code = a.floor_code
		AND dp.dormitory_code = a.dormitory_code;
#####===================宿舍人员表=============================####


#####===================宿舍日常检查表=============================####
SELECT a.id FROM(
	SELECT
			hd.id as hid,
			dp.*
		FROM
			xw_hq_dormitory_daycheck dp
		LEFT JOIN xw_hq_dormitory hd ON dp.school_id = hd.school_id
		AND dp.floor_code = hd.floor_name
		AND dp.dormitory_code = hd.dormitory_code GROUP BY dp.id
) a WHERE a.hid IS NULL;

#删除宿舍日常检查表中 在宿舍找不到对应没有宿舍的学生
DELETE FROM xw_hq_dormitory_daycheck WHERE id IN (SELECT a.id FROM(
	SELECT
			hd.id as hid,
			dp.*
		FROM
			xw_hq_dormitory_daycheck dp
		LEFT JOIN xw_hq_dormitory hd ON dp.school_id = hd.school_id
		AND dp.floor_code = hd.floor_name
		AND dp.dormitory_code = hd.dormitory_code GROUP BY dp.id
) a WHERE a.hid IS NULL);  

#更新数据表
UPDATE xw_hq_dormitory_daycheck dp,(SELECT
			hd.id AS hdid,
			dp.*
		FROM
			xw_hq_dormitory_daycheck dp
		LEFT JOIN xw_hq_dormitory hd ON dp.school_id = hd.school_id
		AND dp.floor_code = hd.floor_name
		AND dp.dormitory_code = hd.dormitory_code GROUP BY dp.id) a SET dp.floor_code = a.hdid WHERE dp.school_id = a.school_id
		AND dp.floor_code = a.floor_code
		AND dp.dormitory_code = a.dormitory_code;
#####===================宿舍日常检查表=============================####


#####===================宿舍考勤表=============================####
SELECT a.id FROM(
	SELECT
			hd.id as hid,
			dp.*
		FROM
			xw_hq_dormitory_attendance dp
		LEFT JOIN xw_hq_dormitory hd ON dp.school_id = hd.school_id
		AND dp.floor_code = hd.floor_name
		AND dp.dormitory_code = hd.dormitory_code GROUP BY dp.id
) a WHERE a.hid IS NULL;

#删除宿舍人员表中 在宿舍找不到对应没有宿舍的学生
DELETE FROM xw_hq_dormitory_attendance WHERE id IN (SELECT a.id FROM(
	SELECT
			hd.id as hid,
			dp.*
		FROM
			xw_hq_dormitory_attendance dp
		LEFT JOIN xw_hq_dormitory hd ON dp.school_id = hd.school_id
		AND dp.floor_code = hd.floor_name
		AND dp.dormitory_code = hd.dormitory_code GROUP BY dp.id
) a WHERE a.hid IS NULL);  

#更新数据表
UPDATE xw_hq_dormitory_attendance dp,(SELECT
			hd.id AS hdid,
			dp.*
		FROM
			xw_hq_dormitory_attendance dp
		LEFT JOIN xw_hq_dormitory hd ON dp.school_id = hd.school_id
		AND dp.floor_code = hd.floor_name
		AND dp.dormitory_code = hd.dormitory_code GROUP BY dp.id) a SET dp.floor_code = a.hdid WHERE dp.school_id = a.school_id
		AND dp.floor_code = a.floor_code
		AND dp.dormitory_code = a.dormitory_code;

#####===================宿舍考勤表=============================####


####======数据同步完之后，将表的列名、属性整理
#将宿舍表中的floor_name数据设置为null
alter table xw_hq_dormitory modify floor_name VARCHAR(50) NULL;
UPDATE xw_hq_dormitory SET floor_name = null;

#修改宿舍人员表的floor_code的属性为Integer
alter table xw_hq_dormitory_person modify floor_code INTEGER(10);

#修改宿舍人员表的列名 由floor_code变成 dormitory_id 
alter table xw_hq_dormitory_person change column floor_code dormitory_id INTEGER(10) NOT NULL;

#修改宿舍日常检查表的列名 由floor_code变成 dormitory_id 
alter table xw_hq_dormitory_daycheck change column floor_code dormitory_id INTEGER(10) NOT NULL;

#修改宿舍考勤表的列名 由floor_code变成 dormitory_id 
alter table xw_hq_dormitory_attendance change column floor_code dormitory_id INTEGER(10) NOT NULL;
