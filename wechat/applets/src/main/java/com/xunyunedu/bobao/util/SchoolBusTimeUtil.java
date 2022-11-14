package com.xunyunedu.bobao.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SchoolBusTimeUtil {

        /**
         * 上学开始时间
         */
        public static final String UP_BEGIN_GMT_TIME ="T00:00:00.000+08:00";
        /**
         * 上学结束时间
         */
        public static final String UP_END_GMT_TIME ="T11:59:59.999+08:00";
        /**
         * 放学开始时间
         */
        public static final String DOWN_BEGIN_GMT_TIME ="T12:00:00.000+08:00";
        /**
         * 放学结束时间
         */
        public static final String DOWN_END_GMT_TIME ="T23:59:59.999+08:00";

        public static String formatGmtEventDateTime(String eventTime){
            if(eventTime!=null && eventTime.length()==25){
                return eventTime.substring(0,10)+" "+eventTime.substring(11,19);
            }
            return eventTime;
        }

        public static String formatGmtEventTimeToSecond(String dateTime){
            if(dateTime!=null && dateTime.length()==25){
                return dateTime.substring(11,19);
            }
            return dateTime;
        }


        public static String getNowDateStr(){
            Date nowDate=new Date();
            return new SimpleDateFormat("yyyy-MM-dd").format(nowDate);
        }

        public static int getNowDirection(){
            Calendar now=Calendar.getInstance();
            now.setTime(new Date());
            int hour=now.get(Calendar.HOUR_OF_DAY);
            if(hour>=0 && hour<12){
                return 0;
            }else{
                return 1;
            }
        }

        public static String getUpBeginGmtDateTimeStr(String date){
            return date+ UP_BEGIN_GMT_TIME;
        }


        public static String getUpEndGmtDateTimeStr(String date){
            return date+ UP_END_GMT_TIME;
        }

        public static String getDownBeginGmtDateTimeStr(String date){
            return date+ DOWN_BEGIN_GMT_TIME;
        }
        public static String getDownEndGmtDateTimeStr(String date){
            return date+ DOWN_END_GMT_TIME;
        }


        public static String getUpBeginDateTime( String date){
            return date+" 00:00:00";
        }

        public static String getUpEndDateTime( String date){
            return date+" 11:59:59";
        }

        public static String getDownBeginDateTime( String date){
            return date+" 12:00:00";
        }

        public static String getDownEndDateTime( String date){
            return date+" 23:59:59";
        }

    }
