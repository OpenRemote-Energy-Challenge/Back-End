package openRemote.demo.CorsConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class EnergyCorsConfi {

    @Bean
    public WebMvcConfigurer energyCorsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/energy")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedOrigins("http://localhost:3000");

            }
        };
    }
}