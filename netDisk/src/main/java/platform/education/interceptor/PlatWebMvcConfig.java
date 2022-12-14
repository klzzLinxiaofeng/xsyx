package platform.education.interceptor;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;


/**
 * @author liz
 * @version 1.0
 * @name UnionPlatormWebMvcConfig.java
 * @since 2018年4月20日 下午2:24:47
 */
@Configuration
public class PlatWebMvcConfig extends WebMvcConfigurationSupport {
    // mvc控制器
    // @Configuration
    @Bean
    public HandlerInterceptor getUnionPlatHandlerInterceptor() {
        return new PlatHandlerInterceptor();
    }

    // 增加拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getUnionPlatHandlerInterceptor()).excludePathPatterns("/static/**"); // 指定拦截器类
    }

    /**
     * 添加静态资源文件，外部可以直接访问地址
     *
     * @param registry
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/opt/upload/**").addResourceLocations("file:/opt/upload/");
    }

    /**
     * 长整型数字处理
     */
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(jackson2HttpMessageConverter);
    }
}
