package Controllers;

import java.io.File;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
    
@SpringBootApplication
public class Application {
    private final String ACCEPTED_ORIGION = "https://projectntk.000webhostapp.com";
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/sites").allowedOrigins(ACCEPTED_ORIGION);
                registry.addMapping("/site/resource").allowedOrigins(ACCEPTED_ORIGION);
                registry.addMapping("/login").allowedOrigins(ACCEPTED_ORIGION);
                registry.addMapping("/content/vula").allowedOrigins(ACCEPTED_ORIGION);
                registry.addMapping("/ingest").allowedOrigins(ACCEPTED_ORIGION);
                registry.addMapping("/assignment").allowedOrigins(ACCEPTED_ORIGION);
                registry.addMapping("/repository/content").allowedOrigins(ACCEPTED_ORIGION);
                registry.addMapping("/").allowedOrigins(ACCEPTED_ORIGION);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
