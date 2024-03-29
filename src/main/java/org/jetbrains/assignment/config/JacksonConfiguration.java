package org.jetbrains.assignment.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDate;

@Configuration
public class JacksonConfiguration {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        logger.info("***** Configure Jackson2ObjectMapperBuilder *****");
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        builder.failOnUnknownProperties(false);
        return builder;
    }
}
