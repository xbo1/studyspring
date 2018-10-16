package com.xbo.studyspring;

import com.xbo.studyspring.config.CustomFilter;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.servlet.Filter;

@SpringBootApplication
@ServletComponentScan //使用FilterRegistrationBean时注释掉
@PropertySource(value="classpath:my.properties",encoding="utf-8")
@MapperScan("com.xbo.studyspring.*.mapper")
@EnableWebSocket
@EnableAsync
@Slf4j
public class StudyspringApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudyspringApplication.class, args);
        log.info("StudySpring 服务启动");
    }
    /**
     * 会自动注册使用了@ServerEndpoint注解声明的Websocket endpoint
     * 要注意，如果使用独立的servlet容器，
     * 而不是直接使用springboot的内置容器，
     * 就不要注入ServerEndpointExporter，因为它将由容器自己提供和管理。
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

//    @Bean
////    public FilterRegistrationBean filterRegistrationBean() {
////        FilterRegistrationBean registration = new FilterRegistrationBean();
////        //当过滤器有注入其他bean类时，可直接通过@bean的方式进行实体类过滤器，这样不可自动注入过滤器使用的其他bean类。
////        //当然，若无其他bean需要获取时，可直接new CustomFilter()，也可使用getBean的方式。
////        registration.setFilter(customFilter());
////        //过滤器名称
////        registration.setName("customFilter");
////        //拦截路径
////        registration.addUrlPatterns("/*");
////        //设置顺序
////        registration.setOrder(10);
////        return registration;
////    }
////
////    @Bean
////    public Filter customFilter() {
////        return new CustomFilter();
////    }
}
