package com.fob.tasks.emre.server;

import static com.google.inject.Stage.PRODUCTION;

//import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fob.tasks.emre.common.InjectionManager;
//import com.fob.tasks.emre.common.PropertyManager;
import com.google.inject.Guice;
import com.google.inject.Injector;

@SpringBootApplication
public class RestApiServer {

    public static Injector injector;

    private static Logger log = LogManager.getLogger(RestApiServer.class);
    
    public static void main(String[] args) {
        log.info("RestApiServer is being initialized..");
//        try {
//            PropertyManager.loadProperties();
//        } catch (IOException e) {
//            log.error("Properties cannot be loaded!", e);
//            return;
//        }
        InjectionManager.loadInjector(Guice.createInjector(PRODUCTION, new RestApiModule()));
        SpringApplication.run(RestApiServer.class, args);
        log.info("RestApiServer is initialized.");
    }
}