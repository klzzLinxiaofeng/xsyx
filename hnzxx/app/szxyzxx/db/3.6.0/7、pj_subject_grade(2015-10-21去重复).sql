DELETE a FROM pj_subject_grade a LEFT JOIN (SELECT (id) from pj_subject_grade GROUP BY school_id, subject_code, grade_code) b ON a.id = b.id
WHERE b.id IS NULL;

