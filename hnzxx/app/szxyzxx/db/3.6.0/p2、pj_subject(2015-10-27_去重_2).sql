DELETE a FROM pj_subject a LEFT JOIN (SELECT (id) from pj_subject GROUP BY school_id, stage_code, code) b ON a.id = b.id
WHERE b.id IS NULL;