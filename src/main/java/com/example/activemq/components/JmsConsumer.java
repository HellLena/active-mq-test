package com.example.activemq.components;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsConsumer {

  @JmsListener(destination = "simple-jms-queue", containerFactory = "jmsListenerContainerFactoryOne")
  public void listener(String msg) {
    System.out.println("Received Message : " + msg);
  }
}