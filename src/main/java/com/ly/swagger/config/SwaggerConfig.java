package com.ly.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

//从Spring3.0，@Configuration用于定义配置类，可替换xml配置文件，被注解的类内部包含有一个或多个被@Bean注解的方法，
// 这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描，
// 并用于构建bean定义，初始化Spring容器。
@Configuration
@EnableSwagger2  //开启swagger2
public class SwaggerConfig {

    //配置了Swagger的Docket的Bean实例
    @Bean
    public Docket docket(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //.enable(false)        //enable是否启动swagger，若为false，则swagger不能在浏览器中访问
                .groupName("这波我的")
                .select()
                //RequestHandlerSelectors配置要扫描接口的方式
                //basePackage:指定要扫描的包
                //any:扫描全部
                //withClassAnnotation:扫描类上的注解
                //withMethodAnnotation():扫描方法上的注解
                //.apis(RequestHandlerSelectors.withMethodAnnotation())
                //.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .apis(RequestHandlerSelectors.basePackage("com.ly.swagger.controller"))
                //path():过滤什么路径
                //.paths(PathSelectors.ant("/ly/**"))
                .build();
        return docket;
    }

    //配置Swagger的信息：apiInfo
    private ApiInfo apiInfo(){
        //作者信息
        Contact DEFAULT_CONTACT = new Contact("刘洋", "https://blog.csdn.net/", "aowu_ly@163.com");
        return new ApiInfo("Api Documentation", "Api Documentation", "1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());

    }

}
