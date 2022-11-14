
package com.xunyunedu.repair.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xunyunedu.common.dao.UploaderDao;
import com.xunyunedu.common.pojo.EntityFile;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.repair.dao.ApplyRepairDao;
import com.xunyunedu.repair.pojo.AcceptRepari;
import com.xunyunedu.repair.pojo.ApplyrepairCommentParam;
import com.xunyunedu.repair.pojo.ApplyrepairCondition;
import com.xunyunedu.repair.pojo.ApplyrepairPojo;
import com.xunyunedu.repair.pojo.model.ShenHe;
import com.xunyunedu.repair.service.RepairService;
import com.xunyunedu.util.ftp.FtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class RepairServiceImpl implements RepairService {

    @Autowired
    private ApplyRepairDao repairDao;
    @Autowired
    private UploaderDao uploaderDao;

    @Autowired
    private FtpUtils ftpUtils;
    @Override
    public void add(ApplyrepairPojo repairPojo) {
        repairDao.add(repairPojo);
    }

    @Override
    public PageInfo pagingList(PageCondition<ApplyrepairCondition> condition) {
        Page page = PageHelper.startPage(condition.getPageNum(),condition.getPageSize());
        List<ApplyrepairPojo> list = repairDao.selectByCondition(condition.getCondition());
        setRealStatus(list);
        PageInfo pageInfo = page.toPageInfo();
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public ApplyrepairPojo getDetailById(Integer id) {
        ApplyrepairCondition condition=new ApplyrepairCondition();
        condition.setId(id);
        ApplyrepairPojo a= CollectionUtil.getFirst(repairDao.selectByCondition(condition));
        if(a!=null){
            if(a.getShenqingPictureId()!=null){
                EntityFile file = uploaderDao.findFileByUUID(a.getShenqingPictureId());
                if (file != null) {
                    a.setShenqingPictureUrl(ftpUtils.relativePath2HttpUrl(file));
                }
            }
            if(a.getPicture()!=null){
                EntityFile file = uploaderDao.findFileByUUID(a.getPicture());
                if (file != null) {
                    a.setPictureUrl(ftpUtils.relativePath2HttpUrl(file));
                }
            }
            a.setRealStatus(getRealStatus(a));
        }
        return a;
    }

    @Override
    @Transactional
    public void comment(ApplyrepairCommentParam param) {
        repairDao.commentRepair(param);
        ApplyrepairPojo a=new ApplyrepairPojo();
        a.setId(param.getRepairId());
        a.setRemark(param.getRemark());
        a.setModifyDate(new Date());
        repairDao.update(a);
    }

    @Override
    @Transactional
    public void addAcceptRepari(AcceptRepari acceptRepari,String state) {
        repairDao.addAcceptRepari(acceptRepari);
        ApplyrepairPojo a=new ApplyrepairPojo();
        a.setId(acceptRepari.getRepariId());
        a.setModifyDate(new Date());
        a.setStatus(state);
        repairDao.update(a);
    }

    @Override
    public void updateShenhe(ShenHe shenHe) {
        repairDao.updateShenhe(shenHe);
    }
    @Override
    public  ApplyrepairPojo findById( Integer id){
      return   repairDao.findById(id);
    }

    @Override
    public void addShiJian(ApplyrepairPojo applyrepairPojo) {
        repairDao.addShiJian(applyrepairPojo);
    }

    @Override
    public void updateShenHeLie(Integer type,Integer dataId) {
        repairDao.updateShenHeLie(type,dataId);
    }

    private void setRealStatus(List<ApplyrepairPojo> list){
        for (ApplyrepairPojo applyrepairPojo : list) {
            if(applyrepairPojo.getShenqingPictureId()!=null){
                EntityFile file = uploaderDao.findFileByUUID(applyrepairPojo.getShenqingPictureId());
                if (file != null) {
                    applyrepairPojo.setShenqingPictureUrl(ftpUtils.relativePath2HttpUrl(file));
                }
            }
            if(applyrepairPojo.getPicture()!=null){
                EntityFile file = uploaderDao.findFileByUUID(applyrepairPojo.getPicture());
                if (file != null) {
                    applyrepairPojo.setPictureUrl(ftpUtils.relativePath2HttpUrl(file));
                }
            }

            applyrepairPojo.setRealStatus(getRealStatus(applyrepairPojo));
        }
    }

    private Integer getRealStatus(ApplyrepairPojo a){
        if(a.getStatus().equals("01") || a.getStatus().equals("02")){
            return 0;
        }
        if(a.getXingji()==null){
            return 1;
        }
        return 2;
    }
}

