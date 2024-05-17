package apiBoschEsy.apiInSpringBoot.infra.security.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//@EnableWebMvcSecurity
public class CorsConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry corsRegistry){
//        corsRegistry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("GET", "POST", "PATCH", "OPTIONS");
//    }
}