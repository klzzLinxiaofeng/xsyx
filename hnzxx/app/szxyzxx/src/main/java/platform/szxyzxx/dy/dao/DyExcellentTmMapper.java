package platform.szxyzxx.dy.dao;

import platform.szxyzxx.dy.pojo.DyExcellentTm;

/**
 * @Entity platform.szxyzxx.dy.domain.DyExcellentTm
 */
public interface DyExcellentTmMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(DyExcellentTm record);

    int createSelective(DyExcellentTm record);

    DyExcellentTm findByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DyExcellentTm record);


}




