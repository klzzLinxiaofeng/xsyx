package platform.education.netdisk.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import platform.education.netdisk.entity.CatalogEntity;
import platform.education.netdisk.mapper.CatalogMapper;
import platform.education.netdisk.service.inter.CatalogService;
import platform.education.netdisk.service.inter.FilesService;
import platform.education.netdisk.vo.CatalogTree;

import java.util.Date;
import java.util.List;

@Service
public class CatalogServiceImpl extends ServiceImpl<CatalogMapper, CatalogEntity> implements CatalogService {

    @Autowired
    private FilesService filesService;

    @Autowired
    private CatalogMapper catalogMapper;

    /**
     * 删除目录及目录中的文件和文件夹
     * @param id
     */
    @Override
    @Transactional
    public void removeCatalog(Integer id) {
        CatalogEntity catalog = new CatalogEntity();
        catalog.setParentId(id);
        List<CatalogEntity> catalogList = this.selectList(new EntityWrapper<>(catalog));
        for(CatalogEntity entity : catalogList){
            this.removeCatalog(entity.getId());
        }
        this.deleteById(id);
        filesService.deleteFilesByCatalogId(id);
    }

    @Override
    public List<CatalogEntity> selectCatalogByOwnIdOrPublic(CatalogEntity entity) {
        return catalogMapper.selectCatalogByOwnIdOrPublic(entity);
    }

    @Override
    public CatalogTree getAncestorTree(CatalogEntity entity){
        if(entity == null){
            return null;
        }
        CatalogTree tree = new CatalogTree(entity);

        return getAncestorTree(tree);
    }

    @Override
    public CatalogTree getAncestorTree(CatalogTree tree){
        if(0 == tree.getParentId()){
            return tree;
        }
        CatalogTree fatherTree = new CatalogTree(this.selectById(tree.getParentId()));
        fatherTree.addChildren(tree);
        return getAncestorTree(fatherTree);
    }

    @Override
    public boolean insert(CatalogEntity entity){
        if(entity.getCreateDate() == null) {
            entity.setCreateDate(new Date());
        }
        return super.insert(entity);
    }
}
