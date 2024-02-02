package com.energizer.auto_uz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Set;

@Configuration
public class AppConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public FormattingConversionServiceFactoryBean conversionService(
            Set<Converter<?,?>> converters,
            Set<Formatter<?>> formatters
    ) {
        FormattingConversionServiceFactoryBean formattingConversionService = new FormattingConversionServiceFactoryBean();
        formattingConversionService.setConverters(converters);
        formattingConversionService.setFormatters(formatters);
        formattingConversionService.afterPropertiesSet();
        return formattingConversionService;
    }
    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
