package com.hexagonal.template.dataSource.restclient.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"com.hexagonal.template.dataSources.restclient"})
public class FeignConfig {
}
