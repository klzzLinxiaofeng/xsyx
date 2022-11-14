package platform.education.netdisk.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import platform.education.netdisk.entity.CatalogEntity;

import java.util.List;

@Mapper
@Repository
public interface CatalogMapper extends BaseMapper<CatalogEntity> {

    List<CatalogEntity> selectCatalogByOwnIdOrPublic(CatalogEntity entity);

}
