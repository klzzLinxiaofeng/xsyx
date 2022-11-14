package platform.szxyzxx.web.schoolbus.vo;

import framework.generic.dao.Page;

import java.util.List;

public class Pages<T> extends Page {

    List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
