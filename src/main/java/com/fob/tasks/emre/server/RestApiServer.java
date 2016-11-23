package com.fob.tasks.emre.server;

import static com.google.inject.Stage.PRODUCTION;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fob.tasks.emre.common.InjectionManager;
import com.fob.tasks.emre.common.PropertyManager;
import com.google.inject.Guice;

/**
 * Application server
 * @author Emre
 */
@SpringBootApplication
public class RestApiServer {

    private static Logger log = LogManager.getLogger(RestApiServer.class);

    public static void main(String[] args) {
        log.info("RestApiServer is being initialized..");
        try {
            PropertyManager.loadProperties();
        } catch (SQLException e) {
            log.error("Properties cannot be loaded!", e);
            return;
        }
        InjectionManager.loadInjector(Guice.createInjector(PRODUCTION, new RestApiModule()));
        SpringApplication.run(RestApiServer.class, args);
        log.info("RestApiServer is initialized.");
    }

    /**
     * Configure cors to allow all origins
     * @return
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/stackservice/*").allowedOrigins("*");
            }
        };
    }
}