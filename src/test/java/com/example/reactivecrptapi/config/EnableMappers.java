package com.example.reactivecrptapi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.example.reactivecrptapi.config.EnableMappers.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MappersConfig.class)
public @interface EnableMappers {
    @Configuration
    @ComponentScan(basePackages = "com.example.reactivecrptapi.mapper")
    class MappersConfig {
    }
}
