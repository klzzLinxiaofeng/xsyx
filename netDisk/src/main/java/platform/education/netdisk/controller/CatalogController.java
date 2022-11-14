package platform.education.netdisk.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import platform.education.annotation.AuthIgnore;
import platform.education.annotation.PermissionIgnore;
import platform.education.netdisk.common.ResponseBean;
import platform.education.netdisk.entity.CatalogEntity;
import platform.education.netdisk.entity.FilesEntity;
import platform.education.netdisk.service.inter.CatalogService;
import platform.education.netdisk.service.inter.FilesService;
import platform.education.netdisk.vo.CatalogTree;
import platform.education.netdisk.vo.FilesCondition;
import platform.education.netdisk.vo.FilesViewVo;
import platform.education.netdisk.vo.ResFileVo;
import platform.education.util.RedisUtils;
import platform.service.storage.service.FileService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/catalog")
public class CatalogController { 

	
	@Autowired
	private CatalogService catalogService;

	@Autowired
	private FilesService filesService;

	@Autowired
	@Qualifier("fileService")
	private FileService fileService;

	@Autowired
	RedisUtils redisUtils;

//	@AuthIgnore
	@RequestMapping(value = "/allFile")
	@ResponseBody
	public ResponseBean index(@RequestBody CatalogEntity catalogCondition,
								HttpServletRequest request) {
		Integer userId = Integer.valueOf(request.getAttribute("userId").toString());

		Map<String, Object> result = new HashMap<>();

		Integer catalogId = catalogCondition.getId();
		catalogId = catalogId==null?0:catalogId;

		CatalogEntity catalogEntity = new CatalogEntity();
		catalogEntity.setParentId(catalogId);
		if(catalogId.equals(0)) catalogEntity.setOwnerId(userId);
		List<CatalogEntity> catalogEntityList = catalogService.selectCatalogByOwnIdOrPublic(catalogEntity);

//		FilesEntity filesEntity = new FilesEntity();
//		filesEntity.setCatalogId(catalogId);
//		List<FilesEntity> filesEntitieList = filesService.selectList(new EntityWrapper<>(filesEntity));
		FilesCondition filesCondition = new FilesCondition();
		filesCondition.setCatalogId(catalogId);
		List<ResFileVo> filesVoList = filesService.selectEntityFilesByFiles(filesCondition);
//		result.put("catalogList", catalogEntityList);
//		result.put("fileList", filesEntitieList);

		if(catalogId != 0){
			catalogEntity = catalogService.selectById(catalogId);
			if(catalogEntity != null) {
				CatalogTree catalogTree = catalogService.getAncestorTree(catalogEntity);
				List<FilesViewVo> treeList = new ArrayList<>();
				treeList.add(new FilesViewVo(catalogTree.getCatalogEntity()));

				while (catalogTree.getChildren() != null && catalogTree.getChildren().size() > 0){
					catalogTree = catalogTree.getChildren().get(0);
					treeList.add(new FilesViewVo(catalogTree.getCatalogEntity()));
				}
				result.put("catalogTree", treeList);
			}
		} else {

			result.put("catalogTree", null);
		}

		List<FilesViewVo> resultList = new ArrayList<>();
		for(CatalogEntity entity : catalogEntityList){
			resultList.add(new FilesViewVo(entity));
		}
		for(ResFileVo entity : filesVoList){
			resultList.add(new FilesViewVo(entity).setHttpUrl(this.fileService.relativePath2HttpUrl(entity.getRelativePath())));
		}

		result.put("list", resultList);

		return new ResponseBean(result);
	}


