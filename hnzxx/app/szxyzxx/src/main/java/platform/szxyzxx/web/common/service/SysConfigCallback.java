package platform.szxyzxx.web.common.service;

import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import com.baidu.disconf.client.common.update.IDisconfUpdate;
import org.springframework.stereotype.Service;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.util.SysContantsAccessor;


/**
 * Created by clouds on 2017/7/20.
 */
@Service
@DisconfUpdateService(confFileKeys = {"System.properties"})
public class SysConfigCallback implements IDisconfUpdate {

    public SysConfigCallback() {
        System.out.println("---------");
    }

    @Override
    public void reload() throws Exception {
        SysContants.reload();
    }
}
