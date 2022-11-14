package platform.szxyzxx.web.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.library.service.LibraryService;
import platform.szxyzxx.library.vo.Library;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

/**
 * @Author zhangyong
 * @Date 2022/10/31 10:17
 * @Version 1.0
 */
@RestController
@RequestMapping("/libraryapi")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;
    @Autowired
    private BasicSQLService basicSQLService;

    /*
    * 学生
    */
    @RequestMapping("/test")
    public String create() throws Exception {
        List<Map<String,Object>> list =basicSQLService.find("select ps.* from pj_student ps " +
                " inner join pj_team pt on pt.id=ps.team_id " +
                " inner join pj_grade pg on pg.id=pt.grade_id  " +
                " where ps.is_delete=0 and ps.study_state='01' and pg.stage_code=2 and " +
                " pt.is_delete=0 and pg.is_deleted=0 and pg.school_year=2022" );
        String sql="INSERT into pj_library(school_id,name)value(:schoolId,:name)";
        for(Map<String,Object> bb:list){
             libraryService.addLibrary(sql,bb);
        }
        /* String sql="INSERT into pj_library(school_id,name)value(:schoolId,:name)";
         Library library=new Library();
         library.setSchoolId(215);
         library.setName("测试");
         Map<String, Object> map = new HashMap<>();
         map = JSONObject.parseObject(JSONObject.toJSONString(library), Map.class);
         return libraryService.addLibrary(sql,map)?"success":"error";*/
        return "success";
     }

    /*
     * 第一次将所有教师推送到图书馆数据库中
     */
    @RequestMapping("/createTeacher")
    public String createTeacher() throws Exception {
        List<Map<String,Object>> list =basicSQLService.find("select * from pj_teacher " +
                " where is_delete=0 and school_id=215 and job_state='11'");
        String sql="INSERT into pj_library(school_id,name)value(:schoolId,:name)";
        for(Map<String,Object> bb:list){
            libraryService.addLibrary(sql,bb);
        }
        /* String sql="INSERT into pj_library(school_id,name)value(:schoolId,:name)";
         Library library=new Library();
         library.setSchoolId(215);
         library.setName("测试");
         Map<String, Object> map = new HashMap<>();
         map = JSONObject.parseObject(JSONObject.toJSONString(library), Map.class);
         return libraryService.addLibrary(sql,map)?"success":"error";*/
        return "success";
    }

    @RequestMapping("/list")
    public List<Library> findByLibrary(){
        String sql="select * from  pj_library";
        return libraryService.findByLibrary(sql);
    }

    public  Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null) {
            return null;
        }

        Object obj = beanClass.newInstance();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){
                continue;
            }
            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }
        return obj;
    }
}
