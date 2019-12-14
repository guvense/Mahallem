package com.mahallem.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.xml.validation.Validator;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public BCryptPasswordEncoder by() {
        return new BCryptPasswordEncoder();
    }



}