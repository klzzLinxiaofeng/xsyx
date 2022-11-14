package platform.szxyzxx.web.schoolbus.vo;

import framework.generic.dao.Page;

import java.io.Serializable;
import java.util.List;

public class StuVo<T> implements Serializable {

    Page page;

    List<T> list;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public <T> List<T> getList() {
        return (List<T>) list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
