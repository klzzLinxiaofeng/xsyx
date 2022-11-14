package com.xunyunedu.resource.service;

import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.file.pojo.ResEntityFile;
import com.xunyunedu.resource.param.ResourceUploadParam;
import com.xunyunedu.resource.pojo.ResPaidResource;

public interface ResPaidResourceService {
    ResPaidResource getById(Integer id);

    ResPaidResource getByUuid(String uuid);

    ApiResult add(ResPaidResource resource);

}
