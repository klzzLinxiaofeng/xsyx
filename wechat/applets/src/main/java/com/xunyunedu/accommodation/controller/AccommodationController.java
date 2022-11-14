package com.xunyunedu.accommodation.controller;


import com.xunyunedu.accommodation.pojo.Accommodation;
import com.xunyunedu.accommodation.service.AccommodationService;
import com.xunyunedu.common.dao.UploaderDao;
import com.xunyunedu.common.pojo.EntityFile;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.util.ftp.FtpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Accommodation")
@Api(value = "/Accommodation", description = "住宿管理")
public class AccommodationController {
    @Autowired
    private AccommodationService accommodationService;
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    private UploaderDao uploaderDao;
    @Autowired
    private FtpUtils ftpUtils;

    /*
     * 住宿登记列表
     */
    @RequestMapping("/findByAll")
    @ApiOperation(value = "住宿登记列表", httpMethod = "GET")
    public List<Accommodation> findByAll(@RequestParam Integer userId){
        List<Accommodation> list=accommodationService.findByAll(userId);
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //格式化当前系统日期
       for(Accommodation aa:list){
           aa.setShangBaoTime(dateFm.format(aa.getShangBaoTime2()));
       }
        return list;
    }
    @RequestMapping("/create")
    @ApiOperation(value = "住宿申请", httpMethod = "POST")
    public ApiResult create(@RequestBody Accommodation accommodation) {
        int num = accommodationService.create(accommodation);
        if(num>0){
            return new ApiResult(ResultCode.SUCCESS);
        }else{
            return new ApiResult(ResultCode.CREATE_FAIL);
        }
    }
    @RequestMapping("/findById")
    @ApiOperation(value = "住宿记录详情", httpMethod = "GET")
    public Accommodation findById(@RequestParam Integer id) {
        Accommodation accommodation = accommodationService.findById(id);

        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //格式化当前系统日期
        accommodation.setShangBaoTime(dateFm.format(accommodation.getShangBaoTime2()));
        String  bathUrl = "/accommodation/xiangqing";
        List<Map<String,Object>> fujianList=new ArrayList<>();
        List<Map<String,Object>> reshuiList=new ArrayList<>();
        List<Map<String,Object>> lenshuiList=new ArrayList<>();
        List<Map<String,Object>> dianbiaoList=new ArrayList<>();
        //附件
        if(accommodation.getFujianUUID()!=null && !accommodation.getFujianUUID().equals("")){
            String [] asd=accommodation.getFujianUUID().split(",");
            for(int a=0;a<asd.length;a++){
                Map<String,Object> map=new HashMap<>();
                EntityFile file = uploaderDao.findFileByUUID(asd[a]);
                if(file != null) {
                    map.put("fileName",file.getFileName());
                    map.put("url",ftpUtils.relativePath2HttpUrl(file));
                    map.put("uuid",asd[a]);
                }
                fujianList.add(map);
            }
        }
        //冷水
        if(accommodation.getColdPictureUUid()!=null && !accommodation.getColdPictureUUid().equals("")){
            String [] asd=accommodation.getColdPictureUUid().split(",");
            for(int a=0;a<asd.length;a++){
                Map<String,Object> map=new HashMap<>();
                EntityFile file = uploaderDao.findFileByUUID(asd[a]);
                if (file != null) {
                    map.put("fileName",file.getFileName());
                    map.put("url",ftpUtils.relativePath2HttpUrl(file));
                    map.put("uuid",asd[a]);
                }
                lenshuiList.add(map);
            }
        }
        //热水
        if(accommodation.getHotPictureUUid()!=null && !accommodation.getHotPictureUUid().equals("")){
            String [] asd=accommodation.getHotPictureUUid().split(",");
            for(int a=0;a<asd.length;a++){
                Map<String,Object> map=new HashMap<>();
                EntityFile file = uploaderDao.findFileByUUID(asd[a]);
                if (file != null) {
                    map.put("fileName",file.getFileName());
                    map.put("url",ftpUtils.relativePath2HttpUrl(file));
                    map.put("uuid",asd[a]);
                }
                reshuiList.add(map);
            }
        }
        //电表
        if(accommodation.getElectricityUUid()!=null && !accommodation.getElectricityUUid().equals("")){
            String [] asd=accommodation.getElectricityUUid().split(",");
            for(int a=0;a<asd.length;a++){
                Map<String,Object> map=new HashMap<>();
                EntityFile file = uploaderDao.findFileByUUID(asd[a]);
                if (file != null) {
                    map.put("fileName",file.getFileName());
                    map.put("url",ftpUtils.relativePath2HttpUrl(file));
                    map.put("uuid",asd[a]);
                }
                dianbiaoList.add(map);
            }
        }
        accommodation.setFujianMap(fujianList);
        accommodation.setDianbiaoMap(dianbiaoList);
        accommodation.setReshuiMap(reshuiList);
        accommodation.setLenshuiMap(lenshuiList);
        return accommodation;
    }
}
