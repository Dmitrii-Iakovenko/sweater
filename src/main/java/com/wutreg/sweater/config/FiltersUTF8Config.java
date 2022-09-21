package com.wutreg.sweater.config;

import org.springframework.boot.web.servlet.filter.OrderedCharacterEncodingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@Configuration
public class FiltersUTF8Config {

    @Bean
    // https://github.com/spring-projects/spring-boot/issues/3912
    public OrderedCharacterEncodingFilter setUTF8OrderedCharacterEncodingFilter() {
        var filter = new OrderedCharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        filter.setOrder(HIGHEST_PRECEDENCE);
        return filter;
    }

}