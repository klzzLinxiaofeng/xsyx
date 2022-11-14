package com.xunyunedu.screening.service;

import com.xunyunedu.screening.vo.Screening;

import java.util.List;

public interface ScreeningService {
    List<Screening> findByAll(Integer userId);

   Integer create(Screening screening);
    Screening findById(Integer id);
}
