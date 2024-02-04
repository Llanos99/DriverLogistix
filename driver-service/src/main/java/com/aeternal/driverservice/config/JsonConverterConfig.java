package com.aeternal.driverservice.config;

import org.javers.core.json.JsonConverterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonConverterConfig {

    @Bean
    public JsonConverterBuilder customJsonConverter() {
        return new JsonConverterBuilder()
                .prettyPrint(true)
                .registerJsonTypeAdapter(new CustomJsonTypeAdapter());
    }

}
