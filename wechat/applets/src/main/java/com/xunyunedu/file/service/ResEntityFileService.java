package com.xunyunedu.file.service;


import com.xunyunedu.file.pojo.ResEntityFile;

public interface ResEntityFileService {
    ResEntityFile getById(Integer id);

    ResEntityFile getByUuid(String uuid);
}
