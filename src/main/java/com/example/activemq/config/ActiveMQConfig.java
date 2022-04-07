package com.example.activemq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

@EnableJms
@Configuration
public class ActiveMQConfig {

  @Value("${spring.activemq.broker-url-1}")
  private String brokerUrl1;

  @Value("${spring.activemq.broker-url-2}")
  private String brokerUrl2;

  @Bean
  public Queue queue() {
    return new ActiveMQQueue("simple-jms-queue");
  }

  // --------------------- SETTINGS FOR BROKER 1 ----------------------- //

  @Bean
  @Primary
  public ActiveMQConnectionFactory connectionFactoryOne() {
    ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
    factory.setBrokerURL(brokerUrl1);
    return factory;
  }

  @Bean
  public JmsListenerContainerFactory<?> jmsListenerContainerFactoryOne(
      ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer
  ) {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    configurer.configure(factory, connectionFactory);
    return factory;
  }

  @Bean
  @Primary
  public JmsTemplate jmsTemplateOne() {
    return new JmsTemplate(connectionFactoryOne());
  }

  // --------------------- SETTINGS FOR BROKER 2 ----------------------- //

  @Bean
  public ActiveMQConnectionFactory connectionFactoryTwo() {
    ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
    factory.setBrokerURL(brokerUrl2);
    return factory;
  }

  @Bean
  public JmsListenerContainerFactory<?> jmsListenerContainerFactoryTwo(
      @Qualifier("connectionFactoryTwo") ConnectionFactory connectionFactory,
      DefaultJmsListenerContainerFactoryConfigurer configurer
  ) {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    configurer.configure(factory, connectionFactory);
    return factory;
  }

  @Bean
  public JmsTemplate jmsTemplateTwo() {
    return new JmsTemplate(connectionFactoryTwo());
  }

}
