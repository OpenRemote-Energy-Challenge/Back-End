package openRemote.demo.CorsConfig;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class UserCORS {

    public WebMvcConfigurer solarCorsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/user")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedOrigins("http://51.75.73.83:3000");

            }
        };
    }
}