	@PostMapping(value = "/search")
	@ResponseBody
	public ResponseBean search(@RequestBody FilesCondition condition,
								HttpServletRequest request) {
		Integer userId = Integer.valueOf(request.getAttribute("userId").toString());


		Map<String, List> result = new HashMap<>();

		CatalogEntity catalogEntity = new CatalogEntity();
		catalogEntity.setName(condition.getName());
		catalogEntity.setOwnerId(userId);
		List<CatalogEntity> catalogEntityList = catalogService.selectCatalogByOwnIdOrPublic(catalogEntity);

		List<ResFileVo> filesVoList = filesService.selectEntityFilesByFiles(condition);

		List<FilesViewVo> resultList = new ArrayList<>();
		for(CatalogEntity entity : catalogEntityList){
			resultList.add(new FilesViewVo(entity));
		}
		for(ResFileVo entity : filesVoList){
			resultList.add(new FilesViewVo(entity).setHttpUrl(this.fileService.relativePath2HttpUrl(entity.getRelativePath())));
		}

		result.put("list", resultList);

		return new ResponseBean(result);
	}
	/**
//	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
//	@ResponseBody
//	public List<Catalog> jsonList(
//			@CurrentUser UserInfo user,
//			@ModelAttribute("condition") CatalogCondition condition,
//			@ModelAttribute("page") Page page,
//			@ModelAttribute("order") Order order,
//			@RequestParam(value = "usePage", required = false) boolean usePage) {
//		page = usePage ? page : null;
//		return this.catalogService.findCatalogByCondition(condition, page, order);
//	}
//
//	@RequestMapping(value = "/creator", method = RequestMethod.GET)
//	public ModelAndView creator() {
//		return new ModelAndView(structurePath("/input"));
//	}
//
	 */
	@PostMapping(value = "/creator")
	@ResponseBody
	public ResponseBean creator(@RequestBody CatalogEntity entity, HttpServletRequest request) {
		if(entity.getParentId() == null){
			entity.setParentId(0);
		}

		CatalogEntity catalog = new CatalogEntity();
		catalog.setParentId(entity.getParentId());
		catalog.setName(entity.getName());
		if (catalogService.selectCount(new EntityWrapper<>(catalog)) > 0){
			return new ResponseBean(ResponseBean.FAIL, "该文件名称已存在");
		}

		Integer userId = Integer.valueOf(request.getAttribute("userId").toString());
		entity.setOwnerId(userId);
		boolean insert = catalogService.insert(entity);
		if(insert){
			CatalogEntity catalogEntity = catalogService.selectById(entity.getId());
				return new ResponseBean(catalogEntity==null? null : new FilesViewVo(catalogEntity));
		}
		return new ResponseBean();//?new ResponseEntity(HttpStatus.OK):new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
	}
/**
//	@RequestMapping(value = "/editor", method = RequestMethod.GET)
//	public ModelAndView editor(
//            @RequestParam(value = "id", required = true) Integer id, Model model) {
//		Catalog catalog = this.catalogService.findCatalogById(id);
//		model.addAttribute("catalog", catalog);
//		return new ModelAndView(structurePath("/input"), model.asMap());
//	}
//
//	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
//	public ModelAndView viewer(
//			@RequestParam(value = "id", required = true) Integer id,
//			Model model) {
//		Catalog catalog = this.catalogService.findCatalogById(id);
//		model.addAttribute("isCK", "disable");
//		model.addAttribute("catalog", catalog);
//		return new ModelAndView(structurePath("/input"), model.asMap());
//	}
//*/
	@PostMapping(value = "/delete/{id}")
	@ResponseBody
	public ResponseBean delete(@PathVariable(value = "id") Integer id, HttpServletRequest request) {
		Integer userId = Integer.valueOf(request.getAttribute("userId").toString());
		try {
			CatalogEntity entity = new CatalogEntity();
			entity.setId(id);
			entity.setOwnerId(userId);

			entity = catalogService.selectOne(new EntityWrapper<>(entity));

			catalogService.removeCatalog(entity.getId());
		} catch (Exception e) {
			return new ResponseBean(ResponseBean.FAIL, "删除失败");
		}
		return new ResponseBean(ResponseBean.SUCCESS, "删除成功");
	}


	@PostMapping(value = "/deleteAll")
	@ResponseBody
	public ResponseBean deleteAll(@RequestBody List<FilesViewVo> voList, HttpServletRequest request) {
		Integer userId = Integer.valueOf(request.getAttribute("userId").toString());

		List<Integer> fileIds = new ArrayList<>();
		for(FilesViewVo vo : voList){
			if(vo.getType() == 0){
				CatalogEntity entity = new CatalogEntity();
				entity.setId(vo.getId());
				entity.setOwnerId(userId);
				entity = catalogService.selectOne(new EntityWrapper<>(entity));
				if(entity != null)
					catalogService.removeCatalog(entity.getId());
			} else {
				fileIds.add(vo.getId());
			}
		}
		if(fileIds.size() > 0)
			filesService.delete(new EntityWrapper<FilesEntity>().in("id", fileIds).eq("owner_id", userId));

		return new ResponseBean(true);
	}

	@PostMapping(value = "/edit")
	@ResponseBody
	public ResponseBean edit(@RequestBody FilesViewVo filesViewVo,
							   HttpServletRequest request) {
		Integer id = filesViewVo.getId();
		Integer userId = Integer.valueOf(request.getAttribute("userId").toString());
		if(filesViewVo.getType() == 0) {
			CatalogEntity entity = catalogService.selectById(id);
			if (filesViewVo.getName() != null) {
				CatalogEntity catalogEntityFilter = new CatalogEntity();
				catalogEntityFilter.setName(filesViewVo.getName());
				catalogEntityFilter.setParentId(entity.getParentId());
				if (!userId.equals(entity.getOwnerId())){
					return new ResponseBean(ResponseBean.NO_PERMISSION, "权限不足");
				}
				if ( catalogService.selectCount(new EntityWrapper<>(catalogEntityFilter)) > 0) {
					return new ResponseBean(ResponseBean.FAIL, "该文件名称已存在");
				}
			}
			entity = new CatalogEntity();
			entity.setId(id);
			entity.setName(filesViewVo.getName());
			boolean result = this.catalogService.updateById(entity);

			return new ResponseBean(result);//result?new ResponseEntity(HttpStatus.OK):new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
		} else if(filesViewVo.getType() == 1){
			FilesEntity entity = filesService.selectById(id);
			if (filesViewVo.getName() != null) {
				FilesEntity filesEntityFilter = new FilesEntity();
				filesEntityFilter.setName(filesViewVo.getName());
				filesEntityFilter.setCatalogId(entity.getCatalogId());
				if (!userId.equals(entity.getOwnerId())){
					return new ResponseBean(ResponseBean.NO_PERMISSION, "权限不足");
				}
				if ( filesService.selectCount(new EntityWrapper<>(filesEntityFilter)) > 0) {
					return new ResponseBean(ResponseBean.FAIL, "该文件名称已存在");
				}
			}
			entity = new FilesEntity();
			entity.setId(id);
			entity.setName(filesViewVo.getName());
			boolean result = this.filesService.updateById(entity);

			return new ResponseBean(result);

		}
		return new ResponseBean(ResponseBean.FAIL, "操作失败，请确认");
	}

}
