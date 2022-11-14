UPDATE pj_subject_grade psg INNER JOIN pj_grade pg ON psg.grade_code = pg.id SET psg.grade_code = pg.uni_grade_code;


