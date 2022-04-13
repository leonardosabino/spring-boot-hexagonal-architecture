package com.hexagonal.template.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import static org.zalando.logbook.BodyFilters.replaceJsonStringProperty;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(
    prefix = "logbook.filter",
    havingValue = "true",
    name = "enabled"
)
public class LogbookConfiguration {

    private final PropertiesLogbook customLogbookProperties;

    @Bean
    public Logbook logbook(final List<HeaderFilter> headerFilters,
        final Predicate<RawHttpRequest> condition) {
        return Logbook.builder()
            .condition(mergeWithExcludes(condition))
            .headerFilters(headerFilters)
            .bodyFilter(jsonBody(customLogbookProperties.getObfuscate().getParameters()))
            .build();
    }

    private BodyFilter jsonBody(List<String> blackListParameters) {
        final Set<String> properties = new HashSet<>(blackListParameters);
        return replaceJsonStringProperty(properties, "XXX");
    }

    private Predicate<RawHttpRequest> mergeWithExcludes(final Predicate<RawHttpRequest> predicate) {
        return customLogbookProperties.getExcludes().stream()
            .map(Conditions::<RawHttpRequest>requestTo)
            .map(Predicate::negate)
            .reduce(predicate, Predicate::and);
    }

}
