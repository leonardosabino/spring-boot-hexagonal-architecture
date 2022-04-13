package com.hexagonal.template.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.zalando.logbook.spring.LogbookProperties;

import java.util.List;

@Configuration
@ConfigurationProperties("logbook")
@Getter
@Setter
@Primary
public class PropertiesLogbook {

    private List<String> excludes;
    private LogbookProperties.Obfuscate obfuscate;

}
