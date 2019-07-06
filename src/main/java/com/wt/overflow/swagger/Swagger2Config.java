package com.wt.overflow.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {"com.wt.overflow.controller.sys"})
@EnableWebMvc
public class Swagger2Config {

    @Bean
    public Docket customDocket() {
        //
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("老王", "https://www.baidu.com", "8822376@qq.com");
        return new ApiInfo("dancer前台API接口",//大标题 title
                "Swagger测试demo",//小标题
                "0.0.1",//版本
                "www.baidu.com",//termsOfServiceUrl
                contact,//作者
                "dancer",//链接显示文字
                "https://www.baidu.com"//网站链接
        );
    }

}
