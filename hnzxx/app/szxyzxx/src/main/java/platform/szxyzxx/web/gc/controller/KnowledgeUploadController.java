package platform.szxyzxx.web.gc.controller;

import framework.generic.facility.poi.excel.config.ParseConfig;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalcode.model.KnowledgeNode;
import platform.education.generalcode.service.KnowledgeNodeService;
import platform.education.generalcode.vo.KnowledgeNodeCondition;
import platform.szxyzxx.web.common.util.ExcelUtil;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.*;

/**
 * Created by panfei on 2017/3/27.
 */
@Controller
@RequestMapping("/knowledge")
public class KnowledgeUploadController {

    @Autowired
    @Qualifier("knowledgeNodeService")
    private KnowledgeNodeService knowledgeNodeService;

    /**
     * @function 上传知识点描述页面路径
     * @param model
     * @return
     */
    @RequestMapping(value = "/uploadDescribePage")
    public ModelAndView toUploadPage(Model model){
        KnowledgeNodeCondition knowledgeNodeCondition = new KnowledgeNodeCondition();
        knowledgeNodeCondition.setIsDeleted(false);
        knowledgeNodeCondition.setParentId(0);
        knowledgeNodeCondition.setLevel(1);
        List<KnowledgeNode> knowledgeNodeList = knowledgeNodeService.findKnowledgeNodeByCondition(knowledgeNodeCondition);
        model.addAttribute("knoledgeList",knowledgeNodeList);
        return new ModelAndView("/generalcode/knowledgetree/importpage",model.asMap());
    }

    @RequestMapping(value = "downloadKnoledgeNode")
    @ResponseBody
    public void downloadData(
            @RequestParam(value = "treeId",required = true) Integer treeId,
                             HttpServletResponse response,
                             HttpServletRequest request) throws Exception{
        List<KnowledgeNode> knowledgeNodes = knowledgeNodeService.findKnowledgeNodeByTreeId(treeId,null,null);
        if(knowledgeNodes != null && knowledgeNodes.size() > 0){
            // 装配所有的数据项
            List<Object> maps = new ArrayList<Object>();
            ParseConfig config = SzxyExcelTookit.getConfig();
            // 装配知识点数据
            Map<String, Object> map = new HashMap<String, Object>();
            // 列名
            String[] columnNames = new String[3];
            // 数据库对应的列名称
            String[] filesNames = new String[3];

            for(KnowledgeNode knowledgeNode : knowledgeNodes){
                map = new HashMap<String, Object>();
                map.put("nodeId",knowledgeNode.getId());
                map.put("nodeName",knowledgeNode.getName());
                map.put("describe",knowledgeNode.getDescribe());
                maps.add(map);
            }

            columnNames[0] = "ID";
            filesNames[0] = "nodeId";
            columnNames[1] = "名称";
            filesNames[1] = "nodeName";
            columnNames[2] = "描述";
            filesNames[2] = "describe";

            config.setTitles(columnNames);
            config.setFieldNames(filesNames);
            config.setSheetTitle("知识点描述模板信息");

            SzxyExcelTookit.exportExcelToWEB(maps, config, request, response,"数据导入模板.xls");
        }
    }

    @RequestMapping(value = "uploadData")
    @ResponseBody
    public Map<String,Object> uploadData(
            @RequestParam("fileUpload") MultipartFile fileUpload
    )throws Exception{
        InputStream is = null;
        List<KnowledgeNode> listForError = new ArrayList<KnowledgeNode>();
        List<KnowledgeNode> listForRight = new ArrayList<KnowledgeNode>();
        Map<String,Object> map = new HashMap<String,Object>();
        is = fileUpload.getInputStream();
        KnowledgeNode kn = null;
        List<KnowledgeNode> excelList = this.excelDataToObject(is);
        if(excelList != null && excelList.size() > 0){
            for(KnowledgeNode knowledgeNode : excelList){
                if(knowledgeNode.getId() <= 0){
                    listForError.add(knowledgeNode);
                }else{
                    try{
                        if("".equals(knowledgeNode.getDescribe())){
                            knowledgeNode.setDescribe(null);
                        }
                        knowledgeNode.setModifyDate(new Date());
                        kn = knowledgeNodeService.modify(knowledgeNode);
                        if(kn != null){
                            listForRight.add(knowledgeNode);
                        }else {
                            knowledgeNode.setDescribe("未知异常，导致导入失败");
                            listForError.add(knowledgeNode);
                        }
                    }catch (Exception e){
                        knowledgeNode.setDescribe("未知异常，导致导入失败");
                        listForError.add(knowledgeNode);
                        e.printStackTrace();
                    }
                }
            }
        }
        map.put("ERROR", listForError);
        map.put("SUCCESS", listForRight);
        return map;
    }

    /**
     * @function 将EXCEL中的数据传化为所需的实体类
     * @param inputStream
     * @return
     * @throws Exception
     */
    public List<KnowledgeNode> excelDataToObject(InputStream inputStream) throws Exception{
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sh = workbook.getSheetAt(0);
        KnowledgeNode knowledgeNode = null;
        List<KnowledgeNode> knowledgeNodeList = new ArrayList<>();
        if(sh != null){
            int rowNum = sh.getLastRowNum();
            //循环每一行获取数据
            String id = "";
            String name = "";
            String describe = "";
            for(int i = 1; i <= rowNum; i++){
                try{
                    HSSFRow hr = sh.getRow(i);

                    if("".equals(hr.getCell(0))){
                        continue;
                    }

                    //获取节点Id
                    id = ExcelUtil.parseExcel(hr.getCell(0));
                    //获取节点名称
                    name = ExcelUtil.parseExcel(hr.getCell(1));
                    //获取节点描述
                    describe = ExcelUtil.parseExcel(hr.getCell(2));

                    knowledgeNode = new KnowledgeNode();
                    knowledgeNode.setId(Str2Integer(id));
                    knowledgeNode.setName(name);
                    knowledgeNode.setDescribe(describe);
                    knowledgeNodeList.add(knowledgeNode);
                }catch (NumberFormatException e){
                    //用户修改ID为非数字
                    knowledgeNode = new KnowledgeNode();
                    knowledgeNode.setId(-1);
                    knowledgeNode.setName(name);
                    knowledgeNode.setDescribe("数据异常，知识点ID为非数字");
                    knowledgeNodeList.add(knowledgeNode);
                }
            }
        }
        return knowledgeNodeList;
    }

    /**
     * @function String转换成Integer
     * 在获取excel数据的时候，有可能描述过长，导致ID是 12.0的类似数据
     * @param num
     * @return
     */
    public int Str2Integer(String num){
        if("".equals(num)){
            return -1;
        }
        double n = Double.parseDouble(num);
        return (int)n;
    }
 }
