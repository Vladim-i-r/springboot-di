package com.vladimir.springboot.di.app.springbootdi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import com.vladimir.springboot.di.app.springbootdi.repositories.ProductRepository;
import com.vladimir.springboot.di.app.springbootdi.repositories.ProductRepositoryJson;

@Configuration
@PropertySource(value="classpath:config.properties", encoding="UTF-8")
public class AppConfig {

    @Value("classpath:json/config.properties")
    private Resource resource;

    @Bean("productJson")
    ProductRepository productRepositoryJson(){
        return new ProductRepositoryJson();
        //return new ProductRepositoryJson(resource);
    }
}
 