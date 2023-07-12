package com.renewtrak.glossary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
        @PropertySource(value = {"classpath:messages.properties"}, ignoreResourceNotFound = true)
})
public class GlossaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlossaryApplication.class, args);
    }

}
