package platform.szxyzxx.web.performanceAnalysis.controller;


import framework.generic.dao.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.core.pojo.BasicResult;
import platform.szxyzxx.excelhelper.exception.CellResolveException;
import platform.szxyzxx.excelhelper.exports.Exporter;
import platform.szxyzxx.excelhelper.exports.impl.DefaultExporter;
import platform.szxyzxx.excelhelper.imports.Importer;
import platform.szxyzxx.excelhelper.imports.impl.DefaultImporter;
import platform.szxyzxx.excelhelper.pojo.ExcelExportParam;
import platform.szxyzxx.excelhelper.pojo.ExcelImportParam;
import platform.szxyzxx.resultsStatistical.pojo.PerformanceAnalysis;
import platform.szxyzxx.resultsStatistical.pojo.vo.PaQuery;
import platform.szxyzxx.resultsStatistical.service.PerformanceAnalysisSerivce;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.schoolbus.vo.StuVo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/performance/analysis")
public class PerformanceAnalysisController extends BaseController {
    @Autowired
    private PerformanceAnalysisSerivce serivce;

    @Autowired
    private BasicSQLService basicSQLService;

    private Importer<PerformanceAnalysis> importer;

    private Exporter exporter;

    public PerformanceAnalysisController(){
        ExcelImportParam<PerformanceAnalysis> param=new ExcelImportParam<>(PerformanceAnalysis.class);
        param.setStartRowIndex(1);
        importer=new DefaultImporter<>(param);
        exporter=new DefaultExporter(new ExcelExportParam<>(PerformanceAnalysis.class," ????????????"));

    }

    @RequestMapping(value = "/getalls")
    public ModelAndView getalls(){
        ModelAndView mov = new ModelAndView("/performanceAnalysis/performanceAnalysis");
        return mov;
    }

    @RequestMapping(value = "/getall")
    public StuVo getall( PaQuery param,Page page){
        StuVo s=new StuVo();
        s.setPage(page);
        if(StringUtils.isEmpty(param.getTestName())){
            s.setList(new ArrayList(0));
            page.setTotalRows(0);
        }else {
            List<PerformanceAnalysis> list = serivce.findByAll(param, page);
            s.setList(list);
        }
        return s;
    }
    /*
    * ????????????????????????
    * */
    @RequestMapping(value = "/downLoadExcel")
    @ResponseBody
    public void downLoadExcel(PaQuery param,HttpServletResponse response) {
        try {
            //page???null?????????
            List<PerformanceAnalysis> list=serivce.findByAll(param,null);
            Workbook wb= exporter.exportToNew(list);
            OutputStream out=response.getOutputStream();
            response.setHeader("Content-disposition", "attachment; filename="+ URLEncoder.encode("????????????.xls","UTF-8"));
            response.setContentType("application/msexcel");
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * ????????????????????????
     * */
    @RequestMapping("add")
    @ResponseBody
    public BasicResult add(MultipartFile file,PerformanceAnalysis obj){
        try {
            List<PerformanceAnalysis> list=importer.importBy(file.getInputStream());
            if(list==null || list.size()==0){
                return BasicResult.error("??????????????????");
            }

            //?????????????????????????????????????????????
            PerformanceAnalysis an=list.get(0);
            if(basicSQLService.findUniqueLong("select exists(select 1 from performance_analysis where xn='"+obj.getXn()+"' and xq='"+obj.getXq()+"' and testName='"+an.getTestName()+"')")==1){
                basicSQLService.update("delete from performance_analysis where xn='"+obj.getXn()+"' and xq='"+obj.getXq()+"' and testName='"+an.getTestName()+"'");
            }
            for (PerformanceAnalysis importObj : list) {
                importObj.setXq(obj.getXq());
                importObj.setXn(obj.getXn());
                serivce.add(importObj);
            }
            return BasicResult.success();
        }catch (CellResolveException e){
            e.printStackTrace();
            return BasicResult.error("???"+(e.getRowIndex()+1)+"??????"+(e.getColIndex()+1)+"???????????????????????????????????????");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return BasicResult.error("????????????");

    }

    @RequestMapping("/addPage")
    public ModelAndView addPage(){
        return view("add");
    }

    private ModelAndView view(String pageName){
        return new ModelAndView( "performanceAnalysis/"+pageName);
    }

}
