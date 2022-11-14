package platform.education.netdisk.service.inter;

import com.baomidou.mybatisplus.service.IService;
import platform.education.netdisk.entity.FilesEntity;
import platform.education.netdisk.vo.FilesCondition;
import platform.education.netdisk.vo.ResFileVo;

import java.util.List;

public interface FilesService extends IService<FilesEntity> {

    Integer deleteFilesByCatalogId(Integer catalogId);

    List<FilesEntity> selectWithEntityFile(Integer userId, String name, Integer type);

    List<ResFileVo> selectEntityFilesByFiles(FilesCondition condition);
}
