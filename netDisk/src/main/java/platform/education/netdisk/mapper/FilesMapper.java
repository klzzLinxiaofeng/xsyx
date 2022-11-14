package platform.education.netdisk.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import platform.education.netdisk.entity.FilesEntity;
import platform.education.netdisk.vo.FilesCondition;
import platform.education.netdisk.vo.ResFileVo;

import java.util.List;

@Mapper
@Repository
public interface FilesMapper extends BaseMapper<FilesEntity> {

    void deleteFilesByCatalogId(Integer catalogId);

    List<FilesEntity> selectWithEntityFile(Integer userId, String name, String[] extensions);

    List<ResFileVo> selectEntityFilesByFiles(FilesCondition condition, String[] extensions);
}
