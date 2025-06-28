package junesoft.appl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // webapp 디렉토리의 리소스 처리
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
        
        // classpath의 리소스 처리
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
} 