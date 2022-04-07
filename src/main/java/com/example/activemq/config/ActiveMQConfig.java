package com.example.activemq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

@EnableJms
@Configuration
public class ActiveMQConfig {

  @Value("${spring.activemq.broker-url}")
  private String brokerUrl;

  @Bean
  public Queue queue(){
    return new ActiveMQQueue("simple-jms-queue");
  }

  @Bean
  public ActiveMQConnectionFactory connectionFatory(){
    ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
    factory.setBrokerURL(brokerUrl);
    return factory;
  }

  @Bean
  public JmsTemplate jmsTemplate(){
    return new JmsTemplate(connectionFatory());
  }

}
