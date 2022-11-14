package platform.education.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 用于加载奇云教学中的Bean
 */
@Configuration
@AutoConfigureAfter(MybatisPlusConfig.class)
@ImportResource(locations={"classpath:config/base-dao.xml","classpath:storage-services.xml","classpath:user-common-services.xml"})
public class MybatisXMLConfig {
}
