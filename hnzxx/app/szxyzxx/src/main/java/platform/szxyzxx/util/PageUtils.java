package platform.szxyzxx.util;

import framework.generic.dao.Page;
import platform.szxyzxx.web.schoolbus.vo.StuVo;

import java.util.ArrayList;
import java.util.List;

public class PageUtils {

    public static StuVo pagingGetStuVoByList(List list, Page page){
        page.setTotalRows(list.size());
        StuVo stuVo=new StuVo();
        int start=(page.getCurrentPage()-1)*page.getPageSize();
        int end=start+ page.getPageSize();
        if(end>list.size()){
            end=list.size();
        }
        List pageList=new ArrayList(page.getPageSize());
        for(int i=start;i<end;i++){
            pageList.add(list.get(i));
        }
        stuVo.setPage(page);
        stuVo.setList(pageList);
        return stuVo;
    }
}
