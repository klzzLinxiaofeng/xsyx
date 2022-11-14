package platform.szxyzxx.web.common.contants;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.web.context.support.XmlWebApplicationContext;


/**
 * 解决部署时因为组件循环调用导致的项目无法启动问题。
 * https://www.cnblogs.com/leonkobe/p/6473021.html
 */
public class MyWebApplicationContext extends XmlWebApplicationContext {

    @Override
    protected DefaultListableBeanFactory createBeanFactory() {
        DefaultListableBeanFactory beanFactory = super.createBeanFactory();
        beanFactory.setAllowRawInjectionDespiteWrapping(true);
        return beanFactory;
    }

}