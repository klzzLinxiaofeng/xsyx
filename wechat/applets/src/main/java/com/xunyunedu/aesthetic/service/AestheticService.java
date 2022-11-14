package com.xunyunedu.aesthetic.service;

import com.xunyunedu.aesthetic.pojo.AestheticPojo;

public interface AestheticService {

   AestheticPojo  findByAlls( Integer studentId, Integer schoolId);
   String findByAllStudent(Integer studentId,Integer schoolId,Integer type);
}
