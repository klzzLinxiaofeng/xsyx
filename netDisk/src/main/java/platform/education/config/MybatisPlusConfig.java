
package platform.education.config;

import framework.generic.dao.mybatis.MybatisGenericDao;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.springframework.context.annotation.ImportResource;


@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /***
     * plus 的性能优化
     * @return
     */
    /*@Bean
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        <!-- SQL 执行性能分析，开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长 -->
        performanceInterceptor.setMaxTime(1000);
        <!--SQL是否格式化 默认false-->
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }*/

//    @Bean(name="genericDao")
//    public MybatisGenericDao genericDao(SqlSessionTemplate sqlSessionTemplate){
//
//        MybatisGenericDao genericDao = new MybatisGenericDao();
//        genericDao.setSqlSessionTemplate(sqlSessionTemplate);
//
//        return genericDao;
//    }


//    @Bean
//    @ConditionalOnMissingBean
//    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//        ExecutorType executorType = this.properties.getExecutorType();
//        return executorType != null ? new SqlSessionTemplate(sqlSessionFactory, executorType) : new SqlSessionTemplate(sqlSessionFactory);
//    }
}
