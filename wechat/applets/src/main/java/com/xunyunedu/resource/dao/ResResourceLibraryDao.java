package com.xunyunedu.resource.dao;

import com.xunyunedu.resource.condition.ResResourceLibraryCondition;
import com.xunyunedu.resource.pojo.ResResourceLibrary;

import java.util.List;

public interface ResResourceLibraryDao {


    List<ResResourceLibrary> selectByCondition(ResResourceLibraryCondition condition);

    Integer insert(ResResourceLibrary resResourceLibrary);
}
