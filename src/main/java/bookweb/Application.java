package bookweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication()
@EntityScan("bookweb.domain.entity")

public class Application {
    @Bean
    public FilterRegistrationBean<CorsFilter> simpleCorsFilter(){

        List<String> origins = new ArrayList<>();
        origins.add("http://localhost:4200");
        List<String> methodsHeaders = new ArrayList<>();
        methodsHeaders.add("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedOrigins(origins);
        config.setAllowedHeaders(methodsHeaders);
        config.setAllowedMethods(methodsHeaders);
        source.registerCorsConfiguration("/**", config);

        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));

        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
