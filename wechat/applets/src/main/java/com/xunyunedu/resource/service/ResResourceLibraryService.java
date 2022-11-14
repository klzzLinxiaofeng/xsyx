package com.xunyunedu.resource.service;

import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.resource.condition.ResResourceLibraryCondition;
import com.xunyunedu.resource.param.ResourceUploadParam;
import com.xunyunedu.resource.pojo.ResResourceLibrary;

import java.util.List;

public interface ResResourceLibraryService {
    List<ResResourceLibrary> getByCondition(ResResourceLibraryCondition condition);


    ResResourceLibrary getOneBySchoolIdAndAppId(Integer schoolId,Integer appId);

    ApiResult add(ResResourceLibrary library);


    ApiResult add(ResourceUploadParam param, String libUuid);
}
