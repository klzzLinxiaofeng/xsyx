package platform.szxyzxx.dto.seewo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbsBasicSeewoMapDataSender extends AbsBasicSeewoDataSender<Map<String,Object>> {

    protected abstract String getIdFieldName();


    /**
     * 更新发送成功数据的ld列表的状态
     */
    protected abstract void updateSendSuccessIdsStatus(Integer[] successfulIdArr);

    @Override
    protected void updateStatus(List<Map<String, Object>> sendDataList, List<Map<String, Object>> sendFailList) {
        if(sendFailList==null || sendFailList.size()==0){
            updateSendSuccessIdsStatus(createIntegerArrByList(sendDataList));
        }else{
            List<Map<String, Object>> successfulList=new ArrayList<>(sendDataList.size()-sendFailList.size());
            String idName=getIdFieldName();
            List<Object> failedIdList= createIdList(sendFailList);
            for (Map<String, Object> map : sendDataList) {
                if(!failedIdList.contains(map.get(idName))){
                    successfulList.add(map);
                }
            }
            updateSendSuccessIdsStatus(createIntegerArrByList(successfulList));
        }
    }

    private List<Object> createIdList(List<Map<String, Object>> list){
        String idName=getIdFieldName();
        List<Object> idList=new ArrayList<Object>(list.size());
        for (Map<String, Object> map : list) {
            idList.add(map.get(idName));
        }
        return idList;
    }

    private Integer[] createIntegerArrByList(List<Map<String, Object>> list){
        String idName=getIdFieldName();
        Integer[] arr=new Integer[list.size()];
        for (int i=0;i<list.size();i++){
            arr[i]=Integer.valueOf(list.get(i).get(idName).toString());
        }
        return arr;
    }

}
