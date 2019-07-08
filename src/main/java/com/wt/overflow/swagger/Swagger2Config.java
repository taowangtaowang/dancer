package com.wt.overflow.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {"com.wt.overflow.controller.sys"})
@EnableWebMvc
public class Swagger2Config extends WebMvcConfigurationSupport {

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()   // 选择那些路径和api会生成document
                //.apis(RequestHandlerSelectors.basePackage("com.wt.overflow.controller.sys"))
                .apis(RequestHandlerSelectors.any())  // 对所有api进行监控
                //.paths(PathSelectors.regex("/api/.*"))
                .paths(PathSelectors.any())   // 对所有路径进行监控
                .build()
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("dancer-API")
                .termsOfServiceUrl("www.wt990099.cn")
                .description("此API提供给前端人员调用")
                .license("License Version 2.0")
                .licenseUrl("www.wt990099.cn")
                .version("2.0").build();
    }


    /*@Bean
    public Docket customDocket() {
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
    }*/

}
