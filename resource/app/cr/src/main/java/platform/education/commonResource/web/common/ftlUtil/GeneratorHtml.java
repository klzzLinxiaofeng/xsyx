package platform.education.commonResource.web.common.ftlUtil;
import java.io.BufferedWriter;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.OutputStreamWriter;  
import java.io.Writer;
import java.util.Locale;
import java.util.Map;  
  
import freemarker.template.Configuration;  
import freemarker.template.Template;  
import freemarker.template.TemplateException;  
  
/** 
 * freemarker生成静态html 
 * @author 张海明 
 * 
 */  
public class GeneratorHtml {  
    private Configuration config = null;    
    
    /**  
     * 如果目录不存在，则自动创建 
     * @param path  
     * @return boolean 是否成功  
     */    
    private boolean creatDirs(String path) {    
        File aFile = new File(path);    
        if (!aFile.exists()) {    
            return aFile.mkdirs();    
        } else {    
            return true;    
        }    
    }    
    
    /** 
     * 模板生成静态html的方法 
     * @param templateFileName(模板文件名) 
     * @param templateFilePath(指定模板目录) 
     * @param contextMap (用于处理模板的属性Object映射) 
     * @param htmlFilePath(指定生成静态html的目录) 
     * @param htmlFileName(生成的静态文件名) 
     */  
    @SuppressWarnings("unchecked")    
    public void geneHtmlFile(String templateFileName, String templateFilePath, Map contextMap,    
            String htmlFilePath, String htmlFileName) {    
    
        try {    
            Template t = this.getFreeMarkerCFG(templateFilePath).getTemplate(templateFileName, "UTF-8"); 
            t.setEncoding("UTF-8");
            // 如果根路径存在,则递归创建子目录     
            this.creatDirs(htmlFilePath);    
            File afile = new File(htmlFilePath + "/" + htmlFileName);    
            Writer out = new BufferedWriter(new OutputStreamWriter(    
                    new FileOutputStream(afile),"UTF-8"));    
            t.process(contextMap, out);    
            out.flush();    
            out.close();    
        } catch (TemplateException e) {    
            System.out.print(e.getMessage());    
        } catch (IOException e) {    
            System.out.print(e.getMessage());    
        } catch (Exception e) {    
            System.out.print(e.getMessage());    
        }    
    }    
    
    /**  
     *   
     * 获取freemarker的配置，freemarker本身支持classpath,目录或从ServletContext获取.  
     *   
     * @param templateFilePath  
     *            获取模板路径  
     * @return Configuration 返回freemaker的配置属性  
     * @throws Exception  
     */    
    private Configuration getFreeMarkerCFG(String templateFilePath)    
            throws Exception {    
        if (null == this.config) {    
    
            this.config = new Configuration();    
            try {    
                this.config.setDirectoryForTemplateLoading(new File(    
                        templateFilePath));    
                this.config.setEncoding(Locale.getDefault(), "UTF-8");
                
            } catch (Exception ex) {    
                throw ex;    
            }    
        }    
        return this.config;    
    }    
  
}  
