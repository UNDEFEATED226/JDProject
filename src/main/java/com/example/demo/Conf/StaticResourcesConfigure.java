package com.example.demo.Conf;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
@Slf4j
public class StaticResourcesConfigure extends WebMvcConfigurationSupport {
	
	private static final Logger log = LoggerFactory.getLogger(StaticResourcesConfigure.class);

    public String getPath() {
        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        if (System.getProperty("os.name").contains("dows")) {
            path = path.substring(1, path.length());
        }
        if (path.contains("jar")) {
            path = path.substring(0, path.lastIndexOf("."));
            return path.substring(0, path.lastIndexOf("/"));
        }
        return path.replace("target/classes/", "");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        String loadResourcePath = getPath() + "/frontend/static/";
        log.info("resourcePath is {}", loadResourcePath);
       
        //控制台静态资源
        registry.addResourceHandler("/static/**")
                .addResourceLocations(loadResourcePath);

        registry.addResourceHandler("/index.html")
                .addResourceLocations(getPath() + "/frontend/");

        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations(getPath() + "/frontend/");
        super.addResourceHandlers(registry);
    }


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {

        //registry.addInterceptor(userAuditInterceptor).addPathPatterns("/**");
    }
}

