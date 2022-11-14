package com.xunyunedu.dateUtils.service.serviceImpl;

import com.xunyunedu.dateUtils.service.DateService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
@Service
public class DateServiceImpl implements DateService {

    @Override
    public List<Integer> getDateList(String date) {
        Calendar now=Calendar.getInstance();
        try {
            now.setTime(new SimpleDateFormat("yyyy-MM").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return new ArrayList<>(0);
        }
        int maxDayNum=now.getActualMaximum(Calendar.DAY_OF_MONTH);
        List<Integer> list = new ArrayList<Integer>(maxDayNum);
        for(int i = 1;i<=maxDayNum;i++){
            list.add(i);
        }
        return list;
    }




}
