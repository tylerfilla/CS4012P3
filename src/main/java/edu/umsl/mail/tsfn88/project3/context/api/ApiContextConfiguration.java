/*
 * Tyler Filla
 * CS 4012
 * Project 3
 */

package edu.umsl.mail.tsfn88.project3.context.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * Configuration for the API context.
 */
@Configuration
@ComponentScan
@EnableWebMvc
public class ApiContextConfiguration implements WebMvcConfigurer {

    private static final Logger log = LogManager.getLogger();

    @Inject ObjectMapper jsonObjectMapper;

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        log.trace("configureContextNegotiation");

        // Prefer application/json content
        configurer.favorPathExtension(false)
                .favorParameter(false)
                .ignoreAcceptHeader(false)
                .defaultContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.trace("configureMessageConverters");

        // Add FasterXML Jackson message converter
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setSupportedMediaTypes(Arrays.asList(
                new MediaType("application", "json"),
                new MediaType("text", "json")
        ));
        jsonConverter.setObjectMapper(jsonObjectMapper);
        converters.add(jsonConverter);
    }

    @Bean
    public ObjectMapper jsonObjectMapper() {
        log.trace("jsonObjectMapper");

        // Set up FasterXML Jackson object mapper
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        return mapper;
    }

}
