package platform.education.netdisk.service.inter;

import com.baomidou.mybatisplus.service.IService;
import platform.education.netdisk.entity.CatalogEntity;
import platform.education.netdisk.vo.CatalogTree;

import java.util.List;

public interface CatalogService extends IService<CatalogEntity> {

    void removeCatalog(Integer id);

    List<CatalogEntity> selectCatalogByOwnIdOrPublic(CatalogEntity entity);

    CatalogTree getAncestorTree(CatalogEntity entity);

    CatalogTree getAncestorTree(CatalogTree tree);
}
