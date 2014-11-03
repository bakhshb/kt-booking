package com.kiwianatours.ktbooking.config.metrics;

import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.sendgrid.SendGrid;

import javax.inject.Inject;
//import javax.sql.DataSource;

@Configuration
public class JHipsterHealthIndicatorConfiguration {
    
    @Inject
    private SendGrid sendGridSender;

    /*@Inject
    private DataSource dataSource;

    @Bean
    public HealthIndicator dbHealthIndicator() {
        return new DatabaseHealthIndicator(dataSource);
    }*/

    @Bean
    public HealthIndicator mailHealthIndicator() {
    	return new JavaMailHealthIndicator(sendGridSender);
    }
}
