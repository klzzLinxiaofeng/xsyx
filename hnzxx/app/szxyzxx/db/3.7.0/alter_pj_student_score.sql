ALTER TABLE `pj_student_score` 
CHANGE COLUMN `score` `score` VARCHAR(5) NULL COMMENT '成绩' ;

delete from pj_student_score where  student_id in  (  
      select e.student_id  from  (  
            select a.*  from pj_student_score as a ,  
            pj_student_score as b where a.student_id =  b.student_id 
						GROUP BY b.school_id,b.school_year, b.exam_team_subject_id,b.exam_name,b.team_id,b.student_id 
						HAVING COUNT(*)>1
) e   
);
