package com.xunyunedu.canteen.dao;

import com.xunyunedu.canteen.model.CanteenPublicityPojo;
import com.xunyunedu.canteen.model.CanteenRecipesPojo;

import java.util.List;


/**
 *  @author: yhc
 *  @Date: 2020/12/31 13:35
 *  @Description:
 */
public interface CanteenRecipesDao {

    List<CanteenRecipesPojo> selectByExample(CanteenRecipesPojo example);

    List<CanteenPublicityPojo> getCanteenPublicity(CanteenPublicityPojo example);
}