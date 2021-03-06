package openRemote.demo.CorsConfig;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class SolarCorsConfi {
    public WebMvcConfigurer solarCorsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/solar")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedOrigins("http://51.75.73.83:3000");

            }
        };
    }
}

