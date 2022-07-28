package com.makichanov.audit.configuration;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.ConnectionFactory;

@EnableJms
@Configuration
public class JmsConfig {

    @Bean
    public ConnectionFactory connectionFactory(){
        var factory = new ActiveMQConnectionFactory();

        factory.setBrokerURL("tcp://localhost:61616");
        factory.setTrustAllPackages(true);

        return factory;
    }

}