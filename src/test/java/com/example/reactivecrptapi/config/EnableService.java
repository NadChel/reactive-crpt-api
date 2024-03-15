package com.example.reactivecrptapi.config;

import com.example.reactivecrptapi.config.EnableService.ServiceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ServiceConfig.class)
public @interface EnableService {
    @Configuration
    @ComponentScan(basePackages = "com.example.reactivecrptapi.service")
    class ServiceConfig {
    }
}
