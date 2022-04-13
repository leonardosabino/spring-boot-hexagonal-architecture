package com.hexagonal.template.config;

import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Needed class in version 2.5.0:spring-boot
 * reference https://github.com/spring-projects/spring-boot/issues/26630
 */
@Configuration
@ConditionalOnProperty(
        prefix = "prometheus",
        havingValue = "true",
        name = "enabled"
)
public class MetricsConfig {

  @Bean
  InitializingBean forcePrometheusPostProcessor(BeanPostProcessor meterRegistryPostProcessor, PrometheusMeterRegistry registry) {
    return () -> meterRegistryPostProcessor.postProcessAfterInitialization(registry, "");
  }

}
