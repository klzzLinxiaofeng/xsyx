package com.xunyunedu.accommodation.service;

import com.xunyunedu.accommodation.pojo.Accommodation;

import java.util.List;

public interface AccommodationService {
    List<Accommodation> findByAll(Integer userId);
    Accommodation findById(Integer id);
    Integer  create(Accommodation accommodation);
}
