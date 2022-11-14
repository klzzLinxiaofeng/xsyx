package com.xunyunedu.canteen.service;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.canteen.model.CanteenPublicityPojo;
import com.xunyunedu.canteen.model.CanteenRecipesPojo;

import java.util.List;


/**
 *  @author: yhc
 *  @Date: 2020/12/31 13:35
 *  @Description:
 */
public interface CanteenRecipesService {

    PageInfo<CanteenRecipesPojo> selectByExample(CanteenRecipesPojo example, Integer pageNum, Integer pageSize);

    PageInfo<CanteenPublicityPojo> getCanteenPublicity(CanteenPublicityPojo example, Integer pageNum, Integer pageSize);
}
