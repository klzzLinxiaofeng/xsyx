package platform.szxyzxx.core.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * 获取spring的IOC容器实例：ApplicationContext，之所以要定义成一个静态属性，是为了在某些无法注入的当前bean的情况下使用
 */
@Service
public class WebApplicationContextAware implements ApplicationContextAware {

    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        WebApplicationContextAware.applicationContext=applicationContext;
    }
}
