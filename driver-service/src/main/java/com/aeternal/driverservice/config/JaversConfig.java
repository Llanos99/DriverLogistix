package com.aeternal.driverservice.config;

import com.aeternal.driverservice.model.Driver;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JaversConfig {

    @Bean
    public Javers initializeJavers() {
        return JaversBuilder
                .javers()
                .registerEntities(Driver.class)
                .build();
    }

}
