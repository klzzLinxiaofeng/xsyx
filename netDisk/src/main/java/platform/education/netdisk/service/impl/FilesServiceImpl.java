package platform.education.netdisk.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.constant.FilesExtensions;
import platform.education.netdisk.entity.FilesEntity;
import platform.education.netdisk.mapper.FilesMapper;
import platform.education.netdisk.service.inter.FilesService;
import platform.education.netdisk.vo.FilesCondition;
import platform.education.netdisk.vo.ResFileVo;

import java.util.Date;
import java.util.List;

@Service
public class FilesServiceImpl extends ServiceImpl<FilesMapper, FilesEntity> implements FilesService {

	@Autowired
	private FilesMapper filesMapper;

	@Override
	public Integer deleteFilesByCatalogId(Integer catalogId) {
		FilesEntity filesEntity = new FilesEntity();
		filesEntity.setCatalogId(catalogId);
		return filesMapper.delete(new EntityWrapper<>(filesEntity));
	}

	@Override
	public List<FilesEntity> selectWithEntityFile(Integer userId, String name, Integer type) {
		String[] extensions = null;
		switch (type) {
			case 1: extensions = FilesExtensions.document;break;
			case 2: extensions = FilesExtensions.image;break;
			case 3: extensions = FilesExtensions.audio;break;
			case 4: extensions = FilesExtensions.voide;break;
		}
		return filesMapper.selectWithEntityFile(userId, name, extensions);
	}

	@Override
	public List<ResFileVo> selectEntityFilesByFiles(FilesCondition condition) {
		String[] extensions = null;
		switch (condition.getType()) {
			case 1: extensions = FilesExtensions.document;break;
			case 2: extensions = FilesExtensions.image;break;
			case 3: extensions = FilesExtensions.audio;break;
			case 4: extensions = FilesExtensions.voide;break;
		}
		return filesMapper.selectEntityFilesByFiles(condition, extensions);
	}


	@Override
	public boolean insert(FilesEntity entity){
		if(entity.getCreateDate() == null) {
			entity.setCreateDate(new Date());
		}
		return super.insert(entity);
	}
}
