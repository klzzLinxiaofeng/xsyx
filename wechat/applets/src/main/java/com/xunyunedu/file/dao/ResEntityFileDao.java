package com.xunyunedu.file.dao;

import com.xunyunedu.file.pojo.ResEntityFile;

public interface ResEntityFileDao {

    ResEntityFile selectById(Integer id);

    ResEntityFile selectByUuid(String uuid);
}
