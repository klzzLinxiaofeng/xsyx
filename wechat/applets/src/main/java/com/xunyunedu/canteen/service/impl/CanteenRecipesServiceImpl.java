package com.xunyunedu.canteen.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xunyunedu.canteen.dao.CanteenRecipesDao;
import com.xunyunedu.canteen.model.CanteenCuisinPojo;
import com.xunyunedu.canteen.model.CanteenPublicityPojo;
import com.xunyunedu.canteen.model.CanteenRecipesPojo;
import com.xunyunedu.canteen.service.CanteenRecipesService;
import com.xunyunedu.common.dao.UploaderDao;
import com.xunyunedu.common.pojo.EntityFile;
import com.xunyunedu.personinfor.pojo.QuestionnairePojo;
import com.xunyunedu.util.ftp.FtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author: yhc
 * @Date: 2020/12/31 13:35
 * @Description:
 */
@Service
public class CanteenRecipesServiceImpl implements CanteenRecipesService {

    @Autowired
    private CanteenRecipesDao canteenRecipesDao;

    @Autowired
    private UploaderDao uploaderDao;

    @Autowired
    private FtpUtils ftpUtils;

    @Override
    public PageInfo<CanteenRecipesPojo> selectByExample(CanteenRecipesPojo example, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        example.setIsDelete(0);
        List<CanteenRecipesPojo> canteenRecipesPojos = canteenRecipesDao.selectByExample(example);
        for (CanteenRecipesPojo canteenRecipesPojo : canteenRecipesPojos) {
            List<CanteenCuisinPojo> canteenCuisinePojoList = canteenRecipesPojo.getCanteenCuisinPojoList();
            if (CollUtil.isNotEmpty(canteenCuisinePojoList)) {
                for (CanteenCuisinPojo canteenCuisinPojo : canteenCuisinePojoList) {
                    String coverUuid = canteenCuisinPojo.getUuid();
                    if (coverUuid != null && !("").equals(coverUuid)) {
                        // 根据uuid查询封面的url
                        EntityFile file = uploaderDao.findFileByUUID(coverUuid);
                        if (file != null) {
                            canteenCuisinPojo.setUrl(ftpUtils.relativePath2HttpUrl(file));
                        }
                    }
                }
            }
        }
        PageInfo<CanteenRecipesPojo> objectPageInfo = new PageInfo<>(canteenRecipesPojos);
        return objectPageInfo;
    }

    @Override
    public PageInfo<CanteenPublicityPojo> getCanteenPublicity(CanteenPublicityPojo example, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        example.setIsDelete(0);
        List<CanteenPublicityPojo> canteenRecipesPojos = canteenRecipesDao.getCanteenPublicity(example);
        for (CanteenPublicityPojo publicityPojo : canteenRecipesPojos) {
            String coverUuid = publicityPojo.getUuid();
            if (coverUuid != null && !("").equals(coverUuid)) {
                // 根据uuid查询封面的url
                EntityFile file = uploaderDao.findFileByUUID(coverUuid);
                if (file != null) {
                    publicityPojo.setUrl(ftpUtils.relativePath2HttpUrl(file));
                }
            }
        }
        PageInfo<CanteenPublicityPojo> objectPageInfo = new PageInfo<>(canteenRecipesPojos);
        return objectPageInfo;
    }

}
