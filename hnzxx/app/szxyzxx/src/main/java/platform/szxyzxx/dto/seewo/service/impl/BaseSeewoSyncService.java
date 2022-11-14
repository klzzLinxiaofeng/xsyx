package platform.szxyzxx.dto.seewo.service.impl;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseSeewoSyncService<P,S> {
    /**
     * 判断两个对象主键是否相同
     */
    abstract boolean pkEqual(P platformObj, S seewoObj);

    /**
     * 根据与s对象主键相等的对象p,判断对象s是否需要更新
     */
    abstract boolean needUpdateSeewoObj(P platformObj, S seewoObj);

    protected List<P> getAddList(List<P> platformList,List<S> seewoList){
        List<P> addList=new ArrayList<>(0);
        for (P p : platformList) {
            if(findSeewoObjByPlatform(seewoList,p)==null){
                addList.add(p);
            }
        }
        return addList;
    }

    protected List<S> getDeleteList(List<P> platformList,List<S> seewoList){
        List<S> deleteList=new ArrayList<>(0);
        for (S s : seewoList) {
            if(findPlatformObjBySeewo(platformList,s)==null){
                deleteList.add(s);
            }
        }
        return deleteList;
    }

    protected List<S> getUpdateList(List<P> platformList,List<S> seewoList){
        List<S> updateList=new ArrayList<>(0);
        for (P p : platformList) {
            S s=findSeewoObjByPlatform(seewoList,p);
            if(s!=null && needUpdateSeewoObj(p,s)){
                updateList.add(s);
            }
        }
        return updateList;
    }


    private S findSeewoObjByPlatform(List<S> list, P p){
        for (S s : list) {
            if(pkEqual(p,s)){
                return s;
            }
        }
        return null;
    }

    private P findPlatformObjBySeewo(List<P> list, S s){
        for (P p : list) {
            if(pkEqual(p,s)){
                return p;
            }
        }
        return null;
    }

}
