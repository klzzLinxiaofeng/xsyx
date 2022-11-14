package platform.szxyzxx.dto.seewo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.szxyzxx.dto.seewo.BatchSeewoDataOperateService;
import platform.szxyzxx.dto.seewo.SeewoDataSender;
import platform.szxyzxx.dto.seewo.pojo.BatchOperateResult;

import java.util.List;

public abstract class AbsBasicSeewoDataSender<T> implements SeewoDataSender {
    private static final Logger logger = LoggerFactory.getLogger(AbsBasicSeewoDataSender.class);
    protected abstract String getDataName();

    protected abstract BatchSeewoDataOperateService getDataOperator();

    protected abstract List<T> querySendData();

    protected abstract void updateStatus(List<T> sentDataList,List<T> sendFailList);


    @Override
    public void sendNotSynchronizedDataAndUpdate(){
        logger.debug("开始同步{}到seewo",getDataName());
        try {
            List<T> list=querySendData();
            if(list!=null && list.size()>0){
                logger.info("发送数据size:"+list.size());
                BatchOperateResult<T> result= getDataOperator().batchAdd(list);
                logger.info("{}发送到seewo成功，失败数据size："+result.getExceptionDataList().size(),getDataName());
                //有发送成功的数据才更新
                if(list.size()>result.getExceptionDataList().size()){
                    updateStatus(list,result.getExceptionDataList());
                }
                logger.info("{}同步成功",getDataName());
            }else{
                logger.debug("当前没有未同步的{}",getDataName());
            }

        } catch (Exception e) {
            logger.error(getDataName()+"数据同步到seewo失败",e);
        }
    }

}
